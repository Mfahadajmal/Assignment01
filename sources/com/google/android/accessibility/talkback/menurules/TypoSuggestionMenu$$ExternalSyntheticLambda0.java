package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TypoSuggestionMenu$$ExternalSyntheticLambda0 implements MenuItem.OnMenuItemClickListener {
    public final /* synthetic */ Object TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ Object TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$1;
    public final /* synthetic */ Object TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$3;
    public final /* synthetic */ String f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TypoSuggestionMenu$$ExternalSyntheticLambda0(RuleUnlabeledNode ruleUnlabeledNode, Context context, String str, ContextMenuItem contextMenuItem, int i) {
        this.switching_field = i;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$1 = ruleUnlabeledNode;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$3 = context;
        this.f$2 = str;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$0 = contextMenuItem;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        if (this.switching_field != 0) {
            RuleUnlabeledNode ruleUnlabeledNode = (RuleUnlabeledNode) this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$1;
            WindowTrackerFactory.addLabel$ar$ds((Context) this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$3, this.f$2, ruleUnlabeledNode.pipeline);
            ruleUnlabeledNode.analytics.onLocalContextMenuAction(4, ((ContextMenuItem) this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$0).itemId);
            return true;
        }
        Object obj = this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$1;
        Locale locale = Locale.ENGLISH;
        AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion = (AccessibilityNodeInfoUtils.SpellingSuggestion) obj;
        Integer valueOf = Integer.valueOf(spellingSuggestion.start());
        Integer valueOf2 = Integer.valueOf(spellingSuggestion.end());
        CharSequence misspelledWord = spellingSuggestion.misspelledWord();
        String str = this.f$2;
        LogUtils.v("RuleTypoSuggestions", String.format(locale, "[%d-%d]%s replaced with %s", valueOf, valueOf2, misspelledWord, str), new Object[0]);
        Logger logger = Performance.DEFAULT_LOGGER;
        Object obj2 = this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$3;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.EditText.Builder edit$ar$edu = Feedback.edit$ar$edu((AccessibilityNodeInfoCompat) obj2, 11);
        edit$ar$edu.text = str;
        edit$ar$edu.spellingSuggestion = spellingSuggestion;
        builder.edit = edit$ar$edu.build();
        return ((TypoSuggestionMenu) this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$0).pipeline.returnFeedback(Feedback.create((Performance.EventId) null, builder.build()));
    }

    public /* synthetic */ TypoSuggestionMenu$$ExternalSyntheticLambda0(TypoSuggestionMenu typoSuggestionMenu, AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion, String str, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        this.switching_field = i;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$0 = typoSuggestionMenu;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$1 = spellingSuggestion;
        this.f$2 = str;
        this.TypoSuggestionMenu$$ExternalSyntheticLambda0$ar$f$3 = accessibilityNodeInfoCompat;
    }
}
