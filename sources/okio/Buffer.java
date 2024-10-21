package okio;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import kotlin.text.Charsets;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public Segment head;
    public long size;

    public final void clear() {
        skip(this.size);
    }

    public final /* synthetic */ Object clone() {
        Buffer buffer = new Buffer();
        if (this.size != 0) {
            Segment segment = this.head;
            segment.getClass();
            Segment sharedCopy = segment.sharedCopy();
            buffer.head = sharedCopy;
            sharedCopy.prev = sharedCopy;
            sharedCopy.next = sharedCopy.prev;
            for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
                Segment segment3 = sharedCopy.prev;
                segment3.getClass();
                segment2.getClass();
                segment3.push$ar$ds(segment2.sharedCopy());
            }
            buffer.size = this.size;
        }
        return buffer;
    }

    public final long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0L;
        }
        Segment segment = this.head;
        segment.getClass();
        Segment segment2 = segment.prev;
        segment2.getClass();
        if (segment2.limit < 8192 && segment2.owner) {
            j -= r3 - segment2.pos;
        }
        return j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Buffer) {
            long j = this.size;
            Buffer buffer = (Buffer) obj;
            if (j == buffer.size) {
                if (j == 0) {
                    return true;
                }
                Segment segment = this.head;
                segment.getClass();
                Segment segment2 = buffer.head;
                segment2.getClass();
                int i = segment.pos;
                int i2 = segment2.pos;
                long j2 = 0;
                while (j2 < this.size) {
                    long min = Math.min(segment.limit - i, segment2.limit - i2);
                    long j3 = 0;
                    while (j3 < min) {
                        int i3 = i + 1;
                        int i4 = i2 + 1;
                        if (segment.data[i] == segment2.data[i2]) {
                            j3++;
                            i = i3;
                            i2 = i4;
                        }
                    }
                    if (i == segment.limit) {
                        segment = segment.next;
                        segment.getClass();
                        i = segment.pos;
                    }
                    if (i2 == segment2.limit) {
                        segment2 = segment2.next;
                        segment2.getClass();
                        i2 = segment2.pos;
                    }
                    j2 += min;
                }
                return true;
            }
        }
        return false;
    }

    @Override // okio.BufferedSource
    public final boolean exhausted() {
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override // okio.BufferedSource
    public final Buffer getBuffer() {
        throw null;
    }

    public final byte getByte(long j) {
        ToxicityDetectionCreateEvent.checkOffsetAndCount(this.size, j, 1L);
        Segment segment = this.head;
        segment.getClass();
        long j2 = this.size;
        if (j2 - j < j) {
            while (j2 > j) {
                segment = segment.prev;
                segment.getClass();
                j2 -= segment.limit - segment.pos;
            }
            segment.getClass();
            return segment.data[(int) ((segment.pos + j) - j2)];
        }
        long j3 = 0;
        while (true) {
            int i = segment.limit;
            int i2 = segment.pos;
            long j4 = (i - i2) + j3;
            if (j4 <= j) {
                segment = segment.next;
                segment.getClass();
                j3 = j4;
            } else {
                segment.getClass();
                return segment.data[(int) ((i2 + j) - j3)];
            }
        }
    }

    public final int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
            segment.getClass();
        } while (segment != this.head);
        return i;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        byteBuffer.getClass();
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        int i = segment.pos + min;
        segment.pos = i;
        this.size -= min;
        if (i == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // okio.BufferedSource
    public final byte readByte() {
        long j = this.size;
        if (j != 0) {
            Segment segment = this.head;
            segment.getClass();
            int i = segment.pos;
            int i2 = i + 1;
            byte[] bArr = segment.data;
            int i3 = segment.limit;
            byte b = bArr[i];
            this.size = j - 1;
            if (i2 == i3) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i2;
            }
            return b;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public final byte[] readByteArray(long j) {
        if (j >= 0 && j <= 2147483647L) {
            if (this.size >= j) {
                int i = (int) j;
                byte[] bArr = new byte[i];
                int i2 = 0;
                while (i2 < i) {
                    int read = read(bArr, i2, i - i2);
                    if (read != -1) {
                        i2 += read;
                    } else {
                        throw new EOFException();
                    }
                }
                return bArr;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount: "));
    }

    public final ByteString readByteString() {
        return readByteString(this.size);
    }

    @Override // okio.BufferedSource
    public final int readInt() {
        long j = this.size;
        if (j >= 4) {
            Segment segment = this.head;
            segment.getClass();
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i3 = (bArr[i] & 255) << 24;
            int i4 = (bArr[i + 1] & 255) << 16;
            int i5 = (bArr[i + 2] & 255) << 8;
            int i6 = bArr[i + 3] & 255;
            this.size = j - 4;
            int i7 = i3 | i4 | i5 | i6;
            int i8 = i + 4;
            if (i8 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
                return i7;
            }
            segment.pos = i8;
            return i7;
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public final short readShort() {
        int i;
        long j = this.size;
        if (j >= 2) {
            Segment segment = this.head;
            segment.getClass();
            int i2 = segment.pos;
            int i3 = segment.limit;
            if (i3 - i2 < 2) {
                i = ((readByte() & 255) << 8) | (readByte() & 255);
            } else {
                byte[] bArr = segment.data;
                int i4 = (bArr[i2] & 255) << 8;
                int i5 = bArr[i2 + 1] & 255;
                this.size = j - 2;
                int i6 = i2 + 2;
                if (i6 == i3) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                } else {
                    segment.pos = i6;
                }
                i = i4 | i5;
            }
            return (short) i;
        }
        throw new EOFException();
    }

    public final String readString(long j, Charset charset) {
        charset.getClass();
        if (j >= 0 && j <= 2147483647L) {
            long j2 = this.size;
            if (j2 >= j) {
                if (j == 0) {
                    return "";
                }
                Segment segment = this.head;
                segment.getClass();
                int i = segment.pos;
                int i2 = segment.limit;
                if (i + j > i2) {
                    return new String(readByteArray(j), charset);
                }
                int i3 = (int) j;
                String str = new String(segment.data, i, i3, charset);
                int i4 = i + i3;
                segment.pos = i4;
                this.size = j2 - j;
                if (i4 == i2) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
                return str;
            }
            throw new EOFException();
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount: "));
    }

    public final String readUtf8() {
        return readString(this.size, Charsets.UTF_8);
    }

    @Override // okio.BufferedSource
    public final void require(long j) {
        throw null;
    }

    @Override // okio.BufferedSource
    public final void skip(long j) {
        while (j > 0) {
            Segment segment = this.head;
            if (segment != null) {
                int min = (int) Math.min(j, segment.limit - segment.pos);
                long j2 = min;
                this.size -= j2;
                j -= j2;
                int i = segment.pos + min;
                segment.pos = i;
                if (i == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    public final ByteString snapshot() {
        long j = this.size;
        if (j <= 2147483647L) {
            return snapshot((int) j);
        }
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "size > Int.MAX_VALUE: "));
    }

    public final String toString() {
        return snapshot().toString();
    }

    public final Segment writableSegment$third_party_java_src_okio_okio_jvm(int i) {
        if (i > 0) {
            Segment segment = this.head;
            if (segment == null) {
                Segment take = SegmentPool.take();
                this.head = take;
                take.prev = take;
                take.next = take;
                return take;
            }
            Segment segment2 = segment.prev;
            segment2.getClass();
            if (segment2.limit + i <= 8192 && segment2.owner) {
                return segment2;
            }
            Segment take2 = SegmentPool.take();
            segment2.push$ar$ds(take2);
            return take2;
        }
        throw new IllegalArgumentException("unexpected capacity");
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        byteBuffer.getClass();
        int remaining = byteBuffer.remaining();
        int i = remaining;
        while (i > 0) {
            Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(1);
            int i2 = 8192 - writableSegment$third_party_java_src_okio_okio_jvm.limit;
            byte[] bArr = writableSegment$third_party_java_src_okio_okio_jvm.data;
            int min = Math.min(i, i2);
            byteBuffer.get(bArr, writableSegment$third_party_java_src_okio_okio_jvm.limit, min);
            i -= min;
            writableSegment$third_party_java_src_okio_okio_jvm.limit += min;
        }
        this.size += remaining;
        return remaining;
    }

    public final void write$ar$ds(ByteString byteString) {
        byteString.write$third_party_java_src_okio_okio_jvm$ar$ds(this, byteString.getSize$third_party_java_src_okio_okio_jvm());
    }

    public final void write$ar$ds$66c9c9c2_0(byte[] bArr, int i, int i2) {
        bArr.getClass();
        long j = i2;
        ToxicityDetectionCreateEvent.checkOffsetAndCount(bArr.length, i, j);
        int i3 = i;
        while (true) {
            int i4 = i + i2;
            if (i3 < i4) {
                Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(1);
                int i5 = 8192 - writableSegment$third_party_java_src_okio_okio_jvm.limit;
                byte[] bArr2 = writableSegment$third_party_java_src_okio_okio_jvm.data;
                int min = Math.min(i4 - i3, i5);
                int i6 = i3 + min;
                OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds(bArr, bArr2, writableSegment$third_party_java_src_okio_okio_jvm.limit, i3, i6);
                writableSegment$third_party_java_src_okio_okio_jvm.limit += min;
                i3 = i6;
            } else {
                this.size += j;
                return;
            }
        }
    }

    @Override // okio.BufferedSink
    public final /* bridge */ /* synthetic */ void write$ar$ds$c3288001_0(byte[] bArr) {
        throw null;
    }

    public final void writeByte$ar$ds(int i) {
        Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(1);
        byte[] bArr = writableSegment$third_party_java_src_okio_okio_jvm.data;
        int i2 = writableSegment$third_party_java_src_okio_okio_jvm.limit;
        writableSegment$third_party_java_src_okio_okio_jvm.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
    }

    @Override // okio.BufferedSink
    public final /* bridge */ /* synthetic */ void writeByte$ar$ds$1b66c408_0(int i) {
        throw null;
    }

    public final void writeInt$ar$ds(int i) {
        Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(4);
        byte[] bArr = writableSegment$third_party_java_src_okio_okio_jvm.data;
        int i2 = writableSegment$third_party_java_src_okio_okio_jvm.limit;
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) ((i >>> 16) & PrivateKeyType.INVALID);
        bArr[i2 + 2] = (byte) ((i >>> 8) & PrivateKeyType.INVALID);
        bArr[i2 + 3] = (byte) (i & PrivateKeyType.INVALID);
        writableSegment$third_party_java_src_okio_okio_jvm.limit = i2 + 4;
        this.size += 4;
    }

    @Override // okio.BufferedSink
    public final /* bridge */ /* synthetic */ void writeInt$ar$ds$7d1bee7_0(int i) {
        throw null;
    }

    public final void writeShort$ar$ds(int i) {
        Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(2);
        byte[] bArr = writableSegment$third_party_java_src_okio_okio_jvm.data;
        int i2 = writableSegment$third_party_java_src_okio_okio_jvm.limit;
        bArr[i2] = (byte) ((i >>> 8) & PrivateKeyType.INVALID);
        bArr[i2 + 1] = (byte) (i & PrivateKeyType.INVALID);
        writableSegment$third_party_java_src_okio_okio_jvm.limit = i2 + 2;
        this.size += 2;
    }

    @Override // okio.BufferedSink
    public final /* bridge */ /* synthetic */ void writeShort$ar$ds$a45469cc_0(int i) {
        throw null;
    }

    @Override // okio.BufferedSink
    public final Buffer writeUtf8(String str) {
        str.getClass();
        writeUtf8$ar$ds(str, str.length());
        return this;
    }

    public final void writeUtf8$ar$ds(String str, int i) {
        char c;
        if (i >= 0) {
            if (i <= str.length()) {
                int i2 = 0;
                while (i2 < i) {
                    int i3 = i2 + 1;
                    char charAt = str.charAt(i2);
                    if (charAt < 128) {
                        Segment writableSegment$third_party_java_src_okio_okio_jvm = writableSegment$third_party_java_src_okio_okio_jvm(1);
                        byte[] bArr = writableSegment$third_party_java_src_okio_okio_jvm.data;
                        int i4 = writableSegment$third_party_java_src_okio_okio_jvm.limit - i2;
                        int min = Math.min(i, 8192 - i4);
                        bArr[i2 + i4] = (byte) charAt;
                        i2 = i3;
                        while (i2 < min) {
                            char charAt2 = str.charAt(i2);
                            if (charAt2 >= 128) {
                                break;
                            }
                            bArr[i2 + i4] = (byte) charAt2;
                            i2++;
                        }
                        int i5 = writableSegment$third_party_java_src_okio_okio_jvm.limit;
                        int i6 = (i4 + i2) - i5;
                        writableSegment$third_party_java_src_okio_okio_jvm.limit = i5 + i6;
                        this.size += i6;
                    } else {
                        if (charAt < 2048) {
                            Segment writableSegment$third_party_java_src_okio_okio_jvm2 = writableSegment$third_party_java_src_okio_okio_jvm(2);
                            byte[] bArr2 = writableSegment$third_party_java_src_okio_okio_jvm2.data;
                            int i7 = writableSegment$third_party_java_src_okio_okio_jvm2.limit;
                            bArr2[i7] = (byte) ((charAt >> 6) | 192);
                            bArr2[i7 + 1] = (byte) ((charAt & '?') | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                            writableSegment$third_party_java_src_okio_okio_jvm2.limit = i7 + 2;
                            this.size += 2;
                        } else if (charAt >= 55296 && charAt <= 57343) {
                            if (i3 < i) {
                                c = str.charAt(i3);
                            } else {
                                c = 0;
                            }
                            if (charAt <= 56319 && c >= 56320 && c < 57344) {
                                Segment writableSegment$third_party_java_src_okio_okio_jvm3 = writableSegment$third_party_java_src_okio_okio_jvm(4);
                                byte[] bArr3 = writableSegment$third_party_java_src_okio_okio_jvm3.data;
                                int i8 = writableSegment$third_party_java_src_okio_okio_jvm3.limit;
                                int i9 = (((charAt & 1023) << 10) | (c & 1023)) + 65536;
                                bArr3[i8] = (byte) ((i9 >> 18) | 240);
                                bArr3[i8 + 1] = (byte) (((i9 >> 12) & 63) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                                bArr3[i8 + 2] = (byte) (((i9 >> 6) & 63) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                                bArr3[i8 + 3] = (byte) ((i9 & 63) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                                writableSegment$third_party_java_src_okio_okio_jvm3.limit = i8 + 4;
                                this.size += 4;
                                i2 += 2;
                            } else {
                                writeByte$ar$ds(63);
                            }
                        } else {
                            Segment writableSegment$third_party_java_src_okio_okio_jvm4 = writableSegment$third_party_java_src_okio_okio_jvm(3);
                            byte[] bArr4 = writableSegment$third_party_java_src_okio_okio_jvm4.data;
                            int i10 = writableSegment$third_party_java_src_okio_okio_jvm4.limit;
                            bArr4[i10] = (byte) ((charAt >> '\f') | 224);
                            bArr4[i10 + 1] = (byte) ((63 & (charAt >> 6)) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                            bArr4[i10 + 2] = (byte) ((charAt & '?') | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                            writableSegment$third_party_java_src_okio_okio_jvm4.limit = i10 + 3;
                            this.size += 3;
                        }
                        i2 = i3;
                    }
                }
                return;
            }
            throw new IllegalArgumentException("endIndex > string.length: " + i + " > " + str.length());
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(0, i, "endIndex < beginIndex: ", " < "));
    }

    @Override // okio.BufferedSource
    public final ByteString readByteString(long j) {
        if (j < 0 || j > 2147483647L) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount: "));
        }
        if (this.size < j) {
            throw new EOFException();
        }
        if (j >= 4096) {
            ByteString snapshot = snapshot((int) j);
            skip(j);
            return snapshot;
        }
        return new ByteString(readByteArray(j));
    }

    public final String readUtf8(long j) {
        return readString(j, Charsets.UTF_8);
    }

    public final ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        ToxicityDetectionCreateEvent.checkOffsetAndCount(this.size, 0L, i);
        Segment segment = this.head;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            segment.getClass();
            int i5 = segment.limit;
            int i6 = segment.pos;
            if (i5 != i6) {
                i3 += i5 - i6;
                i4++;
                segment = segment.next;
            } else {
                throw new AssertionError("s.limit == s.pos");
            }
        }
        byte[][] bArr = new byte[i4];
        int[] iArr = new int[i4 + i4];
        Segment segment2 = this.head;
        int i7 = 0;
        while (i2 < i) {
            segment2.getClass();
            bArr[i7] = segment2.data;
            i2 += segment2.limit - segment2.pos;
            iArr[i7] = Math.min(i2, i);
            iArr[i7 + i4] = segment2.pos;
            segment2.shared = true;
            i7++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, iArr);
    }

    @Override // okio.Source
    public final long read(Buffer buffer, long j) {
        if (j >= 0) {
            long j2 = this.size;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            buffer.write(this, j);
            return j;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount < 0: "));
    }

    @Override // okio.Sink
    public final void write(Buffer buffer, long j) {
        Segment segment;
        buffer.getClass();
        if (buffer != this) {
            ToxicityDetectionCreateEvent.checkOffsetAndCount(buffer.size, 0L, j);
            while (j > 0) {
                Segment segment2 = buffer.head;
                segment2.getClass();
                int i = segment2.limit;
                segment2.getClass();
                int i2 = i - segment2.pos;
                int i3 = 0;
                if (j < i2) {
                    Segment segment3 = this.head;
                    Segment segment4 = segment3 != null ? segment3.prev : null;
                    int i4 = (int) j;
                    if (segment4 != null && segment4.owner) {
                        if ((segment4.limit + j) - (segment4.shared ? 0 : segment4.pos) <= 8192) {
                            segment2.getClass();
                            segment2.writeTo(segment4, i4);
                            buffer.size -= j;
                            this.size += j;
                            return;
                        }
                    }
                    segment2.getClass();
                    if (i4 <= i2) {
                        if (i4 >= 1024) {
                            segment = segment2.sharedCopy();
                        } else {
                            byte[] bArr = segment2.data;
                            Segment take = SegmentPool.take();
                            byte[] bArr2 = take.data;
                            int i5 = segment2.pos;
                            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$default$ar$ds(bArr, bArr2, i5, i5 + i4);
                            segment = take;
                        }
                        segment.limit = segment.pos + i4;
                        segment2.pos += i4;
                        Segment segment5 = segment2.prev;
                        segment5.getClass();
                        segment5.push$ar$ds(segment);
                        buffer.head = segment;
                    } else {
                        throw new IllegalArgumentException("byteCount out of range");
                    }
                }
                Segment segment6 = buffer.head;
                segment6.getClass();
                int i6 = segment6.limit - segment6.pos;
                buffer.head = segment6.pop();
                Segment segment7 = this.head;
                if (segment7 == null) {
                    this.head = segment6;
                    segment6.prev = segment6;
                    segment6.next = segment6.prev;
                } else {
                    Segment segment8 = segment7.prev;
                    segment8.getClass();
                    segment8.push$ar$ds(segment6);
                    Segment segment9 = segment6.prev;
                    if (segment9 != segment6) {
                        segment9.getClass();
                        if (segment9.owner) {
                            int i7 = segment6.limit - segment6.pos;
                            segment9.getClass();
                            int i8 = 8192 - segment9.limit;
                            segment9.getClass();
                            if (!segment9.shared) {
                                segment9.getClass();
                                i3 = segment9.pos;
                            }
                            if (i7 <= i8 + i3) {
                                segment9.getClass();
                                segment6.writeTo(segment9, i7);
                                segment6.pop();
                                SegmentPool.recycle(segment6);
                            }
                        }
                    } else {
                        throw new IllegalStateException("cannot compact");
                    }
                }
                long j2 = i6;
                buffer.size -= j2;
                this.size += j2;
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }

    public final int read(byte[] bArr, int i, int i2) {
        bArr.getClass();
        ToxicityDetectionCreateEvent.checkOffsetAndCount(bArr.length, i, i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        int i3 = segment.pos;
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds(segment.data, bArr, i, i3, i3 + min);
        int i4 = segment.pos + min;
        segment.pos = i4;
        this.size -= min;
        if (i4 != segment.limit) {
            return min;
        }
        this.head = segment.pop();
        SegmentPool.recycle(segment);
        return min;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public final void flush() {
    }

    public final Buffer write(byte[] bArr) {
        write$ar$ds$66c9c9c2_0(bArr, 0, bArr.length);
        return this;
    }
}
