package io.grpc.internal;

import io.grpc.Compressor;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Framer {
    void close();

    void flush();

    boolean isClosed();

    void setCompressor$ar$ds(Compressor compressor);

    void setMaxOutboundMessageSize(int i);

    void writePayload(InputStream inputStream);
}
