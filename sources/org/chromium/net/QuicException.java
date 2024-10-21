package org.chromium.net;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class QuicException extends NetworkException {
    /* JADX INFO: Access modifiers changed from: protected */
    public QuicException(String str, Throwable th) {
        super(str, th);
    }

    public abstract int getQuicDetailedErrorCode();
}
