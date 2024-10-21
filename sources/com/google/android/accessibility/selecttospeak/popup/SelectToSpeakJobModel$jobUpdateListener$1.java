package com.google.android.accessibility.selecttospeak.popup;

import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.lifecycle.ServiceState;
import com.google.android.accessibility.selecttospeak.logging.S2SPipelineAnalytics;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakJobModel$jobUpdateListener$1 implements SelectToSpeakJob.JobUpdateListener {
    final /* synthetic */ Object SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
    private final /* synthetic */ int switching_field;

    public SelectToSpeakJobModel$jobUpdateListener$1(Object obj, int i) {
        this.switching_field = i;
        this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0 = obj;
    }

    @Override // com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob.JobUpdateListener
    public final void onFinish() {
        if (this.switching_field != 0) {
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).hideAssistImmediate();
        } else {
            ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).finish(false);
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob.JobUpdateListener
    public final void onPause() {
        if (this.switching_field != 0) {
            Object obj = this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
            ((SelectToSpeakService) obj).serviceState.setCurrent$ar$edu(ServiceState.State.CONTINUOUS_READING_PAUSED$ar$edu);
            ControlOverlays controlOverlays = ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.controlOverlays;
            CollapsibleControlPanel collapsibleControlPanel = controlOverlays.collapsedPanel;
            if (collapsibleControlPanel != null) {
                collapsibleControlPanel.displayPlayButton();
            }
            CollapsibleControlPanel collapsibleControlPanel2 = controlOverlays.expandablePanel;
            if (collapsibleControlPanel2 != null) {
                collapsibleControlPanel2.displayPlayButton();
            }
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.setControlActionEnabled(2, false);
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.setControlActionEnabled(1, false);
            return;
        }
        ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).isPaused().setValue(true);
        OptionalMethod optionalMethod = ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_PAUSE_ACTION$ar$edu);
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob.JobUpdateListener
    public final void onResume() {
        if (this.switching_field != 0) {
            Object obj = this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
            ((SelectToSpeakService) obj).serviceState.setCurrent$ar$edu(ServiceState.State.CONTINUOUS_READING$ar$edu);
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.displayPauseButton();
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.clearAnimations();
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).updateViewOnSpeechRateChanged();
            return;
        }
        ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).isPaused().setValue(false);
        OptionalMethod optionalMethod = ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_RESUME_ACTION$ar$edu);
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob.JobUpdateListener
    public final void onSpeechRateChanged() {
        if (this.switching_field != 0) {
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).updateViewOnSpeechRateChanged();
        }
    }

    @Override // com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob.JobUpdateListener
    public final void onStart() {
        boolean z = false;
        if (this.switching_field != 0) {
            if (!((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).wakeLock.isHeld()) {
                ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).wakeLock.acquire(SelectToSpeakService.WAKE_LOCK_TIMEOUT_MS);
            }
            S2SPipelineAnalytics s2SPipelineAnalytics = ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).pipelineAnalytics;
            if (s2SPipelineAnalytics.stopWatch.isRunning && !s2SPipelineAnalytics.elapsedMetrics.isEmpty()) {
                s2SPipelineAnalytics.recordElapsed("KEY_INTERACTION_TO_JOB_START_MS");
                s2SPipelineAnalytics.amend();
            } else {
                s2SPipelineAnalytics.cleanupInMemoryMetrics();
            }
            SelectToSpeakService selectToSpeakService = (SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
            if (selectToSpeakService.isMultitaskingSettingEnabled) {
                selectToSpeakService.uIManager.setDrawingBoardOverlayTouchable(false);
                SelectToSpeakService selectToSpeakService2 = (SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
                selectToSpeakService2.uIManager.setControlPanelOverlayOutsideTouchListener(selectToSpeakService2.controlPanelOverlayOutsideTouchListener);
            }
            SelectToSpeakService selectToSpeakService3 = (SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0;
            UIManager uIManager = selectToSpeakService3.uIManager;
            if (selectToSpeakService3.isMultitaskingSettingEnabled || !selectToSpeakService3.isOcrEnabled()) {
                z = true;
            }
            uIManager.triggerButtonDragActionDetector.isEnabled = z;
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.drawingBoard.clear$ar$ds();
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.setControlPanelExpansionEnabled(true);
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.displayPauseButton();
            if (((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).serviceState.getCurrent$ar$edu() == ServiceState.State.PROCESSING_CONTINUOUS_READING$ar$edu) {
                ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).serviceState.setCurrent$ar$edu(ServiceState.State.CONTINUOUS_READING$ar$edu);
            } else if (((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).serviceState.getCurrent$ar$edu() == ServiceState.State.PROCESSING_SELECTION$ar$edu) {
                ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).serviceState.setCurrent$ar$edu(ServiceState.State.READING_SELECTION$ar$edu);
            }
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.setControlActionEnabled(3, true);
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.setControlActionEnabled(4, true);
            ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).updateViewOnSpeechRateChanged();
            if (((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).serviceState.getCurrent$ar$edu() == ServiceState.State.CONTINUOUS_READING$ar$edu && !((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.isControlPanelExpanded()) {
                ((SelectToSpeakService) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).uIManager.expandControlPanel();
                return;
            }
            return;
        }
        ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).isPaused().setValue(false);
        OptionalMethod optionalMethod = ((SelectToSpeakJobModel) this.SelectToSpeakJobModel$jobUpdateListener$1$ar$this$0).analytics$ar$class_merging$a359db55_0$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.POPUP_ENABLE_ACTION$ar$edu);
        }
    }
}
