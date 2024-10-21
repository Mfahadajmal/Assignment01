package com.google.android.accessibility.talkback.menurules;

import android.content.Context;
import android.content.res.Resources;
import android.view.MenuItem;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.CursorGranularityManager;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.AbstractOnContextMenuItemClickListener;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RuleGranularity extends NodeMenuRule {
    private final ActorState actorState;
    final TalkBackAnalytics analytics;
    private final Pipeline.FeedbackReturner pipeline;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GranularityMenuItemClickListener extends AbstractOnContextMenuItemClickListener {
        private final Context context;

        public GranularityMenuItemClickListener(Context context, Pipeline.FeedbackReturner feedbackReturner, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, TalkBackAnalytics talkBackAnalytics) {
            super(accessibilityNodeInfoCompat, feedbackReturner, talkBackAnalytics);
            this.context = context;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public final boolean onMenuItemClick(MenuItem menuItem) {
            CursorGranularity cursorGranularity;
            Logger logger = Performance.DEFAULT_LOGGER;
            if (menuItem == null) {
                return false;
            }
            int itemId = menuItem.getItemId();
            this.analytics.onLocalContextMenuAction(1, itemId);
            CursorGranularity[] values = CursorGranularity.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    cursorGranularity = values[i];
                    if (cursorGranularity.resourceId == itemId) {
                        break;
                    }
                    i++;
                } else {
                    cursorGranularity = null;
                    break;
                }
            }
            if (cursorGranularity == null) {
                return false;
            }
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Feedback.FocusDirection.Builder granularity = Feedback.granularity(cursorGranularity);
            granularity.setFromUser$ar$ds(true);
            granularity.targetNode = this.node;
            boolean $default$returnFeedback = SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, granularity);
            if ($default$returnFeedback) {
                Context context = this.context;
                ImmutableList immutableList = SelectorController.SELECTOR_SETTINGS;
                SelectorController.Setting settingFromCursorGranularity = SelectorController.Granularity.getSettingFromCursorGranularity(cursorGranularity);
                if (settingFromCursorGranularity != null) {
                    switch (settingFromCursorGranularity.ordinal()) {
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                            SelectorController.updateSettingPref(context, settingFromCursorGranularity);
                            break;
                    }
                }
            }
            return $default$returnFeedback;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum GranularitySetting {
        CHARACTERS(R.string.pref_show_navigation_menu_characters_setting_key, R.string.granularity_character, R.bool.pref_show_navigation_menu_characters_default),
        WORDS(R.string.pref_show_navigation_menu_words_setting_key, R.string.granularity_word, R.bool.pref_show_navigation_menu_words_default),
        LINES(R.string.pref_show_navigation_menu_lines_setting_key, R.string.granularity_line, R.bool.pref_show_navigation_menu_lines_default),
        PARAGRAPHS(R.string.pref_show_navigation_menu_paragraphs_setting_key, R.string.granularity_paragraph, R.bool.pref_show_navigation_menu_paragraphs_default),
        HEADINGS(R.string.pref_show_navigation_menu_headings_setting_key, R.string.granularity_native_heading, R.bool.pref_show_navigation_menu_headings_default),
        CONTROLS(R.string.pref_show_navigation_menu_controls_setting_key, R.string.granularity_native_control, R.bool.pref_show_navigation_menu_controls_default),
        LINKS(R.string.pref_show_navigation_menu_links_setting_key, R.string.granularity_native_link, R.bool.pref_show_navigation_menu_links_default),
        WEB_HEADINGS(R.string.pref_show_navigation_menu_headings_setting_key, R.string.granularity_web_heading, R.bool.pref_show_navigation_menu_headings_default),
        WEB_CONTROLS(R.string.pref_show_navigation_menu_controls_setting_key, R.string.granularity_web_control, R.bool.pref_show_navigation_menu_controls_default),
        WEB_LINKS(R.string.pref_show_navigation_menu_links_setting_key, R.string.granularity_web_link, R.bool.pref_show_navigation_menu_links_default),
        WEB_LANDMARKS(R.string.pref_show_navigation_menu_landmarks_setting_key, R.string.granularity_web_landmark, R.bool.pref_show_navigation_menu_landmarks_default),
        WINDOW(R.string.pref_show_navigation_menu_window_setting_key, R.string.granularity_window, R.bool.pref_show_navigation_menu_window_default),
        CONTAINER(R.string.pref_show_navigation_menu_container_setting_key, R.string.granularity_container, R.bool.pref_show_navigation_menu_container_default),
        DEFAULT_NAVIGATION(R.string.pref_show_navigation_menu_granularity_default_setting_key, R.string.granularity_default, R.bool.pref_show_navigation_menu_granularity_default);

        final int defaultValueResId;
        final int granularityResId;
        final int prefKeyResId;

        GranularitySetting(int i, int i2, int i3) {
            this.prefKeyResId = i;
            this.granularityResId = i2;
            this.defaultValueResId = i3;
        }
    }

    public RuleGranularity(Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, TalkBackAnalytics talkBackAnalytics) {
        super(R.string.pref_show_context_menu_granularity_setting_key, R.bool.pref_show_context_menu_granularity_default);
        this.pipeline = feedbackReturner;
        this.actorState = actorState;
        this.analytics = talkBackAnalytics;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final boolean accept(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (getMenuItemsForNode(context, accessibilityNodeInfoCompat, false).isEmpty()) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenu
    public final List getMenuItemsForNode(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        CursorGranularity cursorGranularity;
        GranularitySetting granularitySetting;
        boolean z2;
        RuleGranularity ruleGranularity = this;
        DirectionNavigationActor directionNavigationActor = (DirectionNavigationActor) ruleGranularity.actorState.getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
        if (((CursorGranularityManager) directionNavigationActor.DirectionNavigationActor$ar$cursorGranularityManager).isLockedToNodeOrEditingNode(accessibilityNodeInfoCompat)) {
            cursorGranularity = ((CursorGranularityManager) directionNavigationActor.DirectionNavigationActor$ar$cursorGranularityManager).currentGranularity;
        } else {
            cursorGranularity = CursorGranularity.DEFAULT;
        }
        ArrayList arrayList = new ArrayList();
        boolean supportsWebActions = WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat);
        GranularityMenuItemClickListener granularityMenuItemClickListener = new GranularityMenuItemClickListener(context, ruleGranularity.pipeline, accessibilityNodeInfoCompat, ruleGranularity.analytics);
        CursorGranularity[] values = CursorGranularity.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            CursorGranularity cursorGranularity2 = values[i];
            ActorState actorState = ruleGranularity.actorState;
            int i2 = cursorGranularity2.resourceId;
            GranularitySetting[] values2 = GranularitySetting.values();
            int length2 = values2.length;
            int i3 = 0;
            while (true) {
                if (i3 < length2) {
                    granularitySetting = values2[i3];
                    if (granularitySetting.granularityResId == i2) {
                        break;
                    }
                    i3++;
                } else {
                    granularitySetting = null;
                    break;
                }
            }
            if (granularitySetting != null && (cursorGranularity2 != CursorGranularity.LINE || !actorState.getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().isSelectionModeActive() || SpannableUtils$IdentifierSpan.isAtLeastT())) {
                Resources resources = context.getResources();
                if (SpannableUtils$IdentifierSpan.getSharedPreferences(context).getBoolean(resources.getString(granularitySetting.prefKeyResId), resources.getBoolean(granularitySetting.defaultValueResId))) {
                    if (cursorGranularity2.isWebGranularity()) {
                        if (supportsWebActions) {
                            z2 = true;
                        }
                    } else {
                        z2 = supportsWebActions;
                    }
                    if (!cursorGranularity2.isNativeMacroGranularity() || !z2) {
                        int i4 = cursorGranularity2.resourceId;
                        ContextMenuItem createMenuItem = ContextMenu.createMenuItem(context, 0, i4, 0, context.getString(i4));
                        createMenuItem.listener = granularityMenuItemClickListener;
                        createMenuItem.checkable = true;
                        createMenuItem.checked = cursorGranularity2.equals(cursorGranularity);
                        createMenuItem.setSkipRefocusEvents$ar$ds();
                        createMenuItem.setSkipWindowEvents$ar$ds();
                        arrayList.add(createMenuItem);
                        i++;
                        ruleGranularity = this;
                    }
                }
            }
            i++;
            ruleGranularity = this;
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((ContextMenuItem) arrayList.get(i5)).deferredType$ar$edu = 2;
        }
        return arrayList;
    }

    @Override // com.google.android.accessibility.talkback.menurules.NodeMenuRule
    public final CharSequence getUserFriendlyMenuName(Context context) {
        return context.getString(R.string.title_granularity);
    }
}
