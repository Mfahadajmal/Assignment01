package com.google.android.accessibility.talkback.monitor;

import android.content.Intent;
import com.google.android.accessibility.talkback.Interpretation$Power;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMonitor extends SameThreadBroadcastReceiver {
    private int batteryLevel = -1;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
    public final void onReceiveIntent(Intent intent) {
        char c;
        String action = intent.getAction();
        if (action != null) {
            int hashCode = action.hashCode();
            int i = -1;
            if (hashCode != -1886648615) {
                if (hashCode != -1538406691) {
                    if (hashCode == 1019184907 && action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        c = 2;
                    }
                    c = 65535;
                } else {
                    if (action.equals("android.intent.action.BATTERY_CHANGED")) {
                        c = 0;
                    }
                    c = 65535;
                }
            } else {
                if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                    c = 1;
                }
                c = 65535;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        return;
                    }
                    HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    Logger logger = Performance.DEFAULT_LOGGER;
                    hapticPatternParser$$ExternalSyntheticLambda1.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$Power(true, this.batteryLevel), null);
                    return;
                }
                HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12 = this.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                Logger logger2 = Performance.DEFAULT_LOGGER;
                hapticPatternParser$$ExternalSyntheticLambda12.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$Power(false, this.batteryLevel), null);
                return;
            }
            int intExtra = intent.getIntExtra("level", 0);
            int intExtra2 = intent.getIntExtra("scale", 100);
            if (intExtra2 > 0) {
                i = Math.round((intExtra / intExtra2) * 100.0f);
            }
            this.batteryLevel = i;
        }
    }
}
