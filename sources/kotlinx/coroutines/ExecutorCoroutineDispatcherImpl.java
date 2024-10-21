package kotlinx.coroutines;

import androidx.work.impl.ToContinuation;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import com.google.mlkit.logging.schema.PointF2D;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.internal.ConcurrentKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorCoroutineDispatcherImpl extends ExecutorCoroutineDispatcher implements Delay {
    private final Executor executor;

    public ExecutorCoroutineDispatcherImpl(Executor executor) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;
        Method method;
        this.executor = executor;
        Method method2 = ConcurrentKt.REMOVE_FUTURE_ON_CANCEL;
        try {
            if (executor instanceof ScheduledThreadPoolExecutor) {
                scheduledThreadPoolExecutor = (ScheduledThreadPoolExecutor) executor;
            } else {
                scheduledThreadPoolExecutor = null;
            }
            if (scheduledThreadPoolExecutor != null && (method = ConcurrentKt.REMOVE_FUTURE_ON_CANCEL) != null) {
                method.invoke(scheduledThreadPoolExecutor, true);
            }
        } catch (Throwable unused) {
        }
    }

    private static final void cancelJobOnRejection$ar$ds(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        ScannerAutoZoomEvent.PredictedArea.cancel(coroutineContext, PointF2D.CancellationException("The task was rejected", rejectedExecutionException));
    }

    private final ScheduledFuture scheduleBlock(ScheduledExecutorService scheduledExecutorService, Runnable runnable, CoroutineContext coroutineContext, long j) {
        try {
            return scheduledExecutorService.schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection$ar$ds(coroutineContext, e);
            return null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        ExecutorService executorService;
        Executor executor = this.executor;
        if (executor instanceof ExecutorService) {
            executorService = (ExecutorService) executor;
        } else {
            executorService = null;
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            this.executor.execute(runnable);
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection$ar$ds(coroutineContext, e);
            Dispatchers.IO.dispatch(coroutineContext, runnable);
        }
    }

    public final boolean equals(Object obj) {
        if ((obj instanceof ExecutorCoroutineDispatcherImpl) && ((ExecutorCoroutineDispatcherImpl) obj).executor == this.executor) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return System.identityHashCode(this.executor);
    }

    @Override // kotlinx.coroutines.Delay
    public final DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        ScheduledExecutorService scheduledExecutorService;
        Executor executor = this.executor;
        ScheduledFuture scheduledFuture = null;
        if (executor instanceof ScheduledExecutorService) {
            scheduledExecutorService = (ScheduledExecutorService) executor;
        } else {
            scheduledExecutorService = null;
        }
        if (scheduledExecutorService != null) {
            scheduledFuture = scheduleBlock(scheduledExecutorService, runnable, coroutineContext, j);
        }
        if (scheduledFuture != null) {
            return new DisposableFutureHandle(scheduledFuture);
        }
        return DefaultExecutor.INSTANCE.scheduleInvokeOnTimeout(j, runnable);
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        ScheduledExecutorService scheduledExecutorService;
        Executor executor = this.executor;
        ScheduledFuture scheduledFuture = null;
        if (executor instanceof ScheduledExecutorService) {
            scheduledExecutorService = (ScheduledExecutorService) executor;
        } else {
            scheduledExecutorService = null;
        }
        if (scheduledExecutorService != null) {
            scheduledFuture = scheduleBlock(scheduledExecutorService, new ToContinuation((Object) this, (Object) cancellableContinuationImpl, 4), cancellableContinuationImpl.getContext(), j);
        }
        if (scheduledFuture != null) {
            OnDeviceTranslationLogEvent.invokeOnCancellation$ar$class_merging(cancellableContinuationImpl, new CancelHandler.UserSupplied(scheduledFuture, 1));
        } else {
            DefaultExecutor.INSTANCE.scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(j, cancellableContinuationImpl);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final String toString() {
        return this.executor.toString();
    }
}
