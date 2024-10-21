package com.google.android.gms.googlehelp;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.AdditionalConsentConfigCreator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OfflineSuggestion extends AbstractSafeParcelable {
    public static final Parcelable.Creator<OfflineSuggestion> CREATOR = new AdditionalConsentConfigCreator(8);
    final String body;
    final String browseUrl;
    final String id;
    final String title;

    public OfflineSuggestion(String str, String str2, String str3, String str4) {
        this.id = str;
        this.title = str2;
        this.body = str4;
        this.browseUrl = str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.id);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.title);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.body);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.browseUrl);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
