package com.google.android.accessibility.selecttospeak.debug;

import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableSnapshot implements Serializable {
    private final Setting setting;
    private final SerializableRect userSelected;
    private final List windows;

    public SerializableSnapshot(List list, SerializableRect serializableRect, Setting setting) {
        this.windows = list;
        this.userSelected = serializableRect;
        this.setting = setting;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableSnapshot)) {
            return false;
        }
        SerializableSnapshot serializableSnapshot = (SerializableSnapshot) obj;
        if (Intrinsics.areEqual(this.windows, serializableSnapshot.windows) && Intrinsics.areEqual(this.userSelected, serializableSnapshot.userSelected) && Intrinsics.areEqual(this.setting, serializableSnapshot.setting)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((this.windows.hashCode() * 31) + this.userSelected.hashCode()) * 31) + this.setting.hashCode();
    }

    public final String toString() {
        return "SerializableSnapshot(windows=" + this.windows + ", userSelected=" + this.userSelected + ", setting=" + this.setting + ")";
    }
}
