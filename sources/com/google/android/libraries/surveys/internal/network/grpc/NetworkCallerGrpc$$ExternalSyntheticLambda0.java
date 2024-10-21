package com.google.android.libraries.surveys.internal.network.grpc;

import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.cronet.CronetChannelBuilder;
import org.chromium.net.CronetEngine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NetworkCallerGrpc$$ExternalSyntheticLambda0 implements NetworkCallerGrpc.ManagedChannelFactory {
    public final /* synthetic */ Object NetworkCallerGrpc$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NetworkCallerGrpc$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.NetworkCallerGrpc$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, kotlin.jvm.functions.Function2] */
    @Override // com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc.ManagedChannelFactory
    public final ManagedChannel createChannel$ar$ds(String str) {
        if (this.switching_field != 0) {
            return (ManagedChannel) this.NetworkCallerGrpc$$ExternalSyntheticLambda0$ar$f$0.invoke(str, 443);
        }
        return CronetChannelBuilder.forAddress(str, 443, (CronetEngine) this.NetworkCallerGrpc$$ExternalSyntheticLambda0$ar$f$0).build();
    }
}
