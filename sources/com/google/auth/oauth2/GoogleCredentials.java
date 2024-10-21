package com.google.auth.oauth2;

import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleCredentials extends OAuth2Credentials {
    private static final long serialVersionUID = -1522852442442473691L;
    private final boolean isExplicitUniverseDomain;
    protected final String quotaProjectId;
    private final String universeDomain;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends OAuth2Credentials.Builder {
    }

    static {
        int i = DefaultCredentialsProvider.DefaultCredentialsProvider$ar$NoOp;
    }

    protected GoogleCredentials() {
        this(new Builder());
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public boolean equals(Object obj) {
        if (!(obj instanceof GoogleCredentials)) {
            return false;
        }
        GoogleCredentials googleCredentials = (GoogleCredentials) obj;
        String str = googleCredentials.quotaProjectId;
        if (Objects.equals(null, null) && Objects.equals(this.universeDomain, googleCredentials.universeDomain)) {
            boolean z = googleCredentials.isExplicitUniverseDomain;
            if (Objects.equals(false, false)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public int hashCode() {
        return Objects.hash(null, this.universeDomain, false);
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public final String toString() {
        return toStringHelper().toString();
    }

    protected MoreObjects$ToStringHelper toStringHelper() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.omitNullValues$ar$ds();
        stringHelper.addHolder$ar$ds("quotaProjectId", null);
        stringHelper.addHolder$ar$ds("universeDomain", this.universeDomain);
        return stringHelper.add("isExplicitUniverseDomain", false);
    }

    protected GoogleCredentials(Builder builder) {
        super(builder.accessToken, builder.refreshMargin, builder.expirationMargin);
        this.quotaProjectId = null;
        this.universeDomain = "googleapis.com";
        this.isExplicitUniverseDomain = false;
    }
}
