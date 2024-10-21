package com.google.android.accessibility.braille.brailledisplay.platform.connect.usb;

import android.os.Handler;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UsbConnection implements D2dConnection {
    private final ConnectableDevice device;
    public final Handler handler = new Handler();

    public UsbConnection(ConnectableDevice connectableDevice) {
        this.device = connectableDevice;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final ConnectableDevice getDevice() {
        return this.device;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void open$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.handler.postDelayed(new DelayedWorkTracker.AnonymousClass1(this, resolutionResultListener, 8), 20L);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void shutdown() {
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.connect.D2dConnection
    public final void sendOutgoingPacket(byte[] bArr) {
    }
}
