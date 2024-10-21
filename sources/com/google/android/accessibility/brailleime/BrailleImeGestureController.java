package com.google.android.accessibility.brailleime;

import android.content.Context;
import android.os.Handler;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.common.translate.EditBuffer;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics;
import com.google.android.accessibility.brailleime.input.DotHoldSwipe;
import com.google.android.accessibility.brailleime.input.Gesture;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeGestureController {
    private BrailleImeAnalytics brailleImeAnalytics;
    private final RetryingNameResolver.ResolutionResultListener callback$ar$class_merging$3976bc34_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;
    public EditBuffer editBuffer;
    private final ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Map gestureActionMap = new HashMap();
    public final Map gestureMap = new HashMap();
    private final Handler handler = new Handler();
    public final TalkBackForBrailleIme talkBackForBrailleIme;
    private final TypoHandler typoHandler;

    public BrailleImeGestureController(Context context, TypoHandler typoHandler, EditBuffer editBuffer, RetryingNameResolver.ResolutionResultListener resolutionResultListener, TalkBackForBrailleIme talkBackForBrailleIme, ApplicationModule applicationModule) {
        Optional empty;
        this.context = context;
        this.typoHandler = typoHandler;
        this.editBuffer = editBuffer;
        this.callback$ar$class_merging$3976bc34_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.talkBackForBrailleIme = talkBackForBrailleIme;
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        this.brailleImeAnalytics = BrailleImeAnalytics.getInstance(context);
        for (BrailleImeGesture brailleImeGesture : BrailleImeGesture.values()) {
            if (brailleImeGesture.gestureType$ar$edu == 2) {
                empty = Optional.of(new Swipe(brailleImeGesture.direction, brailleImeGesture.touchCount));
            } else if (brailleImeGesture.gestureType$ar$edu == 3) {
                empty = Optional.of(new DotHoldSwipe(brailleImeGesture.direction, brailleImeGesture.touchCount, new BrailleCharacter(brailleImeGesture.heldDotNumberString)));
            } else {
                empty = Optional.empty();
            }
            if (empty.isPresent()) {
                this.gestureMap.put((Gesture) empty.get(), brailleImeGesture);
                this.gestureMap.put(((Gesture) empty.get()).mirrorDots(), brailleImeGesture);
            }
        }
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_UP_ONE_FINGER, BrailleImeActions.MOVE_CURSOR_BACKWARD);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_DOWN_ONE_FINGER, BrailleImeActions.MOVE_CURSOR_FORWARD);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_LEFT_ONE_FINGER, BrailleImeActions.ADD_SPACE);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_RIGHT_ONE_FINGER, BrailleImeActions.DELETE_CHARACTER);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_UP_TWO_FINGERS, BrailleImeActions.SUBMIT_TEXT);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_DOWN_TWO_FINGERS, BrailleImeActions.HIDE_KEYBOARD);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_LEFT_TWO_FINGERS, BrailleImeActions.ADD_NEWLINE);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_RIGHT_TWO_FINGERS, BrailleImeActions.DELETE_WORD);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_UP_THREE_FINGERS, BrailleImeActions.HELP_AND_OTHER_ACTIONS);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_DOWN_THREE_FINGERS, BrailleImeActions.SWITCH_KEYBOARD);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_LEFT_THREE_FINGERS, BrailleImeActions.NEXT_GRANULARITY);
        this.gestureActionMap.put(BrailleImeGesture.SWIPE_RIGHT_THREE_FINGERS, BrailleImeActions.PREVIOUS_GRANULARITY);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_UP_ONE_FINGER, BrailleImeActions.PREVIOUS_LINE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_DOWN_ONE_FINGER, BrailleImeActions.NEXT_LINE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_UP_TWO_FINGERS, BrailleImeActions.SELECT_PREVIOUS_LINE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_DOWN_TWO_FINGERS, BrailleImeActions.SELECT_NEXT_LINE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_UP_THREE_FINGERS, BrailleImeActions.CUT);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_DOWN_THREE_FINGERS, BrailleImeActions.COPY);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_LEFT_THREE_FINGERS, BrailleImeActions.PASTE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT1_SWIPE_RIGHT_THREE_FINGERS, BrailleImeActions.SELECT_ALL);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOTS12_SWIPE_UP_TWO_FINGERS, BrailleImeActions.SELECT_CURRENT_TO_START);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOTS12_SWIPE_DOWN_TWO_FINGERS, BrailleImeActions.SELECT_CURRENT_TO_END);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT2_SWIPE_UP_ONE_FINGER, BrailleImeActions.PREVIOUS_WORD);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT2_SWIPE_DOWN_ONE_FINGER, BrailleImeActions.NEXT_WORD);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT2_SWIPE_UP_TWO_FINGERS, BrailleImeActions.SELECT_PREVIOUS_WORD);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT2_SWIPE_DOWN_TWO_FINGERS, BrailleImeActions.SELECT_NEXT_WORD);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT3_SWIPE_UP_ONE_FINGER, BrailleImeActions.PREVIOUS_CHARACTER);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT3_SWIPE_DOWN_ONE_FINGER, BrailleImeActions.NEXT_CHARACTER);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT3_SWIPE_UP_TWO_FINGERS, BrailleImeActions.SELECT_PREVIOUS_CHARACTER);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOT3_SWIPE_DOWN_TWO_FINGERS, BrailleImeActions.SELECT_NEXT_CHARACTER);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOTS12_SWIPE_UP_ONE_FINGER, BrailleImeActions.BEGINNING_OF_PAGE);
        this.gestureActionMap.put(BrailleImeGesture.HOLD_DOTS12_SWIPE_DOWN_ONE_FINGER, BrailleImeActions.END_OF_PAGE);
    }

    private final boolean selectText$ar$edu$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, int i) {
        this.editBuffer.commit$ar$class_merging$ar$class_merging(phenotypeProcessReaper);
        return this.talkBackForBrailleIme.performAction$ar$edu$3bc9316c_0(i, new Object[0]);
    }

    /* renamed from: lambda$performImeAction$0$com-google-android-accessibility-brailleime-BrailleImeGestureController, reason: not valid java name */
    public final /* synthetic */ void m72x5bdeef8() {
        TalkBackForBrailleImeImpl talkBackForBrailleImeImpl = (TalkBackForBrailleImeImpl) this.talkBackForBrailleIme;
        if (TalkBackForBrailleImeImpl.VALID_GRANULARITIES.contains(SelectorController.getCurrentSetting(talkBackForBrailleImeImpl.service))) {
            talkBackForBrailleImeImpl.performMovingCursor(false);
        }
        if (this.talkBackForBrailleIme.isCurrentGranularityTypoCorrection()) {
            Handler handler = this.handler;
            TypoHandler typoHandler = this.typoHandler;
            typoHandler.getClass();
            handler.post(new WorkerKt$$ExternalSyntheticLambda0(typoHandler, 13));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0090, code lost:
    
        if (r1 != false) goto L129;
     */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0370  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean performAction(com.google.android.accessibility.brailleime.BrailleImeActions r10) {
        /*
            Method dump skipped, instructions count: 1266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.BrailleImeGestureController.performAction(com.google.android.accessibility.brailleime.BrailleImeActions):boolean");
    }
}
