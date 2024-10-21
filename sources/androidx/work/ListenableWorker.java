package androidx.work;

import android.content.Context;
import android.support.v7.widget.DropDownListView;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$Resolver;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ListenableWorker {
    public Context mAppContext;
    public final AtomicInteger mStopReason = new AtomicInteger(-256);
    public boolean mUsed;
    public WorkerParameters mWorkerParams;

    public ListenableWorker(Context context, WorkerParameters workerParameters) {
        if (context != null) {
            if (workerParameters != null) {
                this.mAppContext = context;
                this.mWorkerParams = workerParameters;
                return;
            }
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        throw new IllegalArgumentException("Application Context is null");
    }

    public final Executor getBackgroundExecutor() {
        return this.mWorkerParams.mBackgroundExecutor;
    }

    public ListenableFuture getForegroundInfoAsync() {
        return DropDownListView.Api33Impl.getFuture(new CallbackToFutureAdapter$Resolver() { // from class: androidx.work.ListenableWorker$$ExternalSyntheticLambda0
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
            public final Object attachCompleter(CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
                callbackToFutureAdapter$Completer.setException$ar$ds(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for`getForegroundInfoAsync()`"));
                return "default failing getForegroundInfoAsync";
            }
        });
    }

    public final UUID getId() {
        return this.mWorkerParams.mId;
    }

    public final Data getInputData() {
        return this.mWorkerParams.mInputData;
    }

    public final boolean isStopped() {
        if (this.mStopReason.get() != -256) {
            return true;
        }
        return false;
    }

    public abstract ListenableFuture startWork();

    public final void stop(int i) {
        this.mStopReason.compareAndSet(-256, i);
    }
}
