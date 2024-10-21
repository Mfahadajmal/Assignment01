package com.google.android.gms.common.util.concurrent;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HandlerExecutor implements Executor {
    private final Handler handler;
    private final /* synthetic */ int switching_field;

    public HandlerExecutor(int i) {
        this.switching_field = i;
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.handler.post(runnable);
                return;
            } else {
                this.handler.post(runnable);
                return;
            }
        }
        this.handler.post(runnable);
    }

    public HandlerExecutor(Looper looper, int i) {
        this.switching_field = i;
        this.handler = new TracingHandler(looper);
    }

    public HandlerExecutor(int i, byte[] bArr) {
        this.switching_field = i;
        this.handler = new TracingHandler(Looper.getMainLooper());
    }
}
