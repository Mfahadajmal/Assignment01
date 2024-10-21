package com.google.android.play.core.serviceconnection;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.gsa.ssb.client.SsbServiceClient;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.play.core.serviceconnection.ServiceConnectionManager;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.splitinstall.protocol.ISplitInstallService;
import com.google.android.play.core.splitinstall.protocol.ISplitInstallService$Stub$Proxy;
import com.google.common.flogger.GoogleLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ServiceConnectionManager {
    private static final Map handlers = new HashMap();
    private final NativeLibraryPathListMutex binderToIInterface$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public ServiceConnection connection;
    public final Context context;
    public boolean isBinding;
    public final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging;
    public IInterface service;
    public final Intent serviceIntent;
    public final List onConnectedRunnables = new ArrayList();
    public final Set attachedRemoteTasks = new HashSet();
    public final Object attachedRemoteTasksLock = new Object();
    public final IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager$$ExternalSyntheticLambda0
        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            ServiceConnectionManager serviceConnectionManager = ServiceConnectionManager.this;
            ServiceConnectionManager.BinderDeathListener binderDeathListener = (ServiceConnectionManager.BinderDeathListener) serviceConnectionManager.binderDeathListenerWeakReference.get();
            if (binderDeathListener != null) {
                binderDeathListener.onBinderDied();
            } else {
                Iterator it = serviceConnectionManager.onConnectedRunnables.iterator();
                while (it.hasNext()) {
                    ((SafeRunnable) it.next()).setException(serviceConnectionManager.createBinderDeadException());
                }
                serviceConnectionManager.onConnectedRunnables.clear();
            }
            synchronized (serviceConnectionManager.attachedRemoteTasksLock) {
                serviceConnectionManager.clearAttachedRemoteTasks();
            }
        }
    };
    public final AtomicInteger connectionCount = new AtomicInteger(0);
    public final String serviceName = "SplitInstallService";
    public final WeakReference binderDeathListenerWeakReference = new WeakReference(null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface BinderDeathListener {
        void onBinderDied();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ServiceConnectionImpl implements ServiceConnection {
        final /* synthetic */ Object ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
        private final /* synthetic */ int switching_field;

        public ServiceConnectionImpl(Object obj, int i) {
            this.switching_field = i;
            this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0 = obj;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
            if (this.switching_field != 0) {
                SsbServiceClient.m190$$Nest$fputisPendingConnection$ar$ds((SsbServiceClient) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0);
                SsbServiceClient ssbServiceClient = (SsbServiceClient) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
                if (ssbServiceClient.callback == null) {
                    return;
                }
                ssbServiceClient.service = new Messenger(iBinder);
                try {
                    Message obtain = Message.obtain((Handler) null, 2);
                    obtain.replyTo = ((SsbServiceClient) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).messenger;
                    ((SsbServiceClient) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).sendMessage(obtain);
                    return;
                } catch (RemoteException e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) SsbServiceClient.logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/gsa/ssb/client/SsbServiceClient$SsbServiceConnection", "onServiceConnected", TrainingProto$TrainingPageId.PAGE_ID_DETAILED_IMAGE_DESCRIPTIONS_15_0_1$ar$edu, "SsbServiceClient.java")).log("SsbServiceConnection - remote call failed");
                    return;
                }
            }
            ((ServiceConnectionManager) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).safePost(new SafeRunnable(this) { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager.ServiceConnectionImpl.1
                final /* synthetic */ ServiceConnectionImpl this$1;

                {
                    this.this$1 = this;
                }

                @Override // com.google.android.play.core.serviceconnection.SafeRunnable
                public final void unsafeRun() {
                    ISplitInstallService iSplitInstallService$Stub$Proxy;
                    IBinder iBinder2 = iBinder;
                    if (iBinder2 == null) {
                        iSplitInstallService$Stub$Proxy = null;
                    } else {
                        IInterface queryLocalInterface = iBinder2.queryLocalInterface("com.google.android.play.core.splitinstall.protocol.ISplitInstallService");
                        if (queryLocalInterface instanceof ISplitInstallService) {
                            iSplitInstallService$Stub$Proxy = (ISplitInstallService) queryLocalInterface;
                        } else {
                            iSplitInstallService$Stub$Proxy = new ISplitInstallService$Stub$Proxy(iBinder2);
                        }
                    }
                    ((ServiceConnectionManager) this.this$1.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).service = iSplitInstallService$Stub$Proxy;
                    Object obj = this.this$1.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
                    try {
                        ((ServiceConnectionManager) obj).service.asBinder().linkToDeath(((ServiceConnectionManager) obj).deathRecipient, 0);
                    } catch (RemoteException e2) {
                        ((ServiceConnectionManager) obj).logger$ar$class_merging$ceb098d3_0$ar$class_merging.e$ar$ds$fb17e3b8_0(e2, "linkToDeath failed", new Object[0]);
                    }
                    ((ServiceConnectionManager) this.this$1.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).isBinding = false;
                    Iterator it = ((ServiceConnectionManager) this.this$1.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).onConnectedRunnables.iterator();
                    while (it.hasNext()) {
                        ((Runnable) it.next()).run();
                    }
                    ((ServiceConnectionManager) this.this$1.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).onConnectedRunnables.clear();
                }
            });
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            if (this.switching_field != 0) {
                SsbServiceClient ssbServiceClient = (SsbServiceClient) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
                ssbServiceClient.service = null;
                SsbServiceClient.m190$$Nest$fputisPendingConnection$ar$ds(ssbServiceClient);
            } else {
                ((ServiceConnectionManager) this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0).safePost(new SafeRunnable() { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager.ServiceConnectionImpl.2
                    @Override // com.google.android.play.core.serviceconnection.SafeRunnable
                    public final void unsafeRun() {
                        ServiceConnectionManager serviceConnectionManager = (ServiceConnectionManager) ServiceConnectionImpl.this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
                        serviceConnectionManager.service.asBinder().unlinkToDeath(serviceConnectionManager.deathRecipient, 0);
                        ServiceConnectionManager serviceConnectionManager2 = (ServiceConnectionManager) ServiceConnectionImpl.this.ServiceConnectionManager$ServiceConnectionImpl$ar$this$0;
                        serviceConnectionManager2.service = null;
                        serviceConnectionManager2.isBinding = false;
                    }
                });
            }
        }
    }

    public ServiceConnectionManager(Context context, SplitInstallModule splitInstallModule, Intent intent, NativeLibraryPathListMutex nativeLibraryPathListMutex) {
        this.context = context;
        this.logger$ar$class_merging$ceb098d3_0$ar$class_merging = splitInstallModule;
        this.serviceIntent = intent;
        this.binderToIInterface$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = nativeLibraryPathListMutex;
    }

    public final void bind$ar$class_merging$ar$class_merging(final SafeRunnable safeRunnable, final AppLifecycleMonitor appLifecycleMonitor) {
        safePost(new SafeRunnable(this, safeRunnable.source$ar$class_merging$6cd5309_0$ar$class_merging) { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager.1
            final /* synthetic */ ServiceConnectionManager this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.play.core.serviceconnection.SafeRunnable
            public final void unsafeRun() {
                synchronized (this.this$0.attachedRemoteTasksLock) {
                    final ServiceConnectionManager serviceConnectionManager = this.this$0;
                    final AppLifecycleMonitor appLifecycleMonitor2 = appLifecycleMonitor;
                    serviceConnectionManager.attachedRemoteTasks.add(appLifecycleMonitor2);
                    ((Task) appLifecycleMonitor2.AppLifecycleMonitor$ar$tracker).addOnCompleteListener$ar$ds(new OnCompleteListener() { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager$$ExternalSyntheticLambda1
                        @Override // com.google.android.gms.tasks.OnCompleteListener
                        public final void onComplete(Task task) {
                            ServiceConnectionManager serviceConnectionManager2 = ServiceConnectionManager.this;
                            Object obj = serviceConnectionManager2.attachedRemoteTasksLock;
                            AppLifecycleMonitor appLifecycleMonitor3 = appLifecycleMonitor2;
                            synchronized (obj) {
                                serviceConnectionManager2.attachedRemoteTasks.remove(appLifecycleMonitor3);
                            }
                        }
                    });
                    this.this$0.connectionCount.getAndIncrement();
                    ServiceConnectionManager serviceConnectionManager2 = this.this$0;
                    SafeRunnable safeRunnable2 = safeRunnable;
                    if (serviceConnectionManager2.service == null && !serviceConnectionManager2.isBinding) {
                        serviceConnectionManager2.onConnectedRunnables.add(safeRunnable2);
                        serviceConnectionManager2.connection = new ServiceConnectionImpl(serviceConnectionManager2, 0);
                        serviceConnectionManager2.isBinding = true;
                        if (!serviceConnectionManager2.context.bindService(serviceConnectionManager2.serviceIntent, serviceConnectionManager2.connection, 1)) {
                            serviceConnectionManager2.isBinding = false;
                            Iterator it = serviceConnectionManager2.onConnectedRunnables.iterator();
                            while (it.hasNext()) {
                                ((SafeRunnable) it.next()).setException(new ServiceUnavailableException());
                            }
                            serviceConnectionManager2.onConnectedRunnables.clear();
                        }
                    } else if (serviceConnectionManager2.isBinding) {
                        serviceConnectionManager2.onConnectedRunnables.add(safeRunnable2);
                    } else {
                        safeRunnable2.run();
                    }
                }
            }
        });
    }

    public final void clearAttachedRemoteTasks() {
        Iterator it = this.attachedRemoteTasks.iterator();
        while (it.hasNext()) {
            ((AppLifecycleMonitor) it.next()).trySetException(createBinderDeadException());
        }
        this.attachedRemoteTasks.clear();
    }

    public final RemoteException createBinderDeadException() {
        return new RemoteException(String.valueOf(this.serviceName).concat(" : Binder has died."));
    }

    public final void safePost(SafeRunnable safeRunnable) {
        Handler handler;
        Map map = handlers;
        synchronized (map) {
            if (!map.containsKey(this.serviceName)) {
                HandlerThread handlerThread = new HandlerThread(this.serviceName, 10);
                handlerThread.start();
                map.put(this.serviceName, new Handler(handlerThread.getLooper()));
            }
            handler = (Handler) map.get(this.serviceName);
        }
        handler.post(safeRunnable);
    }

    public final void unbindAndReleaseTask$ar$class_merging$ar$class_merging(AppLifecycleMonitor appLifecycleMonitor) {
        synchronized (this.attachedRemoteTasksLock) {
            this.attachedRemoteTasks.remove(appLifecycleMonitor);
        }
        safePost(new SafeRunnable() { // from class: com.google.android.play.core.serviceconnection.ServiceConnectionManager.2
            @Override // com.google.android.play.core.serviceconnection.SafeRunnable
            public final void unsafeRun() {
                synchronized (ServiceConnectionManager.this.attachedRemoteTasksLock) {
                    if (ServiceConnectionManager.this.connectionCount.get() > 0 && ServiceConnectionManager.this.connectionCount.decrementAndGet() > 0) {
                        return;
                    }
                    ServiceConnectionManager serviceConnectionManager = ServiceConnectionManager.this;
                    if (serviceConnectionManager.service != null) {
                        serviceConnectionManager.context.unbindService(serviceConnectionManager.connection);
                        ServiceConnectionManager.this.isBinding = false;
                        ServiceConnectionManager serviceConnectionManager2 = ServiceConnectionManager.this;
                        serviceConnectionManager2.service = null;
                        serviceConnectionManager2.connection = null;
                    }
                    ServiceConnectionManager.this.clearAttachedRemoteTasks();
                }
            }
        });
    }
}
