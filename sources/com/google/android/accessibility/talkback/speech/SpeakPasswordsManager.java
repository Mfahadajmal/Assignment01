package com.google.android.accessibility.talkback.speech;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.SnackbarManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeakPasswordsManager {
    private final Context context;
    public final GlobalVariables globalVariables;
    final HapticPatternParser$$ExternalSyntheticLambda1 headphoneChangeListener$ar$class_merging$ar$class_merging$ar$class_merging;
    public final SnackbarManager headphoneStateMonitor$ar$class_merging$ar$class_merging;
    public boolean headphonesConnected;

    public SpeakPasswordsManager(Context context, SnackbarManager snackbarManager, GlobalVariables globalVariables) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.headphoneChangeListener$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.context = context;
        this.headphoneStateMonitor$ar$class_merging$ar$class_merging = snackbarManager;
        this.globalVariables = globalVariables;
        this.headphonesConnected = SnackbarManager.isHeadphoneOn(context);
        snackbarManager.SnackbarManager$ar$currentSnackbar = hapticPatternParser$$ExternalSyntheticLambda1;
        Object obj = snackbarManager.SnackbarManager$ar$currentSnackbar;
        if (obj != null) {
            ((HapticPatternParser$$ExternalSyntheticLambda1) obj).onHeadphoneStateChanged(snackbarManager.hasHeadphones());
        }
    }

    public static boolean getAlwaysSpeakPasswordsPref(Context context) {
        return SpannableUtils$IdentifierSpan.getBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(context), context.getResources(), R.string.pref_speak_passwords_without_headphones, R.bool.pref_speak_passwords_without_headphones_default);
    }

    public final boolean shouldSpeakPasswords() {
        if (getAlwaysSpeakPasswordsPref(this.context)) {
            return true;
        }
        return this.headphonesConnected;
    }
}
