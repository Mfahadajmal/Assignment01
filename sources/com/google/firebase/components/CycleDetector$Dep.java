package com.google.firebase.components;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CycleDetector$Dep {
    private final Qualified anInterface;
    public final boolean set;

    public CycleDetector$Dep(Qualified qualified, boolean z) {
        this.anInterface = qualified;
        this.set = z;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CycleDetector$Dep) {
            CycleDetector$Dep cycleDetector$Dep = (CycleDetector$Dep) obj;
            if (cycleDetector$Dep.anInterface.equals(this.anInterface) && cycleDetector$Dep.set == this.set) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.anInterface.hashCode() ^ 1000003) * 1000003) ^ Boolean.valueOf(this.set).hashCode();
    }
}
