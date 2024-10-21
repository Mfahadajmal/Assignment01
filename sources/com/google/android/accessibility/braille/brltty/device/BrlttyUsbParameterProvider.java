package com.google.android.accessibility.braille.brltty.device;

import android.hardware.usb.UsbDevice;
import android.support.v7.app.AppCompatDelegate;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrlttyUsbParameterProvider extends ParameterProvider {
    private final UsbDevice device;

    public BrlttyUsbParameterProvider(UsbDevice usbDevice) {
        this.device = usbDevice;
    }

    @Override // com.google.android.accessibility.braille.brltty.device.ParameterProvider
    public final String getParameters() {
        String obj = AppCompatDelegate.Api33Impl.filterNonPrintCharacter(this.device.getSerialNumber()).toString();
        UsbDevice usbDevice = this.device;
        return "usb:+serialNumber=" + obj + "+vendorIdentifier=" + usbDevice.getVendorId() + "+productIdentifier=" + usbDevice.getProductId();
    }
}
