package org.chromium.net.impl;

import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RefCountDelegate {
    private final AtomicInteger mCount = new AtomicInteger(1);
    private final Runnable mDelegate;

    public RefCountDelegate(Runnable runnable) {
        this.mDelegate = runnable;
    }

    public final void decrement() {
        if (this.mCount.decrementAndGet() == 0) {
            this.mDelegate.run();
        }
    }

    public final void increment() {
        this.mCount.incrementAndGet();
    }
}
