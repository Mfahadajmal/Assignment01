package com.google.mlkit.logging.schema;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MapEntryLite$Metadata;
import com.google.protobuf.MapFieldLite;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceDocumentScannerStartLogEvent {
    public static final Map forMapData$ar$ds(Object obj) {
        return (MapFieldLite) obj;
    }

    public static final MapEntryLite$Metadata forMapMetadata$ar$ds(Object obj) {
        return (MapEntryLite$Metadata) ((ExecutorSelector) obj).ExecutorSelector$ar$defaultExecutorProvider;
    }

    public static final Map forMutableMapData$ar$ds(Object obj) {
        return (MapFieldLite) obj;
    }

    public static final int getSerializedSize$ar$ds(int i, Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        ExecutorSelector executorSelector = (ExecutorSelector) obj2;
        int i2 = 0;
        if (!mapFieldLite.isEmpty()) {
            for (Map.Entry entry : mapFieldLite.entrySet()) {
                i2 += CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeLengthDelimitedFieldSize(ExecutorSelector.computeSerializedSize((MapEntryLite$Metadata) executorSelector.ExecutorSelector$ar$defaultExecutorProvider, entry.getKey(), entry.getValue()));
            }
        }
        return i2;
    }

    public static final boolean isImmutable$ar$ds(Object obj) {
        if (!((MapFieldLite) obj).isMutable) {
            return true;
        }
        return false;
    }

    public static final Object mergeFrom$ar$ds(Object obj, Object obj2) {
        MapFieldLite mapFieldLite = (MapFieldLite) obj;
        MapFieldLite mapFieldLite2 = (MapFieldLite) obj2;
        if (!mapFieldLite2.isEmpty()) {
            if (!mapFieldLite.isMutable) {
                mapFieldLite = mapFieldLite.mutableCopy();
            }
            mapFieldLite.ensureMutable();
            if (!mapFieldLite2.isEmpty()) {
                mapFieldLite.putAll(mapFieldLite2);
            }
        }
        return mapFieldLite;
    }
}
