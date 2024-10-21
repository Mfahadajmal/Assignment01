package com.google.android.accessibility.braille.brailledisplay.platform.connect.device;

import android.hardware.usb.UsbDevice;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectableUsbDevice extends ConnectableDevice {
    private final UsbDevice usbDevice;

    public ConnectableUsbDevice(UsbDevice usbDevice) {
        this();
        this.usbDevice = usbDevice;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice
    public final String address() {
        return String.valueOf(usbDevice().getDeviceId());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ConnectableUsbDevice) {
            return this.usbDevice.equals(((ConnectableUsbDevice) obj).usbDevice());
        }
        return false;
    }

    public final int hashCode() {
        return this.usbDevice.hashCode() ^ 1000003;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice
    public final String name() {
        return usbDevice().getProductName();
    }

    public final UsbDevice usbDevice() {
        return this.usbDevice;
    }

    public ConnectableUsbDevice() {
    }
}
