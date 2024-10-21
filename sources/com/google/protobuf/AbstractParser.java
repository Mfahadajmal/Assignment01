package com.google.protobuf;

import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractParser implements Parser {
    static {
        ExtensionRegistryLite extensionRegistryLite = ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
        Protobuf protobuf = Protobuf.INSTANCE;
    }

    private static final void checkMessageInitialized$ar$ds(MessageLite messageLite) {
        if (messageLite != null && !messageLite.isInitialized()) {
            throw ((AbstractMessageLite) messageLite).newUninitializedMessageException().asInvalidProtocolBufferException();
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.protobuf.MessageLite, java.lang.Object] */
    @Override // com.google.protobuf.Parser
    public final /* bridge */ /* synthetic */ Object parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) {
        ?? parsePartialFrom = parsePartialFrom(codedInputStream, extensionRegistryLite);
        checkMessageInitialized$ar$ds(parsePartialFrom);
        return parsePartialFrom;
    }

    @Override // com.google.protobuf.Parser
    public final /* bridge */ /* synthetic */ Object parseFrom$ar$ds(byte[] bArr, int i, ExtensionRegistryLite extensionRegistryLite) {
        MessageLite parsePartialFrom$ar$ds = parsePartialFrom$ar$ds(bArr, i, extensionRegistryLite);
        checkMessageInitialized$ar$ds(parsePartialFrom$ar$ds);
        return parsePartialFrom$ar$ds;
    }

    public MessageLite parsePartialFrom$ar$ds(byte[] bArr, int i, ExtensionRegistryLite extensionRegistryLite) {
        throw null;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.google.protobuf.MessageLite, java.lang.Object] */
    @Override // com.google.protobuf.Parser
    public final /* bridge */ /* synthetic */ Object parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) {
        CodedInputStream newInstance = CodedInputStream.newInstance(inputStream);
        ?? parsePartialFrom = parsePartialFrom(newInstance, extensionRegistryLite);
        newInstance.checkLastTagWas(0);
        checkMessageInitialized$ar$ds(parsePartialFrom);
        return parsePartialFrom;
    }
}
