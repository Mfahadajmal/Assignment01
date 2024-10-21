package com.google.android.gms.usagereporting.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.usagereporting.Features;
import com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks;
import com.google.android.gms.usagereporting.internal.IUsageReportingOptInOptionsChangedListener;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsageReportingClientImpl extends GmsClient {
    private final AtomicReference listener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DisconnectCallbacks extends IUsageReportingCallbacks.Stub {
        public DisconnectCallbacks() {
            super(null);
        }

        @Override // com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks.Stub
        public final void onUnregisterOptInOptionsChangedListener(Status status) {
            if (!status.isSuccess()) {
                Log.e("UsageReportingClientImp", "disconnect(): Could not unregister listener: status=".concat(String.valueOf(String.valueOf(status))));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SetOptInOptionsChangedListenerCallbacks extends IUsageReportingCallbacks.Stub {
        private final IUsageReportingOptInOptionsChangedListener.Stub newListener$ar$class_merging;
        private final BaseImplementation$ResultHolder resultHolder;
        private final IUsageReportingService service;

        public SetOptInOptionsChangedListenerCallbacks(IUsageReportingService iUsageReportingService, BaseImplementation$ResultHolder baseImplementation$ResultHolder, IUsageReportingOptInOptionsChangedListener.Stub stub) {
            super(null);
            this.service = iUsageReportingService;
            this.resultHolder = baseImplementation$ResultHolder;
            this.newListener$ar$class_merging = stub;
        }

        @Override // com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks.Stub
        public final void onRegisterOptInOptionsChangedListener(Status status) {
            if (!status.isSuccess()) {
                this.resultHolder.setResult$ar$ds();
            } else {
                this.resultHolder.setResult$ar$ds();
            }
        }

        @Override // com.google.android.gms.usagereporting.internal.IUsageReportingCallbacks.Stub
        public final void onUnregisterOptInOptionsChangedListener(Status status) {
            if (!status.isSuccess()) {
                this.resultHolder.setResult$ar$ds();
                return;
            }
            IUsageReportingOptInOptionsChangedListener.Stub stub = this.newListener$ar$class_merging;
            if (stub == null) {
                this.resultHolder.setResult$ar$ds();
            } else {
                this.service.registerOptInOptionsChangedListener(stub, this);
            }
        }
    }

    public UsageReportingClientImpl(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 41, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.listener = new AtomicReference();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.usagereporting.internal.IUsageReportingService");
        if (queryLocalInterface instanceof IUsageReportingService) {
            return (IUsageReportingService) queryLocalInterface;
        }
        return new IUsageReportingService$Stub$Proxy(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void disconnect() {
        try {
            IUsageReportingOptInOptionsChangedListener.Stub stub = (IUsageReportingOptInOptionsChangedListener.Stub) this.listener.getAndSet(null);
            if (stub != null) {
                ((IUsageReportingService) getService()).unregisterOptInOptionsChangedListener(stub, new DisconnectCallbacks());
            }
        } catch (RemoteException e) {
            Log.e("UsageReportingClientImp", "disconnect(): Could not unregister listener from remote:", e);
        }
        super.disconnect();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return Features.ALL_FEATURES;
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api$Client
    public final int getMinApkVersion() {
        return 12600000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.usagereporting.internal.IUsageReportingService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.usagereporting.service.START";
    }

    public final void setOptInOptionsChangedListenerConnectionless$ar$class_merging(IUsageReportingOptInOptionsChangedListener.Stub stub, IUsageReportingOptInOptionsChangedListener.Stub stub2, BaseImplementation$ResultHolder baseImplementation$ResultHolder) {
        SetOptInOptionsChangedListenerCallbacks setOptInOptionsChangedListenerCallbacks = new SetOptInOptionsChangedListenerCallbacks((IUsageReportingService) getService(), baseImplementation$ResultHolder, stub2);
        if (stub != null) {
            ((IUsageReportingService) getService()).unregisterOptInOptionsChangedListenerConnectionless(stub, setOptInOptionsChangedListenerCallbacks);
        } else if (stub2 == null) {
            baseImplementation$ResultHolder.setResult$ar$ds();
        } else {
            ((IUsageReportingService) getService()).registerOptInOptionsChangedListener(stub2, setOptInOptionsChangedListenerCallbacks);
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }
}
