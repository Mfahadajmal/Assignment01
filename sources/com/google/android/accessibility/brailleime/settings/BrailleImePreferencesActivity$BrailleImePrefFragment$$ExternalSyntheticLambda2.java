package com.google.android.accessibility.brailleime.settings;

import android.content.Context;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.marvin.talkback.R;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2 implements Preference.SummaryProvider {
    public final /* synthetic */ Object BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2(Object obj, int i) {
        this.switching_field = i;
        this.BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2$ar$f$0 = obj;
    }

    @Override // androidx.preference.Preference.SummaryProvider
    public final CharSequence provideSummary(Preference preference) {
        if (this.switching_field != 0) {
            Context context = (Context) this.BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2$ar$f$0;
            List readAvailablePreferredCodes = BrailleUserPreferences.readAvailablePreferredCodes(context);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < readAvailablePreferredCodes.size(); i++) {
                String userFacingName = ((BrailleLanguages.Code) readAvailablePreferredCodes.get(i)).getUserFacingName(context);
                if (i == 0) {
                    sb.append(userFacingName);
                } else {
                    sb.append(context.getResources().getString(R.string.split_comma, userFacingName));
                }
            }
            return sb;
        }
        return ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.BrailleImePreferencesActivity$BrailleImePrefFragment$$ExternalSyntheticLambda2$ar$f$0).m82xd34d01c5(preference);
    }
}
