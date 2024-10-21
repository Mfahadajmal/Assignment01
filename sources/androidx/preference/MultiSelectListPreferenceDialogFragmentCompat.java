package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {
    CharSequence[] mEntries;
    CharSequence[] mEntryValues;
    final Set mNewValues = new HashSet();
    boolean mPreferenceChanged;

    private final MultiSelectListPreference getListPreference() {
        return (MultiSelectListPreference) getPreference();
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            MultiSelectListPreference listPreference = getListPreference();
            if (listPreference.mEntries != null && listPreference.mEntryValues != null) {
                this.mNewValues.clear();
                this.mNewValues.addAll(listPreference.mValues);
                this.mPreferenceChanged = false;
                this.mEntries = listPreference.mEntries;
                this.mEntryValues = listPreference.mEntryValues;
                return;
            }
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        this.mNewValues.clear();
        this.mNewValues.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
        this.mPreferenceChanged = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
        this.mEntries = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
        this.mEntryValues = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void onDialogClosed(boolean z) {
        if (z && this.mPreferenceChanged) {
            MultiSelectListPreference listPreference = getListPreference();
            if (listPreference.callChangeListener(this.mNewValues)) {
                listPreference.setValues(this.mNewValues);
            }
        }
        this.mPreferenceChanged = false;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    protected final void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        int length = this.mEntryValues.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = this.mNewValues.contains(this.mEntryValues[i].toString());
        }
        builder.setMultiChoiceItems$ar$ds(this.mEntries, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: androidx.preference.MultiSelectListPreferenceDialogFragmentCompat.1
            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
            public final void onClick(DialogInterface dialogInterface, int i2, boolean z) {
                MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat = MultiSelectListPreferenceDialogFragmentCompat.this;
                if (z) {
                    multiSelectListPreferenceDialogFragmentCompat.mPreferenceChanged = multiSelectListPreferenceDialogFragmentCompat.mNewValues.add(multiSelectListPreferenceDialogFragmentCompat.mEntryValues[i2].toString()) | multiSelectListPreferenceDialogFragmentCompat.mPreferenceChanged;
                } else {
                    multiSelectListPreferenceDialogFragmentCompat.mPreferenceChanged = multiSelectListPreferenceDialogFragmentCompat.mNewValues.remove(multiSelectListPreferenceDialogFragmentCompat.mEntryValues[i2].toString()) | multiSelectListPreferenceDialogFragmentCompat.mPreferenceChanged;
                }
            }
        });
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList<>(this.mNewValues));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.mPreferenceChanged);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.mEntries);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.mEntryValues);
    }
}
