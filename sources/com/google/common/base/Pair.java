package com.google.common.base;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Pair implements Serializable {
    private static final long serialVersionUID = 747826592375603043L;
    public final Object first;
    public final Object second;

    public Pair(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Pair) {
            Pair pair = (Pair) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.first, pair.first) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.second, pair.second)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        Object obj = this.first;
        int i = 0;
        if (obj == null) {
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
        }
        Object obj2 = this.second;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return (hashCode * 31) + i;
    }

    public final String toString() {
        Object obj = this.second;
        return "(" + String.valueOf(this.first) + ", " + String.valueOf(obj) + ")";
    }
}
