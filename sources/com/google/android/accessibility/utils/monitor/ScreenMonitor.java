package com.google.android.accessibility.utils.monitor;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenMonitor extends BroadcastReceiver {
    public static final IntentFilter SCREEN_CHANGE_FILTER;
    private final PowerManager powerManager;
    private final ScreenStateChangeListener screenStateListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ScreenStateChangeListener {
        void screenTurnedOff();
    }

    static {
        IntentFilter intentFilter = new IntentFilter();
        SCREEN_CHANGE_FILTER = intentFilter;
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
    }

    public ScreenMonitor(PowerManager powerManager, ScreenStateChangeListener screenStateChangeListener) {
        this.powerManager = powerManager;
        this.screenStateListener = screenStateChangeListener;
        if (powerManager != null) {
            powerManager.isInteractive();
        }
    }

    public static boolean isDeviceLocked(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
        if (keyguardManager != null && keyguardManager.isKeyguardLocked()) {
            return true;
        }
        return false;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        ScreenStateChangeListener screenStateChangeListener;
        String action = intent.getAction();
        if (action != null && action.hashCode() == -2128145023 && action.equals("android.intent.action.SCREEN_OFF") && (screenStateChangeListener = this.screenStateListener) != null) {
            screenStateChangeListener.screenTurnedOff();
        }
    }
}
