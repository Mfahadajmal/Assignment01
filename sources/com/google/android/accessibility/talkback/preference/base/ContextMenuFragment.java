package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextMenuFragment extends TalkbackBaseFragment {
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    public ContextMenuFragment() {
        super(R.xml.context_menu_preferences);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onCreatePreferences$0(Preference preference, Context context, SharedPreferences sharedPreferences, String str) {
        if (preference != null && TextUtils.equals(str, context.getString(R.string.pref_show_context_menu_granularity_setting_key))) {
            if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_show_context_menu_granularity_setting_key, R.bool.pref_show_context_menu_granularity_default)) {
                preference.setEnabled(true);
            } else {
                preference.setEnabled(false);
            }
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_context_menu);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        PreferencesActivityUtils.removeEditingKey(getContext());
        super.onCreate(bundle);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        final Context context = getContext();
        if (context != null) {
            SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
            final Preference findPreference = findPreference(context.getString(R.string.pref_show_context_menu_granularity_detail_setting_key));
            if (!SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_show_context_menu_granularity_setting_key, R.bool.pref_show_context_menu_granularity_default) && findPreference != null) {
                findPreference.setEnabled(false);
            }
            SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.ContextMenuFragment$$ExternalSyntheticLambda0
                @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str2) {
                    ContextMenuFragment.lambda$onCreatePreferences$0(Preference.this, context, sharedPreferences2, str2);
                }
            };
            this.listener = onSharedPreferenceChangeListener;
            sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            Preference findPreference2 = findPreference(context.getString(R.string.pref_show_context_menu_image_caption_setting_key));
            if (findPreference2 != null) {
                findPreference2.setVisible(ImageCaptioner.supportsImageCaption$ar$ds());
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = this.listener;
        if (onSharedPreferenceChangeListener != null) {
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
            this.listener = null;
        }
        super.onDestroy();
    }
}
