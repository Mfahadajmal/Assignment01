package com.google.firebase.components;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Qualified {
    private final Class qualifier;
    private final Class type;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public @interface Unqualified {
    }

    public Qualified(Class cls, Class cls2) {
        this.qualifier = cls;
        this.type = cls2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Qualified qualified = (Qualified) obj;
        if (!this.type.equals(qualified.type)) {
            return false;
        }
        return this.qualifier.equals(qualified.qualifier);
    }

    public final int hashCode() {
        return (this.type.hashCode() * 31) + this.qualifier.hashCode();
    }

    public final String toString() {
        return this.type.getName();
    }
}
