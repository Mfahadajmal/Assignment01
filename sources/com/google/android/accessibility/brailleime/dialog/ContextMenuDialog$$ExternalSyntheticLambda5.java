package com.google.android.accessibility.brailleime.dialog;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Range;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda7;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.input.MultitouchHandler;
import com.google.android.accessibility.brailleime.input.MultitouchResult;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView$TypeLetterA$1;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIteratorFactory;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakJobModel;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint;
import com.google.android.accessibility.selecttospeak.tts.VoiceUtil;
import com.google.android.accessibility.selecttospeak.ui.HighlightScrollingTextView;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.stream.Collectors;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ContextMenuDialog$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ Object ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ ContextMenuDialog$$ExternalSyntheticLambda5(Object obj, int i) {
        this.switching_field = i;
        this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$Callback] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$Callback] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$Callback] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$Callback] */
    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        int i = 2;
        SpeechControllerImpl speechControllerImpl = null;
        int i2 = 1;
        switch (this.switching_field) {
            case 0:
                this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0.onTutorialClosed();
                return;
            case 1:
                this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0.onTutorialOpen();
                return;
            case 2:
                this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0.onLaunchSettings();
                return;
            case 3:
                this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0.onCalibration();
                return;
            case 4:
                _BOUNDARY.d("MultitouchHandler", "tap or swipe task is running.");
                MultitouchHandler multitouchHandler = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                Optional createSwipe = multitouchHandler.createSwipe(multitouchHandler.getLastRecentlyInactivatedPointsHistory(SystemClock.uptimeMillis()), ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getRecentlyInactivatedPointsHistory(SystemClock.uptimeMillis()));
                _BOUNDARY.d("MultitouchHandler", "swipe is present: " + createSwipe.isPresent());
                if (createSwipe.isPresent()) {
                    Object obj2 = this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                    Object obj3 = createSwipe.get();
                    List recentlyInactivatedInitialPoints = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getRecentlyInactivatedInitialPoints(SystemClock.uptimeMillis());
                    MultitouchResult multitouchResult = new MultitouchResult();
                    multitouchResult.type = 1;
                    multitouchResult.swipe = (Swipe) obj3;
                    multitouchResult.releasedPoints = recentlyInactivatedInitialPoints;
                    ((MultitouchHandler) obj2).multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(multitouchResult));
                } else {
                    double orElse = Collection.EL.stream(((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).inactivePointers.values()).mapToDouble(new NodePathDescription$$ExternalSyntheticLambda2(i2)).max().orElse(Double.MAX_VALUE);
                    MultitouchHandler multitouchHandler2 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                    if (orElse <= multitouchHandler2.tapMaxDistancePixels) {
                        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = multitouchHandler2.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        long uptimeMillis = SystemClock.uptimeMillis();
                        List list = (List) Collection.EL.stream(multitouchHandler2.inactivePointers.values()).filter(new Connectioneer$AspectConnection$$ExternalSyntheticLambda7(multitouchHandler2, new Range(Long.valueOf((-250) + uptimeMillis), Long.valueOf(uptimeMillis)), i)).map(new BtConnectManager$$ExternalSyntheticLambda1(17)).collect(Collectors.toList());
                        MultitouchResult multitouchResult2 = new MultitouchResult();
                        multitouchResult2.type = 0;
                        multitouchResult2.releasedPoints = list;
                        hapticPatternParser$$ExternalSyntheticLambda1.detect(Optional.of(multitouchResult2));
                    }
                }
                ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).clearPointerCollections();
                ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).handler.removeCallbacksAndMessages(null);
                return;
            case 5:
                _BOUNDARY.d("MultitouchHandler", "hold task is running.");
                MultitouchHandler multitouchHandler3 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12 = multitouchHandler3.holdRecognizer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (hapticPatternParser$$ExternalSyntheticLambda12 != null && hapticPatternParser$$ExternalSyntheticLambda12.isHoldRecognized(multitouchHandler3.getActivePoints().size())) {
                    Iterator it = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).activePointers.values().iterator();
                    while (it.hasNext()) {
                        ((MultitouchHandler.PointerWithHistory) it.next()).isHoldInProgress = true;
                    }
                    Optional lastRecentlyInactivatedPointsHistory = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getLastRecentlyInactivatedPointsHistory(SystemClock.uptimeMillis() - ViewConfiguration.getLongPressTimeout());
                    if (lastRecentlyInactivatedPointsHistory.isPresent() && !((MultitouchHandler.PointerWithHistory) lastRecentlyInactivatedPointsHistory.get()).isHoldInProgress) {
                        MultitouchHandler multitouchHandler4 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                        if (!multitouchHandler4.holdRecognizer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isHoldRecognized(multitouchHandler4.getActivePoints().size() + 1)) {
                            return;
                        }
                    }
                    MultitouchHandler multitouchHandler5 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                    List heldPoints = multitouchHandler5.getHeldPoints();
                    MultitouchResult multitouchResult3 = new MultitouchResult();
                    multitouchResult3.type = 2;
                    multitouchResult3.heldPoints = heldPoints;
                    multitouchHandler5.isProcessed = multitouchHandler5.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(multitouchResult3));
                    _BOUNDARY.d("MultitouchHandler", "hold result: " + ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).isProcessed);
                    return;
                }
                return;
            case 6:
                _BOUNDARY.d("MultitouchHandler", "long hold task is running.");
                MultitouchHandler multitouchHandler6 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda13 = multitouchHandler6.holdRecognizer$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (hapticPatternParser$$ExternalSyntheticLambda13 != null) {
                    int size = multitouchHandler6.getActivePoints().size();
                    BrailleInputView brailleInputView = (BrailleInputView) hapticPatternParser$$ExternalSyntheticLambda13.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
                    if (brailleInputView.callback.isCalibrationHoldRecognized(brailleInputView.inputPlane.inTwoStepCalibration(), size)) {
                        List activePoints = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getActivePoints();
                        MultitouchResult multitouchResult4 = new MultitouchResult();
                        multitouchResult4.type = 3;
                        multitouchResult4.heldPoints = activePoints;
                        MultitouchHandler multitouchHandler7 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                        multitouchHandler7.isProcessed = multitouchHandler7.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(multitouchResult4));
                        _BOUNDARY.d("MultitouchHandler", "long hold result: " + ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).isProcessed);
                        return;
                    }
                    return;
                }
                return;
            case 7:
                _BOUNDARY.d("MultitouchHandler", "hold and swipe is running.");
                List heldPoints2 = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getHeldPoints();
                if (!heldPoints2.isEmpty() && heldPoints2.size() == ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getActivePoints().size()) {
                    MultitouchHandler multitouchHandler8 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                    Optional createSwipe2 = multitouchHandler8.createSwipe(multitouchHandler8.getLastRecentlyInactivatedPointsHistory(SystemClock.uptimeMillis()), ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getRecentlyInactivatedPointsHistory(SystemClock.uptimeMillis()));
                    if (createSwipe2.isPresent()) {
                        obj = createSwipe2.get();
                    } else {
                        obj = false;
                    }
                    _BOUNDARY.d("MultitouchHandler", "swipe is present: ".concat(obj.toString()));
                    if (createSwipe2.isPresent()) {
                        List activePoints2 = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getActivePoints();
                        Object obj4 = createSwipe2.get();
                        List recentlyInactivatedInitialPoints2 = ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).getRecentlyInactivatedInitialPoints(SystemClock.uptimeMillis());
                        MultitouchResult multitouchResult5 = new MultitouchResult();
                        multitouchResult5.type = 4;
                        multitouchResult5.swipe = (Swipe) obj4;
                        multitouchResult5.heldPoints = activePoints2;
                        multitouchResult5.releasedPoints = recentlyInactivatedInitialPoints2;
                        MultitouchHandler multitouchHandler9 = (MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                        multitouchHandler9.isProcessed = multitouchHandler9.multitouchResultListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(multitouchResult5));
                        _BOUNDARY.d("MultitouchHandler", "hold and swipe result: " + ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).isProcessed);
                        ((MultitouchHandler) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).handler.removeCallbacksAndMessages(null);
                        return;
                    }
                    return;
                }
                return;
            case 8:
                ((RetryingNameResolver.ResolutionResultListener) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).onViewUpdated();
                return;
            case 9:
                ((View) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).invalidate();
                return;
            case 10:
                ((TutorialView) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).onUtteranceCompleted();
                return;
            case 11:
                ((TutorialView) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).state.onUtteranceCompleted();
                return;
            case 12:
                ((TutorialView.Intro) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).this$0.findViewById(R.id.tutorial_title).performAccessibilityAction(64, new Bundle());
                return;
            case 13:
                TutorialView$TypeLetterA$1 tutorialView$TypeLetterA$1 = (TutorialView$TypeLetterA$1) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                TutorialView tutorialView = ((TutorialView.SwipeDown3Fingers) tutorialView$TypeLetterA$1.TutorialView$TypeLetterA$1$ar$this$1).this$0;
                tutorialView.removeView(tutorialView.inputView);
                TutorialView tutorialView2 = ((TutorialView.SwipeDown3Fingers) tutorialView$TypeLetterA$1.TutorialView$TypeLetterA$1$ar$this$1).this$0;
                tutorialView2.addView(tutorialView2.inputView, 0);
                ((TutorialView.SwipeDown3Fingers) tutorialView$TypeLetterA$1.TutorialView$TypeLetterA$1$ar$this$1).this$0.dotsFlashingAnimationView.setVisibility(0);
                TutorialView tutorialView3 = ((TutorialView.SwipeDown3Fingers) tutorialView$TypeLetterA$1.TutorialView$TypeLetterA$1$ar$this$1).this$0;
                tutorialView3.tutorialAnimationView.startHintToastAnimation(tutorialView3.state.hintText());
                return;
            case 14:
                SelectToSpeakService selectToSpeakService = (SelectToSpeakService) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                selectToSpeakService.uIManager.drawingBoard.clear$ar$ds();
                if (selectToSpeakService.isScreenshotRequiredOnSelection()) {
                    selectToSpeakService.uIManager.drawingBoard.onScreenCaptureStart();
                }
                selectToSpeakService.uIManager.drawingBoard.requestSelection$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(selectToSpeakService.selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                return;
            case 15:
                ((SelectToSpeakService) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).uIManager.drawingBoard.onScreenCaptureDone();
                return;
            case 16:
                if (SystemClock.uptimeMillis() - ((SelectToSpeakService) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).lastTimeAccessibilityButtonClicked > 500) {
                    SelectToSpeakService.interrupt$ar$ds$762ae497_0();
                    return;
                }
                return;
            case 17:
                ((SelectToSpeakService) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).uIManager.drawingBoard.onScreenCaptureDone();
                return;
            case 18:
                final SelectToSpeakPopupActivity selectToSpeakPopupActivity = (SelectToSpeakPopupActivity) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                SelectToSpeakJobModel jobModel = selectToSpeakPopupActivity.getJobModel();
                Function2 function2 = new Function2() { // from class: com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity$createAndRunJob$1
                    @Override // kotlin.jvm.functions.Function2
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj5, Object obj6) {
                        SpeechController speechController = (SpeechController) obj5;
                        SelectToSpeakJob.JobUpdateListener jobUpdateListener = (SelectToSpeakJob.JobUpdateListener) obj6;
                        speechController.getClass();
                        jobUpdateListener.getClass();
                        Context applicationContext = SelectToSpeakPopupActivity.this.getApplicationContext();
                        applicationContext.getClass();
                        VoiceUtil.loadUserSavedVoice(speechController, applicationContext);
                        TextView textView = SelectToSpeakPopupActivity.this.getScrollView().textView;
                        textView.getClass();
                        SentenceIteratorFactory.CharacterCounter characterCounter = new SentenceIteratorFactory.CharacterCounter((char[]) null);
                        SentenceIterator sentenceIterator = new SentenceIterator(SentenceIteratorFactory.generateSentences$ar$ds(textView.getText(), characterCounter, new SharedSQLiteStatement$stmt$2(textView, 10)), characterCounter.characterCount);
                        SelectToSpeakPopupActivity.this.getSelectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging().recordWordCount$ar$edu(A11yS2SProtoEnums$A11yS2SEntryPoint.ENTRY_CONTEXTUAL_POPUP$ar$edu, sentenceIterator.characterCount);
                        return new SelectToSpeakJob(SelectToSpeakPopupActivity.this.getApplicationContext(), SelectToSpeakPopupActivity.this.getHighlightViewModel().board, speechController, sentenceIterator, jobUpdateListener, null);
                    }
                };
                SpeechControllerImpl speechControllerImpl2 = jobModel.speechController;
                if (speechControllerImpl2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("speechController");
                } else {
                    speechControllerImpl = speechControllerImpl2;
                }
                jobModel.job = (SelectToSpeakJob) function2.invoke(speechControllerImpl, jobModel.jobUpdateListener);
                SelectToSpeakJob selectToSpeakJob = jobModel.job;
                if (selectToSpeakJob != null) {
                    selectToSpeakJob.activateMultitasking();
                }
                SelectToSpeakJob selectToSpeakJob2 = jobModel.job;
                if (selectToSpeakJob2 != null) {
                    selectToSpeakJob2.run();
                    return;
                }
                return;
            case 19:
                HighlightScrollingTextView highlightScrollingTextView = (HighlightScrollingTextView) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0;
                int measuredHeight = highlightScrollingTextView.textView.getMeasuredHeight();
                ViewGroup.LayoutParams layoutParams = highlightScrollingTextView.getLayoutParams();
                if (measuredHeight >= highlightScrollingTextView.maxHeightPx) {
                    highlightScrollingTextView.getShadowBottom().setVisibility(0);
                    highlightScrollingTextView.getShadowTop().setVisibility(0);
                    layoutParams.height = highlightScrollingTextView.maxHeightPx;
                } else {
                    highlightScrollingTextView.getShadowBottom().setVisibility(8);
                    highlightScrollingTextView.getShadowTop().setVisibility(8);
                    layoutParams.height = measuredHeight + highlightScrollingTextView.getPaddingTop();
                }
                highlightScrollingTextView.setLayoutParams(layoutParams);
                highlightScrollingTextView.invalidate();
                return;
            default:
                ((View) this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0).performHapticFeedback(0);
                return;
        }
    }

    public ContextMenuDialog$$ExternalSyntheticLambda5(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.ContextMenuDialog$$ExternalSyntheticLambda5$ar$f$0 = obj;
    }
}
