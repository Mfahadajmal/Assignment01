package io.grpc;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.LoadBalancer;
import io.grpc.NameResolver;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LoadBalancerProvider extends LoadBalancer.Factory {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return false;
    }

    public abstract String getPolicyName();

    public abstract void getPriority$ar$ds();

    public abstract void isAvailable$ar$ds();

    public NameResolver.ConfigOrError parseLoadBalancingPolicyConfig(Map map) {
        throw null;
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("policy", getPolicyName());
        return stringHelper.add("priority", 5).add("available", true).toString();
    }
}
