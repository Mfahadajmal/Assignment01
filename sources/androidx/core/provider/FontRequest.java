package androidx.core.provider;

import _COROUTINE._BOUNDARY;
import android.util.Base64;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FontRequest {
    public final List mCertificates;
    public final String mIdentifier;
    public final String mProviderAuthority;
    public final String mProviderPackage;
    public final String mQuery;

    public FontRequest(String str, String str2, String str3, List list) {
        this.mProviderAuthority = str;
        this.mProviderPackage = str2;
        this.mQuery = str3;
        list.getClass();
        this.mCertificates = list;
        this.mIdentifier = str + "-" + str2 + "-" + str3;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");
        for (int i = 0; i < this.mCertificates.size(); i++) {
            sb.append(" [");
            List list = (List) this.mCertificates.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString((byte[]) list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(0, "mCertificatesArray: "));
        return sb.toString();
    }
}
