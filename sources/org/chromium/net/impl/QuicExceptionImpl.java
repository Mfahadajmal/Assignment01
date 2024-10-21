package org.chromium.net.impl;

import org.chromium.net.QuicException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class QuicExceptionImpl extends QuicException {
    private final NetworkExceptionImpl mNetworkException;
    private final int mQuicDetailedErrorCode;

    public QuicExceptionImpl(String str, int i, int i2, int i3) {
        super(str, null);
        this.mNetworkException = new NetworkExceptionImpl(str, i, i2);
        this.mQuicDetailedErrorCode = i3;
    }

    @Override // org.chromium.net.NetworkException
    public final int getCronetInternalErrorCode() {
        return this.mNetworkException.mCronetInternalErrorCode;
    }

    @Override // org.chromium.net.NetworkException
    public final int getErrorCode() {
        return this.mNetworkException.mErrorCode;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.mNetworkException.getMessage() + ", QuicDetailedErrorCode=" + this.mQuicDetailedErrorCode;
    }

    @Override // org.chromium.net.QuicException
    public final int getQuicDetailedErrorCode() {
        return this.mQuicDetailedErrorCode;
    }

    @Override // org.chromium.net.NetworkException
    public final boolean immediatelyRetryable() {
        return this.mNetworkException.immediatelyRetryable();
    }
}
