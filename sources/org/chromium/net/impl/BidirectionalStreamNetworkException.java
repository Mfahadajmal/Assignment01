package org.chromium.net.impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BidirectionalStreamNetworkException extends NetworkExceptionImpl {
    public BidirectionalStreamNetworkException(String str, int i, int i2) {
        super(str, i, i2);
    }

    @Override // org.chromium.net.impl.NetworkExceptionImpl, org.chromium.net.NetworkException
    public final boolean immediatelyRetryable() {
        int i = this.mCronetInternalErrorCode;
        if (i != -358 && i != -352) {
            return super.immediatelyRetryable();
        }
        return true;
    }
}
