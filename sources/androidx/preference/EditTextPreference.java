package androidx.preference;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.text.TextUtils;
import androidx.preference.Preference;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    public String mText;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public EditTextPreference(android.content.Context r4, android.util.AttributeSet r5) {
        /*
            r3 = this;
            r0 = 2130969076(0x7f0401f4, float:1.7546824E38)
            r1 = 16842898(0x1010092, float:2.3693967E-38)
            int r0 = androidx.core.app.NotificationCompatBuilder$Api20Impl.getAttr(r4, r0, r1)
            r1 = 0
            r3.<init>(r4, r5, r0, r1)
            int[] r2 = androidx.preference.R$styleable.EditTextPreference
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r2, r0, r1)
            boolean r5 = androidx.core.app.NotificationCompatBuilder$Api20Impl.getBoolean(r4, r1, r1, r1)
            if (r5 == 0) goto L2b
            androidx.preference.ListPreference$SimpleSummaryProvider r5 = androidx.preference.ListPreference.SimpleSummaryProvider.sSimpleSummaryProvider$ar$class_merging
            if (r5 != 0) goto L26
            androidx.preference.ListPreference$SimpleSummaryProvider r5 = new androidx.preference.ListPreference$SimpleSummaryProvider
            r0 = 1
            r5.<init>(r0)
            androidx.preference.ListPreference.SimpleSummaryProvider.sSimpleSummaryProvider$ar$class_merging = r5
        L26:
            androidx.preference.ListPreference$SimpleSummaryProvider r5 = androidx.preference.ListPreference.SimpleSummaryProvider.sSimpleSummaryProvider$ar$class_merging
            r3.setSummaryProvider(r5)
        L2b:
            r4.recycle()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.EditTextPreference.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    @Override // androidx.preference.Preference
    protected final Object onGetDefaultValue(TypedArray typedArray, int i) {
        return typedArray.getString(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable != null && parcelable.getClass().equals(SavedState.class)) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            setText(savedState.mText);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.preference.Preference
    public final Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        if (isPersistent()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.mText = this.mText;
        return savedState;
    }

    @Override // androidx.preference.Preference
    protected final void onSetInitialValue(Object obj) {
        setText(getPersistedString((String) obj));
    }

    public final void setText(String str) {
        boolean shouldDisableDependents = shouldDisableDependents();
        this.mText = str;
        persistString(str);
        boolean shouldDisableDependents2 = shouldDisableDependents();
        if (shouldDisableDependents2 != shouldDisableDependents) {
            notifyDependencyChange(shouldDisableDependents2);
        }
        notifyChanged();
    }

    @Override // androidx.preference.Preference
    public final boolean shouldDisableDependents() {
        if (!TextUtils.isEmpty(this.mText) && !super.shouldDisableDependents()) {
            return false;
        }
        return true;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BackStackState.AnonymousClass1(14);
        String mText;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.mText = parcel.readString();
        }

        @Override // android.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.mText);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
