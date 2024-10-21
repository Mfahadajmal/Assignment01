package androidx.work.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Trace;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.WindowCallbackWrapper;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import androidx.lifecycle.ViewModelStore;
import androidx.room.RoomSQLiteQuery;
import androidx.room.coroutines.FlowUtil$createFlow$1;
import androidx.transition.ViewUtilsApi19;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkManager;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.SerialExecutorImpl;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService$$ExternalSyntheticLambda10;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$scheduleAutoCollapse$1;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.AbstractFlow;
import kotlinx.coroutines.flow.FlowKt__DistinctKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.internal.ChannelFlowOperator;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkManagerImpl extends WorkManager {
    private static WorkManagerImpl sDefaultInstance;
    private static WorkManagerImpl sDelegatedInstance;
    public static final Object sLock;
    public Configuration mConfiguration;
    public Context mContext;
    public boolean mForceStopRunnableCompleted = false;
    public ViewModelStore mPreferenceUtils$ar$class_merging$ar$class_merging;
    public Processor mProcessor;
    public BroadcastReceiver.PendingResult mRescheduleReceiverResult;
    public List mSchedulers;
    public final WorkQueue mTrackers$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public WorkDatabase mWorkDatabase;
    private final CoroutineScope mWorkManagerScope;
    public WorkManagerTaskExecutor mWorkTaskExecutor$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api24Impl {
        public static void $default$stopWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(WorkName workName, ViewModelStore viewModelStore) {
            viewModelStore.getClass();
            workName.stopWorkWithReason$ar$class_merging$ar$class_merging(viewModelStore, -512);
        }

        static boolean isDeviceProtectedStorage(Context context) {
            boolean isDeviceProtectedStorage;
            isDeviceProtectedStorage = context.isDeviceProtectedStorage();
            return isDeviceProtectedStorage;
        }
    }

    static {
        Logger.tagWithPrefix("WorkManagerImpl");
        sDelegatedInstance = null;
        sDefaultInstance = null;
        sLock = new Object();
    }

    public WorkManagerImpl(Context context, final Configuration configuration, WorkManagerTaskExecutor workManagerTaskExecutor, WorkDatabase workDatabase, final List list, Processor processor, WorkQueue workQueue) {
        Context applicationContext = context.getApplicationContext();
        if (!Api24Impl.isDeviceProtectedStorage(applicationContext)) {
            Logger logger = new Logger(4);
            synchronized (Logger.sLock) {
                if (Logger.sLogger == null) {
                    Logger.sLogger = logger;
                }
            }
            this.mContext = applicationContext;
            this.mWorkTaskExecutor$ar$class_merging = workManagerTaskExecutor;
            this.mWorkDatabase = workDatabase;
            this.mProcessor = processor;
            this.mTrackers$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = workQueue;
            this.mConfiguration = configuration;
            this.mSchedulers = list;
            workManagerTaskExecutor.getClass();
            CoroutineDispatcher taskCoroutineDispatcher = workManagerTaskExecutor.getTaskCoroutineDispatcher();
            taskCoroutineDispatcher.getClass();
            CoroutineScope CoroutineScope = OtherError.CoroutineScope(taskCoroutineDispatcher);
            this.mWorkManagerScope = CoroutineScope;
            final WorkDatabase workDatabase2 = this.mWorkDatabase;
            this.mPreferenceUtils$ar$class_merging$ar$class_merging = new ViewModelStore((Object) workDatabase2, (byte[]) null);
            Processor processor2 = this.mProcessor;
            final SerialExecutorImpl serialTaskExecutor$ar$class_merging = workManagerTaskExecutor.getSerialTaskExecutor$ar$class_merging();
            processor2.addExecutionListener(new ExecutionListener() { // from class: androidx.work.impl.Schedulers$$ExternalSyntheticLambda0
                @Override // androidx.work.impl.ExecutionListener
                public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
                    serialTaskExecutor$ar$class_merging.execute(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(list, workGenerationalId, configuration, workDatabase2, 2));
                }
            });
            this.mWorkTaskExecutor$ar$class_merging.executeOnTaskThread(new ForceStopRunnable(applicationContext, this));
            Context context2 = this.mContext;
            String str = UnfinishedWorkListenerKt.TAG;
            context2.getClass();
            if (ProcessUtils.isDefaultProcess(context2, configuration)) {
                WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao;
                FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new AbstractFlow(new FlowUtil$createFlow$1(workSpecDao_Impl.__db, new String[]{"workspec"}, new OnBackPressedDispatcher.AnonymousClass1(new WorkSpecDao_Impl.AnonymousClass25(RoomSQLiteQuery.acquire("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1", 0)), 12), null)), new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$1(null), 1);
                if (BufferOverflow.SUSPEND$ar$edu == BufferOverflow.SUSPEND$ar$edu) {
                    OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(CoroutineScope, null, 0, new UIDelayedJobScheduler$scheduleAutoCollapse$1(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt__DistinctKt.distinctUntilChanged(new ChannelFlowOperator(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, 0, BufferOverflow.DROP_OLDEST$ar$edu)), new UnfinishedWorkListenerKt$maybeLaunchUnfinishedWorkListener$2(context2, null), 0), (Continuation) null, 3), 3);
                    return;
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
            return;
        }
        throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static WorkManagerImpl getInstance(Context context) {
        WorkManagerImpl workManagerImpl;
        Object obj = sLock;
        synchronized (obj) {
            synchronized (obj) {
                workManagerImpl = sDelegatedInstance;
                if (workManagerImpl == null) {
                    workManagerImpl = sDefaultInstance;
                }
            }
            return workManagerImpl;
        }
        if (workManagerImpl == null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext instanceof Configuration.Provider) {
                initialize(applicationContext, ((Configuration.Provider) applicationContext).getWorkManagerConfiguration());
                workManagerImpl = getInstance(applicationContext);
            } else {
                throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
            }
        }
        return workManagerImpl;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0016, code lost:
    
        r12 = r12.getApplicationContext();
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        if (androidx.work.impl.WorkManagerImpl.sDefaultInstance != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001e, code lost:
    
        r12.getClass();
        r13.getClass();
        r9 = new androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor(r13.taskExecutor);
        r1 = r12.getApplicationContext();
        r1.getClass();
        r2 = r9.WorkManagerTaskExecutor$ar$mBackgroundExecutor;
        r2.getClass();
        r3 = r13.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
    
        if (r12.getResources().getBoolean(com.google.android.marvin.talkback.R.bool.workmanager_test_configuration) == false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0047, code lost:
    
        r6 = new androidx.room.RoomDatabase.Builder(r1, androidx.work.impl.WorkDatabase.class, null);
        r6.allowMainThreadQueries = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0069, code lost:
    
        r6.queryExecutor = r2;
        r6.callbacks.add(new androidx.work.impl.CleanupCallback(r3));
        r1 = r6.addMigrations(androidx.work.impl.Migration_1_2.INSTANCE).addMigrations(new androidx.work.impl.RescheduleMigration(r1, 2, 3)).addMigrations(androidx.work.impl.Migration_3_4.INSTANCE).addMigrations(androidx.work.impl.Migration_4_5.INSTANCE).addMigrations(new androidx.work.impl.RescheduleMigration(r1, 5, 6)).addMigrations(androidx.work.impl.Migration_6_7.INSTANCE).addMigrations(androidx.work.impl.Migration_7_8.INSTANCE).addMigrations(androidx.work.impl.Migration_8_9.INSTANCE).addMigrations(new androidx.work.impl.WorkMigration9To10(r1)).addMigrations(new androidx.work.impl.RescheduleMigration(r1, 10, 11)).addMigrations(androidx.work.impl.Migration_11_12.INSTANCE).addMigrations(androidx.work.impl.Migration_12_13.INSTANCE).addMigrations(androidx.work.impl.Migration_15_16.INSTANCE).addMigrations(androidx.work.impl.Migration_16_17.INSTANCE).addMigrations(new androidx.work.impl.RescheduleMigration(r1, 21, 22));
        r1.requireMigration = false;
        r1.allowDestructiveMigrationOnDowngrade = true;
        r10 = (androidx.work.impl.WorkDatabase) r1.build();
        r3 = r12.getApplicationContext();
        r3.getClass();
        r1 = r3.getApplicationContext();
        r1.getClass();
        r5 = new androidx.work.impl.constraints.trackers.BatteryChargingTracker(r1, r9);
        r1 = r3.getApplicationContext();
        r1.getClass();
        r6 = new androidx.work.impl.constraints.trackers.BatteryNotLowTracker(r1, r9);
        r1 = r3.getApplicationContext();
        r1.getClass();
        r7 = new androidx.work.impl.constraints.trackers.NetworkStateTracker24(r1, r9);
        r1 = r3.getApplicationContext();
        r1.getClass();
        r11 = new kotlinx.coroutines.scheduling.WorkQueue(r3, r9, r5, r6, r7, new androidx.work.impl.constraints.trackers.StorageNotLowTracker(r1, r9));
        r8 = new androidx.work.impl.Processor(r12.getApplicationContext(), r13, r9, r10);
        r1 = androidx.work.impl.WorkManagerImplExtKt$WorkManagerImpl$1.INSTANCE;
        r10.getClass();
        androidx.work.impl.WorkManagerImpl.sDefaultInstance = new androidx.work.impl.WorkManagerImpl(r12.getApplicationContext(), r13, r9, r10, r1.invoke(r12, r13, r9, r10, r11, r8), r8, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
    
        if (com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent.isBlank("androidx.work.workdb") != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005c, code lost:
    
        r7 = new androidx.room.RoomDatabase.Builder(r1, androidx.work.impl.WorkDatabase.class, "androidx.work.workdb");
        r7.supportOpenHelperFactory = new androidx.work.impl.WorkDatabase$Companion$$ExternalSyntheticLambda0();
        r6 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x01a0, code lost:
    
        throw new java.lang.IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x01a1, code lost:
    
        androidx.work.impl.WorkManagerImpl.sDelegatedInstance = androidx.work.impl.WorkManagerImpl.sDefaultInstance;
     */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void initialize(android.content.Context r12, androidx.work.Configuration r13) {
        /*
            Method dump skipped, instructions count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkManagerImpl.initialize(android.content.Context, androidx.work.Configuration):void");
    }

    @Override // androidx.work.WorkManager
    public final void enqueue$ar$ds(List list) {
        if (!list.isEmpty()) {
            final WorkContinuationImpl workContinuationImpl = new WorkContinuationImpl(this, 2, list);
            if (!workContinuationImpl.mEnqueued) {
                WorkManagerImpl workManagerImpl = workContinuationImpl.mWorkManagerImpl;
                workContinuationImpl.mOperation = WindowCallbackWrapper.Api24Impl.launchOperation$ar$class_merging$ar$class_merging(workManagerImpl.mConfiguration.tracer$ar$class_merging$ar$class_merging, "EnqueueRunnable_".concat("KEEP"), workManagerImpl.mWorkTaskExecutor$ar$class_merging.getSerialTaskExecutor$ar$class_merging(), new Function0() { // from class: androidx.work.impl.WorkContinuationImpl$$ExternalSyntheticLambda0
                    /* JADX WARN: Removed duplicated region for block: B:71:0x02eb  */
                    @Override // kotlin.jvm.functions.Function0
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invoke() {
                        /*
                            Method dump skipped, instructions count: 818
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkContinuationImpl$$ExternalSyntheticLambda0.invoke():java.lang.Object");
                    }
                });
                return;
            }
            Logger.get$ar$ds$16341a92_0();
            Log.w(WorkContinuationImpl.TAG, "Already enqueued work ids (" + TextUtils.join(", ", workContinuationImpl.mIds) + ")");
            return;
        }
        throw new IllegalArgumentException("enqueue needs at least one WorkRequest.");
    }

    public final void onForceStopRunnableCompleted() {
        synchronized (sLock) {
            this.mForceStopRunnableCompleted = true;
            BroadcastReceiver.PendingResult pendingResult = this.mRescheduleReceiverResult;
            if (pendingResult != null) {
                pendingResult.finish();
                this.mRescheduleReceiverResult = null;
            }
        }
    }

    public final void rescheduleEligibleWork() {
        boolean isEnabled;
        ViewUtilsApi19.Api29Impl api29Impl = this.mConfiguration.tracer$ar$class_merging$ar$class_merging;
        SelectToSpeakService$$ExternalSyntheticLambda10 selectToSpeakService$$ExternalSyntheticLambda10 = new SelectToSpeakService$$ExternalSyntheticLambda10(this, 1);
        isEnabled = AppCompatDelegateImpl.Api21Impl.isEnabled();
        if (isEnabled) {
            try {
                AppCompatDelegateImpl.Api21Impl.beginSection("ReschedulingWork");
            } catch (Throwable th) {
                if (isEnabled) {
                    Trace.endSection();
                }
                throw th;
            }
        }
        selectToSpeakService$$ExternalSyntheticLambda10.invoke();
        if (isEnabled) {
            Trace.endSection();
        }
    }
}
