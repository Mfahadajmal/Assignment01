package com.google.android.accessibility.talkback.trainingcommon.tv;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.accessibility.talkback.actor.search.SearchScreenOverlay;
import com.google.android.marvin.talkback.R;
import com.google.android.material.textfield.ClearTextEndIconDelegate;
import com.google.android.material.textfield.DropdownMenuEndIconDelegate;
import com.google.android.material.textfield.EndIconDelegate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TvNavigationButton$$ExternalSyntheticLambda0 implements View.OnFocusChangeListener {
    public final /* synthetic */ Object TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TvNavigationButton$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        int i;
        int i2 = this.switching_field;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        Object obj = this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0;
                        DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = (DropdownMenuEndIconDelegate) obj;
                        dropdownMenuEndIconDelegate.editTextHasFocus = z;
                        ((EndIconDelegate) obj).refreshIconState();
                        if (!z) {
                            dropdownMenuEndIconDelegate.setEndIconChecked(false);
                            dropdownMenuEndIconDelegate.dropdownPopupDirty = false;
                            return;
                        }
                        return;
                    }
                    ClearTextEndIconDelegate clearTextEndIconDelegate = (ClearTextEndIconDelegate) this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0;
                    clearTextEndIconDelegate.animateIcon(clearTextEndIconDelegate.shouldBeVisible());
                    return;
                }
                if (true != z) {
                    i = R.color.a11y_alert_dialog_button_color;
                } else {
                    i = R.color.a11y_alert_dialog_button_focused_color;
                }
                ((Button) view).setTextColor(ContextCompat$Api23Impl.getColor((Context) this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0, i));
                return;
            }
            SearchScreenOverlay searchScreenOverlay = (SearchScreenOverlay) this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0;
            InputMethodManager inputMethodManager = (InputMethodManager) searchScreenOverlay.service.getSystemService("input_method");
            if (z) {
                inputMethodManager.showSoftInput(searchScreenOverlay.keywordEditText, 1);
                return;
            } else {
                inputMethodManager.hideSoftInputFromWindow(searchScreenOverlay.keywordEditText.getWindowToken(), 1);
                return;
            }
        }
        Object obj2 = this.TvNavigationButton$$ExternalSyntheticLambda0$ar$f$0;
        if (z) {
            TvNavigationButton tvNavigationButton = (TvNavigationButton) obj2;
            tvNavigationButton.setBackgroundResource(R.drawable.tv_training_button_focused);
            tvNavigationButton.setTextColor(tvNavigationButton.getResources().getColor(R.color.tv_training_button_focused_text_color, tvNavigationButton.getContext().getTheme()));
            tvNavigationButton.startScaleAnimation(tvNavigationButton.getScaleX(), tvNavigationButton.scaleFocused);
            return;
        }
        ((TvNavigationButton) obj2).onBlur();
    }
}
