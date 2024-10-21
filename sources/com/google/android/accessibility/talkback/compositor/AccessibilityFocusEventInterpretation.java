package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFocusEventInterpretation extends ReadOnly {
    public final int event;
    public boolean forceFeedbackEvenIfAudioPlaybackActive = true;
    public boolean forceFeedbackEvenIfMicrophoneActive = true;
    public boolean forceFeedbackEvenIfSsbActive = false;
    public boolean isInitialFocusAfterScreenStateChange = false;
    public boolean shouldMuteFeedback = false;
    public boolean isNavigateByUser = false;

    public AccessibilityFocusEventInterpretation(int i) {
        this.event = i;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("AccessibilityFocusEventInterpretation{", StringBuilderUtils.optionalInt("event", this.event, -1), StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", this.forceFeedbackEvenIfAudioPlaybackActive), StringBuilderUtils.optionalTag("forceFeedbackEvenIfMicrophoneActive", this.forceFeedbackEvenIfMicrophoneActive), StringBuilderUtils.optionalTag("forceFeedbackEvenIfSsbActive", this.forceFeedbackEvenIfSsbActive), StringBuilderUtils.optionalTag("shouldMuteFeedback", this.shouldMuteFeedback), StringBuilderUtils.optionalTag("isInitialFocusAfterScreenStateChange", this.isInitialFocusAfterScreenStateChange), StringBuilderUtils.optionalTag("isNavigateByUser", this.isNavigateByUser), "}");
    }
}
