package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.CollectCollectors;
import com.google.common.collect.ImmutableList;
import googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfig;
import j$.util.Collection;
import j$.util.Optional;
import j$.util.stream.Stream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleLanguages {
    public static final Locale LOCALE_AR = Locale.forLanguageTag("ar");
    public static final Locale LOCALE_BG = Locale.forLanguageTag("bg");
    public static final Locale LOCALE_CA = Locale.forLanguageTag("ca");
    public static final Locale LOCALE_CKB = Locale.forLanguageTag("ckb");
    public static final Locale LOCALE_CS = Locale.forLanguageTag("cs");
    public static final Locale LOCALE_CY = Locale.forLanguageTag("cy");
    public static final Locale LOCALE_DA = Locale.forLanguageTag("da");
    public static final Locale LOCALE_DE = Locale.GERMAN;
    public static final Locale LOCALE_EL = Locale.forLanguageTag("el");
    public static final Locale LOCALE_EN = Locale.ENGLISH;
    public static final Locale LOCALE_EN_GB = Locale.UK;
    public static final Locale LOCALE_EN_IN = Locale.forLanguageTag("en-IN");
    public static final Locale LOCALE_EN_US = Locale.US;
    public static final Locale LOCALE_ES = Locale.forLanguageTag("es");
    public static final Locale LOCALE_ET = Locale.forLanguageTag("et");
    public static final Locale LOCALE_FI = Locale.forLanguageTag("fi");
    public static final Locale LOCALE_FR = Locale.FRENCH;
    public static final Locale LOCALE_GRC = Locale.forLanguageTag("grc");
    public static final Locale LOCALE_GU_IN = Locale.forLanguageTag("gu-IN");
    public static final Locale LOCALE_HI_IN = Locale.forLanguageTag("hi-IN");
    public static final Locale LOCALE_HE_IL = Locale.forLanguageTag("he-IL");
    public static final Locale LOCALE_HR = Locale.forLanguageTag("hr");
    public static final Locale LOCALE_HU = Locale.forLanguageTag("hu");
    public static final Locale LOCALE_IS = Locale.forLanguageTag("is");
    public static final Locale LOCALE_IT = Locale.ITALIAN;
    public static final Locale LOCALE_JP = Locale.JAPANESE;
    public static final Locale LOCALE_KM = Locale.forLanguageTag("km");
    public static final Locale LOCALE_KO = Locale.KOREAN;
    public static final Locale LOCALE_KN = Locale.forLanguageTag("kn");
    public static final Locale LOCALE_LT = Locale.forLanguageTag("lt");
    public static final Locale LOCALE_LV = Locale.forLanguageTag("lv");
    public static final Locale LOCALE_ML_IN = Locale.forLanguageTag("ml-IN");
    public static final Locale LOCALE_MR_IN = Locale.forLanguageTag("mr-IN");
    public static final Locale LOCALE_NE = Locale.forLanguageTag("ne");
    public static final Locale LOCALE_NE_IN = Locale.forLanguageTag("ne-IN");
    public static final Locale LOCALE_NL = Locale.forLanguageTag("nl");
    public static final Locale LOCALE_NL_NL = Locale.forLanguageTag("nl-NL");
    public static final Locale LOCALE_NO = Locale.forLanguageTag("no");
    public static final Locale LOCALE_PA = Locale.forLanguageTag("pa");
    public static final Locale LOCALE_PL = Locale.forLanguageTag("pl");
    public static final Locale LOCALE_PT = Locale.forLanguageTag("pt");
    public static final Locale LOCALE_RO = Locale.forLanguageTag("ro");
    public static final Locale LOCALE_RU = Locale.forLanguageTag("ru");
    public static final Locale LOCALE_SD_IN = Locale.forLanguageTag("sd-IN");
    public static final Locale LOCALE_SE = Locale.forLanguageTag("se");
    public static final Locale LOCALE_SI = Locale.forLanguageTag("si");
    public static final Locale LOCALE_SK = Locale.forLanguageTag("sk");
    public static final Locale LOCALE_SR = Locale.forLanguageTag("sr");
    public static final Locale LOCALE_SV = Locale.forLanguageTag("sv");
    public static final Locale LOCALE_TA = Locale.forLanguageTag("ta");
    public static final Locale LOCALE_TE_IN = Locale.forLanguageTag("te-IN");
    public static final Locale LOCALE_TR = Locale.forLanguageTag("tr");
    public static final Locale LOCALE_UK = Locale.forLanguageTag("uk");
    public static final Locale LOCALE_UR = Locale.forLanguageTag("ur");
    public static final Locale LOCALE_VI = Locale.forLanguageTag("vi");
    public static final Locale LOCALE_YUE = Locale.forLanguageTag("yue");
    public static final Locale LOCALE_ZH_CN = Locale.SIMPLIFIED_CHINESE;
    public static final Locale LOCALE_ZH_TW = Locale.TRADITIONAL_CHINESE;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Code {
        ARABIC(BrailleLanguages.LOCALE_AR, R.string.code_user_facing_name_arabic, true, false),
        ARABIC_COMP8(BrailleLanguages.LOCALE_AR, R.string.code_user_facing_name_arabic_comp8, false, true),
        BULGARIAN(BrailleLanguages.LOCALE_BG, R.string.code_user_facing_name_bulgarian, false, false),
        BULGARIAN_COMP8(BrailleLanguages.LOCALE_BG, R.string.code_user_facing_name_bulgarian_comp8, false, true),
        CANTONESE(BrailleLanguages.LOCALE_YUE, R.string.code_user_facing_name_catonese, false, false),
        CATALAN(BrailleLanguages.LOCALE_CA, R.string.code_user_facing_name_ca_catalan, false, false),
        CHINESE_CHINA_CURRENT_WITH_TONES(BrailleLanguages.LOCALE_ZH_CN, R.string.code_user_facing_name_chinese_china_current_with_tones, false, false),
        CHINESE_CHINA_CURRENT_WITHOUT_TONES(BrailleLanguages.LOCALE_ZH_CN, R.string.code_user_facing_name_chinese_china_current_without_tones, false, false),
        CHINESE_CHINA_TWO_CELLS(BrailleLanguages.LOCALE_ZH_CN, R.string.code_user_facing_name_chinese_china_two_cells, false, false),
        CHINESE_CHINA_COMMON(BrailleLanguages.LOCALE_ZH_CN, R.string.code_user_facing_name_chinese_china_common, false, false),
        CHINESE_TAIWAN(BrailleLanguages.LOCALE_ZH_TW, R.string.code_user_facing_name_chinese_taiwan, false, false),
        CROATIAN(BrailleLanguages.LOCALE_HR, R.string.code_user_facing_name_croatian, false, false),
        CROATIAN_COMP8(BrailleLanguages.LOCALE_HR, R.string.code_user_facing_name_croatian_comp8, false, true),
        CZECH(BrailleLanguages.LOCALE_CS, R.string.code_user_facing_name_czech, false, false),
        CZECH_COMP8(BrailleLanguages.LOCALE_CS, R.string.code_user_facing_name_czech_comp8, false, true),
        DANISH(BrailleLanguages.LOCALE_DA, R.string.code_user_facing_name_danish, true, false),
        DANISH_8(BrailleLanguages.LOCALE_DA, R.string.code_user_facing_name_danish_eight, true, true),
        DANISH_COMP8(BrailleLanguages.LOCALE_DA, R.string.code_user_facing_name_danish_comp8, false, true),
        DUTCH_NL(BrailleLanguages.LOCALE_NL_NL, R.string.code_user_facing_name_dutch_nl, false, false),
        DUTCH_COMP8(BrailleLanguages.LOCALE_NL, R.string.code_user_facing_name_dutch_comp8, false, true),
        EN_IN(BrailleLanguages.LOCALE_EN_IN, R.string.code_user_facing_name_en_in, false, false),
        UEB(BrailleLanguages.LOCALE_EN, R.string.code_user_facing_name_ueb1, true, false),
        EN_UK(BrailleLanguages.LOCALE_EN_GB, R.string.code_user_facing_name_en_uk, true, false),
        EN_US_EBAE(BrailleLanguages.LOCALE_EN_US, R.string.code_user_facing_name_en_us_ebae, true, false),
        EN_NABCC(BrailleLanguages.LOCALE_EN, R.string.code_user_facing_name_en_nabcc_comp8, false, true),
        EN_COMP6(BrailleLanguages.LOCALE_EN_US, R.string.code_user_facing_name_en_comp6, false, false),
        ESTONIAN(BrailleLanguages.LOCALE_ET, R.string.code_user_facing_name_estonian, false, true),
        FINNISH(BrailleLanguages.LOCALE_FI, R.string.code_user_facing_name_finnish, false, false),
        FINNISH_COMP8(BrailleLanguages.LOCALE_FI, R.string.code_user_facing_name_finnish_comp8, false, true),
        FRENCH(BrailleLanguages.LOCALE_FR, R.string.code_user_facing_name_french, true, false),
        FRENCH_COMP8(BrailleLanguages.LOCALE_FR, R.string.code_user_facing_name_french_comp8, false, true),
        GREEK(BrailleLanguages.LOCALE_EL, R.string.code_user_facing_name_greek, false, false),
        GREEK_INTERNATIONAL(BrailleLanguages.LOCALE_GRC, R.string.code_user_facing_name_greek_international, false, false),
        GERMAN(BrailleLanguages.LOCALE_DE, R.string.code_user_facing_name_german, true, false),
        GERMAN_COMP8(BrailleLanguages.LOCALE_DE, R.string.code_user_facing_name_german_comp8, false, true),
        GUJARATI(BrailleLanguages.LOCALE_GU_IN, R.string.code_user_facing_name_gujarati_india, false, false),
        HEBREW(BrailleLanguages.LOCALE_HE_IL, R.string.code_user_facing_name_hebrew, false, false),
        HEBREW_COMP8(BrailleLanguages.LOCALE_HE_IL, R.string.code_user_facing_name_hebrew_comp8, false, true),
        HINDI(BrailleLanguages.LOCALE_HI_IN, R.string.code_user_facing_name_hindi_india, false, false),
        HUNGARIAN(BrailleLanguages.LOCALE_HU, R.string.code_user_facing_name_hungarian, true, false),
        HUNGARIAN_COMP8(BrailleLanguages.LOCALE_HU, R.string.code_user_facing_name_hungarian_comp8, false, true),
        ICELANDIC(BrailleLanguages.LOCALE_IS, R.string.code_user_facing_name_icelandic, false, false),
        ICELANDIC_COMP8(BrailleLanguages.LOCALE_IS, R.string.code_user_facing_name_icelandic_comp8, false, true),
        ITALIAN_COMP6(BrailleLanguages.LOCALE_IT, R.string.code_user_facing_name_italian_comp6, false, false),
        ITALIAN_COMP8(BrailleLanguages.LOCALE_IT, R.string.code_user_facing_name_italian_comp8, false, true),
        JAPANESE(BrailleLanguages.LOCALE_JP, R.string.code_user_facing_name_japanese, false, true),
        KANNADA(BrailleLanguages.LOCALE_KN, R.string.code_user_facing_name_kannada, false, false),
        KHMER(BrailleLanguages.LOCALE_KM, R.string.code_user_facing_name_khmer, false, false),
        KOREAN(BrailleLanguages.LOCALE_KO, R.string.code_user_facing_name_korean, true, false),
        KOREAN_2006(BrailleLanguages.LOCALE_KO, R.string.code_user_facing_name_korean_2006, true, false),
        KURDISH(BrailleLanguages.LOCALE_CKB, R.string.code_user_facing_name_central_kurdish, false, false),
        LATVIAN(BrailleLanguages.LOCALE_LV, R.string.code_user_facing_name_latvian, false, false),
        LITHUANIAN(BrailleLanguages.LOCALE_LT, R.string.code_user_facing_name_lithuanian, false, false),
        LITHUANIAN_8(BrailleLanguages.LOCALE_LT, R.string.code_user_facing_name_lithuanian_8, false, true),
        MALAYALAM_IN(BrailleLanguages.LOCALE_ML_IN, R.string.code_user_facing_name_malayalam_indian, false, false),
        MARATHI_IN(BrailleLanguages.LOCALE_MR_IN, R.string.code_user_facing_name_marathi_indian, false, false),
        NEPALI(BrailleLanguages.LOCALE_NE, R.string.code_user_facing_name_nepali, false, true),
        NEPALI_IN(BrailleLanguages.LOCALE_NE_IN, R.string.code_user_facing_name_nepali_indian, false, false),
        NORWEGIAN(BrailleLanguages.LOCALE_NO, R.string.code_user_facing_name_norwegian, true, false),
        NORWEGIAN_8_NO(BrailleLanguages.LOCALE_NO, R.string.code_user_facing_name_norwegian_8_no, false, true),
        NORWEGIAN_COMP8(BrailleLanguages.LOCALE_NO, R.string.code_user_facing_name_norwegian_comp8, false, true),
        NORWEGIAN_8(BrailleLanguages.LOCALE_NO, R.string.code_user_facing_name_norwegian_8, false, true),
        NORWEGIAN_8_6FALLBACK(BrailleLanguages.LOCALE_NO, R.string.code_user_facing_name_norwegian_8_with_6_fallback, false, true),
        POLISH(BrailleLanguages.LOCALE_PL, R.string.code_user_facing_name_polish, false, false),
        POLISH_COMP8(BrailleLanguages.LOCALE_PL, R.string.code_user_facing_name_polish_comp8, false, true),
        PORTUGUESE(BrailleLanguages.LOCALE_PT, R.string.code_user_facing_name_portuguese, true, false),
        PORTUGUESE_COMP8(BrailleLanguages.LOCALE_PT, R.string.code_user_facing_name_portuguese_comp8, false, true),
        PUNJABI(BrailleLanguages.LOCALE_PA, R.string.code_user_facing_name_punjabi, false, false),
        ROMANIAN_COMP8(BrailleLanguages.LOCALE_RO, R.string.code_user_facing_name_romanian_comp8, false, true),
        RUSSIAN(BrailleLanguages.LOCALE_RU, R.string.code_user_facing_name_russian, false, false),
        RUSSIAN_DETAILED(BrailleLanguages.LOCALE_RU, R.string.code_user_facing_name_russian_detailed, false, false),
        RUSSIAN_COMP8(BrailleLanguages.LOCALE_RU, R.string.code_user_facing_name_russian_comp8, false, true),
        SERBIAN(BrailleLanguages.LOCALE_SR, R.string.code_user_facing_name_serbian, false, false),
        SINDHI_IN(BrailleLanguages.LOCALE_SD_IN, R.string.code_user_facing_name_sindhi_indian, false, false),
        SINHALA(BrailleLanguages.LOCALE_SI, R.string.code_user_facing_name_sinhala, false, false),
        SLOVAK(BrailleLanguages.LOCALE_SK, R.string.code_user_facing_name_slovak, false, false),
        SPANISH(BrailleLanguages.LOCALE_ES, R.string.code_user_facing_name_spanish, true, false),
        SPANISH_COMP8(BrailleLanguages.LOCALE_ES, R.string.code_user_facing_name_spanish_comp8, false, true),
        SWEDEN(BrailleLanguages.LOCALE_SV, R.string.code_user_facing_name_sweden, true, false),
        SWEDEN_8(BrailleLanguages.LOCALE_SE, R.string.code_user_facing_name_sweden_8, false, true),
        SWEDISH_COMP8_1996(BrailleLanguages.LOCALE_SV, R.string.code_user_facing_name_swedish_comp8_1996, false, true),
        SWEDISH_COMP8_1989(BrailleLanguages.LOCALE_SV, R.string.code_user_facing_name_swedish_comp8_1989, false, true),
        TAMIL(BrailleLanguages.LOCALE_TA, R.string.code_user_facing_name_tamil, false, false),
        TELUGU_IN(BrailleLanguages.LOCALE_TE_IN, R.string.code_user_facing_name_telugu_in, false, false),
        TURKISH(BrailleLanguages.LOCALE_TR, R.string.code_user_facing_name_turkish, true, false),
        TURKISH_COMP8(BrailleLanguages.LOCALE_TR, R.string.code_user_facing_name_turkish_comp8, false, true),
        UKRAINIAN(BrailleLanguages.LOCALE_UK, R.string.code_user_facing_name_ukrainian, false, false),
        UKRAINIAN_COMP8(BrailleLanguages.LOCALE_UK, R.string.code_user_facing_name_ukrainian_comp8, false, true),
        URDU(BrailleLanguages.LOCALE_UR, R.string.code_user_facing_name_urdu, true, false),
        VIETNAMESE(BrailleLanguages.LOCALE_VI, R.string.code_user_facing_name_vietnamese, true, false),
        VIETNAMESE_COMP8(BrailleLanguages.LOCALE_VI, R.string.code_user_facing_name_vietnamese_comp8, false, true),
        WELSH(BrailleLanguages.LOCALE_CY, R.string.code_user_facing_name_welsh, true, false),
        STUB(BrailleLanguages.LOCALE_EN, 0, false, false);

        public final boolean eightDot;
        public final Locale locale;
        private final int stringId;
        private final boolean supportsContracted;

        Code(Locale locale, int i, boolean z, boolean z2) {
            this.locale = locale;
            this.stringId = i;
            this.supportsContracted = z;
            this.eightDot = z2;
        }

        public final String getUserFacingName(Context context) {
            if (isAvailable(context)) {
                return context.getResources().getString(this.stringId);
            }
            return "";
        }

        public final boolean isAvailable(Context context) {
            if (this.stringId != 0) {
                if (this != KOREAN && this != KOREAN_2006) {
                    if (this == JAPANESE) {
                        return BrailleCommonConfig.INSTANCE.get().japaneseBraille(context);
                    }
                    if (this == CANTONESE) {
                        return BrailleCommonConfig.INSTANCE.get().cantoneseBraille(context);
                    }
                    if (this == CHINESE_CHINA_COMMON) {
                        return BrailleCommonConfig.INSTANCE.get().chineseChinaCommonBraille(context);
                    }
                    if (this == CHINESE_CHINA_CURRENT_WITH_TONES) {
                        return BrailleCommonConfig.INSTANCE.get().chineseChinaCurrentWithTonesBraille(context);
                    }
                    if (this == CHINESE_CHINA_CURRENT_WITHOUT_TONES) {
                        return BrailleCommonConfig.INSTANCE.get().chineseChinaCurrentWithoutTonesBraille(context);
                    }
                    if (this == CHINESE_CHINA_TWO_CELLS) {
                        return BrailleCommonConfig.INSTANCE.get().chineseChinaTwoCellsBraille(context);
                    }
                    if (this == CHINESE_TAIWAN) {
                        return BrailleCommonConfig.INSTANCE.get().chineseTaiwanBraille(context);
                    }
                    if (this == NORWEGIAN_COMP8) {
                        return BrailleCommonConfig.INSTANCE.get().norwegian8DotComputerBraille(context);
                    }
                    if (this == BULGARIAN_COMP8) {
                        return BrailleCommonConfig.INSTANCE.get().bulgarian8DotComputerBraille(context);
                    }
                    return true;
                }
                return BrailleCommonConfig.INSTANCE.get().koreanBraille(context);
            }
            return false;
        }

        public final boolean isSupportsContracted(Context context) {
            int ordinal = ordinal();
            if (ordinal != 0) {
                if (ordinal != 29) {
                    if (ordinal != 33) {
                        if (ordinal != 39) {
                            if (ordinal != 58) {
                                if (ordinal != 65) {
                                    if (ordinal != 76) {
                                        if (ordinal != 78) {
                                            if (ordinal != 84) {
                                                if (ordinal != 91) {
                                                    if (ordinal != 15) {
                                                        if (ordinal != 16) {
                                                            if (ordinal != 22) {
                                                                if (ordinal != 23) {
                                                                    if (ordinal != 88) {
                                                                        if (ordinal != 89) {
                                                                            return this.supportsContracted;
                                                                        }
                                                                        return BrailleCommonConfig.INSTANCE.get().vietnameseBrailleGrade2(context);
                                                                    }
                                                                    return BrailleCommonConfig.INSTANCE.get().urduBrailleGrade2(context);
                                                                }
                                                                return BrailleCommonConfig.INSTANCE.get().englishBrailleAmericanEditionGrade2(context);
                                                            }
                                                            return BrailleCommonConfig.INSTANCE.get().englishBrailleUnitedKingdomEditionGrade2(context);
                                                        }
                                                        return BrailleCommonConfig.INSTANCE.get().danish8DotBrailleGrade2(context);
                                                    }
                                                    return BrailleCommonConfig.INSTANCE.get().danishBrailleGrade2(context);
                                                }
                                                return BrailleCommonConfig.INSTANCE.get().welshBrailleGrade2(context);
                                            }
                                            return BrailleCommonConfig.INSTANCE.get().turkishBrailleGrade2(context);
                                        }
                                        return BrailleCommonConfig.INSTANCE.get().swedenBrailleGrade2(context);
                                    }
                                    return BrailleCommonConfig.INSTANCE.get().spanishBrailleGrade2(context);
                                }
                                return BrailleCommonConfig.INSTANCE.get().portugueseBrailleGrade2(context);
                            }
                            return BrailleCommonConfig.INSTANCE.get().norwegianBrailleGrade2(context);
                        }
                        return BrailleCommonConfig.INSTANCE.get().hungarianBrailleGrade2(context);
                    }
                    return BrailleCommonConfig.INSTANCE.get().germanBrailleGrade2(context);
                }
                return BrailleCommonConfig.INSTANCE.get().frenchBrailleGrade2(context);
            }
            return BrailleCommonConfig.INSTANCE.get().arabicBrailleGrade2(context);
        }
    }

    public static List getAvailableCodes(Context context) {
        ArrayList arrayList = new ArrayList();
        for (Code code : Code.values()) {
            if (code.isAvailable(context)) {
                arrayList.add(code);
            }
        }
        return arrayList;
    }

    public static Code getDefaultCode(Context context) {
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return Code.UEB;
        }
        Stream filter = Collection.EL.stream(getAvailableCodes(context)).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(locale, 18));
        int i = ImmutableList.ImmutableList$ar$NoOp;
        List list = (List) filter.collect(CollectCollectors.TO_IMMUTABLE_LIST);
        Optional findFirst = Collection.EL.stream(list).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(locale, 19)).findFirst();
        if (findFirst.isPresent()) {
            return (Code) findFirst.get();
        }
        if (!list.isEmpty()) {
            return (Code) list.get(0);
        }
        return Code.UEB;
    }
}
