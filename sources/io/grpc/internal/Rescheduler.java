package io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Rescheduler {
    public boolean enabled;
    public long runAtNanos;
    public final Runnable runnable;
    public final ScheduledExecutorService scheduler;
    public final Executor serializingExecutor;
    private final Stopwatch stopwatch;
    public ScheduledFuture wakeUp;

    public Rescheduler(Runnable runnable, Executor executor, ScheduledExecutorService scheduledExecutorService, Stopwatch stopwatch) {
        this.runnable = runnable;
        this.serializingExecutor = executor;
        this.scheduler = scheduledExecutorService;
        this.stopwatch = stopwatch;
        stopwatch.start$ar$ds$db96ddcc_0();
    }

    public final long nanoTime() {
        return this.stopwatch.elapsed(TimeUnit.NANOSECONDS);
    }
}
