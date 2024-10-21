package com.google.android.gms.common;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.TypedValue;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.DialogRedirect;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    private static final Object lock = new Object();
    public static final GoogleApiAvailability INSTANCE = new GoogleApiAvailability();
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NotificationHandler extends TracingHandler {
        private final Context applicationContext;

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public NotificationHandler(android.content.Context r2) {
            /*
                r0 = this;
                com.google.android.gms.common.GoogleApiAvailability.this = r1
                android.os.Looper r1 = android.os.Looper.myLooper()
                if (r1 != 0) goto Ld
                android.os.Looper r1 = android.os.Looper.getMainLooper()
                goto L11
            Ld:
                android.os.Looper r1 = android.os.Looper.myLooper()
            L11:
                r0.<init>(r1)
                android.content.Context r1 = r2.getApplicationContext()
                r0.applicationContext = r1
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleApiAvailability.NotificationHandler.<init>(com.google.android.gms.common.GoogleApiAvailability, android.content.Context):void");
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                Log.w("GoogleApiAvailability", "Don't know how to handle this message: " + message.what);
                return;
            }
            int isGooglePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.applicationContext);
            int i = GooglePlayServicesUtilLight.GooglePlayServicesUtilLight$ar$NoOp;
            if (isGooglePlayServicesAvailable != 1 && isGooglePlayServicesAvailable != 2 && isGooglePlayServicesAvailable != 3 && isGooglePlayServicesAvailable != 9) {
                return;
            }
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.this;
            Context context = this.applicationContext;
            googleApiAvailability.showErrorNotification$ar$ds(context, isGooglePlayServicesAvailable, googleApiAvailability.getErrorResolutionPendingIntent$ar$ds(context, isGooglePlayServicesAvailable, "n"));
        }
    }

    public final Dialog getErrorDialog$ar$ds(Context context, int i, DialogRedirect dialogRedirect, DialogInterface.OnCancelListener onCancelListener) {
        String string;
        AlertDialog.Builder builder = null;
        if (i == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        if ("Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId))) {
            builder = new AlertDialog.Builder(context, 5);
        }
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(ConnectionErrorMessages.getErrorMessage(context, i));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        Resources resources = context.getResources();
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    string = resources.getString(R.string.ok);
                } else {
                    string = resources.getString(com.google.android.marvin.talkback.R.string.common_google_play_services_enable_button);
                }
            } else {
                string = resources.getString(com.google.android.marvin.talkback.R.string.common_google_play_services_update_button);
            }
        } else {
            string = resources.getString(com.google.android.marvin.talkback.R.string.common_google_play_services_install_button);
        }
        if (string != null) {
            builder.setPositiveButton(string, dialogRedirect);
        }
        String errorTitle = ConnectionErrorMessages.getErrorTitle(context, i);
        if (errorTitle != null) {
            builder.setTitle(errorTitle);
        }
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(i)), new IllegalArgumentException());
        return builder.create();
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final PendingIntent getErrorResolutionPendingIntent$ar$ds(Context context, int i, String str) {
        return super.getErrorResolutionPendingIntent$ar$ds(context, i, str);
    }

    public final void showDialogFragment(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof FragmentActivity) {
                FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
                SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(dialog, "Cannot display null dialog");
                dialog.setOnCancelListener(null);
                dialog.setOnDismissListener(null);
                supportErrorDialogFragment.dialog = dialog;
                if (onCancelListener != null) {
                    supportErrorDialogFragment.cancelListener = onCancelListener;
                }
                supportErrorDialogFragment.show(supportFragmentManager, str);
                return;
            }
        } catch (NoClassDefFoundError unused) {
        }
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(dialog, "Cannot display null dialog");
        dialog.setOnCancelListener(null);
        dialog.setOnDismissListener(null);
        errorDialogFragment.dialog = dialog;
        if (onCancelListener != null) {
            errorDialogFragment.cancelListener = onCancelListener;
        }
        errorDialogFragment.show(fragmentManager, str);
    }

    public final void showErrorDialogFragment$ar$ds(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        Dialog errorDialog$ar$ds = getErrorDialog$ar$ds(activity, i, new DialogRedirect() { // from class: com.google.android.gms.common.internal.DialogRedirect.1
            final /* synthetic */ Activity val$activity;
            final /* synthetic */ Intent val$intent;
            final /* synthetic */ int val$requestCode;

            public AnonymousClass1(Intent intent, Activity activity2, int i22) {
                r1 = intent;
                r2 = activity2;
                r3 = i22;
            }

            @Override // com.google.android.gms.common.internal.DialogRedirect
            public final void redirect() {
                Intent intent = r1;
                if (intent != null) {
                    r2.startActivityForResult(intent, r3);
                }
            }
        }, onCancelListener);
        if (errorDialog$ar$ds == null) {
            return;
        }
        showDialogFragment(activity2, errorDialog$ar$ds, "GooglePlayServicesErrorDialog", onCancelListener);
    }

    public final void showErrorNotification$ar$ds(Context context, int i, PendingIntent pendingIntent) {
        String errorTitle;
        String remoteMessage;
        NotificationChannel notificationChannel;
        CharSequence name;
        int i2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i), null), new IllegalArgumentException());
        if (i == 18) {
            new NotificationHandler(this, context).sendEmptyMessageDelayed(1, 120000L);
            return;
        }
        if (pendingIntent == null) {
            if (i == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        if (i == 6) {
            errorTitle = ConnectionErrorMessages.getRemoteString(context, "common_google_play_services_resolution_required_title");
        } else {
            errorTitle = ConnectionErrorMessages.getErrorTitle(context, i);
        }
        if (errorTitle == null) {
            errorTitle = context.getResources().getString(com.google.android.marvin.talkback.R.string.common_google_play_services_notification_ticker);
        }
        if (i != 6 && i != 19) {
            remoteMessage = ConnectionErrorMessages.getErrorMessage(context, i);
        } else {
            remoteMessage = ConnectionErrorMessages.getRemoteMessage(context, "common_google_play_services_resolution_required_text", ConnectionErrorMessages.getAppName(context));
        }
        Resources resources = context.getResources();
        Object systemService = context.getSystemService("notification");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(systemService);
        NotificationManager notificationManager = (NotificationManager) systemService;
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context, null);
        notificationCompat$Builder.mLocalOnly = true;
        notificationCompat$Builder.setAutoCancel$ar$ds(true);
        notificationCompat$Builder.setContentTitle$ar$ds(errorTitle);
        NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
        notificationCompat$BigTextStyle.bigText$ar$ds(remoteMessage);
        notificationCompat$Builder.setStyle$ar$ds(notificationCompat$BigTextStyle);
        boolean isWearable = DeviceProperties.isWearable(context);
        int i3 = R.drawable.stat_sys_warning;
        if (isWearable) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(true);
            int i4 = context.getApplicationInfo().icon;
            if (i4 != 0) {
                i3 = i4;
            }
            notificationCompat$Builder.setSmallIcon$ar$ds(i3);
            notificationCompat$Builder.mPriority = 2;
            if (DeviceProperties.isWearableWithoutPlayStore(context)) {
                notificationCompat$Builder.addAction$ar$ds(com.google.android.marvin.talkback.R.drawable.common_full_open_on_phone, resources.getString(com.google.android.marvin.talkback.R.string.common_open_on_phone), pendingIntent);
            } else {
                notificationCompat$Builder.mContentIntent = pendingIntent;
            }
        } else {
            notificationCompat$Builder.setSmallIcon$ar$ds(R.drawable.stat_sys_warning);
            notificationCompat$Builder.setTicker$ar$ds(resources.getString(com.google.android.marvin.talkback.R.string.common_google_play_services_notification_ticker));
            notificationCompat$Builder.setWhen$ar$ds(System.currentTimeMillis());
            notificationCompat$Builder.mContentIntent = pendingIntent;
            notificationCompat$Builder.setContentText$ar$ds(remoteMessage);
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(true);
        synchronized (lock) {
        }
        notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
        String string = context.getResources().getString(com.google.android.marvin.talkback.R.string.common_google_play_services_notification_channel_name);
        if (notificationChannel != null) {
            name = notificationChannel.getName();
            if (!string.contentEquals(name)) {
                notificationChannel.setName(string);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        } else {
            notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string, 4));
        }
        notificationCompat$Builder.mChannelId = "com.google.android.gms.availability";
        Notification build = notificationCompat$Builder.build();
        if (i != 1 && i != 2 && i != 3) {
            i2 = 39789;
        } else {
            GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
            i2 = 10436;
        }
        notificationManager.notify(i2, build);
    }
}
