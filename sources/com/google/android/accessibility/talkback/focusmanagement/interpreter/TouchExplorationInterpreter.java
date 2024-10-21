package com.google.android.accessibility.talkback.focusmanagement.interpreter;

import android.os.Message;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.action.TouchExplorationAction;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.monitor.InputModeTracker;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TouchExplorationInterpreter implements AccessibilityEventListener {
    private final InputModeTracker inputModeTracker;
    private AccessibilityNodeInfoCompat lastTouchedNode;
    public final List listeners = new ArrayList();
    private final PostDelayHandler postDelayHandler = new PostDelayHandler(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostDelayHandler extends WeakReferenceHandler {
        public Performance.EventId touchEndEventId;

        public PostDelayHandler(TouchExplorationInterpreter touchExplorationInterpreter) {
            super(touchExplorationInterpreter);
            this.touchEndEventId = null;
        }

        final void cancelPendingEmptyTouchAction(boolean z) {
            boolean z2;
            if (z && hasMessages(0)) {
                z2 = true;
            } else {
                z2 = false;
            }
            removeMessages(0);
            if (z2) {
                ((TouchExplorationInterpreter) getParent()).dispatchTouchExplorationAction(new TouchExplorationAction(1, null), null);
            }
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            TouchExplorationInterpreter touchExplorationInterpreter = (TouchExplorationInterpreter) obj;
            if (message.what == 0) {
                touchExplorationInterpreter.dispatchTouchExplorationAction(new TouchExplorationAction(1, null), (Performance.EventId) message.obj);
            } else if (message.what == 1) {
                handleTouchEndAction();
            }
        }

        public final void handleTouchEndAction() {
            Performance.EventId eventId;
            TouchExplorationInterpreter touchExplorationInterpreter = (TouchExplorationInterpreter) getParent();
            if (touchExplorationInterpreter != null && (eventId = this.touchEndEventId) != null) {
                if (touchExplorationInterpreter.handleTouchInteractionEndEvent(eventId)) {
                    touchExplorationInterpreter.setInputTouchMode();
                }
                this.touchEndEventId = null;
            }
        }
    }

    public TouchExplorationInterpreter(InputModeTracker inputModeTracker) {
        this.inputModeTracker = inputModeTracker;
    }

    public final boolean dispatchTouchExplorationAction(TouchExplorationAction touchExplorationAction, Performance.EventId eventId) {
        Iterator it = this.listeners.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= ((AccessibilityFocusInterpreter) it.next()).onTouchExplorationAction(touchExplorationAction, eventId);
        }
        return z;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 3145856;
    }

    public final boolean handleTouchInteractionEndEvent(Performance.EventId eventId) {
        this.lastTouchedNode = null;
        this.postDelayHandler.cancelPendingEmptyTouchAction(true);
        return dispatchTouchExplorationAction(new TouchExplorationAction(2, null), eventId);
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        int eventType = accessibilityEvent.getEventType();
        boolean z = false;
        if (eventType != 1048576) {
            if (eventType != 2097152) {
                AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
                if (compat != null && !compat.equals(this.lastTouchedNode)) {
                    this.lastTouchedNode = compat;
                    AccessibilityNodeInfoCompat selfOrMatchingAncestor = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(compat, AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS_EXCEPT_WEB_VIEW);
                    if (selfOrMatchingAncestor == null) {
                        PostDelayHandler postDelayHandler = this.postDelayHandler;
                        postDelayHandler.sendMessageDelayed(postDelayHandler.obtainMessage(0, eventId), 100L);
                    } else {
                        this.postDelayHandler.cancelPendingEmptyTouchAction(false);
                        z = dispatchTouchExplorationAction(new TouchExplorationAction(1, selfOrMatchingAncestor), eventId);
                    }
                }
            } else if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                z = handleTouchInteractionEndEvent(eventId);
            } else {
                PostDelayHandler postDelayHandler2 = this.postDelayHandler;
                postDelayHandler2.touchEndEventId = eventId;
                postDelayHandler2.sendMessageDelayed(postDelayHandler2.obtainMessage(1), 70L);
                return;
            }
        } else {
            PostDelayHandler postDelayHandler3 = this.postDelayHandler;
            if (postDelayHandler3.hasMessages(1)) {
                postDelayHandler3.removeMessages(1);
                postDelayHandler3.handleTouchEndAction();
            }
            this.lastTouchedNode = null;
            this.postDelayHandler.cancelPendingEmptyTouchAction(false);
            z = dispatchTouchExplorationAction(new TouchExplorationAction(0, null), eventId);
        }
        if (z) {
            setInputTouchMode();
        }
    }

    public final void setInputTouchMode() {
        this.inputModeTracker.setInputMode(0);
    }
}
