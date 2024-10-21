package com.google.android.libraries.storage.protostore;

import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.libraries.phenotype.client.stable.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class XDataStore$$ExternalSyntheticLambda3 implements AsyncCallable {
    public final /* synthetic */ Object XDataStore$$ExternalSyntheticLambda3$ar$f$0;
    public final /* synthetic */ ListenableFuture f$1;
    public final /* synthetic */ AsyncFunction f$2;
    public final /* synthetic */ Executor f$3;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ XDataStore$$ExternalSyntheticLambda3(Object obj, ListenableFuture listenableFuture, AsyncFunction asyncFunction, Executor executor, int i) {
        this.switching_field = i;
        this.XDataStore$$ExternalSyntheticLambda3$ar$f$0 = obj;
        this.f$1 = listenableFuture;
        this.f$2 = asyncFunction;
        this.f$3 = executor;
    }

    @Override // com.google.common.util.concurrent.AsyncCallable
    public final ListenableFuture call() {
        if (this.switching_field != 0) {
            Object obj = this.XDataStore$$ExternalSyntheticLambda3$ar$f$0;
            AiCoreBaseService$$ExternalSyntheticLambda16 aiCoreBaseService$$ExternalSyntheticLambda16 = new AiCoreBaseService$$ExternalSyntheticLambda16(obj, 12);
            Executor executor = this.f$3;
            AsyncFunction asyncFunction = this.f$2;
            ListenableFuture create = AbstractTransformFuture.create(this.f$1, aiCoreBaseService$$ExternalSyntheticLambda16, DirectExecutor.INSTANCE);
            ListenableFuture create2 = AbstractTransformFuture.create(create, asyncFunction, executor);
            return AbstractTransformFuture.create(create2, TracePropagation.propagateAsyncFunction(new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(obj, create, create2, 2)), DirectExecutor.INSTANCE);
        }
        Executor executor2 = this.f$3;
        return AbstractTransformFuture.create(this.f$1, TracePropagation.propagateAsyncFunction(new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(this.XDataStore$$ExternalSyntheticLambda3$ar$f$0, this.f$2, executor2, 3)), DirectExecutor.INSTANCE);
    }
}
