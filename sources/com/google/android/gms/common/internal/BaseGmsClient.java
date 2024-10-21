package com.google.android.gms.common.internal;

import _COROUTINE._BOUNDARY;
import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.FeatureCreator;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.BuildInfo$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseGmsClient {
    private static final Feature[] EMPTY_FEATURES_ARRAY = new Feature[0];
    public volatile AppLifecycleMonitor attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public volatile String attributionTag;
    private GmsServiceConnection connection;
    public final FloatingActionButton.ShadowDelegateImpl connectionCallbacks$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl connectionFailedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Context context;
    public final int gCoreServiceId;
    protected ConnectionProgressReportCallbacks mConnectionProgressReportCallbacks;
    GmsServiceEndpoint mCurrentServiceEndpoint;
    final Handler mHandler;
    private final String realClientName;
    private IInterface service;
    public IGmsServiceBroker serviceBroker;
    private final GmsClientSupervisor supervisor;
    private volatile String lastDisconnectMessage = null;
    public final Object lock = new Object();
    public final Object serviceBrokerLock = new Object();
    public final ArrayList callbackProxyList = new ArrayList();
    public int connectState = 1;
    public ConnectionResult remoteServiceFailedResult = null;
    public boolean localServiceFailedConnect = false;
    public volatile ConnectionInfo connectionInfo = null;
    protected final AtomicInteger mDisconnectCount = new AtomicInteger(0);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class CallbackHandler extends TracingHandler {
        public CallbackHandler(Looper looper) {
            super(looper);
        }

        private static final void deliverCallbackFailed$ar$ds(Message message) {
            CallbackProxy callbackProxy = (CallbackProxy) message.obj;
            callbackProxy.onDeliverCallbackFailed();
            callbackProxy.unregister();
        }

        private static final boolean hasCallback$ar$ds(Message message) {
            if (message.what == 2 || message.what == 1 || message.what == 7) {
                return true;
            }
            return false;
        }

        /* JADX WARN: Type inference failed for: r0v21, types: [com.google.android.gms.common.api.internal.ConnectionCallbacks, java.lang.Object] */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Object obj;
            if (BaseGmsClient.this.mDisconnectCount.get() != message.arg1) {
                if (hasCallback$ar$ds(message)) {
                    deliverCallbackFailed$ar$ds(message);
                    return;
                }
                return;
            }
            if ((message.what != 1 && message.what != 7 && message.what != 4 && message.what != 5) || BaseGmsClient.this.isConnecting()) {
                PendingIntent pendingIntent = null;
                if (message.what == 4) {
                    BaseGmsClient.this.remoteServiceFailedResult = new ConnectionResult(message.arg2);
                    BaseGmsClient baseGmsClient = BaseGmsClient.this;
                    if (!baseGmsClient.localServiceFailedConnect && !TextUtils.isEmpty(baseGmsClient.getServiceDescriptor()) && !TextUtils.isEmpty(null)) {
                        try {
                            Class.forName(baseGmsClient.getServiceDescriptor());
                            BaseGmsClient baseGmsClient2 = BaseGmsClient.this;
                            if (!baseGmsClient2.localServiceFailedConnect) {
                                baseGmsClient2.setConnectState(3, null);
                                return;
                            }
                        } catch (ClassNotFoundException unused) {
                        }
                    }
                    ConnectionResult connectionResult = BaseGmsClient.this.remoteServiceFailedResult;
                    if (connectionResult == null) {
                        connectionResult = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
                    System.currentTimeMillis();
                    return;
                }
                if (message.what == 5) {
                    ConnectionResult connectionResult2 = BaseGmsClient.this.remoteServiceFailedResult;
                    if (connectionResult2 == null) {
                        connectionResult2 = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult2);
                    System.currentTimeMillis();
                    return;
                }
                if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent) message.obj;
                    }
                    BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(new ConnectionResult(message.arg2, pendingIntent));
                    System.currentTimeMillis();
                    return;
                }
                if (message.what == 6) {
                    BaseGmsClient.this.setConnectState(5, null);
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = BaseGmsClient.this.connectionCallbacks$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (shadowDelegateImpl != null) {
                        shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0.onConnectionSuspended(message.arg2);
                    }
                    int i = message.arg2;
                    System.currentTimeMillis();
                    BaseGmsClient.this.compareAndSetConnectState(5, 1, null);
                    return;
                }
                if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    deliverCallbackFailed$ar$ds(message);
                    return;
                }
                if (hasCallback$ar$ds(message)) {
                    CallbackProxy callbackProxy = (CallbackProxy) message.obj;
                    synchronized (callbackProxy) {
                        obj = callbackProxy.listener;
                        if (callbackProxy.callbackDelivered) {
                            Log.w("GmsClient", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(callbackProxy, "Callback proxy ", " being reused. This is not safe."));
                        }
                    }
                    if (obj != null) {
                        callbackProxy.deliverCallback$ar$ds();
                    }
                    synchronized (callbackProxy) {
                        callbackProxy.callbackDelivered = true;
                    }
                    callbackProxy.unregister();
                    return;
                }
                Log.wtf("GmsClient", "Don't know how to handle message: " + message.what, new Exception());
                return;
            }
            deliverCallbackFailed$ar$ds(message);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class CallbackProxy {
        public boolean callbackDelivered = false;
        public Object listener;

        public CallbackProxy(Object obj) {
            this.listener = obj;
        }

        protected abstract void deliverCallback$ar$ds();

        protected abstract void onDeliverCallbackFailed();

        public final void removeListener() {
            synchronized (this) {
                this.listener = null;
            }
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.callbackProxyList) {
                BaseGmsClient.this.callbackProxyList.remove(this);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GmsServiceConnection implements ServiceConnection {
        private final int disconnectCount;

        public GmsServiceConnection(int i) {
            this.disconnectCount = i;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            int i;
            int i2;
            if (iBinder == null) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                synchronized (baseGmsClient.lock) {
                    i = baseGmsClient.connectState;
                }
                if (i == 3) {
                    baseGmsClient.localServiceFailedConnect = true;
                    i2 = 5;
                } else {
                    i2 = 4;
                }
                Handler handler = baseGmsClient.mHandler;
                handler.sendMessage(handler.obtainMessage(i2, baseGmsClient.mDisconnectCount.get(), 16));
                return;
            }
            synchronized (BaseGmsClient.this.serviceBrokerLock) {
                BaseGmsClient baseGmsClient2 = BaseGmsClient.this;
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                if (queryLocalInterface != null && (queryLocalInterface instanceof IGmsServiceBroker)) {
                    iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                } else {
                    iGmsServiceBroker = new IGmsServiceBroker(iBinder) { // from class: com.google.android.gms.common.internal.IGmsServiceBroker$Stub$Proxy
                        private final IBinder remote;

                        {
                            this.remote = iBinder;
                        }

                        @Override // android.os.IInterface
                        public final IBinder asBinder() {
                            return this.remote;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // com.google.android.gms.common.internal.IGmsServiceBroker
                        public final void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) {
                            Parcel obtain = Parcel.obtain();
                            Parcel obtain2 = Parcel.obtain();
                            try {
                                obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                                obtain.writeStrongBinder(iGmsCallbacks);
                                obtain.writeInt(1);
                                FeatureCreator.writeToParcel(getServiceRequest, obtain, 0);
                                this.remote.transact(46, obtain, obtain2, 0);
                                obtain2.readException();
                            } finally {
                                obtain2.recycle();
                                obtain.recycle();
                            }
                        }
                    };
                }
                baseGmsClient2.serviceBroker = iGmsServiceBroker;
            }
            BaseGmsClient.this.onPostServiceBindingHandler$ar$ds(0, this.disconnectCount);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.serviceBrokerLock) {
                BaseGmsClient.this.serviceBroker = null;
            }
            BaseGmsClient baseGmsClient = BaseGmsClient.this;
            int i = this.disconnectCount;
            Handler handler = baseGmsClient.mHandler;
            handler.sendMessage(handler.obtainMessage(6, i, 1));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, ((GmsClient) baseGmsClient).scopes);
            } else {
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = BaseGmsClient.this.connectionFailedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (shadowDelegateImpl != null) {
                    shadowDelegateImpl.onConnectionFailed(connectionResult);
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostInitCallback extends ApiServiceCallback {
        public final IBinder service;

        public PostInitCallback(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.service = iBinder;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected final void handleServiceFailure(ConnectionResult connectionResult) {
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = BaseGmsClient.this.connectionFailedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (shadowDelegateImpl != null) {
                shadowDelegateImpl.onConnectionFailed(connectionResult);
            }
            System.currentTimeMillis();
        }

        /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.gms.common.api.internal.ConnectionCallbacks, java.lang.Object] */
        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected final boolean handleServiceSuccess() {
            try {
                IBinder iBinder = this.service;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(iBinder);
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    Log.w("GmsClient", "service descriptor mismatch: " + BaseGmsClient.this.getServiceDescriptor() + " vs. " + interfaceDescriptor);
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.service);
                if (createServiceInterface == null || (!BaseGmsClient.this.compareAndSetConnectState(2, 4, createServiceInterface) && !BaseGmsClient.this.compareAndSetConnectState(3, 4, createServiceInterface))) {
                    return false;
                }
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.remoteServiceFailedResult = null;
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = baseGmsClient.connectionCallbacks$ar$class_merging$ar$class_merging$ar$class_merging;
                if (shadowDelegateImpl != null) {
                    shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0.onConnected$ar$ds();
                    return true;
                }
                return true;
            } catch (RemoteException unused) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PostServiceBindingCallback extends ApiServiceCallback {
        public PostServiceBindingCallback(int i) {
            super(i, null);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected final void handleServiceFailure(ConnectionResult connectionResult) {
            BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(connectionResult);
            System.currentTimeMillis();
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ApiServiceCallback
        protected final boolean handleServiceSuccess() {
            BaseGmsClient.this.mConnectionProgressReportCallbacks.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2, String str) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(context, "Context must not be null");
        this.context = context;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(looper, "Looper must not be null");
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(gmsClientSupervisor, "Supervisor must not be null");
        this.supervisor = gmsClientSupervisor;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new CallbackHandler(looper);
        this.gCoreServiceId = i;
        this.connectionCallbacks$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.connectionFailedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
        this.realClientName = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setConnectState(int i, IInterface iInterface) {
        boolean z;
        boolean z2;
        GmsServiceEndpoint gmsServiceEndpoint;
        boolean z3 = false;
        if (i != 4) {
            z = false;
        } else {
            z = true;
        }
        if (iInterface == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z == z2) {
            z3 = true;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(z3);
        synchronized (this.lock) {
            this.connectState = i;
            this.service = iInterface;
            if (i != 1) {
                if (i != 2 && i != 3) {
                    if (i == 4) {
                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(iInterface);
                        System.currentTimeMillis();
                    }
                } else {
                    GmsServiceConnection gmsServiceConnection = this.connection;
                    if (gmsServiceConnection != null && (gmsServiceEndpoint = this.mCurrentServiceEndpoint) != null) {
                        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + gmsServiceEndpoint.startAction + " on " + gmsServiceEndpoint.packageName);
                        GmsClientSupervisor gmsClientSupervisor = this.supervisor;
                        String str = this.mCurrentServiceEndpoint.startAction;
                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str);
                        GmsServiceEndpoint gmsServiceEndpoint2 = this.mCurrentServiceEndpoint;
                        String str2 = gmsServiceEndpoint2.packageName;
                        int i2 = gmsServiceEndpoint2.bindFlags;
                        gmsClientSupervisor.unbindService(str, str2, GmsClientSupervisor.defaultBindFlags, gmsServiceConnection, getRealClientName(), this.mCurrentServiceEndpoint.useDynamicLookup);
                        this.mDisconnectCount.incrementAndGet();
                    }
                    GmsServiceConnection gmsServiceConnection2 = new GmsServiceConnection(this.mDisconnectCount.get());
                    this.connection = gmsServiceConnection2;
                    GmsServiceEndpoint gmsServiceEndpoint3 = new GmsServiceEndpoint("com.google.android.gms", getStartServiceAction(), GmsClientSupervisor.defaultBindFlags, getUseDynamicLookup());
                    this.mCurrentServiceEndpoint = gmsServiceEndpoint3;
                    if (gmsServiceEndpoint3.useDynamicLookup && getMinApkVersion() < 17895000) {
                        throw new IllegalStateException("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(String.valueOf(gmsServiceEndpoint3.startAction)));
                    }
                    GmsClientSupervisor gmsClientSupervisor2 = this.supervisor;
                    String str3 = gmsServiceEndpoint3.startAction;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str3);
                    GmsServiceEndpoint gmsServiceEndpoint4 = this.mCurrentServiceEndpoint;
                    String str4 = gmsServiceEndpoint4.packageName;
                    int i3 = gmsServiceEndpoint4.bindFlags;
                    String realClientName = getRealClientName();
                    boolean z4 = this.mCurrentServiceEndpoint.useDynamicLookup;
                    getBindServiceExecutor$ar$ds();
                    if (!gmsClientSupervisor2.bindService$ar$ds(new GmsClientSupervisor.ConnectionStatusConfig(str3, str4, GmsClientSupervisor.defaultBindFlags, z4), gmsServiceConnection2, realClientName)) {
                        GmsServiceEndpoint gmsServiceEndpoint5 = this.mCurrentServiceEndpoint;
                        Log.w("GmsClient", "unable to connect to service: " + gmsServiceEndpoint5.startAction + " on " + gmsServiceEndpoint5.packageName);
                        onPostServiceBindingHandler$ar$ds(16, this.mDisconnectCount.get());
                    }
                }
            } else {
                GmsServiceConnection gmsServiceConnection3 = this.connection;
                if (gmsServiceConnection3 != null) {
                    GmsClientSupervisor gmsClientSupervisor3 = this.supervisor;
                    String str5 = this.mCurrentServiceEndpoint.startAction;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(str5);
                    GmsServiceEndpoint gmsServiceEndpoint6 = this.mCurrentServiceEndpoint;
                    String str6 = gmsServiceEndpoint6.packageName;
                    int i4 = gmsServiceEndpoint6.bindFlags;
                    gmsClientSupervisor3.unbindService(str5, str6, GmsClientSupervisor.defaultBindFlags, gmsServiceConnection3, getRealClientName(), this.mCurrentServiceEndpoint.useDynamicLookup);
                    this.connection = null;
                }
            }
        }
    }

    public final boolean compareAndSetConnectState(int i, int i2, IInterface iInterface) {
        synchronized (this.lock) {
            if (this.connectState != i) {
                return false;
            }
            setConnectState(i2, iInterface);
            return true;
        }
    }

    public final void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.mConnectionProgressReportCallbacks = connectionProgressReportCallbacks;
        setConnectState(2, null);
    }

    protected abstract IInterface createServiceInterface(IBinder iBinder);

    public void disconnect() {
        this.mDisconnectCount.incrementAndGet();
        synchronized (this.callbackProxyList) {
            int size = this.callbackProxyList.size();
            for (int i = 0; i < size; i++) {
                ((CallbackProxy) this.callbackProxyList.get(i)).removeListener();
            }
            this.callbackProxyList.clear();
        }
        synchronized (this.serviceBrokerLock) {
            this.serviceBroker = null;
        }
        setConnectState(1, null);
    }

    public Account getAccount() {
        throw null;
    }

    public Feature[] getApiFeatures() {
        return EMPTY_FEATURES_ARRAY;
    }

    public final Feature[] getAvailableFeatures() {
        ConnectionInfo connectionInfo = this.connectionInfo;
        if (connectionInfo == null) {
            return null;
        }
        return connectionInfo.availableFeatures;
    }

    protected void getBindServiceExecutor$ar$ds() {
        throw null;
    }

    public final void getEndpointPackageName$ar$ds() {
        if (isConnected() && this.mCurrentServiceEndpoint != null) {
        } else {
            throw new RuntimeException("Failed to connect when checking package");
        }
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    public final String getLastDisconnectMessage() {
        return this.lastDisconnectMessage;
    }

    public int getMinApkVersion() {
        throw null;
    }

    protected final String getRealClientName() {
        String str = this.realClientName;
        if (str == null) {
            return this.context.getClass().getName();
        }
        return str;
    }

    public final void getRemoteService(IAccountAccessor iAccountAccessor, Set set) {
        Object obj;
        String attributionTag;
        String attributionTag2;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        if (Build.VERSION.SDK_INT < 31) {
            attributionTag2 = this.attributionTag;
        } else if (this.attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && (obj = this.attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.AppLifecycleMonitor$ar$tracker) != null) {
            attributionTag = BuildInfo$$ExternalSyntheticApiModelOutline0.m(obj).getAttributionTag();
            if (attributionTag != null) {
                attributionTag2 = BuildInfo$$ExternalSyntheticApiModelOutline0.m(obj).getAttributionTag();
            } else {
                attributionTag2 = this.attributionTag;
            }
        } else {
            attributionTag2 = this.attributionTag;
        }
        String str = attributionTag2;
        int i = this.gCoreServiceId;
        int i2 = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        Scope[] scopeArr = GetServiceRequest.EMPTY_SCOPES;
        Bundle bundle = new Bundle();
        Feature[] featureArr = GetServiceRequest.EMPTY_FEATURES;
        GetServiceRequest getServiceRequest = new GetServiceRequest(6, i, i2, null, null, scopeArr, bundle, null, featureArr, featureArr, true, 0, false, str);
        getServiceRequest.callingPackage = this.context.getPackageName();
        getServiceRequest.extraArgs = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.scopes = (Scope[]) set.toArray(new Scope[0]);
        }
        if (requiresSignIn()) {
            Account account = getAccount();
            if (account == null) {
                account = new Account("<<default account>>", "com.google");
            }
            getServiceRequest.clientRequestedAccount = account;
            if (iAccountAccessor != null) {
                getServiceRequest.accountAccessorBinder = iAccountAccessor.asBinder();
            }
        }
        getServiceRequest.clientRequiredFeatures = getRequiredFeatures();
        getServiceRequest.clientApiFeatures = getApiFeatures();
        if (usesClientTelemetry()) {
            getServiceRequest.requestingTelemetryConfiguration = true;
        }
        try {
            synchronized (this.serviceBrokerLock) {
                IGmsServiceBroker iGmsServiceBroker = this.serviceBroker;
                if (iGmsServiceBroker != null) {
                    iGmsServiceBroker.getService(new IGmsCallbacks.Stub(this, this.mDisconnectCount.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(6, this.mDisconnectCount.get(), 3));
        } catch (RemoteException e2) {
            e = e2;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.mDisconnectCount.get());
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            e = e4;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.mDisconnectCount.get());
        }
    }

    public Feature[] getRequiredFeatures() {
        throw null;
    }

    public final IInterface getService() {
        IInterface iInterface;
        synchronized (this.lock) {
            if (this.connectState != 5) {
                if (isConnected()) {
                    iInterface = this.service;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(iInterface, "Client is connected but service is null");
                } else {
                    throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
                }
            } else {
                throw new DeadObjectException();
            }
        }
        return iInterface;
    }

    protected abstract String getServiceDescriptor();

    protected abstract String getStartServiceAction();

    protected boolean getUseDynamicLookup() {
        return false;
    }

    public final boolean hasConnectionInfo() {
        if (this.connectionInfo != null) {
            return true;
        }
        return false;
    }

    public final boolean isConnected() {
        boolean z;
        synchronized (this.lock) {
            if (this.connectState == 4) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final boolean isConnecting() {
        boolean z;
        synchronized (this.lock) {
            int i = this.connectState;
            z = true;
            if (i != 2 && i != 3) {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1, i2, -1, new PostInitCallback(i, iBinder, bundle)));
    }

    protected final void onPostServiceBindingHandler$ar$ds(int i, int i2) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, i2, -1, new PostServiceBindingCallback(i)));
    }

    public final void onUserSignOut$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        GoogleApiManager.this.handler.post(new TrainingActivity$$ExternalSyntheticLambda1(shadowDelegateImpl, 10, null));
    }

    public boolean requiresSignIn() {
        return false;
    }

    public boolean usesClientTelemetry() {
        return false;
    }

    public void disconnect(String str) {
        this.lastDisconnectMessage = str;
        disconnect();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class ApiServiceCallback extends CallbackProxy {
        public final Bundle resolution;
        public final int statusCode;

        protected ApiServiceCallback(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.resolution = bundle;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.CallbackProxy
        protected final /* bridge */ /* synthetic */ void deliverCallback$ar$ds() {
            PendingIntent pendingIntent = null;
            if (this.statusCode != 0) {
                BaseGmsClient.this.setConnectState(1, null);
                Bundle bundle = this.resolution;
                if (bundle != null) {
                    pendingIntent = (PendingIntent) bundle.getParcelable("pendingIntent");
                }
                handleServiceFailure(new ConnectionResult(this.statusCode, pendingIntent));
                return;
            }
            if (!handleServiceSuccess()) {
                BaseGmsClient.this.setConnectState(1, null);
                handleServiceFailure(new ConnectionResult(8, null));
            }
        }

        protected abstract void handleServiceFailure(ConnectionResult connectionResult);

        protected abstract boolean handleServiceSuccess();

        @Override // com.google.android.gms.common.internal.BaseGmsClient.CallbackProxy
        protected final void onDeliverCallbackFailed() {
        }
    }

    public final void requiresGooglePlayServices$ar$ds() {
    }
}
