package org.chromium.net.impl;

import android.os.Handler;
import java.util.concurrent.Executor;
import org.chromium.net.InlineExecutionProhibitedException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class JavaUrlRequestUtils$DirectPreventingExecutor implements Executor {
    private final Object JavaUrlRequestUtils$DirectPreventingExecutor$ar$mDelegate;
    private final /* synthetic */ int switching_field;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class InlineCheckingRunnable implements Runnable {
        public Thread mCallingThread;
        private final Runnable mCommand;
        public InlineExecutionProhibitedException mExecutedInline;

        public InlineCheckingRunnable(Runnable runnable, Thread thread) {
            this.mCommand = runnable;
            this.mCallingThread = thread;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (Thread.currentThread() == this.mCallingThread) {
                this.mExecutedInline = new InlineExecutionProhibitedException();
            } else {
                this.mCommand.run();
            }
        }
    }

    public JavaUrlRequestUtils$DirectPreventingExecutor(Object obj, int i) {
        this.switching_field = i;
        this.JavaUrlRequestUtils$DirectPreventingExecutor$ar$mDelegate = obj;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.switching_field != 0) {
            ((Handler) this.JavaUrlRequestUtils$DirectPreventingExecutor$ar$mDelegate).post(runnable);
            return;
        }
        InlineCheckingRunnable inlineCheckingRunnable = new InlineCheckingRunnable(runnable, Thread.currentThread());
        this.JavaUrlRequestUtils$DirectPreventingExecutor$ar$mDelegate.execute(inlineCheckingRunnable);
        InlineExecutionProhibitedException inlineExecutionProhibitedException = inlineCheckingRunnable.mExecutedInline;
        if (inlineExecutionProhibitedException == null) {
            inlineCheckingRunnable.mCallingThread = null;
            return;
        }
        throw inlineExecutionProhibitedException;
    }
}
