package com.google.common.flogger.context;

import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tags {
    public final LightweightTagMap map;
    public static final Comparator VALUE_COMPARATOR = new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(13);
    public static final Tags EMPTY_TAGS = new Tags(new LightweightTagMap(Collections.emptyList()));

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Type {
        BOOLEAN,
        STRING,
        LONG,
        DOUBLE;

        public static Type of(Object obj) {
            if (obj instanceof String) {
                return STRING;
            }
            if (obj instanceof Boolean) {
                return BOOLEAN;
            }
            if (obj instanceof Long) {
                return LONG;
            }
            if (obj instanceof Double) {
                return DOUBLE;
            }
            throw new AssertionError("invalid tag type: ".concat(String.valueOf(String.valueOf(obj.getClass()))));
        }
    }

    public Tags(LightweightTagMap lightweightTagMap) {
        this.map = lightweightTagMap;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof Tags) && ((Tags) obj).map.equals(this.map)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ~this.map.hashCode();
    }

    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    public final String toString() {
        return this.map.toString();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LightweightTagMap extends AbstractMap {
        public static final Comparator ENTRY_COMPARATOR = new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(14);
        public final Object[] array;
        public final int[] offsets;
        public final Set entrySet = new SortedArraySet(-1);
        private Integer hashCode = null;
        private String toString = null;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SortedArraySet extends AbstractSet {
            final int index;

            /* compiled from: PG */
            /* renamed from: com.google.common.flogger.context.Tags$LightweightTagMap$SortedArraySet$1, reason: invalid class name */
            /* loaded from: classes.dex */
            public final class AnonymousClass1 implements Iterator {
                private int n = 0;

                public AnonymousClass1() {
                }

                @Override // java.util.Iterator
                public final boolean hasNext() {
                    if (this.n < SortedArraySet.this.size()) {
                        return true;
                    }
                    return false;
                }

                @Override // java.util.Iterator
                public final Object next() {
                    int i = this.n;
                    if (i < SortedArraySet.this.size()) {
                        SortedArraySet sortedArraySet = SortedArraySet.this;
                        Object obj = LightweightTagMap.this.array[sortedArraySet.getStart() + i];
                        this.n = i + 1;
                        return obj;
                    }
                    throw new NoSuchElementException();
                }

                @Override // java.util.Iterator
                public final void remove() {
                    throw new UnsupportedOperationException();
                }
            }

            public SortedArraySet(int i) {
                this.index = i;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final boolean contains(Object obj) {
                Comparator comparator;
                int i = this.index;
                int start = getStart();
                int end = getEnd();
                if (i == -1) {
                    comparator = LightweightTagMap.ENTRY_COMPARATOR;
                } else {
                    comparator = Tags.VALUE_COMPARATOR;
                }
                if (Arrays.binarySearch(LightweightTagMap.this.array, start, end, obj, comparator) >= 0) {
                    return true;
                }
                return false;
            }

            final int getEnd() {
                return LightweightTagMap.this.offsets[this.index + 1];
            }

            final int getStart() {
                int i = this.index;
                if (i == -1) {
                    return 0;
                }
                return LightweightTagMap.this.offsets[i];
            }

            final Object getValue(int i) {
                return LightweightTagMap.this.array[getStart() + i];
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public final Iterator iterator() {
                return new AnonymousClass1();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public final int size() {
                return getEnd() - getStart();
            }
        }

        public LightweightTagMap(LightweightTagMap lightweightTagMap, LightweightTagMap lightweightTagMap2) {
            int i;
            Object obj;
            Object[] objArr;
            int i2 = -1;
            int size = lightweightTagMap.size() + lightweightTagMap2.size();
            int totalElementCount = lightweightTagMap.getTotalElementCount() + lightweightTagMap2.getTotalElementCount();
            int i3 = size + 1;
            Object[] objArr2 = new Object[totalElementCount];
            int[] iArr = new int[i3];
            int i4 = 0;
            iArr[0] = size;
            int i5 = size;
            Map.Entry entryOrNull = lightweightTagMap.getEntryOrNull(0);
            Map.Entry entryOrNull2 = lightweightTagMap2.getEntryOrNull(0);
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (true) {
                if (entryOrNull == null && entryOrNull2 == null) {
                    break;
                }
                int i9 = i6 + 1;
                int i10 = entryOrNull == null ? 1 : entryOrNull2 == null ? i2 : i4;
                if (i10 == 0 && (i10 = ((String) entryOrNull.getKey()).compareTo((String) entryOrNull2.getKey())) == 0) {
                    int i11 = i8 + 1;
                    int i12 = i7 + 1;
                    objArr2[i6] = newEntry((String) entryOrNull.getKey(), i6);
                    SortedArraySet sortedArraySet = (SortedArraySet) entryOrNull.getValue();
                    SortedArraySet sortedArraySet2 = (SortedArraySet) entryOrNull2.getValue();
                    int i13 = i4;
                    int i14 = i13;
                    while (true) {
                        if (i14 >= sortedArraySet.size() && i13 >= sortedArraySet2.size()) {
                            break;
                        }
                        int i15 = i14 == sortedArraySet.size() ? 1 : i13 == sortedArraySet2.size() ? -1 : i4;
                        if (i15 == 0) {
                            i = i11;
                            i15 = Tags.VALUE_COMPARATOR.compare(sortedArraySet.getValue(i14), sortedArraySet2.getValue(i13));
                        } else {
                            i = i11;
                        }
                        if (i15 < 0) {
                            obj = sortedArraySet.getValue(i14);
                            i14++;
                        } else {
                            int i16 = i13 + 1;
                            Object value = sortedArraySet2.getValue(i13);
                            i14 = i15 == 0 ? i14 + 1 : i14;
                            obj = value;
                            i13 = i16;
                        }
                        objArr2[i5] = obj;
                        i5++;
                        i11 = i;
                        i4 = 0;
                    }
                    iArr[i9] = i5;
                    entryOrNull = lightweightTagMap.getEntryOrNull(i12);
                    entryOrNull2 = lightweightTagMap2.getEntryOrNull(i11);
                    i8 = i11;
                    i7 = i12;
                    i6 = i9;
                    i2 = -1;
                } else {
                    if (i10 < 0) {
                        int i17 = i7 + 1;
                        int copyEntryAndValues = copyEntryAndValues(entryOrNull, i6, i5, objArr2, iArr);
                        entryOrNull = lightweightTagMap.getEntryOrNull(i17);
                        i5 = copyEntryAndValues;
                        i7 = i17;
                    } else {
                        int i18 = i8 + 1;
                        int copyEntryAndValues2 = copyEntryAndValues(entryOrNull2, i6, i5, objArr2, iArr);
                        entryOrNull2 = lightweightTagMap2.getEntryOrNull(i18);
                        i5 = copyEntryAndValues2;
                        i8 = i18;
                    }
                    i6 = i9;
                    i2 = -1;
                    i4 = 0;
                }
            }
            int i19 = iArr[i4];
            int i20 = i19 - i6;
            if (i20 != 0) {
                for (int i21 = i4; i21 <= i6; i21++) {
                    iArr[i21] = iArr[i21] - i20;
                }
                int i22 = iArr[i6];
                int i23 = i22 - i6;
                if (mustResize(totalElementCount, i22)) {
                    objArr = new Object[i22];
                    System.arraycopy(objArr2, i4, objArr, i4, i6);
                } else {
                    objArr = objArr2;
                }
                System.arraycopy(objArr2, i19, objArr, i6, i23);
                objArr2 = objArr;
            }
            this.array = objArr2;
            int i24 = iArr[i4] + 1;
            this.offsets = mustResize(i3, i24) ? Arrays.copyOf(iArr, i24) : iArr;
        }

        private final int copyEntryAndValues(Map.Entry entry, int i, int i2, Object[] objArr, int[] iArr) {
            SortedArraySet sortedArraySet = (SortedArraySet) entry.getValue();
            int end = sortedArraySet.getEnd() - sortedArraySet.getStart();
            System.arraycopy(LightweightTagMap.this.array, sortedArraySet.getStart(), objArr, i2, end);
            objArr[i] = newEntry((String) entry.getKey(), i);
            int i3 = i2 + end;
            iArr[i + 1] = i3;
            return i3;
        }

        private final Map.Entry getEntryOrNull(int i) {
            if (i < this.offsets[0]) {
                return (Map.Entry) this.array[i];
            }
            return null;
        }

        private final int getTotalElementCount() {
            return this.offsets[size()];
        }

        private static boolean mustResize(int i, int i2) {
            if (i > 16 && i * 9 > i2 * 10) {
                return true;
            }
            return false;
        }

        private final Map.Entry newEntry(String str, int i) {
            return new AbstractMap.SimpleImmutableEntry(str, new SortedArraySet(i));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Set entrySet() {
            return this.entrySet;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final int hashCode() {
            if (this.hashCode == null) {
                this.hashCode = Integer.valueOf(super.hashCode());
            }
            return this.hashCode.intValue();
        }

        @Override // java.util.AbstractMap
        public final String toString() {
            if (this.toString == null) {
                this.toString = super.toString();
            }
            return this.toString;
        }

        public LightweightTagMap(List list) {
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                int size = list.size();
                Object[] objArr = new Object[size];
                Iterator it2 = list.iterator();
                if (!it2.hasNext()) {
                    int[] iArr = {0};
                    this.array = mustResize(size, 0) ? Arrays.copyOf(objArr, 0) : objArr;
                    this.offsets = iArr;
                    return;
                }
                SplitInstallSharedPreferences.m213$$Nest$fgetkey$ar$ds$ar$class_merging$ar$class_merging((SplitInstallSharedPreferences) it2.next());
                throw null;
            }
            SplitInstallSharedPreferences.m213$$Nest$fgetkey$ar$ds$ar$class_merging$ar$class_merging((SplitInstallSharedPreferences) it.next());
            throw null;
        }
    }
}
