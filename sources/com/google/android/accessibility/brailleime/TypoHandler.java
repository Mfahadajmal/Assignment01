package com.google.android.accessibility.brailleime;

import android.content.Context;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.TtsSpan;
import android.view.inputmethod.InputConnection;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.common.BrailleTypoFinder;
import com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TypoHandler {
    private final Context context;
    public InputConnection inputConnection;
    public int lastCorrectedSuggestionTailIndex;
    public CharSequence lastCorrectedTypo;
    public int lastCorrectedTypoHeadIndex;
    public final TalkBackForBrailleIme talkBackForBrailleIme;
    private final OnDeviceTextDetectionLoadLogEvent talkBackSpeaker$ar$class_merging$ar$class_merging$ar$class_merging;
    public final BrailleTypoFinder typoFinder;

    public TypoHandler(Context context, AppLifecycleMonitor appLifecycleMonitor, TalkBackForBrailleIme talkBackForBrailleIme, OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        this.context = context;
        this.typoFinder = new BrailleTypoFinder(appLifecycleMonitor);
        this.talkBackForBrailleIme = talkBackForBrailleIme;
        this.talkBackSpeaker$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
        clearCorrectionCache();
    }

    private static final Spanned getSuggestionSpannableString$ar$ds(String str, String str2, String str3) {
        return new SpannableStringBuilder().append((CharSequence) str).append((CharSequence) ". ").append((CharSequence) str2).append((CharSequence) ". ").append(" ", new TtsSpan.VerbatimBuilder(str).build(), 0).append((CharSequence) ". ").append((CharSequence) str3);
    }

    private final String getSuggestionTypeString() {
        int i;
        if (true != isGrammarMistake()) {
            i = R.string.spelling_suggestion;
        } else {
            i = R.string.grammar_suggestion;
        }
        return this.context.getString(i);
    }

    public final void clearCorrectionCache() {
        this.lastCorrectedTypoHeadIndex = -1;
        this.lastCorrectedTypo = null;
        this.lastCorrectedSuggestionTailIndex = -1;
    }

    public final boolean isGrammarMistake() {
        if ((this.typoFinder.suggestionSpanFlag & 8) != 0) {
            return true;
        }
        return false;
    }

    public final boolean nextSuggestion() {
        int i;
        try {
            BrailleTypoFinder brailleTypoFinder = this.typoFinder;
            String[] strArr = brailleTypoFinder.suggestionCandidates;
            if (strArr != null) {
                int i2 = brailleTypoFinder.index;
                if (i2 == -1) {
                    brailleTypoFinder.index = 0;
                } else {
                    i = i2 + 1;
                    brailleTypoFinder.index = i;
                    if (i >= strArr.length) {
                        brailleTypoFinder.index = 0;
                    }
                    String str = strArr[i];
                    ImmutableList suggestionCandidates = brailleTypoFinder.getSuggestionCandidates();
                    this.talkBackSpeaker$ar$class_merging$ar$class_merging$ar$class_merging.speak(getSuggestionSpannableString$ar$ds(str, getSuggestionTypeString(), this.context.getString(R.string.index_of_spelling_suggestion, Integer.valueOf(suggestionCandidates.indexOf(str) + 1), Integer.valueOf(suggestionCandidates.size()))), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
                    return true;
                }
                i = 0;
                String str2 = strArr[i];
                ImmutableList suggestionCandidates2 = brailleTypoFinder.getSuggestionCandidates();
                this.talkBackSpeaker$ar$class_merging$ar$class_merging$ar$class_merging.speak(getSuggestionSpannableString$ar$ds(str2, getSuggestionTypeString(), this.context.getString(R.string.index_of_spelling_suggestion, Integer.valueOf(suggestionCandidates2.indexOf(str2) + 1), Integer.valueOf(suggestionCandidates2.size()))), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
                return true;
            }
            throw new BrailleTypoFinder.NoTypoFocusFoundException();
        } catch (BrailleTypoFinder.NoTypoFocusFoundException unused) {
            if (!updateTypoTarget()) {
                return false;
            }
            return nextSuggestion();
        }
    }

    public final boolean previousSuggestion() {
        int i;
        try {
            BrailleTypoFinder brailleTypoFinder = this.typoFinder;
            String[] strArr = brailleTypoFinder.suggestionCandidates;
            if (strArr != null) {
                int i2 = brailleTypoFinder.index;
                if (i2 == -1) {
                    brailleTypoFinder.index = 0;
                    i = 0;
                } else {
                    i = i2 - 1;
                    brailleTypoFinder.index = i;
                    if (i < 0) {
                        i = strArr.length - 1;
                        brailleTypoFinder.index = i;
                    }
                }
                String str = strArr[i];
                ImmutableList suggestionCandidates = brailleTypoFinder.getSuggestionCandidates();
                this.talkBackSpeaker$ar$class_merging$ar$class_merging$ar$class_merging.speak(getSuggestionSpannableString$ar$ds(str, getSuggestionTypeString(), this.context.getString(R.string.index_of_spelling_suggestion, Integer.valueOf(suggestionCandidates.indexOf(str) + 1), Integer.valueOf(suggestionCandidates.size()))), 0, AppCompatDelegateImpl.Api24Impl.$default$buildSpeakOptions$ar$edu$ar$class_merging$ar$ds(3, null));
                return true;
            }
            throw new BrailleTypoFinder.NoTypoFocusFoundException();
        } catch (BrailleTypoFinder.NoTypoFocusFoundException unused) {
            if (!updateTypoTarget()) {
                return false;
            }
            return previousSuggestion();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean updateTypoTarget() {
        clearCorrectionCache();
        BrailleTypoFinder brailleTypoFinder = this.typoFinder;
        brailleTypoFinder.clear();
        AccessibilityNodeInfoCompat findFocusCompat = brailleTypoFinder.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(1);
        if (findFocusCompat != null) {
            ImmutableList spellingSuggestions = AccessibilityNodeInfoUtils.getSpellingSuggestions(this.context, findFocusCompat);
            if (!spellingSuggestions.isEmpty()) {
                AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion = (AccessibilityNodeInfoUtils.SpellingSuggestion) spellingSuggestions.get(0);
                brailleTypoFinder.targetNode = findFocusCompat;
                brailleTypoFinder.spellingSuggestion = spellingSuggestion;
                brailleTypoFinder.suggestionCandidates = spellingSuggestion.suggestionSpan().getSuggestions();
                brailleTypoFinder.suggestionSpanFlag = spellingSuggestion.suggestionSpan().getFlags();
                if (brailleTypoFinder.suggestionCandidates.length != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
