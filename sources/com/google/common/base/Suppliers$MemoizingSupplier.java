package com.google.common.base;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Suppliers$MemoizingSupplier implements Supplier, Serializable {
    private static final long serialVersionUID = 0;
    final Supplier delegate;
    volatile transient boolean initialized;
    transient Object value;

    public Suppliers$MemoizingSupplier(Supplier supplier) {
        this.delegate = supplier;
    }

    @Override // com.google.common.base.Supplier
    public final Object get() {
        if (!this.initialized) {
            synchronized (this) {
                if (!this.initialized) {
                    Object obj = this.delegate.get();
                    this.value = obj;
                    this.initialized = true;
                    return obj;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj;
        if (this.initialized) {
            obj = "<supplier that returned " + String.valueOf(this.value) + ">";
        } else {
            obj = this.delegate;
        }
        return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_11(obj, "Suppliers.memoize(", ")");
    }
}
