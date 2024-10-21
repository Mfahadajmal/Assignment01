package io.grpc.util;

import io.grpc.LoadBalancer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoundRobinLoadBalancer$EmptyPicker extends LoadBalancer.SubchannelPicker {
    public final boolean equals(Object obj) {
        return obj instanceof RoundRobinLoadBalancer$EmptyPicker;
    }

    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override // io.grpc.LoadBalancer.SubchannelPicker
    public final LoadBalancer.PickResult pickSubchannel(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
        return LoadBalancer.PickResult.NO_RESULT;
    }
}
