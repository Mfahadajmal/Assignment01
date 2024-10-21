package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.eventprocessor.EventState;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.output.FeedbackItem;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.accessibility.utils.traversal.OrderedTraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FullScreenReadActor {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public FullScreenReadDialog fullScreenReadDialog;
    AccessibilityNodeInfoCompat pausedNode;
    public Pipeline.FeedbackReturner pipeline;
    private final AccessibilityService service;
    private final SpeechController speechController;
    private PowerManager.WakeLock wakeLock;
    private int currentState = 0;
    private int previousState = 0;
    private int stateWaitingForContentFocus = 0;
    private final RetryReadingHandler retryReadingHandler = new RetryReadingHandler(this);
    public final HapticPatternParser$$ExternalSyntheticLambda1 state$ar$class_merging$8dadd5e0_0$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    private final SpeechController.UtteranceCompleteRunnable nodeSpokenRunnable = new SelectToSpeakJob.AnonymousClass1(this, 3);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RetryReadingHandler extends WeakReferenceHandler {
        public RetryReadingHandler(FullScreenReadActor fullScreenReadActor) {
            super(fullScreenReadActor, Looper.myLooper());
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            FullScreenReadActor fullScreenReadActor = (FullScreenReadActor) obj;
            if (message.what == 0) {
                fullScreenReadActor.startReadingFromBeginningInternal((Performance.EventId) message.obj, message.arg1);
            }
        }
    }

    public FullScreenReadActor(AccessibilityFocusMonitor accessibilityFocusMonitor, TalkBackService talkBackService, SpeechController speechController) {
        if (accessibilityFocusMonitor != null) {
            this.accessibilityFocusMonitor = accessibilityFocusMonitor;
            this.service = talkBackService;
            this.speechController = speechController;
            this.fullScreenReadDialog = new FullScreenReadDialog(talkBackService);
            this.wakeLock = ((PowerManager) talkBackService.getSystemService("power")).newWakeLock(536870918, "FullScreenReadActor");
            return;
        }
        throw new IllegalStateException();
    }

    private final void moveTo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Logger logger = Performance.DEFAULT_LOGGER;
        FocusActionInfo.Builder builder = new FocusActionInfo.Builder();
        builder.sourceAction = 4;
        FocusActionInfo focusActionInfo = new FocusActionInfo(builder);
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        Feedback.Focus.Builder focus = Feedback.focus(accessibilityNodeInfoCompat, focusActionInfo);
        focus.setForceRefocus$ar$ds(true);
        builder2.focus = focus.build();
        if (!SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder2)) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
            interrupt(true);
        }
    }

    private final void startReadingFromFocusedNodeInternal$ar$ds() {
        if (isActive()) {
            return;
        }
        AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
        if (accessibilityFocus == null) {
            LogUtils.w("FullScreenReadActor", "Fail to read from next: Current node is null.", new Object[0]);
            return;
        }
        setReadingState(2);
        if (!this.wakeLock.isHeld()) {
            this.wakeLock.acquire();
        }
        EventState.instance.setFlag(8);
        moveTo(accessibilityFocus);
    }

    public final void ignore() {
        if (this.pausedNode != null) {
            this.previousState = this.currentState;
            SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) this.speechController;
            if (speechControllerImpl.savedFeedbackQueue != null && speechControllerImpl.requestPause) {
                speechControllerImpl.resetSavedFeedbackInfo();
            }
            this.pausedNode = null;
        }
    }

    public final void interrupt() {
        interrupt(false);
    }

    public final boolean isActive() {
        if (this.currentState != 0) {
            return true;
        }
        return false;
    }

    public final boolean isPreviousActive() {
        if (this.previousState != 0) {
            return true;
        }
        return false;
    }

    public final void pauseOrResumeContinuousReadingState() {
        AccessibilityNodeInfoCompat accessibilityFocus;
        FeedbackItem feedbackItem;
        if ((isActive() || isPreviousActive()) && (accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false)) != null) {
            if (accessibilityFocus.equals(this.pausedNode)) {
                this.pausedNode = null;
                if (isPreviousActive()) {
                    SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) this.speechController;
                    if (speechControllerImpl.requestPause && (feedbackItem = speechControllerImpl.savedFeedbackItem) != null && feedbackItem.hasFlag(64)) {
                        setReadingState(2);
                        return;
                    }
                    return;
                }
                return;
            }
            this.pausedNode = accessibilityFocus;
        }
    }

    public final void readFocusedContent(Performance.EventId eventId) {
        FullScreenReadDialog fullScreenReadDialog = this.fullScreenReadDialog;
        if (fullScreenReadDialog.waitingForContentFocus) {
            fullScreenReadDialog.waitingForContentFocus = false;
            int i = this.stateWaitingForContentFocus;
            if (i == 1) {
                startReadingFromBeginningInternal(eventId, 0);
            } else if (i == 2) {
                startReadingFromFocusedNodeInternal$ar$ds();
            }
        }
    }

    public final void setReadingState(int i) {
        SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable;
        LogUtils.v("FullScreenReadActor", "Continuous reading switching to mode: %s", Integer.valueOf(i));
        this.previousState = this.currentState;
        this.currentState = i;
        SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable2 = this.nodeSpokenRunnable;
        boolean isActive = isActive();
        if (true != isActive) {
            utteranceCompleteRunnable = null;
        } else {
            utteranceCompleteRunnable = utteranceCompleteRunnable2;
        }
        SpeechControllerImpl speechControllerImpl = (SpeechControllerImpl) this.speechController;
        speechControllerImpl.mFullScreenReadNextCallback = utteranceCompleteRunnable;
        speechControllerImpl.mInjectFullScreenReadCallbacks = isActive;
        if (!isActive) {
            Iterator it = speechControllerImpl.mUtteranceCompleteActions.iterator();
            while (it.hasNext()) {
                if (((SpeechControllerImpl.UtteranceCompleteAction) it.next()).runnable == utteranceCompleteRunnable2) {
                    it.remove();
                }
            }
        }
    }

    public final void startReadingFromBeginning(Performance.EventId eventId) {
        if (this.fullScreenReadDialog.getShouldShowDialogPref()) {
            this.stateWaitingForContentFocus = 1;
            this.fullScreenReadDialog.showDialogBeforeReading(eventId);
        } else {
            startReadingFromBeginningInternal(eventId, 0);
        }
    }

    public final void startReadingFromBeginningInternal(Performance.EventId eventId, int i) {
        if (!isActive()) {
            AccessibilityNodeInfoCompat rootInActiveWindow = SpannableUtils$IdentifierSpan.getRootInActiveWindow(this.service);
            if (rootInActiveWindow == null) {
                RetryReadingHandler retryReadingHandler = this.retryReadingHandler;
                retryReadingHandler.removeMessages(0);
                if (i > 10) {
                    LogUtils.w("FullScreenReadActor", "Fail to read from top: No active window.", new Object[0]);
                    return;
                } else {
                    retryReadingHandler.sendMessageDelayed(Message.obtain(retryReadingHandler, 0, i + 1, 0, eventId), 50L);
                    return;
                }
            }
            AccessibilityNodeInfoCompat findFirstFocusInNodeTree = TraversalStrategyUtils.findFirstFocusInNodeTree(new OrderedTraversalStrategy(rootInActiveWindow), rootInActiveWindow, 1, AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS);
            if (findFirstFocusInNodeTree != null) {
                setReadingState(1);
                if (!this.wakeLock.isHeld()) {
                    this.wakeLock.acquire();
                }
                EventState.instance.setFlag(8);
                moveTo(findFirstFocusInNodeTree);
            }
        }
    }

    public final void startReadingFromFocusedNode(Performance.EventId eventId) {
        if (this.fullScreenReadDialog.getShouldShowDialogPref()) {
            this.stateWaitingForContentFocus = 2;
            this.fullScreenReadDialog.showDialogBeforeReading(eventId);
        } else {
            startReadingFromFocusedNodeInternal$ar$ds();
        }
    }

    public final void interrupt(boolean z) {
        if (z) {
            LogUtils.d("FullScreenReadActor", "Continuous reading interrupt internal ", new Object[0]);
        }
        setReadingState(0);
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
    }
}
