package com.google.android.accessibility.braille.brailledisplay.platform.connect.device;

import android.bluetooth.BluetoothDevice;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectableBluetoothDevice extends ConnectableDevice {
    private final BluetoothDevice bluetoothDevice;

    public ConnectableBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this();
        this.bluetoothDevice = bluetoothDevice;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice
    public final String address() {
        return bluetoothDevice().getAddress();
    }

    public final BluetoothDevice bluetoothDevice() {
        return this.bluetoothDevice;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ConnectableBluetoothDevice) {
            return this.bluetoothDevice.equals(((ConnectableBluetoothDevice) obj).bluetoothDevice());
        }
        return false;
    }

    public final int hashCode() {
        return this.bluetoothDevice.hashCode() ^ 1000003;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice
    public final String name() {
        return bluetoothDevice().getName();
    }

    public ConnectableBluetoothDevice() {
    }
}
