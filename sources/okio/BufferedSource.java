package okio;

import java.nio.channels.ReadableByteChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    boolean exhausted();

    Buffer getBuffer();

    byte readByte();

    byte[] readByteArray(long j);

    ByteString readByteString(long j);

    int readInt();

    short readShort();

    void require(long j);

    void skip(long j);
}
