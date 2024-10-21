package com.google.android.libraries.storage.protostore;

import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProtoDataStoreFactory$$ExternalSyntheticLambda1 implements AsyncFunction {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ProtoDataStoreFactory$$ExternalSyntheticLambda1(int i) {
        this.switching_field = i;
    }

    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        if (this.switching_field == 0) {
            return ContextDataProvider.immediateFuture("");
        }
        ApiException apiException = (ApiException) obj;
        throw new PhenotypeRuntimeException(apiException.getStatusCode(), apiException.getMessage(), apiException);
    }
}
