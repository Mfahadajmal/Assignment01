package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = new BackStackState.AnonymousClass1(1);
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int[] mCurrentMaxLifecycleStates;
    final ArrayList mFragmentWhos;
    final int mIndex;
    final String mName;
    final int[] mOldMaxLifecycleStates;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList mSharedElementSourceNames;
    final ArrayList mSharedElementTargetNames;
    final int mTransition;

    public BackStackRecordState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mFragmentWhos = parcel.createStringArrayList();
        this.mOldMaxLifecycleStates = parcel.createIntArray();
        this.mCurrentMaxLifecycleStates = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final void fillInBackStackRecord(BackStackRecord backStackRecord) {
        int i = 0;
        int i2 = 0;
        while (true) {
            int[] iArr = this.mOps;
            boolean z = true;
            if (i < iArr.length) {
                FragmentTransaction.Op op = new FragmentTransaction.Op();
                int i3 = i + 1;
                op.mCmd = iArr[i];
                if (FragmentManager.isLoggingEnabled(2)) {
                    Objects.toString(backStackRecord);
                    int i4 = this.mOps[i3];
                }
                op.mOldMaxState = Lifecycle.State.values()[this.mOldMaxLifecycleStates[i2]];
                op.mCurrentMaxState = Lifecycle.State.values()[this.mCurrentMaxLifecycleStates[i2]];
                int[] iArr2 = this.mOps;
                int i5 = i + 2;
                if (iArr2[i3] == 0) {
                    z = false;
                }
                op.mFromExpandedOp = z;
                int i6 = iArr2[i5];
                op.mEnterAnim = i6;
                int i7 = iArr2[i + 3];
                op.mExitAnim = i7;
                int i8 = i + 5;
                int i9 = iArr2[i + 4];
                op.mPopEnterAnim = i9;
                i += 6;
                int i10 = iArr2[i8];
                op.mPopExitAnim = i10;
                backStackRecord.mEnterAnim = i6;
                backStackRecord.mExitAnim = i7;
                backStackRecord.mPopEnterAnim = i9;
                backStackRecord.mPopExitAnim = i10;
                backStackRecord.addOp(op);
                i2++;
            } else {
                backStackRecord.mTransition = this.mTransition;
                backStackRecord.mName = this.mName;
                backStackRecord.mAddToBackStack = true;
                backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
                backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
                backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
                backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
                backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
                backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
                backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.mOps);
        parcel.writeStringList(this.mFragmentWhos);
        parcel.writeIntArray(this.mOldMaxLifecycleStates);
        parcel.writeIntArray(this.mCurrentMaxLifecycleStates);
        parcel.writeInt(this.mTransition);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed ? 1 : 0);
    }

    public BackStackRecordState(BackStackRecord backStackRecord) {
        int size = backStackRecord.mOps.size();
        this.mOps = new int[size * 6];
        if (backStackRecord.mAddToBackStack) {
            this.mFragmentWhos = new ArrayList(size);
            this.mOldMaxLifecycleStates = new int[size];
            this.mCurrentMaxLifecycleStates = new int[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                FragmentTransaction.Op op = (FragmentTransaction.Op) backStackRecord.mOps.get(i2);
                int i3 = i + 1;
                this.mOps[i] = op.mCmd;
                ArrayList arrayList = this.mFragmentWhos;
                Fragment fragment = op.mFragment;
                arrayList.add(fragment != null ? fragment.mWho : null);
                int[] iArr = this.mOps;
                iArr[i3] = op.mFromExpandedOp ? 1 : 0;
                iArr[i + 2] = op.mEnterAnim;
                iArr[i + 3] = op.mExitAnim;
                int i4 = i + 5;
                iArr[i + 4] = op.mPopEnterAnim;
                i += 6;
                iArr[i4] = op.mPopExitAnim;
                this.mOldMaxLifecycleStates[i2] = op.mOldMaxState.ordinal();
                this.mCurrentMaxLifecycleStates[i2] = op.mCurrentMaxState.ordinal();
            }
            this.mTransition = backStackRecord.mTransition;
            this.mName = backStackRecord.mName;
            this.mIndex = backStackRecord.mIndex;
            this.mBreadCrumbTitleRes = backStackRecord.mBreadCrumbTitleRes;
            this.mBreadCrumbTitleText = backStackRecord.mBreadCrumbTitleText;
            this.mBreadCrumbShortTitleRes = backStackRecord.mBreadCrumbShortTitleRes;
            this.mBreadCrumbShortTitleText = backStackRecord.mBreadCrumbShortTitleText;
            this.mSharedElementSourceNames = backStackRecord.mSharedElementSourceNames;
            this.mSharedElementTargetNames = backStackRecord.mSharedElementTargetNames;
            this.mReorderingAllowed = backStackRecord.mReorderingAllowed;
            return;
        }
        throw new IllegalStateException("Not on back stack");
    }
}
