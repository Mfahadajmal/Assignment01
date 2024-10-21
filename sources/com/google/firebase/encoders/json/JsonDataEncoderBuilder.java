package com.google.firebase.encoders.json;

import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ValueEncoder;
import com.google.firebase.encoders.ValueEncoderContext;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.ProtobufDataEncoderContext$$ExternalSyntheticLambda0;
import j$.util.DesugarTimeZone;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JsonDataEncoderBuilder implements EncoderConfig {
    private static final ValueEncoder BOOLEAN_ENCODER;
    public static final /* synthetic */ int JsonDataEncoderBuilder$ar$NoOp = 0;
    private static final ValueEncoder STRING_ENCODER;
    private static final ObjectEncoder DEFAULT_FALLBACK_ENCODER = new ProtobufDataEncoderContext$$ExternalSyntheticLambda0(1);
    private static final TimestampEncoder TIMESTAMP_ENCODER = new TimestampEncoder();
    public final Map objectEncoders = new HashMap();
    public final Map valueEncoders = new HashMap();
    public final ObjectEncoder fallbackEncoder = DEFAULT_FALLBACK_ENCODER;
    public boolean ignoreNullValues = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TimestampEncoder implements ValueEncoder {
        private static final DateFormat rfc339;

        static {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
            rfc339 = simpleDateFormat;
            simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        }

        @Override // com.google.firebase.encoders.Encoder
        public final /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            ((ValueEncoderContext) obj2).add$ar$ds$cff682e7_0(rfc339.format((Date) obj));
        }
    }

    static {
        final int i = 1;
        STRING_ENCODER = new ValueEncoder() { // from class: com.google.firebase.encoders.json.JsonDataEncoderBuilder$$ExternalSyntheticLambda2
            @Override // com.google.firebase.encoders.Encoder
            public final void encode(Object obj, Object obj2) {
                if (i != 0) {
                    int i2 = JsonDataEncoderBuilder.JsonDataEncoderBuilder$ar$NoOp;
                    ((ValueEncoderContext) obj2).add$ar$ds$cff682e7_0((String) obj);
                } else {
                    int i3 = JsonDataEncoderBuilder.JsonDataEncoderBuilder$ar$NoOp;
                    ((ValueEncoderContext) obj2).add$ar$ds$18f2e6ce_0(((Boolean) obj).booleanValue());
                }
            }
        };
        final int i2 = 0;
        BOOLEAN_ENCODER = new ValueEncoder() { // from class: com.google.firebase.encoders.json.JsonDataEncoderBuilder$$ExternalSyntheticLambda2
            @Override // com.google.firebase.encoders.Encoder
            public final void encode(Object obj, Object obj2) {
                if (i2 != 0) {
                    int i22 = JsonDataEncoderBuilder.JsonDataEncoderBuilder$ar$NoOp;
                    ((ValueEncoderContext) obj2).add$ar$ds$cff682e7_0((String) obj);
                } else {
                    int i3 = JsonDataEncoderBuilder.JsonDataEncoderBuilder$ar$NoOp;
                    ((ValueEncoderContext) obj2).add$ar$ds$18f2e6ce_0(((Boolean) obj).booleanValue());
                }
            }
        };
    }

    public JsonDataEncoderBuilder() {
        registerEncoder$ar$ds$a4346d3a_0(String.class, STRING_ENCODER);
        registerEncoder$ar$ds$a4346d3a_0(Boolean.class, BOOLEAN_ENCODER);
        registerEncoder$ar$ds$a4346d3a_0(Date.class, TIMESTAMP_ENCODER);
    }

    @Override // com.google.firebase.encoders.config.EncoderConfig
    public final /* bridge */ /* synthetic */ void registerEncoder$ar$ds(Class cls, ObjectEncoder objectEncoder) {
        this.objectEncoders.put(cls, objectEncoder);
        this.valueEncoders.remove(cls);
    }

    public final void registerEncoder$ar$ds$a4346d3a_0(Class cls, ValueEncoder valueEncoder) {
        this.valueEncoders.put(cls, valueEncoder);
        this.objectEncoders.remove(cls);
    }
}
