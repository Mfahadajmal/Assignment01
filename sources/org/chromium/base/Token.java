package org.chromium.base;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Token extends TokenBase {
    public Token(long j, long j2) {
        super(j, j2);
    }

    public final String toString() {
        return String.format("%016X%016X", Long.valueOf(this.mHigh), Long.valueOf(this.mLow));
    }
}
