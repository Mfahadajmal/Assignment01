package com.google.mlkit.logging.schema;

import com.google.firebase.encoders.proto.ProtoEnum;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum MLKitEnum$ClientType implements ProtoEnum {
    TYPE_UNKNOWN(0),
    TYPE_THIN(1),
    TYPE_THICK(2),
    TYPE_GMV(3);

    private final int value;

    MLKitEnum$ClientType(int i) {
        this.value = i;
    }

    @Override // com.google.firebase.encoders.proto.ProtoEnum
    public final int getNumber() {
        return this.value;
    }
}
