package com.google.android.accessibility.utils.gestures;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class SecondFingerTap extends GestureMatcher {
    private float baseX;
    private float baseY;
    private int currentTaps;
    private final int doubleTapTimeout;
    private long firstDownTime;
    private final int targetTaps;

    public SecondFingerTap(Context context, int i, int i2, GestureManifold gestureManifold) {
        super(i2, new Handler(context.getMainLooper()), gestureManifold);
        this.targetTaps = 1;
        this.doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout();
        clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void clear() {
        this.currentTaps = 0;
        this.baseX = Float.NaN;
        this.baseY = Float.NaN;
        this.firstDownTime = Long.MAX_VALUE;
        super.clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String getGestureName() {
        int i = this.targetTaps;
        if (i != 1) {
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Second Finger ", " Taps");
        }
        return "Second Finger Tap";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        this.firstDownTime = motionEvent.getEventTime();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        if (motionEvent.getEventTime() - this.firstDownTime < this.doubleTapTimeout) {
            cancelGesture(motionEvent);
            return;
        }
        if (motionEvent.getPointerCount() > 2) {
            motionEvent.getPointerCount();
            cancelGesture(motionEvent);
            return;
        }
        int actionIndex = motionEvent.getActionIndex();
        if (Float.isNaN(this.baseX) && Float.isNaN(this.baseY)) {
            this.baseX = motionEvent.getX(actionIndex);
            this.baseY = motionEvent.getY(actionIndex);
        }
        this.baseX = motionEvent.getX(actionIndex);
        this.baseY = motionEvent.getY(actionIndex);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() <= 2) {
            int i = this.state;
            if (i != 1 && i != 0) {
                cancelGesture(motionEvent);
                return;
            }
            int i2 = this.currentTaps + 1;
            this.currentTaps = i2;
            if (i2 == this.targetTaps) {
                completeGesture(eventId, motionEvent);
                this.currentTaps = 0;
                this.baseX = Float.NaN;
                this.baseY = Float.NaN;
                startGesture(motionEvent);
                return;
            }
            return;
        }
        motionEvent.getPointerCount();
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        cancelGesture(motionEvent);
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String toString() {
        return super.toString() + ", Taps:" + this.currentTaps + ", mBaseX: " + Float.toString(this.baseX) + ", mBaseY: " + this.baseY;
    }
}
