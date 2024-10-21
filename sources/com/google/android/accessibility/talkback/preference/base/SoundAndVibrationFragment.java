package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.widget.SeekBar;
import androidx.core.view.ViewCompat;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SoundAndVibrationFragment extends TalkbackBaseFragment {
    private SharedPreferences prefs;
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener;

    public SoundAndVibrationFragment() {
        super(R.xml.sound_and_vibration_preferences);
        this.sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.SoundAndVibrationFragment$$ExternalSyntheticLambda0
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                SoundAndVibrationFragment.this.m158xc3d08126(sharedPreferences, str);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$onCreatePreferences$1(AudioManager audioManager, Preference preference, Object obj) {
        audioManager.setStreamVolume(10, ((Integer) obj).intValue(), 0);
        return true;
    }

    private void updateSpeechRatePreference() {
        FragmentActivity activity = getActivity();
        activity.getClass();
        Preference findPreference = SpannableUtils$IdentifierSpan.findPreference(activity, getActivity().getString(R.string.pref_speech_rate_seekbar_key_int));
        if (findPreference instanceof SpeechRatePreference) {
            int intPref = SpannableUtils$IdentifierSpan.getIntPref(SpannableUtils$IdentifierSpan.getSharedPreferences(getActivity().getApplicationContext()), getActivity().getResources(), R.string.pref_speech_rate_seekbar_key_int, R.integer.pref_speech_rate_default_int);
            SpeechRatePreference speechRatePreference = (SpeechRatePreference) findPreference;
            speechRatePreference.setValue(intPref);
            SeekBar seekBar = speechRatePreference.getSeekBar();
            if (seekBar != null) {
                ViewCompat.setStateDescription(seekBar, getActivity().getString(R.string.template_percent, new Object[]{String.valueOf(intPref)}));
            }
        }
    }

    private static void updateTwoStatePreferenceStatus(FragmentActivity fragmentActivity, int i, int i2) {
        Preference findPreference = SpannableUtils$IdentifierSpan.findPreference(fragmentActivity, fragmentActivity.getString(i));
        if (findPreference instanceof TwoStatePreference) {
            ((TwoStatePreference) findPreference).setChecked(SpannableUtils$IdentifierSpan.getBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(fragmentActivity.getApplicationContext()), fragmentActivity.getResources(), i, i2));
        }
    }

    private void updateVolumePreferenceValue() {
        FragmentActivity activity = getActivity();
        activity.getClass();
        Preference findPreference = SpannableUtils$IdentifierSpan.findPreference(activity, getActivity().getString(R.string.pref_a11y_volume_key));
        if (findPreference instanceof AccessibilitySeekBarPreference) {
            ((AccessibilitySeekBarPreference) findPreference).setValue(SpannableUtils$IdentifierSpan.getIntPref(SpannableUtils$IdentifierSpan.getSharedPreferences(getActivity().getApplicationContext()), getActivity().getResources(), R.string.pref_a11y_volume_key, R.integer.pef_default_a11y_volume));
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        int i;
        if (true != FeatureSupport.isVibratorSupported(getContext())) {
            i = R.string.title_pref_sound;
        } else {
            i = R.string.title_pref_sound_and_vibration;
        }
        return getText(i);
    }

    /* renamed from: lambda$new$0$com-google-android-accessibility-talkback-preference-base-SoundAndVibrationFragment, reason: not valid java name */
    public /* synthetic */ void m158xc3d08126(SharedPreferences sharedPreferences, String str) {
        FragmentActivity activity = getActivity();
        if (TextUtils.equals(str, getString(R.string.pref_use_audio_focus_key))) {
            updateTwoStatePreferenceStatus(activity, R.string.pref_use_audio_focus_key, R.bool.pref_use_audio_focus_default);
            return;
        }
        if (TextUtils.equals(str, getString(R.string.pref_soundback_key))) {
            updateTwoStatePreferenceStatus(activity, R.string.pref_soundback_key, R.bool.pref_soundback_default);
            return;
        }
        if (TextUtils.equals(str, getString(R.string.pref_vibration_key))) {
            if (FeatureSupport.isVibratorSupported(getContext())) {
                updateTwoStatePreferenceStatus(activity, R.string.pref_vibration_key, R.bool.pref_vibration_default);
            }
        } else if (TextUtils.equals(str, getString(R.string.pref_a11y_volume_key))) {
            updateVolumePreferenceValue();
        } else if (TextUtils.equals(str, getString(R.string.pref_speech_rate_seekbar_key_int))) {
            updateSpeechRatePreference();
        }
    }

    /* renamed from: lambda$onCreatePreferences$2$com-google-android-accessibility-talkback-preference-base-SoundAndVibrationFragment, reason: not valid java name */
    public /* synthetic */ boolean m159xe8a01d05(Preference preference, Object obj) {
        this.prefs.edit().putString(getActivity().getString(R.string.pref_speech_rate_key), Float.toString(((Integer) obj).floatValue() / 100.0f)).apply();
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        int streamMinVolume;
        boolean z;
        super.onCreatePreferences(bundle, str);
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
        FragmentActivity activity = getActivity();
        activity.getClass();
        Preference findPreference = SpannableUtils$IdentifierSpan.findPreference(activity, getActivity().getString(R.string.pref_a11y_volume_key));
        boolean z2 = false;
        if (findPreference instanceof AccessibilitySeekBarPreference) {
            AccessibilitySeekBarPreference accessibilitySeekBarPreference = (AccessibilitySeekBarPreference) findPreference;
            final AudioManager audioManager = (AudioManager) getActivity().getSystemService("audio");
            accessibilitySeekBarPreference.setMax(audioManager.getStreamMaxVolume(10));
            streamMinVolume = audioManager.getStreamMinVolume(10);
            accessibilitySeekBarPreference.setMin(streamMinVolume);
            accessibilitySeekBarPreference.setValue(audioManager.getStreamVolume(10));
            accessibilitySeekBarPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.SoundAndVibrationFragment$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    SoundAndVibrationFragment.lambda$onCreatePreferences$1(audioManager, preference, obj);
                    return true;
                }
            });
            Context context = getContext();
            context.getClass();
            if (TalkBackService.getServiceState() == 1) {
                z = true;
            } else {
                z = false;
            }
            setEnabled(context, R.string.pref_a11y_volume_key, z);
        }
        FragmentActivity activity2 = getActivity();
        activity2.getClass();
        Preference findPreference2 = SpannableUtils$IdentifierSpan.findPreference(activity2, getActivity().getString(R.string.pref_speech_rate_seekbar_key_int));
        if (findPreference2 instanceof SpeechRatePreference) {
            SpeechRatePreference speechRatePreference = (SpeechRatePreference) findPreference2;
            speechRatePreference.setMax(600);
            speechRatePreference.setMin(10);
            speechRatePreference.setValue((int) (SpannableUtils$IdentifierSpan.getFloatFromStringPref(SpannableUtils$IdentifierSpan.getSharedPreferences(getActivity()), getActivity().getResources(), R.string.pref_speech_rate_key, R.string.pref_speech_rate_default) * 100.0f));
            speechRatePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.SoundAndVibrationFragment$$ExternalSyntheticLambda2
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return SoundAndVibrationFragment.this.m159xe8a01d05(preference, obj);
                }
            });
            Context context2 = getContext();
            context2.getClass();
            if (TalkBackService.getServiceState() == 1) {
                z2 = true;
            }
            setEnabled(context2, R.string.pref_speech_rate_seekbar_key_int, z2);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.prefs.unregisterOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        boolean z;
        super.onResume();
        FragmentActivity activity = getActivity();
        this.prefs.registerOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        updateTwoStatePreferenceStatus(activity, R.string.pref_use_audio_focus_key, R.bool.pref_use_audio_focus_default);
        updateTwoStatePreferenceStatus(activity, R.string.pref_soundback_key, R.bool.pref_soundback_default);
        if (FeatureSupport.isVibratorSupported(getContext())) {
            updateTwoStatePreferenceStatus(activity, R.string.pref_vibration_key, R.bool.pref_vibration_default);
        }
        updateVolumePreferenceValue();
        Context context = getContext();
        context.getClass();
        boolean z2 = false;
        if (TalkBackService.getServiceState() == 1) {
            z = true;
        } else {
            z = false;
        }
        setEnabled(context, R.string.pref_a11y_volume_key, z);
        updateSpeechRatePreference();
        Context context2 = getContext();
        context2.getClass();
        if (TalkBackService.getServiceState() == 1) {
            z2 = true;
        }
        setEnabled(context2, R.string.pref_speech_rate_seekbar_key_int, z2);
    }
}
