package com.google.frameworks.client.data.android;

import com.google.android.libraries.performance.primes.NoPiiString;
import io.grpc.CallOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface RpcId {
    public static final CallOptions.Key KEY = new CallOptions.Key("com.google.frameworks.client.data.android.RpcId", null);

    NoPiiString rpcIdString();
}
