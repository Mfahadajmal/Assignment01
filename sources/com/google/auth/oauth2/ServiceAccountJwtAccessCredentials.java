package com.google.auth.oauth2;

import com.google.auth.Credentials;
import io.grpc.auth.GoogleAuthLibraryCallCredentials;
import java.io.ObjectInputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ServiceAccountJwtAccessCredentials extends Credentials {
    private static final long serialVersionUID = -7274955171379494197L;

    static {
        TimeUnit.HOURS.toSeconds(1L);
        TimeUnit.MINUTES.toSeconds(5L);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw null;
    }

    public final boolean equals(Object obj) {
        throw null;
    }

    @Override // com.google.auth.Credentials
    public final void getRequestMetadata$ar$class_merging$ar$ds(Executor executor, GoogleAuthLibraryCallCredentials.AnonymousClass1 anonymousClass1) {
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        throw null;
    }
}
