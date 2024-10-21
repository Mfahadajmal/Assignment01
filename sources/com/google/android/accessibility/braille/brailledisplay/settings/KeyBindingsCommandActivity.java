package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.braille.brltty.BrailleKeyBinding;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.interfaces.BrailleCharacter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.Collection;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class KeyBindingsCommandActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class KeyBindingsCommandFragment extends PreferenceFragmentCompat {
        private Connectioneer connectioneer;
        private Preference descriptionPreference;
        private BrailleKeyBindingUtils.SupportedCommand.Category supportedCommandCategory;
        private final Connectioneer.AspectDisplayProperties.Callback displayPropertyCallback = new AnonymousClass1(this, 0);
        private final Connectioneer.AspectConnection.Callback connectionCallback = new BrailleDisplaySettingsFragment.AnonymousClass4(this, 2);

        /* compiled from: PG */
        /* renamed from: com.google.android.accessibility.braille.brailledisplay.settings.KeyBindingsCommandActivity$KeyBindingsCommandFragment$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Connectioneer.AspectDisplayProperties.Callback {
            final /* synthetic */ PreferenceFragmentCompat KeyBindingsCommandActivity$KeyBindingsCommandFragment$1$ar$this$0;
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(PreferenceFragmentCompat preferenceFragmentCompat, int i) {
                this.switching_field = i;
                this.KeyBindingsCommandActivity$KeyBindingsCommandFragment$1$ar$this$0 = preferenceFragmentCompat;
            }

            @Override // com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer.AspectDisplayProperties.Callback
            public final void onDisplayPropertiesArrived(BrailleDisplayProperties brailleDisplayProperties) {
                if (this.switching_field != 0) {
                    ((BrailleDisplaySettingsFragment) this.KeyBindingsCommandActivity$KeyBindingsCommandFragment$1$ar$this$0).onModelChanged();
                } else {
                    ((KeyBindingsCommandFragment) this.KeyBindingsCommandActivity$KeyBindingsCommandFragment$1$ar$this$0).refresh(brailleDisplayProperties);
                }
            }
        }

        private void addPreference(PreferenceScreen preferenceScreen, BrailleKeyBindingUtils.SupportedCommand.Subcategory subcategory, Preference preference) {
            String string;
            if (subcategory == BrailleKeyBindingUtils.SupportedCommand.Subcategory.UNDEFINED) {
                preferenceScreen.addPreference$ar$ds(preference);
                return;
            }
            PreferenceCategory preferenceCategory = (PreferenceCategory) preferenceScreen.getPreferenceManager().findPreference(subcategory.name());
            if (preferenceCategory == null) {
                preferenceCategory = new PreferenceCategory(getContext());
                preferenceScreen.addPreference$ar$ds(preferenceCategory);
                preferenceCategory.setKey(subcategory.name());
                Resources resources = getResources();
                int i = subcategory.titleRes;
                if (i == 0) {
                    string = "";
                } else {
                    string = resources.getString(i);
                }
                preferenceCategory.setTitle(string);
            }
            preferenceCategory.addPreference$ar$ds(preference);
        }

        private SpannableString changeTextColor(CharSequence charSequence, int i) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(i, null)), 0, charSequence.length(), 33);
            return spannableString;
        }

        private Preference createPreference(CharSequence charSequence, CharSequence charSequence2) {
            String string = getResources().getString(R.string.bd_commands_description_template, charSequence, charSequence2);
            Preference preference = new Preference(getContext());
            preference.setTitle(changeTextColor(string, R.color.settings_primary_text));
            preference.setSelectable(false);
            return preference;
        }

        private String getKeyDescription(BrailleKeyBindingUtils.SupportedCommand supportedCommand, BrailleDisplayProperties brailleDisplayProperties) {
            if (brailleDisplayProperties == null) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.v("KeyBindingCommandActivity", "no property");
                return supportedCommand.getKeyDescription(getResources());
            }
            BrailleKeyBinding brailleKeyBindingForCommand = BrailleKeyBindingUtils.getBrailleKeyBindingForCommand(supportedCommand.command, BrailleKeyBindingUtils.getSortedBindingsForDisplay(brailleDisplayProperties));
            if (brailleKeyBindingForCommand == null) {
                return "";
            }
            Map map = brailleDisplayProperties.friendlyKeyNames;
            Context context = getContext();
            ArrayList<String> newArrayList = ContextDataProvider.newArrayList(brailleKeyBindingForCommand.keyNames);
            ArrayList arrayList = new ArrayList();
            for (String str : newArrayList) {
                String str2 = (String) map.get(str);
                if (str2 != null) {
                    arrayList.add(str2);
                } else {
                    arrayList.add(str);
                }
            }
            BrailleCharacter brailleCharacter = new BrailleCharacter();
            if (AppCompatDelegate.Api33Impl.dotKeyStringsToDotCache == null) {
                AppCompatDelegate.Api33Impl.dotKeyStringsToDotCache = new LinkedHashMap();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot1), BrailleCharacter.DOT1);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot2), BrailleCharacter.DOT2);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot3), BrailleCharacter.DOT3);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot4), BrailleCharacter.DOT4);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot5), BrailleCharacter.DOT5);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot6), BrailleCharacter.DOT6);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot7), BrailleCharacter.DOT7);
                linkedHashMap.put(Integer.valueOf(R.string.key_Dot8), BrailleCharacter.DOT8);
                for (Integer num : linkedHashMap.keySet()) {
                    AppCompatDelegate.Api33Impl.dotKeyStringsToDotCache.put(context.getString(num.intValue()), (BrailleCharacter) linkedHashMap.get(num));
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (AppCompatDelegate.Api33Impl.dotKeyStringsToDotCache.containsKey(str3)) {
                    brailleCharacter.dotNumbers.or(((BrailleCharacter) AppCompatDelegate.Api33Impl.dotKeyStringsToDotCache.get(str3)).dotNumbers);
                    it.remove();
                }
            }
            if (!brailleCharacter.isEmpty()) {
                arrayList.add(BrailleKeyBindingUtils.getDotsDescription(context.getResources(), brailleCharacter));
            }
            String changeToSentence = BrailleKeyBindingUtils.changeToSentence(context.getResources(), (String[]) Collection.EL.stream(arrayList).toArray(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(1)));
            if (brailleKeyBindingForCommand.isLongPress()) {
                return context.getString(R.string.bd_commands_touch_and_hold_template, changeToSentence);
            }
            return context.getString(R.string.bd_commands_press_template, changeToSentence);
        }

        public void refresh(BrailleDisplayProperties brailleDisplayProperties) {
            int preferenceCount = getPreferenceScreen().getPreferenceCount();
            while (true) {
                preferenceCount--;
                if (preferenceCount < 0) {
                    break;
                }
                Preference preference = getPreferenceScreen().getPreference(preferenceCount);
                if (!Objects.equals(preference.getKey(), this.descriptionPreference.getKey())) {
                    getPreferenceScreen().removePreference$ar$ds(preference);
                }
            }
            for (BrailleKeyBindingUtils.SupportedCommand supportedCommand : BrailleKeyBindingUtils.getSupportedCommands(getContext())) {
                if (supportedCommand.category.equals(this.supportedCommandCategory)) {
                    String keyDescription = getKeyDescription(supportedCommand, brailleDisplayProperties);
                    if (!TextUtils.isEmpty(keyDescription)) {
                        addPreference(getPreferenceScreen(), supportedCommand.subcategory, createPreference(getResources().getString(supportedCommand.commandDescriptionRes), keyDescription));
                    }
                }
            }
        }

        @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.connectioneer = Connectioneer.getInstance$ar$class_merging$67dd8c5b_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new WindowTrackerFactory(getContext().getApplicationContext(), new BrailleUserPreferences$$ExternalSyntheticLambda2(1)));
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            String string;
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.key_bindings_command);
            Intent intent = getActivity().getIntent();
            if (intent.getExtras() == null) {
                return;
            }
            getActivity().setTitle(intent.getStringExtra("title"));
            BrailleKeyBindingUtils.SupportedCommand.Category category = (BrailleKeyBindingUtils.SupportedCommand.Category) intent.getSerializableExtra("type");
            this.supportedCommandCategory = category;
            Resources resources = getResources();
            int i = category.descriptionRes;
            if (i == 0) {
                string = "";
            } else {
                string = resources.getString(i);
            }
            this.descriptionPreference = findPreference(getString(R.string.pref_key_bd_keybindings_editing_description));
            if (TextUtils.isEmpty(string)) {
                getPreferenceScreen().removePreference$ar$ds(this.descriptionPreference);
            } else {
                this.descriptionPreference.setSummary(string);
            }
            refresh((BrailleDisplayProperties) intent.getParcelableExtra("property_key"));
        }

        @Override // android.support.v4.app.Fragment
        public void onPause() {
            super.onPause();
            this.connectioneer.aspectDisplayProperties.detach$ar$ds(this.displayPropertyCallback);
            this.connectioneer.aspectConnection.detach$ar$ds(this.connectionCallback);
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            this.connectioneer.aspectDisplayProperties.attach(this.displayPropertyCallback);
            this.connectioneer.aspectConnection.attach(this.connectionCallback);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new KeyBindingsCommandFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "KeyBindingCommandActivity";
    }
}
