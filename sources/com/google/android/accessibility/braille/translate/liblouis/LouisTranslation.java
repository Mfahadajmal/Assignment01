package com.google.android.accessibility.braille.translate.liblouis;

import com.google.android.accessibility.braille.translate.TranslationResult;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LouisTranslation {
    static {
        System.loadLibrary("louiswrap");
        classInitNative();
    }

    private LouisTranslation() {
    }

    public static native String backTranslateNative(byte[] bArr, String str, int i);

    private static native void classInitNative();

    public static native boolean setTablesDirNative(String str);

    public static native TranslationResult translateNative(CharSequence charSequence, String str, int i);
}
