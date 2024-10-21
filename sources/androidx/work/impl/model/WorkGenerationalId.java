package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkGenerationalId {
    public final int generation;
    public final String workSpecId;

    public WorkGenerationalId(String str, int i) {
        str.getClass();
        this.workSpecId = str;
        this.generation = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkGenerationalId)) {
            return false;
        }
        WorkGenerationalId workGenerationalId = (WorkGenerationalId) obj;
        if (Intrinsics.areEqual(this.workSpecId, workGenerationalId.workSpecId) && this.generation == workGenerationalId.generation) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (this.workSpecId.hashCode() * 31) + this.generation;
    }

    public final String toString() {
        return "WorkGenerationalId(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ')';
    }
}
