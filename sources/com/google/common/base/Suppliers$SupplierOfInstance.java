package com.google.common.base;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Suppliers$SupplierOfInstance implements Supplier, Serializable {
    private static final long serialVersionUID = 0;
    public final Object instance;

    public Suppliers$SupplierOfInstance(Object obj) {
        this.instance = obj;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Suppliers$SupplierOfInstance) {
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.instance, ((Suppliers$SupplierOfInstance) obj).instance);
        }
        return false;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        return this.instance;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.instance});
    }

    public final String toString() {
        return "Suppliers.ofInstance(" + this.instance.toString() + ")";
    }
}
