package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;

/* compiled from: PG */
/* loaded from: classes.dex */
class ForwardingManagedChannel extends ManagedChannel {
    public final ManagedChannel delegate;

    public ForwardingManagedChannel(ManagedChannel managedChannel) {
        this.delegate = managedChannel;
    }

    @Override // io.grpc.Channel
    public final String authority() {
        return this.delegate.authority();
    }

    @Override // io.grpc.Channel
    public final ClientCall newCall(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        return this.delegate.newCall(methodDescriptor, callOptions);
    }

    @Override // io.grpc.ManagedChannel
    public void shutdown$ar$ds$17197e6c_0() {
        throw null;
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", this.delegate);
        return stringHelper.toString();
    }
}
