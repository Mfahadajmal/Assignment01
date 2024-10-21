package com.google.android.accessibility.braille.brailledisplay.platform.lib;

import android.content.Context;
import android.os.Bundle;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryChangeReceiver extends ActionReceiver {
    public BatteryChangeReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.intent.action.BATTERY_CHANGED"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        ((RetryingNameResolver.ResolutionResultListener) obj).onBatteryVolumePercentageChanged((int) ((bundle.getInt("level", -1) / bundle.getInt("scale", -1)) * 100.0d));
    }
}
