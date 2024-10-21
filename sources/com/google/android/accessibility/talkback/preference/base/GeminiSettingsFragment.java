package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.talkback.preference.base.GeminiSettingsFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GeminiSettingsFragment extends TalkbackBaseFragment {
    private static final String TAG = "GeminiSettingsFragment";
    private Context context;
    private SharedPreferences prefs;

    public GeminiSettingsFragment() {
        super(R.xml.gemini_settings);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getBooleanPref(int i, int i2) {
        return SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, this.context.getResources(), i, i2);
    }

    private void setupEnablePreference() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_gemini_enabled_key);
        if (findPreferenceByResId == null) {
            return;
        }
        if (getBooleanPref(R.string.pref_gemini_enabled_key, R.bool.pref_gemini_opt_in_default)) {
            findPreferenceByResId.setSummary(R.string.title_pref_enable_gemini_support);
        } else {
            findPreferenceByResId.setSummary(R.string.title_pref_disable_gemini_support);
        }
        findPreferenceByResId.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.GeminiSettingsFragment$$ExternalSyntheticLambda0
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return GeminiSettingsFragment.this.m142xddbad501(preference);
            }
        });
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    protected CharSequence getTitle() {
        return getText(R.string.title_pref_gemini_settings);
    }

    /* renamed from: lambda$setupEnablePreference$0$com-google-android-accessibility-talkback-preference-base-GeminiSettingsFragment, reason: not valid java name */
    public /* synthetic */ boolean m142xddbad501(final Preference preference) {
        new GeminiOptInDialog(this, this.context, R.string.gemini_enable_dialog_title, R.string.gemini_disable_dialog_message, R.string.pref_gemini_enabled_key, R.bool.pref_gemini_opt_in_default) { // from class: com.google.android.accessibility.talkback.preference.base.GeminiSettingsFragment.1
            final /* synthetic */ GeminiSettingsFragment this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.preference.base.GeminiSettingsFragment.GeminiOptInDialog, com.google.android.accessibility.talkback.dialog.BaseDialog
            public void handleDialogClick(int i) {
                super.handleDialogClick(i);
                if (this.this$0.getBooleanPref(R.string.pref_gemini_enabled_key, R.bool.pref_gemini_opt_in_default)) {
                    preference.setSummary(R.string.title_pref_enable_gemini_support);
                } else {
                    preference.setSummary(R.string.title_pref_disable_gemini_support);
                }
            }
        }.showDialog();
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        this.context = context;
        if (context == null) {
            return;
        }
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        setupEnablePreference();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class GeminiOptInDialog extends BaseDialog {
        private final int defaultValue;
        private final int key;
        private final int message;
        private final SharedPreferences prefs;
        private RadioGroup switches;

        public GeminiOptInDialog(Context context, int i, int i2, int i3, int i4) {
            super(context, i, null);
            this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
            this.message = i2;
            this.key = i3;
            this.defaultValue = i4;
            setPositiveButtonStringRes(R.string.switch_auto_image_caption_dialog_positive_button_text);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$getCustomizedView$0(RadioGroup radioGroup, int i) {
            TextView textView = (TextView) radioGroup.findViewById(R.id.gemini_opt_in_switch_dialog_radiobutton_disabled_subtext);
            if (i == R.id.gemini_opt_in_switch_dialog_radiobutton_disabled) {
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public View getCustomizedView() {
            int i;
            ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.gemini_opt_in_switch_dialog, (ViewGroup) null);
            ((TextView) scrollView.findViewById(R.id.gemini_opt_in_switch_dialog_message)).setText(this.message);
            RadioGroup radioGroup = (RadioGroup) scrollView.findViewById(R.id.gemini_opt_in_switch_dialog_radiogroup);
            this.switches = radioGroup;
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.GeminiSettingsFragment$GeminiOptInDialog$$ExternalSyntheticLambda0
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                    GeminiSettingsFragment.GeminiOptInDialog.lambda$getCustomizedView$0(radioGroup2, i2);
                }
            });
            if (true != SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, this.context.getResources(), this.key, this.defaultValue)) {
                i = R.id.gemini_opt_in_switch_dialog_radiobutton_disabled;
            } else {
                i = R.id.gemini_opt_in_switch_dialog_radiobutton_enabled;
            }
            ((RadioButton) this.switches.findViewById(i)).setChecked(true);
            return scrollView;
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public String getMessageString() {
            return null;
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public void handleDialogClick(int i) {
            boolean z;
            if (this.switches != null && i == -1) {
                SharedPreferences sharedPreferences = this.prefs;
                Context context = this.context;
                int i2 = this.key;
                Resources resources = context.getResources();
                if (this.switches.getCheckedRadioButtonId() == R.id.gemini_opt_in_switch_dialog_radiobutton_enabled) {
                    z = true;
                } else {
                    z = false;
                }
                SpannableUtils$IdentifierSpan.putBooleanPref(sharedPreferences, resources, i2, z);
            }
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public void handleDialogDismiss() {
        }
    }
}
