package com.google.android.accessibility.talkback;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.RingerModeAndScreenMonitor;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackExitController implements AccessibilityEventListener, RingerModeAndScreenMonitor.ScreenChangedListener {
    private static final long TAP_TIMEOUT_MS = ViewConfiguration.getJumpTapTimeout();
    public ActorState actorState;
    public final TalkBackService service;
    private AccessibilityNodeInfo targetNode = null;
    private long touchInteractionStartTime;
    public TalkBackService.IpcClientCallbackImpl trainingState$ar$class_merging;

    public TalkBackExitController(TalkBackService talkBackService) {
        this.service = talkBackService;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 3145856;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        String viewIdResourceName;
        AccessibilityNodeInfo source = accessibilityEvent.getSource();
        int eventType = accessibilityEvent.getEventType();
        if (eventType != 128) {
            if (eventType != 1048576) {
                if (eventType == 2097152 && this.targetNode != null && SystemClock.uptimeMillis() - this.touchInteractionStartTime < TAP_TIMEOUT_MS) {
                    this.targetNode.performAction(16);
                    this.targetNode = null;
                    return;
                }
                return;
            }
            this.touchInteractionStartTime = SystemClock.uptimeMillis();
            this.targetNode = null;
            return;
        }
        if (source == null) {
            viewIdResourceName = "";
        } else {
            viewIdResourceName = source.getViewIdResourceName();
        }
        if (true != TextUtils.equals(viewIdResourceName, "com.google.android.marvin.talkback:id/training_exit_talkback_button")) {
            source = null;
        }
        this.targetNode = source;
    }

    @Override // com.google.android.accessibility.talkback.RingerModeAndScreenMonitor.ScreenChangedListener
    public final void onScreenChanged$ar$ds(boolean z) {
        ActorState actorState = this.actorState;
        if (actorState != null && this.trainingState$ar$class_merging != null) {
            ActorStateWritable actorStateWritable = actorState.writable;
            TalkBackService talkBackService = this.service;
            int i = actorStateWritable.lastSystemAction;
            boolean isDeviceLocked = ScreenMonitor.isDeviceLocked(talkBackService);
            LogUtils.d("TalkBackExitController", "onScreenChanged: isDeviceLocked=%b , lastPerformedSystemAction=%d", Boolean.valueOf(isDeviceLocked), Integer.valueOf(i));
            if (isDeviceLocked && i == 0) {
                turnOffTalkBackIfTutorialActive(2);
            }
        }
    }

    public final void turnOffTalkBackIfTutorialActive(int i) {
        boolean isTrainingRecentActive = this.trainingState$ar$class_merging.isTrainingRecentActive();
        PageConfig.PageId pageId = this.trainingState$ar$class_merging.currentPageId;
        LogUtils.w("TalkBackExitController", "turnOffTalkBackIfTutorialActive:  trainingActive=%b, current pageId=%d", Boolean.valueOf(isTrainingRecentActive), Integer.valueOf(pageId.ordinal()));
        if (!this.service.hasTrainingFinishedByUser() && isTrainingRecentActive && pageId == PageConfig.PageId.PAGE_ID_WELCOME_TO_TALKBACK) {
            this.service.requestDisableTalkBack(i);
        }
    }
}
