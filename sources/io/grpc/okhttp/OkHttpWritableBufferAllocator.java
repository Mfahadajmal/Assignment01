package io.grpc.okhttp;

import io.grpc.cronet.CronetWritableBuffer;
import io.grpc.internal.WritableBuffer;
import io.grpc.internal.WritableBufferAllocator;
import java.nio.ByteBuffer;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpWritableBufferAllocator implements WritableBufferAllocator {
    private final /* synthetic */ int switching_field;

    public OkHttpWritableBufferAllocator(int i) {
        this.switching_field = i;
    }

    @Override // io.grpc.internal.WritableBufferAllocator
    public final WritableBuffer allocate(int i) {
        if (this.switching_field != 0) {
            return new CronetWritableBuffer(ByteBuffer.allocateDirect(Math.min(1048576, i)));
        }
        return new OkHttpWritableBuffer(new Buffer(), Math.min(1048576, Math.max(4096, i)));
    }
}
