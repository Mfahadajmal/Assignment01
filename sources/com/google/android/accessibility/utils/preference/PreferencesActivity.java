package com.google.android.accessibility.utils.preference;

import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PreferencesActivity extends BasePreferencesActivity {
    protected abstract PreferenceFragmentCompat createPreferenceFragment();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getContainerId() {
        if (supportHatsSurvey()) {
            return R.id.preference_root;
        }
        return R.id.content_frame;
    }

    protected String getFragmentTag() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        prepareActionBar(null);
        if (supportHatsSurvey()) {
            setContentView(R.layout.preference_with_survey);
        }
        PreferenceFragmentCompat createPreferenceFragment = createPreferenceFragment();
        if (createPreferenceFragment != null && bundle == null) {
            BackStackRecord backStackRecord = new BackStackRecord(getSupportFragmentManager());
            backStackRecord.replace$ar$ds(getContainerId(), createPreferenceFragment, getFragmentTag());
            backStackRecord.addToBackStack$ar$ds(null);
            backStackRecord.commit();
        }
    }

    @Override // com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.app.Activity
    public final boolean onNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finishAfterTransition();
        }
        return true;
    }

    protected boolean supportHatsSurvey() {
        return false;
    }
}
