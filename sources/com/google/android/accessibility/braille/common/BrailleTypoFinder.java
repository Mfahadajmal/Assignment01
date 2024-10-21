package com.google.android.accessibility.braille.common;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleTypoFinder {
    public final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public int index;
    public AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion;
    public String[] suggestionCandidates;
    public int suggestionSpanFlag;
    public AccessibilityNodeInfoCompat targetNode;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class NoTypoFocusFoundException extends Exception {
        public NoTypoFocusFoundException() {
            super("You have to call updateTypoCorrectionFrom(int) in advance!");
        }
    }

    public BrailleTypoFinder(AppLifecycleMonitor appLifecycleMonitor) {
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
    }

    public final void clear() {
        this.index = -1;
        this.targetNode = null;
        this.spellingSuggestion = null;
        this.suggestionCandidates = null;
    }

    public final ImmutableList getSuggestionCandidates() {
        String[] strArr = this.suggestionCandidates;
        if (strArr != null) {
            return ImmutableList.copyOf((Collection) Arrays.asList(strArr));
        }
        throw new NoTypoFocusFoundException();
    }
}
