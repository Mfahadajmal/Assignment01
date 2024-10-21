package com.google.frameworks.client.data.android.impl;

import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.credential.CredentialOption;
import com.google.frameworks.client.data.android.credential.CredentialStrategy;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CredentialStrategyInterceptor implements ClientInterceptor {
    private static final GoogleLogger logger = GoogleLogger.forInjectedClassName("com/google/frameworks/client/data/android/impl/CredentialStrategyInterceptor");

    @Override // io.grpc.ClientInterceptor
    public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
        CredentialStrategy credentialStrategy = (CredentialStrategy) callOptions.getOption(CredentialOption.KEY);
        if (credentialStrategy != null) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/frameworks/client/data/android/impl/CredentialStrategyInterceptor", "interceptCall", 30, "CredentialStrategyInterceptor.java")).log("Using CredentialStrategy: %s", credentialStrategy.getClass());
            return OnDeviceFaceMeshCreateLogEvent.intercept(channel, ContextDataProvider.forStage(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(credentialStrategy, 6))).newCall(methodDescriptor, callOptions);
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) logger.atFine()).withInjectedLogSite("com/google/frameworks/client/data/android/impl/CredentialStrategyInterceptor", "interceptCall", 38, "CredentialStrategyInterceptor.java")).log("Did not set CredentialStrategy");
        return channel.newCall(methodDescriptor, callOptions);
    }
}
