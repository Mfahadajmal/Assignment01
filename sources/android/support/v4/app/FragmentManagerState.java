package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator<FragmentManagerState> CREATOR = new BackStackState.AnonymousClass1(3);
    ArrayList mActive;
    ArrayList mAdded;
    BackStackRecordState[] mBackStack;
    int mBackStackIndex;
    ArrayList mBackStackStateKeys;
    ArrayList mBackStackStates;
    ArrayList mLaunchedFragments;
    String mPrimaryNavActiveWho;

    public FragmentManagerState() {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(this.mActive);
        parcel.writeStringList(this.mAdded);
        parcel.writeTypedArray(this.mBackStack, i);
        parcel.writeInt(this.mBackStackIndex);
        parcel.writeString(this.mPrimaryNavActiveWho);
        parcel.writeStringList(this.mBackStackStateKeys);
        parcel.writeTypedList(this.mBackStackStates);
        parcel.writeTypedList(this.mLaunchedFragments);
    }

    public FragmentManagerState(Parcel parcel) {
        this.mPrimaryNavActiveWho = null;
        this.mBackStackStateKeys = new ArrayList();
        this.mBackStackStates = new ArrayList();
        this.mActive = parcel.createStringArrayList();
        this.mAdded = parcel.createStringArrayList();
        this.mBackStack = (BackStackRecordState[]) parcel.createTypedArray(BackStackRecordState.CREATOR);
        this.mBackStackIndex = parcel.readInt();
        this.mPrimaryNavActiveWho = parcel.readString();
        this.mBackStackStateKeys = parcel.createStringArrayList();
        this.mBackStackStates = parcel.createTypedArrayList(BackStackState.CREATOR);
        this.mLaunchedFragments = parcel.createTypedArrayList(FragmentManager.LaunchedFragmentInfo.CREATOR);
    }
}
