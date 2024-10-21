package com.google.search.mdi.aratea.proto.rpcids;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.frameworks.client.data.android.RpcId;
import com.google.frameworks.client.data.android.RpcServiceConfig;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArateaServiceConfig implements RpcServiceConfig {
    public final ImmutableMap rpcIdByGrpcMethodSuffix;
    public static final NoPiiString RPC_ID_SERVICE_PREFIX = new NoPiiString("mdi.aratea.ArateaService.");
    public static final NoPiiString GRPC_SERVICE_PREFIX = new NoPiiString("mdi.aratea.ArateaService/");
    public static final RpcId GENERATE_IMAGE_FROM_TEXT = new AnonymousClass2(1, null);
    public static final RpcId GENERATE = new AnonymousClass2(0);
    public static final ArateaServiceConfig INSTANCE = new ArateaServiceConfig();
    public static final NoPiiString PREFERRED_HOST_NAME = new NoPiiString("aratea-pa.googleapis.com");

    private ArateaServiceConfig() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        builder.add$ar$ds$4f674a09_0("aratea-pa.mtls.googleapis.com");
        builder.add$ar$ds$4f674a09_0("autopush-aratea-pa.mtls.sandbox.googleapis.com");
        builder.add$ar$ds$4f674a09_0("autopush-aratea-pa.sandbox.googleapis.com");
        builder.add$ar$ds$4f674a09_0("staging-aratea-pa.mtls.sandbox.googleapis.com");
        builder.add$ar$ds$4f674a09_0("staging-aratea-pa.sandbox.googleapis.com");
        builder.add$ar$ds$4f674a09_0("aratea-pa.googleapis.com");
        builder.build();
        new ImmutableSet.Builder().build();
        RpcId rpcId = GENERATE_IMAGE_FROM_TEXT;
        RpcId rpcId2 = GENERATE;
        ImmutableSet.of((Object) rpcId, (Object) rpcId2);
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        builder2.put$ar$ds$de9b9d28_0("GenerateImageFromText", rpcId);
        builder2.put$ar$ds$de9b9d28_0("Generate", rpcId2);
        this.rpcIdByGrpcMethodSuffix = builder2.buildOrThrow();
        new ImmutableMap.Builder().buildOrThrow();
    }

    /* compiled from: PG */
    /* renamed from: com.google.search.mdi.aratea.proto.rpcids.ArateaServiceConfig$2, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass2 implements RpcId {
        private final NoPiiString rpcIdString;
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(int i, byte[] bArr) {
            this.switching_field = i;
            this.rpcIdString = NoPiiString.concat(ArateaServiceConfig.RPC_ID_SERVICE_PREFIX, new NoPiiString("GenerateImageFromText"));
            new SingletonImmutableSet("https://www.googleapis.com/auth/mdi.aratea");
        }

        @Override // com.google.frameworks.client.data.android.RpcId
        public final NoPiiString rpcIdString() {
            if (this.switching_field != 0) {
                return this.rpcIdString;
            }
            return this.rpcIdString;
        }

        public final String toString() {
            int i = this.switching_field;
            return this.rpcIdString.value;
        }

        public AnonymousClass2(int i) {
            this.switching_field = i;
            this.rpcIdString = NoPiiString.concat(ArateaServiceConfig.RPC_ID_SERVICE_PREFIX, new NoPiiString("Generate"));
            new SingletonImmutableSet("https://www.googleapis.com/auth/mdi.aratea");
        }
    }
}
