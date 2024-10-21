package com.google.android.accessibility.talkback;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.TouchInteractionController;
import android.accessibilityservice.TouchInteractionController$Callback;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Display;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.gestures.GestureConfiguration;
import com.google.android.accessibility.utils.gestures.GestureManifold;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.EvictingQueue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import googledata.experiments.mobile.accessibility_suite.features.GestureConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TouchInteractionMonitor implements TouchInteractionController$Callback, GestureManifold.Listener {
    private static final ImmutableSet TOUCH_EXPLORE_GATE = ImmutableSet.of((Object) 17, (Object) 18, (Object) 4, (Object) 3, (Object) 1, (Object) 2, (Object[]) new Integer[]{5, 9, 10, 11, 12, 6, 8, 15, 16, 7, 13, 14});
    private final Queue callerInfos;
    private final Context context;
    public final TouchInteractionController controller;
    private final int determineUserIntentTimeout;
    public final int displayId;
    private final float edgeSwipeHeightPixels;
    public Performance.EventId eventId;
    public final GestureManifold gestureDetector;
    private final boolean handleStateChangeInMainThread;
    public boolean keepMonitorTouchExplore;
    private final boolean logMotionEvent;
    public final Handler mainHandler;
    private final int passthroughTotalSlop;
    public int previousState;
    private final PrimesController primesController;
    private final Queue queuedMotionEvents;
    private final ReceivedPointerTracker receivedPointerTracker;
    public final RequestTouchExplorationDelayed requestTouchExplorationDelayed;
    public final TalkBackService service;
    public int state;
    private boolean stateChangeRequested;
    public final Set touchExploreGate;
    private boolean waitFirstMotionEvent;
    private int draggingPointerId = -1;
    public boolean gestureStarted = false;
    public boolean serviceHandlesDoubleTap = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CallerInfo {
        final String caller;
        final int state;
        final long thread;

        public CallerInfo(int i, String str, long j) {
            this.state = i;
            this.caller = str;
            this.thread = j;
        }

        public final String toString() {
            return "state:" + this.state + ", caller:" + this.caller + ", thread:" + this.thread;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PointerDownInfo {
        public float mX;
        public float mY;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReceivedPointerTracker {
        private final PointerDownInfo[] mReceivedPointers;
        private int mReceivedPointersDown;

        public ReceivedPointerTracker() {
            int maxPointerCount;
            maxPointerCount = TouchInteractionMonitor.this.controller.getMaxPointerCount();
            this.mReceivedPointers = new PointerDownInfo[maxPointerCount];
            clear();
        }

        /* JADX WARN: Incorrect condition in loop: B:3:0x000b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void clear() {
            /*
                r3 = this;
                r0 = 0
                r3.mReceivedPointersDown = r0
            L3:
                com.google.android.accessibility.talkback.TouchInteractionMonitor r1 = com.google.android.accessibility.talkback.TouchInteractionMonitor.this
                android.accessibilityservice.TouchInteractionController r1 = r1.controller
                int r1 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m$1(r1)
                if (r0 >= r1) goto L19
                com.google.android.accessibility.talkback.TouchInteractionMonitor$PointerDownInfo[] r1 = r3.mReceivedPointers
                com.google.android.accessibility.talkback.TouchInteractionMonitor$PointerDownInfo r2 = new com.google.android.accessibility.talkback.TouchInteractionMonitor$PointerDownInfo
                r2.<init>()
                r1[r0] = r2
                int r0 = r0 + 1
                goto L3
            L19:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TouchInteractionMonitor.ReceivedPointerTracker.clear():void");
        }

        public final float getReceivedPointerDownX(int i) {
            return this.mReceivedPointers[i].mX;
        }

        public final float getReceivedPointerDownY(int i) {
            return this.mReceivedPointers[i].mY;
        }

        public final void handleReceivedPointerDown(int i, MotionEvent motionEvent) {
            int pointerId = motionEvent.getPointerId(i);
            this.mReceivedPointersDown = (1 << pointerId) | this.mReceivedPointersDown;
            PointerDownInfo pointerDownInfo = this.mReceivedPointers[pointerId];
            float x = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            motionEvent.getEventTime();
            pointerDownInfo.mX = x;
            pointerDownInfo.mY = y;
        }

        public final void handleReceivedPointerUp(int i, MotionEvent motionEvent) {
            int pointerId = motionEvent.getPointerId(i);
            this.mReceivedPointersDown = (~(1 << pointerId)) & this.mReceivedPointersDown;
            PointerDownInfo pointerDownInfo = this.mReceivedPointers[pointerId];
            pointerDownInfo.mX = 0.0f;
            pointerDownInfo.mY = 0.0f;
        }

        public final boolean isReceivedPointerDown(int i) {
            if (((1 << i) & this.mReceivedPointersDown) != 0) {
                return true;
            }
            return false;
        }

        /* JADX WARN: Incorrect condition in loop: B:3:0x001e */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String toString() {
            /*
                r3 = this;
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                java.lang.String r1 = "=========================\nDown pointers #"
                r0.<init>(r1)
                int r1 = r3.mReceivedPointersDown
                int r1 = java.lang.Integer.bitCount(r1)
                r0.append(r1)
                java.lang.String r1 = " [ "
                r0.append(r1)
                r1 = 0
            L16:
                com.google.android.accessibility.talkback.TouchInteractionMonitor r2 = com.google.android.accessibility.talkback.TouchInteractionMonitor.this
                android.accessibilityservice.TouchInteractionController r2 = r2.controller
                int r2 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m$1(r2)
                if (r1 >= r2) goto L31
                boolean r2 = r3.isReceivedPointerDown(r1)
                if (r2 == 0) goto L2e
                r0.append(r1)
                java.lang.String r2 = " "
                r0.append(r2)
            L2e:
                int r1 = r1 + 1
                goto L16
            L31:
                java.lang.String r1 = "] ]\n========================="
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TouchInteractionMonitor.ReceivedPointerTracker.toString():java.lang.String");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RequestTouchExplorationDelayed implements Runnable {
        public final int mDelay;

        public RequestTouchExplorationDelayed(int i) {
            this.mDelay = i;
        }

        public final void cancel() {
            TouchInteractionMonitor.this.mainHandler.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public final void run() {
            TouchInteractionMonitor.this.requestTouchExplorationFromMainThread("RequestTouchExplorationDelayed", 0L);
        }
    }

    public TouchInteractionMonitor(Context context, TouchInteractionController touchInteractionController, TalkBackService talkBackService, PrimesController primesController) {
        Display display;
        int i = GestureConfiguration.DOUBLE_TAP_TIMEOUT_MS;
        this.determineUserIntentTimeout = i;
        this.queuedMotionEvents = new LinkedList();
        this.stateChangeRequested = false;
        this.keepMonitorTouchExplore = true;
        this.touchExploreGate = new HashSet(32);
        this.context = context;
        this.controller = touchInteractionController;
        this.receivedPointerTracker = new ReceivedPointerTracker();
        this.service = talkBackService;
        this.primesController = primesController;
        this.handleStateChangeInMainThread = GestureConfig.INSTANCE.get().handleStateChangeInMainThread(context);
        display = context.getDisplay();
        int displayId = display.getDisplayId();
        this.displayId = displayId;
        this.mainHandler = new Handler(context.getMainLooper());
        this.gestureDetector = new GestureManifold(context, this, displayId, ImmutableList.copyOf(context.getResources().getStringArray(R.array.service_detected_gesture_list)));
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop() * context.getResources().getInteger(R.integer.config_slop_default_multiplier);
        this.passthroughTotalSlop = context.getResources().getInteger(R.integer.config_passthrough_slop_multiplier) * scaledTouchSlop;
        this.requestTouchExplorationDelayed = new RequestTouchExplorationDelayed(i);
        this.edgeSwipeHeightPixels = (context.getResources().getDisplayMetrics().ydpi / 2.54f) * 0.25f;
        LogUtils.v("TouchInteractionMonitor", "Touch Slop: %s", Integer.valueOf(scaledTouchSlop));
        this.previousState = 0;
        this.logMotionEvent = false;
        this.callerInfos = new EvictingQueue(5);
        clear();
    }

    private final void clear() {
        this.gestureStarted = false;
        this.stateChangeRequested = false;
        this.gestureDetector.clear();
        this.receivedPointerTracker.clear();
        this.requestTouchExplorationDelayed.cancel();
        this.queuedMotionEvents.clear();
        this.touchExploreGate.addAll(TOUCH_EXPLORE_GATE);
        this.keepMonitorTouchExplore = true;
        this.waitFirstMotionEvent = false;
        if (this.eventId != null) {
            Performance.instance.removeRecentEvent(this.eventId);
            this.eventId = null;
        }
    }

    private final float getDistanceToClosestEdge(float f, float f2) {
        long j = this.context.getResources().getDisplayMetrics().widthPixels;
        long j2 = this.context.getResources().getDisplayMetrics().heightPixels;
        float f3 = ((float) j) - f;
        if (f >= f3) {
            f = f3;
        }
        if (f > f2) {
            f = f2;
        }
        float f4 = ((float) j2) - f2;
        if (f > f4) {
            return f4;
        }
        return f;
    }

    private final boolean isDraggingGesture(MotionEvent motionEvent) {
        ReceivedPointerTracker receivedPointerTracker = this.receivedPointerTracker;
        float x = motionEvent.getX(0);
        float y = motionEvent.getY(0);
        float x2 = motionEvent.getX(1);
        float y2 = motionEvent.getY(1);
        float receivedPointerDownX = x - receivedPointerTracker.getReceivedPointerDownX(0);
        float receivedPointerDownY = y - this.receivedPointerTracker.getReceivedPointerDownY(0);
        ReceivedPointerTracker receivedPointerTracker2 = this.receivedPointerTracker;
        float receivedPointerDownX2 = receivedPointerTracker2.getReceivedPointerDownX(1);
        float receivedPointerDownY2 = receivedPointerTracker2.getReceivedPointerDownY(1);
        if (receivedPointerDownX != 0.0f || receivedPointerDownY != 0.0f) {
            float hypot = (float) Math.hypot(receivedPointerDownX, receivedPointerDownY);
            if (hypot > 0.0f) {
                receivedPointerDownX /= hypot;
            }
            if (hypot > 0.0f) {
                receivedPointerDownY /= hypot;
            }
            float f = x2 - receivedPointerDownX2;
            float f2 = y2 - receivedPointerDownY2;
            if (f != 0.0f || f2 != 0.0f) {
                float hypot2 = (float) Math.hypot(f, f2);
                if (hypot2 > 0.0f) {
                    f /= hypot2;
                }
                if (hypot2 > 0.0f) {
                    f2 /= hypot2;
                }
                if ((receivedPointerDownX * f) + (receivedPointerDownY * f2) < 0.52532196f) {
                    return false;
                }
                return true;
            }
        }
        return true;
    }

    private final boolean isStateTransitionAllowed() {
        int state;
        state = this.controller.getState();
        if (state != 4 && state != 2) {
            return true;
        }
        return false;
    }

    private final IllegalStateException packExceptionWithCallerInfo(Exception exc) {
        String stateToString;
        int state;
        String stateToString2;
        stateToString = TouchInteractionController.stateToString(this.state);
        state = this.controller.getState();
        stateToString2 = TouchInteractionController.stateToString(state);
        StringBuilder sb = new StringBuilder(String.format("\nController's expected state: %s, , actual state: %s\n", stateToString, stateToString2));
        ArrayList arrayList = new ArrayList(this.callerInfos);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append((CallerInfo) arrayList.get(i));
            sb.append("\n");
        }
        sb.append("\n");
        return new IllegalStateException(sb.toString(), exc);
    }

    private final void trackStateChangeRequest(int i, String str) {
        CallerInfo callerInfo = new CallerInfo(i, str, Thread.currentThread().getId());
        synchronized (this.callerInfos) {
            this.callerInfos.add(callerInfo);
        }
    }

    public final void dispatchGestureToMainThreadAndClear(AccessibilityGestureEvent accessibilityGestureEvent) {
        this.mainHandler.post(new DelayedWorkTracker.AnonymousClass1(this, accessibilityGestureEvent, 16, (char[]) null));
        clear();
    }

    public final void onMotionEvent(MotionEvent motionEvent) {
        boolean hasCallbacks;
        int pointerCount;
        if (motionEvent != null) {
            if (motionEvent.getActionMasked() == 5) {
                this.keepMonitorTouchExplore = false;
            }
            if (this.stateChangeRequested) {
                this.queuedMotionEvents.add(motionEvent);
                return;
            }
            ReceivedPointerTracker receivedPointerTracker = this.receivedPointerTracker;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            receivedPointerTracker.handleReceivedPointerUp(motionEvent.getActionIndex(), motionEvent);
                        }
                    } else {
                        receivedPointerTracker.handleReceivedPointerDown(motionEvent.getActionIndex(), motionEvent);
                    }
                } else {
                    receivedPointerTracker.handleReceivedPointerUp(motionEvent.getActionIndex(), motionEvent);
                }
            } else {
                receivedPointerTracker.handleReceivedPointerDown(motionEvent.getActionIndex(), motionEvent);
            }
            int i = this.state;
            if (i == 1 || i == 2) {
                if (this.waitFirstMotionEvent && motionEvent.getActionMasked() == 5) {
                    this.eventId = Performance.instance.onGestureEventReceived(this.displayId, motionEvent);
                    this.waitFirstMotionEvent = false;
                }
                this.gestureDetector.onMotionEvent$ar$ds(this.eventId, motionEvent);
            }
            if (!this.gestureStarted) {
                int i2 = this.state;
                if (i2 != 1) {
                    if (i2 == 3 && motionEvent.getActionMasked() == 2 && this.draggingPointerId != -1 && (pointerCount = motionEvent.getPointerCount()) != 1) {
                        if (pointerCount != 2) {
                            if (!this.gestureDetector.multiFingerGesturesEnabled) {
                                requestDelegating("handleMotionEventStateDragging-3-points");
                                return;
                            }
                            return;
                        } else {
                            if (!isDraggingGesture(motionEvent)) {
                                requestDelegating("handleMotionEventStateDragging-2-points");
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                int actionMasked2 = motionEvent.getActionMasked();
                if (actionMasked2 != 0) {
                    if (actionMasked2 != 2) {
                        if (actionMasked2 == 5) {
                            this.requestTouchExplorationDelayed.cancel();
                            return;
                        }
                        return;
                    }
                    int pointerCount2 = motionEvent.getPointerCount();
                    if (pointerCount2 != 2) {
                        if (pointerCount2 == 3) {
                            long j = this.context.getResources().getDisplayMetrics().heightPixels;
                            for (int i3 = 0; i3 < motionEvent.getPointerCount(); i3++) {
                                if (this.receivedPointerTracker.getReceivedPointerDownY(motionEvent.getPointerId(i3)) < ((float) j) - this.edgeSwipeHeightPixels) {
                                    return;
                                }
                            }
                            requestDelegating("handleMotionEventStateTouchInteracting-3-points");
                            return;
                        }
                        return;
                    }
                    if (this.gestureDetector.twoFingerPassthroughEnabled) {
                        for (int i4 = 0; i4 < motionEvent.getPointerCount(); i4++) {
                            int pointerId = motionEvent.getPointerId(i4);
                            if (!this.receivedPointerTracker.isReceivedPointerDown(pointerId)) {
                                LogUtils.e("TouchInteractionMonitor", "Invalid pointer id: %d", Integer.valueOf(pointerId));
                                return;
                            } else {
                                if (Math.hypot(this.receivedPointerTracker.getReceivedPointerDownX(pointerId) - motionEvent.getX(i4), this.receivedPointerTracker.getReceivedPointerDownY(pointerId) - motionEvent.getY(i4)) < this.passthroughTotalSlop) {
                                    return;
                                }
                            }
                        }
                    }
                    if (isDraggingGesture(motionEvent)) {
                        int i5 = this.draggingPointerId;
                        if (i5 == -1 || motionEvent.findPointerIndex(motionEvent.findPointerIndex(i5)) < 0) {
                            float x = motionEvent.getX(0);
                            float y = motionEvent.getY(0);
                            int pointerId2 = motionEvent.getPointerId(0);
                            float x2 = motionEvent.getX(1);
                            float y2 = motionEvent.getY(1);
                            int pointerId3 = motionEvent.getPointerId(1);
                            if (getDistanceToClosestEdge(x, y) >= getDistanceToClosestEdge(x2, y2)) {
                                pointerId2 = pointerId3;
                            }
                            this.draggingPointerId = pointerId2;
                        }
                        final int i6 = this.draggingPointerId;
                        boolean z = this.handleStateChangeInMainThread;
                        final long uptimeMillis = SystemClock.uptimeMillis();
                        if (z && !Looper.getMainLooper().isCurrentThread()) {
                            this.mainHandler.post(new Runnable() { // from class: com.google.android.accessibility.talkback.TouchInteractionMonitor$$ExternalSyntheticLambda2
                                public final /* synthetic */ String f$2 = "handleMotionEventStateTouchInteracting";

                                @Override // java.lang.Runnable
                                public final void run() {
                                    TouchInteractionMonitor.this.requestDraggingFromMainThread(i6, this.f$2, uptimeMillis);
                                }
                            });
                            return;
                        } else {
                            requestDraggingFromMainThread(i6, "handleMotionEventStateTouchInteracting", 0L);
                            return;
                        }
                    }
                    requestDelegating("handleMotionEventStateTouchInteracting-2-points");
                    return;
                }
                if (this.waitFirstMotionEvent) {
                    this.eventId = Performance.instance.onGestureEventReceived(this.displayId, motionEvent);
                    this.waitFirstMotionEvent = false;
                }
                RequestTouchExplorationDelayed requestTouchExplorationDelayed = this.requestTouchExplorationDelayed;
                hasCallbacks = TouchInteractionMonitor.this.mainHandler.hasCallbacks(requestTouchExplorationDelayed);
                if (hasCallbacks) {
                    this.requestTouchExplorationDelayed.cancel();
                }
                RequestTouchExplorationDelayed requestTouchExplorationDelayed2 = this.requestTouchExplorationDelayed;
                TouchInteractionMonitor.this.mainHandler.postDelayed(requestTouchExplorationDelayed2, requestTouchExplorationDelayed2.mDelay);
                return;
            }
            return;
        }
        LogUtils.e("TouchInteractionMonitor", "Event is null.", new Object[0]);
    }

    public final void onStateChanged(int i) {
        String stateToString;
        String stateToString2;
        stateToString = TouchInteractionController.stateToString(this.state);
        stateToString2 = TouchInteractionController.stateToString(i);
        LogUtils.v("TouchInteractionMonitor", "%s -> %s", stateToString, stateToString2);
        if (this.state == 0) {
            clear();
        }
        if (i == 1) {
            this.waitFirstMotionEvent = true;
        } else if (i == 2) {
            Performance.instance.onGestureRecognized(this.eventId, -6);
            this.waitFirstMotionEvent = true;
            i = 2;
        }
        this.previousState = this.state;
        this.state = i;
        this.requestTouchExplorationDelayed.cancel();
        this.stateChangeRequested = false;
        int i2 = this.state;
        if (i2 != 1 && i2 != 3) {
            this.queuedMotionEvents.clear();
            return;
        }
        while (!this.stateChangeRequested && !this.queuedMotionEvents.isEmpty()) {
            onMotionEvent((MotionEvent) this.queuedMotionEvents.poll());
        }
    }

    protected final void reportStateChangeLatency(long j) {
        if (this.handleStateChangeInMainThread) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis >= j) {
                this.primesController.recordDuration(PrimesController.TimerAction.TOUCH_CONTROLLER_STATE_CHANGE_LATENCY, j, uptimeMillis);
            }
        }
    }

    protected final void requestDelegating(String str) {
        boolean z = this.handleStateChangeInMainThread;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (z && !Looper.getMainLooper().isCurrentThread()) {
            this.mainHandler.post(new TouchInteractionMonitor$$ExternalSyntheticLambda1(this, str, uptimeMillis, 1));
        } else {
            requestDelegatingFromMainThread(str, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requestDelegatingFromMainThread(String str, long j) {
        if (j != 0) {
            reportStateChangeLatency(j);
        }
        try {
            if (isStateTransitionAllowed()) {
                trackStateChangeRequest(4, str);
                this.controller.requestDelegating();
            }
            this.gestureDetector.clear();
            this.stateChangeRequested = true;
        } catch (IllegalStateException e) {
            if (this.handleStateChangeInMainThread) {
                throw packExceptionWithCallerInfo(e);
            }
            throw packExceptionWithCallerInfo(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requestDraggingFromMainThread(int i, String str, long j) {
        if (j != 0) {
            reportStateChangeLatency(j);
        }
        try {
            if (isStateTransitionAllowed()) {
                trackStateChangeRequest(3, "handleMotionEventStateTouchInteracting");
                this.controller.requestDragging(i);
            }
            this.gestureDetector.clear();
            this.stateChangeRequested = true;
        } catch (IllegalStateException e) {
            if (this.handleStateChangeInMainThread) {
                throw packExceptionWithCallerInfo(e);
            }
            throw packExceptionWithCallerInfo(e);
        }
    }

    public final void requestTouchExploration(String str) {
        boolean z = this.handleStateChangeInMainThread;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (z && !Looper.getMainLooper().isCurrentThread()) {
            this.mainHandler.post(new TouchInteractionMonitor$$ExternalSyntheticLambda1(this, str, uptimeMillis, 0));
        } else {
            requestTouchExplorationFromMainThread(str, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void requestTouchExplorationFromMainThread(String str, long j) {
        if (j != 0) {
            reportStateChangeLatency(j);
        }
        try {
            if (isStateTransitionAllowed()) {
                trackStateChangeRequest(2, str);
                this.controller.requestTouchExploration();
            }
            this.stateChangeRequested = true;
        } catch (IllegalStateException e) {
            if (this.handleStateChangeInMainThread) {
                throw packExceptionWithCallerInfo(e);
            }
            throw packExceptionWithCallerInfo(e);
        }
    }
}
