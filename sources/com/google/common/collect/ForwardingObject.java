package com.google.common.collect;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingObject {
    protected abstract Object delegate();

    public final String toString() {
        return delegate().toString();
    }
}
