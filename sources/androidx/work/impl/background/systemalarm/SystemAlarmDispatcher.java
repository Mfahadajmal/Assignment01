package androidx.work.impl.background.systemalarm;

import _COROUTINE._BOUNDARY;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.PowerManager;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.AppCompatTextHelper;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.WorkerKt$$ExternalSyntheticLambda0;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.SerialExecutorImpl;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemAlarmDispatcher implements ExecutionListener {
    static final String TAG = Logger.tagWithPrefix("SystemAlarmDispatcher");
    final CommandHandler mCommandHandler;
    public CommandsCompletedListener mCompletedListener;
    final Context mContext;
    Intent mCurrentIntent;
    final List mIntents;
    public final Processor mProcessor;
    private final WorkName mStartStopTokens$ar$class_merging$ar$class_merging;
    final WorkManagerTaskExecutor mTaskExecutor$ar$class_merging;
    public final WorkName mWorkLauncher$ar$class_merging$ar$class_merging;
    public final WorkManagerImpl mWorkManager;
    public final WorkTimer mWorkTimer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AddRunnable implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;
        private final Intent mIntent;
        private final int mStartId;

        public AddRunnable(SystemAlarmDispatcher systemAlarmDispatcher, Intent intent, int i) {
            this.mDispatcher = systemAlarmDispatcher;
            this.mIntent = intent;
            this.mStartId = i;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.mDispatcher.add$ar$ds$d57f4edf_0(this.mIntent, this.mStartId);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface CommandsCompletedListener {
        void onAllCommandsCompleted();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DequeueAndCheckForCompletion implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;

        public DequeueAndCheckForCompletion(SystemAlarmDispatcher systemAlarmDispatcher) {
            this.mDispatcher = systemAlarmDispatcher;
        }

        @Override // java.lang.Runnable
        public final void run() {
            boolean z;
            boolean z2;
            Logger.get$ar$ds$16341a92_0();
            SystemAlarmDispatcher.assertMainThread$ar$ds();
            SystemAlarmDispatcher systemAlarmDispatcher = this.mDispatcher;
            synchronized (systemAlarmDispatcher.mIntents) {
                if (systemAlarmDispatcher.mCurrentIntent != null) {
                    Logger.get$ar$ds$16341a92_0();
                    Objects.toString(systemAlarmDispatcher.mCurrentIntent);
                    if (((Intent) systemAlarmDispatcher.mIntents.remove(0)).equals(systemAlarmDispatcher.mCurrentIntent)) {
                        systemAlarmDispatcher.mCurrentIntent = null;
                    } else {
                        throw new IllegalStateException("Dequeue-d command is not the first.");
                    }
                }
                SerialExecutorImpl serialTaskExecutor$ar$class_merging = systemAlarmDispatcher.mTaskExecutor$ar$class_merging.getSerialTaskExecutor$ar$class_merging();
                CommandHandler commandHandler = systemAlarmDispatcher.mCommandHandler;
                synchronized (commandHandler.mLock) {
                    z = !commandHandler.mPendingDelayMet.isEmpty();
                }
                if (!z && systemAlarmDispatcher.mIntents.isEmpty()) {
                    synchronized (serialTaskExecutor$ar$class_merging.mLock) {
                        z2 = !serialTaskExecutor$ar$class_merging.mTasks.isEmpty();
                    }
                    if (!z2) {
                        Logger.get$ar$ds$16341a92_0();
                        CommandsCompletedListener commandsCompletedListener = systemAlarmDispatcher.mCompletedListener;
                        if (commandsCompletedListener != null) {
                            commandsCompletedListener.onAllCommandsCompleted();
                        }
                    }
                }
                if (!systemAlarmDispatcher.mIntents.isEmpty()) {
                    systemAlarmDispatcher.processCommand();
                }
            }
        }
    }

    public SystemAlarmDispatcher(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        WorkName workName = new WorkName((byte[]) null);
        this.mStartStopTokens$ar$class_merging$ar$class_merging = workName;
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
        this.mWorkManager = workManagerImpl;
        this.mCommandHandler = new CommandHandler(applicationContext, workManagerImpl.mConfiguration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging, workName);
        this.mWorkTimer = new WorkTimer(workManagerImpl.mConfiguration.runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        Processor processor = workManagerImpl.mProcessor;
        this.mProcessor = processor;
        WorkManagerTaskExecutor workManagerTaskExecutor = workManagerImpl.mWorkTaskExecutor$ar$class_merging;
        this.mTaskExecutor$ar$class_merging = workManagerTaskExecutor;
        this.mWorkLauncher$ar$class_merging$ar$class_merging = new WorkName(processor, workManagerTaskExecutor);
        processor.addExecutionListener(this);
        this.mIntents = new ArrayList();
        this.mCurrentIntent = null;
    }

    public static final void assertMainThread$ar$ds() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
        } else {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    public final void add$ar$ds$d57f4edf_0(Intent intent, int i) {
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(intent);
        assertMainThread$ar$ds();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Logger.get$ar$ds$16341a92_0();
            Log.w(TAG, "Unknown command. Ignoring");
            return;
        }
        if ("ACTION_CONSTRAINTS_CHANGED".equals(action)) {
            assertMainThread$ar$ds();
            synchronized (this.mIntents) {
                Iterator it = this.mIntents.iterator();
                while (it.hasNext()) {
                    if ("ACTION_CONSTRAINTS_CHANGED".equals(((Intent) it.next()).getAction())) {
                        return;
                    }
                }
            }
        }
        intent.putExtra("KEY_START_ID", i);
        synchronized (this.mIntents) {
            boolean z = !this.mIntents.isEmpty();
            this.mIntents.add(intent);
            if (!z) {
                processCommand();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onDestroy() {
        Logger.get$ar$ds$16341a92_0();
        this.mProcessor.removeExecutionListener(this);
        this.mCompletedListener = null;
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        Context context = this.mContext;
        Executor mainThreadExecutor = this.mTaskExecutor$ar$class_merging.getMainThreadExecutor();
        Intent intent = new Intent(context, (Class<?>) SystemAlarmService.class);
        intent.setAction("ACTION_EXECUTION_COMPLETED");
        intent.putExtra("KEY_NEEDS_RESCHEDULE", z);
        CommandHandler.writeWorkGenerationalId$ar$ds(intent, workGenerationalId);
        mainThreadExecutor.execute(new AddRunnable(this, intent, 0));
    }

    public final void processCommand() {
        assertMainThread$ar$ds();
        PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(this.mContext, "ProcessCommand");
        try {
            newWakeLock.acquire();
            this.mWorkManager.mWorkTaskExecutor$ar$class_merging.executeOnTaskThread(new Runnable() { // from class: androidx.work.impl.background.systemalarm.SystemAlarmDispatcher.1
                @Override // java.lang.Runnable
                public final void run() {
                    int i;
                    Executor mainThreadExecutor;
                    DequeueAndCheckForCompletion dequeueAndCheckForCompletion;
                    List<ViewModelStore> remove;
                    long currentTimeMillis;
                    boolean z;
                    synchronized (SystemAlarmDispatcher.this.mIntents) {
                        SystemAlarmDispatcher systemAlarmDispatcher = SystemAlarmDispatcher.this;
                        systemAlarmDispatcher.mCurrentIntent = (Intent) systemAlarmDispatcher.mIntents.get(0);
                    }
                    Intent intent = SystemAlarmDispatcher.this.mCurrentIntent;
                    if (intent != null) {
                        SystemAlarmDispatcher systemAlarmDispatcher2 = SystemAlarmDispatcher.this;
                        String action = intent.getAction();
                        int intExtra = systemAlarmDispatcher2.mCurrentIntent.getIntExtra("KEY_START_ID", 0);
                        Logger.get$ar$ds$16341a92_0();
                        Objects.toString(SystemAlarmDispatcher.this.mCurrentIntent);
                        PowerManager.WakeLock newWakeLock2 = WakeLocks.newWakeLock(SystemAlarmDispatcher.this.mContext, action + " (" + intExtra + ")");
                        try {
                            Logger.get$ar$ds$16341a92_0();
                            Objects.toString(newWakeLock2);
                            newWakeLock2.acquire();
                            SystemAlarmDispatcher systemAlarmDispatcher3 = SystemAlarmDispatcher.this;
                            CommandHandler commandHandler = systemAlarmDispatcher3.mCommandHandler;
                            Intent intent2 = systemAlarmDispatcher3.mCurrentIntent;
                            String action2 = intent2.getAction();
                            if ("ACTION_CONSTRAINTS_CHANGED".equals(action2)) {
                                Logger.get$ar$ds$16341a92_0();
                                Objects.toString(intent2);
                                int i2 = ConstraintsCommandHandler.ConstraintsCommandHandler$ar$NoOp;
                                Context context = commandHandler.mContext;
                                ViewModelStore viewModelStore = new ViewModelStore(systemAlarmDispatcher3.mWorkManager.mTrackers$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
                                List<WorkSpec> scheduledWork = systemAlarmDispatcher3.mWorkManager.mWorkDatabase.workSpecDao().getScheduledWork();
                                int i3 = ConstraintProxy.ConstraintProxy$ar$NoOp;
                                Iterator it = scheduledWork.iterator();
                                boolean z2 = false;
                                boolean z3 = false;
                                boolean z4 = false;
                                boolean z5 = false;
                                while (it.hasNext()) {
                                    Constraints constraints = ((WorkSpec) it.next()).constraints;
                                    z2 |= constraints.requiresBatteryNotLow;
                                    z3 |= constraints.requiresCharging;
                                    z4 |= constraints.requiresStorageNotLow;
                                    if (constraints.requiredNetworkType$ar$edu != 1) {
                                        z = true;
                                    } else {
                                        z = false;
                                    }
                                    z5 |= z;
                                    if (z2 && z3 && z4 && z5) {
                                        break;
                                    }
                                }
                                Intent intent3 = new Intent("androidx.work.impl.background.systemalarm.UpdateProxies");
                                intent3.setComponent(new ComponentName(context, (Class<?>) ConstraintProxyUpdateReceiver.class));
                                intent3.putExtra("KEY_BATTERY_NOT_LOW_PROXY_ENABLED", z2).putExtra("KEY_BATTERY_CHARGING_PROXY_ENABLED", z3).putExtra("KEY_STORAGE_NOT_LOW_PROXY_ENABLED", z4).putExtra("KEY_NETWORK_STATE_PROXY_ENABLED", z5);
                                context.sendBroadcast(intent3);
                                ArrayList arrayList = new ArrayList(scheduledWork.size());
                                currentTimeMillis = System.currentTimeMillis();
                                for (WorkSpec workSpec : scheduledWork) {
                                    if (currentTimeMillis >= workSpec.calculateNextRunTime() && (!workSpec.hasConstraints() || viewModelStore.areAllConstraintsMet(workSpec))) {
                                        arrayList.add(workSpec);
                                    }
                                }
                                int size = arrayList.size();
                                for (i = 0; i < size; i++) {
                                    WorkSpec workSpec2 = (WorkSpec) arrayList.get(i);
                                    String str = workSpec2.id;
                                    Intent createDelayMetIntent = CommandHandler.createDelayMetIntent(context, AppCompatTextHelper.Api24Impl.generationalId(workSpec2));
                                    Logger.get$ar$ds$16341a92_0();
                                    systemAlarmDispatcher3.mTaskExecutor$ar$class_merging.getMainThreadExecutor().execute(new AddRunnable(systemAlarmDispatcher3, createDelayMetIntent, intExtra));
                                }
                            } else if ("ACTION_RESCHEDULE".equals(action2)) {
                                Logger.get$ar$ds$16341a92_0();
                                Objects.toString(intent2);
                                systemAlarmDispatcher3.mWorkManager.rescheduleEligibleWork();
                            } else {
                                Bundle extras = intent2.getExtras();
                                String[] strArr = {"KEY_WORKSPEC_ID"};
                                if (extras != null && !extras.isEmpty() && extras.get(strArr[0]) != null) {
                                    if ("ACTION_SCHEDULE_WORK".equals(action2)) {
                                        WorkGenerationalId readWorkGenerationalId = CommandHandler.readWorkGenerationalId(intent2);
                                        Logger.get$ar$ds$16341a92_0();
                                        Objects.toString(readWorkGenerationalId);
                                        readWorkGenerationalId.toString();
                                        WorkDatabase workDatabase = systemAlarmDispatcher3.mWorkManager.mWorkDatabase;
                                        workDatabase.beginTransaction();
                                        try {
                                            WorkSpec workSpec3 = workDatabase.workSpecDao().getWorkSpec(readWorkGenerationalId.workSpecId);
                                            if (workSpec3 == null) {
                                                Logger.get$ar$ds$16341a92_0();
                                                Log.w(CommandHandler.TAG, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(readWorkGenerationalId, "Skipping scheduling ", " because it's no longer in the DB"));
                                            } else if (AppCompatReceiveContentHelper$OnDropApi24Impl.isFinished$ar$edu(workSpec3.state$ar$edu)) {
                                                Logger.get$ar$ds$16341a92_0();
                                                Log.w(CommandHandler.TAG, _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(readWorkGenerationalId, "Skipping scheduling ", "because it is finished."));
                                            } else {
                                                long calculateNextRunTime = workSpec3.calculateNextRunTime();
                                                if (!workSpec3.hasConstraints()) {
                                                    Logger.get$ar$ds$16341a92_0();
                                                    Objects.toString(readWorkGenerationalId);
                                                    Alarms.setAlarm(commandHandler.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                                                } else {
                                                    Logger.get$ar$ds$16341a92_0();
                                                    Objects.toString(readWorkGenerationalId);
                                                    Alarms.setAlarm(commandHandler.mContext, workDatabase, readWorkGenerationalId, calculateNextRunTime);
                                                    systemAlarmDispatcher3.mTaskExecutor$ar$class_merging.getMainThreadExecutor().execute(new AddRunnable(systemAlarmDispatcher3, CommandHandler.createConstraintsChangedIntent(commandHandler.mContext), intExtra));
                                                }
                                                workDatabase.setTransactionSuccessful();
                                            }
                                        } finally {
                                            workDatabase.endTransaction();
                                        }
                                    } else {
                                        int i4 = 2;
                                        if ("ACTION_DELAY_MET".equals(action2)) {
                                            synchronized (commandHandler.mLock) {
                                                WorkGenerationalId readWorkGenerationalId2 = CommandHandler.readWorkGenerationalId(intent2);
                                                Logger.get$ar$ds$16341a92_0();
                                                Objects.toString(readWorkGenerationalId2);
                                                if (!commandHandler.mPendingDelayMet.containsKey(readWorkGenerationalId2)) {
                                                    DelayMetCommandHandler delayMetCommandHandler = new DelayMetCommandHandler(commandHandler.mContext, intExtra, systemAlarmDispatcher3, commandHandler.mStartStopTokens$ar$class_merging$ar$class_merging.tokenFor$ar$class_merging$ar$class_merging(readWorkGenerationalId2));
                                                    commandHandler.mPendingDelayMet.put(readWorkGenerationalId2, delayMetCommandHandler);
                                                    String str2 = delayMetCommandHandler.mWorkGenerationalId.workSpecId;
                                                    delayMetCommandHandler.mWakeLock = WakeLocks.newWakeLock(delayMetCommandHandler.mContext, str2 + " (" + delayMetCommandHandler.mStartId + ")");
                                                    Logger.get$ar$ds$16341a92_0();
                                                    Objects.toString(delayMetCommandHandler.mWakeLock);
                                                    delayMetCommandHandler.mWakeLock.acquire();
                                                    WorkSpec workSpec4 = delayMetCommandHandler.mDispatcher.mWorkManager.mWorkDatabase.workSpecDao().getWorkSpec(str2);
                                                    if (workSpec4 == null) {
                                                        delayMetCommandHandler.mSerialExecutor.execute(new WorkerKt$$ExternalSyntheticLambda0(delayMetCommandHandler, i4));
                                                    } else {
                                                        boolean hasConstraints = workSpec4.hasConstraints();
                                                        delayMetCommandHandler.mHasConstraints = hasConstraints;
                                                        if (!hasConstraints) {
                                                            Logger.get$ar$ds$16341a92_0();
                                                            delayMetCommandHandler.mSerialExecutor.execute(new WorkerKt$$ExternalSyntheticLambda0(delayMetCommandHandler, 3));
                                                        } else {
                                                            delayMetCommandHandler.mJob = WorkConstraintsTrackerKt.listen$ar$class_merging$ar$class_merging$ar$class_merging(delayMetCommandHandler.mWorkConstraintsTracker$ar$class_merging$ar$class_merging$ar$class_merging, workSpec4, delayMetCommandHandler.mCoroutineDispatcher, delayMetCommandHandler);
                                                        }
                                                    }
                                                } else {
                                                    Logger.get$ar$ds$16341a92_0();
                                                    Objects.toString(readWorkGenerationalId2);
                                                }
                                            }
                                        } else if ("ACTION_STOP_WORK".equals(action2)) {
                                            Bundle extras2 = intent2.getExtras();
                                            String string = extras2.getString("KEY_WORKSPEC_ID");
                                            if (extras2.containsKey("KEY_WORKSPEC_GENERATION")) {
                                                int i5 = extras2.getInt("KEY_WORKSPEC_GENERATION");
                                                remove = new ArrayList(1);
                                                ViewModelStore remove$ar$class_merging$ar$class_merging = commandHandler.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(new WorkGenerationalId(string, i5));
                                                if (remove$ar$class_merging$ar$class_merging != null) {
                                                    remove.add(remove$ar$class_merging$ar$class_merging);
                                                }
                                            } else {
                                                remove = commandHandler.mStartStopTokens$ar$class_merging$ar$class_merging.remove(string);
                                            }
                                            for (ViewModelStore viewModelStore2 : remove) {
                                                Logger.get$ar$ds$16341a92_0();
                                                WorkManagerImpl.Api24Impl.$default$stopWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(systemAlarmDispatcher3.mWorkLauncher$ar$class_merging$ar$class_merging, viewModelStore2);
                                                Context context2 = commandHandler.mContext;
                                                WorkDatabase workDatabase2 = systemAlarmDispatcher3.mWorkManager.mWorkDatabase;
                                                Object obj = viewModelStore2.ViewModelStore$ar$map;
                                                int i6 = Alarms.Alarms$ar$NoOp;
                                                SystemIdInfoDao systemIdInfoDao = workDatabase2.systemIdInfoDao();
                                                SystemIdInfo $default$getSystemIdInfo = SystemJobService.Api28Impl.$default$getSystemIdInfo(systemIdInfoDao, (WorkGenerationalId) obj);
                                                if ($default$getSystemIdInfo != null) {
                                                    Alarms.cancelExactAlarm(context2, (WorkGenerationalId) obj, $default$getSystemIdInfo.systemId);
                                                    Logger.get$ar$ds$16341a92_0();
                                                    Objects.toString(obj);
                                                    String str3 = ((WorkGenerationalId) obj).workSpecId;
                                                    int i7 = ((WorkGenerationalId) obj).generation;
                                                    ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.assertNotSuspendingTransaction();
                                                    FrameworkSQLiteStatement acquire$ar$class_merging = ((SystemIdInfoDao_Impl) systemIdInfoDao).__preparedStmtOfRemoveSystemIdInfo.acquire$ar$class_merging();
                                                    acquire$ar$class_merging.bindString(1, str3);
                                                    acquire$ar$class_merging.bindLong(2, i7);
                                                    try {
                                                        ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.beginTransaction();
                                                        try {
                                                            acquire$ar$class_merging.executeUpdateDelete();
                                                            ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.setTransactionSuccessful();
                                                        } finally {
                                                        }
                                                    } finally {
                                                        ((SystemIdInfoDao_Impl) systemIdInfoDao).__preparedStmtOfRemoveSystemIdInfo.release$ar$class_merging(acquire$ar$class_merging);
                                                    }
                                                }
                                                systemAlarmDispatcher3.onExecuted((WorkGenerationalId) viewModelStore2.ViewModelStore$ar$map, false);
                                            }
                                        } else if ("ACTION_EXECUTION_COMPLETED".equals(action2)) {
                                            WorkGenerationalId readWorkGenerationalId3 = CommandHandler.readWorkGenerationalId(intent2);
                                            boolean z6 = intent2.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
                                            Logger.get$ar$ds$16341a92_0();
                                            Objects.toString(intent2);
                                            commandHandler.onExecuted(readWorkGenerationalId3, z6);
                                        } else {
                                            Logger.get$ar$ds$16341a92_0();
                                            String str4 = CommandHandler.TAG;
                                            Objects.toString(intent2);
                                            Log.w(str4, "Ignoring intent ".concat(String.valueOf(intent2)));
                                        }
                                    }
                                }
                                Logger.get$ar$ds$16341a92_0();
                                Log.e(CommandHandler.TAG, "Invalid request for " + action2 + " , requires KEY_WORKSPEC_ID .");
                            }
                            Logger.get$ar$ds$16341a92_0();
                            Objects.toString(newWakeLock2);
                            newWakeLock2.release();
                            SystemAlarmDispatcher systemAlarmDispatcher4 = SystemAlarmDispatcher.this;
                            mainThreadExecutor = systemAlarmDispatcher4.mTaskExecutor$ar$class_merging.getMainThreadExecutor();
                            dequeueAndCheckForCompletion = new DequeueAndCheckForCompletion(systemAlarmDispatcher4);
                        } catch (Throwable th) {
                            try {
                                Logger.get$ar$ds$16341a92_0();
                                Log.e(SystemAlarmDispatcher.TAG, "Unexpected error in onHandleIntent", th);
                                Logger.get$ar$ds$16341a92_0();
                                Objects.toString(newWakeLock2);
                                newWakeLock2.release();
                                SystemAlarmDispatcher systemAlarmDispatcher5 = SystemAlarmDispatcher.this;
                                mainThreadExecutor = systemAlarmDispatcher5.mTaskExecutor$ar$class_merging.getMainThreadExecutor();
                                dequeueAndCheckForCompletion = new DequeueAndCheckForCompletion(systemAlarmDispatcher5);
                            } catch (Throwable th2) {
                                Logger.get$ar$ds$16341a92_0();
                                Objects.toString(newWakeLock2);
                                newWakeLock2.release();
                                SystemAlarmDispatcher systemAlarmDispatcher6 = SystemAlarmDispatcher.this;
                                systemAlarmDispatcher6.mTaskExecutor$ar$class_merging.getMainThreadExecutor().execute(new DequeueAndCheckForCompletion(systemAlarmDispatcher6));
                                throw th2;
                            }
                        }
                        mainThreadExecutor.execute(dequeueAndCheckForCompletion);
                    }
                }
            });
        } finally {
            newWakeLock.release();
        }
    }
}
