package com.google.android.accessibility.utils.gestures;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import com.google.android.accessibility.talkback.TouchInteractionMonitor;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GestureMatcher {
    public final int gestureId;
    public final Handler handler;
    private GestureManifold listener$ar$class_merging$b9bb9984_0;
    public int state = 0;
    public boolean logMotionEvent = false;
    protected final DelayedTransition delayedTransition = new DelayedTransition();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DelayedTransition implements Runnable {
        MotionEvent event;
        int targetState;

        protected DelayedTransition() {
        }

        private final void recycleEvent() {
            MotionEvent motionEvent = this.event;
            if (motionEvent == null) {
                return;
            }
            motionEvent.recycle();
            this.event = null;
        }

        public final void cancel() {
            boolean hasCallbacks;
            synchronized (GestureMatcher.this) {
                hasCallbacks = GestureMatcher.this.handler.hasCallbacks(this);
                if (hasCallbacks) {
                    LogUtils.v("GestureMatcher.DelayedTransition", "%s: canceling delayed transition to %s", GestureMatcher.this.getGestureName(), GestureMatcher.getStateSymbolicName(this.targetState));
                }
                GestureMatcher.this.handler.removeCallbacks(this);
                recycleEvent();
            }
        }

        public final void post(int i, long j, MotionEvent motionEvent) {
            synchronized (GestureMatcher.this) {
                this.targetState = i;
                recycleEvent();
                this.event = MotionEvent.obtain(motionEvent);
                GestureMatcher.this.handler.postDelayed(this, j);
                LogUtils.v("GestureMatcher.DelayedTransition", "%s: posting delayed transition to %s", GestureMatcher.this.getGestureName(), GestureMatcher.getStateSymbolicName(this.targetState));
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (GestureMatcher.this) {
                if (this.event == null) {
                    return;
                }
                LogUtils.v("GestureMatcher.DelayedTransition", "%s: executing delayed transition to %s", GestureMatcher.this.getGestureName(), GestureMatcher.getStateSymbolicName(this.targetState));
                GestureMatcher.this.setState(this.targetState, this.event);
                recycleEvent();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GestureMatcher(int i, Handler handler, GestureManifold gestureManifold) {
        this.listener$ar$class_merging$b9bb9984_0 = null;
        this.gestureId = i;
        this.handler = handler;
        this.listener$ar$class_merging$b9bb9984_0 = gestureManifold;
    }

    static String getStateSymbolicName(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return "STATE_GESTURE_CANCELED";
                }
                return "STATE_GESTURE_COMPLETED";
            }
            return "STATE_GESTURE_STARTED";
        }
        return "STATE_CLEAR";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void cancelAfterDoubleTapTimeout(MotionEvent motionEvent) {
        long doubleTapTimeout = ViewConfiguration.getDoubleTapTimeout();
        this.delayedTransition.cancel();
        this.delayedTransition.post(3, doubleTapTimeout, motionEvent);
    }

    public final void cancelGesture(MotionEvent motionEvent) {
        setState(3, motionEvent);
    }

    protected final void cancelPendingTransitions() {
        this.delayedTransition.cancel();
    }

    public void clear() {
        this.state = 0;
        cancelPendingTransitions();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void completeAfter(long j, Performance.EventId eventId, MotionEvent motionEvent) {
        this.delayedTransition.cancel();
        Performance.instance.onGestureLastMotionEventTime(eventId, motionEvent.getEventTime());
        this.delayedTransition.post(2, j, motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void completeAfterLongPressTimeout(Performance.EventId eventId, MotionEvent motionEvent) {
        completeAfter(ViewConfiguration.getLongPressTimeout(), eventId, motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void completeGesture(Performance.EventId eventId, MotionEvent motionEvent) {
        Performance.instance.onGestureLastMotionEventTime(eventId, motionEvent.getEventTime());
        setState(2, motionEvent);
    }

    protected abstract String getGestureName();

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPointerDown(Performance.EventId eventId, MotionEvent motionEvent) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onPointerUp(Performance.EventId eventId, MotionEvent motionEvent) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onUp(Performance.EventId eventId, MotionEvent motionEvent) {
        throw null;
    }

    public final void setState(int i, MotionEvent motionEvent) {
        setState(i, motionEvent, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void startGesture(MotionEvent motionEvent) {
        setState(1, motionEvent);
    }

    public String toString() {
        return getGestureName() + ":" + getStateSymbolicName(this.state);
    }

    public final void setState(int i, MotionEvent motionEvent, boolean z) {
        GestureManifold gestureManifold;
        this.state = i;
        cancelPendingTransitions();
        if (!z || (gestureManifold = this.listener$ar$class_merging$b9bb9984_0) == null) {
            return;
        }
        int i2 = this.gestureId;
        if (i == 1) {
            TouchInteractionMonitor touchInteractionMonitor = (TouchInteractionMonitor) gestureManifold.listener;
            touchInteractionMonitor.gestureStarted = true;
            touchInteractionMonitor.requestTouchExplorationDelayed.cancel();
            ProcessorPhoneticLetters processorPhoneticLetters = touchInteractionMonitor.service.processorPhoneticLetters;
            if (processorPhoneticLetters != null) {
                Logger logger = Performance.DEFAULT_LOGGER;
                processorPhoneticLetters.cancelPhoneticLetter(null);
                return;
            }
            return;
        }
        if (i == 2) {
            gestureManifold.onGestureCompleted(i2, motionEvent);
            return;
        }
        if (i == 3) {
            TouchInteractionMonitor touchInteractionMonitor2 = (TouchInteractionMonitor) gestureManifold.listener;
            touchInteractionMonitor2.touchExploreGate.remove(Integer.valueOf(i2));
            if (touchInteractionMonitor2.keepMonitorTouchExplore && touchInteractionMonitor2.touchExploreGate.isEmpty() && touchInteractionMonitor2.state == 1) {
                touchInteractionMonitor2.keepMonitorTouchExplore = false;
                touchInteractionMonitor2.requestTouchExplorationDelayed.cancel();
                touchInteractionMonitor2.requestTouchExploration("onGestureCancelled");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onMove$ar$ds(MotionEvent motionEvent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDown(Performance.EventId eventId, MotionEvent motionEvent) {
    }
}
