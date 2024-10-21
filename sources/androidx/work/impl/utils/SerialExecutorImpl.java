package androidx.work.impl.utils;

import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3;
import androidx.work.impl.ToContinuation;
import java.util.ArrayDeque;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerialExecutorImpl implements Executor {
    private Runnable mActive;
    private final Executor mExecutor;
    private final /* synthetic */ int switching_field;
    public final ArrayDeque mTasks = new ArrayDeque();
    public final Object mLock = new Object();

    public SerialExecutorImpl(Executor executor, int i, byte[] bArr) {
        this.switching_field = i;
        this.mExecutor = executor;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.switching_field != 0) {
            runnable.getClass();
            synchronized (this.mLock) {
                this.mTasks.offer(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda3(runnable, this, 20));
                if (this.mActive == null) {
                    scheduleNext();
                }
            }
            return;
        }
        synchronized (this.mLock) {
            this.mTasks.add(new ToContinuation((Object) this, runnable, 2));
            if (this.mActive == null) {
                scheduleNext();
            }
        }
    }

    public final void scheduleNext() {
        if (this.switching_field != 0) {
            synchronized (this.mLock) {
                Object poll = this.mTasks.poll();
                Runnable runnable = (Runnable) poll;
                this.mActive = runnable;
                if (poll != null) {
                    this.mExecutor.execute(runnable);
                }
            }
            return;
        }
        Runnable runnable2 = (Runnable) this.mTasks.poll();
        this.mActive = runnable2;
        if (runnable2 != null) {
            this.mExecutor.execute(runnable2);
        }
    }

    public SerialExecutorImpl(Executor executor, int i) {
        this.switching_field = i;
        this.mExecutor = executor;
    }
}
