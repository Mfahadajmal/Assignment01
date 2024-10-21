package androidx.work.impl.constraints.trackers;

import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.util.Log;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.NetworkApi21;
import androidx.work.impl.utils.NetworkApi23;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkStateTrackerKt {
    public static final String TAG = Logger.tagWithPrefix("NetworkStateTracker");

    public static final NetworkState getActiveNetworkState(ConnectivityManager connectivityManager) {
        boolean z;
        boolean z2;
        NetworkCapabilities networkCapabilitiesCompat;
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z3 = true;
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            z = true;
        } else {
            z = false;
        }
        try {
            networkCapabilitiesCompat = NetworkApi21.getNetworkCapabilitiesCompat(connectivityManager, NetworkApi23.getActiveNetworkCompat(connectivityManager));
        } catch (SecurityException e) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, "Unable to validate active network", e);
        }
        if (networkCapabilitiesCompat != null) {
            z2 = NetworkApi21.hasCapabilityCompat(networkCapabilitiesCompat, 16);
            boolean isActiveNetworkMetered = connectivityManager.isActiveNetworkMetered();
            if (activeNetworkInfo != null || activeNetworkInfo.isRoaming()) {
                z3 = false;
            }
            return new NetworkState(z, z2, isActiveNetworkMetered, z3);
        }
        z2 = false;
        boolean isActiveNetworkMetered2 = connectivityManager.isActiveNetworkMetered();
        if (activeNetworkInfo != null) {
        }
        z3 = false;
        return new NetworkState(z, z2, isActiveNetworkMetered2, z3);
    }
}
