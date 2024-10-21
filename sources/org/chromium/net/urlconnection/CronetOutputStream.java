package org.chromium.net.urlconnection;

import java.io.IOException;
import java.io.OutputStream;
import org.chromium.net.UploadDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class CronetOutputStream extends OutputStream {
    private boolean mClosed;
    public IOException mException;
    public boolean mRequestCompleted;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void checkNoException() {
        IOException iOException = this.mException;
        if (iOException == null) {
        } else {
            throw iOException;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void checkNotClosed() {
        if (!this.mRequestCompleted) {
            if (!this.mClosed) {
            } else {
                throw new IOException("Stream has been closed.");
            }
        } else {
            checkNoException();
            throw new IOException("Writing after request completed.");
        }
    }

    public abstract void checkReceivedEnoughContent();

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.mClosed = true;
    }

    public abstract UploadDataProvider getUploadDataProvider();

    public abstract void setConnected();
}
