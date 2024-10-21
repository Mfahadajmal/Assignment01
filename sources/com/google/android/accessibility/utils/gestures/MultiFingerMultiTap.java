package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class MultiFingerMultiTap extends GestureMatcher {
    private PointF[] bases;
    protected int completedTapCount;
    private int doubleTapSlop;
    private int doubleTapTimeout;
    private ArrayList excludedPointsForDownSlopChecked;
    protected boolean isTargetFingerCountReached;
    private long lastDownTime;
    private long lastUpTime;
    final int mTargetTapCount;
    private int tapTimeout;
    final int targetFingerCount;
    private int touchSlop;

    public MultiFingerMultiTap(Context context, int i, int i2, int i3, GestureManifold gestureManifold) {
        super(i3, new Handler(context.getMainLooper()), gestureManifold);
        int i4 = 0;
        this.isTargetFingerCountReached = false;
        this.mTargetTapCount = i2;
        this.targetFingerCount = i;
        this.doubleTapSlop = ViewConfiguration.get(context).getScaledDoubleTapSlop() * i;
        this.doubleTapTimeout = GestureConfiguration.DOUBLE_TAP_TIMEOUT_MS;
        this.tapTimeout = ViewConfiguration.getTapTimeout() * i;
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * i;
        this.bases = new PointF[i];
        while (true) {
            PointF[] pointFArr = this.bases;
            if (i4 < pointFArr.length) {
                pointFArr[i4] = new PointF();
                i4++;
            } else {
                this.excludedPointsForDownSlopChecked = new ArrayList(this.targetFingerCount);
                clear();
                return;
            }
        }
    }

    private final PointF findNearestPoint(MotionEvent motionEvent, float f, boolean z) {
        boolean z2;
        float f2 = Float.MAX_VALUE;
        int i = 0;
        PointF pointF = null;
        while (true) {
            PointF[] pointFArr = this.bases;
            if (i < pointFArr.length) {
                PointF pointF2 = pointFArr[i];
                if (!Float.isNaN(pointF2.x) || !Float.isNaN(pointF2.y)) {
                    if (z) {
                        if (this.excludedPointsForDownSlopChecked.contains(pointF2)) {
                            continue;
                        } else {
                            z2 = true;
                        }
                    } else {
                        z2 = false;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    float x = pointF2.x - motionEvent.getX(actionIndex);
                    float y = pointF2.y - motionEvent.getY(actionIndex);
                    if (x == 0.0f && y == 0.0f) {
                        if (z2) {
                            this.excludedPointsForDownSlopChecked.add(pointF2);
                        }
                        return pointF2;
                    }
                    float hypot = (float) Math.hypot(x, y);
                    if (f2 > hypot) {
                        pointF = pointF2;
                        f2 = hypot;
                    }
                }
                i++;
            } else {
                if (f2 >= f) {
                    return null;
                }
                if (z) {
                    this.excludedPointsForDownSlopChecked.add(pointF);
                }
                return pointF;
            }
        }
    }

    private final PointF initBaseLocation(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        PointF pointF = this.bases[motionEvent.getPointerCount() - 1];
        if (Float.isNaN(pointF.x) && Float.isNaN(pointF.y)) {
            pointF.set(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex));
        }
        return pointF;
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void clear() {
        int i = 0;
        this.completedTapCount = 0;
        this.isTargetFingerCountReached = false;
        while (true) {
            PointF[] pointFArr = this.bases;
            if (i < pointFArr.length) {
                pointFArr[i].set(Float.NaN, Float.NaN);
                i++;
            } else {
                this.excludedPointsForDownSlopChecked.clear();
                this.lastDownTime = Long.MAX_VALUE;
                this.lastUpTime = Long.MAX_VALUE;
                super.clear();
                return;
            }
        }
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public String getGestureName() {
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
        sb.append(" Tap");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        if (this.completedTapCount == this.mTargetTapCount) {
            cancelGesture(motionEvent);
            return;
        }
        if (motionEvent.getEventTime() - this.lastUpTime > this.doubleTapTimeout) {
            cancelGesture(motionEvent);
            return;
        }
        this.lastDownTime = motionEvent.getEventTime();
        if (this.completedTapCount == 0) {
            initBaseLocation(motionEvent);
            return;
        }
        PointF findNearestPoint = findNearestPoint(motionEvent, this.doubleTapSlop, true);
        if (findNearestPoint != null) {
            int actionIndex = motionEvent.getActionIndex();
            findNearestPoint.set(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex));
        } else {
            cancelGesture(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onMove$ar$ds(MotionEvent motionEvent) {
        if (findNearestPoint(motionEvent, this.touchSlop, false) == null) {
            cancelGesture(motionEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        PointF findNearestPoint;
        if (motionEvent.getEventTime() - this.lastDownTime > this.tapTimeout) {
            cancelGesture(motionEvent);
            return;
        }
        this.lastDownTime = motionEvent.getEventTime();
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount <= this.targetFingerCount && !this.isTargetFingerCountReached) {
            if (this.completedTapCount == 0) {
                findNearestPoint = initBaseLocation(motionEvent);
            } else {
                findNearestPoint = findNearestPoint(motionEvent, this.doubleTapSlop, true);
            }
            int i = this.state;
            if ((i == 1 || i == 0) && findNearestPoint != null) {
                if (pointerCount == this.targetFingerCount) {
                    this.isTargetFingerCountReached = true;
                }
                int actionIndex = motionEvent.getActionIndex();
                findNearestPoint.set(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex));
                return;
            }
            cancelGesture(motionEvent);
            return;
        }
        this.isTargetFingerCountReached = false;
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (this.isTargetFingerCountReached) {
            int i = this.state;
            if (i != 1 && i != 0) {
                cancelGesture(motionEvent);
                return;
            } else if (motionEvent.getEventTime() - Math.max(this.lastDownTime, this.lastUpTime) > this.tapTimeout) {
                cancelGesture(motionEvent);
                return;
            } else {
                this.lastUpTime = motionEvent.getEventTime();
                return;
            }
        }
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (motionEvent.getEventTime() - this.lastUpTime > this.tapTimeout) {
            cancelGesture(motionEvent);
            return;
        }
        this.lastUpTime = motionEvent.getEventTime();
        PointF findNearestPoint = findNearestPoint(motionEvent, this.touchSlop, false);
        int i = this.state;
        if ((i == 1 || i == 0) && findNearestPoint != null) {
            if (this.isTargetFingerCountReached) {
                this.completedTapCount++;
                this.isTargetFingerCountReached = false;
                this.excludedPointsForDownSlopChecked.clear();
            }
            if (this.completedTapCount == 1) {
                startGesture(motionEvent);
            }
            if (this.completedTapCount == this.mTargetTapCount) {
                completeAfter(ViewConfiguration.getDoubleTapTimeout(), eventId, motionEvent);
                return;
            }
            return;
        }
        cancelGesture(motionEvent);
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (this.state != 3) {
            sb.append(", CompletedTapCount: ");
            sb.append(this.completedTapCount);
            sb.append(", IsTargetFingerCountReached: ");
            sb.append(this.isTargetFingerCountReached);
            sb.append(", Bases: ");
            sb.append(Arrays.toString(this.bases));
            sb.append(", ExcludedPointsForDownSlopChecked: ");
            sb.append(this.excludedPointsForDownSlopChecked.toString());
        }
        return sb.toString();
    }
}
