package com.google.android.accessibility.braille.brailledisplay.platform.connect.usb;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsbPermissionReceiver extends ActionReceiver {
    public UsbPermissionReceiver(Context context, GooglePlayServicesUpdatedReceiver.Callback callback) {
        super(context, callback);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{String.valueOf(this.context.getPackageName()).concat(".USB_PERMISSION")};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        GooglePlayServicesUpdatedReceiver.Callback callback = (GooglePlayServicesUpdatedReceiver.Callback) obj;
        if (String.valueOf(this.context.getPackageName()).concat(".USB_PERMISSION").equals(str)) {
            UsbDevice usbDevice = (UsbDevice) bundle.getParcelable("device");
            if (bundle.getBoolean("permission", false)) {
                callback.onPermissionGranted(usbDevice);
            } else {
                callback.onPermissionDenied(usbDevice);
            }
        }
    }
}
