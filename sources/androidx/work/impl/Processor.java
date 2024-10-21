package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.work.Configuration;
import androidx.work.CoroutineWorker$getForegroundInfoAsync$1;
import androidx.work.Logger;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Processor implements ForegroundProcessor {
    private static final String TAG = Logger.tagWithPrefix("Processor");
    public final Context mAppContext;
    private final Configuration mConfiguration;
    public final WorkDatabase mWorkDatabase;
    private final WorkManagerTaskExecutor mWorkTaskExecutor$ar$class_merging;
    public final Map mEnqueuedWorkMap = new HashMap();
    public final Map mForegroundWorkMap = new HashMap();
    public final Set mCancelledIds = new HashSet();
    public final List mOuterListeners = new ArrayList();
    public PowerManager.WakeLock mForegroundLock = null;
    public final Object mLock = new Object();
    public final Map mWorkRuns = new HashMap();

    public Processor(Context context, Configuration configuration, WorkManagerTaskExecutor workManagerTaskExecutor, WorkDatabase workDatabase) {
        this.mAppContext = context;
        this.mConfiguration = configuration;
        this.mWorkTaskExecutor$ar$class_merging = workManagerTaskExecutor;
        this.mWorkDatabase = workDatabase;
    }

    public static boolean interrupt$ar$ds(WorkerWrapper workerWrapper, int i) {
        if (workerWrapper != null) {
            workerWrapper.workerJob$ar$class_merging.cancel(new WorkerStoppedException(i));
            Logger.get$ar$ds$16341a92_0();
            return true;
        }
        Logger.get$ar$ds$16341a92_0();
        return false;
    }

    private final void runOnExecuted$ar$ds(WorkGenerationalId workGenerationalId) {
        this.mWorkTaskExecutor$ar$class_merging.getMainThreadExecutor().execute(new DelayedWorkTracker.AnonymousClass1(this, workGenerationalId, 1, (byte[]) null));
    }

    public final void addExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.add(executionListener);
        }
    }

    public final WorkerWrapper cleanUpWorkerUnsafe(String str) {
        boolean z;
        WorkerWrapper workerWrapper = (WorkerWrapper) this.mForegroundWorkMap.remove(str);
        if (workerWrapper != null) {
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            workerWrapper = (WorkerWrapper) this.mEnqueuedWorkMap.remove(str);
        }
        this.mWorkRuns.remove(str);
        if (z) {
            synchronized (this.mLock) {
                if (this.mForegroundWorkMap.isEmpty()) {
                    Intent intent = new Intent(this.mAppContext, (Class<?>) SystemForegroundService.class);
                    intent.setAction("ACTION_STOP_FOREGROUND");
                    try {
                        this.mAppContext.startService(intent);
                    } catch (Throwable th) {
                        Logger.get$ar$ds$16341a92_0();
                        Log.e(TAG, "Unable to stop foreground service", th);
                    }
                    PowerManager.WakeLock wakeLock = this.mForegroundLock;
                    if (wakeLock != null) {
                        wakeLock.release();
                        this.mForegroundLock = null;
                    }
                }
            }
        }
        return workerWrapper;
    }

    public final WorkerWrapper getWorkerWrapperUnsafe(String str) {
        WorkerWrapper workerWrapper = (WorkerWrapper) this.mForegroundWorkMap.get(str);
        if (workerWrapper == null) {
            return (WorkerWrapper) this.mEnqueuedWorkMap.get(str);
        }
        return workerWrapper;
    }

    public final boolean isEnqueued(String str) {
        boolean z;
        synchronized (this.mLock) {
            if (getWorkerWrapperUnsafe(str) != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public final void removeExecutionListener(ExecutionListener executionListener) {
        synchronized (this.mLock) {
            this.mOuterListeners.remove(executionListener);
        }
    }

    public final boolean startWork$ar$class_merging$1716d22_0$ar$class_merging$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore, AppCompatTextClassifierHelper$Api26Impl appCompatTextClassifierHelper$Api26Impl) {
        final ArrayList arrayList = new ArrayList();
        Object obj = viewModelStore.ViewModelStore$ar$map;
        WorkGenerationalId workGenerationalId = (WorkGenerationalId) obj;
        final String str = workGenerationalId.workSpecId;
        WorkSpec workSpec = (WorkSpec) this.mWorkDatabase.runInTransaction(new Callable() { // from class: androidx.work.impl.Processor$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Processor processor = Processor.this;
                WorkTagDao workTagDao = processor.mWorkDatabase.workTagDao();
                String str2 = str;
                arrayList.addAll(workTagDao.getTagsForWorkSpecId(str2));
                return processor.mWorkDatabase.workSpecDao().getWorkSpec(str2);
            }
        });
        if (workSpec == null) {
            Logger.get$ar$ds$16341a92_0();
            String str2 = TAG;
            Objects.toString(obj);
            Log.w(str2, "Didn't find WorkSpec for id ".concat(obj.toString()));
            runOnExecuted$ar$ds(workGenerationalId);
            return false;
        }
        synchronized (this.mLock) {
            if (isEnqueued(str)) {
                Set set = (Set) this.mWorkRuns.get(str);
                if (((WorkGenerationalId) ((ViewModelStore) set.iterator().next()).ViewModelStore$ar$map).generation == ((WorkGenerationalId) obj).generation) {
                    set.add(viewModelStore);
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(obj);
                } else {
                    runOnExecuted$ar$ds((WorkGenerationalId) obj);
                }
            } else if (workSpec.generation != ((WorkGenerationalId) obj).generation) {
                runOnExecuted$ar$ds((WorkGenerationalId) obj);
            } else {
                DirectionNavigationActor directionNavigationActor = new DirectionNavigationActor(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor$ar$class_merging, this, this.mWorkDatabase, workSpec, arrayList);
                if (appCompatTextClassifierHelper$Api26Impl != null) {
                    directionNavigationActor.DirectionNavigationActor$ar$pipeline = appCompatTextClassifierHelper$Api26Impl;
                }
                WorkerWrapper workerWrapper = new WorkerWrapper(directionNavigationActor);
                ListenableFuture launchFuture$default$ar$ds = AnimatedVectorDrawableCompat.Api23Impl.launchFuture$default$ar$ds(OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) workerWrapper.workTaskExecutor$ar$class_merging.getTaskCoroutineDispatcher(), (CoroutineContext) ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds()), new CoroutineWorker$getForegroundInfoAsync$1(workerWrapper, (Continuation) null, 4, (byte[]) null));
                launchFuture$default$ar$ds.addListener(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(this, launchFuture$default$ar$ds, workerWrapper, 6, (char[]) null), this.mWorkTaskExecutor$ar$class_merging.getMainThreadExecutor());
                this.mEnqueuedWorkMap.put(str, workerWrapper);
                HashSet hashSet = new HashSet();
                hashSet.add(viewModelStore);
                this.mWorkRuns.put(str, hashSet);
                Logger.get$ar$ds$16341a92_0();
                getClass().getSimpleName();
                Objects.toString(obj);
                return true;
            }
            return false;
        }
    }
}
