package com.google.android.accessibility.utils.output;

import android.os.Bundle;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SpeechController {
    public static final int DEFAULT_STREAM = 10;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CompletionRunner implements Runnable {
        private final UtteranceCompleteRunnable mRunnable;
        private final int mStatus;

        public CompletionRunner(UtteranceCompleteRunnable utteranceCompleteRunnable, int i) {
            this.mRunnable = utteranceCompleteRunnable;
            this.mStatus = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.mRunnable.run(this.mStatus);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Delegate {
        boolean isAudioPlaybackActive();

        boolean isMicrophoneActiveAndHeadphoneOff();

        boolean isPhoneCallActive();

        boolean isSsbActiveAndHeadphoneOff();

        void onSpeakingForcedFeedback();

        void onTtsReady();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpeakOptions {
        public Set mEarcons = null;
        public Set mHaptics = null;
        public int mQueueMode = 0;
        public int mFlags = 0;
        public int mUtteranceGroup = 0;
        public Bundle mSpeechParams = null;
        public Bundle mNonSpeechParams = null;
        public final UtteranceStartRunnable mStartingAction = null;
        public HapticPatternParser$$ExternalSyntheticLambda1 mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        public UtteranceCompleteRunnable mCompletedAction = null;

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SpeakOptions)) {
                return false;
            }
            SpeakOptions speakOptions = (SpeakOptions) obj;
            if (this.mQueueMode == speakOptions.mQueueMode && this.mFlags == speakOptions.mFlags && this.mUtteranceGroup == speakOptions.mUtteranceGroup && Objects.equals(this.mEarcons, speakOptions.mEarcons) && Objects.equals(this.mHaptics, speakOptions.mHaptics) && Objects.equals(this.mSpeechParams, speakOptions.mSpeechParams) && Objects.equals(this.mNonSpeechParams, speakOptions.mNonSpeechParams)) {
                UtteranceStartRunnable utteranceStartRunnable = speakOptions.mStartingAction;
                if (Objects.equals(null, null) && Objects.equals(this.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, speakOptions.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging) && Objects.equals(this.mCompletedAction, speakOptions.mCompletedAction)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hash(this.mEarcons, this.mHaptics, Integer.valueOf(this.mQueueMode), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUtteranceGroup), this.mSpeechParams, this.mNonSpeechParams, null, this.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, this.mCompletedAction);
        }

        public final String toString() {
            String str;
            String str2;
            String optionalField = StringBuilderUtils.optionalField("earcons", this.mEarcons);
            String optionalField2 = StringBuilderUtils.optionalField("haptics", this.mHaptics);
            int i = this.mQueueMode;
            StringBuilder sb = new StringBuilder(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
            if (true != SpannableUtils$NonCopyableTextSpan.hasQueueModeFlagSet(i, 1)) {
                str = "QUEUE";
            } else {
                str = "INTERRUPT";
            }
            sb.append(str);
            if (SpannableUtils$NonCopyableTextSpan.hasQueueModeFlagSet(i, 2)) {
                sb.append("/UNINTERRUPTIBLE");
            }
            if (SpannableUtils$NonCopyableTextSpan.hasQueueModeFlagSet(i, 4)) {
                sb.append("/CAN_IGNORE_INTERRUPTS");
            }
            if (SpannableUtils$NonCopyableTextSpan.hasQueueModeFlagSet(i, 8)) {
                sb.append("/FLUSH_ALL");
            }
            String optionalField3 = StringBuilderUtils.optionalField("queueMode", sb.toString());
            String optionalField4 = StringBuilderUtils.optionalField("flags", SpannableUtils$IdentifierSpan.flagsToString(this.mFlags, new AccessibilityEventUtils$$ExternalSyntheticLambda0(3)));
            int i2 = this.mUtteranceGroup;
            if (i2 == 0) {
                str2 = null;
            } else {
                switch (i2) {
                    case 0:
                        str2 = "UTTERANCE_GROUP_DEFAULT";
                        break;
                    case 1:
                        str2 = "UTTERANCE_GROUP_TEXT_SELECTION";
                        break;
                    case 2:
                        str2 = "UTTERANCE_GROUP_SEEK_PROGRESS";
                        break;
                    case 3:
                        str2 = "UTTERANCE_GROUP_PROGRESS_BAR_PROGRESS";
                        break;
                    case 4:
                        str2 = "UTTERANCE_GROUP_SCREEN_MAGNIFICATION";
                        break;
                    case 5:
                        str2 = "UTTERANCE_GROUP_CONTENT_CHANGE";
                        break;
                    case 6:
                        str2 = "UTTERANCE_GROUP_CONTENT_HINTS";
                        break;
                    default:
                        str2 = "(unknown utterance group)";
                        break;
                }
            }
            return StringBuilderUtils.joinFields(optionalField, optionalField2, optionalField3, optionalField4, StringBuilderUtils.optionalField("utteranceGroup", str2), StringBuilderUtils.optionalField("speechParams", this.mSpeechParams), StringBuilderUtils.optionalField("nonSpeechParams", this.mNonSpeechParams));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface UtteranceCompleteRunnable {
        void run(int i);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface UtteranceStartRunnable {
    }

    FailoverTextToSpeech getFailoverTts();

    void interrupt(boolean z);

    void speak(CharSequence charSequence, Performance.EventId eventId, SpeakOptions speakOptions);

    void speak$ar$class_merging$8236f667_0$ar$ds(CharSequence charSequence, Bundle bundle, Performance.EventId eventId);
}
