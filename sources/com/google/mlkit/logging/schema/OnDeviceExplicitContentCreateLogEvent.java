package com.google.mlkit.logging.schema;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UnknownFieldSetLite;
import com.google.protobuf.WireFormat;
import io.grpc.okhttp.internal.framed.Settings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceExplicitContentCreateLogEvent {
    public OnDeviceExplicitContentCreateLogEvent() {
    }

    public static /* bridge */ /* synthetic */ void addFixed32$ar$ds(Object obj, int i, int i2) {
        ((UnknownFieldSetLite) obj).storeField(WireFormat.makeTag(i, 5), Integer.valueOf(i2));
    }

    public static /* bridge */ /* synthetic */ void addFixed64$ar$ds(Object obj, int i, long j) {
        ((UnknownFieldSetLite) obj).storeField(WireFormat.makeTag(i, 1), Long.valueOf(j));
    }

    public static /* bridge */ /* synthetic */ void addGroup$ar$ds(Object obj, int i, Object obj2) {
        ((UnknownFieldSetLite) obj).storeField(WireFormat.makeTag(i, 3), obj2);
    }

    public static /* bridge */ /* synthetic */ void addLengthDelimited$ar$ds(Object obj, int i, ByteString byteString) {
        ((UnknownFieldSetLite) obj).storeField(WireFormat.makeTag(i, 2), byteString);
    }

    public static /* bridge */ /* synthetic */ void addVarint$ar$ds(Object obj, int i, long j) {
        ((UnknownFieldSetLite) obj).storeField(WireFormat.makeTag(i, 0), Long.valueOf(j));
    }

    public static UnknownFieldSetLite getFromMessage$ar$ds(Object obj) {
        return ((GeneratedMessageLite) obj).unknownFields;
    }

    public static /* synthetic */ int getSerializedSizeAsMessageSet$ar$ds(Object obj) {
        UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
        int i = unknownFieldSetLite.memoizedSerializedSize;
        if (i == -1) {
            int i2 = 0;
            for (int i3 = 0; i3 < unknownFieldSetLite.count; i3++) {
                int tagFieldNumber = WireFormat.getTagFieldNumber(unknownFieldSetLite.tags[i3]);
                ByteString byteString = (ByteString) unknownFieldSetLite.objects[i3];
                int computeTagSize = CodedOutputStream.computeTagSize(1);
                i2 += computeTagSize + computeTagSize + CodedOutputStream.computeUInt32Size(2, tagFieldNumber) + CodedOutputStream.computeBytesSize(3, byteString);
            }
            unknownFieldSetLite.memoizedSerializedSize = i2;
            return i2;
        }
        return i;
    }

    public static void handleFourBytes(byte b, byte b2, byte b3, byte b4, char[] cArr, int i) {
        if (!isNotTrailingByte(b2) && (((b << 28) + (b2 + 112)) >> 30) == 0 && !isNotTrailingByte(b3) && !isNotTrailingByte(b4)) {
            int trailingByteValue = ((b & 7) << 18) | (trailingByteValue(b2) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4);
            cArr[i] = (char) ((trailingByteValue >>> 10) + 55232);
            cArr[i + 1] = (char) ((trailingByteValue & 1023) + 56320);
            return;
        }
        throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
    }

    public static void handleOneByte(byte b, char[] cArr, int i) {
        cArr[i] = (char) b;
    }

    public static void handleThreeBytes(byte b, byte b2, byte b3, char[] cArr, int i) {
        if (!isNotTrailingByte(b2)) {
            if (b == -32) {
                if (b2 >= -96) {
                    b = -32;
                }
            }
            if ((b != -19 || b2 < -96) && !isNotTrailingByte(b3)) {
                cArr[i] = (char) (((b & 15) << 12) | (trailingByteValue(b2) << 6) | trailingByteValue(b3));
                return;
            }
        }
        throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
    }

    public static void handleTwoBytes(byte b, byte b2, char[] cArr, int i) {
        if (b >= -62 && !isNotTrailingByte(b2)) {
            cArr[i] = (char) (((b & 31) << 6) | trailingByteValue(b2));
            return;
        }
        throw new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
    }

    private static boolean isNotTrailingByte(byte b) {
        if (b > -65) {
            return true;
        }
        return false;
    }

    public static boolean isOneByte(byte b) {
        if (b >= 0) {
            return true;
        }
        return false;
    }

    public static boolean isThreeBytes(byte b) {
        if (b < -16) {
            return true;
        }
        return false;
    }

    public static boolean isTwoBytes(byte b) {
        if (b < -32) {
            return true;
        }
        return false;
    }

    public static /* bridge */ /* synthetic */ Object merge$ar$ds(Object obj, Object obj2) {
        if (!UnknownFieldSetLite.DEFAULT_INSTANCE.equals(obj2)) {
            if (UnknownFieldSetLite.DEFAULT_INSTANCE.equals(obj)) {
                return UnknownFieldSetLite.mutableCopyOf((UnknownFieldSetLite) obj, (UnknownFieldSetLite) obj2);
            }
            UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj2;
            if (!unknownFieldSetLite.equals(UnknownFieldSetLite.DEFAULT_INSTANCE)) {
                UnknownFieldSetLite unknownFieldSetLite2 = (UnknownFieldSetLite) obj;
                unknownFieldSetLite2.checkMutable();
                int i = unknownFieldSetLite2.count + unknownFieldSetLite.count;
                unknownFieldSetLite2.ensureCapacity(i);
                System.arraycopy(unknownFieldSetLite.tags, 0, unknownFieldSetLite2.tags, unknownFieldSetLite2.count, unknownFieldSetLite.count);
                System.arraycopy(unknownFieldSetLite.objects, 0, unknownFieldSetLite2.objects, unknownFieldSetLite2.count, unknownFieldSetLite.count);
                unknownFieldSetLite2.count = i;
                return obj;
            }
            return obj;
        }
        return obj;
    }

    public static /* synthetic */ Object newBuilder$ar$ds() {
        return new UnknownFieldSetLite();
    }

    public static void setToMessage$ar$ds(Object obj, UnknownFieldSetLite unknownFieldSetLite) {
        ((GeneratedMessageLite) obj).unknownFields = unknownFieldSetLite;
    }

    public static /* synthetic */ Object toImmutable$ar$ds$11cc0da5_0(Object obj) {
        ((UnknownFieldSetLite) obj).makeImmutable();
        return obj;
    }

    private static int trailingByteValue(byte b) {
        return b & 63;
    }

    public static /* synthetic */ void writeAsMessageSetTo$ar$class_merging$d1b76bae_0$ar$ds$ar$class_merging$ar$class_merging(Object obj, ExecutorSelector executorSelector) {
        int i = 0;
        while (true) {
            UnknownFieldSetLite unknownFieldSetLite = (UnknownFieldSetLite) obj;
            if (i < unknownFieldSetLite.count) {
                executorSelector.writeMessageSetItem(WireFormat.getTagFieldNumber(unknownFieldSetLite.tags[i]), unknownFieldSetLite.objects[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public final /* bridge */ /* synthetic */ Object getBuilderFromMessage(Object obj) {
        UnknownFieldSetLite fromMessage$ar$ds = getFromMessage$ar$ds(obj);
        if (fromMessage$ar$ds == UnknownFieldSetLite.DEFAULT_INSTANCE) {
            UnknownFieldSetLite unknownFieldSetLite = new UnknownFieldSetLite();
            setToMessage$ar$ds(obj, unknownFieldSetLite);
            return unknownFieldSetLite;
        }
        return fromMessage$ar$ds;
    }

    public final /* bridge */ /* synthetic */ Object getFromMessage(Object obj) {
        return getFromMessage$ar$ds(obj);
    }

    public final void makeImmutable(Object obj) {
        getFromMessage$ar$ds(obj).makeImmutable();
    }

    public final boolean mergeOneFieldFrom$ar$class_merging$ar$class_merging(Object obj, Settings settings) {
        int i = settings.set;
        int tagFieldNumber = WireFormat.getTagFieldNumber(i);
        int tagWireType = WireFormat.getTagWireType(i);
        if (tagWireType != 0) {
            if (tagWireType != 1) {
                if (tagWireType != 2) {
                    if (tagWireType != 3) {
                        if (tagWireType != 4) {
                            if (tagWireType == 5) {
                                addFixed32$ar$ds(obj, tagFieldNumber, settings.readFixed32());
                                return true;
                            }
                            throw new InvalidProtocolBufferException.InvalidWireTypeException();
                        }
                        return false;
                    }
                    int makeTag = WireFormat.makeTag(tagFieldNumber, 4);
                    Object newBuilder$ar$ds = newBuilder$ar$ds();
                    while (settings.getFieldNumber() != Integer.MAX_VALUE && mergeOneFieldFrom$ar$class_merging$ar$class_merging(newBuilder$ar$ds, settings)) {
                    }
                    if (makeTag == settings.set) {
                        addGroup$ar$ds(obj, tagFieldNumber, toImmutable$ar$ds$11cc0da5_0(newBuilder$ar$ds));
                        return true;
                    }
                    throw new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
                }
                addLengthDelimited$ar$ds(obj, tagFieldNumber, settings.readBytes());
                return true;
            }
            addFixed64$ar$ds(obj, tagFieldNumber, settings.readFixed64());
            return true;
        }
        addVarint$ar$ds(obj, tagFieldNumber, settings.readInt64());
        return true;
    }

    public final /* synthetic */ void setBuilderToMessage(Object obj, Object obj2) {
        setToMessage$ar$ds(obj, (UnknownFieldSetLite) obj2);
    }

    public final /* bridge */ /* synthetic */ void setToMessage(Object obj, Object obj2) {
        setToMessage$ar$ds(obj, (UnknownFieldSetLite) obj2);
    }

    public OnDeviceExplicitContentCreateLogEvent(byte[] bArr) {
        this();
    }
}
