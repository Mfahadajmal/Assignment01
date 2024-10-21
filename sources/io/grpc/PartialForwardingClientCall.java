package io.grpc;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class PartialForwardingClientCall extends ClientCall {
    @Override // io.grpc.ClientCall
    public void cancel(String str, Throwable th) {
        delegate().cancel(str, th);
    }

    protected abstract ClientCall delegate();

    @Override // io.grpc.ClientCall
    public void halfClose() {
        delegate().halfClose();
    }

    @Override // io.grpc.ClientCall
    public void request(int i) {
        delegate().request(i);
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate());
        return stringHelper.toString();
    }
}
