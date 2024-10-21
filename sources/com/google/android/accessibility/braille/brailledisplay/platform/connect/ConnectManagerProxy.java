package com.google.android.accessibility.braille.brailledisplay.platform.connect;

import android.content.Context;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import java.util.Collection;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectManagerProxy extends ConnectManager {
    public final BtConnectManager btConnectManager;
    private ConnectManager connectManager;
    public final UsbConnectManager usbConnectManager;

    public ConnectManagerProxy(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        BtConnectManager btConnectManager = new BtConnectManager(context, resolutionResultListener);
        this.btConnectManager = btConnectManager;
        this.usbConnectManager = new UsbConnectManager(context, resolutionResultListener);
        this.connectManager = btConnectManager;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void connect(ConnectableDevice connectableDevice, boolean z) {
        this.connectManager.connect(connectableDevice, z);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void disconnect() {
        this.connectManager.disconnect();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void forget(ConnectableDevice connectableDevice) {
        this.connectManager.forget(connectableDevice);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Set getBondedDevices() {
        return this.connectManager.getBondedDevices();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Collection getConnectableDevices() {
        return this.connectManager.getConnectableDevices();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectedDevice() {
        return this.connectManager.getCurrentlyConnectedDevice();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final Optional getCurrentlyConnectingDevice() {
        return this.connectManager.getCurrentlyConnectingDevice();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final int getType$ar$edu$c2cf13b1_0() {
        return this.connectManager.getType$ar$edu$c2cf13b1_0();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isConnected() {
        return this.connectManager.isConnected();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isConnecting() {
        return this.connectManager.isConnecting();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final boolean isScanning() {
        return this.connectManager.isScanning();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStart() {
        this.connectManager.onStart();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void onStop() {
        this.connectManager.onStop();
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void sendOutgoingPacket(byte[] bArr) {
        this.connectManager.sendOutgoingPacket(bArr);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void startSearch$ar$edu(int i) {
        this.connectManager.startSearch$ar$edu(i);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.ConnectManager
    public final void stopSearch$ar$edu(int i) {
        this.connectManager.stopSearch$ar$edu(i);
    }

    public final void switchTo$ar$edu(int i) {
        if (i == 2) {
            this.btConnectManager.onStop();
            this.connectManager = this.usbConnectManager;
        } else {
            this.usbConnectManager.onStop();
            this.connectManager = this.btConnectManager;
        }
    }
}
