package com.google.android.accessibility.utils;

import android.content.Context;
import android.os.Looper;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.SuggestionSpan;
import android.view.textservice.SentenceSuggestionsInfo;
import android.view.textservice.SpellCheckerSession;
import android.view.textservice.SuggestionsInfo;
import android.view.textservice.TextServicesManager;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.util.Arrays;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpellChecker {
    public static boolean enabled = true;
    private static final OnDeviceTextDetectionLoadLogEvent resultCache$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
    public final SpellCheckingListener listener;
    public final CharSequence text;
    public final TextServicesManager textServicesManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpellingSuggestion {
        private final int end;
        private final CharSequence misspelledWord;
        private final int start;
        private final SuggestionSpan suggestionSpan;

        public SpellingSuggestion() {
        }

        public final boolean contain(SpellingSuggestion spellingSuggestion) {
            if (start() < spellingSuggestion.start() && end() > spellingSuggestion.end()) {
                return true;
            }
            if (start() == spellingSuggestion.start() && end() > spellingSuggestion.end()) {
                return true;
            }
            if (start() < spellingSuggestion.start() && end() == spellingSuggestion.end()) {
                return true;
            }
            return false;
        }

        public final int end() {
            return this.end;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof SpellingSuggestion) {
                SpellingSuggestion spellingSuggestion = (SpellingSuggestion) obj;
                if (this.start == spellingSuggestion.start() && this.end == spellingSuggestion.end() && this.misspelledWord.equals(spellingSuggestion.misspelledWord()) && this.suggestionSpan.equals(spellingSuggestion.suggestionSpan())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((((((this.start ^ 1000003) * 1000003) ^ this.end) * 1000003) ^ this.misspelledWord.hashCode()) * 1000003) ^ this.suggestionSpan.hashCode();
        }

        public final CharSequence misspelledWord() {
            return this.misspelledWord;
        }

        public final int start() {
            return this.start;
        }

        public final SuggestionSpan suggestionSpan() {
            return this.suggestionSpan;
        }

        public final String toString() {
            Locale locale = Locale.getDefault();
            Integer valueOf = Integer.valueOf(start());
            Integer valueOf2 = Integer.valueOf(end());
            CharSequence misspelledWord = misspelledWord();
            int flags = suggestionSpan().getFlags();
            StringBuilder sb = new StringBuilder();
            if ((flags & 2) != 0) {
                sb.append("FLAG_MISSPELLED/");
            }
            if ((flags & 4) != 0) {
                sb.append("FLAG_AUTO_CORRECTION/");
            }
            if ((flags & 8) != 0) {
                sb.append("FLAG_GRAMMAR_ERROR/");
            }
            return String.format(locale, "[%d-%d][%s][flag=%s][suggestion=%s]", valueOf, valueOf2, misspelledWord, sb.toString(), Arrays.toString(suggestionSpan().getSuggestions()));
        }

        public SpellingSuggestion(int i, int i2, CharSequence charSequence, SuggestionSpan suggestionSpan) {
            this();
            this.start = i;
            this.end = i2;
            if (charSequence == null) {
                throw new NullPointerException("Null misspelledWord");
            }
            this.misspelledWord = charSequence;
            if (suggestionSpan != null) {
                this.suggestionSpan = suggestionSpan;
                return;
            }
            throw new NullPointerException("Null suggestionSpan");
        }
    }

    public SpellChecker(Context context, CharSequence charSequence, SpellCheckingListener spellCheckingListener) {
        this.text = charSequence instanceof Spannable ? charSequence : new SpannableString(charSequence);
        this.listener = spellCheckingListener;
        this.textServicesManager = (TextServicesManager) context.getSystemService("textservices");
    }

    public static CharSequence getTextWithSuggestionSpans(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getTextWithSuggestionSpans(context, accessibilityNodeInfoCompat.getText());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x011b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00f1  */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.CharSequence, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.CharSequence getTextWithSuggestionSpans(android.content.Context r18, java.lang.CharSequence r19) {
        /*
            Method dump skipped, instructions count: 533
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.SpellChecker.getTextWithSuggestionSpans(android.content.Context, java.lang.CharSequence):java.lang.CharSequence");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpellCheckingListener implements SpellCheckerSession.SpellCheckerSessionListener {
        public boolean isFinished;
        public ImmutableList results;

        public SpellCheckingListener() {
            int i = ImmutableList.ImmutableList$ar$NoOp;
            this.results = RegularImmutableList.EMPTY;
        }

        public final void onFinished(ImmutableList immutableList) {
            this.results = immutableList;
            this.isFinished = true;
            Looper myLooper = Looper.myLooper();
            if (myLooper != null && myLooper != Looper.getMainLooper()) {
                myLooper.quitSafely();
            }
        }

        @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
        public final void onGetSentenceSuggestions(SentenceSuggestionsInfo[] sentenceSuggestionsInfoArr) {
            LogUtils.v("SpellChecker", "onGetSentenceSuggestions", new Object[0]);
            if (sentenceSuggestionsInfoArr == null) {
                int i = ImmutableList.ImmutableList$ar$NoOp;
                onFinished(RegularImmutableList.EMPTY);
                return;
            }
            if (LogUtils.shouldLog$ar$ds()) {
                for (SentenceSuggestionsInfo sentenceSuggestionsInfo : sentenceSuggestionsInfoArr) {
                    if (sentenceSuggestionsInfo != null) {
                        for (int i2 = 0; i2 < sentenceSuggestionsInfo.getSuggestionsCount(); i2++) {
                            SuggestionsInfo suggestionsInfoAt = sentenceSuggestionsInfo.getSuggestionsInfoAt(i2);
                            if ((suggestionsInfoAt.getSuggestionsAttributes() & 1) == 0) {
                                StringBuilder sb = new StringBuilder("suggestions=");
                                for (int i3 = 0; i3 < suggestionsInfoAt.getSuggestionsCount(); i3++) {
                                    sb.append(suggestionsInfoAt.getSuggestionAt(i3));
                                    sb.append("/");
                                }
                                Integer valueOf = Integer.valueOf(i2);
                                Integer valueOf2 = Integer.valueOf(sentenceSuggestionsInfo.getOffsetAt(i2));
                                Integer valueOf3 = Integer.valueOf(sentenceSuggestionsInfo.getLengthAt(i2));
                                int suggestionsAttributes = suggestionsInfoAt.getSuggestionsAttributes();
                                StringBuilder sb2 = new StringBuilder();
                                if ((suggestionsAttributes & 2) != 0) {
                                    sb2.append("RESULT_ATTR_LOOKS_LIKE_TYPO/");
                                }
                                if ((suggestionsAttributes & 4) != 0) {
                                    sb2.append("RESULT_ATTR_HAS_RECOMMENDED_SUGGESTIONS/");
                                }
                                if ((suggestionsAttributes & 8) != 0) {
                                    sb2.append("RESULT_ATTR_LOOKS_LIKE_GRAMMAR_ERROR/");
                                }
                                LogUtils.v("SpellChecker", "suggestionInfo[%d] start=%d length=%d flag=%s suggestions=%s", valueOf, valueOf2, valueOf3, sb2.toString(), sb);
                            }
                        }
                    }
                }
            }
            onFinished(ImmutableList.copyOf(sentenceSuggestionsInfoArr));
        }

        @Override // android.view.textservice.SpellCheckerSession.SpellCheckerSessionListener
        public final void onGetSuggestions(SuggestionsInfo[] suggestionsInfoArr) {
        }
    }
}
