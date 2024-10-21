package org.chromium.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TokenBase {
    protected final long mHigh;
    protected final long mLow;

    /* JADX INFO: Access modifiers changed from: protected */
    public TokenBase(long j, long j2) {
        this.mHigh = j;
        this.mLow = j2;
    }

    private long getHighForSerialization() {
        return this.mHigh;
    }

    private long getLowForSerialization() {
        return this.mLow;
    }

    public final boolean equals(Object obj) {
        if (obj != null) {
            if (obj.getClass() == getClass()) {
                TokenBase tokenBase = (TokenBase) obj;
                if (tokenBase.mHigh == this.mHigh && tokenBase.mLow == this.mLow) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.mLow;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.mHigh;
        return i + ((int) ((j2 >>> 32) ^ j2));
    }
}
