package com.google.android.accessibility.brailleime.input;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.os.Handler;
import android.util.Range;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import googledata.experiments.mobile.accessibility_suite.features.BrailleKeyboardConfig;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultitouchHandler {
    public final HapticPatternParser$$ExternalSyntheticLambda1 holdRecognizer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean isProcessed;
    public final HapticPatternParser$$ExternalSyntheticLambda1 multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final float swipeMinDistancePixels;
    private final float swipeMinSpeedPixelsPerSecond;
    public final float tapMaxDistancePixels;
    public final HashMap activePointers = new HashMap();
    public final HashMap inactivePointers = new HashMap();
    public final Handler handler = new Handler();
    private final Runnable tapOrSwipeRunnable = new ContextMenuDialog$$ExternalSyntheticLambda5(this, 4, null);
    private final Runnable holdRunnable = new ContextMenuDialog$$ExternalSyntheticLambda5(this, 5, null);
    private final Runnable longHoldRunnable = new ContextMenuDialog$$ExternalSyntheticLambda5(this, 6, null);
    private final Runnable holdAndSwipeRunnable = new ContextMenuDialog$$ExternalSyntheticLambda5(this, 7, null);
    private final long longHoldDurationMinMillis = 2000;
    public boolean isAccumulationMode = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PointerWithHistory {
        public boolean isHoldInProgress;
        public long momentMadeInactive;
        long momentMadeInitial;
        public final PointF pointCurrent;
        public final PointF pointInitial;
        final int pointerId;

        public PointerWithHistory(int i, PointF pointF, long j) {
            this.pointerId = i;
            this.pointInitial = new PointF(pointF.x, pointF.y);
            this.pointCurrent = new PointF(pointF.x, pointF.y);
            this.momentMadeInitial = j;
        }

        public final Speed computeSpeed() {
            double d = (this.momentMadeInactive - this.momentMadeInitial) / 1000.0d;
            try {
                return new Speed((float) Math.abs((this.pointCurrent.x - this.pointInitial.x) / d), (float) Math.abs((this.pointCurrent.y - this.pointInitial.y) / d));
            } catch (ArithmeticException unused) {
                _BOUNDARY.e("MultitouchHandler", "Divided by zero: pointerDurationInSeconds = " + d);
                return new Speed(0.0f, 0.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Speed {
        final float x;
        final float y;

        public Speed(float f, float f2) {
            this.x = f;
            this.y = f2;
        }
    }

    public MultitouchHandler(Resources resources, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12) {
        this.tapMaxDistancePixels = _BOUNDARY.mmToPixels(resources, 5);
        this.swipeMinSpeedPixelsPerSecond = _BOUNDARY.mmToPixels(resources, 45);
        this.swipeMinDistancePixels = _BOUNDARY.mmToPixels(resources, 9);
        this.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda12;
        this.holdRecognizer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    private static final boolean fingersTravelSameDirection$ar$ds(List list, Function function) {
        if (Math.abs(Collection.EL.stream(list).mapToInt(new ArateaEndpoint$$ExternalSyntheticLambda0(function, 1)).sum()) == list.size()) {
            return true;
        }
        return false;
    }

    private final void transferPointerToInactive(int i, long j) {
        HashMap hashMap = this.activePointers;
        Integer valueOf = Integer.valueOf(i);
        PointerWithHistory pointerWithHistory = (PointerWithHistory) hashMap.get(valueOf);
        if (pointerWithHistory != null) {
            pointerWithHistory.momentMadeInactive = j;
            this.inactivePointers.put(valueOf, pointerWithHistory);
            this.activePointers.remove(valueOf);
        }
    }

    public final void clearPointerCollections() {
        this.activePointers.clear();
        this.inactivePointers.clear();
        this.isProcessed = false;
    }

    public final Optional createSwipe(Optional optional, List list) {
        Optional empty;
        Swipe.Direction direction;
        Swipe.Direction direction2;
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        float f = ((PointerWithHistory) optional.get()).pointCurrent.x - ((PointerWithHistory) optional.get()).pointInitial.x;
        float f2 = ((PointerWithHistory) optional.get()).pointCurrent.y - ((PointerWithHistory) optional.get()).pointInitial.y;
        float abs = Math.abs(f) - this.swipeMinDistancePixels;
        float abs2 = Math.abs(f2) - this.swipeMinDistancePixels;
        if (abs > 0.0f && abs2 > 0.0f) {
            PointF pointF = new PointF(f, f2);
            if ((pointF.x == 0.0f && pointF.y == 0.0f) || (pointF.x != 0.0f && pointF.y != 0.0f && Math.max(Math.abs(pointF.x / pointF.y), Math.abs(pointF.y / pointF.x)) <= 2.0f)) {
                return Optional.empty();
            }
        }
        if (abs > 0.0f && abs > abs2) {
            if (f < 0.0f) {
                direction2 = Swipe.Direction.LEFT;
            } else {
                direction2 = Swipe.Direction.RIGHT;
            }
            empty = Optional.of(direction2);
        } else if (abs2 > 0.0f && abs2 > abs) {
            if (f2 < 0.0f) {
                direction = Swipe.Direction.UP;
            } else {
                direction = Swipe.Direction.DOWN;
            }
            empty = Optional.of(direction);
        } else {
            empty = Optional.empty();
        }
        if (empty.isEmpty()) {
            return Optional.empty();
        }
        Object obj = empty.get();
        Speed computeSpeed = ((PointerWithHistory) this.inactivePointers.get(Integer.valueOf(((PointerWithHistory) optional.get()).pointerId))).computeSpeed();
        if (obj != Swipe.Direction.LEFT && obj != Swipe.Direction.RIGHT) {
            if ((obj == Swipe.Direction.UP || obj == Swipe.Direction.DOWN) && (computeSpeed.y < this.swipeMinSpeedPixelsPerSecond || !fingersTravelSameDirection$ar$ds(list, new BtConnectManager$$ExternalSyntheticLambda1(15)))) {
                return Optional.empty();
            }
        } else if (computeSpeed.x < this.swipeMinSpeedPixelsPerSecond || !fingersTravelSameDirection$ar$ds(list, new BtConnectManager$$ExternalSyntheticLambda1(14))) {
            return Optional.empty();
        }
        return Optional.of(new Swipe((Swipe.Direction) obj, list.size()));
    }

    public final List getActivePoints() {
        return (List) Collection.EL.stream(this.activePointers.values()).map(new BtConnectManager$$ExternalSyntheticLambda1(12)).collect(Collectors.toList());
    }

    public final List getHeldPoints() {
        return (List) Collection.EL.stream(this.activePointers.values()).filter(new BrailleUserPreferences$$ExternalSyntheticLambda2(2)).map(new BtConnectManager$$ExternalSyntheticLambda1(16)).collect(Collectors.toList());
    }

    public final Optional getLastRecentlyInactivatedPointsHistory(long j) {
        return Collection.EL.stream(this.inactivePointers.values()).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(new Range(Long.valueOf((-250) + j), Long.valueOf(j)), 20)).findFirst();
    }

    public final List getRecentlyInactivatedInitialPoints(long j) {
        return (List) Collection.EL.stream(this.inactivePointers.values()).filter(new MultitouchHandler$$ExternalSyntheticLambda8(new Range(Long.valueOf((-250) + j), Long.valueOf(j)), 1)).map(new BtConnectManager$$ExternalSyntheticLambda1(13)).collect(Collectors.toList());
    }

    public final List getRecentlyInactivatedPointsHistory(long j) {
        return (List) Collection.EL.stream(this.inactivePointers.values()).filter(new MultitouchHandler$$ExternalSyntheticLambda8(new Range(Long.valueOf((-250) + j), Long.valueOf(j)), 0)).collect(Collectors.toList());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onTouchEvent$ar$ds$19775094_0(Context context, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        long eventTime = motionEvent.getEventTime();
        if (!this.activePointers.isEmpty()) {
            for (int i = 0; i < motionEvent.getPointerCount(); i++) {
                PointerWithHistory pointerWithHistory = (PointerWithHistory) this.activePointers.get(Integer.valueOf(motionEvent.getPointerId(i)));
                if (pointerWithHistory == null) {
                    this.activePointers.put(Integer.valueOf(pointerId), new PointerWithHistory(pointerId, new PointF(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex)), eventTime));
                } else {
                    int x = (int) motionEvent.getX(i);
                    int y = (int) motionEvent.getY(i);
                    pointerWithHistory.pointCurrent.x = x;
                    pointerWithHistory.pointCurrent.y = y;
                }
            }
        }
        if (actionMasked != 0 && actionMasked != 5) {
            if (actionMasked == 3) {
                clearPointerCollections();
                this.handler.removeCallbacksAndMessages(null);
                return;
            }
            if (actionMasked == 6) {
                transferPointerToInactive(pointerId, eventTime);
                this.handler.removeCallbacksAndMessages(null);
                if (BrailleKeyboardConfig.holdAndSwipeGesture(context)) {
                    this.handler.post(this.holdAndSwipeRunnable);
                    this.handler.postDelayed(this.holdRunnable, ViewConfiguration.getLongPressTimeout());
                }
                this.handler.postDelayed(this.longHoldRunnable, this.longHoldDurationMinMillis);
                return;
            }
            if (actionMasked == 1) {
                if (this.isProcessed) {
                    clearPointerCollections();
                    return;
                } else {
                    transferPointerToInactive(pointerId, eventTime);
                    this.handler.post(this.tapOrSwipeRunnable);
                    return;
                }
            }
            return;
        }
        this.isProcessed = false;
        this.handler.removeCallbacksAndMessages(null);
        this.activePointers.put(Integer.valueOf(pointerId), new PointerWithHistory(pointerId, new PointF(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex)), eventTime));
        if (BrailleKeyboardConfig.holdAndSwipeGesture(context)) {
            this.handler.postDelayed(this.holdRunnable, ViewConfiguration.getLongPressTimeout());
        }
        this.handler.postDelayed(this.longHoldRunnable, this.longHoldDurationMinMillis);
    }
}
