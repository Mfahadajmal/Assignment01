package com.google.android.accessibility.utils.output;

import _COROUTINE._BOUNDARY;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.media.AudioAttributes;
import android.media.AudioPlaybackConfiguration;
import android.support.v7.app.AppCompatDelegateImpl;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.EditorInfo;
import androidx.core.content.ContextCompat$Api21Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.common.translate.EditBuffer;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.input.BrailleDisplayImeStripView;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;
import com.google.android.accessibility.brailleime.input.BrailleInputPlaneResult;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.input.MultitouchResult;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.iterator.InSentenceOffset;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.talkback.CursorGranularityManager;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Interpretation$AccessibilityFocused;
import com.google.android.accessibility.talkback.Interpretation$TouchInteraction;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.VoiceActionMonitor;
import com.google.android.accessibility.talkback.actor.DimScreenActor;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.actor.FocusActor;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.dynamicfeature.Downloader;
import com.google.android.accessibility.talkback.dynamicfeature.SplitApkDownloader;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord;
import com.google.android.accessibility.talkback.labeling.CustomLabelManager;
import com.google.android.accessibility.talkback.labeling.LabelClientRequest;
import com.google.android.accessibility.talkback.labeling.LabelManagerPackageActivity;
import com.google.android.accessibility.talkback.speech.SpeakPasswordsManager;
import com.google.android.accessibility.talkback.trainingcommon.TrainingIpcClient$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.braille.BrailleUnicode;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import com.google.common.collect.ImmutableList;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import j$.time.Duration;
import j$.util.Collection;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class HapticPatternParser$$ExternalSyntheticLambda1 {
    public final /* synthetic */ Object HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;

    public /* synthetic */ HapticPatternParser$$ExternalSyntheticLambda1(Object obj) {
        this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }

    public final boolean areMainWindowsStable() {
        return ((ScreenStateMonitor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).areMainWindowsStable;
    }

    public final void commitHoldings() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        EditBuffer editBuffer = brailleIme.editBuffer;
        if (editBuffer != null) {
            editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    public final void commitHoldingsAndPerformEditorAction$ar$ds() {
        commitHoldings();
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.performEditorAction(brailleIme.getImeConnection$ar$class_merging$ar$class_merging().PhenotypeProcessReaper$ar$isKillable);
    }

    public final boolean commitHoldingsAndPerformEnterKeyAction() {
        commitHoldings();
        if (!((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getCurrentInputConnection().sendKeyEvent(new KeyEvent(0, 66))) {
            return false;
        }
        return ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getCurrentInputConnection().sendKeyEvent(new KeyEvent(1, 66));
    }

    public final boolean copySelectedText() {
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(49, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean cutSelectedText() {
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(48, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final void deleteBackward$ar$ds$81eb5611_0() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.deleteCharacterBackward$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
    }

    public final void deleteWordBackward$ar$ds$984ca5ff_0() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.deleteWord$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
    }

    public final boolean detect(Optional optional) {
        boolean z;
        boolean z2;
        int i;
        int i2;
        optional.isPresent();
        _BOUNDARY.d("BrailleInputPlane", "detect: ".concat(Integer.valueOf(((MultitouchResult) optional.get()).type).toString()));
        BrailleInputPlane brailleInputPlane = (BrailleInputPlane) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleInputPlane.currentlyPressedDots = brailleInputPlane.matchTouchToTargets(brailleInputPlane.multitouchHandler.getActivePoints());
        optional.isPresent();
        Object obj = this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        Object obj2 = optional.get();
        BrailleInputPlane brailleInputPlane2 = (BrailleInputPlane) obj;
        boolean z3 = false;
        if (brailleInputPlane2.twoStepCalibrationState$ar$edu != 1) {
            MultitouchResult multitouchResult = (MultitouchResult) obj2;
            if (multitouchResult.type != 3 || multitouchResult.heldPoints.size() != (brailleInputPlane2.options.brailleType$ar$edu >> 1)) {
                int i3 = brailleInputPlane2.calibrationFailCount;
                if (i3 < 3) {
                    brailleInputPlane2.calibrationFailCount = i3 + 1;
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (brailleInputPlane2.twoStepCalibrationState$ar$edu == 2) {
                        z3 = true;
                    }
                    ((BrailleInputView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).callback.onTwoStepCalibrationRetry(z3);
                } else {
                    brailleInputPlane2.dotTargets = brailleInputPlane2.buildDotTargets(brailleInputPlane2.sizeInPixels);
                    brailleInputPlane2.calibrationFailCount = 0;
                    brailleInputPlane2.twoStepCalibrationState$ar$edu = 1;
                    brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onTwoStepCalibrationFailed();
                }
                return true;
            }
        }
        MultitouchResult multitouchResult2 = (MultitouchResult) obj2;
        int i4 = multitouchResult2.type;
        if (i4 != 0) {
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 != 3) {
                        return brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(brailleInputPlane2.createDotHoldAndSwipe(multitouchResult2.swipe, new BrailleCharacter(brailleInputPlane2.matchTouchToTargetNumbers(multitouchResult2.heldPoints)))));
                    }
                    int i5 = brailleInputPlane2.twoStepCalibrationState$ar$edu;
                    if (i5 != 1) {
                        if (i5 == 2) {
                            z = true;
                        } else {
                            z = false;
                        }
                        Optional of = Optional.of(BrailleInputPlaneResult.createCalibration(z, multitouchResult2.heldPoints.size()));
                        List list = multitouchResult2.heldPoints;
                        if (!brailleInputPlane2.options.tutorialMode) {
                            if (brailleInputPlane2.twoStepCalibrationState$ar$edu == 2) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            brailleInputPlane2.sortDotCentersByGroup(list, z2);
                            brailleInputPlane2.oldDotTargets = new ArrayList(brailleInputPlane2.dotTargets);
                            int[] dotNumberOrder = brailleInputPlane2.getDotNumberOrder();
                            for (int i6 = 0; i6 < list.size(); i6++) {
                                BrailleInputOptions brailleInputOptions = brailleInputPlane2.options;
                                if (brailleInputOptions.reverseDots) {
                                    if (brailleInputPlane2.twoStepCalibrationState$ar$edu == 2) {
                                        i = brailleInputOptions.brailleType$ar$edu;
                                        i2 = (i >> 1) + i6;
                                    }
                                    i2 = i6;
                                } else {
                                    if (brailleInputPlane2.twoStepCalibrationState$ar$edu != 2) {
                                        i = brailleInputOptions.brailleType$ar$edu;
                                        i2 = (i >> 1) + i6;
                                    }
                                    i2 = i6;
                                }
                                brailleInputPlane2.dotTargets.set(i2, new OrderVerifyingClientCall.State(dotNumberOrder[i2], ((PointF) list.get(i6)).x, ((PointF) list.get(i6)).y));
                            }
                        }
                        if (brailleInputPlane2.twoStepCalibrationState$ar$edu == 2) {
                            brailleInputPlane2.twoStepCalibrationState$ar$edu = 3;
                        } else if (brailleInputPlane2.twoStepCalibrationState$ar$edu == 3) {
                            brailleInputPlane2.twoStepCalibrationState$ar$edu = 1;
                        }
                        brailleInputPlane2.calibrationFailCount = 0;
                        return brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(of);
                    }
                    if (multitouchResult2.heldPoints.size() < 5 || multitouchResult2.heldPoints.size() > 8) {
                        return false;
                    }
                    Optional of2 = Optional.of(BrailleInputPlaneResult.createCalibration(false, multitouchResult2.heldPoints.size()));
                    List list2 = multitouchResult2.heldPoints;
                    if (list2.size() != 5 && ((list2.size() != 6 || brailleInputPlane2.options.brailleType$ar$edu != 8) && (list2.size() != 7 || brailleInputPlane2.options.brailleType$ar$edu != 8))) {
                        if (!brailleInputPlane2.options.tutorialMode && (list2.size() == 6 || list2.size() == 8)) {
                            brailleInputPlane2.oldDotTargets = new ArrayList(brailleInputPlane2.dotTargets);
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList(list2);
                            brailleInputPlane2.sortDotCenters(arrayList2);
                            int[] dotNumberOrder2 = brailleInputPlane2.getDotNumberOrder();
                            for (int i7 = 0; i7 < arrayList2.size(); i7++) {
                                arrayList.add(new OrderVerifyingClientCall.State(dotNumberOrder2[i7], ((PointF) arrayList2.get(i7)).x, ((PointF) arrayList2.get(i7)).y));
                            }
                            brailleInputPlane2.dotTargets = arrayList;
                        }
                    } else {
                        brailleInputPlane2.twoStepCalibrationState$ar$edu = 2;
                    }
                    brailleInputPlane2.calibrationFailCount = 0;
                    return brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(of2);
                }
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                int size = multitouchResult2.heldPoints.size();
                BrailleInputPlaneResult brailleInputPlaneResult = new BrailleInputPlaneResult();
                brailleInputPlaneResult.type = 3;
                brailleInputPlaneResult.pointersHeldCount = size;
                return shadowDelegateImpl2.detect(Optional.of(brailleInputPlaneResult));
            }
            return brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(brailleInputPlane2.createSwipe(multitouchResult2.swipe)));
        }
        return brailleInputPlane2.customOnGestureListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.detect(Optional.of(BrailleInputPlaneResult.createTapAndRelease(new BrailleCharacter(brailleInputPlane2.matchTouchToTargetNumbers(multitouchResult2.releasedPoints)))));
    }

    public final CursorGranularity getCurrentGranularity() {
        return ((CursorGranularityManager) ((DirectionNavigationActor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity;
    }

    public final FocusActionInfo getFocusActionInfoFromEvent(AccessibilityEvent accessibilityEvent) {
        FocusActionRecord matchFocusActionRecordFromEvent = matchFocusActionRecordFromEvent(accessibilityEvent);
        if (matchFocusActionRecordFromEvent == null) {
            return null;
        }
        return matchFocusActionRecordFromEvent.extraInfo;
    }

    public final FocusActionRecord getLastFocusActionRecordInWindow(AccessibilityFocusActionHistory.WindowIdentifier windowIdentifier) {
        return (FocusActionRecord) ((AccessibilityFocusActionHistory) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).windowIdentifierToFocusActionRecordMap.get(windowIdentifier);
    }

    public final ScreenState getStableScreenState() {
        return ((ScreenStateMonitor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).stableScreenState;
    }

    public final boolean hasCutActionAtTime(long j) {
        EditTextActionHistory editTextActionHistory = (EditTextActionHistory) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        long j2 = editTextActionHistory.mCutStartTime;
        if (j2 == -1 || j2 > j) {
            return false;
        }
        long j3 = editTextActionHistory.mCutFinishTime;
        if (j3 >= j2 && j3 < j) {
            return false;
        }
        return true;
    }

    public final boolean hasPasteActionAtTime(long j) {
        EditTextActionHistory editTextActionHistory = (EditTextActionHistory) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        long j2 = editTextActionHistory.mPasteStartTime;
        if (j2 == -1 || j2 > j) {
            return false;
        }
        long j3 = editTextActionHistory.mPasteFinishTime;
        if (j3 >= j2 && j3 < j) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.util.Collection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.Collection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List, java.lang.Object] */
    public final boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Performance.EventId eventId, AccessibilityEvent accessibilityEvent, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Pipeline pipeline = (Pipeline) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        ApplicationModule applicationModule = pipeline.userInterface$ar$class_merging$ar$class_merging$ar$class_merging;
        if (!applicationModule.ApplicationModule$ar$application.isEmpty()) {
            if (accessibilityEvent != null && accessibilityEvent.getEventType() == 32768) {
                if (spannableUtils$NonCopyableTextSpan instanceof Interpretation$AccessibilityFocused) {
                    Collection.EL.stream(applicationModule.ApplicationModule$ar$application).forEach(new TrainingIpcClient$$ExternalSyntheticLambda0(accessibilityEvent, spannableUtils$NonCopyableTextSpan, 1));
                }
            } else if (spannableUtils$NonCopyableTextSpan instanceof Interpretation$TouchInteraction) {
                Collection.EL.stream(applicationModule.ApplicationModule$ar$application).forEach(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(spannableUtils$NonCopyableTextSpan, 10));
            }
        }
        Feedback mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = pipeline.mappers$ar$class_merging$ar$class_merging.mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, spannableUtils$NonCopyableTextSpan, accessibilityNodeInfoCompat);
        if (mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            return false;
        }
        return pipeline.execute(mapToFeedback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    public final /* synthetic */ void input$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Performance.EventId eventId, AccessibilityEvent accessibilityEvent, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, spannableUtils$NonCopyableTextSpan, null);
    }

    public final boolean isActive() {
        return ((FullScreenReadActor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).isActive();
    }

    public final boolean isDimmingEnabled() {
        return ((DimScreenActor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).isDimmingEnabled();
    }

    public final boolean isHoldRecognized(int i) {
        if (!((BrailleInputView) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).inputPlane.inTwoStepCalibration() && i <= 3) {
            return true;
        }
        return false;
    }

    public final boolean isSelectionModeActive() {
        return ((CursorGranularityManager) ((DirectionNavigationActor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).DirectionNavigationActor$ar$cursorGranularityManager).selectionModeActive;
    }

    public final FocusActionRecord matchFocusActionRecordFromEvent(AccessibilityEvent accessibilityEvent) {
        AccessibilityNodeInfoCompat compat;
        Iterator it;
        if (!SpannableUtils$IdentifierSpan.eventMatchesAnyType(accessibilityEvent, 32768) || (compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource())) == null) {
            return null;
        }
        Object obj = this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        long eventTime = accessibilityEvent.getEventTime();
        AccessibilityFocusActionHistory accessibilityFocusActionHistory = (AccessibilityFocusActionHistory) obj;
        if (accessibilityFocusActionHistory.pendingWebFocusActionInfo != null) {
            long j = eventTime - accessibilityFocusActionHistory.pendingWebFocusActionTime;
            if (j < accessibilityFocusActionHistory.timeoutToleranceMs && j > 0 && WebInterfaceUtils.supportsWebActions(compat)) {
                accessibilityFocusActionHistory.onAccessibilityFocusAction(compat, accessibilityFocusActionHistory.pendingWebFocusActionInfo, accessibilityFocusActionHistory.pendingWebFocusActionTime, accessibilityFocusActionHistory.pendingScreenState);
                accessibilityFocusActionHistory.pendingWebFocusActionInfo = null;
                accessibilityFocusActionHistory.pendingWebFocusActionTime = -1L;
            }
        }
        Iterator descendingIterator = accessibilityFocusActionHistory.focusActionRecordList.descendingIterator();
        while (descendingIterator.hasNext()) {
            FocusActionRecord focusActionRecord = (FocusActionRecord) descendingIterator.next();
            long j2 = eventTime - focusActionRecord.actionTime;
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = focusActionRecord.focusedNode;
            boolean z = false;
            if (j2 >= 0) {
                it = descendingIterator;
                if (j2 < accessibilityFocusActionHistory.timeoutToleranceMs) {
                    z = true;
                }
            } else {
                it = descendingIterator;
            }
            boolean equals = compat.equals(accessibilityNodeInfoCompat);
            if (z && equals) {
                return FocusActionRecord.copy(focusActionRecord);
            }
            descendingIterator = it;
        }
        return null;
    }

    public final boolean moveCursorBackward() {
        boolean z;
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (!brailleIme.editBuffer.moveCursorBackward$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging())) {
            z = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(42, new Object[0]);
        } else {
            z = true;
        }
        if (z) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return z;
    }

    public final boolean moveCursorBackwardByLine() {
        if (!AppCompatDelegateImpl.Api33Impl.isMultiLineField(((EditorInfo) ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getImeConnection$ar$class_merging$ar$class_merging().PhenotypeProcessReaper$ar$executorProvider).inputType)) {
            return false;
        }
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).mainHandler.post(new BrailleIme$21$$ExternalSyntheticLambda1(1));
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        return true;
    }

    public final void moveCursorBackwardByWord$ar$ds$69f8338f_0() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).mainHandler.post(new BrailleIme$21$$ExternalSyntheticLambda1(0));
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
    }

    public final boolean moveCursorForward() {
        boolean z;
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (!brailleIme.editBuffer.moveCursorForward$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging())) {
            z = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(41, new Object[0]);
        } else {
            z = true;
        }
        if (z) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return z;
    }

    public final boolean moveCursorForwardByLine() {
        if (!AppCompatDelegateImpl.Api33Impl.isMultiLineField(((EditorInfo) ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getImeConnection$ar$class_merging$ar$class_merging().PhenotypeProcessReaper$ar$executorProvider).inputType)) {
            return false;
        }
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(45, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean moveCursorForwardByWord() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(43, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean moveCursorToBeginning() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        boolean moveCursorToBeginning$ar$class_merging$ar$class_merging = brailleIme.editBuffer.moveCursorToBeginning$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        return moveCursorToBeginning$ar$class_merging$ar$class_merging;
    }

    public final boolean moveCursorToEnd() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        boolean moveCursorToEnd$ar$class_merging$ar$class_merging = brailleIme.editBuffer.moveCursorToEnd$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        return moveCursorToEnd$ar$class_merging$ar$class_merging;
    }

    public final boolean moveHoldingsCursor(int i) {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        boolean moveHoldingsCursor$ar$class_merging$ar$class_merging = brailleIme.editBuffer.moveHoldingsCursor$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging(), i);
        if (moveHoldingsCursor$ar$class_merging$ar$class_merging) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return moveHoldingsCursor$ar$class_merging$ar$class_merging;
    }

    public final boolean moveTextFieldCursor(int i) {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        boolean moveTextFieldCursor$ar$class_merging$ar$class_merging = brailleIme.editBuffer.moveTextFieldCursor$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging(), i);
        if (moveTextFieldCursor$ar$class_merging$ar$class_merging) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return moveTextFieldCursor$ar$class_merging$ar$class_merging;
    }

    public final void onAudioPlaybackActivated(List list) {
        AudioAttributes audioAttributes;
        VoiceActionMonitor voiceActionMonitor = (VoiceActionMonitor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (!voiceActionMonitor.skipInterruption) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                AudioPlaybackConfiguration m266m = BundleUtils$$ExternalSyntheticApiModelOutline0.m266m(it.next());
                audioAttributes = m266m.getAudioAttributes();
                int usage = audioAttributes.getUsage();
                if (usage != 1 && usage != 14) {
                    LogUtils.v("VoiceActionMonitor", "AudioPlaybackStateChangedListener: interruptTalkBackAudio (config=%s)", m266m);
                    voiceActionMonitor.interruptTalkBackAudio(2);
                    return;
                }
            }
        }
    }

    public final void onBrailleDisplayConnected() {
        _BOUNDARY.d("BrailleIme", "onBrailleDisplayConnected");
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.brailleDisplayConnectedAndNotSuspended = true;
        brailleIme.updateInputView();
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).activateBrailleIme();
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).updateNavigationBarColor();
    }

    public final void onBrailleDisplayDisconnected() {
        _BOUNDARY.d("BrailleIme", "onBrailleDisplayDisconnected");
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        brailleIme.brailleDisplayConnectedAndNotSuspended = false;
        brailleIme.updateInputView();
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).activateBrailleIme();
    }

    public final void onCallStateChanged$ar$ds(int i) {
        if (i == 2) {
            ((VoiceActionMonitor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).interruptTalkBackAudio(3);
        }
    }

    public final void onHeadphoneStateChanged(boolean z) {
        SpeakPasswordsManager speakPasswordsManager = (SpeakPasswordsManager) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        speakPasswordsManager.headphonesConnected = z;
        speakPasswordsManager.globalVariables.mShouldSpeakPasswords = speakPasswordsManager.shouldSpeakPasswords();
    }

    public final void onLabelsInPackageChanged(String str) {
        ((CustomLabelManager) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).sendCacheRefreshIntent(str);
    }

    public final void onMicrophoneActivated() {
        VoiceActionMonitor voiceActionMonitor = (VoiceActionMonitor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (!voiceActionMonitor.isHeadphoneOn()) {
            voiceActionMonitor.interruptTalkBackAudio(1);
        }
    }

    public final void onPending(boolean z, Duration duration) {
        ImageCaptioner imageCaptioner = (ImageCaptioner) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        imageCaptioner.primeLogImageCaptionEvent(PrimesController.TimerAction.LATENCY_BETWEEN_SCREENSHOT_CAPTURE_REQUEST, duration.toMillis());
        if (z) {
            return;
        }
        imageCaptioner.analytics.onImageCaptionEvent(36);
        imageCaptioner.screenshotRequests.performNextRequest();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.google.android.accessibility.talkback.dynamicfeature.Downloader$DownloadStateUpdateListener, java.lang.Object] */
    public final void onStateUpdate(Object obj) {
        SplitInstallSessionState splitInstallSessionState = (SplitInstallSessionState) obj;
        this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0.onStateUpdate(new Downloader.DownloadState(ImmutableList.copyOf((java.util.Collection) splitInstallSessionState.moduleNames()), SplitApkDownloader.getStatus(splitInstallSessionState.status()), SplitApkDownloader.getErrorCode$ar$edu(splitInstallSessionState.errorCode()), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload()));
    }

    public final void onSwitchToNextIme() {
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).switchToNextInputMethod();
    }

    public final void onTaskPostExecute(LabelClientRequest labelClientRequest) {
        CustomLabelManager.checkUiThread$ar$ds();
        LogUtils.v("CustomLabelManager", "Task %s ending.", labelClientRequest);
        r4.runningTasks--;
        ((CustomLabelManager) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).maybeShutdownClient();
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = ((CustomLabelManager) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).labelChangeListener$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null) {
            new LabelManagerPackageActivity.UpdateLabelsTask().execute(new Void[0]);
        }
    }

    public final void onTaskPreExecute(LabelClientRequest labelClientRequest) {
        CustomLabelManager.checkUiThread$ar$ds();
        LogUtils.v("CustomLabelManager", "Task %s starting.", labelClientRequest);
        ((CustomLabelManager) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).runningTasks++;
    }

    public final void onUtteranceRangeStarted(int i, int i2) {
        SelectToSpeakJob selectToSpeakJob = (SelectToSpeakJob) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (selectToSpeakJob.state == 1) {
            SentenceIterator sentenceIterator = selectToSpeakJob.iterator;
            InSentenceOffset inSentenceOffset = sentenceIterator.offsetTracker;
            int i3 = inSentenceOffset.offset;
            inSentenceOffset.start = i + i3;
            inSentenceOffset.end = i3 + i2;
            Sentence peek = sentenceIterator.getPeek();
            if (peek != null && peek.getSupportsTextLocation()) {
                SelectToSpeakJob selectToSpeakJob2 = (SelectToSpeakJob) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
                selectToSpeakJob2.highlight(selectToSpeakJob2.isMultitaskingActivated, peek);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.CharSequence, java.lang.Object] */
    public final CharSequence parseTTSText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, EventInterpretation eventInterpretation) {
        Compositor$HandleEventOptions compositor$HandleEventOptions = new Compositor$HandleEventOptions();
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode = accessibilityNodeInfoCompat;
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventInterpretation = eventInterpretation;
        EventFeedback eventFeedback = ((Verifier) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).getEventFeedback(i, compositor$HandleEventOptions);
        if (eventFeedback != null && eventFeedback.ttsOutput().isPresent()) {
            return eventFeedback.ttsOutput().get();
        }
        return null;
    }

    public final boolean pasteSelectedText() {
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(50, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectAllText() {
        BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        boolean selectAllText$ar$class_merging$ar$class_merging = brailleIme.editBuffer.selectAllText$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        if (selectAllText$ar$class_merging$ar$class_merging) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return selectAllText$ar$class_merging$ar$class_merging;
    }

    public final boolean selectCurrentToEnd() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(58, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectCurrentToStart() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(57, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectNextCharacter() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(52, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectNextLine() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(56, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectNextWord() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(54, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectPreviousCharacter() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(51, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectPreviousLine() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(55, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean selectPreviousWord() {
        commitHoldings();
        boolean performAction$ar$edu$3bc9316c_0 = BrailleIme.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(53, new Object[0]);
        if (performAction$ar$edu$3bc9316c_0) {
            ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        }
        return performAction$ar$edu$3bc9316c_0;
    }

    public final boolean sendBrailleDots(BrailleCharacter brailleCharacter) {
        BrailleDisplayImeStripView brailleDisplayImeStripView = ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).keyboardView.stripView;
        List dotNumbers = BrailleUnicode.toDotNumbers(brailleCharacter.dotNumbers);
        Drawable[] drawableArr = new Drawable[dotNumbers.size()];
        for (int i = 0; i < dotNumbers.size(); i++) {
            drawableArr[i] = ContextCompat$Api21Impl.getDrawable(brailleDisplayImeStripView.getContext(), ((Integer) brailleDisplayImeStripView.dotsResMap.get(dotNumbers.get(i))).intValue());
        }
        boolean z = true;
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{new LayerDrawable(drawableArr), ContextCompat$Api21Impl.getDrawable(brailleDisplayImeStripView.getContext(), R.drawable.dots_untapped)});
        brailleDisplayImeStripView.dotsBackground.setImageDrawable(transitionDrawable);
        transitionDrawable.setCrossFadeEnabled(true);
        transitionDrawable.startTransition(150);
        if (brailleCharacter.isEmpty()) {
            BrailleIme brailleIme = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
            brailleIme.editBuffer.appendSpace$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
        } else if (brailleCharacter.equals(BrailleCharacter.DOT7)) {
            deleteBackward$ar$ds$81eb5611_0();
        } else if (brailleCharacter.equals(BrailleCharacter.DOT8)) {
            z = commitHoldingsAndPerformEnterKeyAction();
        } else {
            BrailleIme brailleIme2 = (BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
            brailleIme2.editBuffer.appendBraille$ar$class_merging$ar$class_merging(brailleIme2.getImeConnection$ar$class_merging$ar$class_merging(), brailleCharacter);
        }
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
        return z;
    }

    public final void updateHistory(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo) {
        ((FocusActor) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).updateFocusHistory(accessibilityNodeInfoCompat, focusActionInfo);
    }

    public final void updateResultForDisplay() {
        ((BrailleIme) this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).showOnBrailleDisplay();
    }

    public HapticPatternParser$$ExternalSyntheticLambda1(Object obj, byte[] bArr) {
        this.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }
}
