package io.grpc.internal;

import com.google.common.base.Stopwatch;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.common.model.RemoteModelManager;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class KeepAliveManager {
    public final RemoteModelManager keepAlivePinger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final long keepAliveTimeInNanos;
    public final long keepAliveTimeoutInNanos;
    public ScheduledFuture pingFuture;
    public final ScheduledExecutorService scheduler;
    public final Runnable sendPing;
    public final Runnable shutdown;
    public ScheduledFuture shutdownFuture;
    public int state$ar$edu$75a4b03c_0;
    public final Stopwatch stopwatch;

    static {
        TimeUnit.SECONDS.toNanos(10L);
        TimeUnit.MILLISECONDS.toNanos(10L);
    }

    public KeepAliveManager(RemoteModelManager remoteModelManager, ScheduledExecutorService scheduledExecutorService, long j, long j2) {
        Stopwatch stopwatch = new Stopwatch();
        this.state$ar$edu$75a4b03c_0 = 1;
        this.shutdown = new LogExceptionRunnable(new InternalSubchannel$4$1(this, 5));
        this.sendPing = new LogExceptionRunnable(new InternalSubchannel$4$1(this, 6));
        this.keepAlivePinger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = remoteModelManager;
        scheduledExecutorService.getClass();
        this.scheduler = scheduledExecutorService;
        this.stopwatch = stopwatch;
        this.keepAliveTimeInNanos = j;
        this.keepAliveTimeoutInNanos = j2;
        stopwatch.reset$ar$ds();
        stopwatch.start$ar$ds$db96ddcc_0();
    }

    public final synchronized void onDataReceived() {
        Stopwatch stopwatch = this.stopwatch;
        stopwatch.reset$ar$ds();
        stopwatch.start$ar$ds$db96ddcc_0();
        int i = this.state$ar$edu$75a4b03c_0;
        if (i == 2) {
            this.state$ar$edu$75a4b03c_0 = 3;
            return;
        }
        if (i != 4 && i != 5) {
            return;
        }
        ScheduledFuture scheduledFuture = this.shutdownFuture;
        boolean z = false;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        if (this.state$ar$edu$75a4b03c_0 == 5) {
            this.state$ar$edu$75a4b03c_0 = 1;
            return;
        }
        this.state$ar$edu$75a4b03c_0 = 2;
        if (this.pingFuture == null) {
            z = true;
        }
        ContextDataProvider.checkState(z, "There should be no outstanding pingFuture");
        this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos, TimeUnit.NANOSECONDS);
    }

    public final synchronized void onTransportActive() {
        int i = this.state$ar$edu$75a4b03c_0;
        if (i == 1) {
            this.state$ar$edu$75a4b03c_0 = 2;
            if (this.pingFuture == null) {
                this.pingFuture = this.scheduler.schedule(this.sendPing, this.keepAliveTimeInNanos - this.stopwatch.elapsed(TimeUnit.NANOSECONDS), TimeUnit.NANOSECONDS);
            }
        } else if (i == 5) {
            this.state$ar$edu$75a4b03c_0 = 4;
        }
    }

    public final synchronized void onTransportIdle() {
        int i = this.state$ar$edu$75a4b03c_0;
        if (i != 2 && i != 3) {
            if (i == 4) {
                this.state$ar$edu$75a4b03c_0 = 5;
                return;
            }
            return;
        }
        this.state$ar$edu$75a4b03c_0 = 1;
    }

    public final synchronized void onTransportStarted() {
    }

    public final synchronized void onTransportTermination() {
        if (this.state$ar$edu$75a4b03c_0 != 6) {
            this.state$ar$edu$75a4b03c_0 = 6;
            ScheduledFuture scheduledFuture = this.shutdownFuture;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledFuture scheduledFuture2 = this.pingFuture;
            if (scheduledFuture2 != null) {
                scheduledFuture2.cancel(false);
                this.pingFuture = null;
            }
        }
    }
}
