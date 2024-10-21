package io.grpc.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface RetryScheduler {
    void reset();

    void schedule(Runnable runnable);
}
