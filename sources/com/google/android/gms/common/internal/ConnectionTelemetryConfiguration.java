package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ConnectionTelemetryConfiguration> CREATOR = new FeatureCreator(10);
    public final int maxMethodInvocationsLogged;
    public final int[] methodInvocationMethodKeyAllowlist;
    public final int[] methodInvocationMethodKeyDisallowlist;
    public final boolean methodInvocationTelemetryEnabled;
    public final boolean methodTimingTelemetryEnabled;
    public final RootTelemetryConfiguration rootTelemetryConfiguration;

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration rootTelemetryConfiguration, boolean z, boolean z2, int[] iArr, int i, int[] iArr2) {
        this.rootTelemetryConfiguration = rootTelemetryConfiguration;
        this.methodInvocationTelemetryEnabled = z;
        this.methodTimingTelemetryEnabled = z2;
        this.methodInvocationMethodKeyAllowlist = iArr;
        this.maxMethodInvocationsLogged = i;
        this.methodInvocationMethodKeyDisallowlist = iArr2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        RootTelemetryConfiguration rootTelemetryConfiguration = this.rootTelemetryConfiguration;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 1, rootTelemetryConfiguration, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.methodInvocationTelemetryEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.methodTimingTelemetryEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 4, this.methodInvocationMethodKeyAllowlist);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.maxMethodInvocationsLogged);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIntArray$ar$ds(parcel, 6, this.methodInvocationMethodKeyDisallowlist);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
