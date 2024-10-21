package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.brltty.BrailleDisplayProperties;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import j$.util.Collection;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public class KeyBindingsActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class KeyBindingsFragment extends PreferenceFragmentCompat {
        private Map<BrailleKeyBindingUtils.SupportedCommand.Category, Integer> createCategoryMap() {
            EnumMap enumMap = new EnumMap(BrailleKeyBindingUtils.SupportedCommand.Category.class);
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.BASIC, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_basic));
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.NAVIGATION, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_navigation));
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.SYSTEM_ACTIONS, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_system_actions));
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.TALKBACK_FEATURES, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_talkback_features));
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.BRAILLE_SETTINGS, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_braille_settings));
            enumMap.put((EnumMap) BrailleKeyBindingUtils.SupportedCommand.Category.EDITING, (BrailleKeyBindingUtils.SupportedCommand.Category) Integer.valueOf(R.string.pref_key_bd_keybindings_editing));
            return enumMap;
        }

        private boolean isCategoryCommandSupported(BrailleDisplayProperties brailleDisplayProperties, BrailleKeyBindingUtils.SupportedCommand.Category category) {
            return Collection.EL.stream(BrailleKeyBindingUtils.getSupportedCommands(getContext())).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(category, 14)).anyMatch(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(BrailleKeyBindingUtils.getSortedBindingsForDisplay(brailleDisplayProperties), 15));
        }

        public static /* synthetic */ boolean lambda$isCategoryCommandSupported$1(ArrayList arrayList, BrailleKeyBindingUtils.SupportedCommand supportedCommand) {
            if (BrailleKeyBindingUtils.getBrailleKeyBindingForCommand(supportedCommand.command, arrayList) != null) {
                return true;
            }
            return false;
        }

        private void setPreferenceClickListener(BrailleKeyBindingUtils.SupportedCommand.Category category, int i) {
            BrailleDisplayProperties brailleDisplayProperties = (BrailleDisplayProperties) getActivity().getIntent().getParcelableExtra("property_key");
            Preference findPreference = findPreference(getString(i));
            Intent intent = new Intent(getContext(), (Class<?>) KeyBindingsCommandActivity.class);
            intent.putExtra("title", findPreference.getTitle());
            intent.putExtra("type", category);
            intent.putExtra("property_key", brailleDisplayProperties);
            findPreference.setIntent(intent);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.key_bindings);
            BrailleDisplayProperties brailleDisplayProperties = (BrailleDisplayProperties) getActivity().getIntent().getParcelableExtra("property_key");
            for (Map.Entry<BrailleKeyBindingUtils.SupportedCommand.Category, Integer> entry : createCategoryMap().entrySet()) {
                if (brailleDisplayProperties == null) {
                    setPreferenceClickListener(entry.getKey(), entry.getValue().intValue());
                } else if (isCategoryCommandSupported(brailleDisplayProperties, entry.getKey())) {
                    setPreferenceClickListener(entry.getKey(), entry.getValue().intValue());
                } else {
                    Preference findPreference = getPreferenceScreen().findPreference(getString(entry.getValue().intValue()));
                    if (findPreference != null) {
                        findPreference.getParent().removePreference$ar$ds(findPreference);
                    }
                }
            }
        }

        @Override // android.support.v4.app.Fragment
        public boolean onOptionsItemSelected(MenuItem menuItem) {
            if (menuItem.getItemId() != 16908332) {
                return false;
            }
            Intent intent = new Intent(getContext(), (Class<?>) BrailleDisplaySettingsActivity.class);
            intent.addFlags(67108864);
            startActivity(intent);
            return true;
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new KeyBindingsFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "KeyBindingsActivity";
    }
}
