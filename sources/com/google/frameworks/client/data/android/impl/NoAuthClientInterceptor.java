package com.google.frameworks.client.data.android.impl;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.frameworks.client.data.android.auth.AuthContext;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.MethodDescriptor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoAuthClientInterceptor implements ClientInterceptor {
    @Override // io.grpc.ClientInterceptor
    public final ClientCall interceptCall(MethodDescriptor methodDescriptor, CallOptions callOptions, Channel channel) {
        boolean z;
        if (callOptions.getOption(AuthContext.AUTH_CONTEXT_KEY) == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "AuthContext was set, but no AuthContextManager was bound");
        return channel.newCall(methodDescriptor, callOptions);
    }
}
