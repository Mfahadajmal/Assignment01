package org.chromium.net.urlconnection;

import java.net.HttpRetryException;
import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CronetChunkedOutputStream extends CronetOutputStream {
    public final ByteBuffer mBuffer;
    public boolean mLastChunk;
    public final MessageLoop mMessageLoop;
    private final UploadDataProvider mUploadDataProvider = new UploadDataProviderImpl();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UploadDataProviderImpl extends UploadDataProvider {
        public UploadDataProviderImpl() {
        }

        @Override // org.chromium.net.UploadDataProvider
        public final long getLength() {
            return -1L;
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            if (byteBuffer.remaining() >= CronetChunkedOutputStream.this.mBuffer.remaining()) {
                byteBuffer.put(CronetChunkedOutputStream.this.mBuffer);
                uploadDataSink.onReadSucceeded(CronetChunkedOutputStream.this.mLastChunk);
                CronetChunkedOutputStream cronetChunkedOutputStream = CronetChunkedOutputStream.this;
                if (!cronetChunkedOutputStream.mLastChunk) {
                    cronetChunkedOutputStream.mMessageLoop.quit();
                    return;
                }
                return;
            }
            CronetChunkedOutputStream cronetChunkedOutputStream2 = CronetChunkedOutputStream.this;
            int limit = cronetChunkedOutputStream2.mBuffer.limit();
            ByteBuffer byteBuffer2 = cronetChunkedOutputStream2.mBuffer;
            byteBuffer.put(CronetChunkedOutputStream.this.mBuffer);
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void rewind(UploadDataSink uploadDataSink) {
            uploadDataSink.onRewindError(new HttpRetryException("Cannot retry streamed Http body", -1));
        }
    }

    public CronetChunkedOutputStream(int i, MessageLoop messageLoop) {
        if (i > 0) {
            this.mBuffer = ByteBuffer.allocate(i);
            this.mMessageLoop = messageLoop;
            return;
        }
        throw new IllegalArgumentException("chunkLength should be greater than 0");
    }

    private final void ensureBufferHasRemaining() {
        if (!this.mBuffer.hasRemaining()) {
            checkNotClosed();
            this.mMessageLoop.loop();
            checkNoException();
        }
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        super.close();
        if (!this.mLastChunk) {
            this.mLastChunk = true;
        }
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final UploadDataProvider getUploadDataProvider() {
        return this.mUploadDataProvider;
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        ensureBufferHasRemaining();
        this.mBuffer.put((byte) i);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        checkNotClosed();
        if (bArr.length - i < i2 || i < 0 || i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = i2;
        while (i3 > 0) {
            int min = Math.min(i3, this.mBuffer.remaining());
            this.mBuffer.put(bArr, (i + i2) - i3, min);
            i3 -= min;
            ensureBufferHasRemaining();
        }
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void checkReceivedEnoughContent() {
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void setConnected() {
    }
}
