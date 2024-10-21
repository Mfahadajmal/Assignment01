package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleUnlabeledNode extends NodeMenuRule {
    private final ActorState actorState;
    public final TalkBackAnalytics analytics;
    public final Pipeline.FeedbackReturner pipeline;

    public RuleUnlabeledNode(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_labeling_setting_key, R.bool.pref_show_context_menu_labeling_default);
        this.pipeline = feedbackReturner;
        this.analytics = talkBackAnalytics;
        this.actorState = actorState;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return this.actorState.getLabelManagerState().supportsLabel(accessibilityNodeInfoCompat);
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(final Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        final ContextMenuItem createMenuItem;
        final long labelIdForNode = this.actorState.getLabelManagerState().getLabelIdForNode(accessibilityNodeInfoCompat);
        ArrayList arrayList = new ArrayList();
        String viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName();
        if (labelIdForNode == -1) {
            createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.labeling_breakout_add_label, 5, context.getString(R.string.label_dialog_title_add));
            createMenuItem.listener = new TypoSuggestionMenu$$ExternalSyntheticLambda0(this, context, viewIdResourceName, createMenuItem, 1);
        } else {
            createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.labeling_breakout_edit_label, 5, context.getString(R.string.label_dialog_title_edit));
            createMenuItem.listener = new MenuItem.OnMenuItemClickListener() { // from class: com.google.android.accessibility.talkback.menurules.RuleUnlabeledNode$$ExternalSyntheticLambda1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public final boolean onMenuItemClick(MenuItem menuItem) {
                    Context context2 = context;
                    long j = labelIdForNode;
                    RuleUnlabeledNode ruleUnlabeledNode = RuleUnlabeledNode.this;
                    WindowTrackerFactory.editLabel$ar$class_merging$ar$class_merging(context2, j, true, ruleUnlabeledNode.pipeline, null);
                    ruleUnlabeledNode.analytics.onLocalContextMenuAction(4, createMenuItem.itemId);
                    return true;
                }
            };
        }
        arrayList.add(createMenuItem);
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.title_labeling_controls);
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final boolean isSubMenu() {
        return false;
    }
}
