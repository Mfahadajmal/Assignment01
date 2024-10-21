package com.google.android.accessibility.braille.translate;

import android.content.Context;
import com.google.android.accessibility.braille.common.EmojiUtils;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.braille.interfaces.BrailleWord;
import com.google.android.apps.common.inject.ApplicationModule;
import googledata.experiments.mobile.accessibility_suite.features.BrailleCommonConfig;
import java.util.regex.Matcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleBrailleTranslator implements BrailleTranslator {
    private final ApplicationModule customizer$ar$class_merging$ar$class_merging$ar$class_merging;
    private final BrailleTranslator translator;

    public GoogleBrailleTranslator(BrailleTranslator brailleTranslator, ApplicationModule applicationModule) {
        this.translator = brailleTranslator;
        this.customizer$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final TranslationResult translate(CharSequence charSequence, int i) {
        TranslationResult translate = this.translator.translate(charSequence, i);
        if (translate == null) {
            return null;
        }
        int i2 = 0;
        while (i2 < translate.text().length()) {
            int i3 = i2 + 1;
            if (translate.text().charAt(i2) == '\n') {
                BrailleWord brailleWord = new BrailleWord();
                brailleWord.append(BrailleCharacter.EMPTY_CELL);
                brailleWord.append(BrailleWord.NEW_LINE);
                translate = TranslationResult.correctTranslation(translate, brailleWord, i2, i3);
            }
            i2 = i3;
        }
        if (BrailleCommonConfig.INSTANCE.get().replaceEmoji((Context) this.customizer$ar$class_merging$ar$class_merging$ar$class_merging.ApplicationModule$ar$application)) {
            Matcher matcher = EmojiUtils.EMOJI_REGEX_PATTERN.matcher(translate.text());
            while (matcher.find()) {
                String name = Character.getName(matcher.group().codePointAt(0));
                BrailleWord brailleWord2 = new BrailleWord();
                brailleWord2.append(BrailleCharacter.EMPTY_CELL);
                brailleWord2.append(translate(name, -1).cells());
                translate = TranslationResult.correctTranslation(translate, brailleWord2, matcher.start(), matcher.end());
            }
            return translate;
        }
        return translate;
    }

    @Override // com.google.android.accessibility.braille.translate.BrailleTranslator
    public final String translateToPrint(BrailleWord brailleWord) {
        return this.translator.translateToPrint(brailleWord);
    }
}
