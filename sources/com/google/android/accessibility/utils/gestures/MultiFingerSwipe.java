package com.google.android.accessibility.utils.gestures;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.utils.Performance;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiFingerSwipe extends GestureMatcher {
    private PointF[] base;
    private int currentFingerCount;
    private final float minPixelsBetweenSamplesX;
    private final float minPixelsBetweenSamplesY;
    private int[] pointerIds;
    private PointF[] previousGesturePoint;
    private final List strokeBuffers;
    private int targetDirection;
    private int targetFingerCount;
    private boolean targetFingerCountReached;
    private int touchSlop;

    public MultiFingerSwipe(Context context, int i, int i2, int i3, GestureManifold gestureManifold) {
        super(i3, new Handler(context.getMainLooper()), gestureManifold);
        this.targetFingerCountReached = false;
        this.targetFingerCount = i;
        this.pointerIds = new int[i];
        this.base = new PointF[i];
        this.previousGesturePoint = new PointF[i];
        this.strokeBuffers = new ArrayList();
        for (int i4 = 0; i4 < this.targetFingerCount; i4++) {
            this.strokeBuffers.add(new ArrayList());
        }
        this.targetDirection = i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = displayMetrics.xdpi / 2.54f;
        float f2 = displayMetrics.ydpi / 2.54f;
        this.minPixelsBetweenSamplesX = f * 0.25f;
        this.minPixelsBetweenSamplesY = f2 * 0.25f;
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * context.getResources().getInteger(R.integer.config_slop_default_multiplier);
        clear();
    }

    public static String directionToString(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return "Unknown Direction";
                        }
                        return "still";
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
        if (f == 0.0f && f2 == 0.0f) {
            return 4;
        }
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
        this.targetFingerCountReached = false;
        this.currentFingerCount = 0;
        for (int i = 0; i < this.targetFingerCount; i++) {
            this.pointerIds[i] = -1;
            PointF[] pointFArr = this.base;
            if (pointFArr[i] == null) {
                pointFArr[i] = new PointF();
            }
            this.base[i].x = Float.NaN;
            this.base[i].y = Float.NaN;
            PointF[] pointFArr2 = this.previousGesturePoint;
            if (pointFArr2[i] == null) {
                pointFArr2[i] = new PointF();
            }
            this.previousGesturePoint[i].x = Float.NaN;
            this.previousGesturePoint[i].y = Float.NaN;
            ((List) this.strokeBuffers.get(i)).clear();
        }
        super.clear();
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    protected final String getGestureName() {
        return this.targetFingerCount + "-finger Swipe " + directionToString(this.targetDirection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
        if (this.currentFingerCount > 0) {
            cancelGesture(motionEvent);
            return;
        }
        this.currentFingerCount = 1;
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        int pointerCount = motionEvent.getPointerCount() - 1;
        if (pointerId < 0) {
            cancelGesture(motionEvent);
            return;
        }
        int[] iArr = this.pointerIds;
        if (iArr[pointerCount] != -1) {
            cancelGesture(motionEvent);
            return;
        }
        iArr[pointerCount] = pointerId;
        if (Float.isNaN(this.base[pointerCount].x) && Float.isNaN(this.base[pointerCount].y)) {
            float x = motionEvent.getX(actionIndex);
            float y = motionEvent.getY(actionIndex);
            if (x >= 0.0f && y >= 0.0f) {
                this.base[pointerCount].x = x;
                this.base[pointerCount].y = y;
                this.previousGesturePoint[pointerCount].x = x;
                this.previousGesturePoint[pointerCount].y = y;
                return;
            }
            cancelGesture(motionEvent);
            return;
        }
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onMove$ar$ds(MotionEvent motionEvent) {
        for (int i = 0; i < this.targetFingerCount; i++) {
            if (this.pointerIds[i] != -1) {
                getGestureName();
                int findPointerIndex = motionEvent.findPointerIndex(this.pointerIds[i]);
                if (findPointerIndex >= 0) {
                    float x = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    if (x >= 0.0f && y >= 0.0f) {
                        float abs = Math.abs(x - this.previousGesturePoint[i].x);
                        float abs2 = Math.abs(y - this.previousGesturePoint[i].y);
                        double hypot = Math.hypot(Math.abs(x - this.base[i].x), Math.abs(y - this.base[i].y));
                        int i2 = this.state;
                        if (i2 == 0) {
                            int i3 = this.targetFingerCount;
                            if (hypot < this.touchSlop * i3) {
                                continue;
                            } else {
                                if (this.currentFingerCount != i3) {
                                    cancelGesture(motionEvent);
                                    return;
                                }
                                if (toDirection(x - this.base[i].x, y - this.base[i].y) != this.targetDirection) {
                                    cancelGesture(motionEvent);
                                    return;
                                }
                                startGesture(motionEvent);
                                for (int i4 = 0; i4 < this.targetFingerCount; i4++) {
                                    ((List) this.strokeBuffers.get(i4)).add(new PointF(this.base[i4]));
                                }
                            }
                        } else if (i2 != 1) {
                            continue;
                        } else {
                            int direction = toDirection(x - this.base[i].x, y - this.base[i].y);
                            if (direction != 4 && direction != this.targetDirection) {
                                cancelGesture(motionEvent);
                                return;
                            } else if (abs >= this.minPixelsBetweenSamplesX || abs2 >= this.minPixelsBetweenSamplesY) {
                                this.previousGesturePoint[i].x = x;
                                this.previousGesturePoint[i].y = y;
                                ((List) this.strokeBuffers.get(i)).add(new PointF(x, y));
                            }
                        }
                    } else {
                        cancelGesture(motionEvent);
                        return;
                    }
                } else {
                    continue;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > this.targetFingerCount) {
            cancelGesture(motionEvent);
            return;
        }
        int i = this.currentFingerCount + 1;
        this.currentFingerCount = i;
        if (i != motionEvent.getPointerCount()) {
            cancelGesture(motionEvent);
            return;
        }
        if (this.currentFingerCount == this.targetFingerCount) {
            this.targetFingerCountReached = true;
        }
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        if (pointerId < 0) {
            cancelGesture(motionEvent);
            return;
        }
        int i2 = this.currentFingerCount - 1;
        int[] iArr = this.pointerIds;
        if (iArr[i2] != -1) {
            cancelGesture(motionEvent);
            return;
        }
        iArr[i2] = pointerId;
        if (Float.isNaN(this.base[i2].x) && Float.isNaN(this.base[i2].y)) {
            float x = motionEvent.getX(actionIndex);
            float y = motionEvent.getY(actionIndex);
            if (x >= 0.0f && y >= 0.0f) {
                this.base[i2].x = x;
                this.base[i2].y = y;
                this.previousGesturePoint[i2].x = x;
                this.previousGesturePoint[i2].y = y;
                return;
            }
            cancelGesture(motionEvent);
            return;
        }
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onPointerUp(Performance.EventId eventId, MotionEvent motionEvent) {
        if (!this.targetFingerCountReached) {
            cancelGesture(motionEvent);
            return;
        }
        this.currentFingerCount--;
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        if (pointerId < 0) {
            cancelGesture(motionEvent);
            return;
        }
        int binarySearch = Arrays.binarySearch(this.pointerIds, pointerId);
        if (binarySearch < 0) {
            cancelGesture(motionEvent);
            return;
        }
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        if (x >= 0.0f && y >= 0.0f) {
            float abs = Math.abs(x - this.previousGesturePoint[binarySearch].x);
            float f = y - this.previousGesturePoint[binarySearch].y;
            float f2 = this.minPixelsBetweenSamplesX;
            float abs2 = Math.abs(f);
            if (abs < f2 && abs2 < this.minPixelsBetweenSamplesY) {
                return;
            }
            ((List) this.strokeBuffers.get(binarySearch)).add(new PointF(x, y));
            return;
        }
        cancelGesture(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        int i = this.state;
        if (i != 0) {
            if (i != 1) {
                cancelGesture(motionEvent);
                return;
            }
            this.currentFingerCount = 0;
            int actionIndex = motionEvent.getActionIndex();
            int binarySearch = Arrays.binarySearch(this.pointerIds, motionEvent.getPointerId(actionIndex));
            if (binarySearch < 0) {
                cancelGesture(motionEvent);
                return;
            }
            float x = motionEvent.getX(actionIndex);
            float y = motionEvent.getY(actionIndex);
            if (x >= 0.0f && y >= 0.0f) {
                float abs = Math.abs(x - this.previousGesturePoint[binarySearch].x);
                float f = y - this.previousGesturePoint[binarySearch].y;
                float f2 = this.minPixelsBetweenSamplesX;
                float abs2 = Math.abs(f);
                if (abs >= f2 || abs2 >= this.minPixelsBetweenSamplesY) {
                    ((List) this.strokeBuffers.get(binarySearch)).add(new PointF(x, y));
                }
                for (int i2 = 0; i2 < this.targetFingerCount; i2++) {
                    if (((List) this.strokeBuffers.get(i2)).size() < 2) {
                        getGestureName();
                        cancelGesture(motionEvent);
                        return;
                    }
                    List list = (List) this.strokeBuffers.get(i2);
                    list.toString();
                    int i3 = 0;
                    while (i3 < list.size() - 1) {
                        PointF pointF = (PointF) list.get(i3);
                        i3++;
                        PointF pointF2 = (PointF) list.get(i3);
                        if (toDirection(pointF2.x - pointF.x, pointF2.y - pointF.y) != this.targetDirection) {
                            cancelGesture(motionEvent);
                            return;
                        }
                    }
                }
                completeGesture(eventId, motionEvent);
                return;
            }
            cancelGesture(motionEvent);
        }
    }

    @Override // com.google.android.accessibility.utils.gestures.GestureMatcher
    public final String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        if (this.state != 3) {
            sb.append(", mBase: ");
            sb.append(Arrays.toString(this.base));
            sb.append(", mMinPixelsBetweenSamplesX:");
            sb.append(this.minPixelsBetweenSamplesX);
            sb.append(", mMinPixelsBetweenSamplesY:");
            sb.append(this.minPixelsBetweenSamplesY);
        }
        return sb.toString();
    }
}
