package com.google.android.accessibility.talkback.contextmenu;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractOnContextMenuItemClickListener implements OnContextMenuItemClickListener {
    protected final TalkBackAnalytics analytics;
    protected final AccessibilityNodeInfoCompat node;
    protected final Pipeline.FeedbackReturner pipeline;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOnContextMenuItemClickListener(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
        this.node = accessibilityNodeInfoCompat;
        this.pipeline = feedbackReturner;
        this.analytics = talkBackAnalytics;
    }

    @Override // com.google.android.accessibility.talkback.contextmenu.OnContextMenuItemClickListener
    public final /* synthetic */ void clear() {
    }
}
