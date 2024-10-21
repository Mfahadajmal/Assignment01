package com.google.android.accessibility.brailleime.settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.brailleime.BrailleImeActions;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleImeGestureActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BrailleImeGestureFragment extends PreferenceFragmentCompat {
        private void setPreferenceClickListener(int i, BrailleImeActions.Category category) {
            Preference findPreference = findPreference(getString(i));
            Intent intent = new Intent(getContext(), (Class<?>) BrailleImeGestureCommandActivity.class);
            intent.putExtra("category", category);
            findPreference.setIntent(intent);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.brailleime_gesture_category);
            setPreferenceClickListener(R.string.pref_key_brailleime_basic_controls, BrailleImeActions.Category.BASIC);
            setPreferenceClickListener(R.string.pref_key_brailleime_cursor_movement, BrailleImeActions.Category.CURSOR_MOVEMENT);
            setPreferenceClickListener(R.string.pref_key_brailleime_text_selection_and_editing, BrailleImeActions.Category.TEXT_SELECTION_AND_EDITING);
            setPreferenceClickListener(R.string.pref_key_brailleime_spell_check, BrailleImeActions.Category.SPELL_CHECK);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleImeGestureFragment();
    }
}
