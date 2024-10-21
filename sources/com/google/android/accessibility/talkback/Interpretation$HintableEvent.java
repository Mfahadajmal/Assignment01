package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpretation$HintableEvent extends SpannableUtils$NonCopyableTextSpan {
    public final boolean forceFeedbackEvenIfAudioPlaybackActive;
    public final boolean forceFeedbackEvenIfMicrophoneActive;

    public Interpretation$HintableEvent(boolean z, boolean z2) {
        super((byte[]) null);
        this.forceFeedbackEvenIfAudioPlaybackActive = z;
        this.forceFeedbackEvenIfMicrophoneActive = z2;
    }

    public final boolean equals(Object obj) {
        Interpretation$HintableEvent interpretation$HintableEvent = (Interpretation$HintableEvent) SpannableUtils$NonCopyableTextSpan.castOrNull(obj, Interpretation$HintableEvent.class);
        if (interpretation$HintableEvent != null && this.forceFeedbackEvenIfAudioPlaybackActive == interpretation$HintableEvent.forceFeedbackEvenIfAudioPlaybackActive && this.forceFeedbackEvenIfMicrophoneActive == interpretation$HintableEvent.forceFeedbackEvenIfMicrophoneActive) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Boolean.valueOf(this.forceFeedbackEvenIfAudioPlaybackActive), Boolean.valueOf(this.forceFeedbackEvenIfMicrophoneActive));
    }

    public final String toString() {
        return StringBuilderUtils.joinFields("HintableEvent{", StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", this.forceFeedbackEvenIfAudioPlaybackActive), StringBuilderUtils.optionalTag("forceFeedbackEvenIfMicrophoneActive", this.forceFeedbackEvenIfMicrophoneActive), "}");
    }
}
