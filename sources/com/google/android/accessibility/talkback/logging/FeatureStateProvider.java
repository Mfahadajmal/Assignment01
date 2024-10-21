package com.google.android.accessibility.talkback.logging;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.SpeechConfig;
import googledata.experiments.mobile.accessibility_suite.features.TalkbackCompositor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeatureStateProvider {
    public volatile int featureStates = 0;
    public final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;
    public final SharedPreferences sharedPreferences;

    public FeatureStateProvider(final Context context, final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        if (SpannableUtils$NonCopyableTextSpan.enableAggressiveChunking(context)) {
            enableFeatureFlag(1);
        }
        if (SpeechConfig.removeUnnecessarySpans(context)) {
            enableFeatureFlag(2);
        }
        if (TalkbackCompositor.enablePeriodAsSeparator(context)) {
            enableFeatureFlag(4);
        }
        if (SpeechConfig.enableCacheTtsLocale(context)) {
            enableFeatureFlag(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
        }
        if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, context.getResources(), R.string.pref_punctuation_key, R.bool.pref_punctuation_default)) {
            enableFeatureFlag(8);
        }
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.logging.FeatureStateProvider$$ExternalSyntheticLambda0
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences2, String str) {
                Context context2 = context;
                if (context2.getResources().getString(R.string.pref_punctuation_key).equals(str)) {
                    SharedPreferences sharedPreferences3 = sharedPreferences;
                    FeatureStateProvider featureStateProvider = FeatureStateProvider.this;
                    if (SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences3, context2.getResources(), R.string.pref_punctuation_key, R.bool.pref_punctuation_default)) {
                        featureStateProvider.enableFeatureFlag(8);
                    } else {
                        featureStateProvider.featureStates &= -9;
                    }
                }
            }
        };
        this.sharedPreferenceChangeListener = onSharedPreferenceChangeListener;
        sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public final void enableFeatureFlag(int i) {
        this.featureStates = i | this.featureStates;
    }
}
