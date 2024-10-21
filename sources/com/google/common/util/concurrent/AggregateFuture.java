package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AggregateFuture extends AggregateFutureState {
    private static final LazyLogger logger = new LazyLogger(AggregateFuture.class);
    private final boolean allMustSucceed;
    private final boolean collectsValues;
    public ImmutableCollection futures;

    public AggregateFuture(ImmutableCollection immutableCollection, boolean z, boolean z2) {
        super(immutableCollection.size());
        immutableCollection.getClass();
        this.futures = immutableCollection;
        this.allMustSucceed = z;
        this.collectsValues = z2;
    }

    private static boolean addCausalChain(Set set, Throwable th) {
        while (th != null) {
            if (!set.add(th)) {
                return false;
            }
            th = th.getCause();
        }
        return true;
    }

    private final void handleException(Throwable th) {
        th.getClass();
        if (this.allMustSucceed && !setException(th)) {
            Set<Throwable> set = this.seenExceptions;
            if (set == null) {
                Set newSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap());
                addInitialException(newSetFromMap);
                AggregateFutureState.ATOMIC_HELPER.compareAndSetSeenExceptions$ar$ds(this, newSetFromMap);
                set = this.seenExceptions;
                set.getClass();
            }
            if (addCausalChain(set, th)) {
                log(th);
                return;
            }
        }
        if (th instanceof Error) {
            log(th);
        }
    }

    private static void log(Throwable th) {
        String str;
        if (true != (th instanceof Error)) {
            str = "Got more than one input Future failure. Logging failures after the first";
        } else {
            str = "Input Future failed with Error";
        }
        logger.get().logp(Level.SEVERE, "com.google.common.util.concurrent.AggregateFuture", "log", str, th);
    }

    @Override // com.google.common.util.concurrent.AggregateFutureState
    public final void addInitialException(Set set) {
        set.getClass();
        if (!isCancelled()) {
            Throwable tryInternalFastPathGetFailure = tryInternalFastPathGetFailure();
            tryInternalFastPathGetFailure.getClass();
            addCausalChain(set, tryInternalFastPathGetFailure);
        }
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        ImmutableCollection immutableCollection = this.futures;
        boolean z = true;
        releaseResources$ar$edu(1);
        boolean isCancelled = isCancelled();
        if (immutableCollection == null) {
            z = false;
        }
        if (z & isCancelled) {
            boolean wasInterrupted = wasInterrupted();
            UnmodifiableIterator listIterator = immutableCollection.listIterator();
            while (listIterator.hasNext()) {
                ((Future) listIterator.next()).cancel(wasInterrupted);
            }
        }
    }

    public abstract void collectOneValue(int i, Object obj);

    public final void collectValueFromNonCancelledFuture(int i, Future future) {
        try {
            collectOneValue(i, ContextDataProvider.getDone(future));
        } catch (ExecutionException e) {
            handleException(e.getCause());
        } catch (Throwable th) {
            handleException(th);
        }
    }

    public final void decrementCountAndMaybeComplete(ImmutableCollection immutableCollection) {
        boolean z;
        int decrementAndGetRemainingCount = AggregateFutureState.ATOMIC_HELPER.decrementAndGetRemainingCount(this);
        int i = 0;
        if (decrementAndGetRemainingCount >= 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Less than 0 remaining futures");
        if (decrementAndGetRemainingCount == 0) {
            if (immutableCollection != null) {
                UnmodifiableIterator listIterator = immutableCollection.listIterator();
                while (listIterator.hasNext()) {
                    Future future = (Future) listIterator.next();
                    if (!future.isCancelled()) {
                        collectValueFromNonCancelledFuture(i, future);
                    }
                    i++;
                }
            }
            this.seenExceptions = null;
            handleAllCompleted();
            releaseResources$ar$edu(2);
        }
    }

    public abstract void handleAllCompleted();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void init() {
        final ImmutableCollection immutableCollection;
        ImmutableCollection immutableCollection2 = this.futures;
        immutableCollection2.getClass();
        if (immutableCollection2.isEmpty()) {
            handleAllCompleted();
            return;
        }
        if (this.allMustSucceed) {
            UnmodifiableIterator listIterator = this.futures.listIterator();
            final int i = 0;
            while (listIterator.hasNext()) {
                final ListenableFuture listenableFuture = (ListenableFuture) listIterator.next();
                listenableFuture.addListener(new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AggregateFuture aggregateFuture = AggregateFuture.this;
                        int i2 = i;
                        ListenableFuture listenableFuture2 = listenableFuture;
                        try {
                            if (listenableFuture2.isCancelled()) {
                                aggregateFuture.futures = null;
                                aggregateFuture.cancel(false);
                            } else {
                                aggregateFuture.collectValueFromNonCancelledFuture(i2, listenableFuture2);
                            }
                        } finally {
                            aggregateFuture.decrementCountAndMaybeComplete(null);
                        }
                    }
                }, DirectExecutor.INSTANCE);
                i++;
            }
            return;
        }
        if (this.collectsValues) {
            immutableCollection = this.futures;
        } else {
            immutableCollection = null;
        }
        Runnable runnable = new Runnable() { // from class: com.google.common.util.concurrent.AggregateFuture$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AggregateFuture.this.decrementCountAndMaybeComplete(immutableCollection);
            }
        };
        UnmodifiableIterator listIterator2 = this.futures.listIterator();
        while (listIterator2.hasNext()) {
            ((ListenableFuture) listIterator2.next()).addListener(runnable, DirectExecutor.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ImmutableCollection immutableCollection = this.futures;
        if (immutableCollection != null) {
            return "futures=".concat(immutableCollection.toString());
        }
        return super.pendingToString();
    }

    public void releaseResources$ar$edu(int i) {
        this.futures = null;
    }
}
