package com.google.android.gms.feedback;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import j$.util.Objects;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceDump extends AbstractSafeParcelable {
    public static final ServiceDumpCreator CREATOR = new ServiceDumpCreator(0);
    public final byte[] dumpOutput;
    public final ServiceDumpRequest serviceDumpRequest;

    public ServiceDump(ServiceDumpRequest serviceDumpRequest, byte[] bArr) {
        serviceDumpRequest.getClass();
        this.serviceDumpRequest = serviceDumpRequest;
        bArr.getClass();
        this.dumpOutput = bArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ServiceDump serviceDump = (ServiceDump) obj;
            if (this.serviceDumpRequest.equals(serviceDump.serviceDumpRequest) && Arrays.equals(this.dumpOutput, serviceDump.dumpOutput)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Objects.hash(this.serviceDumpRequest) * 31) + Arrays.hashCode(this.dumpOutput);
    }

    public final String toString() {
        byte[] bArr = this.dumpOutput;
        String obj = this.serviceDumpRequest.toString();
        String arrays = Arrays.toString(bArr);
        if (arrays.length() > 20) {
            arrays = String.valueOf(arrays.substring(0, 17)).concat("...");
        }
        return "ServiceDump{serviceDumpRequest=" + obj + ", dumpOutput=\"" + String.format(arrays, new Object[0]) + "\"}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 2, this.serviceDumpRequest, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeByteArray$ar$ds(parcel, 3, this.dumpOutput);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
