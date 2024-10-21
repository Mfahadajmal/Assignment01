package com.google.android.accessibility.braille.brailledisplay.settings;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.preference.PreferencesActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleDisplaySettingsActivity extends PreferencesActivity {
    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleDisplaySettingsFragment(AccessibilityServiceCompatUtils$Constants.TALKBACK_SERVICE, new BrailleUserPreferences$$ExternalSyntheticLambda2(1));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity, com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(null);
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
