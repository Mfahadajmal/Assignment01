package com.google.android.libraries.storage.protostore;

import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.collect.ImmutableCollection;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.CombinedFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.MessageLite;
import io.grpc.internal.RetriableStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProtoDataMigrationInitializer$$ExternalSyntheticLambda3 implements AsyncFunction {
    public final /* synthetic */ ProtoDataMigrationInitializer f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ List f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ProtoDataMigrationInitializer$$ExternalSyntheticLambda3(ProtoDataMigrationInitializer protoDataMigrationInitializer, int i, List list, int i2) {
        this.switching_field = i2;
        this.f$0 = protoDataMigrationInitializer;
        this.f$1 = i;
        this.f$2 = list;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        if (this.switching_field != 0) {
            final MessageLite messageLite = (MessageLite) obj;
            final List list = this.f$2;
            final int i = this.f$1;
            RetriableStream.HedgingPlan whenAllComplete$ar$class_merging$ar$class_merging = ContextDataProvider.whenAllComplete$ar$class_merging$ar$class_merging(list);
            final ProtoDataMigrationInitializer protoDataMigrationInitializer = this.f$0;
            AsyncCallable propagateAsyncCallable = TracePropagation.propagateAsyncCallable(new AsyncCallable() { // from class: com.google.android.libraries.storage.protostore.ProtoDataMigrationInitializer$$ExternalSyntheticLambda1
                @Override // com.google.common.util.concurrent.AsyncCallable
                public final ListenableFuture call() {
                    ListenableFuture immediateFuture = ContextDataProvider.immediateFuture(messageLite);
                    for (int i2 = 0; i2 < i; i2++) {
                        if (((Boolean) ContextDataProvider.getDone((Future) list.get(i2))).booleanValue()) {
                            immediateFuture = AbstractTransformFuture.create(immediateFuture, TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16((ProtoDataMigration) ProtoDataMigrationInitializer.this.migrations.get(i2), 8)), DirectExecutor.INSTANCE);
                        }
                    }
                    return immediateFuture;
                }
            });
            return new CombinedFuture((ImmutableCollection) whenAllComplete$ar$class_merging$ar$class_merging.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis, whenAllComplete$ar$class_merging$ar$class_merging.isHedgeable, protoDataMigrationInitializer.executor, propagateAsyncCallable);
        }
        int i2 = this.f$1;
        ArrayList arrayList = new ArrayList(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            if (((Boolean) ContextDataProvider.getDone((Future) this.f$2.get(i3))).booleanValue()) {
                arrayList.add(((ProtoDataMigration) this.f$0.migrations.get(i3)).cleanup());
            }
        }
        return ContextDataProvider.whenAllSucceed$ar$class_merging$ar$class_merging(arrayList).call(new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(4), DirectExecutor.INSTANCE);
    }

    public /* synthetic */ ProtoDataMigrationInitializer$$ExternalSyntheticLambda3(ProtoDataMigrationInitializer protoDataMigrationInitializer, List list, int i, int i2) {
        this.switching_field = i2;
        this.f$0 = protoDataMigrationInitializer;
        this.f$2 = list;
        this.f$1 = i;
    }
}
