package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.core.widget.NestedScrollView;
import androidx.navigation.NavBackStackEntryState;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.SeekBarPreference;
import androidx.preference.TwoStatePreference;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackStackState implements Parcelable {
    public static final Parcelable.Creator<BackStackState> CREATOR = new AnonymousClass1(0);
    final List mFragments;
    final List mTransactions;

    /* compiled from: PG */
    /* renamed from: android.support.v4.app.BackStackState$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Parcelable.Creator {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            Intent intent;
            switch (this.switching_field) {
                case 0:
                    return new BackStackState(parcel);
                case 1:
                    return new BackStackRecordState(parcel);
                case 2:
                    return new FragmentManager.LaunchedFragmentInfo(parcel);
                case 3:
                    return new FragmentManagerState(parcel);
                case 4:
                    return new FragmentState(parcel);
                case 5:
                    return new ActionMenuPresenter.SavedState(parcel);
                case 6:
                    return new AppCompatSpinner.SavedState(parcel);
                case 7:
                    return new LinearLayoutManager.SavedState(parcel);
                case 8:
                    return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(parcel);
                case 9:
                    return new StaggeredGridLayoutManager.SavedState(parcel);
                case 10:
                    parcel.getClass();
                    int readInt = parcel.readInt();
                    if (parcel.readInt() == 0) {
                        intent = null;
                    } else {
                        intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                    }
                    return new ActivityResult(readInt, intent);
                case 11:
                    parcel.getClass();
                    Parcelable readParcelable = parcel.readParcelable(IntentSender.class.getClassLoader());
                    readParcelable.getClass();
                    return new IntentSenderRequest((IntentSender) readParcelable, (Intent) parcel.readParcelable(Intent.class.getClassLoader()), parcel.readInt(), parcel.readInt());
                case 12:
                    return new NestedScrollView.SavedState(parcel);
                case 13:
                    parcel.getClass();
                    return new NavBackStackEntryState(parcel);
                case 14:
                    return new EditTextPreference.SavedState(parcel);
                case 15:
                    return new ListPreference.SavedState(parcel);
                case 16:
                    return new MultiSelectListPreference.SavedState(parcel);
                case 17:
                    return new Preference.BaseSavedState(parcel);
                case 18:
                    return new PreferenceGroup.SavedState(parcel);
                case 19:
                    return new SeekBarPreference.SavedState(parcel);
                default:
                    return new TwoStatePreference.SavedState(parcel);
            }
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ Object[] newArray(int i) {
            switch (this.switching_field) {
                case 0:
                    return new BackStackState[i];
                case 1:
                    return new BackStackRecordState[i];
                case 2:
                    return new FragmentManager.LaunchedFragmentInfo[i];
                case 3:
                    return new FragmentManagerState[i];
                case 4:
                    return new FragmentState[i];
                case 5:
                    return new ActionMenuPresenter.SavedState[i];
                case 6:
                    return new AppCompatSpinner.SavedState[i];
                case 7:
                    return new LinearLayoutManager.SavedState[i];
                case 8:
                    return new StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem[i];
                case 9:
                    return new StaggeredGridLayoutManager.SavedState[i];
                case 10:
                    return new ActivityResult[i];
                case 11:
                    return new IntentSenderRequest[i];
                case 12:
                    return new NestedScrollView.SavedState[i];
                case 13:
                    return new NavBackStackEntryState[i];
                case 14:
                    return new EditTextPreference.SavedState[i];
                case 15:
                    return new ListPreference.SavedState[i];
                case 16:
                    return new MultiSelectListPreference.SavedState[i];
                case 17:
                    return new Preference.BaseSavedState[i];
                case 18:
                    return new PreferenceGroup.SavedState[i];
                case 19:
                    return new SeekBarPreference.SavedState[i];
                default:
                    return new TwoStatePreference.SavedState[i];
            }
        }
    }

    public BackStackState(List list, List list2) {
        this.mFragments = list;
        this.mTransactions = list2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mFragments);
        parcel.writeTypedList(this.mTransactions);
    }

    public BackStackState(Parcel parcel) {
        this.mFragments = parcel.createStringArrayList();
        this.mTransactions = parcel.createTypedArrayList(BackStackRecordState.CREATOR);
    }
}
