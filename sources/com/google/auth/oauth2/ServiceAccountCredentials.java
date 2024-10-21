package com.google.auth.oauth2;

import com.google.common.base.MoreObjects$ToStringHelper;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import java.io.ObjectInputStream;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ServiceAccountCredentials extends GoogleCredentials {
    private static final long serialVersionUID = 7807543542681217978L;

    private void readObject(ObjectInputStream objectInputStream) {
        throw null;
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public final boolean equals(Object obj) {
        throw null;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials, com.google.auth.Credentials
    public final void getRequestMetadata$ar$class_merging$ar$ds(Executor executor, GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1) {
        throw null;
    }

    @Override // com.google.auth.oauth2.GoogleCredentials, com.google.auth.oauth2.OAuth2Credentials
    public final int hashCode() {
        throw null;
    }

    @Override // com.google.auth.oauth2.OAuth2Credentials
    public final AccessToken refreshAccessToken() {
        throw null;
    }

    @Override // com.google.auth.oauth2.GoogleCredentials
    protected final MoreObjects$ToStringHelper toStringHelper() {
        throw null;
    }
}
