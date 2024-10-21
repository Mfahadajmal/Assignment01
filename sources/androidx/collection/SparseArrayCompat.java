package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SparseArrayCompat implements Cloneable {
    public /* synthetic */ boolean garbage;
    public /* synthetic */ int[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public SparseArrayCompat() {
        int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(10);
        this.keys = new int[idealIntArraySize];
        this.values = new Object[idealIntArraySize];
    }

    public final void append(int i, Object obj) {
        int i2 = this.size;
        if (i2 != 0 && i <= this.keys[i2 - 1]) {
            put(i, obj);
            return;
        }
        if (this.garbage && i2 >= this.keys.length) {
            SparseArrayCompatKt.gc(this);
        }
        int i3 = this.size;
        int[] iArr = this.keys;
        if (i3 >= iArr.length) {
            int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i3 + 1);
            int[] copyOf = Arrays.copyOf(iArr, idealIntArraySize);
            copyOf.getClass();
            this.keys = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
            copyOf2.getClass();
            this.values = copyOf2;
        }
        this.keys[i3] = i;
        this.values[i3] = obj;
        this.size = i3 + 1;
    }

    public final /* bridge */ /* synthetic */ Object clone() {
        Object clone = super.clone();
        clone.getClass();
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) clone;
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }

    public final int indexOfValue(Object obj) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.values[i2] == obj) {
                return i2;
            }
        }
        return -1;
    }

    public final int keyAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.keys[i];
    }

    public final void put(int i, Object obj) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i2 = ~binarySearch;
        int i3 = this.size;
        if (i2 < i3) {
            Object[] objArr = this.values;
            if (objArr[i2] == SparseArrayCompatKt.DELETED) {
                this.keys[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (this.garbage && i3 >= this.keys.length) {
            SparseArrayCompatKt.gc(this);
            i2 = ~ContainerHelpersKt.binarySearch(this.keys, this.size, i);
        }
        int i4 = this.size;
        int[] iArr = this.keys;
        if (i4 >= iArr.length) {
            int idealIntArraySize = ContainerHelpersKt.idealIntArraySize(i4 + 1);
            int[] copyOf = Arrays.copyOf(iArr, idealIntArraySize);
            copyOf.getClass();
            this.keys = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.values, idealIntArraySize);
            copyOf2.getClass();
            this.values = copyOf2;
        }
        int i5 = this.size;
        if (i5 - i2 != 0) {
            int[] iArr2 = this.keys;
            int i6 = i2 + 1;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr2, iArr2, i6, i2, i5);
            Object[] objArr2 = this.values;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i6, i2, this.size);
        }
        this.keys[i2] = i;
        this.values[i2] = obj;
        this.size++;
    }

    public final int size() {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
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
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final Object valueAt(int i) {
        if (this.garbage) {
            SparseArrayCompatKt.gc(this);
        }
        return this.values[i];
    }
}
