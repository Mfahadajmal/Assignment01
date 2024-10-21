package androidx.work.impl.background.greedy;

import android.content.Context;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatTextHelper;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelStore;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.Scheduler;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsMet;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsNotMet;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GreedyScheduler implements Scheduler, OnConstraintsStateChangedListener, ExecutionListener {
    private static final String TAG = Logger.tagWithPrefix("GreedyScheduler");
    private final Configuration mConfiguration;
    private final ViewModelStore mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context mContext;
    private final DelayedWorkTracker mDelayedWorkTracker;
    Boolean mInDefaultProcess;
    private final Processor mProcessor;
    private boolean mRegisteredExecutionListener;
    private final WorkManagerTaskExecutor mTaskExecutor$ar$class_merging;
    private final TimeLimiter mTimeLimiter;
    private final WorkName mWorkLauncher$ar$class_merging$ar$class_merging;
    private final Map mConstrainedWorkSpecs = new HashMap();
    private final Object mLock = new Object();
    private final WorkName mStartStopTokens$ar$class_merging$ar$class_merging = new WorkName((byte[]) null);
    private final Map mFirstRunAttempts = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class AttemptData {
        final int mRunAttemptCount;
        final long mTimeStamp;

        public AttemptData(int i, long j) {
            this.mRunAttemptCount = i;
            this.mTimeStamp = j;
        }
    }

    public GreedyScheduler(Context context, Configuration configuration, WorkQueue workQueue, Processor processor, WorkName workName, WorkManagerTaskExecutor workManagerTaskExecutor) {
        this.mContext = context;
        ViewModelStore viewModelStore = configuration.runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.mDelayedWorkTracker = new DelayedWorkTracker(this, viewModelStore, configuration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging);
        this.mTimeLimiter = new TimeLimiter(viewModelStore, workName);
        this.mTaskExecutor$ar$class_merging = workManagerTaskExecutor;
        this.mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging = new ViewModelStore(workQueue);
        this.mConfiguration = configuration;
        this.mProcessor = processor;
        this.mWorkLauncher$ar$class_merging$ar$class_merging = workName;
    }

    private final void checkDefaultProcess() {
        this.mInDefaultProcess = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.mContext, this.mConfiguration));
    }

    private final void registerExecutionListenerIfNeeded() {
        if (!this.mRegisteredExecutionListener) {
            this.mProcessor.addExecutionListener(this);
            this.mRegisteredExecutionListener = true;
        }
    }

    @Override // androidx.work.impl.Scheduler
    public final void cancel(String str) {
        Runnable runnable;
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get$ar$ds$16341a92_0();
            return;
        }
        registerExecutionListenerIfNeeded();
        Logger.get$ar$ds$16341a92_0();
        DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
        if (delayedWorkTracker != null && (runnable = (Runnable) delayedWorkTracker.mRunnables.remove(str)) != null) {
            delayedWorkTracker.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.cancel(runnable);
        }
        for (ViewModelStore viewModelStore : this.mStartStopTokens$ar$class_merging$ar$class_merging.remove(str)) {
            this.mTimeLimiter.cancel$ar$class_merging$ar$class_merging(viewModelStore);
            this.mWorkLauncher$ar$class_merging$ar$class_merging.stopWork$ar$class_merging$ar$class_merging(viewModelStore);
        }
    }

    @Override // androidx.work.impl.Scheduler
    public final boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public final void onConstraintsStateChanged$ar$class_merging(WorkSpec workSpec, SystemJobService.Api24Impl api24Impl) {
        boolean z = api24Impl instanceof ConstraintsState$ConstraintsMet;
        WorkGenerationalId generationalId = AppCompatTextHelper.Api24Impl.generationalId(workSpec);
        if (z) {
            if (!this.mStartStopTokens$ar$class_merging$ar$class_merging.contains(generationalId)) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(generationalId);
                generationalId.toString();
                ViewModelStore viewModelStore = this.mStartStopTokens$ar$class_merging$ar$class_merging.tokenFor$ar$class_merging$ar$class_merging(generationalId);
                this.mTimeLimiter.track$ar$class_merging$ar$class_merging(viewModelStore);
                this.mWorkLauncher$ar$class_merging$ar$class_merging.startWork$ar$class_merging$d9075868_0$ar$class_merging(viewModelStore);
                return;
            }
            return;
        }
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(generationalId);
        generationalId.toString();
        ViewModelStore remove$ar$class_merging$ar$class_merging = this.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(generationalId);
        if (remove$ar$class_merging$ar$class_merging != null) {
            this.mTimeLimiter.cancel$ar$class_merging$ar$class_merging(remove$ar$class_merging$ar$class_merging);
            this.mWorkLauncher$ar$class_merging$ar$class_merging.stopWorkWithReason$ar$class_merging$ar$class_merging(remove$ar$class_merging$ar$class_merging, ((ConstraintsState$ConstraintsNotMet) api24Impl).reason);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        Job job;
        ViewModelStore remove$ar$class_merging$ar$class_merging = this.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(workGenerationalId);
        if (remove$ar$class_merging$ar$class_merging != null) {
            this.mTimeLimiter.cancel$ar$class_merging$ar$class_merging(remove$ar$class_merging$ar$class_merging);
        }
        synchronized (this.mLock) {
            job = (Job) this.mConstrainedWorkSpecs.remove(workGenerationalId);
        }
        if (job != null) {
            Logger.get$ar$ds$16341a92_0();
            Objects.toString(workGenerationalId);
            job.cancel(null);
        }
        if (!z) {
            synchronized (this.mLock) {
                this.mFirstRunAttempts.remove(workGenerationalId);
            }
        }
    }

    @Override // androidx.work.impl.Scheduler
    public final void schedule(WorkSpec... workSpecArr) {
        long max;
        long currentTimeMillis;
        long currentTimeMillis2;
        long currentTimeMillis3;
        if (this.mInDefaultProcess == null) {
            checkDefaultProcess();
        }
        if (!this.mInDefaultProcess.booleanValue()) {
            Logger.get$ar$ds$16341a92_0();
            return;
        }
        registerExecutionListenerIfNeeded();
        HashSet<WorkSpec> hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (WorkSpec workSpec : workSpecArr) {
            if (!this.mStartStopTokens$ar$class_merging$ar$class_merging.contains(AppCompatTextHelper.Api24Impl.generationalId(workSpec))) {
                synchronized (this.mLock) {
                    WorkGenerationalId generationalId = AppCompatTextHelper.Api24Impl.generationalId(workSpec);
                    AttemptData attemptData = (AttemptData) this.mFirstRunAttempts.get(generationalId);
                    if (attemptData == null) {
                        int i = workSpec.runAttemptCount;
                        WindowCallbackWrapper.Api26Impl api26Impl = this.mConfiguration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
                        currentTimeMillis3 = System.currentTimeMillis();
                        attemptData = new AttemptData(i, currentTimeMillis3);
                        this.mFirstRunAttempts.put(generationalId, attemptData);
                    }
                    max = attemptData.mTimeStamp + (Math.max((workSpec.runAttemptCount - attemptData.mRunAttemptCount) - 5, 0) * 30000);
                }
                long max2 = Math.max(workSpec.calculateNextRunTime(), max);
                WindowCallbackWrapper.Api26Impl api26Impl2 = this.mConfiguration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
                currentTimeMillis = System.currentTimeMillis();
                if (workSpec.state$ar$edu == 1) {
                    if (currentTimeMillis < max2) {
                        DelayedWorkTracker delayedWorkTracker = this.mDelayedWorkTracker;
                        if (delayedWorkTracker != null) {
                            Runnable runnable = (Runnable) delayedWorkTracker.mRunnables.remove(workSpec.id);
                            if (runnable != null) {
                                delayedWorkTracker.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.cancel(runnable);
                            }
                            DelayedWorkTracker.AnonymousClass1 anonymousClass1 = new DelayedWorkTracker.AnonymousClass1(delayedWorkTracker, workSpec, 0);
                            delayedWorkTracker.mRunnables.put(workSpec.id, anonymousClass1);
                            currentTimeMillis2 = System.currentTimeMillis();
                            delayedWorkTracker.mRunnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.scheduleWithDelay(max2 - currentTimeMillis2, anonymousClass1);
                        }
                    } else if (workSpec.hasConstraints()) {
                        Constraints constraints = workSpec.constraints;
                        if (constraints.requiresDeviceIdle) {
                            Logger.get$ar$ds$16341a92_0();
                            Objects.toString(workSpec);
                        } else if (constraints.hasContentUriTriggers()) {
                            Logger.get$ar$ds$16341a92_0();
                            Objects.toString(workSpec);
                        } else {
                            hashSet.add(workSpec);
                            hashSet2.add(workSpec.id);
                        }
                    } else if (!this.mStartStopTokens$ar$class_merging$ar$class_merging.contains(AppCompatTextHelper.Api24Impl.generationalId(workSpec))) {
                        Logger.get$ar$ds$16341a92_0();
                        String str = workSpec.id;
                        WorkName workName = this.mStartStopTokens$ar$class_merging$ar$class_merging;
                        workSpec.getClass();
                        ViewModelStore viewModelStore = workName.tokenFor$ar$class_merging$ar$class_merging(AppCompatTextHelper.Api24Impl.generationalId(workSpec));
                        this.mTimeLimiter.track$ar$class_merging$ar$class_merging(viewModelStore);
                        this.mWorkLauncher$ar$class_merging$ar$class_merging.startWork$ar$class_merging$d9075868_0$ar$class_merging(viewModelStore);
                    }
                }
            }
        }
        synchronized (this.mLock) {
            if (!hashSet.isEmpty()) {
                TextUtils.join(",", hashSet2);
                Logger.get$ar$ds$16341a92_0();
                for (WorkSpec workSpec2 : hashSet) {
                    WorkGenerationalId generationalId2 = AppCompatTextHelper.Api24Impl.generationalId(workSpec2);
                    if (!this.mConstrainedWorkSpecs.containsKey(generationalId2)) {
                        this.mConstrainedWorkSpecs.put(generationalId2, WorkConstraintsTrackerKt.listen$ar$class_merging$ar$class_merging$ar$class_merging(this.mConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging, workSpec2, this.mTaskExecutor$ar$class_merging.getTaskCoroutineDispatcher(), this));
                    }
                }
            }
        }
    }
}
