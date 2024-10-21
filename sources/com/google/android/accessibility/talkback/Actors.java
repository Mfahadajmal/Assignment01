package com.google.android.accessibility.talkback;

import android.content.Context;
import com.google.android.accessibility.talkback.actor.AutoScrollActor;
import com.google.android.accessibility.talkback.actor.DimScreenActor;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.actor.FocusActor;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange;
import com.google.android.accessibility.talkback.actor.FocusActorForTapAndTouchExploration;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.LanguageActor;
import com.google.android.accessibility.talkback.actor.NodeActionPerformer;
import com.google.android.accessibility.talkback.actor.NumberAdjustor;
import com.google.android.accessibility.talkback.actor.PassThroughModeActor;
import com.google.android.accessibility.talkback.actor.SystemActionPerformer;
import com.google.android.accessibility.talkback.actor.TextEditActor;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.actor.search.SearchScreenNodeStrategy;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchActor;
import com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForLogicalNavigation;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.FeedbackController;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.util.concurrent.ExecutionList;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.OutboundFlowController;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class Actors {
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public final ActorStateWritable actorState;
    private final TalkBackAnalytics analytics;
    private final RetryingNameResolver.ResolutionResultListener brailleDisplayActor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    public final FullScreenReadActor continuousReader;
    public final DimScreenActor dimmer;
    public final DirectionNavigationActor directionNavigator;
    public final TextEditActor editor;
    public final FocusActor focuser;
    public final FocusActorForTapAndTouchExploration focuserTouch;
    public final FocusActorForScreenStateChange focuserWindowChange;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    public final GeminiActor geminiActor;
    private final SpannableUtils$NonCopyableTextSpan gestureReporter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ImageCaptioner imageCaptioner;
    private final TalkBackLabelManager labeler;
    public final LanguageActor languageSwitcher;
    private final NodeActionPerformer nodeActionPerformer;
    public final NumberAdjustor numberAdjustor;
    public final PassThroughModeActor passThroughModeActor;
    public final AutoScrollActor scroller;
    private final SearchScreenNodeStrategy searcher;
    private final FloatingActionButton.ShadowDelegateImpl serviceFlagRequester$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FeedbackController soundAndVibration;
    public final SpeechControllerImpl speaker;
    private final OutboundFlowController speechRateActor$ar$class_merging;
    public final SpeechRecognizerActor speechRecognizer;
    private final SystemActionPerformer systemActionPerformer;
    private final ExecutionList.RunnableExecutorPair talkBackUIActor$ar$class_merging$ar$class_merging;
    public final SearchScreenNodeStrategy typoNavigator$ar$class_merging;
    public final UniversalSearchActor universalSearchActor;
    private final WindowTrackerFactory volumeAdjustor$ar$class_merging$ar$class_merging$ar$class_merging;

    public Actors(Context context, TalkBackAnalytics talkBackAnalytics, AccessibilityFocusMonitor accessibilityFocusMonitor, DimScreenActor dimScreenActor, SpeechControllerImpl speechControllerImpl, FullScreenReadActor fullScreenReadActor, FeedbackController feedbackController, AutoScrollActor autoScrollActor, FocusActor focusActor, FocusActorForScreenStateChange focusActorForScreenStateChange, FocusActorForTapAndTouchExploration focusActorForTapAndTouchExploration, DirectionNavigationActor directionNavigationActor, SearchScreenNodeStrategy searchScreenNodeStrategy, TextEditActor textEditActor, TalkBackLabelManager talkBackLabelManager, NodeActionPerformer nodeActionPerformer, SystemActionPerformer systemActionPerformer, LanguageActor languageActor, PassThroughModeActor passThroughModeActor, ExecutionList.RunnableExecutorPair runnableExecutorPair, OutboundFlowController outboundFlowController, NumberAdjustor numberAdjustor, SearchScreenNodeStrategy searchScreenNodeStrategy2, WindowTrackerFactory windowTrackerFactory, SpeechRecognizerActor speechRecognizerActor, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, ImageCaptioner imageCaptioner, UniversalSearchActor universalSearchActor, GeminiActor geminiActor, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.context = context;
        this.analytics = talkBackAnalytics;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.dimmer = dimScreenActor;
        this.speaker = speechControllerImpl;
        this.continuousReader = fullScreenReadActor;
        this.soundAndVibration = feedbackController;
        this.scroller = autoScrollActor;
        this.focuser = focusActor;
        this.focuserWindowChange = focusActorForScreenStateChange;
        this.focuserTouch = focusActorForTapAndTouchExploration;
        this.directionNavigator = directionNavigationActor;
        this.searcher = searchScreenNodeStrategy;
        this.editor = textEditActor;
        this.labeler = talkBackLabelManager;
        this.nodeActionPerformer = nodeActionPerformer;
        this.systemActionPerformer = systemActionPerformer;
        this.languageSwitcher = languageActor;
        this.passThroughModeActor = passThroughModeActor;
        this.talkBackUIActor$ar$class_merging$ar$class_merging = runnableExecutorPair;
        this.speechRateActor$ar$class_merging = outboundFlowController;
        this.numberAdjustor = numberAdjustor;
        this.typoNavigator$ar$class_merging = searchScreenNodeStrategy2;
        this.volumeAdjustor$ar$class_merging$ar$class_merging$ar$class_merging = windowTrackerFactory;
        this.speechRecognizer = speechRecognizerActor;
        this.gestureReporter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.imageCaptioner = imageCaptioner;
        this.universalSearchActor = universalSearchActor;
        this.geminiActor = geminiActor;
        this.serviceFlagRequester$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.brailleDisplayActor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = dimScreenActor.state$ar$class_merging$17ade4d6_0$ar$class_merging;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = speechControllerImpl.state$ar$class_merging$b33be634_0$ar$class_merging$ar$class_merging$ar$class_merging;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12 = fullScreenReadActor.state$ar$class_merging$8dadd5e0_0$ar$class_merging;
        AutoScrollActor.StateReader stateReader = autoScrollActor.stateReader;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda13 = focusActor.history.reader$ar$class_merging$ar$class_merging;
        Object obj = directionNavigationActor.DirectionNavigationActor$ar$state;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda14 = (HapticPatternParser$$ExternalSyntheticLambda1) obj;
        ActorStateWritable actorStateWritable = new ActorStateWritable(hapticPatternParser$$ExternalSyntheticLambda1, shadowDelegateImpl2, hapticPatternParser$$ExternalSyntheticLambda12, stateReader, hapticPatternParser$$ExternalSyntheticLambda13, hapticPatternParser$$ExternalSyntheticLambda14, nodeActionPerformer.stateReader$ar$class_merging$1a9fae11_0$ar$class_merging$ar$class_merging, languageActor.state$ar$class_merging$2e40e015_0$ar$class_merging$ar$class_merging$ar$class_merging, (FloatingActionButton.ShadowDelegateImpl) outboundFlowController.OutboundFlowController$ar$frameWriter, passThroughModeActor.state$ar$class_merging$39712d2b_0$ar$class_merging$ar$class_merging, talkBackLabelManager.stateReader(), geminiActor.state$ar$class_merging$afb67bed_0$ar$class_merging$ar$class_merging);
        this.actorState = actorStateWritable;
        focusActor.actorState = actorStateWritable;
        focusActor.focusManagerInternal.actorState = actorStateWritable;
        systemActionPerformer.actorState = actorStateWritable;
        ActorState actorState = new ActorState(actorStateWritable);
        ((FocusProcessorForLogicalNavigation) directionNavigationActor.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation).actorState = actorState;
        focusActorForScreenStateChange.actorState = actorState;
        languageActor.actorState = actorState;
        focusActorForTapAndTouchExploration.actorState = actorState;
        imageCaptioner.actorState = actorState;
        ((TalkBackAnalyticsImpl) talkBackAnalytics).talkBackAnalyticsLogger.actorState = actorState;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:433:0x091e, code lost:
    
        if (r5 > r4) goto L455;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x035f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0445 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x04e2  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x051a  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x056c  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x059e  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x05e7  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x05f6  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x075f  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x07a8  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x07bb  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x0832  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0854  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x087a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x0952  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x0990  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x09a7  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01f5  */
    /* JADX WARN: Type inference failed for: r3v39, types: [java.lang.CharSequence, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean act(com.google.android.accessibility.utils.Performance.EventId r22, com.google.android.accessibility.talkback.Feedback.Part r23) {
        /*
            Method dump skipped, instructions count: 2636
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.Actors.act(com.google.android.accessibility.utils.Performance$EventId, com.google.android.accessibility.talkback.Feedback$Part):boolean");
    }

    public final ActorState getState() {
        return new ActorState(this.actorState);
    }

    public final void interruptAllFeedback$ar$ds() {
        this.speaker.interrupt(false);
        this.soundAndVibration.interrupt();
    }

    public final void interruptSoundAndVibration() {
        this.soundAndVibration.interrupt();
    }
}
