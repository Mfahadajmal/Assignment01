package com.google.android.accessibility.talkback;

import android.content.Context;
import android.os.Message;
import android.os.SystemClock;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.actor.FocusActor;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.PassThroughModeActor;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchActor;
import com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognitionDialog;
import com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.dynamicfeature.IconDetectionModuleDownloadPrompter;
import com.google.android.accessibility.talkback.eventprocessor.AccessibilityEventProcessor;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForScreenStateChange;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForTapAndTouchExploration;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadStateListenerResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$ImageCaptionPreferenceKeys;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.accessibility.talkback.interpreters.AutoScrollInterpreter;
import com.google.android.accessibility.talkback.interpreters.DirectionNavigationInterpreter;
import com.google.android.accessibility.talkback.interpreters.FullScreenReadInterpreter;
import com.google.android.accessibility.talkback.interpreters.HintEventInterpreter;
import com.google.android.accessibility.talkback.interpreters.PassThroughModeInterpreter;
import com.google.android.accessibility.talkback.interpreters.StateChangeEventInterpreter;
import com.google.android.accessibility.talkback.interpreters.SubtreeChangeEventInterpreter;
import com.google.android.accessibility.talkback.utils.DiagnosticOverlayControllerImpl;
import com.google.android.accessibility.talkback.utils.HighlightOverlay;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.common.collect.ImmutableList;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Pipeline implements AccessibilityEventListener, AccessibilityEventProcessor.AccessibilityEventIdleListener {
    public final Actors actors;
    private final Verifier compositor$ar$class_merging$e4d5cfcc_0;
    private final Context context;
    private final DiagnosticOverlayControllerImpl diagnosticOverlayController;
    private final FloatingActionButton.ShadowDelegateImpl eventReceiver$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FeedbackDelayer feedbackDelayer;
    public final FeedbackReturner feedbackReturner;
    public final HapticPatternParser$$ExternalSyntheticLambda1 interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Interpreters interpreters;
    public final SearchScreenNodeStrategy mappers$ar$class_merging$ar$class_merging;
    final HashMap messageIdToDelayedFeedback;
    private final Monitors monitors;
    public final FloatingActionButton.ShadowDelegateImpl proximityChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 speaker$ar$class_merging$ar$class_merging$ar$class_merging;
    public final WindowTrackerFactory speechObserver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ApplicationModule userInterface$ar$class_merging$ar$class_merging$ar$class_merging;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackDelayer extends WeakReferenceHandler {
        private final Actors actors;

        public FeedbackDelayer(Pipeline pipeline, Actors actors) {
            super(pipeline);
            this.actors = actors;
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) message.obj;
            Feedback.Part part = (Feedback.Part) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
            this.actors.act((Performance.EventId) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider, part);
            if (getParent() != null) {
                Pipeline pipeline = (Pipeline) getParent();
                int i = message.what;
                List list = (List) pipeline.messageIdToDelayedFeedback.get(Integer.valueOf(i));
                if (list != null) {
                    int size = list.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        } else if (part == list.get(size)) {
                            list.remove(size);
                        }
                    }
                    if (list.isEmpty()) {
                        pipeline.messageIdToDelayedFeedback.remove(Integer.valueOf(i));
                    }
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FeedbackReturner {
        boolean returnFeedback(Feedback feedback);

        void returnFeedback$ar$ds(Performance.EventId eventId, Feedback.Focus.Builder builder);

        void returnFeedback$ar$ds$1fdc2aa8_0(Performance.EventId eventId, Feedback.Part.Builder builder);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SyntheticEvent {
        public final CharSequence eventText;
        public final int eventType$ar$edu;
        public final long uptimeMs;

        public SyntheticEvent(int i) {
            this.eventType$ar$edu = 1;
            this.eventText = null;
            this.uptimeMs = SystemClock.uptimeMillis();
        }

        public final String toString() {
            String str;
            if (this.eventType$ar$edu != 1) {
                str = "TEXT_TRAVERSAL";
            } else {
                str = "SCROLL_TIMEOUT";
            }
            return String.format("type=%s, text=%s, time=%d", str, this.eventText, Long.valueOf(this.uptimeMs));
        }

        public SyntheticEvent(int i, CharSequence charSequence) {
            this.eventType$ar$edu = 2;
            this.eventText = charSequence;
            this.uptimeMs = SystemClock.uptimeMillis();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x001c, code lost:
    
        if (r6.autoScrolledTime <= r5.handledAutoScrollUptimeMs) goto L8;
     */
    /* renamed from: -$$Nest$minputEvent$ar$ds, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* bridge */ /* synthetic */ void m94$$Nest$minputEvent$ar$ds(com.google.android.accessibility.talkback.Pipeline r5, com.google.android.accessibility.talkback.Pipeline.SyntheticEvent r6) {
        /*
            int r0 = r6.eventType$ar$edu
            com.google.android.accessibility.talkback.Interpreters r5 = r5.interpreters
            r1 = 1
            r2 = 0
            if (r0 != r1) goto L50
            com.google.android.accessibility.talkback.interpreters.AutoScrollInterpreter r5 = r5.autoScrollInterpreter
            com.google.android.accessibility.talkback.ActorState r6 = r5.actorState
            com.google.android.accessibility.talkback.actor.AutoScrollActor$StateReader r6 = r6.getScrollerState()
            com.google.android.accessibility.talkback.actor.AutoScrollActor r6 = com.google.android.accessibility.talkback.actor.AutoScrollActor.this
            com.google.android.accessibility.utils.output.ScrollActionRecord r6 = r6.failedScrollActionRecord
            if (r6 == 0) goto L1e
            long r0 = r5.handledAutoScrollUptimeMs
            long r3 = r6.autoScrolledTime
            int r0 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r0 > 0) goto L1f
        L1e:
            r6 = r2
        L1f:
            if (r6 != 0) goto L22
            goto L87
        L22:
            long r0 = r6.autoScrolledTime
            r5.handledAutoScrollUptimeMs = r0
            r6.refresh()
            java.lang.String r0 = r6.scrollSource
            java.lang.String r1 = "FOCUS"
            if (r0 != r1) goto L3a
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r1 = r6.scrolledNodeCompat
            if (r1 != 0) goto L34
            goto L3a
        L34:
            com.google.android.accessibility.talkback.actor.DirectionNavigationActor r5 = r5.directionNavigationActor
            r5.onAutoScrollFailed(r1)
            return
        L3a:
            java.lang.String r1 = "SEARCH"
            if (r0 != r1) goto L87
            com.google.android.accessibility.utils.AccessibilityNode r6 = r6.scrolledNode
            if (r6 == 0) goto L87
            com.google.android.accessibility.talkback.actor.search.UniversalSearchActor r5 = r5.universalSearchActor
            com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay r5 = r5.searchScreenOverlay
            com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay$5 r6 = r5.scrollCallback$ar$class_merging
            if (r6 == 0) goto L87
            r6.onAutoScrollFailed$ar$ds()
            r5.scrollCallback$ar$class_merging = r2
            return
        L50:
            int r0 = r6.eventType$ar$edu
            r1 = 2
            if (r0 != r1) goto L87
            j$.util.Optional r0 = r5.pipelineInterpretations
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L87
            com.google.android.accessibility.talkback.compositor.EventInterpretation r0 = new com.google.android.accessibility.talkback.compositor.EventInterpretation
            r1 = 1073741842(0x40000012, float:2.0000043)
            r0.<init>(r1)
            com.google.android.accessibility.utils.input.TextEventInterpretation r3 = new com.google.android.accessibility.utils.input.TextEventInterpretation
            r3.<init>(r1)
            java.lang.CharSequence r6 = r6.eventText
            r3.setTraversedText(r6)
            r0.setTextEventInterpretation(r3)
            r0.setReadOnly()
            j$.util.Optional r5 = r5.pipelineInterpretations
            java.lang.Object r5 = r5.get()
            com.google.android.accessibility.talkback.Interpretation$CompositorID r6 = new com.google.android.accessibility.talkback.Interpretation$CompositorID
            int r1 = r0.mEvent
            r6.<init>(r1, r0)
            com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r5 = (com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1) r5
            r5.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(r2, r2, r6, r2)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.Pipeline.m94$$Nest$minputEvent$ar$ds(com.google.android.accessibility.talkback.Pipeline, com.google.android.accessibility.talkback.Pipeline$SyntheticEvent):void");
    }

    public Pipeline(Context context, Monitors monitors, Interpreters interpreters, SearchScreenNodeStrategy searchScreenNodeStrategy, Actors actors, TalkBackService.ProximitySensorListener proximitySensorListener, SpeechController speechController, DiagnosticOverlayControllerImpl diagnosticOverlayControllerImpl, Verifier verifier, ApplicationModule applicationModule) {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this);
        this.eventReceiver$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        final HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(this);
        this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        FeedbackReturner feedbackReturner = new FeedbackReturner() { // from class: com.google.android.accessibility.talkback.Pipeline$$ExternalSyntheticLambda1
            @Override // com.google.android.accessibility.talkback.Pipeline.FeedbackReturner
            public final boolean returnFeedback(Feedback feedback) {
                return Pipeline.this.execute(feedback);
            }

            @Override // com.google.android.accessibility.talkback.Pipeline.FeedbackReturner
            public final /* synthetic */ void returnFeedback$ar$ds(Performance.EventId eventId, Feedback.Focus.Builder builder) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this, (Performance.EventId) null, builder);
            }

            @Override // com.google.android.accessibility.talkback.Pipeline.FeedbackReturner
            public final /* synthetic */ void returnFeedback$ar$ds$1fdc2aa8_0(Performance.EventId eventId, Feedback.Part.Builder builder) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this, eventId, builder);
            }
        };
        this.feedbackReturner = feedbackReturner;
        this.speaker$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.messageIdToDelayedFeedback = new HashMap();
        this.proximityChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        this.context = context;
        this.monitors = monitors;
        this.interpreters = interpreters;
        this.mappers$ar$class_merging$ar$class_merging = searchScreenNodeStrategy;
        this.actors = actors;
        this.diagnosticOverlayController = diagnosticOverlayControllerImpl;
        this.compositor$ar$class_merging$e4d5cfcc_0 = verifier;
        this.userInterface$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        monitors.batteryMonitor.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        interpreters.pipelineInterpretations = Optional.of(hapticPatternParser$$ExternalSyntheticLambda1);
        interpreters.scrollEventInterpreter.addListener(new ScrollEventInterpreter.ScrollEventHandler() { // from class: com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda2
            @Override // com.google.android.accessibility.utils.input.ScrollEventInterpreter.ScrollEventHandler
            public final void onScrollEvent(AccessibilityEvent accessibilityEvent, ScrollEventInterpreter.ScrollEventInterpretation scrollEventInterpretation, Performance.EventId eventId) {
                HapticPatternParser$$ExternalSyntheticLambda1.this.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$Scroll(scrollEventInterpretation), null);
            }
        });
        AutoScrollInterpreter autoScrollInterpreter = interpreters.autoScrollInterpreter;
        autoScrollInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        interpreters.scrollPositionInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        AccessibilityFocusInterpreter accessibilityFocusInterpreter = interpreters.accessibilityFocusInterpreter;
        accessibilityFocusInterpreter.pipelineInterpretations$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration = accessibilityFocusInterpreter.focusProcessorForTapAndTouchExploration;
        focusProcessorForTapAndTouchExploration.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        FocusProcessorForScreenStateChange focusProcessorForScreenStateChange = accessibilityFocusInterpreter.focusProcessorForScreenStateChange;
        focusProcessorForScreenStateChange.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        focusProcessorForTapAndTouchExploration.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        FullScreenReadInterpreter fullScreenReadInterpreter = interpreters.continuousReadInterpreter;
        fullScreenReadInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        StateChangeEventInterpreter stateChangeEventInterpreter = interpreters.stateChangeEventInterpreter;
        stateChangeEventInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        DirectionNavigationInterpreter directionNavigationInterpreter = interpreters.directionNavigationInterpreter;
        directionNavigationInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        HintEventInterpreter hintEventInterpreter = interpreters.hintEventInterpreter;
        hintEventInterpreter.pipelineInterpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        interpreters.voiceCommandProcessor.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        PassThroughModeInterpreter passThroughModeInterpreter = interpreters.passThroughModeInterpreter;
        passThroughModeInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        SubtreeChangeEventInterpreter subtreeChangeEventInterpreter = interpreters.subtreeChangeEventInterpreter;
        subtreeChangeEventInterpreter.subtreeChangedHandler.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        interpreters.accessibilityEventIdleInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        interpreters.uiChangeEventInterpreter.pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        ActorState state = actors.getState();
        interpreters.inputFocusInterpreter.actorState = state;
        interpreters.scrollEventInterpreter.scrollActorState = state.getScrollerState();
        interpreters.manualScrollInterpreter.actorState = state;
        autoScrollInterpreter.actorState = state;
        accessibilityFocusInterpreter.actorState = state;
        focusProcessorForTapAndTouchExploration.actorState = state;
        focusProcessorForScreenStateChange.actorState = state;
        fullScreenReadInterpreter.actorState = state;
        stateChangeEventInterpreter.actorState = state;
        directionNavigationInterpreter.actorState = state;
        hintEventInterpreter.actorState = state;
        passThroughModeInterpreter.actorState = state;
        subtreeChangeEventInterpreter.scrollActorState = state.getScrollerState();
        searchScreenNodeStrategy.SearchScreenNodeStrategy$ar$lastKeyword = monitors.state$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        actors.scroller.pipelineReceiver$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        ((CursorGranularityManager) actors.directionNavigator.DirectionNavigationActor$ar$cursorGranularityManager).granularityTraversal$ar$class_merging.SnackbarManager$ar$currentSnackbar = Optional.of(shadowDelegateImpl);
        actors.scroller.feedbackReturner = feedbackReturner;
        actors.dimmer.pipeline = feedbackReturner;
        FullScreenReadActor fullScreenReadActor = actors.continuousReader;
        fullScreenReadActor.pipeline = feedbackReturner;
        fullScreenReadActor.fullScreenReadDialog.pipeline = feedbackReturner;
        DirectionNavigationActor directionNavigationActor = actors.directionNavigator;
        directionNavigationActor.DirectionNavigationActor$ar$pipeline = feedbackReturner;
        ((FocusProcessorForLogicalNavigation) directionNavigationActor.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation).pipeline = feedbackReturner;
        CursorGranularityManager cursorGranularityManager = (CursorGranularityManager) directionNavigationActor.DirectionNavigationActor$ar$cursorGranularityManager;
        SnackbarManager snackbarManager = cursorGranularityManager.granularityTraversal$ar$class_merging;
        ((ProcessorPhoneticLetters) snackbarManager.SnackbarManager$ar$handler).pipeline = feedbackReturner;
        snackbarManager.SnackbarManager$ar$nextSnackbar = Optional.of(feedbackReturner);
        cursorGranularityManager.pipeline = feedbackReturner;
        actors.editor.pipeline = feedbackReturner;
        FocusActor focusActor = actors.focuser;
        focusActor.pipeline = feedbackReturner;
        focusActor.focusManagerInternal.pipeline = feedbackReturner;
        focusActor.webActor$ar$class_merging.pipeline = feedbackReturner;
        actors.focuserWindowChange.pipeline = feedbackReturner;
        actors.languageSwitcher.pipeline = feedbackReturner;
        PassThroughModeActor passThroughModeActor = actors.passThroughModeActor;
        if (passThroughModeActor != null) {
            passThroughModeActor.pipeline = feedbackReturner;
            passThroughModeActor.passThroughDialog.pipeline = feedbackReturner;
        }
        actors.focuserTouch.pipeline = feedbackReturner;
        actors.numberAdjustor.pipeline = feedbackReturner;
        actors.typoNavigator$ar$class_merging.SearchScreenNodeStrategy$ar$lastKeyword = feedbackReturner;
        SpeechRecognizerActor speechRecognizerActor = actors.speechRecognizer;
        speechRecognizerActor.pipeline = feedbackReturner;
        speechRecognizerActor.speechRecognitionDialog = new SpeechRecognitionDialog(speechRecognizerActor.talkbackContext, feedbackReturner);
        ImageCaptioner imageCaptioner = actors.imageCaptioner;
        imageCaptioner.pipeline = feedbackReturner;
        IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter = imageCaptioner.iconDetectionModuleDownloadPrompter;
        iconDetectionModuleDownloadPrompter.pipeline = feedbackReturner;
        imageCaptioner.imageDescriptionModuleDownloadPrompter.pipeline = feedbackReturner;
        iconDetectionModuleDownloadPrompter.downloadStateListener = new ImageCaptioner.ManualDownloadStateListener(imageCaptioner.service, feedbackReturner, imageCaptioner.analytics, imageCaptioner, ImageCaptionConstants$DownloadStateListenerResources.ICON_DETECTION, ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION, ImageCaptionConstants$DownloadDialogResources.ICON_DETECTION.moduleSizeInMb, TalkBackAnalytics.ImageCaptionLogKeys.ICON_DETECTION);
        imageCaptioner.imageDescriptionModuleDownloadPrompter.downloadStateListener = new ImageCaptioner.ManualDownloadStateListener(imageCaptioner.service, feedbackReturner, imageCaptioner.analytics, imageCaptioner, ImageCaptionConstants$DownloadStateListenerResources.IMAGE_DESCRIPTION, ImageCaptionConstants$ImageCaptionPreferenceKeys.IMAGE_DESCRIPTION, ImageCaptionConstants$DownloadDialogResources.IMAGE_DESCRIPTION.moduleSizeInMb, TalkBackAnalytics.ImageCaptionLogKeys.IMAGE_DESCRIPTION);
        UniversalSearchActor universalSearchActor = actors.universalSearchActor;
        universalSearchActor.pipeline = feedbackReturner;
        universalSearchActor.searchScreenOverlay.pipeline = feedbackReturner;
        actors.geminiActor.pipeline = feedbackReturner;
        ((CursorGranularityManager) actors.directionNavigator.DirectionNavigationActor$ar$cursorGranularityManager).userInterface$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        this.feedbackDelayer = new FeedbackDelayer(this, actors);
        this.speechObserver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory(proximitySensorListener, speechController);
    }

    protected static int toMessageId(int i, int i2) {
        return (i * 10) + i2;
    }

    public final void cancelAllDelays() {
        this.feedbackDelayer.removeCallbacksAndMessages(null);
        this.messageIdToDelayedFeedback.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean execute(Feedback feedback) {
        Feedback.Part part;
        Performance.EventId eventId;
        String str;
        LogUtils.d("Pipeline", "execute() feedback=%s", feedback);
        int i = 0;
        while (true) {
            ImmutableList immutableList = feedback.failovers;
            if (i >= immutableList.size()) {
                return false;
            }
            Feedback.Part part2 = (Feedback.Part) immutableList.get(i);
            if (part2.speech() != null && part2.speech().hintSpeakOptions != null && part2.speech().hint != null) {
                Context context = this.context;
                if (SpannableUtils$IdentifierSpan.getPreferenceValueBool(SpannableUtils$IdentifierSpan.getSharedPreferences(context), context.getResources(), context.getString(R.string.pref_a11y_hints_key), context.getResources().getBoolean(R.bool.pref_a11y_hints_default))) {
                    final CharSequence charSequence = part2.speech().hint;
                    final int i2 = part2.speech().hintSpeakOptions.mFlags;
                    part2.speech();
                    final int i3 = part2.speech().hintInterruptLevel;
                    part2.speech().hintSpeakOptions.mCompletedAction = new SpeechController.UtteranceCompleteRunnable() { // from class: com.google.android.accessibility.talkback.Pipeline$$ExternalSyntheticLambda2
                        @Override // com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable
                        public final void run(int i4) {
                            if (i4 != 4 && i4 != 3) {
                                return;
                            }
                            int i5 = i2;
                            CharSequence charSequence2 = charSequence;
                            int i6 = i3;
                            Pipeline pipeline = Pipeline.this;
                            Logger logger = Performance.DEFAULT_LOGGER;
                            Feedback.Part.Builder builder = Feedback.Part.builder();
                            builder.setDelayMs$ar$ds(400);
                            builder.setInterruptGroup$ar$ds(0);
                            builder.setInterruptLevel$ar$ds(i6);
                            builder.senderName = "Pipeline";
                            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                            speakOptions.mQueueMode = 0;
                            speakOptions.mFlags = i5;
                            speakOptions.mUtteranceGroup = 0;
                            pipeline.execute(Feedback.create((Performance.EventId) null, builder.speech(charSequence2, speakOptions).build()));
                        }
                    };
                }
            }
            if (part2.interruptGroup() != -1) {
                int interruptGroup = part2.interruptGroup();
                int interruptLevel = part2.interruptLevel();
                String senderName = part2.senderName();
                for (int i4 = 0; i4 <= interruptLevel; i4++) {
                    this.feedbackDelayer.removeMessages(toMessageId(interruptGroup, i4));
                    int messageId = toMessageId(interruptGroup, i4);
                    List<Feedback.Part> list = (List) this.messageIdToDelayedFeedback.remove(Integer.valueOf(messageId));
                    if (list != null) {
                        for (Feedback.Part part3 : list) {
                            if (senderName != null) {
                                String senderName2 = part3.senderName();
                                int i5 = messageId / 10;
                                if (i5 != 0) {
                                    if (i5 != 1) {
                                        str = "(unknown)";
                                    } else {
                                        str = "GESTURE_VIBRATION";
                                    }
                                } else {
                                    str = "HINT";
                                }
                                LogUtils.v("Pipeline", "Feedback Interrupt: source %s is interrupted by source %s because in the Group %s.", senderName2, senderName, str);
                            }
                        }
                    }
                }
                Actors actors = this.actors;
                int interruptGroup2 = part2.interruptGroup();
                int interruptLevel2 = part2.interruptLevel();
                if (interruptGroup2 == 0 && interruptLevel2 >= 2) {
                    Iterator it = actors.speaker.mUtteranceCompleteActions.iterator();
                    while (it.hasNext()) {
                        if (((SpeechControllerImpl.UtteranceCompleteAction) it.next()).utteranceGroup == 6) {
                            it.remove();
                        }
                    }
                }
            }
            if (part2.interruptSoundAndVibration()) {
                this.actors.interruptSoundAndVibration();
            }
            if (part2.interruptGentle()) {
                Actors actors2 = this.actors;
                if (SpannableUtils$IdentifierSpan.getRole(actors2.accessibilityFocusMonitor.getAccessibilityFocus(false)) != 15) {
                    if (actors2.actorState.continuousRead$ar$class_merging$ar$class_merging.isActive()) {
                        actors2.interruptSoundAndVibration();
                    } else {
                        actors2.interruptAllFeedback$ar$ds();
                    }
                }
            }
            if (this.diagnosticOverlayController.isHighlightOverlayEnabled()) {
                if (feedback.failovers.size() <= 0) {
                    part = null;
                } else {
                    part = (Feedback.Part) feedback.failovers.get(0);
                }
                if (part != null && (eventId = feedback.eventId) != null && (part.focus() != null || part.focusDirection() != null || part.scroll() != null)) {
                    int i6 = eventId.eventSubtype;
                    if (i6 == 4194304 || i6 == 32 || part.scroll() != null) {
                        this.diagnosticOverlayController.clearHighlight();
                    }
                    if (part.focus() != null) {
                        DiagnosticOverlayControllerImpl diagnosticOverlayControllerImpl = this.diagnosticOverlayController;
                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = part.focus().target;
                        if (diagnosticOverlayControllerImpl.isHighlightOverlayEnabled() && accessibilityNodeInfoCompat != null) {
                            DiagnosticOverlayControllerImpl.traversedIdToNode.remove(Integer.valueOf(accessibilityNodeInfoCompat.hashCode()));
                            DiagnosticOverlayControllerImpl.unfocusedIdToNode.remove(Integer.valueOf(accessibilityNodeInfoCompat.hashCode()));
                            HighlightOverlay highlightOverlay = diagnosticOverlayControllerImpl.highlightOverlay;
                            HashMap hashMap = DiagnosticOverlayControllerImpl.unfocusedIdToNode;
                            HashSet hashSet = DiagnosticOverlayControllerImpl.refocusNodePath;
                            HighlightOverlay.highlightView.setVisibility(0);
                            highlightOverlay.show();
                            highlightOverlay.skippedNodes = hashMap;
                            highlightOverlay.refocusNodePath = hashSet;
                            HighlightOverlay.highlightView.invalidate();
                            DiagnosticOverlayControllerImpl.unfocusedIdToNode = new HashMap();
                            DiagnosticOverlayControllerImpl.refocusNodePath = new HashSet();
                            DiagnosticOverlayControllerImpl.traversedIdToNode.clear();
                        }
                    } else if (part.focusDirection() != null) {
                        this.diagnosticOverlayController.clearHighlight();
                    }
                }
            }
            if (part2.delayMs() <= 0) {
                boolean act = this.actors.act(feedback.eventId, part2);
                LogUtils.v("Pipeline", "execute() success=%s for part=%s", Boolean.valueOf(act), part2);
                if (act) {
                    break;
                }
                i++;
            } else {
                Performance.EventId eventId2 = feedback.eventId;
                int messageId2 = toMessageId(part2.interruptGroup(), part2.interruptLevel());
                this.feedbackDelayer.sendMessageDelayed(this.feedbackDelayer.obtainMessage(messageId2, new WindowTrackerFactory((Object) part2, eventId2)), part2.delayMs());
                HashMap hashMap2 = this.messageIdToDelayedFeedback;
                Integer valueOf = Integer.valueOf(messageId2);
                List list2 = (List) hashMap2.get(valueOf);
                if (list2 == null) {
                    list2 = new ArrayList();
                    this.messageIdToDelayedFeedback.put(valueOf, list2);
                }
                list2.add(part2);
            }
        }
        return true;
    }

    public final ActorState getActorState() {
        return this.actors.getState();
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return this.interpreters.eventTypeMask | this.monitors.eventTypeMask;
    }

    public final void interruptAllFeedback$ar$ds$404beace_0() {
        cancelAllDelays();
        this.actors.interruptAllFeedback$ar$ds();
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x01fb, code lost:
    
        if (r6.state == 4) goto L139;
     */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:177:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d3  */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r20, com.google.android.accessibility.utils.Performance.EventId r21) {
        /*
            Method dump skipped, instructions count: 660
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.Pipeline.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent, com.google.android.accessibility.utils.Performance$EventId):void");
    }

    public final void onBoot(boolean z) {
        SpeechControllerImpl speechControllerImpl = this.actors.speaker;
        speechControllerImpl.mSkipNextTTSChangeAnnouncement = z;
        speechControllerImpl.mFailoverTts.updateDefaultEngine();
    }

    public final void onUnbind(float f, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable) {
        cancelAllDelays();
        Actors actors = this.actors;
        SpeechControllerImpl speechControllerImpl = actors.speaker;
        speechControllerImpl.mShouldHandleTtsCallBackInMainThread = false;
        speechControllerImpl.mFailoverTts.mShouldHandleTtsCallbackInMainThread = false;
        speechControllerImpl.setOverlayEnabled(false);
        actors.speaker.mSpeechVolume = f;
        Logger logger = Performance.DEFAULT_LOGGER;
        Compositor$HandleEventOptions compositor$HandleEventOptions = new Compositor$HandleEventOptions();
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$onCompleteRunnable = utteranceCompleteRunnable;
        this.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741928, (Performance.EventId) null, compositor$HandleEventOptions);
        Actors actors2 = this.actors;
        actors2.speaker.isMuteSpeech = true;
        actors2.soundAndVibration.shutdown();
        AiCoreEndpoint aiCoreEndpoint = actors2.geminiActor.aiCoreEndpoint;
        if (aiCoreEndpoint != null) {
            aiCoreEndpoint.onUnbind();
        }
    }
}
