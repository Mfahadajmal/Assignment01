package com.google.android.apps.aicore.client.api.internal;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.aidl.IAICoreService$Stub$Proxy;
import com.google.android.apps.aicore.aidl.IAiCoreServiceProvider;
import com.google.android.apps.aicore.aidl.IAiCoreServiceProvider$Stub$Proxy;
import com.google.android.apps.aicore.aidl.IAiCoreServiceProviderCallback;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiCoreClientOptions;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.libraries.phenotype.client.stable.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.SettableFuture;
import com.google.search.mdi.aratea.proto.FeatureName;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiCoreClientImpl implements AiCoreClient {
    public static final ComponentName AICORE_COMPONENT_NAME;
    public static final ComponentName AICORE_FALLBACK_COMPONENT_NAME;
    public static final String TAG = "AiCoreClientImpl";
    public final Executor callbackExecutor;
    public final Context context;
    private final Object lock = new Object();
    private AiCoreServiceConnection serviceConnection;
    public final ListeningExecutorService workerExecutor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AiCoreServiceConnection implements ServiceConnection {
        public SettableFuture serviceContextFuture;
        public final Object lock = new Object();
        private FailureSignal disconnectSignal = new FailureSignal();

        public AiCoreServiceConnection() {
            resetServiceContextFuture();
        }

        public final boolean bindService(Intent intent) {
            return AiCoreClientImpl.this.context.bindService(intent, this, 1);
        }

        public final void handleAiCoreService(IAICoreService iAICoreService) {
            synchronized (this.lock) {
                this.serviceContextFuture.set(new AiCoreClient.ServiceContext(iAICoreService, this.disconnectSignal));
            }
        }

        public final void handleConnectionError(AiCoreException aiCoreException) {
            synchronized (this.lock) {
                this.serviceContextFuture.setException(aiCoreException);
            }
            AiCoreClientImpl.this.cleanUpConnection();
        }

        @Override // android.content.ServiceConnection
        public final void onBindingDied(ComponentName componentName) {
            synchronized (this.lock) {
                this.disconnectSignal.signal();
                handleConnectionError(AiCoreException.newConnectionError(603, "AiCore service binding died."));
            }
            AiCoreClientImpl.this.cleanUpConnection();
        }

        @Override // android.content.ServiceConnection
        public final void onNullBinding(ComponentName componentName) {
            handleConnectionError(AiCoreException.newConnectionError(605, "AiCore service returns null on binding."));
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IAICoreService iAICoreService = null;
            IAiCoreServiceProvider iAiCoreServiceProvider = null;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(componentName, AiCoreClientImpl.AICORE_COMPONENT_NAME)) {
                if (iBinder != null) {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.IAiCoreServiceProvider");
                    if (queryLocalInterface instanceof IAiCoreServiceProvider) {
                        iAiCoreServiceProvider = (IAiCoreServiceProvider) queryLocalInterface;
                    } else {
                        iAiCoreServiceProvider = new IAiCoreServiceProvider$Stub$Proxy(iBinder);
                    }
                }
                try {
                    iAiCoreServiceProvider.get(new IAiCoreServiceProviderCallback.Stub(this));
                    return;
                } catch (RemoteException | RuntimeException e) {
                    handleConnectionError(new AiCoreException(4, 6, "AiCore service is not connected.", e));
                    return;
                }
            }
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(componentName, AiCoreClientImpl.AICORE_FALLBACK_COMPONENT_NAME)) {
                if (iBinder != null) {
                    IInterface queryLocalInterface2 = iBinder.queryLocalInterface("com.google.android.apps.aicore.aidl.IAICoreService");
                    if (queryLocalInterface2 instanceof IAICoreService) {
                        iAICoreService = (IAICoreService) queryLocalInterface2;
                    } else {
                        iAICoreService = new IAICoreService$Stub$Proxy(iBinder);
                    }
                }
                handleAiCoreService(iAICoreService);
                return;
            }
            handleConnectionError(AiCoreException.newConnectionError(0, "AiCore service is not connected. Unknown component ".concat(String.valueOf(String.valueOf(componentName)))));
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (this.lock) {
                this.disconnectSignal.signal();
                this.serviceContextFuture.setException(AiCoreException.newConnectionError(602, "AiCore service disconnected."));
                resetServiceContextFuture();
            }
        }

        final void resetServiceContextFuture() {
            synchronized (this.lock) {
                this.serviceContextFuture = new SettableFuture();
                this.disconnectSignal = new FailureSignal();
            }
        }
    }

    static {
        ComponentName componentName = new ComponentName("com.google.android.aicore", "com.google.android.apps.aicore.service.multiuser.AiCoreMultiUserService");
        AICORE_COMPONENT_NAME = componentName;
        AICORE_FALLBACK_COMPONENT_NAME = new ComponentName(componentName.getPackageName(), "com.google.android.apps.aicore.service.AiCoreService");
    }

    public AiCoreClientImpl(AiCoreClientOptions aiCoreClientOptions) {
        this.context = aiCoreClientOptions.context;
        this.workerExecutor = ContextDataProvider.listeningDecorator(aiCoreClientOptions.workerExecutor);
        this.callbackExecutor = aiCoreClientOptions.callbackExecutor;
    }

    public final void cleanUpConnection() {
        synchronized (this.lock) {
            AiCoreServiceConnection aiCoreServiceConnection = this.serviceConnection;
            if (aiCoreServiceConnection != null) {
                this.context.unbindService(aiCoreServiceConnection);
                this.serviceConnection = null;
            }
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        cleanUpConnection();
    }

    @Override // com.google.android.apps.aicore.client.api.AiCoreClient
    public final ListenableFuture getFeatureStatus(AiFeature aiFeature) {
        return AbstractTransformFuture.create(getService(), new AiCoreClientImpl$$ExternalSyntheticLambda2(aiFeature, 0), this.workerExecutor);
    }

    public final ListenableFuture getService() {
        return AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(getServiceContext()), new AccessibilityEventUtils$$ExternalSyntheticLambda0(5), DirectExecutor.INSTANCE);
    }

    @Override // com.google.android.apps.aicore.client.api.AiCoreClient
    public final ListenableFuture getServiceContext() {
        AiCoreServiceConnection aiCoreServiceConnection;
        SettableFuture settableFuture;
        synchronized (this.lock) {
            aiCoreServiceConnection = this.serviceConnection;
            if (aiCoreServiceConnection == null) {
                aiCoreServiceConnection = new AiCoreServiceConnection();
                this.serviceConnection = aiCoreServiceConnection;
                Intent intent = new Intent();
                intent.setComponent(AICORE_COMPONENT_NAME);
                if (!aiCoreServiceConnection.bindService(intent)) {
                    AiCoreClientImpl.this.context.unbindService(aiCoreServiceConnection);
                    Intent intent2 = new Intent();
                    intent2.setComponent(AICORE_FALLBACK_COMPONENT_NAME);
                    if (!aiCoreServiceConnection.bindService(intent2)) {
                        aiCoreServiceConnection.handleConnectionError(AiCoreException.newConnectionError(FeatureName.AM_GENERATIVE_STICKERS$ar$edu, "AiCore service failed to bind."));
                    }
                }
            }
        }
        synchronized (aiCoreServiceConnection.lock) {
            if (aiCoreServiceConnection.serviceContextFuture.isCancelled()) {
                aiCoreServiceConnection.resetServiceContextFuture();
            }
            settableFuture = aiCoreServiceConnection.serviceContextFuture;
        }
        return settableFuture;
    }

    @Override // com.google.android.apps.aicore.client.api.AiCoreClient
    public final ListenableFuture requestDownloadableFeature(AiFeature aiFeature, DownloadCallback downloadCallback) {
        return AbstractTransformFuture.create(getServiceContext(), new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(this, aiFeature, downloadCallback, 1), this.workerExecutor);
    }
}
