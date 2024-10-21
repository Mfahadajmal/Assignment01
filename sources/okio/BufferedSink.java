package okio;

import java.nio.channels.WritableByteChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    @Override // okio.Sink, java.io.Flushable
    void flush();

    void write$ar$ds$c3288001_0(byte[] bArr);

    void writeByte$ar$ds$1b66c408_0(int i);

    void writeInt$ar$ds$7d1bee7_0(int i);

    void writeShort$ar$ds$a45469cc_0(int i);

    BufferedSink writeUtf8(String str);
}
