package com.google.android.accessibility.selecttospeak;

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
    public static final NoPiiString COMPONENT_NAME = new NoPiiString("SELECT_TO_SPEAK");
    private final Map timerEventMap = new EnumMap(Timer.class);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Timer {
        S2S_START_UP(new NoPiiString("SelectToSpeak_StartUp")),
        S2S_WARM_START(new NoPiiString("SelectToSpeak_UI_Inflation")),
        S2S_UI_INFLATION(new NoPiiString("SelectToSpeak_UI_Inflation"));

        final NoPiiString noPiiString;

        Timer(NoPiiString noPiiString) {
            this.noPiiString = noPiiString;
        }
    }

    public static boolean getBoolean$ar$ds$53176685_0(Context context, String str) {
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

    public final void stopTimer(Timer timer) {
        TimerEvent timerEvent = (TimerEvent) this.timerEventMap.remove(timer);
        if (timerEvent != null) {
            Primes.get().stopTimer(timerEvent, timer.noPiiString);
        }
    }
}
