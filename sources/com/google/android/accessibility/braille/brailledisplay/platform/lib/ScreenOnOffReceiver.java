package com.google.android.accessibility.braille.brailledisplay.platform.lib;

import android.content.Context;
import android.os.Bundle;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenOnOffReceiver extends ActionReceiver {
    public ScreenOnOffReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.intent.action.SCREEN_ON", "android.intent.action.SCREEN_OFF"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        if ("android.intent.action.SCREEN_ON".equals(str)) {
            resolutionResultListener.onScreenOn();
        } else if ("android.intent.action.SCREEN_OFF".equals(str)) {
            resolutionResultListener.onScreenOff();
        }
    }
}
