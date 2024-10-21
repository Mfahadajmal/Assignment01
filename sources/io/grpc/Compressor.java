package io.grpc;

import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Compressor {
    OutputStream compress(OutputStream outputStream);

    String getMessageEncoding();
}
