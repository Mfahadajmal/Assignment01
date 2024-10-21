package io.grpc.util;

import io.grpc.LoadBalancer;
import io.grpc.LoadBalancerProvider;
import io.grpc.NameResolver;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SecretRoundRobinLoadBalancerProvider$Provider extends LoadBalancerProvider {
    @Override // io.grpc.LoadBalancerProvider
    public final String getPolicyName() {
        return "round_robin";
    }

    @Override // io.grpc.LoadBalancer.Factory
    public final LoadBalancer newLoadBalancer(LoadBalancer.Helper helper) {
        return new MultiChildLoadBalancer(helper, null);
    }

    @Override // io.grpc.LoadBalancerProvider
    public final NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map map) {
        return new NameResolver.ConfigOrError("no service config");
    }

    @Override // io.grpc.LoadBalancerProvider
    public final void getPriority$ar$ds() {
    }

    @Override // io.grpc.LoadBalancerProvider
    public final void isAvailable$ar$ds() {
    }
}
