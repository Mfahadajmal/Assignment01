package com.google.android.gms.libs.punchclock.threads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TracingHandler extends Handler {
    public TracingHandler() {
        Looper.getMainLooper();
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        super.dispatchMessage(message);
    }

    public TracingHandler(Looper looper) {
        super(looper);
        Looper.getMainLooper();
    }

    public TracingHandler(Looper looper, Handler.Callback callback) {
        super(looper, callback);
        Looper.getMainLooper();
    }
}
