package com.google.android.material.datepicker;

import android.support.v4.app.Fragment;
import java.util.LinkedHashSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class PickerFragment extends Fragment {
    protected final LinkedHashSet onSelectionChangedListeners = new LinkedHashSet();

    public boolean addOnSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        return this.onSelectionChangedListeners.add(onSelectionChangedListener);
    }
}
