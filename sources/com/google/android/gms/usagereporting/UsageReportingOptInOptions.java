package com.google.android.gms.usagereporting;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.feedback.ServiceDumpCreator;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsageReportingOptInOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UsageReportingOptInOptions> CREATOR = new ServiceDumpCreator(10);
    public final List accountsToUpload;
    public final boolean cbFromSetupWizard;
    public final boolean newCheckboxOptIn;
    public final int optInPlayPass;
    public final int optInUsageReporting;
    public final String playPassAccount;

    public UsageReportingOptInOptions(int i, boolean z, List list, int i2, String str, boolean z2) {
        ArrayList arrayList = new ArrayList();
        this.accountsToUpload = arrayList;
        this.optInUsageReporting = i;
        this.newCheckboxOptIn = z;
        if (list != null) {
            arrayList.addAll(list);
        }
        this.optInPlayPass = i2;
        this.playPassAccount = str;
        this.cbFromSetupWizard = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.optInUsageReporting);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.newCheckboxOptIn);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringList$ar$ds(parcel, 4, this.accountsToUpload);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.optInPlayPass);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.playPassAccount);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 7, this.cbFromSetupWizard);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
