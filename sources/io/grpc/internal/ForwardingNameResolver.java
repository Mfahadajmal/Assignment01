package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.NameResolver;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class ForwardingNameResolver extends NameResolver {
    public final NameResolver delegate;

    public ForwardingNameResolver(NameResolver nameResolver) {
        this.delegate = nameResolver;
    }

    @Override // io.grpc.NameResolver
    public final String getServiceAuthority() {
        return this.delegate.getServiceAuthority();
    }

    @Override // io.grpc.NameResolver
    public final void refresh() {
        this.delegate.refresh();
    }

    @Override // io.grpc.NameResolver
    public void shutdown() {
        throw null;
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", this.delegate);
        return stringHelper.toString();
    }
}
