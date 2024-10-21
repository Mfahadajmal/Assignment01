package io.grpc.internal;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.util.MultiChildLoadBalancer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer {
    public LoadBalancer delegate;
    public LoadBalancerProvider delegateProvider;
    public final LoadBalancer.Helper helper;
    final /* synthetic */ MultiChildLoadBalancer.AcceptResolvedAddrRetVal this$0$ar$class_merging$3174ccf_0;

    public AutoConfiguredLoadBalancerFactory$AutoConfiguredLoadBalancer(MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal, LoadBalancer.Helper helper) {
        this.this$0$ar$class_merging$3174ccf_0 = acceptResolvedAddrRetVal;
        this.helper = helper;
        LoadBalancerProvider provider = ((LoadBalancerRegistry) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$status).getProvider((String) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren);
        this.delegateProvider = provider;
        if (provider != null) {
            this.delegate = provider.newLoadBalancer(helper);
            return;
        }
        throw new IllegalStateException("Could not find policy '" + ((String) acceptResolvedAddrRetVal.MultiChildLoadBalancer$AcceptResolvedAddrRetVal$ar$removedChildren) + "'. Make sure its implementation is either registered to LoadBalancerRegistry or included in META-INF/services/io.grpc.LoadBalancerProvider from your jar files.");
    }
}
