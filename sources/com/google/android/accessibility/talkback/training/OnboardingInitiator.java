package com.google.android.accessibility.talkback.training;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnboardingInitiator {
    public static final /* synthetic */ int OnboardingInitiator$ar$NoOp = 0;
    static final int[] legacyKey = {R.string.pref_update_talkback91_shown_key, R.string.pref_update_welcome_12_2_shown_key, R.string.pref_update_welcome_13_0_shown_key, R.string.pref_update_welcome_13_1_shown_key, R.string.pref_update_welcome_14_0_shown_key, R.string.pref_update_welcome_14_1_shown_key, R.string.pref_update_welcome_14_2_shown_key};

    public static Intent createOnboardingIntent$ar$ds(Context context) {
        return TrainingActivity.createTrainingIntent(context, TrainingConfig.TrainingId.TRAINING_ID_FIRST_RUN_AFTER_UPDATED_ON_BOARDING_TALKBACK, true);
    }

    public static boolean hasOnboardingForMultiFingerGestureSupportBeenShown(SharedPreferences sharedPreferences, Context context) {
        return sharedPreferences.getBoolean(context.getString(R.string.pref_update_multi_finger_gestures_shown_key), false);
    }

    public static boolean hasOnboardingForNewFeaturesBeenShown(SharedPreferences sharedPreferences, Context context) {
        boolean z;
        z = sharedPreferences.getBoolean(context.getResources().getString(R.string.pref_update_welcome_15_0_shown_key), false);
        return z;
    }

    public static void markOnboardingForMultiFingerGesturesAsShown(SharedPreferences sharedPreferences, Context context) {
        sharedPreferences.edit().putBoolean(context.getString(R.string.pref_update_multi_finger_gestures_shown_key), true).apply();
    }

    public static void markOnboardingForNewFeaturesAsShown(SharedPreferences sharedPreferences, Context context) {
        sharedPreferences.edit().putBoolean(context.getString(R.string.pref_update_welcome_15_0_shown_key), true).apply();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int[] iArr = legacyKey;
        for (int i = 0; i < 7; i++) {
            edit.remove(context.getString(iArr[i]));
        }
        edit.apply();
    }
}
