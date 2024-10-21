package com.google.android.gms.common.api.internal;

import _COROUTINE._BOUNDARY;
import android.app.ActivityManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.core.provider.CallbackWithHandler$2;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.ApiCallRunner;
import com.google.android.gms.common.api.internal.BaseLifecycleHelper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.common.internal.TelemetryLoggingClient;
import com.google.android.gms.common.internal.TelemetryLoggingOptions;
import com.google.android.gms.common.internal.service.InternalTelemetryLoggingClient;
import com.google.android.gms.common.internal.service.TelemetryLoggingClientImpl;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.platform.PendingIntentCompat;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.gms.signin.internal.SignInClientImpl;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import io.grpc.okhttp.internal.OptionalMethod;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleApiManager implements Handler.Callback {
    private static GoogleApiManager instance;
    public final GoogleApiAvailability apiAvailability;
    public final MetricsLogger apiAvailabilityCache$ar$class_merging$ar$class_merging;
    public volatile boolean autoResolveAvailabilityIssues;
    public final Context context;
    public final Handler handler;
    private TelemetryData telemetryData;
    private TelemetryLoggingClient telemetryLoggingClient;
    public static final Status SIGNED_OUT = new Status(4, "Sign-out occurred while this API call was in progress.");
    public static final Status SIGN_IN_REQUIRED = new Status(4, "The user must be signed in to make this API call.");
    public static final Object lock = new Object();
    public final long resumeDelayMs = 5000;
    public final long resumeTimeoutMs = 120000;
    public long serviceConnectionTimeoutMs = 10000;
    public boolean telemetryUnavailable = false;
    public final AtomicInteger nextApiInstanceId = new AtomicInteger(1);
    public final AtomicInteger signOutCount = new AtomicInteger(0);
    public final Map apiMap = new ConcurrentHashMap(5, 0.75f, 1);
    public ConnectionlessLifecycleHelper activeLifecycleHelper = null;
    public final Set activeLifecycleHelperApis = new ArraySet();
    private final Set authenticatedApis = new ArraySet();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ClientConnection implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        public final ApiKey apiKey;
        public final Api$Client client;
        private final WindowTrackerFactory inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging;
        public final int instanceId;
        public boolean resuming;
        private final SignInCoordinator signInCoordinator;
        public final Queue methodQueue = new LinkedList();
        private final Set availabilityCallbacks = new HashSet();
        public final Map registeredListeners = new HashMap();
        public final List retryingFeatures = new ArrayList();
        private ConnectionResult opportunisticConnectionFailure = null;
        public int numMethodInvocationsLogged = 0;

        /* JADX WARN: Multi-variable type inference failed */
        public ClientConnection(GoogleApi googleApi) {
            Looper looper = GoogleApiManager.this.handler.getLooper();
            ClientSettings build = googleApi.createClientSettingsBuilder().build();
            Api$Client buildClient = ((SpannableUtils$IdentifierSpan) googleApi.api$ar$class_merging$ar$class_merging$ar$class_merging.OptionalMethod$ar$returnType).buildClient(googleApi.context, looper, build, (Object) googleApi.apiOptions, (GoogleApiClient.ConnectionCallbacks) this, (GoogleApiClient.OnConnectionFailedListener) this);
            AppLifecycleMonitor appLifecycleMonitor = googleApi.attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (appLifecycleMonitor != null) {
                ((BaseGmsClient) buildClient).attributionSourceWrapper$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
            } else {
                String str = googleApi.attributionTag;
                if (str != null) {
                    ((BaseGmsClient) buildClient).attributionTag = str;
                }
            }
            this.client = buildClient;
            this.apiKey = googleApi.apiKey;
            this.inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging = new WindowTrackerFactory();
            this.instanceId = googleApi.id;
            if (buildClient.requiresSignIn()) {
                this.signInCoordinator = new SignInCoordinator(GoogleApiManager.this.context, GoogleApiManager.this.handler, googleApi.createClientSettingsBuilder().build());
            } else {
                this.signInCoordinator = null;
            }
        }

        private final void callAndClearAvailabilityCallbacks(ConnectionResult connectionResult) {
            Iterator it = this.availabilityCallbacks.iterator();
            if (it.hasNext()) {
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    this.client.getEndpointPackageName$ar$ds();
                }
                throw null;
            }
            this.availabilityCallbacks.clear();
        }

        private final boolean checkFeaturesAndRun(ApiCallRunner apiCallRunner) {
            if (!(apiCallRunner instanceof ApiCallRunner.FeatureRunner)) {
                run(apiCallRunner);
                return true;
            }
            ApiCallRunner.FeatureRunner featureRunner = (ApiCallRunner.FeatureRunner) apiCallRunner;
            Feature unsatisfiedFeature = getUnsatisfiedFeature(featureRunner.getRequiredFeatures(this));
            if (unsatisfiedFeature == null) {
                run(apiCallRunner);
                return true;
            }
            Log.w("GoogleApiManager", this.client.getClass().getName() + " could not execute call because it requires feature (" + unsatisfiedFeature.name + ", " + unsatisfiedFeature.getVersion() + ").");
            if (GoogleApiManager.this.autoResolveAvailabilityIssues && featureRunner.shouldAutoResolveMissingFeatures(this)) {
                FeatureApiKey featureApiKey = new FeatureApiKey(this.apiKey, unsatisfiedFeature);
                int indexOf = this.retryingFeatures.indexOf(featureApiKey);
                if (indexOf >= 0) {
                    FeatureApiKey featureApiKey2 = (FeatureApiKey) this.retryingFeatures.get(indexOf);
                    GoogleApiManager.this.handler.removeMessages(15, featureApiKey2);
                    Handler handler = GoogleApiManager.this.handler;
                    handler.sendMessageDelayed(Message.obtain(handler, 15, featureApiKey2), 5000L);
                    return false;
                }
                this.retryingFeatures.add(featureApiKey);
                Handler handler2 = GoogleApiManager.this.handler;
                handler2.sendMessageDelayed(Message.obtain(handler2, 15, featureApiKey), 5000L);
                Handler handler3 = GoogleApiManager.this.handler;
                handler3.sendMessageDelayed(Message.obtain(handler3, 16, featureApiKey), 120000L);
                ConnectionResult connectionResult = new ConnectionResult(2, null);
                if (!startResolutionWithLifecycleHelper(connectionResult)) {
                    GoogleApiManager.this.showErrorNotificationIfNeeded(connectionResult, this.instanceId);
                    return false;
                }
                return false;
            }
            featureRunner.reportFailure(new UnsupportedApiCallException(unsatisfiedFeature));
            return true;
        }

        private final void failEnqueuedMethods(Status status, Exception exc, boolean z) {
            boolean z2;
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            boolean z3 = false;
            if (status != null) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (exc == null) {
                z3 = true;
            }
            if (z2 != z3) {
                Iterator it = this.methodQueue.iterator();
                while (it.hasNext()) {
                    ApiCallRunner apiCallRunner = (ApiCallRunner) it.next();
                    if (!z || apiCallRunner.type == 2) {
                        if (status != null) {
                            apiCallRunner.reportFailure(status);
                        } else {
                            apiCallRunner.reportFailure(exc);
                        }
                        it.remove();
                    }
                }
                return;
            }
            throw new IllegalArgumentException("Status XOR exception should be null");
        }

        private final Feature getUnsatisfiedFeature(Feature[] featureArr) {
            if (featureArr != null) {
                Feature[] availableFeatures = this.client.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    arrayMap.put(feature.name, Long.valueOf(feature.getVersion()));
                }
                for (int i = 0; i <= 0; i++) {
                    Feature feature2 = featureArr[i];
                    Long l = (Long) arrayMap.get(feature2.name);
                    if (l == null || l.longValue() < feature2.getVersion()) {
                        return feature2;
                    }
                }
            }
            return null;
        }

        private final void resetServiceConnectionTimeout() {
            GoogleApiManager.this.handler.removeMessages(12, this.apiKey);
            Handler handler = GoogleApiManager.this.handler;
            handler.sendMessageDelayed(handler.obtainMessage(12, this.apiKey), GoogleApiManager.this.serviceConnectionTimeoutMs);
        }

        private final void run(ApiCallRunner apiCallRunner) {
            apiCallRunner.trackAsInProgressCall$ar$class_merging$ar$class_merging$ar$class_merging(this.inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging, requiresSignIn());
            try {
                apiCallRunner.run(this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.client.disconnect("DeadObjectException thrown while running ApiCallRunner.");
            }
        }

        private final boolean startResolutionWithLifecycleHelper(ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                GoogleApiManager googleApiManager = GoogleApiManager.this;
                if (googleApiManager.activeLifecycleHelper == null || !googleApiManager.activeLifecycleHelperApis.contains(this.apiKey)) {
                    return false;
                }
                ConnectionlessLifecycleHelper connectionlessLifecycleHelper = GoogleApiManager.this.activeLifecycleHelper;
                OrderVerifyingClientCall.State state = new OrderVerifyingClientCall.State(connectionResult, this.instanceId);
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(connectionlessLifecycleHelper.mFailingConnectionResult, state)) {
                    connectionlessLifecycleHelper.connectionFailedHandler.post(new BaseLifecycleHelper.ConnectionFailedResolver(connectionlessLifecycleHelper, state, 0));
                }
                return true;
            }
        }

        private final Status statusForFailedConnection(ConnectionResult connectionResult) {
            return GoogleApiManager.statusForFailedConnection(this.apiKey, connectionResult);
        }

        public final void clearOpportunisticConnectionFailure() {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            this.opportunisticConnectionFailure = null;
        }

        public final void connect() {
            int i;
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            if (!this.client.isConnected() && !this.client.isConnecting()) {
                try {
                    GoogleApiManager googleApiManager = GoogleApiManager.this;
                    MetricsLogger metricsLogger = googleApiManager.apiAvailabilityCache$ar$class_merging$ar$class_merging;
                    Context context = googleApiManager.context;
                    Api$Client api$Client = this.client;
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
                    StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(api$Client);
                    api$Client.requiresGooglePlayServices$ar$ds();
                    int minApkVersion = api$Client.getMinApkVersion();
                    int apkVersionAvailability$ar$ds = metricsLogger.getApkVersionAvailability$ar$ds(minApkVersion);
                    if (apkVersionAvailability$ar$ds == -1) {
                        synchronized (metricsLogger.MetricsLogger$ar$logSessionId) {
                            int i2 = 0;
                            int i3 = 0;
                            while (true) {
                                if (i3 < ((SparseIntArray) metricsLogger.MetricsLogger$ar$logSessionId).size()) {
                                    int keyAt = ((SparseIntArray) metricsLogger.MetricsLogger$ar$logSessionId).keyAt(i3);
                                    if (keyAt > minApkVersion && ((SparseIntArray) metricsLogger.MetricsLogger$ar$logSessionId).get(keyAt) == 0) {
                                        break;
                                    } else {
                                        i3++;
                                    }
                                } else {
                                    i2 = -1;
                                    break;
                                }
                            }
                            if (i2 == -1) {
                                i = ((GoogleApiAvailabilityLight) metricsLogger.MetricsLogger$ar$loggerProvider$ar$class_merging).isGooglePlayServicesAvailable(context, minApkVersion);
                            } else {
                                i = i2;
                            }
                            ((SparseIntArray) metricsLogger.MetricsLogger$ar$logSessionId).put(minApkVersion, i);
                        }
                        apkVersionAvailability$ar$ds = i;
                    }
                    if (apkVersionAvailability$ar$ds != 0) {
                        ConnectionResult connectionResult = new ConnectionResult(apkVersionAvailability$ar$ds, null);
                        Log.w("GoogleApiManager", "The service for " + this.client.getClass().getName() + " is not available: " + connectionResult.toString());
                        onConnectionFailed(connectionResult);
                        return;
                    }
                    GoogleApiManager googleApiManager2 = GoogleApiManager.this;
                    Api$Client api$Client2 = this.client;
                    GoogleApiProgressCallbacks googleApiProgressCallbacks = new GoogleApiProgressCallbacks(api$Client2, this.apiKey);
                    if (api$Client2.requiresSignIn()) {
                        SignInCoordinator signInCoordinator = this.signInCoordinator;
                        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(signInCoordinator);
                        SignInClientImpl signInClientImpl = signInCoordinator.mSignInClient$ar$class_merging;
                        if (signInClientImpl != null) {
                            signInClientImpl.disconnect();
                        }
                        signInCoordinator.mClientSettings.sessionId = Integer.valueOf(System.identityHashCode(signInCoordinator));
                        SpannableUtils$IdentifierSpan spannableUtils$IdentifierSpan = signInCoordinator.mClientBuilder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                        Context context2 = signInCoordinator.mContext;
                        Handler handler = signInCoordinator.mHandler;
                        ClientSettings clientSettings = signInCoordinator.mClientSettings;
                        signInCoordinator.mSignInClient$ar$class_merging = (SignInClientImpl) spannableUtils$IdentifierSpan.buildClient(context2, handler.getLooper(), clientSettings, (Object) clientSettings.signInOptions, (GoogleApiClient.ConnectionCallbacks) signInCoordinator, (GoogleApiClient.OnConnectionFailedListener) signInCoordinator);
                        signInCoordinator.mCallback$ar$class_merging$71a2fb1_0 = googleApiProgressCallbacks;
                        Set set = signInCoordinator.mScopes;
                        if (set != null && !set.isEmpty()) {
                            signInCoordinator.mSignInClient$ar$class_merging.connect();
                        } else {
                            signInCoordinator.mHandler.post(new TrainingActivity$$ExternalSyntheticLambda1(signInCoordinator, 11, null));
                        }
                    }
                    try {
                        this.client.connect(googleApiProgressCallbacks);
                    } catch (SecurityException e) {
                        onConnectionFailed(new ConnectionResult(10), e);
                    }
                } catch (IllegalStateException e2) {
                    onConnectionFailed(new ConnectionResult(10), e2);
                }
            }
        }

        public final void enqueue(ApiCallRunner apiCallRunner) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.client.isConnected()) {
                if (checkFeaturesAndRun(apiCallRunner)) {
                    resetServiceConnectionTimeout();
                    return;
                } else {
                    this.methodQueue.add(apiCallRunner);
                    return;
                }
            }
            this.methodQueue.add(apiCallRunner);
            ConnectionResult connectionResult = this.opportunisticConnectionFailure;
            if (connectionResult != null && connectionResult.hasResolution()) {
                onConnectionFailed(this.opportunisticConnectionFailure);
            } else {
                connect();
            }
        }

        public final void failAllEnqueuedMethods(Status status) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            failEnqueuedMethods(status, null, false);
        }

        public final void flushQueue() {
            ArrayList arrayList = new ArrayList(this.methodQueue);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ApiCallRunner apiCallRunner = (ApiCallRunner) arrayList.get(i);
                if (this.client.isConnected()) {
                    if (checkFeaturesAndRun(apiCallRunner)) {
                        this.methodQueue.remove(apiCallRunner);
                    }
                } else {
                    return;
                }
            }
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnected$ar$ds() {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectedInternal();
                return;
            }
            GoogleApiManager googleApiManager = GoogleApiManager.this;
            googleApiManager.handler.post(new TrainingActivity$$ExternalSyntheticLambda1(this, 9, null));
        }

        public final void onConnectedInternal() {
            clearOpportunisticConnectionFailure();
            callAndClearAvailabilityCallbacks(ConnectionResult.RESULT_SUCCESS);
            stopResuming();
            Iterator it = this.registeredListeners.values().iterator();
            while (it.hasNext()) {
                OptionalMethod optionalMethod = (OptionalMethod) it.next();
                if (getUnsatisfiedFeature(((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).requiredFeatures) != null) {
                    it.remove();
                } else {
                    try {
                        ((RegisterListenerMethod) optionalMethod.OptionalMethod$ar$methodParams).registerListener$ar$class_merging$ar$class_merging(this.client, new AppLifecycleMonitor((short[]) null));
                    } catch (DeadObjectException unused) {
                        onConnectionSuspended(3);
                        this.client.disconnect("DeadObjectException thrown while calling register listener method.");
                    } catch (RemoteException e) {
                        e = e;
                        Log.e("GoogleApiManager", "Failed to register listener on re-connection.", e);
                        it.remove();
                    } catch (RuntimeException e2) {
                        e = e2;
                        Log.e("GoogleApiManager", "Failed to register listener on re-connection.", e);
                        it.remove();
                    }
                }
            }
            flushQueue();
            resetServiceConnectionTimeout();
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            onConnectionFailed(connectionResult, null);
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                onConnectionSuspendedInternal(i);
                return;
            }
            GoogleApiManager googleApiManager = GoogleApiManager.this;
            googleApiManager.handler.post(new CallbackWithHandler$2(this, i, 19));
        }

        public final void onConnectionSuspendedInternal(int i) {
            clearOpportunisticConnectionFailure();
            this.resuming = true;
            String lastDisconnectMessage = this.client.getLastDisconnectMessage();
            StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
            if (i == 1) {
                sb.append(" due to service disconnection.");
            } else if (i == 3) {
                sb.append(" due to dead object exception.");
            }
            if (lastDisconnectMessage != null) {
                sb.append(" Last reason for disconnect: ");
                sb.append(lastDisconnectMessage);
            }
            this.inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging.failCalls(true, new Status(20, sb.toString()));
            GoogleApiManager googleApiManager = GoogleApiManager.this;
            ApiKey apiKey = this.apiKey;
            Handler handler = googleApiManager.handler;
            handler.sendMessageDelayed(Message.obtain(handler, 9, apiKey), 5000L);
            GoogleApiManager googleApiManager2 = GoogleApiManager.this;
            ApiKey apiKey2 = this.apiKey;
            Handler handler2 = googleApiManager2.handler;
            handler2.sendMessageDelayed(Message.obtain(handler2, 11, apiKey2), 120000L);
            GoogleApiManager.this.apiAvailabilityCache$ar$class_merging$ar$class_merging.flush();
            Iterator it = this.registeredListeners.values().iterator();
            while (it.hasNext()) {
                Object obj = ((OptionalMethod) it.next()).OptionalMethod$ar$methodName;
            }
        }

        public final void onSignInFailed(ConnectionResult connectionResult) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            Api$Client api$Client = this.client;
            api$Client.disconnect("onSignInFailed for " + api$Client.getClass().getName() + " with " + String.valueOf(connectionResult));
            onConnectionFailed(connectionResult);
        }

        public final boolean requiresSignIn() {
            return this.client.requiresSignIn();
        }

        public final void signOut() {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            failAllEnqueuedMethods(GoogleApiManager.SIGNED_OUT);
            this.inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging.failCalls(false, GoogleApiManager.SIGNED_OUT);
            for (ListenerHolder.ListenerKey listenerKey : (ListenerHolder.ListenerKey[]) this.registeredListeners.keySet().toArray(new ListenerHolder.ListenerKey[0])) {
                enqueue(new ApiCallRunner.UnregisterListenerRunner(listenerKey, new AppLifecycleMonitor((short[]) null)));
            }
            callAndClearAvailabilityCallbacks(new ConnectionResult(4));
            if (this.client.isConnected()) {
                this.client.onUserSignOut$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new FloatingActionButton.ShadowDelegateImpl(this));
            }
        }

        public final void stopResuming() {
            if (this.resuming) {
                GoogleApiManager googleApiManager = GoogleApiManager.this;
                googleApiManager.handler.removeMessages(11, this.apiKey);
                GoogleApiManager googleApiManager2 = GoogleApiManager.this;
                googleApiManager2.handler.removeMessages(9, this.apiKey);
                this.resuming = false;
            }
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v8, types: [java.util.Map, java.lang.Object] */
        public final void timeoutServiceConnection$ar$ds(boolean z) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.client.isConnected() && this.registeredListeners.isEmpty()) {
                WindowTrackerFactory windowTrackerFactory = this.inProgressCalls$ar$class_merging$ar$class_merging$ar$class_merging;
                if (windowTrackerFactory.WindowTrackerFactory$ar$executorProvider.isEmpty() && windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider.isEmpty()) {
                    this.client.disconnect("Timing out service connection.");
                } else {
                    resetServiceConnectionTimeout();
                }
            }
        }

        public final void onConnectionFailed(ConnectionResult connectionResult, Exception exc) {
            SignInClientImpl signInClientImpl;
            StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
            SignInCoordinator signInCoordinator = this.signInCoordinator;
            if (signInCoordinator != null && (signInClientImpl = signInCoordinator.mSignInClient$ar$class_merging) != null) {
                signInClientImpl.disconnect();
            }
            clearOpportunisticConnectionFailure();
            GoogleApiManager.this.apiAvailabilityCache$ar$class_merging$ar$class_merging.flush();
            callAndClearAvailabilityCallbacks(connectionResult);
            if ((this.client instanceof TelemetryLoggingClientImpl) && connectionResult.statusCode != 24) {
                GoogleApiManager googleApiManager = GoogleApiManager.this;
                googleApiManager.telemetryUnavailable = true;
                Handler handler = googleApiManager.handler;
                handler.sendMessageDelayed(handler.obtainMessage(19), 300000L);
            }
            if (connectionResult.statusCode == 4) {
                failAllEnqueuedMethods(GoogleApiManager.SIGN_IN_REQUIRED);
                return;
            }
            if (this.methodQueue.isEmpty()) {
                this.opportunisticConnectionFailure = connectionResult;
                return;
            }
            if (exc != null) {
                StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
                failEnqueuedMethods(null, exc, false);
                return;
            }
            if (!GoogleApiManager.this.autoResolveAvailabilityIssues) {
                failAllEnqueuedMethods(statusForFailedConnection(connectionResult));
                return;
            }
            failEnqueuedMethods(statusForFailedConnection(connectionResult), null, true);
            if (this.methodQueue.isEmpty() || startResolutionWithLifecycleHelper(connectionResult) || GoogleApiManager.this.showErrorNotificationIfNeeded(connectionResult, this.instanceId)) {
                return;
            }
            if (connectionResult.statusCode == 18) {
                this.resuming = true;
            }
            if (this.resuming) {
                GoogleApiManager googleApiManager2 = GoogleApiManager.this;
                ApiKey apiKey = this.apiKey;
                Handler handler2 = googleApiManager2.handler;
                handler2.sendMessageDelayed(Message.obtain(handler2, 9, apiKey), 5000L);
                return;
            }
            failAllEnqueuedMethods(statusForFailedConnection(connectionResult));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeatureApiKey {
        public final Feature feature;
        public final ApiKey key;

        public FeatureApiKey(ApiKey apiKey, Feature feature) {
            this.key = apiKey;
            this.feature = feature;
        }

        public final boolean equals(Object obj) {
            if (obj != null && (obj instanceof FeatureApiKey)) {
                FeatureApiKey featureApiKey = (FeatureApiKey) obj;
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.key, featureApiKey.key) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.feature, featureApiKey.feature)) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.key, this.feature});
        }

        public final String toString() {
            Objects$ToStringHelper objects$ToStringHelper = new Objects$ToStringHelper(this);
            objects$ToStringHelper.add$ar$ds$bdea682c_0("key", this.key);
            objects$ToStringHelper.add$ar$ds$bdea682c_0("feature", this.feature);
            return objects$ToStringHelper.toString();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GoogleApiProgressCallbacks implements BaseGmsClient.ConnectionProgressReportCallbacks {
        public final ApiKey apiKey;
        public final Api$Client client;
        public IAccountAccessor resolvedAccountAccessor = null;
        public Set scopes = null;
        public boolean serviceBound = false;

        public GoogleApiProgressCallbacks(Api$Client api$Client, ApiKey apiKey) {
            this.client = api$Client;
            this.apiKey = apiKey;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public final void onReportServiceBinding(ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post(new ListMenuManager$$ExternalSyntheticLambda3(this, connectionResult, 11));
        }

        public final void onSignInFailed(ConnectionResult connectionResult) {
            ClientConnection clientConnection = (ClientConnection) GoogleApiManager.this.apiMap.get(this.apiKey);
            if (clientConnection != null) {
                clientConnection.onSignInFailed(connectionResult);
            }
        }

        public final void tryGetRemoteService() {
            IAccountAccessor iAccountAccessor;
            if (this.serviceBound && (iAccountAccessor = this.resolvedAccountAccessor) != null) {
                this.client.getRemoteService(iAccountAccessor, this.scopes);
            }
        }
    }

    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.autoResolveAvailabilityIssues = true;
        this.context = context;
        TracingHandler tracingHandler = new TracingHandler(looper, this);
        this.handler = tracingHandler;
        this.apiAvailability = googleApiAvailability;
        this.apiAvailabilityCache$ar$class_merging$ar$class_merging = new MetricsLogger(googleApiAvailability);
        Boolean bool = DeviceProperties.isIoT;
        PackageManager packageManager = context.getPackageManager();
        if (DeviceProperties.isAuto == null) {
            DeviceProperties.isAuto = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.automotive"));
        }
        if (DeviceProperties.isAuto.booleanValue()) {
            this.autoResolveAvailabilityIssues = false;
        }
        tracingHandler.sendMessage(tracingHandler.obtainMessage(6));
    }

    public static GoogleApiManager getInstance(Context context) {
        GoogleApiManager googleApiManager;
        HandlerThread handlerThread;
        synchronized (lock) {
            if (instance == null) {
                synchronized (GmsClientSupervisor.singletonLock) {
                    handlerThread = GmsClientSupervisor.handlerThread;
                    if (handlerThread == null) {
                        GmsClientSupervisor.handlerThread = new HandlerThread("GoogleApiHandler", 9);
                        GmsClientSupervisor.handlerThread.start();
                        handlerThread = GmsClientSupervisor.handlerThread;
                    }
                }
                instance = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.INSTANCE);
            }
            googleApiManager = instance;
        }
        return googleApiManager;
    }

    private final TelemetryLoggingClient getTelemetryLoggingClient() {
        if (this.telemetryLoggingClient == null) {
            this.telemetryLoggingClient = new InternalTelemetryLoggingClient(this.context, TelemetryLoggingOptions.DEFAULT_OPTIONS);
        }
        return this.telemetryLoggingClient;
    }

    private final void logAndResetTelemetryData() {
        TelemetryData telemetryData = this.telemetryData;
        if (telemetryData != null) {
            if (telemetryData.telemetryConfigVersion > 0 || isClientTelemetryPossiblyEnabled()) {
                getTelemetryLoggingClient().log(telemetryData);
            }
            this.telemetryData = null;
        }
    }

    private final ClientConnection registerInternal(GoogleApi googleApi) {
        Map map = this.apiMap;
        ApiKey apiKey = googleApi.apiKey;
        ClientConnection clientConnection = (ClientConnection) map.get(apiKey);
        if (clientConnection == null) {
            clientConnection = new ClientConnection(googleApi);
            this.apiMap.put(apiKey, clientConnection);
        }
        if (clientConnection.requiresSignIn()) {
            this.authenticatedApis.add(apiKey);
        }
        clientConnection.connect();
        return clientConnection;
    }

    public static Status statusForFailedConnection(ApiKey apiKey, ConnectionResult connectionResult) {
        Object obj = apiKey.api$ar$class_merging$ar$class_merging$ar$class_merging.OptionalMethod$ar$methodName;
        return new Status(17, "API: " + ((String) obj) + " is not available on this device. Connection failed with: " + String.valueOf(connectionResult), connectionResult.pendingIntent, connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ClientConnection getClientConnectionForKey(ApiKey apiKey) {
        return (ClientConnection) this.apiMap.get(apiKey);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Status status;
        Feature[] requiredFeatures;
        long j = 300000;
        ClientConnection clientConnection = null;
        switch (message.what) {
            case 1:
                if (true == ((Boolean) message.obj).booleanValue()) {
                    j = 10000;
                }
                this.serviceConnectionTimeoutMs = j;
                this.handler.removeMessages(12);
                for (ApiKey apiKey : this.apiMap.keySet()) {
                    Handler handler = this.handler;
                    handler.sendMessageDelayed(handler.obtainMessage(12, apiKey), this.serviceConnectionTimeoutMs);
                }
                return true;
            case 2:
                throw null;
            case 3:
                for (ClientConnection clientConnection2 : this.apiMap.values()) {
                    clientConnection2.clearOpportunisticConnectionFailure();
                    clientConnection2.connect();
                }
                return true;
            case 4:
            case 8:
            case 13:
                PhenotypeProcessReaper phenotypeProcessReaper = (PhenotypeProcessReaper) message.obj;
                ClientConnection clientConnection3 = (ClientConnection) this.apiMap.get(((GoogleApi) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).apiKey);
                if (clientConnection3 == null) {
                    clientConnection3 = registerInternal((GoogleApi) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider);
                }
                if (clientConnection3.requiresSignIn() && this.signOutCount.get() != phenotypeProcessReaper.pollingMinutes) {
                    ((ApiCallRunner) phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).reportFailure(SIGNED_OUT);
                    clientConnection3.signOut();
                } else {
                    clientConnection3.enqueue((ApiCallRunner) phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable);
                }
                return true;
            case 5:
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                Iterator it = this.apiMap.values().iterator();
                while (true) {
                    if (it.hasNext()) {
                        ClientConnection clientConnection4 = (ClientConnection) it.next();
                        if (clientConnection4.instanceId == i) {
                            clientConnection = clientConnection4;
                        }
                    }
                }
                if (clientConnection != null) {
                    if (connectionResult.statusCode == 13) {
                        String str = connectionResult.statusMessage;
                        clientConnection.failAllEnqueuedMethods(new Status(17, "Error resolution was canceled by the user, original error message: " + ConnectionResult.getStatusString(13) + ": " + str));
                    } else {
                        clientConnection.failAllEnqueuedMethods(statusForFailedConnection(clientConnection.apiKey, connectionResult));
                    }
                } else {
                    Log.wtf("GoogleApiManager", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i, "Could not find API instance ", " while trying to fail enqueued calls."), new Exception());
                }
                return true;
            case 6:
                if (this.context.getApplicationContext() instanceof Application) {
                    Application application = (Application) this.context.getApplicationContext();
                    synchronized (BackgroundDetector.instance) {
                        BackgroundDetector backgroundDetector = BackgroundDetector.instance;
                        if (!backgroundDetector.initialized) {
                            application.registerActivityLifecycleCallbacks(backgroundDetector);
                            application.registerComponentCallbacks(BackgroundDetector.instance);
                            BackgroundDetector.instance.initialized = true;
                        }
                    }
                    BackgroundDetector backgroundDetector2 = BackgroundDetector.instance;
                    FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = new FloatingActionButton.ShadowDelegateImpl(this);
                    synchronized (backgroundDetector2) {
                        backgroundDetector2.listeners.add(shadowDelegateImpl);
                    }
                    BackgroundDetector backgroundDetector3 = BackgroundDetector.instance;
                    if (!backgroundDetector3.stateKnown.get()) {
                        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
                        ActivityManager.getMyMemoryState(runningAppProcessInfo);
                        if (!backgroundDetector3.stateKnown.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                            backgroundDetector3.background.set(true);
                        }
                    }
                    if (!backgroundDetector3.background.get()) {
                        this.serviceConnectionTimeoutMs = 300000L;
                    }
                }
                return true;
            case 7:
                registerInternal((GoogleApi) message.obj);
                return true;
            case 9:
                if (this.apiMap.containsKey(message.obj)) {
                    ClientConnection clientConnection5 = (ClientConnection) this.apiMap.get(message.obj);
                    StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
                    if (clientConnection5.resuming) {
                        clientConnection5.connect();
                    }
                }
                return true;
            case 10:
                ArraySet.ElementIterator elementIterator = new ArraySet.ElementIterator();
                while (elementIterator.hasNext()) {
                    ClientConnection clientConnection6 = (ClientConnection) this.apiMap.remove((ApiKey) elementIterator.next());
                    if (clientConnection6 != null) {
                        clientConnection6.signOut();
                    }
                }
                this.authenticatedApis.clear();
                return true;
            case 11:
                if (this.apiMap.containsKey(message.obj)) {
                    ClientConnection clientConnection7 = (ClientConnection) this.apiMap.get(message.obj);
                    StrictModeUtils$VmPolicyBuilderCompatS.checkHandlerThread(GoogleApiManager.this.handler);
                    if (clientConnection7.resuming) {
                        clientConnection7.stopResuming();
                        GoogleApiManager googleApiManager = GoogleApiManager.this;
                        if (googleApiManager.apiAvailability.isGooglePlayServicesAvailable(googleApiManager.context) == 18) {
                            status = new Status(21, "Connection timed out waiting for Google Play services update to complete.");
                        } else {
                            status = new Status(22, "API failed to connect while resuming due to an unknown error.");
                        }
                        clientConnection7.failAllEnqueuedMethods(status);
                        clientConnection7.client.disconnect("Timing out connection while resuming.");
                    }
                }
                return true;
            case 12:
                if (this.apiMap.containsKey(message.obj)) {
                    ((ClientConnection) this.apiMap.get(message.obj)).timeoutServiceConnection$ar$ds(true);
                }
                return true;
            case 14:
                throw null;
            case 15:
                FeatureApiKey featureApiKey = (FeatureApiKey) message.obj;
                if (this.apiMap.containsKey(featureApiKey.key)) {
                    ClientConnection clientConnection8 = (ClientConnection) this.apiMap.get(featureApiKey.key);
                    if (clientConnection8.retryingFeatures.contains(featureApiKey) && !clientConnection8.resuming) {
                        if (!clientConnection8.client.isConnected()) {
                            clientConnection8.connect();
                        } else {
                            clientConnection8.flushQueue();
                        }
                    }
                }
                return true;
            case 16:
                FeatureApiKey featureApiKey2 = (FeatureApiKey) message.obj;
                if (this.apiMap.containsKey(featureApiKey2.key)) {
                    ClientConnection clientConnection9 = (ClientConnection) this.apiMap.get(featureApiKey2.key);
                    if (clientConnection9.retryingFeatures.remove(featureApiKey2)) {
                        GoogleApiManager.this.handler.removeMessages(15, featureApiKey2);
                        GoogleApiManager.this.handler.removeMessages(16, featureApiKey2);
                        Feature feature = featureApiKey2.feature;
                        ArrayList arrayList = new ArrayList(clientConnection9.methodQueue.size());
                        for (ApiCallRunner apiCallRunner : clientConnection9.methodQueue) {
                            if ((apiCallRunner instanceof ApiCallRunner.FeatureRunner) && (requiredFeatures = ((ApiCallRunner.FeatureRunner) apiCallRunner).getRequiredFeatures(clientConnection9)) != null) {
                                int i2 = 0;
                                while (true) {
                                    if (i2 > 0) {
                                        break;
                                    }
                                    if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(requiredFeatures[i2], feature)) {
                                        if (i2 >= 0) {
                                            arrayList.add(apiCallRunner);
                                        }
                                    } else {
                                        i2++;
                                    }
                                }
                            }
                        }
                        int size = arrayList.size();
                        for (int i3 = 0; i3 < size; i3++) {
                            ApiCallRunner apiCallRunner2 = (ApiCallRunner) arrayList.get(i3);
                            clientConnection9.methodQueue.remove(apiCallRunner2);
                            apiCallRunner2.reportFailure(new UnsupportedApiCallException(feature));
                        }
                    }
                }
                return true;
            case 17:
                logAndResetTelemetryData();
                return true;
            case 18:
                MethodInvocationMessage methodInvocationMessage = (MethodInvocationMessage) message.obj;
                if (methodInvocationMessage.batchPeriodMillis == 0) {
                    getTelemetryLoggingClient().log(new TelemetryData(methodInvocationMessage.configVersion, Arrays.asList(methodInvocationMessage.methodInvocation)));
                } else {
                    TelemetryData telemetryData = this.telemetryData;
                    if (telemetryData != null) {
                        List list = telemetryData.methodInvocations;
                        if (telemetryData.telemetryConfigVersion == methodInvocationMessage.configVersion && (list == null || list.size() < methodInvocationMessage.maxMethodsInBatch)) {
                            TelemetryData telemetryData2 = this.telemetryData;
                            MethodInvocation methodInvocation = methodInvocationMessage.methodInvocation;
                            if (telemetryData2.methodInvocations == null) {
                                telemetryData2.methodInvocations = new ArrayList();
                            }
                            telemetryData2.methodInvocations.add(methodInvocation);
                        } else {
                            this.handler.removeMessages(17);
                            logAndResetTelemetryData();
                        }
                    }
                    if (this.telemetryData == null) {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(methodInvocationMessage.methodInvocation);
                        this.telemetryData = new TelemetryData(methodInvocationMessage.configVersion, arrayList2);
                        Handler handler2 = this.handler;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), methodInvocationMessage.batchPeriodMillis);
                    }
                }
                return true;
            case 19:
                this.telemetryUnavailable = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isClientTelemetryPossiblyEnabled() {
        if (this.telemetryUnavailable) {
            return false;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().config;
        if (rootTelemetryConfiguration != null && !rootTelemetryConfiguration.methodInvocationTelemetryEnabled) {
            return false;
        }
        int apkVersionAvailability$ar$ds = this.apiAvailabilityCache$ar$class_merging$ar$class_merging.getApkVersionAvailability$ar$ds(203400000);
        if (apkVersionAvailability$ar$ds != -1 && apkVersionAvailability$ar$ds != 0) {
            return false;
        }
        return true;
    }

    public final void maybeAddInvocationListener$ar$class_merging$ar$class_merging(AppLifecycleMonitor appLifecycleMonitor, int i, GoogleApi googleApi) {
        long j;
        long j2;
        if (i != 0) {
            ApiKey apiKey = googleApi.apiKey;
            MethodInvocationLoggingListener methodInvocationLoggingListener = null;
            if (isClientTelemetryPossiblyEnabled()) {
                RootTelemetryConfiguration rootTelemetryConfiguration = RootTelemetryConfigManager.getInstance().config;
                boolean z = true;
                if (rootTelemetryConfiguration != null) {
                    if (rootTelemetryConfiguration.methodInvocationTelemetryEnabled) {
                        boolean z2 = rootTelemetryConfiguration.methodTimingTelemetryEnabled;
                        ClientConnection clientConnectionForKey = getClientConnectionForKey(apiKey);
                        if (clientConnectionForKey != null) {
                            Object obj = clientConnectionForKey.client;
                            if (obj instanceof BaseGmsClient) {
                                BaseGmsClient baseGmsClient = (BaseGmsClient) obj;
                                if (baseGmsClient.hasConnectionInfo() && !baseGmsClient.isConnecting()) {
                                    ConnectionTelemetryConfiguration configIfShouldLogMethodInvocation = MethodInvocationLoggingListener.getConfigIfShouldLogMethodInvocation(clientConnectionForKey, baseGmsClient, i);
                                    if (configIfShouldLogMethodInvocation != null) {
                                        clientConnectionForKey.numMethodInvocationsLogged++;
                                        z = configIfShouldLogMethodInvocation.methodTimingTelemetryEnabled;
                                    }
                                }
                            }
                        }
                        z = z2;
                    }
                }
                if (z) {
                    j = System.currentTimeMillis();
                } else {
                    j = 0;
                }
                if (z) {
                    j2 = SystemClock.elapsedRealtime();
                } else {
                    j2 = 0;
                }
                methodInvocationLoggingListener = new MethodInvocationLoggingListener(this, i, apiKey, j, j2);
            }
            if (methodInvocationLoggingListener != null) {
                Object obj2 = appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
                final Handler handler = this.handler;
                handler.getClass();
                ((Task) obj2).addOnCompleteListener$ar$ds$6dfdfa2c_0(new Executor() { // from class: com.google.android.gms.common.api.internal.GoogleApiManager$$ExternalSyntheticLambda0
                    @Override // java.util.concurrent.Executor
                    public final void execute(Runnable runnable) {
                        handler.post(runnable);
                    }
                }, methodInvocationLoggingListener);
            }
        }
    }

    public final void onErrorResolutionFailed(ConnectionResult connectionResult, int i) {
        if (!showErrorNotificationIfNeeded(connectionResult, i)) {
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public final void onErrorsResolved() {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void registerLifecycleHelper(ConnectionlessLifecycleHelper connectionlessLifecycleHelper) {
        synchronized (lock) {
            if (this.activeLifecycleHelper != connectionlessLifecycleHelper) {
                this.activeLifecycleHelper = connectionlessLifecycleHelper;
                this.activeLifecycleHelperApis.clear();
            }
            this.activeLifecycleHelperApis.addAll(connectionlessLifecycleHelper.managedApiKeys);
        }
    }

    final boolean showErrorNotificationIfNeeded(ConnectionResult connectionResult, int i) {
        PendingIntent errorResolutionPendingIntent$ar$ds;
        Context context = this.context;
        if (StrictModeUtils$VmPolicyBuilderCompatS.isInstantApp(context)) {
            return false;
        }
        GoogleApiAvailability googleApiAvailability = this.apiAvailability;
        if (connectionResult.hasResolution()) {
            errorResolutionPendingIntent$ar$ds = connectionResult.pendingIntent;
        } else {
            errorResolutionPendingIntent$ar$ds = googleApiAvailability.getErrorResolutionPendingIntent$ar$ds(context, connectionResult.statusCode, null);
        }
        if (errorResolutionPendingIntent$ar$ds == null) {
            return false;
        }
        googleApiAvailability.showErrorNotification$ar$ds(context, connectionResult.statusCode, PendingIntent.getActivity(context, 0, GoogleApiActivity.getIntentForResolution(context, errorResolutionPendingIntent$ar$ds, i, true), PendingIntentCompat.FLAG_MUTABLE | 134217728));
        return true;
    }
}
