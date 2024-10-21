package com.google.android.accessibility.talkback.preference.base;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceActionHelper {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum WebPage {
        WEB_PAGE_PRIVACY_POLICY(R.string.privacy_policy_url),
        WEB_PAGE_TERMS_OF_SERVICE(R.string.tos_url);

        final int urlRes;

        WebPage(int i) {
            this.urlRes = i;
        }
    }

    private PreferenceActionHelper() {
    }

    public static void assignWebIntentToPreference(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference, WebPage webPage) {
        SpannableUtils$IdentifierSpan.assignWebIntentToPreference(preferenceFragmentCompat, preference, preferenceFragmentCompat.getString(webPage.urlRes));
    }
}
