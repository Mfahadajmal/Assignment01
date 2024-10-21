package androidx.core.view.accessibility;

import android.view.accessibility.AccessibilityRecord;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityRecordCompat {
    private final AccessibilityRecord mRecord;

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.mRecord = (AccessibilityRecord) obj;
    }

    @Deprecated
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityRecordCompat)) {
            return false;
        }
        return this.mRecord.equals(((AccessibilityRecordCompat) obj).mRecord);
    }

    @Deprecated
    public final AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
    }

    @Deprecated
    public final int hashCode() {
        return this.mRecord.hashCode();
    }
}
