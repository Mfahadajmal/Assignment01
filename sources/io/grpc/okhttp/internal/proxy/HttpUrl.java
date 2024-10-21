package io.grpc.okhttp.internal.proxy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HttpUrl {
    public final String host;
    public final int port;
    private final String url;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public String host;
        public int port = -1;
        public String scheme;

        /* JADX WARN: Code restructure failed: missing block: B:25:0x00c0, code lost:
        
            return null;
         */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0096  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static java.net.InetAddress decodeIpv6$ar$ds(java.lang.String r16, int r17) {
            /*
                Method dump skipped, instructions count: 222
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.internal.proxy.HttpUrl.Builder.decodeIpv6$ar$ds(java.lang.String, int):java.net.InetAddress");
        }

        final int effectivePort() {
            int i = this.port;
            if (i != -1) {
                return i;
            }
            return HttpUrl.defaultPort(this.scheme);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            if (this.host.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.host);
                sb.append(']');
            } else {
                sb.append(this.host);
            }
            int effectivePort = effectivePort();
            if (effectivePort != HttpUrl.defaultPort(this.scheme)) {
                sb.append(':');
                sb.append(effectivePort);
            }
            return sb.toString();
        }
    }

    public HttpUrl(Builder builder) {
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.url = builder.toString();
    }

    public static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        return -1;
    }

    public static int defaultPort(String str) {
        if (str.equals("http")) {
            return 80;
        }
        if (str.equals("https")) {
            return 443;
        }
        return -1;
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof HttpUrl) && ((HttpUrl) obj).url.equals(this.url)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.url.hashCode();
    }

    public final String toString() {
        return this.url;
    }
}
