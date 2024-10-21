package androidx.versionedparcelable;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.material.badge.BadgeState;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new BadgeState.State.AnonymousClass1(1);
    private final VersionedParcelable mParcel;

    public ParcelImpl(Parcel parcel) {
        this.mParcel = new VersionedParcel(parcel).readVersionedParcelable();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        new VersionedParcel(parcel).writeVersionedParcelable(this.mParcel);
    }
}
