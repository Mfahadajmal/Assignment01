package kotlinx.coroutines.internal;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlin.coroutines.CoroutineContext;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import org.chromium.net.impl.CronetUrlRequest;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LimitedDispatcher extends CoroutineDispatcher implements Delay {
    public final CoroutineDispatcher dispatcher;
    private final int parallelism;
    private final /* synthetic */ Delay $$delegate_0 = DefaultExecutorKt.DefaultDelay;
    private final AtomicInt runningWorkers = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
    private final ExecutorSelector queue$ar$class_merging$ar$class_merging = new ExecutorSelector((byte[]) null);
    private final Object workerAllocationLock = new Object();

    public LimitedDispatcher(CoroutineDispatcher coroutineDispatcher, int i) {
        this.dispatcher = coroutineDispatcher;
        this.parallelism = i;
    }

    private final boolean tryAllocateWorker() {
        synchronized (this.workerAllocationLock) {
            if (this.runningWorkers.value >= this.parallelism) {
                return false;
            }
            this.runningWorkers.incrementAndGet();
            return true;
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable obtainTaskOrDeallocateWorker;
        this.queue$ar$class_merging$ar$class_merging.addLast(runnable);
        if (this.runningWorkers.value < this.parallelism && tryAllocateWorker() && (obtainTaskOrDeallocateWorker = obtainTaskOrDeallocateWorker()) != null) {
            this.dispatcher.dispatch(this, new CronetUrlRequest.OnReadCompletedRunnable(this, obtainTaskOrDeallocateWorker, 1));
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        Runnable obtainTaskOrDeallocateWorker;
        this.queue$ar$class_merging$ar$class_merging.addLast(runnable);
        if (this.runningWorkers.value < this.parallelism && tryAllocateWorker() && (obtainTaskOrDeallocateWorker = obtainTaskOrDeallocateWorker()) != null) {
            this.dispatcher.dispatchYield(this, new CronetUrlRequest.OnReadCompletedRunnable(this, obtainTaskOrDeallocateWorker, 1));
        }
    }

    @Override // kotlinx.coroutines.Delay
    public final DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return this.$$delegate_0.invokeOnTimeout(j, runnable, coroutineContext);
    }

    public final Runnable obtainTaskOrDeallocateWorker() {
        while (true) {
            Runnable runnable = (Runnable) this.queue$ar$class_merging$ar$class_merging.removeFirstOrNull();
            if (runnable == null) {
                synchronized (this.workerAllocationLock) {
                    this.runningWorkers.decrementAndGet();
                    if (this.queue$ar$class_merging$ar$class_merging.getSize() == 0) {
                        return null;
                    }
                    this.runningWorkers.incrementAndGet();
                }
            } else {
                return runnable;
            }
        }
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        this.$$delegate_0.scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(j, cancellableContinuationImpl);
    }
}
