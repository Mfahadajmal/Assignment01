package com.google.android.accessibility.braille.brailledisplay.platform.lib;

import android.content.Context;
import android.os.Bundle;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScreenUnlockReceiver extends ActionReceiver {
    public ScreenUnlockReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.intent.action.USER_PRESENT", "android.intent.action.SCREEN_OFF"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        if (str.equals("android.intent.action.USER_PRESENT")) {
            resolutionResultListener.onUnlock();
        } else if (str.equals("android.intent.action.SCREEN_OFF")) {
            resolutionResultListener.onLock();
        }
    }
}
