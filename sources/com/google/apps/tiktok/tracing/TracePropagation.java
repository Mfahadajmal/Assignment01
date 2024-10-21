package com.google.apps.tiktok.tracing;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.SynchronizationContext;
import java.util.HashMap;
import java.util.Random;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TracePropagation {
    public static final /* synthetic */ int TracePropagation$ar$NoOp = 0;

    static {
        Math.abs(new Random().nextInt());
        new HashMap();
    }

    public static final AsyncCallable propagateAsyncCallable(AsyncCallable asyncCallable) {
        return new ExecutionSequencer.AnonymousClass2(Tracer.getOrCreateDebug(), asyncCallable, 1);
    }

    public static final AsyncFunction propagateAsyncFunction(final AsyncFunction asyncFunction) {
        final Trace orCreateDebug = Tracer.getOrCreateDebug();
        return new AsyncFunction() { // from class: com.google.apps.tiktok.tracing.TracePropagation$propagateAsyncFunction$1
            @Override // com.google.common.util.concurrent.AsyncFunction
            public final ListenableFuture apply(Object obj) {
                Trace trace = Tracer.set(Tracer.getCurrentThreadState(), Trace.this);
                try {
                    ListenableFuture apply = asyncFunction.apply(obj);
                    if (apply != null) {
                        return apply;
                    }
                    throw new IllegalStateException("AsyncFunction should return a ListenableFuture instead of null.");
                } finally {
                }
            }

            public final String toString() {
                return "propagating=[" + asyncFunction + "]";
            }
        };
    }

    public static final Function propagateFunction(final Function function) {
        final Trace orCreateDebug = Tracer.getOrCreateDebug();
        return new Function() { // from class: com.google.apps.tiktok.tracing.TracePropagation$propagateFunction$1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                Trace trace = Tracer.set(Tracer.getCurrentThreadState(), Trace.this);
                try {
                    return function.apply(obj);
                } finally {
                }
            }

            public final String toString() {
                return "propagating=[" + function + "]";
            }
        };
    }

    public static final Runnable propagateRunnable(Runnable runnable) {
        return new SynchronizationContext.AnonymousClass1(new Ref$ObjectRef(), Tracer.getOrCreateDebug(), runnable, 1);
    }
}
