package com.google.android.gms.common.internal;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.accessibility.braille.brailledisplay.platform.Displayer;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsClientSupervisor {
    public static final int defaultBindFlags = 4225;
    public static final int gamHandlerThreadPriority = 9;
    public static HandlerThread handlerThread;
    private static GmsClientSupervisor singletonInstance$ar$class_merging;
    public static final Object singletonLock = new Object();
    public final Context applicationContext;
    public final long bindTimeoutMillis;
    public final HashMap connectionStatus;
    public final ConnectionTracker connectionTracker;
    private volatile Executor defaultBinderExecutor;
    public volatile Handler handler;
    private final Displayer.MainHandlerCallback handlerCallback$ar$class_merging;
    private final long unbindDelayMillis;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConnectionStatusConfig {
        public static final Uri CONTENT_PROVIDER_AUTHORITY = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
        public final String action;
        public final int bindFlags;
        public final ComponentName componentName;
        public final String packageName;
        public final boolean useDynamicLookup;

        public ConnectionStatusConfig(ComponentName componentName, int i) {
            this.action = null;
            this.packageName = null;
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(componentName);
            this.componentName = componentName;
            this.bindFlags = GmsClientSupervisor.defaultBindFlags;
            this.useDynamicLookup = false;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ConnectionStatusConfig)) {
                return false;
            }
            ConnectionStatusConfig connectionStatusConfig = (ConnectionStatusConfig) obj;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.action, connectionStatusConfig.action) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.packageName, connectionStatusConfig.packageName) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.componentName, connectionStatusConfig.componentName)) {
                int i = connectionStatusConfig.bindFlags;
                if (this.useDynamicLookup == connectionStatusConfig.useDynamicLookup) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{this.action, this.packageName, this.componentName, Integer.valueOf(GmsClientSupervisor.defaultBindFlags), Boolean.valueOf(this.useDynamicLookup)});
        }

        public final String toString() {
            String str = this.action;
            if (str == null) {
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(this.componentName);
                return this.componentName.flattenToString();
            }
            return str;
        }

        public ConnectionStatusConfig(String str, String str2, int i, boolean z) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds(str);
            this.action = str;
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds("com.google.android.gms");
            this.packageName = "com.google.android.gms";
            this.componentName = null;
            this.bindFlags = GmsClientSupervisor.defaultBindFlags;
            this.useDynamicLookup = z;
        }
    }

    public GmsClientSupervisor() {
    }

    public static GmsClientSupervisor getInstance(Context context) {
        synchronized (singletonLock) {
            if (singletonInstance$ar$class_merging == null) {
                singletonInstance$ar$class_merging = new GmsClientSupervisor(context.getApplicationContext(), context.getMainLooper());
            }
        }
        return singletonInstance$ar$class_merging;
    }

    public final boolean bindService$ar$ds(ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection, String str) {
        boolean z;
        synchronized (this.connectionStatus) {
            GmsClientSupervisorImpl$GmsClientConnectionStatus gmsClientSupervisorImpl$GmsClientConnectionStatus = (GmsClientSupervisorImpl$GmsClientConnectionStatus) this.connectionStatus.get(connectionStatusConfig);
            if (gmsClientSupervisorImpl$GmsClientConnectionStatus == null) {
                gmsClientSupervisorImpl$GmsClientConnectionStatus = new GmsClientSupervisorImpl$GmsClientConnectionStatus(this, connectionStatusConfig);
                gmsClientSupervisorImpl$GmsClientConnectionStatus.addServiceConnection$ar$ds(serviceConnection, serviceConnection);
                gmsClientSupervisorImpl$GmsClientConnectionStatus.bindService$ar$ds$2c1b82ae_0();
                this.connectionStatus.put(connectionStatusConfig, gmsClientSupervisorImpl$GmsClientConnectionStatus);
            } else {
                this.handler.removeMessages(0, connectionStatusConfig);
                if (!gmsClientSupervisorImpl$GmsClientConnectionStatus.containsGmsServiceConnection(serviceConnection)) {
                    gmsClientSupervisorImpl$GmsClientConnectionStatus.addServiceConnection$ar$ds(serviceConnection, serviceConnection);
                    int i = gmsClientSupervisorImpl$GmsClientConnectionStatus.state;
                    if (i != 1) {
                        if (i == 2) {
                            gmsClientSupervisorImpl$GmsClientConnectionStatus.bindService$ar$ds$2c1b82ae_0();
                        }
                    } else {
                        serviceConnection.onServiceConnected(gmsClientSupervisorImpl$GmsClientConnectionStatus.componentName, gmsClientSupervisorImpl$GmsClientConnectionStatus.binder);
                    }
                } else {
                    throw new IllegalStateException("Trying to bind a GmsServiceConnection that was already connected before.  config=" + connectionStatusConfig.toString());
                }
            }
            z = gmsClientSupervisorImpl$GmsClientConnectionStatus.isBound;
        }
        return z;
    }

    public final void unbindService(String str, String str2, int i, ServiceConnection serviceConnection, String str3, boolean z) {
        unbindService$ar$ds$cf8f3871_0(new ConnectionStatusConfig(str, "com.google.android.gms", defaultBindFlags, z), serviceConnection);
    }

    public final void unbindService$ar$ds(ComponentName componentName, ServiceConnection serviceConnection) {
        unbindService$ar$ds$cf8f3871_0(new ConnectionStatusConfig(componentName, defaultBindFlags), serviceConnection);
    }

    protected final void unbindService$ar$ds$cf8f3871_0(ConnectionStatusConfig connectionStatusConfig, ServiceConnection serviceConnection) {
        synchronized (this.connectionStatus) {
            GmsClientSupervisorImpl$GmsClientConnectionStatus gmsClientSupervisorImpl$GmsClientConnectionStatus = (GmsClientSupervisorImpl$GmsClientConnectionStatus) this.connectionStatus.get(connectionStatusConfig);
            if (gmsClientSupervisorImpl$GmsClientConnectionStatus != null) {
                if (gmsClientSupervisorImpl$GmsClientConnectionStatus.containsGmsServiceConnection(serviceConnection)) {
                    gmsClientSupervisorImpl$GmsClientConnectionStatus.clientConnections.remove(serviceConnection);
                    if (gmsClientSupervisorImpl$GmsClientConnectionStatus.hasNoGmsServiceConnections()) {
                        this.handler.sendMessageDelayed(this.handler.obtainMessage(0, connectionStatusConfig), this.unbindDelayMillis);
                    }
                } else {
                    throw new IllegalStateException("Trying to unbind a GmsServiceConnection  that was not bound before.  config=" + connectionStatusConfig.toString());
                }
            } else {
                throw new IllegalStateException("Nonexistent connection status for service config: " + connectionStatusConfig.toString());
            }
        }
    }

    public GmsClientSupervisor(Context context, Looper looper) {
        this();
        this.connectionStatus = new HashMap();
        Displayer.MainHandlerCallback mainHandlerCallback = new Displayer.MainHandlerCallback(this, 2);
        this.handlerCallback$ar$class_merging = mainHandlerCallback;
        this.applicationContext = context.getApplicationContext();
        this.handler = new TracingHandler(looper, mainHandlerCallback);
        if (ConnectionTracker.instance == null) {
            synchronized (ConnectionTracker.singletonLock) {
                if (ConnectionTracker.instance == null) {
                    ConnectionTracker.instance = new ConnectionTracker();
                }
            }
        }
        ConnectionTracker connectionTracker = ConnectionTracker.instance;
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(connectionTracker);
        this.connectionTracker = connectionTracker;
        this.unbindDelayMillis = 5000L;
        this.bindTimeoutMillis = 300000L;
        this.defaultBinderExecutor = null;
    }
}
