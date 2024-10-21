package com.google.android.gms.feedback;

import _COROUTINE._BOUNDARY;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import j$.util.Objects;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceDumpRequest extends AbstractSafeParcelable {
    public static final ServiceDumpRequestCreator CREATOR = new ServiceDumpRequestCreator(0);
    public final String[] dumpsysFlags;
    public final int expectedOutputFormat;
    public final String service;
    public final boolean showOutputToUser;

    public ServiceDumpRequest(String str, String[] strArr, int i, boolean z) {
        str.getClass();
        this.service = str;
        this.dumpsysFlags = strArr;
        if (i != 2 && i != 1) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Unknown expected output format="));
        }
        this.expectedOutputFormat = i;
        this.showOutputToUser = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ServiceDumpRequest serviceDumpRequest = (ServiceDumpRequest) obj;
            if (this.showOutputToUser == serviceDumpRequest.showOutputToUser && this.expectedOutputFormat == serviceDumpRequest.expectedOutputFormat && this.service.equals(serviceDumpRequest.service) && Arrays.equals(this.dumpsysFlags, serviceDumpRequest.dumpsysFlags)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Objects.hash(this.service, Boolean.valueOf(this.showOutputToUser), Integer.valueOf(this.expectedOutputFormat)) * 31) + Arrays.hashCode(this.dumpsysFlags);
    }

    public final String toString() {
        return "ServiceDumpRequest{service='" + this.service + "', dumpsysFlags=" + Arrays.toString(this.dumpsysFlags) + ", expectedOutputFormat=" + this.expectedOutputFormat + ", showOutputToUser=" + this.showOutputToUser + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 2, this.service);
        StrictModeUtils$VmPolicyBuilderCompatS.writeStringArray$ar$ds(parcel, 3, this.dumpsysFlags);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.expectedOutputFormat);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 5, this.showOutputToUser);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
