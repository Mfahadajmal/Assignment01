package com.google.android.accessibility.braille.brltty;

import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeviceInfo {
    private final boolean connectSecurely;
    public final String driverCode;
    public final ImmutableMap friendlyKeyNames;
    public final String modelName;

    public DeviceInfo() {
    }

    public final boolean connectSecurely() {
        return this.connectSecurely;
    }

    public final String driverCode() {
        return this.driverCode;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DeviceInfo) {
            DeviceInfo deviceInfo = (DeviceInfo) obj;
            if (this.driverCode.equals(deviceInfo.driverCode()) && this.modelName.equals(deviceInfo.modelName()) && this.connectSecurely == deviceInfo.connectSecurely() && ContextDataProvider.equalsImpl(this.friendlyKeyNames, deviceInfo.friendlyKeyNames())) {
                return true;
            }
        }
        return false;
    }

    public final ImmutableMap friendlyKeyNames() {
        return this.friendlyKeyNames;
    }

    public final int hashCode() {
        int i;
        int hashCode = ((this.driverCode.hashCode() ^ 1000003) * 1000003) ^ this.modelName.hashCode();
        if (true != this.connectSecurely) {
            i = 1237;
        } else {
            i = 1231;
        }
        return (((hashCode * 1000003) ^ i) * 1000003) ^ this.friendlyKeyNames.hashCode();
    }

    public final String modelName() {
        return this.modelName;
    }

    public final String toString() {
        return "DeviceInfo{driverCode=" + this.driverCode + ", modelName=" + this.modelName + ", connectSecurely=" + this.connectSecurely + ", friendlyKeyNames=" + String.valueOf(this.friendlyKeyNames) + "}";
    }

    public DeviceInfo(String str, String str2, boolean z, ImmutableMap immutableMap) {
        this();
        this.driverCode = str;
        this.modelName = str2;
        this.connectSecurely = z;
        this.friendlyKeyNames = immutableMap;
    }
}
