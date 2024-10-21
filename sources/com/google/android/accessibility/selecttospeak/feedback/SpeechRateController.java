package com.google.android.accessibility.selecttospeak.feedback;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechRateController {
    public static final SpannableUtils$NonCopyableTextSpan Companion$ar$class_merging$9d095341_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    public static final float[] SPEECH_RATE_LIST = {0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f, 1.4f, 1.8f, 2.2f, 2.6f, 3.0f};
    public float speechRate;
    public int speechRateIndex;

    public SpeechRateController(float f, int i) {
        this.speechRateIndex = i;
        this.speechRate = f;
    }

    public static final SpeechRateController createSpeechRateController(Context context) {
        return SpannableUtils$NonCopyableTextSpan.createSpeechRateController$ar$ds(context);
    }

    public final boolean canIncreaseSpeechRate() {
        if (this.speechRateIndex < 10) {
            return true;
        }
        return false;
    }

    public final boolean canReduceSpeechRate() {
        if (this.speechRateIndex > 0) {
            return true;
        }
        return false;
    }
}
