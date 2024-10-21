package com.google.common.base;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Functions$ConstantFunction implements Function, Serializable {
    private static final long serialVersionUID = 0;
    private final Object value = null;

    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return null;
    }

    @Override // com.google.common.base.Function
    public final boolean equals(Object obj) {
        if (obj instanceof Functions$ConstantFunction) {
            Object obj2 = ((Functions$ConstantFunction) obj).value;
            return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(null, null);
        }
        return false;
    }

    public final int hashCode() {
        return 0;
    }

    public final String toString() {
        return "Functions.constant(null)";
    }
}
