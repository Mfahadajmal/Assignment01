package androidx.work;

import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkerParameters {
    public final Executor mBackgroundExecutor;
    public final ForegroundUpdater mForegroundUpdater;
    public final UUID mId;
    public final Data mInputData;
    public final WorkManagerTaskExecutor mWorkTaskExecutor$ar$class_merging;
    public final CoroutineContext mWorkerContext;
    public final WorkerFactory mWorkerFactory;

    public WorkerParameters(UUID uuid, Data data, Collection collection, Executor executor, CoroutineContext coroutineContext, WorkManagerTaskExecutor workManagerTaskExecutor, WorkerFactory workerFactory, ForegroundUpdater foregroundUpdater) {
        this.mId = uuid;
        this.mInputData = data;
        new HashSet(collection);
        this.mBackgroundExecutor = executor;
        this.mWorkerContext = coroutineContext;
        this.mWorkTaskExecutor$ar$class_merging = workManagerTaskExecutor;
        this.mWorkerFactory = workerFactory;
        this.mForegroundUpdater = foregroundUpdater;
    }
}
