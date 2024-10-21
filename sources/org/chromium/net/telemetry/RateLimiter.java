package org.chromium.net.telemetry;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RateLimiter {
    public int mSamplesLoggedDuringSecond;
    public final Object mLock = new Object();
    public long mLastPermitMillis = Long.MIN_VALUE;
}
