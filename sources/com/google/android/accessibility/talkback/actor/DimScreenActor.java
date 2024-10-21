package com.google.android.accessibility.talkback.actor;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.view.WindowManager;
import com.google.android.accessibility.talkback.DimmingOverlayView;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DimScreenActor {
    public int currentInstructionVisibleTime;
    private final DimScreenDialog dimScreenDialog;
    private final RetryingNameResolver.ResolutionResultListener dimScreenNotifier$ar$class_merging$ar$class_merging$ar$class_merging;
    private final GestureShortcutMapping gestureShortcutMapping;
    private boolean isDimmed;
    public boolean isInstructionDisplayed;
    public Pipeline.FeedbackReturner pipeline;
    private final SharedPreferences prefs;
    private final Context service;
    public DimmingOverlayView view;
    private WindowManager.LayoutParams viewParams;
    private WindowManager windowManager;
    public final HapticPatternParser$$ExternalSyntheticLambda1 state$ar$class_merging$17ade4d6_0$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    private final Handler dimmingHandler = new Handler(Looper.getMainLooper()) { // from class: com.google.android.accessibility.talkback.actor.DimScreenActor.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            String valueOf;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                DimScreenActor dimScreenActor = DimScreenActor.this;
                int i2 = dimScreenActor.currentInstructionVisibleTime - 1;
                dimScreenActor.currentInstructionVisibleTime = i2;
                if (i2 > 0) {
                    DimmingOverlayView dimmingOverlayView = dimScreenActor.view;
                    Context context = dimmingOverlayView.getContext();
                    Integer valueOf2 = Integer.valueOf(i2 / 60);
                    int i3 = i2 % 60;
                    if (i3 < 10) {
                        valueOf = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i3, "0");
                    } else {
                        valueOf = String.valueOf(i3);
                    }
                    dimmingOverlayView.timerView.setText(context.getString(R.string.dim_screen_timer, valueOf2, valueOf));
                    dimmingOverlayView.progress.setProgress(dimmingOverlayView.timerLimit - i2);
                    sendEmptyMessageDelayed(2, TimeUnit.SECONDS.toMillis(1L));
                    return;
                }
                dimScreenActor.hideInstructionAndTurnOnDimming();
                return;
            }
            DimScreenActor dimScreenActor2 = DimScreenActor.this;
            dimScreenActor2.currentInstructionVisibleTime = 180;
            dimScreenActor2.isInstructionDisplayed = true;
            sendEmptyMessage(2);
        }
    };
    private boolean stopFeedback = false;

    public DimScreenActor(TalkBackService talkBackService, GestureShortcutMapping gestureShortcutMapping, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.service = talkBackService;
        this.gestureShortcutMapping = gestureShortcutMapping;
        this.dimScreenNotifier$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
        this.dimScreenDialog = new DimScreenDialog(talkBackService, this);
    }

    private final void announceFeedbackAndUsageHintForScreenDimmed() {
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.Speech.Builder builder2 = Feedback.Speech.builder();
        builder2.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
        builder2.text = this.service.getString(R.string.screen_dimmed);
        builder.speech = builder2.build();
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
        Feedback.Part.Builder builder3 = Feedback.Part.builder();
        Feedback.Speech.Builder builder4 = Feedback.Speech.builder();
        builder4.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
        Context context = this.service;
        builder4.text = context.getString(R.string.screen_dimming_exit_instruction_line2, this.gestureShortcutMapping.getGestureFromActionKey(context.getString(R.string.shortcut_value_talkback_breakout)), this.service.getString(R.string.shortcut_disable_dimming));
        builder3.speech = builder4.build();
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, builder3);
    }

    private final float getDeviceBrightness() {
        try {
            return Settings.System.getInt(this.service.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException unused) {
            return 1.0f;
        }
    }

    private final void initView() {
        if (this.viewParams == null || this.view == null) {
            this.windowManager = (WindowManager) this.service.getSystemService("window");
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.viewParams = layoutParams;
            layoutParams.type = 2032;
            this.viewParams.flags |= 2;
            this.viewParams.flags |= 8;
            this.viewParams.flags |= 16;
            this.viewParams.flags |= 1024;
            this.viewParams.flags &= -2097153;
            this.viewParams.flags &= -129;
            this.viewParams.format = -1;
            DimmingOverlayView dimmingOverlayView = new DimmingOverlayView(this.service);
            this.view = dimmingOverlayView;
            dimmingOverlayView.timerLimit = 180;
            dimmingOverlayView.progress.setMax(180);
        }
        this.view.setInstruction(this.gestureShortcutMapping.getGestureFromActionKey(this.service.getString(R.string.shortcut_value_talkback_breakout)));
        Point point = new Point();
        this.windowManager.getDefaultDisplay().getRealSize(point);
        this.viewParams.width = point.x;
        this.viewParams.height = point.y;
    }

    public static boolean isSupported(TalkBackService talkBackService) {
        isSupportedbyPlatform(talkBackService);
        if (!ScreenMonitor.isDeviceLocked(talkBackService)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedbyPlatform(TalkBackService talkBackService) {
        talkBackService.getCompositorFlavor();
        return true;
    }

    private final void makeScreenBright() {
        if (this.isDimmed) {
            this.isDimmed = false;
            this.isInstructionDisplayed = false;
            this.windowManager.removeViewImmediate(this.view);
            this.dimmingHandler.removeMessages(1);
            this.dimmingHandler.removeMessages(2);
            this.dimScreenNotifier$ar$class_merging$ar$class_merging$ar$class_merging.onScreenBright();
        }
        if (!this.stopFeedback) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(this.service.getString(R.string.screen_brightness_restored)));
        }
    }

    public final void disableDimming() {
        makeScreenBright();
        SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, this.service.getResources(), R.string.pref_dim_when_talkback_enabled_key, false);
    }

    public final void enableDimmingAndShowConfirmDialog$ar$ds() {
        if (isDimmingEnabled()) {
            announceFeedbackAndUsageHintForScreenDimmed();
            return;
        }
        DimScreenDialog dimScreenDialog = this.dimScreenDialog;
        if (dimScreenDialog == null) {
            return;
        }
        if (!dimScreenDialog.getShouldShowDialogPref()) {
            dimScreenDialog.dimScreenController.makeScreenDim();
            dimScreenDialog.setSharedPreferencesByKey$ar$ds();
        } else {
            dimScreenDialog.showDialog();
        }
    }

    public final void hideInstructionAndTurnOnDimming() {
        this.viewParams.dimAmount = 0.9f;
        this.viewParams.screenBrightness = 0.1f;
        WindowManager.LayoutParams layoutParams = this.viewParams;
        layoutParams.buttonBrightness = layoutParams.screenBrightness;
        this.isInstructionDisplayed = false;
        this.view.hideText();
        if (this.isDimmed) {
            this.windowManager.removeViewImmediate(this.view);
            this.windowManager.addView(this.view, this.viewParams);
        }
    }

    public final boolean isDimmingEnabled() {
        return SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, this.service.getResources(), R.string.pref_dim_when_talkback_enabled_key, R.bool.pref_dim_when_talkback_enabled_default);
    }

    public final void makeScreenDim() {
        if (!this.isDimmed) {
            this.isDimmed = true;
            initView();
            this.viewParams.dimAmount = 0.9f;
            this.viewParams.screenBrightness = getDeviceBrightness();
            this.viewParams.buttonBrightness = 0.1f;
            this.windowManager.addView(this.view, this.viewParams);
            this.view.showText();
            DimScreenDialog dimScreenDialog = this.dimScreenDialog;
            if (dimScreenDialog != null && !dimScreenDialog.getShouldShowDialogPref()) {
                hideInstructionAndTurnOnDimming();
            } else {
                this.dimmingHandler.sendEmptyMessage(1);
            }
            this.dimScreenNotifier$ar$class_merging$ar$class_merging$ar$class_merging.onScreenDim();
        }
        announceFeedbackAndUsageHintForScreenDimmed();
    }

    public final void onConfigurationChanged$ar$ds$e8a368f3_0() {
        if (this.isDimmed) {
            this.windowManager.removeViewImmediate(this.view);
        }
        this.view = null;
        initView();
        if (this.isDimmed) {
            if (this.isInstructionDisplayed) {
                this.view.showText();
            } else {
                this.view.hideText();
            }
            this.windowManager.addView(this.view, this.viewParams);
        }
    }

    public final void shutdown() {
        this.stopFeedback = true;
        suspend();
        this.viewParams = null;
        this.view = null;
    }

    public final void suspend() {
        makeScreenBright();
        DimScreenDialog dimScreenDialog = this.dimScreenDialog;
        if (dimScreenDialog != null) {
            dimScreenDialog.cancelDialog();
        }
    }
}
