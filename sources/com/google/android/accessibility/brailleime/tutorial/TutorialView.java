package com.google.android.accessibility.brailleime.tutorial;

import android.animation.ValueAnimator;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.ActionBarContextView;
import android.text.TextUtils;
import android.util.Size;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.common.translate.BrailleTranslateUtils;
import com.google.android.accessibility.braille.common.translate.BrailleTranslateUtilsUeb;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleImeVibrator$VibrationType;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.OrientationMonitor$Callback;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.input.MultitouchHandler$$ExternalSyntheticLambda8;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.accessibility.selecttospeak.UIManager$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.DesugarCollections;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TutorialView extends FrameLayout {
    public static final String TAG = "TutorialView";
    private boolean allowExploration;
    public final Context context;
    public final ContextMenuDialog contextMenuDialog;
    private final ContextMenuDialog.Callback contextMenuDialogCallback;
    public final ContextMenuOpened contextMenuOpened;
    public final DotBlockView dotBlockView;
    public DotsFlashingAnimationView dotsFlashingAnimationView;
    private final GestureDetector.SimpleOnGestureListener doubleTapDetector;
    private final GestureDetector gestureDetector;
    public final Handler handler;
    public final HoldSixFingers holdSixFingers;
    private final BrailleInputView.Callback inputPlaneCallback;
    public final BrailleInputView inputView;
    public final AtomicInteger instructionSpeechId;
    private final Intro intro;
    private boolean isStopped;
    public final boolean isTabletop;
    public int numberOfInteractionsPerState;
    public int orientation;
    public final RotateOrientation rotateOrientation;
    public final RotateOrientationContinue rotateOrientationContinue;
    public TutorialState state;
    public final SwipeDown3Fingers swipeDown$ar$class_merging;
    public final SwipeDown3Fingers swipeDown2Fingers$ar$class_merging;
    public final SwipeDown3Fingers swipeDown3Fingers;
    public final SwipeDown3Fingers swipeLeft$ar$class_merging;
    public final SwipeDown3Fingers swipeRight$ar$class_merging;
    public final SwipeDown3Fingers swipeUp$ar$class_merging;
    public final SwipeUp3Fingers swipeUp3Fingers;
    public final TypeLetterBCD tapLetterBCD;
    public final BrailleTranslator translator;
    public TutorialAnimationView tutorialAnimationView;
    public final RetryingNameResolver.ResolutionResultListener tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final TutorialFinished tutorialFinished;
    public final SwipeDown3Fingers typeLetterA$ar$class_merging;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.tutorial.TutorialView$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements ContextMenuDialog.Callback {
        final /* synthetic */ Object TutorialView$2$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(Object obj, int i) {
            this.switching_field = i;
            this.TutorialView$2$ar$this$0 = obj;
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onCalibration() {
            if (this.switching_field != 0) {
                ((BrailleIme) this.TutorialView$2$ar$this$0).activateBrailleIme();
                ((BrailleIme) this.TutorialView$2$ar$this$0).layoutOrientator$ar$class_merging.startIfNeeded();
                BrailleInputView brailleInputView = ((BrailleIme) this.TutorialView$2$ar$this$0).keyboardView.brailleInputView;
                if (brailleInputView == null) {
                    return;
                }
                BrailleInputPlane brailleInputPlane = brailleInputView.inputPlane;
                brailleInputPlane.twoStepCalibrationState$ar$edu = 2;
                brailleInputPlane.calibrationFailCount = 0;
                brailleInputView.calibrationType$ar$edu = 5;
                brailleInputView.callback.onCalibration$ar$edu$55d81d41_0(5, 1);
                brailleInputView.invalidate();
            }
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onDialogHidden() {
            if (this.switching_field != 0) {
                ((BrailleIme) this.TutorialView$2$ar$this$0).activateBrailleIme();
                ((BrailleIme) this.TutorialView$2$ar$this$0).startAnalyticsPossibly();
                ((BrailleIme) this.TutorialView$2$ar$this$0).layoutOrientator$ar$class_merging.startIfNeeded();
                ((BrailleIme) this.TutorialView$2$ar$this$0).showOnBrailleDisplay();
                return;
            }
            ((TutorialView) this.TutorialView$2$ar$this$0).tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleImeActivated();
            TutorialView tutorialView = (TutorialView) this.TutorialView$2$ar$this$0;
            ContextMenuOpened contextMenuOpened = tutorialView.contextMenuOpened;
            TutorialState tutorialState = tutorialView.state;
            if (tutorialState == contextMenuOpened) {
                tutorialView.switchState(tutorialView.swipeUp3Fingers, 0L);
            } else {
                tutorialState.onUtteranceCompleted();
            }
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onDialogShown() {
            if (this.switching_field != 0) {
                ((BrailleIme) this.TutorialView$2$ar$this$0).deactivateBrailleIme();
                ((BrailleIme) this.TutorialView$2$ar$this$0).layoutOrientator$ar$class_merging.stop();
            } else {
                ((TutorialView) this.TutorialView$2$ar$this$0).tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleImeInactivated();
            }
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onLaunchSettings() {
            if (this.switching_field != 0) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(((BrailleIme) this.TutorialView$2$ar$this$0).getPackageName(), BrailleImePreferencesActivity.class.getName()));
                intent.addFlags(268435456);
                ((BrailleIme) this.TutorialView$2$ar$this$0).startActivity(intent);
                return;
            }
            ((TutorialView) this.TutorialView$2$ar$this$0).tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onLaunchSettings();
            TutorialView tutorialView = (TutorialView) this.TutorialView$2$ar$this$0;
            tutorialView.switchState(tutorialView.tutorialFinished, 0L);
            ((TutorialView) this.TutorialView$2$ar$this$0).tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onTutorialFinished();
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onTutorialClosed() {
            if (this.switching_field != 0) {
                ((BrailleIme) this.TutorialView$2$ar$this$0).escapeReminder.startTimer();
                return;
            }
            TutorialView tutorialView = (TutorialView) this.TutorialView$2$ar$this$0;
            tutorialView.switchState(tutorialView.tutorialFinished, 0L);
            OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(((TutorialView) this.TutorialView$2$ar$this$0).getResources().getString(R.string.finish_tutorial_announcement), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(2, null));
            ((TutorialView) this.TutorialView$2$ar$this$0).tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onTutorialFinished();
        }

        @Override // com.google.android.accessibility.brailleime.dialog.ContextMenuDialog.Callback
        public final void onTutorialOpen() {
            if (this.switching_field != 0) {
                ((BrailleIme) this.TutorialView$2$ar$this$0).escapeReminder.cancelTimer();
                ((BrailleIme) this.TutorialView$2$ar$this$0).layoutOrientator$ar$class_merging.stop();
                ((BrailleIme) this.TutorialView$2$ar$this$0).tutorialState$ar$edu = 2;
                ((BrailleIme) this.TutorialView$2$ar$this$0).createAndAddTutorialView();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface TutorialState {
        String audialAnnouncementRepeated();

        int getCurrentState$ar$edu();

        String hintText();

        boolean isActionCompleted();

        void loadView();

        void onBrailleProduced(BrailleCharacter brailleCharacter);

        void onCalibration$ar$edu$ar$ds(int i);

        void onDoubleTap();

        void onSwipeProduced(Swipe swipe);

        void onTwoStepCalibrationRetry$ar$ds();

        void onUtteranceCompleted();
    }

    /* renamed from: -$$Nest$mspeakBrailleCharacter$ar$ds, reason: not valid java name */
    static /* bridge */ /* synthetic */ void m84$$Nest$mspeakBrailleCharacter$ar$ds(TutorialView tutorialView, BrailleCharacter brailleCharacter) {
        String str;
        Resources resources = tutorialView.getResources();
        BrailleCharacter brailleCharacter2 = BrailleTranslateUtilsUeb.LETTER_A;
        if (brailleCharacter.equals(BrailleTranslateUtils.DOT6)) {
            str = resources.getString(R.string.capitalize_announcement);
        } else if (brailleCharacter.equals(BrailleTranslateUtils.DOTS3456)) {
            str = resources.getString(R.string.number_announcement);
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            str = tutorialView.translator.translateToPrint(new BrailleWord(brailleCharacter));
        }
        if (TextUtils.isEmpty(str) || !BrailleTranslateUtils.isPronounceable(str)) {
            str = BrailleTranslateUtils.getDotsText(tutorialView.getResources(), brailleCharacter);
        }
        tutorialView.speakUserEventAnnouncement$ar$ds(str);
    }

    public TutorialView(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener, Size size) {
        super(context);
        this.handler = new Handler();
        this.instructionSpeechId = new AtomicInteger();
        BrailleInputView.Callback callback = new BrailleInputView.Callback() { // from class: com.google.android.accessibility.brailleime.tutorial.TutorialView.1
            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final boolean isCalibrationHoldRecognized(boolean z, int i) {
                if (i >= 5) {
                    return true;
                }
                if (z && i == 3) {
                    return true;
                }
                return false;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final String onBrailleProduced(BrailleCharacter brailleCharacter) {
                UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(TutorialView.this.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                TutorialView.this.state.onBrailleProduced(brailleCharacter);
                return null;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final boolean onCalibration$ar$edu$55d81d41_0(int i, int i2) {
                TutorialView.this.state.onCalibration$ar$edu$ar$ds(i2);
                return true;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final void onCalibrationFailed$ar$edu(int i) {
                TutorialView tutorialView = TutorialView.this;
                tutorialView.speakAnnouncement(tutorialView.getResources().getString(R.string.calibration_fail_announcement), 0);
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final boolean onDotHoldAndDotSwipe(Swipe swipe, BrailleCharacter brailleCharacter) {
                return false;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final boolean onHoldProduced(int i) {
                return false;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final boolean onSwipeProduced(Swipe swipe) {
                Swipe.Direction direction = swipe.direction;
                boolean z = true;
                if (direction == Swipe.Direction.UP && swipe.touchCount == 3) {
                    TutorialView.this.openContextMenu();
                } else if (direction == Swipe.Direction.RIGHT && swipe.touchCount == 3 && TaskApiCall.Builder.getInstance$ar$class_merging().methodKey == 2) {
                    TutorialView.this.openContextMenu();
                } else {
                    z = false;
                }
                TutorialView.this.vibrateForSwipeGestures(swipe);
                TutorialView.this.state.onSwipeProduced(swipe);
                return z;
            }

            @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
            public final void onTwoStepCalibrationRetry(boolean z) {
                TutorialView.this.state.onTwoStepCalibrationRetry$ar$ds();
            }
        };
        this.inputPlaneCallback = callback;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(this, 0);
        this.contextMenuDialogCallback = anonymousClass2;
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.google.android.accessibility.brailleime.tutorial.TutorialView.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
                TutorialView.this.state.onDoubleTap();
                return false;
            }
        };
        this.doubleTapDetector = simpleOnGestureListener;
        this.context = context;
        this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.orientation = getResources().getConfiguration().orientation;
        this.intro = new Intro();
        this.rotateOrientation = new RotateOrientation();
        this.rotateOrientationContinue = new RotateOrientationContinue();
        this.typeLetterA$ar$class_merging = new SwipeDown3Fingers(this, 6);
        this.tapLetterBCD = new TypeLetterBCD();
        this.swipeLeft$ar$class_merging = new SwipeDown3Fingers(this, 3);
        this.swipeRight$ar$class_merging = new SwipeDown3Fingers(this, 4);
        this.swipeUp$ar$class_merging = new SwipeDown3Fingers(this, 5);
        this.swipeDown$ar$class_merging = new SwipeDown3Fingers(this, 2);
        this.swipeDown2Fingers$ar$class_merging = new SwipeDown3Fingers(this, 1);
        this.swipeDown3Fingers = new SwipeDown3Fingers(this, 0);
        this.swipeUp3Fingers = new SwipeUp3Fingers();
        this.contextMenuOpened = new ContextMenuOpened();
        TutorialFinished tutorialFinished = new TutorialFinished();
        this.tutorialFinished = tutorialFinished;
        this.holdSixFingers = new HoldSixFingers();
        this.state = tutorialFinished;
        this.gestureDetector = new GestureDetector(context, simpleOnGestureListener);
        BrailleInputOptions.Builder builder = BrailleInputOptions.builder();
        builder.setTutorialMode$ar$ds(true);
        builder.setBrailleType$ar$edu$ar$ds(6);
        BrailleInputView brailleInputView = new BrailleInputView(context, callback, size, builder.build());
        this.inputView = brailleInputView;
        boolean z = !AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(context.getResources());
        this.isTabletop = z;
        brailleInputView.setTableMode(z);
        this.dotBlockView = new DotBlockView(context, this.orientation, z, BrailleInputOptions.builder().build());
        this.tutorialAnimationView = new TutorialAnimationView(context, this.orientation, size, z);
        ContextMenuDialog contextMenuDialog = new ContextMenuDialog(context, anonymousClass2);
        this.contextMenuDialog = contextMenuDialog;
        contextMenuDialog.tutorialMode = true;
        this.translator = BrailleUserPreferences.readTranslatorFactory(context).create(context, BrailleLanguages.Code.UEB.name(), false);
    }

    private final void speakUserEventAnnouncement$ar$ds(String str) {
        if (!this.allowExploration) {
            return;
        }
        if (this.numberOfInteractionsPerState < 5) {
            OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(str, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(1, null));
            this.numberOfInteractionsPerState++;
        } else {
            speakAnnouncement(this.context.getString(R.string.continue_tutorial_announcement, this.state.audialAnnouncementRepeated()), 0);
        }
    }

    public final List getDotTargetsForBrailleCharacter(BrailleCharacter brailleCharacter) {
        return (List) Collection.EL.stream(DesugarCollections.unmodifiableList(ImmutableList.copyOf((java.util.Collection) this.inputView.inputPlane.dotTargets))).filter(new MultitouchHandler$$ExternalSyntheticLambda8(brailleCharacter, 2)).collect(Collectors.toList());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isStopped = true;
        this.handler.removeCallbacksAndMessages(null);
        this.contextMenuDialog.dismiss();
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    public final void onUtteranceCompleted() {
        if (this.isStopped) {
            return;
        }
        this.handler.removeCallbacksAndMessages(null);
        if (this.state.isActionCompleted()) {
            this.state.onUtteranceCompleted();
        } else {
            this.allowExploration = true;
            this.handler.postDelayed(new ContextMenuDialog$$ExternalSyntheticLambda5(this, 11), 5000L);
        }
    }

    public final void openContextMenu() {
        this.contextMenuDialog.show(this);
        speakAnnouncement(getResources().getString(R.string.open_context_menu_announcement), 0);
    }

    public final void reloadView() {
        removeAllViews();
        this.state.loadView();
    }

    public final void speakAnnouncement(String str, int i) {
        this.allowExploration = false;
        this.handler.removeCallbacksAndMessages(null);
        final int incrementAndGet = this.instructionSpeechId.incrementAndGet();
        OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(str, i, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(1, new SpeechController.UtteranceCompleteRunnable() { // from class: com.google.android.accessibility.brailleime.tutorial.TutorialView$$ExternalSyntheticLambda3
            @Override // com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable
            public final void run(int i2) {
                int i3;
                TutorialView tutorialView = TutorialView.this;
                if (i2 == 3) {
                    if (incrementAndGet == tutorialView.instructionSpeechId.get()) {
                        if (true != tutorialView.state.isActionCompleted()) {
                            i3 = 5000;
                        } else {
                            i3 = 0;
                        }
                        tutorialView.handler.postDelayed(new ContextMenuDialog$$ExternalSyntheticLambda5(tutorialView, 10), i3);
                        return;
                    }
                    return;
                }
                tutorialView.onUtteranceCompleted();
            }
        }));
    }

    public final void speakSwipeEvent(Swipe swipe) {
        String str;
        int i = swipe.touchCount;
        Swipe.Direction direction = swipe.direction;
        if (direction == Swipe.Direction.DOWN && i == 2) {
            str = getResources().getString(R.string.perform_hide_keyboard_announcement);
        } else if (direction == Swipe.Direction.DOWN && i == 3) {
            str = getResources().getString(R.string.perform_switch_keyboard_announcement);
        } else if (direction == Swipe.Direction.UP && i == 2) {
            str = getResources().getString(R.string.perform_submit_text_announcement);
        } else {
            Swipe.Direction direction2 = Swipe.Direction.LEFT;
            if (direction == direction2 && i == 1) {
                str = getResources().getString(R.string.perform_add_space_announcement);
            } else if (direction == direction2 && i == 2) {
                str = getResources().getString(R.string.perform_add_new_line_announcement);
            } else {
                Swipe.Direction direction3 = Swipe.Direction.RIGHT;
                if (direction == direction3 && i == 1) {
                    str = getResources().getString(R.string.perform_delete_letter_announcement);
                } else if (direction == direction3 && i == 2) {
                    str = getResources().getString(R.string.perform_delete_word_announcement);
                } else {
                    str = null;
                }
            }
        }
        if (str != null) {
            speakUserEventAnnouncement$ar$ds(str);
        }
    }

    public final void switchState(TutorialState tutorialState, long j) {
        postDelayed(new DelayedWorkTracker.AnonymousClass1(this, tutorialState, 14, (char[]) null), j);
    }

    public final void switchState$ar$edu$ar$ds(int i) {
        TutorialState tutorialState;
        TutorialState tutorialState2 = this.state;
        if (i != 0) {
            switch (i - 1) {
                case 1:
                    tutorialState = this.intro;
                    this.state = tutorialState;
                    break;
                case 2:
                    this.state = this.rotateOrientation;
                    break;
                case 3:
                    tutorialState = this.rotateOrientationContinue;
                    this.state = tutorialState;
                    break;
                case 4:
                    tutorialState = this.typeLetterA$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 5:
                    tutorialState = this.tapLetterBCD;
                    this.state = tutorialState;
                    break;
                case 6:
                    tutorialState = this.swipeLeft$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 7:
                    tutorialState = this.swipeRight$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 8:
                    tutorialState = this.swipeUp$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 9:
                    tutorialState = this.swipeDown$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 10:
                    tutorialState = this.swipeDown2Fingers$ar$class_merging;
                    this.state = tutorialState;
                    break;
                case 11:
                    tutorialState = this.swipeDown3Fingers;
                    this.state = tutorialState;
                    break;
                case 12:
                    tutorialState = this.swipeUp3Fingers;
                    this.state = tutorialState;
                    break;
                case 13:
                    tutorialState = this.contextMenuOpened;
                    this.state = tutorialState;
                    break;
                case 14:
                    tutorialState = this.holdSixFingers;
                    this.state = tutorialState;
                    break;
            }
            if (this.state.equals(tutorialState2)) {
                return;
            }
            switchState(this.state, 0L);
            return;
        }
        throw null;
    }

    public final void vibrateForSwipeGestures(Swipe swipe) {
        int i = swipe.touchCount;
        Swipe.Direction direction = swipe.direction;
        if (direction == Swipe.Direction.UP && i == 1) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.SPACE_DELETE_OR_MOVE_CURSOR_OR_GRANULARITY);
            return;
        }
        Swipe.Direction direction2 = Swipe.Direction.DOWN;
        if (direction == direction2 && i == 1) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.SPACE_DELETE_OR_MOVE_CURSOR_OR_GRANULARITY);
            return;
        }
        if (direction == direction2 && i == 2) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.OTHER_GESTURES);
            return;
        }
        if (direction == direction2 && i == 3) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.OTHER_GESTURES);
            return;
        }
        Swipe.Direction direction3 = Swipe.Direction.UP;
        if (direction == direction3 && i == 2) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.OTHER_GESTURES);
            return;
        }
        Swipe.Direction direction4 = Swipe.Direction.LEFT;
        if (direction == direction4 && i == 1) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.SPACE_DELETE_OR_MOVE_CURSOR_OR_GRANULARITY);
            return;
        }
        if (direction == direction4 && i == 2) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.NEWLINE_OR_DELETE_WORD);
            return;
        }
        Swipe.Direction direction5 = Swipe.Direction.RIGHT;
        if (direction == direction5 && i == 1) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.SPACE_DELETE_OR_MOVE_CURSOR_OR_GRANULARITY);
            return;
        }
        if (direction == direction5 && i == 2) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.NEWLINE_OR_DELETE_WORD);
            return;
        }
        if (direction == direction3 && i == 3) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.OTHER_GESTURES);
        } else if (direction == direction5 && i == 3 && TaskApiCall.Builder.getInstance$ar$class_merging().methodKey == 2) {
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.context).vibrate(BrailleImeVibrator$VibrationType.OTHER_GESTURES);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContextMenuOpened implements TutorialState {
        public ContextMenuOpened() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            return TutorialView.this.getResources().getString(R.string.open_context_menu_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 14;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            TutorialView tutorialView = TutorialView.this;
            tutorialView.addView(tutorialView.inputView);
            if (!TutorialView.this.contextMenuDialog.isShowing()) {
                TutorialView.this.openContextMenu();
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onSwipeProduced(Swipe swipe) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HoldSixFingers implements TutorialState {
        private boolean actionCompleted;
        private int fingersPattern$ar$edu;

        public HoldSixFingers() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            int i = this.fingersPattern$ar$edu;
            if (i == 5) {
                return TutorialView.this.getResources().getString(R.string.calibration_hold_left_or_right_three_finger_announcement, TutorialView.this.getResources().getString(R.string.left_hand));
            }
            if (i == 6) {
                return TutorialView.this.getResources().getString(R.string.calibration_step2_hold_left_or_right_finger_announcement, TutorialView.this.getResources().getString(R.string.right_hand));
            }
            return TutorialView.this.getResources().getString(R.string.hold_six_fingers_inactive_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 15;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final boolean isActionCompleted() {
            return this.actionCompleted;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            TutorialView.this.inputView.setAlpha(0.0f);
            TutorialView tutorialView = TutorialView.this;
            tutorialView.addView(tutorialView.inputView);
            View.inflate(TutorialView.this.getContext(), R.layout.tutorial_hold_six_fingers, TutorialView.this);
            ((AnimationDrawable) ((ImageView) TutorialView.this.findViewById(R.id.tutorial_animation)).getDrawable()).start();
            TutorialView tutorialView2 = TutorialView.this;
            tutorialView2.speakAnnouncement(tutorialView2.getResources().getString(R.string.hold_six_fingers_announcement, tutorialView2.getResources().getString(R.string.hold_six_fingers_description)), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onCalibration$ar$edu$ar$ds(int i) {
            this.fingersPattern$ar$edu = i;
            if (i != 2 && i != 7) {
                if (i == 5) {
                    TutorialView.this.speakAnnouncement(TutorialView.this.getResources().getString(R.string.calibration_step1_hold_left_or_right_finger_announcement, TutorialView.this.getResources().getString(R.string.left_hand)) + " " + TutorialView.this.getResources().getString(R.string.calibration_hold_left_or_right_three_finger_announcement, TutorialView.this.getResources().getString(R.string.left_hand)), 0);
                    return;
                }
                if (i == 6) {
                    this.fingersPattern$ar$edu = 6;
                    TutorialView.this.speakAnnouncement(TutorialView.this.getResources().getString(R.string.calibration_step2_hold_left_or_right_finger_announcement, TutorialView.this.getResources().getString(R.string.right_hand)) + " " + TutorialView.this.getResources().getString(R.string.calibration_hold_left_or_right_three_finger_announcement, TutorialView.this.getResources().getString(R.string.right_hand)), 0);
                    return;
                }
                return;
            }
            if (this.actionCompleted) {
                return;
            }
            TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
            TutorialView tutorialView = TutorialView.this;
            tutorialView.speakAnnouncement(tutorialView.getResources().getString(R.string.calibration_finish_announcement), 1000);
            this.actionCompleted = true;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onTwoStepCalibrationRetry$ar$ds() {
            TutorialView.this.speakAnnouncement(TutorialView.this.getResources().getString(R.string.calibration_hold_left_or_right_three_finger_announcement, TutorialView.this.getResources().getString(R.string.left_hand)), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            if (this.actionCompleted) {
                this.actionCompleted = false;
                TutorialView tutorialView = TutorialView.this;
                tutorialView.switchState(tutorialView.typeLetterA$ar$class_merging, 0L);
                return;
            }
            TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onSwipeProduced(Swipe swipe) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Intro implements TutorialState {
        public Intro() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String audialAnnouncementRepeated() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 2;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        /* renamed from: lambda$loadView$0$com-google-android-accessibility-brailleime-tutorial-TutorialView$Intro$ar$ds, reason: not valid java name */
        public final /* synthetic */ void m85x247088b4() {
            TutorialState tutorialState;
            TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleImeActivated();
            TutorialView tutorialView = TutorialView.this;
            if (tutorialView.isTabletop) {
                tutorialState = tutorialView.holdSixFingers;
            } else {
                tutorialState = tutorialView.rotateOrientation;
            }
            tutorialView.switchState(tutorialState, 0L);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onBrailleImeInactivated();
            View.inflate(TutorialView.this.getContext(), R.layout.tutorial_intro, TutorialView.this);
            ((AnimationDrawable) ((ImageView) TutorialView.this.findViewById(R.id.tutorial_animation)).getDrawable()).start();
            TutorialView.this.findViewById(R.id.next_button).setOnClickListener(new ActionBarContextView.AnonymousClass1(this, 20, null));
            TutorialView.this.findViewById(R.id.leave_keyboard_button).setOnClickListener(new UIManager$$ExternalSyntheticLambda2(this, 1));
            TutorialView.this.postDelayed(new ContextMenuDialog$$ExternalSyntheticLambda5(this, 12), 1000L);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onUtteranceCompleted() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onSwipeProduced(Swipe swipe) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RotateOrientation implements TutorialState {
        private boolean actionCompleted;

        public RotateOrientation() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            return TutorialView.this.getResources().getString(R.string.rotate_orientation_inactive_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 3;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            TutorialView.this.inputView.setAlpha(0.0f);
            TutorialView tutorialView = TutorialView.this;
            tutorialView.addView(tutorialView.inputView);
            View.inflate(TutorialView.this.getContext(), R.layout.tutorial_rotate_orientation, TutorialView.this);
            TutorialView.this.findViewById(R.id.tap_to_continue).setBackground(new TapMeAnimationDrawable(TutorialView.this.context));
            TutorialView tutorialView2 = TutorialView.this;
            tutorialView2.speakAnnouncement(tutorialView2.getResources().getString(R.string.rotate_orientation_announcement, tutorialView2.getResources().getString(R.string.rotate_orientation_inactive_announcement)), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onDoubleTap() {
            TutorialState tutorialState;
            if (!this.actionCompleted) {
                this.actionCompleted = true;
                TutorialView tutorialView = TutorialView.this;
                int i = TaskApiCall.Builder.getInstance$ar$class_merging().methodKey;
                if (i != 2 && i != 182) {
                    tutorialState = TutorialView.this.typeLetterA$ar$class_merging;
                } else {
                    tutorialState = TutorialView.this.rotateOrientationContinue;
                }
                tutorialView.switchState(tutorialState, 500L);
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onSwipeProduced(Swipe swipe) {
            int i = swipe.touchCount;
            Swipe.Direction direction = swipe.direction;
            if (direction == Swipe.Direction.DOWN && i == 3) {
                TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextInputMethod();
            } else if (direction == Swipe.Direction.LEFT && i == 3 && TaskApiCall.Builder.getInstance$ar$class_merging().methodKey == 2) {
                TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSwitchToNextInputMethod();
            }
            TutorialView.this.vibrateForSwipeGestures(swipe);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RotateOrientationContinue implements TutorialState {
        private boolean actionCompleted;
        public ValueAnimator animator;
        final OrientationMonitor$Callback orientationMonitorCallBack = new AnonymousClass1(this);

        /* compiled from: PG */
        /* renamed from: com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements OrientationMonitor$Callback {
            public final /* synthetic */ Object TutorialView$RotateOrientationContinue$1$ar$this$1;

            public AnonymousClass1(Object obj) {
                this.TutorialView$RotateOrientationContinue$1$ar$this$1 = obj;
            }
        }

        public RotateOrientationContinue() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            return TutorialView.this.getResources().getString(R.string.rotate_orientation_continue_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 4;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            TutorialView tutorialView = TutorialView.this;
            ((BrailleIme) tutorialView.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetryingNameResolver$ResolutionResultListener$ar$this$0).orientationCallbackDelegate = this.orientationMonitorCallBack;
            tutorialView.inputView.setAlpha(0.0f);
            TutorialView tutorialView2 = TutorialView.this;
            tutorialView2.addView(tutorialView2.inputView);
            View.inflate(TutorialView.this.getContext(), R.layout.tutorial_rotate_orientation, TutorialView.this);
            TutorialView.this.findViewById(R.id.tap_to_continue).setBackground(new TapMeAnimationDrawable(TutorialView.this.context));
            TextView textView = (TextView) TutorialView.this.findViewById(R.id.highlight_description);
            CharSequence text = textView.getText();
            int color = TutorialView.this.getResources().getColor(R.color.text_highlight_color, null);
            ValueAnimator ofInt = ValueAnimator.ofInt(1, text.length());
            this.animator = ofInt;
            ofInt.setDuration(text.length() * 10);
            this.animator.start();
            this.animator.addUpdateListener(new TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0(text, color, textView, 0));
            this.animator.addListener(new TutorialView$TypeLetterA$1(this, 1));
            TutorialView tutorialView3 = TutorialView.this;
            tutorialView3.speakAnnouncement(tutorialView3.getResources().getString(R.string.rotate_orientation_continue_announcement), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onDoubleTap() {
            if (!this.actionCompleted) {
                this.actionCompleted = true;
                TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.unregisterOrientationChange();
                TutorialView tutorialView = TutorialView.this;
                tutorialView.switchState(tutorialView.typeLetterA$ar$class_merging, 0L);
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onSwipeProduced(Swipe swipe) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SwipeDown3Fingers implements TutorialState {
        private boolean actionCompleted;
        private final /* synthetic */ int switching_field;
        public final /* synthetic */ TutorialView this$0;

        public SwipeDown3Fingers(TutorialView tutorialView, int i) {
            this.switching_field = i;
            this.this$0 = tutorialView;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return this.this$0.getResources().getString(R.string.type_letter_a_inactive_announcement);
                                }
                                return this.this$0.getResources().getString(R.string.swipe_up_announcement);
                            }
                            return this.this$0.getResources().getString(R.string.swipe_right_announcement);
                        }
                        return this.this$0.getResources().getString(R.string.swipe_left_announcement);
                    }
                    return this.this$0.getResources().getString(R.string.swipe_down_announcement);
                }
                return this.this$0.getResources().getString(R.string.swipe_down_2_fingers_announcement);
            }
            return this.this$0.getResources().getString(R.string.swipe_down_3_fingers_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return 5;
                                }
                                return 9;
                            }
                            return 8;
                        }
                        return 7;
                    }
                    return 10;
                }
                return 11;
            }
            return 12;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String hintText() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return this.this$0.getResources().getString(R.string.instruction_type_letter_a);
                                }
                                return this.this$0.getResources().getString(R.string.instruction_swipe_up);
                            }
                            return this.this$0.getResources().getString(R.string.instruction_swipe_right);
                        }
                        return this.this$0.getResources().getString(R.string.instruction_swipe_left);
                    }
                    return this.this$0.getResources().getString(R.string.instruction_swipe_down);
                }
                return this.this$0.getResources().getString(R.string.instruction_swipe_down_2_fingers);
            }
            return this.this$0.getResources().getString(R.string.instruction_swipe_down_3_fingers);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final boolean isActionCompleted() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    return this.actionCompleted;
                                }
                                return this.actionCompleted;
                            }
                            return this.actionCompleted;
                        }
                        return this.actionCompleted;
                    }
                    return this.actionCompleted;
                }
                return this.actionCompleted;
            }
            return this.actionCompleted;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    this.this$0.dotBlockView.setAlpha(0.0f);
                                    this.this$0.dotBlockView.animate().alpha(1.0f).setDuration(600L);
                                    TutorialView tutorialView = this.this$0;
                                    tutorialView.addView(tutorialView.dotBlockView);
                                    TutorialView tutorialView2 = this.this$0;
                                    List dotTargetsForBrailleCharacter = tutorialView2.getDotTargetsForBrailleCharacter(BrailleTranslateUtilsUeb.LETTER_A);
                                    TutorialView tutorialView3 = this.this$0;
                                    tutorialView2.dotsFlashingAnimationView = new DotsFlashingAnimationView(tutorialView2.context, dotTargetsForBrailleCharacter, tutorialView3.orientation, tutorialView3.isTabletop);
                                    this.this$0.dotsFlashingAnimationView.setVisibility(4);
                                    this.this$0.inputView.setAlpha(0.0f);
                                    this.this$0.inputView.animate().alpha(1.0f).setDuration(500L).setStartDelay(600L).setListener(new TutorialView$TypeLetterA$1(this, 0));
                                    TutorialView tutorialView4 = this.this$0;
                                    tutorialView4.addView(tutorialView4.inputView);
                                    TutorialView tutorialView5 = this.this$0;
                                    tutorialView5.addView(tutorialView5.dotsFlashingAnimationView);
                                    this.this$0.tutorialAnimationView.reset();
                                    TutorialView tutorialView6 = this.this$0;
                                    tutorialView6.addView(tutorialView6.tutorialAnimationView);
                                    TutorialView tutorialView7 = this.this$0;
                                    tutorialView7.speakAnnouncement(tutorialView7.getResources().getString(R.string.type_letter_a_announcement), 0);
                                    return;
                                }
                                TutorialView tutorialView8 = this.this$0;
                                tutorialView8.addView(tutorialView8.inputView);
                                this.this$0.tutorialAnimationView.reset();
                                this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(1, 2);
                                TutorialView tutorialView9 = this.this$0;
                                tutorialView9.tutorialAnimationView.startHintToastAnimation(tutorialView9.state.hintText());
                                TutorialView tutorialView10 = this.this$0;
                                tutorialView10.addView(tutorialView10.tutorialAnimationView);
                                TutorialView tutorialView11 = this.this$0;
                                tutorialView11.speakAnnouncement(tutorialView11.getResources().getString(R.string.swipe_up_announcement), 0);
                                return;
                            }
                            TutorialView tutorialView12 = this.this$0;
                            tutorialView12.addView(tutorialView12.inputView);
                            this.this$0.tutorialAnimationView.reset();
                            this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(1, 4);
                            TutorialView tutorialView13 = this.this$0;
                            tutorialView13.tutorialAnimationView.startHintToastAnimation(tutorialView13.state.hintText());
                            TutorialView tutorialView14 = this.this$0;
                            tutorialView14.addView(tutorialView14.tutorialAnimationView);
                            TutorialView tutorialView15 = this.this$0;
                            tutorialView15.speakAnnouncement(tutorialView15.getResources().getString(R.string.swipe_right_announcement), 0);
                            return;
                        }
                        TutorialView tutorialView16 = this.this$0;
                        tutorialView16.addView(tutorialView16.inputView);
                        this.this$0.tutorialAnimationView.reset();
                        this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(1, 3);
                        TutorialView tutorialView17 = this.this$0;
                        tutorialView17.tutorialAnimationView.startHintToastAnimation(tutorialView17.state.hintText());
                        TutorialView tutorialView18 = this.this$0;
                        tutorialView18.addView(tutorialView18.tutorialAnimationView);
                        TutorialView tutorialView19 = this.this$0;
                        tutorialView19.speakAnnouncement(tutorialView19.getResources().getString(R.string.swipe_left_announcement), 0);
                        return;
                    }
                    TutorialView tutorialView20 = this.this$0;
                    tutorialView20.addView(tutorialView20.inputView);
                    this.this$0.tutorialAnimationView.reset();
                    this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(1, 1);
                    TutorialView tutorialView21 = this.this$0;
                    tutorialView21.tutorialAnimationView.startHintToastAnimation(tutorialView21.state.hintText());
                    TutorialView tutorialView22 = this.this$0;
                    tutorialView22.addView(tutorialView22.tutorialAnimationView);
                    TutorialView tutorialView23 = this.this$0;
                    tutorialView23.speakAnnouncement(tutorialView23.getResources().getString(R.string.swipe_down_announcement), 0);
                    return;
                }
                TutorialView tutorialView24 = this.this$0;
                tutorialView24.addView(tutorialView24.inputView);
                this.this$0.tutorialAnimationView.reset();
                this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(2, 1);
                TutorialView tutorialView25 = this.this$0;
                tutorialView25.tutorialAnimationView.startHintToastAnimation(tutorialView25.state.hintText());
                TutorialView tutorialView26 = this.this$0;
                tutorialView26.addView(tutorialView26.tutorialAnimationView);
                TutorialView tutorialView27 = this.this$0;
                tutorialView27.speakAnnouncement(tutorialView27.getResources().getString(R.string.swipe_down_2_fingers_announcement), 0);
                return;
            }
            TutorialView tutorialView28 = this.this$0;
            tutorialView28.addView(tutorialView28.inputView);
            this.this$0.tutorialAnimationView.reset();
            this.this$0.tutorialAnimationView.startSwipeAnimation$ar$edu(3, 1);
            TutorialView tutorialView29 = this.this$0;
            tutorialView29.tutorialAnimationView.startHintToastAnimation(tutorialView29.state.hintText());
            TutorialView tutorialView30 = this.this$0;
            tutorialView30.addView(tutorialView30.tutorialAnimationView);
            TutorialView tutorialView31 = this.this$0;
            tutorialView31.speakAnnouncement(tutorialView31.getResources().getString(R.string.swipe_down_3_fingers_announcement), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onBrailleProduced(BrailleCharacter brailleCharacter) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    if (this.actionCompleted) {
                                        return;
                                    }
                                    UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                                    if (brailleCharacter.toByte() == 1) {
                                        TutorialView tutorialView = this.this$0;
                                        tutorialView.tutorialAnimationView.startActionResultAnimation(ContextDataProvider.toUpperCase(tutorialView.translator.translateToPrint(new BrailleWord(brailleCharacter))));
                                        this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                                        this.this$0.speakAnnouncement(this.this$0.translator.translateToPrint(new BrailleWord(brailleCharacter)), 1000);
                                        this.actionCompleted = true;
                                        return;
                                    }
                                    TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                                    return;
                                }
                                if (this.actionCompleted) {
                                    return;
                                }
                                UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                                TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                                return;
                            }
                            if (this.actionCompleted) {
                                return;
                            }
                            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                            TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                            return;
                        }
                        if (this.actionCompleted) {
                            return;
                        }
                        UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                        TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                        return;
                    }
                    if (this.actionCompleted) {
                        return;
                    }
                    UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                    TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                    return;
                }
                if (this.actionCompleted) {
                    return;
                }
                UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
                return;
            }
            if (this.actionCompleted) {
                return;
            }
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this.this$0.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
            TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(this.this$0, brailleCharacter);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onSwipeProduced(Swipe swipe) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    if (this.actionCompleted) {
                                        return;
                                    }
                                    this.this$0.speakSwipeEvent(swipe);
                                    this.this$0.vibrateForSwipeGestures(swipe);
                                    return;
                                }
                                if (this.actionCompleted) {
                                    return;
                                }
                                int i2 = swipe.touchCount;
                                if (swipe.direction == Swipe.Direction.UP && i2 == 1) {
                                    this.this$0.tutorialAnimationView.stopSwipeAnimation();
                                    TutorialView tutorialView = this.this$0;
                                    tutorialView.tutorialAnimationView.startActionResultAnimation(tutorialView.getResources().getString(R.string.result_swipe_up));
                                    this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                                    TutorialView tutorialView2 = this.this$0;
                                    tutorialView2.speakAnnouncement(tutorialView2.getResources().getString(R.string.result_swipe_up), 1000);
                                    this.actionCompleted = true;
                                } else {
                                    this.this$0.speakSwipeEvent(swipe);
                                }
                                this.this$0.vibrateForSwipeGestures(swipe);
                                return;
                            }
                            if (this.actionCompleted) {
                                return;
                            }
                            int i3 = swipe.touchCount;
                            if (swipe.direction == Swipe.Direction.LEFT && i3 == 1) {
                                this.this$0.tutorialAnimationView.stopSwipeAnimation();
                                TutorialView tutorialView3 = this.this$0;
                                tutorialView3.tutorialAnimationView.startActionResultAnimation(tutorialView3.getResources().getString(R.string.result_swipe_right));
                                this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                                TutorialView tutorialView4 = this.this$0;
                                tutorialView4.speakAnnouncement(tutorialView4.getResources().getString(R.string.result_swipe_right), 1000);
                                this.actionCompleted = true;
                            } else {
                                this.this$0.speakSwipeEvent(swipe);
                            }
                            this.this$0.vibrateForSwipeGestures(swipe);
                            return;
                        }
                        if (this.actionCompleted) {
                            return;
                        }
                        int i4 = swipe.touchCount;
                        if (swipe.direction == Swipe.Direction.RIGHT && i4 == 1) {
                            this.this$0.tutorialAnimationView.stopSwipeAnimation();
                            TutorialView tutorialView5 = this.this$0;
                            tutorialView5.tutorialAnimationView.startActionResultAnimation(tutorialView5.getResources().getString(R.string.result_swipe_left));
                            this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                            TutorialView tutorialView6 = this.this$0;
                            tutorialView6.speakAnnouncement(tutorialView6.getResources().getString(R.string.swipe_left_action_result_announcement), 1000);
                            this.actionCompleted = true;
                        } else {
                            this.this$0.speakSwipeEvent(swipe);
                        }
                        this.this$0.vibrateForSwipeGestures(swipe);
                        return;
                    }
                    if (this.actionCompleted) {
                        return;
                    }
                    int i5 = swipe.touchCount;
                    if (swipe.direction == Swipe.Direction.DOWN && i5 == 1) {
                        this.this$0.tutorialAnimationView.stopSwipeAnimation();
                        TutorialView tutorialView7 = this.this$0;
                        tutorialView7.tutorialAnimationView.startActionResultAnimation(tutorialView7.getResources().getString(R.string.result_swipe_down));
                        this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                        TutorialView tutorialView8 = this.this$0;
                        tutorialView8.speakAnnouncement(tutorialView8.getResources().getString(R.string.result_swipe_down), 1000);
                        this.actionCompleted = true;
                    } else {
                        this.this$0.speakSwipeEvent(swipe);
                    }
                    this.this$0.vibrateForSwipeGestures(swipe);
                    return;
                }
                if (this.actionCompleted) {
                    return;
                }
                int i6 = swipe.touchCount;
                if (swipe.direction == Swipe.Direction.DOWN && i6 == 2) {
                    this.this$0.tutorialAnimationView.stopSwipeAnimation();
                    TutorialView tutorialView9 = this.this$0;
                    tutorialView9.tutorialAnimationView.startActionResultAnimation(tutorialView9.getResources().getString(R.string.result_swipe_down_2_fingers));
                    this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                    TutorialView tutorialView10 = this.this$0;
                    tutorialView10.speakAnnouncement(tutorialView10.getResources().getString(R.string.swipe_down_2_fingers_completed_announcement), 1000);
                    this.actionCompleted = true;
                } else {
                    this.this$0.speakSwipeEvent(swipe);
                }
                this.this$0.vibrateForSwipeGestures(swipe);
                return;
            }
            if (this.actionCompleted) {
                return;
            }
            int i7 = swipe.touchCount;
            if (swipe.direction == Swipe.Direction.DOWN && i7 == 3) {
                this.this$0.tutorialAnimationView.stopSwipeAnimation();
                TutorialView tutorialView11 = this.this$0;
                tutorialView11.tutorialAnimationView.startActionResultAnimation(tutorialView11.getResources().getString(R.string.result_swipe_down_3_fingers));
                this.this$0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                TutorialView tutorialView12 = this.this$0;
                tutorialView12.speakAnnouncement(tutorialView12.getResources().getString(R.string.swipe_down_3_fingers_completed_announcement), 1000);
                this.actionCompleted = true;
            } else {
                this.this$0.speakSwipeEvent(swipe);
            }
            this.this$0.vibrateForSwipeGestures(swipe);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i != 5) {
                                    if (this.actionCompleted) {
                                        this.actionCompleted = false;
                                        TutorialView tutorialView = this.this$0;
                                        tutorialView.switchState(tutorialView.tapLetterBCD, 0L);
                                        return;
                                    }
                                    this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                                    return;
                                }
                                if (this.actionCompleted) {
                                    this.actionCompleted = false;
                                    TutorialView tutorialView2 = this.this$0;
                                    tutorialView2.switchState(tutorialView2.swipeDown$ar$class_merging, 0L);
                                    return;
                                }
                                this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                                return;
                            }
                            if (this.actionCompleted) {
                                this.actionCompleted = false;
                                TutorialView tutorialView3 = this.this$0;
                                tutorialView3.switchState(tutorialView3.swipeUp$ar$class_merging, 0L);
                                return;
                            }
                            this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                            return;
                        }
                        if (this.actionCompleted) {
                            this.actionCompleted = false;
                            TutorialView tutorialView4 = this.this$0;
                            tutorialView4.switchState(tutorialView4.swipeRight$ar$class_merging, 0L);
                            return;
                        }
                        this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                        return;
                    }
                    if (this.actionCompleted) {
                        this.actionCompleted = false;
                        TutorialView tutorialView5 = this.this$0;
                        tutorialView5.switchState(tutorialView5.swipeDown2Fingers$ar$class_merging, 0L);
                        return;
                    }
                    this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                    return;
                }
                if (this.actionCompleted) {
                    this.actionCompleted = false;
                    TutorialView tutorialView6 = this.this$0;
                    tutorialView6.switchState(tutorialView6.swipeDown3Fingers, 0L);
                    return;
                }
                this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
                return;
            }
            if (this.actionCompleted) {
                this.actionCompleted = false;
                TutorialView tutorialView7 = this.this$0;
                tutorialView7.switchState(tutorialView7.swipeUp3Fingers, 0L);
                return;
            }
            this.this$0.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SwipeUp3Fingers implements TutorialState {
        private boolean actionCompleted;

        public SwipeUp3Fingers() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            return TutorialView.this.getResources().getString(R.string.swipe_up_3_fingers_announcement);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 13;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String hintText() {
            return TutorialView.this.getResources().getString(R.string.instruction_swipe_up_3_fingers);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            this.actionCompleted = false;
            TutorialView tutorialView = TutorialView.this;
            tutorialView.addView(tutorialView.inputView);
            TutorialView.this.tutorialAnimationView.reset();
            TutorialView.this.tutorialAnimationView.startSwipeAnimation$ar$edu(3, 2);
            TutorialView tutorialView2 = TutorialView.this;
            tutorialView2.tutorialAnimationView.startHintToastAnimation(tutorialView2.state.hintText());
            TutorialView tutorialView3 = TutorialView.this;
            tutorialView3.addView(tutorialView3.tutorialAnimationView);
            TutorialView tutorialView4 = TutorialView.this;
            tutorialView4.speakAnnouncement(tutorialView4.getResources().getString(R.string.swipe_up_3_fingers_announcement), 0);
        }

        public final TutorialState nextState() {
            return TutorialView.this.contextMenuOpened;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onBrailleProduced(BrailleCharacter brailleCharacter) {
            if (this.actionCompleted) {
                return;
            }
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(TutorialView.this.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
            TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(TutorialView.this, brailleCharacter);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onSwipeProduced(Swipe swipe) {
            if (!this.actionCompleted) {
                int i = swipe.touchCount;
                Swipe.Direction direction = swipe.direction;
                if (direction == Swipe.Direction.UP && i == 3) {
                    TutorialView.this.switchState(nextState(), 0L);
                    this.actionCompleted = true;
                } else if (direction == Swipe.Direction.RIGHT && i == 3) {
                    if (TaskApiCall.Builder.getInstance$ar$class_merging().methodKey == 2) {
                        TutorialView.this.switchState(nextState(), 0L);
                        this.actionCompleted = true;
                    }
                } else {
                    TutorialView.this.speakSwipeEvent(swipe);
                }
                TutorialView.this.vibrateForSwipeGestures(swipe);
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            if (!this.actionCompleted) {
                TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TutorialFinished implements TutorialState {
        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String audialAnnouncementRepeated() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 1;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ String hintText() {
            return "";
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ boolean isActionCompleted() {
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onUtteranceCompleted() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onBrailleProduced(BrailleCharacter brailleCharacter) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onSwipeProduced(Swipe swipe) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TypeLetterBCD implements TutorialState {
        private boolean actionCompleted;
        private final List remainLetters = new ArrayList(Arrays.asList('B', 'C', 'D'));

        public TypeLetterBCD() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String audialAnnouncementRepeated() {
            switch (((Character) this.remainLetters.get(0)).charValue()) {
                case 'B':
                    return TutorialView.this.getResources().getString(R.string.type_letter_inactive_announcement, BrailleTranslateUtils.getDotsText(TutorialView.this.getResources(), BrailleTranslateUtilsUeb.LETTER_B), this.remainLetters.get(0));
                case 'C':
                    return TutorialView.this.getResources().getString(R.string.type_letter_inactive_announcement, BrailleTranslateUtils.getDotsText(TutorialView.this.getResources(), BrailleTranslateUtilsUeb.LETTER_C), this.remainLetters.get(0));
                case 'D':
                    return TutorialView.this.getResources().getString(R.string.type_letter_inactive_announcement, BrailleTranslateUtils.getDotsText(TutorialView.this.getResources(), BrailleTranslateUtilsUeb.LETTER_D), this.remainLetters.get(0));
                default:
                    return TutorialView.this.getResources().getString(R.string.type_letter_inactive_announcement, BrailleTranslateUtils.getDotsText(TutorialView.this.getResources(), BrailleTranslateUtilsUeb.LETTER_B), this.remainLetters.get(0));
            }
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final int getCurrentState$ar$edu() {
            return 6;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final String hintText() {
            return TutorialView.this.getResources().getString(R.string.instruction_type_letter_bcd);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final boolean isActionCompleted() {
            return this.actionCompleted;
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void loadView() {
            BrailleCharacter brailleCharacter;
            String audialAnnouncementRepeated;
            TutorialView tutorialView = TutorialView.this;
            tutorialView.addView(tutorialView.inputView);
            switch (((Character) this.remainLetters.get(0)).charValue()) {
                case 'B':
                    brailleCharacter = BrailleTranslateUtilsUeb.LETTER_B;
                    break;
                case 'C':
                    brailleCharacter = BrailleTranslateUtilsUeb.LETTER_C;
                    break;
                case 'D':
                    brailleCharacter = BrailleTranslateUtilsUeb.LETTER_D;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: ".concat(String.valueOf(String.valueOf(this.remainLetters.get(0)))));
            }
            TutorialView tutorialView2 = TutorialView.this;
            tutorialView2.dotsFlashingAnimationView = new DotsFlashingAnimationView(tutorialView2.context, tutorialView2.getDotTargetsForBrailleCharacter(brailleCharacter), tutorialView2.orientation, tutorialView2.isTabletop);
            TutorialView tutorialView3 = TutorialView.this;
            tutorialView3.addView(tutorialView3.dotsFlashingAnimationView);
            TutorialView.this.tutorialAnimationView.reset();
            TutorialView tutorialView4 = TutorialView.this;
            tutorialView4.tutorialAnimationView.startHintToastAnimation(tutorialView4.state.hintText());
            TutorialView tutorialView5 = TutorialView.this;
            tutorialView5.addView(tutorialView5.tutorialAnimationView);
            TutorialView tutorialView6 = TutorialView.this;
            if (this.remainLetters.size() == 3) {
                audialAnnouncementRepeated = TutorialView.this.getResources().getString(R.string.instruction_type_letter_bcd);
            } else {
                audialAnnouncementRepeated = audialAnnouncementRepeated();
            }
            tutorialView6.speakAnnouncement(audialAnnouncementRepeated, 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onBrailleProduced(BrailleCharacter brailleCharacter) {
            if (this.actionCompleted) {
                return;
            }
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(TutorialView.this.context).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
            String upperCase = ContextDataProvider.toUpperCase(TutorialView.this.translator.translateToPrint(new BrailleWord(brailleCharacter)));
            char c = (char) 65535;
            if (upperCase.length() > 0) {
                c = Character.valueOf(upperCase.charAt(0));
            }
            if (this.remainLetters.contains(c)) {
                TutorialView.this.tutorialAnimationView.startActionResultAnimation(upperCase);
                TutorialView.this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onPlaySound(FeedbackManager$Type.BEEP);
                TutorialView.this.speakAnnouncement(TutorialView.this.translator.translateToPrint(new BrailleWord(brailleCharacter)), 1000);
                this.actionCompleted = true;
                this.remainLetters.remove(c);
                return;
            }
            TutorialView.m84$$Nest$mspeakBrailleCharacter$ar$ds(TutorialView.this, brailleCharacter);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onSwipeProduced(Swipe swipe) {
            if (this.actionCompleted) {
                return;
            }
            TutorialView.this.speakSwipeEvent(swipe);
            TutorialView.this.vibrateForSwipeGestures(swipe);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final void onUtteranceCompleted() {
            TutorialState tutorialState;
            if (this.actionCompleted) {
                this.actionCompleted = false;
                TutorialView tutorialView = TutorialView.this;
                if (this.remainLetters.isEmpty()) {
                    tutorialState = TutorialView.this.swipeLeft$ar$class_merging;
                } else {
                    tutorialState = this;
                }
                tutorialView.switchState(tutorialState, 0L);
                return;
            }
            TutorialView.this.speakAnnouncement(audialAnnouncementRepeated(), 0);
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onDoubleTap() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onTwoStepCalibrationRetry$ar$ds() {
        }

        @Override // com.google.android.accessibility.brailleime.tutorial.TutorialView.TutorialState
        public final /* synthetic */ void onCalibration$ar$edu$ar$ds(int i) {
        }
    }
}
