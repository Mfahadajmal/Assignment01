package org.chromium.base;

import com.google.mlkit.logging.schema.acceleration.DeviceInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MemoryPressureListener {
    private static ObserverList sCallbacks;

    private static void addNativeCallback() {
        DeviceInfo deviceInfo = new DeviceInfo();
        if (sCallbacks == null) {
            sCallbacks = new ObserverList();
        }
        sCallbacks.addObserver$ar$ds(deviceInfo);
    }
}
