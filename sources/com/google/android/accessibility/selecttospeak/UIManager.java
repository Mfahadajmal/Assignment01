package com.google.android.accessibility.selecttospeak;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageButton;
import androidx.activity.OnBackPressedDispatcher$addCancellableCallback$1;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.room.SharedSQLiteStatement$stmt$2;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.selecttospeak.logging.S2SPipelineAnalytics;
import com.google.android.accessibility.selecttospeak.logging.S2sHatsSurveyRequester$requestSurveyData$1;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlaysAnimations;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$scheduleAutoCollapse$1;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.accessibility.selecttospeak.ui.DrawingBoard;
import com.google.android.accessibility.selecttospeak.ui.OutsideTouchListener;
import com.google.android.accessibility.selecttospeak.ui.S2SWindowOverlay;
import com.google.android.accessibility.selecttospeak.ui.TriggerButtonDragActionDetector;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.marvin.talkback.R;
import com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction0;
import com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction2;
import com.google.common.labs.kotlin.base.VoidAsUnit$asUnit$3;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.S2sMagnificationConfig;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.internal.OptionalMethod;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt__JobKt$invokeOnCompletion$1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UIManager {
    public static final String S2S_OVERLAY_IDENTIFIER = SelectToSpeakOverlay.class.getName();
    private final MetricsLogger chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ControlOverlays controlOverlays;
    public CollapsibleControlPanel.CollapsibleControlPanelDragDetector controlPanelHorizontalDragActionDetector;
    public final OnDeviceTextDetectionLoadLogEvent coordinateConverter$ar$class_merging$ar$class_merging;
    public DrawingBoard drawingBoard;
    public boolean isA11yButtonSupported;
    public boolean isTriggerButtonAppearanceActive;
    public final ControlOverlaysAnimations overlaysAnimations;
    public OptionalMethod selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SelectToSpeakService service;
    public TriggerButtonDragActionDetector triggerButtonDragActionDetector;
    private final UIDelayedJobScheduler uiDelayedJobScheduler;
    private final RetryingNameResolver.ResolutionResultListener uiInflater$ar$class_merging$ar$class_merging$ar$class_merging;
    public final RetriableStream.HedgingPlan uiInflationController$ar$class_merging$ar$class_merging$ar$class_merging;
    public SelectToSpeakOverlay workingBoardOverlay;

    public UIManager(SelectToSpeakService selectToSpeakService, LifecycleCoroutineScope lifecycleCoroutineScope, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, S2SPipelineAnalytics s2SPipelineAnalytics) {
        ControlOverlays controlOverlays = new ControlOverlays();
        this.controlOverlays = controlOverlays;
        this.isTriggerButtonAppearanceActive = false;
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this);
        this.uiInflater$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.service = selectToSpeakService;
        MetricsLogger metricsLogger = new MetricsLogger(lifecycleCoroutineScope);
        this.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging = metricsLogger;
        this.uiDelayedJobScheduler = new UIDelayedJobScheduler(lifecycleCoroutineScope, controlOverlays, new OnBackPressedDispatcher$addCancellableCallback$1(new VoidAsUnit$VoidFunction0() { // from class: com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda3
            @Override // com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction0
            public final void invoke() {
                UIManager.this.collapseControlPanel();
            }
        }, 3, null));
        this.overlaysAnimations = new ControlOverlaysAnimations(new JobKt__JobKt$invokeOnCompletion$1(new UIManager$$ExternalSyntheticLambda4(this, 0), 1, null), new JobKt__JobKt$invokeOnCompletion$1(new UIManager$$ExternalSyntheticLambda4(this, 2), 1, null), new VoidAsUnit$asUnit$3(new VoidAsUnit$VoidFunction2() { // from class: com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda6
            @Override // com.google.common.labs.kotlin.base.VoidAsUnit$VoidFunction2
            public final void invoke(Object obj, Object obj2) {
                UIManager.this.loadTriggerButtonFractionalCoordinates(((Boolean) obj).booleanValue(), (float[]) obj2);
            }
        }), metricsLogger, s2SPipelineAnalytics);
        this.uiInflationController$ar$class_merging$ar$class_merging$ar$class_merging = new RetriableStream.HedgingPlan(resolutionResultListener, S2sMagnificationConfig.INSTANCE.get().inflateOnDemand(selectToSpeakService));
        this.coordinateConverter$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlinx.coroutines.Job, java.lang.Object] */
    public final void clearAnimations() {
        UIDelayedJobScheduler uIDelayedJobScheduler = this.uiDelayedJobScheduler;
        uIDelayedJobScheduler.cancelHideOverlayJob();
        uIDelayedJobScheduler.cancelAutoCollapseJob();
        CollapsibleControlPanel.CollapsibleControlPanelDragDetector collapsibleControlPanelDragDetector = this.controlPanelHorizontalDragActionDetector;
        if (collapsibleControlPanelDragDetector != null) {
            collapsibleControlPanelDragDetector.cancelDragDetection();
        }
        TriggerButtonDragActionDetector triggerButtonDragActionDetector = this.triggerButtonDragActionDetector;
        if (triggerButtonDragActionDetector != null) {
            triggerButtonDragActionDetector.cancelDragDetection();
        }
        MetricsLogger metricsLogger = this.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging;
        ?? r1 = metricsLogger.MetricsLogger$ar$loggerProvider$ar$class_merging;
        if (r1 != 0) {
            r1.cancel(null);
        }
        metricsLogger.MetricsLogger$ar$loggerProvider$ar$class_merging = null;
    }

    public final void clearBoardBackground() {
        this.drawingBoard.setBackground(null);
    }

    public final void collapseControlPanel() {
        this.uiDelayedJobScheduler.cancelAutoCollapseJob();
        ControlOverlaysAnimations controlOverlaysAnimations = this.overlaysAnimations;
        CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = controlOverlaysAnimations.collapseExpandAnimator;
        if (collapseExpandAnimator != null) {
            collapseExpandAnimator.prepareForCollapseAnimation$ar$ds();
            controlOverlaysAnimations.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging.runAnimation$ar$ds(collapseExpandAnimator, new SharedSQLiteStatement$stmt$2(controlOverlaysAnimations, 12));
        }
    }

    public final void displayPauseButton() {
        ControlOverlays controlOverlays = this.controlOverlays;
        CollapsibleControlPanel collapsibleControlPanel = controlOverlays.collapsedPanel;
        if (collapsibleControlPanel != null) {
            collapsibleControlPanel.displayPauseButton();
        }
        CollapsibleControlPanel collapsibleControlPanel2 = controlOverlays.expandablePanel;
        if (collapsibleControlPanel2 != null) {
            collapsibleControlPanel2.displayPauseButton();
        }
    }

    public final void expandControlPanel() {
        ControlOverlaysAnimations controlOverlaysAnimations = this.overlaysAnimations;
        CollapsibleControlPanel.CollapseExpandAnimator collapseExpandAnimator = controlOverlaysAnimations.collapseExpandAnimator;
        if (collapseExpandAnimator != null) {
            collapseExpandAnimator.prepareForExpandAnimation$ar$ds();
            controlOverlaysAnimations.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging.runAnimation$ar$ds(collapseExpandAnimator, new SharedSQLiteStatement$stmt$2(controlOverlaysAnimations, 13));
        }
        scheduleCollapseControlPanelAction();
    }

    public final void initializeInfrastructure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(boolean z, RetryingNameResolver.ResolutionResultListener resolutionResultListener, View.OnClickListener onClickListener) {
        this.isA11yButtonSupported = z;
        ControlOverlays controlOverlays = this.controlOverlays;
        controlOverlays.controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        controlOverlays.triggerButtonClickListener = onClickListener;
        S2SWindowOverlay s2SWindowOverlay = new S2SWindowOverlay(this.service, R.layout.layout_overlay_working_board, -1, -1);
        this.workingBoardOverlay = s2SWindowOverlay;
        s2SWindowOverlay.params.flags |= 134217728;
        if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
            s2SWindowOverlay.params.layoutInDisplayCutoutMode = 1;
        }
        if (s2SWindowOverlay.isVisible) {
            s2SWindowOverlay.updateScreenBounds();
            s2SWindowOverlay.windowManager.updateViewLayout(s2SWindowOverlay.contentView, s2SWindowOverlay.params);
        }
        SelectToSpeakOverlay selectToSpeakOverlay = this.workingBoardOverlay;
        ((S2SWindowOverlay) selectToSpeakOverlay).rootViewClassName = S2S_OVERLAY_IDENTIFIER;
        DrawingBoard drawingBoard = (DrawingBoard) selectToSpeakOverlay.findViewById(R.id.drawing_board);
        this.drawingBoard = drawingBoard;
        drawingBoard.coordinateConverter$ar$class_merging$ar$class_merging = this.coordinateConverter$ar$class_merging$ar$class_merging;
        RetriableStream.HedgingPlan hedgingPlan = this.uiInflationController$ar$class_merging$ar$class_merging$ar$class_merging;
        LogUtils.v("ControlOverlayInflater", "initializeInfrastructure onDemand: %s", Boolean.valueOf(hedgingPlan.isHedgeable));
        if (!hedgingPlan.isHedgeable) {
            ((RetryingNameResolver.ResolutionResultListener) hedgingPlan.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis).inflateView();
        }
    }

    public final boolean isControlPanelExpanded() {
        if (this.controlOverlays.foregroundOverlayType == ControlOverlays.OverlayTypes.EXPANDABLE && this.overlaysAnimations.getControlPanelAnimatorVisibility() == 1 && !this.controlOverlays.isCollapsed()) {
            return true;
        }
        return false;
    }

    public final boolean isLayoutRtl() {
        return SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.service);
    }

    public final boolean isNarrowScreen() {
        if (SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(this.service).getResources().getDimensionPixelSize(R.dimen.minimal_width_for_full_control_panel_width) > this.service.getResources().getDisplayMetrics().widthPixels) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.Job, java.lang.Object] */
    public final boolean isUIStable() {
        ?? r0 = this.chainAnimator$ar$class_merging$ar$class_merging$ar$class_merging.MetricsLogger$ar$loggerProvider$ar$class_merging;
        if (r0 == 0 || !r0.isActive()) {
            return true;
        }
        return false;
    }

    public final void loadTriggerButtonFractionalCoordinates(boolean z, float[] fArr) {
        int i;
        int i2;
        float clampValue;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this.service);
        if (z) {
            i = R.string.pref_active_fab_x_as_int_bit_key;
        } else {
            i = R.string.pref_fab_x_as_int_bit_key;
        }
        int i3 = sharedPreferences.getInt(this.service.getString(i), Float.floatToIntBits(1.0f));
        SelectToSpeakService selectToSpeakService = this.service;
        if (true != z) {
            i2 = R.string.pref_fab_y_as_int_bit_key;
        } else {
            i2 = R.string.pref_active_fab_y_as_int_bit_key;
        }
        int i4 = sharedPreferences.getInt(selectToSpeakService.getString(i2), Float.floatToIntBits(1.0f));
        if (z) {
            clampValue = 1.0f;
        } else {
            clampValue = SpannableUtils$IdentifierSpan.clampValue(Float.intBitsToFloat(i3), 0.0f, 1.0f);
        }
        fArr[0] = clampValue;
        if (isLayoutRtl()) {
            fArr[0] = 1.0f - fArr[0];
        }
        fArr[1] = SpannableUtils$IdentifierSpan.clampValue(Float.intBitsToFloat(i4), 0.0f, 1.0f);
    }

    public final void scheduleCollapseControlPanelAction() {
        UIDelayedJobScheduler uIDelayedJobScheduler = this.uiDelayedJobScheduler;
        Job job = uIDelayedJobScheduler.autoExpandJob;
        if (job != null) {
            job.cancel(null);
        }
        uIDelayedJobScheduler.autoExpandJob = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(uIDelayedJobScheduler.scope, null, 0, new UIDelayedJobScheduler$scheduleAutoCollapse$1(uIDelayedJobScheduler, (Continuation) null, 0), 3);
    }

    public final void setBoardBackground(Bitmap bitmap) {
        this.drawingBoard.post(new DelayedWorkTracker.AnonymousClass1(this, bitmap, 15, (char[]) null));
    }

    public final void setControlActionEnabled(int i, boolean z) {
        ControlOverlays controlOverlays = this.controlOverlays;
        CollapsibleControlPanel collapsibleControlPanel = controlOverlays.collapsedPanel;
        if (collapsibleControlPanel != null) {
            collapsibleControlPanel.setEnabled(i, z);
        }
        CollapsibleControlPanel collapsibleControlPanel2 = controlOverlays.expandablePanel;
        if (collapsibleControlPanel2 != null) {
            collapsibleControlPanel2.setEnabled(i, z);
        }
    }

    public final void setControlPanelExpansionEnabled(boolean z) {
        ControlOverlays controlOverlays = this.controlOverlays;
        CollapsibleControlPanel collapsibleControlPanel = controlOverlays.collapsedPanel;
        if (collapsibleControlPanel != null) {
            collapsibleControlPanel.setCollapseExpandButtonEnabled(z);
        }
        CollapsibleControlPanel collapsibleControlPanel2 = controlOverlays.expandablePanel;
        if (collapsibleControlPanel2 != null) {
            collapsibleControlPanel2.setCollapseExpandButtonEnabled(z);
        }
        CollapsibleControlPanel.CollapsibleControlPanelDragDetector collapsibleControlPanelDragDetector = this.controlPanelHorizontalDragActionDetector;
        if (collapsibleControlPanelDragDetector != null) {
            collapsibleControlPanelDragDetector.isEnabled = z;
        }
    }

    public final void setControlPanelOverlayOutsideTouchListener(OutsideTouchListener outsideTouchListener) {
        ControlOverlays controlOverlays = this.controlOverlays;
        SelectToSpeakOverlay selectToSpeakOverlay = controlOverlays.triggerButtonOverlay;
        if (selectToSpeakOverlay != null) {
            selectToSpeakOverlay.setOnTouchListener(outsideTouchListener);
        }
        SelectToSpeakOverlay selectToSpeakOverlay2 = controlOverlays.collapsedOverlay;
        if (selectToSpeakOverlay2 != null) {
            selectToSpeakOverlay2.setOnTouchListener(outsideTouchListener);
        }
        SelectToSpeakOverlay selectToSpeakOverlay3 = controlOverlays.expandableOverlay;
        if (selectToSpeakOverlay3 != null) {
            selectToSpeakOverlay3.setOnTouchListener(outsideTouchListener);
        }
    }

    public final void setDrawingBoardOverlayTouchable(boolean z) {
        SelectToSpeakOverlay selectToSpeakOverlay = this.workingBoardOverlay;
        if (z) {
            ((S2SWindowOverlay) selectToSpeakOverlay).params.flags &= -17;
        } else {
            ((S2SWindowOverlay) selectToSpeakOverlay).params.flags |= 16;
        }
        S2SWindowOverlay s2SWindowOverlay = (S2SWindowOverlay) selectToSpeakOverlay;
        if (s2SWindowOverlay.isVisible) {
            s2SWindowOverlay.windowManager.updateViewLayout(s2SWindowOverlay.contentView, s2SWindowOverlay.params);
        }
    }

    public final void setForegroundOverlay(ControlOverlays.OverlayTypes overlayTypes) {
        SelectToSpeakOverlay selectToSpeakOverlay;
        this.uiDelayedJobScheduler.cancelHideOverlayJob();
        if (overlayTypes != null && overlayTypes != ControlOverlays.OverlayTypes.NONE) {
            ControlOverlays controlOverlays = this.controlOverlays;
            int ordinal = overlayTypes.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal != 2) {
                        selectToSpeakOverlay = null;
                    } else {
                        selectToSpeakOverlay = controlOverlays.expandableOverlay;
                    }
                } else {
                    selectToSpeakOverlay = controlOverlays.collapsedOverlay;
                }
            } else {
                selectToSpeakOverlay = controlOverlays.triggerButtonOverlay;
            }
            if (selectToSpeakOverlay != null) {
                selectToSpeakOverlay.show();
                UIDelayedJobScheduler uIDelayedJobScheduler = this.uiDelayedJobScheduler;
                Job job = uIDelayedJobScheduler.hideAllExceptJob;
                if (job != null) {
                    job.cancel(null);
                }
                uIDelayedJobScheduler.hideAllExceptJob = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(uIDelayedJobScheduler.scope, null, 0, new S2sHatsSurveyRequester$requestSurveyData$1(uIDelayedJobScheduler, selectToSpeakOverlay, (Continuation) null, 2), 3);
            }
            this.controlOverlays.setForegroundOverlayType(overlayTypes);
            return;
        }
        this.controlOverlays.hideAll();
        this.controlOverlays.setForegroundOverlayType(ControlOverlays.OverlayTypes.NONE);
    }

    public final void shutdown() {
        clearAnimations();
        this.workingBoardOverlay.hide();
        this.controlOverlays.hideAll();
    }

    public final void updateTriggerButtonAppearance(boolean z) {
        int i;
        int i2;
        int i3;
        this.isTriggerButtonAppearanceActive = z;
        if (z) {
            i = R.color.stop_button_background;
        } else {
            i = R.color.control_panel_background;
        }
        if (true != z) {
            i2 = R.drawable.ic_chat_bubble_white_24dp;
        } else {
            i2 = R.drawable.ic_stop_white_24dp;
        }
        if (true != z) {
            i3 = R.string.floating_button_deactivated_contentDesc;
        } else {
            i3 = R.string.floating_button_activated_contentDesc;
        }
        for (ImageButton imageButton : this.controlOverlays._triggerButtons) {
            imageButton.setBackgroundTintList(ColorStateList.valueOf(this.service.getColor(i)));
            imageButton.setContentDescription(this.service.getString(i3));
            imageButton.setImageDrawable(this.service.getDrawable(i2));
        }
    }
}
