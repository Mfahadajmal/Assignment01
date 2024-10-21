package com.google.mlkit.logging.schema;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FieldSet;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import com.google.protobuf.SchemaUtil;
import com.google.protobuf.UnsafeUtil;
import com.google.protobuf.WireFormat;
import io.grpc.okhttp.internal.framed.Settings;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceDocumentScannerFinishLogEvent {
    public OnDeviceDocumentScannerFinishLogEvent() {
    }

    public static FieldSet getExtensions$ar$ds(Object obj) {
        return ((GeneratedMessageLite.ExtendableMessage) obj).extensions;
    }

    static Internal.ProtobufList getProtobufList(Object obj, long j) {
        return (Internal.ProtobufList) UnsafeUtil.getObject(obj, j);
    }

    public static boolean hasExtensions$ar$ds(MessageLite messageLite) {
        return messageLite instanceof GeneratedMessageLite.ExtendableMessage;
    }

    public static final void mergeListsAt$ar$ds(Object obj, Object obj2, long j) {
        Internal.ProtobufList protobufList = getProtobufList(obj, j);
        Internal.ProtobufList protobufList2 = getProtobufList(obj2, j);
        int size = protobufList.size();
        int size2 = protobufList2.size();
        if (size > 0 && size2 > 0) {
            if (!protobufList.isModifiable()) {
                protobufList = protobufList.mutableCopyWithCapacity(size2 + size);
            }
            protobufList.addAll(protobufList2);
        }
        if (size > 0) {
            protobufList2 = protobufList;
        }
        UnsafeUtil.putObject(obj, j, protobufList2);
    }

    public static final List mutableListAt$ar$ds(Object obj, long j) {
        int i;
        Internal.ProtobufList protobufList = getProtobufList(obj, j);
        if (!protobufList.isModifiable()) {
            int size = protobufList.size();
            if (size == 0) {
                i = 10;
            } else {
                i = size + size;
            }
            Internal.ProtobufList mutableCopyWithCapacity = protobufList.mutableCopyWithCapacity(i);
            UnsafeUtil.putObject(obj, j, mutableCopyWithCapacity);
            return mutableCopyWithCapacity;
        }
        return protobufList;
    }

    public static Object parseExtension$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(Object obj, Settings settings, Object obj2, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet, Object obj3, OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent) {
        Object valueOf;
        Object field$ar$class_merging;
        ArrayList arrayList;
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj2;
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = generatedExtension.descriptor;
        int number = generatedExtension.getNumber();
        if (extensionDescriptor.isRepeated && extensionDescriptor.isPacked) {
            WireFormat.FieldType fieldType = WireFormat.FieldType.DOUBLE;
            switch (generatedExtension.getLiteType()) {
                case DOUBLE:
                    arrayList = new ArrayList();
                    settings.readDoubleList(arrayList);
                    break;
                case FLOAT:
                    arrayList = new ArrayList();
                    settings.readFloatList(arrayList);
                    break;
                case INT64:
                    arrayList = new ArrayList();
                    settings.readInt64List(arrayList);
                    break;
                case UINT64:
                    arrayList = new ArrayList();
                    settings.readUInt64List(arrayList);
                    break;
                case INT32:
                    arrayList = new ArrayList();
                    settings.readInt32List(arrayList);
                    break;
                case FIXED64:
                    arrayList = new ArrayList();
                    settings.readFixed64List(arrayList);
                    break;
                case FIXED32:
                    arrayList = new ArrayList();
                    settings.readFixed32List(arrayList);
                    break;
                case BOOL:
                    arrayList = new ArrayList();
                    settings.readBoolList(arrayList);
                    break;
                case STRING:
                case GROUP:
                case MESSAGE:
                case BYTES:
                default:
                    throw new IllegalStateException("Type cannot be packed: ".concat(String.valueOf(String.valueOf(generatedExtension.descriptor.type))));
                case UINT32:
                    arrayList = new ArrayList();
                    settings.readUInt32List(arrayList);
                    break;
                case ENUM:
                    ArrayList arrayList2 = new ArrayList();
                    settings.readEnumList(arrayList2);
                    obj3 = SchemaUtil.filterUnknownEnumList$ar$class_merging$ar$class_merging(obj, number, arrayList2, generatedExtension.descriptor.enumTypeMap, obj3, onDeviceExplicitContentCreateLogEvent);
                    arrayList = arrayList2;
                    break;
                case SFIXED32:
                    arrayList = new ArrayList();
                    settings.readSFixed32List(arrayList);
                    break;
                case SFIXED64:
                    arrayList = new ArrayList();
                    settings.readSFixed64List(arrayList);
                    break;
                case SINT32:
                    arrayList = new ArrayList();
                    settings.readSInt32List(arrayList);
                    break;
                case SINT64:
                    arrayList = new ArrayList();
                    settings.readSInt64List(arrayList);
                    break;
            }
            fieldSet.setField$ar$class_merging(generatedExtension.descriptor, arrayList);
        } else {
            if (generatedExtension.getLiteType() == WireFormat.FieldType.ENUM) {
                int readInt32 = settings.readInt32();
                if (generatedExtension.descriptor.enumTypeMap.findValueByNumber$ar$ds() == null) {
                    return SchemaUtil.storeUnknownEnum$ar$class_merging$ar$class_merging(obj, number, readInt32, obj3, onDeviceExplicitContentCreateLogEvent);
                }
                valueOf = Integer.valueOf(readInt32);
            } else {
                switch (generatedExtension.getLiteType()) {
                    case DOUBLE:
                        valueOf = Double.valueOf(settings.readDouble());
                        break;
                    case FLOAT:
                        valueOf = Float.valueOf(settings.readFloat());
                        break;
                    case INT64:
                        valueOf = Long.valueOf(settings.readInt64());
                        break;
                    case UINT64:
                        valueOf = Long.valueOf(settings.readUInt64());
                        break;
                    case INT32:
                        valueOf = Integer.valueOf(settings.readInt32());
                        break;
                    case FIXED64:
                        valueOf = Long.valueOf(settings.readFixed64());
                        break;
                    case FIXED32:
                        valueOf = Integer.valueOf(settings.readFixed32());
                        break;
                    case BOOL:
                        valueOf = Boolean.valueOf(settings.readBool());
                        break;
                    case STRING:
                        valueOf = settings.readString();
                        break;
                    case GROUP:
                        if (!generatedExtension.isRepeated()) {
                            Object field$ar$class_merging2 = fieldSet.getField$ar$class_merging(generatedExtension.descriptor);
                            if (field$ar$class_merging2 instanceof GeneratedMessageLite) {
                                Schema schemaFor = Protobuf.INSTANCE.schemaFor(field$ar$class_merging2);
                                if (!((GeneratedMessageLite) field$ar$class_merging2).isMutable()) {
                                    Object newInstance = schemaFor.newInstance();
                                    schemaFor.mergeFrom(newInstance, field$ar$class_merging2);
                                    fieldSet.setField$ar$class_merging(generatedExtension.descriptor, newInstance);
                                    field$ar$class_merging2 = newInstance;
                                }
                                settings.mergeGroupField(field$ar$class_merging2, schemaFor, extensionRegistryLite);
                                return obj3;
                            }
                        }
                        Class<?> cls = generatedExtension.messageDefaultInstance.getClass();
                        settings.requireWireType(3);
                        valueOf = settings.readGroup(Protobuf.INSTANCE.schemaFor((Class) cls), extensionRegistryLite);
                        break;
                    case MESSAGE:
                        if (!generatedExtension.isRepeated()) {
                            Object field$ar$class_merging3 = fieldSet.getField$ar$class_merging(generatedExtension.descriptor);
                            if (field$ar$class_merging3 instanceof GeneratedMessageLite) {
                                Schema schemaFor2 = Protobuf.INSTANCE.schemaFor(field$ar$class_merging3);
                                if (!((GeneratedMessageLite) field$ar$class_merging3).isMutable()) {
                                    Object newInstance2 = schemaFor2.newInstance();
                                    schemaFor2.mergeFrom(newInstance2, field$ar$class_merging3);
                                    fieldSet.setField$ar$class_merging(generatedExtension.descriptor, newInstance2);
                                    field$ar$class_merging3 = newInstance2;
                                }
                                settings.mergeMessageField(field$ar$class_merging3, schemaFor2, extensionRegistryLite);
                                return obj3;
                            }
                        }
                        valueOf = settings.readMessage(generatedExtension.messageDefaultInstance.getClass(), extensionRegistryLite);
                        break;
                    case BYTES:
                        valueOf = settings.readBytes();
                        break;
                    case UINT32:
                        valueOf = Integer.valueOf(settings.readUInt32());
                        break;
                    case ENUM:
                        throw new IllegalStateException("Shouldn't reach here.");
                    case SFIXED32:
                        valueOf = Integer.valueOf(settings.readSFixed32());
                        break;
                    case SFIXED64:
                        valueOf = Long.valueOf(settings.readSFixed64());
                        break;
                    case SINT32:
                        valueOf = Integer.valueOf(settings.readSInt32());
                        break;
                    case SINT64:
                        valueOf = Long.valueOf(settings.readSInt64());
                        break;
                    default:
                        valueOf = null;
                        break;
                }
            }
            if (generatedExtension.isRepeated()) {
                fieldSet.addRepeatedField$ar$class_merging(generatedExtension.descriptor, valueOf);
            } else {
                int ordinal = generatedExtension.getLiteType().ordinal();
                if ((ordinal == 9 || ordinal == 10) && (field$ar$class_merging = fieldSet.getField$ar$class_merging(generatedExtension.descriptor)) != null) {
                    byte[] bArr = Internal.EMPTY_BYTE_ARRAY;
                    valueOf = ((MessageLite) field$ar$class_merging).toBuilder().mergeFrom((MessageLite) valueOf).buildPartial();
                }
                fieldSet.setField$ar$class_merging(generatedExtension.descriptor, valueOf);
            }
        }
        return obj3;
    }

    public static void parseLengthPrefixedMessageSetItem$ar$class_merging$ar$ds$ar$class_merging(Settings settings, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet) {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        fieldSet.setField$ar$class_merging(generatedExtension.descriptor, settings.readMessage(generatedExtension.messageDefaultInstance.getClass(), extensionRegistryLite));
    }

    public static void parseMessageSetItem$ar$ds(ByteString byteString, Object obj, ExtensionRegistryLite extensionRegistryLite, FieldSet fieldSet) {
        GeneratedMessageLite.GeneratedExtension generatedExtension = (GeneratedMessageLite.GeneratedExtension) obj;
        MessageLite.Builder newBuilderForType = generatedExtension.messageDefaultInstance.newBuilderForType();
        CodedInputStream newCodedInput = byteString.newCodedInput();
        newBuilderForType.mergeFrom(newCodedInput, extensionRegistryLite);
        fieldSet.setField$ar$class_merging(generatedExtension.descriptor, newBuilderForType.buildPartial());
        newCodedInput.checkLastTagWas(0);
    }

    public static void serializeExtension$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(ExecutorSelector executorSelector, Map.Entry entry) {
        GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
        if (extensionDescriptor.isRepeated) {
            WireFormat.FieldType fieldType = WireFormat.FieldType.DOUBLE;
            switch (extensionDescriptor.type) {
                case DOUBLE:
                    SchemaUtil.writeDoubleList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case FLOAT:
                    SchemaUtil.writeFloatList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case INT64:
                    SchemaUtil.writeInt64List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case UINT64:
                    SchemaUtil.writeUInt64List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case INT32:
                    SchemaUtil.writeInt32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case FIXED64:
                    SchemaUtil.writeFixed64List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case FIXED32:
                    SchemaUtil.writeFixed32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case BOOL:
                    SchemaUtil.writeBoolList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case STRING:
                    SchemaUtil.writeStringList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector);
                    return;
                case GROUP:
                    List list = (List) entry.getValue();
                    if (list != null && !list.isEmpty()) {
                        SchemaUtil.writeGroupList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, Protobuf.INSTANCE.schemaFor((Class) list.get(0).getClass()));
                        return;
                    }
                    return;
                case MESSAGE:
                    List list2 = (List) entry.getValue();
                    if (list2 != null && !list2.isEmpty()) {
                        SchemaUtil.writeMessageList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, Protobuf.INSTANCE.schemaFor((Class) list2.get(0).getClass()));
                        return;
                    }
                    return;
                case BYTES:
                    SchemaUtil.writeBytesList$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector);
                    return;
                case UINT32:
                    SchemaUtil.writeUInt32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case ENUM:
                    SchemaUtil.writeInt32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case SFIXED32:
                    SchemaUtil.writeSFixed32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case SFIXED64:
                    SchemaUtil.writeSFixed64List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case SINT32:
                    SchemaUtil.writeSInt32List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                case SINT64:
                    SchemaUtil.writeSInt64List$ar$class_merging$ar$class_merging$ar$class_merging(extensionDescriptor.number, (List) entry.getValue(), executorSelector, extensionDescriptor.isPacked);
                    return;
                default:
                    return;
            }
        }
        WireFormat.FieldType fieldType2 = WireFormat.FieldType.DOUBLE;
        switch (extensionDescriptor.type) {
            case DOUBLE:
                executorSelector.writeDouble(extensionDescriptor.number, ((Double) entry.getValue()).doubleValue());
                return;
            case FLOAT:
                executorSelector.writeFloat(extensionDescriptor.number, ((Float) entry.getValue()).floatValue());
                return;
            case INT64:
                executorSelector.writeInt64(extensionDescriptor.number, ((Long) entry.getValue()).longValue());
                return;
            case UINT64:
                executorSelector.writeUInt64(extensionDescriptor.number, ((Long) entry.getValue()).longValue());
                return;
            case INT32:
                executorSelector.writeInt32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case FIXED64:
                executorSelector.writeFixed64(extensionDescriptor.number, ((Long) entry.getValue()).longValue());
                return;
            case FIXED32:
                executorSelector.writeFixed32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case BOOL:
                executorSelector.writeBool(extensionDescriptor.number, ((Boolean) entry.getValue()).booleanValue());
                return;
            case STRING:
                executorSelector.writeString(extensionDescriptor.number, (String) entry.getValue());
                return;
            case GROUP:
                executorSelector.writeGroup(extensionDescriptor.number, entry.getValue(), Protobuf.INSTANCE.schemaFor((Class) entry.getValue().getClass()));
                return;
            case MESSAGE:
                executorSelector.writeMessage(extensionDescriptor.number, entry.getValue(), Protobuf.INSTANCE.schemaFor((Class) entry.getValue().getClass()));
                return;
            case BYTES:
                executorSelector.writeBytes(extensionDescriptor.number, (ByteString) entry.getValue());
                return;
            case UINT32:
                executorSelector.writeUInt32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case ENUM:
                executorSelector.writeInt32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED32:
                executorSelector.writeSFixed32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case SFIXED64:
                executorSelector.writeSFixed64(extensionDescriptor.number, ((Long) entry.getValue()).longValue());
                return;
            case SINT32:
                executorSelector.writeSInt32(extensionDescriptor.number, ((Integer) entry.getValue()).intValue());
                return;
            case SINT64:
                executorSelector.writeSInt64(extensionDescriptor.number, ((Long) entry.getValue()).longValue());
                return;
            default:
                return;
        }
    }

    public final void makeImmutable(Object obj) {
        getExtensions$ar$ds(obj).makeImmutable();
    }

    public OnDeviceDocumentScannerFinishLogEvent(byte[] bArr, byte[] bArr2) {
        this();
    }
}
