package com.google.android.accessibility.braille.brailledisplay.platform.connect;

import com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import j$.util.Optional;
import java.util.Collection;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ConnectManager {
    public BrailleDisplayManager.AccessibilityServiceContextProvider accessibilityServiceContextProvider;

    public abstract void connect(ConnectableDevice connectableDevice, boolean z);

    public abstract void disconnect();

    public abstract void forget(ConnectableDevice connectableDevice);

    public abstract Set getBondedDevices();

    public abstract Collection getConnectableDevices();

    public abstract Optional getCurrentlyConnectedDevice();

    public abstract Optional getCurrentlyConnectingDevice();

    public abstract int getType$ar$edu$c2cf13b1_0();

    public abstract boolean isConnected();

    public abstract boolean isConnecting();

    public abstract boolean isScanning();

    public abstract void onStart();

    public abstract void onStop();

    public abstract void sendOutgoingPacket(byte[] bArr);

    public abstract void startSearch$ar$edu(int i);

    public abstract void stopSearch$ar$edu(int i);
}
