package com.google.protobuf;

import _COROUTINE._BOUNDARY;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import io.grpc.okhttp.internal.framed.Settings;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CodedInputStream {
    public static final /* synthetic */ int CodedInputStream$ar$NoOp = 0;
    private static volatile int defaultRecursionLimit = 100;
    public int recursionDepth;
    public int recursionLimit = defaultRecursionLimit;
    public int sizeLimit = Preference.DEFAULT_ORDER;
    public Settings wrapper$ar$class_merging;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit = Preference.DEFAULT_ORDER;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        public ArrayDecoder(byte[] bArr, int i, int i2) {
            this.buffer = bArr;
            this.limit = i2 + i;
            this.pos = i;
            this.startPos = i;
        }

        private final void recomputeBufferSizeAfterLimit() {
            int i = this.limit + this.bufferSizeAfterLimit;
            this.limit = i;
            int i2 = i - this.startPos;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.bufferSizeAfterLimit = i4;
                this.limit = i - i4;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void checkLastTagWas(int i) {
            if (this.lastTag == i) {
            } else {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean isAtEnd() {
            if (this.pos == this.limit) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int pushLimit(int i) {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                if (totalBytesRead >= 0) {
                    int i2 = this.currentLimit;
                    if (totalBytesRead <= i2) {
                        this.currentLimit = totalBytesRead;
                        recomputeBufferSizeAfterLimit();
                        return i2;
                    }
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean readBool() {
            if (readRawVarint64() != 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final byte[] readByteArray() {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
                    this.pos += readRawVarint32;
                    return copyFrom;
                }
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return new ByteString.LiteralByteString(readRawBytes(readRawVarint32));
        }

        @Override // com.google.protobuf.CodedInputStream
        public final double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readInt64() {
            return readRawVarint64();
        }

        public final byte readRawByte() {
            int i = this.pos;
            if (i != this.limit) {
                byte[] bArr = this.buffer;
                this.pos = i + 1;
                return bArr[i];
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        public final byte[] readRawBytes(int i) {
            if (i > 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    int i4 = i + i3;
                    this.pos = i4;
                    return Arrays.copyOfRange(this.buffer, i3, i4);
                }
            }
            if (i <= 0) {
                if (i == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        public final int readRawLittleEndian32() {
            int i = this.pos;
            if (this.limit - i >= 4) {
                byte[] bArr = this.buffer;
                this.pos = i + 4;
                int i2 = bArr[i] & 255;
                int i3 = bArr[i + 1] & 255;
                int i4 = bArr[i + 2] & 255;
                return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        public final long readRawLittleEndian64() {
            int i = this.pos;
            if (this.limit - i >= 8) {
                byte[] bArr = this.buffer;
                this.pos = i + 8;
                long j = bArr[i];
                long j2 = bArr[i + 2];
                long j3 = bArr[i + 3];
                return ((bArr[i + 7] & 255) << 56) | (j & 255) | ((bArr[i + 1] & 255) << 8) | ((j2 & 255) << 16) | ((j3 & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readRawVarint32() {
            int i;
            int i2 = this.pos;
            int i3 = this.limit;
            if (i3 != i2) {
                byte[] bArr = this.buffer;
                int i4 = i2 + 1;
                byte b = bArr[i2];
                if (b >= 0) {
                    this.pos = i4;
                    return b;
                }
                if (i3 - i4 >= 9) {
                    int i5 = i2 + 2;
                    int i6 = (bArr[i4] << 7) ^ b;
                    if (i6 < 0) {
                        i = i6 ^ (-128);
                    } else {
                        int i7 = i2 + 3;
                        int i8 = (bArr[i5] << 14) ^ i6;
                        if (i8 >= 0) {
                            i = i8 ^ 16256;
                        } else {
                            int i9 = i2 + 4;
                            int i10 = i8 ^ (bArr[i7] << 21);
                            if (i10 < 0) {
                                i = (-2080896) ^ i10;
                            } else {
                                i7 = i2 + 5;
                                byte b2 = bArr[i9];
                                int i11 = (i10 ^ (b2 << 28)) ^ 266354560;
                                if (b2 < 0) {
                                    i9 = i2 + 6;
                                    if (bArr[i7] < 0) {
                                        i7 = i2 + 7;
                                        if (bArr[i9] < 0) {
                                            i9 = i2 + 8;
                                            if (bArr[i7] < 0) {
                                                i7 = i2 + 9;
                                                if (bArr[i9] < 0) {
                                                    int i12 = i2 + 10;
                                                    if (bArr[i7] >= 0) {
                                                        i5 = i12;
                                                        i = i11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i = i11;
                                }
                                i = i11;
                            }
                            i5 = i9;
                        }
                        i5 = i7;
                    }
                    this.pos = i5;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readRawVarint64() {
            long j;
            long j2;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return b;
                }
                if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    int i5 = (bArr[i3] << 7) ^ b;
                    if (i5 < 0) {
                        j = i5 ^ (-128);
                    } else {
                        int i6 = i + 3;
                        int i7 = (bArr[i4] << 14) ^ i5;
                        if (i7 >= 0) {
                            j = i7 ^ 16256;
                        } else {
                            int i8 = i + 4;
                            int i9 = i7 ^ (bArr[i6] << 21);
                            if (i9 < 0) {
                                long j3 = (-2080896) ^ i9;
                                i4 = i8;
                                j = j3;
                            } else {
                                i6 = i + 5;
                                long j4 = (bArr[i8] << 28) ^ i9;
                                if (j4 >= 0) {
                                    j = j4 ^ 266354560;
                                } else {
                                    i4 = i + 6;
                                    long j5 = (bArr[i6] << 35) ^ j4;
                                    if (j5 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        int i10 = i + 7;
                                        long j6 = j5 ^ (bArr[i4] << 42);
                                        if (j6 >= 0) {
                                            j = j6 ^ 4363953127296L;
                                        } else {
                                            i4 = i + 8;
                                            j5 = j6 ^ (bArr[i10] << 49);
                                            if (j5 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                i10 = i + 9;
                                                long j7 = (j5 ^ (bArr[i4] << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i + 10;
                                                    if (bArr[i10] >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    j = j7;
                                                }
                                            }
                                        }
                                        i4 = i10;
                                    }
                                    j = j5 ^ j2;
                                }
                            }
                        }
                        i4 = i6;
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        final long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                j |= (r3 & Byte.MAX_VALUE) << i;
                if ((readRawByte() & 128) == 0) {
                    return j;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSInt32() {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSInt64() {
            return decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readStringRequireUtf8() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String decodeUtf8 = Utf8.decodeUtf8(this.buffer, i2, readRawVarint32);
                    this.pos += readRawVarint32;
                    return decodeUtf8;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return readRawVarint32;
            }
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean skipField(int i) {
            int readTag;
            int tagWireType = WireFormat.getTagWireType(i);
            int i2 = 0;
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 4) {
                                return false;
                            }
                            if (tagWireType == 5) {
                                skipRawBytes(4);
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        do {
                            readTag = readTag();
                            if (readTag == 0) {
                                break;
                            }
                        } while (skipField(readTag));
                        checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                        return true;
                    }
                    skipRawBytes(readRawVarint32());
                    return true;
                }
                skipRawBytes(8);
                return true;
            }
            if (this.limit - this.pos >= 10) {
                while (i2 < 10) {
                    byte[] bArr = this.buffer;
                    int i3 = this.pos;
                    this.pos = i3 + 1;
                    if (bArr[i3] < 0) {
                        i2++;
                    }
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            }
            while (i2 < 10) {
                if (readRawByte() < 0) {
                    i2++;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            return true;
        }

        public final void skipRawBytes(int i) {
            if (i >= 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i3 + i;
                    return;
                }
            }
            if (i < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class IterableDirectByteBufferDecoder extends CodedInputStream {
        private int bufferSizeAfterCurrentLimit;
        private long currentAddress;
        private ByteBuffer currentByteBuffer;
        private long currentByteBufferLimit;
        private long currentByteBufferPos;
        private long currentByteBufferStartPos;
        private final Iterable input;
        private final Iterator iterator;
        private int lastTag;
        private int totalBufferSize;
        private int currentLimit = Preference.DEFAULT_ORDER;
        private int totalBytesRead = 0;

        public IterableDirectByteBufferDecoder(Iterable iterable, int i) {
            this.totalBufferSize = i;
            this.input = iterable;
            this.iterator = iterable.iterator();
            if (i == 0) {
                this.currentByteBuffer = Internal.EMPTY_BYTE_BUFFER;
                this.currentByteBufferPos = 0L;
                this.currentByteBufferStartPos = 0L;
                this.currentByteBufferLimit = 0L;
                this.currentAddress = 0L;
                return;
            }
            tryGetNextByteBuffer();
        }

        private final long currentRemaining() {
            return this.currentByteBufferLimit - this.currentByteBufferPos;
        }

        private final void getNextByteBuffer() {
            if (this.iterator.hasNext()) {
                tryGetNextByteBuffer();
                return;
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        private final void readRawBytesTo$ar$ds(byte[] bArr, int i) {
            if (i <= remaining()) {
                int i2 = i;
                while (i2 > 0) {
                    if (currentRemaining() == 0) {
                        getNextByteBuffer();
                    }
                    int min = Math.min(i2, (int) currentRemaining());
                    long j = min;
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, i - i2, j);
                    i2 -= min;
                    this.currentByteBufferPos += j;
                }
                return;
            }
            if (i <= 0) {
            } else {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }

        private final void recomputeBufferSizeAfterLimit() {
            int i = this.totalBufferSize + this.bufferSizeAfterCurrentLimit;
            this.totalBufferSize = i;
            int i2 = this.currentLimit;
            if (i > i2) {
                int i3 = i - i2;
                this.bufferSizeAfterCurrentLimit = i3;
                this.totalBufferSize = i - i3;
                return;
            }
            this.bufferSizeAfterCurrentLimit = 0;
        }

        private final int remaining() {
            return (int) (((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos) + this.currentByteBufferStartPos);
        }

        private final void tryGetNextByteBuffer() {
            ByteBuffer byteBuffer = (ByteBuffer) this.iterator.next();
            this.currentByteBuffer = byteBuffer;
            this.totalBytesRead += (int) (this.currentByteBufferPos - this.currentByteBufferStartPos);
            long position = byteBuffer.position();
            this.currentByteBufferPos = position;
            this.currentByteBufferStartPos = position;
            this.currentByteBufferLimit = this.currentByteBuffer.limit();
            long addressOffset = UnsafeUtil.addressOffset(this.currentByteBuffer);
            this.currentAddress = addressOffset;
            this.currentByteBufferPos += addressOffset;
            this.currentByteBufferStartPos += addressOffset;
            this.currentByteBufferLimit += addressOffset;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void checkLastTagWas(int i) {
            if (this.lastTag == i) {
            } else {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int getTotalBytesRead() {
            return (int) ((this.totalBytesRead + this.currentByteBufferPos) - this.currentByteBufferStartPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean isAtEnd() {
            if ((this.totalBytesRead + this.currentByteBufferPos) - this.currentByteBufferStartPos == this.totalBufferSize) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int pushLimit(int i) {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead <= i2) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean readBool() {
            if (readRawVarint64() != 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final byte[] readByteArray() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 >= 0) {
                long j = readRawVarint32;
                if (j <= currentRemaining()) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(this.currentByteBufferPos, bArr, 0L, j);
                    this.currentByteBufferPos += j;
                    return bArr;
                }
            }
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo$ar$ds(bArr2, readRawVarint32);
                return bArr2;
            }
            if (readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                long j3 = readRawVarint32;
                if (j3 <= j - j2) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(j2, bArr, 0L, j3);
                    this.currentByteBufferPos += j3;
                    return new ByteString.LiteralByteString(bArr);
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo$ar$ds(bArr2, readRawVarint32);
                return new ByteString.LiteralByteString(bArr2);
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            if (readRawVarint32 < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readInt64() {
            return readRawVarint64();
        }

        public final byte readRawByte() {
            if (currentRemaining() == 0) {
                getNextByteBuffer();
            }
            long j = this.currentByteBufferPos;
            this.currentByteBufferPos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        public final int readRawLittleEndian32() {
            if (currentRemaining() >= 4) {
                long j = this.currentByteBufferPos;
                this.currentByteBufferPos = 4 + j;
                int i = UnsafeUtil.getByte(j) & 255;
                int i2 = (UnsafeUtil.getByte(1 + j) & 255) << 8;
                return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | i | i2 | ((UnsafeUtil.getByte(2 + j) & 255) << 16);
            }
            int readRawByte = readRawByte() & 255;
            int readRawByte2 = (readRawByte() & 255) << 8;
            return readRawByte | readRawByte2 | ((readRawByte() & 255) << 16) | ((readRawByte() & 255) << 24);
        }

        public final long readRawLittleEndian64() {
            long readRawByte;
            long readRawByte2;
            if (currentRemaining() >= 8) {
                this.currentByteBufferPos = 8 + this.currentByteBufferPos;
                long j = (UnsafeUtil.getByte(2 + r13) & 255) << 16;
                long j2 = (UnsafeUtil.getByte(3 + r13) & 255) << 24;
                long j3 = (UnsafeUtil.getByte(r13 + 4) & 255) << 32;
                long j4 = (UnsafeUtil.getByte(r13 + 5) & 255) << 40;
                long j5 = (UnsafeUtil.getByte(r13 + 6) & 255) << 48;
                readRawByte = (UnsafeUtil.getByte(r13 + 7) & 255) << 56;
                readRawByte2 = (UnsafeUtil.getByte(r13) & 255) | ((UnsafeUtil.getByte(1 + r13) & 255) << 8) | j | j2 | j3 | j4 | j5;
            } else {
                long readRawByte3 = (readRawByte() & 255) << 16;
                long readRawByte4 = (readRawByte() & 255) << 24;
                long readRawByte5 = (readRawByte() & 255) << 32;
                long readRawByte6 = (readRawByte() & 255) << 40;
                long readRawByte7 = (readRawByte() & 255) << 48;
                readRawByte = (readRawByte() & 255) << 56;
                readRawByte2 = (readRawByte() & 255) | ((readRawByte() & 255) << 8) | readRawByte3 | readRawByte4 | readRawByte5 | readRawByte6 | readRawByte7;
            }
            return readRawByte2 | readRawByte;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readRawVarint32() {
            int i;
            long j = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j) {
                long j2 = j + 1;
                byte b = UnsafeUtil.getByte(j);
                if (b >= 0) {
                    this.currentByteBufferPos++;
                    return b;
                }
                if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j3 = 2 + j;
                    int i2 = (UnsafeUtil.getByte(j2) << 7) ^ b;
                    if (i2 < 0) {
                        i = i2 ^ (-128);
                    } else {
                        long j4 = 3 + j;
                        int i3 = (UnsafeUtil.getByte(j3) << 14) ^ i2;
                        if (i3 >= 0) {
                            i = i3 ^ 16256;
                        } else {
                            long j5 = 4 + j;
                            int i4 = i3 ^ (UnsafeUtil.getByte(j4) << 21);
                            if (i4 < 0) {
                                i = (-2080896) ^ i4;
                            } else {
                                j4 = 5 + j;
                                byte b2 = UnsafeUtil.getByte(j5);
                                int i5 = (i4 ^ (b2 << 28)) ^ 266354560;
                                if (b2 < 0) {
                                    j5 = 6 + j;
                                    if (UnsafeUtil.getByte(j4) < 0) {
                                        j4 = 7 + j;
                                        if (UnsafeUtil.getByte(j5) < 0) {
                                            j5 = 8 + j;
                                            if (UnsafeUtil.getByte(j4) < 0) {
                                                j4 = 9 + j;
                                                if (UnsafeUtil.getByte(j5) < 0) {
                                                    long j6 = j + 10;
                                                    if (UnsafeUtil.getByte(j4) >= 0) {
                                                        i = i5;
                                                        j3 = j6;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i = i5;
                                }
                                i = i5;
                            }
                            j3 = j5;
                        }
                        j3 = j4;
                    }
                    this.currentByteBufferPos = j3;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readRawVarint64() {
            long j;
            long j2;
            long j3 = this.currentByteBufferPos;
            if (this.currentByteBufferLimit != j3) {
                long j4 = j3 + 1;
                byte b = UnsafeUtil.getByte(j3);
                if (b >= 0) {
                    this.currentByteBufferPos++;
                    return b;
                }
                if (this.currentByteBufferLimit - this.currentByteBufferPos >= 10) {
                    long j5 = 2 + j3;
                    int i = (UnsafeUtil.getByte(j4) << 7) ^ b;
                    if (i < 0) {
                        j = i ^ (-128);
                    } else {
                        long j6 = 3 + j3;
                        int i2 = (UnsafeUtil.getByte(j5) << 14) ^ i;
                        if (i2 >= 0) {
                            j = i2 ^ 16256;
                        } else {
                            long j7 = 4 + j3;
                            int i3 = i2 ^ (UnsafeUtil.getByte(j6) << 21);
                            if (i3 < 0) {
                                j = (-2080896) ^ i3;
                                j5 = j7;
                            } else {
                                j6 = 5 + j3;
                                long j8 = (UnsafeUtil.getByte(j7) << 28) ^ i3;
                                if (j8 >= 0) {
                                    j = 266354560 ^ j8;
                                } else {
                                    long j9 = 6 + j3;
                                    long j10 = j8 ^ (UnsafeUtil.getByte(j6) << 35);
                                    if (j10 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        long j11 = 7 + j3;
                                        long j12 = j10 ^ (UnsafeUtil.getByte(j9) << 42);
                                        if (j12 >= 0) {
                                            j = 4363953127296L ^ j12;
                                        } else {
                                            j9 = 8 + j3;
                                            j10 = j12 ^ (UnsafeUtil.getByte(j11) << 49);
                                            if (j10 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                j11 = 9 + j3;
                                                long j13 = (j10 ^ (UnsafeUtil.getByte(j9) << 56)) ^ 71499008037633920L;
                                                if (j13 < 0) {
                                                    long j14 = j3 + 10;
                                                    if (UnsafeUtil.getByte(j11) >= 0) {
                                                        j = j13;
                                                        j5 = j14;
                                                    }
                                                } else {
                                                    j = j13;
                                                }
                                            }
                                        }
                                        j5 = j11;
                                    }
                                    j = j2 ^ j10;
                                    j5 = j9;
                                }
                            }
                        }
                        j5 = j6;
                    }
                    this.currentByteBufferPos = j5;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        final long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                j |= (r3 & Byte.MAX_VALUE) << i;
                if ((readRawByte() & 128) == 0) {
                    return j;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSInt32() {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSInt64() {
            return decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                long j3 = readRawVarint32;
                if (j3 <= j - j2) {
                    byte[] bArr = new byte[readRawVarint32];
                    UnsafeUtil.copyMemory(j2, bArr, 0L, j3);
                    String str = new String(bArr, Internal.UTF_8);
                    this.currentByteBufferPos += j3;
                    return str;
                }
            }
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr2 = new byte[readRawVarint32];
                readRawBytesTo$ar$ds(bArr2, readRawVarint32);
                return new String(bArr2, Internal.UTF_8);
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readStringRequireUtf8() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                long j = this.currentByteBufferLimit;
                long j2 = this.currentByteBufferPos;
                long j3 = readRawVarint32;
                if (j3 <= j - j2) {
                    String decodeUtf8 = Utf8.decodeUtf8(this.currentByteBuffer, (int) (j2 - this.currentByteBufferStartPos), readRawVarint32);
                    this.currentByteBufferPos += j3;
                    return decodeUtf8;
                }
            }
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                readRawBytesTo$ar$ds(bArr, readRawVarint32);
                return Utf8.decodeUtf8(bArr, 0, readRawVarint32);
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return readRawVarint32;
            }
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean skipField(int i) {
            int readTag;
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 4) {
                                return false;
                            }
                            if (tagWireType == 5) {
                                skipRawBytes(4);
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        do {
                            readTag = readTag();
                            if (readTag == 0) {
                                break;
                            }
                        } while (skipField(readTag));
                        checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                        return true;
                    }
                    skipRawBytes(readRawVarint32());
                    return true;
                }
                skipRawBytes(8);
                return true;
            }
            for (int i2 = 0; i2 < 10; i2++) {
                if (readRawByte() >= 0) {
                    return true;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        public final void skipRawBytes(int i) {
            if (i >= 0) {
                if (i <= ((this.totalBufferSize - this.totalBytesRead) - this.currentByteBufferPos) + this.currentByteBufferStartPos) {
                    while (i > 0) {
                        if (currentRemaining() == 0) {
                            getNextByteBuffer();
                        }
                        int min = Math.min(i, (int) currentRemaining());
                        i -= min;
                        this.currentByteBufferPos += min;
                    }
                    return;
                }
            }
            if (i < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit = Preference.DEFAULT_ORDER;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private int totalBytesRetired;

        public StreamDecoder(InputStream inputStream) {
            byte[] bArr = Internal.EMPTY_BYTE_ARRAY;
            this.input = inputStream;
            this.buffer = new byte[4096];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        private static int read(InputStream inputStream, byte[] bArr, int i, int i2) {
            try {
                return inputStream.read(bArr, i, i2);
            } catch (InvalidProtocolBufferException e) {
                e.setThrownFromInputStream();
                throw e;
            }
        }

        private final byte[] readRawBytesSlowPath$ar$ds(int i) {
            byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(i);
            if (readRawBytesSlowPathOneChunk != null) {
                return readRawBytesSlowPathOneChunk;
            }
            int i2 = this.pos;
            int i3 = this.bufferSize;
            int i4 = i3 - i2;
            this.totalBytesRetired += i3;
            this.pos = 0;
            this.bufferSize = 0;
            List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(i - i4);
            byte[] bArr = new byte[i];
            System.arraycopy(this.buffer, i2, bArr, 0, i4);
            for (byte[] bArr2 : readRawBytesSlowPathRemainingChunks) {
                int length = bArr2.length;
                System.arraycopy(bArr2, 0, bArr, i4, length);
                i4 += length;
            }
            return bArr;
        }

        private final byte[] readRawBytesSlowPathOneChunk(int i) {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            int i2 = this.totalBytesRetired;
            int i3 = this.pos;
            int i4 = i2 + i3 + i;
            if (i4 - this.sizeLimit <= 0) {
                int i5 = this.currentLimit;
                if (i4 <= i5) {
                    int i6 = this.bufferSize - i3;
                    int i7 = i - i6;
                    if (i7 >= 4096) {
                        try {
                            if (i7 > this.input.available()) {
                                return null;
                            }
                        } catch (InvalidProtocolBufferException e) {
                            e.setThrownFromInputStream();
                            throw e;
                        }
                    }
                    byte[] bArr = new byte[i];
                    System.arraycopy(this.buffer, this.pos, bArr, 0, i6);
                    this.totalBytesRetired += this.bufferSize;
                    this.pos = 0;
                    this.bufferSize = 0;
                    while (i6 < i) {
                        int read = read(this.input, bArr, i6, i - i6);
                        if (read != -1) {
                            this.totalBytesRetired += read;
                            i6 += read;
                        } else {
                            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                        }
                    }
                    return bArr;
                }
                skipRawBytes((i5 - i2) - i3);
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
        }

        private final List readRawBytesSlowPathRemainingChunks(int i) {
            ArrayList arrayList = new ArrayList();
            while (i > 0) {
                int min = Math.min(i, 4096);
                byte[] bArr = new byte[min];
                int i2 = 0;
                while (i2 < min) {
                    int read = this.input.read(bArr, i2, min - i2);
                    if (read != -1) {
                        this.totalBytesRetired += read;
                        i2 += read;
                    } else {
                        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    }
                }
                i -= min;
                arrayList.add(bArr);
            }
            return arrayList;
        }

        private final void recomputeBufferSizeAfterLimit() {
            int i = this.bufferSize + this.bufferSizeAfterLimit;
            this.bufferSize = i;
            int i2 = this.totalBytesRetired + i;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                int i4 = i2 - i3;
                this.bufferSizeAfterLimit = i4;
                this.bufferSize = i - i4;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private final void refillBuffer(int i) {
            if (!tryRefillBuffer(i)) {
                if (i > (this.sizeLimit - this.totalBytesRetired) - this.pos) {
                    throw new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }

        private final boolean tryRefillBuffer(int i) {
            int i2 = this.pos;
            int i3 = i2 + i;
            int i4 = this.bufferSize;
            if (i3 > i4) {
                int i5 = this.sizeLimit;
                int i6 = this.totalBytesRetired;
                if (i > (i5 - i6) - i2 || i6 + i2 + i > this.currentLimit) {
                    return false;
                }
                if (i2 > 0) {
                    if (i4 > i2) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
                    }
                    i6 = this.totalBytesRetired + i2;
                    this.totalBytesRetired = i6;
                    i4 = this.bufferSize - i2;
                    this.bufferSize = i4;
                    this.pos = 0;
                }
                int read = read(this.input, this.buffer, i4, Math.min(4096 - i4, (this.sizeLimit - i6) - i4));
                if (read != 0 && read >= -1 && read <= 4096) {
                    if (read <= 0) {
                        return false;
                    }
                    this.bufferSize += read;
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= i) {
                        return true;
                    }
                    return tryRefillBuffer(i);
                }
                throw new IllegalStateException(String.valueOf(this.input.getClass()) + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
            }
            throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "refillBuffer() called when ", " bytes were already available in buffer"));
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void checkLastTagWas(int i) {
            if (this.lastTag == i) {
            } else {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean isAtEnd() {
            if (this.pos == this.bufferSize && !tryRefillBuffer(1)) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int pushLimit(int i) {
            if (i >= 0) {
                int i2 = i + this.totalBytesRetired + this.pos;
                if (i2 >= 0) {
                    int i3 = this.currentLimit;
                    if (i2 <= i3) {
                        this.currentLimit = i2;
                        recomputeBufferSizeAfterLimit();
                        return i3;
                    }
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean readBool() {
            if (readRawVarint64() != 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final byte[] readByteArray() {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                byte[] copyOfRange = Arrays.copyOfRange(this.buffer, i2, i2 + readRawVarint32);
                this.pos += readRawVarint32;
                return copyOfRange;
            }
            if (readRawVarint32 >= 0) {
                return readRawBytesSlowPath$ar$ds(readRawVarint32);
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
                this.pos += readRawVarint32;
                return copyFrom;
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            if (readRawVarint32 >= 0) {
                byte[] readRawBytesSlowPathOneChunk = readRawBytesSlowPathOneChunk(readRawVarint32);
                if (readRawBytesSlowPathOneChunk != null) {
                    return ByteString.copyFrom(readRawBytesSlowPathOneChunk);
                }
                int i3 = this.pos;
                int i4 = this.bufferSize;
                int i5 = i4 - i3;
                this.totalBytesRetired += i4;
                this.pos = 0;
                this.bufferSize = 0;
                List<byte[]> readRawBytesSlowPathRemainingChunks = readRawBytesSlowPathRemainingChunks(readRawVarint32 - i5);
                byte[] bArr = new byte[readRawVarint32];
                System.arraycopy(this.buffer, i3, bArr, 0, i5);
                for (byte[] bArr2 : readRawBytesSlowPathRemainingChunks) {
                    int length = bArr2.length;
                    System.arraycopy(bArr2, 0, bArr, i5, length);
                    i5 += length;
                }
                return new ByteString.LiteralByteString(bArr);
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readInt64() {
            return readRawVarint64();
        }

        public final byte readRawByte() {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        public final int readRawLittleEndian32() {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            int i2 = bArr[i] & 255;
            int i3 = bArr[i + 1] & 255;
            int i4 = bArr[i + 2] & 255;
            return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
        }

        public final long readRawLittleEndian64() {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            long j = bArr[i];
            long j2 = bArr[i + 2];
            long j3 = bArr[i + 3];
            return ((bArr[i + 7] & 255) << 56) | (j & 255) | ((bArr[i + 1] & 255) << 8) | ((j2 & 255) << 16) | ((j3 & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readRawVarint32() {
            int i;
            int i2 = this.pos;
            int i3 = this.bufferSize;
            if (i3 != i2) {
                byte[] bArr = this.buffer;
                int i4 = i2 + 1;
                byte b = bArr[i2];
                if (b >= 0) {
                    this.pos = i4;
                    return b;
                }
                if (i3 - i4 >= 9) {
                    int i5 = i2 + 2;
                    int i6 = (bArr[i4] << 7) ^ b;
                    if (i6 < 0) {
                        i = i6 ^ (-128);
                    } else {
                        int i7 = i2 + 3;
                        int i8 = (bArr[i5] << 14) ^ i6;
                        if (i8 >= 0) {
                            i = i8 ^ 16256;
                        } else {
                            int i9 = i2 + 4;
                            int i10 = i8 ^ (bArr[i7] << 21);
                            if (i10 < 0) {
                                i = (-2080896) ^ i10;
                            } else {
                                i7 = i2 + 5;
                                byte b2 = bArr[i9];
                                int i11 = (i10 ^ (b2 << 28)) ^ 266354560;
                                if (b2 < 0) {
                                    i9 = i2 + 6;
                                    if (bArr[i7] < 0) {
                                        i7 = i2 + 7;
                                        if (bArr[i9] < 0) {
                                            i9 = i2 + 8;
                                            if (bArr[i7] < 0) {
                                                i7 = i2 + 9;
                                                if (bArr[i9] < 0) {
                                                    int i12 = i2 + 10;
                                                    if (bArr[i7] >= 0) {
                                                        i5 = i12;
                                                        i = i11;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    i = i11;
                                }
                                i = i11;
                            }
                            i5 = i9;
                        }
                        i5 = i7;
                    }
                    this.pos = i5;
                    return i;
                }
            }
            return (int) readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readRawVarint64() {
            long j;
            long j2;
            int i = this.pos;
            int i2 = this.bufferSize;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return b;
                }
                if (i2 - i3 >= 9) {
                    int i4 = i + 2;
                    int i5 = (bArr[i3] << 7) ^ b;
                    if (i5 < 0) {
                        j = i5 ^ (-128);
                    } else {
                        int i6 = i + 3;
                        int i7 = (bArr[i4] << 14) ^ i5;
                        if (i7 >= 0) {
                            j = i7 ^ 16256;
                        } else {
                            int i8 = i + 4;
                            int i9 = i7 ^ (bArr[i6] << 21);
                            if (i9 < 0) {
                                long j3 = (-2080896) ^ i9;
                                i4 = i8;
                                j = j3;
                            } else {
                                i6 = i + 5;
                                long j4 = (bArr[i8] << 28) ^ i9;
                                if (j4 >= 0) {
                                    j = j4 ^ 266354560;
                                } else {
                                    i4 = i + 6;
                                    long j5 = (bArr[i6] << 35) ^ j4;
                                    if (j5 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        int i10 = i + 7;
                                        long j6 = j5 ^ (bArr[i4] << 42);
                                        if (j6 >= 0) {
                                            j = j6 ^ 4363953127296L;
                                        } else {
                                            i4 = i + 8;
                                            j5 = j6 ^ (bArr[i10] << 49);
                                            if (j5 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                i10 = i + 9;
                                                long j7 = (j5 ^ (bArr[i4] << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i + 10;
                                                    if (bArr[i10] >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    j = j7;
                                                }
                                            }
                                        }
                                        i4 = i10;
                                    }
                                    j = j5 ^ j2;
                                }
                            }
                        }
                        i4 = i6;
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        final long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                j |= (r3 & Byte.MAX_VALUE) << i;
                if ((readRawByte() & 128) == 0) {
                    return j;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSInt32() {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSInt64() {
            return decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.bufferSize;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 >= 0) {
                if (readRawVarint32 <= this.bufferSize) {
                    refillBuffer(readRawVarint32);
                    String str2 = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str2;
                }
                return new String(readRawBytesSlowPath$ar$ds(readRawVarint32), Internal.UTF_8);
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readStringRequireUtf8() {
            byte[] readRawBytesSlowPath$ar$ds;
            int readRawVarint32 = readRawVarint32();
            int i = this.pos;
            int i2 = this.bufferSize;
            if (readRawVarint32 <= i2 - i && readRawVarint32 > 0) {
                readRawBytesSlowPath$ar$ds = this.buffer;
                this.pos = i + readRawVarint32;
            } else {
                if (readRawVarint32 == 0) {
                    return "";
                }
                if (readRawVarint32 >= 0) {
                    i = 0;
                    if (readRawVarint32 <= i2) {
                        refillBuffer(readRawVarint32);
                        readRawBytesSlowPath$ar$ds = this.buffer;
                        this.pos = readRawVarint32;
                    } else {
                        readRawBytesSlowPath$ar$ds = readRawBytesSlowPath$ar$ds(readRawVarint32);
                    }
                } else {
                    throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                }
            }
            return Utf8.decodeUtf8(readRawBytesSlowPath$ar$ds, i, readRawVarint32);
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return readRawVarint32;
            }
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean skipField(int i) {
            int readTag;
            int tagWireType = WireFormat.getTagWireType(i);
            int i2 = 0;
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 4) {
                                return false;
                            }
                            if (tagWireType == 5) {
                                skipRawBytes(4);
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        do {
                            readTag = readTag();
                            if (readTag == 0) {
                                break;
                            }
                        } while (skipField(readTag));
                        checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                        return true;
                    }
                    skipRawBytes(readRawVarint32());
                    return true;
                }
                skipRawBytes(8);
                return true;
            }
            if (this.bufferSize - this.pos >= 10) {
                while (i2 < 10) {
                    byte[] bArr = this.buffer;
                    int i3 = this.pos;
                    this.pos = i3 + 1;
                    if (bArr[i3] < 0) {
                        i2++;
                    }
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            }
            while (i2 < 10) {
                if (readRawByte() < 0) {
                    i2++;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            return true;
        }

        public final void skipRawBytes(int i) {
            int i2 = this.bufferSize;
            int i3 = this.pos;
            int i4 = i2 - i3;
            if (i <= i4 && i >= 0) {
                this.pos = i3 + i;
                return;
            }
            if (i >= 0) {
                int i5 = this.totalBytesRetired;
                int i6 = i5 + i3;
                int i7 = this.currentLimit;
                if (i6 + i <= i7) {
                    this.totalBytesRetired = i6;
                    this.bufferSize = 0;
                    this.pos = 0;
                    while (i4 < i) {
                        try {
                            long j = i - i4;
                            try {
                                long skip = this.input.skip(j);
                                if (skip >= 0 && skip <= j) {
                                    if (skip == 0) {
                                        break;
                                    } else {
                                        i4 += (int) skip;
                                    }
                                } else {
                                    throw new IllegalStateException(String.valueOf(this.input.getClass()) + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                                }
                            } catch (InvalidProtocolBufferException e) {
                                e.setThrownFromInputStream();
                                throw e;
                            }
                        } finally {
                            this.totalBytesRetired += i4;
                            recomputeBufferSizeAfterLimit();
                        }
                    }
                    if (i4 < i) {
                        int i8 = this.bufferSize;
                        int i9 = i8 - this.pos;
                        this.pos = i8;
                        refillBuffer(1);
                        while (true) {
                            int i10 = i - i9;
                            int i11 = this.bufferSize;
                            if (i10 > i11) {
                                i9 += i11;
                                this.pos = i11;
                                refillBuffer(1);
                            } else {
                                this.pos = i10;
                                return;
                            }
                        }
                    }
                } else {
                    skipRawBytes((i7 - i5) - i3);
                    throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                }
            } else {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit = Preference.DEFAULT_ORDER;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        public UnsafeDirectNioDecoder(ByteBuffer byteBuffer) {
            this.buffer = byteBuffer;
            long addressOffset = UnsafeUtil.addressOffset(byteBuffer);
            this.address = addressOffset;
            this.limit = byteBuffer.limit() + addressOffset;
            long position = addressOffset + byteBuffer.position();
            this.pos = position;
            this.startPos = position;
        }

        private final int bufferPos(long j) {
            return (int) (j - this.address);
        }

        private final void recomputeBufferSizeAfterLimit() {
            long j = this.limit + this.bufferSizeAfterLimit;
            this.limit = j;
            int i = (int) (j - this.startPos);
            int i2 = this.currentLimit;
            if (i > i2) {
                int i3 = i - i2;
                this.bufferSizeAfterLimit = i3;
                this.limit = j - i3;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private final int remaining() {
            return (int) (this.limit - this.pos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void checkLastTagWas(int i) {
            if (this.lastTag == i) {
            } else {
                throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean isAtEnd() {
            if (this.pos == this.limit) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int pushLimit(int i) {
            if (i >= 0) {
                int totalBytesRead = i + getTotalBytesRead();
                int i2 = this.currentLimit;
                if (totalBytesRead <= i2) {
                    this.currentLimit = totalBytesRead;
                    recomputeBufferSizeAfterLimit();
                    return i2;
                }
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean readBool() {
            if (readRawVarint64() != 0) {
                return true;
            }
            return false;
        }

        @Override // com.google.protobuf.CodedInputStream
        public final byte[] readByteArray() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 >= 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = this.pos;
                long j2 = readRawVarint32;
                long j3 = j + j2;
                ByteBuffer byteBuffer = this.buffer;
                int position = byteBuffer.position();
                int limit = byteBuffer.limit();
                try {
                    try {
                        byteBuffer.position(bufferPos(j));
                        byteBuffer.limit(bufferPos(j3));
                        ByteBuffer slice = this.buffer.slice();
                        byteBuffer.position(position);
                        byteBuffer.limit(limit);
                        slice.get(bArr);
                        this.pos += j2;
                        return bArr;
                    } catch (IllegalArgumentException e) {
                        InvalidProtocolBufferException invalidProtocolBufferException = new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                        invalidProtocolBufferException.initCause(e);
                        throw invalidProtocolBufferException;
                    }
                } catch (Throwable th) {
                    byteBuffer.position(position);
                    byteBuffer.limit(limit);
                    throw th;
                }
            }
            if (readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final ByteString readBytes() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0L, j);
                this.pos += j;
                return new ByteString.LiteralByteString(bArr);
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            if (readRawVarint32 < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final double readDouble() {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readEnum() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final float readFloat() {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readInt64() {
            return readRawVarint64();
        }

        public final byte readRawByte() {
            long j = this.pos;
            if (j != this.limit) {
                this.pos = 1 + j;
                return UnsafeUtil.getByte(j);
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        public final int readRawLittleEndian32() {
            long j = this.pos;
            if (this.limit - j >= 4) {
                this.pos = 4 + j;
                int i = UnsafeUtil.getByte(j) & 255;
                int i2 = UnsafeUtil.getByte(1 + j) & 255;
                int i3 = UnsafeUtil.getByte(2 + j) & 255;
                return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (i2 << 8) | i | (i3 << 16);
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        public final long readRawLittleEndian64() {
            long j = this.pos;
            if (this.limit - j >= 8) {
                this.pos = 8 + j;
                long j2 = UnsafeUtil.getByte(j);
                long j3 = UnsafeUtil.getByte(1 + j);
                long j4 = UnsafeUtil.getByte(2 + j);
                long j5 = UnsafeUtil.getByte(3 + j);
                long j6 = UnsafeUtil.getByte(4 + j);
                return ((UnsafeUtil.getByte(j + 7) & 255) << 56) | (j2 & 255) | ((j3 & 255) << 8) | ((j4 & 255) << 16) | ((j5 & 255) << 24) | ((j6 & 255) << 32) | ((UnsafeUtil.getByte(5 + j) & 255) << 40) | ((UnsafeUtil.getByte(6 + j) & 255) << 48);
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
        
            if (com.google.protobuf.UnsafeUtil.getByte(r3) >= 0) goto L33;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int readRawVarint32() {
            /*
                r9 = this;
                long r0 = r9.pos
                long r2 = r9.limit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L92
            La:
                r2 = 1
                long r2 = r2 + r0
                byte r4 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r4 < 0) goto L16
                r9.pos = r2
                return r4
            L16:
                long r5 = r9.limit
                long r5 = r5 - r2
                r7 = 9
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 < 0) goto L92
                r5 = 2
                long r5 = r5 + r0
                byte r2 = com.google.protobuf.UnsafeUtil.getByte(r2)
                int r2 = r2 << 7
                r2 = r2 ^ r4
                if (r2 >= 0) goto L2e
                r0 = r2 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L8f
            L2e:
                r3 = 3
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r5 = r5 << 14
                r2 = r2 ^ r5
                if (r2 < 0) goto L3e
                r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
            L3c:
                r5 = r3
                goto L8f
            L3e:
                r5 = 4
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                int r3 = r3 << 21
                r2 = r2 ^ r3
                if (r2 >= 0) goto L4f
                r0 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L8f
            L4f:
                r3 = 5
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                int r6 = r5 << 28
                r2 = r2 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r2 = r2 ^ r6
                if (r5 >= 0) goto L8d
                r5 = 6
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L8b
                r3 = 7
                long r3 = r3 + r0
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L8d
                r5 = 8
                long r5 = r5 + r0
                byte r3 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r3 >= 0) goto L8b
                long r3 = r0 + r7
                byte r5 = com.google.protobuf.UnsafeUtil.getByte(r5)
                if (r5 >= 0) goto L8d
                r5 = 10
                long r5 = r5 + r0
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r3)
                if (r0 < 0) goto L92
            L8b:
                r0 = r2
                goto L8f
            L8d:
                r0 = r2
                goto L3c
            L8f:
                r9.pos = r5
                return r0
            L92:
                long r0 = r9.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readRawVarint64() {
            long j;
            long j2;
            int i;
            long j3 = this.pos;
            if (this.limit != j3) {
                long j4 = 1 + j3;
                byte b = UnsafeUtil.getByte(j3);
                if (b >= 0) {
                    this.pos = j4;
                    return b;
                }
                if (this.limit - j4 >= 9) {
                    long j5 = 2 + j3;
                    int i2 = (UnsafeUtil.getByte(j4) << 7) ^ b;
                    if (i2 < 0) {
                        i = i2 ^ (-128);
                    } else {
                        long j6 = 3 + j3;
                        int i3 = i2 ^ (UnsafeUtil.getByte(j5) << 14);
                        if (i3 >= 0) {
                            j = i3 ^ 16256;
                        } else {
                            j5 = 4 + j3;
                            int i4 = i3 ^ (UnsafeUtil.getByte(j6) << 21);
                            if (i4 < 0) {
                                i = (-2080896) ^ i4;
                            } else {
                                j6 = 5 + j3;
                                long j7 = (UnsafeUtil.getByte(j5) << 28) ^ i4;
                                if (j7 >= 0) {
                                    j = 266354560 ^ j7;
                                } else {
                                    long j8 = 6 + j3;
                                    long j9 = (UnsafeUtil.getByte(j6) << 35) ^ j7;
                                    if (j9 < 0) {
                                        j2 = -34093383808L;
                                    } else {
                                        j5 = j3 + 7;
                                        long j10 = j9 ^ (UnsafeUtil.getByte(j8) << 42);
                                        if (j10 >= 0) {
                                            j = 4363953127296L ^ j10;
                                        } else {
                                            j8 = 8 + j3;
                                            j9 = j10 ^ (UnsafeUtil.getByte(j5) << 49);
                                            if (j9 < 0) {
                                                j2 = -558586000294016L;
                                            } else {
                                                j5 = j3 + 9;
                                                long j11 = (j9 ^ (UnsafeUtil.getByte(j8) << 56)) ^ 71499008037633920L;
                                                if (j11 < 0) {
                                                    long j12 = j3 + 10;
                                                    if (UnsafeUtil.getByte(j5) >= 0) {
                                                        j5 = j12;
                                                    }
                                                }
                                                j = j11;
                                            }
                                        }
                                        this.pos = j5;
                                        return j;
                                    }
                                    j = j2 ^ j9;
                                    j5 = j8;
                                    this.pos = j5;
                                    return j;
                                }
                            }
                        }
                        j5 = j6;
                        this.pos = j5;
                        return j;
                    }
                    j = i;
                    this.pos = j5;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        final long readRawVarint64SlowPath() {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                j |= (r3 & Byte.MAX_VALUE) << i;
                if ((readRawByte() & 128) == 0) {
                    return j;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSFixed32() {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSFixed64() {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readSInt32() {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readSInt64() {
            return decodeZigZag64(readRawVarint64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readString() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                byte[] bArr = new byte[readRawVarint32];
                long j = readRawVarint32;
                UnsafeUtil.copyMemory(this.pos, bArr, 0L, j);
                String str = new String(bArr, Internal.UTF_8);
                this.pos += j;
                return str;
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final String readStringRequireUtf8() {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0 && readRawVarint32 <= remaining()) {
                String decodeUtf8 = Utf8.decodeUtf8(this.buffer, bufferPos(this.pos), readRawVarint32);
                this.pos += readRawVarint32;
                return decodeUtf8;
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readTag() {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            int readRawVarint32 = readRawVarint32();
            this.lastTag = readRawVarint32;
            if (WireFormat.getTagFieldNumber(readRawVarint32) != 0) {
                return readRawVarint32;
            }
            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
        }

        @Override // com.google.protobuf.CodedInputStream
        public final int readUInt32() {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final long readUInt64() {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public final boolean skipField(int i) {
            int readTag;
            int tagWireType = WireFormat.getTagWireType(i);
            int i2 = 0;
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 4) {
                                return false;
                            }
                            if (tagWireType == 5) {
                                skipRawBytes(4);
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        do {
                            readTag = readTag();
                            if (readTag == 0) {
                                break;
                            }
                        } while (skipField(readTag));
                        checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                        return true;
                    }
                    skipRawBytes(readRawVarint32());
                    return true;
                }
                skipRawBytes(8);
                return true;
            }
            if (remaining() >= 10) {
                while (i2 < 10) {
                    long j = this.pos;
                    this.pos = 1 + j;
                    if (UnsafeUtil.getByte(j) < 0) {
                        i2++;
                    }
                }
                throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            }
            while (i2 < 10) {
                if (readRawByte() < 0) {
                    i2++;
                }
            }
            throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
            return true;
        }

        public final void skipRawBytes(int i) {
            if (i >= 0 && i <= remaining()) {
                this.pos += i;
            } else {
                if (i < 0) {
                    throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                }
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
        }
    }

    public static int decodeZigZag32(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long decodeZigZag64(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static CodedInputStream newInstance(InputStream inputStream) {
        if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        }
        return new StreamDecoder(inputStream);
    }

    public static int readRawVarint32(int i, InputStream inputStream) {
        if ((i & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0) {
            return i;
        }
        int i2 = i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            i2 |= (read & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE) << i3;
            if ((read & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
            }
            if ((read2 & BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
    }

    public abstract void checkLastTagWas(int i);

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd();

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i);

    public abstract boolean readBool();

    public abstract byte[] readByteArray();

    public abstract ByteString readBytes();

    public abstract double readDouble();

    public abstract int readEnum();

    public abstract int readFixed32();

    public abstract long readFixed64();

    public abstract float readFloat();

    public abstract int readInt32();

    public abstract long readInt64();

    public abstract int readRawVarint32();

    public abstract long readRawVarint64();

    public abstract int readSFixed32();

    public abstract long readSFixed64();

    public abstract int readSInt32();

    public abstract long readSInt64();

    public abstract String readString();

    public abstract String readStringRequireUtf8();

    public abstract int readTag();

    public abstract int readUInt32();

    public abstract long readUInt64();

    public abstract boolean skipField(int i);

    public static CodedInputStream newInstance(byte[] bArr) {
        int length = bArr.length;
        return newInstance(bArr, 0, 0);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i, i2);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
