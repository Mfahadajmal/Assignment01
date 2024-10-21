package androidx.preference;

import android.content.Context;
import android.support.v7.widget.DropDownListView;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DropDownPreference extends ListPreference {
    private final ArrayAdapter mAdapter;
    private final Context mContext;
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    private Spinner mSpinner;

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.dropdownPreferenceStyle, 0);
        this.mItemSelectedListener = new AnonymousClass1(this, 0);
        this.mContext = context;
        this.mAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item);
        updateEntries();
    }

    private final void updateEntries() {
        this.mAdapter.clear();
        if (getEntries() != null) {
            for (CharSequence charSequence : getEntries()) {
                this.mAdapter.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void notifyChanged() {
        super.notifyChanged();
        ArrayAdapter arrayAdapter = this.mAdapter;
        if (arrayAdapter != null) {
            arrayAdapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        Spinner spinner = (Spinner) preferenceViewHolder.itemView.findViewById(R.id.spinner);
        this.mSpinner = spinner;
        spinner.setAdapter((SpinnerAdapter) this.mAdapter);
        this.mSpinner.setOnItemSelectedListener(this.mItemSelectedListener);
        Spinner spinner2 = this.mSpinner;
        String value = getValue();
        CharSequence[] entryValues = getEntryValues();
        int i = -1;
        if (value != null && entryValues != null) {
            int length = entryValues.length - 1;
            while (true) {
                if (length < 0) {
                    break;
                }
                if (TextUtils.equals(entryValues[length].toString(), value)) {
                    i = length;
                    break;
                }
                length--;
            }
        }
        spinner2.setSelection(i);
        super.onBindViewHolder(preferenceViewHolder);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    protected final void onClick() {
        this.mSpinner.performClick();
    }

    @Override // androidx.preference.ListPreference
    public final void setEntries(CharSequence[] charSequenceArr) {
        super.setEntries(charSequenceArr);
        updateEntries();
    }

    @Override // androidx.preference.ListPreference
    public final void setValueIndex(int i) {
        setValue(getEntryValues()[i].toString());
    }

    /* compiled from: PG */
    /* renamed from: androidx.preference.DropDownPreference$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements AdapterView.OnItemSelectedListener {
        final /* synthetic */ Object DropDownPreference$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.DropDownPreference$1$ar$this$0 = obj;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onItemSelected(AdapterView adapterView, View view, int i, long j) {
            DropDownListView dropDownListView;
            if (this.switching_field != 0) {
                if (i != -1 && (dropDownListView = ((ListPopupWindow) this.DropDownPreference$1$ar$this$0).mDropDownList) != null) {
                    dropDownListView.mListSelectionHidden = false;
                    return;
                }
                return;
            }
            if (i >= 0) {
                String charSequence = ((ListPreference) this.DropDownPreference$1$ar$this$0).getEntryValues()[i].toString();
                if (!charSequence.equals(((ListPreference) this.DropDownPreference$1$ar$this$0).getValue()) && ((Preference) this.DropDownPreference$1$ar$this$0).callChangeListener(charSequence)) {
                    ((ListPreference) this.DropDownPreference$1$ar$this$0).setValue(charSequence);
                }
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public final void onNothingSelected(AdapterView adapterView) {
        }
    }
}
