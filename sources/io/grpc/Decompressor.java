package io.grpc;

import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Decompressor {
    InputStream decompress(InputStream inputStream);

    String getMessageEncoding();
}
