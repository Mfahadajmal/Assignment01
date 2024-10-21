package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ProtobufDataEncoderContext$$ExternalSyntheticLambda0 implements ObjectEncoder {
    private final /* synthetic */ int switching_field;

    @Override // com.google.firebase.encoders.Encoder
    public final void encode(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
            }
            throw new EncodingException("Couldn't find encoder for type ".concat(String.valueOf(obj.getClass().getCanonicalName())));
        }
        Map.Entry entry = (Map.Entry) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.add$ar$ds$6bd70da1_0(ProtobufDataEncoderContext.MAP_KEY_DESC, entry.getKey());
        objectEncoderContext.add$ar$ds$6bd70da1_0(ProtobufDataEncoderContext.MAP_VALUE_DESC, entry.getValue());
    }
}
