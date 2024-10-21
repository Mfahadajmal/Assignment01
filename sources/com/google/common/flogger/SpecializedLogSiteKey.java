package com.google.common.flogger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SpecializedLogSiteKey implements LogSiteKey {
    private final LogSiteKey delegate;
    private final Object qualifier;

    public SpecializedLogSiteKey(LogSiteKey logSiteKey, Object obj) {
        logSiteKey.getClass();
        this.delegate = logSiteKey;
        obj.getClass();
        this.qualifier = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SpecializedLogSiteKey)) {
            return false;
        }
        SpecializedLogSiteKey specializedLogSiteKey = (SpecializedLogSiteKey) obj;
        if (!this.delegate.equals(specializedLogSiteKey.delegate) || !this.qualifier.equals(specializedLogSiteKey.qualifier)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.qualifier;
        return obj.hashCode() ^ this.delegate.hashCode();
    }

    public final String toString() {
        Object obj = this.qualifier;
        return "SpecializedLogSiteKey{ delegate='" + this.delegate.toString() + "', qualifier='" + obj.toString() + "' }";
    }
}
