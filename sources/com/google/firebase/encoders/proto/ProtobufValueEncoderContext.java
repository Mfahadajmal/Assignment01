package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtobufValueEncoderContext implements ValueEncoderContext {
    public FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    public boolean encoded = false;
    public boolean skipDefault = false;

    public ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext) {
        this.objEncoderCtx = protobufDataEncoderContext;
    }

    private final void checkNotUsed() {
        if (!this.encoded) {
            this.encoded = true;
            return;
        }
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final void add$ar$ds$18f2e6ce_0(boolean z) {
        checkNotUsed();
        this.objEncoderCtx.add$ar$ds$b85ccfce_0(this.field, z ? 1 : 0, this.skipDefault);
    }

    @Override // com.google.firebase.encoders.ValueEncoderContext
    public final void add$ar$ds$cff682e7_0(String str) {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, str, this.skipDefault);
    }
}
