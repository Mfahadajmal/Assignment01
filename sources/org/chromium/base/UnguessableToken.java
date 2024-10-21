package org.chromium.base;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.feedback.ServiceDumpRequestCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UnguessableToken extends TokenBase implements Parcelable {
    public static final Parcelable.Creator<UnguessableToken> CREATOR = new ServiceDumpRequestCreator(13);

    public UnguessableToken(long j, long j2) {
        super(j, j2);
    }

    private UnguessableToken parcelAndUnparcelForTesting() {
        Parcel obtain = Parcel.obtain();
        writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        UnguessableToken createFromParcel = CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return createFromParcel;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mHigh);
        parcel.writeLong(this.mLow);
    }
}
