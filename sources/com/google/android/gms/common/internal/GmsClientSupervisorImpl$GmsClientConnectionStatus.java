package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GmsClientSupervisorImpl$GmsClientConnectionStatus implements ServiceConnection {
    public IBinder binder;
    public ComponentName componentName;
    public final GmsClientSupervisor.ConnectionStatusConfig config;
    public boolean isBound;
    public final /* synthetic */ GmsClientSupervisor this$0$ar$class_merging$33154e58_0;
    public final Map clientConnections = new HashMap();
    public int state = 2;

    public GmsClientSupervisorImpl$GmsClientConnectionStatus(GmsClientSupervisor gmsClientSupervisor, GmsClientSupervisor.ConnectionStatusConfig connectionStatusConfig) {
        this.this$0$ar$class_merging$33154e58_0 = gmsClientSupervisor;
        this.config = connectionStatusConfig;
    }

    public final void addServiceConnection$ar$ds(ServiceConnection serviceConnection, ServiceConnection serviceConnection2) {
        this.clientConnections.put(serviceConnection, serviceConnection2);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c0 A[Catch: all -> 0x00e7, TryCatch #2 {all -> 0x00e7, blocks: (B:5:0x001f, B:7:0x0029, B:9:0x002e, B:11:0x003a, B:15:0x0064, B:16:0x0059, B:19:0x0048, B:21:0x0075, B:22:0x008e, B:25:0x00b6, B:26:0x00bc, B:28:0x00c0, B:32:0x00d7, B:34:0x00da, B:37:0x0095, B:39:0x009e, B:42:0x00ae, B:45:0x0083), top: B:4:0x001f, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d7 A[Catch: all -> 0x00e7, TRY_LEAVE, TryCatch #2 {all -> 0x00e7, blocks: (B:5:0x001f, B:7:0x0029, B:9:0x002e, B:11:0x003a, B:15:0x0064, B:16:0x0059, B:19:0x0048, B:21:0x0075, B:22:0x008e, B:25:0x00b6, B:26:0x00bc, B:28:0x00c0, B:32:0x00d7, B:34:0x00da, B:37:0x0095, B:39:0x009e, B:42:0x00ae, B:45:0x0083), top: B:4:0x001f, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void bindService$ar$ds$2c1b82ae_0() {
        /*
            Method dump skipped, instructions count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.GmsClientSupervisorImpl$GmsClientConnectionStatus.bindService$ar$ds$2c1b82ae_0():void");
    }

    public final boolean containsGmsServiceConnection(ServiceConnection serviceConnection) {
        return this.clientConnections.containsKey(serviceConnection);
    }

    public final boolean hasNoGmsServiceConnections() {
        return this.clientConnections.isEmpty();
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        onServiceDisconnected(componentName);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.this$0$ar$class_merging$33154e58_0.connectionStatus) {
            this.this$0$ar$class_merging$33154e58_0.handler.removeMessages(1, this.config);
            this.binder = iBinder;
            this.componentName = componentName;
            Iterator it = this.clientConnections.values().iterator();
            while (it.hasNext()) {
                ((ServiceConnection) it.next()).onServiceConnected(componentName, iBinder);
            }
            this.state = 1;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.this$0$ar$class_merging$33154e58_0.connectionStatus) {
            this.this$0$ar$class_merging$33154e58_0.handler.removeMessages(1, this.config);
            this.binder = null;
            this.componentName = componentName;
            Iterator it = this.clientConnections.values().iterator();
            while (it.hasNext()) {
                ((ServiceConnection) it.next()).onServiceDisconnected(componentName);
            }
            this.state = 2;
        }
    }
}
