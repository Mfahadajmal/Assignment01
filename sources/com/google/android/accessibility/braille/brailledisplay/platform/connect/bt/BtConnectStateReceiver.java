package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BtConnectStateReceiver extends ActionReceiver {
    public BtConnectStateReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.bluetooth.device.action.BOND_STATE_CHANGED", "android.bluetooth.device.action.ACL_CONNECTED", "android.bluetooth.device.action.ACL_DISCONNECTED"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) bundle.getParcelable("android.bluetooth.device.extra.DEVICE");
        if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(str)) {
            int i = bundle.getInt("android.bluetooth.device.extra.BOND_STATE", 10);
            if (i == 12) {
                resolutionResultListener.onBonded(bluetoothDevice);
                return;
            } else {
                if (i == 10) {
                    resolutionResultListener.onUnBonded(bluetoothDevice);
                    return;
                }
                return;
            }
        }
        if (Objects.equals(str, "android.bluetooth.device.action.ACL_CONNECTED")) {
            resolutionResultListener.onConnected(bluetoothDevice);
        } else if (Objects.equals(str, "android.bluetooth.device.action.ACL_DISCONNECTED")) {
            resolutionResultListener.onDisconnected(bluetoothDevice);
        }
    }
}
