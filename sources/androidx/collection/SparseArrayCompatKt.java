package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SparseArrayCompatKt {
    public static final Object DELETED = new Object();

    public static final Object commonGet(SparseArrayCompat sparseArrayCompat, int i) {
        Object obj;
        int binarySearch = ContainerHelpersKt.binarySearch(sparseArrayCompat.keys, sparseArrayCompat.size, i);
        if (binarySearch >= 0 && (obj = sparseArrayCompat.values[binarySearch]) != DELETED) {
            return obj;
        }
        return null;
    }

    public static final void gc(SparseArrayCompat sparseArrayCompat) {
        int i = sparseArrayCompat.size;
        int[] iArr = sparseArrayCompat.keys;
        Object[] objArr = sparseArrayCompat.values;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != DELETED) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        sparseArrayCompat.garbage = false;
        sparseArrayCompat.size = i2;
    }
}
