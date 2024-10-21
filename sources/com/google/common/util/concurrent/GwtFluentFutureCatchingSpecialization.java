package com.google.common.util.concurrent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GwtFluentFutureCatchingSpecialization extends AbstractFuture {
    public GwtFluentFutureCatchingSpecialization() {
    }

    public static GwtFluentFutureCatchingSpecialization from$ar$class_merging(ListenableFuture listenableFuture) {
        if (listenableFuture instanceof GwtFluentFutureCatchingSpecialization) {
            return (GwtFluentFutureCatchingSpecialization) listenableFuture;
        }
        return new ForwardingFluentFuture(listenableFuture);
    }

    public GwtFluentFutureCatchingSpecialization(byte[] bArr) {
        this();
    }
}
