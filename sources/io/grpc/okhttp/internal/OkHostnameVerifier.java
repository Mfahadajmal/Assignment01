package io.grpc.okhttp.internal;

import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHostnameVerifier implements HostnameVerifier {
    public static final OkHostnameVerifier INSTANCE = new OkHostnameVerifier();
    private static final Pattern VERIFY_AS_IP_ADDRESS = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    private OkHostnameVerifier() {
    }

    private static List getSubjectAltNames(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (list != null && list.size() >= 2 && (num = (Integer) list.get(0)) != null && num.intValue() == i && (str = (String) list.get(1)) != null) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    private static final boolean verifyHostName$ar$ds(String str, String str2) {
        if (str != null && str.length() != 0 && !str.startsWith(".") && !str.endsWith("..") && str2 != null && str2.length() != 0 && !str2.startsWith(".") && !str2.endsWith("..")) {
            if (!str.endsWith(".")) {
                str = str.concat(".");
            }
            if (!str2.endsWith(".")) {
                str2 = str2.concat(".");
            }
            String lowerCase = str2.toLowerCase(Locale.US);
            if (!lowerCase.contains("*")) {
                return str.equals(lowerCase);
            }
            if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
                return false;
            }
            String substring = lowerCase.substring(1);
            if (!str.endsWith(substring)) {
                return false;
            }
            int length = str.length() - substring.length();
            if (length > 0 && str.lastIndexOf(46, length - 1) != -1) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:80:0x00ec, code lost:
    
        r1 = r3.beg;
        r7 = new java.lang.String(r8, r1, r3.end - r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0213 A[Catch: SSLException -> 0x0218, TryCatch #0 {SSLException -> 0x0218, blocks: (B:29:0x0213, B:42:0x00a7, B:43:0x00ab, B:84:0x00b1, B:45:0x00bf, B:77:0x00cd, B:72:0x00da, B:80:0x00ec, B:48:0x00f8, B:49:0x0106, B:51:0x010c, B:53:0x0112, B:56:0x0121, B:61:0x012b, B:87:0x0201, B:89:0x0209, B:91:0x021a, B:97:0x0228, B:98:0x0237, B:99:0x0238, B:103:0x0245, B:104:0x0254, B:109:0x0139, B:111:0x013d, B:112:0x0143, B:114:0x0149, B:129:0x0156, B:130:0x015c, B:132:0x0162, B:134:0x0168, B:136:0x0181, B:138:0x0189, B:140:0x018d, B:142:0x0196, B:144:0x01a2, B:146:0x01ac, B:147:0x01bb, B:123:0x0175, B:125:0x017a, B:152:0x017f, B:157:0x01bc, B:158:0x01cb, B:159:0x01cc, B:160:0x01d4, B:162:0x01da, B:171:0x01e0, B:172:0x01e4, B:174:0x01ea, B:176:0x01f0, B:178:0x01f5, B:165:0x0257, B:167:0x0264, B:168:0x0260, B:181:0x0270, B:182:0x027f), top: B:41:0x00a7 }] */
    @Override // javax.net.ssl.HostnameVerifier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean verify(java.lang.String r17, javax.net.ssl.SSLSession r18) {
        /*
            Method dump skipped, instructions count: 644
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.okhttp.internal.OkHostnameVerifier.verify(java.lang.String, javax.net.ssl.SSLSession):boolean");
    }
}
