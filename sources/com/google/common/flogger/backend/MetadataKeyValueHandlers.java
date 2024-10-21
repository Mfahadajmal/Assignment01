package com.google.common.flogger.backend;

import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.backend.MetadataHandler;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetadataKeyValueHandlers {
    private static final MetadataHandler.ValueHandler EMIT_METADATA = new MetadataHandler.ValueHandler() { // from class: com.google.common.flogger.backend.MetadataKeyValueHandlers.1
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.flogger.backend.MetadataHandler.ValueHandler
        public final /* synthetic */ void handle(MetadataKey metadataKey, Object obj, Object obj2) {
            if (metadataKey.isCustom && Platform.getCurrentRecursionDepth() > 20) {
                obj2.handle(metadataKey.label, obj);
            } else {
                metadataKey.emit(obj, obj2);
            }
        }
    };
    private static final MetadataHandler.RepeatedValueHandler EMIT_REPEATED_METADATA = new MetadataHandler.RepeatedValueHandler() { // from class: com.google.common.flogger.backend.MetadataKeyValueHandlers.2
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.flogger.backend.MetadataHandler.RepeatedValueHandler
        public final /* synthetic */ void handle(MetadataKey metadataKey, Iterator it, Object obj) {
            if (metadataKey.canRepeat) {
                if (metadataKey.isCustom && Platform.getCurrentRecursionDepth() > 20) {
                    while (it.hasNext()) {
                        obj.handle(metadataKey.label, it.next());
                    }
                    return;
                } else {
                    metadataKey.emitRepeated(it, obj);
                    return;
                }
            }
            throw new IllegalStateException("non repeating key");
        }
    };

    public static MetadataHandler getDefaultHandler(Set set) {
        MetadataHandler.Builder builder = new MetadataHandler.Builder(EMIT_METADATA);
        builder.defaultRepeatedHandler = EMIT_REPEATED_METADATA;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            MetadataKey metadataKey = (MetadataKey) it.next();
            metadataKey.getClass();
            if (metadataKey.canRepeat) {
                MetadataHandler.RepeatedValueHandler repeatedValueHandler = MetadataHandler.Builder.IGNORE_REPEATED_VALUE;
                ContextDataProvider.checkArgument(true, "key must be repeating");
                builder.singleValueHandlers.remove(metadataKey);
                builder.repeatedValueHandlers.put(metadataKey, repeatedValueHandler);
            } else {
                Map map = builder.repeatedValueHandlers;
                MetadataHandler.ValueHandler valueHandler = MetadataHandler.Builder.IGNORE_VALUE;
                map.remove(metadataKey);
                builder.singleValueHandlers.put(metadataKey, valueHandler);
            }
        }
        return new MetadataHandler.MapBasedhandler(builder);
    }
}
