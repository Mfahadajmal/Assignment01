package androidx.collection;

import _COROUTINE._BOUNDARY;
import androidx.collection.internal.ContainerHelpersKt;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LongSparseArray implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ long[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public LongSparseArray() {
        int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(10);
        this.keys = new long[idealLongArraySize];
        this.values = new Object[idealLongArraySize];
    }

    public final void clear() {
        int i = this.size;
        Object[] objArr = this.values;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        Object clone = super.clone();
        clone.getClass();
        LongSparseArray longSparseArray = (LongSparseArray) clone;
        longSparseArray.keys = (long[]) this.keys.clone();
        longSparseArray.values = (Object[]) this.values.clone();
        return longSparseArray;
    }

    public final Object get(long j) {
        Object obj;
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0 && (obj = this.values[binarySearch]) != LongSparseArrayKt.DELETED) {
            return obj;
        }
        return null;
    }

    public final long keyAt(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.size)) {
            if (this.garbage) {
                long[] jArr = this.keys;
                Object[] objArr = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr[i4];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
            }
            return this.keys[i];
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }

    public final void put(long j, Object obj) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, j);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i = ~binarySearch;
        int i2 = this.size;
        if (i < i2) {
            Object[] objArr = this.values;
            if (objArr[i] == LongSparseArrayKt.DELETED) {
                this.keys[i] = j;
                objArr[i] = obj;
                return;
            }
        }
        if (this.garbage) {
            long[] jArr = this.keys;
            if (i2 >= jArr.length) {
                Object[] objArr2 = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj2 = objArr2[i4];
                    if (obj2 != LongSparseArrayKt.DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr2[i3] = obj2;
                            objArr2[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
                i = ~ContainerHelpersKt.binarySearch(this.keys, i3, j);
            }
        }
        int i5 = this.size;
        long[] jArr2 = this.keys;
        if (i5 >= jArr2.length) {
            int idealLongArraySize = ContainerHelpersKt.idealLongArraySize(i5 + 1);
            long[] copyOf = Arrays.copyOf(jArr2, idealLongArraySize);
            copyOf.getClass();
            this.keys = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.values, idealLongArraySize);
            copyOf2.getClass();
            this.values = copyOf2;
        }
        int i6 = this.size - i;
        if (i6 != 0) {
            long[] jArr3 = this.keys;
            int i7 = i + 1;
            jArr3.getClass();
            jArr3.getClass();
            System.arraycopy(jArr3, i, jArr3, i7, i6);
            Object[] objArr3 = this.values;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr3, i7, i, this.size);
        }
        this.keys[i] = j;
        this.values[i] = obj;
        this.size++;
    }

    public final int size() {
        if (this.garbage) {
            int i = this.size;
            long[] jArr = this.keys;
            Object[] objArr = this.values;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                if (obj != LongSparseArrayKt.DELETED) {
                    if (i3 != i2) {
                        jArr[i2] = jArr[i3];
                        objArr[i2] = obj;
                        objArr[i3] = null;
                    }
                    i2++;
                }
            }
            this.garbage = false;
            this.size = i2;
        }
        return this.size;
    }

    public final String toString() {
        if (size() <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            sb.append(keyAt(i2));
            sb.append('=');
            Object valueAt = valueAt(i2);
            if (valueAt != sb) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final Object valueAt(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.size)) {
            if (this.garbage) {
                long[] jArr = this.keys;
                Object[] objArr = this.values;
                int i3 = 0;
                for (int i4 = 0; i4 < i2; i4++) {
                    Object obj = objArr[i4];
                    if (obj != LongSparseArrayKt.DELETED) {
                        if (i4 != i3) {
                            jArr[i3] = jArr[i4];
                            objArr[i3] = obj;
                            objArr[i4] = null;
                        }
                        i3++;
                    }
                }
                this.garbage = false;
                this.size = i3;
            }
            return this.values[i];
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }
}
