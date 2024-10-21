package io.grpc.internal;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.PickFirstLeafLoadBalancer;
import io.grpc.internal.PickFirstLoadBalancer;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PickFirstLoadBalancerProvider extends LoadBalancerProvider {
    public static final /* synthetic */ int PickFirstLoadBalancerProvider$ar$NoOp = 0;
    static final boolean enableNewPickFirst = GrpcUtil.getFlag$ar$ds("GRPC_EXPERIMENTAL_ENABLE_NEW_PICK_FIRST");

    @Override // io.grpc.LoadBalancerProvider
    public final String getPolicyName() {
        return "pick_first";
    }

    @Override // io.grpc.LoadBalancer.Factory
    public final LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        if (enableNewPickFirst) {
            return new PickFirstLeafLoadBalancer(helper);
        }
        return new PickFirstLoadBalancer(helper);
    }

    @Override // io.grpc.LoadBalancerProvider
    public final NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map map) {
        Object pickFirstLoadBalancerConfig;
        try {
            Boolean bool = JsonUtil.getBoolean(map, "shuffleAddressList");
            if (enableNewPickFirst) {
                pickFirstLoadBalancerConfig = new PickFirstLeafLoadBalancer.PickFirstLeafLoadBalancerConfig(bool);
            } else {
                pickFirstLoadBalancerConfig = new PickFirstLoadBalancer.PickFirstLoadBalancerConfig(bool);
            }
            return new NameResolver.ConfigOrError(pickFirstLoadBalancerConfig);
        } catch (RuntimeException e) {
            return new NameResolver.ConfigOrError(Status.UNAVAILABLE.withCause(e).withDescription("Failed parsing configuration for ".concat("pick_first")));
        }
    }

    @Override // io.grpc.LoadBalancerProvider
    public final void getPriority$ar$ds() {
    }

    @Override // io.grpc.LoadBalancerProvider
    public final void isAvailable$ar$ds() {
    }
}
