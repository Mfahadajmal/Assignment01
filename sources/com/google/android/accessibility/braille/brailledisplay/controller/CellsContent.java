package com.google.android.accessibility.braille.brailledisplay.controller;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CellsContent {
    public int panStrategy;
    public final CharSequence text;

    public CellsContent(CharSequence charSequence) {
        this.text = charSequence;
    }

    public final String toString() {
        return String.format("BrailleContent {text=%s}", this.text);
    }
}
