package io.grpc;

import io.grpc.Codec;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompressorRegistry {
    public static final CompressorRegistry DEFAULT_INSTANCE = new CompressorRegistry(new Codec.Identity(1), Codec.Identity.NONE);
    private final ConcurrentMap compressors = new ConcurrentHashMap();

    public CompressorRegistry(Compressor... compressorArr) {
        for (int i = 0; i < 2; i++) {
            Compressor compressor = compressorArr[i];
            this.compressors.put(compressor.getMessageEncoding(), compressor);
        }
    }
}
