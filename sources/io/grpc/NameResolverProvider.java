package io.grpc;

import io.grpc.NameResolver;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NameResolverProvider extends NameResolver.Factory {
    public Collection getProducedSocketAddressTypes() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void isAvailable$ar$ds$f3439281_1();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void priority$ar$ds();
}
