package com.google.android.material.datepicker;

import android.os.Parcelable;
import android.view.View;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface DateSelector extends Parcelable {
    int getDefaultThemeResId$ar$ds();

    Collection getSelectedDays();

    Collection getSelectedRanges();

    Object getSelection();

    String getSelectionContentDescription$ar$ds();

    String getSelectionDisplayString$ar$ds();

    boolean isSelectionComplete();

    View onCreateTextInputView$ar$ds();

    void select$ar$ds();
}
