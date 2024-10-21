package androidx.collection;

import android.support.v7.widget.DrawableUtils;
import androidx.collection.internal.ContainerHelpersKt;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArraySet implements Collection, Set, KMutableCollection {
    public int _size;
    public Object[] array;
    public int[] hashes;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ElementIterator extends IndexBasedArrayIterator {
        public ElementIterator() {
            super(ArraySet.this._size);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final Object elementAt(int i) {
            return ArraySet.this.valueAt(i);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final void removeAt(int i) {
            ArraySet.this.removeAt$ar$ds(i);
        }
    }

    public ArraySet() {
        this(0);
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean add(Object obj) {
        int i;
        int indexOf;
        int i2 = this._size;
        if (obj == null) {
            indexOf = DrawableUtils.Api29Impl.indexOfNull(this);
            i = 0;
        } else {
            int hashCode = obj.hashCode();
            i = hashCode;
            indexOf = DrawableUtils.Api29Impl.indexOf(this, obj, hashCode);
        }
        if (indexOf >= 0) {
            return false;
        }
        int i3 = ~indexOf;
        int[] iArr = this.hashes;
        int length = iArr.length;
        if (i2 >= length) {
            int i4 = 8;
            if (i2 >= 8) {
                i4 = (i2 >> 1) + i2;
            } else if (i2 < 4) {
                i4 = 4;
            }
            Object[] objArr = this.array;
            DrawableUtils.Api29Impl.allocArrays(this, i4);
            if (i2 == this._size) {
                int[] iArr2 = this.hashes;
                if (iArr2.length != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, iArr2, 0, 0, length);
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds$41d531b2_0(objArr, this.array, 0, objArr.length, 6);
                }
            } else {
                throw new ConcurrentModificationException();
            }
        }
        if (i3 < i2) {
            int[] iArr3 = this.hashes;
            int i5 = i3 + 1;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr3, iArr3, i5, i3, i2);
            Object[] objArr2 = this.array;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i5, i3, i2);
        }
        int i6 = this._size;
        if (i2 == i6) {
            int[] iArr4 = this.hashes;
            if (i3 < iArr4.length) {
                iArr4[i3] = i;
                this.array[i3] = obj;
                this._size = i6 + 1;
                return true;
            }
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean addAll(Collection collection) {
        collection.getClass();
        int size = this._size + collection.size();
        int i = this._size;
        int[] iArr = this.hashes;
        boolean z = false;
        if (iArr.length < size) {
            Object[] objArr = this.array;
            DrawableUtils.Api29Impl.allocArrays(this, size);
            int i2 = this._size;
            if (i2 > 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, this.hashes, 0, 0, i2);
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds$41d531b2_0(objArr, this.array, 0, this._size, 6);
            }
        }
        if (this._size == i) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                z |= add(it.next());
            }
            return z;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final void clear() {
        if (this._size != 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this._size = 0;
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        collection.getClass();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof Set) && this._size == ((Set) obj).size()) {
            try {
                int i = this._size;
                for (int i2 = 0; i2 < i; i2++) {
                    if (((Set) obj).contains(valueAt(i2))) {
                    }
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        int[] iArr = this.hashes;
        int i = this._size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += iArr[i3];
        }
        return i2;
    }

    public final int indexOf(Object obj) {
        if (obj == null) {
            return DrawableUtils.Api29Impl.indexOfNull(this);
        }
        return DrawableUtils.Api29Impl.indexOf(this, obj, obj.hashCode());
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        if (this._size <= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new ElementIterator();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt$ar$ds(indexOf);
            return true;
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        Iterator it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= remove(it.next());
        }
        return z;
    }

    public final void removeAt$ar$ds(int i) {
        int i2 = this._size;
        Object[] objArr = this.array;
        Object obj = objArr[i];
        if (i2 <= 1) {
            clear();
            return;
        }
        int i3 = i2 - 1;
        int[] iArr = this.hashes;
        int length = iArr.length;
        int i4 = 8;
        if (length > 8 && i2 < length / 3) {
            if (i2 > 8) {
                i4 = i2 + (i2 >> 1);
            }
            DrawableUtils.Api29Impl.allocArrays(this, i4);
            if (i > 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, this.hashes, 0, 0, i);
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds$41d531b2_0(objArr, this.array, 0, i, 6);
            }
            if (i < i3) {
                int i5 = i + 1;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, this.hashes, i, i5, i2);
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, this.array, i, i5, i2);
            }
        } else {
            if (i < i3) {
                int i6 = i + 1;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(iArr, iArr, i, i6, i2);
                Object[] objArr2 = this.array;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i, i6, i2);
            }
            this.array[i3] = null;
        }
        if (i2 == this._size) {
            this._size = i3;
            return;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        boolean z = false;
        for (int i = this._size - 1; i >= 0; i--) {
            if (!OnDeviceLanguageIdentificationLogEvent.contains(collection, this.array[i])) {
                removeAt$ar$ds(i);
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.Collection, java.util.Set
    public final int size() {
        return this._size;
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray() {
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyOfRange(this.array, 0, this._size);
    }

    public final String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(this._size * 14);
        sb.append('{');
        int i = this._size;
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 > 0) {
                sb.append(", ");
            }
            Object valueAt = valueAt(i2);
            if (valueAt != this) {
                sb.append(valueAt);
            } else {
                sb.append("(this Set)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public final Object valueAt(int i) {
        return this.array[i];
    }

    public ArraySet(int i) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if (i > 0) {
            DrawableUtils.Api29Impl.allocArrays(this, i);
        }
    }

    @Override // java.util.Collection, java.util.Set
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int i = this._size;
        int length = objArr.length;
        if (length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        } else if (length > i) {
            objArr[i] = null;
        }
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(this.array, objArr, 0, 0, this._size);
        objArr.getClass();
        return objArr;
    }

    public ArraySet(Collection collection) {
        this(0);
        if (collection != null) {
            addAll(collection);
        }
    }
}
