package com.google.android.accessibility.talkback.preference.base;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.keyboard.KeyComboModel;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.talkback.preference.TalkBackPreferenceFilter;
import com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import j$.util.function.Consumer$CC;
import java.util.Iterator;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackKeyboardShortcutPreferenceFragment extends TalkbackBaseFragment {
    private final DialogInterface.OnClickListener chooseTriggerModifierConfirmDialogPositive;
    private String keymap;
    private final Preference.OnPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences prefs;
    private final DialogInterface.OnClickListener resetKeymapConfirmDialogPositive;
    private PreferenceScreen resetKeymapPreference;
    private final Preference.OnPreferenceClickListener resetKeymapPreferenceClickListener;
    TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener serviceStateChangeListener;
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
    private String triggerModifierToBeSet;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$1, reason: invalid class name */
    /* loaded from: classes.dex */
    class AnonymousClass1 implements TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener {
        public AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onServiceStateChange$0(boolean z, KeyboardShortcutDialogPreference keyboardShortcutDialogPreference) {
            keyboardShortcutDialogPreference.setEnabled(z);
            if (z) {
                keyboardShortcutDialogPreference.updateKeyComboManager();
                keyboardShortcutDialogPreference.onTriggerModifierChanged();
            }
        }

        @Override // com.google.android.accessibility.talkback.TalkBackService$TalkbackServiceStateNotifier$TalkBackServiceStateChangeListener
        public void onServiceStateChange(final boolean z) {
            TalkBackKeyboardShortcutPreferenceFragment.this.resetKeymapPreference.setEnabled(z);
            TalkBackKeyboardShortcutPreferenceFragment.this.setUpDialogPreference(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$1$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TalkBackKeyboardShortcutPreferenceFragment.AnonymousClass1.lambda$onServiceStateChange$0(z, (KeyboardShortcutDialogPreference) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }
    }

    public TalkBackKeyboardShortcutPreferenceFragment() {
        super(R.xml.key_combo_preferences);
        this.sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda4
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                TalkBackKeyboardShortcutPreferenceFragment.this.m167x95e4bd7f(sharedPreferences, str);
            }
        };
        this.chooseTriggerModifierConfirmDialogPositive = new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TalkBackKeyboardShortcutPreferenceFragment.this.m168x5e42e21e(dialogInterface, i);
            }
        };
        this.resetKeymapConfirmDialogPositive = new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TalkBackKeyboardShortcutPreferenceFragment.this.m169x26a106bd(dialogInterface, i);
            }
        };
        this.resetKeymapPreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda7
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return TalkBackKeyboardShortcutPreferenceFragment.this.m170xb75d4ffb(preference);
            }
        };
        this.preferenceChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda8
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return TalkBackKeyboardShortcutPreferenceFragment.this.m172x48199939(preference, obj);
            }
        };
        this.serviceStateChangeListener = new AnonymousClass1();
    }

    private KeyComboManager getKeyComboManager() {
        TalkBackService talkBackService = TalkBackService.instance;
        if (talkBackService == null) {
            return new KeyComboManager(getActivity());
        }
        return talkBackService.keyComboManager;
    }

    private String getKeymap() {
        return getKeyComboManager().getKeymap();
    }

    private String getKeymapName(String str) {
        if (str.equals(getString(R.string.classic_keymap_entry_value))) {
            return getString(R.string.value_classic_keymap);
        }
        if (str.equals(getString(R.string.default_keymap_entry_value))) {
            return getString(R.string.value_default_keymap);
        }
        return null;
    }

    private int getPreferenceResourceId() {
        if (TextUtils.equals(this.keymap, getContext().getString(R.string.default_keymap_entry_value))) {
            return R.xml.default_key_combo_preferences;
        }
        return R.xml.key_combo_preferences;
    }

    private void initPreferenceUIs(PreferenceGroup preferenceGroup) {
        if (preferenceGroup != null) {
            KeyComboModel keyComboModel = getKeyComboManager().keyComboModel;
            String preferenceKeyForTriggerModifier = keyComboModel.getPreferenceKeyForTriggerModifier();
            for (int i = 0; i < preferenceGroup.getPreferenceCount(); i++) {
                Preference preference = preferenceGroup.getPreference(i);
                String key = preference.getKey();
                if (key != null && (preference instanceof KeyboardShortcutDialogPreference) && !keyComboModel.getKeyComboCodeMap().containsKey(key)) {
                    preference.setEnabled(false);
                } else if (!(preference instanceof KeyboardShortcutDialogPreference) && ((key == null || !key.equals(getString(R.string.pref_select_keymap_key))) && (key == null || !key.equals(preferenceKeyForTriggerModifier)))) {
                    if (preference instanceof PreferenceGroup) {
                        initPreferenceUIs((PreferenceGroup) preference);
                    }
                } else {
                    preference.setOnPreferenceChangeListener(this.preferenceChangeListener);
                }
            }
        }
    }

    private boolean isServiceActive() {
        return TalkBackService.isServiceActive();
    }

    private void resetKeymap() {
        KeyComboModel keyComboModel = getKeyComboManager().keyComboModel;
        for (String str : keyComboModel.getKeyComboCodeMap().keySet()) {
            long defaultKeyComboCode = keyComboModel.getDefaultKeyComboCode(str);
            if (defaultKeyComboCode != keyComboModel.getKeyComboCodeForKey(str)) {
                keyComboModel.saveKeyComboCode(str, defaultKeyComboCode);
                KeyboardShortcutDialogPreference keyboardShortcutDialogPreference = (KeyboardShortcutDialogPreference) findPreference(str);
                keyboardShortcutDialogPreference.setKeyComboCode(defaultKeyComboCode);
                keyboardShortcutDialogPreference.notifyChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpDialogPreference(Consumer<KeyboardShortcutDialogPreference> consumer) {
        Iterator it = getKeyComboManager().keyComboModel.getKeyComboCodeMap().keySet().iterator();
        while (it.hasNext()) {
            KeyboardShortcutDialogPreference keyboardShortcutDialogPreference = (KeyboardShortcutDialogPreference) findPreference((String) it.next());
            if (keyboardShortcutDialogPreference != null) {
                consumer.accept(keyboardShortcutDialogPreference);
            }
        }
    }

    private void updateDialogAndResetKeymapPreference() {
        this.resetKeymapPreference.setEnabled(isServiceActive());
        setUpDialogPreference(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                TalkBackKeyboardShortcutPreferenceFragment.this.m173x457f803d((KeyboardShortcutDialogPreference) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    private void updateFragment() {
        int preferenceResourceId = getPreferenceResourceId();
        getPreferenceManager().setStorageDeviceProtected();
        setPreferencesFromResource(preferenceResourceId, null);
        updatePreference();
    }

    private void updatePreference() {
        new TalkBackPreferenceFilter(getActivity().getApplicationContext()).filterPreferences(getPreferenceScreen());
        ListPreference listPreference = (ListPreference) findPreference(getString(R.string.pref_default_keymap_trigger_modifier_key));
        if (listPreference != null && getKeyComboManager().keyComboModel.getTriggerModifier() != 65536) {
            ((PreferenceCategory) findPreference(getString(R.string.pref_keymap_category_key))).removePreference$ar$ds(listPreference);
        }
        PreferenceScreen preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_reset_keymap_key));
        this.resetKeymapPreference = preferenceScreen;
        preferenceScreen.setOnPreferenceClickListener(this.resetKeymapPreferenceClickListener);
        updateDialogAndResetKeymapPreference();
        initPreferenceUIs(getPreferenceScreen());
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_manage_keyboard_shortcuts);
    }

    /* renamed from: lambda$new$0$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m167x95e4bd7f(SharedPreferences sharedPreferences, String str) {
        if (TextUtils.equals(str, getString(R.string.pref_select_keymap_key))) {
            this.keymap = getKeymap();
            getKeyComboManager().refreshKeyComboModel();
            updateFragment();
        } else if (TextUtils.equals(str, getString(R.string.pref_default_keymap_trigger_modifier_key))) {
            getKeyComboManager().refreshKeyComboModel();
            updateFragment();
        }
    }

    /* renamed from: lambda$new$1$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m168x5e42e21e(DialogInterface dialogInterface, int i) {
        resetKeymap();
        KeyComboModel keyComboModel = getKeyComboManager().keyComboModel;
        ListPreference listPreference = (ListPreference) findPreference(keyComboModel.getPreferenceKeyForTriggerModifier());
        listPreference.setValue(this.triggerModifierToBeSet);
        keyComboModel.notifyTriggerModifierChanged();
        setUpDialogPreference(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((KeyboardShortcutDialogPreference) obj).onTriggerModifierChanged();
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        PreferencesActivityUtils.announceText(getString(R.string.keycombo_menu_announce_new_trigger_modifier, listPreference.getEntries()[listPreference.findIndexOfValue(this.triggerModifierToBeSet)]), getActivity());
        this.triggerModifierToBeSet = null;
    }

    /* renamed from: lambda$new$2$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m169x26a106bd(DialogInterface dialogInterface, int i) {
        resetKeymap();
        dialogInterface.dismiss();
        PreferencesActivityUtils.announceText(getString(R.string.keycombo_menu_announce_reset_keymap), getActivity());
    }

    /* renamed from: lambda$new$4$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m170xb75d4ffb(Preference preference) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(getActivity(), 1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$51f49cd0_0$ar$ds(getString(R.string.keycombo_menu_reset_keymap));
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(getString(R.string.message_in_reset_keymap_confirm_dialog));
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.reset_button_in_reset_keymap_confirm_dialog, this.resetKeymapConfirmDialogPositive);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
        create.show();
        A11yAlertDialogWrapper.focusCancelButton(create);
        return true;
    }

    /* renamed from: lambda$new$5$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m171x7fbb749a(DialogInterface dialogInterface, int i) {
        this.triggerModifierToBeSet = null;
    }

    /* renamed from: lambda$new$6$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m172x48199939(Preference preference, Object obj) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        String preferenceKeyForTriggerModifier = getKeyComboManager().keyComboModel.getPreferenceKeyForTriggerModifier();
        if ((preference instanceof KeyboardShortcutDialogPreference) && (obj instanceof Long)) {
            KeyboardShortcutDialogPreference keyboardShortcutDialogPreference = (KeyboardShortcutDialogPreference) preference;
            keyboardShortcutDialogPreference.setKeyComboCode(((Long) obj).longValue());
            keyboardShortcutDialogPreference.notifyChanged();
        } else if (preference.getKey() != null && preference.getKey().equals(getString(R.string.pref_select_keymap_key)) && (obj instanceof String)) {
            String str = (String) obj;
            if (this.keymap.equals(str)) {
                return false;
            }
            PreferencesActivityUtils.announceText(String.format(getString(R.string.keycombo_menu_announce_active_keymap), getKeymapName(str)), getActivity());
        } else if (preference.getKey() != null && preference.getKey().equals(preferenceKeyForTriggerModifier) && (obj instanceof String)) {
            this.triggerModifierToBeSet = (String) obj;
            ListPreference listPreference = (ListPreference) preference;
            if (listPreference.getValue().equals(this.triggerModifierToBeSet)) {
                return false;
            }
            CharSequence charSequence = listPreference.getEntries()[listPreference.findIndexOfValue(this.triggerModifierToBeSet)];
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(getContext(), 2);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.keycombo_menu_alert_title_trigger_modifier);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(getString(R.string.keycombo_menu_alert_message_trigger_modifier, charSequence));
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.keycombo_menu_alert_button_trigger_modifier, this.chooseTriggerModifierConfirmDialogPositive);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackKeyboardShortcutPreferenceFragment$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    TalkBackKeyboardShortcutPreferenceFragment.this.m171x7fbb749a(dialogInterface, i);
                }
            });
            A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
            create.show();
            A11yAlertDialogWrapper.focusCancelButton(create);
            return false;
        }
        return true;
    }

    /* renamed from: lambda$updateDialogAndResetKeymapPreference$7$com-google-android-accessibility-talkback-preference-base-TalkBackKeyboardShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m173x457f803d(KeyboardShortcutDialogPreference keyboardShortcutDialogPreference) {
        keyboardShortcutDialogPreference.setEnabled(isServiceActive());
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.Set, java.lang.Object] */
    @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ApplicationModule.getInstance$ar$class_merging$416cc131_0().ApplicationModule$ar$application.add(this.serviceStateChangeListener);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        this.keymap = getKeymap();
        setPreferencesFromResource(getPreferenceResourceId(), str);
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
        this.prefs = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        updatePreference();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Set, java.lang.Object] */
    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.prefs.unregisterOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        ApplicationModule.getInstance$ar$class_merging$416cc131_0().ApplicationModule$ar$application.remove(this.serviceStateChangeListener);
    }
}
