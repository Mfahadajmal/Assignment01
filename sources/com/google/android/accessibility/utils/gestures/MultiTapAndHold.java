package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.view.MotionEvent;
import com.google.android.accessibility.utils.Performance;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiTapAndHold extends MultiTap {
    public MultiTapAndHold(Context context, int i, int i2, GestureManifold gestureManifold) {
        super(context, 2, i2, gestureManifold);
    }

    @Override // com.google.android.accessibility.utils.gestures.MultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String getGestureName() {
        int i = this.targetTaps;
        if (i != 2) {
            return String.valueOf(Integer.toString(i)).concat(" Taps and Hold");
        }
        return "Double Tap and Hold";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.MultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        super.onDown(eventId, motionEvent);
        if (this.currentTaps + 1 == this.targetTaps && this.state != 3) {
            completeAfterLongPressTimeout(eventId, motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.MultiTap, com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (isValidUpEvent(motionEvent)) {
            int i = this.state;
            if (i != 1 && i != 0) {
                cancelGesture(motionEvent);
                return;
            }
            int i2 = this.currentTaps + 1;
            this.currentTaps = i2;
            if (i2 == this.targetTaps) {
                cancelGesture(motionEvent);
                return;
            } else {
                cancelAfterDoubleTapTimeout(motionEvent);
                return;
            }
        }
        cancelGesture(motionEvent);
    }
}
