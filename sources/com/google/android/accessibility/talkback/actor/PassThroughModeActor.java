package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Region;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.Timer;
import java.util.TimerTask;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PassThroughModeActor {
    public boolean locked;
    public final PassThroughModeDialog passThroughDialog;
    private Timer passThroughGuardTimer;
    public Pipeline.FeedbackReturner pipeline;
    private final AccessibilityService service;
    public final FloatingActionButton.ShadowDelegateImpl state$ar$class_merging$39712d2b_0$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    public boolean touchExplorePassThroughActive;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PassThroughExitTask extends TimerTask {
        public PassThroughExitTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            PassThroughModeActor passThroughModeActor = PassThroughModeActor.this;
            if (!passThroughModeActor.locked) {
                passThroughModeActor.setTouchExplorePassThrough(false);
            }
        }
    }

    public PassThroughModeActor(AccessibilityService accessibilityService) {
        this.service = accessibilityService;
        this.passThroughDialog = new PassThroughModeDialog(accessibilityService);
    }

    public final void cancelPassThroughGuardTimer() {
        if (this.touchExplorePassThroughActive && !this.locked) {
            this.passThroughGuardTimer.cancel();
        }
    }

    public final void setTouchExplorePassThrough(boolean z) {
        Region region;
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && !this.locked) {
            if (z) {
                AccessibilityService accessibilityService = this.service;
                DisplayMetrics displayMetrics = new DisplayMetrics();
                Display display = ((DisplayManager) accessibilityService.getSystemService("display")).getDisplay(0);
                if (display == null) {
                    region = new Region();
                } else {
                    display.getRealMetrics(displayMetrics);
                    region = new Region(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
                }
                accessibilityService.setTouchExplorationPassthroughRegion(0, region);
                Timer timer = new Timer();
                this.passThroughGuardTimer = timer;
                timer.schedule(new PassThroughExitTask(), 4000L);
                LogUtils.v("PassThroughModeActor", "Enter touch explore pass-through mode.", new Object[0]);
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.sound(R.raw.chime_up));
            } else {
                this.service.setTouchExplorationPassthroughRegion(0, new Region());
                if (this.touchExplorePassThroughActive) {
                    LogUtils.v("PassThroughModeActor", "Leave touch explore pass-through mode.", new Object[0]);
                    Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                    Logger logger2 = Performance.DEFAULT_LOGGER;
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.sound(R.raw.chime_down));
                }
            }
            this.touchExplorePassThroughActive = z;
        }
    }

    public final void showEducationDialog() {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && !this.locked) {
            if (this.passThroughDialog.getShouldShowDialogPref()) {
                LogUtils.v("PassThroughModeActor", "Enter touch explore pass-through confirm dialog.", new Object[0]);
                this.passThroughDialog.showDialog();
            } else {
                setTouchExplorePassThrough(true);
            }
        }
    }
}
