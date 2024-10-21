package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextHelper;
import android.util.Log;
import androidx.lifecycle.ViewModelStore;
import androidx.preference.Preference;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Configuration;
import androidx.work.Constraints;
import androidx.work.Logger;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemJobScheduler implements Scheduler {
    public static final /* synthetic */ int SystemJobScheduler$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("SystemJobScheduler");
    private final Configuration mConfiguration;
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final SystemJobInfoConverter mSystemJobInfoConverter;
    private final WorkDatabase mWorkDatabase;

    public SystemJobScheduler(Context context, WorkDatabase workDatabase, Configuration configuration) {
        JobScheduler wmJobScheduler = JobSchedulerExtKt.getWmJobScheduler(context);
        SystemJobInfoConverter systemJobInfoConverter = new SystemJobInfoConverter(context, configuration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging, true);
        this.mContext = context;
        this.mJobScheduler = wmJobScheduler;
        this.mSystemJobInfoConverter = systemJobInfoConverter;
        this.mWorkDatabase = workDatabase;
        this.mConfiguration = configuration;
    }

    public static void cancelJobById(JobScheduler jobScheduler, int i) {
        try {
            jobScheduler.cancel(i);
        } catch (Throwable th) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(i)), th);
        }
    }

    public static List getPendingJobs(Context context, JobScheduler jobScheduler) {
        List<JobInfo> safePendingJobs = JobSchedulerExtKt.getSafePendingJobs(jobScheduler);
        if (safePendingJobs == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(safePendingJobs.size());
        ComponentName componentName = new ComponentName(context, (Class<?>) SystemJobService.class);
        for (JobInfo jobInfo : safePendingJobs) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    public static WorkGenerationalId getWorkGenerationalIdFromJobInfo(JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras != null) {
            try {
                if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                    return new WorkGenerationalId(extras.getString("EXTRA_WORK_SPEC_ID"), extras.getInt("EXTRA_WORK_SPEC_GENERATION", 0));
                }
                return null;
            } catch (NullPointerException unused) {
                return null;
            }
        }
        return null;
    }

    @Override // androidx.work.impl.Scheduler
    public final void cancel(String str) {
        ArrayList arrayList;
        List<JobInfo> pendingJobs = getPendingJobs(this.mContext, this.mJobScheduler);
        if (pendingJobs == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList(2);
            for (JobInfo jobInfo : pendingJobs) {
                WorkGenerationalId workGenerationalIdFromJobInfo = getWorkGenerationalIdFromJobInfo(jobInfo);
                if (workGenerationalIdFromJobInfo != null && str.equals(workGenerationalIdFromJobInfo.workSpecId)) {
                    arrayList2.add(Integer.valueOf(jobInfo.getId()));
                }
            }
            arrayList = arrayList2;
        }
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                cancelJobById(this.mJobScheduler, ((Integer) it.next()).intValue());
            }
            SystemIdInfoDao systemIdInfoDao = this.mWorkDatabase.systemIdInfoDao();
            SystemIdInfoDao_Impl systemIdInfoDao_Impl = (SystemIdInfoDao_Impl) systemIdInfoDao;
            systemIdInfoDao_Impl.__db.assertNotSuspendingTransaction();
            FrameworkSQLiteStatement acquire$ar$class_merging = systemIdInfoDao_Impl.__preparedStmtOfRemoveSystemIdInfo_1.acquire$ar$class_merging();
            acquire$ar$class_merging.bindString(1, str);
            try {
                ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.beginTransaction();
                try {
                    acquire$ar$class_merging.executeUpdateDelete();
                    ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.setTransactionSuccessful();
                } finally {
                    ((SystemIdInfoDao_Impl) systemIdInfoDao).__db.endTransaction();
                }
            } finally {
                systemIdInfoDao_Impl.__preparedStmtOfRemoveSystemIdInfo_1.release$ar$class_merging(acquire$ar$class_merging);
            }
        }
    }

    @Override // androidx.work.impl.Scheduler
    public final boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public final void schedule(WorkSpec... workSpecArr) {
        int intValue;
        WorkDatabase workDatabase;
        final ViewModelStore viewModelStore = new ViewModelStore(this.mWorkDatabase, (byte[]) null);
        for (WorkSpec workSpec : workSpecArr) {
            this.mWorkDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = this.mWorkDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger.get$ar$ds$16341a92_0();
                    Log.w(TAG, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB");
                    this.mWorkDatabase.setTransactionSuccessful();
                    workDatabase = this.mWorkDatabase;
                } else if (workSpec2.state$ar$edu != 1) {
                    Logger.get$ar$ds$16341a92_0();
                    Log.w(TAG, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued");
                    this.mWorkDatabase.setTransactionSuccessful();
                    workDatabase = this.mWorkDatabase;
                } else {
                    WorkGenerationalId generationalId = AppCompatTextHelper.Api24Impl.generationalId(workSpec);
                    SystemIdInfo $default$getSystemIdInfo = SystemJobService.Api28Impl.$default$getSystemIdInfo(this.mWorkDatabase.systemIdInfoDao(), generationalId);
                    if ($default$getSystemIdInfo != null) {
                        intValue = $default$getSystemIdInfo.systemId;
                    } else {
                        Object obj = viewModelStore.ViewModelStore$ar$map;
                        final int i = Preference.DEFAULT_ORDER;
                        Object runInTransaction = ((RoomDatabase) obj).runInTransaction(new Callable(i) { // from class: androidx.work.impl.utils.IdGenerator$$ExternalSyntheticLambda1
                            public final /* synthetic */ int f$2 = Preference.DEFAULT_ORDER;

                            @Override // java.util.concurrent.Callable
                            public final Object call() {
                                ViewModelStore viewModelStore2 = ViewModelStore.this;
                                int nextId = AppCompatTextHelper.Api28Impl.nextId((WorkDatabase) viewModelStore2.ViewModelStore$ar$map, "next_job_scheduler_id");
                                if (nextId < 0) {
                                    AppCompatTextHelper.Api28Impl.updatePreference((WorkDatabase) viewModelStore2.ViewModelStore$ar$map, "next_job_scheduler_id", 1);
                                    nextId = 0;
                                }
                                return Integer.valueOf(nextId);
                            }
                        });
                        runInTransaction.getClass();
                        intValue = ((Number) runInTransaction).intValue();
                    }
                    if ($default$getSystemIdInfo == null) {
                        this.mWorkDatabase.systemIdInfoDao().insertSystemIdInfo(SystemJobService.Api31Impl.systemIdInfo(generationalId, intValue));
                    }
                    scheduleInternal(workSpec, intValue);
                    this.mWorkDatabase.setTransactionSuccessful();
                    workDatabase = this.mWorkDatabase;
                }
                workDatabase.endTransaction();
            } catch (Throwable th) {
                this.mWorkDatabase.endTransaction();
                throw th;
            }
        }
    }

    public final void scheduleInternal(WorkSpec workSpec, int i) {
        int i2;
        long currentTimeMillis;
        int i3;
        int i4;
        String str;
        String str2;
        int i5;
        Constraints constraints = workSpec.constraints;
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("EXTRA_WORK_SPEC_ID", workSpec.id);
        persistableBundle.putInt("EXTRA_WORK_SPEC_GENERATION", workSpec.generation);
        persistableBundle.putBoolean("EXTRA_IS_PERIODIC", workSpec.isPeriodic());
        JobInfo.Builder extras = new JobInfo.Builder(i, this.mSystemJobInfoConverter.mWorkServiceComponent).setRequiresCharging(constraints.requiresCharging).setRequiresDeviceIdle(constraints.requiresDeviceIdle).setExtras(persistableBundle);
        NetworkRequest requiredNetworkRequest = constraints.getRequiredNetworkRequest();
        int i6 = 0;
        if (Build.VERSION.SDK_INT >= 28 && requiredNetworkRequest != null) {
            extras.getClass();
            extras.setRequiredNetwork(requiredNetworkRequest);
        } else {
            int i7 = constraints.requiredNetworkType$ar$edu;
            if (Build.VERSION.SDK_INT >= 30 && i7 == 6) {
                extras.setRequiredNetwork(new NetworkRequest.Builder().addCapability(25).build());
            } else {
                int i8 = i7 - 1;
                if (i8 != 0) {
                    if (i8 != 1) {
                        if (i8 != 2) {
                            i2 = 3;
                            if (i8 != 3) {
                                i2 = 4;
                                if (i8 != 4) {
                                    Logger.get$ar$ds$16341a92_0();
                                    Objects.toString(AppCompatDelegateImpl.Api33Impl.toStringGenerated69893d619e9da448(i7));
                                }
                            }
                        } else {
                            i2 = 2;
                        }
                    }
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                extras.setRequiredNetworkType(i2);
            }
        }
        if (!constraints.requiresDeviceIdle) {
            if (workSpec.backoffPolicy$ar$edu == 2) {
                i5 = 0;
            } else {
                i5 = 1;
            }
            extras.setBackoffCriteria(workSpec.backoffDelayDuration, i5);
        }
        long calculateNextRunTime = workSpec.calculateNextRunTime();
        currentTimeMillis = System.currentTimeMillis();
        long max = Math.max(calculateNextRunTime - currentTimeMillis, 0L);
        if (Build.VERSION.SDK_INT <= 28) {
            extras.setMinimumLatency(max);
        } else if (max > 0) {
            extras.setMinimumLatency(max);
        } else if (!workSpec.expedited) {
            extras.setImportantWhileForeground(true);
        }
        if (constraints.hasContentUriTriggers()) {
            for (Constraints.ContentUriTrigger contentUriTrigger : constraints.contentUriTriggers) {
                extras.addTriggerContentUri(new JobInfo.TriggerContentUri(contentUriTrigger.uri, contentUriTrigger.isTriggeredForDescendants ? 1 : 0));
            }
            extras.setTriggerContentUpdateDelay(constraints.contentTriggerUpdateDelayMillis);
            extras.setTriggerContentMaxDelay(constraints.contentTriggerMaxDelayMillis);
        }
        extras.setPersisted(false);
        extras.setRequiresBatteryNotLow(constraints.requiresBatteryNotLow);
        extras.setRequiresStorageNotLow(constraints.requiresStorageNotLow);
        int i9 = workSpec.runAttemptCount;
        if (Build.VERSION.SDK_INT >= 31 && workSpec.expedited && i9 <= 0 && max <= 0) {
            extras.setExpedited(true);
        }
        if (Build.VERSION.SDK_INT >= 35 && (str2 = workSpec.traceTag) != null) {
            extras.setTraceTag(str2);
        }
        JobInfo build = extras.build();
        Logger.get$ar$ds$16341a92_0();
        String str3 = workSpec.id;
        try {
            if (this.mJobScheduler.schedule(build) == 0) {
                Logger.get$ar$ds$16341a92_0();
                Log.w(TAG, "Unable to schedule work ID " + workSpec.id);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy$ar$edu == 1) {
                    workSpec.expedited = false;
                    String.format("Scheduling a non-expedited job (work ID %s)", workSpec.id);
                    Logger.get$ar$ds$16341a92_0();
                    scheduleInternal(workSpec, i);
                }
            }
        } catch (IllegalStateException e) {
            Context context = this.mContext;
            WorkDatabase workDatabase = this.mWorkDatabase;
            int i10 = JobSchedulerExtKt.JobSchedulerExtKt$ar$NoOp;
            int size = workDatabase.workSpecDao().getScheduledWork().size();
            String str4 = "<faulty JobScheduler failed to getPendingJobs>";
            if (Build.VERSION.SDK_INT >= 34) {
                JobScheduler wmJobScheduler = JobSchedulerExtKt.getWmJobScheduler(context);
                List safePendingJobs = JobSchedulerExtKt.getSafePendingJobs(wmJobScheduler);
                if (safePendingJobs != null) {
                    List pendingJobs = getPendingJobs(context, wmJobScheduler);
                    if (pendingJobs != null) {
                        i4 = safePendingJobs.size() - pendingJobs.size();
                    } else {
                        i4 = 0;
                    }
                    String str5 = null;
                    if (i4 == 0) {
                        str = null;
                    } else {
                        str = i4 + " of which are not owned by WorkManager";
                    }
                    Object systemService = context.getSystemService("jobscheduler");
                    systemService.getClass();
                    List pendingJobs2 = getPendingJobs(context, (JobScheduler) systemService);
                    if (pendingJobs2 != null) {
                        i6 = pendingJobs2.size();
                    }
                    if (i6 != 0) {
                        str5 = i6 + " from WorkManager in the default namespace";
                    }
                    str4 = OnDeviceLanguageIdentificationLogEvent.joinToString$default$ar$ds(OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.filterNotNull(new String[]{safePendingJobs.size() + " jobs in \"androidx.work.systemjobscheduler\" namespace", str, str5}), ",\n", null, null, null, 62);
                }
            } else {
                List pendingJobs3 = getPendingJobs(context, JobSchedulerExtKt.getWmJobScheduler(context));
                if (pendingJobs3 != null) {
                    str4 = pendingJobs3.size() + " jobs from WorkManager";
                }
            }
            if (Build.VERSION.SDK_INT >= 31) {
                i3 = 150;
            } else {
                i3 = 100;
            }
            String str6 = "JobScheduler " + i3 + " job limit exceeded.\nIn JobScheduler there are " + str4 + ".\nThere are " + size + " jobs tracked by WorkManager's database;\nthe Configuration limit is 20.";
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, str6);
            throw new IllegalStateException(str6, e);
        } catch (Throwable th) {
            Logger.get$ar$ds$16341a92_0();
            String str7 = TAG;
            Objects.toString(workSpec);
            Log.e(str7, "Unable to schedule ".concat(String.valueOf(workSpec)), th);
        }
    }
}
