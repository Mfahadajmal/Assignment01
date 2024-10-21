package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.AbstractOnContextMenuItemClickListener;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CustomActionMenu implements NodeMenu {
    final TalkBackAnalytics analytics;
    private final Pipeline.FeedbackReturner pipeline;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CustomMenuItemClickListener extends AbstractOnContextMenuItemClickListener {
        private final int id;

        public CustomMenuItemClickListener(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
            super(accessibilityNodeInfoCompat, feedbackReturner, talkBackAnalytics);
            this.id = i;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            Logger logger = Performance.DEFAULT_LOGGER;
            boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.nodeAction(this.node, this.id));
            int i = this.id;
            if (i != 262144 && i != 524288 && i != 1048576) {
                this.analytics.onLocalContextMenuAction(5, -1);
            } else {
                this.analytics.onLocalContextMenuAction(5, i);
            }
            return $default$returnFeedback;
        }
    }

    public CustomActionMenu(Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
        this.pipeline = feedbackReturner;
        this.analytics = talkBackAnalytics;
    }

    public static boolean acceptCustomActionMenu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        List actionList = accessibilityNodeInfoCompat.getActionList();
        if (actionList != null && !actionList.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        throw null;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0020 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void populateCustomMenuItemsForNode(android.content.Context r9, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r10, java.util.List r11, boolean r12, java.util.Set r13) {
        /*
            Method dump skipped, instructions count: 271
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.menurules.CustomActionMenu.populateCustomMenuItemsForNode(android.content.Context, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, java.util.List, boolean, java.util.Set):void");
    }
}
