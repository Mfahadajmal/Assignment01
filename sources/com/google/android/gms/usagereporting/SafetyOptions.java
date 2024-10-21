package com.google.android.gms.usagereporting;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SafetyOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SafetyOptions> CREATOR = new ServiceDumpCreator(9);
    public final boolean isGaiaUploadAllowed;
    public final boolean isSafetyNoticeReviewed;
    public final int safetyNoticeReviewedVersion;

    public SafetyOptions(boolean z, boolean z2, int i) {
        this.isSafetyNoticeReviewed = z;
        this.isGaiaUploadAllowed = z2;
        this.safetyNoticeReviewedVersion = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.isSafetyNoticeReviewed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.isGaiaUploadAllowed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.safetyNoticeReviewedVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
