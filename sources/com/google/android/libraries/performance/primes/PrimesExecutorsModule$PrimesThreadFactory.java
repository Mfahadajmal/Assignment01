package com.google.android.libraries.performance.primes;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class PrimesExecutorsModule$PrimesThreadFactory implements ThreadFactory {
    private final AtomicInteger count = new AtomicInteger(1);
    private final String prefix = "Primes";
    public final int priority;

    public PrimesExecutorsModule$PrimesThreadFactory(int i) {
        this.priority = i;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, runnable, 0), this.prefix + "-" + this.count.getAndIncrement());
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        return thread;
    }
}
