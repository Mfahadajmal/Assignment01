package com.google.android.accessibility.braille.brailledisplay.platform.lib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.core.view.inputmethod.EditorInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ActionReceiver extends BroadcastReceiver {
    private final Object callback;
    public final Context context;
    private boolean registered;

    public ActionReceiver(Context context, Object obj) {
        this.context = context;
        this.callback = obj;
    }

    protected abstract String[] getActionsList();

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        onReceive(this.callback, intent.getAction(), intent.getExtras());
    }

    protected abstract void onReceive(Object obj, String str, Bundle bundle);

    public final void registerSelf$ar$ds() {
        if (!this.registered) {
            IntentFilter intentFilter = new IntentFilter();
            for (String str : getActionsList()) {
                intentFilter.addAction(str);
            }
            EditorInfoCompat.registerReceiver$ar$ds(this.context, this, intentFilter, 2);
            this.registered = true;
        }
    }

    public final void unregisterSelf() {
        if (this.registered) {
            this.context.unregisterReceiver(this);
            this.registered = false;
        }
    }
}
