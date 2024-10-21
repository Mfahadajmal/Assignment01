package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Swipe extends GestureMatcher {
    private long baseTime;
    private float baseX;
    private float baseY;
    private final int[] directions;
    private final float gestureDetectionThresholdPixels;
    private final int maxContinueThreshold;
    private final int maxStartThreshold;
    private final float minPixelsBetweenSamplesX;
    private final float minPixelsBetweenSamplesY;
    private float previousGestureX;
    private float previousGestureY;
    private final ArrayList strokeBuffer;
    private int touchSlop;

    public Swipe(Context context, int[] iArr, int i, GestureManifold gestureManifold) {
        super(i, new Handler(context.getMainLooper()), gestureManifold);
        float f;
        this.strokeBuffer = new ArrayList(100);
        f = context.getResources().getFloat(R.dimen.config_gesture_confirm_distance_cm);
        int integer = context.getResources().getInteger(R.integer.config_max_time_to_start_swipe_ms_per_cm);
        int integer2 = context.getResources().getInteger(R.integer.config_max_time_to_continue_swipe_ms_per_cm);
        this.maxStartThreshold = (int) (integer * f);
        this.maxContinueThreshold = (int) (integer2 * f);
        this.directions = iArr;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.gestureDetectionThresholdPixels = TypedValue.applyDimension(5, 10.0f, displayMetrics) * f;
        float f2 = displayMetrics.xdpi / 2.54f;
        float f3 = displayMetrics.ydpi / 2.54f;
        this.minPixelsBetweenSamplesX = f2 * 0.25f;
        this.minPixelsBetweenSamplesY = f3 * 0.25f;
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        clear();
    }

    public static String directionToString(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return "Unknown Direction";
                    }
                    return "down";
                }
                return "up";
            }
            return "right";
        }
        return "left";
    }

    private static int toDirection(float f, float f2) {
        if (Math.abs(f) > Math.abs(f2)) {
            if (f < 0.0f) {
                return 0;
            }
            return 1;
        }
        if (f2 < 0.0f) {
            return 2;
        }
        return 3;
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void clear() {
        this.baseX = Float.NaN;
        this.baseY = Float.NaN;
        this.baseTime = 0L;
        this.previousGestureX = Float.NaN;
        this.previousGestureY = Float.NaN;
        this.strokeBuffer.clear();
        super.clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    protected final String getGestureName() {
        StringBuilder sb = new StringBuilder("Swipe ");
        sb.append(directionToString(this.directions[0]));
        for (int i = 1; i < this.directions.length; i = 2) {
            sb.append(" and ");
            sb.append(directionToString(this.directions[1]));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        if (Float.isNaN(this.baseX) && Float.isNaN(this.baseY)) {
            this.baseX = motionEvent.getX();
            this.baseY = motionEvent.getY();
            this.baseTime = motionEvent.getEventTime();
            this.previousGestureX = this.baseX;
            this.previousGestureY = this.baseY;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onMove$ar$ds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        long eventTime = motionEvent.getEventTime();
        float abs = Math.abs(x - this.previousGestureX);
        float abs2 = Math.abs(y - this.previousGestureY);
        double hypot = Math.hypot(Math.abs(x - this.baseX), Math.abs(y - this.baseY));
        long j = eventTime - this.baseTime;
        if (this.state == 0) {
            if (hypot >= this.touchSlop) {
                if (this.strokeBuffer.isEmpty()) {
                    if (toDirection(x - this.baseX, y - this.baseY) != this.directions[0]) {
                        cancelGesture(motionEvent);
                        return;
                    }
                    this.strokeBuffer.add(new PointF(this.baseX, this.baseY));
                }
            } else {
                return;
            }
        }
        if (hypot > this.gestureDetectionThresholdPixels) {
            this.baseX = x;
            this.baseY = y;
            this.baseTime = eventTime;
            startGesture(motionEvent);
        } else {
            int i = this.state;
            if (i == 0) {
                if (j > this.maxStartThreshold) {
                    cancelGesture(motionEvent);
                    return;
                }
            } else if (i == 1 && j > this.maxContinueThreshold) {
                cancelGesture(motionEvent);
                return;
            }
        }
        if (abs < this.minPixelsBetweenSamplesX && abs2 < this.minPixelsBetweenSamplesY) {
            return;
        }
        this.previousGestureX = x;
        this.previousGestureY = y;
        this.strokeBuffer.add(new PointF(x, y));
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
    public final void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        PointF pointF;
        float f;
        float f2;
        float f3;
        float f4;
        int i = this.state;
        if (i != 0) {
            if (i != 1) {
                cancelGesture(motionEvent);
                return;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            float abs = Math.abs(x - this.previousGestureX);
            float f5 = y - this.previousGestureY;
            float f6 = this.minPixelsBetweenSamplesX;
            float abs2 = Math.abs(f5);
            if (abs >= f6 || abs2 >= this.minPixelsBetweenSamplesY) {
                this.strokeBuffer.add(new PointF(x, y));
            }
            if (this.strokeBuffer.size() < 2) {
                cancelGesture(motionEvent);
                return;
            }
            ArrayList arrayList = new ArrayList();
            PointF pointF2 = (PointF) this.strokeBuffer.get(0);
            arrayList.add(pointF2);
            PointF pointF3 = null;
            int i2 = 1;
            int i3 = 0;
            float f7 = 0.0f;
            float f8 = 0.0f;
            float f9 = 0.0f;
            while (i2 < this.strokeBuffer.size()) {
                PointF pointF4 = (PointF) this.strokeBuffer.get(i2);
                if (i3 > 0) {
                    float f10 = i3;
                    float f11 = f8 / f10;
                    float f12 = f9 / f10;
                    PointF pointF5 = new PointF((f7 * f11) + pointF2.x, (f7 * f12) + pointF2.y);
                    float f13 = pointF4.x - pointF5.x;
                    float f14 = pointF4.y - pointF5.y;
                    pointF = pointF4;
                    f = f8;
                    f2 = f9;
                    float hypot = (float) Math.hypot(f13, f14);
                    if ((f11 * (f13 / hypot)) + (f12 * (f14 / hypot)) < 0.0f) {
                        arrayList.add(pointF5);
                        f3 = 0.0f;
                        f4 = 0.0f;
                        pointF2 = pointF5;
                        pointF3 = pointF;
                        i3 = 0;
                        float f15 = pointF3.x - pointF2.x;
                        float f16 = pointF3.y - pointF2.y;
                        float hypot2 = (float) Math.hypot(f15, f16);
                        i3++;
                        f9 = f4 + (f16 / hypot2);
                        f8 = f3 + (f15 / hypot2);
                        i2++;
                        pointF2 = pointF2;
                        f7 = hypot2;
                    }
                } else {
                    pointF = pointF4;
                    f = f8;
                    f2 = f9;
                }
                pointF3 = pointF;
                f3 = f;
                f4 = f2;
                float f152 = pointF3.x - pointF2.x;
                float f162 = pointF3.y - pointF2.y;
                float hypot22 = (float) Math.hypot(f152, f162);
                i3++;
                f9 = f4 + (f162 / hypot22);
                f8 = f3 + (f152 / hypot22);
                i2++;
                pointF2 = pointF2;
                f7 = hypot22;
            }
            arrayList.add(pointF3);
            arrayList.toString();
            if (arrayList.size() == this.directions.length + 1) {
                int i4 = 0;
                while (i4 < arrayList.size() - 1) {
                    PointF pointF6 = (PointF) arrayList.get(i4);
                    int i5 = i4 + 1;
                    PointF pointF7 = (PointF) arrayList.get(i5);
                    if (toDirection(pointF7.x - pointF6.x, pointF7.y - pointF6.y) == this.directions[i4]) {
                        i4 = i5;
                    } else {
                        int i6 = this.directions[i4];
                        cancelGesture(motionEvent);
                        return;
                    }
                }
                completeGesture(eventId, motionEvent);
                return;
            }
            cancelGesture(motionEvent);
            return;
        }
        clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (this.state != 3) {
            sb.append(", mBaseX: ");
            sb.append(this.baseX);
            sb.append(", mBaseY: ");
            sb.append(this.baseY);
            sb.append(", mGestureDetectionThreshold:");
            sb.append(this.gestureDetectionThresholdPixels);
            sb.append(", mMinPixelsBetweenSamplesX:");
            sb.append(this.minPixelsBetweenSamplesX);
            sb.append(", mMinPixelsBetweenSamplesY:");
            sb.append(this.minPixelsBetweenSamplesY);
        }
        return sb.toString();
    }
}
