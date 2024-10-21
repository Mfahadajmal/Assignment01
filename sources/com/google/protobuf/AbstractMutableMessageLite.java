package com.google.protobuf;

import com.google.protobuf.MessageLite;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractMutableMessageLite implements MutableMessageLite {
    @Override // com.google.protobuf.MutableMessageLite
    public final MutableMessageLite clone() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final MessageLite.Builder newBuilderForType() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final MessageLite.Builder toBuilder() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final byte[] toByteArray() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final ByteString toByteString() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final void writeTo(CodedOutputStream codedOutputStream) {
        throw null;
    }

    /* renamed from: clone, reason: collision with other method in class */
    public final /* synthetic */ Object m236clone() {
        throw null;
    }

    @Override // com.google.protobuf.MessageLite
    public final void writeTo(OutputStream outputStream) {
        throw null;
    }
}
