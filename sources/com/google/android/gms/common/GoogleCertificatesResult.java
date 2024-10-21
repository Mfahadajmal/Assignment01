package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GoogleCertificatesResult {
    public static final GoogleCertificatesResult ALLOWED_INSTANCE = new GoogleCertificatesResult(true, null);
    public final boolean allowed;
    final String staticErrorMessage;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NotAllowedResult extends GoogleCertificatesResult {
        private final Callable errorMessageCallable;

        public NotAllowedResult(Callable callable) {
            super(false, null);
            this.errorMessageCallable = callable;
        }
    }

    public GoogleCertificatesResult(boolean z, String str) {
        this.allowed = z;
        this.staticErrorMessage = str;
    }

    public static GoogleCertificatesResult error(String str) {
        return new GoogleCertificatesResult(false, str);
    }

    public static GoogleCertificatesResult error(String str, Throwable th) {
        return new GoogleCertificatesResult(false, str);
    }
}
