package com.google.common.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Present extends Optional {
    private static final long serialVersionUID = 0;
    public final Object reference;

    public Present(Object obj) {
        this.reference = obj;
    }

    @Override // com.google.common.base.Optional
    public final boolean equals(Object obj) {
        if (obj instanceof Present) {
            return this.reference.equals(((Present) obj).reference);
        }
        return false;
    }

    @Override // com.google.common.base.Optional
    public final Object get() {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final int hashCode() {
        return this.reference.hashCode() + 1502476572;
    }

    @Override // com.google.common.base.Optional
    public final boolean isPresent() {
        return true;
    }

    @Override // com.google.common.base.Optional
    public final Object or(Supplier supplier) {
        return this.reference;
    }

    @Override // com.google.common.base.Optional
    public final Object orNull() {
        return this.reference;
    }

    public final String toString() {
        return "Optional.of(" + this.reference.toString() + ")";
    }

    @Override // com.google.common.base.Optional
    public final Optional transform(Function function) {
        Object apply = function.apply(this.reference);
        apply.getClass();
        return new Present(apply);
    }

    @Override // com.google.common.base.Optional
    public final Object or(Object obj) {
        obj.getClass();
        return this.reference;
    }
}
