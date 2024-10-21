package com.google.android.accessibility.accessibilitymenu;

import android.content.Context;
import com.google.android.gsf.Gservices;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesController {
    public static final NoPiiString COMPONENT_NAME = new NoPiiString("AccessibilityMenu");
    private final Map timerEventMap = new EnumMap(Timer.class);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Timer {
        CONFIG_LAYOUT(new NoPiiString("AccessibilityMenu_ConfigLayout")),
        CLICK_SHORTCUT(new NoPiiString("AccessibilityMenu_ClickShortcut."));

        final NoPiiString noPiiString;

        Timer(NoPiiString noPiiString) {
            this.noPiiString = noPiiString;
        }
    }

    public static boolean getBoolean$ar$ds(Context context, String str) {
        try {
            return Gservices.getBoolean$ar$ds$5696552e_0(context.getContentResolver(), str);
        } catch (SecurityException e) {
            LogUtils.e("PrimesController", e, "Failed to read Gservices.", new Object[0]);
            return false;
        }
    }

    public final void startTimer(Timer timer) {
        if (!this.timerEventMap.containsKey(timer)) {
            this.timerEventMap.put(timer, Primes.get().startTimer());
        }
    }

    public final void stopTimer(Timer timer, Enum r5) {
        TimerEvent timerEvent = (TimerEvent) this.timerEventMap.remove(timer);
        if (timerEvent != null) {
            NoPiiString noPiiString = timer.noPiiString;
            if (r5 != null) {
                noPiiString = NoPiiString.concat(noPiiString, NoPiiString.fromEnum(r5));
            }
            Primes.get().stopTimer(timerEvent, noPiiString);
        }
    }
}
