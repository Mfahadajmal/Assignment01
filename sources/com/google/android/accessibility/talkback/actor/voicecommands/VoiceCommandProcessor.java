package com.google.android.accessibility.talkback.actor.voicecommands;

import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.core.provider.CallbackWithHandler$2;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Interpretation$VoiceCommand;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.DimScreenActor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalyticsLoggerWithClearcut;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.menurules.RuleAction;
import com.google.android.accessibility.talkback.preference.base.VerbosityPrefFragment;
import com.google.android.accessibility.talkback.selector.SelectorController;
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
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VoiceCommandProcessor {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorState actorState;
    private final TalkBackAnalytics analytics;
    public boolean echoNotRecognizedTextEnabled;
    private List granularityCommandList;
    public HapticPatternParser$$ExternalSyntheticLambda1 interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public ListMenuManager menuManager;
    public Pipeline.FeedbackReturner pipeline;
    public SelectorController selectorController;
    private final TalkBackService service;
    private List verbosityCommandList;
    private static final int[] typeCommandResArray = {R.string.voice_commands_type, R.string.voice_commands_input, R.string.voice_commands_dictate, R.string.voice_commands_write, R.string.voice_commands_Spell};
    private static final int[] talkbackSettingCommandResArray = {R.string.voice_commands_talkback_settings, R.string.voice_commands_talkback_setting};
    private static final int[] finishSelectCommandResArray = {R.string.voice_commands_finish_select, R.string.voice_commands_finish_selection_mode, R.string.voice_commands_finish_selection, R.string.voice_commands_end_select};
    private static final int[] selectCommandResArray = {R.string.voice_commands_select, R.string.voice_commands_start_select, R.string.voice_commands_start_selection, R.string.voice_commands_start_selection_mode};
    private static final int[] languageCommandResArray = {R.string.voice_commands_language, R.string.voice_commands_languages};
    private static final int[] actionsCommandResArray = {R.string.title_custom_action, R.string.voice_commands_custom_actions, R.string.voice_commands_action};
    private static final int[] quickSettingCommandResArray = {R.string.voice_commands_quick_settings, R.string.voice_commands_quick_setting};
    private static final int[] showScreenCommandResArray = {R.string.shortcut_disable_dimming, R.string.voice_commands_brighten_screen, R.string.voice_commands_restore_screen, R.string.voice_commands_cancel_hide_screen};
    private static final int[] notificationsCommandResArray = {R.string.voice_commands_notification, R.string.voice_commands_notifications};
    private static final int[] hideScreenCommandResArray = {R.string.voice_commands_dim, R.string.voice_commands_darken};
    private static final int[] readFromNextCommandResArray = {R.string.shortcut_read_from_current, R.string.voice_commands_read_from_next};
    private static final int[] overviewCommandResArray = {R.string.voice_commands_overview, R.string.voice_commands_recent_apps, R.string.voice_commands_recent, R.string.voice_commands_recents};
    private static final int[] verbosityCommandArray = {R.string.voice_commands_verbosity_parameter, R.string.voice_commands_parameter_verbosity, R.string.voice_commands_change_verbosity_to_parameter};
    private static final int[] verbosityParameters = {R.string.pref_verbosity_preset_entry_high, R.string.pref_verbosity_preset_entry_custom, R.string.pref_verbosity_preset_entry_low, R.string.voice_commands_homophone_high_and_hi};
    private static final int[] granularityCommandArray = {R.string.voice_commands_navigation_by_parameter, R.string.voice_commands_parameter_navigation, R.string.voice_commands_parameter_granularity, R.string.voice_commands_read_by_parameter};
    private static final int[] granularityModeArray = {R.string.granularity_character, R.string.granularity_word, R.string.granularity_line, R.string.granularity_paragraph, R.string.granularity_web_heading, R.string.granularity_web_control, R.string.granularity_web_landmark, R.string.granularity_window, R.string.granularity_default};
    private static final int[] findCommandResArray = {R.string.voice_commands_find, R.string.voice_commands_search_for, R.string.voice_commands_search};

    public VoiceCommandProcessor(TalkBackService talkBackService, AccessibilityFocusMonitor accessibilityFocusMonitor, SelectorController selectorController, TalkBackAnalytics talkBackAnalytics) {
        this.service = talkBackService;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.selectorController = selectorController;
        this.analytics = talkBackAnalytics;
    }

    private final int contains(String str, int[] iArr) {
        if (str != null) {
            for (int i = 0; i < iArr.length; i++) {
                if (containsWord(str, iArr[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private final boolean containsWord(String str, int i) {
        String[] split = str.split("\\s|\\p{Punct}");
        if (equals(str, i)) {
            return true;
        }
        for (String str2 : split) {
            if (equals(str2, i)) {
                return true;
            }
        }
        return false;
    }

    private final void dimScreenVoiceCommand$ar$ds() {
        if (DimScreenActor.isSupported(this.service)) {
            sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_DIM_SCREEN);
        }
    }

    private final boolean equals(String str, int i) {
        return str.equals(this.service.getString(i).toLowerCase());
    }

    private static final String equals$ar$ds(String str, List list) {
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(((String) list.get(i)).toLowerCase())) {
                return str;
            }
        }
        return null;
    }

    private final List getCommandList(int[] iArr, int[] iArr2) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            for (int i2 : iArr2) {
                TalkBackService talkBackService = this.service;
                arrayList.add(talkBackService.getString(i, new Object[]{talkBackService.getString(i2)}));
            }
        }
        return arrayList;
    }

    private final AccessibilityNodeInfoCompat getEditTextFocus() {
        AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(true);
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityFocus) == 4) {
            return accessibilityFocus;
        }
        speakDelayed(this.service.getString(R.string.not_editable));
        return null;
    }

    private final void handleVoiceCommandRecognized(int i) {
        this.analytics.onVoiceCommandEvent(3);
        TalkBackAnalyticsImpl talkBackAnalyticsImpl = (TalkBackAnalyticsImpl) this.analytics;
        if (talkBackAnalyticsImpl.initialized) {
            TalkBackAnalyticsLoggerWithClearcut talkBackAnalyticsLoggerWithClearcut = talkBackAnalyticsImpl.talkBackAnalyticsLogger;
            if (talkBackAnalyticsLoggerWithClearcut.dbHelper != null) {
                new TalkBackAnalyticsLoggerWithClearcut.ActionTask(new CallbackWithHandler$2(talkBackAnalyticsLoggerWithClearcut, i, 12)).execute(new Void[0]);
            }
        }
    }

    private final CharSequence remainder(String str, int i) {
        return str.substring(this.service.getString(i).toLowerCase().length());
    }

    private final void sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action action, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        sendInterpretation$ar$ds$ec20acbd_0(action, accessibilityNodeInfoCompat, null);
    }

    private final boolean sendInterpretation$ar$ds$8eaa16db_0(Interpretation$VoiceCommand.Action action, CursorGranularity cursorGranularity) {
        boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$VoiceCommand(action, null, cursorGranularity, null), null);
        return input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    private final boolean sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action action) {
        return sendInterpretation$ar$ds$ec20acbd_0(action, null, null);
    }

    private final boolean sendInterpretation$ar$ds$ec20acbd_0(Interpretation$VoiceCommand.Action action, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CharSequence charSequence) {
        boolean input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.interpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(null, null, new Interpretation$VoiceCommand(action, accessibilityNodeInfoCompat, null, charSequence), null);
        return input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    private final void speakDelayed(String str) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 30;
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder speech = Feedback.speech(str, speakOptions);
        speech.setDelayMs$ar$ds(100);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, speech);
    }

    private final boolean startsWith(String str, int i) {
        return str.startsWith(this.service.getString(i).toLowerCase());
    }

    public final boolean handleSpeechCommand(String str) {
        boolean sendInterpretation$ar$ds$ec20acbd_0;
        CursorGranularity cursorGranularity;
        CursorGranularity cursorGranularity2;
        CursorGranularity cursorGranularity3;
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        LogUtils.i("VoiceCommandProcessor", "handleSpeechCommand() command=\"%s\"", str);
        Logger logger = Performance.DEFAULT_LOGGER;
        SelectorController.Setting setting = null;
        if (SpannableUtils$NonCopyableTextSpan.isGeminiVoiceCommandEnabled(this.service) && startsWith(str, R.string.voice_commands_gemini)) {
            final String substring = str.substring(this.service.getString(R.string.voice_commands_gemini).length());
            SpannableUtils$NonCopyableTextSpan.takeScreenshot(this.service, new ScreenCaptureController.CaptureListener() { // from class: com.google.android.accessibility.talkback.actor.voicecommands.VoiceCommandProcessor$$ExternalSyntheticLambda0
                @Override // com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController.CaptureListener
                public final void onScreenCaptureFinished(Bitmap bitmap, boolean z) {
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(VoiceCommandProcessor.this.pipeline, (Performance.EventId) null, Feedback.geminiRequest(-1, substring, bitmap));
                }
            });
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
            handleVoiceCommandRecognized(39);
            return true;
        }
        if (equals(str, android.R.string.selectAll)) {
            AccessibilityNodeInfoCompat editTextFocus = getEditTextFocus();
            if (editTextFocus != null) {
                sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_SELECT_ALL, editTextFocus);
            }
            handleVoiceCommandRecognized(1);
            return true;
        }
        if (equals(str, R.string.shortcut_enable_dimming)) {
            dimScreenVoiceCommand$ar$ds();
            handleVoiceCommandRecognized(2);
            return true;
        }
        if (equals(str, finishSelectCommandResArray) >= 0) {
            AccessibilityNodeInfoCompat editTextFocus2 = getEditTextFocus();
            if (editTextFocus2 != null) {
                sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_END_SELECT, editTextFocus2);
            }
            handleVoiceCommandRecognized(4);
            return true;
        }
        if (!FormFactorUtils.getInstance().isAndroidWear && (equals(str, R.string.voice_commands_screen_search) || equals(str, R.string.voice_commands_search_on_screen))) {
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.universalSearch$ar$edu(1));
            handleVoiceCommandRecognized(3);
            return true;
        }
        if (equals(str, selectCommandResArray) >= 0) {
            AccessibilityNodeInfoCompat editTextFocus3 = getEditTextFocus();
            if (editTextFocus3 != null) {
                sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_START_SELECT, editTextFocus3);
            }
            handleVoiceCommandRecognized(5);
            return true;
        }
        if (equals(str, actionsCommandResArray) >= 0) {
            AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(true);
            List menuItemsForNode = new RuleAction(this.pipeline, this.actorState, this.accessibilityFocusMonitor, this.analytics).getMenuItemsForNode(this.service, accessibilityFocus, true);
            if (accessibilityFocus != null && !menuItemsForNode.isEmpty()) {
                this.menuManager.showMenu$ar$edu(2, null);
            } else {
                this.menuManager.showMenu$ar$edu$a8c95a97_0(2, null, R.string.voice_commands_no_actions_feedback);
            }
            handleVoiceCommandRecognized(6);
            return true;
        }
        if (equals(str, R.string.voice_commands_next_heading)) {
            boolean supportsWebActions = WebInterfaceUtils.supportsWebActions(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
            Interpretation$VoiceCommand.Action action = Interpretation$VoiceCommand.Action.VOICE_COMMAND_NEXT_GRANULARITY;
            if (supportsWebActions) {
                cursorGranularity3 = CursorGranularity.WEB_HEADING;
            } else {
                cursorGranularity3 = null;
            }
            if (!sendInterpretation$ar$ds$8eaa16db_0(action, cursorGranularity3)) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                speakDelayed(this.service.getString(R.string.voice_commands_no_next_heading_feedback));
            }
            handleVoiceCommandRecognized(7);
            return true;
        }
        if (equals(str, R.string.voice_commands_next_control)) {
            boolean supportsWebActions2 = WebInterfaceUtils.supportsWebActions(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
            Interpretation$VoiceCommand.Action action2 = Interpretation$VoiceCommand.Action.VOICE_COMMAND_NEXT_GRANULARITY;
            if (supportsWebActions2) {
                cursorGranularity2 = CursorGranularity.WEB_CONTROL;
            } else {
                cursorGranularity2 = CursorGranularity.CONTROL;
            }
            if (!sendInterpretation$ar$ds$8eaa16db_0(action2, cursorGranularity2)) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                speakDelayed(this.service.getString(R.string.voice_commands_no_next_control_feedback));
            }
            handleVoiceCommandRecognized(8);
            return true;
        }
        if (equals(str, R.string.voice_commands_next_link)) {
            boolean supportsWebActions3 = WebInterfaceUtils.supportsWebActions(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
            Interpretation$VoiceCommand.Action action3 = Interpretation$VoiceCommand.Action.VOICE_COMMAND_NEXT_GRANULARITY;
            if (supportsWebActions3) {
                cursorGranularity = CursorGranularity.WEB_LINK;
            } else {
                cursorGranularity = CursorGranularity.LINK;
            }
            if (!sendInterpretation$ar$ds$8eaa16db_0(action3, cursorGranularity)) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                speakDelayed(this.service.getString(R.string.voice_commands_no_next_link_feedback));
            }
            handleVoiceCommandRecognized(9);
            return true;
        }
        if (this.verbosityCommandList == null) {
            this.verbosityCommandList = getCommandList(verbosityCommandArray, verbosityParameters);
        }
        String equals$ar$ds = equals$ar$ds(str, this.verbosityCommandList);
        if (equals$ar$ds != null) {
            int contains = contains(equals$ar$ds, verbosityParameters);
            if (!TextUtils.equals(SpannableUtils$IdentifierSpan.getDefaultLocale(), "en") || !containsWord(equals$ar$ds, R.string.voice_commands_homophone_high_and_hi)) {
                i = contains;
            }
            if (i >= 0) {
                SelectorController selectorController = this.selectorController;
                List asList = Arrays.asList(selectorController.context.getResources().getStringArray(R.array.pref_verbosity_preset_values));
                if (i < asList.size()) {
                    String string = selectorController.context.getString(R.string.pref_verbosity_preset_key);
                    int indexOf = asList.indexOf(selectorController.prefs.getString(string, selectorController.context.getString(R.string.pref_verbosity_preset_value_default)));
                    String str2 = (String) asList.get(i);
                    if (indexOf != i) {
                        selectorController.prefs.edit().putString(string, str2).apply();
                    }
                    selectorController.announceSetting(null, VerbosityPrefFragment.getVerbosityChangeAnnouncement(str2, selectorController.context), selectorController.getSelectSettingGestures());
                }
            }
            handleVoiceCommandRecognized(11);
            return true;
        }
        if (this.granularityCommandList == null) {
            this.granularityCommandList = getCommandList(granularityCommandArray, granularityModeArray);
        }
        String equals$ar$ds2 = equals$ar$ds(str, this.granularityCommandList);
        if (equals$ar$ds2 != null) {
            int[] iArr = granularityModeArray;
            int contains2 = contains(equals$ar$ds2, iArr);
            if (contains2 >= 0) {
                int i2 = iArr[contains2];
                ImmutableList immutableList = SelectorController.SELECTOR_SETTINGS;
                if (i2 == R.string.granularity_character) {
                    setting = SelectorController.Setting.GRANULARITY_CHARACTERS;
                } else if (i2 == R.string.granularity_word) {
                    setting = SelectorController.Setting.GRANULARITY_WORDS;
                } else if (i2 == R.string.granularity_line) {
                    setting = SelectorController.Setting.GRANULARITY_LINES;
                } else if (i2 == R.string.granularity_paragraph) {
                    setting = SelectorController.Setting.GRANULARITY_PARAGRAPHS;
                } else if (i2 == R.string.granularity_web_heading) {
                    setting = SelectorController.Setting.GRANULARITY_HEADINGS;
                } else if (i2 == R.string.granularity_web_control) {
                    setting = SelectorController.Setting.GRANULARITY_CONTROLS;
                } else if (i2 == R.string.granularity_web_landmark) {
                    setting = SelectorController.Setting.GRANULARITY_LANDMARKS;
                } else if (i2 == R.string.granularity_window) {
                    setting = SelectorController.Setting.GRANULARITY_WINDOWS;
                } else if (i2 == R.string.granularity_container) {
                    setting = SelectorController.Setting.GRANULARITY_CONTAINERS;
                } else if (i2 == R.string.granularity_default) {
                    setting = SelectorController.Setting.GRANULARITY_DEFAULT;
                }
                this.selectorController.selectSetting$ar$edu$ar$ds(setting, 3);
            }
            handleVoiceCommandRecognized(12);
            return true;
        }
        if (equals(str, R.string.voice_commands_next_landmark)) {
            if (!WebInterfaceUtils.supportsWebActions(this.accessibilityFocusMonitor.getAccessibilityFocus(false)) || !sendInterpretation$ar$ds$8eaa16db_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_NEXT_GRANULARITY, CursorGranularity.WEB_LANDMARK)) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                speakDelayed(this.service.getString(R.string.voice_commands_no_next_landmark_feedback));
            }
            handleVoiceCommandRecognized(10);
            return true;
        }
        if (equals(str, showScreenCommandResArray) >= 0) {
            if (DimScreenActor.isSupported(this.service)) {
                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_BRIGHTEN_SCREEN);
            }
            handleVoiceCommandRecognized(13);
            return true;
        }
        if (!equals(str, R.string.voice_commands_back) && !equals(str, R.string.voice_commands_go_back)) {
            if (equals(str, R.string.voice_commands_increase_speech_rate)) {
                this.selectorController.changeSpeechRate(null, true);
                handleVoiceCommandRecognized(15);
                return true;
            }
            if (equals(str, R.string.voice_commands_decrease_speech_rate)) {
                this.selectorController.changeSpeechRate(null, false);
                handleVoiceCommandRecognized(16);
                return true;
            }
            int startsWith = startsWith(str, findCommandResArray);
            if (startsWith >= 0) {
                CharSequence remainder = remainder(str, startsWith);
                if (TextUtils.isEmpty(remainder)) {
                    sendInterpretation$ar$ds$ec20acbd_0 = sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_REPEAT_SEARCH);
                } else {
                    sendInterpretation$ar$ds$ec20acbd_0 = sendInterpretation$ar$ds$ec20acbd_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_FIND, null, remainder);
                }
                if (!sendInterpretation$ar$ds$ec20acbd_0) {
                    speakDelayed(this.service.getString(R.string.msg_no_matches));
                }
                handleVoiceCommandRecognized(17);
                return true;
            }
            int startsWith2 = startsWith(str, typeCommandResArray);
            if (startsWith2 >= 0) {
                AccessibilityNodeInfoCompat editTextFocus4 = getEditTextFocus();
                if (editTextFocus4 != null) {
                    CharSequence remainder2 = remainder(str, startsWith2);
                    if (!TextUtils.isEmpty(remainder2)) {
                        sendInterpretation$ar$ds$ec20acbd_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_INSERT, editTextFocus4, remainder2);
                    }
                }
                handleVoiceCommandRecognized(18);
                return true;
            }
            if (startsWith(str, R.string.voice_commands_label)) {
                CharSequence trimText = SpannableUtils$IdentifierSpan.trimText(remainder(str, R.string.voice_commands_label));
                AccessibilityNodeInfoCompat accessibilityFocus2 = this.accessibilityFocusMonitor.getAccessibilityFocus(false);
                if (accessibilityFocus2 != null && !TextUtils.isEmpty(trimText)) {
                    if (sendInterpretation$ar$ds$ec20acbd_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_LABEL, accessibilityFocus2, trimText)) {
                        String string2 = this.service.getString(R.string.voice_commands_label_saved);
                        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                        Feedback.Part.Builder speech = Feedback.speech(string2, new SpeechController.SpeakOptions());
                        speech.setDelayMs$ar$ds(500);
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, speech);
                    } else {
                        speakDelayed(this.service.getString(R.string.voice_commands_cannot_label_feedback));
                    }
                }
                handleVoiceCommandRecognized(19);
                return true;
            }
            if (startsWith(str, readFromNextCommandResArray) >= 0) {
                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_START_AT_CURSOR);
                handleVoiceCommandRecognized(20);
                return true;
            }
            if (startsWith(str, R.string.shortcut_read_from_top)) {
                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_START_AT_TOP);
                handleVoiceCommandRecognized(21);
                return true;
            }
            if (startsWith(str, R.string.title_copy_last_spoken_phrase)) {
                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_COPY_LAST_SPOKEN_UTTERANCE);
                handleVoiceCommandRecognized(22);
                return true;
            }
            if (contains(str, quickSettingCommandResArray) >= 0) {
                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_QUICK_SETTINGS);
                handleVoiceCommandRecognized(23);
                return true;
            }
            if (contains(str, talkbackSettingCommandResArray) >= 0 && SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(this.service)) {
                Intent intent = new Intent(this.service, (Class<?>) TalkBackPreferencesActivity.class);
                intent.addFlags(268435456);
                this.service.startActivity(intent);
                handleVoiceCommandRecognized(24);
                return true;
            }
            if (contains(str, hideScreenCommandResArray) >= 0) {
                dimScreenVoiceCommand$ar$ds();
                handleVoiceCommandRecognized(2);
                return true;
            }
            if (containsWord(str, R.string.voice_commands_copy)) {
                AccessibilityNodeInfoCompat editTextFocus5 = getEditTextFocus();
                if (editTextFocus5 != null) {
                    sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_COPY, editTextFocus5);
                }
                handleVoiceCommandRecognized(25);
                return true;
            }
            if (containsWord(str, R.string.voice_commands_paste)) {
                AccessibilityNodeInfoCompat editTextFocus6 = getEditTextFocus();
                if (editTextFocus6 != null) {
                    sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_PASTE, editTextFocus6);
                }
                handleVoiceCommandRecognized(26);
                return true;
            }
            if (containsWord(str, R.string.voice_commands_delete)) {
                AccessibilityNodeInfoCompat editTextFocus7 = getEditTextFocus();
                if (editTextFocus7 != null) {
                    sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_DELETE, editTextFocus7);
                }
                handleVoiceCommandRecognized(28);
                return true;
            }
            if (!containsWord(str, R.string.voice_commands_first) && !containsWord(str, R.string.voice_commands_top)) {
                if (!containsWord(str, R.string.voice_commands_last) && !containsWord(str, R.string.voice_commands_bottom)) {
                    if (contains(str, languageCommandResArray) >= 0) {
                        this.menuManager.showMenu$ar$edu(3, null);
                        handleVoiceCommandRecognized(31);
                        return true;
                    }
                    if (contains(str, notificationsCommandResArray) >= 0) {
                        if (!sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_NOTIFICATIONS)) {
                            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                        }
                        handleVoiceCommandRecognized(32);
                        return true;
                    }
                    if (containsWord(str, R.string.voice_commands_apps) && FeatureSupport.supportGetSystemActions(this.service) && !containsWord(str, R.string.voice_commands_recent) && !containsWord(str, R.string.voice_commands_recents)) {
                        if (!sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_ALL_APPS)) {
                            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                        }
                        handleVoiceCommandRecognized(34);
                        return true;
                    }
                    if (contains(str, overviewCommandResArray) >= 0) {
                        if (!sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_RECENT)) {
                            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                        }
                        handleVoiceCommandRecognized(33);
                        return true;
                    }
                    if (containsWord(str, R.string.voice_commands_assistant)) {
                        this.service.startActivity(new Intent("android.intent.action.VOICE_COMMAND").setFlags(268435456));
                        handleVoiceCommandRecognized(37);
                        return true;
                    }
                    if (!containsWord(str, R.string.voice_commands_home) && !containsWord(str, R.string.voice_commands_desktop)) {
                        if (!containsWord(str, R.string.voice_commands_stop) && !containsWord(str, R.string.voice_commands_quit) && !containsWord(str, R.string.voice_commands_quiet) && !containsWord(str, R.string.voice_commands_pause) && !equals(str, R.string.voice_commands_nevermind) && !equals(str, R.string.voice_commands_shut_up)) {
                            if (containsWord(str, R.string.voice_commands_faster)) {
                                this.selectorController.changeSpeechRate(null, true);
                                handleVoiceCommandRecognized(15);
                                return true;
                            }
                            if (containsWord(str, R.string.voice_commands_slower)) {
                                this.selectorController.changeSpeechRate(null, false);
                                handleVoiceCommandRecognized(16);
                                return true;
                            }
                            if (containsWord(str, R.string.voice_commands_cut)) {
                                AccessibilityNodeInfoCompat editTextFocus8 = getEditTextFocus();
                                if (editTextFocus8 != null) {
                                    sendInterpretation$ar$ds(Interpretation$VoiceCommand.Action.VOICE_COMMAND_CUT, editTextFocus8);
                                }
                                handleVoiceCommandRecognized(27);
                                return true;
                            }
                            if ((containsWord(str, R.string.voice_commands_what) && containsWord(str, R.string.voice_commands_say)) || containsWord(str, R.string.title_pref_help)) {
                                sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_SHOW_COMMAND_LIST);
                                handleVoiceCommandRecognized(38);
                                return true;
                            }
                            if (this.echoNotRecognizedTextEnabled) {
                                speakDelayed(this.service.getString(R.string.voice_commands_echo_feedback_not_recognized, new Object[]{str}));
                            } else {
                                TalkBackService talkBackService = this.service;
                                speakDelayed(talkBackService.getString(R.string.voice_commands_partial_result, new Object[]{talkBackService.getString(R.string.title_pref_help)}));
                            }
                            this.analytics.onVoiceCommandEvent(4);
                            return false;
                        }
                        handleVoiceCommandRecognized(36);
                        return true;
                    }
                    sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_HOME);
                    handleVoiceCommandRecognized(35);
                    return true;
                }
                if (!sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_LAST)) {
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                }
                handleVoiceCommandRecognized(30);
                return true;
            }
            if (!sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_FIRST)) {
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
            }
            handleVoiceCommandRecognized(29);
            return true;
        }
        sendInterpretation$ar$ds$b830b2c1_0(Interpretation$VoiceCommand.Action.VOICE_COMMAND_BACK);
        handleVoiceCommandRecognized(14);
        return true;
    }

    private final int equals(String str, int[] iArr) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (str.equals(this.service.getString(iArr[i]).toLowerCase())) {
                return iArr[i];
            }
        }
        return -1;
    }

    private final int startsWith(String str, int[] iArr) {
        if (str == null) {
            return -1;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (str.startsWith(this.service.getString(iArr[i]).toLowerCase())) {
                return iArr[i];
            }
        }
        return -1;
    }
}
