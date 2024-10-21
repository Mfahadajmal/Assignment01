package io.grpc;

import com.google.common.io.BaseEncoding;
import io.grpc.Metadata;
import java.nio.charset.Charset;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InternalMetadata {
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    public static final BaseEncoding BASE64_ENCODING_OMIT_PADDING = Metadata.BASE64_ENCODING_OMIT_PADDING;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TrustedAsciiMarshaller extends Metadata.TrustedAsciiMarshaller {
    }

    public static Metadata.Key keyOf(String str, TrustedAsciiMarshaller trustedAsciiMarshaller) {
        boolean z = false;
        if (!str.isEmpty() && str.charAt(0) == ':') {
            z = true;
        }
        return new Metadata.TrustedAsciiKey(str, z, trustedAsciiMarshaller);
    }
}
