package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AuthAccountResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<AuthAccountResult> CREATOR = new ServiceDumpCreator(2);
    public int connectionResultCode;
    public Intent rawAuthResolutionIntent;
    final int versionCode;

    public AuthAccountResult(int i, int i2, Intent intent) {
        this.versionCode = i;
        this.connectionResultCode = i2;
        this.rawAuthResolutionIntent = intent;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        throw null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, this.versionCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.connectionResultCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 3, this.rawAuthResolutionIntent, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public AuthAccountResult() {
        this(2, 0, null);
    }
}
