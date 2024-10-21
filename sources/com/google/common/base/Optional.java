package com.google.common.base;

import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public static Optional fromNullable(Object obj) {
        if (obj == null) {
            return Absent.INSTANCE;
        }
        return new Present(obj);
    }

    public static Optional of(Object obj) {
        obj.getClass();
        return new Present(obj);
    }

    public abstract boolean equals(Object obj);

    public abstract Object get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Object or(Supplier supplier);

    public abstract Object or(Object obj);

    public abstract Object orNull();

    public abstract Optional transform(Function function);
}
