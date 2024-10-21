package org.chromium.net.impl;

import org.chromium.net.NetworkException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NetworkExceptionImpl extends NetworkException {
    protected final int mCronetInternalErrorCode;
    protected final int mErrorCode;

    public NetworkExceptionImpl(String str, int i, int i2) {
        super(str, null);
        this.mErrorCode = i;
        this.mCronetInternalErrorCode = i2;
    }

    @Override // org.chromium.net.NetworkException
    public final int getCronetInternalErrorCode() {
        return this.mCronetInternalErrorCode;
    }

    @Override // org.chromium.net.NetworkException
    public final int getErrorCode() {
        return this.mErrorCode;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        StringBuilder sb = new StringBuilder(super.getMessage());
        sb.append(", ErrorCode=");
        sb.append(this.mErrorCode);
        if (this.mCronetInternalErrorCode != 0) {
            sb.append(", InternalErrorCode=");
            sb.append(this.mCronetInternalErrorCode);
        }
        sb.append(", Retryable=");
        sb.append(immediatelyRetryable());
        return sb.toString();
    }

    @Override // org.chromium.net.NetworkException
    public boolean immediatelyRetryable() {
        int i = this.mErrorCode;
        if (i != 3 && i != 4 && i != 5 && i != 6 && i != 8) {
            return false;
        }
        return true;
    }
}
