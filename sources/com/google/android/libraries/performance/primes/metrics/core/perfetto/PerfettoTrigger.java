package com.google.android.libraries.performance.primes.metrics.core.perfetto;

import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PerfettoTrigger {
    private volatile Process lastTriggerProcess;
    private final Stopwatch stopwatch;
    public volatile boolean hasFailed = false;
    public final Function newPerfettoProcessForTriggerName = new AiCoreClientImpl$$ExternalSyntheticLambda2(this, 2);

    public PerfettoTrigger(Ticker ticker) {
        this.stopwatch = new Stopwatch(ticker);
    }

    public final void trigger(String str) {
        if (!str.isEmpty()) {
            if (this.lastTriggerProcess != null) {
                try {
                    if (this.lastTriggerProcess.exitValue() != 0) {
                        this.hasFailed = true;
                        this.lastTriggerProcess = null;
                    }
                } catch (IllegalThreadStateException unused) {
                    return;
                }
            }
            if (!this.hasFailed) {
                synchronized (this) {
                    Stopwatch stopwatch = this.stopwatch;
                    if (stopwatch.isRunning && stopwatch.elapsed(TimeUnit.MILLISECONDS) < 60000) {
                        return;
                    }
                    this.stopwatch.reset$ar$ds();
                    this.stopwatch.start$ar$ds$db96ddcc_0();
                    this.lastTriggerProcess = (Process) this.newPerfettoProcessForTriggerName.apply(str);
                }
            }
        }
    }
}
