package com.google.common.hash;

import com.google.common.flogger.context.ContextDataProvider;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractStreamingHasher extends AbstractHasher {
    private final ByteBuffer buffer;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractStreamingHasher() {
        ContextDataProvider.checkArgument(true);
        this.buffer = ByteBuffer.allocate(23).order(ByteOrder.LITTLE_ENDIAN);
    }

    private final void munch() {
        this.buffer.flip();
        while (this.buffer.remaining() >= 16) {
            process(this.buffer);
        }
        this.buffer.compact();
    }

    private final void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        munch();
        this.buffer.flip();
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.limit());
        }
        return makeHash();
    }

    protected abstract HashCode makeHash();

    protected abstract void process(ByteBuffer byteBuffer);

    protected void processRemaining(ByteBuffer byteBuffer) {
        throw null;
    }

    @Override // com.google.common.hash.Hasher
    public final void putByte$ar$ds() {
        this.buffer.put((byte) 0);
        munchIfFull();
    }

    @Override // com.google.common.hash.AbstractHasher
    public final Hasher putBytes$ar$ds(byte[] bArr, int i) {
        ByteBuffer order = ByteBuffer.wrap(bArr, 0, i).order(ByteOrder.LITTLE_ENDIAN);
        if (order.remaining() <= this.buffer.remaining()) {
            this.buffer.put(order);
            munchIfFull();
        } else {
            int position = 16 - this.buffer.position();
            for (int i2 = 0; i2 < position; i2++) {
                this.buffer.put(order.get());
            }
            munch();
            while (order.remaining() >= 16) {
                process(order);
            }
            this.buffer.put(order);
        }
        return this;
    }
}
