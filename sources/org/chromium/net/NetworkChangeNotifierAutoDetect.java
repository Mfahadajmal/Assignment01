package org.chromium.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.Trace;
import android.util.Log;
import com.google.android.libraries.surveys.internal.view.RatingView$$ExternalSyntheticLambda5;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.RetriableStream;
import io.grpc.internal.RetriableStream$Sublistener$1RetryBackoffRunnable$1;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import org.chromium.base.ContextUtils;
import org.chromium.base.StrictModeContext;
import org.chromium.base.TraceEvent;
import org.chromium.base.metrics.ScopedSysTraceEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NetworkChangeNotifierAutoDetect extends BroadcastReceiver {
    private static final String TAG = "NetworkChangeNotifierAutoDetect";
    private static final int UNKNOWN_LINK_SPEED = -1;
    private ConnectivityManagerDelegate mConnectivityManagerDelegate;
    private ConnectivityManager.NetworkCallback mDefaultNetworkCallback;
    private final Handler mHandler;
    private boolean mIgnoreNextBroadcast;
    private final NetworkConnectivityIntentFilter mIntentFilter;
    private final Looper mLooper;
    private MyNetworkCallback mNetworkCallback;
    private NetworkRequest mNetworkRequest;
    private NetworkState mNetworkState;
    private final Observer mObserver;
    private boolean mRegisterNetworkCallbackFailed;
    private boolean mRegistered;
    private final RegistrationPolicy mRegistrationPolicy;
    private boolean mShouldSignalObserver;
    private WifiManagerDelegate mWifiManagerDelegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AndroidRDefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        LinkProperties mLinkProperties;
        NetworkCapabilities mNetworkCapabilities;

        public AndroidRDefaultNetworkCallback() {
        }

        private final NetworkState getNetworkState(Network network) {
            int i;
            int i2;
            boolean isPrivateDnsActive;
            String privateDnsServerName;
            int i3;
            int i4 = -1;
            if (!this.mNetworkCapabilities.hasTransport(1) && !this.mNetworkCapabilities.hasTransport(5)) {
                if (this.mNetworkCapabilities.hasTransport(0)) {
                    NetworkInfo rawNetworkInfo = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getRawNetworkInfo(network);
                    if (rawNetworkInfo != null) {
                        i4 = rawNetworkInfo.getSubtype();
                    }
                    i2 = i4;
                    i = 0;
                } else {
                    if (this.mNetworkCapabilities.hasTransport(3)) {
                        i3 = 9;
                    } else if (this.mNetworkCapabilities.hasTransport(2)) {
                        i3 = 7;
                    } else if (this.mNetworkCapabilities.hasTransport(4)) {
                        NetworkInfo networkInfo = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkInfo(network);
                        if (networkInfo != null) {
                            i3 = networkInfo.getType();
                        } else {
                            i3 = 17;
                        }
                    } else {
                        i = -1;
                        i2 = -1;
                    }
                    i = i3;
                }
                boolean z = !this.mNetworkCapabilities.hasCapability(11);
                String valueOf = String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network));
                isPrivateDnsActive = this.mLinkProperties.isPrivateDnsActive();
                privateDnsServerName = this.mLinkProperties.getPrivateDnsServerName();
                return new NetworkState(true, i, i2, z, valueOf, isPrivateDnsActive, privateDnsServerName);
            }
            i = 1;
            i2 = -1;
            boolean z2 = !this.mNetworkCapabilities.hasCapability(11);
            String valueOf2 = String.valueOf(NetworkChangeNotifierAutoDetect.networkToNetId(network));
            isPrivateDnsActive = this.mLinkProperties.isPrivateDnsActive();
            privateDnsServerName = this.mLinkProperties.getPrivateDnsServerName();
            return new NetworkState(true, i, i2, z2, valueOf2, isPrivateDnsActive, privateDnsServerName);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            this.mLinkProperties = null;
            this.mNetworkCapabilities = null;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            this.mNetworkCapabilities = networkCapabilities;
            if (NetworkChangeNotifierAutoDetect.this.mRegistered && this.mLinkProperties != null && this.mNetworkCapabilities != null) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(getNetworkState(network));
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            this.mLinkProperties = linkProperties;
            if (NetworkChangeNotifierAutoDetect.this.mRegistered && this.mLinkProperties != null && this.mNetworkCapabilities != null) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(getNetworkState(network));
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            this.mLinkProperties = null;
            this.mNetworkCapabilities = null;
            if (NetworkChangeNotifierAutoDetect.this.mRegistered) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChangedTo(new NetworkState(false, -1, -1, false, null, false, ""));
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ConnectivityManagerDelegate {
        public final ConnectivityManager mConnectivityManager;

        ConnectivityManagerDelegate() {
            this.mConnectivityManager = null;
        }

        final int getConnectionType(Network network) {
            NetworkInfo networkInfo = getNetworkInfo(network);
            if (networkInfo != null && networkInfo.isConnected()) {
                return NetworkChangeNotifierAutoDetect.convertToConnectionType(networkInfo.getType(), networkInfo.getSubtype());
            }
            return 6;
        }

        final Network getDefaultNetwork() {
            Network activeNetwork = this.mConnectivityManager.getActiveNetwork();
            if (activeNetwork == null) {
                NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
                if (activeNetworkInfo == null) {
                    return null;
                }
                for (Network network : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(this, null)) {
                    NetworkInfo rawNetworkInfo = getRawNetworkInfo(network);
                    if (rawNetworkInfo != null && (rawNetworkInfo.getType() == activeNetworkInfo.getType() || rawNetworkInfo.getType() == 17)) {
                        if (activeNetwork != null && Build.VERSION.SDK_INT >= 29) {
                            if (rawNetworkInfo.getDetailedState() != NetworkInfo.DetailedState.CONNECTING) {
                                NetworkInfo rawNetworkInfo2 = getRawNetworkInfo(activeNetwork);
                                if (rawNetworkInfo2 != null && rawNetworkInfo2.getDetailedState() == NetworkInfo.DetailedState.CONNECTING) {
                                    activeNetwork = null;
                                }
                            }
                        }
                        if (activeNetwork != null) {
                            Log.e("cr_".concat(String.valueOf(NetworkChangeNotifierAutoDetect.TAG)), "There should not be multiple connected networks of the same type. At least as of Android Marshmallow this is not supported. If this becomes supported this assertion may trigger.");
                        }
                        activeNetwork = network;
                    }
                }
            }
            return activeNetwork;
        }

        protected final NetworkCapabilities getNetworkCapabilities(Network network) {
            for (int i = 0; i < 2; i++) {
                try {
                    return this.mConnectivityManager.getNetworkCapabilities(network);
                } catch (SecurityException unused) {
                }
            }
            return null;
        }

        final NetworkInfo getNetworkInfo(Network network) {
            NetworkInfo rawNetworkInfo = getRawNetworkInfo(network);
            if (rawNetworkInfo != null && rawNetworkInfo.getType() == 17) {
                return this.mConnectivityManager.getActiveNetworkInfo();
            }
            return rawNetworkInfo;
        }

        /* JADX WARN: Removed duplicated region for block: B:5:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0033  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        final org.chromium.net.NetworkChangeNotifierAutoDetect.NetworkState getNetworkState$ar$ds() {
            /*
                r20 = this;
                r0 = r20
                android.net.Network r1 = r20.getDefaultNetwork()
                android.net.NetworkInfo r2 = r0.getNetworkInfo(r1)
                r3 = 0
                if (r2 != 0) goto Lf
            Ld:
                r2 = r3
                goto L22
            Lf:
                boolean r4 = r2.isConnected()
                if (r4 != 0) goto L22
                android.net.NetworkInfo$DetailedState r2 = r2.getDetailedState()
                android.net.NetworkInfo$DetailedState r4 = android.net.NetworkInfo.DetailedState.BLOCKED
                if (r2 == r4) goto L1e
                goto Ld
            L1e:
                org.chromium.base.ApplicationStatus.getStateForApplication()
                goto Ld
            L22:
                if (r2 != 0) goto L33
                org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState r1 = new org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState
                r9 = 0
                r10 = 0
                r5 = 0
                r6 = -1
                r7 = -1
                r8 = 0
                java.lang.String r11 = ""
                r4 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10, r11)
                return r1
            L33:
                r4 = 1
                if (r1 == 0) goto L89
                android.net.NetworkCapabilities r3 = r0.getNetworkCapabilities(r1)
                r5 = 0
                if (r3 == 0) goto L47
                r6 = 11
                boolean r3 = r3.hasCapability(r6)
                if (r3 != 0) goto L47
                r10 = r4
                goto L48
            L47:
                r10 = r5
            L48:
                org.chromium.net.DnsStatus r3 = org.chromium.net.AndroidNetworkLibrary.getDnsStatus(r1)
                if (r3 != 0) goto L69
                org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState r3 = new org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState
                int r8 = r2.getType()
                int r9 = r2.getSubtype()
                long r1 = org.chromium.net.NetworkChangeNotifierAutoDetect.networkToNetId(r1)
                java.lang.String r11 = java.lang.String.valueOf(r1)
                r12 = 0
                java.lang.String r13 = ""
                r7 = 1
                r6 = r3
                r6.<init>(r7, r8, r9, r10, r11, r12, r13)
                return r3
            L69:
                org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState r4 = new org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState
                int r8 = r2.getType()
                int r9 = r2.getSubtype()
                long r1 = org.chromium.net.NetworkChangeNotifierAutoDetect.networkToNetId(r1)
                java.lang.String r11 = java.lang.String.valueOf(r1)
                boolean r12 = r3.getPrivateDnsActive()
                java.lang.String r13 = r3.getPrivateDnsServerName()
                r7 = 1
                r6 = r4
                r6.<init>(r7, r8, r9, r10, r11, r12, r13)
                return r4
            L89:
                int r1 = r2.getType()
                if (r1 != r4) goto Lc0
                java.lang.String r1 = r2.getExtraInfo()
                if (r1 == 0) goto Lb9
                java.lang.String r1 = r2.getExtraInfo()
                java.lang.String r4 = ""
                boolean r1 = r4.equals(r1)
                if (r1 != 0) goto Lb9
                org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState r1 = new org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState
                int r6 = r2.getType()
                int r7 = r2.getSubtype()
                java.lang.String r9 = r2.getExtraInfo()
                r10 = 0
                java.lang.String r11 = ""
                r5 = 1
                r8 = 0
                r4 = r1
                r4.<init>(r5, r6, r7, r8, r9, r10, r11)
                return r1
            Lb9:
                r2.getType()
                r2.getSubtype()
                throw r3
            Lc0:
                org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState r1 = new org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState
                int r14 = r2.getType()
                int r15 = r2.getSubtype()
                r18 = 0
                java.lang.String r19 = ""
                r13 = 1
                r16 = 0
                r17 = 0
                r12 = r1
                r12.<init>(r13, r14, r15, r16, r17, r18, r19)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.chromium.net.NetworkChangeNotifierAutoDetect.ConnectivityManagerDelegate.getNetworkState$ar$ds():org.chromium.net.NetworkChangeNotifierAutoDetect$NetworkState");
        }

        final NetworkInfo getRawNetworkInfo(Network network) {
            try {
                try {
                    return this.mConnectivityManager.getNetworkInfo(network);
                } catch (NullPointerException unused) {
                    return this.mConnectivityManager.getNetworkInfo(network);
                }
            } catch (NullPointerException unused2) {
                return null;
            }
        }

        final void unregisterNetworkCallback(ConnectivityManager.NetworkCallback networkCallback) {
            this.mConnectivityManager.unregisterNetworkCallback(networkCallback);
        }

        protected final boolean vpnAccessible(Network network) {
            boolean z;
            TraceEvent scoped;
            Socket socket = new Socket();
            try {
                scoped = TraceEvent.scoped("StrictModeContext.allowAllVmPolicies");
            } catch (IOException unused) {
                z = false;
            } catch (Throwable th) {
                try {
                    socket.close();
                } catch (IOException unused2) {
                }
                throw th;
            }
            try {
                StrictMode.VmPolicy vmPolicy = StrictMode.getVmPolicy();
                StrictMode.setVmPolicy(StrictMode.VmPolicy.LAX);
                StrictModeContext strictModeContext = new StrictModeContext(null, vmPolicy);
                if (scoped != null) {
                    scoped.close();
                }
                try {
                    network.bindSocket(socket);
                    strictModeContext.close();
                    z = true;
                    try {
                        socket.close();
                    } catch (IOException unused3) {
                    }
                    return z;
                } catch (Throwable th2) {
                    try {
                        strictModeContext.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th5) {
                        th4.addSuppressed(th5);
                    }
                }
                throw th4;
            }
        }

        public ConnectivityManagerDelegate(Context context) {
            this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DefaultNetworkCallback extends ConnectivityManager.NetworkCallback {
        public DefaultNetworkCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            if (NetworkChangeNotifierAutoDetect.this.mRegistered) {
                NetworkChangeNotifierAutoDetect.this.connectionTypeChanged();
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
            onAvailable(null);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            onAvailable(null);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MyNetworkCallback extends ConnectivityManager.NetworkCallback {
        public Network mVpnInPlace;

        /* compiled from: PG */
        /* renamed from: org.chromium.net.NetworkChangeNotifierAutoDetect$MyNetworkCallback$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements Runnable {
            final /* synthetic */ int val$connectionType;
            final /* synthetic */ boolean val$makeVpnDefault;
            final /* synthetic */ long val$netId;

            public AnonymousClass1(long j, int i, boolean z) {
                r2 = j;
                r4 = i;
                r5 = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(r2, r4);
                if (r5) {
                    NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(r4);
                    NetworkChangeNotifierAutoDetect.this.mObserver.purgeActiveNetworkList(new long[]{r2});
                }
            }
        }

        /* compiled from: PG */
        /* renamed from: org.chromium.net.NetworkChangeNotifierAutoDetect$MyNetworkCallback$2 */
        /* loaded from: classes.dex */
        final class AnonymousClass2 implements Runnable {
            final /* synthetic */ int val$connectionType;
            final /* synthetic */ long val$netId;

            public AnonymousClass2(long j, int i) {
                r2 = j;
                r4 = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(r2, r4);
            }
        }

        public MyNetworkCallback() {
        }

        private final boolean ignoreConnectedNetwork(Network network, NetworkCapabilities networkCapabilities) {
            if (!ignoreNetworkDueToVpn(network)) {
                if (networkCapabilities == null) {
                    networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
                }
                if (networkCapabilities != null) {
                    if (!networkCapabilities.hasTransport(4) || NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.vpnAccessible(network)) {
                        return false;
                    }
                    return true;
                }
                return true;
            }
            return true;
        }

        private final boolean ignoreNetworkDueToVpn(Network network) {
            Network network2 = this.mVpnInPlace;
            if (network2 != null && !network2.equals(network)) {
                return true;
            }
            return false;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onAvailable(Network network) {
            boolean z;
            Network network2;
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onAvailable");
            try {
                NetworkCapabilities networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(network);
                if (!ignoreConnectedNetwork(network, networkCapabilities)) {
                    if (networkCapabilities.hasTransport(4) && ((network2 = this.mVpnInPlace) == null || !network.equals(network2))) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        this.mVpnInPlace = network;
                    }
                    NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.1
                        final /* synthetic */ int val$connectionType;
                        final /* synthetic */ boolean val$makeVpnDefault;
                        final /* synthetic */ long val$netId;

                        public AnonymousClass1(long j, int i, boolean z2) {
                            r2 = j;
                            r4 = i;
                            r5 = z2;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(r2, r4);
                            if (r5) {
                                NetworkChangeNotifierAutoDetect.this.mObserver.onConnectionTypeChanged(r4);
                                NetworkChangeNotifierAutoDetect.this.mObserver.purgeActiveNetworkList(new long[]{r2});
                            }
                        }
                    });
                }
                if (scoped != null) {
                    scoped.close();
                }
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onCapabilitiesChanged");
            try {
                if (!ignoreConnectedNetwork(network, networkCapabilities)) {
                    NetworkChangeNotifierAutoDetect.this.runOnThread(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect.MyNetworkCallback.2
                        final /* synthetic */ int val$connectionType;
                        final /* synthetic */ long val$netId;

                        public AnonymousClass2(long j, int i) {
                            r2 = j;
                            r4 = i;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            NetworkChangeNotifierAutoDetect.this.mObserver.onNetworkConnect(r2, r4);
                        }
                    });
                }
                if (scoped != null) {
                    scoped.close();
                }
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLosing(Network network, int i) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onLosing");
            try {
                if (!ignoreConnectedNetwork(network, null)) {
                    NetworkChangeNotifierAutoDetect.this.runOnThread(new Http2Ping.AnonymousClass1(this, NetworkChangeNotifierAutoDetect.networkToNetId(network), 4));
                }
                if (scoped != null) {
                    scoped.close();
                }
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

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            TraceEvent scoped = TraceEvent.scoped("NetworkChangeNotifierCallback::onLost");
            try {
                if (!ignoreNetworkDueToVpn(network)) {
                    NetworkChangeNotifierAutoDetect.this.runOnThread(new RetriableStream.Sublistener.AnonymousClass4(this, network, 3));
                    if (this.mVpnInPlace != null) {
                        this.mVpnInPlace = null;
                        for (Network network2 : NetworkChangeNotifierAutoDetect.getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, network)) {
                            onAvailable(network2);
                        }
                        NetworkChangeNotifierAutoDetect.this.updateCurrentNetworkState();
                        NetworkChangeNotifierAutoDetect.this.runOnThread(new RatingView$$ExternalSyntheticLambda5(this, NetworkChangeNotifierAutoDetect.this.getCurrentNetworkState().getConnectionType(), 11, (char[]) null));
                    }
                }
                if (scoped != null) {
                    scoped.close();
                }
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

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NetworkConnectivityIntentFilter extends IntentFilter {
        public NetworkConnectivityIntentFilter() {
            addAction("android.net.conn.CONNECTIVITY_CHANGE");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NetworkState {
        private final boolean mConnected;
        private final boolean mIsMetered;
        private final boolean mIsPrivateDnsActive;
        private final String mNetworkIdentifier;
        private final String mPrivateDnsServerName;
        private final int mSubtype;
        private final int mType;

        public NetworkState(boolean z, int i, int i2, boolean z2, String str, boolean z3, String str2) {
            this.mConnected = z;
            this.mType = i;
            this.mSubtype = i2;
            this.mIsMetered = z2;
            this.mNetworkIdentifier = str == null ? "" : str;
            this.mIsPrivateDnsActive = z3;
            this.mPrivateDnsServerName = str2 == null ? "" : str2;
        }

        public int getConnectionCost() {
            if (isMetered()) {
                return 2;
            }
            return 1;
        }

        public int getConnectionSubtype() {
            if (!isConnected()) {
                return 1;
            }
            int networkType = getNetworkType();
            if (networkType != 0 && networkType != 4 && networkType != 5) {
                return 0;
            }
            switch (getNetworkSubType()) {
                case 1:
                    return 7;
                case 2:
                    return 8;
                case 3:
                    return 9;
                case 4:
                    return 5;
                case 5:
                    return 10;
                case 6:
                    return 11;
                case 7:
                    return 6;
                case 8:
                    return 14;
                case 9:
                    return 15;
                case 10:
                    return 12;
                case 11:
                    return 4;
                case 12:
                    return 13;
                case 13:
                    return 18;
                case 14:
                    return 16;
                case 15:
                    return 17;
                default:
                    return 0;
            }
        }

        public int getConnectionType() {
            if (isConnected()) {
                return NetworkChangeNotifierAutoDetect.convertToConnectionType(getNetworkType(), getNetworkSubType());
            }
            return 6;
        }

        public String getNetworkIdentifier() {
            return this.mNetworkIdentifier;
        }

        public int getNetworkSubType() {
            return this.mSubtype;
        }

        public int getNetworkType() {
            return this.mType;
        }

        public String getPrivateDnsServerName() {
            return this.mPrivateDnsServerName;
        }

        public boolean isConnected() {
            return this.mConnected;
        }

        public boolean isMetered() {
            return this.mIsMetered;
        }

        public boolean isPrivateDnsActive() {
            return this.mIsPrivateDnsActive;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Observer {
        void onConnectionCostChanged(int i);

        void onConnectionSubtypeChanged(int i);

        void onConnectionTypeChanged(int i);

        void onNetworkConnect(long j, int i);

        void onNetworkDisconnect(long j);

        void onNetworkSoonToDisconnect(long j);

        void purgeActiveNetworkList(long[] jArr);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class RegistrationPolicy {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private NetworkChangeNotifierAutoDetect mNotifier;

        protected abstract void destroy();

        public void init(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
            this.mNotifier = networkChangeNotifierAutoDetect;
        }

        public final void register() {
            this.mNotifier.register();
        }

        public final void unregister() {
            this.mNotifier.unregister();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WifiManagerDelegate {
    }

    /* renamed from: -$$Nest$fgetmIgnoreNextBroadcast */
    public static /* bridge */ /* synthetic */ boolean m341$$Nest$fgetmIgnoreNextBroadcast(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        return networkChangeNotifierAutoDetect.mIgnoreNextBroadcast;
    }

    /* renamed from: -$$Nest$fputmIgnoreNextBroadcast */
    public static /* bridge */ /* synthetic */ void m344$$Nest$fputmIgnoreNextBroadcast(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect, boolean z) {
        networkChangeNotifierAutoDetect.mIgnoreNextBroadcast = z;
    }

    /* renamed from: -$$Nest$mconnectionTypeChanged */
    public static /* bridge */ /* synthetic */ void m345$$Nest$mconnectionTypeChanged(NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect) {
        networkChangeNotifierAutoDetect.connectionTypeChanged();
    }

    public NetworkChangeNotifierAutoDetect(Observer observer, RegistrationPolicy registrationPolicy) {
        DefaultNetworkCallback defaultNetworkCallback;
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.constructor");
        try {
            Looper myLooper = Looper.myLooper();
            this.mLooper = myLooper;
            this.mHandler = new Handler(myLooper);
            this.mObserver = observer;
            this.mConnectivityManagerDelegate = new ConnectivityManagerDelegate(ContextUtils.sApplicationContext);
            this.mNetworkCallback = new MyNetworkCallback();
            this.mNetworkRequest = new NetworkRequest.Builder().addCapability(12).removeCapability(15).build();
            if (Build.VERSION.SDK_INT >= 30) {
                this.mDefaultNetworkCallback = new AndroidRDefaultNetworkCallback();
            } else {
                if (Build.VERSION.SDK_INT >= 28) {
                    defaultNetworkCallback = new DefaultNetworkCallback();
                } else {
                    defaultNetworkCallback = null;
                }
                this.mDefaultNetworkCallback = defaultNetworkCallback;
            }
            updateCurrentNetworkState();
            this.mIntentFilter = new NetworkConnectivityIntentFilter();
            this.mIgnoreNextBroadcast = false;
            this.mShouldSignalObserver = false;
            this.mRegistrationPolicy = registrationPolicy;
            registrationPolicy.init(this);
            this.mShouldSignalObserver = true;
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void connectionTypeChanged() {
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.connectionTypeChanged");
        try {
            connectionTypeChangedTo(this.mConnectivityManagerDelegate.getNetworkState$ar$ds());
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void connectionTypeChangedTo(NetworkState networkState) {
        if (networkState.getConnectionType() != this.mNetworkState.getConnectionType() || !networkState.getNetworkIdentifier().equals(this.mNetworkState.getNetworkIdentifier()) || networkState.isPrivateDnsActive() != this.mNetworkState.isPrivateDnsActive() || !networkState.getPrivateDnsServerName().equals(this.mNetworkState.getPrivateDnsServerName())) {
            this.mObserver.onConnectionTypeChanged(networkState.getConnectionType());
        }
        if (networkState.getConnectionType() != this.mNetworkState.getConnectionType() || networkState.getConnectionSubtype() != this.mNetworkState.getConnectionSubtype()) {
            this.mObserver.onConnectionSubtypeChanged(networkState.getConnectionSubtype());
        }
        if (networkState.getConnectionCost() != this.mNetworkState.getConnectionCost()) {
            this.mObserver.onConnectionCostChanged(networkState.getConnectionCost());
        }
        this.mNetworkState = networkState;
    }

    public static int convertToConnectionType(int i, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 4 && i != 5) {
                    if (i == 6) {
                        return 5;
                    }
                    if (i == 7) {
                        return 7;
                    }
                    if (i != 9) {
                        return 0;
                    }
                    return 1;
                }
            } else {
                return 2;
            }
        }
        if (i2 != 20) {
            switch (i2) {
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return 3;
                case 3:
                case 5:
                case 6:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return 4;
                case 13:
                    return 5;
                default:
                    return 0;
            }
        }
        return 8;
    }

    public static Network[] getAllNetworksFiltered(ConnectivityManagerDelegate connectivityManagerDelegate, Network network) {
        NetworkCapabilities networkCapabilities;
        Network[] allNetworks = connectivityManagerDelegate.mConnectivityManager.getAllNetworks();
        if (allNetworks == null) {
            allNetworks = new Network[0];
        }
        int i = 0;
        for (Network network2 : allNetworks) {
            if (!network2.equals(network) && (networkCapabilities = connectivityManagerDelegate.getNetworkCapabilities(network2)) != null && networkCapabilities.hasCapability(12)) {
                if (networkCapabilities.hasTransport(4)) {
                    if (connectivityManagerDelegate.vpnAccessible(network2)) {
                        return new Network[]{network2};
                    }
                } else {
                    allNetworks[i] = network2;
                    i++;
                }
            }
        }
        return (Network[]) Arrays.copyOf(allNetworks, i);
    }

    public /* synthetic */ void lambda$runOnThread$0(Runnable runnable) {
        if (this.mRegistered) {
            runnable.run();
        }
    }

    public /* synthetic */ void lambda$setConnectivityManagerDelegateForTests$1(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.mConnectivityManagerDelegate = connectivityManagerDelegate;
    }

    public /* synthetic */ void lambda$setWifiManagerDelegateForTests$2(WifiManagerDelegate wifiManagerDelegate) {
        this.mWifiManagerDelegate = wifiManagerDelegate;
    }

    public static long networkToNetId(Network network) {
        return network.getNetworkHandle();
    }

    private boolean onThread() {
        if (this.mLooper == Looper.myLooper()) {
            return true;
        }
        return false;
    }

    public void runOnThread(final Runnable runnable) {
        if (onThread()) {
            runnable.run();
        } else {
            this.mHandler.post(new Runnable() { // from class: org.chromium.net.NetworkChangeNotifierAutoDetect$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    NetworkChangeNotifierAutoDetect.this.lambda$runOnThread$0(runnable);
                }
            });
        }
    }

    public void destroy() {
        this.mRegistrationPolicy.destroy();
        unregister();
    }

    public NetworkState getCurrentNetworkState() {
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.getCurrentNetworkState");
        try {
            NetworkState networkState = this.mNetworkState;
            Trace.endSection();
            return networkState;
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public long getDefaultNetId() {
        Network defaultNetwork = getDefaultNetwork();
        if (defaultNetwork == null) {
            return -1L;
        }
        return networkToNetId(defaultNetwork);
    }

    public Network getDefaultNetwork() {
        return this.mConnectivityManagerDelegate.getDefaultNetwork();
    }

    public long[] getNetworksAndTypes() {
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.getNetworksAndTypes");
        try {
            Network[] allNetworksFiltered = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
            int length = allNetworksFiltered.length;
            long[] jArr = new long[length + length];
            int i = 0;
            for (Network network : allNetworksFiltered) {
                int i2 = i + 1;
                jArr[i] = networkToNetId(network);
                i += 2;
                jArr[i2] = this.mConnectivityManagerDelegate.getConnectionType(r5);
            }
            Trace.endSection();
            return jArr;
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public Network[] getNetworksForTesting() {
        return getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
    }

    RegistrationPolicy getRegistrationPolicy() {
        return this.mRegistrationPolicy;
    }

    boolean isReceiverRegisteredForTesting() {
        return this.mRegistered;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        runOnThread(new RetriableStream$Sublistener$1RetryBackoffRunnable$1(this, 10));
    }

    public void register() {
        NetworkCapabilities networkCapabilities;
        boolean z;
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.register");
        try {
            if (this.mRegistered) {
                connectionTypeChanged();
            } else {
                if (this.mShouldSignalObserver) {
                    connectionTypeChanged();
                }
                ConnectivityManager.NetworkCallback networkCallback = this.mDefaultNetworkCallback;
                if (networkCallback != null) {
                    try {
                        this.mConnectivityManagerDelegate.mConnectivityManager.registerDefaultNetworkCallback(networkCallback, this.mHandler);
                    } catch (RuntimeException unused) {
                        this.mDefaultNetworkCallback = null;
                    }
                }
                if (this.mDefaultNetworkCallback == null) {
                    if (ContextUtils.registerProtectedBroadcastReceiver(ContextUtils.sApplicationContext, this, this.mIntentFilter) != null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.mIgnoreNextBroadcast = z;
                }
                this.mRegistered = true;
                MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
                if (myNetworkCallback != null) {
                    new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.initializeVpnInPlace");
                    try {
                        Network[] allNetworksFiltered = getAllNetworksFiltered(NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate, null);
                        myNetworkCallback.mVpnInPlace = null;
                        if (allNetworksFiltered.length == 1 && (networkCapabilities = NetworkChangeNotifierAutoDetect.this.mConnectivityManagerDelegate.getNetworkCapabilities(allNetworksFiltered[0])) != null && networkCapabilities.hasTransport(4)) {
                            myNetworkCallback.mVpnInPlace = allNetworksFiltered[0];
                        }
                        Trace.endSection();
                        try {
                            ConnectivityManagerDelegate connectivityManagerDelegate = this.mConnectivityManagerDelegate;
                            NetworkRequest networkRequest = this.mNetworkRequest;
                            MyNetworkCallback myNetworkCallback2 = this.mNetworkCallback;
                            Handler handler = this.mHandler;
                            StrictModeContext allowDiskReads = StrictModeContext.allowDiskReads();
                            try {
                                connectivityManagerDelegate.mConnectivityManager.registerNetworkCallback(networkRequest, myNetworkCallback2, handler);
                                allowDiskReads.close();
                            } catch (Throwable th) {
                                try {
                                    allowDiskReads.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } catch (RuntimeException unused2) {
                            this.mRegisterNetworkCallbackFailed = true;
                            this.mNetworkCallback = null;
                        }
                        if (!this.mRegisterNetworkCallbackFailed && this.mShouldSignalObserver) {
                            Network[] allNetworksFiltered2 = getAllNetworksFiltered(this.mConnectivityManagerDelegate, null);
                            long[] jArr = new long[allNetworksFiltered2.length];
                            for (int i = 0; i < allNetworksFiltered2.length; i++) {
                                jArr[i] = networkToNetId(allNetworksFiltered2[i]);
                            }
                            this.mObserver.purgeActiveNetworkList(jArr);
                        }
                    } finally {
                    }
                }
            }
            Trace.endSection();
        } catch (Throwable th3) {
            try {
                Trace.endSection();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public boolean registerNetworkCallbackFailed() {
        return this.mRegisterNetworkCallbackFailed;
    }

    void setConnectivityManagerDelegateForTests(ConnectivityManagerDelegate connectivityManagerDelegate) {
        this.mConnectivityManagerDelegate = connectivityManagerDelegate;
    }

    void setWifiManagerDelegateForTests(WifiManagerDelegate wifiManagerDelegate) {
        this.mWifiManagerDelegate = wifiManagerDelegate;
    }

    public void unregister() {
        if (!this.mRegistered) {
            return;
        }
        this.mRegistered = false;
        MyNetworkCallback myNetworkCallback = this.mNetworkCallback;
        if (myNetworkCallback != null) {
            this.mConnectivityManagerDelegate.unregisterNetworkCallback(myNetworkCallback);
        }
        ConnectivityManager.NetworkCallback networkCallback = this.mDefaultNetworkCallback;
        if (networkCallback != null) {
            this.mConnectivityManagerDelegate.unregisterNetworkCallback(networkCallback);
        } else {
            ContextUtils.sApplicationContext.unregisterReceiver(this);
        }
    }

    public void updateCurrentNetworkState() {
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.updateCurrentNetworkState");
        try {
            this.mNetworkState = this.mConnectivityManagerDelegate.getNetworkState$ar$ds();
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private void assertOnThread() {
    }
}
