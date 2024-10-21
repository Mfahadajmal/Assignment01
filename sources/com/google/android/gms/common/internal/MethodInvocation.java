package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MethodInvocation extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new FeatureCreator(12);
    public final String callingEntryPoint;
    public final String callingModuleId;
    public final int connectionResultStatusCode;
    public final long endTimeMillis;
    public final int latencyMillis;
    public final int methodKey;
    public final int resultStatusCode;
    public final int serviceId;
    public final long startTimeMillis;

    public MethodInvocation(int i, int i2, int i3, long j, long j2, String str, String str2, int i4, int i5) {
        this.methodKey = i;
        this.resultStatusCode = i2;
        this.connectionResultStatusCode = i3;
        this.startTimeMillis = j;
        this.endTimeMillis = j2;
        this.callingModuleId = str;
        this.callingEntryPoint = str2;
        this.serviceId = i4;
        this.latencyMillis = i5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.methodKey;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, i2);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, this.resultStatusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.connectionResultStatusCode);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 4, this.startTimeMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeLong(parcel, 5, this.endTimeMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 6, this.callingModuleId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 7, this.callingEntryPoint);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 8, this.serviceId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 9, this.latencyMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
