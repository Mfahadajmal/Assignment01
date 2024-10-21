package com.google.android.accessibility.utils;

import android.os.Handler;
import android.os.Message;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DelayHandler extends Handler {
    public final void delay(long j, Object obj) {
        sendMessageDelayed(obtainMessage(1, obj), j);
    }

    public abstract void handle(Object obj);

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message.what == 1) {
            handle(message.obj);
        }
    }

    public final void removeMessages() {
        removeMessages(1);
    }
}
