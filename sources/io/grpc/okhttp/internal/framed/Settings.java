package io.grpc.okhttp.internal.framed;

import androidx.preference.Preference;
import com.google.protobuf.BooleanArrayList;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.DoubleArrayList;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.FloatArrayList;
import com.google.protobuf.IntArrayList;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.LongArrayList;
import com.google.protobuf.Protobuf;
import com.google.protobuf.Schema;
import com.google.protobuf.WireFormat;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Settings {
    public final Object Settings$ar$values;
    private int persistValue;
    public int persisted;
    public int set;

    public Settings() {
        this.Settings$ar$values = new int[10];
    }

    public static Settings forCodedInput$ar$class_merging(CodedInputStream codedInputStream) {
        Settings settings = codedInputStream.wrapper$ar$class_merging;
        if (settings != null) {
            return settings;
        }
        return new Settings(codedInputStream);
    }

    private final void mergeGroupFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int i = this.persistValue;
        this.persistValue = WireFormat.makeTag(WireFormat.getTagFieldNumber(this.set), 4);
        try {
            schema.mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(obj, this, extensionRegistryLite);
            if (this.set == this.persistValue) {
            } else {
                throw new InvalidProtocolBufferException("Failed to parse the message.");
            }
        } finally {
            this.persistValue = i;
        }
    }

    private final void mergeMessageFieldInternal(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
        CodedInputStream codedInputStream = (CodedInputStream) this.Settings$ar$values;
        if (codedInputStream.recursionDepth < codedInputStream.recursionLimit) {
            int pushLimit = codedInputStream.pushLimit(readUInt32);
            ((CodedInputStream) this.Settings$ar$values).recursionDepth++;
            schema.mergeFrom$ar$class_merging$eb9677be_0$ar$class_merging(obj, this, extensionRegistryLite);
            ((CodedInputStream) this.Settings$ar$values).checkLastTagWas(0);
            r5.recursionDepth--;
            ((CodedInputStream) this.Settings$ar$values).popLimit(pushLimit);
            return;
        }
        throw new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    private final void requirePosition(int i) {
        if (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() == i) {
        } else {
            throw new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        }
    }

    private static final void verifyPackedFixed32Length$ar$ds(int i) {
        if ((i & 3) == 0) {
        } else {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
    }

    private static final void verifyPackedFixed64Length$ar$ds(int i) {
        if ((i & 7) == 0) {
        } else {
            throw new InvalidProtocolBufferException("Failed to parse the message.");
        }
    }

    public final int get(int i) {
        return ((int[]) this.Settings$ar$values)[i];
    }

    public final int getFieldNumber() {
        int i = this.persisted;
        if (i != 0) {
            this.set = i;
            this.persisted = 0;
        } else {
            i = ((CodedInputStream) this.Settings$ar$values).readTag();
            this.set = i;
        }
        if (i != 0 && i != this.persistValue) {
            return WireFormat.getTagFieldNumber(i);
        }
        return Preference.DEFAULT_ORDER;
    }

    public final int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return ((int[]) this.Settings$ar$values)[1];
        }
        return -1;
    }

    public final boolean isSet(int i) {
        if (((1 << i) & this.set) != 0) {
            return true;
        }
        return false;
    }

    public final void mergeGroupField(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(3);
        mergeGroupFieldInternal(obj, schema, extensionRegistryLite);
    }

    public final void mergeMessageField(Object obj, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(2);
        mergeMessageFieldInternal(obj, schema, extensionRegistryLite);
    }

    public final boolean readBool() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readBool();
    }

    public final void readBoolList(List list) {
        int readTag;
        int i;
        if (list instanceof BooleanArrayList) {
            BooleanArrayList booleanArrayList = (BooleanArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        booleanArrayList.addBoolean(((CodedInputStream) this.Settings$ar$values).readBool());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                booleanArrayList.addBoolean(((CodedInputStream) this.Settings$ar$values).readBool());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Boolean.valueOf(((CodedInputStream) this.Settings$ar$values).readBool()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Boolean.valueOf(((CodedInputStream) this.Settings$ar$values).readBool()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final ByteString readBytes() {
        requireWireType(2);
        return ((CodedInputStream) this.Settings$ar$values).readBytes();
    }

    public final double readDouble() {
        requireWireType(1);
        return ((CodedInputStream) this.Settings$ar$values).readDouble();
    }

    public final void readDoubleList(List list) {
        int readTag;
        int i;
        if (list instanceof DoubleArrayList) {
            DoubleArrayList doubleArrayList = (DoubleArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt32);
                    int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                    do {
                        doubleArrayList.addDouble(((CodedInputStream) this.Settings$ar$values).readDouble());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                doubleArrayList.addDouble(((CodedInputStream) this.Settings$ar$values).readDouble());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 1) {
                if (tagWireType2 == 2) {
                    int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt322);
                    int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                    do {
                        list.add(Double.valueOf(((CodedInputStream) this.Settings$ar$values).readDouble()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Double.valueOf(((CodedInputStream) this.Settings$ar$values).readDouble()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final int readEnum() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readEnum();
    }

    public final void readEnumList(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readEnum());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readEnum());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readEnum()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readEnum()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final Object readField(WireFormat.FieldType fieldType, Class cls, ExtensionRegistryLite extensionRegistryLite) {
        WireFormat.FieldType fieldType2 = WireFormat.FieldType.DOUBLE;
        switch (fieldType) {
            case DOUBLE:
                return Double.valueOf(readDouble());
            case FLOAT:
                return Float.valueOf(readFloat());
            case INT64:
                return Long.valueOf(readInt64());
            case UINT64:
                return Long.valueOf(readUInt64());
            case INT32:
                return Integer.valueOf(readInt32());
            case FIXED64:
                return Long.valueOf(readFixed64());
            case FIXED32:
                return Integer.valueOf(readFixed32());
            case BOOL:
                return Boolean.valueOf(readBool());
            case STRING:
                return readStringRequireUtf8();
            case GROUP:
            default:
                throw new IllegalArgumentException("unsupported field type.");
            case MESSAGE:
                return readMessage(cls, extensionRegistryLite);
            case BYTES:
                return readBytes();
            case UINT32:
                return Integer.valueOf(readUInt32());
            case ENUM:
                return Integer.valueOf(readEnum());
            case SFIXED32:
                return Integer.valueOf(readSFixed32());
            case SFIXED64:
                return Long.valueOf(readSFixed64());
            case SINT32:
                return Integer.valueOf(readSInt32());
            case SINT64:
                return Long.valueOf(readSInt64());
        }
    }

    public final int readFixed32() {
        requireWireType(5);
        return ((CodedInputStream) this.Settings$ar$values).readFixed32();
    }

    public final void readFixed32List(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 2) {
                if (tagWireType != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readFixed32());
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        i = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (i == this.set);
            } else {
                int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt32);
                int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readFixed32());
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                return;
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 2) {
                if (tagWireType2 != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readFixed32()));
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.set);
                i = readTag;
            } else {
                int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt322);
                int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                do {
                    list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readFixed32()));
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                return;
            }
        }
        this.persisted = i;
    }

    public final long readFixed64() {
        requireWireType(1);
        return ((CodedInputStream) this.Settings$ar$values).readFixed64();
    }

    public final void readFixed64List(List list) {
        int readTag;
        int i;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt32);
                    int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                    do {
                        longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readFixed64());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readFixed64());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 1) {
                if (tagWireType2 == 2) {
                    int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt322);
                    int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                    do {
                        list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readFixed64()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readFixed64()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final float readFloat() {
        requireWireType(5);
        return ((CodedInputStream) this.Settings$ar$values).readFloat();
    }

    public final void readFloatList(List list) {
        int readTag;
        int i;
        if (list instanceof FloatArrayList) {
            FloatArrayList floatArrayList = (FloatArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 2) {
                if (tagWireType != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    floatArrayList.addFloat(((CodedInputStream) this.Settings$ar$values).readFloat());
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        i = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (i == this.set);
            } else {
                int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt32);
                int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                do {
                    floatArrayList.addFloat(((CodedInputStream) this.Settings$ar$values).readFloat());
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                return;
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 2) {
                if (tagWireType2 != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    list.add(Float.valueOf(((CodedInputStream) this.Settings$ar$values).readFloat()));
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.set);
                i = readTag;
            } else {
                int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt322);
                int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                do {
                    list.add(Float.valueOf(((CodedInputStream) this.Settings$ar$values).readFloat()));
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                return;
            }
        }
        this.persisted = i;
    }

    public final Object readGroup(Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        Object newInstance = schema.newInstance();
        mergeGroupFieldInternal(newInstance, schema, extensionRegistryLite);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    public final int readInt32() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readInt32();
    }

    public final void readInt32List(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readInt32());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readInt32());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readInt32()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readInt32()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final long readInt64() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readInt64();
    }

    public final void readInt64List(List list) {
        int readTag;
        int i;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readInt64());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readInt64());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readInt64()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readInt64()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final Object readMessage(Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        Object newInstance = schema.newInstance();
        mergeMessageFieldInternal(newInstance, schema, extensionRegistryLite);
        schema.makeImmutable(newInstance);
        return newInstance;
    }

    public final int readSFixed32() {
        requireWireType(5);
        return ((CodedInputStream) this.Settings$ar$values).readSFixed32();
    }

    public final void readSFixed32List(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 2) {
                if (tagWireType != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readSFixed32());
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        i = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (i == this.set);
            } else {
                int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt32);
                int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                do {
                    intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readSFixed32());
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                return;
            }
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 2) {
                if (tagWireType2 != 5) {
                    throw new InvalidProtocolBufferException.InvalidWireTypeException();
                }
                do {
                    list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readSFixed32()));
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (readTag == this.set);
                i = readTag;
            } else {
                int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                verifyPackedFixed32Length$ar$ds(readUInt322);
                int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                do {
                    list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readSFixed32()));
                } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                return;
            }
        }
        this.persisted = i;
    }

    public final long readSFixed64() {
        requireWireType(1);
        return ((CodedInputStream) this.Settings$ar$values).readSFixed64();
    }

    public final void readSFixed64List(List list) {
        int readTag;
        int i;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 1) {
                if (tagWireType == 2) {
                    int readUInt32 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt32);
                    int totalBytesRead = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt32;
                    do {
                        longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readSFixed64());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readSFixed64());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 1) {
                if (tagWireType2 == 2) {
                    int readUInt322 = ((CodedInputStream) this.Settings$ar$values).readUInt32();
                    verifyPackedFixed64Length$ar$ds(readUInt322);
                    int totalBytesRead2 = ((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() + readUInt322;
                    do {
                        list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readSFixed64()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readSFixed64()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final int readSInt32() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readSInt32();
    }

    public final void readSInt32List(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readSInt32());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readSInt32());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readSInt32()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readSInt32()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final long readSInt64() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readSInt64();
    }

    public final void readSInt64List(List list) {
        int readTag;
        int i;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readSInt64());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readSInt64());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readSInt64()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readSInt64()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final String readString() {
        requireWireType(2);
        return ((CodedInputStream) this.Settings$ar$values).readString();
    }

    public final void readStringListInternal(List list, boolean z) {
        String readString;
        int readTag;
        int i;
        if (WireFormat.getTagWireType(this.set) == 2) {
            if ((list instanceof LazyStringList) && !z) {
                LazyStringList lazyStringList = (LazyStringList) list;
                do {
                    readBytes();
                    lazyStringList.add$ar$ds$3cea9816_0();
                    if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        i = ((CodedInputStream) this.Settings$ar$values).readTag();
                    } else {
                        return;
                    }
                } while (i == this.set);
            } else {
                do {
                    if (z) {
                        readString = readStringRequireUtf8();
                    } else {
                        readString = readString();
                    }
                    list.add(readString);
                    if (((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                        return;
                    } else {
                        readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                    }
                } while (readTag == this.set);
                i = readTag;
            }
            this.persisted = i;
            return;
        }
        throw new InvalidProtocolBufferException.InvalidWireTypeException();
    }

    public final String readStringRequireUtf8() {
        requireWireType(2);
        return ((CodedInputStream) this.Settings$ar$values).readStringRequireUtf8();
    }

    public final int readUInt32() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readUInt32();
    }

    public final void readUInt32List(List list) {
        int readTag;
        int i;
        if (list instanceof IntArrayList) {
            IntArrayList intArrayList = (IntArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readUInt32());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                intArrayList.addInt(((CodedInputStream) this.Settings$ar$values).readUInt32());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readUInt32()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Integer.valueOf(((CodedInputStream) this.Settings$ar$values).readUInt32()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final long readUInt64() {
        requireWireType(0);
        return ((CodedInputStream) this.Settings$ar$values).readUInt64();
    }

    public final void readUInt64List(List list) {
        int readTag;
        int i;
        if (list instanceof LongArrayList) {
            LongArrayList longArrayList = (LongArrayList) list;
            int tagWireType = WireFormat.getTagWireType(this.set);
            if (tagWireType != 0) {
                if (tagWireType == 2) {
                    Object obj = this.Settings$ar$values;
                    int totalBytesRead = ((CodedInputStream) obj).getTotalBytesRead() + ((CodedInputStream) obj).readUInt32();
                    do {
                        longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readUInt64());
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead);
                    requirePosition(totalBytesRead);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                longArrayList.addLong(((CodedInputStream) this.Settings$ar$values).readUInt64());
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    i = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (i == this.set);
        } else {
            int tagWireType2 = WireFormat.getTagWireType(this.set);
            if (tagWireType2 != 0) {
                if (tagWireType2 == 2) {
                    Object obj2 = this.Settings$ar$values;
                    int totalBytesRead2 = ((CodedInputStream) obj2).getTotalBytesRead() + ((CodedInputStream) obj2).readUInt32();
                    do {
                        list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readUInt64()));
                    } while (((CodedInputStream) this.Settings$ar$values).getTotalBytesRead() < totalBytesRead2);
                    requirePosition(totalBytesRead2);
                    return;
                }
                throw new InvalidProtocolBufferException.InvalidWireTypeException();
            }
            do {
                list.add(Long.valueOf(((CodedInputStream) this.Settings$ar$values).readUInt64()));
                if (!((CodedInputStream) this.Settings$ar$values).isAtEnd()) {
                    readTag = ((CodedInputStream) this.Settings$ar$values).readTag();
                } else {
                    return;
                }
            } while (readTag == this.set);
            i = readTag;
        }
        this.persisted = i;
    }

    public final void requireWireType(int i) {
        if (WireFormat.getTagWireType(this.set) == i) {
        } else {
            throw new InvalidProtocolBufferException.InvalidWireTypeException();
        }
    }

    public final void set$ar$ds$b5988668_0(int i, int i2) {
        if (i >= 10) {
            return;
        }
        this.set = (1 << i) | this.set;
        this.persistValue = 0;
        this.persisted = 0;
        ((int[]) this.Settings$ar$values)[i] = i2;
    }

    public final boolean skipField() {
        int i;
        if (!((CodedInputStream) this.Settings$ar$values).isAtEnd() && (i = this.set) != this.persistValue) {
            return ((CodedInputStream) this.Settings$ar$values).skipField(i);
        }
        return false;
    }

    private Settings(CodedInputStream codedInputStream) {
        this.persisted = 0;
        Internal.checkNotNull$ar$ds$40668187_0(codedInputStream, "input");
        this.Settings$ar$values = codedInputStream;
        codedInputStream.wrapper$ar$class_merging = this;
    }

    public final Object readMessage(Class cls, ExtensionRegistryLite extensionRegistryLite) {
        requireWireType(2);
        return readMessage(Protobuf.INSTANCE.schemaFor(cls), extensionRegistryLite);
    }
}
