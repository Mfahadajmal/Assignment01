package com.google.android.accessibility.braille.brailledisplay.platform.connect.usb;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableUsbDevice;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsbAttachedReceiver extends ActionReceiver {
    public UsbAttachedReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.hardware.usb.action.USB_DEVICE_ATTACHED", "android.hardware.usb.action.USB_DEVICE_DETACHED"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        UsbDevice usbDevice = (UsbDevice) bundle.getParcelable("device");
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (byte[]) null, (char[]) null, (byte[]) null);
        onDeviceTextDetectionLoadLogEvent.setUsbDevice$ar$ds(usbDevice);
        ConnectableUsbDevice m223build = onDeviceTextDetectionLoadLogEvent.m223build();
        if (str.equals("android.hardware.usb.action.USB_DEVICE_ATTACHED")) {
            resolutionResultListener.onUsbAttached(m223build);
        } else if (str.equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
            resolutionResultListener.onUsbDetached(m223build);
        }
    }
}
