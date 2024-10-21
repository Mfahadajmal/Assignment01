package com.google.android.gms.usagereporting;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ElCapitanOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ElCapitanOptions> CREATOR = new ServiceDumpCreator(8);
    public final int elCapitanReviewedVersion;
    public final boolean isElCapitanReviewed;

    public ElCapitanOptions(boolean z, int i) {
        this.isElCapitanReviewed = z;
        this.elCapitanReviewedVersion = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.isElCapitanReviewed);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.elCapitanReviewedVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
