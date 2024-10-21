package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Bundle;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BtScanReceiver extends ActionReceiver {
    public BtScanReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.bluetooth.device.action.FOUND", "android.bluetooth.adapter.action.DISCOVERY_STARTED", "android.bluetooth.adapter.action.DISCOVERY_FINISHED"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        if ("android.bluetooth.adapter.action.DISCOVERY_STARTED".equals(str)) {
            resolutionResultListener.onDiscoveryStarted();
        } else if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(str)) {
            resolutionResultListener.onDiscoveryFinished();
        } else if ("android.bluetooth.device.action.FOUND".equals(str)) {
            resolutionResultListener.onDeviceSeen((BluetoothDevice) bundle.getParcelable("android.bluetooth.device.extra.DEVICE"));
        }
    }
}
