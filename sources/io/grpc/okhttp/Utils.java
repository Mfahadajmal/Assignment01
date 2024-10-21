package io.grpc.okhttp;

import io.grpc.internal.TransportFrameUtil;
import io.grpc.okhttp.internal.framed.Header;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Utils {
    static {
        Logger.getLogger(Utils.class.getName());
    }

    private Utils() {
    }

    public static byte[][] convertHeadersToArray(List list) {
        int size = list.size();
        byte[][] bArr = new byte[size + size];
        Iterator it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            Header header = (Header) it.next();
            bArr[i] = header.name.toByteArray();
            bArr[i + 1] = header.value.toByteArray();
            i += 2;
        }
        return TransportFrameUtil.toRawSerializedHeaders(bArr);
    }
}
