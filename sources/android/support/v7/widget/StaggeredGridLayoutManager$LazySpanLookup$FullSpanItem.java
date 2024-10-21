package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem implements Parcelable {
    public static final Parcelable.Creator<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> CREATOR = new BackStackState.AnonymousClass1(8);
    public int mGapDir;
    int[] mGapPerSpan;
    public boolean mHasUnwantedGapAfter;
    public int mPosition;

    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem() {
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.mPosition + ", mGapDir=" + this.mGapDir + ", mHasUnwantedGapAfter=" + this.mHasUnwantedGapAfter + ", mGapPerSpan=" + Arrays.toString(this.mGapPerSpan) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPosition);
        parcel.writeInt(this.mGapDir);
        parcel.writeInt(this.mHasUnwantedGapAfter ? 1 : 0);
        int[] iArr = this.mGapPerSpan;
        if (iArr != null) {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.mGapPerSpan);
        } else {
            parcel.writeInt(0);
        }
    }

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem(Parcel parcel) {
        this.mPosition = parcel.readInt();
        this.mGapDir = parcel.readInt();
        this.mHasUnwantedGapAfter = parcel.readInt() == 1;
        int readInt = parcel.readInt();
        if (readInt > 0) {
            int[] iArr = new int[readInt];
            this.mGapPerSpan = iArr;
            parcel.readIntArray(iArr);
        }
    }
}
