package com.google.protobuf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayDecoders$Registers {
    public final ExtensionRegistryLite extensionRegistry;
    public int int1;
    public long long1;
    public Object object1;

    ArrayDecoders$Registers() {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
        this.extensionRegistry = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    public ArrayDecoders$Registers(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.getClass();
        this.extensionRegistry = extensionRegistryLite;
    }
}
