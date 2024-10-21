package org.chromium.net.apihelpers;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.chromium.net.UploadDataProvider;
import org.chromium.net.UploadDataSink;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UploadDataProviders$FileUploadProvider extends UploadDataProvider {
    private volatile FileChannel mChannel;
    private final Object mLock = new Object();
    private final UploadDataProviders$FileChannelProvider mProvider;

    public UploadDataProviders$FileUploadProvider(UploadDataProviders$FileChannelProvider uploadDataProviders$FileChannelProvider) {
        this.mProvider = uploadDataProviders$FileChannelProvider;
    }

    private final FileChannel getChannel() {
        if (this.mChannel == null) {
            synchronized (this.mLock) {
                if (this.mChannel == null) {
                    this.mChannel = this.mProvider.getChannel();
                }
            }
        }
        return this.mChannel;
    }

    @Override // org.chromium.net.UploadDataProvider, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        FileChannel fileChannel = this.mChannel;
        if (fileChannel != null) {
            fileChannel.close();
        }
    }

    @Override // org.chromium.net.UploadDataProvider
    public final long getLength() {
        return getChannel().size();
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void read(UploadDataSink uploadDataSink, ByteBuffer byteBuffer) {
        int read;
        if (byteBuffer.hasRemaining()) {
            FileChannel channel = getChannel();
            do {
                read = channel.read(byteBuffer);
                if (read == -1) {
                    break;
                }
            } while (read == 0);
            uploadDataSink.onReadSucceeded(false);
            return;
        }
        throw new IllegalStateException("Cronet passed a buffer with no bytes remaining");
    }

    @Override // org.chromium.net.UploadDataProvider
    public final void rewind(UploadDataSink uploadDataSink) {
        getChannel().position(0L);
        uploadDataSink.onRewindSucceeded();
    }
}
