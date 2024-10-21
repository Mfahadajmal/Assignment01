package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.ConnectionInfo;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.common.moduleinstall.internal.ApiFeatureRequest;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeatureCreator implements Parcelable.Creator {
    private final /* synthetic */ int switching_field;

    public FeatureCreator(int i) {
        this.switching_field = i;
    }

    public static void writeToParcel(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int beginObjectHeader = StrictModeUtils$VmPolicyBuilderCompatS.beginObjectHeader(parcel);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 1, getServiceRequest.version);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 2, getServiceRequest.serviceId);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 3, getServiceRequest.clientLibraryVersion);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 4, getServiceRequest.callingPackage);
        StrictModeUtils$VmPolicyBuilderCompatS.writeIBinder$ar$ds(parcel, 5, getServiceRequest.accountAccessorBinder);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 6, getServiceRequest.scopes, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBundle$ar$ds(parcel, 7, getServiceRequest.extraArgs);
        StrictModeUtils$VmPolicyBuilderCompatS.writeParcelable$ar$ds(parcel, 8, getServiceRequest.clientRequestedAccount, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 10, getServiceRequest.clientRequiredFeatures, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeTypedArray$ar$ds(parcel, 11, getServiceRequest.clientApiFeatures, i);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 12, getServiceRequest.requestingConnectionInfo);
        StrictModeUtils$VmPolicyBuilderCompatS.writeInt(parcel, 13, getServiceRequest.binderPropagationProtocol);
        StrictModeUtils$VmPolicyBuilderCompatS.writeBoolean(parcel, 14, getServiceRequest.requestingTelemetryConfiguration);
        StrictModeUtils$VmPolicyBuilderCompatS.writeString$ar$ds(parcel, 15, getServiceRequest.attributionTag);
        StrictModeUtils$VmPolicyBuilderCompatS.finishVariableData(parcel, beginObjectHeader);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 564
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:64)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r26) {
        /*
            Method dump skipped, instructions count: 1660
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.FeatureCreator.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        switch (this.switching_field) {
            case 0:
                return new Feature[i];
            case 1:
                return new ConnectionResult[i];
            case 2:
                return new GoogleCertificatesLookupQuery[i];
            case 3:
                return new GoogleCertificatesLookupResponse[i];
            case 4:
                return new GoogleCertificatesQuery[i];
            case 5:
                return new Scope[i];
            case 6:
                return new Status[i];
            case 7:
                return new BitmapTeleporter[i];
            case 8:
                return new DataHolder[i];
            case 9:
                return new ConnectionInfo[i];
            case 10:
                return new ConnectionTelemetryConfiguration[i];
            case 11:
                return new GetServiceRequest[i];
            case 12:
                return new MethodInvocation[i];
            case 13:
                return new ResolveAccountRequest[i];
            case 14:
                return new ResolveAccountResponse[i];
            case 15:
                return new RootTelemetryConfiguration[i];
            case 16:
                return new TelemetryData[i];
            case 17:
                return new ModuleAvailabilityResponse[i];
            case 18:
                return new ModuleInstallIntentResponse[i];
            case 19:
                return new ModuleInstallResponse[i];
            default:
                return new ApiFeatureRequest[i];
        }
    }
}
