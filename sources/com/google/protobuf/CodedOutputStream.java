package com.google.protobuf;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.Utf8;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CodedOutputStream extends ByteOutput {
    public ExecutorSelector wrapper$ar$class_merging$36e28e02_0$ar$class_merging;
    private static final Logger logger = Logger.getLogger(CodedOutputStream.class.getName());
    public static final boolean HAS_UNSAFE_ARRAY_OPERATIONS = UnsafeUtil.HAS_UNSAFE_ARRAY_OPERATIONS;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OutOfSpaceException extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        OutOfSpaceException() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        public OutOfSpaceException(String str, Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(String.valueOf(str)), th);
        }

        public OutOfSpaceException(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }
    }

    public static int computeBoolSize$ar$ds(int i) {
        return computeTagSize(i) + 1;
    }

    public static int computeBytesSize(int i, ByteString byteString) {
        return computeTagSize(i) + computeBytesSizeNoTag(byteString);
    }

    public static int computeBytesSizeNoTag(ByteString byteString) {
        return computeLengthDelimitedFieldSize(byteString.size());
    }

    public static int computeDoubleSize$ar$ds(int i) {
        return computeTagSize(i) + 8;
    }

    public static int computeEnumSize(int i, int i2) {
        return computeTagSize(i) + computeInt32SizeNoTag(i2);
    }

    public static int computeFixed32Size$ar$ds(int i) {
        return computeTagSize(i) + 4;
    }

    public static int computeFixed64Size$ar$ds(int i) {
        return computeTagSize(i) + 8;
    }

    public static int computeFloatSize$ar$ds(int i) {
        return computeTagSize(i) + 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int computeGroupSize(int i, MessageLite messageLite, Schema schema) {
        int computeTagSize = computeTagSize(i);
        return computeTagSize + computeTagSize + ((AbstractMessageLite) messageLite).getSerializedSize(schema);
    }

    public static int computeInt32Size(int i, int i2) {
        return computeTagSize(i) + computeInt32SizeNoTag(i2);
    }

    public static int computeInt32SizeNoTag(int i) {
        return computeUInt64SizeNoTag(i);
    }

    public static int computeInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    public static int computeLazyFieldSize(int i, LazyFieldLite lazyFieldLite) {
        return computeTagSize(i) + computeLazyFieldSizeNoTag(lazyFieldLite);
    }

    public static int computeLazyFieldSizeNoTag(LazyFieldLite lazyFieldLite) {
        int i;
        if (lazyFieldLite.memoizedBytes != null) {
            i = lazyFieldLite.memoizedBytes.size();
        } else if (lazyFieldLite.value != null) {
            i = lazyFieldLite.value.getSerializedSize();
        } else {
            i = 0;
        }
        return computeLengthDelimitedFieldSize(i);
    }

    public static int computeLengthDelimitedFieldSize(int i) {
        return computeUInt32SizeNoTag(i) + i;
    }

    public static int computeMessageSizeNoTag(MessageLite messageLite) {
        return computeLengthDelimitedFieldSize(messageLite.getSerializedSize());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computePreferredBufferSize(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int computeSFixed32Size$ar$ds(int i) {
        return computeTagSize(i) + 4;
    }

    public static int computeSFixed64Size$ar$ds(int i) {
        return computeTagSize(i) + 8;
    }

    public static int computeSInt32Size(int i, int i2) {
        return computeTagSize(i) + computeSInt32SizeNoTag(i2);
    }

    public static int computeSInt32SizeNoTag(int i) {
        return computeUInt32SizeNoTag(encodeZigZag32(i));
    }

    public static int computeSInt64Size(int i, long j) {
        return computeTagSize(i) + computeSInt64SizeNoTag(j);
    }

    public static int computeSInt64SizeNoTag(long j) {
        return computeUInt64SizeNoTag(encodeZigZag64(j));
    }

    public static int computeStringSize(int i, String str) {
        return computeTagSize(i) + computeStringSizeNoTag(str);
    }

    public static int computeStringSizeNoTag(String str) {
        int length;
        try {
            length = Utf8.encodedLength(str);
        } catch (Utf8.UnpairedSurrogateException unused) {
            length = str.getBytes(Internal.UTF_8).length;
        }
        return computeLengthDelimitedFieldSize(length);
    }

    public static int computeTagSize(int i) {
        return computeUInt32SizeNoTag(WireFormat.makeTag(i, 0));
    }

    public static int computeUInt32Size(int i, int i2) {
        return computeTagSize(i) + computeUInt32SizeNoTag(i2);
    }

    public static int computeUInt32SizeNoTag(int i) {
        return (352 - (Integer.numberOfLeadingZeros(i) * 9)) >>> 6;
    }

    public static int computeUInt64Size(int i, long j) {
        return computeTagSize(i) + computeUInt64SizeNoTag(j);
    }

    public static int computeUInt64SizeNoTag(long j) {
        return (640 - (Long.numberOfLeadingZeros(j) * 9)) >>> 6;
    }

    public static int encodeZigZag32(int i) {
        return (i >> 31) ^ (i + i);
    }

    public static long encodeZigZag64(long j) {
        return (j >> 63) ^ (j + j);
    }

    public static CodedOutputStream newInstance(byte[] bArr) {
        return new ArrayEncoder(bArr, 0, bArr.length);
    }

    public final void checkNoSpaceLeft() {
        if (spaceLeft() == 0) {
        } else {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void flush();

    final void inefficientWriteStringNoTag(String str, Utf8.UnpairedSurrogateException unpairedSurrogateException) {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) unpairedSurrogateException);
        byte[] bytes = str.getBytes(Internal.UTF_8);
        try {
            int length = bytes.length;
            writeUInt32NoTag(length);
            writeLazy(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new OutOfSpaceException(e);
        }
    }

    public abstract int spaceLeft();

    public abstract void write(byte b);

    public abstract void writeBool(int i, boolean z);

    public abstract void writeByteArrayNoTag$ar$ds(byte[] bArr, int i);

    public abstract void writeBytes(int i, ByteString byteString);

    public abstract void writeBytesNoTag(ByteString byteString);

    public final void writeDouble(int i, double d) {
        writeFixed64(i, Double.doubleToRawLongBits(d));
    }

    public final void writeDoubleNoTag(double d) {
        writeFixed64NoTag(Double.doubleToRawLongBits(d));
    }

    public abstract void writeFixed32(int i, int i2);

    public abstract void writeFixed32NoTag(int i);

    public abstract void writeFixed64(int i, long j);

    public abstract void writeFixed64NoTag(long j);

    public final void writeFloat(int i, float f) {
        writeFixed32(i, Float.floatToRawIntBits(f));
    }

    public final void writeFloatNoTag(float f) {
        writeFixed32NoTag(Float.floatToRawIntBits(f));
    }

    @Deprecated
    public final void writeGroupNoTag(MessageLite messageLite) {
        messageLite.writeTo(this);
    }

    public abstract void writeInt32(int i, int i2);

    public abstract void writeInt32NoTag(int i);

    @Override // com.google.protobuf.ByteOutput
    public abstract void writeLazy(byte[] bArr, int i, int i2);

    public abstract void writeMessage(int i, MessageLite messageLite, Schema schema);

    public abstract void writeMessageNoTag(MessageLite messageLite);

    public abstract void writeMessageSetExtension(int i, MessageLite messageLite);

    public abstract void writeRawMessageSetExtension(int i, ByteString byteString);

    public final void writeSInt32(int i, int i2) {
        writeUInt32(i, encodeZigZag32(i2));
    }

    public final void writeSInt32NoTag(int i) {
        writeUInt32NoTag(encodeZigZag32(i));
    }

    public final void writeSInt64(int i, long j) {
        writeUInt64(i, encodeZigZag64(j));
    }

    public final void writeSInt64NoTag(long j) {
        writeUInt64NoTag(encodeZigZag64(j));
    }

    public abstract void writeString(int i, String str);

    public abstract void writeStringNoTag(String str);

    public abstract void writeTag(int i, int i2);

    public abstract void writeUInt32(int i, int i2);

    public abstract void writeUInt32NoTag(int i);

    public abstract void writeUInt64(int i, long j);

    public abstract void writeUInt64NoTag(long j);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AbstractBufferedEncoder extends CodedOutputStream {
        final byte[] buffer;
        final int limit;
        private final OutputStream out;
        int position;
        int totalBytesWritten;

        public AbstractBufferedEncoder(int i) {
            if (i >= 0) {
                byte[] bArr = new byte[Math.max(i, 20)];
                this.buffer = bArr;
                this.limit = bArr.length;
                return;
            }
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }

        private final void doFlush() {
            this.out.write(this.buffer, 0, this.position);
            this.position = 0;
        }

        private final void flushIfNotAvailable(int i) {
            if (this.limit - this.position < i) {
                doFlush();
            }
        }

        final void buffer(byte b) {
            int i = this.position;
            this.position = i + 1;
            this.buffer[i] = b;
            this.totalBytesWritten++;
        }

        final void bufferFixed32NoTag(int i) {
            int i2 = this.position;
            byte b = (byte) (i & PrivateKeyType.INVALID);
            byte[] bArr = this.buffer;
            bArr[i2] = b;
            bArr[i2 + 1] = (byte) ((i >> 8) & PrivateKeyType.INVALID);
            bArr[i2 + 2] = (byte) ((i >> 16) & PrivateKeyType.INVALID);
            this.position = i2 + 4;
            bArr[i2 + 3] = (byte) ((i >> 24) & PrivateKeyType.INVALID);
            this.totalBytesWritten += 4;
        }

        final void bufferFixed64NoTag(long j) {
            int i = this.position;
            byte[] bArr = this.buffer;
            bArr[i] = (byte) (j & 255);
            bArr[i + 1] = (byte) ((j >> 8) & 255);
            bArr[i + 2] = (byte) ((j >> 16) & 255);
            bArr[i + 3] = (byte) (255 & (j >> 24));
            bArr[i + 4] = (byte) (((int) (j >> 32)) & PrivateKeyType.INVALID);
            bArr[i + 5] = (byte) (((int) (j >> 40)) & PrivateKeyType.INVALID);
            bArr[i + 6] = (byte) (((int) (j >> 48)) & PrivateKeyType.INVALID);
            this.position = i + 8;
            bArr[i + 7] = (byte) (((int) (j >> 56)) & PrivateKeyType.INVALID);
            this.totalBytesWritten += 8;
        }

        final void bufferTag(int i, int i2) {
            bufferUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        final void bufferUInt32NoTag(int i) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j = this.position;
                while ((i & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    UnsafeUtil.putByte(bArr, i2, (byte) ((i | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID));
                    i >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                UnsafeUtil.putByte(bArr2, i3, (byte) i);
                this.totalBytesWritten += (int) (this.position - j);
                return;
            }
            while ((i & (-128)) != 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) ((i | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID);
                this.totalBytesWritten++;
                i >>>= 7;
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            bArr4[i5] = (byte) i;
            this.totalBytesWritten++;
        }

        final void bufferUInt64NoTag(long j) {
            if (CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS) {
                long j2 = this.position;
                while (true) {
                    int i = (int) j;
                    if ((j & (-128)) == 0) {
                        byte[] bArr = this.buffer;
                        int i2 = this.position;
                        this.position = i2 + 1;
                        UnsafeUtil.putByte(bArr, i2, (byte) i);
                        this.totalBytesWritten += (int) (this.position - j2);
                        return;
                    }
                    byte[] bArr2 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    UnsafeUtil.putByte(bArr2, i3, (byte) ((i | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID));
                    j >>>= 7;
                }
            } else {
                while (true) {
                    int i4 = (int) j;
                    if ((j & (-128)) == 0) {
                        byte[] bArr3 = this.buffer;
                        int i5 = this.position;
                        this.position = i5 + 1;
                        bArr3[i5] = (byte) i4;
                        this.totalBytesWritten++;
                        return;
                    }
                    byte[] bArr4 = this.buffer;
                    int i6 = this.position;
                    this.position = i6 + 1;
                    bArr4[i6] = (byte) ((i4 | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID);
                    this.totalBytesWritten++;
                    j >>>= 7;
                }
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void flush() {
            if (this.position > 0) {
                doFlush();
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void write(byte b) {
            if (this.position == this.limit) {
                doFlush();
            }
            buffer(b);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBool(int i, boolean z) {
            flushIfNotAvailable(11);
            bufferTag(i, 0);
            buffer(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArrayNoTag$ar$ds(byte[] bArr, int i) {
            writeUInt32NoTag(i);
            write(bArr, 0, i);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytes(int i, ByteString byteString) {
            writeTag(i, 2);
            writeBytesNoTag(byteString);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytesNoTag(ByteString byteString) {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32(int i, int i2) {
            flushIfNotAvailable(14);
            bufferTag(i, 5);
            bufferFixed32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32NoTag(int i) {
            flushIfNotAvailable(4);
            bufferFixed32NoTag(i);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64(int i, long j) {
            flushIfNotAvailable(18);
            bufferTag(i, 1);
            bufferFixed64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64NoTag(long j) {
            flushIfNotAvailable(8);
            bufferFixed64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32(int i, int i2) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            if (i2 >= 0) {
                bufferUInt32NoTag(i2);
            } else {
                bufferUInt64NoTag(i2);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32NoTag(int i) {
            if (i >= 0) {
                writeUInt32NoTag(i);
            } else {
                writeUInt64NoTag(i);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void writeLazy(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessage(int i, MessageLite messageLite, Schema schema) {
            writeTag(i, 2);
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(messageLite, this.wrapper$ar$class_merging$36e28e02_0$ar$class_merging);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageNoTag(MessageLite messageLite) {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageSetExtension(int i, MessageLite messageLite) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeTag(3, 2);
            writeMessageNoTag(messageLite);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeRawMessageSetExtension(int i, ByteString byteString) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeString(int i, String str) {
            writeTag(i, 2);
            writeStringNoTag(str);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeStringNoTag(String str) {
            int encodedLength;
            try {
                int length = str.length() * 3;
                int computeUInt32SizeNoTag = computeUInt32SizeNoTag(length);
                int i = computeUInt32SizeNoTag + length;
                int i2 = this.limit;
                if (i > i2) {
                    byte[] bArr = new byte[length];
                    int encode = Utf8.encode(str, bArr, 0, length);
                    writeUInt32NoTag(encode);
                    write(bArr, 0, encode);
                    return;
                }
                if (i > i2 - this.position) {
                    doFlush();
                }
                int computeUInt32SizeNoTag2 = computeUInt32SizeNoTag(str.length());
                int i3 = this.position;
                try {
                    if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                        int i4 = i3 + computeUInt32SizeNoTag2;
                        this.position = i4;
                        int encode2 = Utf8.encode(str, this.buffer, i4, this.limit - i4);
                        this.position = i3;
                        encodedLength = (encode2 - i3) - computeUInt32SizeNoTag2;
                        bufferUInt32NoTag(encodedLength);
                        this.position = encode2;
                    } else {
                        encodedLength = Utf8.encodedLength(str);
                        bufferUInt32NoTag(encodedLength);
                        this.position = Utf8.encode(str, this.buffer, this.position, encodedLength);
                    }
                    this.totalBytesWritten += encodedLength;
                } catch (Utf8.UnpairedSurrogateException e) {
                    this.totalBytesWritten -= this.position - i3;
                    this.position = i3;
                    throw e;
                } catch (ArrayIndexOutOfBoundsException e2) {
                    throw new OutOfSpaceException(e2);
                }
            } catch (Utf8.UnpairedSurrogateException e3) {
                inefficientWriteStringNoTag(str, e3);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeTag(int i, int i2) {
            writeUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32(int i, int i2) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferUInt32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int i) {
            flushIfNotAvailable(5);
            bufferUInt32NoTag(i);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64(int i, long j) {
            flushIfNotAvailable(20);
            bufferTag(i, 0);
            bufferUInt64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64NoTag(long j) {
            flushIfNotAvailable(10);
            bufferUInt64NoTag(j);
        }

        public final void write(byte[] bArr, int i, int i2) {
            int i3 = this.limit;
            int i4 = this.position;
            int i5 = i3 - i4;
            if (i5 >= i2) {
                System.arraycopy(bArr, i, this.buffer, i4, i2);
                this.position += i2;
                this.totalBytesWritten += i2;
                return;
            }
            System.arraycopy(bArr, i, this.buffer, i4, i5);
            int i6 = i + i5;
            this.position = this.limit;
            this.totalBytesWritten += i5;
            doFlush();
            int i7 = i2 - i5;
            if (i7 <= this.limit) {
                System.arraycopy(bArr, i6, this.buffer, 0, i7);
                this.position = i7;
            } else {
                this.out.write(bArr, i6, i7);
            }
            this.totalBytesWritten += i7;
        }

        public AbstractBufferedEncoder(OutputStream outputStream, int i) {
            this(i);
            if (outputStream != null) {
                this.out = outputStream;
                return;
            }
            throw new NullPointerException("out");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int computeMessageSizeNoTag(MessageLite messageLite, Schema schema) {
        return computeLengthDelimitedFieldSize(((AbstractMessageLite) messageLite).getSerializedSize(schema));
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ArrayEncoder extends CodedOutputStream {
        private final byte[] buffer;
        private final int limit;
        private int position;

        public ArrayEncoder(byte[] bArr, int i, int i2) {
            if (bArr != null) {
                int i3 = i + i2;
                int length = bArr.length;
                if ((i | i2 | (length - i3)) >= 0) {
                    this.buffer = bArr;
                    this.position = i;
                    this.limit = i3;
                    return;
                }
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            throw new NullPointerException("buffer");
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final int spaceLeft() {
            return this.limit - this.position;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void write(byte b) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBool(int i, boolean z) {
            writeTag(i, 0);
            write(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeByteArrayNoTag$ar$ds(byte[] bArr, int i) {
            writeUInt32NoTag(i);
            write(bArr, 0, i);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytes(int i, ByteString byteString) {
            writeTag(i, 2);
            writeBytesNoTag(byteString);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeBytesNoTag(ByteString byteString) {
            writeUInt32NoTag(byteString.size());
            byteString.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32(int i, int i2) {
            writeTag(i, 5);
            writeFixed32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed32NoTag(int i) {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                bArr[i2] = (byte) (i & PrivateKeyType.INVALID);
                bArr[i2 + 1] = (byte) ((i >> 8) & PrivateKeyType.INVALID);
                bArr[i2 + 2] = (byte) ((i >> 16) & PrivateKeyType.INVALID);
                this.position = i2 + 4;
                bArr[i2 + 3] = (byte) ((i >> 24) & PrivateKeyType.INVALID);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64(int i, long j) {
            writeTag(i, 1);
            writeFixed64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeFixed64NoTag(long j) {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                bArr[i] = (byte) (((int) j) & PrivateKeyType.INVALID);
                bArr[i + 1] = (byte) (((int) (j >> 8)) & PrivateKeyType.INVALID);
                bArr[i + 2] = (byte) (((int) (j >> 16)) & PrivateKeyType.INVALID);
                bArr[i + 3] = (byte) (((int) (j >> 24)) & PrivateKeyType.INVALID);
                bArr[i + 4] = (byte) (((int) (j >> 32)) & PrivateKeyType.INVALID);
                bArr[i + 5] = (byte) (((int) (j >> 40)) & PrivateKeyType.INVALID);
                bArr[i + 6] = (byte) (((int) (j >> 48)) & PrivateKeyType.INVALID);
                this.position = i + 8;
                bArr[i + 7] = (byte) (((int) (j >> 56)) & PrivateKeyType.INVALID);
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32(int i, int i2) {
            writeTag(i, 0);
            writeInt32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeInt32NoTag(int i) {
            if (i >= 0) {
                writeUInt32NoTag(i);
            } else {
                writeUInt64NoTag(i);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream, com.google.protobuf.ByteOutput
        public final void writeLazy(byte[] bArr, int i, int i2) {
            write(bArr, i, i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessage(int i, MessageLite messageLite, Schema schema) {
            writeTag(i, 2);
            writeUInt32NoTag(((AbstractMessageLite) messageLite).getSerializedSize(schema));
            schema.writeTo$ar$class_merging$d1b76bae_0$ar$class_merging$ar$class_merging(messageLite, this.wrapper$ar$class_merging$36e28e02_0$ar$class_merging);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageNoTag(MessageLite messageLite) {
            writeUInt32NoTag(messageLite.getSerializedSize());
            messageLite.writeTo(this);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeMessageSetExtension(int i, MessageLite messageLite) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeTag(3, 2);
            writeMessageNoTag(messageLite);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeRawMessageSetExtension(int i, ByteString byteString) {
            writeTag(1, 3);
            writeUInt32(2, i);
            writeBytes(3, byteString);
            writeTag(1, 4);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeString(int i, String str) {
            writeTag(i, 2);
            writeStringNoTag(str);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeStringNoTag(String str) {
            int i = this.position;
            try {
                int computeUInt32SizeNoTag = computeUInt32SizeNoTag(str.length() * 3);
                int computeUInt32SizeNoTag2 = computeUInt32SizeNoTag(str.length());
                if (computeUInt32SizeNoTag2 == computeUInt32SizeNoTag) {
                    int i2 = i + computeUInt32SizeNoTag2;
                    this.position = i2;
                    int encode = Utf8.encode(str, this.buffer, i2, spaceLeft());
                    this.position = i;
                    writeUInt32NoTag((encode - i) - computeUInt32SizeNoTag2);
                    this.position = encode;
                    return;
                }
                writeUInt32NoTag(Utf8.encodedLength(str));
                this.position = Utf8.encode(str, this.buffer, this.position, spaceLeft());
            } catch (Utf8.UnpairedSurrogateException e) {
                this.position = i;
                inefficientWriteStringNoTag(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new OutOfSpaceException(e2);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeTag(int i, int i2) {
            writeUInt32NoTag(WireFormat.makeTag(i, i2));
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32(int i, int i2) {
            writeTag(i, 0);
            writeUInt32NoTag(i2);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt32NoTag(int i) {
            while ((i & (-128)) != 0) {
                try {
                    byte[] bArr = this.buffer;
                    int i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID);
                    i >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr2 = this.buffer;
            int i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) i;
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64(int i, long j) {
            writeTag(i, 0);
            writeUInt64NoTag(j);
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void writeUInt64NoTag(long j) {
            if (!CodedOutputStream.HAS_UNSAFE_ARRAY_OPERATIONS || spaceLeft() < 10) {
                while ((j & (-128)) != 0) {
                    try {
                        byte[] bArr = this.buffer;
                        int i = this.position;
                        this.position = i + 1;
                        bArr[i] = (byte) ((((int) j) | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID);
                        j >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                    }
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) j;
                return;
            }
            while (true) {
                int i3 = (int) j;
                if ((j & (-128)) == 0) {
                    byte[] bArr3 = this.buffer;
                    int i4 = this.position;
                    this.position = i4 + 1;
                    UnsafeUtil.putByte(bArr3, i4, (byte) i3);
                    return;
                }
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                UnsafeUtil.putByte(bArr4, i5, (byte) ((i3 | BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) & PrivateKeyType.INVALID));
                j >>>= 7;
            }
        }

        public final void write(byte[] bArr, int i, int i2) {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new OutOfSpaceException(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.protobuf.CodedOutputStream
        public final void flush() {
        }
    }
}
