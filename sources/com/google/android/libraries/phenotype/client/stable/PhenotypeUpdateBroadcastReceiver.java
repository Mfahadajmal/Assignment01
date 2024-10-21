package com.google.android.libraries.phenotype.client.stable;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.base.Pair;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PhenotypeUpdateBroadcastReceiver extends BroadcastReceiver {
    public static final Object LOCK = new Object();
    static final ConcurrentMap packageAndAccountCallbacks = new ConcurrentHashMap();
    public static volatile boolean registered;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl;
        String stringExtra = intent.getStringExtra("com.google.android.gms.phenotype.PACKAGE_NAME");
        if (stringExtra != null) {
            if (!stringExtra.contains("../") && !stringExtra.contains("/..")) {
                ArrayList arrayList = new ArrayList(packageAndAccountCallbacks.keySet());
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    Pair pair = (Pair) arrayList.get(i);
                    if (((String) pair.first).equals(stringExtra) && (shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) packageAndAccountCallbacks.get(pair)) != null) {
                        shadowDelegateImpl.onUpdateReceived$ar$ds();
                    }
                }
                return;
            }
            Log.w("PhUpdateBroadcastRecv", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(stringExtra, "Got an invalid config package for P/H that includes '..': ", ". Exiting."));
        }
    }
}
