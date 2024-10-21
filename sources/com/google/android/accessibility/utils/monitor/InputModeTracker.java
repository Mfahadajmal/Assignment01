package com.google.android.accessibility.utils.monitor;

import android.view.InputDevice;
import android.view.KeyEvent;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ServiceKeyEventListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputModeTracker implements ServiceKeyEventListener {
    public int mInputMode = -1;

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean onKeyEvent(KeyEvent keyEvent, Performance.EventId eventId) {
        InputDevice device;
        boolean isExternal;
        if (FormFactorUtils.getInstance().isAndroidWear && (device = InputDevice.getDevice(keyEvent.getDeviceId())) != null) {
            isExternal = device.isExternal();
            if (!isExternal) {
                return false;
            }
        }
        InputDevice device2 = InputDevice.getDevice(keyEvent.getDeviceId());
        int i = 1;
        if (device2 != null && device2.getKeyboardType() == 1) {
            i = 3;
        }
        setInputMode(i);
        return false;
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean processWhenServiceSuspended() {
        return false;
    }

    public final void setInputMode(int i) {
        if (i == -1) {
            return;
        }
        this.mInputMode = i;
    }
}
