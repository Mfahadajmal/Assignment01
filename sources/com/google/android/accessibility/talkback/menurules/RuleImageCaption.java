package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.AbstractOnContextMenuItemClickListener;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleImageCaption extends NodeMenuRule {
    private final ActorState actorState;
    private final TalkBackAnalytics analytics;
    private final Pipeline.FeedbackReturner pipeline;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ImageCaptionMenuItemOnClickListener extends AbstractOnContextMenuItemClickListener {
        private final ActorState actorState;
        private final Context context;

        public ImageCaptionMenuItemOnClickListener(Context context, ActorState actorState, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics) {
            super(accessibilityNodeInfoCompat, feedbackReturner, talkBackAnalytics);
            this.context = context;
            this.actorState = actorState;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            Feedback.Part.Builder preferredImageDescriptionFeedback = GeminiFunctionUtils.getPreferredImageDescriptionFeedback(this.context, this.actorState, this.node);
            if (preferredImageDescriptionFeedback != null) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                if (SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, preferredImageDescriptionFeedback)) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    public RuleImageCaption(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_image_caption_setting_key, R.bool.pref_show_context_menu_image_caption_default);
        this.pipeline = feedbackReturner;
        this.actorState = actorState;
        this.analytics = talkBackAnalytics;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return ImageCaptioner.supportsImageCaption$ar$ds();
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        ArrayList arrayList = new ArrayList();
        ContextMenuItem createMenuItem = ContextMenu.createMenuItem(context, 0, R.id.image_caption_menu, 6, context.getString(R.string.title_image_caption));
        createMenuItem.listener = new ImageCaptionMenuItemOnClickListener(context, this.actorState, accessibilityNodeInfoCompat, this.pipeline, this.analytics);
        createMenuItem.setSkipRefocusEvents$ar$ds();
        createMenuItem.setSkipWindowEvents$ar$ds();
        createMenuItem.deferredType$ar$edu = 2;
        arrayList.add(createMenuItem);
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.title_image_caption);
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final boolean isSubMenu() {
        return false;
    }
}
