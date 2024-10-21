package kotlin.collections;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayDeque extends AbstractMutableList {
    private static final Object[] emptyElementData = new Object[0];
    private Object[] elementData;
    private int head;
    public int size;

    public ArrayDeque() {
        this.elementData = emptyElementData;
    }

    private final void copyCollectionElements(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size += collection.size();
    }

    private final int decremented(int i) {
        if (i == 0) {
            return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.getLastIndex(this.elementData);
        }
        return i - 1;
    }

    private final void ensureCapacity(int i) {
        if (i >= 0) {
            Object[] objArr = this.elementData;
            int length = objArr.length;
            if (i <= length) {
                return;
            }
            if (objArr == emptyElementData) {
                this.elementData = new Object[OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i, 10)];
                return;
            }
            Object[] objArr2 = new Object[OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.newCapacity$kotlin_stdlib$ar$ds(length, i)];
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr2, 0, this.head, length);
            Object[] objArr3 = this.elementData;
            int length2 = objArr3.length;
            int i2 = this.head;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr2, length2 - i2, 0, i2);
            this.head = 0;
            this.elementData = objArr2;
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    private final int incremented(int i) {
        if (i == OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.getLastIndex(this.elementData)) {
            return 0;
        }
        return i + 1;
    }

    private final int negativeMod(int i) {
        if (i < 0) {
            return i + this.elementData.length;
        }
        return i;
    }

    private final int positiveMod(int i) {
        int length = this.elementData.length;
        if (i >= length) {
            return i - length;
        }
        return i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, this.size);
        int i2 = this.size;
        if (i == i2) {
            addLast(obj);
            return;
        }
        if (i != 0) {
            ensureCapacity(i2 + 1);
            int positiveMod = positiveMod(this.head + i);
            int i3 = this.size;
            if (i < ((i3 + 1) >> 1)) {
                int decremented = decremented(positiveMod);
                int decremented2 = decremented(this.head);
                int i4 = this.head;
                if (decremented >= i4) {
                    Object[] objArr = this.elementData;
                    objArr[decremented2] = objArr[i4];
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, i4, i4 + 1, decremented + 1);
                } else {
                    Object[] objArr2 = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i4 - 1, i4, objArr2.length);
                    Object[] objArr3 = this.elementData;
                    objArr3[objArr3.length - 1] = objArr3[0];
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr3, 0, 1, decremented + 1);
                }
                this.elementData[decremented] = obj;
                this.head = decremented2;
            } else {
                int positiveMod2 = positiveMod(this.head + i3);
                if (positiveMod < positiveMod2) {
                    Object[] objArr4 = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr4, objArr4, positiveMod + 1, positiveMod, positiveMod2);
                } else {
                    Object[] objArr5 = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr5, objArr5, 1, 0, positiveMod2);
                    Object[] objArr6 = this.elementData;
                    int length = objArr6.length - 1;
                    objArr6[0] = objArr6[length];
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr6, objArr6, positiveMod + 1, positiveMod, length);
                }
                this.elementData[positiveMod] = obj;
            }
            this.size++;
            return;
        }
        addFirst(obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        collection.getClass();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, this.size);
        if (collection.isEmpty()) {
            return false;
        }
        int i2 = this.size;
        if (i != i2) {
            ensureCapacity(i2 + collection.size());
            int positiveMod = positiveMod(this.head + this.size);
            int positiveMod2 = positiveMod(this.head + i);
            int size = collection.size();
            if (i < ((this.size + 1) >> 1)) {
                int i3 = this.head;
                int i4 = i3 - size;
                if (positiveMod2 < i3) {
                    Object[] objArr = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, i4, i3, objArr.length);
                    if (size >= positiveMod2) {
                        Object[] objArr2 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, objArr2.length - size, 0, positiveMod2);
                    } else {
                        Object[] objArr3 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr3, objArr3.length - size, 0, size);
                        Object[] objArr4 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr4, objArr4, 0, size, positiveMod2);
                    }
                } else if (i4 >= 0) {
                    Object[] objArr5 = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr5, objArr5, i4, i3, positiveMod2);
                } else {
                    Object[] objArr6 = this.elementData;
                    int length = objArr6.length;
                    i4 += length;
                    int i5 = length - i4;
                    if (i5 >= positiveMod2 - i3) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr6, objArr6, i4, i3, positiveMod2);
                    } else {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr6, objArr6, i4, i3, i3 + i5);
                        Object[] objArr7 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr7, objArr7, 0, this.head + i5, positiveMod2);
                    }
                }
                this.head = i4;
                copyCollectionElements(negativeMod(positiveMod2 - size), collection);
            } else {
                int i6 = positiveMod2 + size;
                if (positiveMod2 < positiveMod) {
                    int i7 = size + positiveMod;
                    Object[] objArr8 = this.elementData;
                    int length2 = objArr8.length;
                    if (i7 <= length2) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr8, objArr8, i6, positiveMod2, positiveMod);
                    } else if (i6 >= length2) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr8, objArr8, i6 - length2, positiveMod2, positiveMod);
                    } else {
                        int i8 = positiveMod - (i7 - length2);
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr8, objArr8, 0, i8, positiveMod);
                        Object[] objArr9 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr9, objArr9, i6, positiveMod2, i8);
                    }
                } else {
                    Object[] objArr10 = this.elementData;
                    OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr10, objArr10, size, 0, positiveMod);
                    Object[] objArr11 = this.elementData;
                    int length3 = objArr11.length;
                    if (i6 >= length3) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr11, objArr11, i6 - length3, positiveMod2, length3);
                    } else {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr11, objArr11, 0, length3 - size, length3);
                        Object[] objArr12 = this.elementData;
                        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr12, objArr12, i6, positiveMod2, objArr12.length - size);
                    }
                }
                copyCollectionElements(positiveMod2, collection);
            }
            return true;
        }
        return addAll(collection);
    }

    public final void addFirst(Object obj) {
        ensureCapacity(this.size + 1);
        int decremented = decremented(this.head);
        this.head = decremented;
        this.elementData[decremented] = obj;
        this.size++;
    }

    public final void addLast(Object obj) {
        ensureCapacity(this.size + 1);
        this.elementData[positiveMod(this.head + this.size)] = obj;
        this.size++;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int positiveMod = positiveMod(this.head + this.size);
        int i = this.head;
        if (i < positiveMod) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.fill(this.elementData, null, i, positiveMod);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.fill(objArr, null, this.head, objArr.length);
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.fill(this.elementData, null, 0, positiveMod);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (indexOf(obj) != -1) {
            return true;
        }
        return false;
    }

    public final Object first() {
        if (!isEmpty()) {
            return this.elementData[this.head];
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final Object firstOrNull() {
        if (isEmpty()) {
            return null;
        }
        return this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.size);
        return this.elementData[positiveMod(this.head + i)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int positiveMod = positiveMod(this.head + this.size);
        int i2 = this.head;
        if (i2 < positiveMod) {
            while (i2 < positiveMod) {
                if (!Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i2++;
                }
            }
            return -1;
        }
        if (i2 >= positiveMod) {
            int length = this.elementData.length;
            while (i2 < length) {
                if (!Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i2++;
                }
            }
            for (int i3 = 0; i3 < positiveMod; i3++) {
                if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                    i2 = i3 + this.elementData.length;
                    i = this.head;
                    return i2 - i;
                }
            }
            return -1;
        }
        return -1;
        i = this.head;
        return i2 - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    public final Object last() {
        if (!isEmpty()) {
            return this.elementData[positiveMod(this.head + OnDeviceLanguageIdentificationLogEvent.getLastIndex(this))];
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int lastIndex;
        int i;
        int positiveMod = positiveMod(this.head + this.size);
        int i2 = this.head;
        if (i2 < positiveMod) {
            lastIndex = positiveMod - 1;
            if (i2 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                    if (lastIndex != i2) {
                        lastIndex--;
                    }
                }
                i = this.head;
            }
            return -1;
        }
        if (i2 > positiveMod) {
            for (int i3 = positiveMod - 1; i3 >= 0; i3--) {
                if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                    lastIndex = i3 + this.elementData.length;
                    i = this.head;
                    break;
                }
            }
            lastIndex = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.getLastIndex(this.elementData);
            int i4 = this.head;
            if (i4 <= lastIndex) {
                while (!Intrinsics.areEqual(obj, this.elementData[lastIndex])) {
                    if (lastIndex != i4) {
                        lastIndex--;
                    }
                }
                i = this.head;
            }
        }
        return -1;
        return lastIndex - i;
    }

    public final Object lastOrNull() {
        if (isEmpty()) {
            return null;
        }
        return this.elementData[positiveMod(this.head + OnDeviceLanguageIdentificationLogEvent.getLastIndex(this))];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int positiveMod;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.head + this.size);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (!collection.contains(obj)) {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.fill(this.elementData, null, positiveMod, positiveMod2);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (!collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (!collection.contains(obj3)) {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.size = negativeMod(positiveMod - this.head);
                return true;
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.size);
        if (i == OnDeviceLanguageIdentificationLogEvent.getLastIndex(this)) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        int positiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        Object obj = objArr[positiveMod];
        if (i < (this.size >> 1)) {
            int i2 = this.head;
            if (positiveMod >= i2) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, i2 + 1, i2, positiveMod);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, 1, 0, positiveMod);
                Object[] objArr2 = this.elementData;
                int length = objArr2.length - 1;
                objArr2[0] = objArr2[length];
                int i3 = this.head;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i3 + 1, i3, length);
            }
            Object[] objArr3 = this.elementData;
            int i4 = this.head;
            objArr3[i4] = null;
            this.head = incremented(i4);
        } else {
            int positiveMod2 = positiveMod(this.head + OnDeviceLanguageIdentificationLogEvent.getLastIndex(this));
            if (positiveMod <= positiveMod2) {
                Object[] objArr4 = this.elementData;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr4, objArr4, positiveMod, positiveMod + 1, positiveMod2 + 1);
            } else {
                Object[] objArr5 = this.elementData;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr5, objArr5, positiveMod, positiveMod + 1, objArr5.length);
                Object[] objArr6 = this.elementData;
                objArr6[objArr6.length - 1] = objArr6[0];
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr6, objArr6, 0, 1, positiveMod2 + 1);
            }
            this.elementData[positiveMod2] = null;
        }
        this.size--;
        return obj;
    }

    public final Object removeFirst() {
        if (!isEmpty()) {
            Object[] objArr = this.elementData;
            int i = this.head;
            Object obj = objArr[i];
            objArr[i] = null;
            this.head = incremented(i);
            this.size--;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    public final Object removeLast() {
        if (!isEmpty()) {
            int positiveMod = positiveMod(this.head + OnDeviceLanguageIdentificationLogEvent.getLastIndex(this));
            Object[] objArr = this.elementData;
            Object obj = objArr[positiveMod];
            objArr[positiveMod] = null;
            this.size--;
            return obj;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int positiveMod;
        collection.getClass();
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.head + this.size);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.fill(this.elementData, null, positiveMod, positiveMod2);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                this.size = negativeMod(positiveMod - this.head);
                return true;
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.size);
        int positiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        Object obj2 = objArr[positiveMod];
        objArr[positiveMod] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[this.size]);
    }

    public ArrayDeque(int i) {
        this.elementData = i == 0 ? emptyElementData : new Object[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        int length = objArr.length;
        int i = this.size;
        if (length < i) {
            Object newInstance = Array.newInstance(objArr.getClass().getComponentType(), i);
            newInstance.getClass();
            objArr = (Object[]) newInstance;
        }
        int positiveMod = positiveMod(this.head + this.size);
        int i2 = this.head;
        if (i2 < positiveMod) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds$41d531b2_0(this.elementData, objArr, i2, positiveMod, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.elementData;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr, 0, this.head, objArr2.length);
            Object[] objArr3 = this.elementData;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr, objArr3.length - this.head, 0, positiveMod);
        }
        OnDeviceLanguageIdentificationLogEvent.terminateCollectionToArray$ar$ds(this.size, objArr);
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        collection.getClass();
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(this.size + collection.size());
        copyCollectionElements(positiveMod(this.head + this.size), collection);
        return true;
    }
}
