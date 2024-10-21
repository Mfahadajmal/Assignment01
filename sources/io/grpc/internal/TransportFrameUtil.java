package io.grpc.internal;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransportFrameUtil {
    private static final Logger logger = Logger.getLogger(TransportFrameUtil.class.getName());
    private static final byte[] binaryHeaderSuffixBytes = "-bin".getBytes(Charsets.US_ASCII);

    private TransportFrameUtil() {
    }

    private static boolean endsWith(byte[] bArr, byte[] bArr2) {
        int length = bArr.length - bArr2.length;
        if (length < 0) {
            return false;
        }
        for (int i = length; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i - length]) {
                return false;
            }
        }
        return true;
    }

    public static byte[][] toHttp2Headers(Metadata metadata) {
        int i;
        Charset charset = InternalMetadata.US_ASCII;
        int len = metadata.len();
        byte[][] bArr = new byte[len];
        Object[] objArr = metadata.namesAndValues;
        if (objArr instanceof byte[][]) {
            System.arraycopy(objArr, 0, bArr, 0, metadata.len());
        } else {
            for (int i2 = 0; i2 < metadata.size; i2++) {
                int i3 = i2 + i2;
                bArr[i3] = metadata.name(i2);
                bArr[i3 + 1] = metadata.valueAsBytes(i2);
            }
        }
        int i4 = 0;
        for (int i5 = 0; i5 < len; i5 += 2) {
            byte[] bArr2 = bArr[i5];
            byte[] bArr3 = bArr[i5 + 1];
            if (endsWith(bArr2, binaryHeaderSuffixBytes)) {
                i = i4 + 2;
                bArr[i4] = bArr2;
                bArr[i4 + 1] = InternalMetadata.BASE64_ENCODING_OMIT_PADDING.encode(bArr3).getBytes(Charsets.US_ASCII);
            } else {
                for (byte b : bArr3) {
                    if (b < 32 || b > 126) {
                        logger.logp(Level.WARNING, "io.grpc.internal.TransportFrameUtil", "toHttp2Headers", "Metadata key=" + new String(bArr2, Charsets.US_ASCII) + ", value=" + Arrays.toString(bArr3) + " contains invalid ASCII characters");
                        break;
                    }
                }
                i = i4 + 2;
                bArr[i4] = bArr2;
                bArr[i4 + 1] = bArr3;
            }
            i4 = i;
        }
        if (i4 == len) {
            return bArr;
        }
        return (byte[][]) Arrays.copyOfRange(bArr, 0, i4);
    }

    public static byte[][] toRawSerializedHeaders(byte[][] bArr) {
        int i = 0;
        while (i < bArr.length) {
            byte[] bArr2 = bArr[i];
            int i2 = i + 1;
            byte[] bArr3 = bArr[i2];
            if (endsWith(bArr2, binaryHeaderSuffixBytes)) {
                for (byte b : bArr3) {
                    if (b == 44) {
                        ArrayList arrayList = new ArrayList(bArr.length + 10);
                        for (int i3 = 0; i3 < i; i3++) {
                            arrayList.add(bArr[i3]);
                        }
                        while (i < bArr.length) {
                            byte[] bArr4 = bArr[i];
                            byte[] bArr5 = bArr[i + 1];
                            if (!endsWith(bArr4, binaryHeaderSuffixBytes)) {
                                arrayList.add(bArr4);
                                arrayList.add(bArr5);
                            } else {
                                int i4 = 0;
                                int i5 = 0;
                                while (true) {
                                    int length = bArr5.length;
                                    if (i4 <= length) {
                                        if (i4 == length || bArr5[i4] == 44) {
                                            byte[] decode = BaseEncoding.BASE64.decode(new String(bArr5, i5, i4 - i5, Charsets.US_ASCII));
                                            arrayList.add(bArr4);
                                            arrayList.add(decode);
                                            i5 = i4 + 1;
                                        }
                                        i4++;
                                    }
                                }
                            }
                            i += 2;
                        }
                        return (byte[][]) arrayList.toArray(new byte[0]);
                    }
                }
                bArr[i2] = BaseEncoding.BASE64.decode(new String(bArr3, Charsets.US_ASCII));
            }
            i += 2;
        }
        return bArr;
    }
}
