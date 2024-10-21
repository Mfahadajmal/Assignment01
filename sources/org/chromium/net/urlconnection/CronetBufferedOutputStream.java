package org.chromium.net.urlconnection;

import _COROUTINE._BOUNDARY;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CronetBufferedOutputStream extends CronetOutputStream {
    public ByteBuffer mBuffer;
    public boolean mConnected;
    public final int mInitialContentLength;
    private final UploadDataProvider mUploadDataProvider;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UploadDataProviderImpl extends UploadDataProvider {
        public UploadDataProviderImpl() {
        }

        @Override // org.chromium.net.UploadDataProvider
        public final long getLength() {
            int position;
            CronetBufferedOutputStream cronetBufferedOutputStream = CronetBufferedOutputStream.this;
            int i = cronetBufferedOutputStream.mInitialContentLength;
            if (i == -1) {
                boolean z = cronetBufferedOutputStream.mConnected;
                ByteBuffer byteBuffer = cronetBufferedOutputStream.mBuffer;
                if (z) {
                    position = byteBuffer.limit();
                } else {
                    position = byteBuffer.position();
                }
                return position;
            }
            return i;
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            int remaining = byteBuffer.remaining();
            if (remaining < CronetBufferedOutputStream.this.mBuffer.remaining()) {
                byteBuffer.put(CronetBufferedOutputStream.this.mBuffer.array(), CronetBufferedOutputStream.this.mBuffer.position(), remaining);
                ByteBuffer byteBuffer2 = CronetBufferedOutputStream.this.mBuffer;
            } else {
                byteBuffer.put(CronetBufferedOutputStream.this.mBuffer);
            }
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void rewind(UploadDataSink uploadDataSink) {
            uploadDataSink.onRewindSucceeded();
        }
    }

    public CronetBufferedOutputStream() {
        this.mUploadDataProvider = new UploadDataProviderImpl();
        this.mInitialContentLength = -1;
        this.mBuffer = ByteBuffer.allocate(16384);
    }

    private final void ensureCanWrite(int i) {
        if (this.mInitialContentLength != -1) {
            int position = this.mBuffer.position() + i;
            int i2 = this.mInitialContentLength;
            if (position > i2) {
                throw new ProtocolException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i2, "exceeded content-length limit of ", " bytes"));
            }
        }
        if (!this.mConnected) {
            if (this.mInitialContentLength != -1 || this.mBuffer.limit() - this.mBuffer.position() > i) {
                return;
            }
            int capacity = this.mBuffer.capacity();
            ByteBuffer allocate = ByteBuffer.allocate(Math.max(capacity + capacity, this.mBuffer.capacity() + i));
            allocate.put(this.mBuffer);
            this.mBuffer = allocate;
            return;
        }
        throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for writing after connect");
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final UploadDataProvider getUploadDataProvider() {
        return this.mUploadDataProvider;
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void setConnected() {
        this.mConnected = true;
        if (this.mBuffer.position() >= this.mInitialContentLength) {
            return;
        }
        throw new ProtocolException("Content received is less than Content-Length");
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        checkNotClosed();
        ensureCanWrite(1);
        this.mBuffer.put((byte) i);
    }

    public CronetBufferedOutputStream(long j) {
        this.mUploadDataProvider = new UploadDataProviderImpl();
        if (j > 2147483647L) {
            throw new IllegalArgumentException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2GB.");
        }
        if (j >= 0) {
            int i = (int) j;
            this.mInitialContentLength = i;
            this.mBuffer = ByteBuffer.allocate(i);
            return;
        }
        throw new IllegalArgumentException("Content length < 0.");
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        checkNotClosed();
        ensureCanWrite(i2);
        this.mBuffer.put(bArr, i, i2);
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void checkReceivedEnoughContent() {
    }
}
