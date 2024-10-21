package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiConfigFlagsImpl implements GeminiConfigFlags {
    public static final ProcessStablePhenotypeFlag enableGeminiVoiceCommand;
    public static final ProcessStablePhenotypeFlag enableOnDeviceGeminiImageCaptioning;
    public static final ProcessStablePhenotypeFlag enableServerSideGeminiImageCaptioning;
    public static final ProcessStablePhenotypeFlag geminiModel;
    public static final ProcessStablePhenotypeFlag prefixPrompt;
    public static final ProcessStablePhenotypeFlag safetyThresholdDangerousContent;
    public static final ProcessStablePhenotypeFlag safetyThresholdHarassment;
    public static final ProcessStablePhenotypeFlag safetyThresholdHateSpeech;
    public static final ProcessStablePhenotypeFlag safetyThresholdSexuallyExplicit;
    public static final ProcessStablePhenotypeFlag useAratea;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        enableGeminiVoiceCommand = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GeminiConfig__enable_gemini_voice_command", false, "com.google.android.marvin.talkback", of, true, false);
        enableOnDeviceGeminiImageCaptioning = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GeminiConfig__enable_on_device_gemini_image_captioning", true, "com.google.android.marvin.talkback", of, true, false);
        enableServerSideGeminiImageCaptioning = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GeminiConfig__enable_server_side_gemini_image_captioning", true, "com.google.android.marvin.talkback", of, true, false);
        geminiModel = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__gemini_model", "gemini-1.5-flash", "com.google.android.marvin.talkback", of, true, false);
        prefixPrompt = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__prefix_prompt", "", "com.google.android.marvin.talkback", of, true, false);
        safetyThresholdDangerousContent = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__safety_threshold_dangerous_content", "BLOCK_LOW_AND_ABOVE", "com.google.android.marvin.talkback", of, true, false);
        safetyThresholdHarassment = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__safety_threshold_harassment", "BLOCK_LOW_AND_ABOVE", "com.google.android.marvin.talkback", of, true, false);
        safetyThresholdHateSpeech = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__safety_threshold_hate_speech", "BLOCK_LOW_AND_ABOVE", "com.google.android.marvin.talkback", of, true, false);
        safetyThresholdSexuallyExplicit = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$1bcefca3_0("GeminiConfig__safety_threshold_sexually_explicit", "BLOCK_LOW_AND_ABOVE", "com.google.android.marvin.talkback", of, true, false);
        useAratea = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("GeminiConfig__use_aratea", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final boolean enableGeminiVoiceCommand(Context context) {
        return ((Boolean) enableGeminiVoiceCommand.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final boolean enableOnDeviceGeminiImageCaptioning(Context context) {
        return ((Boolean) enableOnDeviceGeminiImageCaptioning.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final boolean enableServerSideGeminiImageCaptioning(Context context) {
        return ((Boolean) enableServerSideGeminiImageCaptioning.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String geminiModel(Context context) {
        return (String) geminiModel.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String prefixPrompt(Context context) {
        return (String) prefixPrompt.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String safetyThresholdDangerousContent(Context context) {
        return (String) safetyThresholdDangerousContent.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String safetyThresholdHarassment(Context context) {
        return (String) safetyThresholdHarassment.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String safetyThresholdHateSpeech(Context context) {
        return (String) safetyThresholdHateSpeech.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final String safetyThresholdSexuallyExplicit(Context context) {
        return (String) safetyThresholdSexuallyExplicit.get(context);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.GeminiConfigFlags
    public final boolean useAratea(Context context) {
        return ((Boolean) useAratea.get(context)).booleanValue();
    }
}
