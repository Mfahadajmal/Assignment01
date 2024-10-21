package com.google.android.accessibility.braille.brailledisplay.platform;

import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectibleDeviceInfo {
    public final ConnectableDevice device;
    public final String deviceAddress;
    public final String deviceName;
    public final boolean isConnected;
    public final boolean isConnecting;
    public final boolean isRemembered;

    public ConnectibleDeviceInfo(String str, String str2, boolean z, boolean z2, boolean z3, ConnectableDevice connectableDevice) {
        this.deviceName = str;
        this.deviceAddress = str2;
        this.isRemembered = z;
        this.isConnecting = z2;
        this.isConnected = z3;
        this.device = connectableDevice;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ConnectibleDeviceInfo connectibleDeviceInfo = (ConnectibleDeviceInfo) obj;
            if (this.deviceName.equals(connectibleDeviceInfo.deviceName) && Objects.equals(this.device, connectibleDeviceInfo.device)) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasConnectableDevice() {
        if (this.device != null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.deviceName, this.device);
    }

    public final String toString() {
        String str;
        String str2;
        String str3;
        String format = String.format("%-30s", this.deviceName);
        StringBuilder sb = new StringBuilder("RowDevice{='");
        sb.append(format);
        sb.append("', ");
        String str4 = "***";
        if (this.device == null) {
            str = "***";
        } else {
            str = "Vis";
        }
        sb.append(str);
        sb.append(", ");
        if (true != this.isRemembered) {
            str2 = "***";
        } else {
            str2 = "Rem";
        }
        sb.append(str2);
        sb.append(", ");
        if (true != this.isConnecting) {
            str3 = "***";
        } else {
            str3 = "Ing";
        }
        sb.append(str3);
        sb.append(", ");
        if (true == this.isConnected) {
            str4 = "Ted";
        }
        sb.append(str4);
        sb.append("}");
        return sb.toString();
    }
}
