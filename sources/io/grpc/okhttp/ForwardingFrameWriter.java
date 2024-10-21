package io.grpc.okhttp;

import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.FrameWriter;
import io.grpc.okhttp.internal.framed.Settings;
import java.util.List;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
class ForwardingFrameWriter implements FrameWriter {
    public final FrameWriter delegate;

    public ForwardingFrameWriter(FrameWriter frameWriter) {
        this.delegate = frameWriter;
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ackSettings(Settings settings) {
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.delegate.close();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void connectionPreface() {
        this.delegate.connectionPreface();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void data(boolean z, int i, Buffer buffer, int i2) {
        this.delegate.data(z, i, buffer, i2);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void flush() {
        this.delegate.flush();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void goAway$ar$ds(ErrorCode errorCode, byte[] bArr) {
        this.delegate.goAway$ar$ds(errorCode, bArr);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final int maxDataLength() {
        return this.delegate.maxDataLength();
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void ping(boolean z, int i, int i2) {
        throw null;
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public void rstStream(int i, ErrorCode errorCode) {
        throw null;
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void settings(Settings settings) {
        this.delegate.settings(settings);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void synStream$ar$ds(boolean z, int i, List list) {
        this.delegate.synStream$ar$ds(z, i, list);
    }

    @Override // io.grpc.okhttp.internal.framed.FrameWriter
    public final void windowUpdate(int i, long j) {
        this.delegate.windowUpdate(i, j);
    }
}
