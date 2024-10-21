package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExponentialBackoffPolicy {
    private final long initialBackoffNanos;
    private final double jitter;
    private final long maxBackoffNanos;
    private final double multiplier;
    private long nextBackoffNanos;
    private final Random random = new Random();

    public ExponentialBackoffPolicy() {
        long nanos = TimeUnit.SECONDS.toNanos(1L);
        this.initialBackoffNanos = nanos;
        this.maxBackoffNanos = TimeUnit.MINUTES.toNanos(2L);
        this.multiplier = 1.6d;
        this.jitter = 0.2d;
        this.nextBackoffNanos = nanos;
    }

    public final long nextBackoffNanos() {
        boolean z;
        long j = this.nextBackoffNanos;
        double d = j;
        this.nextBackoffNanos = Math.min((long) (1.6d * d), this.maxBackoffNanos);
        double d2 = 0.2d * d;
        double d3 = d * (-0.2d);
        if (d2 >= d3) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z);
        return j + ((long) ((this.random.nextDouble() * (d2 - d3)) + d3));
    }
}
