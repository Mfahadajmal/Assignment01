package com.google.android.accessibility.talkback.preference;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.hardware.SensorManager;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.preference.TalkBackPreferenceFilter;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import googledata.experiments.mobile.accessibility_suite.features.GestureSetConfig;
import j$.util.Optional;
import j$.util.function.Predicate$CC;
import j$.util.stream.Stream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.function.Predicate;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackPreferenceFilter {
    public static final boolean DISABLE_FREQUENT_UPDATE_UI = true;
    private static final int HIDDEN = Integer.MAX_VALUE;
    private static final int HIDDEN_NO_MEDIA_CONTROL = 524288;
    private static final int HIDDEN_NO_VIBRATION = 16;
    private static final int HIDDEN_ON_RELEASE_BUILD = 65536;
    private static final int HIDDEN_ON_TV = 2;
    private static final int HIDDEN_ON_WATCH = 8;
    private static final int HIDDEN_SETUP = 32;
    private static final int HIDE_FREQUENT_UPDATE_UI_FLAG = Integer.MAX_VALUE;
    private static final int HIDE_GEMINI_SETTINGS = 8388608;
    private static final int HIDE_HAS_VOLUME_KEY = 512;
    private static final int HIDE_ICON_DETECTION = 2097152;
    private static final int HIDE_IF_NO_USER_DISABLING_OF_GLOBAL_ANIMATIONS = 1048576;
    private static final int HIDE_MULTIPLE_GESTURE_SET = 4194304;
    private static final int HIDE_NO_ACCESSIBILITY_AUDIO_STREAM = 262144;
    private static final int HIDE_NO_BRAILLE_DISPLAY = 131072;
    private static final int HIDE_NO_BRAILLE_KEYBOARD = 1024;
    private static final int HIDE_NO_PROXIMITY_SENSOR = 256;
    private static final int HIDE_SERVER_SIDE_GEMINI = 16777216;
    private static final int SHOW_FOCUS_INDICATOR = 32768;
    private static final int SHOW_IF_FINGER_PRINT = 4096;
    private static final int SHOW_IF_MULTI_FINGER = 2048;
    private static final int SHOW_IF_MULTI_FINGER_TAP_AND_HOLD = 16384;
    private static final int SHOW_SYSTEM_ACTION = 8192;
    private final Context context;
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();

    /* compiled from: PG */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface FilterFlag {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TalkBackPreference {
        A11Y_VOLUME(R.string.pref_a11y_volume_key, TalkBackPreferenceFilter.HIDE_NO_ACCESSIBILITY_AUDIO_STREAM),
        AUDIO_DUCKING(R.string.pref_use_audio_focus_key, 10),
        VIBRATION_FEEDBACK(R.string.pref_vibration_key, 16),
        SPEECH_FOOTER(R.string.pref_speech_footer_key, TalkBackPreferenceFilter.HIDE_NO_ACCESSIBILITY_AUDIO_STREAM),
        CUSTOM_LABELS(R.string.pref_manage_labels_key, 42),
        SINGLE_TAP_ACTIVATION(R.string.pref_single_tap_key, 10),
        REDUCE_WINDOW_DELAY(R.string.pref_reduce_window_delay_key, TalkBackPreferenceFilter.HIDE_IF_NO_USER_DISABLING_OF_GLOBAL_ANIMATIONS),
        KEYBOARD_SHORTCUTS(R.string.pref_category_manage_keyboard_shortcut_key, 10),
        PLAY_PAUSE_MEDIA(R.string.keycombo_shortcut_global_play_pause_media, TalkBackPreferenceFilter.HIDDEN_NO_MEDIA_CONTROL),
        TYPING_CONFIRMATION(R.string.pref_typing_confirmation_key, 2),
        TYPING_LONG_PRESS_DURATION(R.string.pref_typing_long_press_duration_key, 2),
        PRIVACY_POLICY(R.string.pref_policy_key, 32),
        TERMS_OF_SERVICE(R.string.pref_show_tos_key, 32),
        HELP_AND_FEEDBACK(R.string.pref_help_and_feedback_key, 32),
        PRACTICE_GESTURES(R.string.pref_practice_gestures_entry_point_key, 10),
        TUTORIAL(R.string.pref_tutorial_entry_point_key, 0),
        NEW_FEATURE(R.string.pref_new_feature_in_talkback_entry_point_key, 2),
        PROXIMITY(R.string.pref_proximity_key, 258),
        TTS_SETTINGS(R.string.pref_tts_settings_key, 8),
        SPEECH_VOLUME(R.string.pref_speech_volume_key, TalkBackPreferenceFilter.HIDE_HAS_VOLUME_KEY),
        BRAILLE_KEYBOARD(R.string.pref_brailleime_key, 1034),
        BRAILLE_DISPLAY(R.string.pref_brailledisplay_key, 131082),
        CUSTOMIZE_MENU(R.string.pref_manage_customize_menus_key, 2),
        CUSTOMIZE_TALKBACK_MENU_VOICE_COMMAND_CONTROLS(R.string.pref_show_context_menu_voice_commands_setting_key, 8),
        CUSTOMIZE_TALKBACK_MENU_EDIT_NAVIGATION_CONTROLS(R.string.pref_show_navigation_menu_controls_setting_key, 8),
        CUSTOMIZE_TALKBACK_MENU_EDIT_NAVIGATION_LINKS(R.string.pref_show_navigation_menu_links_setting_key, 8),
        CUSTOMIZE_TALKBACK_MENU_EDIT_NAVIGATION_LANDMARKS(R.string.pref_show_navigation_menu_landmarks_setting_key, 8),
        CUSTOMIZE_TALKBACK_MENU_EDIT_NAVIGATION_WINDOWS(R.string.pref_show_navigation_menu_window_setting_key, 8),
        CUSTOMIZE_TALKBACK_MENU_SCREEN_SEARCH(R.string.pref_show_context_menu_find_on_screen_setting_key, 8),
        SUPPORT_SYSTEM_ACTION(R.string.pref_show_context_menu_system_action_setting_key, TalkBackPreferenceFilter.SHOW_SYSTEM_ACTION),
        CUSTOMIZE_TALKBACK_MENU_VIBRATION_FEEDBACK(R.string.pref_show_context_menu_vibration_feedback_setting_key, 16),
        CUSTOMIZE_TALKBACK_MENU_IMAGE_CAPTION(R.string.pref_show_context_menu_image_caption_setting_key, 8),
        CUSTOMIZE_TALKBACK_SERVER_SIDE_GEMINI(R.string.pref_detailed_image_description_key, 16777226),
        CUSTOMIZE_READING_MENU_NAVIGATION_LINKS(R.string.pref_selector_granularity_links_key, 8),
        CUSTOMIZE_READING_MENU_NAVIGATION_LANDMARKS(R.string.pref_selector_granularity_landmarks_key, 8),
        CUSTOMIZE_READING_MENU_NAVIGATION_WINDOWS(R.string.pref_selector_granularity_windows_key, 8),
        CUSTOMIZE_READING_MENU_ADJUST_VOLUME(R.string.pref_selector_change_a11y_volume_key, TalkBackPreferenceFilter.HIDE_NO_ACCESSIBILITY_AUDIO_STREAM),
        CUSTOMIZE_READING_MENU_SPELL_CHECK(R.string.pref_selector_granularity_typo_key, 8),
        GESTURE_HANDLING(R.string.pref_talkback_gesture_detection_key, 2),
        EXPLORE_BY_TOUCH(R.string.pref_explore_by_touch_reflect_key, 2),
        CONFIG_ON_SCREEN_KEYBOARD_ECHO(R.string.pref_keyboard_echo_on_screen_key, 8),
        CONFIG_PHYSICAL_KEYBOARD_ECHO(R.string.pref_keyboard_echo_physical_key, 8),
        SPEAK_NUMBER_OF_LIST_(R.string.pref_verbose_scroll_announcement_key, 8),
        SPEAK_ELEMENT_TYPE(R.string.pref_speak_roles_key, 8),
        SPEAK_WINDOW_TITLES(R.string.pref_speak_system_window_titles_key, 8),
        LIMIT_FREQUENT_CONTENT_CHANGE_ANNOUNCEMENT(R.string.pref_allow_frequent_content_change_announcement_key, Preference.DEFAULT_ORDER),
        SPEAK_PHONETIC_LETTERS(R.string.pref_phonetic_letters_key, 8),
        USE_PITCH_CHANGE(R.string.pref_intonation_key, 8),
        SPEAK_WHEN_SCREEN_OFF(R.string.pref_screenoff_key, 8),
        SPEAK_CAPITAL_LETTERS(R.string.pref_capital_letters_key, 8),
        SPEAK_ELEMENT_ID(R.string.pref_speak_element_ids_key, 8),
        SPEAK_PUNCTUATION_LEGACY(R.string.pref_punctuation_key, Preference.DEFAULT_ORDER),
        SPEAK_PUNCTUATION(R.string.pref_punctuation_verbosity, 8),
        CUSTOMIZE_GESTURE(R.string.pref_category_manage_gestures_key, 2),
        MULTIPLE_GESTURE_SET(R.string.pref_gesture_set_key, 4194314),
        CUSTOMIZE_GESTURE_GROUP_2FINGER(R.string.pref_category_2finger_shortcuts_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER),
        CUSTOMIZE_GESTURE_GROUP_3FINGER(R.string.pref_category_3finger_shortcuts_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER),
        CUSTOMIZE_GESTURE_GROUP_4FINGER(R.string.pref_category_4finger_shortcuts_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER),
        CUSTOMIZE_GESTURE_FINGERPRINT(R.string.pref_category_fingerprint_touch_shortcuts_key, TalkBackPreferenceFilter.SHOW_IF_FINGER_PRINT),
        CUSTOMIZE_GESTURE_2FINGER_3TAP_HOLD(R.string.pref_shortcut_2finger_3tap_hold_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER_TAP_AND_HOLD),
        CUSTOMIZE_GESTURE_3FINGER_1TAP_HOLD(R.string.pref_shortcut_3finger_1tap_hold_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER_TAP_AND_HOLD),
        CUSTOMIZE_GESTURE_3FINGER_3TAP_HOLD(R.string.pref_shortcut_3finger_3tap_hold_key, TalkBackPreferenceFilter.SHOW_IF_MULTI_FINGER_TAP_AND_HOLD),
        CUSTOMIZE_FOCUS_INDICATOR(R.string.pref_category_manage_focus_indicator_key, TalkBackPreferenceFilter.SHOW_FOCUS_INDICATOR),
        AUTOMATIC_DESCRIPTIONS(R.string.pref_auto_image_captioning_key, 2),
        GEMINI_SUPPORT(R.string.pref_gemini_settings_key, 8388618),
        ICON_DETECTION(R.string.pref_icon_detection_key, 2097160);

        final int hideFlags;
        final int resId;

        TalkBackPreference(int i, int i2) {
            this.resId = i;
            this.hideFlags = i2;
        }
    }

    public TalkBackPreferenceFilter(Context context) {
        this.context = context;
    }

    private boolean hasFlag(TalkBackPreference talkBackPreference, int i) {
        if ((talkBackPreference.hideFlags & i) == i) {
            return true;
        }
        return false;
    }

    private boolean hide(final Preference preference) {
        Optional findFirst = Stream.CC.of((Object[]) TalkBackPreference.values()).filter(new Predicate() { // from class: com.google.android.accessibility.talkback.preference.TalkBackPreferenceFilter$$ExternalSyntheticLambda0
            public /* synthetic */ Predicate and(Predicate predicate) {
                return Predicate$CC.$default$and(this, predicate);
            }

            public /* synthetic */ Predicate negate() {
                return Predicate$CC.$default$negate(this);
            }

            public /* synthetic */ Predicate or(Predicate predicate) {
                return Predicate$CC.$default$or(this, predicate);
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TalkBackPreferenceFilter.this.m102xa9dd5c8a(preference, (TalkBackPreferenceFilter.TalkBackPreference) obj);
            }
        }).findFirst();
        if (findFirst.isEmpty()) {
            return false;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), 2) && this.formFactorUtils.isAndroidTv) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), 8) && FormFactorUtils.getInstance().isAndroidWear) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), 16) && !FeatureSupport.isVibratorSupported(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), 32) && !SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_NO_PROXIMITY_SENSOR) && ((SensorManager) this.context.getSystemService("sensor")).getDefaultSensor(8) == null) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_HAS_VOLUME_KEY) && FeatureSupport.hasAccessibilityAudioStream(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_NO_BRAILLE_KEYBOARD) && !FeatureSupport.supportBrailleKeyboard(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_NO_BRAILLE_DISPLAY) && !FeatureSupport.supportBrailleDisplay(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_IF_NO_USER_DISABLING_OF_GLOBAL_ANIMATIONS) && !SpannableUtils$IdentifierSpan.isAtLeastP()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), SHOW_IF_MULTI_FINGER) && !FeatureSupport.isMultiFingerGestureSupported()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), SHOW_IF_MULTI_FINGER_TAP_AND_HOLD) && !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), SHOW_IF_FINGER_PRINT) && !FeatureSupport.isFingerprintGestureSupported(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), SHOW_SYSTEM_ACTION) && !FeatureSupport.supportGetSystemActions(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), SHOW_FOCUS_INDICATOR) && !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_NO_ACCESSIBILITY_AUDIO_STREAM) && !FeatureSupport.hasAccessibilityAudioStream(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDDEN_NO_MEDIA_CONTROL) && !SpannableUtils$IdentifierSpan.isAtLeastR()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_ICON_DETECTION) && !ImageCaptioner.supportsIconDetection$ar$ds()) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_MULTIPLE_GESTURE_SET) && (!SpannableUtils$IdentifierSpan.isAtLeastT() || !GestureSetConfig.activateMultipleGestureSet(this.context))) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_GEMINI_SETTINGS) && !SpannableUtils$NonCopyableTextSpan.isGeminiVoiceCommandEnabled(this.context)) {
            return true;
        }
        if (hasFlag((TalkBackPreference) findFirst.get(), HIDE_SERVER_SIDE_GEMINI) && !GeminiConfig.enableServerSideGeminiImageCaptioning(this.context)) {
            return true;
        }
        if ((findFirst.get() != TalkBackPreference.TUTORIAL || !this.formFactorUtils.isAndroidTv || SpannableUtils$IdentifierSpan.shouldShowTraining(VendorConfigReader.retrieveConfig(this.context))) && !hasFlag((TalkBackPreference) findFirst.get(), Preference.DEFAULT_ORDER) && !hasFlag((TalkBackPreference) findFirst.get(), HIDDEN_ON_RELEASE_BUILD)) {
            return false;
        }
        return true;
    }

    public void filterPreferences(PreferenceGroup preferenceGroup) {
        int i = 0;
        while (i < preferenceGroup.getPreferenceCount()) {
            Preference preference = preferenceGroup.getPreference(i);
            if (hide(preference)) {
                preferenceGroup.removePreference$ar$ds(preference);
            } else {
                if ((preference instanceof PreferenceGroup) && (!(preference instanceof PreferenceScreen) || ((PreferenceGroup) preference).getPreferenceCount() != 0)) {
                    PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                    filterPreferences(preferenceGroup2);
                    if (preferenceGroup2.getPreferenceCount() == 0) {
                        preferenceGroup.removePreference$ar$ds(preference);
                    }
                }
                i++;
            }
            i--;
            i++;
        }
    }

    /* renamed from: lambda$hide$0$com-google-android-accessibility-talkback-preference-TalkBackPreferenceFilter, reason: not valid java name */
    public /* synthetic */ boolean m102xa9dd5c8a(Preference preference, TalkBackPreference talkBackPreference) {
        return TextUtils.equals(preference.getKey(), this.context.getString(talkBackPreference.resId));
    }
}
