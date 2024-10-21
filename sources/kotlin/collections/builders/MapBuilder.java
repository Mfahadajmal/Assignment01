package kotlin.collections.builders;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectInferenceLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.collections.AbstractMutableCollection;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MapBuilder implements Map, Serializable, KMappedMarker {
    public static final OnDeviceObjectInferenceLogEvent Companion$ar$class_merging$1276f993_0$ar$class_merging = new OnDeviceObjectInferenceLogEvent();
    public static final MapBuilder Empty;
    private MapBuilderEntries entriesView;
    private int[] hashArray;
    private int hashShift;
    public boolean isReadOnly;
    public Object[] keysArray;
    private MapBuilderKeys keysView;
    public int length;
    private int maxProbeDistance;
    public int modCount;
    public int[] presenceArray;
    public int size;
    public Object[] valuesArray;
    private AbstractMutableCollection valuesView$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EntriesItr extends Itr implements Iterator, KMappedMarker {
        public EntriesItr(MapBuilder mapBuilder) {
            super(mapBuilder);
        }

        @Override // java.util.Iterator
        public final /* bridge */ /* synthetic */ Object next() {
            checkForComodification$kotlin_stdlib();
            MapBuilder mapBuilder = this.map;
            int i = this.index;
            if (i < mapBuilder.length) {
                this.index = i + 1;
                this.lastIndex = i;
                EntryRef entryRef = new EntryRef(mapBuilder, i);
                initNext$kotlin_stdlib();
                return entryRef;
            }
            throw new NoSuchElementException();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EntryRef implements Map.Entry, KMappedMarker {
        private final int index;
        private final MapBuilder map;

        public EntryRef(MapBuilder mapBuilder, int i) {
            this.map = mapBuilder;
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue())) {
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.map.keysArray[this.index];
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            Object[] objArr = this.map.valuesArray;
            objArr.getClass();
            return objArr[this.index];
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            int i;
            Object key = getKey();
            int i2 = 0;
            if (key != null) {
                i = key.hashCode();
            } else {
                i = 0;
            }
            Object value = getValue();
            if (value != null) {
                i2 = value.hashCode();
            }
            return i ^ i2;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] allocateValuesArray = this.map.allocateValuesArray();
            int i = this.index;
            Object obj2 = allocateValuesArray[i];
            allocateValuesArray[i] = obj;
            return obj2;
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Itr {
        private int expectedModCount;
        public int index;
        public int lastIndex = -1;
        public final MapBuilder map;

        public Itr(MapBuilder mapBuilder) {
            this.map = mapBuilder;
            this.expectedModCount = mapBuilder.modCount;
            initNext$kotlin_stdlib();
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (this.map.modCount == this.expectedModCount) {
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public final boolean hasNext() {
            if (this.index < this.map.length) {
                return true;
            }
            return false;
        }

        public final void initNext$kotlin_stdlib() {
            while (true) {
                int i = this.index;
                MapBuilder mapBuilder = this.map;
                if (i < mapBuilder.length && mapBuilder.presenceArray[i] < 0) {
                    this.index = i + 1;
                } else {
                    return;
                }
            }
        }

        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex != -1) {
                this.map.checkIsMutable$kotlin_stdlib();
                this.map.removeKeyAt(this.lastIndex);
                this.lastIndex = -1;
                this.expectedModCount = this.map.modCount;
                return;
            }
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ValuesItr extends Itr implements Iterator, KMappedMarker {
        private final /* synthetic */ int switching_field;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValuesItr(MapBuilder mapBuilder, int i, byte[] bArr) {
            super(mapBuilder);
            this.switching_field = i;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (this.switching_field != 0) {
                checkForComodification$kotlin_stdlib();
                int i = this.index;
                MapBuilder mapBuilder = this.map;
                if (i < mapBuilder.length) {
                    this.index = i + 1;
                    this.lastIndex = i;
                    Object obj = mapBuilder.keysArray[i];
                    initNext$kotlin_stdlib();
                    return obj;
                }
                throw new NoSuchElementException();
            }
            checkForComodification$kotlin_stdlib();
            int i2 = this.index;
            MapBuilder mapBuilder2 = this.map;
            if (i2 < mapBuilder2.length) {
                this.index = i2 + 1;
                this.lastIndex = i2;
                Object[] objArr = mapBuilder2.valuesArray;
                objArr.getClass();
                Object obj2 = objArr[i2];
                initNext$kotlin_stdlib();
                return obj2;
            }
            throw new NoSuchElementException();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ValuesItr(MapBuilder mapBuilder, int i) {
            super(mapBuilder);
            this.switching_field = i;
        }
    }

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.isReadOnly = true;
        Empty = mapBuilder;
    }

    public MapBuilder() {
        this(8);
    }

    private final void ensureExtraCapacity(int i) {
        Object[] objArr;
        int highestOneBit;
        int capacity$kotlin_stdlib = getCapacity$kotlin_stdlib();
        int i2 = this.length;
        int i3 = capacity$kotlin_stdlib - i2;
        int i4 = i2 - this.size;
        if (i3 < i && i3 + i4 >= i && i4 >= (getCapacity$kotlin_stdlib() >> 2)) {
            rehash(getHashSize());
            return;
        }
        int i5 = this.length + i;
        if (i5 >= 0) {
            if (i5 > getCapacity$kotlin_stdlib()) {
                int newCapacity$kotlin_stdlib$ar$ds = OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.newCapacity$kotlin_stdlib$ar$ds(getCapacity$kotlin_stdlib(), i5);
                this.keysArray = OnDeviceObjectCreateLogEvent.copyOfUninitializedElements(this.keysArray, newCapacity$kotlin_stdlib$ar$ds);
                Object[] objArr2 = this.valuesArray;
                if (objArr2 != null) {
                    objArr = OnDeviceObjectCreateLogEvent.copyOfUninitializedElements(objArr2, newCapacity$kotlin_stdlib$ar$ds);
                } else {
                    objArr = null;
                }
                this.valuesArray = objArr;
                int[] copyOf = Arrays.copyOf(this.presenceArray, newCapacity$kotlin_stdlib$ar$ds);
                copyOf.getClass();
                this.presenceArray = copyOf;
                highestOneBit = Integer.highestOneBit(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(newCapacity$kotlin_stdlib$ar$ds, 1) * 3);
                if (highestOneBit > getHashSize()) {
                    rehash(highestOneBit);
                    return;
                }
                return;
            }
            return;
        }
        throw new OutOfMemoryError();
    }

    private final int getHashSize() {
        return this.hashArray.length;
    }

    private final int hash(Object obj) {
        int i;
        if (obj != null) {
            i = obj.hashCode();
        } else {
            i = 0;
        }
        return (i * (-1640531527)) >>> this.hashShift;
    }

    private final void registerModification() {
        this.modCount++;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x006a, code lost:
    
        r3[r0] = r7;
        r6.presenceArray[r2] = r0;
        r2 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void rehash(int r7) {
        /*
            r6 = this;
            r6.registerModification()
            int r0 = r6.length
            int r1 = r6.size
            r2 = 0
            if (r0 <= r1) goto L37
            java.lang.Object[] r0 = r6.valuesArray
            r1 = r2
            r3 = r1
        Le:
            int r4 = r6.length
            if (r1 >= r4) goto L29
            int[] r4 = r6.presenceArray
            r4 = r4[r1]
            if (r4 < 0) goto L26
            java.lang.Object[] r4 = r6.keysArray
            r5 = r4[r1]
            r4[r3] = r5
            if (r0 == 0) goto L24
            r4 = r0[r1]
            r0[r3] = r4
        L24:
            int r3 = r3 + 1
        L26:
            int r1 = r1 + 1
            goto Le
        L29:
            java.lang.Object[] r1 = r6.keysArray
            com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent.resetRange(r1, r3, r4)
            if (r0 == 0) goto L35
            int r1 = r6.length
            com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent.resetRange(r0, r3, r1)
        L35:
            r6.length = r3
        L37:
            int r0 = r6.getHashSize()
            if (r7 == r0) goto L48
            int[] r0 = new int[r7]
            r6.hashArray = r0
            int r7 = com.google.mlkit.logging.schema.OnDeviceObjectInferenceLogEvent.computeShift$ar$ds(r7)
            r6.hashShift = r7
            goto L54
        L48:
            int[] r7 = r6.hashArray
            int r0 = r6.getHashSize()
            r7.getClass()
            java.util.Arrays.fill(r7, r2, r0, r2)
        L54:
            int r7 = r6.length
            if (r2 >= r7) goto L8b
            int r7 = r2 + 1
            java.lang.Object[] r0 = r6.keysArray
            r0 = r0[r2]
            int r0 = r6.hash(r0)
            int r1 = r6.maxProbeDistance
        L64:
            int[] r3 = r6.hashArray
            r4 = r3[r0]
            if (r4 != 0) goto L72
            r3[r0] = r7
            int[] r1 = r6.presenceArray
            r1[r2] = r0
            r2 = r7
            goto L54
        L72:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L83
            int r3 = r0 + (-1)
            if (r0 != 0) goto L81
            int r0 = r6.getHashSize()
            int r0 = r0 + (-1)
            goto L64
        L81:
            r0 = r3
            goto L64
        L83:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r7.<init>(r0)
            throw r7
        L8b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.rehash(int):void");
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int hash = hash(obj);
            int i = this.maxProbeDistance;
            int i2 = i + i;
            int hashSize = getHashSize() >> 1;
            int i3 = 0;
            while (true) {
                int i4 = this.hashArray[hash];
                if (i4 <= 0) {
                    if (this.length >= getCapacity$kotlin_stdlib()) {
                        ensureExtraCapacity(1);
                    } else {
                        int i5 = this.length;
                        int i6 = i5 + 1;
                        this.length = i6;
                        this.keysArray[i5] = obj;
                        this.presenceArray[i5] = hash;
                        this.hashArray[hash] = i6;
                        this.size++;
                        registerModification();
                        if (i3 > this.maxProbeDistance) {
                            this.maxProbeDistance = i3;
                        }
                        return i5;
                    }
                } else {
                    if (Intrinsics.areEqual(this.keysArray[i4 - 1], obj)) {
                        return -i4;
                    }
                    i3++;
                    if (i3 > OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(i2, hashSize)) {
                        int hashSize2 = getHashSize();
                        rehash(hashSize2 + hashSize2);
                        break;
                    }
                    int i7 = hash - 1;
                    if (hash == 0) {
                        hash = getHashSize() - 1;
                    } else {
                        hash = i7;
                    }
                }
            }
        }
    }

    public final Object[] allocateValuesArray() {
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            return objArr;
        }
        Object[] objArr2 = new Object[getCapacity$kotlin_stdlib()];
        this.valuesArray = objArr2;
        return objArr2;
    }

    public final Map build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if (this.size > 0) {
            return this;
        }
        MapBuilder mapBuilder = Empty;
        mapBuilder.getClass();
        return mapBuilder;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (!this.isReadOnly) {
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final void clear() {
        checkIsMutable$kotlin_stdlib();
        IntIterator it = new IntRange(0, this.length - 1).iterator();
        while (it.hasNext()) {
            int nextInt = it.nextInt();
            int[] iArr = this.presenceArray;
            int i = iArr[nextInt];
            if (i >= 0) {
                this.hashArray[i] = 0;
                iArr[nextInt] = -1;
            }
        }
        OnDeviceObjectCreateLogEvent.resetRange(this.keysArray, 0, this.length);
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            OnDeviceObjectCreateLogEvent.resetRange(objArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
        registerModification();
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection collection) {
        collection.getClass();
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry entry) {
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        objArr.getClass();
        return Intrinsics.areEqual(objArr[findKey], entry.getValue());
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        if (findKey(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        if (findValue(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set entrySet() {
        MapBuilderEntries mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries == null) {
            MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
            this.entriesView = mapBuilderEntries2;
            return mapBuilderEntries2;
        }
        return mapBuilderEntries;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.size == map.size() && containsAllEntries$kotlin_stdlib(map.entrySet())) {
            return true;
        }
        return false;
    }

    public final int findKey(Object obj) {
        int hash = hash(obj);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[hash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(this.keysArray[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            int i4 = hash - 1;
            if (hash == 0) {
                hash = getHashSize() - 1;
            } else {
                hash = i4;
            }
        }
    }

    public final int findValue(Object obj) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                Object[] objArr = this.valuesArray;
                objArr.getClass();
                if (Intrinsics.areEqual(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        objArr.getClass();
        return objArr[findKey];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    @Override // java.util.Map
    public final int hashCode() {
        int i;
        int i2;
        EntriesItr entriesItr = new EntriesItr(this);
        int i3 = 0;
        while (entriesItr.hasNext()) {
            int i4 = entriesItr.index;
            MapBuilder mapBuilder = entriesItr.map;
            if (i4 < mapBuilder.length) {
                entriesItr.index = i4 + 1;
                entriesItr.lastIndex = i4;
                Object obj = mapBuilder.keysArray[i4];
                if (obj != null) {
                    i = obj.hashCode();
                } else {
                    i = 0;
                }
                Object[] objArr = entriesItr.map.valuesArray;
                objArr.getClass();
                Object obj2 = objArr[entriesItr.lastIndex];
                if (obj2 != null) {
                    i2 = obj2.hashCode();
                } else {
                    i2 = 0;
                }
                entriesItr.initNext$kotlin_stdlib();
                i3 += i ^ i2;
            } else {
                throw new NoSuchElementException();
            }
        }
        return i3;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ Set keySet() {
        MapBuilderKeys mapBuilderKeys = this.keysView;
        if (mapBuilderKeys == null) {
            MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
            this.keysView = mapBuilderKeys2;
            return mapBuilderKeys2;
        }
        return mapBuilderKeys;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        checkIsMutable$kotlin_stdlib();
        int addKey$kotlin_stdlib = addKey$kotlin_stdlib(obj);
        Object[] allocateValuesArray = allocateValuesArray();
        if (addKey$kotlin_stdlib < 0) {
            int i = (-addKey$kotlin_stdlib) - 1;
            Object obj3 = allocateValuesArray[i];
            allocateValuesArray[i] = obj2;
            return obj3;
        }
        allocateValuesArray[addKey$kotlin_stdlib] = obj2;
        return null;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        map.getClass();
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry> entrySet = map.entrySet();
        if (!entrySet.isEmpty()) {
            ensureExtraCapacity(entrySet.size());
            for (Map.Entry entry : entrySet) {
                int addKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
                Object[] allocateValuesArray = allocateValuesArray();
                if (addKey$kotlin_stdlib >= 0) {
                    allocateValuesArray[addKey$kotlin_stdlib] = entry.getValue();
                } else {
                    int i = (-addKey$kotlin_stdlib) - 1;
                    if (!Intrinsics.areEqual(entry.getValue(), allocateValuesArray[i])) {
                        allocateValuesArray[i] = entry.getValue();
                    }
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        int removeKey$kotlin_stdlib = removeKey$kotlin_stdlib(obj);
        if (removeKey$kotlin_stdlib < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        objArr.getClass();
        Object obj2 = objArr[removeKey$kotlin_stdlib];
        OnDeviceObjectCreateLogEvent.resetAt(objArr, removeKey$kotlin_stdlib);
        return obj2;
    }

    public final int removeKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(obj);
        if (findKey < 0) {
            return -1;
        }
        removeKeyAt(findKey);
        return findKey;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[LOOP:0: B:2:0x001a->B:19:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void removeKeyAt(int r11) {
        /*
            r10 = this;
            java.lang.Object[] r0 = r10.keysArray
            com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent.resetAt(r0, r11)
            int[] r0 = r10.presenceArray
            r0 = r0[r11]
            int r1 = r10.maxProbeDistance
            int r1 = r1 + r1
            int r2 = r10.getHashSize()
            int r2 = r2 >> 1
            int r1 = com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(r1, r2)
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L1a:
            r5 = -1
            if (r0 != 0) goto L23
            int r0 = r10.getHashSize()
            int r0 = r0 + r5
            goto L25
        L23:
            int r0 = r0 + (-1)
        L25:
            int r4 = r4 + 1
            int r6 = r10.maxProbeDistance
            if (r4 <= r6) goto L30
            int[] r0 = r10.hashArray
            r0[r1] = r2
            goto L63
        L30:
            int[] r6 = r10.hashArray
            r7 = r6[r0]
            if (r7 != 0) goto L39
            r6[r1] = r2
            goto L63
        L39:
            if (r7 >= 0) goto L40
            r6[r1] = r5
        L3d:
            r1 = r0
            r4 = r2
            goto L5c
        L40:
            java.lang.Object[] r6 = r10.keysArray
            int r8 = r7 + (-1)
            r6 = r6[r8]
            int r6 = r10.hash(r6)
            int r6 = r6 - r0
            int r9 = r10.getHashSize()
            int r9 = r9 + r5
            r6 = r6 & r9
            if (r6 < r4) goto L5c
            int[] r4 = r10.hashArray
            r4[r1] = r7
            int[] r4 = r10.presenceArray
            r4[r8] = r1
            goto L3d
        L5c:
            int r3 = r3 + r5
            if (r3 >= 0) goto L1a
            int[] r0 = r10.hashArray
            r0[r1] = r5
        L63:
            int[] r0 = r10.presenceArray
            r0[r11] = r5
            int r11 = r10.size
            int r11 = r11 + r5
            r10.size = r11
            r10.registerModification()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.removeKeyAt(int):void");
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.size * 3) + 2);
        sb.append("{");
        EntriesItr entriesItr = new EntriesItr(this);
        int i = 0;
        while (entriesItr.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = entriesItr.index;
            MapBuilder mapBuilder = entriesItr.map;
            if (i2 < mapBuilder.length) {
                entriesItr.index = i2 + 1;
                entriesItr.lastIndex = i2;
                Object obj = mapBuilder.keysArray[i2];
                if (obj == mapBuilder) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj);
                }
                sb.append('=');
                MapBuilder mapBuilder2 = entriesItr.map;
                Object[] objArr = mapBuilder2.valuesArray;
                objArr.getClass();
                Object obj2 = objArr[entriesItr.lastIndex];
                if (obj2 == mapBuilder2) {
                    sb.append("(this Map)");
                } else {
                    sb.append(obj2);
                }
                entriesItr.initNext$kotlin_stdlib();
                i++;
            } else {
                throw new NoSuchElementException();
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // java.util.Map
    public final /* bridge */ Collection values() {
        AbstractMutableCollection abstractMutableCollection = this.valuesView$ar$class_merging;
        if (abstractMutableCollection == null) {
            AbstractMutableCollection abstractMutableCollection2 = new AbstractMutableCollection(this);
            this.valuesView$ar$class_merging = abstractMutableCollection2;
            return abstractMutableCollection2;
        }
        return abstractMutableCollection;
    }

    public MapBuilder(int i) {
        int highestOneBit;
        Object[] objArr = new Object[i];
        int[] iArr = new int[i];
        highestOneBit = Integer.highestOneBit(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(i, 1) * 3);
        this.keysArray = objArr;
        this.valuesArray = null;
        this.presenceArray = iArr;
        this.hashArray = new int[highestOneBit];
        this.maxProbeDistance = 2;
        this.length = 0;
        this.hashShift = OnDeviceObjectInferenceLogEvent.computeShift$ar$ds(getHashSize());
    }
}
