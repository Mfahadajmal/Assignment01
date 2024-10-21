package com.google.protobuf;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.LazyField;
import com.google.protobuf.WireFormat;
import io.grpc.okhttp.internal.framed.Settings;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageSetSchema implements Schema {
    private final MessageLite defaultInstance;
    private final OnDeviceDocumentScannerFinishLogEvent extensionSchema$ar$class_merging$ar$class_merging;
    private final boolean hasExtensions;
    private final OnDeviceExplicitContentCreateLogEvent unknownFieldSchema$ar$class_merging$ar$class_merging;

    public MessageSetSchema(OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent, OnDeviceDocumentScannerFinishLogEvent onDeviceDocumentScannerFinishLogEvent, MessageLite messageLite) {
        this.unknownFieldSchema$ar$class_merging$ar$class_merging = onDeviceExplicitContentCreateLogEvent;
        this.hasExtensions = OnDeviceDocumentScannerFinishLogEvent.hasExtensions$ar$ds(messageLite);
        this.extensionSchema$ar$class_merging$ar$class_merging = onDeviceDocumentScannerFinishLogEvent;
        this.defaultInstance = messageLite;
    }

    @Override // com.google.protobuf.Schema
    public final boolean equals(Object obj, Object obj2) {
        OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent = this.unknownFieldSchema$ar$class_merging$ar$class_merging;
        if (!onDeviceExplicitContentCreateLogEvent.getFromMessage(obj).equals(onDeviceExplicitContentCreateLogEvent.getFromMessage(obj2))) {
            return false;
        }
        if (this.hasExtensions) {
            return OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).equals(OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj2));
        }
        return true;
    }

    @Override // com.google.protobuf.Schema
    public final int getSerializedSize(Object obj) {
        int serializedSizeAsMessageSet$ar$ds = OnDeviceExplicitContentCreateLogEvent.getSerializedSizeAsMessageSet$ar$ds(this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj));
        if (this.hasExtensions) {
            FieldSet extensions$ar$ds = OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj);
            int i = 0;
            for (int i2 = 0; i2 < extensions$ar$ds.fields.getNumArrayEntries(); i2++) {
                i += extensions$ar$ds.getMessageSetSerializedSize(extensions$ar$ds.fields.getArrayEntryAt(i2));
            }
            Iterator it = extensions$ar$ds.fields.getOverflowEntries().iterator();
            while (it.hasNext()) {
                i += extensions$ar$ds.getMessageSetSerializedSize((Map.Entry) it.next());
            }
            return serializedSizeAsMessageSet$ar$ds + i;
        }
        return serializedSizeAsMessageSet$ar$ds;
    }

    @Override // com.google.protobuf.Schema
    public final int hashCode(Object obj) {
        int hashCode = this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj).hashCode();
        if (this.hasExtensions) {
            return (hashCode * 53) + OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).hashCode();
        }
        return hashCode;
    }

    @Override // com.google.protobuf.Schema
    public final boolean isInitialized(Object obj) {
        return OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).isInitialized();
    }

    @Override // com.google.protobuf.Schema
    public final void makeImmutable(Object obj) {
        this.unknownFieldSchema$ar$class_merging$ar$class_merging.makeImmutable(obj);
        this.extensionSchema$ar$class_merging$ar$class_merging.makeImmutable(obj);
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom(Object obj, Object obj2) {
        SchemaUtil.mergeUnknownFields$ar$class_merging$ar$class_merging(this.unknownFieldSchema$ar$class_merging$ar$class_merging, obj, obj2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions$ar$class_merging$ar$class_merging(this.extensionSchema$ar$class_merging$ar$class_merging, obj, obj2);
        }
    }

    @Override // com.google.protobuf.Schema
    public final void mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(Object obj, Settings settings, ExtensionRegistryLite extensionRegistryLite) {
        FieldSet ensureExtensionsAreMutable;
        boolean skipField;
        Object findLiteExtensionByNumber;
        OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent = this.unknownFieldSchema$ar$class_merging$ar$class_merging;
        Object builderFromMessage = onDeviceExplicitContentCreateLogEvent.getBuilderFromMessage(obj);
        ensureExtensionsAreMutable = ((GeneratedMessageLite.ExtendableMessage) obj).ensureExtensionsAreMutable();
        while (settings.getFieldNumber() != Integer.MAX_VALUE) {
            try {
                int i = settings.set;
                if (i != WireFormat.MESSAGE_SET_ITEM_TAG) {
                    if (WireFormat.getTagWireType(i) == 2) {
                        findLiteExtensionByNumber = extensionRegistryLite.findLiteExtensionByNumber(this.defaultInstance, WireFormat.getTagFieldNumber(i));
                        if (findLiteExtensionByNumber != null) {
                            OnDeviceDocumentScannerFinishLogEvent.parseLengthPrefixedMessageSetItem$ar$class_merging$ar$ds$ar$class_merging(settings, findLiteExtensionByNumber, extensionRegistryLite, ensureExtensionsAreMutable);
                        } else {
                            skipField = onDeviceExplicitContentCreateLogEvent.mergeOneFieldFrom$ar$class_merging$ar$class_merging(builderFromMessage, settings);
                        }
                    } else {
                        skipField = settings.skipField();
                    }
                    if (!skipField) {
                        break;
                    }
                } else {
                    Object obj2 = null;
                    int i2 = 0;
                    ByteString byteString = null;
                    while (settings.getFieldNumber() != Integer.MAX_VALUE) {
                        int i3 = settings.set;
                        if (i3 == WireFormat.MESSAGE_SET_TYPE_ID_TAG) {
                            i2 = settings.readUInt32();
                            obj2 = extensionRegistryLite.findLiteExtensionByNumber(this.defaultInstance, i2);
                        } else if (i3 == WireFormat.MESSAGE_SET_MESSAGE_TAG) {
                            if (obj2 != null) {
                                OnDeviceDocumentScannerFinishLogEvent.parseLengthPrefixedMessageSetItem$ar$class_merging$ar$ds$ar$class_merging(settings, obj2, extensionRegistryLite, ensureExtensionsAreMutable);
                            } else {
                                byteString = settings.readBytes();
                            }
                        } else if (!settings.skipField()) {
                            break;
                        }
                    }
                    if (settings.set == WireFormat.MESSAGE_SET_ITEM_END_TAG) {
                        if (byteString != null) {
                            if (obj2 != null) {
                                OnDeviceDocumentScannerFinishLogEvent.parseMessageSetItem$ar$ds(byteString, obj2, extensionRegistryLite, ensureExtensionsAreMutable);
                            } else {
                                OnDeviceExplicitContentCreateLogEvent.addLengthDelimited$ar$ds(builderFromMessage, i2, byteString);
                            }
                        }
                    } else {
                        throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
                    }
                }
            } finally {
                onDeviceExplicitContentCreateLogEvent.setBuilderToMessage(obj, builderFromMessage);
            }
        }
    }

    @Override // com.google.protobuf.Schema
    public final Object newInstance() {
        MessageLite messageLite = this.defaultInstance;
        if (messageLite instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) messageLite).newMutableInstance();
        }
        return messageLite.newBuilderForType().buildPartial();
    }

    @Override // com.google.protobuf.Schema
    public final void writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(Object obj, ExecutorSelector executorSelector) {
        Iterator it = OnDeviceDocumentScannerFinishLogEvent.getExtensions$ar$ds(obj).iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            GeneratedMessageLite.ExtensionDescriptor extensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor) entry.getKey();
            if (extensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extensionDescriptor.isRepeated() && !extensionDescriptor.isPacked()) {
                if (entry instanceof LazyField.LazyEntry) {
                    executorSelector.writeMessageSetItem(extensionDescriptor.getNumber(), ((LazyField) ((LazyField.LazyEntry) entry).entry.getValue()).toByteString());
                } else {
                    executorSelector.writeMessageSetItem(extensionDescriptor.getNumber(), entry.getValue());
                }
            } else {
                throw new IllegalStateException("Found invalid MessageSet item.");
            }
        }
        OnDeviceExplicitContentCreateLogEvent.writeAsMessageSetTo$ar$class_merging$d1b76bae_0$ar$ds$ar$class_merging$ar$class_merging(this.unknownFieldSchema$ar$class_merging$ar$class_merging.getFromMessage(obj), executorSelector);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00be A[EDGE_INSN: B:24:0x00be->B:25:0x00be BREAK  A[LOOP:1: B:10:0x0066->B:18:0x0066], SYNTHETIC] */
    @Override // com.google.protobuf.Schema
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mergeFrom(java.lang.Object r11, byte[] r12, int r13, int r14, com.google.protobuf.ArrayDecoders$Registers r15) {
        /*
            r10 = this;
            r0 = r11
            com.google.protobuf.GeneratedMessageLite r0 = (com.google.protobuf.GeneratedMessageLite) r0
            com.google.protobuf.UnknownFieldSetLite r1 = r0.unknownFields
            com.google.protobuf.UnknownFieldSetLite r2 = com.google.protobuf.UnknownFieldSetLite.DEFAULT_INSTANCE
            if (r1 != r2) goto L10
            com.google.protobuf.UnknownFieldSetLite r1 = new com.google.protobuf.UnknownFieldSetLite
            r1.<init>()
            r0.unknownFields = r1
        L10:
            com.google.protobuf.GeneratedMessageLite$ExtendableMessage r11 = (com.google.protobuf.GeneratedMessageLite.ExtendableMessage) r11
            com.google.protobuf.FieldSet r11 = r11.ensureExtensionsAreMutable()
            r0 = 0
            r2 = r0
        L18:
            if (r13 >= r14) goto Lca
            int r4 = com.google.protobuf.ExtensionLite.decodeVarint32(r12, r13, r15)
            int r13 = r15.int1
            int r3 = com.google.protobuf.WireFormat.MESSAGE_SET_ITEM_TAG
            r5 = 2
            if (r13 == r3) goto L64
            int r3 = com.google.protobuf.WireFormat.getTagWireType(r13)
            if (r3 != r5) goto L5f
            com.google.protobuf.ExtensionRegistryLite r2 = r15.extensionRegistry
            com.google.protobuf.MessageLite r3 = r10.defaultInstance
            int r5 = com.google.protobuf.WireFormat.getTagFieldNumber(r13)
            java.lang.Object r8 = com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent.findExtensionByNumber$ar$ds(r2, r3, r5)
            if (r8 == 0) goto L54
            com.google.protobuf.Protobuf r13 = com.google.protobuf.Protobuf.INSTANCE
            r2 = r8
            com.google.protobuf.GeneratedMessageLite$GeneratedExtension r2 = (com.google.protobuf.GeneratedMessageLite.GeneratedExtension) r2
            com.google.protobuf.MessageLite r3 = r2.messageDefaultInstance
            java.lang.Class r3 = r3.getClass()
            com.google.protobuf.Schema r13 = r13.schemaFor(r3)
            int r13 = com.google.protobuf.ExtensionLite.decodeMessageField(r13, r12, r4, r14, r15)
            java.lang.Object r3 = r15.object1
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r2 = r2.descriptor
            r11.setField$ar$class_merging(r2, r3)
            goto L5d
        L54:
            r2 = r13
            r3 = r12
            r5 = r14
            r6 = r1
            r7 = r15
            int r13 = com.google.protobuf.ExtensionLite.decodeUnknownField(r2, r3, r4, r5, r6, r7)
        L5d:
            r2 = r8
            goto L18
        L5f:
            int r13 = com.google.protobuf.ExtensionLite.skipField(r13, r12, r4, r14, r15)
            goto L18
        L64:
            r13 = 0
            r3 = r0
        L66:
            if (r4 >= r14) goto Lbe
            int r4 = com.google.protobuf.ExtensionLite.decodeVarint32(r12, r4, r15)
            int r6 = r15.int1
            int r7 = com.google.protobuf.WireFormat.getTagFieldNumber(r6)
            int r8 = com.google.protobuf.WireFormat.getTagWireType(r6)
            if (r7 == r5) goto La4
            r9 = 3
            if (r7 == r9) goto L7c
            goto Lb5
        L7c:
            if (r2 == 0) goto L99
            com.google.protobuf.Protobuf r6 = com.google.protobuf.Protobuf.INSTANCE
            r7 = r2
            com.google.protobuf.GeneratedMessageLite$GeneratedExtension r7 = (com.google.protobuf.GeneratedMessageLite.GeneratedExtension) r7
            com.google.protobuf.MessageLite r8 = r7.messageDefaultInstance
            java.lang.Class r8 = r8.getClass()
            com.google.protobuf.Schema r6 = r6.schemaFor(r8)
            int r4 = com.google.protobuf.ExtensionLite.decodeMessageField(r6, r12, r4, r14, r15)
            java.lang.Object r6 = r15.object1
            com.google.protobuf.GeneratedMessageLite$ExtensionDescriptor r7 = r7.descriptor
            r11.setField$ar$class_merging(r7, r6)
            goto L66
        L99:
            if (r8 != r5) goto Lb5
            int r4 = com.google.protobuf.ExtensionLite.decodeBytes(r12, r4, r15)
            java.lang.Object r3 = r15.object1
            com.google.protobuf.ByteString r3 = (com.google.protobuf.ByteString) r3
            goto L66
        La4:
            if (r8 != 0) goto Lb5
            int r4 = com.google.protobuf.ExtensionLite.decodeVarint32(r12, r4, r15)
            int r13 = r15.int1
            com.google.protobuf.ExtensionRegistryLite r2 = r15.extensionRegistry
            com.google.protobuf.MessageLite r6 = r10.defaultInstance
            java.lang.Object r2 = com.google.mlkit.logging.schema.OnDeviceDocumentScannerFinishLogEvent.findExtensionByNumber$ar$ds(r2, r6, r13)
            goto L66
        Lb5:
            int r7 = com.google.protobuf.WireFormat.MESSAGE_SET_ITEM_END_TAG
            if (r6 == r7) goto Lbe
            int r4 = com.google.protobuf.ExtensionLite.skipField(r6, r12, r4, r14, r15)
            goto L66
        Lbe:
            if (r3 == 0) goto Lc7
            int r13 = com.google.protobuf.WireFormat.makeTag(r13, r5)
            r1.storeField(r13, r3)
        Lc7:
            r13 = r4
            goto L18
        Lca:
            if (r13 != r14) goto Lcd
            return
        Lcd:
            com.google.protobuf.InvalidProtocolBufferException r11 = new com.google.protobuf.InvalidProtocolBufferException
            java.lang.String r12 = "Failed to parse the message."
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.MessageSetSchema.mergeFrom(java.lang.Object, byte[], int, int, com.google.protobuf.ArrayDecoders$Registers):void");
    }
}
