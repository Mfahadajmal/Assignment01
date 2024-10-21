package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.LazyField;
import com.google.protobuf.MessageLite;
import com.google.protobuf.WireFormat;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FieldSet {
    public static final FieldSet DEFAULT_INSTANCE = new FieldSet(null);
    final SmallSortedMap fields = new SmallSortedMap() { // from class: com.google.protobuf.SmallSortedMap.1
    };
    private boolean hasLazyField;
    public boolean isImmutable;

    private FieldSet() {
    }

    private static Object cloneIfMutable(Object obj) {
        if (obj instanceof MutableMessageLite) {
            return ((MutableMessageLite) obj).clone();
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            return bArr2;
        }
        return obj;
    }

    public static int computeElementSize(WireFormat.FieldType fieldType, int i, Object obj) {
        int computeTagSize = CodedOutputStream.computeTagSize(i);
        if (fieldType == WireFormat.FieldType.GROUP) {
            Internal.isProto1Group((MessageLite) obj);
            computeTagSize += computeTagSize;
        }
        return computeTagSize + computeElementSizeNoTag(fieldType, obj);
    }

    static int computeElementSizeNoTag(WireFormat.FieldType fieldType, Object obj) {
        WireFormat.FieldType fieldType2 = WireFormat.FieldType.DOUBLE;
        WireFormat.JavaType javaType = WireFormat.JavaType.INT;
        switch (fieldType) {
            case DOUBLE:
                ((Double) obj).doubleValue();
                boolean z = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 8;
            case FLOAT:
                ((Float) obj).floatValue();
                boolean z2 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 4;
            case INT64:
                return CodedOutputStream.computeUInt64SizeNoTag(((Long) obj).longValue());
            case UINT64:
                return CodedOutputStream.computeUInt64SizeNoTag(((Long) obj).longValue());
            case INT32:
                return CodedOutputStream.computeInt32SizeNoTag(((Integer) obj).intValue());
            case FIXED64:
                ((Long) obj).longValue();
                boolean z3 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 8;
            case FIXED32:
                ((Integer) obj).intValue();
                boolean z4 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 4;
            case BOOL:
                ((Boolean) obj).booleanValue();
                boolean z5 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 1;
            case STRING:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                }
                return CodedOutputStream.computeStringSizeNoTag((String) obj);
            case GROUP:
                boolean z6 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return ((MessageLite) obj).getSerializedSize();
            case MESSAGE:
                if (obj instanceof LazyField) {
                    return CodedOutputStream.computeLazyFieldSizeNoTag((LazyField) obj);
                }
                return CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj);
            case BYTES:
                if (obj instanceof ByteString) {
                    return CodedOutputStream.computeBytesSizeNoTag((ByteString) obj);
                }
                boolean z7 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return CodedOutputStream.computeLengthDelimitedFieldSize(((byte[]) obj).length);
            case UINT32:
                return CodedOutputStream.computeUInt32SizeNoTag(((Integer) obj).intValue());
            case ENUM:
                if (obj instanceof Internal.EnumLite) {
                    return CodedOutputStream.computeInt32SizeNoTag(((Internal.EnumLite) obj).getNumber());
                }
                return CodedOutputStream.computeInt32SizeNoTag(((Integer) obj).intValue());
            case SFIXED32:
                ((Integer) obj).intValue();
                boolean z8 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 4;
            case SFIXED64:
                ((Long) obj).longValue();
                boolean z9 = CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS;
                return 8;
            case SINT32:
                return CodedOutputStream.computeSInt32SizeNoTag(((Integer) obj).intValue());
            case SINT64:
                return CodedOutputStream.computeSInt64SizeNoTag(((Long) obj).longValue());
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int computeFieldSize$ar$class_merging(GeneratedMessageLite.ExtensionDescriptor extensionDescriptor, Object obj) {
        WireFormat.FieldType liteType = extensionDescriptor.getLiteType();
        int number = extensionDescriptor.getNumber();
        if (extensionDescriptor.isRepeated()) {
            List list = (List) obj;
            int i = 0;
            if (extensionDescriptor.isPacked()) {
                if (list.isEmpty()) {
                    return 0;
                }
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    i += computeElementSizeNoTag(liteType, it.next());
                }
                return CodedOutputStream.computeTagSize(number) + i + CodedOutputStream.computeUInt32SizeNoTag(i);
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                i += computeElementSize(liteType, number, it2.next());
            }
            return i;
        }
        return computeElementSize(liteType, number, obj);
    }

    private static boolean isMessageFieldValueInitialized(Object obj) {
        if (obj instanceof MessageLiteOrBuilder) {
            return ((MessageLiteOrBuilder) obj).isInitialized();
        }
        if (obj instanceof LazyField) {
            return true;
        }
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0011. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void verifyType$ar$class_merging$ar$ds(com.google.protobuf.GeneratedMessageLite.ExtensionDescriptor r4, java.lang.Object r5) {
        /*
            com.google.protobuf.WireFormat$FieldType r0 = r4.getLiteType()
            com.google.protobuf.Internal.checkNotNull$ar$ds$ca384cd1_2(r5)
            com.google.protobuf.WireFormat$FieldType r1 = com.google.protobuf.WireFormat.FieldType.DOUBLE
            com.google.protobuf.WireFormat$JavaType r1 = com.google.protobuf.WireFormat.JavaType.INT
            com.google.protobuf.WireFormat$JavaType r0 = r0.javaType
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L3f;
                case 1: goto L3c;
                case 2: goto L39;
                case 3: goto L36;
                case 4: goto L33;
                case 5: goto L30;
                case 6: goto L27;
                case 7: goto L1e;
                case 8: goto L15;
                default: goto L14;
            }
        L14:
            goto L44
        L15:
            boolean r0 = r5 instanceof com.google.protobuf.MessageLite
            if (r0 != 0) goto L43
            boolean r0 = r5 instanceof com.google.protobuf.LazyField
            if (r0 == 0) goto L44
            goto L43
        L1e:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L43
            boolean r0 = r5 instanceof com.google.protobuf.Internal.EnumLite
            if (r0 == 0) goto L44
            goto L43
        L27:
            boolean r0 = r5 instanceof com.google.protobuf.ByteString
            if (r0 != 0) goto L43
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L44
            goto L43
        L30:
            boolean r0 = r5 instanceof java.lang.String
            goto L41
        L33:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L41
        L36:
            boolean r0 = r5 instanceof java.lang.Double
            goto L41
        L39:
            boolean r0 = r5 instanceof java.lang.Float
            goto L41
        L3c:
            boolean r0 = r5 instanceof java.lang.Long
            goto L41
        L3f:
            boolean r0 = r5 instanceof java.lang.Integer
        L41:
            if (r0 == 0) goto L44
        L43:
            return
        L44:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            int r1 = r4.getNumber()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            com.google.protobuf.WireFormat$FieldType r4 = r4.getLiteType()
            com.google.protobuf.WireFormat$JavaType r4 = r4.javaType
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r1
            r1 = 1
            r2[r1] = r4
            r4 = 2
            r2[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r2)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.FieldSet.verifyType$ar$class_merging$ar$ds(com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor, java.lang.Object):void");
    }

    public static void writeElement(CodedOutputStream codedOutputStream, WireFormat.FieldType fieldType, int i, Object obj) {
        if (fieldType != WireFormat.FieldType.GROUP) {
            codedOutputStream.writeTag(i, fieldType.wireType);
            WireFormat.JavaType javaType = WireFormat.JavaType.INT;
            switch (fieldType) {
                case DOUBLE:
                    codedOutputStream.writeDoubleNoTag(((Double) obj).doubleValue());
                    return;
                case FLOAT:
                    codedOutputStream.writeFloatNoTag(((Float) obj).floatValue());
                    return;
                case INT64:
                    codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                    return;
                case UINT64:
                    codedOutputStream.writeUInt64NoTag(((Long) obj).longValue());
                    return;
                case INT32:
                    codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                    return;
                case FIXED64:
                    codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                    return;
                case FIXED32:
                    codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                    return;
                case BOOL:
                    codedOutputStream.write(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                    return;
                case STRING:
                    if (obj instanceof ByteString) {
                        codedOutputStream.writeBytesNoTag((ByteString) obj);
                        return;
                    } else {
                        codedOutputStream.writeStringNoTag((String) obj);
                        return;
                    }
                case GROUP:
                    codedOutputStream.writeGroupNoTag((MessageLite) obj);
                    return;
                case MESSAGE:
                    codedOutputStream.writeMessageNoTag((MessageLite) obj);
                    return;
                case BYTES:
                    if (obj instanceof ByteString) {
                        codedOutputStream.writeBytesNoTag((ByteString) obj);
                        return;
                    } else {
                        byte[] bArr = (byte[]) obj;
                        codedOutputStream.writeByteArrayNoTag$ar$ds(bArr, bArr.length);
                        return;
                    }
                case UINT32:
                    codedOutputStream.writeUInt32NoTag(((Integer) obj).intValue());
                    return;
                case ENUM:
                    if (obj instanceof Internal.EnumLite) {
                        codedOutputStream.writeInt32NoTag(((Internal.EnumLite) obj).getNumber());
                        return;
                    } else {
                        codedOutputStream.writeInt32NoTag(((Integer) obj).intValue());
                        return;
                    }
                case SFIXED32:
                    codedOutputStream.writeFixed32NoTag(((Integer) obj).intValue());
                    return;
                case SFIXED64:
                    codedOutputStream.writeFixed64NoTag(((Long) obj).longValue());
                    return;
                case SINT32:
                    codedOutputStream.writeSInt32NoTag(((Integer) obj).intValue());
                    return;
                case SINT64:
                    codedOutputStream.writeSInt64NoTag(((Long) obj).longValue());
                    return;
                default:
                    return;
            }
        }
        MessageLite messageLite = (MessageLite) obj;
        Internal.isProto1Group(messageLite);
        codedOutputStream.writeTag(i, 3);
        codedOutputStream.writeGroupNoTag(messageLite);
        codedOutputStream.writeTag(i, 4);
    }

    public final void addRepeatedField$ar$class_merging(GeneratedMessageLite.ExtensionDescriptor extensionDescriptor, Object obj) {
        List list;
        if (extensionDescriptor.isRepeated) {
            verifyType$ar$class_merging$ar$ds(extensionDescriptor, obj);
            Object field$ar$class_merging = getField$ar$class_merging(extensionDescriptor);
            if (field$ar$class_merging == null) {
                list = new ArrayList();
                this.fields.put((Comparable) extensionDescriptor, (Object) list);
            } else {
                list = (List) field$ar$class_merging;
            }
            list.add(obj);
            return;
        }
        throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    }

    /* renamed from: clone */
    public final FieldSet m237clone() {
        FieldSet fieldSet = new FieldSet();
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            Map.Entry arrayEntryAt = this.fields.getArrayEntryAt(i);
            fieldSet.setField$ar$class_merging((GeneratedMessageLite.ExtensionDescriptor) arrayEntryAt.getKey(), arrayEntryAt.getValue());
        }
        for (Map.Entry entry : this.fields.getOverflowEntries()) {
            fieldSet.setField$ar$class_merging((GeneratedMessageLite.ExtensionDescriptor) entry.getKey(), entry.getValue());
        }
        fieldSet.hasLazyField = this.hasLazyField;
        return fieldSet;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FieldSet)) {
            return false;
        }
        return this.fields.equals(((FieldSet) obj).fields);
    }

    public final Object getField$ar$class_merging(GeneratedMessageLite.ExtensionDescriptor extensionDescriptor) {
        Object obj = this.fields.get(extensionDescriptor);
        if (!(obj instanceof LazyField)) {
            return obj;
        }
        throw null;
    }

    public final int getMessageSetSerializedSize(Map.Entry entry) {
        int computeUInt32Size;
        int computeTagSize;
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
        Object value = entry.getValue();
        if (extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extensionDescriptor.isRepeated() && !extensionDescriptor.isPacked()) {
            if (value instanceof LazyField) {
                int number = ((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).getNumber();
                int computeTagSize2 = CodedOutputStream.computeTagSize(1);
                computeUInt32Size = computeTagSize2 + computeTagSize2 + CodedOutputStream.computeUInt32Size(2, number);
                computeTagSize = CodedOutputStream.computeLazyFieldSize(3, (LazyField) value);
            } else {
                int number2 = ((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).getNumber();
                int computeTagSize3 = CodedOutputStream.computeTagSize(1);
                computeUInt32Size = computeTagSize3 + computeTagSize3 + CodedOutputStream.computeUInt32Size(2, number2);
                computeTagSize = CodedOutputStream.computeTagSize(3) + CodedOutputStream.computeMessageSizeNoTag((MessageLite) value);
            }
            return computeUInt32Size + computeTagSize;
        }
        return computeFieldSize$ar$class_merging(extensionDescriptor, value);
    }

    public final boolean hasField$ar$class_merging(GeneratedMessageLite.ExtensionDescriptor extensionDescriptor) {
        if (!extensionDescriptor.isRepeated) {
            if (this.fields.get(extensionDescriptor) != null) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    }

    public final int hashCode() {
        return this.fields.hashCode();
    }

    public final boolean isEmpty() {
        return this.fields.isEmpty();
    }

    public final boolean isInitialized() {
        for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
            if (!isInitialized(this.fields.getArrayEntryAt(i))) {
                return false;
            }
        }
        Iterator it = this.fields.getOverflowEntries().iterator();
        while (it.hasNext()) {
            if (!isInitialized((Map.Entry) it.next())) {
                return false;
            }
        }
        return true;
    }

    public final Iterator iterator() {
        if (isEmpty()) {
            return Collections.emptyIterator();
        }
        if (this.hasLazyField) {
            return new LazyField.LazyIterator(this.fields.entrySet().iterator());
        }
        return this.fields.entrySet().iterator();
    }

    public final void makeImmutable() {
        Map unmodifiableMap;
        Map unmodifiableMap2;
        if (!this.isImmutable) {
            for (int i = 0; i < this.fields.getNumArrayEntries(); i++) {
                Map.Entry arrayEntryAt = this.fields.getArrayEntryAt(i);
                if (arrayEntryAt.getValue() instanceof GeneratedMessageLite) {
                    ((GeneratedMessageLite) arrayEntryAt.getValue()).makeImmutable();
                }
            }
            SmallSortedMap smallSortedMap = this.fields;
            if (!smallSortedMap.isImmutable) {
                for (int i2 = 0; i2 < smallSortedMap.getNumArrayEntries(); i2++) {
                    Map.Entry arrayEntryAt2 = smallSortedMap.getArrayEntryAt(i2);
                    if (((GeneratedMessageLite.ExtensionDescriptor) arrayEntryAt2.getKey()).isRepeated()) {
                        arrayEntryAt2.setValue(DesugarCollections.unmodifiableList((List) arrayEntryAt2.getValue()));
                    }
                }
                for (Map.Entry entry : smallSortedMap.getOverflowEntries()) {
                    if (((GeneratedMessageLite.ExtensionDescriptor) entry.getKey()).isRepeated()) {
                        entry.setValue(DesugarCollections.unmodifiableList((List) entry.getValue()));
                    }
                }
            }
            if (!smallSortedMap.isImmutable) {
                if (smallSortedMap.overflowEntries.isEmpty()) {
                    unmodifiableMap = Collections.emptyMap();
                } else {
                    unmodifiableMap = DesugarCollections.unmodifiableMap(smallSortedMap.overflowEntries);
                }
                smallSortedMap.overflowEntries = unmodifiableMap;
                if (smallSortedMap.overflowEntriesDescending.isEmpty()) {
                    unmodifiableMap2 = Collections.emptyMap();
                } else {
                    unmodifiableMap2 = DesugarCollections.unmodifiableMap(smallSortedMap.overflowEntriesDescending);
                }
                smallSortedMap.overflowEntriesDescending = unmodifiableMap2;
                smallSortedMap.isImmutable = true;
            }
            this.isImmutable = true;
        }
    }

    public final void mergeFromField(Map.Entry entry) {
        MessageLite.Builder mergeFrom;
        MessageLite build;
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
        Object value = entry.getValue();
        boolean z = value instanceof LazyField;
        if (extensionDescriptor.isRepeated()) {
            if (!z) {
                Object field$ar$class_merging = getField$ar$class_merging(extensionDescriptor);
                if (field$ar$class_merging == null) {
                    field$ar$class_merging = new ArrayList();
                }
                Iterator it = ((List) value).iterator();
                while (it.hasNext()) {
                    ((List) field$ar$class_merging).add(cloneIfMutable(it.next()));
                }
                this.fields.put((Comparable) extensionDescriptor, field$ar$class_merging);
                return;
            }
            throw new IllegalStateException("Lazy fields can not be repeated");
        }
        if (extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) {
            Object field$ar$class_merging2 = getField$ar$class_merging(extensionDescriptor);
            if (field$ar$class_merging2 == null) {
                this.fields.put((Comparable) extensionDescriptor, cloneIfMutable(value));
                if (z) {
                    this.hasLazyField = true;
                    return;
                }
                return;
            }
            if (!z) {
                if (!(field$ar$class_merging2 instanceof MutableMessageLite)) {
                    mergeFrom = ((GeneratedMessageLite.Builder) ((MessageLite) field$ar$class_merging2).toBuilder()).mergeFrom((GeneratedMessageLite.Builder) ((MessageLite) value));
                    build = mergeFrom.build();
                } else {
                    build = GeneratedMessageLite.ExtensionDescriptor.internalMergeFrom$ar$ds$bad72047_0();
                }
                this.fields.put((Comparable) extensionDescriptor, (Object) build);
                return;
            }
            throw null;
        }
        if (!z) {
            this.fields.put((Comparable) extensionDescriptor, cloneIfMutable(value));
            return;
        }
        throw new IllegalStateException("Lazy fields must be message-valued");
    }

    public final void setField$ar$class_merging(GeneratedMessageLite.ExtensionDescriptor extensionDescriptor, Object obj) {
        if (extensionDescriptor.isRepeated()) {
            if (obj instanceof List) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll((List) obj);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    verifyType$ar$class_merging$ar$ds(extensionDescriptor, arrayList.get(i));
                }
                obj = arrayList;
            } else {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
        } else {
            verifyType$ar$class_merging$ar$ds(extensionDescriptor, obj);
        }
        if (obj instanceof LazyField) {
            this.hasLazyField = true;
        }
        this.fields.put((Comparable) extensionDescriptor, obj);
    }

    private FieldSet(byte[] bArr) {
        makeImmutable();
        makeImmutable();
    }

    private static boolean isInitialized(Map.Entry entry) {
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
        if (extensionDescriptor.getLiteJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        if (extensionDescriptor.isRepeated()) {
            Iterator it = ((List) entry.getValue()).iterator();
            while (it.hasNext()) {
                if (!isMessageFieldValueInitialized(it.next())) {
                    return false;
                }
            }
            return true;
        }
        return isMessageFieldValueInitialized(entry.getValue());
    }
}
