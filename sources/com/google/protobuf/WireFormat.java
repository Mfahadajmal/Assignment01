package com.google.protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WireFormat {
    static final int MESSAGE_SET_ITEM_TAG = makeTag(1, 3);
    static final int MESSAGE_SET_ITEM_END_TAG = makeTag(1, 4);
    static final int MESSAGE_SET_TYPE_ID_TAG = makeTag(2, 0);
    static final int MESSAGE_SET_MESSAGE_TAG = makeTag(3, 2);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum FieldType {
        DOUBLE(JavaType.DOUBLE, 1),
        FLOAT(JavaType.FLOAT, 5),
        INT64(JavaType.LONG, 0),
        UINT64(JavaType.LONG, 0),
        INT32(JavaType.INT, 0),
        FIXED64(JavaType.LONG, 1),
        FIXED32(JavaType.INT, 5),
        BOOL(JavaType.BOOLEAN, 0),
        STRING(JavaType.STRING, 2),
        GROUP(JavaType.MESSAGE, 3),
        MESSAGE(JavaType.MESSAGE, 2),
        BYTES(JavaType.BYTE_STRING, 2),
        UINT32(JavaType.INT, 0),
        ENUM(JavaType.ENUM, 0),
        SFIXED32(JavaType.INT, 5),
        SFIXED64(JavaType.LONG, 1),
        SINT32(JavaType.INT, 0),
        SINT64(JavaType.LONG, 0);

        public final JavaType javaType;
        public final int wireType;

        FieldType(JavaType javaType, int i) {
            this.javaType = javaType;
            this.wireType = i;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum JavaType {
        INT,
        LONG,
        FLOAT,
        DOUBLE,
        BOOLEAN,
        STRING,
        BYTE_STRING,
        ENUM,
        MESSAGE;

        static {
            ByteString byteString = ByteString.EMPTY;
        }
    }

    public static int getTagFieldNumber(int i) {
        return i >>> 3;
    }

    public static int getTagWireType(int i) {
        return i & 7;
    }

    public static int makeTag(int i, int i2) {
        return (i << 3) | i2;
    }
}
