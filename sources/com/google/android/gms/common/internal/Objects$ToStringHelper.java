package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Objects$ToStringHelper {
    private final List fieldStrings = new ArrayList();
    private final Object instance;

    public Objects$ToStringHelper(Object obj) {
        this.instance = obj;
    }

    public final void add$ar$ds$bdea682c_0(String str, Object obj) {
        this.fieldStrings.add(str + "=" + String.valueOf(obj));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.instance.getClass().getSimpleName());
        sb.append('{');
        int size = this.fieldStrings.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) this.fieldStrings.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
