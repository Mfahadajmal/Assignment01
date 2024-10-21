package io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicLongCounter implements LongCounter {
    private final AtomicLong counter = new AtomicLong();

    @Override // io.grpc.internal.LongCounter
    public final void add$ar$ds$3d014f3e_0() {
        this.counter.getAndAdd(1L);
    }
}
