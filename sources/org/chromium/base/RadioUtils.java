package org.chromium.base;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Process;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.google.mlkit.logging.schema.TranslatorOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RadioUtils {
    private static Boolean sHaveAccessNetworkState;
    private static Boolean sHaveAccessWifiState;

    private RadioUtils() {
    }

    private static int getCellDataActivity() {
        int i;
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::getCellDataActivity");
        try {
            try {
                i = ((TelephonyManager) ContextUtils.sApplicationContext.getSystemService("phone")).getDataActivity();
            } catch (SecurityException unused) {
                i = -1;
            }
            if (scoped != null) {
                scoped.close();
            }
            return i;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static int getCellSignalLevel() {
        SignalStrength signalStrength;
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::getCellSignalLevel");
        try {
            int i = -1;
            try {
                signalStrength = ((TelephonyManager) ContextUtils.sApplicationContext.getSystemService("phone")).getSignalStrength();
                if (signalStrength != null) {
                    i = signalStrength.getLevel();
                }
            } catch (SecurityException unused) {
            }
            if (scoped != null) {
                scoped.close();
            }
            return i;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private static boolean isSupported() {
        boolean z;
        boolean z2;
        if (Build.VERSION.SDK_INT >= 28) {
            if (sHaveAccessNetworkState == null) {
                if (TranslatorOptions.checkPermission(ContextUtils.sApplicationContext, "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                sHaveAccessNetworkState = Boolean.valueOf(z2);
            }
            if (sHaveAccessNetworkState.booleanValue()) {
                if (sHaveAccessWifiState == null) {
                    if (TranslatorOptions.checkPermission(ContextUtils.sApplicationContext, "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    sHaveAccessWifiState = Boolean.valueOf(z);
                }
                if (sHaveAccessWifiState.booleanValue()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isWifiConnected() {
        NetworkCapabilities networkCapabilities;
        TraceEvent scoped = TraceEvent.scoped("RadioUtils::isWifiConnected");
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity");
            Network activeNetwork = connectivityManager.getActiveNetwork();
            boolean z = false;
            if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
                z = networkCapabilities.hasTransport(1);
            }
            if (scoped != null) {
                scoped.close();
            }
            return z;
        } catch (Throwable th) {
            if (scoped != null) {
                try {
                    scoped.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
