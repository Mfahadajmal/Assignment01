package com.google.mlkit.logging.schema.acceleration;

import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.apihelpers.UploadDataProviders$ByteBufferUploadProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GPUInfo {
    public static UploadDataProvider create(byte[] bArr, int i, int i2) {
        return new UploadDataProviders$ByteBufferUploadProvider(ByteBuffer.wrap(bArr, i, i2).slice());
    }

    public static /* synthetic */ String toStringGenerated5ff6403310ffba06(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return "BYTES";
                    }
                    return "STRING";
                }
                return "FLOAT";
            }
            return "INT";
        }
        return "BOOL";
    }
}
