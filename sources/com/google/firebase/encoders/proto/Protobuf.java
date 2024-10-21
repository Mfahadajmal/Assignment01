package com.google.firebase.encoders.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public @interface Protobuf {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum IntEncoding {
        DEFAULT,
        SIGNED,
        FIXED
    }

    IntEncoding intEncoding() default IntEncoding.DEFAULT;

    int tag();
}
