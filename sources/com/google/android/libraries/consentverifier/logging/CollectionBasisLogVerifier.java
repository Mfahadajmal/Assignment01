package com.google.android.libraries.consentverifier.logging;

import android.content.Context;
import com.google.android.libraries.consentverifier.BaseProtoCollectionBasis;
import com.google.android.libraries.consentverifier.CollectionBasisContext;
import com.google.android.libraries.consentverifier.VerifiableProtoCollectionBasis;
import com.google.common.base.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionBasisLogVerifier {
    private final CollectionBasisContext collectionBasisContext;
    private final VerifiableProtoCollectionBasis verifiableBasis;

    protected CollectionBasisLogVerifier(Context context, VerifiableProtoCollectionBasis verifiableProtoCollectionBasis) {
        Context context2;
        context.getClass();
        Context applicationContext = context.getApplicationContext();
        CollectionBasisVerificationException collectionBasisVerificationException = new CollectionBasisVerificationException();
        CollectionBasisContext.Builder builder = new CollectionBasisContext.Builder(null);
        builder.setGooglerOverridesCheckbox$ar$ds();
        if (applicationContext != null) {
            builder.context = applicationContext;
            builder.stacktrace = Optional.of(collectionBasisVerificationException);
            builder.setGooglerOverridesCheckbox$ar$ds();
            if (builder.set$0 == 1 && (context2 = builder.context) != null) {
                this.collectionBasisContext = new CollectionBasisContext(context2, builder.accountNames, builder.stacktrace, builder.executor);
                this.verifiableBasis = verifiableProtoCollectionBasis;
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (builder.context == null) {
                sb.append(" context");
            }
            if (builder.set$0 == 0) {
                sb.append(" googlerOverridesCheckbox");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        throw new NullPointerException("Null context");
    }

    public static CollectionBasisLogVerifier newInstance$ar$class_merging$30765897_0(Context context, BaseProtoCollectionBasis baseProtoCollectionBasis) {
        return new CollectionBasisLogVerifier(context, new VerifiableProtoCollectionBasis(baseProtoCollectionBasis));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0171 A[Catch: all -> 0x0195, TryCatch #1 {, blocks: (B:8:0x0021, B:10:0x0025, B:12:0x0033, B:14:0x003f, B:16:0x004b, B:17:0x017f, B:19:0x0183, B:20:0x018a, B:22:0x0053, B:24:0x005d, B:25:0x0061, B:29:0x006f, B:31:0x0074, B:42:0x00f4, B:43:0x016d, B:45:0x0171, B:60:0x00fa, B:61:0x00fd, B:64:0x010e, B:65:0x011a, B:67:0x0122, B:68:0x0129, B:70:0x012d, B:73:0x0133, B:75:0x014a, B:77:0x014e, B:79:0x0156, B:81:0x015e, B:83:0x0167, B:86:0x0175, B:89:0x018c, B:90:0x018f, B:93:0x010b, B:94:0x0190, B:95:0x0193, B:33:0x007e, B:35:0x0083, B:36:0x0086, B:38:0x0097, B:39:0x009d, B:41:0x00a1, B:46:0x00ab, B:48:0x00b4, B:52:0x00be, B:55:0x00cb, B:58:0x00da, B:28:0x0066, B:92:0x0104), top: B:7:0x0021, inners: #2, #4, #8 }] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.os.StrictMode$ThreadPolicy] */
    /* JADX WARN: Type inference failed for: r7v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean canLog(com.google.protobuf.ByteString r18) {
        /*
            Method dump skipped, instructions count: 473
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.consentverifier.logging.CollectionBasisLogVerifier.canLog(com.google.protobuf.ByteString):boolean");
    }

    public final String toString() {
        return "CollectionBasisLogVerifier{collectionBasisContext=" + this.collectionBasisContext + ", basis=" + this.verifiableBasis + "}";
    }
}
