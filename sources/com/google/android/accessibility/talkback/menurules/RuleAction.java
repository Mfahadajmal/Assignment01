package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextDirectionHeuristics;
import android.text.style.SuggestionSpan;
import android.text.style.TtsSpan;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleAction extends NodeMenuRule {
    private final CustomActionMenu customActionMenu;
    private final EditingAndSelectingMenu editingAndSelectingMenu;
    private final TypoSuggestionMenu typoSuggestionMenu;

    public RuleAction(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, AccessibilityFocusMonitor accessibilityFocusMonitor, TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_custom_action_setting_key, R.bool.pref_show_context_menu_custom_action_default);
        this.customActionMenu = new CustomActionMenu(feedbackReturner, talkBackAnalytics);
        this.editingAndSelectingMenu = new EditingAndSelectingMenu(feedbackReturner, actorState, accessibilityFocusMonitor, talkBackAnalytics);
        this.typoSuggestionMenu = new TypoSuggestionMenu(feedbackReturner, accessibilityFocusMonitor);
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!CustomActionMenu.acceptCustomActionMenu(accessibilityNodeInfoCompat) && !this.editingAndSelectingMenu.accept(context, accessibilityNodeInfoCompat) && !this.typoSuggestionMenu.accept(context, accessibilityNodeInfoCompat)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        int i;
        CharSequence unicodeWrap;
        ArrayList arrayList = new ArrayList();
        if (this.typoSuggestionMenu.accept(context, accessibilityNodeInfoCompat)) {
            TypoSuggestionMenu typoSuggestionMenu = this.typoSuggestionMenu;
            ArrayList arrayList2 = new ArrayList();
            AccessibilityNodeInfoCompat editingNode = typoSuggestionMenu.getEditingNode(accessibilityNodeInfoCompat);
            AccessibilityNodeInfoUtils.SpellingSuggestion spellingSuggestion = null;
            char c = 0;
            if (editingNode != null) {
                if (!editingNode.refresh()) {
                    LogUtils.v("RuleTypoSuggestions", "Fail to refresh node.", new Object[0]);
                } else {
                    ImmutableList spellingSuggestions = AccessibilityNodeInfoUtils.getSpellingSuggestions(context, editingNode);
                    if (!spellingSuggestions.isEmpty()) {
                        spellingSuggestion = (AccessibilityNodeInfoUtils.SpellingSuggestion) spellingSuggestions.get(0);
                    }
                }
            }
            if (spellingSuggestion != null) {
                SuggestionSpan suggestionSpan = spellingSuggestion.suggestionSpan();
                int flags = suggestionSpan.getFlags() & 8;
                String[] suggestions = suggestionSpan.getSuggestions();
                int i2 = 0;
                for (int length = suggestions.length; i2 < length; length = length) {
                    String str = suggestions[i2];
                    if (flags == 0) {
                        i = R.string.title_edittext_typo_suggestion;
                    } else {
                        i = R.string.title_edittext_grammar_suggestion;
                    }
                    String[] strArr = suggestions;
                    Object[] objArr = new Object[1];
                    objArr[c] = str;
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(context.getString(i, objArr));
                    spannableStringBuilder.append(" ", new TtsSpan.VerbatimBuilder(str).build(), 17);
                    int i3 = i2 + 1;
                    Object valueOf = Integer.valueOf(i3);
                    Object valueOf2 = Integer.valueOf(length);
                    Object[] objArr2 = new Object[2];
                    objArr2[c] = valueOf;
                    objArr2[1] = valueOf2;
                    spannableStringBuilder.append(" ", new TtsSpan.TextBuilder(context.getString(R.string.title_edittext_typo_suggestion_number, objArr2)).build(), 17);
                    unicodeWrap = BidiFormatter.getInstance().unicodeWrap(spannableStringBuilder, TextDirectionHeuristics.LOCALE);
                    ContextMenuItem createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.typo_suggestions_menu, 0, unicodeWrap);
                    createMenuItem.listener = new TypoSuggestionMenu$$ExternalSyntheticLambda0(typoSuggestionMenu, spellingSuggestion, str, editingNode, 0);
                    createMenuItem.setSkipRefocusEvents$ar$ds();
                    createMenuItem.setSkipWindowEvents$ar$ds();
                    createMenuItem.deferredType$ar$edu = 3;
                    arrayList2.add(createMenuItem);
                    i2 = i3;
                    suggestions = strArr;
                    c = 0;
                }
            }
            arrayList.addAll(arrayList2);
        }
        if (CustomActionMenu.acceptCustomActionMenu(accessibilityNodeInfoCompat)) {
            CustomActionMenu customActionMenu = this.customActionMenu;
            ArrayList arrayList3 = new ArrayList();
            HashSet hashSet = new HashSet();
            if (CustomActionMenu.acceptCustomActionMenu(accessibilityNodeInfoCompat)) {
                customActionMenu.populateCustomMenuItemsForNode(context, accessibilityNodeInfoCompat, arrayList3, z, hashSet);
            }
            arrayList.addAll(arrayList3);
        }
        if (this.editingAndSelectingMenu.accept(context, accessibilityNodeInfoCompat)) {
            EditingAndSelectingMenu editingAndSelectingMenu = this.editingAndSelectingMenu;
            ArrayList arrayList4 = new ArrayList();
            AccessibilityNodeInfoCompat editingNodeFromFocusedKeyboard = editingAndSelectingMenu.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat);
            if (EditingAndSelectingMenu.acceptEditingAndSelectingMenu(accessibilityNodeInfoCompat, editingNodeFromFocusedKeyboard)) {
                if (editingNodeFromFocusedKeyboard != null) {
                    editingAndSelectingMenu.populateEditingAndSelectingMenuItemsForNode(context, editingNodeFromFocusedKeyboard, arrayList4);
                } else {
                    editingAndSelectingMenu.populateEditingAndSelectingMenuItemsForNode(context, accessibilityNodeInfoCompat, arrayList4);
                }
            }
            arrayList.addAll(arrayList4);
        }
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.title_custom_action);
    }
}
