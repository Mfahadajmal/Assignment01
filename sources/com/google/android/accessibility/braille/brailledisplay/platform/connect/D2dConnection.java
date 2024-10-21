package com.google.android.accessibility.braille.brailledisplay.platform.connect;

import com.google.android.accessibility.braille.brailledisplay.platform.connect.device.ConnectableDevice;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface D2dConnection {
    ConnectableDevice getDevice();

    void open$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(RetryingNameResolver.ResolutionResultListener resolutionResultListener);

    void sendOutgoingPacket(byte[] bArr);

    void shutdown();
}
