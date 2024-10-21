package com.google.android.accessibility.selecttospeak.iterator;

import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Sentence {
    public final TextLocationFinder locationFinder;
    public final int offsetFromParagraph;
    public final Paragraph paragraph;
    public final CharSequence text;

    public Sentence(TextLocationFinder textLocationFinder, int i, CharSequence charSequence, Paragraph paragraph) {
        textLocationFinder.getClass();
        charSequence.getClass();
        this.locationFinder = textLocationFinder;
        this.offsetFromParagraph = i;
        this.text = charSequence;
        this.paragraph = paragraph;
    }

    public static /* synthetic */ List bounds$default$ar$ds(Sentence sentence) {
        return sentence.bounds(false, 0, sentence.text.length());
    }

    public final List bounds(boolean z, int i, int i2) {
        return this.locationFinder.getTextLocation(z, this.offsetFromParagraph, i, i2);
    }

    public final boolean getSupportsTextLocation() {
        return this.locationFinder.getSupportsTextLocation();
    }

    public final String toString() {
        return OnDeviceStainRemovalLogEvent.trimIndent("\n      offsetFromParagraph: " + this.offsetFromParagraph + "\n      bound:             : " + bounds$default$ar$ds(this) + "\n      text               : " + ((Object) this.text) + "\n    ");
    }
}
