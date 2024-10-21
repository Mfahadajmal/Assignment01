package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.database.Cursor;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.widget.AppCompatTextClassifierHelper$Api26Impl;
import android.util.Log;
import androidx.core.widget.ImageViewCompat$Api21Impl;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelStore;
import androidx.room.RoomSQLiteQuery;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkName;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SystemJobService extends JobService implements ExecutionListener {
    public static final /* synthetic */ int SystemJobService$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("SystemJobService");
    private final Map mJobParameters = new HashMap();
    private final WorkName mStartStopTokens$ar$class_merging$ar$class_merging = new WorkName((byte[]) null);
    private WorkName mWorkLauncher$ar$class_merging$ar$class_merging;
    private WorkManagerImpl mWorkManagerImpl;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Api24Impl {
        static String[] getTriggeredContentAuthorities(JobParameters jobParameters) {
            return jobParameters.getTriggeredContentAuthorities();
        }

        static Uri[] getTriggeredContentUris(JobParameters jobParameters) {
            return jobParameters.getTriggeredContentUris();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api28Impl {
        public static SystemIdInfo $default$getSystemIdInfo(SystemIdInfoDao systemIdInfoDao, WorkGenerationalId workGenerationalId) {
            SystemIdInfo systemIdInfo;
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?", 2);
            acquire.bindString(1, workGenerationalId.workSpecId);
            acquire.bindLong(2, workGenerationalId.generation);
            SystemIdInfoDao_Impl systemIdInfoDao_Impl = (SystemIdInfoDao_Impl) systemIdInfoDao;
            systemIdInfoDao_Impl.__db.assertNotSuspendingTransaction();
            Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(systemIdInfoDao_Impl.__db, acquire);
            try {
                int columnIndexOrThrow = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "work_spec_id");
                int columnIndexOrThrow2 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "generation");
                int columnIndexOrThrow3 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "system_id");
                if (query$ar$ds$e1ca310e_0.moveToFirst()) {
                    systemIdInfo = new SystemIdInfo(query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow), query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow2), query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow3));
                } else {
                    systemIdInfo = null;
                }
                return systemIdInfo;
            } finally {
                query$ar$ds$e1ca310e_0.close();
                acquire.release();
            }
        }

        static Network getNetwork(JobParameters jobParameters) {
            return jobParameters.getNetwork();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api31Impl {
        static int getStopReason(JobParameters jobParameters) {
            int stopReason = jobParameters.getStopReason();
            int i = SystemJobService.SystemJobService$ar$NoOp;
            switch (stopReason) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                    return stopReason;
                default:
                    return -512;
            }
        }

        public static final SystemIdInfo systemIdInfo(WorkGenerationalId workGenerationalId, int i) {
            return new SystemIdInfo(workGenerationalId.workSpecId, workGenerationalId.generation, i);
        }
    }

    private static WorkGenerationalId workGenerationalIdFromJobParameters(JobParameters jobParameters) {
        try {
            PersistableBundle extras = jobParameters.getExtras();
            if (extras != null && extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                return new WorkGenerationalId(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION"));
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        try {
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(getApplicationContext());
            this.mWorkManagerImpl = workManagerImpl;
            Processor processor = workManagerImpl.mProcessor;
            this.mWorkLauncher$ar$class_merging$ar$class_merging = new WorkName(processor, workManagerImpl.mWorkTaskExecutor$ar$class_merging);
            processor.addExecutionListener(this);
        } catch (IllegalStateException e) {
            if (Application.class.equals(getApplication().getClass())) {
                Logger.get$ar$ds$16341a92_0();
                Log.w(TAG, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
                return;
            }
            throw new IllegalStateException("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
        }
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        WorkManagerImpl workManagerImpl = this.mWorkManagerImpl;
        if (workManagerImpl != null) {
            workManagerImpl.mProcessor.removeExecutionListener(this);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId, boolean z) {
        JobParameters jobParameters;
        Logger.get$ar$ds$16341a92_0();
        synchronized (this.mJobParameters) {
            jobParameters = (JobParameters) this.mJobParameters.remove(workGenerationalId);
        }
        this.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(workGenerationalId);
        if (jobParameters != null) {
            jobFinished(jobParameters, z);
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        if (this.mWorkManagerImpl == null) {
            Logger.get$ar$ds$16341a92_0();
            jobFinished(jobParameters, true);
            return false;
        }
        WorkGenerationalId workGenerationalIdFromJobParameters = workGenerationalIdFromJobParameters(jobParameters);
        if (workGenerationalIdFromJobParameters == null) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, "WorkSpec id not found!");
            return false;
        }
        synchronized (this.mJobParameters) {
            if (this.mJobParameters.containsKey(workGenerationalIdFromJobParameters)) {
                Logger.get$ar$ds$16341a92_0();
                Objects.toString(workGenerationalIdFromJobParameters);
                return false;
            }
            Logger.get$ar$ds$16341a92_0();
            Objects.toString(workGenerationalIdFromJobParameters);
            this.mJobParameters.put(workGenerationalIdFromJobParameters, jobParameters);
            AppCompatTextClassifierHelper$Api26Impl appCompatTextClassifierHelper$Api26Impl = new AppCompatTextClassifierHelper$Api26Impl(null);
            if (Api24Impl.getTriggeredContentUris(jobParameters) != null) {
                Arrays.asList(Api24Impl.getTriggeredContentUris(jobParameters));
            }
            if (Api24Impl.getTriggeredContentAuthorities(jobParameters) != null) {
                Arrays.asList(Api24Impl.getTriggeredContentAuthorities(jobParameters));
            }
            if (Build.VERSION.SDK_INT >= 28) {
                Api28Impl.getNetwork(jobParameters);
            }
            this.mWorkLauncher$ar$class_merging$ar$class_merging.startWork$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.mStartStopTokens$ar$class_merging$ar$class_merging.tokenFor$ar$class_merging$ar$class_merging(workGenerationalIdFromJobParameters), appCompatTextClassifierHelper$Api26Impl);
            return true;
        }
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        boolean contains;
        int i;
        if (this.mWorkManagerImpl == null) {
            Logger.get$ar$ds$16341a92_0();
            return true;
        }
        WorkGenerationalId workGenerationalIdFromJobParameters = workGenerationalIdFromJobParameters(jobParameters);
        if (workGenerationalIdFromJobParameters == null) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, "WorkSpec id not found!");
            return false;
        }
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(workGenerationalIdFromJobParameters);
        workGenerationalIdFromJobParameters.toString();
        synchronized (this.mJobParameters) {
            this.mJobParameters.remove(workGenerationalIdFromJobParameters);
        }
        ViewModelStore remove$ar$class_merging$ar$class_merging = this.mStartStopTokens$ar$class_merging$ar$class_merging.remove$ar$class_merging$ar$class_merging(workGenerationalIdFromJobParameters);
        if (remove$ar$class_merging$ar$class_merging != null) {
            if (Build.VERSION.SDK_INT >= 31) {
                i = Api31Impl.getStopReason(jobParameters);
            } else {
                i = -512;
            }
            WorkManagerImpl.Api24Impl.$default$stopWorkWithReason$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.mWorkLauncher$ar$class_merging$ar$class_merging, remove$ar$class_merging$ar$class_merging, i);
        }
        Processor processor = this.mWorkManagerImpl.mProcessor;
        String str = workGenerationalIdFromJobParameters.workSpecId;
        synchronized (processor.mLock) {
            contains = processor.mCancelledIds.contains(str);
        }
        if (!contains) {
            return true;
        }
        return false;
    }
}
