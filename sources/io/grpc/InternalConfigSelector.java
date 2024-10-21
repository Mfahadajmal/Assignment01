package io.grpc;

import io.grpc.Attributes;
import io.grpc.util.MultiChildLoadBalancer;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class InternalConfigSelector {
    public static final Attributes.Key KEY = new Attributes.Key("internal:io.grpc.config-selector");

    public abstract MultiChildLoadBalancer.AcceptResolvedAddrRetVal selectConfig$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging();
}
