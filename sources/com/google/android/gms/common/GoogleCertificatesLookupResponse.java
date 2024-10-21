package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleCertificatesLookupResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleCertificatesLookupResponse> CREATOR = new FeatureCreator(3);
    public final String errorMessage;
    public final int firstPartyStatusValue;
    public final boolean result;
    public final int statusValue;

    public GoogleCertificatesLookupResponse(boolean z, String str, int i, int i2) {
        this.result = z;
        this.errorMessage = str;
        this.statusValue = SpannableUtils$IdentifierSpan.fromInt$ar$edu$ad7c944d_0(i) - 1;
        this.firstPartyStatusValue = SpannableUtils$IdentifierSpan.fromInt$ar$edu(i2) - 1;
    }

    public final int getFirstPartyStatus$ar$edu() {
        return SpannableUtils$IdentifierSpan.fromInt$ar$edu(this.firstPartyStatusValue);
    }

    public final int getStatus$ar$edu() {
        return SpannableUtils$IdentifierSpan.fromInt$ar$edu$ad7c944d_0(this.statusValue);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        boolean z = this.result;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 1, z);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.errorMessage);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.statusValue);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.firstPartyStatusValue);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
