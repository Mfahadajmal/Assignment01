package androidx.work.impl;

import _COROUTINE._BOUNDARY;
import android.support.v7.widget.GridLayoutManager;
import androidx.work.Logger;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.SerialExecutorImpl;
import androidx.work.impl.utils.WorkTimer;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.concurrent.ExecutionException;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToContinuation implements Runnable {
    private final Object ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0;
    private final Object ToContinuation$ar$futureToObserve;
    private final /* synthetic */ int switching_field;

    public ToContinuation(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0 = obj;
        this.ToContinuation$ar$futureToObserve = obj2;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v21, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.coroutines.Continuation, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v4, types: [kotlin.coroutines.Continuation, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.concurrent.Future, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        ((CancellableContinuationImpl) this.ToContinuation$ar$futureToObserve).resumeUndispatched((CoroutineDispatcher) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0, Unit.INSTANCE);
                        return;
                    }
                    synchronized (((WorkTimer) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).mLock) {
                        if (((ToContinuation) ((WorkTimer) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).mTimerMap.remove(this.ToContinuation$ar$futureToObserve)) != null) {
                            WorkTimer.TimeLimitExceededListener timeLimitExceededListener = (WorkTimer.TimeLimitExceededListener) ((WorkTimer) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).mListeners.remove(this.ToContinuation$ar$futureToObserve);
                            if (timeLimitExceededListener != null) {
                                timeLimitExceededListener.onTimeLimitExceeded((WorkGenerationalId) this.ToContinuation$ar$futureToObserve);
                            }
                        } else {
                            Logger.get$ar$ds$16341a92_0();
                            String.format("Timer with %s is already marked as complete.", this.ToContinuation$ar$futureToObserve);
                        }
                    }
                    return;
                }
                try {
                    this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0.run();
                    synchronized (((SerialExecutorImpl) this.ToContinuation$ar$futureToObserve).mLock) {
                        ((SerialExecutorImpl) this.ToContinuation$ar$futureToObserve).scheduleNext();
                    }
                    return;
                } catch (Throwable th) {
                    synchronized (((SerialExecutorImpl) this.ToContinuation$ar$futureToObserve).mLock) {
                        ((SerialExecutorImpl) this.ToContinuation$ar$futureToObserve).scheduleNext();
                        throw th;
                    }
                }
            }
            if (this.ToContinuation$ar$futureToObserve.isCancelled()) {
                ((CancellableContinuationImpl) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).cancel(null);
                return;
            }
            try {
                ((CancellableContinuationImpl) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).resumeWith(_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_14(this.ToContinuation$ar$futureToObserve));
                return;
            } catch (ExecutionException e) {
                this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(GridLayoutManager.Api21Impl.nonNullCause(e)));
                return;
            }
        }
        if (this.ToContinuation$ar$futureToObserve.isCancelled()) {
            ((CancellableContinuationImpl) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).cancel(null);
            return;
        }
        try {
            ((CancellableContinuationImpl) this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0).resumeWith(WorkerWrapperKt.getUninterruptibly(this.ToContinuation$ar$futureToObserve));
        } catch (ExecutionException e2) {
            this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(WorkerWrapperKt.nonNullCause(e2)));
        }
    }

    public ToContinuation(Object obj, Runnable runnable, int i) {
        this.switching_field = i;
        this.ToContinuation$ar$futureToObserve = obj;
        this.ToContinuation$ar$continuation$ar$class_merging$ea1f27b_0 = runnable;
    }
}
