package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Absent extends Optional {
    public static final Absent INSTANCE = new Absent();
    private static final long serialVersionUID = 0;

    private Absent() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public final Object get() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    @Override // com.google.common.base.Optional
    public final int hashCode() {
        return 2040732332;
    }

    @Override // com.google.common.base.Optional
    public final boolean isPresent() {
        return false;
    }

    @Override // com.google.common.base.Optional
    public final Object or(Supplier supplier) {
        return supplier.get();
    }

    @Override // com.google.common.base.Optional
    public final Object orNull() {
        return null;
    }

    public final String toString() {
        return "Optional.absent()";
    }

    @Override // com.google.common.base.Optional
    public final Optional transform(Function function) {
        return INSTANCE;
    }

    @Override // com.google.common.base.Optional
    public final Object or(Object obj) {
        obj.getClass();
        return obj;
    }
}
