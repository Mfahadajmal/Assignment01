package com.google.android.accessibility.talkback.actor.search;

import android.content.Context;
import android.content.res.Configuration;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.utils.AccessibilityWindow;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UniversalSearchActor {
    private final Context context;
    public Configuration currentConfig;
    public Pipeline.FeedbackReturner pipeline;
    public final HapticPatternParser$$ExternalSyntheticLambda1 screenState$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SearchScreenOverlay searchScreenOverlay;
    public final FloatingActionButton.ShadowDelegateImpl state$ar$class_merging$eb11fcdf_0$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    public final TalkBackService talkBackService;

    public UniversalSearchActor(TalkBackService talkBackService, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, AppLifecycleMonitor appLifecycleMonitor, TalkBackLabelManager talkBackLabelManager) {
        this.context = talkBackService;
        this.talkBackService = talkBackService;
        this.screenState$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.searchScreenOverlay = new SearchScreenOverlay(talkBackService, appLifecycleMonitor, talkBackLabelManager);
        this.currentConfig = new Configuration(talkBackService.getResources().getConfiguration());
    }

    public final void cancelSearch(Performance.EventId eventId) {
        if (isUiVisible()) {
            this.searchScreenOverlay.hide();
            String string = this.context.getString(R.string.search_mode_cancel);
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mFlags = 30;
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            if (feedbackReturner != null) {
                Feedback.Part.Builder speech = Feedback.speech(string, speakOptions);
                speech.setInterruptGroup$ar$ds(0);
                speech.setInterruptLevel$ar$ds(1);
                speech.senderName = "UniversalSearchActor";
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, speech);
            }
        }
    }

    public final int getInitialFocusedWindowId() {
        AccessibilityWindowInfoCompat accessibilityWindowInfoCompat;
        AccessibilityWindow accessibilityWindow = this.searchScreenOverlay.initialFocusedWindow;
        if (accessibilityWindow == null || (accessibilityWindowInfoCompat = accessibilityWindow.windowCompat) == null) {
            return -1;
        }
        return accessibilityWindowInfoCompat.getId();
    }

    public final int getOverlayId() {
        return this.searchScreenOverlay.overlayPanel.overlayId;
    }

    public final boolean isUiVisible() {
        SearchScreenOverlayLayout searchScreenOverlayLayout = this.searchScreenOverlay.overlayPanel;
        if (searchScreenOverlayLayout != null && searchScreenOverlayLayout.getVisibility() == 0) {
            return true;
        }
        return false;
    }
}
