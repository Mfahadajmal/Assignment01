package com.google.android.accessibility.braille.brailledisplay.settings;

import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.common.collect.ImmutableList;
import java.util.function.IntFunction;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13 implements IntFunction {
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleDisplaySettingsFragment$$ExternalSyntheticLambda13(int i) {
        this.switching_field = i;
    }

    @Override // java.util.function.IntFunction
    public final Object apply(int i) {
        switch (this.switching_field) {
            case 0:
                return BrailleDisplaySettingsFragment.lambda$showPermissionsDialogIfNecessary$15(i);
            case 1:
                return new String[i];
            case 2:
                return BrailleDisplaySettingsFragment.lambda$constructBannerPreference$2(i);
            case 3:
                return new CharSequence[i];
            case 4:
                return new CharSequence[i];
            case 5:
                ImmutableList immutableList = ContextMenuDialog.ITEM_STRING_IDS;
                return new CharSequence[i];
            case 6:
                return BrailleImePreferencesActivity.BrailleImePrefFragment.lambda$configurePrefs$5(i);
            default:
                return BrailleImePreferencesActivity.BrailleImePrefFragment.lambda$configurePrefs$7(i);
        }
    }
}
