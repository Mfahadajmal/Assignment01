package io.grpc.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedResourcePool implements ObjectPool {
    private final Object SharedResourcePool$ar$resource;
    private final /* synthetic */ int switching_field;

    public SharedResourcePool(Object obj, int i) {
        this.switching_field = i;
        this.SharedResourcePool$ar$resource = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.grpc.internal.SharedResourceHolder$Resource, java.lang.Object] */
    @Override // io.grpc.internal.ObjectPool
    public final Object getObject() {
        if (this.switching_field != 0) {
            return this.SharedResourcePool$ar$resource;
        }
        return SharedResourceHolder.get(this.SharedResourcePool$ar$resource);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [io.grpc.internal.SharedResourceHolder$Resource, java.lang.Object] */
    @Override // io.grpc.internal.ObjectPool
    public final void returnObject$ar$ds(Object obj) {
        if (this.switching_field == 0) {
            SharedResourceHolder.holder.releaseInternal$ar$ds(this.SharedResourcePool$ar$resource, obj);
        }
    }
}
