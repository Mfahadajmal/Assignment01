package j$.desugar.sun.nio.fs;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes2.dex */
final class a extends FileChannel implements SeekableByteChannel {
    final FileChannel a;

    private a(FileChannel fileChannel) {
        this.a = fileChannel;
    }

    public static FileChannel a(FileChannel fileChannel) {
        return fileChannel instanceof a ? fileChannel : new a(fileChannel);
    }

    @Override // java.nio.channels.FileChannel
    public final void force(boolean z) {
        this.a.force(z);
    }

    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public final void implCloseChannel() {
        this.a.close();
    }

    @Override // java.nio.channels.FileChannel
    public final FileLock lock(long j, long j2, boolean z) {
        FileLock lock = this.a.lock(j, j2, z);
        if (lock == null) {
            return null;
        }
        return new b(lock, this);
    }

    @Override // java.nio.channels.FileChannel
    public final MappedByteBuffer map(FileChannel.MapMode mapMode, long j, long j2) {
        return this.a.map(mapMode, j, j2);
    }

    @Override // java.nio.channels.FileChannel
    public final long position() {
        return this.a.position();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        return this.a.read(byteBuffer);
    }

    @Override // java.nio.channels.FileChannel
    public final long size() {
        return this.a.size();
    }

    @Override // java.nio.channels.FileChannel
    public final long transferFrom(ReadableByteChannel readableByteChannel, long j, long j2) {
        return this.a.transferFrom(readableByteChannel, j, j2);
    }

    @Override // java.nio.channels.FileChannel
    public final long transferTo(long j, long j2, WritableByteChannel writableByteChannel) {
        return this.a.transferTo(j, j2, writableByteChannel);
    }

    @Override // java.nio.channels.FileChannel
    public final FileChannel truncate(long j) {
        return a(this.a.truncate(j));
    }

    @Override // java.nio.channels.FileChannel
    public final FileLock tryLock(long j, long j2, boolean z) {
        FileLock tryLock = this.a.tryLock(j, j2, z);
        if (tryLock == null) {
            return null;
        }
        return new b(tryLock, this);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        return this.a.write(byteBuffer);
    }

    @Override // java.nio.channels.FileChannel
    public final FileChannel position(long j) {
        return a(this.a.position(j));
    }

    @Override // java.nio.channels.FileChannel
    public final int read(ByteBuffer byteBuffer, long j) {
        return this.a.read(byteBuffer, j);
    }

    @Override // java.nio.channels.FileChannel
    public final int write(ByteBuffer byteBuffer, long j) {
        return this.a.write(byteBuffer, j);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
    public final long read(ByteBuffer[] byteBufferArr, int i, int i2) {
        return this.a.read(byteBufferArr, i, i2);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    public final long write(ByteBuffer[] byteBufferArr, int i, int i2) {
        return this.a.write(byteBufferArr, i, i2);
    }
}
