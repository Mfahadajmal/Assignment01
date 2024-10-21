package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.os.PowerManager;
import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsMet;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WorkTimer;
import java.util.Objects;
import java.util.concurrent.Executor;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DelayMetCommandHandler implements OnConstraintsStateChangedListener, WorkTimer.TimeLimitExceededListener {
    private static final String TAG = Logger.tagWithPrefix("DelayMetCommandHandler");
    public final Context mContext;
    public final CoroutineDispatcher mCoroutineDispatcher;
    public int mCurrentState;
    public final SystemAlarmDispatcher mDispatcher;
    public boolean mHasConstraints;
    public volatile Job mJob;
    private final Object mLock;
    public final Executor mMainThreadExecutor;
    public final Executor mSerialExecutor;
    public final int mStartId;
    public final ViewModelStore mToken$ar$class_merging$ar$class_merging;
    public PowerManager.WakeLock mWakeLock;
    public final ViewModelStore mWorkConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging;
    public final WorkGenerationalId mWorkGenerationalId;

    public DelayMetCommandHandler(Context context, int i, SystemAlarmDispatcher systemAlarmDispatcher, ViewModelStore viewModelStore) {
        this.mContext = context;
        this.mStartId = i;
        this.mDispatcher = systemAlarmDispatcher;
        this.mWorkGenerationalId = (WorkGenerationalId) viewModelStore.ViewModelStore$ar$map;
        this.mToken$ar$class_merging$ar$class_merging = viewModelStore;
        WorkQueue workQueue = systemAlarmDispatcher.mWorkManager.mTrackers$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.mSerialExecutor = systemAlarmDispatcher.mTaskExecutor$ar$class_merging.getSerialTaskExecutor$ar$class_merging();
        this.mMainThreadExecutor = systemAlarmDispatcher.mTaskExecutor$ar$class_merging.getMainThreadExecutor();
        this.mCoroutineDispatcher = systemAlarmDispatcher.mTaskExecutor$ar$class_merging.getTaskCoroutineDispatcher();
        this.mWorkConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging = new ViewModelStore(workQueue);
        this.mHasConstraints = false;
        this.mCurrentState = 0;
        this.mLock = new Object();
    }

    public final void cleanUp() {
        synchronized (this.mLock) {
            if (this.mJob != null) {
                this.mJob.cancel(null);
            }
            this.mDispatcher.mWorkTimer.stopTimer(this.mWorkGenerationalId);
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(this.mWakeLock);
                Objects.toString(this.mWorkGenerationalId);
                this.mWakeLock.release();
            }
        }
    }

    @Override // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public final void onConstraintsStateChanged$ar$class_merging(WorkSpec workSpec, SystemJobService.Api24Impl api24Impl) {
        if (api24Impl instanceof ConstraintsState$ConstraintsMet) {
            this.mSerialExecutor.execute(new WorkerKt$$ExternalSyntheticLambda0(this, 3));
        } else {
            this.mSerialExecutor.execute(new WorkerKt$$ExternalSyntheticLambda0(this, 2));
        }
    }

    @Override // androidx.work.impl.utils.WorkTimer.TimeLimitExceededListener
    public final void onTimeLimitExceeded(WorkGenerationalId workGenerationalId) {
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(workGenerationalId);
        workGenerationalId.toString();
        this.mSerialExecutor.execute(new WorkerKt$$ExternalSyntheticLambda0(this, 2));
    }
}
