package com.google.android.play.core.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ListenerRegistry {
    private final IntentFilter broadcastIntentFilter;
    private final Context context;
    public final SplitInstallModule logger$ar$class_merging$ceb098d3_0$ar$class_merging;
    protected final Set listeners = new HashSet();
    private ListenerRegistryBroadcastReceiver broadcastReceiver = null;
    private volatile boolean receiverAlwaysOn = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListenerRegistryBroadcastReceiver extends BroadcastReceiver {
        public ListenerRegistryBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            ListenerRegistry.this.onReceive(context, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ListenerRegistry(SplitInstallModule splitInstallModule, IntentFilter intentFilter, Context context) {
        this.logger$ar$class_merging$ceb098d3_0$ar$class_merging = splitInstallModule;
        this.broadcastIntentFilter = intentFilter;
        this.context = NativeLibraryPathListMutex.getApplicationContext(context);
    }

    private final void updateReceiverRegistration() {
        ListenerRegistryBroadcastReceiver listenerRegistryBroadcastReceiver;
        if ((this.receiverAlwaysOn || !this.listeners.isEmpty()) && this.broadcastReceiver == null) {
            this.broadcastReceiver = new ListenerRegistryBroadcastReceiver();
            if (Build.VERSION.SDK_INT >= 33) {
                this.context.registerReceiver(this.broadcastReceiver, this.broadcastIntentFilter, 2);
            } else {
                this.context.registerReceiver(this.broadcastReceiver, this.broadcastIntentFilter);
            }
        }
        if (!this.receiverAlwaysOn && this.listeners.isEmpty() && (listenerRegistryBroadcastReceiver = this.broadcastReceiver) != null) {
            this.context.unregisterReceiver(listenerRegistryBroadcastReceiver);
            this.broadcastReceiver = null;
        }
    }

    protected abstract void onReceive(Context context, Intent intent);

    public final synchronized void registerListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listeners.add(hapticPatternParser$$ExternalSyntheticLambda1);
        updateReceiverRegistration();
    }

    public final synchronized void setReceiverAlwaysOn$ar$ds() {
        this.receiverAlwaysOn = true;
        updateReceiverRegistration();
    }

    public final synchronized void unregisterListener$ar$class_merging$29583f7f_0$ar$class_merging$ar$class_merging$ar$class_merging(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.listeners.remove(hapticPatternParser$$ExternalSyntheticLambda1);
        updateReceiverRegistration();
    }

    public final synchronized void updateListeners(Object obj) {
        Iterator it = new HashSet(this.listeners).iterator();
        while (it.hasNext()) {
            ((HapticPatternParser$$ExternalSyntheticLambda1) it.next()).onStateUpdate(obj);
        }
    }
}
