package com.google.protobuf;

import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExtensionRegistryLite {
    public static final ExtensionRegistryLite EMPTY_REGISTRY_LITE = new ExtensionRegistryLite(null);
    private static volatile boolean eagerlyParseMessageSets = false;
    private static volatile ExtensionRegistryLite generatedRegistry;
    public final Map extensionsByNumber;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ObjectIntPair {
        private final int number;
        private final Object object;

        public ObjectIntPair(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof ObjectIntPair)) {
                return false;
            }
            ObjectIntPair objectIntPair = (ObjectIntPair) obj;
            if (this.object != objectIntPair.object || this.number != objectIntPair.number) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }
    }

    public ExtensionRegistryLite() {
        this.extensionsByNumber = new HashMap();
    }

    public static ExtensionRegistryLite getGeneratedRegistry() {
        ExtensionRegistryLite extensionRegistryLite = generatedRegistry;
        if (extensionRegistryLite != null) {
            return extensionRegistryLite;
        }
        synchronized (ExtensionRegistryLite.class) {
            ExtensionRegistryLite extensionRegistryLite2 = generatedRegistry;
            if (extensionRegistryLite2 != null) {
                return extensionRegistryLite2;
            }
            Protobuf protobuf = Protobuf.INSTANCE;
            ExtensionRegistryLite load = GeneratedExtensionRegistryLoader.load(ExtensionRegistryLite.class);
            generatedRegistry = load;
            return load;
        }
    }

    public GeneratedMessageLite.GeneratedExtension findLiteExtensionByNumber(MessageLite messageLite, int i) {
        return (GeneratedMessageLite.GeneratedExtension) this.extensionsByNumber.get(new ObjectIntPair(messageLite, i));
    }

    public ExtensionRegistryLite(byte[] bArr) {
        this.extensionsByNumber = Collections.emptyMap();
    }
}
