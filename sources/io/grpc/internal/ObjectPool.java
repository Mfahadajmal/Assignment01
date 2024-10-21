package io.grpc.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ObjectPool {
    Object getObject();

    void returnObject$ar$ds(Object obj);
}
