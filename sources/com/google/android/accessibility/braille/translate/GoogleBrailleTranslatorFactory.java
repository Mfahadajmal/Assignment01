package com.google.android.accessibility.braille.translate;

import android.content.Context;
import com.google.android.apps.common.inject.ApplicationModule;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleBrailleTranslatorFactory implements TranslatorFactory {
    private final ApplicationModule customizer$ar$class_merging$ar$class_merging$ar$class_merging;
    private final TranslatorFactory translatorFactory;

    public GoogleBrailleTranslatorFactory(TranslatorFactory translatorFactory, ApplicationModule applicationModule) {
        this.translatorFactory = translatorFactory;
        this.customizer$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
    }

    @Override // com.google.android.accessibility.braille.translate.TranslatorFactory
    public final BrailleTranslator create(Context context, String str, boolean z) {
        return new GoogleBrailleTranslator(this.translatorFactory.create(context, str, z), this.customizer$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    @Override // com.google.android.accessibility.braille.translate.TranslatorFactory
    public final String getLibraryName() {
        return this.translatorFactory.getLibraryName();
    }
}
