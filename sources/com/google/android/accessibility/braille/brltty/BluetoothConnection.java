package com.google.android.accessibility.braille.brltty;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BluetoothConnection {
    protected static final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private static BluetoothDevice[] pairedDevices = null;
    protected final byte[] remoteAddressBytes;
    protected final long remoteAddressValue;

    public BluetoothConnection(long j) {
        byte[] bArr = new byte[6];
        this.remoteAddressBytes = bArr;
        this.remoteAddressValue = j;
        int length = bArr.length;
        while (length > 0) {
            length--;
            this.remoteAddressBytes[length] = (byte) (255 & j);
            j >>= 8;
        }
    }

    private static BluetoothDevice getPairedDevice(int i) {
        BluetoothDevice[] bluetoothDeviceArr;
        if (i >= 0 && (bluetoothDeviceArr = pairedDevices) != null && i < bluetoothDeviceArr.length) {
            return bluetoothDeviceArr[i];
        }
        return null;
    }

    public static String getPairedDeviceAddress(int i) {
        BluetoothDevice pairedDevice = getPairedDevice(i);
        if (pairedDevice == null) {
            return null;
        }
        return pairedDevice.getAddress();
    }

    public static int getPairedDeviceCount() {
        Set<BluetoothDevice> bondedDevices;
        if (isUp() && (bondedDevices = bluetoothAdapter.getBondedDevices()) != null) {
            BluetoothDevice[] bluetoothDeviceArr = (BluetoothDevice[]) bondedDevices.toArray(new BluetoothDevice[bondedDevices.size()]);
            pairedDevices = bluetoothDeviceArr;
            return bluetoothDeviceArr.length;
        }
        pairedDevices = null;
        return 0;
    }

    public static String getPairedDeviceName(int i) {
        BluetoothDevice pairedDevice = getPairedDevice(i);
        if (pairedDevice == null) {
            return null;
        }
        return pairedDevice.getName();
    }

    public static boolean isUp() {
        BluetoothAdapter bluetoothAdapter2 = bluetoothAdapter;
        if (bluetoothAdapter2 == null) {
            return false;
        }
        return bluetoothAdapter2.isEnabled();
    }

    public final BluetoothDevice getDevice() {
        if (!isUp()) {
            return null;
        }
        return bluetoothAdapter.getRemoteDevice(this.remoteAddressBytes);
    }

    public final String getName() {
        BluetoothDevice device = getDevice();
        if (device == null) {
            return null;
        }
        return device.getName();
    }

    public static String getName(long j) {
        return new BluetoothConnection(j).getName();
    }
}
