package com.google.android.accessibility.utils.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.google.android.accessibility.utils.WeakReferenceHandler;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SameThreadBroadcastReceiver extends BroadcastReceiver {
    private final BroadcastReceiverHandler handler = new BroadcastReceiverHandler(this);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BroadcastReceiverHandler extends WeakReferenceHandler {
        public BroadcastReceiverHandler(SameThreadBroadcastReceiver sameThreadBroadcastReceiver) {
            super(sameThreadBroadcastReceiver, Looper.myLooper());
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            ((SameThreadBroadcastReceiver) obj).onReceiveIntent((Intent) message.getData().getParcelable("intent"));
        }
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("intent", intent);
        Message message = new Message();
        message.setData(bundle);
        this.handler.sendMessage(message);
    }

    protected abstract void onReceiveIntent(Intent intent);
}
