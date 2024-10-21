package com.google.protobuf;

import com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Protobuf {
    public static final Protobuf INSTANCE = new Protobuf();
    private final ConcurrentMap schemaCache = new ConcurrentHashMap();
    private final ManifestSchemaFactory schemaFactory$ar$class_merging = new ManifestSchemaFactory();

    private Protobuf() {
    }

    public final Schema schemaFor(Class cls) {
        Internal.checkNotNull$ar$ds$40668187_0(cls, "messageType");
        Schema schema = (Schema) this.schemaCache.get(cls);
        if (schema == null) {
            ManifestSchemaFactory manifestSchemaFactory = this.schemaFactory$ar$class_merging;
            OnDeviceExplicitContentCreateLogEvent onDeviceExplicitContentCreateLogEvent = SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging;
            GeneratedMessageLite.class.isAssignableFrom(cls);
            MessageInfo messageInfoFor = manifestSchemaFactory.messageInfoFactory.messageInfoFor(cls);
            if (messageInfoFor.isMessageSetWireFormat()) {
                schema = new MessageSetSchema(SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging, ExtensionSchemas.LITE_SCHEMA$ar$class_merging$e18e2d68_0$ar$class_merging, messageInfoFor.getDefaultInstance());
            } else {
                schema = MessageSchema.newSchema$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging(messageInfoFor, NewInstanceSchemas.LITE_SCHEMA$ar$class_merging$32037ad0_0$ar$class_merging, ListFieldSchemas.LITE_SCHEMA$ar$class_merging$ar$class_merging, SchemaUtil.UNKNOWN_FIELD_SET_LITE_SCHEMA$ar$class_merging$ar$class_merging, ManifestSchemaFactory.allowExtensions(messageInfoFor) ? ExtensionSchemas.LITE_SCHEMA$ar$class_merging$e18e2d68_0$ar$class_merging : null, MapFieldSchemas.LITE_SCHEMA$ar$class_merging$ad12112_0$ar$class_merging$ar$class_merging);
            }
            Internal.checkNotNull$ar$ds$40668187_0(cls, "messageType");
            Schema schema2 = (Schema) this.schemaCache.putIfAbsent(cls, schema);
            if (schema2 != null) {
                return schema2;
            }
        }
        return schema;
    }

    public final Schema schemaFor(Object obj) {
        return schemaFor((Class) obj.getClass());
    }
}
