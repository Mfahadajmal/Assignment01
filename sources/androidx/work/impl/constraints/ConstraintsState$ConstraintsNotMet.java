package androidx.work.impl.constraints;

import androidx.work.impl.background.systemjob.SystemJobService;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintsState$ConstraintsNotMet extends SystemJobService.Api24Impl {
    public final int reason;

    public ConstraintsState$ConstraintsNotMet(int i) {
        this.reason = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof ConstraintsState$ConstraintsNotMet) && this.reason == ((ConstraintsState$ConstraintsNotMet) obj).reason) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.reason;
    }

    public final String toString() {
        return "ConstraintsNotMet(reason=" + this.reason + ')';
    }
}
