package io.grpc.okhttp.internal;

import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionSpec {
    private static final CipherSuite[] APPROVED_CIPHER_SUITES;
    public static final ConnectionSpec MODERN_TLS;
    public final String[] cipherSuites;
    public final boolean supportsTlsExtensions;
    final boolean tls;
    public final String[] tlsVersions;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public String[] cipherSuites;
        public boolean supportsTlsExtensions;
        public final boolean tls;
        public String[] tlsVersions;

        public Builder(boolean z) {
            this.tls = z;
        }

        public final void cipherSuites$ar$ds(CipherSuite... cipherSuiteArr) {
            if (this.tls) {
                String[] strArr = new String[cipherSuiteArr.length];
                for (int i = 0; i < cipherSuiteArr.length; i++) {
                    strArr[i] = cipherSuiteArr[i].javaName;
                }
                this.cipherSuites = strArr;
                return;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public final void supportsTlsExtensions$ar$ds() {
            if (this.tls) {
                this.supportsTlsExtensions = true;
                return;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public final void tlsVersions$ar$ds(TlsVersion... tlsVersionArr) {
            if (this.tls) {
                String[] strArr = new String[tlsVersionArr.length];
                for (int i = 0; i < tlsVersionArr.length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                this.tlsVersions = strArr;
                return;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }
    }

    static {
        CipherSuite[] cipherSuiteArr = {CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        APPROVED_CIPHER_SUITES = cipherSuiteArr;
        Builder builder = new Builder(true);
        builder.cipherSuites$ar$ds(cipherSuiteArr);
        builder.tlsVersions$ar$ds(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2);
        builder.supportsTlsExtensions$ar$ds();
        ConnectionSpec connectionSpec = new ConnectionSpec(builder);
        MODERN_TLS = connectionSpec;
        Builder builder2 = new Builder(connectionSpec);
        builder2.tlsVersions$ar$ds(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0);
        builder2.supportsTlsExtensions$ar$ds();
    }

    public ConnectionSpec(Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ConnectionSpec)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ConnectionSpec connectionSpec = (ConnectionSpec) obj;
        boolean z = this.tls;
        if (z != connectionSpec.tls) {
            return false;
        }
        if (z && (!Arrays.equals(this.cipherSuites, connectionSpec.cipherSuites) || !Arrays.equals(this.tlsVersions, connectionSpec.tlsVersions) || this.supportsTlsExtensions != connectionSpec.supportsTlsExtensions)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        if (this.tls) {
            return ((((Arrays.hashCode(this.cipherSuites) + 527) * 31) + Arrays.hashCode(this.tlsVersions)) * 31) + (!this.supportsTlsExtensions ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        List immutableList;
        CipherSuite valueOf;
        String obj;
        TlsVersion tlsVersion;
        if (this.tls) {
            String[] strArr = this.cipherSuites;
            int i = 0;
            if (strArr == null) {
                immutableList = null;
            } else {
                CipherSuite[] cipherSuiteArr = new CipherSuite[strArr.length];
                int i2 = 0;
                while (true) {
                    String[] strArr2 = this.cipherSuites;
                    if (i2 >= strArr2.length) {
                        break;
                    }
                    String str = strArr2[i2];
                    CipherSuite cipherSuite = CipherSuite.TLS_RSA_WITH_NULL_MD5;
                    if (str.startsWith("SSL_")) {
                        valueOf = CipherSuite.valueOf("TLS_".concat(String.valueOf(str.substring(4))));
                    } else {
                        valueOf = CipherSuite.valueOf(str);
                    }
                    cipherSuiteArr[i2] = valueOf;
                    i2++;
                }
                immutableList = Util.immutableList(cipherSuiteArr);
            }
            if (immutableList == null) {
                obj = "[use default]";
            } else {
                obj = immutableList.toString();
            }
            TlsVersion[] tlsVersionArr = new TlsVersion[this.tlsVersions.length];
            while (true) {
                String[] strArr3 = this.tlsVersions;
                if (i < strArr3.length) {
                    String str2 = strArr3[i];
                    TlsVersion tlsVersion2 = TlsVersion.TLS_1_3;
                    if ("TLSv1.3".equals(str2)) {
                        tlsVersion = TlsVersion.TLS_1_3;
                    } else if ("TLSv1.2".equals(str2)) {
                        tlsVersion = TlsVersion.TLS_1_2;
                    } else if ("TLSv1.1".equals(str2)) {
                        tlsVersion = TlsVersion.TLS_1_1;
                    } else if ("TLSv1".equals(str2)) {
                        tlsVersion = TlsVersion.TLS_1_0;
                    } else if ("SSLv3".equals(str2)) {
                        tlsVersion = TlsVersion.SSL_3_0;
                    } else {
                        throw new IllegalArgumentException("Unexpected TLS version: ".concat(String.valueOf(str2)));
                    }
                    tlsVersionArr[i] = tlsVersion;
                    i++;
                } else {
                    return "ConnectionSpec(cipherSuites=" + obj + ", tlsVersions=" + String.valueOf(Util.immutableList(tlsVersionArr)) + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
                }
            }
        } else {
            return "ConnectionSpec()";
        }
    }
}
