package com.google.protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum JavaType {
    VOID(Void.class),
    INT(Integer.class),
    LONG(Long.class),
    FLOAT(Float.class),
    DOUBLE(Double.class),
    BOOLEAN(Boolean.class),
    STRING(String.class),
    BYTE_STRING(ByteString.class),
    ENUM(Integer.class),
    MESSAGE(Object.class);

    public final Class boxedType;

    static {
        ByteString byteString = ByteString.EMPTY;
    }

    JavaType(Class cls) {
        this.boxedType = cls;
    }
}
