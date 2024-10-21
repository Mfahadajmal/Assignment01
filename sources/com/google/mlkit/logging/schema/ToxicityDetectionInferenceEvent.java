package com.google.mlkit.logging.schema;

import okio.ByteString;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToxicityDetectionInferenceEvent {
    public static final ByteString encodeUtf8$ar$ds(String str) {
        str.getClass();
        ByteString byteString = new ByteString(ToxicityDetectionLoadEvent.asUtf8ToByteArray(str));
        byteString.utf8 = str;
        return byteString;
    }
}
