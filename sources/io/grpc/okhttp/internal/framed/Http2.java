package io.grpc.okhttp.internal.framed;

import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import io.grpc.okhttp.internal.framed.Hpack;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Source;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Http2 implements Variant {
    public static final Logger logger = Logger.getLogger(FrameLogger.class.getName());
    public static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FrameLogger {
        private static final String[] TYPES = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        private static final String[] FLAGS = new String[64];
        private static final String[] BINARY = new String[256];

        static {
            for (int i = 0; i < 256; i++) {
                BINARY[i] = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
            }
            String[] strArr = FLAGS;
            strArr[0] = "";
            strArr[1] = "END_STREAM";
            int[] iArr = {1};
            strArr[8] = "PADDED";
            for (int i2 = 0; i2 <= 0; i2++) {
                int i3 = iArr[i2];
                String[] strArr2 = FLAGS;
                strArr2[i3 | 8] = String.valueOf(strArr2[i3]).concat("|PADDED");
            }
            String[] strArr3 = FLAGS;
            strArr3[4] = "END_HEADERS";
            strArr3[32] = "PRIORITY";
            strArr3[36] = "END_HEADERS|PRIORITY";
            int[] iArr2 = {4, 32, 36};
            for (int i4 = 0; i4 < 3; i4++) {
                int i5 = iArr2[i4];
                for (int i6 = 0; i6 <= 0; i6++) {
                    int i7 = iArr[i6];
                    int i8 = i7 | i5;
                    String[] strArr4 = FLAGS;
                    strArr4[i8] = strArr4[i7] + "|" + strArr4[i5];
                    strArr4[i8 | 8] = strArr4[i7] + "|" + strArr4[i5] + "|PADDED";
                }
            }
            for (int i9 = 0; i9 < 64; i9++) {
                String[] strArr5 = FLAGS;
                if (strArr5[i9] == null) {
                    strArr5[i9] = BINARY[i9];
                }
            }
        }

        FrameLogger() {
        }

        public static String formatHeader(boolean z, int i, int i2, byte b, byte b2) {
            String format;
            String str;
            String str2;
            String str3;
            if (b < 10) {
                format = TYPES[b];
            } else {
                format = String.format("0x%02x", Byte.valueOf(b));
            }
            if (b2 == 0) {
                str = "";
            } else {
                if (b != 2 && b != 3) {
                    if (b != 4 && b != 6) {
                        if (b != 7 && b != 8) {
                            if (b2 < 64) {
                                str2 = FLAGS[b2];
                            } else {
                                str2 = BINARY[b2];
                            }
                            if (b == 5) {
                                if ((b2 & 4) != 0) {
                                    str = str2.replace("HEADERS", "PUSH_PROMISE");
                                }
                                str = str2;
                            } else {
                                if (b == 0 && (b2 & 32) != 0) {
                                    str = str2.replace("PRIORITY", "COMPRESSED");
                                }
                                str = str2;
                            }
                        }
                    } else if (b2 == 1) {
                        str = "ACK";
                    } else {
                        str = BINARY[b2];
                    }
                }
                str = BINARY[b2];
            }
            Locale locale = Locale.US;
            if (true != z) {
                str3 = ">>";
            } else {
                str3 = "<<";
            }
            return String.format(locale, "%s 0x%08x %5d %-13s %s", str3, Integer.valueOf(i), Integer.valueOf(i2), format, str);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Reader implements Closeable {
        private final ContinuationSource continuation;
        public final Hpack.Reader hpackReader;
        public final BufferedSource source;

        public Reader(BufferedSource bufferedSource) {
            this.source = bufferedSource;
            ContinuationSource continuationSource = new ContinuationSource(bufferedSource);
            this.continuation = continuationSource;
            this.hpackReader = new Hpack.Reader(continuationSource);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            this.source.close();
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x00c6, code lost:
        
            throw new java.io.IOException(_COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(r3, "Invalid dynamic table size update "));
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List readHeaderBlock(int r2, short r3, byte r4, int r5) {
            /*
                Method dump skipped, instructions count: 280
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.internal.framed.Http2.Reader.readHeaderBlock(int, short, byte, int):java.util.List");
        }

        public final void readPriority$ar$class_merging$ar$ds() {
            this.source.readInt();
            this.source.readByte();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Writer implements FrameWriter {
        private boolean closed;
        private final Buffer hpackBuffer;
        private final Hpack.Writer hpackWriter;
        private int maxFrameSize;
        private final BufferedSink sink;

        public Writer(BufferedSink bufferedSink) {
            this.sink = bufferedSink;
            Buffer buffer = new Buffer();
            this.hpackBuffer = buffer;
            this.hpackWriter = new Hpack.Writer(buffer);
            this.maxFrameSize = 16384;
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void ackSettings(Settings settings) {
            if (!this.closed) {
                int i = this.maxFrameSize;
                if ((settings.set & 32) != 0) {
                    i = ((int[]) settings.Settings$ar$values)[5];
                }
                this.maxFrameSize = i;
                frameHeader(0, 0, (byte) 4, (byte) 1);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final synchronized void close() {
            this.closed = true;
            this.sink.close();
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void connectionPreface() {
            if (!this.closed) {
                if (Http2.logger.isLoggable(Level.FINE)) {
                    Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "connectionPreface", String.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.hex()));
                }
                this.sink.write$ar$ds$c3288001_0(Http2.CONNECTION_PREFACE.toByteArray());
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void data(boolean z, int i, Buffer buffer, int i2) {
            if (!this.closed) {
                frameHeader(i, i2, (byte) 0, z ? (byte) 1 : (byte) 0);
                if (i2 > 0) {
                    this.sink.write(buffer, i2);
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void flush() {
            if (!this.closed) {
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        final void frameHeader(int i, int i2, byte b, byte b2) {
            if (Http2.logger.isLoggable(Level.FINE)) {
                Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$Writer", "frameHeader", FrameLogger.formatHeader(false, i, i2, b, b2));
            }
            int i3 = this.maxFrameSize;
            if (i2 <= i3) {
                if ((Integer.MIN_VALUE & i) == 0) {
                    BufferedSink bufferedSink = this.sink;
                    bufferedSink.writeByte$ar$ds$1b66c408_0((i2 >>> 16) & PrivateKeyType.INVALID);
                    bufferedSink.writeByte$ar$ds$1b66c408_0((i2 >>> 8) & PrivateKeyType.INVALID);
                    bufferedSink.writeByte$ar$ds$1b66c408_0(i2 & PrivateKeyType.INVALID);
                    this.sink.writeByte$ar$ds$1b66c408_0(b);
                    this.sink.writeByte$ar$ds$1b66c408_0(b2);
                    this.sink.writeInt$ar$ds$7d1bee7_0(i & Preference.DEFAULT_ORDER);
                    return;
                }
                throw Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
            }
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void goAway$ar$ds(ErrorCode errorCode, byte[] bArr) {
            if (!this.closed) {
                if (errorCode.httpCode != -1) {
                    frameHeader(0, 8, (byte) 7, (byte) 0);
                    this.sink.writeInt$ar$ds$7d1bee7_0(0);
                    this.sink.writeInt$ar$ds$7d1bee7_0(errorCode.httpCode);
                    this.sink.flush();
                } else {
                    throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final int maxDataLength() {
            return this.maxFrameSize;
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void ping(boolean z, int i, int i2) {
            if (!this.closed) {
                frameHeader(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
                this.sink.writeInt$ar$ds$7d1bee7_0(i);
                this.sink.writeInt$ar$ds$7d1bee7_0(i2);
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void rstStream(int i, ErrorCode errorCode) {
            if (!this.closed) {
                if (errorCode.httpCode != -1) {
                    frameHeader(i, 4, (byte) 3, (byte) 0);
                    this.sink.writeInt$ar$ds$7d1bee7_0(errorCode.httpCode);
                    this.sink.flush();
                } else {
                    throw new IllegalArgumentException();
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void settings(Settings settings) {
            int i;
            if (!this.closed) {
                int i2 = 0;
                frameHeader(0, Integer.bitCount(settings.set) * 6, (byte) 4, (byte) 0);
                while (i2 < 10) {
                    if (settings.isSet(i2)) {
                        if (i2 == 4) {
                            int i3 = i2;
                            i2 = 3;
                            i = i3;
                        } else {
                            i = 7;
                            if (i2 == 7) {
                                i2 = 4;
                            } else {
                                i = i2;
                            }
                        }
                        this.sink.writeShort$ar$ds$a45469cc_0(i2);
                        this.sink.writeInt$ar$ds$7d1bee7_0(settings.get(i));
                        i2 = i;
                    }
                    i2++;
                }
                this.sink.flush();
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void synStream$ar$ds(boolean z, int i, List list) {
            int i2;
            byte b;
            int i3;
            int i4;
            if (!this.closed) {
                Hpack.Writer writer = this.hpackWriter;
                int size = list.size();
                for (int i5 = 0; i5 < size; i5++) {
                    Header header = (Header) list.get(i5);
                    ByteString asciiLowercase = header.name.toAsciiLowercase();
                    ByteString byteString = header.value;
                    Integer num = (Integer) Hpack.NAME_TO_FIRST_INDEX.get(asciiLowercase);
                    if (num != null) {
                        int intValue = num.intValue();
                        i4 = intValue + 1;
                        if (i4 >= 2 && i4 <= 7) {
                            if (Hpack.STATIC_HEADER_TABLE[intValue].value.equals(byteString)) {
                                i3 = i4;
                            } else if (Hpack.STATIC_HEADER_TABLE[i4].value.equals(byteString)) {
                                i4 = intValue + 2;
                                i3 = i4;
                            }
                        }
                        i3 = i4;
                        i4 = -1;
                    } else {
                        i3 = -1;
                        i4 = -1;
                    }
                    if (i4 == -1) {
                        int i6 = writer.nextDynamicTableIndex + 1;
                        while (true) {
                            Header[] headerArr = writer.dynamicTable;
                            if (i6 < headerArr.length) {
                                if (headerArr[i6].name.equals(asciiLowercase)) {
                                    if (writer.dynamicTable[i6].value.equals(byteString)) {
                                        int i7 = i6 - writer.nextDynamicTableIndex;
                                        int length = Hpack.STATIC_HEADER_TABLE.length;
                                        i4 = i7 + 61;
                                        break;
                                    } else if (i3 == -1) {
                                        int i8 = i6 - writer.nextDynamicTableIndex;
                                        int length2 = Hpack.STATIC_HEADER_TABLE.length;
                                        i3 = i8 + 61;
                                    }
                                }
                                i6++;
                            } else {
                                i4 = -1;
                                break;
                            }
                        }
                    }
                    if (i4 != -1) {
                        writer.writeInt(i4, BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE, BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
                    } else if (i3 == -1) {
                        writer.out.writeByte$ar$ds(64);
                        writer.writeByteString(asciiLowercase);
                        writer.writeByteString(byteString);
                        writer.insertIntoDynamicTable(header);
                    } else {
                        ByteString byteString2 = Hpack.PSEUDO_PREFIX;
                        byteString2.getClass();
                        if (asciiLowercase.rangeEquals$ar$ds(byteString2, byteString2.getSize$third_party_java_src_okio_okio_jvm()) && !Header.TARGET_AUTHORITY.equals(asciiLowercase)) {
                            writer.writeInt(i3, 15, 0);
                            writer.writeByteString(byteString);
                        } else {
                            writer.writeInt(i3, 63, 64);
                            writer.writeByteString(byteString);
                            writer.insertIntoDynamicTable(header);
                        }
                    }
                }
                long j = this.hpackBuffer.size;
                int min = (int) Math.min(this.maxFrameSize, j);
                long j2 = min;
                if (j == j2) {
                    i2 = 4;
                } else {
                    i2 = 0;
                }
                if (z) {
                    i2 |= 1;
                }
                frameHeader(i, min, (byte) 1, (byte) i2);
                this.sink.write(this.hpackBuffer, j2);
                if (j > j2) {
                    long j3 = j - j2;
                    while (j3 > 0) {
                        int min2 = (int) Math.min(this.maxFrameSize, j3);
                        long j4 = min2;
                        j3 -= j4;
                        if (j3 == 0) {
                            b = 4;
                        } else {
                            b = 0;
                        }
                        frameHeader(i, min2, (byte) 9, b);
                        this.sink.write(this.hpackBuffer, j4);
                    }
                }
            } else {
                throw new IOException("closed");
            }
        }

        @Override // io.grpc.okhttp.internal.framed.FrameWriter
        public final synchronized void windowUpdate(int i, long j) {
            if (!this.closed) {
                if (j != 0) {
                    frameHeader(i, 4, (byte) 8, (byte) 0);
                    this.sink.writeInt$ar$ds$7d1bee7_0((int) j);
                    this.sink.flush();
                } else {
                    throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", 0L);
                }
            } else {
                throw new IOException("closed");
            }
        }
    }

    public static IllegalArgumentException illegalArgument(String str, Object... objArr) {
        throw new IllegalArgumentException(String.format(Locale.US, str, objArr));
    }

    public static IOException ioException(String str, Object... objArr) {
        throw new IOException(String.format(Locale.US, str, objArr));
    }

    public static int lengthWithoutPadding(int i, byte b, short s) {
        if ((b & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return i - s;
        }
        throw ioException("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
    }

    public static int readMedium(BufferedSource bufferedSource) {
        return (bufferedSource.readByte() & 255) | ((bufferedSource.readByte() & 255) << 16) | ((bufferedSource.readByte() & 255) << 8);
    }

    @Override // io.grpc.okhttp.internal.framed.Variant
    public final Reader newReader$ar$class_merging$ar$ds(BufferedSource bufferedSource) {
        return new Reader(bufferedSource);
    }

    @Override // io.grpc.okhttp.internal.framed.Variant
    public final FrameWriter newWriter$ar$ds(BufferedSink bufferedSink) {
        return new Writer(bufferedSink);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContinuationSource implements Source {
        byte flags;
        int left;
        int length;
        short padding;
        private final BufferedSource source;
        int streamId;

        public ContinuationSource(BufferedSource bufferedSource) {
            this.source = bufferedSource;
        }

        @Override // okio.Source
        public final long read(Buffer buffer, long j) {
            int i;
            int readInt;
            do {
                int i2 = this.left;
                if (i2 == 0) {
                    this.source.skip(this.padding);
                    this.padding = (short) 0;
                    if ((this.flags & 4) == 0) {
                        i = this.streamId;
                        int readMedium = Http2.readMedium(this.source);
                        this.left = readMedium;
                        this.length = readMedium;
                        int readByte = this.source.readByte() & 255;
                        this.flags = (byte) (this.source.readByte() & 255);
                        byte b = (byte) readByte;
                        if (Http2.logger.isLoggable(Level.FINE)) {
                            Http2.logger.logp(Level.FINE, "io.grpc.okhttp.internal.framed.Http2$ContinuationSource", "readContinuationHeader", FrameLogger.formatHeader(true, this.streamId, this.length, b, this.flags));
                        }
                        readInt = this.source.readInt() & Preference.DEFAULT_ORDER;
                        this.streamId = readInt;
                        if (b != 9) {
                            throw Http2.ioException("%s != TYPE_CONTINUATION", Byte.valueOf(b));
                        }
                    }
                } else {
                    long read = this.source.read(buffer, Math.min(j, i2));
                    if (read != -1) {
                        this.left -= (int) read;
                        return read;
                    }
                }
                return -1L;
            } while (readInt == i);
            throw Http2.ioException("TYPE_CONTINUATION streamId changed", new Object[0]);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }
    }
}
