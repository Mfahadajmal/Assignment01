package com.google.protobuf;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent;
import com.google.mlkit.logging.schema.OnDeviceDocumentScannerStartLogEvent;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent;
import com.google.mlkit.vision.text.internal.TextRecognizerImplFactory;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.WireFormat;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.net.PrivateKeyType;
import sun.misc.Unsafe;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageSchema<T> implements Schema<T> {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final OnDeviceDocumentScannerFinishLogEvent extensionSchema$ar$class_merging$ar$class_merging;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final OnDeviceDocumentScannerFinishLogEvent listFieldSchema$ar$class_merging$ar$class_merging;
    private final boolean lite;
    private final OnDeviceDocumentScannerStartLogEvent mapFieldSchema$ar$class_merging$ar$class_merging$ar$class_merging;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final OnDeviceDocumentScannerStartLogEvent newInstanceSchema$ar$class_merging$ar$class_merging;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final OnDeviceExplicitContentCreateLogEvent unknownFieldSchema$ar$class_merging$ar$class_merging;
    private final boolean useCachedSizeField;

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, boolean z, int[] iArr2, int i3, int i4, OnDeviceDocumentScannerStartLogEvent onDeviceDocumentScannerStartLogEvent, OnDeviceDocumentScannerFinishLogEvent onDeviceDocumentScannerFinishLogEvent, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent, OnDeviceDocumentScannerFinishLogEvent onDeviceDocumentScannerFinishLogEvent2, OnDeviceDocumentScannerStartLogEvent onDeviceDocumentScannerStartLogEvent2) {
        boolean z2;
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite instanceof GeneratedMessageLite;
        if (onDeviceDocumentScannerFinishLogEvent2 != null && OnDeviceDocumentScannerFinishLogEvent.hasExtensions$ar$ds(messageLite)) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.hasExtensions = z2;
        this.useCachedSizeField = false;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema$ar$class_merging$ar$class_merging = onDeviceDocumentScannerStartLogEvent;
        this.listFieldSchema$ar$class_merging$ar$class_merging = onDeviceDocumentScannerFinishLogEvent;
        this.unknownFieldSchema$ar$class_merging$ar$class_merging = onDeviceExplicitContentCreateLogEvent;
        this.extensionSchema$ar$class_merging$ar$class_merging = onDeviceDocumentScannerFinishLogEvent2;
        this.defaultInstance = messageLite;
        this.mapFieldSchema$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceDocumentScannerStartLogEvent2;
    }

    private final boolean arePresentForEquals(Object obj, Object obj2, int i) {
        if (isFieldPresent(obj, i) == isFieldPresent(obj2, i)) {
            return true;
        }
        return false;
    }

    private static void checkMutable(Object obj) {
        if (isMutable(obj)) {
        } else {
            throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
        }
    }

    private static final int decodeMapEntryValue$ar$ds(byte[] bArr, int i, int i2, WireFormat.FieldType fieldType, Class cls, ArrayDecoders$Registers arrayDecoders$Registers) {
        int i3;
        boolean z;
        WireFormat.FieldType fieldType2 = WireFormat.FieldType.DOUBLE;
        switch (fieldType) {
            case DOUBLE:
                i3 = i + 8;
                arrayDecoders$Registers.object1 = Double.valueOf(ExtensionLite.decodeDouble(bArr, i));
                break;
            case FLOAT:
                i3 = i + 4;
                arrayDecoders$Registers.object1 = Float.valueOf(ExtensionLite.decodeFloat(bArr, i));
                break;
            case INT64:
            case UINT64:
                int decodeVarint64 = ExtensionLite.decodeVarint64(bArr, i, arrayDecoders$Registers);
                arrayDecoders$Registers.object1 = Long.valueOf(arrayDecoders$Registers.long1);
                return decodeVarint64;
            case INT32:
            case UINT32:
            case ENUM:
                int decodeVarint32 = ExtensionLite.decodeVarint32(bArr, i, arrayDecoders$Registers);
                arrayDecoders$Registers.object1 = Integer.valueOf(arrayDecoders$Registers.int1);
                return decodeVarint32;
            case FIXED64:
            case SFIXED64:
                i3 = i + 8;
                arrayDecoders$Registers.object1 = Long.valueOf(ExtensionLite.decodeFixed64(bArr, i));
                break;
            case FIXED32:
            case SFIXED32:
                i3 = i + 4;
                arrayDecoders$Registers.object1 = Integer.valueOf(ExtensionLite.decodeFixed32(bArr, i));
                break;
            case BOOL:
                int decodeVarint642 = ExtensionLite.decodeVarint64(bArr, i, arrayDecoders$Registers);
                if (arrayDecoders$Registers.long1 != 0) {
                    z = true;
                } else {
                    z = false;
                }
                arrayDecoders$Registers.object1 = Boolean.valueOf(z);
                return decodeVarint642;
            case STRING:
                return ExtensionLite.decodeStringRequireUtf8(bArr, i, arrayDecoders$Registers);
            case GROUP:
            default:
                throw new RuntimeException("unsupported field type.");
            case MESSAGE:
                return ExtensionLite.decodeMessageField(Protobuf.INSTANCE.schemaFor(cls), bArr, i, i2, arrayDecoders$Registers);
            case BYTES:
                return ExtensionLite.decodeBytes(bArr, i, arrayDecoders$Registers);
            case SINT32:
                int decodeVarint322 = ExtensionLite.decodeVarint32(bArr, i, arrayDecoders$Registers);
                arrayDecoders$Registers.object1 = Integer.valueOf(CodedInputStream.decodeZigZag32(arrayDecoders$Registers.int1));
                return decodeVarint322;
            case SINT64:
                int decodeVarint643 = ExtensionLite.decodeVarint64(bArr, i, arrayDecoders$Registers);
                arrayDecoders$Registers.object1 = Long.valueOf(CodedInputStream.decodeZigZag64(arrayDecoders$Registers.long1));
                return decodeVarint643;
        }
        return i3;
    }

    private final Object filterMapUnknownEnumValues$ar$class_merging$ar$class_merging(Object obj, int i, Object obj2, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent, Object obj3) {
        int numberAt = numberAt(i);
        Object object = UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i)));
        if (object == null) {
            return obj2;
        }
        Internal.EnumVerifier enumFieldVerifier = getEnumFieldVerifier(i);
        if (enumFieldVerifier == null) {
            return obj2;
        }
        Map forMutableMapData$ar$ds = OnDeviceDocumentScannerStartLogEvent.forMutableMapData$ar$ds(object);
        MapEntryLite$Metadata forMapMetadata$ar$ds = OnDeviceDocumentScannerStartLogEvent.forMapMetadata$ar$ds(getMapFieldDefaultEntry(i));
        Iterator it = forMutableMapData$ar$ds.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!enumFieldVerifier.isInRange(((Integer) entry.getValue()).intValue())) {
                if (obj2 == null) {
                    obj2 = onDeviceExplicitContentCreateLogEvent.getBuilderFromMessage(obj3);
                }
                TextRecognizerImplFactory textRecognizerImplFactory = new TextRecognizerImplFactory(ExecutorSelector.computeSerializedSize(forMapMetadata$ar$ds, entry.getKey(), entry.getValue()));
                try {
                    ExecutorSelector.writeTo((CodedOutputStream) textRecognizerImplFactory.TextRecognizerImplFactory$ar$executorSelector, forMapMetadata$ar$ds, entry.getKey(), entry.getValue());
                    OnDeviceExplicitContentCreateLogEvent.addLengthDelimited$ar$ds(obj2, numberAt, textRecognizerImplFactory.build());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj2;
    }

    private final Internal.EnumVerifier getEnumFieldVerifier(int i) {
        int i2 = i / 3;
        return (Internal.EnumVerifier) this.objects[i2 + i2 + 1];
    }

    private final Object getMapFieldDefaultEntry(int i) {
        int i2 = i / 3;
        return this.objects[i2 + i2];
    }

    private final Schema getMessageFieldSchema(int i) {
        Object[] objArr = this.objects;
        int i2 = i / 3;
        int i3 = i2 + i2;
        Schema schema = (Schema) objArr[i3];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.INSTANCE.schemaFor((Class) objArr[i3 + 1]);
        this.objects[i3] = schemaFor;
        return schemaFor;
    }

    static UnknownFieldSetLite getMutableUnknownFields(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite == UnknownFieldSetLite.DEFAULT_INSTANCE) {
            UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
            generatedMessageLite.unknownFields = unknownFieldSetLite2;
            return unknownFieldSetLite2;
        }
        return unknownFieldSetLite;
    }

    private static boolean isEnforceUtf8(int i) {
        if ((i & 536870912) != 0) {
            return true;
        }
        return false;
    }

    private final boolean isFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j != 1048575) {
            return (UnsafeUtil.getInt(obj, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0;
        }
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                return Double.doubleToRawLongBits(UnsafeUtil.getDouble(obj, offset)) != 0;
            case 1:
                return Float.floatToRawIntBits(UnsafeUtil.getFloat(obj, offset)) != 0;
            case 2:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 3:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 4:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 5:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 6:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 7:
                return UnsafeUtil.getBoolean(obj, offset);
            case 8:
                Object object = UnsafeUtil.getObject(obj, offset);
                if (object instanceof String) {
                    return !((String) object).isEmpty();
                }
                if (object instanceof ByteString) {
                    return !ByteString.EMPTY.equals(object);
                }
                throw new IllegalArgumentException();
            case 9:
                return UnsafeUtil.getObject(obj, offset) != null;
            case 10:
                return !ByteString.EMPTY.equals(UnsafeUtil.getObject(obj, offset));
            case 11:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 12:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 13:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 14:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 15:
                return UnsafeUtil.getInt(obj, offset) != 0;
            case 16:
                return UnsafeUtil.getLong(obj, offset) != 0;
            case 17:
                return UnsafeUtil.getObject(obj, offset) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private final boolean isOneofPresent(Object obj, int i, int i2) {
        if (UnsafeUtil.getInt(obj, presenceMaskAndOffsetAt(i2) & 1048575) == i) {
            return true;
        }
        return false;
    }

    private final void mergeMessage(Object obj, Object obj2, int i) {
        if (!isFieldPresent(obj2, i)) {
            return;
        }
        long offset = offset(typeAndOffsetAt(i));
        Unsafe unsafe = UNSAFE;
        Object object = unsafe.getObject(obj2, offset);
        if (object != null) {
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isFieldPresent(obj, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(obj, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(obj, offset, newInstance);
                }
                setFieldPresent(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(obj, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
            return;
        }
        throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + obj2.toString());
    }

    private final void mergeOneofMessage(Object obj, Object obj2, int i) {
        int numberAt = numberAt(i);
        if (!isOneofPresent(obj2, numberAt, i)) {
            return;
        }
        long offset = offset(typeAndOffsetAt(i));
        Unsafe unsafe = UNSAFE;
        Object object = unsafe.getObject(obj2, offset);
        if (object != null) {
            Schema messageFieldSchema = getMessageFieldSchema(i);
            if (!isOneofPresent(obj, numberAt, i)) {
                if (!isMutable(object)) {
                    unsafe.putObject(obj, offset, object);
                } else {
                    Object newInstance = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance, object);
                    unsafe.putObject(obj, offset, newInstance);
                }
                setOneofPresent(obj, numberAt, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, offset);
            if (!isMutable(object2)) {
                Object newInstance2 = messageFieldSchema.newInstance();
                messageFieldSchema.mergeFrom(newInstance2, object2);
                unsafe.putObject(obj, offset, newInstance2);
                object2 = newInstance2;
            }
            messageFieldSchema.mergeFrom(object2, object);
            return;
        }
        throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + obj2.toString());
    }

    private final Object mutableMessageFieldForMerge(Object obj, int i) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(obj, i)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private final Object mutableOneofMessageFieldForMerge(Object obj, int i, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(obj, i, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, offset(typeAndOffsetAt(i2)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0395  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x027e  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0264  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.protobuf.MessageSchema newSchema$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(com.google.protobuf.MessageInfo r33, com.google.mlkit.logging.schema.OnDeviceDocumentScannerStartLogEvent r34, com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent r35, com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent r36, com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent r37, com.google.mlkit.logging.schema.OnDeviceDocumentScannerStartLogEvent r38) {
        /*
            Method dump skipped, instructions count: 1030
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.newSchema$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(com.google.protobuf.MessageInfo, com.google.mlkit.logging.schema.OnDeviceDocumentScannerStartLogEvent, com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent, com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent, com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent, com.google.mlkit.logging.schema.OnDeviceDocumentScannerStartLogEvent):com.google.protobuf.MessageSchema");
    }

    private final int numberAt(int i) {
        return this.buffer[i];
    }

    private static long offset(int i) {
        return i & 1048575;
    }

    private static boolean oneofBooleanAt(Object obj, long j) {
        return ((Boolean) UnsafeUtil.getObject(obj, j)).booleanValue();
    }

    private static double oneofDoubleAt(Object obj, long j) {
        return ((Double) UnsafeUtil.getObject(obj, j)).doubleValue();
    }

    private static float oneofFloatAt(Object obj, long j) {
        return ((Float) UnsafeUtil.getObject(obj, j)).floatValue();
    }

    private static int oneofIntAt(Object obj, long j) {
        return ((Integer) UnsafeUtil.getObject(obj, j)).intValue();
    }

    private static long oneofLongAt(Object obj, long j) {
        return ((Long) UnsafeUtil.getObject(obj, j)).longValue();
    }

    private final int positionForFieldNumber(int i) {
        if (i >= this.minFieldNumber && i <= this.maxFieldNumber) {
            return slowPositionForFieldNumber(i, 0);
        }
        return -1;
    }

    private final int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private final void readString$ar$class_merging$ar$class_merging(Object obj, int i, Settings settings) {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), settings.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), settings.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), settings.readBytes());
        }
    }

    private static Field reflectField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private final void setFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = 1048575 & presenceMaskAndOffsetAt;
        if (j == 1048575) {
            return;
        }
        UnsafeUtil.putInt(obj, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(obj, j));
    }

    private final void setOneofPresent(Object obj, int i, int i2) {
        UnsafeUtil.putInt(obj, presenceMaskAndOffsetAt(i2) & 1048575, i);
    }

    private final int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }

    private final void storeMessageField(Object obj, int i, Object obj2) {
        UNSAFE.putObject(obj, offset(typeAndOffsetAt(i)), obj2);
        setFieldPresent(obj, i);
    }

    private final void storeOneofMessageField(Object obj, int i, int i2, Object obj2) {
        UNSAFE.putObject(obj, offset(typeAndOffsetAt(i2)), obj2);
        setOneofPresent(obj, i, i2);
    }

    private static int type(int i) {
        return (i >>> 20) & PrivateKeyType.INVALID;
    }

    private final int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    private final void writeMapHelper$ar$class_merging$ar$class_merging$ar$class_merging(ExecutorSelector executorSelector, int i, Object obj, int i2) {
        if (obj != null) {
            MapEntryLite$Metadata forMapMetadata$ar$ds = OnDeviceDocumentScannerStartLogEvent.forMapMetadata$ar$ds(getMapFieldDefaultEntry(i2));
            Map forMapData$ar$ds = OnDeviceDocumentScannerStartLogEvent.forMapData$ar$ds(obj);
            Object obj2 = executorSelector.ExecutorSelector$ar$defaultExecutorProvider;
            for (Map.Entry entry : forMapData$ar$ds.entrySet()) {
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeTag(i, 2);
                ((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider).writeUInt32NoTag(ExecutorSelector.computeSerializedSize(forMapMetadata$ar$ds, entry.getKey(), entry.getValue()));
                ExecutorSelector.writeTo((CodedOutputStream) executorSelector.ExecutorSelector$ar$defaultExecutorProvider, forMapMetadata$ar$ds, entry.getKey(), entry.getValue());
            }
        }
    }

    private static final void writeString$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(int i, Object obj, ExecutorSelector executorSelector) {
        if (obj instanceof String) {
            executorSelector.writeString(i, (String) obj);
        } else {
            executorSelector.writeBytes(i, (ByteString) obj);
        }
    }

    private static final void writeUnknownInMessageTo$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent, Object obj, ExecutorSelector executorSelector) {
        ((UnknownFieldSetLite) onDeviceExplicitContentCreateLogEvent.getFromMessage(obj)).writeTo$ar$class_merging$8755f9f6_0$ar$class_merging$ar$class_merging(executorSelector);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0013. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x01c4 A[SYNTHETIC] */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(java.lang.Object r8, java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x0051. Please report as an issue. */
    @Override // com.google.protobuf.Schema
    public final int getSerializedSize(Object obj) {
        int serializedSize;
        int i;
        int i2;
        int i3;
        int computeDoubleSize$ar$ds;
        int computeSizeInt64ListNoTag;
        int size;
        int size2;
        int computeSizeUInt64ListNoTag;
        int computeTagSize;
        int computeTagSize2;
        int computeStringSizeNoTag;
        int computeStringSizeNoTag2;
        int computeTagSize3;
        int computeMessageSizeNoTag;
        int i4;
        int computeTagSize4;
        int computeUInt32SizeNoTag;
        int i5;
        int i6;
        Unsafe unsafe = UNSAFE;
        int i7 = 1048575;
        int i8 = 1048575;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        while (i10 < this.buffer.length) {
            int typeAndOffsetAt = typeAndOffsetAt(i10);
            int type = type(typeAndOffsetAt);
            int numberAt = numberAt(i10);
            int i12 = this.buffer[i10 + 2];
            int i13 = i12 & i7;
            if (type <= 17) {
                if (i13 != i8) {
                    if (i13 == i7) {
                        i6 = 0;
                    } else {
                        i6 = unsafe.getInt(obj, i13);
                    }
                    i9 = i6;
                    i8 = i13;
                }
                i = i8;
                i2 = i9;
                i3 = 1 << (i12 >>> 20);
            } else {
                i = i8;
                i2 = i9;
                i3 = 0;
            }
            long offset = offset(typeAndOffsetAt);
            if (type >= FieldType.DOUBLE_LIST_PACKED.id) {
                int i14 = FieldType.SINT64_LIST_PACKED.id;
            }
            switch (type) {
                case 0:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeDoubleSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFloatSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeInt64Size(numberAt, unsafe.getLong(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeUInt64Size(numberAt, unsafe.getLong(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeInt32Size(numberAt, unsafe.getInt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFixed64Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFixed32Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeBoolSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        Object object = unsafe.getObject(obj, offset);
                        if (object instanceof ByteString) {
                            computeDoubleSize$ar$ds = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object);
                        } else {
                            computeDoubleSize$ar$ds = CodedOutputStream.computeStringSize(numberAt, (String) object);
                        }
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 9:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i10));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 10:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeUInt32Size(numberAt, unsafe.getInt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeEnumSize(numberAt, unsafe.getInt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSFixed32Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSFixed64Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSInt32Size(numberAt, unsafe.getInt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSInt64Size(numberAt, unsafe.getLong(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 17:
                    if (isFieldPresent(obj, i10, i, i2, i3)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(obj, offset), getMessageFieldSchema(i10));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 18:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed64List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 19:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed32List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 20:
                    List list = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    if (list.size() != 0) {
                        computeSizeInt64ListNoTag = SchemaUtil.computeSizeInt64ListNoTag(list);
                        size = list.size() * CodedOutputStream.computeTagSize(numberAt);
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    }
                    i4 = 0;
                    i11 += i4;
                case 21:
                    List list2 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent2 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list2.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeUInt64ListNoTag(list2);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 22:
                    List list3 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent3 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list3.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeInt32ListNoTag(list3);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 23:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed64List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 24:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed32List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 25:
                    List list4 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent4 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    int size3 = list4.size();
                    if (size3 != 0) {
                        computeDoubleSize$ar$ds = size3 * CodedOutputStream.computeBoolSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 26:
                    List list5 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent5 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    int size4 = list5.size();
                    if (size4 == 0) {
                        computeTagSize2 = 0;
                    } else {
                        computeTagSize2 = CodedOutputStream.computeTagSize(numberAt) * size4;
                        if (list5 instanceof LazyStringList) {
                            LazyStringList lazyStringList = (LazyStringList) list5;
                            for (int i15 = 0; i15 < size4; i15++) {
                                Object raw$ar$ds = lazyStringList.getRaw$ar$ds();
                                if (raw$ar$ds instanceof ByteString) {
                                    computeStringSizeNoTag2 = CodedOutputStream.computeBytesSizeNoTag((ByteString) raw$ar$ds);
                                } else {
                                    computeStringSizeNoTag2 = CodedOutputStream.computeStringSizeNoTag((String) raw$ar$ds);
                                }
                                computeTagSize2 += computeStringSizeNoTag2;
                            }
                        } else {
                            for (int i16 = 0; i16 < size4; i16++) {
                                Object obj2 = list5.get(i16);
                                if (obj2 instanceof ByteString) {
                                    computeStringSizeNoTag = CodedOutputStream.computeBytesSizeNoTag((ByteString) obj2);
                                } else {
                                    computeStringSizeNoTag = CodedOutputStream.computeStringSizeNoTag((String) obj2);
                                }
                                computeTagSize2 += computeStringSizeNoTag;
                            }
                        }
                    }
                    i11 += computeTagSize2;
                    break;
                case 27:
                    List list6 = (List) unsafe.getObject(obj, offset);
                    Schema messageFieldSchema = getMessageFieldSchema(i10);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent6 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    int size5 = list6.size();
                    if (size5 == 0) {
                        computeTagSize3 = 0;
                    } else {
                        computeTagSize3 = CodedOutputStream.computeTagSize(numberAt) * size5;
                        for (int i17 = 0; i17 < size5; i17++) {
                            Object obj3 = list6.get(i17);
                            if (obj3 instanceof LazyFieldLite) {
                                computeMessageSizeNoTag = CodedOutputStream.computeLazyFieldSizeNoTag((LazyFieldLite) obj3);
                            } else {
                                computeMessageSizeNoTag = CodedOutputStream.computeMessageSizeNoTag((MessageLite) obj3, messageFieldSchema);
                            }
                            computeTagSize3 += computeMessageSizeNoTag;
                        }
                    }
                    i11 += computeTagSize3;
                    break;
                case 28:
                    List list7 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent7 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    int size6 = list7.size();
                    if (size6 != 0) {
                        i4 = size6 * CodedOutputStream.computeTagSize(numberAt);
                        for (int i18 = 0; i18 < list7.size(); i18++) {
                            i4 += CodedOutputStream.computeBytesSizeNoTag((ByteString) list7.get(i18));
                        }
                        i11 += i4;
                        break;
                    }
                    i4 = 0;
                    i11 += i4;
                case 29:
                    List list8 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent8 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list8.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeUInt32ListNoTag(list8);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 30:
                    List list9 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent9 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list9.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeEnumListNoTag(list9);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 31:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed32List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 32:
                    computeDoubleSize$ar$ds = SchemaUtil.computeSizeFixed64List$ar$ds(numberAt, (List) unsafe.getObject(obj, offset));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 33:
                    List list10 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent10 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list10.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeSInt32ListNoTag(list10);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 34:
                    List list11 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent11 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size2 = list11.size();
                    if (size2 != 0) {
                        computeSizeUInt64ListNoTag = SchemaUtil.computeSizeSInt64ListNoTag(list11);
                        computeTagSize = CodedOutputStream.computeTagSize(numberAt);
                        computeDoubleSize$ar$ds = computeSizeUInt64ListNoTag + (size2 * computeTagSize);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    }
                    computeDoubleSize$ar$ds = 0;
                    i11 += computeDoubleSize$ar$ds;
                case 35:
                    size = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 36:
                    size = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 37:
                    size = SchemaUtil.computeSizeInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 38:
                    size = SchemaUtil.computeSizeUInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 39:
                    size = SchemaUtil.computeSizeInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 40:
                    size = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 41:
                    size = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 42:
                    List list12 = (List) unsafe.getObject(obj, offset);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent12 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    size = list12.size();
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 43:
                    size = SchemaUtil.computeSizeUInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 44:
                    size = SchemaUtil.computeSizeEnumListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 45:
                    size = SchemaUtil.computeSizeFixed32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 46:
                    size = SchemaUtil.computeSizeFixed64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 47:
                    size = SchemaUtil.computeSizeSInt32ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 48:
                    size = SchemaUtil.computeSizeSInt64ListNoTag((List) unsafe.getObject(obj, offset));
                    if (size > 0) {
                        computeTagSize4 = CodedOutputStream.computeTagSize(numberAt);
                        computeUInt32SizeNoTag = CodedOutputStream.computeUInt32SizeNoTag(size);
                        computeSizeInt64ListNoTag = computeTagSize4 + computeUInt32SizeNoTag;
                        i4 = computeSizeInt64ListNoTag + size;
                        i11 += i4;
                        break;
                    } else {
                        break;
                    }
                case 49:
                    List list13 = (List) unsafe.getObject(obj, offset);
                    Schema messageFieldSchema2 = getMessageFieldSchema(i10);
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent13 = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    int size7 = list13.size();
                    if (size7 == 0) {
                        i5 = 0;
                    } else {
                        i5 = 0;
                        for (int i19 = 0; i19 < size7; i19++) {
                            i5 += CodedOutputStream.computeGroupSize(numberAt, (MessageLite) list13.get(i19), messageFieldSchema2);
                        }
                    }
                    i11 += i5;
                    break;
                case 50:
                    computeDoubleSize$ar$ds = OnDeviceDocumentScannerStartLogEvent.getSerializedSize$ar$ds(numberAt, unsafe.getObject(obj, offset), getMapFieldDefaultEntry(i10));
                    i11 += computeDoubleSize$ar$ds;
                    break;
                case 51:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeDoubleSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFloatSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeInt64Size(numberAt, oneofLongAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeUInt64Size(numberAt, oneofLongAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeInt32Size(numberAt, oneofIntAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFixed64Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeFixed32Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeBoolSize$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        Object object2 = unsafe.getObject(obj, offset);
                        if (object2 instanceof ByteString) {
                            computeDoubleSize$ar$ds = CodedOutputStream.computeBytesSize(numberAt, (ByteString) object2);
                        } else {
                            computeDoubleSize$ar$ds = CodedOutputStream.computeStringSize(numberAt, (String) object2);
                        }
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = SchemaUtil.computeSizeMessage(numberAt, unsafe.getObject(obj, offset), getMessageFieldSchema(i10));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeBytesSize(numberAt, (ByteString) unsafe.getObject(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeUInt32Size(numberAt, oneofIntAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeEnumSize(numberAt, oneofIntAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSFixed32Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSFixed64Size$ar$ds(numberAt);
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSInt32Size(numberAt, oneofIntAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeSInt64Size(numberAt, oneofLongAt(obj, offset));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(obj, numberAt, i10)) {
                        computeDoubleSize$ar$ds = CodedOutputStream.computeGroupSize(numberAt, (MessageLite) unsafe.getObject(obj, offset), getMessageFieldSchema(i10));
                        i11 += computeDoubleSize$ar$ds;
                        break;
                    } else {
                        break;
                    }
            }
            i10 += 3;
            i8 = i;
            i9 = i2;
            i7 = 1048575;
        }
        serializedSize = ((UnknownFieldSetLite) this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj)).getSerializedSize();
        int i20 = i11 + serializedSize;
        if (this.hasExtensions) {
            FieldSet extensions$ar$ds = OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj);
            int i21 = 0;
            for (int i22 = 0; i22 < extensions$ar$ds.fields.getNumArrayEntries(); i22++) {
                Map.Entry arrayEntryAt = extensions$ar$ds.fields.getArrayEntryAt(i22);
                i21 += FieldSet.computeFieldSize$ar$class_merging((GeneratedMessageLite.ExtensionDescriptor) arrayEntryAt.getKey(), arrayEntryAt.getValue());
            }
            for (Map.Entry entry : extensions$ar$ds.fields.getOverflowEntries()) {
                i21 += FieldSet.computeFieldSize$ar$class_merging((GeneratedMessageLite.ExtensionDescriptor) entry.getKey(), entry.getValue());
            }
            return i20 + i21;
        }
        return i20;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x0019. Please report as an issue. */
    @Override // com.google.protobuf.Schema
    public final int hashCode(Object obj) {
        int i;
        int hashLong;
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.buffer.length; i4 += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i4);
            int numberAt = numberAt(i4);
            long offset = offset(typeAndOffsetAt);
            int i5 = 37;
            switch (type(typeAndOffsetAt)) {
                case 0:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(Double.doubleToLongBits(UnsafeUtil.getDouble(obj, offset)));
                    i3 = i + hashLong;
                    break;
                case 1:
                    i = i3 * 53;
                    hashLong = Float.floatToIntBits(UnsafeUtil.getFloat(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 2:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 3:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 4:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 5:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 6:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 7:
                    i = i3 * 53;
                    hashLong = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(UnsafeUtil.getBoolean(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 8:
                    i = i3 * 53;
                    hashLong = ((String) UnsafeUtil.getObject(obj, offset)).hashCode();
                    i3 = i + hashLong;
                    break;
                case 9:
                    i2 = i3 * 53;
                    Object object = UnsafeUtil.getObject(obj, offset);
                    if (object != null) {
                        i5 = object.hashCode();
                    }
                    i3 = i2 + i5;
                    break;
                case 10:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                    i3 = i + hashLong;
                    break;
                case 11:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 12:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 13:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 14:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 15:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getInt(obj, offset);
                    i3 = i + hashLong;
                    break;
                case 16:
                    i = i3 * 53;
                    hashLong = Internal.hashLong(UnsafeUtil.getLong(obj, offset));
                    i3 = i + hashLong;
                    break;
                case 17:
                    i2 = i3 * 53;
                    Object object2 = UnsafeUtil.getObject(obj, offset);
                    if (object2 != null) {
                        i5 = object2.hashCode();
                    }
                    i3 = i2 + i5;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                    i3 = i + hashLong;
                    break;
                case 50:
                    i = i3 * 53;
                    hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                    i3 = i + hashLong;
                    break;
                case 51:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(Double.doubleToLongBits(oneofDoubleAt(obj, offset)));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Float.floatToIntBits(oneofFloatAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(oneofBooleanAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = ((String) UnsafeUtil.getObject(obj, offset)).hashCode();
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = oneofIntAt(obj, offset);
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = Internal.hashLong(oneofLongAt(obj, offset));
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (isOneofPresent(obj, numberAt, i4)) {
                        i = i3 * 53;
                        hashLong = UnsafeUtil.getObject(obj, offset).hashCode();
                        i3 = i + hashLong;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i3 * 53) + this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj).hashCode();
        if (this.hasExtensions) {
            return (hashCode * 53) + OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(Object obj) {
        int i;
        int i2;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1048575;
        while (i4 < this.checkInitializedCount) {
            int i6 = this.intArray[i4];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i5) {
                if (i8 != 1048575) {
                    i3 = UNSAFE.getInt(obj, i8);
                }
                i2 = i3;
                i = i8;
            } else {
                i = i5;
                i2 = i3;
            }
            if ((268435456 & typeAndOffsetAt) != 0 && !isFieldPresent(obj, i6, i, i2, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type != 9 && type != 17) {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(obj, numberAt, i6) && !isInitialized(obj, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type != 50) {
                            continue;
                        } else {
                            Map forMapData$ar$ds = OnDeviceDocumentScannerStartLogEvent.forMapData$ar$ds(UnsafeUtil.getObject(obj, offset(typeAndOffsetAt)));
                            if (forMapData$ar$ds.isEmpty()) {
                                continue;
                            } else {
                                if (((WireFormat.FieldType) OnDeviceDocumentScannerStartLogEvent.forMapMetadata$ar$ds(getMapFieldDefaultEntry(i6)).MapEntryLite$Metadata$ar$valueType).javaType == WireFormat.JavaType.MESSAGE) {
                                    Schema schema = null;
                                    for (Object obj2 : forMapData$ar$ds.values()) {
                                        if (schema == null) {
                                            schema = Protobuf.INSTANCE.schemaFor((Class) obj2.getClass());
                                        }
                                        if (!schema.isInitialized(obj2)) {
                                            return false;
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
                List list = (List) UnsafeUtil.getObject(obj, offset(typeAndOffsetAt));
                if (list.isEmpty()) {
                    continue;
                } else {
                    Schema messageFieldSchema = getMessageFieldSchema(i6);
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        if (!messageFieldSchema.isInitialized(list.get(i10))) {
                            return false;
                        }
                    }
                }
            } else if (isFieldPresent(obj, i6, i, i2, i9) && !isInitialized(obj, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                return false;
            }
            i4++;
            i5 = i;
            i3 = i2;
        }
        return !this.hasExtensions || OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public final void makeImmutable(Object obj) {
        if (isMutable(obj)) {
            if (obj instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int[] iArr = this.buffer;
            for (int i = 0; i < iArr.length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    if (type != 60 && type != 68) {
                        switch (type) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                OnDeviceDocumentScannerFinishLogEvent.getProtobufList(obj, offset).makeImmutable();
                                break;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(obj, offset);
                                if (object != null) {
                                    ((MapFieldLite) object).makeImmutable();
                                    unsafe.putObject(obj, offset, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    } else if (isOneofPresent(obj, numberAt(i), i)) {
                        getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(obj, offset));
                    }
                }
                if (isFieldPresent(obj, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(obj, offset));
                }
            }
            this.unknownFieldSchema$ar$class_merging$ar$class_merging.makeImmutable(obj);
            if (this.hasExtensions) {
                this.extensionSchema$ar$class_merging$ar$class_merging.makeImmutable(obj);
            }
        }
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, Object obj2) {
        checkMutable(obj);
        obj2.getClass();
        for (int i = 0; i < this.buffer.length; i += 3) {
            int typeAndOffsetAt = typeAndOffsetAt(i);
            long offset = offset(typeAndOffsetAt);
            int numberAt = numberAt(i);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putDouble(obj, offset, UnsafeUtil.getDouble(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putFloat(obj, offset, UnsafeUtil.getFloat(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putBoolean(obj, offset, UnsafeUtil.getBoolean(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    mergeMessage(obj, obj2, i);
                    break;
                case 10:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (isFieldPresent(obj2, i)) {
                        UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                        setFieldPresent(obj, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    mergeMessage(obj, obj2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    OnDeviceDocumentScannerFinishLogEvent.mergeListsAt$ar$ds(obj, obj2, offset);
                    break;
                case 50:
                    OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
                    UnsafeUtil.putObject(obj, offset, OnDeviceDocumentScannerStartLogEvent.mergeFrom$ar$ds(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset)));
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (isOneofPresent(obj2, numberAt, i)) {
                        UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                        setOneofPresent(obj, numberAt, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    mergeOneofMessage(obj, obj2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (isOneofPresent(obj2, numberAt, i)) {
                        UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                        setOneofPresent(obj, numberAt, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    mergeOneofMessage(obj, obj2, i);
                    break;
            }
        }
        r0.setToMessage(obj, OnDeviceExplicitContentCreateLogEvent.merge$ar$ds(r0.getFromMessage(obj), this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj2)));
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions$ar$class_merging$ar$class_merging(this.extensionSchema$ar$class_merging$ar$class_merging, obj, obj2);
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:13:0x00a8. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02ab A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02a6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0768  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0742 A[Catch: all -> 0x0739, TRY_ENTER, TryCatch #9 {all -> 0x0739, blocks: (B:18:0x0718, B:39:0x0742, B:40:0x0747), top: B:17:0x0718 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x074d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x077b A[LOOP:3: B:50:0x0777->B:52:0x077b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x078f  */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(java.lang.Object r17, io.grpc.okhttp.internal.framed.Settings r18, com.google.protobuf.ExtensionRegistryLite r19) {
        /*
            Method dump skipped, instructions count: 2082
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(java.lang.Object, io.grpc.okhttp.internal.framed.Settings, com.google.protobuf.ExtensionRegistryLite):void");
    }

    @Override // com.google.protobuf.Schema
    public final Object newInstance() {
        Object newMutableInstance;
        newMutableInstance = ((GeneratedMessageLite) this.defaultInstance).newMutableInstance();
        return newMutableInstance;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0eeb, code lost:
    
        if (r5 == r0) goto L1196;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0eed, code lost:
    
        r35.putInt(r6, r5, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0ef3, code lost:
    
        r7 = r8.checkInitializedCount;
        r3 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0ef9, code lost:
    
        if (r7 >= r8.repeatedFieldOffsetStart) goto L1296;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0efb, code lost:
    
        r3 = filterMapUnknownEnumValues$ar$class_merging$ar$class_merging(r40, r8.intArray[r7], r3, r8.unknownFieldSchema$ar$class_merging$ar$class_merging, r40);
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0f0e, code lost:
    
        if (r3 == null) goto L1202;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0f10, code lost:
    
        r8.unknownFieldSchema$ar$class_merging$ar$class_merging.setBuilderToMessage(r6, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x0f15, code lost:
    
        if (r10 != 0) goto L1207;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0f17, code lost:
    
        if (r11 != r9) goto L1205;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0f21, code lost:
    
        throw new com.google.protobuf.InvalidProtocolBufferException(r26);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0f28, code lost:
    
        return r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0f22, code lost:
    
        r1 = r26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0f24, code lost:
    
        if (r11 > r9) goto L1211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0f26, code lost:
    
        if (r13 != r10) goto L1211;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0f2e, code lost:
    
        throw new com.google.protobuf.InvalidProtocolBufferException(r1);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:120:0x0d02. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:31:0x033c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:446:0x08f8. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:545:0x00a9. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:88:0x0be8. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0e97  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0e9d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0793 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:451:0x0b66  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x0b77  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x07a5 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v128, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int parseMessage(java.lang.Object r40, byte[] r41, int r42, int r43, int r44, com.google.protobuf.ArrayDecoders$Registers r45) {
        /*
            Method dump skipped, instructions count: 4112
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.parseMessage(java.lang.Object, byte[], int, int, int, com.google.protobuf.ArrayDecoders$Registers):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:32:0x008e. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0363  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0480  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x04a1  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04e9  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x050a  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x052b  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x056d  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0620  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01f5  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0215  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002f  */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(java.lang.Object r22, com.google.mlkit.common.sdkinternal.ExecutorSelector r23) {
        /*
            Method dump skipped, instructions count: 1736
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSchema.writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(java.lang.Object, com.google.mlkit.common.sdkinternal.ExecutorSelector):void");
    }

    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private final boolean isFieldPresent(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(obj, i);
        }
        return (i3 & i4) != 0;
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, byte[] bArr, int i, int i2, ArrayDecoders$Registers arrayDecoders$Registers) {
        parseMessage(obj, bArr, i, i2, 0, arrayDecoders$Registers);
    }
}
