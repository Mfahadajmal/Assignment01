package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MultiTap extends GestureMatcher {
    float baseX;
    float baseY;
    int currentTaps;
    final int doubleTapSlop;
    final int doubleTapTimeout;
    long lastDownTime;
    long lastUpTime;
    final int tapTimeout;
    final int targetTaps;
    final int touchSlop;

    public MultiTap(Context context, int i, int i2, GestureManifold gestureManifold) {
        super(i2, new Handler(context.getMainLooper()), gestureManifold);
        this.targetTaps = 2;
        this.doubleTapSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop();
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.tapTimeout = ViewConfiguration.getTapTimeout() + context.getResources().getInteger(R.integer.config_tap_timeout_delta);
        this.doubleTapTimeout = GestureConfiguration.DOUBLE_TAP_TIMEOUT_MS;
        clear();
    }

    private final boolean isInsideSlop(MotionEvent motionEvent, int i) {
        float x = this.baseX - motionEvent.getX();
        float y = this.baseY - motionEvent.getY();
        if (x == 0.0f && y == 0.0f) {
            return true;
        }
        if (Math.hypot(x, y) <= i) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void clear() {
        this.currentTaps = 0;
        this.baseX = Float.NaN;
        this.baseY = Float.NaN;
        this.lastDownTime = Long.MAX_VALUE;
        this.lastUpTime = Long.MAX_VALUE;
        super.clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public String getGestureName() {
        int i = this.targetTaps;
        if (i != 2) {
            return String.valueOf(Integer.toString(i)).concat(" Taps");
        }
        return "Double Tap";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isValidUpEvent(MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        if (eventTime - this.lastDownTime > this.tapTimeout) {
            return false;
        }
        this.lastUpTime = eventTime;
        if (!isInsideSlop(motionEvent, this.touchSlop)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        long eventTime = motionEvent.getEventTime();
        if (eventTime - this.lastUpTime > this.doubleTapTimeout) {
            cancelGesture(motionEvent);
            return;
        }
        this.lastDownTime = eventTime;
        if (Float.isNaN(this.baseX) && Float.isNaN(this.baseY)) {
            this.baseX = motionEvent.getX();
            this.baseY = motionEvent.getY();
        }
        if (!isInsideSlop(motionEvent, this.doubleTapSlop)) {
            cancelGesture(motionEvent);
            return;
        }
        this.baseX = motionEvent.getX();
        this.baseY = motionEvent.getY();
        if (this.currentTaps + 1 == this.targetTaps) {
            startGesture(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onMove$ar$ds(MotionEvent motionEvent) {
        if (!isInsideSlop(motionEvent, this.touchSlop)) {
            cancelGesture(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerUp(Performance.EventId eventId, MotionEvent motionEvent) {
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (isValidUpEvent(motionEvent)) {
            int i = this.state;
            if (i != 1 && i != 0) {
                cancelGesture(motionEvent);
                return;
            }
            int i2 = this.currentTaps + 1;
            this.currentTaps = i2;
            if (i2 == this.targetTaps) {
                completeGesture(eventId, motionEvent);
                return;
            }
            return;
        }
        cancelGesture(motionEvent);
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String toString() {
        return super.toString() + ", Taps:" + this.currentTaps + ", mBaseX: " + Float.toString(this.baseX) + ", mBaseY: " + Float.toString(this.baseY);
    }
}
