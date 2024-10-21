package com.google.protobuf;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentCreateLogEvent;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Utf8 {
    public static final Processor processor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Processor {
        static final String decodeUtf8Default$ar$ds(ByteBuffer byteBuffer, int i, int i2) {
            int i3;
            if ((((byteBuffer.limit() - i) - i2) | i | i2) >= 0) {
                int i4 = i + i2;
                char[] cArr = new char[i2];
                int i5 = 0;
                while (i < i4) {
                    byte b = byteBuffer.get(i);
                    if (!OnDeviceExplicitContentCreateLogEvent.isOneByte(b)) {
                        break;
                    }
                    i++;
                    OnDeviceExplicitContentCreateLogEvent.handleOneByte(b, cArr, i5);
                    i5++;
                }
                int i6 = i5;
                while (i < i4) {
                    int i7 = i + 1;
                    byte b2 = byteBuffer.get(i);
                    if (OnDeviceExplicitContentCreateLogEvent.isOneByte(b2)) {
                        OnDeviceExplicitContentCreateLogEvent.handleOneByte(b2, cArr, i6);
                        i6++;
                        i = i7;
                        while (i < i4) {
                            byte b3 = byteBuffer.get(i);
                            if (OnDeviceExplicitContentCreateLogEvent.isOneByte(b3)) {
                                i++;
                                OnDeviceExplicitContentCreateLogEvent.handleOneByte(b3, cArr, i6);
                                i6++;
                            }
                        }
                    } else {
                        if (OnDeviceExplicitContentCreateLogEvent.isTwoBytes(b2)) {
                            if (i7 < i4) {
                                i3 = i6 + 1;
                                i += 2;
                                OnDeviceExplicitContentCreateLogEvent.handleTwoBytes(b2, byteBuffer.get(i7), cArr, i6);
                            } else {
                                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                            }
                        } else if (OnDeviceExplicitContentCreateLogEvent.isThreeBytes(b2)) {
                            if (i7 < i4 - 1) {
                                i3 = i6 + 1;
                                int i8 = i + 2;
                                i += 3;
                                OnDeviceExplicitContentCreateLogEvent.handleThreeBytes(b2, byteBuffer.get(i7), byteBuffer.get(i8), cArr, i6);
                            } else {
                                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                            }
                        } else if (i7 < i4 - 2) {
                            byte b4 = byteBuffer.get(i7);
                            int i9 = i + 3;
                            byte b5 = byteBuffer.get(i + 2);
                            i += 4;
                            OnDeviceExplicitContentCreateLogEvent.handleFourBytes(b2, b4, b5, byteBuffer.get(i9), cArr, i6);
                            i6 += 2;
                        } else {
                            throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                        }
                        i6 = i3;
                    }
                }
                return new String(cArr, 0, i6);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        public abstract String decodeUtf8(byte[] bArr, int i, int i2);

        public abstract String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2);

        public abstract int encodeUtf8(String str, byte[] bArr, int i, int i2);

        public abstract int partialIsValidUtf8(int i, byte[] bArr, int i2, int i3);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SafeProcessor extends Processor {
        @Override // com.google.protobuf.Utf8.Processor
        public final String decodeUtf8(byte[] bArr, int i, int i2) {
            int i3;
            int length = bArr.length;
            if ((((length - i) - i2) | i | i2) >= 0) {
                int i4 = i + i2;
                char[] cArr = new char[i2];
                int i5 = 0;
                while (i < i4) {
                    byte b = bArr[i];
                    if (!OnDeviceExplicitContentCreateLogEvent.isOneByte(b)) {
                        break;
                    }
                    i++;
                    OnDeviceExplicitContentCreateLogEvent.handleOneByte(b, cArr, i5);
                    i5++;
                }
                while (i < i4) {
                    int i6 = i + 1;
                    byte b2 = bArr[i];
                    if (OnDeviceExplicitContentCreateLogEvent.isOneByte(b2)) {
                        OnDeviceExplicitContentCreateLogEvent.handleOneByte(b2, cArr, i5);
                        i5++;
                        i = i6;
                        while (i < i4) {
                            byte b3 = bArr[i];
                            if (OnDeviceExplicitContentCreateLogEvent.isOneByte(b3)) {
                                i++;
                                OnDeviceExplicitContentCreateLogEvent.handleOneByte(b3, cArr, i5);
                                i5++;
                            }
                        }
                    } else {
                        if (OnDeviceExplicitContentCreateLogEvent.isTwoBytes(b2)) {
                            if (i6 < i4) {
                                i3 = i5 + 1;
                                i += 2;
                                OnDeviceExplicitContentCreateLogEvent.handleTwoBytes(b2, bArr[i6], cArr, i5);
                            } else {
                                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                            }
                        } else if (OnDeviceExplicitContentCreateLogEvent.isThreeBytes(b2)) {
                            if (i6 < i4 - 1) {
                                i3 = i5 + 1;
                                int i7 = i + 2;
                                i += 3;
                                OnDeviceExplicitContentCreateLogEvent.handleThreeBytes(b2, bArr[i6], bArr[i7], cArr, i5);
                            } else {
                                throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                            }
                        } else if (i6 < i4 - 2) {
                            byte b4 = bArr[i6];
                            int i8 = i + 3;
                            byte b5 = bArr[i + 2];
                            i += 4;
                            OnDeviceExplicitContentCreateLogEvent.handleFourBytes(b2, b4, b5, bArr[i8], cArr, i5);
                            i5 += 2;
                        } else {
                            throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
                        }
                        i5 = i3;
                    }
                }
                return new String(cArr, 0, i5);
            }
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)));
        }

        @Override // com.google.protobuf.Utf8.Processor
        public final String decodeUtf8Direct(ByteBuffer byteBuffer, int i, int i2) {
            return decodeUtf8Default$ar$ds(byteBuffer, i, i2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
        
            return r11 + r0;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int encodeUtf8(java.lang.String r9, byte[] r10, int r11, int r12) {
            /*
                Method dump skipped, instructions count: 253
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.encodeUtf8(java.lang.String, byte[], int, int):int");
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x001c, code lost:
        
            if (r13[r14] <= (-65)) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0047, code lost:
        
            if (r13[r14] <= (-65)) goto L11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0081, code lost:
        
            if (r13[r14] <= (-65)) goto L11;
         */
        @Override // com.google.protobuf.Utf8.Processor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final int partialIsValidUtf8(int r12, byte[] r13, int r14, int r15) {
            /*
                Method dump skipped, instructions count: 244
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.SafeProcessor.partialIsValidUtf8(int, byte[], int, int):int");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UnpairedSurrogateException extends IllegalArgumentException {
        public UnpairedSurrogateException(int i, int i2) {
            super(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_9(i2, i, "Unpaired surrogate at index ", " of "));
        }
    }

    static {
        boolean z = UnsafeUtil.HAS_UNSAFE_BYTEBUFFER_OPERATIONS;
        processor = new SafeProcessor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        Processor processor2 = processor;
        if (byteBuffer.hasArray()) {
            return processor2.decodeUtf8(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2);
        }
        if (byteBuffer.isDirect()) {
            return processor2.decodeUtf8Direct(byteBuffer, i, i2);
        }
        return Processor.decodeUtf8Default$ar$ds(byteBuffer, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encode(String str, byte[] bArr, int i, int i2) {
        return processor.encodeUtf8(str, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodedLength(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && str.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = str.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = str.length();
                while (i2 < length2) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (charAt2 >= 55296 && charAt2 <= 57343) {
                            if (Character.codePointAt(str, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new UnpairedSurrogateException(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i3 + 4294967296L));
    }

    public static int incompleteStateFor(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        if (processor.partialIsValidUtf8(0, bArr, i, i2) != 0) {
            return false;
        }
        return true;
    }

    public static int incompleteStateFor(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static int incompleteStateFor(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    public static int incompleteStateFor(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte b = bArr[i - 1];
        if (i3 == 0) {
            return incompleteStateFor(b);
        }
        if (i3 == 1) {
            return incompleteStateFor(b, bArr[i]);
        }
        if (i3 == 2) {
            return incompleteStateFor(b, bArr[i], bArr[i + 1]);
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decodeUtf8(byte[] bArr, int i, int i2) {
        return processor.decodeUtf8(bArr, i, i2);
    }
}
