package okio;

import com.google.mlkit.logging.schema.ToxicityDetectionInferenceEvent;

/* compiled from: PG */
/* renamed from: okio.-Base64, reason: invalid class name */
/* loaded from: classes.dex */
public final class Base64 {
    public static final byte[] BASE64;

    static {
        ByteString byteString = ByteString.EMPTY;
        BASE64 = ToxicityDetectionInferenceEvent.encodeUtf8$ar$ds("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/").data;
        ToxicityDetectionInferenceEvent.encodeUtf8$ar$ds("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");
    }
}
