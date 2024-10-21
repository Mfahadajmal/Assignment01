package androidx.collection;

import _COROUTINE._BOUNDARY;
import androidx.collection.internal.ContainerHelpersKt;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SimpleArrayMap {
    public Object[] array;
    public int[] hashes;
    public int size;

    public SimpleArrayMap() {
        this((byte[]) null);
    }

    private final int indexOf(Object obj, int i) {
        int i2 = this.size;
        if (i2 == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpersKt.binarySearch(this.hashes, i2, i);
        if (binarySearch < 0) {
            return binarySearch;
        }
        if (Intrinsics.areEqual(obj, this.array[binarySearch + binarySearch])) {
            return binarySearch;
        }
        int i3 = binarySearch + 1;
        while (i3 < i2 && this.hashes[i3] == i) {
            if (Intrinsics.areEqual(obj, this.array[i3 + i3])) {
                return i3;
            }
            i3++;
        }
        for (int i4 = binarySearch - 1; i4 >= 0 && this.hashes[i4] == i; i4--) {
            if (Intrinsics.areEqual(obj, this.array[i4 + i4])) {
                return i4;
            }
        }
        return ~i3;
    }

    private final int indexOfNull() {
        int i = this.size;
        if (i == 0) {
            return -1;
        }
        int binarySearch = ContainerHelpersKt.binarySearch(this.hashes, i, 0);
        if (binarySearch < 0) {
            return binarySearch;
        }
        if (this.array[binarySearch + binarySearch] == null) {
            return binarySearch;
        }
        int i2 = binarySearch + 1;
        while (i2 < i && this.hashes[i2] == 0) {
            if (this.array[i2 + i2] == null) {
                return i2;
            }
            i2++;
        }
        for (int i3 = binarySearch - 1; i3 >= 0 && this.hashes[i3] == 0; i3--) {
            if (this.array[i3 + i3] == null) {
                return i3;
            }
        }
        return ~i2;
    }

    public final int __restricted$indexOfValue(Object obj) {
        int i = this.size;
        int i2 = i + i;
        Object[] objArr = this.array;
        if (obj == null) {
            for (int i3 = 1; i3 < i2; i3 += 2) {
                if (objArr[i3] == null) {
                    return i3 >> 1;
                }
            }
            return -1;
        }
        for (int i4 = 1; i4 < i2; i4 += 2) {
            if (Intrinsics.areEqual(obj, objArr[i4])) {
                return i4 >> 1;
            }
        }
        return -1;
    }

    public final void clear() {
        if (this.size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this.size = 0;
        }
    }

    public final boolean containsKey(Object obj) {
        if (indexOfKey(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(Object obj) {
        if (__restricted$indexOfValue(obj) >= 0) {
            return true;
        }
        return false;
    }

    public final void ensureCapacity(int i) {
        int i2 = this.size;
        int[] iArr = this.hashes;
        if (iArr.length < i) {
            int[] copyOf = Arrays.copyOf(iArr, i);
            copyOf.getClass();
            this.hashes = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.array, i + i);
            copyOf2.getClass();
            this.array = copyOf2;
        }
        if (this.size == i2) {
        } else {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        try {
            if (obj instanceof SimpleArrayMap) {
                int i = this.size;
                SimpleArrayMap simpleArrayMap = (SimpleArrayMap) obj;
                if (i != simpleArrayMap.size) {
                    return false;
                }
                for (int i2 = 0; i2 < i; i2++) {
                    Object keyAt = keyAt(i2);
                    Object valueAt = valueAt(i2);
                    Object obj2 = simpleArrayMap.get(keyAt);
                    if (valueAt == null) {
                        if (obj2 != null || !simpleArrayMap.containsKey(keyAt)) {
                            return false;
                        }
                    } else if (!Intrinsics.areEqual(valueAt, obj2)) {
                        return false;
                    }
                }
                return true;
            }
            if (!(obj instanceof Map) || this.size != ((Map) obj).size()) {
                return false;
            }
            int i3 = this.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Object keyAt2 = keyAt(i4);
                Object valueAt2 = valueAt(i4);
                Map map = (Map) obj;
                Object obj3 = map.get(keyAt2);
                if (valueAt2 == null) {
                    if (obj3 != null || !map.containsKey(keyAt2)) {
                        return false;
                    }
                } else if (!Intrinsics.areEqual(valueAt2, obj3)) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
        }
        return false;
    }

    public final Object get(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return this.array[indexOfKey + indexOfKey + 1];
        }
        return null;
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return this.array[indexOfKey + indexOfKey + 1];
        }
        return obj2;
    }

    public final int hashCode() {
        int i;
        int[] iArr = this.hashes;
        Object[] objArr = this.array;
        int i2 = this.size;
        int i3 = 1;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Object obj = objArr[i3];
            int i6 = iArr[i4];
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i5 += i ^ i6;
            i4++;
            i3 += 2;
        }
        return i5;
    }

    public final int indexOfKey(Object obj) {
        if (obj == null) {
            return indexOfNull();
        }
        return indexOf(obj, obj.hashCode());
    }

    public final boolean isEmpty() {
        if (this.size <= 0) {
            return true;
        }
        return false;
    }

    public final Object keyAt(int i) {
        if (i >= 0 && i < this.size) {
            return this.array[i + i];
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }

    public final Object put(Object obj, Object obj2) {
        int i;
        int indexOfNull;
        int i2 = this.size;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        if (obj != null) {
            indexOfNull = indexOf(obj, i);
        } else {
            indexOfNull = indexOfNull();
        }
        if (indexOfNull >= 0) {
            int i3 = indexOfNull + indexOfNull + 1;
            Object[] objArr = this.array;
            Object obj3 = objArr[i3];
            objArr[i3] = obj2;
            return obj3;
        }
        int i4 = ~indexOfNull;
        int[] iArr = this.hashes;
        if (i2 >= iArr.length) {
            int i5 = 8;
            if (i2 >= 8) {
                i5 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i5 = 4;
            }
            int[] copyOf = Arrays.copyOf(iArr, i5);
            copyOf.getClass();
            this.hashes = copyOf;
            Object[] copyOf2 = Arrays.copyOf(this.array, i5 + i5);
            copyOf2.getClass();
            this.array = copyOf2;
            if (i2 != this.size) {
                throw new ConcurrentModificationException();
            }
        }
        if (i4 < i2) {
            int[] iArr2 = this.hashes;
            int i6 = i4 + 1;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr2, iArr2, i6, i4, i2);
            Object[] objArr2 = this.array;
            int i7 = this.size;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i6 + i6, i4 + i4, i7 + i7);
        }
        int i8 = this.size;
        if (i2 == i8) {
            int[] iArr3 = this.hashes;
            if (i4 < iArr3.length) {
                iArr3[i4] = i;
                Object[] objArr3 = this.array;
                int i9 = i4 + i4;
                objArr3[i9] = obj;
                objArr3[i9 + 1] = obj2;
                this.size = i8 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Object obj3 = get(obj);
        if (obj3 == null) {
            return put(obj, obj2);
        }
        return obj3;
    }

    public final Object remove(Object obj) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return removeAt(indexOfKey);
        }
        return null;
    }

    public final Object removeAt(int i) {
        int i2;
        if (i >= 0 && i < (i2 = this.size)) {
            Object[] objArr = this.array;
            int i3 = i + i;
            Object obj = objArr[i3 + 1];
            if (i2 <= 1) {
                clear();
            } else {
                int i4 = i2 - 1;
                int[] iArr = this.hashes;
                int length = iArr.length;
                int i5 = 8;
                if (length > 8 && i2 < length / 3) {
                    if (i2 > 8) {
                        i5 = i2 + (i2 >> 1);
                    }
                    int[] copyOf = Arrays.copyOf(iArr, i5);
                    copyOf.getClass();
                    this.hashes = copyOf;
                    Object[] copyOf2 = Arrays.copyOf(this.array, i5 + i5);
                    copyOf2.getClass();
                    this.array = copyOf2;
                    if (i2 == this.size) {
                        if (i > 0) {
                            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, this.hashes, 0, 0, i);
                            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, this.array, 0, 0, i3);
                        }
                        if (i < i4) {
                            int i6 = i + 1;
                            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, this.hashes, i, i6, i2);
                            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, this.array, i3, i6 + i6, i2 + i2);
                        }
                    } else {
                        throw new ConcurrentModificationException();
                    }
                } else {
                    if (i < i4) {
                        int i7 = i + 1;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, iArr, i, i7, i2);
                        Object[] objArr2 = this.array;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i3, i7 + i7, i2 + i2);
                    }
                    Object[] objArr3 = this.array;
                    int i8 = i4 + i4;
                    objArr3[i8] = null;
                    objArr3[i8 + 1] = null;
                }
                if (i2 == this.size) {
                    this.size = i4;
                } else {
                    throw new ConcurrentModificationException();
                }
            }
            return obj;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }

    public final Object replace(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey >= 0) {
            return setValueAt(indexOfKey, obj2);
        }
        return null;
    }

    public final Object setValueAt(int i, Object obj) {
        if (i >= 0 && i < this.size) {
            Object[] objArr = this.array;
            int i2 = i + i + 1;
            Object obj2 = objArr[i2];
            objArr[i2] = obj;
            return obj2;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }

    public final int size() {
        return this.size;
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this.size * 28);
        sb.append('{');
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object keyAt = keyAt(i2);
            if (keyAt != sb) {
                sb.append(keyAt);
            } else {
                sb.append("(this Map)");
            }
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
        if (i >= 0 && i < this.size) {
            return this.array[i + i + 1];
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected index to be within 0..size()-1, but was "));
    }

    public SimpleArrayMap(int i) {
        this.hashes = i == 0 ? ContainerHelpersKt.EMPTY_INTS : new int[i];
        this.array = i == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[i + i];
    }

    public final boolean remove(Object obj, Object obj2) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey < 0 || !Intrinsics.areEqual(obj2, valueAt(indexOfKey))) {
            return false;
        }
        removeAt(indexOfKey);
        return true;
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        int indexOfKey = indexOfKey(obj);
        if (indexOfKey < 0 || !Intrinsics.areEqual(obj2, valueAt(indexOfKey))) {
            return false;
        }
        setValueAt(indexOfKey, obj3);
        return true;
    }

    public /* synthetic */ SimpleArrayMap(byte[] bArr) {
        this(0);
    }
}
