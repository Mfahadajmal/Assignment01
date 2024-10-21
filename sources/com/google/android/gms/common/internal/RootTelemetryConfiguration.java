package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RootTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RootTelemetryConfiguration> CREATOR = new FeatureCreator(15);
    public final int batchPeriodMillis;
    public final int maxMethodInvocationsInBatch;
    public final boolean methodInvocationTelemetryEnabled;
    public final boolean methodTimingTelemetryEnabled;
    public final int version;

    public RootTelemetryConfiguration(int i, boolean z, boolean z2, int i2, int i3) {
        this.version = i;
        this.methodInvocationTelemetryEnabled = z;
        this.methodTimingTelemetryEnabled = z2;
        this.batchPeriodMillis = i2;
        this.maxMethodInvocationsInBatch = i3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2 = this.version;
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, i2);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 2, this.methodInvocationTelemetryEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 3, this.methodTimingTelemetryEnabled);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 4, this.batchPeriodMillis);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 5, this.maxMethodInvocationsInBatch);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }
}
