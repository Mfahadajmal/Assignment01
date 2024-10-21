package com.google.android.accessibility.utils.gestures;

import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.TouchInteractionController;
import android.content.Context;
import android.view.MotionEvent;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.talkback.TouchInteractionMonitor;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureManifold {
    private final int displayId;
    public Listener listener;
    public final List gestures = new ArrayList();
    public final List multiFingerGestures = new ArrayList();
    public final List twoFingerSwipes = new ArrayList();
    public boolean logMotionEvent = false;
    public boolean multiFingerGesturesEnabled = false;
    public boolean twoFingerPassthroughEnabled = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Listener {
    }

    public GestureManifold(Context context, Listener listener, int i, ImmutableList immutableList) {
        GestureMatcher multiFingerMultiTap;
        GestureMatcher secondFingerTap;
        this.listener = listener;
        this.displayId = i;
        ArrayList<GestureMatcher> arrayList = new ArrayList();
        for (GestureMatcherFactory$GestureMatchConfig gestureMatcherFactory$GestureMatchConfig : GestureMatcherFactory$GestureMatchConfig.values()) {
            if (immutableList.contains(gestureMatcherFactory$GestureMatchConfig.name())) {
                int i2 = gestureMatcherFactory$GestureMatchConfig.finger;
                if (i2 == 1) {
                    int i3 = gestureMatcherFactory$GestureMatchConfig.tap;
                    if (i3 == 0) {
                        int i4 = gestureMatcherFactory$GestureMatchConfig.direction2;
                        if (i4 == -1) {
                            multiFingerMultiTap = new Swipe(context, new int[]{gestureMatcherFactory$GestureMatchConfig.direction1}, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                        } else {
                            secondFingerTap = new Swipe(context, new int[]{gestureMatcherFactory$GestureMatchConfig.direction1, i4}, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                            multiFingerMultiTap = secondFingerTap;
                        }
                    } else {
                        if (i3 == 1) {
                            if (gestureMatcherFactory$GestureMatchConfig.isHold) {
                                secondFingerTap = new SecondFingerTap(context, 1, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                                multiFingerMultiTap = secondFingerTap;
                            }
                        } else if (i3 == 2) {
                            if (gestureMatcherFactory$GestureMatchConfig.isHold) {
                                multiFingerMultiTap = new MultiTapAndHold(context, 2, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                            } else {
                                multiFingerMultiTap = new MultiTap(context, 2, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                            }
                        }
                        throw new IllegalArgumentException(String.format("IllegalArgumentException: GestureMatchConfig %s defines the wrong argument. %s", gestureMatcherFactory$GestureMatchConfig.name(), gestureMatcherFactory$GestureMatchConfig));
                    }
                } else {
                    int i5 = gestureMatcherFactory$GestureMatchConfig.tap;
                    if (i5 == 0) {
                        multiFingerMultiTap = new MultiFingerSwipe(context, i2, gestureMatcherFactory$GestureMatchConfig.direction1, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                    } else if (gestureMatcherFactory$GestureMatchConfig.isHold) {
                        multiFingerMultiTap = new MultiFingerMultiTapAndHold(context, i2, i5, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                    } else {
                        multiFingerMultiTap = new MultiFingerMultiTap(context, i2, i5, gestureMatcherFactory$GestureMatchConfig.gestureId, this);
                    }
                }
                arrayList.add(multiFingerMultiTap);
            }
        }
        for (GestureMatcher gestureMatcher : arrayList) {
            if (gestureMatcher != null) {
                if (!(gestureMatcher instanceof Swipe) && !(gestureMatcher instanceof MultiTap) && !(gestureMatcher instanceof MultiTapAndHold) && !(gestureMatcher instanceof SecondFingerTap)) {
                    this.multiFingerGestures.add(gestureMatcher);
                    int i6 = gestureMatcher.gestureId;
                    if (i6 == 26 || i6 == 27 || i6 == 28 || i6 == 25) {
                        this.twoFingerSwipes.add(gestureMatcher);
                    }
                } else {
                    this.gestures.add(gestureMatcher);
                }
            }
        }
    }

    public final void clear() {
        Iterator it = this.gestures.iterator();
        while (it.hasNext()) {
            ((GestureMatcher) it.next()).clear();
        }
    }

    public final void onGestureCompleted(int i, MotionEvent motionEvent) {
        int gestureId;
        String stateToString;
        String stateToString2;
        int gestureId2;
        AccessibilityGestureEvent accessibilityGestureEvent = new AccessibilityGestureEvent(i, this.displayId, new ArrayList());
        for (GestureMatcher gestureMatcher : this.gestures) {
            if (gestureMatcher.gestureId != i) {
                gestureMatcher.setState(3, motionEvent, false);
            }
        }
        Listener listener = this.listener;
        gestureId = accessibilityGestureEvent.getGestureId();
        LogUtils.v("TouchInteractionMonitor", "TalkBack gesture id:%s detected", SpannableUtils$IdentifierSpan.gestureIdToString(gestureId));
        TouchInteractionMonitor touchInteractionMonitor = (TouchInteractionMonitor) listener;
        touchInteractionMonitor.keepMonitorTouchExplore = false;
        int i2 = touchInteractionMonitor.state;
        if (i2 != 3 && i2 != 4 && ((i2 != 0 || touchInteractionMonitor.previousState != 3) && touchInteractionMonitor.previousState != 4)) {
            gestureId2 = accessibilityGestureEvent.getGestureId();
            Performance.instance.onGestureRecognized(touchInteractionMonitor.eventId, gestureId2);
            int i3 = 17;
            if (gestureId2 == 17) {
                if (!touchInteractionMonitor.serviceHandlesDoubleTap) {
                    touchInteractionMonitor.controller.performClick();
                    return;
                } else {
                    touchInteractionMonitor.dispatchGestureToMainThreadAndClear(accessibilityGestureEvent);
                    return;
                }
            }
            if (gestureId2 == 18) {
                touchInteractionMonitor.controller.performLongClickAndStartDrag();
                return;
            } else if (gestureId2 == -3) {
                touchInteractionMonitor.mainHandler.post(new DelayedWorkTracker.AnonymousClass1(listener, new AccessibilityGestureEvent(-3, touchInteractionMonitor.displayId, new ArrayList()), i3, (char[]) null));
                touchInteractionMonitor.eventId = Performance.instance.onGestureEventReceived(touchInteractionMonitor.displayId, null);
                return;
            } else {
                touchInteractionMonitor.dispatchGestureToMainThreadAndClear(accessibilityGestureEvent);
                return;
            }
        }
        stateToString = TouchInteractionController.stateToString(i2);
        stateToString2 = TouchInteractionController.stateToString(touchInteractionMonitor.previousState);
        LogUtils.w("TouchInteractionMonitor", "Gesture %s dropped in state %s , previous state %s", accessibilityGestureEvent, stateToString, stateToString2);
    }

    public final void onMotionEvent$ar$ds(Performance.EventId eventId, MotionEvent motionEvent) {
        for (GestureMatcher gestureMatcher : this.gestures) {
            if (gestureMatcher.state != 3) {
                int i = gestureMatcher.state;
                if (i != 3 && i != 2) {
                    int actionMasked = motionEvent.getActionMasked();
                    if (actionMasked != 0) {
                        if (actionMasked != 1) {
                            if (actionMasked != 2) {
                                if (actionMasked != 5) {
                                    if (actionMasked != 6) {
                                        gestureMatcher.setState(3, motionEvent);
                                    } else {
                                        gestureMatcher.onPointerUp(eventId, motionEvent);
                                    }
                                } else {
                                    gestureMatcher.onPointerDown(eventId, motionEvent);
                                }
                            } else {
                                gestureMatcher.onMove$ar$ds(motionEvent);
                            }
                        } else {
                            gestureMatcher.onUp(eventId, motionEvent);
                        }
                    } else {
                        gestureMatcher.onDown(eventId, motionEvent);
                    }
                    int i2 = gestureMatcher.state;
                }
                if (gestureMatcher.state == 2) {
                    return;
                }
            }
        }
    }
}
