package com.google.android.accessibility.selecttospeak.debug;

import _COROUTINE._BOUNDARY;
import java.io.Serializable;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializableNode implements Serializable {
    private final SerializableNodeDetail detail;
    private final boolean intersectsWithUserSelection;
    private final boolean isVisibleToUserBeneathWindows;
    private final SerializableRect nodeBounds;
    private final List sentences;
    private final int textLength;
    private final SerializableRect visibleBoundsInScreen;

    public SerializableNode(List list, SerializableRect serializableRect, boolean z, SerializableRect serializableRect2, boolean z2, int i, SerializableNodeDetail serializableNodeDetail) {
        this.sentences = list;
        this.nodeBounds = serializableRect;
        this.isVisibleToUserBeneathWindows = z;
        this.visibleBoundsInScreen = serializableRect2;
        this.intersectsWithUserSelection = z2;
        this.textLength = i;
        this.detail = serializableNodeDetail;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerializableNode)) {
            return false;
        }
        SerializableNode serializableNode = (SerializableNode) obj;
        if (Intrinsics.areEqual(this.sentences, serializableNode.sentences) && Intrinsics.areEqual(this.nodeBounds, serializableNode.nodeBounds) && this.isVisibleToUserBeneathWindows == serializableNode.isVisibleToUserBeneathWindows && Intrinsics.areEqual(this.visibleBoundsInScreen, serializableNode.visibleBoundsInScreen) && this.intersectsWithUserSelection == serializableNode.intersectsWithUserSelection && this.textLength == serializableNode.textLength && Intrinsics.areEqual(this.detail, serializableNode.detail)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (((((((((((this.sentences.hashCode() * 31) + this.nodeBounds.hashCode()) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.isVisibleToUserBeneathWindows)) * 31) + this.visibleBoundsInScreen.hashCode()) * 31) + _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_11(this.intersectsWithUserSelection)) * 31) + this.textLength) * 31) + this.detail.hashCode();
    }

    public final String toString() {
        return "SerializableNode(sentences=" + this.sentences + ", nodeBounds=" + this.nodeBounds + ", isVisibleToUserBeneathWindows=" + this.isVisibleToUserBeneathWindows + ", visibleBoundsInScreen=" + this.visibleBoundsInScreen + ", intersectsWithUserSelection=" + this.intersectsWithUserSelection + ", textLength=" + this.textLength + ", detail=" + this.detail + ")";
    }
}
