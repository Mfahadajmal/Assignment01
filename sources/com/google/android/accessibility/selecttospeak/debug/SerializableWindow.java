package com.google.android.accessibility.selecttospeak.debug;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableWindow implements Serializable {
    private final boolean containsLoop;
    private final int id;
    private final boolean intersectsWithUserSelection;
    private final Boolean isRegionRect;
    private final List nodes;
    private final String title;
    private final SerializableRect windowBounds;
    private final int windowType;

    public SerializableWindow(int i, String str, List list, int i2, SerializableRect serializableRect, Boolean bool, boolean z, boolean z2) {
        this.id = i;
        this.title = str;
        this.nodes = list;
        this.windowType = i2;
        this.windowBounds = serializableRect;
        this.isRegionRect = bool;
        this.intersectsWithUserSelection = z;
        this.containsLoop = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableWindow)) {
            return false;
        }
        SerializableWindow serializableWindow = (SerializableWindow) obj;
        if (this.id == serializableWindow.id && Intrinsics.areEqual(this.title, serializableWindow.title) && Intrinsics.areEqual(this.nodes, serializableWindow.nodes) && this.windowType == serializableWindow.windowType && Intrinsics.areEqual(this.windowBounds, serializableWindow.windowBounds) && Intrinsics.areEqual(this.isRegionRect, serializableWindow.isRegionRect) && this.intersectsWithUserSelection == serializableWindow.intersectsWithUserSelection && this.containsLoop == serializableWindow.containsLoop) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2 = (((((((this.id * 31) + this.title.hashCode()) * 31) + this.nodes.hashCode()) * 31) + this.windowType) * 31) + this.windowBounds.hashCode();
        Boolean bool = this.isRegionRect;
        if (bool == null) {
            hashCode = 0;
        } else {
            hashCode = bool.hashCode();
        }
        return (((((hashCode2 * 31) + hashCode) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.intersectsWithUserSelection)) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.containsLoop);
    }

    public final String toString() {
        return "SerializableWindow(id=" + this.id + ", title=" + this.title + ", nodes=" + this.nodes + ", windowType=" + this.windowType + ", windowBounds=" + this.windowBounds + ", isRegionRect=" + this.isRegionRect + ", intersectsWithUserSelection=" + this.intersectsWithUserSelection + ", containsLoop=" + this.containsLoop + ")";
    }
}
