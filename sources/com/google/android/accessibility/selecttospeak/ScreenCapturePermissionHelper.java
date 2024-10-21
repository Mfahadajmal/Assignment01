package com.google.android.accessibility.selecttospeak;

import android.app.AppOpsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.selecttospeak.activities.ScreenCaptureNotificationActivity;
import com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils;
import com.google.android.accessibility.utils.compat.CompatUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenCapturePermissionHelper {
    private final LocalBroadcastManager broadcastManager;
    private final Context context;
    public ScreenCapturePermissionListener listener;
    public final ScreenCaptureController screenCaptureController;
    private final BroadcastReceiver screenCaptureNotificationReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("com.google.android.accessibility.selecttospeak.screencapturenotification.ACTION_CONTINUE".equals(intent.getAction())) {
                ScreenCapturePermissionHelper screenCapturePermissionHelper = ScreenCapturePermissionHelper.this;
                screenCapturePermissionHelper.screenCaptureController.authorizeCaptureAsync(screenCapturePermissionHelper.screenCaptureControllerListener);
            }
            LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
        }
    };
    public final ScreenCaptureController.AuthorizationListener screenCaptureControllerListener = new ScreenCaptureController.AuthorizationListener() { // from class: com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper.2
        @Override // com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.AuthorizationListener
        public final void onAuthorizationFinished(boolean z) {
            ScreenCapturePermissionListener screenCapturePermissionListener = ScreenCapturePermissionHelper.this.listener;
            if (screenCapturePermissionListener != null) {
                screenCapturePermissionListener.onAuthorizationFinished(z);
            }
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ScreenCapturePermissionListener {
        void onAuthorizationFinished(boolean z);
    }

    public ScreenCapturePermissionHelper(Context context, ScreenCaptureController screenCaptureController) {
        this.context = context;
        this.broadcastManager = LocalBroadcastManager.getInstance(context);
        this.screenCaptureController = screenCaptureController;
    }

    public final void deauthorizeCapture() {
        this.screenCaptureController.deauthorizeCapture();
    }

    public final boolean isAuthorizedForScreenCapture() {
        return this.screenCaptureController.canRequestScreenCapture();
    }

    public final void requestForPermission(ScreenCapturePermissionListener screenCapturePermissionListener) {
        int i;
        this.listener = screenCapturePermissionListener;
        String packageName = this.context.getPackageName();
        try {
            i = this.context.getPackageManager().getApplicationInfo(packageName, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.w("ScreenCapPermissionHelp", "Unable to resolve ApplicationInfo for %1$s", packageName);
            i = -1;
        }
        if (i > 0) {
            if (((Integer) CompatUtils.invoke((AppOpsManager) this.context.getSystemService("appops"), -1, AppOpsManagerCompatUtils.METHOD_checkOpNoThrow, Integer.valueOf(AppOpsManagerCompatUtils.OP_PROJECT_MEDIA), Integer.valueOf(i), packageName)).intValue() == 0) {
                this.screenCaptureController.authorizeCaptureAsync(this.screenCaptureControllerListener);
                return;
            }
        }
        LocalBroadcastManager localBroadcastManager = this.broadcastManager;
        BroadcastReceiver broadcastReceiver = this.screenCaptureNotificationReceiver;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.accessibility.selecttospeak.screencapturenotification.ACTION_CONTINUE");
        intentFilter.addAction("com.google.android.accessibility.selecttospeak.screencapturenotification.ACTION_CANCEL");
        localBroadcastManager.registerReceiver(broadcastReceiver, intentFilter);
        Intent intent = new Intent(this.context, (Class<?>) ScreenCaptureNotificationActivity.class);
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }
}
