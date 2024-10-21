package io.grpc.internal;

import io.grpc.Compressor;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Stream {
    void flush();

    boolean isReady();

    void optimizeForDirectExecutor();

    void request(int i);

    void setCompressor(Compressor compressor);

    void writeMessage(InputStream inputStream);
}
