package com.google.android.accessibility.braille.brltty.device;

import android.bluetooth.BluetoothDevice;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrlttyBluetoothParameterProvider extends ParameterProvider {
    private final BluetoothDevice device;

    public BrlttyBluetoothParameterProvider(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    @Override // com.google.android.accessibility.braille.brltty.device.ParameterProvider
    public final String getParameters() {
        return "bluetooth:".concat(String.valueOf(this.device.getAddress()));
    }
}
