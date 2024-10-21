package com.google.android.accessibility.talkback.selector;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.brailleime.input.MultitouchHandler$$ExternalSyntheticLambda8;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Interpretation$AccessibilityFocused;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.UserInterface$UserInputEventListener;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.TalkBackUIActor$Type;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.contextmenu.ContextMenu;
import com.google.android.accessibility.talkback.contextmenu.ContextMenuItem;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorAccessibilityHints$HintInfo;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.accessibility.talkback.preference.base.VerbosityPrefFragment;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectorController implements UserInterface$UserInputEventListener {
    public static final ImmutableList SELECTOR_SETTINGS = ImmutableList.of(Setting.GRANULARITY_TYPO, Setting.ACTIONS, Setting.GRANULARITY_CHARACTERS, Setting.GRANULARITY_WORDS, Setting.GRANULARITY_LINES, Setting.GRANULARITY_PARAGRAPHS, Setting.GRANULARITY_HEADINGS, Setting.GRANULARITY_CONTROLS, Setting.GRANULARITY_LINKS, Setting.GRANULARITY_LANDMARKS, Setting.GRANULARITY_WINDOWS, Setting.GRANULARITY_CONTAINERS, Setting.GRANULARITY_DEFAULT, Setting.SPEECH_RATE, Setting.VERBOSITY, Setting.PUNCTUATION, Setting.LANGUAGE, Setting.HIDE_SCREEN, Setting.AUDIO_FOCUS, Setting.SCROLLING_SEQUENTIAL, Setting.CHANGE_ACCESSIBILITY_VOLUME, Setting.ADJUSTABLE_WIDGET);
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    private final ActorState actorState;
    private final TalkBackAnalytics analytics;
    public String cachedSelectSettingGestureNames;
    private final HapticPatternParser$$ExternalSyntheticLambda1 compositor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;
    private final ImmutableList contextualSettings;
    public ContextMenuItem.ContextMenuItemId currentActionId;
    private final FormFactorUtils formFactorUtils;
    private final GestureShortcutMapping gestureMapping;
    private final ImmutableList hiddenSettings;
    private final NodeMenuRuleCreator nodeMenuCreator;
    private final Pipeline.FeedbackReturner pipeline;
    public final SharedPreferences prefs;
    private final RetryingNameResolver.ResolutionResultListener selectorEventNotifier$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private Setting settingToRestore;
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
    private boolean hasRequestServiceHandlesDoubleTap = false;
    private boolean touchActive = false;
    private AccessibilityNodeInfo lastFocusedNode = null;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ContextualSetting {
        Setting getSetting();

        boolean isNodeSupportSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

        boolean shouldActivateSetting(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Granularity {
        HEADINGS(Setting.GRANULARITY_HEADINGS, CursorGranularity.HEADING, 1),
        WORDS(Setting.GRANULARITY_WORDS, CursorGranularity.WORD, 0),
        PARAGRAPHS(Setting.GRANULARITY_PARAGRAPHS, CursorGranularity.PARAGRAPH, 0),
        CHARACTERS(Setting.GRANULARITY_CHARACTERS, CursorGranularity.CHARACTER, 0),
        LINES(Setting.GRANULARITY_LINES, CursorGranularity.LINE, 0),
        LINKS(Setting.GRANULARITY_LINKS, CursorGranularity.LINK, 1),
        CONTROLS(Setting.GRANULARITY_CONTROLS, CursorGranularity.CONTROL, 1),
        WINDOWS(Setting.GRANULARITY_WINDOWS, CursorGranularity.WINDOWS, 0),
        CONTAINERS(Setting.GRANULARITY_CONTAINERS, CursorGranularity.CONTAINER, 0),
        DEFAULT(Setting.GRANULARITY_DEFAULT, CursorGranularity.DEFAULT, 0),
        WEB_LANDMARKS(Setting.GRANULARITY_LANDMARKS, CursorGranularity.WEB_LANDMARK, 2),
        WEB_HEADINGS(Setting.GRANULARITY_HEADINGS, CursorGranularity.WEB_HEADING, 2),
        WEB_LINKS(Setting.GRANULARITY_LINKS, CursorGranularity.WEB_LINK, 2),
        WEB_CONTROLS(Setting.GRANULARITY_CONTROLS, CursorGranularity.WEB_CONTROL, 2);

        final CursorGranularity cursorGranularity;
        final int granularityType;
        final Setting setting;

        Granularity(Setting setting, CursorGranularity cursorGranularity, int i) {
            this.setting = setting;
            this.cursorGranularity = cursorGranularity;
            this.granularityType = i;
        }

        public static List getFromSetting(Setting setting) {
            ArrayList arrayList = new ArrayList();
            for (Granularity granularity : values()) {
                if (granularity.setting.prefKeyResId == setting.prefKeyResId) {
                    arrayList.add(granularity);
                }
            }
            return arrayList;
        }

        public static Setting getSettingFromCursorGranularity(CursorGranularity cursorGranularity) {
            for (Granularity granularity : values()) {
                if (granularity.cursorGranularity == cursorGranularity) {
                    return granularity.setting;
                }
            }
            return null;
        }

        static boolean isValid(Granularity granularity, boolean z) {
            int i = granularity.granularityType;
            if (i == 0) {
                return true;
            }
            if (i == 1) {
                if (z) {
                    z = true;
                }
                return true;
            }
            if (i == 2 && z) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum Setting {
        SPEECH_RATE(R.string.pref_selector_speech_rate_key, R.string.selector_speech_rate, R.bool.pref_selector_speech_rate_default),
        LANGUAGE(R.string.pref_selector_language_key, R.string.selector_language, R.bool.pref_selector_language_default),
        VERBOSITY(R.string.pref_selector_verbosity_key, R.string.selector_verbosity, R.bool.pref_selector_verbosity_default),
        PUNCTUATION(R.string.pref_selector_punctuation_key, R.string.selector_punctuation, R.bool.pref_selector_punctuation_default),
        GRANULARITY(R.string.pref_selector_granularity_key, R.string.selector_granularity, R.bool.pref_selector_granularity_default),
        HIDE_SCREEN(R.string.pref_selector_hide_screen_key, R.string.selector_hide_screen, R.bool.pref_selector_hide_screen_default),
        AUDIO_FOCUS(R.string.pref_selector_audio_focus_key, R.string.selector_audio_focus, R.bool.pref_selector_audio_focus_default),
        SCROLLING_SEQUENTIAL(R.string.pref_selector_scroll_seq_key, R.string.selector_scroll_seq, R.bool.pref_selector_scroll_seq_default),
        CHANGE_ACCESSIBILITY_VOLUME(R.string.pref_selector_change_a11y_volume_key, R.string.selector_a11y_volume_change, R.bool.pref_selector_a11y_volume_default),
        ACTIONS(R.string.pref_selector_actions_key, R.string.selector_actions, R.bool.pref_selector_actions_default),
        GRANULARITY_HEADINGS(R.string.pref_selector_granularity_headings_key, R.string.selector_granularity_headings, R.bool.pref_selector_granularity_headings_default),
        GRANULARITY_WORDS(R.string.pref_selector_granularity_words_key, R.string.selector_granularity_words, R.bool.pref_selector_granularity_words_default),
        GRANULARITY_PARAGRAPHS(R.string.pref_selector_granularity_paragraphs_key, R.string.selector_granularity_paragraphs, R.bool.pref_selector_granularity_paragraphs_default),
        GRANULARITY_CHARACTERS(R.string.pref_selector_granularity_characters_key, R.string.selector_granularity_characters, R.bool.pref_selector_granularity_characters_default),
        GRANULARITY_LINES(R.string.pref_selector_granularity_lines_key, R.string.selector_granularity_lines, R.bool.pref_selector_granularity_lines_default),
        GRANULARITY_LINKS(R.string.pref_selector_granularity_links_key, R.string.selector_granularity_links, R.bool.pref_selector_granularity_links_default),
        GRANULARITY_CONTROLS(R.string.pref_selector_granularity_controls_key, R.string.selector_granularity_controls, R.bool.pref_selector_granularity_controls_default),
        GRANULARITY_LANDMARKS(R.string.pref_selector_granularity_landmarks_key, R.string.selector_granularity_landmarks, R.bool.pref_selector_granularity_landmarks_default),
        GRANULARITY_WINDOWS(R.string.pref_selector_granularity_windows_key, R.string.selector_granularity_windows, R.bool.pref_selector_granularity_windows_default),
        GRANULARITY_CONTAINERS(R.string.pref_selector_granularity_containers_key, R.string.selector_granularity_containers, R.bool.pref_selector_granularity_containers_default),
        GRANULARITY_DEFAULT(R.string.pref_selector_granularity_key, R.string.granularity_default, R.bool.pref_show_navigation_menu_granularity_default),
        ADJUSTABLE_WIDGET(R.string.pref_selector_special_widget_key, R.string.selector_special_widget, R.bool.pref_selector_special_widget_default),
        GRANULARITY_TYPO(R.string.pref_selector_granularity_typo_key, R.string.selector_granularity_typo, R.bool.pref_selector_granularity_typo_default);

        public final int defaultValueResId;
        public final int prefKeyResId;
        final int prefValueResId;

        Setting(int i, int i2, int i3) {
            this.prefKeyResId = i;
            this.prefValueResId = i2;
            this.defaultValueResId = i3;
        }
    }

    public SelectorController(Context context, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, AccessibilityFocusMonitor accessibilityFocusMonitor, NodeMenuRuleCreator nodeMenuRuleCreator, TalkBackAnalytics talkBackAnalytics, GestureShortcutMapping gestureShortcutMapping, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        OverlayDisplay.AnonymousClass1 anonymousClass1 = new OverlayDisplay.AnonymousClass1(this, 16, null);
        this.sharedPreferenceChangeListener = anonymousClass1;
        this.context = context;
        this.pipeline = feedbackReturner;
        this.actorState = actorState;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.nodeMenuCreator = nodeMenuRuleCreator;
        this.analytics = talkBackAnalytics;
        this.gestureMapping = gestureShortcutMapping;
        this.compositor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
        this.formFactorUtils = formFactorUtils;
        this.selectorEventNotifier$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (formFactorUtils.isAndroidWear) {
            builder.add$ar$ds$4f674a09_0(Setting.GRANULARITY_TYPO);
        } else if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            builder.add$ar$ds$4f674a09_0(Setting.ACTIONS);
        }
        this.hiddenSettings = builder.build();
        ImmutableList.Builder builder2 = new ImmutableList.Builder();
        builder2.add$ar$ds$4f674a09_0(new AdjustableWidgetSetting());
        builder2.add$ar$ds$4f674a09_0(new ActionsSetting(nodeMenuRuleCreator, accessibilityFocusMonitor));
        builder2.add$ar$ds$4f674a09_0(new TypoGranularity(accessibilityFocusMonitor));
        this.contextualSettings = builder2.build();
        resetActionMenuToDefault();
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.prefs = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(anonymousClass1);
    }

    private final boolean allowedSetting(Setting setting) {
        return allowedSetting(setting, null);
    }

    private final Optional findContextualSetting(Setting setting) {
        return Collection.EL.stream(this.contextualSettings).filter(new MultitouchHandler$$ExternalSyntheticLambda8(setting, 6)).findFirst();
    }

    private final String getAdjustSelectedSettingGestureNames() {
        String string;
        List gestureTextsFromActionKeys = this.gestureMapping.getGestureTextsFromActionKeys(this.context.getString(R.string.shortcut_value_selected_setting_previous_action), this.context.getString(R.string.shortcut_value_selected_setting_next_action));
        if (gestureTextsFromActionKeys.isEmpty()) {
            string = "";
        } else if (gestureTextsFromActionKeys.size() == 1) {
            string = ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(0));
        } else {
            string = this.context.getString(R.string.gesture_1_or_2, ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(0)), ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(1)));
        }
        this.cachedSelectSettingGestureNames = string;
        return string;
    }

    private final String getAdjustSelectedSettingGestures() {
        String adjustSelectedSettingGestureNames = getAdjustSelectedSettingGestureNames();
        if (adjustSelectedSettingGestureNames.isEmpty()) {
            Context context = this.context;
            return context.getString(R.string.no_adjust_setting_gesture, ContextDataProvider.toLowerCase(context.getString(R.string.shortcut_selected_setting_next_action)));
        }
        return this.context.getString(R.string.adjust_setting_hint, adjustSelectedSettingGestureNames);
    }

    public static Setting getCurrentSetting(Context context) {
        return getCurrentSetting(context, SpannableUtils$IdentifierSpan.getSharedPreferences(context));
    }

    private final boolean isContextualSetting(Setting setting) {
        return Collection.EL.stream(this.contextualSettings).anyMatch(new MultitouchHandler$$ExternalSyntheticLambda8(setting, 8));
    }

    private final void requestServiceHandlesDoubleTap(Performance.EventId eventId, boolean z) {
        if (z) {
            if (!this.hasRequestServiceHandlesDoubleTap) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.requestServiceFlag$ar$edu$ar$ds(1));
                this.hasRequestServiceHandlesDoubleTap = true;
                return;
            }
            return;
        }
        if (this.hasRequestServiceHandlesDoubleTap) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.requestServiceFlag$ar$edu$ar$ds(2));
            this.hasRequestServiceHandlesDoubleTap = false;
        }
    }

    private final void resetActionMenuToDefault() {
        this.currentActionId = new ContextMenuItem.ContextMenuItemId(16, this.context.getString(R.string.shortcut_perform_click_action));
    }

    public static void resetSelectedSetting(Context context) {
        Setting setting = Setting.GRANULARITY_CHARACTERS;
        SpannableUtils$IdentifierSpan.getSharedPreferences(context).edit().putString(context.getString(R.string.pref_current_selector_setting_key), context.getString(setting.prefValueResId)).putBoolean(context.getString(setting.prefKeyResId), true).apply();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void restoreSetting() {
        Setting setting = this.settingToRestore;
        if (setting != null && allowedSetting(setting)) {
            Logger logger = Performance.DEFAULT_LOGGER;
            setCurrentSetting$ar$edu(null, this.settingToRestore, 1, false);
        } else {
            ImmutableList filteredSettings = getFilteredSettings();
            if (!filteredSettings.isEmpty()) {
                selectSetting$ar$edu$ar$ds((Setting) filteredSettings.get(0), 1);
            } else {
                Logger logger2 = Performance.DEFAULT_LOGGER;
                requestServiceHandlesDoubleTap(null, false);
                resetSelectedSetting(this.context);
            }
        }
        this.settingToRestore = null;
    }

    private final void showQuickMenuActionOverlay(Performance.EventId eventId, CharSequence charSequence) {
        Context context = this.context;
        String string = context.getString(R.string.shortcut_value_selected_setting_previous_action);
        String string2 = context.getString(R.string.shortcut_value_selected_setting_next_action);
        boolean z = false;
        if (!this.formFactorUtils.isAndroidWear) {
            SharedPreferences sharedPreferences = this.prefs;
            Context context2 = this.context;
            if (string.equals(sharedPreferences.getString(context2.getString(R.string.pref_shortcut_up_key), context2.getString(R.string.pref_shortcut_up_default)))) {
                SharedPreferences sharedPreferences2 = this.prefs;
                Context context3 = this.context;
                if (string2.equals(sharedPreferences2.getString(context3.getString(R.string.pref_shortcut_down_key), context3.getString(R.string.pref_shortcut_down_default)))) {
                    z = true;
                }
            }
        }
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.showSelectorUI(TalkBackUIActor$Type.SELECTOR_ITEM_ACTION_OVERLAY, charSequence, z));
        this.selectorEventNotifier$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.onSelectorOverlayShown(charSequence);
    }

    private final void switchCurrentSetting(AccessibilityNodeInfo accessibilityNodeInfo) {
        Setting currentSetting = getCurrentSetting(this.context, this.prefs);
        this.lastFocusedNode = null;
        Optional findFirst = Collection.EL.stream(this.contextualSettings).filter(new SelectorController$$ExternalSyntheticLambda4(this.hiddenSettings, this.context, new AccessibilityNodeInfoCompat(accessibilityNodeInfo), 0)).findFirst();
        if (findFirst.isPresent()) {
            Setting setting = ((ContextualSetting) findFirst.get()).getSetting();
            if (setting == Setting.ACTIONS && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfo) == 4) {
                restoreSetting();
                return;
            } else {
                if (setting != currentSetting) {
                    this.settingToRestore = currentSetting;
                    Logger logger = Performance.DEFAULT_LOGGER;
                    setCurrentSetting$ar$edu(null, setting, 1, false);
                    return;
                }
                return;
            }
        }
        if (isContextualSetting(currentSetting)) {
            restoreSetting();
        }
    }

    public static void updateSettingPref(Context context, Setting setting) {
        SpannableUtils$IdentifierSpan.getSharedPreferences(context).edit().putString(context.getString(R.string.pref_current_selector_setting_key), context.getString(setting.prefValueResId)).apply();
    }

    public final void activateCurrentAction(Performance.EventId eventId) {
        if (this.accessibilityFocusMonitor.hasAccessibilityFocus$ar$ds()) {
            if (!this.prefs.getBoolean(this.context.getString(Setting.ACTIONS.prefKeyResId), this.context.getResources().getBoolean(Setting.ACTIONS.defaultValueResId))) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(this.context.getString(R.string.actions_setting_not_enabled)));
                return;
            }
            AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
            if (accessibilityFocus != null) {
                if (this.currentActionId.itemId == 16) {
                    Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                    Feedback.Focus.Builder focus = Feedback.focus(Feedback.Focus.Action.CLICK_NODE);
                    focus.target = accessibilityFocus;
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, focus);
                    return;
                }
                List nodeMenuByRule$ar$ds = this.nodeMenuCreator.getNodeMenuByRule$ar$ds(NodeMenuRuleCreator.MenuRules.RULE_CUSTOM_ACTION, this.context, accessibilityFocus);
                if (!nodeMenuByRule$ar$ds.isEmpty()) {
                    Optional findFirst = Collection.EL.stream(nodeMenuByRule$ar$ds).filter(new MultitouchHandler$$ExternalSyntheticLambda8(this, 7)).findFirst();
                    if (findFirst.isPresent()) {
                        this.analytics.onSelectorActionEvent(getCurrentSetting(this.context, this.prefs));
                        if (((ContextMenuItem) findFirst.get()).onClickPerformed()) {
                            return;
                        }
                    }
                }
                Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                Feedback.Part.Builder builder = Feedback.Part.builder();
                Feedback.Speech.Builder builder2 = Feedback.Speech.builder();
                builder2.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
                builder2.text = this.context.getString(R.string.action_not_supported);
                SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                speakOptions.mFlags = 2;
                builder2.hintSpeakOptions = speakOptions;
                builder2.hint = this.context.getString(R.string.hint_select_action);
                builder.speech = builder2.build();
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, eventId, builder);
            }
        }
    }

    public final void adjustSelectedSetting(Performance.EventId eventId, boolean z) {
        String string;
        Feedback.FocusDirection.Action action;
        int i;
        String string2;
        int i2;
        int i3;
        ContextMenuItem contextMenuItem;
        String string3;
        Setting currentSetting = getCurrentSetting(this.context, this.prefs);
        if (isContextualSetting(currentSetting) && !this.accessibilityFocusMonitor.hasAccessibilityFocus$ar$ds()) {
            restoreSetting();
            currentSetting = getCurrentSetting(this.context, this.prefs);
        }
        if (!Setting.ACTIONS.equals(currentSetting)) {
            this.analytics.onSelectorActionEvent(currentSetting);
        }
        int i4 = 2;
        int i5 = 0;
        switch (currentSetting) {
            case SPEECH_RATE:
                changeSpeechRate(eventId, !z);
                return;
            case LANGUAGE:
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                if (!z) {
                    i4 = 1;
                }
                Feedback.Part.Builder builder = Feedback.Part.builder();
                builder.language = new Feedback.Language(i4, null);
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, builder);
                announceSetting(eventId, this.actorState.getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().getCurrentLanguageString(), getSelectSettingGestures());
                showQuickMenuActionOverlay(eventId, this.actorState.getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().getCurrentLanguageString());
                return;
            case VERBOSITY:
                List asList = Arrays.asList(this.context.getResources().getStringArray(R.array.pref_verbosity_preset_values));
                int size = asList.size();
                if (size != 0) {
                    Context context = this.context;
                    SharedPreferences sharedPreferences = this.prefs;
                    String string4 = context.getString(R.string.pref_verbosity_preset_key);
                    int indexOf = asList.indexOf(sharedPreferences.getString(string4, context.getString(R.string.pref_verbosity_preset_value_default)));
                    if (z) {
                        int i6 = indexOf + 1;
                        if (i6 < size && i6 >= 0) {
                            i5 = i6;
                        }
                    } else {
                        i5 = indexOf - 1;
                        if (i5 >= size || i5 < 0) {
                            i5 = size - 1;
                        }
                    }
                    String str = (String) asList.get(i5);
                    this.analytics.onManuallyChangeSetting$ar$ds(string4);
                    this.prefs.edit().putString(string4, str).apply();
                    announceSetting(eventId, VerbosityPrefFragment.getVerbosityChangeAnnouncement(str, this.context), getSelectSettingGestures());
                    showQuickMenuActionOverlay(eventId, VerbosityPrefFragment.verbosityValueToName(str, this.context));
                    return;
                }
                return;
            case PUNCTUATION:
                Context context2 = this.context;
                SharedPreferences sharedPreferences2 = this.prefs;
                Resources resources = context2.getResources();
                int parseInt = Integer.parseInt(SpannableUtils$IdentifierSpan.getStringPref(sharedPreferences2, resources, R.string.pref_punctuation_verbosity, R.string.pref_punctuation_verbosity_default));
                this.analytics.onManuallyChangeSetting$ar$ds(resources.getString(R.string.pref_use_audio_focus_key));
                String[] stringArray = this.context.getResources().getStringArray(R.array.pref_punctuation_values);
                String[] stringArray2 = this.context.getResources().getStringArray(R.array.pref_punctuation_entries);
                int i7 = (parseInt + 1) % 3;
                SpannableUtils$IdentifierSpan.putStringPref(this.prefs, resources, R.string.pref_punctuation_verbosity, stringArray[i7]);
                announceSetting(eventId, this.context.getString(R.string.punctuation_state, stringArray2[i7]), getSelectSettingGestures());
                showQuickMenuActionOverlay(eventId, stringArray2[i7]);
                return;
            case GRANULARITY:
                updateSettingPref(this.context, Setting.GRANULARITY_CHARACTERS);
                adjustSelectedSetting(eventId, z);
                return;
            case HIDE_SCREEN:
                if (this.actorState.getDimScreen$ar$class_merging$ar$class_merging().isDimmingEnabled()) {
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.dimScreen$ar$edu(2));
                    announceSetting(eventId, this.context.getString(R.string.screen_brightness_restored), getSelectSettingGestures());
                    showQuickMenuActionOverlay(eventId, this.context.getString(R.string.shortcut_disable_dimming));
                    return;
                } else {
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.dimScreen$ar$edu(1));
                    showQuickMenuActionOverlay(eventId, this.context.getString(R.string.shortcut_enable_dimming));
                    return;
                }
            case AUDIO_FOCUS:
                Context context3 = this.context;
                SharedPreferences sharedPreferences3 = this.prefs;
                Resources resources2 = context3.getResources();
                boolean booleanPref = SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences3, resources2, R.string.pref_use_audio_focus_key, R.bool.pref_use_audio_focus_default);
                this.analytics.onManuallyChangeSetting$ar$ds(resources2.getString(R.string.pref_use_audio_focus_key));
                SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, resources2, R.string.pref_use_audio_focus_key, !booleanPref);
                Context context4 = this.context;
                int i8 = R.string.value_on;
                if (!booleanPref) {
                    string = context4.getString(R.string.value_on);
                } else {
                    string = context4.getString(R.string.value_off);
                }
                announceSetting(eventId, context4.getString(R.string.audio_focus_state, string), getSelectSettingGestures());
                Context context5 = this.context;
                if (true == booleanPref) {
                    i8 = R.string.value_off;
                }
                showQuickMenuActionOverlay(eventId, context5.getString(i8));
                return;
            case SCROLLING_SEQUENTIAL:
                Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
                if (z) {
                    action = Feedback.FocusDirection.Action.PREVIOUS_PAGE;
                } else {
                    action = Feedback.FocusDirection.Action.NEXT_PAGE;
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, eventId, Feedback.focusDirection(action));
                return;
            case CHANGE_ACCESSIBILITY_VOLUME:
                Pipeline.FeedbackReturner feedbackReturner3 = this.pipeline;
                if (!z) {
                    i4 = 1;
                }
                if (SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner3, eventId, Feedback.adjustVolume$ar$edu(i4, 1))) {
                    Context context6 = this.context;
                    if (true != z) {
                        i2 = R.string.template_volume_change_increase;
                    } else {
                        i2 = R.string.template_volume_change_decrease;
                    }
                    string2 = context6.getString(i2);
                } else {
                    Context context7 = this.context;
                    if (true != z) {
                        i = R.string.template_volume_change_maximum;
                    } else {
                        i = R.string.template_volume_change_minimum;
                    }
                    string2 = context7.getString(i);
                }
                announceSetting(eventId, string2, getSelectSettingGestures());
                showQuickMenuActionOverlay(eventId, string2);
                return;
            case ACTIONS:
                AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
                if (accessibilityFocus != null) {
                    ArrayList arrayList = new ArrayList();
                    List<ContextMenuItem> nodeMenuByRule$ar$ds = this.nodeMenuCreator.getNodeMenuByRule$ar$ds(NodeMenuRuleCreator.MenuRules.RULE_CUSTOM_ACTION, this.context, accessibilityFocus);
                    if (nodeMenuByRule$ar$ds.isEmpty()) {
                        i3 = -1;
                    } else {
                        Context context8 = this.context;
                        arrayList.add(ContextMenu.createMenuItem(context8, 0, 16, 0, context8.getString(R.string.shortcut_perform_click_action)));
                        i3 = 0;
                        for (ContextMenuItem contextMenuItem2 : nodeMenuByRule$ar$ds) {
                            if (contextMenuItem2.getContextMenuItemId().equals(this.currentActionId)) {
                                i3 = arrayList.size();
                            }
                            arrayList.add(contextMenuItem2);
                        }
                    }
                    int size2 = arrayList.size();
                    if (size2 == 0) {
                        announceSetting(eventId, this.context.getString(R.string.no_action_available), getSelectSettingGestures());
                        return;
                    }
                    if (i3 == -1) {
                        contextMenuItem = (ContextMenuItem) arrayList.get(0);
                    } else if (z) {
                        contextMenuItem = (ContextMenuItem) arrayList.get((i3 + 1) % size2);
                    } else {
                        contextMenuItem = (ContextMenuItem) arrayList.get(((i3 - 1) + size2) % size2);
                    }
                    this.currentActionId = contextMenuItem.getContextMenuItemId();
                    CharSequence charSequence = contextMenuItem.title;
                    if (this.currentActionId.itemId == 16) {
                        requestServiceHandlesDoubleTap(eventId, false);
                    } else {
                        requestServiceHandlesDoubleTap(eventId, true);
                    }
                    if (charSequence == null) {
                        charSequence = this.context.getString(R.string.value_unlabelled);
                    }
                    if (contextMenuItem.itemId == R.id.typo_suggestions_menu) {
                        string3 = this.context.getString(R.string.use_spelling_suggestion_hint);
                    } else {
                        string3 = this.context.getString(R.string.use_action_hint);
                    }
                    announceSetting(eventId, charSequence, string3);
                    showQuickMenuActionOverlay(eventId, charSequence);
                    return;
                }
                return;
            case GRANULARITY_HEADINGS:
            case GRANULARITY_WORDS:
            case GRANULARITY_PARAGRAPHS:
            case GRANULARITY_CHARACTERS:
            case GRANULARITY_LINES:
            case GRANULARITY_LINKS:
            case GRANULARITY_CONTROLS:
            case GRANULARITY_LANDMARKS:
            case GRANULARITY_WINDOWS:
            case GRANULARITY_CONTAINERS:
            case GRANULARITY_DEFAULT:
                List<Granularity> fromSetting = Granularity.getFromSetting(currentSetting);
                if (!fromSetting.isEmpty()) {
                    boolean supportsWebActions = WebInterfaceUtils.supportsWebActions(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
                    for (Granularity granularity : fromSetting) {
                        if (Granularity.isValid(granularity, supportsWebActions)) {
                            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.granularity(granularity.cursorGranularity));
                            Setting settingFromCursorGranularity = Granularity.getSettingFromCursorGranularity(granularity.cursorGranularity);
                            Pipeline.FeedbackReturner feedbackReturner4 = this.pipeline;
                            if (true == z) {
                                i4 = 1;
                            }
                            Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i4);
                            focusDirection.setInputMode$ar$ds(0);
                            focusDirection.setToWindow$ar$ds(settingFromCursorGranularity.equals(Setting.GRANULARITY_WINDOWS));
                            focusDirection.setToContainer$ar$ds(settingFromCursorGranularity.equals(Setting.GRANULARITY_CONTAINERS));
                            focusDirection.setDefaultToInputFocus$ar$ds(true);
                            focusDirection.setScroll$ar$ds(true);
                            focusDirection.setWrap$ar$ds(true);
                            if (!SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner4, eventId, focusDirection)) {
                                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.sound(R.raw.complete));
                                return;
                            }
                            return;
                        }
                    }
                    return;
                }
                return;
            case ADJUSTABLE_WIDGET:
                Pipeline.FeedbackReturner feedbackReturner5 = this.pipeline;
                if (!z) {
                    i4 = 1;
                }
                Feedback.Part.Builder builder2 = Feedback.Part.builder();
                builder2.adjustValue = new Feedback.AdjustValue(i4);
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner5, eventId, builder2);
                return;
            case GRANULARITY_TYPO:
                Pipeline.FeedbackReturner feedbackReturner6 = this.pipeline;
                Feedback.Part.Builder builder3 = Feedback.Part.builder();
                builder3.navigateTypo = new Feedback.NavigateTypo(z);
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner6, eventId, builder3);
                return;
            default:
                return;
        }
    }

    public final void announceSetting(Performance.EventId eventId, CharSequence charSequence, String str) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (!TextUtils.isEmpty(str)) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.compositor$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                ProcessorAccessibilityHints$HintInfo processorAccessibilityHints$HintInfo = new ProcessorAccessibilityHints$HintInfo();
                processorAccessibilityHints$HintInfo.pendingSelectorHint = str;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, SpannableUtils$IdentifierSpan.hintInfoToFeedback$ar$class_merging$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(processorAccessibilityHints$HintInfo, hapticPatternParser$$ExternalSyntheticLambda1));
            }
            Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mQueueMode = 1;
            speakOptions.mFlags = 318;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, eventId, Feedback.speech(charSequence, speakOptions));
        }
    }

    public final void changeSpeechRate(Performance.EventId eventId, boolean z) {
        int i;
        if (z) {
            i = 1;
        } else {
            i = 2;
        }
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.speechRate = new Feedback.SpeechRate(i);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, builder);
        String string = this.context.getString(R.string.template_speech_rate_change, Integer.valueOf(this.actorState.writable.speechRateState$ar$class_merging$ar$class_merging.getSpeechRatePercentage()));
        announceSetting(eventId, string, getSelectSettingGestures());
        showQuickMenuActionOverlay(eventId, string);
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void editTextOrSelectableTextSelected(boolean z) {
        if (z) {
            this.settingToRestore = getCurrentSetting(this.context, this.prefs);
            Logger logger = Performance.DEFAULT_LOGGER;
            setCurrentSetting$ar$edu(null, Setting.GRANULARITY_CHARACTERS, 1, false);
        } else if (this.settingToRestore != null) {
            restoreSetting();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    final ImmutableList getFilteredSettings() {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
        ImmutableList immutableList = SELECTOR_SETTINGS;
        int i = ((RegularImmutableList) immutableList).size;
        for (int i2 = 0; i2 < i; i2++) {
            Setting setting = (Setting) immutableList.get(i2);
            if (!this.hiddenSettings.contains(setting) && allowedSetting(setting, accessibilityFocus)) {
                builder.add$ar$ds$4f674a09_0(setting);
            }
        }
        return builder.build();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Optional getNextOrPreviousSetting(boolean z) {
        int i;
        ImmutableList filteredSettings = getFilteredSettings();
        int i2 = ((RegularImmutableList) filteredSettings).size;
        if (i2 == 0) {
            return Optional.empty();
        }
        int indexOf = filteredSettings.indexOf(getCurrentSetting(this.context, this.prefs));
        if (z) {
            i = indexOf + 1;
            if (i >= i2 || i < 0) {
                i = 0;
            }
        } else {
            i = indexOf - 1;
            if (i >= i2 || i < 0) {
                i = i2 - 1;
            }
        }
        return Optional.of((Setting) filteredSettings.get(i));
    }

    public final String getSelectSettingGestures() {
        String str = this.cachedSelectSettingGestureNames;
        if (str == null) {
            List gestureTextsFromActionKeys = this.gestureMapping.getGestureTextsFromActionKeys(this.context.getString(R.string.shortcut_value_select_previous_setting), this.context.getString(R.string.shortcut_value_select_next_setting));
            if (gestureTextsFromActionKeys.isEmpty()) {
                str = "";
            } else if (gestureTextsFromActionKeys.size() == 1) {
                str = ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(0));
            } else {
                str = this.context.getString(R.string.gesture_1_or_2, ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(0)), ContextDataProvider.toLowerCase((String) gestureTextsFromActionKeys.get(1)));
            }
            this.cachedSelectSettingGestureNames = str;
        }
        if (str.isEmpty()) {
            Context context = this.context;
            return context.getString(R.string.no_adjust_setting_gesture, ContextDataProvider.toLowerCase(context.getString(R.string.shortcut_select_next_setting)));
        }
        return this.context.getString(R.string.select_setting_hint, str);
    }

    public final String getSelectorActionSettingsDescription() {
        return this.context.getString(R.string.title_pref_selector_actions);
    }

    public final boolean isSettingAvailable(Setting setting) {
        return getFilteredSettings().contains(setting);
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void newItemFocused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(AccessibilityNodeInfo accessibilityNodeInfo, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        FocusActionInfo focusActionInfo;
        NavigationAction navigationAction;
        resetActionMenuToDefault();
        if (spannableUtils$NonCopyableTextSpan instanceof Interpretation$AccessibilityFocused) {
            focusActionInfo = ((Interpretation$AccessibilityFocused) spannableUtils$NonCopyableTextSpan).focusActionInfo();
        } else {
            focusActionInfo = null;
        }
        if (focusActionInfo != null) {
            int i = focusActionInfo.sourceAction;
            if ((i == 2 || i == 5 || ((navigationAction = focusActionInfo.navigationAction) != null && navigationAction.originalNavigationGranularity == CursorGranularity.DEFAULT)) && accessibilityNodeInfo != null && !accessibilityNodeInfo.equals(this.lastFocusedNode)) {
                if (this.touchActive) {
                    this.lastFocusedNode = accessibilityNodeInfo;
                } else {
                    switchCurrentSetting(accessibilityNodeInfo);
                }
            }
        }
    }

    public final void selectPreviousOrNextSetting$ar$edu(Performance.EventId eventId, int i, boolean z) {
        Optional nextOrPreviousSetting = getNextOrPreviousSetting(z);
        if (nextOrPreviousSetting.isEmpty()) {
            return;
        }
        TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) this.analytics;
        if (talkBackAnalyticsImpl.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new VoiceActionMonitor$$ExternalSyntheticLambda0(talkBackAnalyticsLoggerWithClearcut, 12)).execute(new Void[0]);
            }
        }
        setCurrentSetting$ar$edu(eventId, (Setting) nextOrPreviousSetting.get(), i, true);
    }

    public final void selectSetting$ar$edu$ar$ds(Setting setting, int i) {
        if (setting != null && allowedSetting(setting)) {
            Context context = this.context;
            SharedPreferences sharedPreferences = this.prefs;
            String string = context.getString(R.string.pref_current_selector_setting_key);
            String string2 = sharedPreferences.getString(string, null);
            String string3 = this.context.getString(setting.prefValueResId);
            if (!string3.equals(string2)) {
                this.prefs.edit().putString(string, string3).apply();
            }
            Logger logger = Performance.DEFAULT_LOGGER;
            setCurrentSetting$ar$edu(null, setting, i, false);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0263, code lost:
    
        if (r12.equals(r13.getString(r0.getString(com.google.android.marvin.talkback.R.string.pref_shortcut_down_and_up_key), r0.getString(com.google.android.marvin.talkback.R.string.pref_shortcut_down_and_up_default))) != false) goto L71;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v51, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v54, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v55, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v56, types: [java.lang.CharSequence, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setCurrentSetting$ar$edu(com.google.android.accessibility.utils.Performance.EventId r10, com.google.android.accessibility.talkback.selector.SelectorController.Setting r11, int r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 682
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.selector.SelectorController.setCurrentSetting$ar$edu(com.google.android.accessibility.utils.Performance$EventId, com.google.android.accessibility.talkback.selector.SelectorController$Setting, int, boolean):void");
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void touchInteractionState(boolean z) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        this.touchActive = z;
        if (!z && (accessibilityNodeInfo = this.lastFocusedNode) != null) {
            switchCurrentSetting(accessibilityNodeInfo);
        }
    }

    private final boolean allowedSetting(Setting setting, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!this.prefs.getBoolean(this.context.getString(setting.prefKeyResId), this.context.getResources().getBoolean(setting.defaultValueResId))) {
            return false;
        }
        int ordinal = setting.ordinal();
        if (ordinal == 1) {
            return this.actorState.getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().allowSelectLanguage();
        }
        if (ordinal == 14) {
            return SpannableUtils$IdentifierSpan.isAtLeastT() || !this.actorState.getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging().isSelectionModeActive();
        }
        if (ordinal == 8) {
            return FeatureSupport.hasAccessibilityAudioStream(this.context);
        }
        if (ordinal == 9) {
            Optional findContextualSetting = findContextualSetting(Setting.ACTIONS);
            if (findContextualSetting.isEmpty()) {
                return false;
            }
            if (accessibilityNodeInfoCompat == null) {
                accessibilityNodeInfoCompat = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
            }
            return ((ContextualSetting) findContextualSetting.get()).isNodeSupportSetting(this.context, accessibilityNodeInfoCompat);
        }
        if (ordinal != 21) {
            if (ordinal != 22) {
                return true;
            }
            Optional findContextualSetting2 = findContextualSetting(Setting.GRANULARITY_TYPO);
            if (findContextualSetting2.isEmpty()) {
                return false;
            }
            return ((ContextualSetting) findContextualSetting2.get()).isNodeSupportSetting(this.context, accessibilityNodeInfoCompat);
        }
        Optional findContextualSetting3 = findContextualSetting(Setting.ADJUSTABLE_WIDGET);
        if (findContextualSetting3.isEmpty()) {
            return false;
        }
        if (accessibilityNodeInfoCompat == null) {
            accessibilityNodeInfoCompat = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
        }
        return ((ContextualSetting) findContextualSetting3.get()).isNodeSupportSetting(this.context, accessibilityNodeInfoCompat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Setting getCurrentSetting(Context context, SharedPreferences sharedPreferences) {
        Setting setting;
        String string = sharedPreferences.getString(context.getString(R.string.pref_current_selector_setting_key), context.getString(R.string.pref_selector_setting_default));
        Setting[] values = Setting.values();
        int length = values.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                setting = null;
                break;
            }
            setting = values[i];
            if (TextUtils.equals(context.getString(setting.prefValueResId), string)) {
                break;
            }
            i++;
        }
        return setting == null ? (Setting) SELECTOR_SETTINGS.get(0) : setting;
    }
}
