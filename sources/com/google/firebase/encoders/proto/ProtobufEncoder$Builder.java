package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.config.EncoderConfig;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtobufEncoder$Builder implements EncoderConfig {
    private static final ObjectEncoder DEFAULT_FALLBACK_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0(2);
    public final Map objectEncoders = new HashMap();
    public final Map valueEncoders = new HashMap();
    public final ObjectEncoder fallbackEncoder = DEFAULT_FALLBACK_ENCODER;

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ void registerEncoder$ar$ds(Class cls, ObjectEncoder objectEncoder) {
        this.objectEncoders.put(cls, objectEncoder);
        this.valueEncoders.remove(cls);
    }
}
