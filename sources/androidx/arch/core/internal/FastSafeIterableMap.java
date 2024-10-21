package androidx.arch.core.internal;

import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FastSafeIterableMap extends SafeIterableMap {
    public final HashMap mHashMap = new HashMap();

    public final boolean contains(Object obj) {
        return this.mHashMap.containsKey(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public final SafeIterableMap.Entry get(Object obj) {
        return (SafeIterableMap.Entry) this.mHashMap.get(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public final Object remove(Object obj) {
        Object remove = super.remove(obj);
        this.mHashMap.remove(obj);
        return remove;
    }
}
