package com.google.android.accessibility.braille.translate;

import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface TranslatorFactory {
    BrailleTranslator create(Context context, String str, boolean z);

    String getLibraryName();
}
