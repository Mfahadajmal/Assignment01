package com.google.auth.oauth2;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Objects;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessToken implements Serializable {
    private static final long serialVersionUID = -8514239465808977353L;
    public final Long expirationTimeMillis = null;
    private final List scopes = new ArrayList();
    public final String tokenValue;

    public AccessToken(String str) {
        this.tokenValue = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AccessToken)) {
            return false;
        }
        AccessToken accessToken = (AccessToken) obj;
        if (Objects.equals(this.tokenValue, accessToken.tokenValue)) {
            Long l = accessToken.expirationTimeMillis;
            if (Objects.equals(null, null) && Objects.equals(this.scopes, accessToken.scopes)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.tokenValue, null, this.scopes);
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("tokenValue", this.tokenValue);
        stringHelper.addHolder$ar$ds("expirationTimeMillis", null);
        stringHelper.addHolder$ar$ds("scopes", this.scopes);
        return stringHelper.toString();
    }
}
