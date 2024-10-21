package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import java.util.Locale;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AdvancedSettingsActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AdvancedSettingsFragment extends PreferenceFragmentCompat {
        private ListPreference blinkingIntervalPreference;
        private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 5, null);
        private ListPreference timedMessageDurationPreference;

        private int getQuantity(float f) {
            if (f == 1.0f) {
                return 1;
            }
            return 2;
        }

        private void updatePreferences() {
            NumberFormat numberInstance;
            String format;
            this.timedMessageDurationPreference.setValue(BrailleUserPreferences.readTimedMessageDurationFraction(getContext()));
            String[] availableBlinkingIntervalsMs = BrailleUserPreferences.getAvailableBlinkingIntervalsMs(getContext());
            this.blinkingIntervalPreference.setEntryValues(availableBlinkingIntervalsMs);
            String[] strArr = new String[availableBlinkingIntervalsMs.length];
            numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
            for (int i = 0; i < availableBlinkingIntervalsMs.length; i++) {
                float parseInt = Integer.parseInt(availableBlinkingIntervalsMs[i]);
                Resources resources = getResources();
                float f = parseInt / 1000.0f;
                int quantity = getQuantity(f);
                format = numberInstance.format(f);
                strArr[i] = resources.getQuantityString(R.plurals.bd_blinking_interval, quantity, format);
            }
            this.blinkingIntervalPreference.setEntries(strArr);
            this.blinkingIntervalPreference.setValue(String.valueOf(BrailleUserPreferences.readBlinkingIntervalMs(getContext())));
        }

        /* renamed from: lambda$new$0$com-google-android-accessibility-braille-brailledisplay-settings-AdvancedSettingsActivity$AdvancedSettingsFragment, reason: not valid java name */
        public /* synthetic */ void m41x2823ac43(SharedPreferences sharedPreferences, String str) {
            if (str.equals(getString(R.string.pref_bd_blinking_interval_key))) {
                BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(getContext());
                int readBlinkingIntervalMs = BrailleUserPreferences.readBlinkingIntervalMs(getContext());
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                builder.copyOnWrite();
                BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
                braillebackLogProto$BraillebackExtension.bitField0_ |= 1024;
                braillebackLogProto$BraillebackExtension.blinkFrequencyMs_ = readBlinkingIntervalMs;
                brailleDisplayAnalytics.sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
                return;
            }
            if (str.equals(getString(R.string.pref_bd_reverse_panning_buttons))) {
                BrailleDisplayAnalytics brailleDisplayAnalytics2 = BrailleDisplayAnalytics.getInstance(getContext());
                boolean readReversePanningButtons = BrailleUserPreferences.readReversePanningButtons(getContext());
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                int state$ar$ds$ar$edu = BrailleDisplayAnalytics.getState$ar$ds$ar$edu(readReversePanningButtons);
                builder2.copyOnWrite();
                BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension2 = (BraillebackLogProto$BraillebackExtension) builder2.instance;
                int i = state$ar$ds$ar$edu - 1;
                if (state$ar$ds$ar$edu != 0) {
                    braillebackLogProto$BraillebackExtension2.reversePanningV2_ = i;
                    braillebackLogProto$BraillebackExtension2.bitField0_ |= 16384;
                    brailleDisplayAnalytics2.sendLogs((BraillebackLogProto$BraillebackExtension) builder2.build());
                    return;
                }
                throw null;
            }
            if (str.equals(getString(R.string.pref_bd_timed_message_duration_fraction_key))) {
                int round = Math.round(Float.parseFloat(BrailleUserPreferences.readTimedMessageDurationFraction(getContext())) * 1000.0f);
                BrailleDisplayAnalytics brailleDisplayAnalytics3 = BrailleDisplayAnalytics.getInstance(getContext());
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                builder3.copyOnWrite();
                BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension3 = (BraillebackLogProto$BraillebackExtension) builder3.instance;
                braillebackLogProto$BraillebackExtension3.bitField0_ |= 2048;
                braillebackLogProto$BraillebackExtension3.timedMessageDurationMs_ = round;
                brailleDisplayAnalytics3.sendLogs((BraillebackLogProto$BraillebackExtension) builder3.build());
            }
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.bd_advanced_settings_preferences);
            this.timedMessageDurationPreference = (ListPreference) findPreference(getString(R.string.pref_bd_timed_message_duration_fraction_key));
            this.blinkingIntervalPreference = (ListPreference) findPreference(getString(R.string.pref_bd_blinking_interval_key));
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }

        @Override // android.support.v4.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            updatePreferences();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new AdvancedSettingsFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "AdvancedSettingsActivity";
    }
}
