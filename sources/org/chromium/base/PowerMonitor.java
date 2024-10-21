package org.chromium.base;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.PowerManager$OnThermalStatusChangedListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PowerMonitor {
    private static PowerMonitor sInstance;
    private boolean mIsBatteryPower;

    private PowerMonitor() {
    }

    public static void create() {
        PowerManager powerManager;
        if (sInstance == null) {
            Context context = ContextUtils.sApplicationContext;
            sInstance = new PowerMonitor();
            Intent registerProtectedBroadcastReceiver = ContextUtils.registerProtectedBroadcastReceiver(context, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerProtectedBroadcastReceiver != null) {
                boolean z = false;
                if (registerProtectedBroadcastReceiver.getIntExtra("plugged", 0) == 0) {
                    z = true;
                }
                onBatteryChargingChanged(z);
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
            intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
            ContextUtils.registerProtectedBroadcastReceiver(context, new BroadcastReceiver() { // from class: org.chromium.base.PowerMonitor.1
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context2, Intent intent) {
                    PowerMonitor.onBatteryChargingChanged(intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED"));
                }
            }, intentFilter);
            if (Build.VERSION.SDK_INT >= 29 && (powerManager = (PowerManager) context.getSystemService("power")) != null) {
                powerManager.addThermalStatusListener(new PowerManager$OnThermalStatusChangedListener() { // from class: org.chromium.base.PowerMonitorForQ$1
                    public final void onThermalStatusChanged(int i) {
                        N.MQNVaF2F(i);
                    }
                });
            }
        }
    }

    private static int getCurrentThermalStatus() {
        int currentThermalStatus;
        if (Build.VERSION.SDK_INT >= 29) {
            if (sInstance == null) {
                create();
            }
            PowerManager powerManager = (PowerManager) ContextUtils.sApplicationContext.getSystemService("power");
            if (powerManager != null) {
                currentThermalStatus = powerManager.getCurrentThermalStatus();
                return currentThermalStatus;
            }
            return -1;
        }
        return -1;
    }

    private static int getRemainingBatteryCapacity() {
        if (sInstance == null) {
            create();
        }
        return ((BatteryManager) ContextUtils.sApplicationContext.getSystemService("batterymanager")).getIntProperty(1);
    }

    private static boolean isBatteryPower() {
        if (sInstance == null) {
            create();
        }
        return sInstance.mIsBatteryPower;
    }

    public static void onBatteryChargingChanged(boolean z) {
        sInstance.mIsBatteryPower = z;
        N.MCImhGql();
    }
}
