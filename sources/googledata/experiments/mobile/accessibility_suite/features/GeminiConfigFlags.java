package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface GeminiConfigFlags {
    boolean enableGeminiVoiceCommand(Context context);

    boolean enableOnDeviceGeminiImageCaptioning(Context context);

    boolean enableServerSideGeminiImageCaptioning(Context context);

    String geminiModel(Context context);

    String prefixPrompt(Context context);

    String safetyThresholdDangerousContent(Context context);

    String safetyThresholdHarassment(Context context);

    String safetyThresholdHateSpeech(Context context);

    String safetyThresholdSexuallyExplicit(Context context);

    boolean useAratea(Context context);
}
