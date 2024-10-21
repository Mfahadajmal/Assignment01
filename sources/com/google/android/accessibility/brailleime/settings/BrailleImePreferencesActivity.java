package com.google.android.accessibility.brailleime.settings;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegateImpl;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodInfo;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13;
import com.google.android.accessibility.braille.common.BraillePreferenceUtils$$ExternalSyntheticLambda5;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.braille.common.settings.BrailleLanguagesActivity;
import com.google.android.accessibility.braille.interfaces.TalkBackForBrailleIme;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda6;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import j$.util.DesugarArrays;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleImePreferencesActivity extends PreferencesActivity {
    public static TalkBackForBrailleIme talkBackForBrailleIme;
    private final ContentObserver imeSettingsContentObserver = new ContentObserver(new Handler()) { // from class: com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity.1
        public AnonymousClass1(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final boolean deliverSelfNotifications() {
            return false;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            onChange(z, null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            if (BrailleImePreferencesActivity.this.isImeEnabled()) {
                BrailleImePreferencesActivity.this.finishActivity(100);
            }
        }
    };
    private PreferenceFragmentCompat preferenceFragmentCompat;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends ContentObserver {
        public AnonymousClass1(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final boolean deliverSelfNotifications() {
            return false;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            onChange(z, null);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            if (BrailleImePreferencesActivity.this.isImeEnabled()) {
                BrailleImePreferencesActivity.this.finishActivity(100);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BrailleImePrefFragment extends PreferenceFragmentCompat {
        private Preference brailleGradePreference;
        private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 10);

        public void configurePrefs() {
            String string;
            Preference findPreference = findPreference(getString(R.string.pref_brailleime_turn_on_braille_keyboard));
            findPreference.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 2));
            if (((BrailleImePreferencesActivity) getActivity()).isImeEnabled()) {
                string = getString(R.string.how_to_use_braille_keyboard);
            } else {
                string = getString(R.string.set_up_braille_keyboard);
            }
            findPreference.setTitle(string);
            Preference findPreference2 = findPreference(getString(R.string.pref_brailleime_translator_codes_preferred));
            findPreference2.setIntent(new Intent(getContext(), (Class<?>) BrailleLanguagesActivity.class));
            AppCompatDelegateImpl.Api21Impl.setupPreferredCodePreference(getContext(), findPreference2, new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 7));
            ListPreference listPreference = (ListPreference) findPreference(getString(R.string.pref_brailleime_translator_code));
            if (listPreference != null) {
                AppCompatDelegateImpl.Api21Impl.setupLanguageListPreference(getContext(), listPreference, new BtConnectManager$$ExternalSyntheticLambda1(20), new UsbConnectManager$$ExternalSyntheticLambda0(7), new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 8));
            }
            findPreference(getString(R.string.pref_brailleime_review_all_gestures)).setIntent(new Intent(getContext(), (Class<?>) BrailleImeGestureActivity.class));
            SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.pref_brailleime_accumulate_mode));
            switchPreference.setChecked(BrailleUserPreferences.readAccumulateMode(getContext()));
            switchPreference.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 3));
            switchPreference.setChecked(BrailleUserPreferences.readAccumulateMode(getContext()));
            SwitchPreference switchPreference2 = (SwitchPreference) findPreference(getString(R.string.pref_brailleime_reverse_dots_mode));
            switchPreference2.setChecked(BrailleUserPreferences.readReverseDotsMode(getContext()));
            switchPreference2.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 4));
            switchPreference2.setChecked(BrailleUserPreferences.readReverseDotsMode(getContext()));
            ListPreference listPreference2 = (ListPreference) findPreference(getString(R.string.pref_brailleime_layout_mode));
            if (listPreference2 != null) {
                listPreference2.setEntryValues((CharSequence[]) DesugarArrays.stream(TouchDots.values()).map(new BtConnectManager$$ExternalSyntheticLambda1(19)).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(6)));
                listPreference2.setEntries((CharSequence[]) DesugarArrays.stream(TouchDots.values()).map(new BraillePreferenceUtils$$ExternalSyntheticLambda5(this, 4)).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(7)));
                listPreference2.setValue(BrailleUserPreferences.readLayoutMode(getContext()).name());
                listPreference2.setSummaryProvider(new BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2(this, 0));
                listPreference2.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 6));
            }
            updateBrailleGradeSummary();
        }

        public static /* synthetic */ CharSequence[] lambda$configurePrefs$5(int i) {
            return new CharSequence[i];
        }

        public static /* synthetic */ CharSequence[] lambda$configurePrefs$7(int i) {
            return new CharSequence[i];
        }

        private void showSwitchInputCodeGestureTipDialog() {
            AppCompatDelegateImpl.Api21Impl.createTipAlertDialog(getContext(), getString(R.string.switch_input_code_gesture_tip_dialog_title), getString(R.string.switch_input_code_gesture_tip_dialog_message), new UsbConnectManager$$ExternalSyntheticLambda0(6)).show();
        }

        public void updateBrailleGradeSummary() {
            int i;
            Preference preference = this.brailleGradePreference;
            if (true != BrailleUserPreferences.readContractedMode(getContext())) {
                i = R.string.bd_preference_braille_uncontracted;
            } else {
                i = R.string.bd_preference_braille_contracted;
            }
            preference.setSummary(getString(i));
        }

        /* renamed from: lambda$configurePrefs$0$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m76xd1f5cbbd(Preference preference) {
            String string;
            int i;
            Spanned fromHtml;
            int i2;
            Spanned fromHtml2;
            final BrailleImePreferencesActivity brailleImePreferencesActivity = (BrailleImePreferencesActivity) getActivity();
            AlertDialog.Builder alertDialogBuilder = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(brailleImePreferencesActivity);
            if (brailleImePreferencesActivity.isImeEnabled()) {
                string = brailleImePreferencesActivity.getString(R.string.how_to_use_braille_keyboard);
            } else {
                string = brailleImePreferencesActivity.getString(R.string.set_up_braille_keyboard);
            }
            alertDialogBuilder.setTitle(string);
            if (brailleImePreferencesActivity.isImeEnabled()) {
                String string2 = brailleImePreferencesActivity.getString(R.string.gboard_name);
                fromHtml2 = HtmlCompat.Api24Impl.fromHtml(brailleImePreferencesActivity.getString(R.string.use_brailleime_pref_dialog_case_ime_enabled, new Object[]{"KEYBOARD_ICON", brailleImePreferencesActivity.getString(R.string.braille_ime_service_name), string2}), 0);
                SpannableString replaceKeyboardIconTokenToIconDrawable = brailleImePreferencesActivity.replaceKeyboardIconTokenToIconDrawable(SpannableString.valueOf(fromHtml2));
                if (SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(brailleImePreferencesActivity)) {
                    BrailleImePreferencesActivity.insertHyperLinkToSubString(replaceKeyboardIconTokenToIconDrawable, string2);
                }
                alertDialogBuilder.setMessage(replaceKeyboardIconTokenToIconDrawable).setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) null);
            } else {
                String string3 = brailleImePreferencesActivity.getString(R.string.gboard_name);
                boolean supportEnableIme$ar$ds = BrailleImePreferencesActivity.supportEnableIme$ar$ds();
                Object[] objArr = {brailleImePreferencesActivity.getString(R.string.braille_ime_service_name), "KEYBOARD_ICON", string3};
                if (true != supportEnableIme$ar$ds) {
                    i = R.string.use_brailleime_pref_dialog_case_ime_disabled_settings;
                } else {
                    i = R.string.use_brailleime_pref_dialog_case_ime_disabled_turn_on;
                }
                fromHtml = HtmlCompat.Api24Impl.fromHtml(brailleImePreferencesActivity.getString(i, objArr), 0);
                SpannableString replaceKeyboardIconTokenToIconDrawable2 = brailleImePreferencesActivity.replaceKeyboardIconTokenToIconDrawable(SpannableString.valueOf(fromHtml));
                if (SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(brailleImePreferencesActivity)) {
                    BrailleImePreferencesActivity.insertHyperLinkToSubString(replaceKeyboardIconTokenToIconDrawable2, string3);
                }
                AlertDialog.Builder message = alertDialogBuilder.setMessage(replaceKeyboardIconTokenToIconDrawable2);
                if (true != BrailleImePreferencesActivity.supportEnableIme$ar$ds()) {
                    i2 = R.string.use_brailleime_pref_button_case_ime_disabled_settings;
                } else {
                    i2 = R.string.use_brailleime_pref_button_case_ime_disabled_turn_on;
                }
                message.setPositiveButton(brailleImePreferencesActivity.getString(i2), new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(brailleImePreferencesActivity, 15)).setNegativeButton(android.R.string.cancel, new TrainingActivity$$ExternalSyntheticLambda6(1));
            }
            final AlertDialog create = alertDialogBuilder.create();
            if (!brailleImePreferencesActivity.isImeEnabled() && BrailleImePreferencesActivity.supportEnableIme$ar$ds()) {
                create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity$$ExternalSyntheticLambda2
                    @Override // android.content.DialogInterface.OnShowListener
                    public final void onShow(DialogInterface dialogInterface) {
                        create.getButton(-1).setContentDescription(BrailleImePreferencesActivity.this.getString(R.string.use_brailleime_pref_button_case_ime_disabled_turn_on_announcement));
                    }
                });
            }
            create.show();
            View findViewById = create.findViewById(android.R.id.message);
            if (findViewById instanceof TextView) {
                ((TextView) findViewById).setMovementMethod(LinkMovementMethod.getInstance());
            }
            return true;
        }

        /* renamed from: lambda$configurePrefs$1$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m77x1220b27e(Preference preference, Object obj) {
            configurePrefs();
            return false;
        }

        /* renamed from: lambda$configurePrefs$2$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m78x524b993f(Preference preference, Object obj) {
            Context context = getContext();
            if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_show_switch_input_code_gesture_tip), true)) {
                showSwitchInputCodeGestureTipDialog();
                return false;
            }
            return false;
        }

        /* renamed from: lambda$configurePrefs$3$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m79x92768000(Preference preference) {
            Context context = getContext();
            BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putBoolean(context.getString(R.string.pref_brailleime_accumulate_mode), ((SwitchPreference) preference).isChecked()).apply();
            return true;
        }

        /* renamed from: lambda$configurePrefs$4$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m80xd2a166c1(Preference preference) {
            Context context = getContext();
            BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putBoolean(context.getString(R.string.pref_brailleime_reverse_dots_mode), ((SwitchPreference) preference).isChecked()).apply();
            return true;
        }

        /* renamed from: lambda$configurePrefs$6$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ String m81x52f73443(TouchDots touchDots) {
            return getResources().getString(touchDots.layoutDescriptionStringId);
        }

        /* renamed from: lambda$configurePrefs$8$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ CharSequence m82xd34d01c5(Preference preference) {
            return getResources().getString(BrailleUserPreferences.readLayoutMode(getContext()).layoutNameStringId);
        }

        /* renamed from: lambda$configurePrefs$9$com-google-android-accessibility-brailleime-settings-BrailleImePreferencesActivity$BrailleImePrefFragment */
        public /* synthetic */ boolean m83x1377e886(Preference preference, Object obj) {
            Context context = getContext();
            BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putString(context.getString(R.string.pref_brailleime_layout_mode), ((TouchDots) Enum.valueOf(TouchDots.class, obj.toString())).name()).apply();
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.brailleime_preferences);
            this.brailleGradePreference = findPreference(getString(R.string.pref_braille_contracted_mode));
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }

        @Override // android.support.v4.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            configurePrefs();
        }
    }

    public static void insertHyperLinkToSubString(SpannableString spannableString, String str) {
        int indexOf = spannableString.toString().indexOf(str);
        int length = str.length() + indexOf;
        if (indexOf == -1) {
            return;
        }
        spannableString.setSpan(new URLSpan("http://play.google.com/store/apps/details?id=com.google.android.inputmethod.latin"), indexOf, length, 33);
    }

    public static final boolean supportEnableIme$ar$ds() {
        TalkBackForBrailleIme talkBackForBrailleIme2;
        if (SpannableUtils$IdentifierSpan.isAtLeastT() && (talkBackForBrailleIme2 = talkBackForBrailleIme) != null && talkBackForBrailleIme2.getServiceStatus$ar$edu() != 2) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        BrailleImePrefFragment brailleImePrefFragment = new BrailleImePrefFragment();
        this.preferenceFragmentCompat = brailleImePrefFragment;
        return brailleImePrefFragment;
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "BrailleImePreferencesActivity";
    }

    public final boolean isImeEnabled() {
        ComponentName componentName = new ComponentName(this, BrailleIme.class.getName());
        Iterator it = SpannableUtils$IdentifierSpan.getEnabledInputMethodList(this).iterator();
        while (it.hasNext()) {
            if (((InputMethodInfo) it.next()).getComponent().equals(componentName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity, com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getContentResolver().registerContentObserver(Settings.Secure.getUriFor("enabled_input_methods"), false, this.imeSettingsContentObserver);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        getContentResolver().unregisterContentObserver(this.imeSettingsContentObserver);
        super.onDestroy();
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            Intent intent = new Intent();
            intent.setComponent(AccessibilityServiceCompatUtils$Constants.SETTINGS_ACTIVITY);
            intent.addFlags(16777216);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public final SpannableString replaceKeyboardIconTokenToIconDrawable(SpannableString spannableString) {
        Drawable drawable = getDrawable(R.drawable.quantum_ic_keyboard_grey600_24);
        int indexOf = spannableString.toString().indexOf("KEYBOARD_ICON");
        int i = indexOf + 13;
        if (indexOf == -1) {
            return spannableString;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
        spannableStringBuilder.replace(indexOf, i, (CharSequence) " ");
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        spannableStringBuilder.setSpan(new ImageSpan(drawable), indexOf, indexOf + 1, 33);
        return SpannableString.valueOf(spannableStringBuilder);
    }
}
