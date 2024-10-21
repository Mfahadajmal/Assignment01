package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RecordConsentByConsentResultResponse extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<RecordConsentByConsentResultResponse> CREATOR = new ServiceDumpCreator(3);
    public final List grantedScopes;
    public final String token;

    public RecordConsentByConsentResultResponse(List list, String str) {
        this.grantedScopes = list;
        this.token = str;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        throw null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        List list = this.grantedScopes;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 1, list);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.token);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
