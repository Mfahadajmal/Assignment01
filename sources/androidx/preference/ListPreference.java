package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.app.NotificationCompatBuilder$Api20Impl;
import androidx.preference.Preference;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    private static final String TAG = "ListPreference";
    private CharSequence[] mEntries;
    private CharSequence[] mEntryValues;
    private String mSummary;
    private String mValue;
    private boolean mValueSet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SimpleSummaryProvider implements Preference.SummaryProvider {
        public static SimpleSummaryProvider sSimpleSummaryProvider;
        public static SimpleSummaryProvider sSimpleSummaryProvider$ar$class_merging;
        private final /* synthetic */ int switching_field;

        public SimpleSummaryProvider(int i) {
            this.switching_field = i;
        }

        @Override // androidx.preference.Preference.SummaryProvider
        public final /* synthetic */ CharSequence provideSummary(Preference preference) {
            if (this.switching_field != 0) {
                EditTextPreference editTextPreference = (EditTextPreference) preference;
                if (TextUtils.isEmpty(editTextPreference.mText)) {
                    return editTextPreference.getContext().getString(R.string.not_set);
                }
                return editTextPreference.mText;
            }
            ListPreference listPreference = (ListPreference) preference;
            if (TextUtils.isEmpty(listPreference.getEntry())) {
                return listPreference.getContext().getString(R.string.not_set);
            }
            return listPreference.getEntry();
        }
    }

    public ListPreference(Context context) {
        this(context, null);
    }

    private int getValueIndex() {
        return findIndexOfValue(this.mValue);
    }

    public int findIndexOfValue(String str) {
        CharSequence[] charSequenceArr;
        if (str != null && (charSequenceArr = this.mEntryValues) != null) {
            for (int length = charSequenceArr.length - 1; length >= 0; length--) {
                if (TextUtils.equals(this.mEntryValues[length].toString(), str)) {
                    return length;
                }
            }
        }
        return -1;
    }

    public CharSequence[] getEntries() {
        return this.mEntries;
    }

    public CharSequence getEntry() {
        CharSequence[] charSequenceArr;
        int valueIndex = getValueIndex();
        if (valueIndex >= 0 && (charSequenceArr = this.mEntries) != null) {
            return charSequenceArr[valueIndex];
        }
        return null;
    }

    public CharSequence[] getEntryValues() {
        return this.mEntryValues;
    }

    @Override // androidx.preference.Preference
    public CharSequence getSummary() {
        if (getSummaryProvider() != null) {
            return getSummaryProvider().provideSummary(this);
        }
        CharSequence entry = getEntry();
        CharSequence summary = super.getSummary();
        String str = this.mSummary;
        if (str != null) {
            if (entry == null) {
                entry = "";
            }
            String format = String.format(str, entry);
            if (!TextUtils.equals(format, summary)) {
                Log.w(TAG, "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
                return format;
            }
        }
        return summary;
    }

    public String getValue() {
        return this.mValue;
    }

    @Override // androidx.preference.Preference
    protected Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(SavedState.class)) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setValue(savedState.mValue);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.mValue = getValue();
        return savedState;
    }

    @Override // androidx.preference.Preference
    protected void onSetInitialValue(Object obj) {
        setValue(getPersistedString((String) obj));
    }

    public void setEntries(CharSequence[] charSequenceArr) {
        this.mEntries = charSequenceArr;
    }

    public void setEntryValues(CharSequence[] charSequenceArr) {
        this.mEntryValues = charSequenceArr;
    }

    @Override // androidx.preference.Preference
    public void setSummary(CharSequence charSequence) {
        super.setSummary(charSequence);
        if (charSequence == null) {
            this.mSummary = null;
        } else {
            this.mSummary = charSequence.toString();
        }
    }

    public void setValue(String str) {
        boolean z = !TextUtils.equals(this.mValue, str);
        if (z || !this.mValueSet) {
            this.mValue = str;
            this.mValueSet = true;
            persistString(str);
            if (z) {
                notifyChanged();
            }
        }
    }

    public void setValueIndex(int i) {
        CharSequence[] charSequenceArr = this.mEntryValues;
        if (charSequenceArr != null) {
            setValue(charSequenceArr[i].toString());
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BackStackState.AnonymousClass1(15);
        String mValue;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mValue = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.mValue);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, NotificationCompatBuilder$Api20Impl.getAttr(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }

    public void setEntries(int i) {
        setEntries(getContext().getResources().getTextArray(i));
    }

    public void setEntryValues(int i) {
        setEntryValues(getContext().getResources().getTextArray(i));
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ListPreference, i, i2);
        this.mEntries = NotificationCompatBuilder$Api20Impl.getTextArray(obtainStyledAttributes, 2, 0);
        this.mEntryValues = NotificationCompatBuilder$Api20Impl.getTextArray(obtainStyledAttributes, 3, 1);
        if (NotificationCompatBuilder$Api20Impl.getBoolean(obtainStyledAttributes, 4, 4, false)) {
            if (SimpleSummaryProvider.sSimpleSummaryProvider == null) {
                SimpleSummaryProvider.sSimpleSummaryProvider = new SimpleSummaryProvider(0);
            }
            setSummaryProvider(SimpleSummaryProvider.sSimpleSummaryProvider);
        }
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference, i, i2);
        this.mSummary = NotificationCompatBuilder$Api20Impl.getString(obtainStyledAttributes2, 33, 7);
        obtainStyledAttributes2.recycle();
    }
}
