package com.google.android.libraries.storage.protostore.serializers;

import com.google.android.libraries.storage.protostore.Serializer;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ProtoSerializer implements Serializer {
    public abstract MessageLite defaultValue();

    public abstract ExtensionRegistryLite extensionRegistryLite();
}
