package com.google.android.accessibility.talkback;

import java.text.BreakIterator;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
final class GranularityIterator$WordTextSegmentIterator extends GranularityIterator$CharacterTextSegmentIterator {
    public static GranularityIterator$WordTextSegmentIterator instance;

    public GranularityIterator$WordTextSegmentIterator(Locale locale) {
        super(locale, BreakIterator.getWordInstance(locale));
    }

    private final boolean isLetterOrDigit(int i) {
        if (i >= 0 && i < this.iteratorText.length()) {
            return Character.isLetterOrDigit(this.iteratorText.codePointAt(i));
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$CharacterTextSegmentIterator, com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public final int[] following(int i) {
        if (this.iteratorText.length() > 0 && i < this.iteratorText.length()) {
            if (i < 0) {
                i = 0;
            }
            while (!isLetterOrDigit(i)) {
                i = this.breakIterator.following(i);
                if (i == -1) {
                    return null;
                }
            }
            int following = this.breakIterator.following(i);
            if (following != -1) {
                return getRange(i, following);
            }
            return null;
        }
        return null;
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$CharacterTextSegmentIterator, com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public final int[] preceding(int i) {
        int length = this.iteratorText.length();
        if (length > 0 && i > 0) {
            if (i > length) {
                i = length;
            }
            while (i > 0 && !isLetterOrDigit(i - 1)) {
                i = this.breakIterator.preceding(i);
                if (i == -1) {
                    return null;
                }
            }
            int preceding = this.breakIterator.preceding(i);
            if (preceding != -1) {
                return getRange(preceding, i);
            }
            return null;
        }
        return null;
    }
}
