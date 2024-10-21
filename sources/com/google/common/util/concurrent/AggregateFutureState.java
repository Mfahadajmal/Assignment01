package com.google.common.util.concurrent;

import com.google.common.util.concurrent.AbstractFuture;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AggregateFutureState extends AbstractFuture.TrustedFuture {
    public static final AtomicHelper ATOMIC_HELPER;
    private static final LazyLogger log = new LazyLogger(AggregateFutureState.class);
    public volatile int remaining;
    public volatile Set<Throwable> seenExceptions = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class AtomicHelper {
        public abstract void compareAndSetSeenExceptions$ar$ds(AggregateFutureState aggregateFutureState, Set set);

        public abstract int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SafeAtomicHelper extends AtomicHelper {
        final AtomicIntegerFieldUpdater remainingCountUpdater;
        final AtomicReferenceFieldUpdater seenExceptionsUpdater;

        public SafeAtomicHelper(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            this.seenExceptionsUpdater = atomicReferenceFieldUpdater;
            this.remainingCountUpdater = atomicIntegerFieldUpdater;
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final void compareAndSetSeenExceptions$ar$ds(AggregateFutureState aggregateFutureState, Set set) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
            do {
                atomicReferenceFieldUpdater = this.seenExceptionsUpdater;
                if (atomicReferenceFieldUpdater.compareAndSet(aggregateFutureState, null, set)) {
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(aggregateFutureState) == null);
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            return this.remainingCountUpdater.decrementAndGet(aggregateFutureState);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SynchronizedAtomicHelper extends AtomicHelper {
        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final void compareAndSetSeenExceptions$ar$ds(AggregateFutureState aggregateFutureState, Set set) {
            synchronized (aggregateFutureState) {
                if (aggregateFutureState.seenExceptions == null) {
                    aggregateFutureState.seenExceptions = set;
                }
            }
        }

        @Override // com.google.common.util.concurrent.AggregateFutureState.AtomicHelper
        public final int decrementAndGetRemainingCount(AggregateFutureState aggregateFutureState) {
            int i;
            synchronized (aggregateFutureState) {
                i = aggregateFutureState.remaining - 1;
                aggregateFutureState.remaining = i;
            }
            return i;
        }
    }

    static {
        Throwable th;
        AtomicHelper synchronizedAtomicHelper;
        try {
            synchronizedAtomicHelper = new SafeAtomicHelper(AtomicReferenceFieldUpdater.newUpdater(AggregateFutureState.class, Set.class, "seenExceptions"), AtomicIntegerFieldUpdater.newUpdater(AggregateFutureState.class, "remaining"));
            th = null;
        } catch (Throwable th2) {
            th = th2;
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
        }
        Throwable th3 = th;
        ATOMIC_HELPER = synchronizedAtomicHelper;
        if (th3 != null) {
            log.get().logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFutureState", "<clinit>", "SafeAtomicHelper is broken!", th3);
        }
    }

    public AggregateFutureState(int i) {
        this.remaining = i;
    }

    public abstract void addInitialException(Set set);
}
