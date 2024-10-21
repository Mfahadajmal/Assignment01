package com.google.android.accessibility.talkback.gesture;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.talkback.compositor.GestureShortcutProvider;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.GestureSetConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GestureShortcutMapping implements GestureShortcutProvider {
    private static final String TAG = "GestureShortcutMapping";
    public final String actionGestureUnsupported;
    public final String actionNextReadingMenuSetting;
    protected final String actionReadingMenuDown;
    protected final String actionReadingMenuUp;
    public final String actionShortcut;
    protected final String actionTalkbackContextMenu;
    public final String actionUnassigned;
    public final Context context;
    public int currentGestureSet;
    public boolean gestureSetEnabled;
    public final String mediaControlShortcut;
    public final String nextWindowShortcut;
    public final SharedPreferences prefs;
    public final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
    public int previousScreenLayout = 0;
    public final List actionToGesture = new ArrayList();
    public final List gestureIdToActionKey = new ArrayList();
    public HashMap fingerprintGestureIdToActionKey = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TalkBackGesture {
        SWIPE_UP(1, 1, R.string.pref_shortcut_up_key, R.string.pref_shortcut_up_default),
        SWIPE_DOWN(2, 1, R.string.pref_shortcut_down_key, R.string.pref_shortcut_down_default),
        SWIPE_LEFT(3, 1, 1, R.string.pref_shortcut_left_key, R.string.pref_shortcut_left_default),
        SWIPE_LEFT_RTL(3, 1, 2, R.string.pref_shortcut_right_key, R.string.pref_shortcut_right_default),
        SWIPE_RIGHT(4, 1, 1, R.string.pref_shortcut_right_key, R.string.pref_shortcut_right_default),
        SWIPE_RIGHT_RTL(4, 1, 2, R.string.pref_shortcut_left_key, R.string.pref_shortcut_left_default),
        SWIPE_UP_AND_DOWN(7, 1, R.string.pref_shortcut_up_and_down_key, R.string.pref_shortcut_up_and_down_default),
        SWIPE_DOWN_AND_UP(8, 1, R.string.pref_shortcut_down_and_up_key, R.string.pref_shortcut_down_and_up_default),
        SWIPE_LEFT_AND_RIGHT(5, 1, 1, R.string.pref_shortcut_left_and_right_key, R.string.pref_shortcut_left_and_right_default),
        SWIPE_LEFT_AND_RIGHT_RTL(5, 1, 2, R.string.pref_shortcut_right_and_left_key, R.string.pref_shortcut_right_and_left_default),
        SWIPE_RIGHT_AND_LEFT(6, 1, 1, R.string.pref_shortcut_right_and_left_key, R.string.pref_shortcut_right_and_left_default),
        SWIPE_RIGHT_AND_LEFT_RTL(6, 1, 2, R.string.pref_shortcut_left_and_right_key, R.string.pref_shortcut_left_and_right_default),
        SWIPE_UP_AND_LEFT(13, 1, R.string.pref_shortcut_up_and_left_key, R.string.pref_shortcut_up_and_left_default),
        SWIPE_UP_AND_RIGHT(14, 1, R.string.pref_shortcut_up_and_right_key, R.string.pref_shortcut_up_and_right_default),
        SWIPE_DOWN_AND_LEFT(15, 1, R.string.pref_shortcut_down_and_left_key, R.string.pref_shortcut_down_and_left_default),
        SWIPE_DOWN_AND_RIGHT(16, 1, R.string.pref_shortcut_down_and_right_key, R.string.pref_shortcut_down_and_right_default),
        SWIPE_RIGHT_AND_DOWN(12, 1, R.string.pref_shortcut_right_and_down_key, R.string.pref_shortcut_right_and_down_default),
        SWIPE_RIGHT_AND_UP(11, 1, R.string.pref_shortcut_right_and_up_key, R.string.pref_shortcut_right_and_up_default),
        SWIPE_LEFT_AND_DOWN(10, 1, R.string.pref_shortcut_left_and_down_key, R.string.pref_shortcut_left_and_down_default),
        SWIPE_LEFT_AND_UP(9, 1, R.string.pref_shortcut_left_and_up_key, R.string.pref_shortcut_left_and_up_default),
        ONE_FINGER_DOUBLE_TAP(17, 0, R.string.pref_shortcut_1finger_2tap_key, R.string.pref_shortcut_1finger_2tap_default),
        ONE_FINGER_DOUBLE_TAP_AND_HOLD(18, 0, R.string.pref_shortcut_1finger_2tap_hold_key, R.string.pref_shortcut_1finger_2tap_hold_default),
        TWO_FINGER_SINGLE_TAP(19, 0, R.string.pref_shortcut_2finger_1tap_key, R.string.pref_shortcut_2finger_1tap_default),
        TWO_FINGER_DOUBLE_TAP(20, 0, R.string.pref_shortcut_2finger_2tap_key, R.string.pref_shortcut_2finger_2tap_default),
        TWO_FINGER_TRIPLE_TAP(21, 0, R.string.pref_shortcut_2finger_3tap_key, R.string.pref_shortcut_2finger_3tap_default),
        THREE_FINGER_SINGLE_TAP(22, 0, R.string.pref_shortcut_3finger_1tap_key, R.string.pref_shortcut_3finger_1tap_default),
        THREE_FINGER_DOUBLE_TAP(23, 0, R.string.pref_shortcut_3finger_2tap_key, R.string.pref_shortcut_3finger_2tap_default),
        THREE_FINGER_TRIPLE_TAP(24, 0, R.string.pref_shortcut_3finger_3tap_key, R.string.pref_shortcut_3finger_3tap_default),
        THREE_FINGER_TRIPLE_TAP_AND_HOLD(45, 0, R.string.pref_shortcut_3finger_3tap_hold_key, R.string.pref_shortcut_3finger_3tap_hold_default),
        FOUR_FINGER_SINGLE_TAP(37, 0, R.string.pref_shortcut_4finger_1tap_key, R.string.pref_shortcut_4finger_1tap_default),
        FOUR_FINGER_DOUBLE_TAP(38, 0, R.string.pref_shortcut_4finger_2tap_key, R.string.pref_shortcut_4finger_2tap_default),
        FOUR_FINGER_TRIPLE_TAP(39, 0, R.string.pref_shortcut_4finger_3tap_key, R.string.pref_shortcut_4finger_3tap_default),
        TWO_FINGER_SWIPE_UP(25, 0, R.string.pref_shortcut_2finger_swipe_up_key, R.string.pref_shortcut_2finger_swipe_up_default),
        TWO_FINGER_SWIPE_DOWN(26, 0, R.string.pref_shortcut_2finger_swipe_down_key, R.string.pref_shortcut_2finger_swipe_down_default),
        TWO_FINGER_SWIPE_LEFT(27, 0, R.string.pref_shortcut_2finger_swipe_left_key, R.string.pref_shortcut_2finger_swipe_left_default),
        TWO_FINGER_SWIPE_RIGHT(28, 0, R.string.pref_shortcut_2finger_swipe_right_key, R.string.pref_shortcut_2finger_swipe_right_default),
        THREE_FINGER_SWIPE_UP(29, 0, R.string.pref_shortcut_3finger_swipe_up_key, R.string.pref_shortcut_3finger_swipe_up_default),
        THREE_FINGER_SWIPE_DOWN(30, 0, R.string.pref_shortcut_3finger_swipe_down_key, R.string.pref_shortcut_3finger_swipe_down_default),
        THREE_FINGER_SWIPE_LEFT(31, 0, R.string.pref_shortcut_3finger_swipe_left_key, R.string.pref_shortcut_3finger_swipe_left_default),
        THREE_FINGER_SWIPE_RIGHT(32, 0, R.string.pref_shortcut_3finger_swipe_right_key, R.string.pref_shortcut_3finger_swipe_right_default),
        FOUR_FINGER_SWIPE_UP(33, 0, R.string.pref_shortcut_4finger_swipe_up_key, R.string.pref_shortcut_4finger_swipe_up_default),
        FOUR_FINGER_SWIPE_DOWN(34, 0, R.string.pref_shortcut_4finger_swipe_down_key, R.string.pref_shortcut_4finger_swipe_down_default),
        FOUR_FINGER_SWIPE_LEFT(35, 0, R.string.pref_shortcut_4finger_swipe_left_key, R.string.pref_shortcut_4finger_swipe_left_default),
        FOUR_FINGER_SWIPE_RIGHT(36, 0, R.string.pref_shortcut_4finger_swipe_right_key, R.string.pref_shortcut_4finger_swipe_right_default),
        TWO_FINGER_DOUBLE_TAP_AND_HOLD(40, 0, R.string.pref_shortcut_2finger_2tap_hold_key, R.string.pref_shortcut_2finger_2tap_hold_default),
        THREE_FINGER_TAP_AND_HOLD(44, 0, R.string.pref_shortcut_3finger_1tap_hold_key, R.string.pref_shortcut_3finger_1tap_hold_default),
        THREE_FINGER_DOUBLE_TAP_AND_HOLD(41, 0, R.string.pref_shortcut_3finger_2tap_hold_key, R.string.pref_shortcut_3finger_2tap_hold_default),
        FOUR_FINGER_DOUBLE_TAP_AND_HOLD(42, 0, R.string.pref_shortcut_4finger_2tap_hold_key, R.string.pref_shortcut_4finger_2tap_hold_default),
        TWO_FINGER_TRIPLE_TAP_AND_HOLD(43, 0, R.string.pref_shortcut_2finger_3tap_hold_key, R.string.pref_shortcut_2finger_3tap_hold_default),
        FINGERPRINT_SWIPE_UP(4, 2, R.string.pref_shortcut_fingerprint_up_key, R.string.pref_shortcut_fingerprint_up_default),
        FINGERPRINT_SWIPE_DOWN(8, 2, R.string.pref_shortcut_fingerprint_down_key, R.string.pref_shortcut_fingerprint_down_default),
        FINGERPRINT_SWIPE_LEFT(2, 2, R.string.pref_shortcut_fingerprint_left_key, R.string.pref_shortcut_fingerprint_left_default),
        FINGERPRINT_SWIPE_RIGHT(1, 2, R.string.pref_shortcut_fingerprint_right_key, R.string.pref_shortcut_fingerprint_right_default);

        final int defaultActionId;
        public final int gestureId;
        public final int gestureType;
        final int keyId;
        final int rtlType;

        TalkBackGesture(int i, int i2, int i3, int i4) {
            this(i, i2, 0, i3, i4);
        }

        TalkBackGesture(int i, int i2, int i3, int i4, int i5) {
            this.gestureId = i;
            this.gestureType = i2;
            this.rtlType = i3;
            this.keyId = i4;
            this.defaultActionId = i5;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TalkbackAction {
        UNASSIGNED_ACTION(-1, -1),
        PERFORM_CLICK(R.string.shortcut_value_perform_click_action, R.string.shortcut_perform_click_action),
        PERFORM_LONG_CLICK(R.string.shortcut_value_perform_click_action, R.string.shortcut_perform_long_click_action),
        PREVIOUS(R.string.shortcut_value_previous, R.string.shortcut_previous),
        NEXT(R.string.shortcut_value_next, R.string.shortcut_next),
        FIRST_IN_SCREEN(R.string.shortcut_value_first_in_screen, R.string.shortcut_first_in_screen),
        LAST_IN_SCREEN(R.string.shortcut_value_last_in_screen, R.string.shortcut_last_in_screen),
        PREV_CONTAINER(R.string.shortcut_value_prev_container, R.string.shortcut_prev_container),
        NEXT_CONTAINER(R.string.shortcut_value_next_container, R.string.shortcut_next_container),
        PREVIOUS_WINDOW(R.string.shortcut_value_previous_window, R.string.shortcut_previous_window),
        NEXT_WINDOW(R.string.shortcut_value_next_window, R.string.shortcut_next_window),
        SCROLL_BACK(R.string.shortcut_value_scroll_back, R.string.shortcut_scroll_back),
        SCROLL_FORWARD(R.string.shortcut_value_scroll_forward, R.string.shortcut_scroll_forward),
        SCROLL_UP(R.string.shortcut_value_scroll_up, R.string.shortcut_scroll_up),
        SCROLL_DOWN(R.string.shortcut_value_scroll_down, R.string.shortcut_scroll_down),
        SCROLL_LEFT(R.string.shortcut_value_scroll_left, R.string.shortcut_scroll_left),
        SCROLL_RIGHT(R.string.shortcut_value_scroll_right, R.string.shortcut_scroll_right),
        HOME(R.string.shortcut_value_home, R.string.shortcut_home),
        BACK(R.string.shortcut_value_back, R.string.shortcut_back),
        OVERVIEW(R.string.shortcut_value_overview, R.string.shortcut_overview),
        NOTIFICATIONS(R.string.shortcut_value_notifications, R.string.shortcut_notifications),
        QUICK_SETTINGS(R.string.shortcut_value_quick_settings, R.string.shortcut_quick_settings),
        ALL_APPS(R.string.shortcut_value_all_apps, R.string.shortcut_all_apps),
        A11Y_BUTTON(R.string.shortcut_value_a11y_button, R.string.shortcut_a11y_button),
        A11Y_BUTTON_LONG_PRESS(R.string.shortcut_value_a11y_button_long_press, R.string.shortcut_a11y_button_long_press),
        READ_FROM_TOP(R.string.shortcut_value_read_from_top, R.string.shortcut_read_from_top),
        READ_FROM_CURRENT(R.string.shortcut_value_read_from_current, R.string.shortcut_read_from_current),
        PAUSE_OR_RESUME_FEEDBACK(R.string.shortcut_value_pause_or_resume_feedback, R.string.shortcut_pause_or_resume_feedback),
        TOGGLE_VOICE_FEEDBACK(R.string.shortcut_value_toggle_voice_feedback, R.string.shortcut_toggle_voice_feedback),
        SHOW_LANGUAGE_OPTIONS(R.string.shortcut_value_show_language_options, R.string.shortcut_show_language_options),
        TALKBACK_BREAKOUT(R.string.shortcut_value_talkback_breakout, R.string.shortcut_talkback_breakout),
        SELECT_PREVIOUS_SETTING(R.string.shortcut_value_select_previous_setting, R.string.shortcut_select_previous_setting),
        SELECT_NEXT_SETTING(R.string.shortcut_value_select_next_setting, R.string.shortcut_select_next_setting),
        SELECTED_SETTING_PREVIOUS_ACTION(R.string.shortcut_value_selected_setting_previous_action, R.string.shortcut_selected_setting_previous_action),
        SELECTED_SETTING_NEXT_ACTION(R.string.shortcut_value_selected_setting_next_action, R.string.shortcut_selected_setting_next_action),
        START_SELECTION_MODE(R.string.shortcut_value_start_selection_mode, R.string.title_edittext_breakout_start_selection_mode),
        MOVE_CURSOR_TO_BEGINNING(R.string.shortcut_value_move_cursor_to_beginning, R.string.title_edittext_breakout_move_to_beginning),
        MOVE_CURSOR_TO_END(R.string.shortcut_value_move_cursor_to_end, R.string.title_edittext_breakout_move_to_end),
        SELECT_ALL(R.string.shortcut_value_select_all, android.R.string.selectAll),
        COPY(R.string.shortcut_value_copy, android.R.string.copy),
        CUT(R.string.shortcut_value_cut, android.R.string.cut),
        PASTE(R.string.shortcut_value_paste, android.R.string.paste),
        COPY_LAST_SPOKEN_UTTERANCE(R.string.shortcut_value_copy_last_spoken_phrase, R.string.title_copy_last_spoken_phrase),
        BRAILLE_KEYBOARD(R.string.shortcut_value_braille_keyboard, R.string.shortcut_braille_keyboard),
        MEDIA_CONTROL(R.string.shortcut_value_media_control, R.string.shortcut_media_control),
        INCREASE_VOLUME(R.string.shortcut_value_increase_volume, R.string.shortcut_increase_volume),
        DECREASE_VOLUME(R.string.shortcut_value_decrease_volume, R.string.shortcut_decrease_volume),
        VOICE_COMMANDS(R.string.shortcut_value_voice_commands, R.string.shortcut_voice_commands),
        SCREEN_SEARCH(R.string.shortcut_value_screen_search, R.string.title_show_screen_search),
        SHOW_HIDE_SCREEN(R.string.shortcut_value_show_hide_screen, R.string.title_show_hide_screen),
        PASS_THROUGH_NEXT_GESTURE(R.string.shortcut_value_pass_through_next_gesture, R.string.shortcut_pass_through_next),
        PRINT_NODE_TREE(R.string.shortcut_value_print_node_tree, R.string.shortcut_print_node_tree),
        PRINT_PERFORMANCE_STATS(R.string.shortcut_value_print_performance_stats, R.string.shortcut_print_performance_stats),
        SHOW_CUSTOM_ACTIONS(R.string.shortcut_value_show_custom_actions, R.string.shortcut_show_custom_actions),
        NAVIGATE_BRAILLE_SETTINGS(R.string.shortcut_value_braille_display_settings, R.string.shortcut_braille_display_settings),
        TUTORIAL(R.string.shortcut_value_tutorial, R.string.shortcut_tutorial),
        PRACTICE_GESTURE(R.string.shortcut_value_practice_gestures, R.string.shortcut_practice_gestures),
        REPORT_GESTURE(R.string.shortcut_value_report_gesture, R.string.shortcut_report_gesture),
        TOGGLE_BRAILLE_DISPLAY_ON_OFF(R.string.shortcut_value_toggle_braille_display, R.string.shortcut_toggle_braille_display),
        DESCRIBE_IMAGE(R.string.shortcut_value_describe_image, R.string.title_image_caption);

        final int actionKeyResId;
        final int actionNameResId;

        TalkbackAction(int i, int i2) {
            this.actionKeyResId = i;
            this.actionNameResId = i2;
        }
    }

    public GestureShortcutMapping(Context context) {
        OverlayDisplay.AnonymousClass1 anonymousClass1 = new OverlayDisplay.AnonymousClass1(this, 14, null);
        this.sharedPreferenceChangeListener = anonymousClass1;
        this.context = context;
        this.actionGestureUnsupported = context.getString(R.string.shortcut_value_unsupported);
        this.actionUnassigned = context.getString(R.string.shortcut_value_unassigned);
        this.actionTalkbackContextMenu = context.getString(R.string.shortcut_value_talkback_breakout);
        this.actionNextReadingMenuSetting = context.getString(R.string.shortcut_value_select_next_setting);
        this.actionReadingMenuUp = context.getString(R.string.shortcut_value_selected_setting_previous_action);
        this.actionReadingMenuDown = context.getString(R.string.shortcut_value_selected_setting_next_action);
        this.actionShortcut = context.getString(R.string.shortcut_value_show_custom_actions);
        this.nextWindowShortcut = context.getString(R.string.shortcut_value_next_window);
        this.mediaControlShortcut = context.getString(R.string.shortcut_value_media_control);
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.prefs = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(anonymousClass1);
        loadGestureIdToActionKeyMap();
        boolean isGestureSetEnabled$ar$ds = isGestureSetEnabled$ar$ds(context, sharedPreferences);
        this.gestureSetEnabled = isGestureSetEnabled$ar$ds;
        this.currentGestureSet = isGestureSetEnabled$ar$ds ? SpannableUtils$IdentifierSpan.getIntFromStringPref(sharedPreferences, context.getResources(), R.string.pref_gesture_set_key, R.string.pref_gesture_set_value_default) : 0;
    }

    public static String getActionString(Context context, String str) {
        for (TalkbackAction talkbackAction : TalkbackAction.values()) {
            int i = talkbackAction.actionKeyResId;
            if (i != -1 && TextUtils.equals(context.getString(i), str)) {
                return context.getString(talkbackAction.actionNameResId);
            }
        }
        return context.getString(R.string.shortcut_unassigned);
    }

    public static String getFingerprintGestureString(Context context, int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 8) {
                        return null;
                    }
                    return context.getString(R.string.title_pref_shortcut_fingerprint_down);
                }
                return context.getString(R.string.title_pref_shortcut_fingerprint_up);
            }
            return context.getString(R.string.title_pref_shortcut_fingerprint_left);
        }
        return context.getString(R.string.title_pref_shortcut_fingerprint_right);
    }

    public static String getGestureString(Context context, int i) {
        switch (i) {
            case -3:
                return context.getString(R.string.shortcut_value_split_typing);
            case -2:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_touch_explore);
                }
                return null;
            case -1:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_pass_through);
                }
                return null;
            case 0:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_unknown);
                }
                return null;
            case 1:
                return context.getString(R.string.title_pref_shortcut_up);
            case 2:
                return context.getString(R.string.title_pref_shortcut_down);
            case 3:
                return context.getString(R.string.title_pref_shortcut_left);
            case 4:
                return context.getString(R.string.title_pref_shortcut_right);
            case 5:
                return context.getString(R.string.title_pref_shortcut_left_and_right);
            case 6:
                return context.getString(R.string.title_pref_shortcut_right_and_left);
            case 7:
                return context.getString(R.string.title_pref_shortcut_up_and_down);
            case 8:
                return context.getString(R.string.title_pref_shortcut_down_and_up);
            case 9:
                return context.getString(R.string.title_pref_shortcut_left_and_up);
            case 10:
                return context.getString(R.string.title_pref_shortcut_left_and_down);
            case 11:
                return context.getString(R.string.title_pref_shortcut_right_and_up);
            case 12:
                return context.getString(R.string.title_pref_shortcut_right_and_down);
            case 13:
                return context.getString(R.string.title_pref_shortcut_up_and_left);
            case 14:
                return context.getString(R.string.title_pref_shortcut_up_and_right);
            case 15:
                return context.getString(R.string.title_pref_shortcut_down_and_left);
            case 16:
                return context.getString(R.string.title_pref_shortcut_down_and_right);
            case 17:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_double_tap);
                }
                return null;
            case 18:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_double_tap_and_hold);
                }
                return null;
            case 19:
                return context.getString(R.string.title_pref_shortcut_2finger_1tap);
            case 20:
                return context.getString(R.string.title_pref_shortcut_2finger_2tap);
            case 21:
                return context.getString(R.string.title_pref_shortcut_2finger_3tap);
            case 22:
                return context.getString(R.string.title_pref_shortcut_3finger_1tap);
            case 23:
                return context.getString(R.string.title_pref_shortcut_3finger_2tap);
            case 24:
                return context.getString(R.string.title_pref_shortcut_3finger_3tap);
            case 25:
                return context.getString(R.string.title_pref_shortcut_2finger_swipe_up);
            case 26:
                return context.getString(R.string.title_pref_shortcut_2finger_swipe_down);
            case 27:
                return context.getString(R.string.title_pref_shortcut_2finger_swipe_left);
            case 28:
                return context.getString(R.string.title_pref_shortcut_2finger_swipe_right);
            case 29:
                return context.getString(R.string.title_pref_shortcut_3finger_swipe_up);
            case 30:
                return context.getString(R.string.title_pref_shortcut_3finger_swipe_down);
            case 31:
                return context.getString(R.string.title_pref_shortcut_3finger_swipe_left);
            case 32:
                return context.getString(R.string.title_pref_shortcut_3finger_swipe_right);
            case 33:
                return context.getString(R.string.title_pref_shortcut_4finger_swipe_up);
            case 34:
                return context.getString(R.string.title_pref_shortcut_4finger_swipe_down);
            case 35:
                return context.getString(R.string.title_pref_shortcut_4finger_swipe_left);
            case 36:
                return context.getString(R.string.title_pref_shortcut_4finger_swipe_right);
            case 37:
                return context.getString(R.string.title_pref_shortcut_4finger_1tap);
            case 38:
                return context.getString(R.string.title_pref_shortcut_4finger_2tap);
            case 39:
                return context.getString(R.string.title_pref_shortcut_4finger_3tap);
            case 40:
                return context.getString(R.string.title_pref_shortcut_2finger_2tap_hold);
            case 41:
                return context.getString(R.string.title_pref_shortcut_3finger_2tap_hold);
            case 42:
                return context.getString(R.string.title_pref_shortcut_4finger_2tap_hold);
            case 43:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_2finger_3tap_hold);
                }
                return null;
            case 44:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_3finger_tap_hold);
                }
                return null;
            case 45:
                if (FeatureSupport.supportGestureMotionEvents()) {
                    return context.getString(R.string.gesture_name_3finger_3tap_hold);
                }
                return null;
            default:
                return null;
        }
    }

    public static String getPrefKeyWithGestureSet(String str, int i) {
        if (i < 0 || i >= 2) {
            i = 0;
        }
        int indexOf = str.indexOf("-");
        if (i == 0) {
            if (indexOf != -1) {
                return str.substring(0, indexOf);
            }
            return str;
        }
        if (indexOf == -1) {
            return str + "-1";
        }
        return str.substring(0, indexOf + 1) + 1;
    }

    public static boolean isGestureSetEnabled$ar$ds(Context context, SharedPreferences sharedPreferences) {
        if (SpannableUtils$IdentifierSpan.isAtLeastT() && GestureSetConfig.activateMultipleGestureSet(context) && SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_multiple_gesture_set_key, R.bool.pref_multiple_gesture_set_default)) {
            return true;
        }
        return false;
    }

    public final TalkbackAction getActionEvent(String str) {
        for (TalkbackAction talkbackAction : TalkbackAction.values()) {
            int i = talkbackAction.actionKeyResId;
            if (i != -1 && TextUtils.equals(this.context.getString(i), str)) {
                return talkbackAction;
            }
        }
        return null;
    }

    public final String getActionKeyFromGestureId(int i) {
        int i2 = this.currentGestureSet;
        if (i2 < 0 || i2 >= 2) {
            LogUtils.w(TAG, "Gesture set is not allowed; fallback to 0.", new Object[0]);
            i2 = 0;
        }
        if (i != -4 && i != -5) {
            String str = (String) ((HashMap) this.gestureIdToActionKey.get(i2)).get(Integer.valueOf(i));
            if (str == null) {
                return this.actionUnassigned;
            }
            return str;
        }
        if (this.gestureSetEnabled) {
            return this.context.getString(R.string.switch_gesture_set);
        }
        return this.actionUnassigned;
    }

    public final String getGestureFromActionKey(String str) {
        if (TextUtils.isEmpty(str) || !((HashMap) this.actionToGesture.get(this.currentGestureSet)).containsKey(str)) {
            return null;
        }
        TalkBackGesture prioritizedGesture = ((GoogleApi.Settings.Builder) ((HashMap) this.actionToGesture.get(this.currentGestureSet)).get(str)).getPrioritizedGesture();
        if (prioritizedGesture == null) {
            LogUtils.w(TAG, "The Action is loaded in the mapping table, but no suitable gesture be found.", new Object[0]);
            return null;
        }
        if (prioritizedGesture.gestureType == 2) {
            return getFingerprintGestureString(this.context, prioritizedGesture.gestureId);
        }
        return getGestureString(this.context, prioritizedGesture.gestureId);
    }

    public final List getGestureTextsFromActionKeys(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            String gestureFromActionKey = getGestureFromActionKey(strArr[i]);
            if (!TextUtils.isEmpty(gestureFromActionKey)) {
                arrayList.add(gestureFromActionKey);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Type inference failed for: r14v12, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v8, types: [java.util.List, java.lang.Object] */
    public final void loadGestureIdToActionKeyMap() {
        GoogleApi.Settings.Builder builder;
        boolean isMultiFingerGestureSupported = FeatureSupport.isMultiFingerGestureSupported();
        boolean isFingerprintGestureSupported = FeatureSupport.isFingerprintGestureSupported(this.context);
        int i = 0;
        LogUtils.d(TAG, "loadActionToGestureIdMap - isMultiFingerOn : " + isMultiFingerGestureSupported + " isFingerprintOn : " + isFingerprintGestureSupported, new Object[0]);
        boolean isEmpty = this.actionToGesture.isEmpty();
        HashMap hashMap = new HashMap();
        int i2 = 0;
        while (true) {
            int i3 = 2;
            if (i2 < 2) {
                HashMap hashMap2 = new HashMap();
                HashMap hashMap3 = new HashMap();
                TalkBackGesture[] values = TalkBackGesture.values();
                int length = values.length;
                int i4 = i;
                while (i4 < length) {
                    TalkBackGesture talkBackGesture = values[i4];
                    if (talkBackGesture.rtlType != 0) {
                        if (SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.context)) {
                            if (talkBackGesture.rtlType == 1) {
                            }
                        } else if (talkBackGesture.rtlType == i3) {
                        }
                        i4++;
                        i3 = 2;
                    }
                    if ((isMultiFingerGestureSupported || talkBackGesture.gestureType != 0) && (isFingerprintGestureSupported || talkBackGesture.gestureType != i3)) {
                        String string = this.prefs.getString(getPrefKeyWithGestureSet(this.context.getString(talkBackGesture.keyId), i2), this.context.getString(talkBackGesture.defaultActionId));
                        if (talkBackGesture == TalkBackGesture.FOUR_FINGER_SINGLE_TAP && PreferencesActivityUtils.isDiagnosisModeOn(this.prefs, this.context.getResources())) {
                            string = this.context.getString(R.string.shortcut_value_print_node_tree);
                        }
                        if (hashMap3.containsKey(string)) {
                            builder = (GoogleApi.Settings.Builder) hashMap3.get(string);
                        } else {
                            builder = new GoogleApi.Settings.Builder(null);
                        }
                        if (TextUtils.equals(string, this.context.getString(talkBackGesture.defaultActionId))) {
                            builder.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging.add(talkBackGesture);
                        } else {
                            builder.GoogleApi$Settings$Builder$ar$looper.add(talkBackGesture);
                        }
                        hashMap3.put(string, builder);
                        if (talkBackGesture.gestureType == 2) {
                            hashMap.put(Integer.valueOf(talkBackGesture.gestureId), this.prefs.getString(this.context.getString(talkBackGesture.keyId), this.context.getString(talkBackGesture.defaultActionId)));
                        } else {
                            hashMap2.put(Integer.valueOf(talkBackGesture.gestureId), string);
                        }
                    }
                    i4++;
                    i3 = 2;
                }
                hashMap2.put(-3, this.context.getString(R.string.shortcut_value_split_typing));
                hashMap3.remove(this.actionUnassigned);
                if (isEmpty) {
                    this.gestureIdToActionKey.add(hashMap2);
                    this.actionToGesture.add(hashMap3);
                } else {
                    this.gestureIdToActionKey.set(i2, hashMap2);
                    this.actionToGesture.set(i2, hashMap3);
                }
                i2++;
                i = 0;
            } else {
                this.fingerprintGestureIdToActionKey = hashMap;
                return;
            }
        }
    }

    @Override // com.google.android.accessibility.talkback.compositor.GestureShortcutProvider
    public final CharSequence nodeMenuShortcut() {
        return getGestureFromActionKey(this.actionTalkbackContextMenu);
    }

    @Override // com.google.android.accessibility.talkback.compositor.GestureShortcutProvider
    public final CharSequence readingMenuDownShortcut() {
        return getGestureFromActionKey(this.actionReadingMenuDown);
    }

    @Override // com.google.android.accessibility.talkback.compositor.GestureShortcutProvider
    public final CharSequence readingMenuUpShortcut() {
        return getGestureFromActionKey(this.actionReadingMenuUp);
    }
}
