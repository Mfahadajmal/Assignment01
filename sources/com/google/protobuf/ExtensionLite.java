package com.google.protobuf;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ExtensionLite<ContainingType extends MessageLite, Type> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeBoolList(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        boolean z;
        boolean z2;
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int decodeVarint64 = decodeVarint64(bArr, i2, arrayDecoders$Registers);
        if (arrayDecoders$Registers.long1 != 0) {
            z = true;
        } else {
            z = false;
        }
        booleanArrayList.addBoolean(z);
        while (decodeVarint64 < i3) {
            int decodeVarint32 = decodeVarint32(bArr, decodeVarint64, arrayDecoders$Registers);
            if (i != arrayDecoders$Registers.int1) {
                break;
            }
            decodeVarint64 = decodeVarint64(bArr, decodeVarint32, arrayDecoders$Registers);
            if (arrayDecoders$Registers.long1 != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            booleanArrayList.addBoolean(z2);
        }
        return decodeVarint64;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeBytes(byte[] bArr, int i, ArrayDecoders$Registers arrayDecoders$Registers) {
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1;
        if (i2 >= 0) {
            if (i2 <= bArr.length - decodeVarint32) {
                if (i2 == 0) {
                    arrayDecoders$Registers.object1 = ByteString.EMPTY;
                    return decodeVarint32;
                }
                arrayDecoders$Registers.object1 = ByteString.copyFrom(bArr, decodeVarint32, i2);
                return decodeVarint32 + i2;
            }
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
        throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double decodeDouble(byte[] bArr, int i) {
        return Double.longBitsToDouble(decodeFixed64(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeFixed32(byte[] bArr, int i) {
        int i2 = bArr[i] & 255;
        int i3 = bArr[i + 1] & 255;
        int i4 = bArr[i + 2] & 255;
        return ((bArr[i + 3] & 255) << 24) | (i3 << 8) | i2 | (i4 << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long decodeFixed64(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48) | ((bArr[i + 7] & 255) << 56);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float decodeFloat(byte[] bArr, int i) {
        return Float.intBitsToFloat(decodeFixed32(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeGroupField(Schema schema, byte[] bArr, int i, int i2, int i3, ArrayDecoders$Registers arrayDecoders$Registers) {
        Object newInstance = schema.newInstance();
        int mergeGroupField = mergeGroupField(newInstance, schema, bArr, i, i2, i3, arrayDecoders$Registers);
        schema.makeImmutable(newInstance);
        arrayDecoders$Registers.object1 = newInstance;
        return mergeGroupField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeMessageField(Schema schema, byte[] bArr, int i, int i2, ArrayDecoders$Registers arrayDecoders$Registers) {
        Object newInstance = schema.newInstance();
        int mergeMessageField = mergeMessageField(newInstance, schema, bArr, i, i2, arrayDecoders$Registers);
        schema.makeImmutable(newInstance);
        arrayDecoders$Registers.object1 = newInstance;
        return mergeMessageField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeMessageList(Schema schema, int i, byte[] bArr, int i2, int i3, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        int decodeMessageField = decodeMessageField(schema, bArr, i2, i3, arrayDecoders$Registers);
        protobufList.add(arrayDecoders$Registers.object1);
        while (decodeMessageField < i3) {
            int decodeVarint32 = decodeVarint32(bArr, decodeMessageField, arrayDecoders$Registers);
            if (i != arrayDecoders$Registers.int1) {
                break;
            }
            decodeMessageField = decodeMessageField(schema, bArr, decodeVarint32, i3, arrayDecoders$Registers);
            protobufList.add(arrayDecoders$Registers.object1);
        }
        return decodeMessageField;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedBoolList(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        boolean z;
        BooleanArrayList booleanArrayList = (BooleanArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, arrayDecoders$Registers);
            if (arrayDecoders$Registers.long1 != 0) {
                z = true;
            } else {
                z = false;
            }
            booleanArrayList.addBoolean(z);
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedDoubleList(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        DoubleArrayList doubleArrayList = (DoubleArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            doubleArrayList.addDouble(decodeDouble(bArr, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFixed32List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            intArrayList.addInt(decodeFixed32(bArr, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFixed64List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            longArrayList.addLong(decodeFixed64(bArr, decodeVarint32));
            decodeVarint32 += 8;
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedFloatList(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        FloatArrayList floatArrayList = (FloatArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            floatArrayList.addFloat(decodeFloat(bArr, decodeVarint32));
            decodeVarint32 += 4;
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedSInt32List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            decodeVarint32 = decodeVarint32(bArr, decodeVarint32, arrayDecoders$Registers);
            intArrayList.addInt(CodedInputStream.decodeZigZag32(arrayDecoders$Registers.int1));
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedSInt64List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, arrayDecoders$Registers);
            longArrayList.addLong(CodedInputStream.decodeZigZag64(arrayDecoders$Registers.long1));
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedVarint32List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            decodeVarint32 = decodeVarint32(bArr, decodeVarint32, arrayDecoders$Registers);
            intArrayList.addInt(arrayDecoders$Registers.int1);
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodePackedVarint64List(byte[] bArr, int i, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        LongArrayList longArrayList = (LongArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1 + decodeVarint32;
        while (decodeVarint32 < i2) {
            decodeVarint32 = decodeVarint64(bArr, decodeVarint32, arrayDecoders$Registers);
            longArrayList.addLong(arrayDecoders$Registers.long1);
        }
        if (decodeVarint32 == i2) {
            return decodeVarint32;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeString(byte[] bArr, int i, ArrayDecoders$Registers arrayDecoders$Registers) {
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1;
        if (i2 >= 0) {
            if (i2 == 0) {
                arrayDecoders$Registers.object1 = "";
                return decodeVarint32;
            }
            arrayDecoders$Registers.object1 = new String(bArr, decodeVarint32, i2, Internal.UTF_8);
            return decodeVarint32 + i2;
        }
        throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeStringRequireUtf8(byte[] bArr, int i, ArrayDecoders$Registers arrayDecoders$Registers) {
        int decodeVarint32 = decodeVarint32(bArr, i, arrayDecoders$Registers);
        int i2 = arrayDecoders$Registers.int1;
        if (i2 >= 0) {
            if (i2 == 0) {
                arrayDecoders$Registers.object1 = "";
                return decodeVarint32;
            }
            arrayDecoders$Registers.object1 = Utf8.decodeUtf8(bArr, decodeVarint32, i2);
            return decodeVarint32 + i2;
        }
        throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeUnknownField(int i, byte[] bArr, int i2, int i3, UnknownFieldSetLite unknownFieldSetLite, ArrayDecoders$Registers arrayDecoders$Registers) {
        if (WireFormat.getTagFieldNumber(i) != 0) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 5) {
                                unknownFieldSetLite.storeField(i, Integer.valueOf(decodeFixed32(bArr, i2)));
                                return i2 + 4;
                            }
                            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
                        }
                        UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
                        int i4 = (i & (-8)) | 4;
                        int i5 = 0;
                        while (true) {
                            if (i2 >= i3) {
                                break;
                            }
                            int decodeVarint32 = decodeVarint32(bArr, i2, arrayDecoders$Registers);
                            int i6 = arrayDecoders$Registers.int1;
                            i5 = i6;
                            if (i6 != i4) {
                                int decodeUnknownField = decodeUnknownField(i5, bArr, decodeVarint32, i3, unknownFieldSetLite2, arrayDecoders$Registers);
                                i5 = i6;
                                i2 = decodeUnknownField;
                            } else {
                                i2 = decodeVarint32;
                                break;
                            }
                        }
                        if (i2 <= i3 && i5 == i4) {
                            unknownFieldSetLite.storeField(i, unknownFieldSetLite2);
                            return i2;
                        }
                        throw new InvalidProtocolBufferException("Failed to parse the message.");
                    }
                    int decodeVarint322 = decodeVarint32(bArr, i2, arrayDecoders$Registers);
                    int i7 = arrayDecoders$Registers.int1;
                    if (i7 >= 0) {
                        if (i7 <= bArr.length - decodeVarint322) {
                            if (i7 == 0) {
                                unknownFieldSetLite.storeField(i, ByteString.EMPTY);
                            } else {
                                unknownFieldSetLite.storeField(i, ByteString.copyFrom(bArr, decodeVarint322, i7));
                            }
                            return decodeVarint322 + i7;
                        }
                        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
                    }
                    throw new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
                }
                unknownFieldSetLite.storeField(i, Long.valueOf(decodeFixed64(bArr, i2)));
                return i2 + 8;
            }
            int decodeVarint64 = decodeVarint64(bArr, i2, arrayDecoders$Registers);
            unknownFieldSetLite.storeField(i, Long.valueOf(arrayDecoders$Registers.long1));
            return decodeVarint64;
        }
        throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32(int i, byte[] bArr, int i2, ArrayDecoders$Registers arrayDecoders$Registers) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE;
        if (b >= 0) {
            arrayDecoders$Registers.int1 = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Byte.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            arrayDecoders$Registers.int1 = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & Byte.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            arrayDecoders$Registers.int1 = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & Byte.MAX_VALUE) << 21);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            arrayDecoders$Registers.int1 = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & Byte.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                arrayDecoders$Registers.int1 = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32List(int i, byte[] bArr, int i2, int i3, Internal.ProtobufList protobufList, ArrayDecoders$Registers arrayDecoders$Registers) {
        IntArrayList intArrayList = (IntArrayList) protobufList;
        int decodeVarint32 = decodeVarint32(bArr, i2, arrayDecoders$Registers);
        intArrayList.addInt(arrayDecoders$Registers.int1);
        while (decodeVarint32 < i3) {
            int decodeVarint322 = decodeVarint32(bArr, decodeVarint32, arrayDecoders$Registers);
            if (i != arrayDecoders$Registers.int1) {
                break;
            }
            decodeVarint32 = decodeVarint32(bArr, decodeVarint322, arrayDecoders$Registers);
            intArrayList.addInt(arrayDecoders$Registers.int1);
        }
        return decodeVarint32;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint64(byte[] bArr, int i, ArrayDecoders$Registers arrayDecoders$Registers) {
        long j = bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            arrayDecoders$Registers.long1 = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & Byte.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (r10 & Byte.MAX_VALUE) << i4;
            b = bArr[i3];
            i3 = i5;
        }
        arrayDecoders$Registers.long1 = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int mergeGroupField(Object obj, Schema schema, byte[] bArr, int i, int i2, int i3, ArrayDecoders$Registers arrayDecoders$Registers) {
        int parseMessage = ((MessageSchema) schema).parseMessage(obj, bArr, i, i2, i3, arrayDecoders$Registers);
        arrayDecoders$Registers.object1 = obj;
        return parseMessage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int mergeMessageField(Object obj, Schema schema, byte[] bArr, int i, int i2, ArrayDecoders$Registers arrayDecoders$Registers) {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = decodeVarint32(i4, bArr, i3, arrayDecoders$Registers);
            i4 = arrayDecoders$Registers.int1;
        }
        int i5 = i3;
        if (i4 >= 0 && i4 <= i2 - i5) {
            int i6 = i4 + i5;
            schema.mergeFrom(obj, bArr, i5, i6, arrayDecoders$Registers);
            arrayDecoders$Registers.object1 = obj;
            return i6;
        }
        throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int skipField(int i, byte[] bArr, int i2, int i3, ArrayDecoders$Registers arrayDecoders$Registers) {
        if (WireFormat.getTagFieldNumber(i) != 0) {
            int tagWireType = WireFormat.getTagWireType(i);
            if (tagWireType != 0) {
                if (tagWireType != 1) {
                    if (tagWireType != 2) {
                        if (tagWireType != 3) {
                            if (tagWireType == 5) {
                                return i2 + 4;
                            }
                            throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
                        }
                        int i4 = (i & (-8)) | 4;
                        int i5 = 0;
                        while (i2 < i3) {
                            i2 = decodeVarint32(bArr, i2, arrayDecoders$Registers);
                            i5 = arrayDecoders$Registers.int1;
                            if (i5 == i4) {
                                break;
                            }
                            i2 = skipField(i5, bArr, i2, i3, arrayDecoders$Registers);
                        }
                        if (i2 <= i3 && i5 == i4) {
                            return i2;
                        }
                        throw new InvalidProtocolBufferException("Failed to parse the message.");
                    }
                    return decodeVarint32(bArr, i2, arrayDecoders$Registers) + arrayDecoders$Registers.int1;
                }
                return i2 + 8;
            }
            return decodeVarint64(bArr, i2, arrayDecoders$Registers);
        }
        throw new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int decodeVarint32(byte[] bArr, int i, ArrayDecoders$Registers arrayDecoders$Registers) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return decodeVarint32(b, bArr, i2, arrayDecoders$Registers);
        }
        arrayDecoders$Registers.int1 = b;
        return i2;
    }
}
