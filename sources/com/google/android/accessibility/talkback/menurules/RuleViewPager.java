package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.AbstractOnContextMenuItemClickListener;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleViewPager extends NodeMenuRule {
    private static final Filter FILTER_PAGED = new Filter() { // from class: com.google.android.accessibility.talkback.menurules.RuleViewPager.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) == 16) {
                return true;
            }
            return false;
        }
    };
    private final TalkBackAnalytics analytics;
    private final Pipeline.FeedbackReturner pipeline;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ViewPagerItemClickListener extends AbstractOnContextMenuItemClickListener {
        public ViewPagerItemClickListener(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
            super(accessibilityNodeInfoCompat, feedbackReturner, talkBackAnalytics);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            if (menuItem == null) {
                return true;
            }
            int itemId = menuItem.getItemId();
            this.analytics.onLocalContextMenuAction(3, itemId);
            Logger logger = Performance.DEFAULT_LOGGER;
            if (itemId == R.id.viewpager_breakout_prev_page) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, 8192));
            } else if (itemId == R.id.viewpager_breakout_next_page) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, 4096));
            } else if (itemId == R.id.viewpager_breakout_page_up) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_UP.getId()));
            } else if (itemId == R.id.viewpager_breakout_page_down) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_DOWN.getId()));
            } else if (itemId == R.id.viewpager_breakout_page_left) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_LEFT.getId()));
            } else if (itemId == R.id.viewpager_breakout_page_right) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_RIGHT.getId()));
            } else {
                return false;
            }
            return true;
        }
    }

    public RuleViewPager(Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_page_navigation_setting_key, R.bool.pref_show_context_menu_page_navigation_default);
        this.pipeline = feedbackReturner;
        this.analytics = talkBackAnalytics;
    }

    private static final void addMenuItemIfActionExists$ar$ds(List list, Context context, int i, int i2, int i3, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (AccessibilityNodeInfoUtils.supportsAction(accessibilityNodeInfoCompat, i3)) {
            list.add(ContextMenu.createMenuItem(context, 0, i, 0, context.getString(i2)));
        }
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat root = AccessibilityNodeInfoUtils.getRoot(accessibilityNodeInfoCompat);
        if (root == null || AccessibilityNodeInfoUtils.searchFromBfs(root, FILTER_PAGED) == null) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        ArrayList arrayList = new ArrayList();
        AccessibilityNodeInfoCompat selfOrMatchingAncestor = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, FILTER_PAGED);
        if (selfOrMatchingAncestor != null && (z || selfOrMatchingAncestor.equals(accessibilityNodeInfoCompat))) {
            addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_page_up, R.string.title_viewpager_breakout_page_up, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_UP.getId(), selfOrMatchingAncestor);
            addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_page_down, R.string.title_viewpager_breakout_page_down, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_DOWN.getId(), selfOrMatchingAncestor);
            addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_page_left, R.string.title_viewpager_breakout_page_left, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_LEFT.getId(), selfOrMatchingAncestor);
            addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_page_right, R.string.title_viewpager_breakout_page_right, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_RIGHT.getId(), selfOrMatchingAncestor);
            if (arrayList.isEmpty()) {
                addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_prev_page, R.string.title_viewpager_breakout_prev_page, 8192, selfOrMatchingAncestor);
                addMenuItemIfActionExists$ar$ds(arrayList, context, R.id.viewpager_breakout_next_page, R.string.title_viewpager_breakout_next_page, 4096, selfOrMatchingAncestor);
            }
            if (!arrayList.isEmpty()) {
                ViewPagerItemClickListener viewPagerItemClickListener = new ViewPagerItemClickListener(selfOrMatchingAncestor, this.pipeline, this.analytics);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((ContextMenuItem) arrayList.get(i)).listener = viewPagerItemClickListener;
                }
            }
        }
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.title_viewpager_controls);
    }
}
