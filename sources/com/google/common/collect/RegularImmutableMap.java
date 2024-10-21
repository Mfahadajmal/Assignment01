package com.google.common.collect;

import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RegularImmutableMap extends ImmutableMap {
    public static final ImmutableMap EMPTY = new RegularImmutableMap(null, new Object[0], 0);
    private static final long serialVersionUID = 0;
    final transient Object[] alternatingKeysAndValues;
    private final transient Object hashTable;
    public final transient int size;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EntrySet extends ImmutableSet {
        public final transient Object[] alternatingKeysAndValues;
        private final transient ImmutableMap map;
        public final transient int size;

        public EntrySet(ImmutableMap immutableMap, Object[] objArr, int i) {
            this.map = immutableMap;
            this.alternatingKeysAndValues = objArr;
            this.size = i;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Object value = entry.getValue();
                if (value != null && value.equals(this.map.get(key))) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int copyIntoArray(Object[] objArr, int i) {
            return asList().copyIntoArray(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableSet
        public final ImmutableList createAsList() {
            return new ImmutableList() { // from class: com.google.common.collect.RegularImmutableMap.EntrySet.1
                @Override // java.util.List
                public final /* bridge */ /* synthetic */ Object get(int i) {
                    ContextDataProvider.checkElementIndex$ar$ds(i, EntrySet.this.size);
                    Object[] objArr = EntrySet.this.alternatingKeysAndValues;
                    int i2 = i + i;
                    Object obj = objArr[i2];
                    obj.getClass();
                    Object obj2 = objArr[i2 + 1];
                    obj2.getClass();
                    return new AbstractMap.SimpleImmutableEntry(obj, obj2);
                }

                @Override // com.google.common.collect.ImmutableCollection
                public final boolean isPartialView() {
                    return true;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                public final int size() {
                    return EntrySet.this.size;
                }

                @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
                public Object writeReplace() {
                    return super.writeReplace();
                }
            };
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        /* renamed from: iterator */
        public final UnmodifiableIterator listIterator() {
            return asList().iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeySet extends ImmutableSet {
        private final transient ImmutableList list;
        private final transient ImmutableMap map;

        public KeySet(ImmutableMap immutableMap, ImmutableList immutableList) {
            this.map = immutableMap;
            this.list = immutableList;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public final ImmutableList asList() {
            return this.list;
        }

        @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (this.map.get(obj) != null) {
                return true;
            }
            return false;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int copyIntoArray(Object[] objArr, int i) {
            return this.list.copyIntoArray(objArr, i);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return true;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        /* renamed from: iterator */
        public final UnmodifiableIterator listIterator() {
            return this.list.iterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return ((RegularImmutableMap) this.map).size;
        }

        @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeysOrValuesAsList extends ImmutableList {
        private final transient Object[] alternatingKeysAndValues;
        private final transient int offset;
        private final transient int size;

        public KeysOrValuesAsList(Object[] objArr, int i, int i2) {
            this.alternatingKeysAndValues = objArr;
            this.offset = i;
            this.size = i2;
        }

        @Override // java.util.List
        public final Object get(int i) {
            ContextDataProvider.checkElementIndex$ar$ds(i, this.size);
            Object obj = this.alternatingKeysAndValues[i + i + this.offset];
            obj.getClass();
            return obj;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.size;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }
    }

    private RegularImmutableMap(Object obj, Object[] objArr, int i) {
        this.hashTable = obj;
        this.alternatingKeysAndValues = objArr;
        this.size = i;
    }

    public static RegularImmutableMap create(int i, Object[] objArr) {
        return create(i, objArr, null);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet createEntrySet() {
        return new EntrySet(this, this.alternatingKeysAndValues, this.size);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableSet createKeySet() {
        return new KeySet(this, new KeysOrValuesAsList(this.alternatingKeysAndValues, 0, this.size));
    }

    @Override // com.google.common.collect.ImmutableMap
    public final ImmutableCollection createValues() {
        return new KeysOrValuesAsList(this.alternatingKeysAndValues, 1, this.size);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x009e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x009f A[RETURN] */
    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object get(java.lang.Object r10) {
        /*
            r9 = this;
            r0 = 0
            if (r10 != 0) goto L6
        L3:
            r10 = r0
            goto L9c
        L6:
            int r1 = r9.size
            java.lang.Object[] r2 = r9.alternatingKeysAndValues
            r3 = 1
            if (r1 != r3) goto L20
            r1 = 0
            r1 = r2[r1]
            r1.getClass()
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L3
            r10 = r2[r3]
            r10.getClass()
            goto L9c
        L20:
            java.lang.Object r1 = r9.hashTable
            if (r1 != 0) goto L25
            goto L3
        L25:
            boolean r4 = r1 instanceof byte[]
            r5 = -1
            if (r4 == 0) goto L51
            r4 = r1
            byte[] r4 = (byte[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.common.flogger.context.ContextDataProvider.smear(r1)
        L38:
            r1 = r1 & r6
            r5 = r4[r1]
            r7 = 255(0xff, float:3.57E-43)
            r5 = r5 & r7
            if (r5 != r7) goto L41
            goto L3
        L41:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L4e
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9c
        L4e:
            int r1 = r1 + 1
            goto L38
        L51:
            boolean r4 = r1 instanceof short[]
            if (r4 == 0) goto L7d
            r4 = r1
            short[] r4 = (short[]) r4
            int r1 = r4.length
            int r6 = r1 + (-1)
            int r1 = r10.hashCode()
            int r1 = com.google.common.flogger.context.ContextDataProvider.smear(r1)
        L63:
            r1 = r1 & r6
            short r5 = r4[r1]
            char r5 = (char) r5
            r7 = 65535(0xffff, float:9.1834E-41)
            if (r5 != r7) goto L6d
            goto L3
        L6d:
            r7 = r2[r5]
            boolean r7 = r10.equals(r7)
            if (r7 == 0) goto L7a
            r10 = r5 ^ 1
            r10 = r2[r10]
            goto L9c
        L7a:
            int r1 = r1 + 1
            goto L63
        L7d:
            int[] r1 = (int[]) r1
            int r4 = r1.length
            int r4 = r4 + r5
            int r6 = r10.hashCode()
            int r6 = com.google.common.flogger.context.ContextDataProvider.smear(r6)
        L89:
            r6 = r6 & r4
            r7 = r1[r6]
            if (r7 != r5) goto L90
            goto L3
        L90:
            r8 = r2[r7]
            boolean r8 = r10.equals(r8)
            if (r8 == 0) goto La0
            r10 = r7 ^ 1
            r10 = r2[r10]
        L9c:
            if (r10 != 0) goto L9f
            return r0
        L9f:
            return r10
        La0:
            int r6 = r6 + 1
            goto L89
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.get(java.lang.Object):java.lang.Object");
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableMap
    public Object writeReplace() {
        return super.writeReplace();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static RegularImmutableMap create(int i, Object[] objArr, ImmutableMap.Builder builder) {
        char c;
        short[] sArr;
        short[] sArr2;
        int i2 = i;
        Object[] objArr2 = objArr;
        if (i2 == 0) {
            return (RegularImmutableMap) EMPTY;
        }
        Object obj = null;
        int i3 = 1;
        if (i2 == 1) {
            Object obj2 = objArr2[0];
            obj2.getClass();
            Object obj3 = objArr2[1];
            obj3.getClass();
            ContextDataProvider.checkEntryNotNull(obj2, obj3);
            return new RegularImmutableMap(null, objArr2, 1);
        }
        ContextDataProvider.checkPositionIndex$ar$ds(i2, objArr2.length >> 1);
        int chooseTableSize = ImmutableSet.chooseTableSize(i);
        if (i2 == 1) {
            Object obj4 = objArr2[0];
            obj4.getClass();
            Object obj5 = objArr2[1];
            obj5.getClass();
            ContextDataProvider.checkEntryNotNull(obj4, obj5);
            c = 2;
        } else {
            int i4 = chooseTableSize - 1;
            int i5 = -1;
            if (chooseTableSize <= 128) {
                byte[] bArr = new byte[chooseTableSize];
                Arrays.fill(bArr, (byte) -1);
                OptionalMethod optionalMethod = null;
                int i6 = 0;
                int i7 = 0;
                while (i6 < i2) {
                    int i8 = i7 + i7;
                    int i9 = i6 + i6;
                    Object obj6 = objArr2[i9];
                    obj6.getClass();
                    Object obj7 = objArr2[i9 ^ i3];
                    obj7.getClass();
                    ContextDataProvider.checkEntryNotNull(obj6, obj7);
                    int smear = ContextDataProvider.smear(obj6.hashCode());
                    while (true) {
                        int i10 = smear & i4;
                        int i11 = bArr[i10] & 255;
                        if (i11 == 255) {
                            bArr[i10] = (byte) i8;
                            if (i7 < i6) {
                                objArr2[i8] = obj6;
                                objArr2[i8 ^ 1] = obj7;
                            }
                            i7++;
                        } else {
                            if (obj6.equals(objArr2[i11])) {
                                int i12 = i11 ^ 1;
                                Object obj8 = objArr2[i12];
                                obj8.getClass();
                                optionalMethod = new OptionalMethod(obj6, obj7, obj8, (byte[]) null);
                                objArr2[i12] = obj7;
                                break;
                            }
                            smear = i10 + 1;
                        }
                    }
                    i6++;
                    i3 = 1;
                }
                if (i7 == i2) {
                    sArr = bArr;
                } else {
                    c = 2;
                    sArr2 = new Object[]{bArr, Integer.valueOf(i7), optionalMethod};
                    i3 = 1;
                    obj = sArr2;
                }
            } else {
                if (chooseTableSize <= 32768) {
                    short[] sArr3 = new short[chooseTableSize];
                    Arrays.fill(sArr3, (short) -1);
                    OptionalMethod optionalMethod2 = null;
                    int i13 = 0;
                    for (int i14 = 0; i14 < i2; i14++) {
                        int i15 = i13 + i13;
                        int i16 = i14 + i14;
                        Object obj9 = objArr2[i16];
                        obj9.getClass();
                        Object obj10 = objArr2[i16 ^ 1];
                        obj10.getClass();
                        ContextDataProvider.checkEntryNotNull(obj9, obj10);
                        int smear2 = ContextDataProvider.smear(obj9.hashCode());
                        while (true) {
                            int i17 = smear2 & i4;
                            char c2 = (char) sArr3[i17];
                            if (c2 == 65535) {
                                sArr3[i17] = (short) i15;
                                if (i13 < i14) {
                                    objArr2[i15] = obj9;
                                    objArr2[i15 ^ 1] = obj10;
                                }
                                i13++;
                            } else {
                                if (obj9.equals(objArr2[c2])) {
                                    int i18 = c2 ^ 1;
                                    Object obj11 = objArr2[i18];
                                    obj11.getClass();
                                    optionalMethod2 = new OptionalMethod(obj9, obj10, obj11, (byte[]) null);
                                    objArr2[i18] = obj10;
                                    break;
                                }
                                smear2 = i17 + 1;
                            }
                        }
                    }
                    if (i13 == i2) {
                        sArr = sArr3;
                    } else {
                        i3 = 1;
                        c = 2;
                        obj = new Object[]{sArr3, Integer.valueOf(i13), optionalMethod2};
                    }
                } else {
                    int i19 = 1;
                    int[] iArr = new int[chooseTableSize];
                    Arrays.fill(iArr, -1);
                    Object obj12 = null;
                    int i20 = 0;
                    int i21 = 0;
                    while (i21 < i2) {
                        int i22 = i20 + i20;
                        int i23 = i21 + i21;
                        Object obj13 = objArr2[i23];
                        obj13.getClass();
                        Object obj14 = objArr2[i23 ^ i19];
                        obj14.getClass();
                        ContextDataProvider.checkEntryNotNull(obj13, obj14);
                        int smear3 = ContextDataProvider.smear(obj13.hashCode());
                        while (true) {
                            int i24 = smear3 & i4;
                            byte b = iArr[i24];
                            if (b == i5) {
                                iArr[i24] = i22;
                                if (i20 < i21) {
                                    objArr2[i22] = obj13;
                                    objArr2[i22 ^ 1] = obj14;
                                }
                                i20++;
                            } else {
                                if (obj13.equals(objArr2[b])) {
                                    int i25 = b ^ 1;
                                    Object obj15 = objArr2[i25];
                                    obj15.getClass();
                                    Object optionalMethod3 = new OptionalMethod(obj13, obj14, obj15, (byte[]) null);
                                    objArr2[i25] = obj14;
                                    obj12 = optionalMethod3;
                                    break;
                                }
                                smear3 = i24 + 1;
                                i5 = -1;
                            }
                        }
                        i21++;
                        i19 = 1;
                        i5 = -1;
                    }
                    if (i20 == i2) {
                        sArr = iArr;
                    } else {
                        i3 = 1;
                        c = 2;
                        obj = new Object[]{iArr, Integer.valueOf(i20), obj12};
                    }
                }
                i3 = 1;
                obj = sArr2;
            }
            c = 2;
            sArr2 = sArr;
            i3 = 1;
            obj = sArr2;
        }
        boolean z = obj instanceof Object[];
        Object obj16 = obj;
        if (z) {
            Object[] objArr3 = (Object[]) obj;
            OptionalMethod optionalMethod4 = (OptionalMethod) objArr3[c];
            if (builder != null) {
                builder.ImmutableMap$Builder$ar$duplicateKey = optionalMethod4;
                Object obj17 = objArr3[0];
                int intValue = ((Integer) objArr3[i3]).intValue();
                objArr2 = Arrays.copyOf(objArr2, intValue + intValue);
                obj16 = obj17;
                i2 = intValue;
            } else {
                throw optionalMethod4.exception();
            }
        }
        return new RegularImmutableMap(obj16, objArr2, i2);
    }

    @Override // com.google.common.collect.ImmutableMap
    public final void isPartialView$ar$ds() {
    }
}
