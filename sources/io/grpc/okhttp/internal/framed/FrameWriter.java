package io.grpc.okhttp.internal.framed;

import java.io.Closeable;
import java.util.List;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface FrameWriter extends Closeable {
    void ackSettings(Settings settings);

    void connectionPreface();

    void data(boolean z, int i, Buffer buffer, int i2);

    void flush();

    void goAway$ar$ds(ErrorCode errorCode, byte[] bArr);

    int maxDataLength();

    void ping(boolean z, int i, int i2);

    void rstStream(int i, ErrorCode errorCode);

    void settings(Settings settings);

    void synStream$ar$ds(boolean z, int i, List list);

    void windowUpdate(int i, long j);
}
