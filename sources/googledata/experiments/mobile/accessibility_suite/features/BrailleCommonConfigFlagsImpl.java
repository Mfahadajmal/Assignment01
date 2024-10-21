package googledata.experiments.mobile.accessibility_suite.features;

import android.content.Context;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlag;
import com.google.android.libraries.phenotype.client.stable.ProcessStablePhenotypeFlagFactory;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleCommonConfigFlagsImpl implements BrailleCommonConfigFlags {
    public static final ProcessStablePhenotypeFlag arabicBrailleGrade2;
    public static final ProcessStablePhenotypeFlag bulgarian8DotComputerBraille;
    public static final ProcessStablePhenotypeFlag cantoneseBraille;
    public static final ProcessStablePhenotypeFlag chineseChinaCommonBraille;
    public static final ProcessStablePhenotypeFlag chineseChinaCurrentWithTonesBraille;
    public static final ProcessStablePhenotypeFlag chineseChinaCurrentWithoutTonesBraille;
    public static final ProcessStablePhenotypeFlag chineseChinaTwoCellsBraille;
    public static final ProcessStablePhenotypeFlag chineseTaiwanBraille;
    public static final ProcessStablePhenotypeFlag danish8DotBrailleGrade2;
    public static final ProcessStablePhenotypeFlag danishBrailleGrade2;
    public static final ProcessStablePhenotypeFlag englishBrailleAmericanEditionGrade2;
    public static final ProcessStablePhenotypeFlag englishBrailleUnitedKingdomEditionGrade2;
    public static final ProcessStablePhenotypeFlag frenchBrailleGrade2;
    public static final ProcessStablePhenotypeFlag germanBrailleGrade2;
    public static final ProcessStablePhenotypeFlag hungarianBrailleGrade2;
    public static final ProcessStablePhenotypeFlag japaneseBraille;
    public static final ProcessStablePhenotypeFlag koreanBraille;
    public static final ProcessStablePhenotypeFlag norwegian8DotComputerBraille;
    public static final ProcessStablePhenotypeFlag norwegianBrailleGrade2;
    public static final ProcessStablePhenotypeFlag portugueseBrailleGrade2;
    public static final ProcessStablePhenotypeFlag replaceEmoji;
    public static final ProcessStablePhenotypeFlag spanishBrailleGrade2;
    public static final ProcessStablePhenotypeFlag swedenBrailleGrade2;
    public static final ProcessStablePhenotypeFlag turkishBrailleGrade2;
    public static final ProcessStablePhenotypeFlag urduBrailleGrade2;
    public static final ProcessStablePhenotypeFlag vietnameseBrailleGrade2;
    public static final ProcessStablePhenotypeFlag welshBrailleGrade2;

    static {
        RegularImmutableSet regularImmutableSet = RegularImmutableSet.EMPTY;
        ImmutableSet of = ImmutableSet.of((Object) "TALKBACK_ANDROID_PRIMES", (Object) "TALKBACK");
        arabicBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__arabic_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        bulgarian8DotComputerBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__bulgarian_8_dot_computer_braille", true, "com.google.android.marvin.talkback", of, true, false);
        cantoneseBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__cantonese_braille", false, "com.google.android.marvin.talkback", of, true, false);
        chineseChinaCommonBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__chinese_china_common_braille", false, "com.google.android.marvin.talkback", of, true, false);
        chineseChinaCurrentWithTonesBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__chinese_china_current_with_tones_braille", false, "com.google.android.marvin.talkback", of, true, false);
        chineseChinaCurrentWithoutTonesBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__chinese_china_current_without_tones_braille", false, "com.google.android.marvin.talkback", of, true, false);
        chineseChinaTwoCellsBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__chinese_china_two_cells_braille", false, "com.google.android.marvin.talkback", of, true, false);
        chineseTaiwanBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__chinese_taiwan_braille", false, "com.google.android.marvin.talkback", of, true, false);
        danish8DotBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__danish_8_dot_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        danishBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__danish_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        englishBrailleAmericanEditionGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__english_braille_american_edition_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        englishBrailleUnitedKingdomEditionGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__english_braille_united_kingdom_edition_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        frenchBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__french_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        germanBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__german_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        hungarianBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__hungarian_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        japaneseBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__japanese_braille", false, "com.google.android.marvin.talkback", of, true, false);
        koreanBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__korean_braille", false, "com.google.android.marvin.talkback", of, true, false);
        norwegian8DotComputerBraille = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__norwegian_8_dot_computer_braille", true, "com.google.android.marvin.talkback", of, true, false);
        norwegianBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__norwegian_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        portugueseBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__portuguese_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        replaceEmoji = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__replace_emoji", true, "com.google.android.marvin.talkback", of, true, false);
        spanishBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__spanish_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        swedenBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__sweden_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        turkishBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__turkish_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        urduBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__urdu_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        vietnameseBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__vietnamese_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
        welshBrailleGrade2 = ProcessStablePhenotypeFlagFactory.createFlagRestricted$ar$objectUnboxing$c42f40f1_0("BrailleCommonConfig__welsh_braille_grade2", true, "com.google.android.marvin.talkback", of, true, false);
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean arabicBrailleGrade2(Context context) {
        return ((Boolean) arabicBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean bulgarian8DotComputerBraille(Context context) {
        return ((Boolean) bulgarian8DotComputerBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean cantoneseBraille(Context context) {
        return ((Boolean) cantoneseBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean chineseChinaCommonBraille(Context context) {
        return ((Boolean) chineseChinaCommonBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean chineseChinaCurrentWithTonesBraille(Context context) {
        return ((Boolean) chineseChinaCurrentWithTonesBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean chineseChinaCurrentWithoutTonesBraille(Context context) {
        return ((Boolean) chineseChinaCurrentWithoutTonesBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean chineseChinaTwoCellsBraille(Context context) {
        return ((Boolean) chineseChinaTwoCellsBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean chineseTaiwanBraille(Context context) {
        return ((Boolean) chineseTaiwanBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean danish8DotBrailleGrade2(Context context) {
        return ((Boolean) danish8DotBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean danishBrailleGrade2(Context context) {
        return ((Boolean) danishBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean englishBrailleAmericanEditionGrade2(Context context) {
        return ((Boolean) englishBrailleAmericanEditionGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean englishBrailleUnitedKingdomEditionGrade2(Context context) {
        return ((Boolean) englishBrailleUnitedKingdomEditionGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean frenchBrailleGrade2(Context context) {
        return ((Boolean) frenchBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean germanBrailleGrade2(Context context) {
        return ((Boolean) germanBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean hungarianBrailleGrade2(Context context) {
        return ((Boolean) hungarianBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean japaneseBraille(Context context) {
        return ((Boolean) japaneseBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean koreanBraille(Context context) {
        return ((Boolean) koreanBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean norwegian8DotComputerBraille(Context context) {
        return ((Boolean) norwegian8DotComputerBraille.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean norwegianBrailleGrade2(Context context) {
        return ((Boolean) norwegianBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean portugueseBrailleGrade2(Context context) {
        return ((Boolean) portugueseBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean replaceEmoji(Context context) {
        return ((Boolean) replaceEmoji.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean spanishBrailleGrade2(Context context) {
        return ((Boolean) spanishBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean swedenBrailleGrade2(Context context) {
        return ((Boolean) swedenBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean turkishBrailleGrade2(Context context) {
        return ((Boolean) turkishBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean urduBrailleGrade2(Context context) {
        return ((Boolean) urduBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean vietnameseBrailleGrade2(Context context) {
        return ((Boolean) vietnameseBrailleGrade2.get(context)).booleanValue();
    }

    @Override // googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfigFlags
    public final boolean welshBrailleGrade2(Context context) {
        return ((Boolean) welshBrailleGrade2.get(context)).booleanValue();
    }
}
