package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectionInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ConnectionInfo> CREATOR = new FeatureCreator(9);
    Feature[] availableFeatures;
    int binderPropagationProtocol;
    public ConnectionTelemetryConfiguration connectionTelemetryConfiguration;
    Bundle resolutionBundle;

    public ConnectionInfo() {
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 1, this.resolutionBundle);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 2, this.availableFeatures, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, this.binderPropagationProtocol);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 4, this.connectionTelemetryConfiguration, i);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    public ConnectionInfo(Bundle bundle, Feature[] featureArr, int i, ConnectionTelemetryConfiguration connectionTelemetryConfiguration) {
        this.resolutionBundle = bundle;
        this.availableFeatures = featureArr;
        this.binderPropagationProtocol = i;
        this.connectionTelemetryConfiguration = connectionTelemetryConfiguration;
    }
}
