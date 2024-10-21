package io.grpc.util;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.ConnectivityState;
import io.grpc.LoadBalancer;
import io.grpc.SynchronizationContext;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingLoadBalancerHelper extends LoadBalancer.Helper {
    @Override // io.grpc.LoadBalancer.Helper
    public final LoadBalancer.Subchannel createSubchannel(LoadBalancer.CreateSubchannelArgs createSubchannelArgs) {
        return delegate().createSubchannel(createSubchannelArgs);
    }

    protected abstract LoadBalancer.Helper delegate();

    @Override // io.grpc.LoadBalancer.Helper
    public final ScheduledExecutorService getScheduledExecutorService() {
        return delegate().getScheduledExecutorService();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public final SynchronizationContext getSynchronizationContext() {
        return delegate().getSynchronizationContext();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public final void refreshNameResolution() {
        delegate().refreshNameResolution();
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate());
        return stringHelper.toString();
    }

    @Override // io.grpc.LoadBalancer.Helper
    public void updateBalancingState(ConnectivityState connectivityState, LoadBalancer.SubchannelPicker subchannelPicker) {
        throw null;
    }
}
