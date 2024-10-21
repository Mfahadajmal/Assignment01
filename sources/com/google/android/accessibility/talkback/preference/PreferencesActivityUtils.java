package com.google.android.accessibility.talkback.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PreferencesActivityUtils {
    public static final int GESTURE_CHANGE_NOTIFICATION_ID = 2;
    public static final String HELP_URL = "https://support.google.com/accessibility/android/answer/6283677";

    private PreferencesActivityUtils() {
    }

    public static void announceText(String str, Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain(16384);
            obtain.setContentDescription(str);
            accessibilityManager.sendAccessibilityEvent(obtain);
        }
    }

    public static boolean getDiagnosticPref(Context context, int i, int i2) {
        return getDiagnosticPref(SpannableUtils$IdentifierSpan.getSharedPreferences(context), context.getResources(), i, i2);
    }

    public static boolean isDiagnosisModeOn(SharedPreferences sharedPreferences, Resources resources) {
        return SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, resources, R.string.pref_diagnosis_mode_key, R.bool.pref_diagnosis_mode_default);
    }

    public static void removeEditingKey(Context context) {
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        String string = context.getString(R.string.pref_show_context_menu_editing_setting_key);
        if (sharedPreferences.contains(string)) {
            if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_show_context_menu_editing_setting_key, R.bool.pref_show_context_menu_editing_default)) {
                SpannableUtils$IdentifierSpan.putBooleanPref(sharedPreferences, context.getResources(), R.string.pref_show_context_menu_custom_action_setting_key, true);
            }
            edit.remove(string);
            edit.apply();
        }
    }

    public static void setSummary(Context context, PreferenceManager preferenceManager, int i, int i2) {
        Preference findPreference;
        if (preferenceManager != null && (findPreference = preferenceManager.findPreference(context.getString(i))) != null) {
            findPreference.setSummary(i2);
        }
    }

    public static boolean getDiagnosticPref(SharedPreferences sharedPreferences, Resources resources, int i, int i2) {
        return isDiagnosisModeOn(sharedPreferences, resources) || SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, resources, i, i2);
    }
}
