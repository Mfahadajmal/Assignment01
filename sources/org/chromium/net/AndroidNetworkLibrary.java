package org.chromium.net;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.security.NetworkSecurityPolicy;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.mlkit.logging.schema.TranslatorOptions;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;
import java.net.URLConnection;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;
import org.chromium.base.ContextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class AndroidNetworkLibrary {
    private static Boolean sHaveAccessNetworkState;
    private static Boolean sHaveAccessWifiState;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NetworkSecurityPolicyProxy {
        private static NetworkSecurityPolicyProxy sInstance = new NetworkSecurityPolicyProxy();

        public static NetworkSecurityPolicyProxy getInstance() {
            return sInstance;
        }

        public static void setInstanceForTesting(NetworkSecurityPolicyProxy networkSecurityPolicyProxy) {
            sInstance = networkSecurityPolicyProxy;
        }

        public boolean isCleartextTrafficPermitted() {
            return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted();
        }

        public boolean isCleartextTrafficPermitted(String str) {
            boolean isCleartextTrafficPermitted;
            isCleartextTrafficPermitted = NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
            return isCleartextTrafficPermitted;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SocketFd extends Socket {

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class SocketImplFd extends SocketImpl {
            public SocketImplFd(FileDescriptor fileDescriptor) {
                this.fd = fileDescriptor;
            }

            @Override // java.net.SocketImpl
            protected final void accept(SocketImpl socketImpl) {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected final int available() {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void bind(InetAddress inetAddress, int i) {
                throw new RuntimeException("accept not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void connect(String str, int i) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected final InputStream getInputStream() {
                throw new RuntimeException("getInputStream not implemented");
            }

            @Override // java.net.SocketOptions
            public final Object getOption(int i) {
                throw new RuntimeException("getOption not implemented");
            }

            @Override // java.net.SocketImpl
            protected final OutputStream getOutputStream() {
                throw new RuntimeException("getOutputStream not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void listen(int i) {
                throw new RuntimeException("listen not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void sendUrgentData(int i) {
                throw new RuntimeException("sendUrgentData not implemented");
            }

            @Override // java.net.SocketOptions
            public final void setOption(int i, Object obj) {
                throw new RuntimeException("setOption not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void connect(InetAddress inetAddress, int i) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void connect(SocketAddress socketAddress, int i) {
                throw new RuntimeException("connect not implemented");
            }

            @Override // java.net.SocketImpl
            protected final void close() {
            }

            @Override // java.net.SocketImpl
            protected final void create(boolean z) {
            }
        }

        public SocketFd(FileDescriptor fileDescriptor) {
            super(new SocketImplFd(fileDescriptor));
        }
    }

    AndroidNetworkLibrary() {
    }

    public static void addTestRootCertificate(byte[] bArr) {
        X509Util.addTestRootCertificate(bArr);
    }

    public static void clearTestRootCertificates() {
        X509Util.clearTestRootCertificates();
    }

    public static DnsStatus getCurrentDnsStatus() {
        return getDnsStatus(null);
    }

    public static DnsStatus getDnsStatus(Network network) {
        ConnectivityManager connectivityManager;
        boolean isPrivateDnsActive;
        String privateDnsServerName;
        boolean z;
        if (sHaveAccessNetworkState == null) {
            if (TranslatorOptions.checkPermission(ContextUtils.sApplicationContext, "android.permission.ACCESS_NETWORK_STATE", Process.myPid(), Process.myUid()) == 0) {
                z = true;
            } else {
                z = false;
            }
            sHaveAccessNetworkState = Boolean.valueOf(z);
        }
        if (sHaveAccessNetworkState.booleanValue() && (connectivityManager = (ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity")) != null) {
            if (network == null) {
                network = connectivityManager.getActiveNetwork();
            }
            if (network != null) {
                try {
                    LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                    if (linkProperties != null) {
                        List<InetAddress> dnsServers = linkProperties.getDnsServers();
                        String domains = linkProperties.getDomains();
                        if (Build.VERSION.SDK_INT >= 28) {
                            isPrivateDnsActive = linkProperties.isPrivateDnsActive();
                            privateDnsServerName = linkProperties.getPrivateDnsServerName();
                            return new DnsStatus(dnsServers, isPrivateDnsActive, privateDnsServerName, domains);
                        }
                        return new DnsStatus(dnsServers, false, "", domains);
                    }
                    return null;
                } catch (RuntimeException unused) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }

    public static DnsStatus getDnsStatusForNetwork(long j) {
        Network fromNetworkHandle;
        try {
            fromNetworkHandle = Network.fromNetworkHandle(j);
            return getDnsStatus(fromNetworkHandle);
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private static boolean getIsCaptivePortal() {
        Network activeNetwork;
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetwork = connectivityManager.getActiveNetwork()) == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null || !networkCapabilities.hasCapability(17)) {
            return false;
        }
        return true;
    }

    private static boolean getIsRoaming() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        return activeNetworkInfo.isRoaming();
    }

    public static String getMimeTypeFromExtension(String str) {
        return URLConnection.guessContentTypeFromName("foo.".concat(String.valueOf(str)));
    }

    private static String getNetworkOperator() {
        TelephonyManager telephonyManager = (TelephonyManager) ContextUtils.sApplicationContext.getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkOperator();
    }

    public static byte[][] getUserAddedRoots() {
        return X509Util.getUserAddedRoots();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
    
        r5 = r5.getTransportInfo();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.net.wifi.WifiInfo getWifiInfo() {
        /*
            boolean r0 = haveAccessWifiState()
            r1 = 0
            if (r0 == 0) goto L4e
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L3f
            android.content.Context r0 = org.chromium.base.ContextUtils.sApplicationContext
            java.lang.String r2 = "connectivity"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0
            android.net.Network[] r2 = r0.getAllNetworks()
            int r3 = r2.length
            r4 = 0
        L1d:
            if (r4 >= r3) goto L66
            r5 = r2[r4]
            android.net.NetworkCapabilities r5 = r0.getNetworkCapabilities(r5)
            if (r5 == 0) goto L3c
            r6 = 1
            boolean r6 = r5.hasTransport(r6)
            if (r6 == 0) goto L3c
            android.net.TransportInfo r5 = org.chromium.base.PathUtils$$ExternalSyntheticApiModelOutline0.m(r5)
            if (r5 == 0) goto L3c
            boolean r6 = r5 instanceof android.net.wifi.WifiInfo
            if (r6 != 0) goto L39
            goto L3c
        L39:
            android.net.wifi.WifiInfo r5 = (android.net.wifi.WifiInfo) r5
            return r5
        L3c:
            int r4 = r4 + 1
            goto L1d
        L3f:
            android.content.Context r0 = org.chromium.base.ContextUtils.sApplicationContext
            java.lang.String r1 = "wifi"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0
            android.net.wifi.WifiInfo r0 = r0.getConnectionInfo()
            return r0
        L4e:
            android.content.Context r0 = org.chromium.base.ContextUtils.sApplicationContext
            android.content.IntentFilter r2 = new android.content.IntentFilter
            java.lang.String r3 = "android.net.wifi.STATE_CHANGE"
            r2.<init>(r3)
            android.content.Intent r0 = org.chromium.base.ContextUtils.registerProtectedBroadcastReceiver(r0, r1, r2)
            if (r0 == 0) goto L66
            java.lang.String r1 = "wifiInfo"
            android.os.Parcelable r0 = r0.getParcelableExtra(r1)
            android.net.wifi.WifiInfo r0 = (android.net.wifi.WifiInfo) r0
            return r0
        L66:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.AndroidNetworkLibrary.getWifiInfo():android.net.wifi.WifiInfo");
    }

    public static String getWifiSSID() {
        String ssid;
        WifiInfo wifiInfo = getWifiInfo();
        if (wifiInfo != null && (ssid = wifiInfo.getSSID()) != null && !ssid.equals("<unknown ssid>")) {
            return ssid;
        }
        return "";
    }

    public static int getWifiSignalLevel(int i) {
        int intExtra;
        int calculateSignalLevel;
        if (ContextUtils.sApplicationContext != null && ContextUtils.sApplicationContext.getContentResolver() != null) {
            if (haveAccessWifiState()) {
                WifiInfo wifiInfo = getWifiInfo();
                if (wifiInfo != null) {
                    intExtra = wifiInfo.getRssi();
                } else {
                    return -1;
                }
            } else {
                try {
                    Intent registerProtectedBroadcastReceiver = ContextUtils.registerProtectedBroadcastReceiver(ContextUtils.sApplicationContext, null, new IntentFilter("android.net.wifi.RSSI_CHANGED"));
                    if (registerProtectedBroadcastReceiver != null) {
                        intExtra = registerProtectedBroadcastReceiver.getIntExtra("newRssi", Integer.MIN_VALUE);
                    } else {
                        return -1;
                    }
                } catch (IllegalArgumentException unused) {
                    return -1;
                }
            }
            if (intExtra != Integer.MIN_VALUE && (calculateSignalLevel = WifiManager.calculateSignalLevel(intExtra, i)) >= 0 && calculateSignalLevel < i) {
                return calculateSignalLevel;
            }
            return -1;
        }
        return -1;
    }

    private static boolean haveAccessWifiState() {
        boolean z;
        if (sHaveAccessWifiState == null) {
            if (TranslatorOptions.checkPermission(ContextUtils.sApplicationContext, "android.permission.ACCESS_WIFI_STATE", Process.myPid(), Process.myUid()) == 0) {
                z = true;
            } else {
                z = false;
            }
            sHaveAccessWifiState = Boolean.valueOf(z);
        }
        return sHaveAccessWifiState.booleanValue();
    }

    public static boolean haveOnlyLoopbackAddresses() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                try {
                    if (nextElement.isUp() && !nextElement.isLoopback()) {
                        return false;
                    }
                } catch (SocketException unused) {
                }
            }
            return true;
        } catch (Exception e) {
            e.toString();
            Log.w("AndroidNetworkLibrary", "could not get network interfaces: ".concat(e.toString()));
            return false;
        }
    }

    private static boolean isCleartextPermitted(String str) {
        try {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted(str);
        } catch (IllegalArgumentException unused) {
            return NetworkSecurityPolicyProxy.getInstance().isCleartextTrafficPermitted();
        }
    }

    private static boolean reportBadDefaultNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) ContextUtils.sApplicationContext.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        connectivityManager.reportNetworkConnectivity(null, false);
        return true;
    }

    private static void tagSocket(int i, int i2, int i3) {
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(i3);
        }
        if (i2 != -1) {
            ThreadStatsUid.set(i2);
        }
        ParcelFileDescriptor adoptFd = ParcelFileDescriptor.adoptFd(i);
        SocketFd socketFd = new SocketFd(adoptFd.getFileDescriptor());
        TrafficStats.tagSocket(socketFd);
        socketFd.close();
        if (adoptFd != null) {
            adoptFd.detachFd();
        }
        if (i3 != threadStatsTag) {
            TrafficStats.setThreadStatsTag(threadStatsTag);
        }
        if (i2 != -1) {
            ThreadStatsUid.clear();
        }
    }

    public static AndroidCertVerifyResult verifyServerCertificates(byte[][] bArr, String str, String str2) {
        try {
            return X509Util.verifyServerCertificates(bArr, str, str2);
        } catch (IllegalArgumentException unused) {
            return new AndroidCertVerifyResult(-1);
        } catch (KeyStoreException unused2) {
            return new AndroidCertVerifyResult(-1);
        } catch (NoSuchAlgorithmException unused3) {
            return new AndroidCertVerifyResult(-1);
        }
    }
}
