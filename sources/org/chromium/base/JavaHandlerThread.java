package org.chromium.base;

import J.N;
import android.os.Handler;
import android.os.HandlerThread;
import io.grpc.internal.Http2Ping;
import io.grpc.internal.ManagedChannelImpl;
import java.lang.Thread;

/* compiled from: PG */
/* loaded from: classes.dex */
public class JavaHandlerThread {
    public final HandlerThread mThread;
    public Throwable mUnhandledException;

    public JavaHandlerThread(String str, int i) {
        this.mThread = new HandlerThread(str, i);
    }

    private static JavaHandlerThread create(String str, int i) {
        return new JavaHandlerThread(str, i);
    }

    private Throwable getUncaughtExceptionIfAny() {
        return this.mUnhandledException;
    }

    private boolean isAlive() {
        return this.mThread.isAlive();
    }

    private void joinThread() {
        boolean z = false;
        while (!z) {
            try {
                this.mThread.join();
                z = true;
            } catch (InterruptedException unused) {
            }
        }
    }

    private void listenForUncaughtExceptionsForTesting() {
        this.mThread.setUncaughtExceptionHandler(new ManagedChannelImpl.AnonymousClass3(this, 2));
    }

    private void quitThreadSafely(long j) {
        new Handler(this.mThread.getLooper()).post(new Http2Ping.AnonymousClass1(this, j, 2));
        this.mThread.getLooper().quitSafely();
    }

    private void startAndInitialize(final long j, final long j2) {
        if (this.mThread.getState() == Thread.State.NEW) {
            this.mThread.start();
        }
        new Handler(this.mThread.getLooper()).post(new Runnable() { // from class: org.chromium.base.JavaHandlerThread.1
            @Override // java.lang.Runnable
            public final void run() {
                N.MJcct7gJ(j, j2);
            }
        });
    }
}
