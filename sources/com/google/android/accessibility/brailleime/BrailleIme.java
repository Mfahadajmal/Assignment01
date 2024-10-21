package com.google.android.accessibility.brailleime;

import _COROUTINE._BOUNDARY;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.graphics.Region;
import android.inputmethodservice.InputMethodService;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatDelegateImpl;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.core.content.ContextCompat$Api23Impl;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.common.translate.EditBuffer;
import com.google.android.accessibility.braille.common.translate.EditBufferArabic2;
import com.google.android.accessibility.braille.common.translate.EditBufferBritish2;
import com.google.android.accessibility.braille.common.translate.EditBufferBulgarian;
import com.google.android.accessibility.braille.common.translate.EditBufferCatalan;
import com.google.android.accessibility.braille.common.translate.EditBufferCroatian;
import com.google.android.accessibility.braille.common.translate.EditBufferDanish;
import com.google.android.accessibility.braille.common.translate.EditBufferDanish2;
import com.google.android.accessibility.braille.common.translate.EditBufferDutch;
import com.google.android.accessibility.braille.common.translate.EditBufferEbae2;
import com.google.android.accessibility.braille.common.translate.EditBufferEnComp6;
import com.google.android.accessibility.braille.common.translate.EditBufferFrench;
import com.google.android.accessibility.braille.common.translate.EditBufferFrench2;
import com.google.android.accessibility.braille.common.translate.EditBufferGerman;
import com.google.android.accessibility.braille.common.translate.EditBufferGerman2;
import com.google.android.accessibility.braille.common.translate.EditBufferGreek;
import com.google.android.accessibility.braille.common.translate.EditBufferHungarian;
import com.google.android.accessibility.braille.common.translate.EditBufferHungarian2;
import com.google.android.accessibility.braille.common.translate.EditBufferItalian;
import com.google.android.accessibility.braille.common.translate.EditBufferKorean;
import com.google.android.accessibility.braille.common.translate.EditBufferKorean2;
import com.google.android.accessibility.braille.common.translate.EditBufferLatvian;
import com.google.android.accessibility.braille.common.translate.EditBufferLithuanian;
import com.google.android.accessibility.braille.common.translate.EditBufferNorwegian2;
import com.google.android.accessibility.braille.common.translate.EditBufferPolish;
import com.google.android.accessibility.braille.common.translate.EditBufferPortuguese;
import com.google.android.accessibility.braille.common.translate.EditBufferPortuguese2;
import com.google.android.accessibility.braille.common.translate.EditBufferRussian;
import com.google.android.accessibility.braille.common.translate.EditBufferSerbian;
import com.google.android.accessibility.braille.common.translate.EditBufferSpanish;
import com.google.android.accessibility.braille.common.translate.EditBufferSpanish2;
import com.google.android.accessibility.braille.common.translate.EditBufferStub;
import com.google.android.accessibility.braille.common.translate.EditBufferSwedish2;
import com.google.android.accessibility.braille.common.translate.EditBufferTurkish2;
import com.google.android.accessibility.braille.common.translate.EditBufferUeb2;
import com.google.android.accessibility.braille.common.translate.EditBufferUnContracted;
import com.google.android.accessibility.braille.common.translate.EditBufferUrdu2;
import com.google.android.accessibility.braille.common.translate.EditBufferVietnamese;
import com.google.android.accessibility.braille.common.translate.EditBufferVietnamese2;
import com.google.android.accessibility.braille.common.translate.EditBufferWelsh2;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.accessibility.braille.translate.TranslatorFactory;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalyticsHelper$SessionCache;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationState;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$ErrorType;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.accessibility.brailleime.dialog.TalkBackOffDialog;
import com.google.android.accessibility.brailleime.dialog.TalkBackSuspendDialog;
import com.google.android.accessibility.brailleime.dialog.TooFewTouchPointsDialog;
import com.google.android.accessibility.brailleime.dialog.ViewAttachedDialog;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.input.DotHoldSwipe;
import com.google.android.accessibility.brailleime.input.Swipe;
import com.google.android.accessibility.brailleime.keyboardview.AccessibilityOverlayKeyboardView;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.brailleime.keyboardview.StandardKeyboardView;
import com.google.android.accessibility.brailleime.tutorial.TutorialView;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.common.collect.ImmutableSet;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.BrailleKeyboardConfig;
import io.grpc.internal.ManagedChannelImpl;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import java.lang.Thread;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleIme extends InputMethodService {
    public static BrailleDisplayForBrailleIme brailleDisplayForBrailleIme;
    public static BrailleIme instance;
    public static WindowTrackerFactory talkBackForBrailleCommon$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public static TalkBackForBrailleIme talkBackForBrailleIme;
    public boolean brailleDisplayConnectedAndNotSuspended;
    public BrailleImeAnalytics brailleImeAnalytics;
    public BrailleImeGestureController brailleImeGestureController;
    public Handler calibrationAnnouncementHandler;
    public ViewAttachedDialog contextMenuDialog;
    private boolean deviceSupportsAtLeast5Pointers;
    public EditBuffer editBuffer;
    public EscapeReminder escapeReminder;
    public ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    private boolean isVisible;
    public KeyboardView keyboardView;
    public Verifier layoutOrientator$ar$class_merging;
    public Handler mainHandler;
    private int orientation;
    public OrientationMonitor$Callback orientationCallbackDelegate;
    public Thread.UncaughtExceptionHandler originalDefaultUncaughtExceptionHandler;
    private ViewAttachedDialog talkBackSuspendDialog;
    private ViewAttachedDialog talkbackOffDialog;
    private ViewAttachedDialog tooFewTouchPointsDialog;
    public int tutorialState$ar$edu;
    private TypoHandler typoHandler;
    public final AtomicInteger instructionSpeechId = new AtomicInteger();
    private final RetryingNameResolver.ResolutionResultListener layoutOrientatorCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final Thread.UncaughtExceptionHandler localUncaughtExceptionHandler = new ManagedChannelImpl.AnonymousClass3(this, 1);
    private final BroadcastReceiver screenOffReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.brailleime.BrailleIme.3
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                _BOUNDARY.d("BrailleIme", "screen off");
                BrailleIme.this.deactivateIfNeeded$ar$ds();
                BrailleIme.this.dismissDialogs();
                BrailleIme.this.brailleImeAnalytics.collectSessionEvents();
                return;
            }
            if (intent.getAction().equals("android.intent.action.SCREEN_ON")) {
                _BOUNDARY.d("BrailleIme", "screen on");
                KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
                _BOUNDARY.d("BrailleIme", "screen is locked: " + keyguardManager.isKeyguardLocked());
                if (!keyguardManager.isKeyguardLocked()) {
                    BrailleIme.this.activateIfNeeded();
                }
            }
        }
    };
    private final BroadcastReceiver closeSystemDialogsReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.brailleime.BrailleIme.4
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (intent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && (stringExtra = intent.getStringExtra("reason")) != null) {
                _BOUNDARY.d("BrailleIme", "action:" + intent.getAction() + ",reason:" + stringExtra);
                if (stringExtra.equals("homekey") || stringExtra.equals("recentapps") || stringExtra.equals("voiceinteraction")) {
                    BrailleIme.this.dismissDialogs();
                }
            }
        }
    };
    private final BroadcastReceiver imeChangeListener = new BroadcastReceiver() { // from class: com.google.android.accessibility.brailleime.BrailleIme.5
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.INPUT_METHOD_CHANGED")) {
                BrailleIme.this.dismissDialogs();
            }
        }
    };
    public final FloatingActionButton.ShadowDelegateImpl brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    private final ContentObserver accessibilityServiceStatusChangeObserver = new ContentObserver(new Handler()) { // from class: com.google.android.accessibility.brailleime.BrailleIme.7
        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            super.onChange(z);
            if (!BrailleIme.this.isInputViewShown()) {
                return;
            }
            if (SpannableUtils$IdentifierSpan.isAccessibilityServiceEnabled(BrailleIme.this, AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE.flattenToShortString())) {
                _BOUNDARY.d("BrailleIme", "TalkBack becomes active.");
                BrailleIme.this.activateBrailleIme();
                return;
            }
            _BOUNDARY.d("BrailleIme", "TalkBack becomes inactive.");
            if (SpannableUtils$IdentifierSpan.areMultipleImesEnabled(BrailleIme.this)) {
                BrailleIme.this.switchToNextInputMethod();
            } else {
                BrailleIme.this.deactivateIfNeeded$ar$ds();
                BrailleIme.this.showTalkBackOffDialog();
            }
        }
    };
    private final RetryingNameResolver.ResolutionResultListener brailleImeGestureCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final OrientationMonitor$Callback orientationMonitorCallback = new TutorialView.RotateOrientationContinue.AnonymousClass1(this);
    public final BrailleInputView.Callback inputPlaneCallback = new AnonymousClass10();
    private final RetryingNameResolver.ResolutionResultListener keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final RetryingNameResolver.ResolutionResultListener brailleDisplayKeyboardCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 9);
    public final ContextMenuDialog.Callback contextMenuDialogCallback = new TutorialView.AnonymousClass2(this, 1);
    private final RetryingNameResolver.ResolutionResultListener tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final RetryingNameResolver.ResolutionResultListener escapeReminderCallback$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final RetryingNameResolver.ResolutionResultListener talkBackOffDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final HapticPatternParser$$ExternalSyntheticLambda1 talkBackSuspendDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    private final FloatingActionButton.ShadowDelegateImpl tooFewTouchPointsDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    private final PhoneStateListener phoneStateListener = new PhoneStateListener() { // from class: com.google.android.accessibility.brailleime.BrailleIme.20
        @Override // android.telephony.PhoneStateListener
        public final void onCallStateChanged(int i, String str) {
            if (i == 1 && BrailleIme.this.keyboardView.isViewContainerCreated()) {
                BrailleIme.this.hideSelf();
            }
        }
    };
    public final HapticPatternParser$$ExternalSyntheticLambda1 brailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.BrailleIme$10, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass10 implements BrailleInputView.Callback {
        public AnonymousClass10() {
        }

        private static final int mapCalibrationToType$ar$edu$ar$ds(int i) {
            if (i != 0) {
                int i2 = i - 1;
                if (i2 == 0) {
                    return 2;
                }
                if (i2 == 1) {
                    return 3;
                }
                if (i2 != 2) {
                    if (i2 != 3) {
                        return 6;
                    }
                    return 5;
                }
                return 4;
            }
            throw null;
        }

        private final void playCalibrationDoneSoundAndAnnouncement(String str) {
            for (int i = 0; i < 3; i++) {
                new Handler().postDelayed(new DelayedWorkTracker.AnonymousClass1(BrailleIme.this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging, FeedbackManager$Type.CALIBRATION, 9, (char[]) null), i * 500);
            }
            OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(str, 1500, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(1, null));
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final boolean isCalibrationHoldRecognized(boolean z, int i) {
            if ((i == 5 || i == 6) && !BrailleIme.this.isEightDotsBraille()) {
                return true;
            }
            if (i >= 5 && i <= 8 && BrailleIme.this.isEightDotsBraille()) {
                return true;
            }
            if (!z) {
                return false;
            }
            if (BrailleIme.this.isEightDotsBraille()) {
                if (i == 4) {
                    return true;
                }
            } else if (i == 3) {
                return true;
            }
            return false;
        }

        /* renamed from: lambda$onCalibration$0$com-google-android-accessibility-brailleime-BrailleIme$10$ar$edu, reason: not valid java name */
        public final /* synthetic */ void m71x1dc0b536(int i) {
            OnDeviceTextDetectionLoadLogEvent instance$ar$class_merging$8b242409_0$ar$class_merging = OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging();
            String twoStepsCalibrationAnnounceString$ar$edu = BrailleIme.this.getTwoStepsCalibrationAnnounceString$ar$edu(i);
            BrailleIme brailleIme = BrailleIme.this;
            instance$ar$class_merging$8b242409_0$ar$class_merging.speak(twoStepsCalibrationAnnounceString$ar$edu, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, brailleIme.getRepeatAnnouncementRunnable(brailleIme.getRepeatedTwoStepCalibrationAnnounceString$ar$edu(i))));
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final String onBrailleProduced(BrailleCharacter brailleCharacter) {
            if (BrailleIme.this.isConnectionValid()) {
                BrailleIme.talkBackForBrailleIme.interruptSpeak();
                if (BrailleIme.talkBackForBrailleIme.isCurrentGranularityTypoCorrection()) {
                    BrailleIme.talkBackForBrailleIme.resetGranularity();
                }
                BrailleIme brailleIme = BrailleIme.this;
                ((BrailleImeAnalyticsHelper$SessionCache) brailleIme.brailleImeAnalytics.helper$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex).totalBrailleCharCount++;
                String appendBraille$ar$class_merging$ar$class_merging = brailleIme.editBuffer.appendBraille$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging(), brailleCharacter);
                if (!TextUtils.isEmpty(appendBraille$ar$class_merging$ar$class_merging)) {
                    BrailleIme.this.escapeReminder.restartTimer();
                    BrailleIme.this.showOnBrailleDisplay();
                }
                UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(BrailleIme.this).vibrate(BrailleImeVibrator$VibrationType.BRAILLE_COMMISSION);
                return appendBraille$ar$class_merging$ar$class_merging;
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0041  */
        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean onCalibration$ar$edu$55d81d41_0(int r7, int r8) {
            /*
                r6 = this;
                com.google.android.accessibility.brailleime.BrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.this
                android.os.Handler r0 = r0.calibrationAnnouncementHandler
                r1 = 0
                r0.removeCallbacksAndMessages(r1)
                com.google.android.accessibility.brailleime.BrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.this
                boolean r0 = r0.isEightDotsBraille()
                r1 = 9
                r2 = 1
                r3 = 7
                if (r8 != r3) goto L17
                if (r0 == 0) goto L25
                r0 = r2
            L17:
                if (r8 != r1) goto L1b
                if (r0 != 0) goto L25
            L1b:
                r4 = 2
                if (r8 != r4) goto L20
                if (r0 == 0) goto L25
            L20:
                r5 = 4
                if (r8 != r5) goto L86
                if (r0 == 0) goto L86
            L25:
                com.google.android.accessibility.brailleime.BrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.this
                r4 = 2132085403(0x7f150a9b, float:1.9811004E38)
                if (r8 == r3) goto L32
                if (r8 != r1) goto L2f
                goto L32
            L2f:
                r4 = 2132083158(0x7f1501d6, float:1.980645E38)
            L32:
                java.lang.String r8 = r0.getString(r4)
                r6.playCalibrationDoneSoundAndAnnouncement(r8)
                com.google.android.accessibility.brailleime.BrailleIme r8 = com.google.android.accessibility.brailleime.BrailleIme.this
                com.google.android.accessibility.brailleime.keyboardview.KeyboardView r8 = r8.keyboardView
                com.google.android.accessibility.brailleime.input.BrailleInputView r8 = r8.brailleInputView
                if (r8 == 0) goto L65
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                com.google.android.accessibility.brailleime.input.BrailleInputPlane r8 = r8.inputPlane
                java.util.List r1 = r8.dotTargets
                java.util.Iterator r1 = r1.iterator()
            L4e:
                boolean r3 = r1.hasNext()
                if (r3 == 0) goto L60
                java.lang.Object r3 = r1.next()
                com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State r3 = (com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall.State) r3
                java.lang.Object r3 = r3.OrderVerifyingClientCall$State$ar$cancellationStatus
                r0.add(r3)
                goto L4e
            L60:
                android.util.Size r1 = r8.sizeInPixels
                r8.writeLayoutPoints(r0, r1)
            L65:
                com.google.android.accessibility.brailleime.BrailleIme r8 = com.google.android.accessibility.brailleime.BrailleIme.this
                com.google.android.accessibility.brailleime.EscapeReminder r8 = r8.escapeReminder
                r8.startTimer()
                com.google.android.accessibility.brailleime.BrailleIme r8 = com.google.android.accessibility.brailleime.BrailleIme.this
                com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics r8 = r8.brailleImeAnalytics
                int r7 = mapCalibrationToType$ar$edu$ar$ds(r7)
                com.google.android.accessibility.brailleime.BrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.this
                kotlinx.coroutines.scheduling.WorkQueue r8 = r8.helper$ar$class_merging$ar$class_merging$ar$class_merging
                boolean r1 = r0.isCurrentTableTopMode()
                boolean r0 = r0.isEightDotsBraille()
                int r3 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationState.CALIBRATION_STATE_ENDED$ar$edu
                r8.addCalibration$ar$edu$ar$edu(r7, r1, r3, r0)
                goto Ld2
            L86:
                r0 = 3
                r1 = 5
                if (r7 != r1) goto L8c
                if (r8 == r2) goto La6
            L8c:
                if (r8 == r1) goto La6
                if (r8 == r4) goto La6
                if (r8 != r0) goto L93
                goto La6
            L93:
                r7 = 6
                if (r8 == r7) goto L9c
                r7 = 8
                if (r8 == r7) goto L9c
                r2 = 0
                goto Ld2
            L9c:
                com.google.android.accessibility.brailleime.BrailleIme r7 = com.google.android.accessibility.brailleime.BrailleIme.this
                java.lang.String r7 = r7.getTwoStepsCalibrationAnnounceString$ar$edu(r8)
                r6.playCalibrationDoneSoundAndAnnouncement(r7)
                return r2
            La6:
                com.google.android.accessibility.brailleime.BrailleIme r1 = com.google.android.accessibility.brailleime.BrailleIme.this
                com.google.android.accessibility.brailleime.EscapeReminder r1 = r1.escapeReminder
                r1.cancelTimer()
                com.google.android.accessibility.brailleime.BrailleIme r1 = com.google.android.accessibility.brailleime.BrailleIme.this
                com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics r3 = r1.brailleImeAnalytics
                kotlinx.coroutines.scheduling.WorkQueue r3 = r3.helper$ar$class_merging$ar$class_merging$ar$class_merging
                int r7 = mapCalibrationToType$ar$edu$ar$ds(r7)
                boolean r4 = r1.isCurrentTableTopMode()
                boolean r1 = r1.isEightDotsBraille()
                int r5 = com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$CalibrationState.CALIBRATION_STATE_STARTED$ar$edu
                r3.addCalibration$ar$edu$ar$edu(r7, r4, r5, r1)
                com.google.android.accessibility.brailleime.BrailleIme r7 = com.google.android.accessibility.brailleime.BrailleIme.this
                android.os.Handler r7 = r7.calibrationAnnouncementHandler
                androidx.core.provider.CallbackWithHandler$2 r1 = new androidx.core.provider.CallbackWithHandler$2
                r1.<init>(r6, r8, r0)
                r3 = 1500(0x5dc, double:7.41E-321)
                r7.postDelayed(r1, r3)
            Ld2:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.BrailleIme.AnonymousClass10.onCalibration$ar$edu$55d81d41_0(int, int):boolean");
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final void onCalibrationFailed$ar$edu(int i) {
            BrailleIme.this.brailleImeAnalytics.helper$ar$class_merging$ar$class_merging$ar$class_merging.addCalibration$ar$edu$ar$edu(mapCalibrationToType$ar$edu$ar$ds(i), BrailleIme.this.isCurrentTableTopMode(), BrailleImeLogProto$CalibrationState.CALIBRATION_STATE_FAILED$ar$edu, BrailleIme.this.isEightDotsBraille());
            BrailleIme.this.calibrationAnnouncementHandler.removeCallbacksAndMessages(null);
            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), BrailleIme.this.getString(R.string.calibration_fail_announcement), 1);
            if (i != 5) {
                BrailleIme brailleIme = BrailleIme.this;
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(brailleIme.getString(R.string.calibration_fail_try_again_announcement, new Object[]{Integer.valueOf(BrailleUserPreferences.getCurrentTypingLanguageType$ar$edu(brailleIme.getApplicationContext()))}), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
            }
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final boolean onDotHoldAndDotSwipe(Swipe swipe, BrailleCharacter brailleCharacter) {
            BrailleImeGestureController brailleImeGestureController = BrailleIme.this.brailleImeGestureController;
            if (BrailleKeyboardConfig.holdAndSwipeGesture(brailleImeGestureController.context)) {
                BrailleImeGesture brailleImeGesture = (BrailleImeGesture) brailleImeGestureController.gestureMap.get(new DotHoldSwipe(swipe.direction, swipe.touchCount, brailleCharacter));
                if (brailleImeGesture == null) {
                    _BOUNDARY.d("BrailleImeGestureController", "unknown dot hold and swipe");
                    return false;
                }
                if (brailleImeGestureController.performAction((BrailleImeActions) brailleImeGestureController.gestureActionMap.get(brailleImeGesture))) {
                    BrailleIme.this.showOnBrailleDisplay();
                    return true;
                }
                return false;
            }
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final boolean onHoldProduced(int i) {
            BrailleImeGestureController brailleImeGestureController = BrailleIme.this.brailleImeGestureController;
            if (i != 1 && i != 2 && i != 3) {
                return false;
            }
            UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(brailleImeGestureController.context).vibrate(BrailleImeVibrator$VibrationType.HOLD);
            return true;
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final boolean onSwipeProduced(Swipe swipe) {
            BrailleImeGestureController brailleImeGestureController = BrailleIme.this.brailleImeGestureController;
            BrailleImeGesture brailleImeGesture = (BrailleImeGesture) brailleImeGestureController.gestureMap.get(swipe);
            if (brailleImeGesture == null) {
                _BOUNDARY.d("BrailleImeGestureController", "unknown swipe");
                return false;
            }
            if (brailleImeGestureController.performAction((BrailleImeActions) brailleImeGestureController.gestureActionMap.get(brailleImeGesture))) {
                BrailleIme.this.showOnBrailleDisplay();
                BrailleIme brailleIme = BrailleIme.this;
                if (!brailleIme.brailleDisplayConnectedAndNotSuspended) {
                    brailleIme.escapeReminder.restartTimer();
                    return true;
                }
                return true;
            }
            return false;
        }

        @Override // com.google.android.accessibility.brailleime.input.BrailleInputView.Callback
        public final void onTwoStepCalibrationRetry(boolean z) {
            String string;
            int i;
            BrailleIme.this.calibrationAnnouncementHandler.removeCallbacksAndMessages(null);
            boolean readReverseDotsMode = BrailleUserPreferences.readReverseDotsMode(BrailleIme.this);
            BrailleIme brailleIme = BrailleIme.this;
            boolean isEightDotsBraille = brailleIme.isEightDotsBraille();
            int i2 = R.string.right_hand;
            if (z) {
                BrailleIme brailleIme2 = BrailleIme.this;
                if (true != readReverseDotsMode) {
                    i2 = R.string.left_hand;
                }
                string = brailleIme2.getString(i2);
            } else {
                BrailleIme brailleIme3 = BrailleIme.this;
                if (true == readReverseDotsMode) {
                    i2 = R.string.left_hand;
                }
                string = brailleIme3.getString(i2);
            }
            if (true != isEightDotsBraille) {
                i = R.string.calibration_hold_left_or_right_three_finger_announcement;
            } else {
                i = R.string.calibration_hold_left_or_right_four_finger_announcement;
            }
            String string2 = brailleIme.getString(i, new Object[]{string});
            OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(string2, 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(1, BrailleIme.this.getRepeatAnnouncementRunnable(string2)));
        }
    }

    static {
        ImmutableSet.construct(4, CursorGranularity.CHARACTER, CursorGranularity.WORD, CursorGranularity.LINE, CursorGranularity.PARAGRAPH);
    }

    private final KeyboardView createKeyboardView() {
        if (this.brailleDisplayConnectedAndNotSuspended) {
            return new StandardKeyboardView(this, this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, false);
        }
        return new AccessibilityOverlayKeyboardView(this, this.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    private final void createViewContainerAndAddView() {
        this.keyboardView.windowManager = (WindowManager) ((TalkBackForBrailleImeImpl) talkBackForBrailleIme).service.getSystemService("window");
        KeyboardView keyboardView = this.keyboardView;
        if (keyboardView.isViewContainerCreated()) {
            KeyboardView.ViewContainer viewContainer = keyboardView.viewContainer;
        } else {
            keyboardView.init();
            keyboardView.viewContainer = keyboardView.createViewContainerInternal();
            keyboardView.updateViewContainerInternal();
            KeyboardView.ViewContainer viewContainer2 = keyboardView.viewContainer;
        }
        if (this.brailleDisplayConnectedAndNotSuspended) {
            KeyboardView keyboardView2 = this.keyboardView;
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = this.brailleDisplayKeyboardCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (keyboardView2.isStripViewAttached()) {
                keyboardView2.keyboardViewCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onViewReady();
                return;
            } else {
                keyboardView2.runWhenViewContainerIsReady(new DelayedWorkTracker.AnonymousClass1(keyboardView2, resolutionResultListener, 13, (char[]) null));
                return;
            }
        }
        if (this.tutorialState$ar$edu == 1 && !BrailleUserPreferences.shouldLaunchTutorial(getApplicationContext())) {
            if (!this.keyboardView.isInputViewCreated()) {
                this.keyboardView.createAndAddInputView(this.inputPlaneCallback);
                this.escapeReminder.startTimer();
                return;
            }
            return;
        }
        if (this.tutorialState$ar$edu == 1) {
            this.tutorialState$ar$edu = 2;
        }
        createAndAddTutorialView();
    }

    public final void activateBrailleIme() {
        if (talkBackForBrailleIme != null && isInputViewShown()) {
            if (this.keyboardView.obtainImeViewRegion().isPresent()) {
                new Region((Rect) this.keyboardView.obtainImeViewRegion().get());
            }
            TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
            boolean z = this.brailleDisplayConnectedAndNotSuspended;
            TalkBackForBrailleImeImpl talkBackForBrailleImeImpl = (TalkBackForBrailleImeImpl) talkBackForBrailleIme2;
            talkBackForBrailleImeImpl.service.inputModeTracker.setInputMode(5);
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = talkBackForBrailleImeImpl.talkBackPrivateMethodProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            TalkBackService.instance.requestTouchExploration$ar$ds$dd7cb577_0(z);
            BrailleDisplayForBrailleIme brailleDisplayForBrailleIme2 = brailleDisplayForBrailleIme;
            if (brailleDisplayForBrailleIme2 != null && this.brailleDisplayConnectedAndNotSuspended && !this.isVisible) {
                this.isVisible = true;
                brailleDisplayForBrailleIme2.onImeVisibilityChanged(true);
            }
        }
    }

    public final boolean activateIfNeeded() {
        _BOUNDARY.d("BrailleIme", "activateIfNeeded");
        boolean z = false;
        if (this.keyboardView == null) {
            _BOUNDARY.e("BrailleIme", "keyboardView is null. Activate should not invoke before onCreate()");
            return false;
        }
        if (isInputViewShown()) {
            TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
            if (talkBackForBrailleIme2 != null && talkBackForBrailleIme2.getServiceStatus$ar$edu() != 2) {
                talkBackForBrailleIme.getServiceStatus$ar$edu();
                if (!this.deviceSupportsAtLeast5Pointers) {
                    if (!this.tooFewTouchPointsDialog.isShowing()) {
                        this.brailleImeAnalytics.sendErrorLog$ar$edu(BrailleImeLogProto$ErrorType.TOO_FEW_TOUCH_POINTS$ar$edu);
                        this.keyboardView.showViewAttachedDialog(this.tooFewTouchPointsDialog);
                    }
                } else {
                    _BOUNDARY.d("BrailleIme", "activate");
                    TalkBackForBrailleImeImpl talkBackForBrailleImeImpl = (TalkBackForBrailleImeImpl) talkBackForBrailleIme;
                    if (FeatureSupport.isVibratorSupported(talkBackForBrailleImeImpl.service) && SpannableUtils$IdentifierSpan.getBooleanPref(talkBackForBrailleImeImpl.prefs, talkBackForBrailleImeImpl.service.getResources(), R.string.pref_vibration_key, R.bool.pref_vibration_default)) {
                        UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this).wholeScreenChangeObserved = true;
                    }
                    BrailleDisplayForBrailleIme brailleDisplayForBrailleIme2 = brailleDisplayForBrailleIme;
                    if (brailleDisplayForBrailleIme2 != null && brailleDisplayForBrailleIme2.isBrailleDisplayConnectedAndNotSuspended()) {
                        z = true;
                    }
                    if (this.brailleDisplayConnectedAndNotSuspended != z) {
                        this.brailleDisplayConnectedAndNotSuspended = z;
                        updateInputView();
                    }
                    createViewContainerAndAddView();
                    createEditBuffer();
                    TaskApiCall.Builder instance$ar$class_merging = TaskApiCall.Builder.getInstance$ar$class_merging();
                    if (!instance$ar$class_merging.autoResolveMissingFeatures) {
                        instance$ar$class_merging.autoResolveMissingFeatures = true;
                        ((OrientationEventListener) instance$ar$class_merging.TaskApiCall$Builder$ar$execute).enable();
                    }
                    TaskApiCall.Builder.getInstance$ar$class_merging().TaskApiCall$Builder$ar$features = this.orientationMonitorCallback;
                    updateNavigationBarColor();
                    if (this.typoHandler == null) {
                        TalkBackForBrailleIme talkBackForBrailleIme3 = talkBackForBrailleIme;
                        this.typoHandler = new TypoHandler(this, new AppLifecycleMonitor(((TalkBackForBrailleImeImpl) talkBackForBrailleIme3).service), talkBackForBrailleIme3, OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging());
                    }
                    this.typoHandler.inputConnection = getCurrentInputConnection();
                    this.brailleImeGestureController = new BrailleImeGestureController(this, this.typoHandler, this.editBuffer, this.brailleImeGestureCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, talkBackForBrailleIme, this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging);
                    return true;
                }
            } else {
                _BOUNDARY.e("BrailleIme", "talkBackForBrailleIme is null or Talkback is off.");
                showTalkBackOffDialog();
                return false;
            }
        }
        return false;
    }

    public final void createAndAddTutorialView() {
        if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(getResources())) {
            if (this.tutorialState$ar$edu == 15) {
                this.tutorialState$ar$edu = 3;
            }
        } else {
            int i = this.tutorialState$ar$edu;
            if (i == 3 || i == 4) {
                this.tutorialState$ar$edu = 15;
            }
        }
        this.keyboardView.createAndAddTutorialView$ar$edu$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.tutorialState$ar$edu, this.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        ((TalkBackForBrailleImeImpl) talkBackForBrailleIme).proximitySensorListener.setSilenceOnProximity(false);
    }

    public final void createEditBuffer() {
        EditBuffer editBufferUnContracted;
        BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(this);
        boolean z = false;
        if (BrailleUserPreferences.readContractedMode(this) && readCurrentActiveInputCodeAndCorrect.isSupportsContracted(this)) {
            z = true;
        }
        _BOUNDARY.d("BrailleIme", "Code: " + readCurrentActiveInputCodeAndCorrect.getUserFacingName(this) + " contracted: " + z);
        TranslatorFactory readTranslatorFactory = BrailleUserPreferences.readTranslatorFactory(this);
        OnDeviceTextDetectionLoadLogEvent instance$ar$class_merging$8b242409_0$ar$class_merging = OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging();
        Locale locale = BrailleLanguages.LOCALE_AR;
        BrailleTranslator create = readTranslatorFactory.create(this, readCurrentActiveInputCodeAndCorrect.name(), z);
        switch (readCurrentActiveInputCodeAndCorrect.ordinal()) {
            case 0:
                if (z) {
                    editBufferUnContracted = new EditBufferArabic2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 2:
                editBufferUnContracted = new EditBufferBulgarian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 5:
                editBufferUnContracted = new EditBufferCatalan(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 11:
                editBufferUnContracted = new EditBufferCroatian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 15:
                if (z) {
                    editBufferUnContracted = new EditBufferDanish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferDanish(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 16:
                if (z) {
                    editBufferUnContracted = new EditBufferDanish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferDanish(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 18:
                editBufferUnContracted = new EditBufferDutch(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 21:
                if (z) {
                    editBufferUnContracted = new EditBufferUeb2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 22:
                if (z) {
                    editBufferUnContracted = new EditBufferBritish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 23:
                if (z) {
                    editBufferUnContracted = new EditBufferEbae2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 25:
                editBufferUnContracted = new EditBufferEnComp6(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 29:
                if (z) {
                    editBufferUnContracted = new EditBufferFrench2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferFrench(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 31:
                editBufferUnContracted = new EditBufferGreek(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 33:
                if (z) {
                    editBufferUnContracted = new EditBufferGerman2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferGerman(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 39:
                if (z) {
                    editBufferUnContracted = new EditBufferHungarian2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferHungarian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 43:
                editBufferUnContracted = new EditBufferItalian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 48:
                if (z) {
                    editBufferUnContracted = new EditBufferKorean2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferKorean(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 49:
                if (z) {
                    editBufferUnContracted = new EditBufferKorean2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferKorean(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 51:
                editBufferUnContracted = new EditBufferLatvian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 52:
                editBufferUnContracted = new EditBufferLithuanian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 58:
                if (z) {
                    editBufferUnContracted = new EditBufferNorwegian2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 63:
                editBufferUnContracted = new EditBufferPolish(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 65:
                if (z) {
                    editBufferUnContracted = new EditBufferPortuguese2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferPortuguese(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 69:
                editBufferUnContracted = new EditBufferRussian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 70:
                editBufferUnContracted = new EditBufferRussian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 72:
                editBufferUnContracted = new EditBufferSerbian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 76:
                if (z) {
                    editBufferUnContracted = new EditBufferSpanish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferSpanish(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 78:
                if (z) {
                    editBufferUnContracted = new EditBufferSwedish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 84:
                if (z) {
                    editBufferUnContracted = new EditBufferTurkish2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 86:
                editBufferUnContracted = new EditBufferRussian(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
            case 88:
                if (z) {
                    editBufferUnContracted = new EditBufferUrdu2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 89:
                if (z) {
                    editBufferUnContracted = new EditBufferVietnamese2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferVietnamese(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 91:
                if (z) {
                    editBufferUnContracted = new EditBufferWelsh2(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                } else {
                    editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                    break;
                }
            case 92:
                editBufferUnContracted = new EditBufferStub(create);
                break;
            default:
                editBufferUnContracted = new EditBufferUnContracted(this, create, instance$ar$class_merging$8b242409_0$ar$class_merging);
                break;
        }
        this.editBuffer = editBufferUnContracted;
    }

    public final void deactivateBrailleIme() {
        TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
        if (talkBackForBrailleIme2 != null) {
            if (this.tutorialState$ar$edu != 0) {
                TalkBackForBrailleImeImpl talkBackForBrailleImeImpl = (TalkBackForBrailleImeImpl) talkBackForBrailleIme2;
                if (talkBackForBrailleImeImpl.getServiceStatus$ar$edu() == 1 && SpannableUtils$IdentifierSpan.getBooleanPref(talkBackForBrailleImeImpl.prefs, talkBackForBrailleImeImpl.service.getResources(), R.string.pref_explore_by_touch_key, R.bool.pref_explore_by_touch_default)) {
                    TalkBackService.instance.requestTouchExploration$ar$ds$dd7cb577_0(true);
                }
            } else {
                throw null;
            }
        }
        BrailleDisplayForBrailleIme brailleDisplayForBrailleIme2 = brailleDisplayForBrailleIme;
        if (brailleDisplayForBrailleIme2 != null && this.brailleDisplayConnectedAndNotSuspended && this.isVisible) {
            this.isVisible = false;
            brailleDisplayForBrailleIme2.onImeVisibilityChanged(false);
        }
    }

    public final void deactivateIfNeeded$ar$ds() {
        int i;
        _BOUNDARY.d("BrailleIme", "deactivateIfNeeded");
        dismissDialogs();
        this.escapeReminder.cancelTimer();
        if (!this.keyboardView.isViewContainerCreated()) {
            return;
        }
        if (talkBackForBrailleIme == null) {
            _BOUNDARY.e("BrailleIme", "talkBackForBrailleIme is null");
            return;
        }
        _BOUNDARY.d("BrailleIme", "deactivate");
        UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(this).wholeScreenChangeObserved = false;
        if (isConnectionValid()) {
            this.editBuffer.commit$ar$class_merging$ar$class_merging(getImeConnection$ar$class_merging$ar$class_merging());
        }
        deactivateBrailleIme();
        TutorialView tutorialView = this.keyboardView.tutorialView;
        if (tutorialView != null) {
            i = tutorialView.state.getCurrentState$ar$edu();
        } else {
            i = 1;
        }
        this.tutorialState$ar$edu = i;
        this.keyboardView.tearDown();
        this.calibrationAnnouncementHandler.removeCallbacksAndMessages(null);
        TaskApiCall.Builder.getInstance$ar$class_merging().TaskApiCall$Builder$ar$features = null;
        TaskApiCall.Builder instance$ar$class_merging = TaskApiCall.Builder.getInstance$ar$class_merging();
        if (!instance$ar$class_merging.autoResolveMissingFeatures) {
            return;
        }
        instance$ar$class_merging.autoResolveMissingFeatures = false;
        ((OrientationEventListener) instance$ar$class_merging.TaskApiCall$Builder$ar$execute).disable();
    }

    public final void dismissDialogs() {
        this.talkbackOffDialog.dismiss();
        this.contextMenuDialog.dismiss();
        this.tooFewTouchPointsDialog.dismiss();
        this.talkBackSuspendDialog.dismiss();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0031, code lost:
    
        if (r0 != 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001c, code lost:
    
        if (r0 != 0) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper getImeConnection$ar$class_merging$ar$class_merging() {
        /*
            r4 = this;
            com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.brailleDisplayForBrailleIme
            boolean r0 = r0.isBrailleDisplayConnectedAndNotSuspended()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L1f
            com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.talkBackForBrailleIme
            com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl r0 = (com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl) r0
            android.content.SharedPreferences r3 = r0.prefs
            com.google.android.accessibility.talkback.TalkBackService r0 = r0.service
            android.content.res.Resources r0 = r0.getResources()
            int r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.readPhysicalKeyboardEcho(r3, r0)
            if (r0 == r2) goto L33
            if (r0 != 0) goto L34
            goto L33
        L1f:
            com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.talkBackForBrailleIme
            com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl r0 = (com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl) r0
            android.content.SharedPreferences r3 = r0.prefs
            com.google.android.accessibility.talkback.TalkBackService r0 = r0.service
            android.content.res.Resources r0 = r0.getResources()
            int r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.readOnScreenKeyboardEcho(r3, r0)
            if (r0 == r2) goto L33
            if (r0 != 0) goto L34
        L33:
            r1 = r2
        L34:
            com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.talkBackForBrailleIme
            r3 = 3
            if (r0 == 0) goto L4b
            if (r1 == 0) goto L4b
            com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl r0 = (com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl) r0
            io.grpc.internal.RetryingNameResolver$ResolutionResultListener r0 = r0.talkBackPrivateMethodProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
            java.lang.Object r0 = r0.RetryingNameResolver$ResolutionResultListener$ar$this$0
            com.google.android.accessibility.talkback.TalkBackService r0 = (com.google.android.accessibility.talkback.TalkBackService) r0
            com.google.android.accessibility.talkback.compositor.GlobalVariables r0 = r0.globalVariables
            boolean r0 = r0.mShouldSpeakPasswords
            if (r2 == r0) goto L4c
            r2 = 2
            goto L4c
        L4b:
            r2 = r3
        L4c:
            com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper r0 = new com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper
            android.view.inputmethod.InputConnection r1 = r4.getCurrentInputConnection()
            android.view.inputmethod.EditorInfo r3 = r4.getCurrentInputEditorInfo()
            r0.<init>(r1, r3, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.BrailleIme.getImeConnection$ar$class_merging$ar$class_merging():com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper");
    }

    public final SpeechController.UtteranceCompleteRunnable getRepeatAnnouncementRunnable(final CharSequence charSequence) {
        final int incrementAndGet = this.instructionSpeechId.incrementAndGet();
        return new SpeechController.UtteranceCompleteRunnable() { // from class: com.google.android.accessibility.brailleime.BrailleIme$$ExternalSyntheticLambda3
            @Override // com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable
            public final void run(int i) {
                int i2 = incrementAndGet;
                BrailleIme brailleIme = BrailleIme.this;
                if (i2 == brailleIme.instructionSpeechId.get() && brailleIme.keyboardView.inTwoStepCalibration()) {
                    CharSequence charSequence2 = charSequence;
                    brailleIme.calibrationAnnouncementHandler.removeCallbacksAndMessages(null);
                    brailleIme.calibrationAnnouncementHandler.postDelayed(new DelayedWorkTracker.AnonymousClass1(brailleIme, charSequence2, 10, (byte[]) null), 8000L);
                }
            }
        };
    }

    public final String getRepeatedTwoStepCalibrationAnnounceString$ar$edu(int i) {
        boolean readReverseDotsMode = BrailleUserPreferences.readReverseDotsMode(this);
        int i2 = i - 1;
        int i3 = R.string.left_hand;
        int i4 = R.string.calibration_hold_left_or_right_three_finger_announcement;
        if (i2 != 0 && i2 != 1 && i2 != 2 && i2 != 4) {
            if (i2 != 5 && i2 != 7) {
                return "";
            }
            if (true == isEightDotsBraille()) {
                i4 = R.string.calibration_hold_left_or_right_four_finger_announcement;
            }
            if (true != readReverseDotsMode) {
                i3 = R.string.right_hand;
            }
            return getString(i4, new Object[]{getString(i3)});
        }
        if (true == isEightDotsBraille()) {
            i4 = R.string.calibration_hold_left_or_right_four_finger_announcement;
        }
        if (true == readReverseDotsMode) {
            i3 = R.string.right_hand;
        }
        return getString(i4, new Object[]{getString(i3)});
    }

    public final String getTwoStepsCalibrationAnnounceString$ar$edu(int i) {
        int i2;
        int i3;
        int i4;
        boolean readReverseDotsMode = BrailleUserPreferences.readReverseDotsMode(this);
        StringBuilder sb = new StringBuilder();
        int i5 = i - 1;
        int i6 = R.string.calibration_hold_left_or_right_three_finger_announcement;
        int i7 = R.string.right_hand;
        if (i5 != 0 && i5 != 1 && i5 != 2 && i5 != 4) {
            if (i5 != 5) {
                if (i5 != 7) {
                    return "";
                }
                if (readReverseDotsMode) {
                    i4 = R.string.left_hand;
                } else {
                    i4 = R.string.right_hand;
                }
                sb.append(getString(R.string.eightDot_braille_calibration_step2_hold_left_or_right_finger_announcement, new Object[]{getString(i4)}));
                sb.append(" ");
                if (true == isEightDotsBraille()) {
                    i6 = R.string.calibration_hold_left_or_right_four_finger_announcement;
                }
                if (true == readReverseDotsMode) {
                    i7 = R.string.left_hand;
                }
                sb.append(getString(i6, new Object[]{getString(i7)}));
                return sb.toString();
            }
            if (readReverseDotsMode) {
                i3 = R.string.left_hand;
            } else {
                i3 = R.string.right_hand;
            }
            sb.append(getString(R.string.calibration_step2_hold_left_or_right_finger_announcement, new Object[]{getString(i3)}));
            sb.append(" ");
            if (true == isEightDotsBraille()) {
                i6 = R.string.calibration_hold_left_or_right_four_finger_announcement;
            }
            if (true == readReverseDotsMode) {
                i7 = R.string.left_hand;
            }
            sb.append(getString(i6, new Object[]{getString(i7)}));
            return sb.toString();
        }
        if (readReverseDotsMode) {
            i2 = R.string.right_hand;
        } else {
            i2 = R.string.left_hand;
        }
        sb.append(getString(R.string.calibration_step1_hold_left_or_right_finger_announcement, new Object[]{getString(i2)}));
        sb.append(" ");
        if (true == isEightDotsBraille()) {
            i6 = R.string.calibration_hold_left_or_right_four_finger_announcement;
        }
        if (true != readReverseDotsMode) {
            i7 = R.string.left_hand;
        }
        sb.append(getString(i6, new Object[]{getString(i7)}));
        return sb.toString();
    }

    public final void hideSelf() {
        requestHideSelf(0);
    }

    public final boolean isConnectionValid() {
        if (getCurrentInputConnection() == null) {
            _BOUNDARY.e("BrailleIme", "lack of InputConnection");
            return false;
        }
        if (getCurrentInputEditorInfo() == null) {
            _BOUNDARY.e("BrailleIme", "lack of InputEditorInfo");
            return false;
        }
        return true;
    }

    public final boolean isCurrentTableTopMode() {
        Object obj = this.layoutOrientator$ar$class_merging.Verifier$ar$basePackageInfo;
        TouchDots readLayoutMode = BrailleUserPreferences.readLayoutMode(this);
        if (readLayoutMode == TouchDots.TABLETOP) {
            return true;
        }
        if (readLayoutMode == TouchDots.AUTO_DETECT) {
            Optional optional = (Optional) obj;
            if (optional.isPresent()) {
                if (optional.get() == TouchDots.TABLETOP) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public final boolean isEightDotsBraille() {
        return BrailleUserPreferences.isCurrentActiveInputCodeEightDot(getApplicationContext());
    }

    @Override // android.inputmethodservice.InputMethodService
    public final void onBindInput() {
        _BOUNDARY.d("BrailleIme", "onBindInput");
        super.onBindInput();
    }

    @Override // android.inputmethodservice.InputMethodService
    public final void onComputeInsets(InputMethodService.Insets insets) {
        if (this.keyboardView.obtainImeViewRegion().isPresent()) {
            Rect rect = (Rect) this.keyboardView.obtainImeViewRegion().get();
            if (Build.VERSION.SDK_INT == 28) {
                if (this.brailleDisplayConnectedAndNotSuspended) {
                    insets.visibleTopInsets = rect.top;
                } else {
                    insets.visibleTopInsets = rect.bottom - 1;
                }
                insets.contentTopInsets = insets.visibleTopInsets;
            }
        }
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.orientation != configuration.orientation) {
            this.orientation = configuration.orientation;
            this.keyboardView.onOrientationChanged(configuration.orientation);
        }
    }

    @Override // android.inputmethodservice.InputMethodService, android.app.Service
    public final void onCreate() {
        super.onCreate();
        instance = this;
        _BOUNDARY.d("BrailleIme", "onCreate");
        WindowTrackerFactory windowTrackerFactory = talkBackForBrailleCommon$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (windowTrackerFactory != null) {
            this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = new ApplicationModule(windowTrackerFactory.getFeedBackController());
        }
        this.orientation = getResources().getConfiguration().orientation;
        this.deviceSupportsAtLeast5Pointers = getPackageManager().hasSystemFeature("android.hardware.touchscreen.multitouch.jazzhand");
        this.mainHandler = new Handler();
        this.calibrationAnnouncementHandler = new Handler();
        BrailleDisplayForBrailleIme brailleDisplayForBrailleIme2 = brailleDisplayForBrailleIme;
        if (brailleDisplayForBrailleIme2 != null) {
            this.brailleDisplayConnectedAndNotSuspended = brailleDisplayForBrailleIme2.isBrailleDisplayConnectedAndNotSuspended();
        }
        this.keyboardView = createKeyboardView();
        this.escapeReminder = new EscapeReminder(this, this.escapeReminderCallback$ar$class_merging$ar$class_merging$ar$class_merging);
        this.talkbackOffDialog = new TalkBackOffDialog(this, this.talkBackOffDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        this.contextMenuDialog = new ContextMenuDialog(this, this.contextMenuDialogCallback);
        this.tooFewTouchPointsDialog = new TooFewTouchPointsDialog(this, this.tooFewTouchPointsDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        this.talkBackSuspendDialog = new TalkBackSuspendDialog(this, this.talkBackSuspendDialogCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        this.tutorialState$ar$edu = 1;
        this.originalDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this.localUncaughtExceptionHandler);
        BrailleUserPreferences.getSharedPreferences$ar$ds(this).registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        EditorInfoCompat.registerReceiver$ar$ds(this, this.screenOffReceiver, intentFilter, 4);
        EditorInfoCompat.registerReceiver$ar$ds(this, this.closeSystemDialogsReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 4);
        EditorInfoCompat.registerReceiver$ar$ds(this, this.imeChangeListener, new IntentFilter("android.intent.action.INPUT_METHOD_CHANGED"), 4);
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("enabled_accessibility_services"), false, this.accessibilityServiceStatusChangeObserver);
        this.brailleImeAnalytics = BrailleImeAnalytics.getInstance(this);
        if (TaskApiCall.Builder.singleton$ar$class_merging == null) {
            TaskApiCall.Builder.singleton$ar$class_merging = new TaskApiCall.Builder(null);
            TaskApiCall.Builder builder = TaskApiCall.Builder.singleton$ar$class_merging;
            final Context applicationContext = getApplicationContext();
            builder.TaskApiCall$Builder$ar$execute = new OrientationEventListener(applicationContext) { // from class: com.google.android.accessibility.brailleime.OrientationMonitor$1
                /* JADX WARN: Code restructure failed: missing block: B:43:0x002f, code lost:
                
                    if (r1 > r0) goto L13;
                 */
                @Override // android.view.OrientationEventListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final void onOrientationChanged(int r8) {
                    /*
                        r7 = this;
                        android.content.Context r0 = r2
                        java.lang.String r1 = "window"
                        java.lang.Object r2 = r0.getSystemService(r1)
                        android.view.WindowManager r2 = (android.view.WindowManager) r2
                        android.view.Display r2 = r2.getDefaultDisplay()
                        int r2 = r2.getRotation()
                        android.util.DisplayMetrics r3 = new android.util.DisplayMetrics
                        r3.<init>()
                        java.lang.Object r0 = r0.getSystemService(r1)
                        android.view.WindowManager r0 = (android.view.WindowManager) r0
                        android.view.Display r0 = r0.getDefaultDisplay()
                        r0.getMetrics(r3)
                        int r0 = r3.widthPixels
                        int r1 = r3.heightPixels
                        r3 = 2
                        r4 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L32
                        r2 = r3
                    L2f:
                        if (r1 <= r0) goto L32
                        goto L3b
                    L32:
                        if (r2 == r4) goto L37
                        r5 = 3
                        if (r2 != r5) goto L39
                    L37:
                        if (r0 > r1) goto L3b
                    L39:
                        int r8 = r8 + (-90)
                    L3b:
                        int[] r0 = _COROUTINE._BOUNDARY.values$ar$edu$2b5d664c_0()
                        r1 = 0
                    L40:
                        r2 = 5
                        r5 = 45
                        if (r1 >= r2) goto L58
                        r2 = r0[r1]
                        if (r2 == r4) goto L55
                        int r6 = r2 + (-2)
                        int r6 = r8 - r6
                        int r6 = java.lang.Math.abs(r6)
                        if (r6 > r5) goto L55
                        r3 = r2
                        goto L62
                    L55:
                        int r1 = r1 + 1
                        goto L40
                    L58:
                        int r8 = r8 + (-360)
                        int r8 = java.lang.Math.abs(r8)
                        if (r8 > r5) goto L61
                        goto L62
                    L61:
                        r3 = r4
                    L62:
                        com.google.android.gms.common.api.internal.TaskApiCall$Builder r8 = com.google.android.gms.common.api.internal.TaskApiCall.Builder.singleton$ar$class_merging
                        int r0 = r8.methodKey
                        if (r3 == r0) goto Lba
                        r8.methodKey = r3
                        java.lang.Object r8 = r8.TaskApiCall$Builder$ar$features
                        if (r8 == 0) goto Lba
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue$1 r8 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue.AnonymousClass1) r8
                        java.lang.Object r8 = r8.TutorialView$RotateOrientationContinue$1$ar$this$1
                        com.google.android.accessibility.brailleime.BrailleIme r8 = (com.google.android.accessibility.brailleime.BrailleIme) r8
                        com.google.android.accessibility.brailleime.OrientationMonitor$Callback r8 = r8.orientationCallbackDelegate
                        if (r8 == 0) goto Lba
                        r0 = 92
                        if (r3 == r0) goto L80
                        r0 = 272(0x110, float:3.81E-43)
                        if (r3 != r0) goto Lba
                    L80:
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue$1 r8 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue.AnonymousClass1) r8
                        java.lang.Object r0 = r8.TutorialView$RotateOrientationContinue$1$ar$this$1
                        com.google.android.accessibility.braille.common.FeedbackManager$Type r1 = com.google.android.accessibility.braille.common.FeedbackManager$Type.BEEP
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue r0 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue) r0
                        com.google.android.accessibility.brailleime.tutorial.TutorialView r0 = com.google.android.accessibility.brailleime.tutorial.TutorialView.this
                        io.grpc.internal.RetryingNameResolver$ResolutionResultListener r0 = r0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
                        r0.onPlaySound(r1)
                        java.lang.Object r0 = r8.TutorialView$RotateOrientationContinue$1$ar$this$1
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue r0 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue) r0
                        com.google.android.accessibility.brailleime.tutorial.TutorialView r0 = com.google.android.accessibility.brailleime.tutorial.TutorialView.this
                        android.content.Context r0 = r0.context
                        com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker r0 = com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker.getInstance$ar$class_merging$7c2e3f61_0$ar$class_merging(r0)
                        com.google.android.accessibility.brailleime.BrailleImeVibrator$VibrationType r1 = com.google.android.accessibility.brailleime.BrailleImeVibrator$VibrationType.OTHER_GESTURES
                        r0.vibrate(r1)
                        java.lang.Object r0 = r8.TutorialView$RotateOrientationContinue$1$ar$this$1
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue r0 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue) r0
                        com.google.android.accessibility.brailleime.tutorial.TutorialView r0 = com.google.android.accessibility.brailleime.tutorial.TutorialView.this
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientation r1 = r0.rotateOrientation
                        r0.state = r1
                        io.grpc.internal.RetryingNameResolver$ResolutionResultListener r0 = r0.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
                        r0.unregisterOrientationChange()
                        java.lang.Object r8 = r8.TutorialView$RotateOrientationContinue$1$ar$this$1
                        com.google.android.accessibility.brailleime.tutorial.TutorialView$RotateOrientationContinue r8 = (com.google.android.accessibility.brailleime.tutorial.TutorialView.RotateOrientationContinue) r8
                        com.google.android.accessibility.brailleime.tutorial.TutorialView r8 = com.google.android.accessibility.brailleime.tutorial.TutorialView.this
                        io.grpc.internal.RetryingNameResolver$ResolutionResultListener r8 = r8.tutorialCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging
                        r8.onRestartTutorial()
                    Lba:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.OrientationMonitor$1.onOrientationChanged(int):void");
                }
            };
        }
        this.layoutOrientator$ar$class_merging = new Verifier(this, this.layoutOrientatorCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
        if (talkBackForBrailleIme2 != null) {
            ((TalkBackForBrailleImeImpl) talkBackForBrailleIme2).brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        }
    }

    @Override // android.inputmethodservice.InputMethodService
    public final View onCreateInputView() {
        View createImeInputView = this.keyboardView.createImeInputView();
        if (createImeInputView.getParent() != null) {
            ((ViewGroup) createImeInputView.getParent()).removeView(createImeInputView);
        }
        return createImeInputView;
    }

    @Override // android.inputmethodservice.InputMethodService, android.inputmethodservice.AbstractInputMethodService, android.app.Service
    public final void onDestroy() {
        _BOUNDARY.d("BrailleIme", "onDestroy");
        instance = null;
        TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
        if (talkBackForBrailleIme2 != null) {
            ((TalkBackForBrailleImeImpl) talkBackForBrailleIme2).brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        }
        BrailleUserPreferences.getSharedPreferences$ar$ds(this).unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        unregisterReceiver(this.screenOffReceiver);
        unregisterReceiver(this.closeSystemDialogsReceiver);
        unregisterReceiver(this.imeChangeListener);
        getContentResolver().unregisterContentObserver(this.accessibilityServiceStatusChangeObserver);
        super.onDestroy();
        this.keyboardView.tearDown();
        this.keyboardView = null;
        this.brailleImeAnalytics.sendAllLogs();
    }

    @Override // android.inputmethodservice.InputMethodService
    public final boolean onEvaluateFullscreenMode() {
        return false;
    }

    @Override // android.inputmethodservice.InputMethodService
    public final void onFinishInputView(boolean z) {
        if (_BOUNDARY.isPhonePermissionGranted(this)) {
            ((TelephonyManager) getSystemService("phone")).listen(this.phoneStateListener, 0);
        }
        _BOUNDARY.d("BrailleIme", "onFinishInputView");
        super.onFinishInputView(z);
        deactivateIfNeeded$ar$ds();
        this.brailleImeAnalytics.collectSessionEvents();
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0029, code lost:
    
        if (r1.actionTime >= r0.lastMenuDismissUptimeMs) goto L16;
     */
    @Override // android.inputmethodservice.InputMethodService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onShowInputRequested(int r5, boolean r6) {
        /*
            r4 = this;
            com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme r0 = com.google.android.accessibility.brailleime.BrailleIme.talkBackForBrailleIme
            if (r0 == 0) goto L35
            com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl r0 = (com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl) r0
            com.google.android.accessibility.talkback.TalkBackService r0 = r0.service
            com.google.android.accessibility.talkback.contextmenu.ListMenuManager r0 = r0.menuManager
            com.google.android.accessibility.utils.material.A11yAlertDialogWrapper r1 = r0.currentDialog
            if (r1 != 0) goto L2c
            com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall$State r1 = r0.deferredAction$ar$class_merging$ar$class_merging
            if (r1 == 0) goto L13
            goto L2c
        L13:
            com.google.android.accessibility.talkback.ActorState r1 = r0.actorState
            com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r1 = r1.getFocusHistory$ar$class_merging$ar$class_merging()
            java.lang.Object r1 = r1.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0
            com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory r1 = (com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory) r1
            com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord r1 = r1.getLastFocusActionRecord()
            if (r1 == 0) goto L35
            long r2 = r0.lastMenuDismissUptimeMs
            long r0 = r1.actionTime
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L2c
            goto L35
        L2c:
            java.lang.String r5 = "BrailleIme"
            java.lang.String r6 = "TalkBack context menu is running."
            _COROUTINE._BOUNDARY.d(r5, r6)
            r5 = 0
            return r5
        L35:
            boolean r5 = super.onShowInputRequested(r5, r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.BrailleIme.onShowInputRequested(int, boolean):boolean");
    }

    @Override // android.inputmethodservice.InputMethodService
    public final void onStartInputView(EditorInfo editorInfo, boolean z) {
        _BOUNDARY.d("BrailleIme", "onStartInputView");
        getWindow().setTitle(_BOUNDARY.getBrailleKeyboardDisplayName(this));
        if (_BOUNDARY.isPhonePermissionGranted(this)) {
            ((TelephonyManager) getSystemService("phone")).listen(this.phoneStateListener, 32);
        }
        if (((PowerManager) getSystemService("power")).isInteractive()) {
            if (activateIfNeeded() && !z) {
                talkBackForBrailleIme.resetGranularity();
            }
        } else {
            hideSelf();
        }
        startAnalyticsPossibly();
        if (getCurrentInputConnection() != null) {
            getCurrentInputConnection().requestCursorUpdates(3);
        }
    }

    public final void performEditorAction(InputConnection inputConnection) {
        EditorInfo currentInputEditorInfo = getCurrentInputEditorInfo();
        int i = currentInputEditorInfo.imeOptions & PrivateKeyType.INVALID;
        _BOUNDARY.d("BrailleIme", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "performEnterAction editorAction = "));
        if (i != 0 && i != 1) {
            if (TextUtils.equals(currentInputEditorInfo.packageName, "com.google.android.apps.messaging")) {
                new Handler().postDelayed(new CallbackWithHandler$2(inputConnection, i, 2), 50L);
            } else {
                inputConnection.performEditorAction(i);
            }
            if (i == 5) {
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(getString(R.string.perform_action_next), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
            } else {
                OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(getString(R.string.perform_action_submitting), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
            }
        }
    }

    public final void showOnBrailleDisplay() {
        if (!isInputViewShown()) {
            return;
        }
        this.mainHandler.post(new Runnable() { // from class: com.google.android.accessibility.brailleime.BrailleIme$$ExternalSyntheticLambda1
            /* JADX WARN: Code restructure failed: missing block: B:71:0x0084, code lost:
            
                if (r2 == null) goto L11;
             */
            /* JADX WARN: Type inference failed for: r2v9, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void run() {
                /*
                    Method dump skipped, instructions count: 363
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.brailleime.BrailleIme$$ExternalSyntheticLambda1.run():void");
            }
        });
    }

    public final void showTalkBackOffDialog() {
        if (!this.talkbackOffDialog.isShowing()) {
            this.brailleImeAnalytics.logTalkBackOffDialogDisplay();
            this.keyboardView.showViewAttachedDialog(this.talkbackOffDialog);
        }
    }

    public final void startAnalyticsPossibly() {
        if (this.tutorialState$ar$edu == 1) {
            this.brailleImeAnalytics.helper$ar$class_merging$ar$class_merging$ar$class_merging.startCalculateSessionTime$ar$ds();
        }
    }

    public final boolean switchToNextInputMethod() {
        boolean switchToNextInputMethod;
        EditBuffer editBuffer;
        TalkBackForBrailleIme talkBackForBrailleIme2 = talkBackForBrailleIme;
        if (talkBackForBrailleIme2 != null) {
            talkBackForBrailleIme2.interruptSpeak();
        }
        if (isConnectionValid() && (editBuffer = this.editBuffer) != null) {
            editBuffer.commit$ar$class_merging$ar$class_merging(getImeConnection$ar$class_merging$ar$class_merging());
        }
        boolean z = false;
        if (!SpannableUtils$IdentifierSpan.areMultipleImesEnabled(this)) {
            Toast.makeText(this, getString(R.string.bring_ime_settings_page), 0).show();
            Intent intent = new Intent("android.settings.INPUT_METHOD_SETTINGS");
            intent.addFlags(276856832);
            startActivity(intent);
            return false;
        }
        String enabledImeId = SpannableUtils$IdentifierSpan.getEnabledImeId(this, "com.google.android.inputmethod.latin");
        if (!TextUtils.isEmpty(enabledImeId)) {
            switchInputMethod(enabledImeId);
        } else {
            if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
                switchToNextInputMethod = switchToNextInputMethod(false);
            } else {
                switchToNextInputMethod = ((InputMethodManager) getSystemService("input_method")).switchToNextInputMethod(getWindow().getWindow().getAttributes().token, false);
            }
            if (!switchToNextInputMethod) {
                Iterator it = SpannableUtils$IdentifierSpan.getEnabledInputMethodList(this).iterator();
                String str = "";
                String str2 = "";
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    InputMethodInfo inputMethodInfo = (InputMethodInfo) it.next();
                    if (z) {
                        str = inputMethodInfo.getId();
                        break;
                    }
                    if (inputMethodInfo.getPackageName().equals(getPackageName())) {
                        z = true;
                    } else if (TextUtils.isEmpty(str2)) {
                        str2 = inputMethodInfo.getId();
                    }
                }
                if (!z || !TextUtils.isEmpty(str)) {
                    str2 = str;
                }
                switchInputMethod(str2);
            }
        }
        return true;
    }

    public final void updateInputView() {
        KeyboardView keyboardView = this.keyboardView;
        if (keyboardView != null) {
            keyboardView.tearDown();
        }
        KeyboardView createKeyboardView = createKeyboardView();
        this.keyboardView = createKeyboardView;
        setInputView(createKeyboardView.createImeInputView());
        createViewContainerAndAddView();
    }

    public final void updateNavigationBarColor() {
        int i;
        Window window = getWindow().getWindow();
        if (true != this.brailleDisplayConnectedAndNotSuspended) {
            i = R.color.google_transparent;
        } else {
            i = R.color.braille_keyboard_background;
        }
        window.setNavigationBarColor(ContextCompat$Api23Impl.getColor(this, i));
    }
}
