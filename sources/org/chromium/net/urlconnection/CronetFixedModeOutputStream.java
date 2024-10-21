package org.chromium.net.urlconnection;

import java.net.HttpRetryException;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CronetFixedModeOutputStream extends CronetOutputStream {
    private static final int sDefaultBufferLength = 16384;
    public final ByteBuffer mBuffer;
    private long mBytesWritten;
    public final long mContentLength;
    public final MessageLoop mMessageLoop;
    private final UploadDataProvider mUploadDataProvider = new UploadDataProviderImpl();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UploadDataProviderImpl extends UploadDataProvider {
        public UploadDataProviderImpl() {
        }

        @Override // org.chromium.net.UploadDataProvider
        public final long getLength() {
            return CronetFixedModeOutputStream.this.mContentLength;
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
            if (byteBuffer.remaining() >= CronetFixedModeOutputStream.this.mBuffer.remaining()) {
                byteBuffer.put(CronetFixedModeOutputStream.this.mBuffer);
                uploadDataSink.onReadSucceeded(false);
                CronetFixedModeOutputStream.this.mMessageLoop.quit();
                return;
            }
            CronetFixedModeOutputStream cronetFixedModeOutputStream = CronetFixedModeOutputStream.this;
            int limit = cronetFixedModeOutputStream.mBuffer.limit();
            ByteBuffer byteBuffer2 = cronetFixedModeOutputStream.mBuffer;
            byteBuffer.put(CronetFixedModeOutputStream.this.mBuffer);
            uploadDataSink.onReadSucceeded(false);
        }

        @Override // org.chromium.net.UploadDataProvider
        public final void rewind(UploadDataSink uploadDataSink) {
            uploadDataSink.onRewindError(new HttpRetryException("Cannot retry streamed Http body", -1));
        }
    }

    public CronetFixedModeOutputStream(long j, MessageLoop messageLoop) {
        if (j >= 0) {
            this.mContentLength = j;
            this.mBuffer = ByteBuffer.allocate((int) Math.min(j, 16384L));
            this.mMessageLoop = messageLoop;
            this.mBytesWritten = 0L;
            return;
        }
        throw new IllegalArgumentException("Content length must be larger than 0 for non-chunked upload.");
    }

    private final void checkNotExceedContentLength(int i) {
        long j = this.mBytesWritten + i;
        long j2 = this.mContentLength;
        if (j <= j2) {
            return;
        }
        throw new ProtocolException("expected " + (j2 - this.mBytesWritten) + " bytes but received " + i);
    }

    private final void ensureBufferHasRemaining() {
        if (!this.mBuffer.hasRemaining()) {
            uploadBufferInternal();
        }
    }

    private final void uploadBufferInternal() {
        checkNotClosed();
        this.mMessageLoop.loop();
        checkNoException();
    }

    private final void uploadIfComplete() {
        if (this.mBytesWritten == this.mContentLength) {
            uploadBufferInternal();
        }
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void checkReceivedEnoughContent() {
        if (this.mBytesWritten >= this.mContentLength) {
        } else {
            throw new ProtocolException("Content received is less than Content-Length.");
        }
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final UploadDataProvider getUploadDataProvider() {
        return this.mUploadDataProvider;
    }

    @Override // java.io.OutputStream
    public final void write(int i) {
        checkNotClosed();
        checkNotExceedContentLength(1);
        ensureBufferHasRemaining();
        this.mBuffer.put((byte) i);
        this.mBytesWritten++;
        uploadIfComplete();
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        checkNotClosed();
        if (bArr.length - i >= i2 && i >= 0 && i2 >= 0) {
            checkNotExceedContentLength(i2);
            int i3 = i2;
            while (i3 > 0) {
                ensureBufferHasRemaining();
                int min = Math.min(i3, this.mBuffer.remaining());
                this.mBuffer.put(bArr, (i + i2) - i3, min);
                i3 -= min;
            }
            this.mBytesWritten += i2;
            uploadIfComplete();
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // org.chromium.net.urlconnection.CronetOutputStream
    public final void setConnected() {
    }
}
