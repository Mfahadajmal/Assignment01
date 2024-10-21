package com.google.android.accessibility.talkback.actor.search;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import androidx.preference.SeekBarPreference;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.libraries.surveys.internal.view.PromptDialogDelegate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SearchScreenOverlay$$ExternalSyntheticLambda13 implements View.OnKeyListener {
    public final /* synthetic */ Object SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0;
    private final /* synthetic */ int switching_field;

    public SearchScreenOverlay$$ExternalSyntheticLambda13(SeekBarPreference seekBarPreference, int i) {
        this.switching_field = i;
        this.SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0 = seekBarPreference;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        int i2 = this.switching_field;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i != 4) {
                    return false;
                }
                PromptDialogDelegate promptDialogDelegate = (PromptDialogDelegate) this.SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0;
                promptDialogDelegate.transmitOtherAccess(promptDialogDelegate.themeContext, promptDialogDelegate.triggerId, promptDialogDelegate.session, SurveyUtils.isPiiCollectionEnabled(promptDialogDelegate.surveyPayload));
                promptDialogDelegate.dialogFragment.dismissAllowingStateLoss();
                if (promptDialogDelegate.ignoreFirstQuestion) {
                    return true;
                }
                return false;
            }
            if (keyEvent.getAction() != 0) {
                return false;
            }
            SeekBarPreference seekBarPreference = (SeekBarPreference) this.SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0;
            if ((!seekBarPreference.mAdjustable && (i == 21 || i == 22)) || i == 23 || i == 66) {
                return false;
            }
            SeekBar seekBar = seekBarPreference.mSeekBar;
            if (seekBar == null) {
                Log.e("SeekBarPreference", "SeekBar view is null and hence cannot be adjusted.");
                return false;
            }
            return seekBar.onKeyDown(i, keyEvent);
        }
        Object obj = this.SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0;
        if (i != 4) {
            if (i == 66) {
                SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) obj;
                if (searchScreenOverlay.keywordEditText.isFocused()) {
                    if (keyEvent.getAction() != 0) {
                        return true;
                    }
                    searchScreenOverlay.performSearch();
                    return true;
                }
            }
            return false;
        }
        ((SearchScreenOverlay) obj).hide();
        return true;
    }

    public /* synthetic */ SearchScreenOverlay$$ExternalSyntheticLambda13(Object obj, int i) {
        this.switching_field = i;
        this.SearchScreenOverlay$$ExternalSyntheticLambda13$ar$f$0 = obj;
    }
}
