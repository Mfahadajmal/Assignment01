package com.google.android.accessibility.talkback;

import java.text.BreakIterator;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
class GranularityIterator$CharacterTextSegmentIterator extends GranularityIterator$AbstractTextSegmentIterator {
    public static GranularityIterator$CharacterTextSegmentIterator instance;
    final BreakIterator breakIterator;
    public Locale iteratorLocale;

    public GranularityIterator$CharacterTextSegmentIterator(Locale locale, BreakIterator breakIterator) {
        this.iteratorLocale = locale;
        this.breakIterator = breakIterator;
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public int[] following(int i) {
        int length = this.iteratorText.length();
        if (length > 0 && i < length) {
            if (i < 0) {
                i = 0;
            }
            while (!this.breakIterator.isBoundary(i)) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void initialize(String str) {
        this.iteratorText = str;
        this.breakIterator.setText(str);
    }

    @Override // com.google.android.accessibility.talkback.GranularityIterator$TextSegmentIterator
    public int[] preceding(int i) {
        int length = this.iteratorText.length();
        if (length > 0 && i > 0) {
            if (i > length) {
                i = length;
            }
            while (!this.breakIterator.isBoundary(i)) {
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
