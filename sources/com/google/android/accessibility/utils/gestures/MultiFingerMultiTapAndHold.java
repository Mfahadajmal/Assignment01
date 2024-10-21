package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.accessibility.utils.Performance;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiFingerMultiTapAndHold extends MultiFingerMultiTap {
    public MultiFingerMultiTapAndHold(Context context, int i, int i2, int i3, GestureManifold gestureManifold) {
        super(context, i, i2, i3, gestureManifold);
    }

    @Override // com.google.android.accessibility.utils.gestures.MultiFingerMultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String getGestureName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.targetFingerCount);
        sb.append("-Finger ");
        int i = this.mTargetTapCount;
        if (i == 1) {
            sb.append("Single");
        } else if (i == 2) {
            sb.append("Double");
        } else if (i == 3) {
            sb.append("Triple");
        }
        sb.append(" Tap and hold");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.MultiFingerMultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        super.onPointerDown(eventId, motionEvent);
        if (this.isTargetFingerCountReached && this.completedTapCount + 1 == this.mTargetTapCount) {
            completeAfterLongPressTimeout(eventId, motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.MultiFingerMultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (this.completedTapCount + 1 == this.mTargetTapCount) {
            cancelGesture(motionEvent);
        } else {
            super.onUp(eventId, motionEvent);
            cancelAfterDoubleTapTimeout(motionEvent);
        }
    }
}
