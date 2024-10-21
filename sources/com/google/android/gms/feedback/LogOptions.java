package com.google.android.gms.feedback;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<LogOptions> CREATOR = new AdditionalConsentConfigCreator(3);
    final boolean includeContentCaptureDumpLogs;
    final boolean includeFullMainLogs;
    final boolean includeFullSystemLogs;
    final boolean includeRadioLogs;
    final String logFilter;
    final ServiceDumpRequest[] serviceDumpRequests;

    public LogOptions(String str, boolean z, boolean z2, boolean z3, boolean z4, ServiceDumpRequest[] serviceDumpRequestArr) {
        this.logFilter = str;
        this.includeRadioLogs = z;
        this.includeFullSystemLogs = z2;
        this.includeFullMainLogs = z3;
        this.includeContentCaptureDumpLogs = z4;
        this.serviceDumpRequests = serviceDumpRequestArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.logFilter);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.includeRadioLogs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 4, this.includeFullSystemLogs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.includeFullMainLogs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 6, this.includeContentCaptureDumpLogs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 7, this.serviceDumpRequests, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
