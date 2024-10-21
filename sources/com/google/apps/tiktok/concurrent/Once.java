package com.google.apps.tiktok.concurrent;

import _COROUTINE._BOUNDARY;
import com.google.apps.tiktok.concurrent.Once;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.Futures$CancellationPropagater;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SequentialExecutor;
import com.google.common.util.concurrent.SettableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Once {
    public final Futures$CancellationPropagater callableHolder$ar$class_merging;
    public final SettableFuture completedValue;
    public final AtomicLong currentState = new AtomicLong(packState(Integer.MIN_VALUE, Integer.MIN_VALUE));
    public final AtomicReference currentlyExecuting = new AtomicReference(null);
    private final AtomicReference prevInvocation = new AtomicReference(null);
    private final Executor sequentialDirect = new SequentialExecutor(DirectExecutor.INSTANCE);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OnceFuture extends AbstractFuture {
        private Once once;
        private final int tag;

        public OnceFuture(Once once, int i) {
            this.once = once;
            this.tag = i;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected final void afterDone() {
            long j;
            int i;
            int tag;
            boolean z;
            Once once = this.once;
            this.once = null;
            if (once == null) {
                return;
            }
            do {
                j = once.currentState.get();
                i = (int) j;
                tag = Once.getTag(j);
                if (i != Integer.MIN_VALUE) {
                    if (i == -2147483647) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        tag++;
                    }
                } else {
                    throw new AssertionError(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "Refcount is: "));
                }
            } while (!once.currentState.compareAndSet(j, Once.packState(tag, i - 1)));
            if (!z) {
                return;
            }
            while (true) {
                TaggedFuture taggedFuture = (TaggedFuture) once.currentlyExecuting.get();
                if (taggedFuture != null) {
                    if (taggedFuture.tag <= this.tag) {
                        taggedFuture.cancel(true);
                        AtomicReference atomicReference = once.currentlyExecuting;
                        while (!atomicReference.compareAndSet(taggedFuture, null)) {
                            if (atomicReference.get() != taggedFuture) {
                                break;
                            }
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public final String pendingToString() {
            Object obj;
            Once once = this.once;
            if (once == null || (obj = once.callableHolder$ar$class_merging.Futures$CancellationPropagater$ar$from) == null) {
                return null;
            }
            String _BOUNDARY$ar$MethodOutlining$dc56d17a_10 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(obj, "callable=[", "]");
            TaggedFuture taggedFuture = (TaggedFuture) this.once.currentlyExecuting.get();
            if (taggedFuture != null) {
                return _BOUNDARY$ar$MethodOutlining$dc56d17a_10 + ", trial=[" + taggedFuture.toString() + "]";
            }
            return _BOUNDARY$ar$MethodOutlining$dc56d17a_10;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean setFuture(ListenableFuture listenableFuture) {
            return super.setFuture(listenableFuture);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TaggedFuture extends AbstractFuture {
        public final int tag;

        public TaggedFuture(int i) {
            this.tag = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean setFuture(ListenableFuture listenableFuture) {
            return super.setFuture(listenableFuture);
        }
    }

    public Once(AsyncCallable asyncCallable, Executor executor) {
        SettableFuture settableFuture = new SettableFuture();
        this.completedValue = settableFuture;
        Futures$CancellationPropagater futures$CancellationPropagater = new Futures$CancellationPropagater(asyncCallable, executor, 1);
        this.callableHolder$ar$class_merging = futures$CancellationPropagater;
        settableFuture.addListener(futures$CancellationPropagater, DirectExecutor.INSTANCE);
    }

    public static int getTag(long j) {
        return (int) (j >>> 32);
    }

    public static long packState(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    public final ListenableFuture get() {
        long j;
        final int tag;
        ListenableFuture create;
        if (this.completedValue.isDone()) {
            return this.completedValue;
        }
        do {
            j = this.currentState.get();
            tag = getTag(j);
        } while (!this.currentState.compareAndSet(j, packState(tag, ((int) j) + 1)));
        final SettableFuture settableFuture = new SettableFuture();
        ListenableFuture listenableFuture = (ListenableFuture) this.prevInvocation.getAndSet(settableFuture);
        if (listenableFuture == null) {
            create = ContextDataProvider.submitAsync(TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda1
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    return Once.this.query(tag);
                }
            }), DirectExecutor.INSTANCE);
        } else {
            create = AbstractCatchingFuture.create(listenableFuture, Throwable.class, TracePropagation.propagateAsyncFunction(new AsyncFunction() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda2
                @Override // com.google.common.util.concurrent.AsyncFunction
                public final ListenableFuture apply(Object obj) {
                    return Once.this.query(tag);
                }
            }), this.sequentialDirect);
        }
        settableFuture.setFuture(create);
        final OnceFuture onceFuture = new OnceFuture(this, tag);
        settableFuture.addListener(new Runnable() { // from class: com.google.apps.tiktok.concurrent.Once$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                Once.OnceFuture onceFuture2 = onceFuture;
                SettableFuture settableFuture2 = settableFuture;
                try {
                    Object done = ContextDataProvider.getDone(settableFuture2);
                    Once once = Once.this;
                    once.completedValue.set(done);
                    onceFuture2.setFuture(once.completedValue);
                } catch (Throwable unused) {
                    onceFuture2.setFuture(settableFuture2);
                }
            }
        }, DirectExecutor.INSTANCE);
        return onceFuture;
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object, com.google.common.util.concurrent.AsyncCallable] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.concurrent.Executor, java.lang.Object] */
    public final ListenableFuture query(int i) {
        TaggedFuture taggedFuture;
        if (getTag(this.currentState.get()) > i) {
            return ContextDataProvider.immediateCancelledFuture();
        }
        TaggedFuture taggedFuture2 = new TaggedFuture(i);
        do {
            taggedFuture = (TaggedFuture) this.currentlyExecuting.get();
            if (taggedFuture != null && taggedFuture.tag > i) {
                return ContextDataProvider.immediateCancelledFuture();
            }
        } while (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.currentlyExecuting, taggedFuture, taggedFuture2));
        if (getTag(this.currentState.get()) > i) {
            taggedFuture2.cancel(true);
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(this.currentlyExecuting, taggedFuture2, null);
            return taggedFuture2;
        }
        Futures$CancellationPropagater futures$CancellationPropagater = this.callableHolder$ar$class_merging;
        ?? r1 = futures$CancellationPropagater.Futures$CancellationPropagater$ar$from;
        ?? r4 = futures$CancellationPropagater.Futures$CancellationPropagater$ar$to;
        if (r1 != 0 && r4 != 0) {
            taggedFuture2.setFuture(ContextDataProvider.submitAsync(TracePropagation.propagateAsyncCallable(r1), r4));
        } else {
            taggedFuture2.setFuture(this.completedValue);
        }
        return taggedFuture2;
    }
}
