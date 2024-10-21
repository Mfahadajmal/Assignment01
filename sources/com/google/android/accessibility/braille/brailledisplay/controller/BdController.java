package com.google.android.accessibility.braille.brailledisplay.controller;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BdController {
    public final FloatingActionButton.ShadowDelegateImpl behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging;
    public final BrailleDisplayForBrailleIme brailleDisplayForBrailleIme;
    private final RetryingNameResolver.ResolutionResultListener brailleImeProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final CellsContentManager cellsContentManager;
    public final Context context;
    public Displayer displayer;
    public final EventManager eventManager;
    public final ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    public final OverlayDisplay overlayDisplay;
    public AlertDialog someBrailleCommandUnavailableDialog;
    public final AtomicBoolean suspended = new AtomicBoolean();
    public final WorkQueue talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public TranslatorManager translatorManager;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.controller.BdController$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements BrailleDisplayForBrailleIme {
        public AnonymousClass1() {
        }

        @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
        public final boolean isBrailleDisplayConnectedAndNotSuspended() {
            if (BdController.this.suspended.get()) {
                return false;
            }
            return BdController.this.isDisplayerReady();
        }

        @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
        public final void onImeVisibilityChanged(boolean z) {
            if (z) {
                Context context = BdController.this.context;
                int i = 1;
                if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_bd_show_navigation_command_unavailable_tip), true)) {
                    if (!BdController.this.isSomeBrailleCommandUnavailableDialogShowing()) {
                        BdController bdController = BdController.this;
                        Context context2 = bdController.context;
                        Context context3 = bdController.context;
                        bdController.someBrailleCommandUnavailableDialog = AppCompatDelegateImpl.Api21Impl.createTipAlertDialog((Context) bdController.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer, context2.getString(R.string.bd_notify_navigation_commands_unavailable_dialog_title), context3.getString(R.string.bd_notify_navigation_commands_unavailable_dialog_message), new UsbConnectManager$$ExternalSyntheticLambda0(i));
                        BdController.this.someBrailleCommandUnavailableDialog.getWindow().setType(2032);
                        if (Build.VERSION.SDK_INT >= 28) {
                            BdController.this.someBrailleCommandUnavailableDialog.show();
                            return;
                        } else {
                            new Handler().post(new WorkerKt$$ExternalSyntheticLambda0(this, 9));
                            return;
                        }
                    }
                    return;
                }
            }
            if (BdController.this.isSomeBrailleCommandUnavailableDialogShowing()) {
                BdController.this.someBrailleCommandUnavailableDialog.dismiss();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:23:0x00c0 A[Catch: IOException -> 0x0403, TryCatch #0 {IOException -> 0x0403, blocks: (B:7:0x001d, B:9:0x002b, B:10:0x007c, B:12:0x0084, B:15:0x0094, B:17:0x00a8, B:21:0x00b2, B:23:0x00c0, B:25:0x00d6, B:26:0x00e2, B:28:0x0104, B:29:0x010e, B:30:0x0109, B:31:0x00c8, B:33:0x0149, B:35:0x0151, B:38:0x0159, B:40:0x0167, B:41:0x017b, B:43:0x0195, B:44:0x01a8, B:45:0x01a2, B:46:0x016f, B:47:0x021b, B:50:0x02af, B:52:0x02b7, B:54:0x02cb, B:56:0x02d9, B:57:0x0356, B:59:0x0360, B:61:0x0366, B:63:0x036c, B:65:0x0377, B:67:0x037b, B:69:0x0385, B:81:0x0374, B:82:0x0225, B:84:0x022f, B:87:0x0245, B:88:0x0268, B:90:0x0270, B:93:0x02a5, B:94:0x02a8), top: B:6:0x001d }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00d6 A[Catch: IOException -> 0x0403, TryCatch #0 {IOException -> 0x0403, blocks: (B:7:0x001d, B:9:0x002b, B:10:0x007c, B:12:0x0084, B:15:0x0094, B:17:0x00a8, B:21:0x00b2, B:23:0x00c0, B:25:0x00d6, B:26:0x00e2, B:28:0x0104, B:29:0x010e, B:30:0x0109, B:31:0x00c8, B:33:0x0149, B:35:0x0151, B:38:0x0159, B:40:0x0167, B:41:0x017b, B:43:0x0195, B:44:0x01a8, B:45:0x01a2, B:46:0x016f, B:47:0x021b, B:50:0x02af, B:52:0x02b7, B:54:0x02cb, B:56:0x02d9, B:57:0x0356, B:59:0x0360, B:61:0x0366, B:63:0x036c, B:65:0x0377, B:67:0x037b, B:69:0x0385, B:81:0x0374, B:82:0x0225, B:84:0x022f, B:87:0x0245, B:88:0x0268, B:90:0x0270, B:93:0x02a5, B:94:0x02a8), top: B:6:0x001d }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0104 A[Catch: IOException -> 0x0403, TryCatch #0 {IOException -> 0x0403, blocks: (B:7:0x001d, B:9:0x002b, B:10:0x007c, B:12:0x0084, B:15:0x0094, B:17:0x00a8, B:21:0x00b2, B:23:0x00c0, B:25:0x00d6, B:26:0x00e2, B:28:0x0104, B:29:0x010e, B:30:0x0109, B:31:0x00c8, B:33:0x0149, B:35:0x0151, B:38:0x0159, B:40:0x0167, B:41:0x017b, B:43:0x0195, B:44:0x01a8, B:45:0x01a2, B:46:0x016f, B:47:0x021b, B:50:0x02af, B:52:0x02b7, B:54:0x02cb, B:56:0x02d9, B:57:0x0356, B:59:0x0360, B:61:0x0366, B:63:0x036c, B:65:0x0377, B:67:0x037b, B:69:0x0385, B:81:0x0374, B:82:0x0225, B:84:0x022f, B:87:0x0245, B:88:0x0268, B:90:0x0270, B:93:0x02a5, B:94:0x02a8), top: B:6:0x001d }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0109 A[Catch: IOException -> 0x0403, TryCatch #0 {IOException -> 0x0403, blocks: (B:7:0x001d, B:9:0x002b, B:10:0x007c, B:12:0x0084, B:15:0x0094, B:17:0x00a8, B:21:0x00b2, B:23:0x00c0, B:25:0x00d6, B:26:0x00e2, B:28:0x0104, B:29:0x010e, B:30:0x0109, B:31:0x00c8, B:33:0x0149, B:35:0x0151, B:38:0x0159, B:40:0x0167, B:41:0x017b, B:43:0x0195, B:44:0x01a8, B:45:0x01a2, B:46:0x016f, B:47:0x021b, B:50:0x02af, B:52:0x02b7, B:54:0x02cb, B:56:0x02d9, B:57:0x0356, B:59:0x0360, B:61:0x0366, B:63:0x036c, B:65:0x0377, B:67:0x037b, B:69:0x0385, B:81:0x0374, B:82:0x0225, B:84:0x022f, B:87:0x0245, B:88:0x0268, B:90:0x0270, B:93:0x02a5, B:94:0x02a8), top: B:6:0x001d }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00c8 A[Catch: IOException -> 0x0403, TryCatch #0 {IOException -> 0x0403, blocks: (B:7:0x001d, B:9:0x002b, B:10:0x007c, B:12:0x0084, B:15:0x0094, B:17:0x00a8, B:21:0x00b2, B:23:0x00c0, B:25:0x00d6, B:26:0x00e2, B:28:0x0104, B:29:0x010e, B:30:0x0109, B:31:0x00c8, B:33:0x0149, B:35:0x0151, B:38:0x0159, B:40:0x0167, B:41:0x017b, B:43:0x0195, B:44:0x01a8, B:45:0x01a2, B:46:0x016f, B:47:0x021b, B:50:0x02af, B:52:0x02b7, B:54:0x02cb, B:56:0x02d9, B:57:0x0356, B:59:0x0360, B:61:0x0366, B:63:0x036c, B:65:0x0377, B:67:0x037b, B:69:0x0385, B:81:0x0374, B:82:0x0225, B:84:0x022f, B:87:0x0245, B:88:0x0268, B:90:0x0270, B:93:0x02a5, B:94:0x02a8), top: B:6:0x001d }] */
        @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void showOnDisplay(com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme.ResultForDisplay r22) {
            /*
                Method dump skipped, instructions count: 1203
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.controller.BdController.AnonymousClass1.showOnDisplay(com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme$ResultForDisplay):void");
        }
    }

    public BdController(Context context, WorkQueue workQueue, WindowTrackerFactory windowTrackerFactory, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this);
        this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.brailleDisplayForBrailleIme = new AnonymousClass1();
        this.context = context;
        this.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = workQueue;
        this.brailleImeProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        ApplicationModule applicationModule = new ApplicationModule(windowTrackerFactory.getFeedBackController());
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        this.translatorManager = new TranslatorManager(context);
        CellsContentManager cellsContentManager = new CellsContentManager(context, new FloatingActionButton.ShadowDelegateImpl(this), this.translatorManager, new FloatingActionButton.ShadowDelegateImpl(this));
        this.cellsContentManager = cellsContentManager;
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = new FloatingActionButton.ShadowDelegateImpl(this);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3 = new FloatingActionButton.ShadowDelegateImpl(this);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl4 = new FloatingActionButton.ShadowDelegateImpl(this);
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl5 = new FloatingActionButton.ShadowDelegateImpl(this);
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl5;
        this.eventManager = new EventManager(context, cellsContentManager, applicationModule, shadowDelegateImpl5, shadowDelegateImpl, shadowDelegateImpl3, shadowDelegateImpl2, shadowDelegateImpl4, new FloatingActionButton.ShadowDelegateImpl(this));
        this.overlayDisplay = new OverlayDisplay(context, new FloatingActionButton.ShadowDelegateImpl(this));
    }

    public final HapticPatternParser$$ExternalSyntheticLambda1 getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.brailleImeProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging();
    }

    public final boolean isBrailleKeyboardActivated() {
        if (getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null && ((BrailleIme) getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).isInputViewShown()) {
            return true;
        }
        return false;
    }

    public final boolean isDisplayerReady() {
        Displayer displayer = this.displayer;
        if (displayer != null && displayer.isDisplayReady()) {
            return true;
        }
        return false;
    }

    public final boolean isOnscreenKeyboardActive() {
        return SpannableUtils$IdentifierSpan.isInputWindowOnScreen((AccessibilityService) this.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$buffer);
    }

    public final boolean isSomeBrailleCommandUnavailableDialogShowing() {
        AlertDialog alertDialog = this.someBrailleCommandUnavailableDialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            return true;
        }
        return false;
    }

    public final void onBrailleInputEvent(BrailleInputEvent brailleInputEvent) {
        AccessibilityNodeInfoCompat accessibilityFocusNode;
        if (!isDisplayerReady()) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.w("BdController", "Displayer is not ready yet.");
            return;
        }
        WorkQueue workQueue = this.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        accessibilityFocusNode = AppLifecycleMonitor.getAccessibilityFocusNode(TalkBackService.instance, false);
        workQueue.performAction$ar$edu$3bc9316c_0(40, accessibilityFocusNode);
        if (brailleInputEvent.getCommand() != 5 && brailleInputEvent.getCommand() != 6 && isBrailleKeyboardActivated() && this.suspended.get()) {
            BrailleDisplayAnalytics.getInstance(this.context).logChangeTypingMode(true);
            this.suspended.set(false);
            OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().speak(this.context.getString(R.string.bd_switch_to_braille_hardware_message), 200, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(1, null));
            getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().onBrailleDisplayConnected();
            AccessibilityNodeInfoCompat findFocusCompat = WorkQueue.createFocusFinder$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().findFocusCompat(1);
            if (findFocusCompat != null) {
                findFocusCompat.performAction$ar$ds();
                return;
            }
            return;
        }
        this.eventManager.onMappedInputEvent(brailleInputEvent);
    }

    public final void onDisconnected() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.v("BdController", "onDisconnected");
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitFeedback(FeedbackManager$Type.DISPLAY_DISCONNECTED);
        if (isSomeBrailleCommandUnavailableDialogShowing()) {
            this.someBrailleCommandUnavailableDialog.dismiss();
        }
        this.eventManager.onDeactivate();
        this.overlayDisplay.shutdown$ar$ds();
        CellsContentManager cellsContentManager = this.cellsContentManager;
        cellsContentManager.pulseHandler$ar$class_merging.cancelPulse();
        cellsContentManager.translatorManager.removeOnOutputTablesChangedListener(cellsContentManager.outputCodeChangedListener);
        BrailleUserPreferences.getSharedPreferences$ar$ds(cellsContentManager.context).unregisterOnSharedPreferenceChangeListener(cellsContentManager.onSharedPreferenceChangeListener);
        cellsContentManager.onDisplayContentChangeListeners.clear();
        this.displayer = null;
        WorkQueue workQueue = this.talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (((SpeechControllerImpl) workQueue.WorkQueue$ar$consumerIndex).isMuteSpeech) {
            workQueue.performAction$ar$edu$3bc9316c_0(21, new Object[0]);
        }
        if (isBrailleKeyboardActivated()) {
            getBrailleImeForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().onBrailleDisplayDisconnected();
        }
    }

    public final void onReadingControlChanged(CharSequence charSequence) {
        this.eventManager.displayTimedMessage(charSequence.toString());
    }
}
