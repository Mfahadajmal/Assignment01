package io.grpc.okhttp.internal.proxy;

import com.google.mlkit.common.model.RemoteModelManager;
import io.grpc.okhttp.internal.Headers;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Request {
    public final Headers headers;
    public final HttpUrl url;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public final RemoteModelManager headers$ar$class_merging = new RemoteModelManager();
        public HttpUrl url;

        /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v3, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v3, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v5, types: [java.util.List, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v9, types: [java.util.List, java.lang.Object] */
        public final void header$ar$ds(String str, String str2) {
            if (!str.isEmpty()) {
                int length = str.length();
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    char charAt = str.charAt(i2);
                    if (charAt <= 31 || charAt >= 127) {
                        throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in header name: %s", Integer.valueOf(charAt), Integer.valueOf(i2), str));
                    }
                }
                if (str2 != null) {
                    for (int i3 = 0; i3 < str2.length(); i3++) {
                        char charAt2 = str2.charAt(i3);
                        if (charAt2 <= 31 || charAt2 >= 127) {
                            throw new IllegalArgumentException(String.format(Locale.US, "Unexpected char %#04x at %d in header value: %s", Integer.valueOf(charAt2), Integer.valueOf(i3), str2));
                        }
                    }
                    while (true) {
                        RemoteModelManager remoteModelManager = this.headers$ar$class_merging;
                        if (i < remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.size()) {
                            if (str.equalsIgnoreCase((String) remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.get(i))) {
                                remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.remove(i);
                                remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.remove(i);
                                i -= 2;
                            }
                            i += 2;
                        } else {
                            remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.add(str);
                            remoteModelManager.RemoteModelManager$ar$remoteModelManagerInstances.add(str2.trim());
                            return;
                        }
                    }
                } else {
                    throw new IllegalArgumentException("value == null");
                }
            } else {
                throw new IllegalArgumentException("name is empty");
            }
        }
    }

    public Request(Builder builder) {
        this.url = builder.url;
        this.headers = new Headers(builder.headers$ar$class_merging);
    }

    public final String toString() {
        return "Request{url=" + String.valueOf(this.url) + "}";
    }
}
