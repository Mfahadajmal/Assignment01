package com.google.android.accessibility.braille.brailledisplay.settings;

import androidx.preference.Preference;
import com.google.android.accessibility.braille.brailledisplay.settings.AutoScrollActivity;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.activities.S2SDeveloperSettingsPreferenceActivity;
import com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12 implements Preference.OnPreferenceChangeListener {
    public final /* synthetic */ Object BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0;
    private final /* synthetic */ int switching_field;

    public BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment selectToSpeakPreferenceFragment, int i) {
        this.switching_field = i;
        this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0 = selectToSpeakPreferenceFragment;
    }

    /* JADX WARN: Type inference failed for: r0v11, types: [androidx.preference.Preference$OnPreferenceChangeListener, java.lang.Object] */
    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        switch (this.switching_field) {
            case 0:
                return ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m58x6eb07857(preference, obj);
            case 1:
                ((AutoScrollActivity.AutoScrollFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m43x1f0b43e6(preference, obj);
                return true;
            case 2:
                return ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m57x5ecf6e27(preference, obj);
            case 3:
                return ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m59xc4f301d6(preference, obj);
            case 4:
                return ((BrailleDisplaySettingsFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m60xf4aa35d7(preference, obj);
            case 5:
                this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0.onPreferenceChange(preference, obj);
                return false;
            case 6:
                return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m83x1377e886(preference, obj);
            case 7:
                return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m77x1220b27e(preference, obj);
            case 8:
                return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).m78x524b993f(preference, obj);
            case 9:
                S2SDeveloperSettingsPreferenceActivity.S2SDeveloperSettingsPreferenceFragment.m86$r8$lambda$WV84ZfDaumbgMlH_fH482NY_X0((S2SDeveloperSettingsPreferenceActivity.S2SDeveloperSettingsPreferenceFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0, preference, obj);
                return true;
            case 10:
                S2SDeveloperSettingsPreferenceActivity.S2SDeveloperSettingsPreferenceFragment.$r8$lambda$8sUfpu1M_YoPWKO73OthASijNmc((S2SDeveloperSettingsPreferenceActivity.S2SDeveloperSettingsPreferenceFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0, preference, obj);
                return true;
            default:
                SelectToSpeakService selectToSpeakService = SelectToSpeakService.getInstance();
                if (selectToSpeakService != null) {
                    ScreenCapturePermissionHelper screenCapturePermissionHelper = selectToSpeakService.screenCapturePermissionHelper;
                    if (screenCapturePermissionHelper.isAuthorizedForScreenCapture()) {
                        screenCapturePermissionHelper.deauthorizeCapture();
                        SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment.m87$$Nest$fgetocrPreference((SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0).setChecked(false);
                        SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment.m89$$Nest$msaveOCRPreference((SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0, false);
                    } else {
                        screenCapturePermissionHelper.requestForPermission(SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment.m88$$Nest$fgetscreenCapturePermissionListener((SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment) this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0));
                    }
                }
                return false;
        }
    }

    public /* synthetic */ BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(Object obj, int i) {
        this.switching_field = i;
        this.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12$ar$f$0 = obj;
    }
}
