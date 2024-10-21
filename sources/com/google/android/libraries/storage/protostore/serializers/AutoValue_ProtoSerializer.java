package com.google.android.libraries.storage.protostore.serializers;

import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AutoValue_ProtoSerializer extends ProtoSerializer {
    public final MessageLite defaultValue;
    private final ExtensionRegistryLite extensionRegistryLite;

    public AutoValue_ProtoSerializer(MessageLite messageLite, ExtensionRegistryLite extensionRegistryLite) {
        if (messageLite != null) {
            this.defaultValue = messageLite;
            if (extensionRegistryLite != null) {
                this.extensionRegistryLite = extensionRegistryLite;
                return;
            }
            throw new NullPointerException("Null extensionRegistryLite");
        }
        throw new NullPointerException("Null defaultValue");
    }

    @Override // com.google.android.libraries.storage.protostore.serializers.ProtoSerializer
    public final MessageLite defaultValue() {
        return this.defaultValue;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ProtoSerializer) {
            ProtoSerializer protoSerializer = (ProtoSerializer) obj;
            if (this.defaultValue.equals(protoSerializer.defaultValue()) && this.extensionRegistryLite.equals(protoSerializer.extensionRegistryLite())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.libraries.storage.protostore.serializers.ProtoSerializer
    public final ExtensionRegistryLite extensionRegistryLite() {
        return this.extensionRegistryLite;
    }

    public final int hashCode() {
        return ((this.defaultValue.hashCode() ^ 1000003) * 1000003) ^ this.extensionRegistryLite.hashCode();
    }

    public final String toString() {
        ExtensionRegistryLite extensionRegistryLite = this.extensionRegistryLite;
        return "ProtoSerializer{defaultValue=" + this.defaultValue.toString() + ", extensionRegistryLite=" + extensionRegistryLite.toString() + "}";
    }
}
