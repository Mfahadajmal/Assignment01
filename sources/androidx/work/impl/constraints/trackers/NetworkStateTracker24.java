package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import androidx.work.Logger;
import androidx.work.impl.utils.NetworkApi21;
import androidx.work.impl.utils.NetworkApi24;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkStateTracker24 extends ConstraintTracker {
    public final ConnectivityManager connectivityManager;
    private final NetworkStateTracker24$networkCallback$1 networkCallback;

    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.work.impl.constraints.trackers.NetworkStateTracker24$networkCallback$1] */
    public NetworkStateTracker24(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        super(context, workManagerTaskExecutor);
        Object systemService = this.appContext.getSystemService("connectivity");
        systemService.getClass();
        this.connectivityManager = (ConnectivityManager) systemService;
        this.networkCallback = new ConnectivityManager.NetworkCallback() { // from class: androidx.work.impl.constraints.trackers.NetworkStateTracker24$networkCallback$1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                network.getClass();
                networkCapabilities.getClass();
                Logger.get$ar$ds$16341a92_0();
                String str = NetworkStateTrackerKt.TAG;
                Objects.toString(networkCapabilities);
                networkCapabilities.toString();
                NetworkStateTracker24 networkStateTracker24 = NetworkStateTracker24.this;
                networkStateTracker24.setState(NetworkStateTrackerKt.getActiveNetworkState(networkStateTracker24.connectivityManager));
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public final void onLost(Network network) {
                network.getClass();
                Logger.get$ar$ds$16341a92_0();
                String str = NetworkStateTrackerKt.TAG;
                NetworkStateTracker24 networkStateTracker24 = NetworkStateTracker24.this;
                networkStateTracker24.setState(NetworkStateTrackerKt.getActiveNetworkState(networkStateTracker24.connectivityManager));
            }
        };
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final /* bridge */ /* synthetic */ Object readSystemState() {
        return NetworkStateTrackerKt.getActiveNetworkState(this.connectivityManager);
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final void startTracking() {
        try {
            Logger.get$ar$ds$16341a92_0();
            String str = NetworkStateTrackerKt.TAG;
            NetworkApi24.registerDefaultNetworkCallbackCompat(this.connectivityManager, this.networkCallback);
        } catch (IllegalArgumentException e) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(NetworkStateTrackerKt.TAG, "Received exception while registering network callback", e);
        } catch (SecurityException e2) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(NetworkStateTrackerKt.TAG, "Received exception while registering network callback", e2);
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final void stopTracking() {
        try {
            Logger.get$ar$ds$16341a92_0();
            String str = NetworkStateTrackerKt.TAG;
            NetworkApi21.unregisterNetworkCallbackCompat(this.connectivityManager, this.networkCallback);
        } catch (IllegalArgumentException e) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(NetworkStateTrackerKt.TAG, "Received exception while unregistering network callback", e);
        } catch (SecurityException e2) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(NetworkStateTrackerKt.TAG, "Received exception while unregistering network callback", e2);
        }
    }
}
