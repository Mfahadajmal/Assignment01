package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.text.TextUtils;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.AbstractOnContextMenuItemClickListener;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EditingAndSelectingMenu implements NodeMenu {
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    private final ActorState actorState;
    final TalkBackAnalytics analytics;
    private final boolean isAndroidWear = FormFactorUtils.getInstance().isAndroidWear;
    private final Pipeline.FeedbackReturner pipeline;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EditingMenuItemClickListener extends AbstractOnContextMenuItemClickListener {
        public EditingMenuItemClickListener(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
            super(accessibilityNodeInfoCompat, feedbackReturner, talkBackAnalytics);
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            boolean $default$returnFeedback;
            if (menuItem != null) {
                int itemId = menuItem.getItemId();
                this.analytics.onLocalContextMenuAction(2, itemId);
                Logger logger = Performance.DEFAULT_LOGGER;
                if (itemId == R.id.edittext_breakout_move_to_beginning) {
                    $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 8));
                } else if (itemId == R.id.edittext_breakout_move_to_end) {
                    $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 9));
                } else if (itemId == R.id.edittext_breakout_cut) {
                    $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 5));
                } else if (itemId == R.id.edittext_breakout_copy) {
                    $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 4));
                } else if (itemId == R.id.edittext_breakout_paste) {
                    $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 6));
                } else {
                    if (itemId == R.id.edittext_breakout_select_all) {
                        if (!TextUtils.isEmpty(AccessibilityNodeInfoUtils.getText(this.node))) {
                            $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 1));
                        } else {
                            itemId = R.id.edittext_breakout_select_all;
                        }
                    }
                    if (itemId == R.id.edittext_breakout_start_selection_mode) {
                        $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 2));
                    } else {
                        if (itemId == R.id.edittext_breakout_end_selection_mode) {
                            $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.edit$ar$edu(this.node, 3));
                        }
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                    }
                }
                if ($default$returnFeedback) {
                    TalkBackService talkBackService = TalkBackService.instance;
                    if (talkBackService != null && talkBackService.analytics == null) {
                        throw new IllegalStateException("mAnalytics has not been initialized");
                    }
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
            }
            return true;
        }
    }

    public EditingAndSelectingMenu(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, AccessibilityFocusMonitor accessibilityFocusMonitor, TalkBackAnalytics talkBackAnalytics) {
        this.pipeline = feedbackReturner;
        this.actorState = actorState;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.analytics = talkBackAnalytics;
    }

    public static boolean acceptEditingAndSelectingMenu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        if ((!accessibilityNodeInfoCompat.isFocused() || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4) && !AccessibilityNodeInfoUtils.isNonEditableSelectableText(accessibilityNodeInfoCompat) && accessibilityNodeInfoCompat2 == null) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return acceptEditingAndSelectingMenu(accessibilityNodeInfoCompat, this.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat));
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        throw null;
    }

    public final void populateEditingAndSelectingMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, List list) {
        ContextMenuItem createMenuItem;
        if (TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
            if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 131072, 512)) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_move_to_beginning, 0, context.getString(R.string.title_edittext_breakout_move_to_beginning)));
            }
            if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 131072, 256)) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_move_to_end, 0, context.getString(R.string.title_edittext_breakout_move_to_end)));
            }
            if (!this.isAndroidWear && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 65536)) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_cut, 0, context.getString(android.R.string.cut)));
            }
            if (!this.isAndroidWear && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 16384)) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_copy, 0, context.getString(android.R.string.copy)));
            }
            if (!this.isAndroidWear && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4 && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 32768)) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_paste, 0, context.getString(android.R.string.paste)));
            }
            if (!this.isAndroidWear && AccessibilityNodeInfoUtils.supportsAnyAction(accessibilityNodeInfoCompat, 131072) && AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat) != null) {
                list.add(ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_select_all, 0, context.getString(android.R.string.selectAll)));
            }
            if (!this.isAndroidWear) {
                if (this.actorState.getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().isSelectionModeActive()) {
                    createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_end_selection_mode, 0, context.getString(R.string.title_edittext_breakout_end_selection_mode));
                } else {
                    createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.edittext_breakout_start_selection_mode, 0, context.getString(R.string.title_edittext_breakout_start_selection_mode));
                }
                list.add(createMenuItem);
            }
        }
        EditingMenuItemClickListener editingMenuItemClickListener = new EditingMenuItemClickListener(accessibilityNodeInfoCompat, this.pipeline, this.analytics);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ContextMenuItem contextMenuItem = (ContextMenuItem) it.next();
            contextMenuItem.listener = editingMenuItemClickListener;
            contextMenuItem.setSkipRefocusEvents$ar$ds();
            contextMenuItem.setSkipWindowEvents$ar$ds();
            contextMenuItem.deferredType$ar$edu = 3;
        }
    }
}
