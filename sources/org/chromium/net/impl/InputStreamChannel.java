package org.chromium.net.impl;

import j$.nio.channels.DesugarChannels;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
final class InputStreamChannel implements ReadableByteChannel {
    private final InputStream mInputStream;
    private final AtomicBoolean mIsOpen = new AtomicBoolean(true);

    private InputStreamChannel(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ReadableByteChannel wrap(InputStream inputStream) {
        if (inputStream instanceof FileInputStream) {
            return DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(((FileInputStream) inputStream).getChannel());
        }
        return new InputStreamChannel(inputStream);
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.mIsOpen.compareAndSet(true, false)) {
            this.mInputStream.close();
        }
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return this.mIsOpen.get();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            int read = this.mInputStream.read(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            if (read > 0) {
                return read;
            }
            return read;
        }
        byte[] bArr = new byte[Math.min(16384, Math.min(Math.max(this.mInputStream.available(), 4096), byteBuffer.remaining()))];
        int read2 = this.mInputStream.read(bArr);
        if (read2 > 0) {
            byteBuffer.put(bArr, 0, read2);
            return read2;
        }
        return read2;
    }
}
