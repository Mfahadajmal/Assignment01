package com.google.android.libraries.storage.protostore;

import android.net.Uri;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.storage.file.openers.ReadStreamOpener;
import com.google.android.libraries.storage.file.openers.WriteStreamOpener;
import com.google.android.libraries.storage.protostore.serializers.AutoValue_ProtoSerializer;
import com.google.android.libraries.storage.protostore.serializers.ProtoSerializer;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.apps.tiktok.tracing.SpanEndSignal;
import com.google.apps.tiktok.tracing.SpanExtras;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ExecutionSequencer;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SequentialExecutor;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Protobuf;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleProcProtoDataStore {
    public final ListenableFuture fileFuture;
    public final Executor ioExecutor;
    private final NativeLibraryPathListMutex libraryTracing$ar$class_merging;
    public final Optional optionalIOExceptionHandler;
    private final Serializer serializer;
    public final OptionalMethod storage$ar$class_merging$ar$class_merging$ar$class_merging;
    public final String tracingName;
    public final Object lock = new Object();
    private final ExecutionSequencer futureSerializer = new ExecutionSequencer();
    public ListenableFuture cachedData = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory extends XDataStoreVariantFactory {
        public static final XDataStoreVariantFactory INSTANCE = new Factory();

        private Factory() {
        }

        @Override // com.google.android.libraries.storage.protostore.XDataStoreVariantFactory
        public final /* bridge */ /* synthetic */ SingleProcProtoDataStore create$ar$edu$3ef90a92_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ProtoDataStoreConfig protoDataStoreConfig, String str, Executor executor, OptionalMethod optionalMethod, int i) {
            ExtensionRegistryLite extensionRegistryLite;
            if (protoDataStoreConfig.useGeneratedExtensionRegistry) {
                extensionRegistryLite = ExtensionRegistryLite.getGeneratedRegistry();
            } else {
                ExtensionRegistryLite extensionRegistryLite2 = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
                Protobuf protobuf = Protobuf.INSTANCE;
                extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
            }
            AutoValue_ProtoSerializer autoValue_ProtoSerializer = new AutoValue_ProtoSerializer(protoDataStoreConfig.schema, extensionRegistryLite);
            Uri uri = protoDataStoreConfig.uri;
            return new SingleProcProtoDataStore(str, ContextDataProvider.immediateFuture(uri), autoValue_ProtoSerializer, executor, optionalMethod, protoDataStoreConfig.handler, new NativeLibraryPathListMutex(null, null));
        }

        @Override // com.google.android.libraries.storage.protostore.XDataStoreVariantFactory
        public final String id$ar$edu(int i) {
            return "singleproc";
        }
    }

    public SingleProcProtoDataStore(String str, ListenableFuture listenableFuture, Serializer serializer, Executor executor, OptionalMethod optionalMethod, Optional optional, NativeLibraryPathListMutex nativeLibraryPathListMutex) {
        this.tracingName = str;
        this.fileFuture = ContextDataProvider.nonCancellationPropagating(listenableFuture);
        this.serializer = serializer;
        this.ioExecutor = new SequentialExecutor(executor);
        this.storage$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.optionalIOExceptionHandler = optional;
        this.libraryTracing$ar$class_merging = nativeLibraryPathListMutex;
    }

    public final ListenableFuture populateAndGetCachedData() {
        ListenableFuture listenableFuture;
        synchronized (this.lock) {
            ListenableFuture listenableFuture2 = this.cachedData;
            if (listenableFuture2 != null && listenableFuture2.isDone()) {
                try {
                    ContextDataProvider.getDone(this.cachedData);
                } catch (ExecutionException unused) {
                    this.cachedData = null;
                }
            }
            if (this.cachedData == null) {
                this.cachedData = ContextDataProvider.nonCancellationPropagating(this.futureSerializer.submitAsync(TracePropagation.propagateAsyncCallable(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(this, 6)), this.ioExecutor));
            }
            listenableFuture = this.cachedData;
        }
        return listenableFuture;
    }

    public final Object readDataSync(Uri uri) {
        SpanEndSignal beginSpan$ar$edu$7f8f730_0;
        try {
            try {
                beginSpan$ar$edu$7f8f730_0 = Tracer.beginSpan$ar$edu$7f8f730_0("Read " + this.tracingName, 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, false);
                try {
                    InputStream inputStream = (InputStream) this.storage$ar$class_merging$ar$class_merging$ar$class_merging.open(uri, new ReadStreamOpener(0));
                    try {
                        Serializer serializer = this.serializer;
                        Object parseFrom = ((ProtoSerializer) serializer).defaultValue().getParserForType().parseFrom(inputStream, ((ProtoSerializer) serializer).extensionRegistryLite());
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        beginSpan$ar$edu$7f8f730_0.close();
                        return parseFrom;
                    } finally {
                    }
                } catch (Throwable th) {
                    try {
                        beginSpan$ar$edu$7f8f730_0.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                if (!this.storage$ar$class_merging$ar$class_merging$ar$class_merging.exists(uri)) {
                    return ((AutoValue_ProtoSerializer) this.serializer).defaultValue;
                }
                throw e;
            }
        } catch (IOException e2) {
            throw DisplayStats.attachFileDebugInfoV2$ar$class_merging$ar$class_merging$ar$class_merging(this.storage$ar$class_merging$ar$class_merging$ar$class_merging, uri, e2, this.tracingName);
        }
    }

    public final ListenableFuture update$ar$ds$bf118803_0(AsyncFunction asyncFunction, Executor executor) {
        return this.futureSerializer.submitAsync(TracePropagation.propagateAsyncCallable(new XDataStore$$ExternalSyntheticLambda3(this, populateAndGetCachedData(), asyncFunction, executor, 1)), DirectExecutor.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.libraries.storage.file.spi.Backend, java.lang.Object] */
    public final void writeDataSync(Uri uri, Object obj) {
        SpanEndSignal beginSpan$ar$edu$7f8f730_0;
        Uri addSuffix = JankMetricService.addSuffix(uri, ".tmp");
        try {
            beginSpan$ar$edu$7f8f730_0 = Tracer.beginSpan$ar$edu$7f8f730_0("Write " + this.tracingName, 1, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS, false);
            try {
                ImageInfo.Builder builder = new ImageInfo.Builder();
                try {
                    OptionalMethod optionalMethod = this.storage$ar$class_merging$ar$class_merging$ar$class_merging;
                    WriteStreamOpener writeStreamOpener = new WriteStreamOpener();
                    writeStreamOpener.behaviors$ar$class_merging$ar$class_merging$ar$class_merging = new ImageInfo.Builder[]{builder};
                    OutputStream outputStream = (OutputStream) optionalMethod.open(addSuffix, writeStreamOpener);
                    try {
                        ((MessageLite) obj).writeTo(outputStream);
                        builder.sync();
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        beginSpan$ar$edu$7f8f730_0.close();
                        this.storage$ar$class_merging$ar$class_merging$ar$class_merging.rename(addSuffix, uri);
                    } catch (Throwable th) {
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e) {
                    throw DisplayStats.attachFileDebugInfoV2$ar$class_merging$ar$class_merging$ar$class_merging(this.storage$ar$class_merging$ar$class_merging$ar$class_merging, uri, e, this.tracingName);
                }
            } finally {
            }
        } catch (IOException e2) {
            if (this.storage$ar$class_merging$ar$class_merging$ar$class_merging.exists(addSuffix)) {
                try {
                    WorkQueue context$ar$class_merging$ar$class_merging = this.storage$ar$class_merging$ar$class_merging$ar$class_merging.getContext$ar$class_merging$ar$class_merging(addSuffix);
                    context$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer.deleteFile((Uri) context$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex);
                } catch (IOException e3) {
                    e2.addSuppressed(e3);
                }
            }
            throw e2;
        }
    }
}
