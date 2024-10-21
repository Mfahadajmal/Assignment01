package kotlin.collections;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class IndexedValue {
    public final int index;
    public final Object value;

    public IndexedValue(int i, Object obj) {
        this.index = i;
        this.value = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndexedValue)) {
            return false;
        }
        IndexedValue indexedValue = (IndexedValue) obj;
        if (this.index == indexedValue.index && Intrinsics.areEqual(this.value, indexedValue.value)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        Object obj = this.value;
        if (obj == null) {
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
        }
        return (this.index * 31) + hashCode;
    }

    public final String toString() {
        return "IndexedValue(index=" + this.index + ", value=" + this.value + ")";
    }
}
