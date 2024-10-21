package com.google.protobuf;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SchemaUtil {
    private static final OnDeviceExplicitContentCreateLogEvent UNKNOWN_FIELD_SET_FULL_SCHEMA$ar$class_merging$ar$class_merging;
    public static final OnDeviceExplicitContentCreateLogEvent UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;

    static {
        Protobuf protobuf = Protobuf.INSTANCE;
        UNKNOWN_FIELD_SET_FULL_SCHEMA$ar$class_merging$ar$class_merging = null;
        UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging = new OnDeviceExplicitContentCreateLogEvent(null);
    }

    public static int computeSizeEnumListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeFixed32List$ar$ds(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * CodedOutputStream.computeFixed32Size$ar$ds(i);
    }

    public static int computeSizeFixed32ListNoTag(List list) {
        return list.size() * 4;
    }

    public static int computeSizeFixed64List$ar$ds(int i, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return size * CodedOutputStream.computeFixed64Size$ar$ds(i);
    }

    public static int computeSizeFixed64ListNoTag(List list) {
        return list.size() * 8;
    }

    public static int computeSizeInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeMessage(int i, Object obj, Schema schema) {
        if (obj instanceof LazyFieldLite) {
            return CodedOutputStream.computeLazyFieldSize(i, (LazyFieldLite) obj);
        }
        return CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj, schema);
    }

    public static int computeSizeSInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeSInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeSInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeUInt32ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt32SizeNoTag(((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
        return i;
    }

    public static int computeSizeUInt64ListNoTag(List list) {
        int i;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i2));
                i2++;
            }
        } else {
            i = 0;
            while (i2 < size) {
                i += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i2)).longValue());
                i2++;
            }
        }
        return i;
    }

    public static Object filterUnknownEnumList$ar$class_merging$ar$class_merging(Object obj, int i, List list, Internal.EnumLiteMap enumLiteMap, Object obj2, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent) {
        if (enumLiteMap == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = (Integer) list.get(i3);
                int intValue = num.intValue();
                if (enumLiteMap.findValueByNumber$ar$ds() != null) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    obj2 = storeUnknownEnum$ar$class_merging$ar$class_merging(obj, i, intValue, obj2, onDeviceExplicitContentCreateLogEvent);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (enumLiteMap.findValueByNumber$ar$ds() == null) {
                    obj2 = storeUnknownEnum$ar$class_merging$ar$class_merging(obj, i, intValue2, obj2, onDeviceExplicitContentCreateLogEvent);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    public static Object filterUnknownEnumList$ar$class_merging$e1973ddf_0$ar$class_merging(Object obj, int i, List list, Internal.EnumVerifier enumVerifier, Object obj2, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent) {
        if (enumVerifier == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                Integer num = (Integer) list.get(i3);
                int intValue = num.intValue();
                if (enumVerifier.isInRange(intValue)) {
                    if (i3 != i2) {
                        list.set(i2, num);
                    }
                    i2++;
                } else {
                    obj2 = storeUnknownEnum$ar$class_merging$ar$class_merging(obj, i, intValue, obj2, onDeviceExplicitContentCreateLogEvent);
                }
            }
            if (i2 != size) {
                list.subList(i2, size).clear();
                return obj2;
            }
        } else {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                int intValue2 = ((Integer) it.next()).intValue();
                if (!enumVerifier.isInRange(intValue2)) {
                    obj2 = storeUnknownEnum$ar$class_merging$ar$class_merging(obj, i, intValue2, obj2, onDeviceExplicitContentCreateLogEvent);
                    it.remove();
                }
            }
        }
        return obj2;
    }

    public static void mergeExtensions$ar$class_merging$ar$class_merging(OnDeviceDocumentScannerFinishLogEvent onDeviceDocumentScannerFinishLogEvent, Object obj, Object obj2) {
        FieldSet ensureExtensionsAreMutable;
        FieldSet extensions$ar$ds = OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj2);
        if (!extensions$ar$ds.isEmpty()) {
            ensureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) obj).ensureExtensionsAreMutable();
            for (int i = 0; i < extensions$ar$ds.fields.getNumArrayEntries(); i++) {
                ensureExtensionsAreMutable.mergeFromField(extensions$ar$ds.fields.getArrayEntryAt(i));
            }
            Iterator it = extensions$ar$ds.fields.getOverflowEntries().iterator();
            while (it.hasNext()) {
                ensureExtensionsAreMutable.mergeFromField((Map.Entry) it.next());
            }
        }
    }

    public static void mergeUnknownFields$ar$class_merging$ar$class_merging(OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent, Object obj, Object obj2) {
        onDeviceExplicitContentCreateLogEvent.setToMessage(obj, OnDeviceExplicitContentCreateLogEvent.merge$ar$ds(onDeviceExplicitContentCreateLogEvent.getFromMessage(obj), onDeviceExplicitContentCreateLogEvent.getFromMessage(obj2)));
    }

    public static Object storeUnknownEnum$ar$class_merging$ar$class_merging(Object obj, int i, int i2, Object obj2, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent) {
        if (obj2 == null) {
            obj2 = onDeviceExplicitContentCreateLogEvent.getBuilderFromMessage(obj);
        }
        OnDeviceExplicitContentCreateLogEvent.addVarint$ar$ds(obj2, i, i2);
        return obj2;
    }

    public static void writeBoolList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof BooleanArrayList) {
                BooleanArrayList booleanArrayList = (BooleanArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < booleanArrayList.size; i4++) {
                        booleanArrayList.getBoolean(i4);
                        i3++;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < booleanArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).write(booleanArrayList.getBoolean(i2) ? (byte) 1 : (byte) 0);
                        i2++;
                    }
                    return;
                }
                while (i2 < booleanArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeBool(i, booleanArrayList.getBoolean(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Boolean) list.get(i6)).booleanValue();
                    i5++;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).write(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeBool(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
        }
    }

    public static void writeBytesList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeBytes(i, (ByteString) list.get(i2));
            }
        }
    }

    public static void writeDoubleList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof DoubleArrayList) {
                DoubleArrayList doubleArrayList = (DoubleArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < doubleArrayList.size; i4++) {
                        doubleArrayList.getDouble(i4);
                        i3 += 8;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < doubleArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeDoubleNoTag(doubleArrayList.getDouble(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < doubleArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeDouble(i, doubleArrayList.getDouble(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Double) list.get(i6)).doubleValue();
                    i5 += 8;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeDoubleNoTag(((Double) list.get(i2)).doubleValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeDouble(i, ((Double) list.get(i2)).doubleValue());
                i2++;
            }
        }
    }

    public static void writeEnumList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i6)).intValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeFixed32List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        intArrayList.getInt(i4);
                        i3 += 4;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Integer) list.get(i6)).intValue();
                    i5 += 4;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeFixed64List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < longArrayList.size; i4++) {
                        longArrayList.getLong(i4);
                        i3 += 8;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < longArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64NoTag(longArrayList.getLong(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < longArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, longArrayList.getLong(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Long) list.get(i6)).longValue();
                    i5 += 8;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64NoTag(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public static void writeFloatList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof FloatArrayList) {
                FloatArrayList floatArrayList = (FloatArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < floatArrayList.size; i4++) {
                        floatArrayList.getFloat(i4);
                        i3 += 4;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < floatArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFloatNoTag(floatArrayList.getFloat(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < floatArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFloat(i, floatArrayList.getFloat(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Float) list.get(i6)).floatValue();
                    i5 += 4;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFloatNoTag(((Float) list.get(i2)).floatValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFloat(i, ((Float) list.get(i2)).floatValue());
                i2++;
            }
        }
    }

    public static void writeGroupList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, Schema schema) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                executorSelector.writeGroup(i, list.get(i2), schema);
            }
        }
    }

    public static void writeInt32List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeInt32SizeNoTag(intArrayList.getInt(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeInt32SizeNoTag(((Integer) list.get(i6)).intValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeInt64List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < longArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < longArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64NoTag(longArrayList.getLong(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < longArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, longArrayList.getLong(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i6)).longValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64NoTag(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public static void writeMessageList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, Schema schema) {
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                executorSelector.writeMessage(i, list.get(i2), schema);
            }
        }
    }

    public static void writeSFixed32List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        intArrayList.getInt(i4);
                        i3 += 4;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Integer) list.get(i6)).intValue();
                    i5 += 4;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeSFixed64List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < longArrayList.size; i4++) {
                        longArrayList.getLong(i4);
                        i3 += 8;
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < longArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64NoTag(longArrayList.getLong(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < longArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, longArrayList.getLong(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    ((Long) list.get(i6)).longValue();
                    i5 += 8;
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64NoTag(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeFixed64(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public static void writeSInt32List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeSInt32SizeNoTag(intArrayList.getInt(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeSInt32SizeNoTag(((Integer) list.get(i6)).intValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeSInt64List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < longArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeSInt64SizeNoTag(longArrayList.getLong(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < longArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt64NoTag(longArrayList.getLong(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < longArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt64(i, longArrayList.getLong(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeSInt64SizeNoTag(((Long) list.get(i6)).longValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt64NoTag(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeSInt64(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }

    public static void writeStringList$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LazyStringList) {
                LazyStringList lazyStringList = (LazyStringList) list;
                while (i2 < list.size()) {
                    Object raw$ar$ds = lazyStringList.getRaw$ar$ds();
                    if (raw$ar$ds instanceof String) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeString(i, (String) raw$ar$ds);
                    } else {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeBytes(i, (ByteString) raw$ar$ds);
                    }
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeString(i, (String) list.get(i2));
                i2++;
            }
        }
    }

    public static void writeUInt32List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof IntArrayList) {
                IntArrayList intArrayList = (IntArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < intArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeUInt32SizeNoTag(intArrayList.getInt(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < intArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(intArrayList.getInt(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < intArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32(i, intArrayList.getInt(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeUInt32SizeNoTag(((Integer) list.get(i6)).intValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(((Integer) list.get(i2)).intValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
        }
    }

    public static void writeUInt64List$ar$class_merging$ar$class_merging$ar$class_merging(int i, List list, ExecutorSelector executorSelector, boolean z) {
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            if (list instanceof LongArrayList) {
                LongArrayList longArrayList = (LongArrayList) list;
                if (z) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                    int i3 = 0;
                    for (int i4 = 0; i4 < longArrayList.size; i4++) {
                        i3 += CodedOutputStream.computeUInt64SizeNoTag(longArrayList.getLong(i4));
                    }
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i3);
                    while (i2 < longArrayList.size) {
                        ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64NoTag(longArrayList.getLong(i2));
                        i2++;
                    }
                    return;
                }
                while (i2 < longArrayList.size) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, longArrayList.getLong(i2));
                    i2++;
                }
                return;
            }
            if (z) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                int i5 = 0;
                for (int i6 = 0; i6 < list.size(); i6++) {
                    i5 += CodedOutputStream.computeUInt64SizeNoTag(((Long) list.get(i6)).longValue());
                }
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(i5);
                while (i2 < list.size()) {
                    ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64NoTag(((Long) list.get(i2)).longValue());
                    i2++;
                }
                return;
            }
            while (i2 < list.size()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt64(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
        }
    }
}
