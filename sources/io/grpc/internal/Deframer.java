package io.grpc.internal;

import io.grpc.Decompressor;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Deframer {
    void close();

    void closeWhenComplete();

    void deframe(ReadableBuffer readableBuffer);

    void request(int i);

    void setDecompressor(Decompressor decompressor);

    void setMaxInboundMessageSize(int i);
}
