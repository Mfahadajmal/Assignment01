package com.google.android.accessibility.braille.common.translate;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.TextUtils;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.accessibility.braille.translate.BrailleTranslator;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.common.collect.ImmutableMap;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.lang.Character;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditBufferKorean extends EditBufferUnContracted {
    private final Context context;
    private final ImmutableMap finalConsonantsTranslationMap;
    private final ImmutableMap initialConsonantsTranslationMap;
    private final ImmutableMap speakFinalConsonantsTranslationMap;
    private final ImmutableMap speakInitialConsonantsTranslationMap;
    private final ImmutableMap speakVowelTranslationMap;
    private final ImmutableMap specialOTranslationMap;
    private final OnDeviceTextDetectionLoadLogEvent talkBack$ar$class_merging$ar$class_merging$ar$class_merging;
    private final BrailleTranslator translator;
    private final ImmutableMap vowelTranslationMap;

    public EditBufferKorean(Context context, BrailleTranslator brailleTranslator, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        super(context, brailleTranslator, onDeviceTextDetectionLoadLogEvent);
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0("4", "ᄀ");
        builder.put$ar$ds$de9b9d28_0("14", "ㄴ");
        builder.put$ar$ds$de9b9d28_0("24", "ㄷ");
        builder.put$ar$ds$de9b9d28_0("5", "ㄹ");
        builder.put$ar$ds$de9b9d28_0("15", "ㅁ");
        builder.put$ar$ds$de9b9d28_0("45", "ㅂ");
        builder.put$ar$ds$de9b9d28_0("6", "ㅅ");
        builder.put$ar$ds$de9b9d28_0("46", "ㅈ");
        builder.put$ar$ds$de9b9d28_0("56", "ㅊ");
        builder.put$ar$ds$de9b9d28_0("124", "ㅋ");
        builder.put$ar$ds$de9b9d28_0("125", "ㅌ");
        builder.put$ar$ds$de9b9d28_0("145", "ㅍ");
        builder.put$ar$ds$de9b9d28_0("245", "ㅎ");
        builder.put$ar$ds$de9b9d28_0("1245", "ㅇ");
        this.initialConsonantsTranslationMap = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        builder2.put$ar$ds$de9b9d28_0("126", "ᅡ");
        builder2.put$ar$ds$de9b9d28_0("345", "ᅣ");
        builder2.put$ar$ds$de9b9d28_0("234", "ᅥ");
        builder2.put$ar$ds$de9b9d28_0("156", "ᅧ");
        builder2.put$ar$ds$de9b9d28_0("136", "ᅩ");
        builder2.put$ar$ds$de9b9d28_0("346", "ᅭ");
        builder2.put$ar$ds$de9b9d28_0("134", "ᅮ");
        builder2.put$ar$ds$de9b9d28_0("146", "ᅲ");
        builder2.put$ar$ds$de9b9d28_0("246", "ᅳ");
        builder2.put$ar$ds$de9b9d28_0("135", "ᅵ");
        builder2.put$ar$ds$de9b9d28_0("1345", "ᅦ");
        builder2.put$ar$ds$de9b9d28_0("1235", "ᅢ");
        builder2.put$ar$ds$de9b9d28_0("34", "ᅨ");
        builder2.put$ar$ds$de9b9d28_0("2456", "ᅴ");
        builder2.put$ar$ds$de9b9d28_0("1236", "ᅪ");
        builder2.put$ar$ds$de9b9d28_0("1234", "ᅯ");
        builder2.put$ar$ds$de9b9d28_0("13456", "ᅬ");
        builder2.put$ar$ds$de9b9d28_0("345-1235", "ᅤ");
        builder2.put$ar$ds$de9b9d28_0("1236-1235", "ᅫ");
        builder2.put$ar$ds$de9b9d28_0("1234-1235", "ᅰ");
        builder2.put$ar$ds$de9b9d28_0("134-1235", "ᅱ");
        this.vowelTranslationMap = builder2.buildOrThrow();
        ImmutableMap.Builder builder3 = new ImmutableMap.Builder();
        builder3.put$ar$ds$de9b9d28_0("1", "ᆨ");
        builder3.put$ar$ds$de9b9d28_0("25", "ᆫ");
        builder3.put$ar$ds$de9b9d28_0("35", "ᆮ");
        builder3.put$ar$ds$de9b9d28_0("2", "ᆯ");
        builder3.put$ar$ds$de9b9d28_0("26", "ᆷ");
        builder3.put$ar$ds$de9b9d28_0("12", "ᆸ");
        builder3.put$ar$ds$de9b9d28_0("3", "ᆺ");
        builder3.put$ar$ds$de9b9d28_0("13", "ᆽ");
        builder3.put$ar$ds$de9b9d28_0("23", "ᆾ");
        builder3.put$ar$ds$de9b9d28_0("235", "ᆿ");
        builder3.put$ar$ds$de9b9d28_0("236", "ᇀ");
        builder3.put$ar$ds$de9b9d28_0("256", "ᇁ");
        builder3.put$ar$ds$de9b9d28_0("356", "ᇂ");
        builder3.put$ar$ds$de9b9d28_0("2356", "ᆼ");
        this.finalConsonantsTranslationMap = builder3.buildOrThrow();
        ImmutableMap.Builder builder4 = new ImmutableMap.Builder();
        builder4.put$ar$ds$de9b9d28_0("4", "ㄱ");
        builder4.put$ar$ds$de9b9d28_0("14", "ㄴ");
        builder4.put$ar$ds$de9b9d28_0("24", "ㄷ");
        builder4.put$ar$ds$de9b9d28_0("5", "ㄹ");
        builder4.put$ar$ds$de9b9d28_0("15", "ㅁ");
        builder4.put$ar$ds$de9b9d28_0("45", "ㅂ");
        builder4.put$ar$ds$de9b9d28_0("6", "ㅅ");
        builder4.put$ar$ds$de9b9d28_0("46", "ㅈ");
        builder4.put$ar$ds$de9b9d28_0("56", "ㅊ");
        builder4.put$ar$ds$de9b9d28_0("124", "ㅋ");
        builder4.put$ar$ds$de9b9d28_0("125", "ㅌ");
        builder4.put$ar$ds$de9b9d28_0("145", "ㅍ");
        builder4.put$ar$ds$de9b9d28_0("245", "ㅎ");
        builder4.put$ar$ds$de9b9d28_0("1245", "ㅇ");
        this.speakInitialConsonantsTranslationMap = builder4.buildOrThrow();
        ImmutableMap.Builder builder5 = new ImmutableMap.Builder();
        builder5.put$ar$ds$de9b9d28_0("126", "ㅏ");
        builder5.put$ar$ds$de9b9d28_0("345", "ㅑ");
        builder5.put$ar$ds$de9b9d28_0("234", "ㅓ");
        builder5.put$ar$ds$de9b9d28_0("156", "ㅕ");
        builder5.put$ar$ds$de9b9d28_0("136", "ㅗ");
        builder5.put$ar$ds$de9b9d28_0("346", "ㅛ");
        builder5.put$ar$ds$de9b9d28_0("134", "ㅜ");
        builder5.put$ar$ds$de9b9d28_0("146", "ㅠ");
        builder5.put$ar$ds$de9b9d28_0("246", "ㅡ");
        builder5.put$ar$ds$de9b9d28_0("135", "ㅣ");
        builder5.put$ar$ds$de9b9d28_0("1345", "ㅔ");
        builder5.put$ar$ds$de9b9d28_0("1235", "ㅐ");
        builder5.put$ar$ds$de9b9d28_0("34", "ㅖ");
        builder5.put$ar$ds$de9b9d28_0("2456", "ㅢ");
        builder5.put$ar$ds$de9b9d28_0("1236", "ㅘ");
        builder5.put$ar$ds$de9b9d28_0("1234", "ㅝ");
        builder5.put$ar$ds$de9b9d28_0("13456", "ㅚ");
        builder5.put$ar$ds$de9b9d28_0("345-1235", "ㅒ");
        builder5.put$ar$ds$de9b9d28_0("1236-1235", "ㅙ");
        builder5.put$ar$ds$de9b9d28_0("1234-1235", "ㅞ");
        builder5.put$ar$ds$de9b9d28_0("134-1235", "ㅟ");
        this.speakVowelTranslationMap = builder5.buildOrThrow();
        ImmutableMap.Builder builder6 = new ImmutableMap.Builder();
        builder6.put$ar$ds$de9b9d28_0("1", "ㄱ");
        builder6.put$ar$ds$de9b9d28_0("25", "ㄴ");
        builder6.put$ar$ds$de9b9d28_0("35", "ㄷ");
        builder6.put$ar$ds$de9b9d28_0("2", "ㄹ");
        builder6.put$ar$ds$de9b9d28_0("26", "ㅁ");
        builder6.put$ar$ds$de9b9d28_0("12", "ㅂ");
        builder6.put$ar$ds$de9b9d28_0("3", "ㅅ");
        builder6.put$ar$ds$de9b9d28_0("13", "ㅈ");
        builder6.put$ar$ds$de9b9d28_0("23", "ㅊ");
        builder6.put$ar$ds$de9b9d28_0("235", "ㅋ");
        builder6.put$ar$ds$de9b9d28_0("236", "ㅌ");
        builder6.put$ar$ds$de9b9d28_0("256", "ㅍ");
        builder6.put$ar$ds$de9b9d28_0("356", "ㅎ");
        builder6.put$ar$ds$de9b9d28_0("2356", "ㅇ");
        this.speakFinalConsonantsTranslationMap = builder6.buildOrThrow();
        ImmutableMap.Builder builder7 = new ImmutableMap.Builder();
        builder7.put$ar$ds$de9b9d28_0("126", "아");
        builder7.put$ar$ds$de9b9d28_0("345", "야");
        builder7.put$ar$ds$de9b9d28_0("234", "어");
        builder7.put$ar$ds$de9b9d28_0("156", "여");
        builder7.put$ar$ds$de9b9d28_0("136", "오");
        builder7.put$ar$ds$de9b9d28_0("346", "요");
        builder7.put$ar$ds$de9b9d28_0("134", "우");
        builder7.put$ar$ds$de9b9d28_0("146", "유");
        builder7.put$ar$ds$de9b9d28_0("246", "으");
        builder7.put$ar$ds$de9b9d28_0("135", "이");
        builder7.put$ar$ds$de9b9d28_0("1235", "애");
        builder7.put$ar$ds$de9b9d28_0("1345", "에");
        builder7.put$ar$ds$de9b9d28_0("345-1235", "얘");
        builder7.put$ar$ds$de9b9d28_0("34", "예");
        builder7.put$ar$ds$de9b9d28_0("1236", "와");
        builder7.put$ar$ds$de9b9d28_0("1236-1235", "왜");
        builder7.put$ar$ds$de9b9d28_0("13456", "외");
        builder7.put$ar$ds$de9b9d28_0("2456", "의");
        builder7.put$ar$ds$de9b9d28_0("1234", "워");
        builder7.put$ar$ds$de9b9d28_0("1234-1235", "웨");
        builder7.put$ar$ds$de9b9d28_0("134-1235", "위");
        this.specialOTranslationMap = builder7.buildOrThrow();
        this.translator = brailleTranslator;
        this.context = context;
        this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
    }

    private final String correctIfPossible(String str) {
        BrailleWord holdings = getHoldings(str);
        if (holdings.size() == 1 && this.specialOTranslationMap.containsKey(holdings.toString())) {
            StringBuilder sb = new StringBuilder((String) this.specialOTranslationMap.get(holdings.toString()));
            int indexOf = this.holdings.indexOf(holdings) + holdings.size();
            BrailleWord brailleWord = this.holdings;
            BrailleWord subword = brailleWord.subword(indexOf, brailleWord.size());
            String translateToPrint = this.translator.translateToPrint(subword);
            if (!isAllHangul$ar$ds(translateToPrint)) {
                sb.append(getHangulIfPossible(subword));
            } else {
                sb.append(translateToPrint);
            }
            return sb.toString();
        }
        return str;
    }

    private final String getAppendResult(String str, String str2, BrailleCharacter brailleCharacter) {
        String str3;
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return "";
        }
        if (TextUtils.isEmpty(str)) {
            if (!isAllHangul$ar$ds(str2)) {
                if (this.specialOTranslationMap.containsKey(brailleCharacter.toString())) {
                    return (String) this.specialOTranslationMap.get(brailleCharacter.toString());
                }
                return getSyllable(brailleCharacter);
            }
            return str2;
        }
        if (str2.startsWith(str)) {
            String substring = str2.substring(str.length());
            if (!TextUtils.isEmpty(substring) && !isAllHangul$ar$ds(substring)) {
                String substring2 = str2.substring(0, str2.indexOf(str) + 1);
                if (!isAllHangul$ar$ds(substring2) && !TextUtils.isEmpty(substring2)) {
                    return correctIfPossible(substring2);
                }
                BrailleWord holdings = getHoldings(substring2);
                BrailleWord brailleWord = this.holdings;
                return String.valueOf(substring2).concat(String.valueOf(getHangulIfPossible(brailleWord.subword(brailleWord.indexOf(holdings) + holdings.size(), this.holdings.size()))));
            }
            return str2;
        }
        if (!isAllHangul$ar$ds(str2)) {
            int i = -1;
            for (int i2 = 1; i2 < str.length(); i2++) {
                if (true == str2.startsWith(str.substring(0, i2))) {
                    i = i2;
                }
            }
            if (i == -1) {
                str3 = "";
            } else {
                str3 = str.substring(0, i);
            }
            if (!TextUtils.isEmpty(str3)) {
                if (!isAllHangul$ar$ds(str3)) {
                    return correctIfPossible(str3);
                }
                return str3;
            }
            return getComposedCharacter(this.holdings);
        }
        return str2;
    }

    private final String getComposedCharacter(BrailleWord brailleWord) {
        char[] cArr = new char[brailleWord.size()];
        for (int i = 0; i < brailleWord.size(); i++) {
            BrailleCharacter brailleCharacter = brailleWord.get(i);
            if (!TextUtils.isEmpty(getSyllable(brailleCharacter))) {
                cArr[i] = getSyllable(brailleCharacter).charAt(0);
            }
        }
        return new String(cArr);
    }

    private final String getHangulIfPossible(BrailleWord brailleWord) {
        if (brailleWord.size() == 1) {
            return getSyllable(brailleWord.get(0));
        }
        String translateToPrint = this.translator.translateToPrint(brailleWord);
        if (isAllHangul$ar$ds(translateToPrint)) {
            return translateToPrint;
        }
        return getComposedCharacter(brailleWord);
    }

    private final BrailleWord getHoldings(String str) {
        for (int i = 1; i < this.holdings.size(); i++) {
            BrailleWord subword = this.holdings.subword(0, i);
            if (this.translator.translateToPrint(subword).equals(str)) {
                return subword;
            }
        }
        return new BrailleWord();
    }

    private final String getSpeakableAnnouncement(BrailleCharacter brailleCharacter) {
        String str = (String) this.speakInitialConsonantsTranslationMap.get(brailleCharacter.toString());
        if (TextUtils.isEmpty(str)) {
            str = (String) this.speakVowelTranslationMap.get(brailleCharacter.toString());
        }
        if (TextUtils.isEmpty(str)) {
            str = (String) this.speakFinalConsonantsTranslationMap.get(brailleCharacter.toString());
        }
        if (TextUtils.isEmpty(str)) {
            return BrailleTranslateUtils.getDotsText(this.context.getResources(), brailleCharacter);
        }
        return str;
    }

    private final String getSyllable(BrailleCharacter brailleCharacter) {
        String str = (String) this.initialConsonantsTranslationMap.get(brailleCharacter.toString());
        if (TextUtils.isEmpty(str)) {
            str = (String) this.vowelTranslationMap.get(brailleCharacter.toString());
        }
        if (TextUtils.isEmpty(str)) {
            str = (String) this.finalConsonantsTranslationMap.get(brailleCharacter.toString());
        }
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str;
    }

    private static final boolean isAllHangul$ar$ds(String str) {
        Character.UnicodeScript of;
        Character.UnicodeScript unicodeScript;
        for (int i = 0; i < str.length(); i++) {
            of = Character.UnicodeScript.of(str.charAt(i));
            unicodeScript = Character.UnicodeScript.HANGUL;
            if (of != unicodeScript) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBufferUnContracted, com.google.android.accessibility.braille.common.translate.EditBuffer
    public final String appendBraille$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper, BrailleCharacter brailleCharacter) {
        String translateToPrint = this.translator.translateToPrint(this.holdings);
        this.holdings.append(brailleCharacter);
        String translateToPrint2 = this.translator.translateToPrint(this.holdings);
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging;
        String speakableAnnouncement = getSpeakableAnnouncement(brailleCharacter);
        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent, speakableAnnouncement, 2);
        String appendResult = getAppendResult(translateToPrint, translateToPrint2, brailleCharacter);
        if (!TextUtils.isEmpty(appendResult)) {
            phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setComposingText(appendResult, appendResult.length());
        }
        this.lastCommitIndexOfHoldings = this.holdings.size();
        return speakableAnnouncement;
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v4, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v5, types: [android.view.inputmethod.InputConnection, java.lang.Object] */
    @Override // com.google.android.accessibility.braille.common.translate.EditBufferUnContracted, com.google.android.accessibility.braille.common.translate.EditBuffer
    public final void deleteCharacterBackward$ar$class_merging$ar$class_merging(PhenotypeProcessReaper phenotypeProcessReaper) {
        if (this.holdings.isEmpty()) {
            AppCompatDelegate.Api33Impl.performKeyAction$ar$ds(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
            return;
        }
        BrailleWord brailleWord = this.holdings;
        int size = brailleWord.size() - 1;
        BrailleWord brailleWord2 = this.holdings;
        BrailleCharacter brailleCharacter = brailleWord.get(size);
        brailleWord2.remove(brailleWord2.size() - 1);
        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(this.talkBack$ar$class_merging$ar$class_merging$ar$class_merging, getSpeakableAnnouncement(brailleCharacter), 2);
        if (!this.holdings.isEmpty()) {
            String appendResult = getAppendResult(this.translator.translateToPrint(this.holdings.subword(0, r1.size() - 1)), this.translator.translateToPrint(this.holdings), this.holdings.get(r3.size() - 1));
            if (appendResult != null) {
                phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable.setComposingText(appendResult, appendResult.length());
            }
        } else {
            AppCompatDelegate.Api33Impl.performKeyAction$ar$ds(phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
        }
        this.lastCommitIndexOfHoldings = this.holdings.size();
    }
}
