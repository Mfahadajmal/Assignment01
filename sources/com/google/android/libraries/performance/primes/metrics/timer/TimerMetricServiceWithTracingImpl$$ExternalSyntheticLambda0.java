package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0 implements Callable {
    private final /* synthetic */ int switching_field;

    @Override // java.util.concurrent.Callable
    public final Object call() {
        int i = this.switching_field;
        if (i != 0 && i != 1 && i != 2 && i != 3 && i != 4) {
            GmsLogger gmsLogger = MobileVisionBase.GMS_LOGGER;
        }
        return null;
    }
}
