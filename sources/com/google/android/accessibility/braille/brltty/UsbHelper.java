package com.google.android.accessibility.braille.brltty;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class UsbHelper {
    public static void cancelRequest(UsbRequest usbRequest) {
        usbRequest.cancel();
        usbRequest.close();
    }

    public static UsbInterface getDeviceInterface(UsbDevice usbDevice, int i) {
        int interfaceCount = usbDevice.getInterfaceCount();
        for (int i2 = 0; i2 < interfaceCount; i2++) {
            UsbInterface usbInterface = usbDevice.getInterface(i2);
            if (i == usbInterface.getId()) {
                return usbInterface;
            }
        }
        return null;
    }

    public static Iterator<UsbDevice> getDeviceIterator(Context context) {
        return ((UsbManager) context.getSystemService("usb")).getDeviceList().values().iterator();
    }

    public static UsbEndpoint getInterfaceEndpoint(UsbInterface usbInterface, int i) {
        int endpointCount = usbInterface.getEndpointCount();
        for (int i2 = 0; i2 < endpointCount; i2++) {
            UsbEndpoint endpoint = usbInterface.getEndpoint(i2);
            if (i == endpoint.getAddress()) {
                return endpoint;
            }
        }
        return null;
    }

    public static UsbDevice getNextDevice(Iterator<UsbDevice> it) {
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    public static UsbDeviceConnection openDeviceConnection(Context context, UsbDevice usbDevice) {
        return ((UsbManager) context.getSystemService("usb")).openDevice(usbDevice);
    }
}
