package com.google.android.libraries.accessibility.utils.screencapture;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScreenshotAuthProxyActivity extends Activity {
    private LocalBroadcastManager broadcastManager;

    @Override // android.app.Activity
    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1000) {
            LogUtils.e("ScreenshotAuthProxyActivity", "Incorrect request code for activity result", new Object[0]);
            return;
        }
        if (i2 == -1) {
            Intent intent2 = new Intent("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_AUTHORIZED");
            intent2.putExtra("com.google.android.libraries.accessibility.utils.screencapture.EXTRA_SCREEN_CAPTURE_AUTH_INTENT", intent);
            this.broadcastManager.sendBroadcast$ar$ds(intent2);
        } else {
            this.broadcastManager.sendBroadcast$ar$ds(new Intent("com.google.android.libraries.accessibility.utils.screencapture.ACTION_SCREEN_CAPTURE_NOT_AUTHORIZED"));
        }
        finish();
    }

    @Override // android.app.Activity
    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.broadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = (Intent) getIntent().getParcelableExtra("com.google.android.libraries.accessibility.utils.screencapture.EXTRA_SCREEN_CAPTURE_INTENT");
        if (intent == null) {
            LogUtils.e("ScreenshotAuthProxyActivity", "Could not start authorization as no MediaProjection intent was provided.", new Object[0]);
            finish();
        }
        startActivityForResult(intent, 1000);
    }
}
