package org.chromium.net.apihelpers;

import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UploadDataProviders$ByteBufferUploadProvider extends UploadDataProvider {
    private final ByteBuffer mUploadBuffer;

    public UploadDataProviders$ByteBufferUploadProvider(ByteBuffer byteBuffer) {
        this.mUploadBuffer = byteBuffer;
    }

    @Override // org.chromium.net.UploadDataProvider
    public final long getLength() {
        return this.mUploadBuffer.limit();
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
        if (byteBuffer.hasRemaining()) {
            if (byteBuffer.remaining() >= this.mUploadBuffer.remaining()) {
                byteBuffer.put(this.mUploadBuffer);
            } else {
                ByteBuffer byteBuffer2 = this.mUploadBuffer;
                int limit = byteBuffer2.limit();
                byteBuffer.put(this.mUploadBuffer);
            }
            uploadDataSink.onReadSucceeded(false);
            return;
        }
        throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void rewind(UploadDataSink uploadDataSink) {
        uploadDataSink.onRewindSucceeded();
    }
}
