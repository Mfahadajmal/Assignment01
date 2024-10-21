package com.google.frameworks.client.data.android.impl;

import com.google.android.libraries.grpc.CallOptionsKeys;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.ChannelConfigOption;
import com.google.frameworks.client.data.android.RpcId;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import com.google.frameworks.client.data.android.interceptor.Outcome;
import com.google.frameworks.client.data.android.interceptor.ResponseOutcome;
import com.google.mlkit.common.model.RemoteModelManager;
import com.google.search.mdi.aratea.proto.rpcids.ArateaServiceConfig;
import io.grpc.CallOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RpcIdInterceptor implements AsyncClientInterceptor {
    private final Object RpcIdInterceptor$ar$rpcServiceConfig;
    private final /* synthetic */ int switching_field;

    public RpcIdInterceptor(Object obj, int i) {
        this.switching_field = i;
        this.RpcIdInterceptor$ar$rpcServiceConfig = obj;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueOnCompleteProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome continueRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        if (this.switching_field != 0) {
            return Outcome.PROCEED;
        }
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome continueRequestMessageProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return Outcome.PROCEED;
        }
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseHeaderProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome continueResponseMessageProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startOnCompleteProcessing$ar$class_merging$ar$class_merging(RemoteModelManager remoteModelManager) {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final Outcome startRequestHeaderProcessing(AsyncClientInterceptor.RequestHeaderContext requestHeaderContext) {
        boolean z = true;
        if (this.switching_field != 0) {
            CallOptions callOptions = requestHeaderContext.callOptions;
            if (callOptions.getOption(ChannelConfigOption.KEY) != null) {
                z = false;
            }
            ContextDataProvider.checkState(z, "ChannelConfig provided twice");
            return Outcome.proceedWithCallOptions(callOptions.withOption(ChannelConfigOption.KEY, this.RpcIdInterceptor$ar$rpcServiceConfig));
        }
        ContextDataProvider.checkState(true, "Cronet does not support CallOptions.withAuthority(). See https://github.com/grpc/grpc-java/issues/1767");
        CallOptions callOptions2 = requestHeaderContext.callOptions;
        RpcId rpcId = (RpcId) callOptions2.getOption(RpcId.KEY);
        if (rpcId == null) {
            String str = requestHeaderContext.methodDescriptor().fullMethodName;
            Object obj = this.RpcIdInterceptor$ar$rpcServiceConfig;
            String str2 = ArateaServiceConfig.GRPC_SERVICE_PREFIX.value;
            if (str.startsWith(str2)) {
                String substring = str.substring(str2.length());
                ArateaServiceConfig arateaServiceConfig = (ArateaServiceConfig) obj;
                if (arateaServiceConfig.rpcIdByGrpcMethodSuffix.containsKey(substring)) {
                    rpcId = (RpcId) arateaServiceConfig.rpcIdByGrpcMethodSuffix.get(substring);
                    rpcId.getClass();
                    callOptions2 = callOptions2.withOption(RpcId.KEY, rpcId);
                }
            }
            rpcId = null;
            rpcId.getClass();
            callOptions2 = callOptions2.withOption(RpcId.KEY, rpcId);
        }
        return Outcome.proceedWithCallOptions(callOptions2.withOption(CallOptionsKeys.CONSTANT_RPC_PATH_KEY, rpcId.rpcIdString()));
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ Outcome startRequestMessageProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return Outcome.PROCEED;
        }
        return Outcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseHeaderProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }

    @Override // com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor
    public final /* synthetic */ ResponseOutcome startResponseMessageProcessing$ar$ds() {
        if (this.switching_field != 0) {
            return ResponseOutcome.PROCEED;
        }
        return ResponseOutcome.PROCEED;
    }
}
