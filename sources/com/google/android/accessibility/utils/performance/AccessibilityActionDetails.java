package com.google.android.accessibility.utils.performance;

import _COROUTINE._BOUNDARY;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityActionDetails {
    public final int actionId;
    public final long finishedUpTime;
    public final long processingTime;
    public final boolean success;

    public AccessibilityActionDetails(int i, long j, long j2, boolean z) {
        this.actionId = i;
        this.processingTime = j;
        this.finishedUpTime = j2;
        this.success = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityActionDetails)) {
            return false;
        }
        AccessibilityActionDetails accessibilityActionDetails = (AccessibilityActionDetails) obj;
        if (this.actionId == accessibilityActionDetails.actionId && this.processingTime == accessibilityActionDetails.processingTime && this.finishedUpTime == accessibilityActionDetails.finishedUpTime && this.success == accessibilityActionDetails.success) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.actionId * 31;
        boolean z = this.success;
        return ((((i + SpannableUtils$NonCopyableTextSpan.m(this.processingTime)) * 31) + SpannableUtils$NonCopyableTextSpan.m(this.finishedUpTime)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(z);
    }

    public final String toString() {
        return "AccessibilityActionDetails(actionId=" + this.actionId + ", processingTime=" + this.processingTime + ", finishedUpTime=" + this.finishedUpTime + ", success=" + this.success + ")";
    }
}
