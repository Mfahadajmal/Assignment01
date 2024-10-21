package com.google.protobuf;

import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Parser<MessageType> {
    Object parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);

    Object parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite);

    Object parseFrom$ar$ds(byte[] bArr, int i, ExtensionRegistryLite extensionRegistryLite);

    Object parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite);
}
