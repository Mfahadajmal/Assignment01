package com.google.android.gms.feedback;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AdditionalConsentConfig extends AbstractSafeParcelable {
    public static final AdditionalConsentConfigCreator CREATOR = new AdditionalConsentConfigCreator(0);
    final String consentQuestion;
    final String consentReason;
    final String consentedDataEncodedHtml;
    final String dataPageContentEncodedHtml;
    final String dataPageHeader;
    final Bundle psdBundle;

    public AdditionalConsentConfig(String str, String str2, String str3, String str4, String str5, Bundle bundle) {
        this.dataPageHeader = str;
        this.dataPageContentEncodedHtml = str2;
        this.consentedDataEncodedHtml = str3;
        this.consentQuestion = str4;
        this.consentReason = str5;
        this.psdBundle = bundle;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.dataPageHeader);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 3, this.dataPageContentEncodedHtml);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, this.consentedDataEncodedHtml);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 5, this.consentQuestion);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.consentReason);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 7, this.psdBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
