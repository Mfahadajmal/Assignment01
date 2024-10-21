package com.google.android.libraries.storage.protostore;

import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.storage.protostore.loggers.NoOpLogger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.apps.tiktok.concurrent.Once;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.SpanExtras;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.android.base.AndroidTicker;
import com.google.common.base.Function;
import com.google.common.base.Stopwatch;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.logging.proto2api.PlaylogIcingProtoEnums$ProtoDataStoreVariant$Code;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.Futures$CancellationPropagater;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class XDataStore {
    private final ListenableFuture hashedUri;
    private final Once init;
    public List initTasks;
    public final Object lock;
    private final NoOpLogger logger$ar$class_merging$b9e9d160_0;
    public final NativeLibraryPathListMutex tracing$ar$class_merging;
    public final String tracingName;
    private final ExecutionSequencer updateSequencer;
    public final SingleProcProtoDataStore variant$ar$class_merging;
    private final int variantCode$ar$edu;
    public final Once variantInit;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InitializationTasks implements AsyncCallable {
        public List tasks;

        public InitializationTasks() {
        }

        @Override // com.google.common.util.concurrent.AsyncCallable
        public final ListenableFuture call() {
            SpanEndSignal beginSpan$ar$edu$7f8f730_0;
            beginSpan$ar$edu$7f8f730_0 = Tracer.beginSpan$ar$edu$7f8f730_0("Initialize ".concat(String.valueOf(XDataStore.this.tracingName)), 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, false);
            try {
                synchronized (XDataStore.this.lock) {
                    if (this.tasks == null) {
                        XDataStore xDataStore = XDataStore.this;
                        this.tasks = xDataStore.initTasks;
                        xDataStore.initTasks = Collections.emptyList();
                    }
                }
                ArrayList arrayList = new ArrayList(this.tasks.size());
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(XDataStore.this);
                Iterator it = this.tasks.iterator();
                while (it.hasNext()) {
                    try {
                        arrayList.add(((AsyncFunction) it.next()).apply(shadowDelegateImpl));
                    } catch (Exception e) {
                        arrayList.add(ContextDataProvider.immediateFailedFuture(e));
                    }
                }
                ListenableFuture call = ContextDataProvider.whenAllSucceed$ar$class_merging$ar$class_merging(arrayList).call(new WorkerWrapper$$ExternalSyntheticLambda0(this, 10), DirectExecutor.INSTANCE);
                beginSpan$ar$edu$7f8f730_0.attachToFuture$ar$ds(call);
                beginSpan$ar$edu$7f8f730_0.close();
                return call;
            } catch (Throwable th) {
                try {
                    beginSpan$ar$edu$7f8f730_0.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public XDataStore(SingleProcProtoDataStore singleProcProtoDataStore, NoOpLogger noOpLogger, ListenableFuture listenableFuture, byte[] bArr) {
        this(singleProcProtoDataStore, noOpLogger, listenableFuture);
    }

    public final void addInitializer(AsyncFunction asyncFunction) {
        synchronized (this.lock) {
            this.initTasks.add(asyncFunction);
        }
    }

    public final ListenableFuture getData() {
        SpanEndSignal beginSpan$ar$edu$7f8f730_0;
        ListenableFuture listenableFuture;
        Stopwatch.createStarted(AndroidTicker.SYSTEM_TICKER);
        if (!this.init.completedValue.isDone()) {
            beginSpan$ar$edu$7f8f730_0 = Tracer.beginSpan$ar$edu$7f8f730_0("Get ".concat(String.valueOf(this.tracingName)), 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, false);
            try {
                ListenableFuture create = AbstractTransformFuture.create(this.init.get(), TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16(this, 15)), DirectExecutor.INSTANCE);
                beginSpan$ar$edu$7f8f730_0.attachToFuture$ar$ds(create);
                beginSpan$ar$edu$7f8f730_0.close();
                listenableFuture = create;
            } catch (Throwable th) {
                try {
                    beginSpan$ar$edu$7f8f730_0.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } else {
            listenableFuture = this.variant$ar$class_merging.populateAndGetCachedData();
        }
        this.logger$ar$class_merging$b9e9d160_0.logApiMetrics$ar$ds();
        NoOpLogger noOpLogger = this.logger$ar$class_merging$b9e9d160_0;
        ContextDataProvider.nonCancellationPropagating(this.hashedUri);
        noOpLogger.logGetData$ar$ds();
        return ContextDataProvider.nonCancellationPropagating(listenableFuture);
    }

    /* renamed from: lambda$getData$1$com-google-android-libraries-storage-protostore-XDataStore$ar$ds, reason: not valid java name */
    public final /* synthetic */ ListenableFuture m206x3ee98819() {
        return this.variant$ar$class_merging.populateAndGetCachedData();
    }

    public final ListenableFuture updateData(Function function, Executor executor) {
        return updateDataAsync(TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16(function, 13)), executor);
    }

    public final ListenableFuture updateDataAsync(AsyncFunction asyncFunction, Executor executor) {
        SpanEndSignal beginSpan$ar$edu$7f8f730_0;
        Stopwatch.createStarted(AndroidTicker.SYSTEM_TICKER);
        beginSpan$ar$edu$7f8f730_0 = Tracer.beginSpan$ar$edu$7f8f730_0("Update ".concat(String.valueOf(this.tracingName)), 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, false);
        try {
            ListenableFuture listenableFuture = this.init.get();
            this.updateSequencer.submitAsync(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(listenableFuture, 7), DirectExecutor.INSTANCE);
            ListenableFuture submitAsync = this.updateSequencer.submitAsync(TracePropagation.propagateAsyncCallable(new XDataStore$$ExternalSyntheticLambda3(this, listenableFuture, asyncFunction, executor, 0)), DirectExecutor.INSTANCE);
            submitAsync.getClass();
            if (!listenableFuture.isDone()) {
                if (submitAsync.isDone()) {
                    ContextDataProvider.maybePropagateCancellation(submitAsync, listenableFuture);
                } else {
                    Futures$CancellationPropagater futures$CancellationPropagater = new Futures$CancellationPropagater(submitAsync, listenableFuture, 0);
                    submitAsync.addListener(futures$CancellationPropagater, DirectExecutor.INSTANCE);
                    listenableFuture.addListener(futures$CancellationPropagater, DirectExecutor.INSTANCE);
                }
            }
            this.logger$ar$class_merging$b9e9d160_0.logApiMetrics$ar$ds();
            NoOpLogger noOpLogger = this.logger$ar$class_merging$b9e9d160_0;
            ContextDataProvider.nonCancellationPropagating(this.hashedUri);
            noOpLogger.logUpdateData$ar$ds();
            ListenableFuture listenableFuture2 = JankMetricService.toVoid(submitAsync);
            beginSpan$ar$edu$7f8f730_0.attachToFuture$ar$ds(listenableFuture2);
            beginSpan$ar$edu$7f8f730_0.close();
            return listenableFuture2;
        } catch (Throwable th) {
            try {
                beginSpan$ar$edu$7f8f730_0.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public XDataStore(SingleProcProtoDataStore singleProcProtoDataStore, NoOpLogger noOpLogger, ListenableFuture listenableFuture) {
        this.init = new Once(new InitializationTasks(), DirectExecutor.INSTANCE);
        this.lock = new Object();
        this.initTasks = new ArrayList();
        this.variant$ar$class_merging = singleProcProtoDataStore;
        this.logger$ar$class_merging$b9e9d160_0 = noOpLogger;
        this.hashedUri = listenableFuture;
        this.tracingName = singleProcProtoDataStore.tracingName;
        this.variantInit = new Once(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(singleProcProtoDataStore, 5), DirectExecutor.INSTANCE);
        this.updateSequencer = new ExecutionSequencer();
        this.variantCode$ar$edu = PlaylogIcingProtoEnums$ProtoDataStoreVariant$Code.VARIANT_SINGLEPROC$ar$edu;
        this.tracing$ar$class_merging = new NativeLibraryPathListMutex(null, null);
        addInitializer(new AiCoreBaseService$$ExternalSyntheticLambda16(this, 14));
    }
}
