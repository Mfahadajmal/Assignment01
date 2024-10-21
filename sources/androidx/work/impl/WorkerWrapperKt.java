package androidx.work.impl;

import androidx.navigation.Navigator$navigate$1;
import androidx.work.DirectExecutor;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkerWrapperKt {
    public static final String TAG = Logger.tagWithPrefix("WorkerWrapper");

    public static final Object awaitWithin(ListenableFuture listenableFuture, ListenableWorker listenableWorker, Continuation continuation) {
        try {
            if (listenableFuture.isDone()) {
                return getUninterruptibly(listenableFuture);
            }
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            listenableFuture.addListener(new ToContinuation((Object) listenableFuture, (Runnable) cancellableContinuationImpl, 0), DirectExecutor.INSTANCE);
            cancellableContinuationImpl.invokeOnCancellation(new Navigator$navigate$1(listenableWorker, listenableFuture, 2, null));
            Object result = cancellableContinuationImpl.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            return result;
        } catch (ExecutionException e) {
            throw nonNullCause(e);
        }
    }

    public static final Object getUninterruptibly(Future future) {
        Object obj;
        boolean z = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static final Throwable nonNullCause(ExecutionException executionException) {
        Throwable cause = executionException.getCause();
        cause.getClass();
        return cause;
    }
}
