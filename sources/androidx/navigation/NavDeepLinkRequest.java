package androidx.navigation;

import android.net.Uri;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavDeepLinkRequest {
    public final String action;
    public final String mimeType;
    public final Uri uri;

    public NavDeepLinkRequest(Uri uri, String str, String str2) {
        this.uri = uri;
        this.action = str;
        this.mimeType = str2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("NavDeepLinkRequest{");
        if (this.uri != null) {
            sb.append(" uri=");
            sb.append(String.valueOf(this.uri));
        }
        if (this.action != null) {
            sb.append(" action=");
            sb.append(this.action);
        }
        if (this.mimeType != null) {
            sb.append(" mimetype=");
            sb.append(this.mimeType);
        }
        sb.append(" }");
        return sb.toString();
    }
}
