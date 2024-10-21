package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.utils.ReadOnly;
import com.google.android.accessibility.utils.StringBuilderUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HintEventInterpretation extends ReadOnly {
    public boolean forceFeedbackEvenIfAudioPlaybackActive = false;
    public boolean forceFeedbackEvenIfMicrophoneActive = false;
    public final int mHintType;
    public CharSequence mText;

    public HintEventInterpretation(int i) {
        this.mHintType = i;
    }

    public final String toString() {
        String str;
        int i = this.mHintType;
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            str = "HINT_TYPE_TYPO";
                        } else {
                            str = "HINT_TYPE_TEXT_SUGGESTION";
                        }
                    } else {
                        str = "HINT_TYPE_SELECTOR";
                    }
                } else {
                    str = "HINT_TYPE_SCREEN";
                }
            } else {
                str = "HINT_TYPE_INPUT_FOCUS";
            }
        } else {
            str = "HINT_TYPE_ACCESSIBILITY_FOCUS";
        }
        return StringBuilderUtils.joinFields(str, StringBuilderUtils.optionalText("Text", this.mText), StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", this.forceFeedbackEvenIfAudioPlaybackActive), StringBuilderUtils.optionalTag("forceFeedbackEvenIfMicrophoneActive", this.forceFeedbackEvenIfMicrophoneActive));
    }
}
