package org.chromium.net.urlconnection;

import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CronetInputStream extends InputStream implements InputStreamRetargetInterface {
    public ByteBuffer mBuffer;
    public IOException mException;
    private final CronetHttpURLConnection mHttpURLConnection;
    public boolean mResponseDataCompleted;

    public CronetInputStream(CronetHttpURLConnection cronetHttpURLConnection) {
        this.mHttpURLConnection = cronetHttpURLConnection;
    }

    private final void getMoreDataIfNeeded() {
        if (this.mResponseDataCompleted) {
            IOException iOException = this.mException;
            if (iOException != null) {
                throw iOException;
            }
            return;
        }
        if (!hasUnreadData()) {
            if (this.mBuffer == null) {
                this.mBuffer = ByteBuffer.allocateDirect(32768);
            }
            CronetHttpURLConnection cronetHttpURLConnection = this.mHttpURLConnection;
            cronetHttpURLConnection.mRequest.read(this.mBuffer);
            cronetHttpURLConnection.mMessageLoop.loop(cronetHttpURLConnection.getReadTimeout());
            IOException iOException2 = this.mException;
            if (iOException2 == null) {
                ByteBuffer byteBuffer = this.mBuffer;
                if (byteBuffer != null) {
                    return;
                }
                return;
            }
            throw iOException2;
        }
    }

    private final boolean hasUnreadData() {
        ByteBuffer byteBuffer = this.mBuffer;
        if (byteBuffer != null && byteBuffer.hasRemaining()) {
            return true;
        }
        return false;
    }

    @Override // java.io.InputStream
    public final int available() {
        if (this.mResponseDataCompleted) {
            IOException iOException = this.mException;
            if (iOException != null) {
                throw iOException;
            }
            return 0;
        }
        if (hasUnreadData()) {
            return this.mBuffer.remaining();
        }
        return 0;
    }

    @Override // java.io.InputStream
    public final int read() {
        getMoreDataIfNeeded();
        if (hasUnreadData()) {
            return this.mBuffer.get() & 255;
        }
        return -1;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public final /* synthetic */ long transferTo(OutputStream outputStream) {
        return DesugarInputStream.transferTo(this, outputStream);
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return 0;
        }
        getMoreDataIfNeeded();
        if (!hasUnreadData()) {
            return -1;
        }
        int min = Math.min(this.mBuffer.limit() - this.mBuffer.position(), i2);
        this.mBuffer.get(bArr, i, min);
        return min;
    }
}
