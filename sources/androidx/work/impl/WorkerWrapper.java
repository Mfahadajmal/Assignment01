package androidx.work.impl;

import android.content.Context;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.ListenableWorker$Result$Failure;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.List;
import kotlinx.coroutines.JobImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkerWrapper {
    public final Context appContext;
    public final WindowCallbackWrapper.Api26Impl clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
    public final Configuration configuration;
    public final DependencyDao dependencyDao;
    private final ForegroundProcessor foregroundProcessor;
    private final AppCompatTextClassifierHelper$Api26Impl runtimeExtras$ar$class_merging$ar$class_merging;
    private final List tags;
    public final WorkDatabase workDatabase;
    public final String workDescription;
    public final WorkSpec workSpec;
    public final WorkSpecDao workSpecDao;
    public final String workSpecId;
    public final WorkManagerTaskExecutor workTaskExecutor$ar$class_merging;
    public final JobImpl workerJob$ar$class_merging;

    /* JADX WARN: Type inference failed for: r1v8, types: [androidx.work.impl.foreground.ForegroundProcessor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List, java.lang.Object, java.lang.Iterable] */
    public WorkerWrapper(DirectionNavigationActor directionNavigationActor) {
        WorkSpec workSpec = (WorkSpec) directionNavigationActor.DirectionNavigationActor$ar$accessibilityFocusMonitor;
        this.workSpec = workSpec;
        this.appContext = directionNavigationActor.DirectionNavigationActor$ar$service;
        String str = workSpec.id;
        this.workSpecId = str;
        this.runtimeExtras$ar$class_merging$ar$class_merging = (AppCompatTextClassifierHelper$Api26Impl) directionNavigationActor.DirectionNavigationActor$ar$pipeline;
        this.workTaskExecutor$ar$class_merging = (WorkManagerTaskExecutor) directionNavigationActor.DirectionNavigationActor$ar$focusProcessorForLogicalNavigation;
        Configuration configuration = (Configuration) directionNavigationActor.DirectionNavigationActor$ar$cursorGranularityManager;
        this.configuration = configuration;
        this.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging = configuration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
        this.foregroundProcessor = directionNavigationActor.DirectionNavigationActor$ar$analytics;
        WorkDatabase workDatabase = (WorkDatabase) directionNavigationActor.DirectionNavigationActor$ar$state;
        this.workDatabase = workDatabase;
        this.workSpecDao = workDatabase.workSpecDao();
        this.dependencyDao = workDatabase.dependencyDao();
        ?? r2 = directionNavigationActor.DirectionNavigationActor$ar$inputModeTracker;
        this.tags = r2;
        this.workDescription = "Work [ id=" + str + ", tags={ " + OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(r2, ",", null, null, null, 62) + " } ]";
        this.workerJob$ar$class_merging = ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds();
    }

    public final WorkGenerationalId getWorkGenerationalId() {
        return AppCompatTextHelper.Api24Impl.generationalId(this.workSpec);
    }

    public final void reschedule$ar$ds(int i) {
        long currentTimeMillis;
        this.workSpecDao.setState$ar$edu$ar$ds(1, this.workSpecId);
        WorkSpecDao workSpecDao = this.workSpecDao;
        String str = this.workSpecId;
        currentTimeMillis = System.currentTimeMillis();
        workSpecDao.setLastEnqueueTime(str, currentTimeMillis);
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.nextScheduleTimeOverrideGeneration);
        this.workSpecDao.markWorkSpecScheduled$ar$ds(this.workSpecId, -1L);
        this.workSpecDao.setStopReason(this.workSpecId, i);
    }

    public final void resetPeriodic$ar$ds() {
        long currentTimeMillis;
        WorkSpecDao workSpecDao = this.workSpecDao;
        String str = this.workSpecId;
        currentTimeMillis = System.currentTimeMillis();
        workSpecDao.setLastEnqueueTime(str, currentTimeMillis);
        this.workSpecDao.setState$ar$edu$ar$ds(1, this.workSpecId);
        WorkSpecDao workSpecDao2 = this.workSpecDao;
        WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao2;
        workSpecDao_Impl.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = workSpecDao_Impl.__preparedStmtOfResetWorkSpecRunAttemptCount.acquire$ar$class_merging();
        acquire$ar$class_merging.bindString(1, this.workSpecId);
        try {
            ((WorkSpecDao_Impl) workSpecDao2).__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                ((WorkSpecDao_Impl) workSpecDao2).__db.setTransactionSuccessful();
                workSpecDao_Impl.__preparedStmtOfResetWorkSpecRunAttemptCount.release$ar$class_merging(acquire$ar$class_merging);
                this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.nextScheduleTimeOverrideGeneration);
                workSpecDao2 = this.workSpecDao;
                String str2 = this.workSpecId;
                WorkSpecDao_Impl workSpecDao_Impl2 = (WorkSpecDao_Impl) workSpecDao2;
                workSpecDao_Impl2.__db.assertNotSuspendingTransaction();
                FrameworkSQLiteStatement acquire$ar$class_merging2 = workSpecDao_Impl2.__preparedStmtOfIncrementPeriodCount.acquire$ar$class_merging();
                acquire$ar$class_merging2.bindString(1, str2);
                try {
                    ((WorkSpecDao_Impl) workSpecDao2).__db.beginTransaction();
                    try {
                        acquire$ar$class_merging2.executeUpdateDelete();
                        ((WorkSpecDao_Impl) workSpecDao2).__db.setTransactionSuccessful();
                        workSpecDao_Impl2.__preparedStmtOfIncrementPeriodCount.release$ar$class_merging(acquire$ar$class_merging2);
                        this.workSpecDao.markWorkSpecScheduled$ar$ds(this.workSpecId, -1L);
                    } finally {
                    }
                } catch (Throwable th) {
                    workSpecDao_Impl2.__preparedStmtOfIncrementPeriodCount.release$ar$class_merging(acquire$ar$class_merging2);
                    throw th;
                }
            } finally {
            }
        } catch (Throwable th2) {
            workSpecDao_Impl.__preparedStmtOfResetWorkSpecRunAttemptCount.release$ar$class_merging(acquire$ar$class_merging);
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object runWorker(kotlin.coroutines.Continuation r22) {
        /*
            Method dump skipped, instructions count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.WorkerWrapper.runWorker(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void setFailed$ar$ds$ar$class_merging$ar$class_merging(AppCompatDelegateImpl.Api33Impl api33Impl) {
        List mutableListOf = OnDeviceLanguageIdentificationLogEvent.mutableListOf(this.workSpecId);
        while (!mutableListOf.isEmpty()) {
            String str = (String) OnDeviceLanguageIdentificationLogEvent.removeLast(mutableListOf);
            if (this.workSpecDao.getState$ar$edu$fd856834_0(str) != 6) {
                this.workSpecDao.setState$ar$edu$ar$ds(4, str);
            }
            mutableListOf.addAll(this.dependencyDao.getDependentWorkIds(str));
        }
        Data data = ((ListenableWorker$Result$Failure) api33Impl).mOutputData;
        data.getClass();
        this.workSpecDao.resetWorkSpecNextScheduleTimeOverride(this.workSpecId, this.workSpec.nextScheduleTimeOverrideGeneration);
        this.workSpecDao.setOutput(this.workSpecId, data);
    }
}
