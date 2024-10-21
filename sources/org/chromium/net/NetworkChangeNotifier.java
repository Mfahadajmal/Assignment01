package org.chromium.net;

import J.N;
import android.os.Trace;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ObserverList;
import org.chromium.base.metrics.ScopedSysTraceEvent;
import org.chromium.net.NetworkChangeNotifierAutoDetect;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NetworkChangeNotifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static NetworkChangeNotifier sInstance;
    private NetworkChangeNotifierAutoDetect mAutoDetector;
    private int mCurrentConnectionType = 0;
    private int mCurrentConnectionCost = 0;
    private final ArrayList<Long> mNativeChangeNotifiers = new ArrayList<>();
    private final ObserverList<ConnectionTypeObserver> mConnectionTypeObservers = new ObserverList<>();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ConnectionTypeObserver {
        void onConnectionTypeChanged(int i);
    }

    protected NetworkChangeNotifier() {
    }

    public static void addConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().addConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void addConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.addObserver$ar$ds(connectionTypeObserver);
    }

    private void destroyAutoDetector() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect != null) {
            networkChangeNotifierAutoDetect.destroy();
            this.mAutoDetector = null;
        }
    }

    public static void fakeConnectionCostChanged(int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionCostChange(i);
    }

    public static void fakeConnectionSubtypeChanged(int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionSubtypeChange(i);
    }

    public static void fakeDefaultNetwork(long j, int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfConnectionTypeChange(i, j);
    }

    public static void fakeNetworkConnected(long j, int i) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkConnect(j, i);
    }

    public static void fakeNetworkDisconnected(long j) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkDisconnect(j);
    }

    public static void fakeNetworkSoonToBeDisconnected(long j) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversOfNetworkSoonToDisconnect(j);
    }

    public static void fakePurgeActiveNetworkList(long[] jArr) {
        setAutoDetectConnectivityState(false);
        getInstance().notifyObserversToPurgeActiveNetworkList(jArr);
    }

    public static void forceConnectivityState(boolean z) {
        setAutoDetectConnectivityState(false);
        getInstance().forceConnectivityStateInternal(z);
    }

    private void forceConnectivityStateInternal(boolean z) {
        boolean z2;
        int i = 0;
        if (this.mCurrentConnectionType == 6) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2 != z) {
            if (true != z) {
                i = 6;
            }
            updateCurrentConnectionType(i);
            notifyObserversOfConnectionSubtypeChange(!z ? 1 : 0);
        }
    }

    public static NetworkChangeNotifierAutoDetect getAutoDetectorForTest() {
        return getInstance().mAutoDetector;
    }

    public static NetworkChangeNotifier getInstance() {
        return sInstance;
    }

    public static NetworkChangeNotifier init() {
        if (sInstance == null) {
            sInstance = new NetworkChangeNotifier();
        }
        return sInstance;
    }

    public static boolean isInitialized() {
        if (sInstance != null) {
            return true;
        }
        return false;
    }

    public static boolean isOnline() {
        if (getInstance().getCurrentConnectionType() != 6) {
            return true;
        }
        return false;
    }

    public static void registerToReceiveNotificationsAlways() {
        getInstance().setAutoDetectConnectivityStateInternal(true, new RegistrationPolicyAlwaysRegister());
    }

    public static void removeConnectionTypeObserver(ConnectionTypeObserver connectionTypeObserver) {
        getInstance().removeConnectionTypeObserverInternal(connectionTypeObserver);
    }

    private void removeConnectionTypeObserverInternal(ConnectionTypeObserver connectionTypeObserver) {
        this.mConnectionTypeObservers.removeObserver(connectionTypeObserver);
    }

    public static void resetInstanceForTests(NetworkChangeNotifier networkChangeNotifier) {
        sInstance = networkChangeNotifier;
    }

    public static void setAutoDetectConnectivityState(NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy, boolean z) {
        getInstance().setAutoDetectConnectivityStateInternal(true, registrationPolicy, z);
    }

    private void setAutoDetectConnectivityStateInternal(boolean z, NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy) {
        setAutoDetectConnectivityStateInternal(z, registrationPolicy, true);
    }

    private void updateCurrentConnectionCost(int i) {
        this.mCurrentConnectionCost = i;
        notifyObserversOfConnectionCostChange(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentConnectionType(int i) {
        this.mCurrentConnectionType = i;
        notifyObserversOfConnectionTypeChange(i);
    }

    public void addNativeObserver(long j) {
        this.mNativeChangeNotifiers.add(Long.valueOf(j));
    }

    public int getCurrentConnectionCost() {
        return this.mCurrentConnectionCost;
    }

    public int getCurrentConnectionSubtype() {
        int connectionSubtype;
        new ScopedSysTraceEvent("NetworkChangeNotifier.getCurrentConnectionSubtype");
        try {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
            if (networkChangeNotifierAutoDetect == null) {
                connectionSubtype = 0;
            } else {
                networkChangeNotifierAutoDetect.updateCurrentNetworkState();
                connectionSubtype = this.mAutoDetector.getCurrentNetworkState().getConnectionSubtype();
            }
            Trace.endSection();
            return connectionSubtype;
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public int getCurrentConnectionType() {
        return this.mCurrentConnectionType;
    }

    public long getCurrentDefaultNetId() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return -1L;
        }
        return networkChangeNotifierAutoDetect.getDefaultNetId();
    }

    public long[] getCurrentNetworksAndTypes() {
        long[] networksAndTypes;
        new ScopedSysTraceEvent("NetworkChangeNotifierAutoDetect.getCurrentNetworksAndTypes");
        try {
            NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
            if (networkChangeNotifierAutoDetect == null) {
                networksAndTypes = new long[0];
            } else {
                networksAndTypes = networkChangeNotifierAutoDetect.getNetworksAndTypes();
            }
            Trace.endSection();
            return networksAndTypes;
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void notifyObserversOfConnectionCostChange(int i) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            N.Mg0W7eRL(arrayList.get(i2).longValue(), this, i);
        }
    }

    public void notifyObserversOfConnectionSubtypeChange(int i) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            N.MCEqyWQ0(arrayList.get(i2).longValue(), this, i);
        }
    }

    public void notifyObserversOfConnectionTypeChange(int i) {
        notifyObserversOfConnectionTypeChange(i, getCurrentDefaultNetId());
    }

    public void notifyObserversOfNetworkConnect(long j, int i) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            N.MBT1i5cd(arrayList.get(i2).longValue(), this, j, i);
        }
    }

    public void notifyObserversOfNetworkDisconnect(long j) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            N.MDpuHJTB(arrayList.get(i).longValue(), this, j);
        }
    }

    public void notifyObserversOfNetworkSoonToDisconnect(long j) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            N.MiJIMrTb(arrayList.get(i).longValue(), this, j);
        }
    }

    public void notifyObserversToPurgeActiveNetworkList(long[] jArr) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            N.MpF$179U(arrayList.get(i).longValue(), this, jArr);
        }
    }

    public boolean registerNetworkCallbackFailed() {
        NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = this.mAutoDetector;
        if (networkChangeNotifierAutoDetect == null) {
            return false;
        }
        return networkChangeNotifierAutoDetect.registerNetworkCallbackFailed();
    }

    public void removeNativeObserver(long j) {
        this.mNativeChangeNotifiers.remove(Long.valueOf(j));
    }

    private void notifyObserversOfConnectionTypeChange(int i, long j) {
        ArrayList<Long> arrayList = this.mNativeChangeNotifiers;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            N.MbPIImnU(arrayList.get(i2).longValue(), this, i, j);
        }
        Iterator it = this.mConnectionTypeObservers.iterator();
        while (it.hasNext()) {
            ((ConnectionTypeObserver) it.next()).onConnectionTypeChanged(i);
        }
    }

    public static void resetInstanceForTests() {
        sInstance = new NetworkChangeNotifier();
    }

    public static void setAutoDetectConnectivityState(boolean z) {
        getInstance().setAutoDetectConnectivityStateInternal(z, new RegistrationPolicyApplicationStatus());
    }

    private void setAutoDetectConnectivityStateInternal(boolean z, NetworkChangeNotifierAutoDetect.RegistrationPolicy registrationPolicy, boolean z2) {
        new ScopedSysTraceEvent("NetworkChangeNotifier.setAutoDetectConnectivityStateInternal");
        if (z) {
            try {
                if (this.mAutoDetector == null) {
                    NetworkChangeNotifierAutoDetect networkChangeNotifierAutoDetect = new NetworkChangeNotifierAutoDetect(new NetworkChangeNotifierAutoDetect.Observer() { // from class: org.chromium.net.NetworkChangeNotifier.1
                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onConnectionCostChanged(int i) {
                            NetworkChangeNotifier.this.notifyObserversOfConnectionCostChange(i);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onConnectionSubtypeChanged(int i) {
                            NetworkChangeNotifier.this.notifyObserversOfConnectionSubtypeChange(i);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onConnectionTypeChanged(int i) {
                            NetworkChangeNotifier.this.updateCurrentConnectionType(i);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onNetworkConnect(long j, int i) {
                            NetworkChangeNotifier.this.notifyObserversOfNetworkConnect(j, i);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onNetworkDisconnect(long j) {
                            NetworkChangeNotifier.this.notifyObserversOfNetworkDisconnect(j);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void onNetworkSoonToDisconnect(long j) {
                            NetworkChangeNotifier.this.notifyObserversOfNetworkSoonToDisconnect(j);
                        }

                        @Override // org.chromium.net.NetworkChangeNotifierAutoDetect.Observer
                        public final void purgeActiveNetworkList(long[] jArr) {
                            NetworkChangeNotifier.this.notifyObserversToPurgeActiveNetworkList(jArr);
                        }
                    }, registrationPolicy);
                    this.mAutoDetector = networkChangeNotifierAutoDetect;
                    if (z2) {
                        networkChangeNotifierAutoDetect.updateCurrentNetworkState();
                    }
                    NetworkChangeNotifierAutoDetect.NetworkState currentNetworkState = this.mAutoDetector.getCurrentNetworkState();
                    updateCurrentConnectionType(currentNetworkState.getConnectionType());
                    updateCurrentConnectionCost(currentNetworkState.getConnectionCost());
                    notifyObserversOfConnectionSubtypeChange(currentNetworkState.getConnectionSubtype());
                } else {
                    destroyAutoDetector();
                }
            } catch (Throwable th) {
                try {
                    Trace.endSection();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        Trace.endSection();
    }
}
