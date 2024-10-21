package com.google.android.gms.common.internal;

import _COROUTINE._BOUNDARY;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ResolveAccountResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ResolveAccountResponse> CREATOR = new FeatureCreator(14);
    final IBinder accountAccessorBinder;
    public final ConnectionResult connectionResult;
    public final boolean isFromCrossClientAuth;
    public final boolean saveDefaultAccount;
    final int versionCode;

    public ResolveAccountResponse(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.versionCode = i;
        this.accountAccessorBinder = iBinder;
        this.connectionResult = connectionResult;
        this.saveDefaultAccount = z;
        this.isFromCrossClientAuth = z2;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ResolveAccountResponse)) {
            return false;
        }
        ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse) obj;
        if (!this.connectionResult.equals(resolveAccountResponse.connectionResult) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(getAccountAccessor(), resolveAccountResponse.getAccountAccessor())) {
            return false;
        }
        return true;
    }

    public final IAccountAccessor getAccountAccessor() {
        IBinder iBinder = this.accountAccessorBinder;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        if (queryLocalInterface instanceof IAccountAccessor) {
            return (IAccountAccessor) queryLocalInterface;
        }
        return new IAccountAccessor$Stub$Proxy(iBinder);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.versionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIBinder$ar$ds(parcel, 2, this.accountAccessorBinder);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.connectionResult, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, this.saveDefaultAccount);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.isFromCrossClientAuth);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
