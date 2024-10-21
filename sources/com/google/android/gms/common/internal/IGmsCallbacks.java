package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.google.android.aidl.BaseStub;
import com.google.android.aidl.Codecs;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface IGmsCallbacks extends IInterface {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Stub extends BaseStub implements IGmsCallbacks {
        private final int disconnectCount;
        private BaseGmsClient gmsClient;

        public Stub(BaseGmsClient baseGmsClient, int i) {
            this();
            this.gmsClient = baseGmsClient;
            this.disconnectCount = i;
        }

        public static void onAccountValidationComplete$ar$ds() {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @Override // com.google.android.aidl.BaseStub
        protected final boolean dispatchTransaction$ar$ds(int i, Parcel parcel, Parcel parcel2) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    int readInt = parcel.readInt();
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    ConnectionInfo connectionInfo = (ConnectionInfo) Codecs.createParcelable(parcel, ConnectionInfo.CREATOR);
                    Codecs.enforceNoDataAvail(parcel);
                    onPostInitCompleteWithConnectionInfo(readInt, readStrongBinder, connectionInfo);
                } else {
                    parcel.readInt();
                    Codecs.enforceNoDataAvail(parcel);
                    onAccountValidationComplete$ar$ds();
                }
            } else {
                int readInt2 = parcel.readInt();
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                Bundle bundle = (Bundle) Codecs.createParcelable(parcel, Bundle.CREATOR);
                Codecs.enforceNoDataAvail(parcel);
                onPostInitComplete(readInt2, readStrongBinder2, bundle);
            }
            parcel2.writeNoException();
            return true;
        }

        public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(this.gmsClient, "onPostInitComplete can be called only once per call to getRemoteService");
            this.gmsClient.onPostInitHandler(i, iBinder, bundle, this.disconnectCount);
            this.gmsClient = null;
        }

        public final void onPostInitCompleteWithConnectionInfo(int i, IBinder iBinder, ConnectionInfo connectionInfo) {
            RootTelemetryConfiguration rootTelemetryConfiguration;
            BaseGmsClient baseGmsClient = this.gmsClient;
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(baseGmsClient, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(connectionInfo);
            baseGmsClient.connectionInfo = connectionInfo;
            if (baseGmsClient.usesClientTelemetry()) {
                ConnectionTelemetryConfiguration connectionTelemetryConfiguration = connectionInfo.connectionTelemetryConfiguration;
                RootTelemetryConfigManager rootTelemetryConfigManager = RootTelemetryConfigManager.getInstance();
                if (connectionTelemetryConfiguration == null) {
                    rootTelemetryConfiguration = null;
                } else {
                    rootTelemetryConfiguration = connectionTelemetryConfiguration.rootTelemetryConfiguration;
                }
                rootTelemetryConfigManager.updateConfig(rootTelemetryConfiguration);
            }
            onPostInitComplete(i, iBinder, connectionInfo.resolutionBundle);
        }

        public Stub() {
            super("com.google.android.gms.common.internal.IGmsCallbacks");
        }
    }
}
