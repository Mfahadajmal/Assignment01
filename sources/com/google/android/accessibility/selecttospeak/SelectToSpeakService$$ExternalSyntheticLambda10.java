package com.google.android.accessibility.selecttospeak;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.JobSchedulerExtKt;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import java.util.Iterator;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakService$$ExternalSyntheticLambda10 implements Function0 {
    public final /* synthetic */ Object SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectToSpeakService$$ExternalSyntheticLambda10(Object obj, int i) {
        this.switching_field = i;
        this.SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    SelectToSpeakService selectToSpeakService = (SelectToSpeakService) this.SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0;
                    selectToSpeakService.uIManager.drawingBoard.clear$ar$ds();
                    selectToSpeakService.uIManager.drawingBoard.onScreenCaptureStart();
                    return Unit.INSTANCE;
                }
                return Boolean.valueOf(((SelectToSpeakService) this.SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0).isOcrEnabled());
            }
            WorkManagerImpl workManagerImpl = (WorkManagerImpl) this.SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0;
            Context context = workManagerImpl.mContext;
            if (Build.VERSION.SDK_INT >= 34) {
                JobSchedulerExtKt.getWmJobScheduler(context).cancelAll();
            }
            JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
            List pendingJobs = SystemJobScheduler.getPendingJobs(context, jobScheduler);
            if (pendingJobs != null && !pendingJobs.isEmpty()) {
                Iterator it = pendingJobs.iterator();
                while (it.hasNext()) {
                    SystemJobScheduler.cancelJobById(jobScheduler, ((JobInfo) it.next()).getId());
                }
            }
            WorkSpecDao workSpecDao = workManagerImpl.mWorkDatabase.workSpecDao();
            WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao;
            workSpecDao_Impl.__db.assertNotSuspendingTransaction();
            FrameworkSQLiteStatement acquire$ar$class_merging = workSpecDao_Impl.__preparedStmtOfResetScheduledState.acquire$ar$class_merging();
            try {
                ((WorkSpecDao_Impl) workSpecDao).__db.beginTransaction();
                try {
                    acquire$ar$class_merging.executeUpdateDelete();
                    ((WorkSpecDao_Impl) workSpecDao).__db.setTransactionSuccessful();
                    workSpecDao_Impl.__preparedStmtOfResetScheduledState.release$ar$class_merging(acquire$ar$class_merging);
                    Schedulers.schedule(workManagerImpl.mConfiguration, workManagerImpl.mWorkDatabase, workManagerImpl.mSchedulers);
                    return Unit.INSTANCE;
                } finally {
                    ((WorkSpecDao_Impl) workSpecDao).__db.endTransaction();
                }
            } catch (Throwable th) {
                workSpecDao_Impl.__preparedStmtOfResetScheduledState.release$ar$class_merging(acquire$ar$class_merging);
                throw th;
            }
        }
        SelectToSpeakService selectToSpeakService2 = (SelectToSpeakService) this.SelectToSpeakService$$ExternalSyntheticLambda10$ar$f$0;
        selectToSpeakService2.uIManager.drawingBoard.onScreenCaptureDone();
        selectToSpeakService2.showAssist();
        return Unit.INSTANCE;
    }
}
