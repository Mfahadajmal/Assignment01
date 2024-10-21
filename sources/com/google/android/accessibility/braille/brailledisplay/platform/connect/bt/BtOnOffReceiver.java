package com.google.android.accessibility.braille.brailledisplay.platform.connect.bt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BtOnOffReceiver extends ActionReceiver {
    public BtOnOffReceiver(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final String[] getActionsList() {
        return new String[]{"android.bluetooth.adapter.action.STATE_CHANGED"};
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.platform.lib.ActionReceiver
    protected final /* bridge */ /* synthetic */ void onReceive(Object obj, String str, Bundle bundle) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) obj;
        if (str.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
            int i = bundle.getInt("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (i == 10) {
                resolutionResultListener.onBluetoothTurnedOff();
                return;
            }
            if (i == 13) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onBluetoothTurningOff");
            } else if (i == 12) {
                resolutionResultListener.onBluetoothTurnedOn();
            } else if (i == 11) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.d("BtConnectManager", "onBluetoothTurningOn");
            }
        }
    }
}
