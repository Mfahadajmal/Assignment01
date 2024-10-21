package com.google.frameworks.client.data.android.interceptor;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.CallOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Outcome {
    public static final Outcome PROCEED = new Outcome(1, null, null, null, null);
    public final CallOptions callOptions;
    public final RemoteModelManager.RemoteModelManagerRegistration errorResponse$ar$class_merging;
    public final Response response = null;
    private final ListenableFuture trigger;
    public final int type$ar$edu$1ad18efe_0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Response {
    }

    public Outcome(int i, Response response, RemoteModelManager.RemoteModelManagerRegistration remoteModelManagerRegistration, ListenableFuture listenableFuture, CallOptions callOptions) {
        this.type$ar$edu$1ad18efe_0 = i;
        this.errorResponse$ar$class_merging = remoteModelManagerRegistration;
        this.trigger = listenableFuture;
        this.callOptions = callOptions;
    }

    public static Outcome proceedWithCallOptions(CallOptions callOptions) {
        return new Outcome(1, null, null, null, callOptions);
    }

    public final ListenableFuture getTrigger() {
        boolean z;
        if (this.type$ar$edu$1ad18efe_0 == 4) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z);
        return this.trigger;
    }
}
