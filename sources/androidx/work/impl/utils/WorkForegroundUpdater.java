package androidx.work.impl.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.support.v7.widget.AppCompatTextHelper;
import android.support.v7.widget.DropDownListView;
import androidx.core.content.ContextCompat$Api26Impl;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.Logger;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda17;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkForegroundUpdater implements ForegroundUpdater {
    final ForegroundProcessor mForegroundProcessor;
    private final WorkManagerTaskExecutor mTaskExecutor$ar$class_merging;
    final WorkSpecDao mWorkSpecDao;

    static {
        Logger.tagWithPrefix("WMFgUpdater");
    }

    public WorkForegroundUpdater(WorkDatabase workDatabase, ForegroundProcessor foregroundProcessor, WorkManagerTaskExecutor workManagerTaskExecutor) {
        this.mForegroundProcessor = foregroundProcessor;
        this.mTaskExecutor$ar$class_merging = workManagerTaskExecutor;
        this.mWorkSpecDao = workDatabase.workSpecDao();
    }

    @Override // androidx.work.ForegroundUpdater
    public final ListenableFuture setForegroundAsync(final Context context, final UUID uuid, final ForegroundInfo foregroundInfo) {
        SerialExecutorImpl serialTaskExecutor$ar$class_merging = this.mTaskExecutor$ar$class_merging.getSerialTaskExecutor$ar$class_merging();
        Function0 function0 = new Function0() { // from class: androidx.work.impl.utils.WorkForegroundUpdater$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                ForegroundInfo foregroundInfo2;
                UUID uuid2 = uuid;
                WorkForegroundUpdater workForegroundUpdater = WorkForegroundUpdater.this;
                WorkSpecDao workSpecDao = workForegroundUpdater.mWorkSpecDao;
                String uuid3 = uuid2.toString();
                WorkSpec workSpec = workSpecDao.getWorkSpec(uuid3);
                if (workSpec != null && !AppCompatReceiveContentHelper$OnDropApi24Impl.isFinished$ar$edu(workSpec.state$ar$edu)) {
                    ForegroundProcessor foregroundProcessor = workForegroundUpdater.mForegroundProcessor;
                    synchronized (((Processor) foregroundProcessor).mLock) {
                        Logger.get$ar$ds$16341a92_0();
                        WorkerWrapper workerWrapper = (WorkerWrapper) ((Processor) foregroundProcessor).mEnqueuedWorkMap.remove(uuid3);
                        foregroundInfo2 = foregroundInfo;
                        if (workerWrapper != null) {
                            if (((Processor) foregroundProcessor).mForegroundLock == null) {
                                ((Processor) foregroundProcessor).mForegroundLock = WakeLocks.newWakeLock(((Processor) foregroundProcessor).mAppContext, "ProcessorForegroundLck");
                                ((Processor) foregroundProcessor).mForegroundLock.acquire();
                            }
                            ((Processor) foregroundProcessor).mForegroundWorkMap.put(uuid3, workerWrapper);
                            Context context2 = ((Processor) foregroundProcessor).mAppContext;
                            WorkGenerationalId workGenerationalId = workerWrapper.getWorkGenerationalId();
                            Intent intent = new Intent(context2, (Class<?>) SystemForegroundService.class);
                            intent.setAction("ACTION_START_FOREGROUND");
                            intent.putExtra("KEY_WORKSPEC_ID", workGenerationalId.workSpecId);
                            intent.putExtra("KEY_GENERATION", workGenerationalId.generation);
                            intent.putExtra("KEY_NOTIFICATION_ID", foregroundInfo2.mNotificationId);
                            intent.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo2.mForegroundServiceType);
                            intent.putExtra("KEY_NOTIFICATION", foregroundInfo2.mNotification);
                            ContextCompat$Api26Impl.startForegroundService(((Processor) foregroundProcessor).mAppContext, intent);
                        }
                    }
                    Context context3 = context;
                    WorkGenerationalId generationalId = AppCompatTextHelper.Api24Impl.generationalId(workSpec);
                    Intent intent2 = new Intent(context3, (Class<?>) SystemForegroundService.class);
                    intent2.setAction("ACTION_NOTIFY");
                    intent2.putExtra("KEY_NOTIFICATION_ID", foregroundInfo2.mNotificationId);
                    intent2.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo2.mForegroundServiceType);
                    intent2.putExtra("KEY_NOTIFICATION", foregroundInfo2.mNotification);
                    intent2.putExtra("KEY_WORKSPEC_ID", generationalId.workSpecId);
                    intent2.putExtra("KEY_GENERATION", generationalId.generation);
                    context3.startService(intent2);
                    return null;
                }
                throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
            }
        };
        serialTaskExecutor$ar$class_merging.getClass();
        return DropDownListView.Api33Impl.getFuture(new AiCoreBaseService$$ExternalSyntheticLambda17(serialTaskExecutor$ar$class_merging, function0, 1));
    }
}
