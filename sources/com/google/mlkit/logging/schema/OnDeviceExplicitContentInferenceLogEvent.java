package com.google.mlkit.logging.schema;

import android.os.Parcel;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.contrib.android.ProtoParsers$InternalDontUse;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceExplicitContentInferenceLogEvent {
    public static MessageLite get(Parcel parcel, MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        ProtoParsers$InternalDontUse protoParsers$InternalDontUse = (ProtoParsers$InternalDontUse) parcel.readTypedObject(ProtoParsers$InternalDontUse.CREATOR);
        MessageLite defaultInstanceForType = messageLite.getDefaultInstanceForType();
        if (protoParsers$InternalDontUse.message == null) {
            protoParsers$InternalDontUse.message = defaultInstanceForType.toBuilder().mergeFrom(protoParsers$InternalDontUse.bytes, extensionRegistryLite).build();
        }
        return protoParsers$InternalDontUse.message;
    }

    public static void put(Parcel parcel, MessageLite messageLite) {
        parcel.writeTypedObject(new ProtoParsers$InternalDontUse(null, messageLite), 0);
    }
}
