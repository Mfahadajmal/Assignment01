package androidx.work.impl.workers;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.WindowCallbackWrapper;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.core.widget.ImageViewCompat$Api21Impl;
import androidx.core.widget.NestedScrollView;
import androidx.room.RoomSQLiteQuery;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ListenableWorker$Result$Success;
import androidx.work.Logger;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.NetworkRequestCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiagnosticsWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        context.getClass();
        workerParameters.getClass();
    }

    @Override // androidx.work.Worker
    public final AppCompatDelegateImpl.Api33Impl doWork$ar$class_merging$ar$class_merging() {
        long currentTimeMillis;
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int columnIndexOrThrow14;
        SystemIdInfoDao systemIdInfoDao;
        WorkNameDao workNameDao;
        WorkTagDao workTagDao;
        int i;
        boolean z;
        String string;
        int i2;
        boolean z2;
        int i3;
        boolean z3;
        int i4;
        boolean z4;
        int i5;
        boolean z5;
        WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(this.mAppContext);
        WorkDatabase workDatabase = workManagerImpl.mWorkDatabase;
        workDatabase.getClass();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkNameDao workNameDao2 = workDatabase.workNameDao();
        WorkTagDao workTagDao2 = workDatabase.workTagDao();
        SystemIdInfoDao systemIdInfoDao2 = workDatabase.systemIdInfoDao();
        WindowCallbackWrapper.Api26Impl api26Impl = workManagerImpl.mConfiguration.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;
        currentTimeMillis = System.currentTimeMillis();
        long millis = currentTimeMillis - TimeUnit.DAYS.toMillis(1L);
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC", 1);
        acquire.bindLong(1, millis);
        WorkSpecDao_Impl workSpecDao_Impl = (WorkSpecDao_Impl) workSpecDao;
        workSpecDao_Impl.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(workSpecDao_Impl.__db, acquire);
        try {
            columnIndexOrThrow = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "id");
            columnIndexOrThrow2 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "state");
            columnIndexOrThrow3 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "worker_class_name");
            columnIndexOrThrow4 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input_merger_class_name");
            columnIndexOrThrow5 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input");
            columnIndexOrThrow6 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "output");
            columnIndexOrThrow7 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "initial_delay");
            columnIndexOrThrow8 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "interval_duration");
            columnIndexOrThrow9 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "flex_duration");
            columnIndexOrThrow10 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "run_attempt_count");
            columnIndexOrThrow11 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_policy");
            columnIndexOrThrow12 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_delay_duration");
            columnIndexOrThrow13 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "last_enqueue_time");
            columnIndexOrThrow14 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow15 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "schedule_requested_at");
            int columnIndexOrThrow16 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "run_in_foreground");
            int columnIndexOrThrow17 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "out_of_quota_policy");
            int columnIndexOrThrow18 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "period_count");
            int columnIndexOrThrow19 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "generation");
            int columnIndexOrThrow20 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "next_schedule_time_override");
            int columnIndexOrThrow21 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "next_schedule_time_override_generation");
            int columnIndexOrThrow22 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "stop_reason");
            int columnIndexOrThrow23 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trace_tag");
            int columnIndexOrThrow24 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "required_network_type");
            int columnIndexOrThrow25 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "required_network_request");
            int columnIndexOrThrow26 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_charging");
            int columnIndexOrThrow27 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_device_idle");
            int columnIndexOrThrow28 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_battery_not_low");
            int columnIndexOrThrow29 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "requires_storage_not_low");
            int columnIndexOrThrow30 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trigger_content_update_delay");
            int columnIndexOrThrow31 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "trigger_max_content_delay");
            int columnIndexOrThrow32 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "content_uri_triggers");
            int i6 = columnIndexOrThrow14;
            ArrayList arrayList = new ArrayList(query$ar$ds$e1ca310e_0.getCount());
            while (query$ar$ds$e1ca310e_0.moveToNext()) {
                String string2 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow);
                int intToState$ar$edu = AppCompatTextHelper.Api26Impl.intToState$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow2));
                String string3 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow3);
                String string4 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow4);
                Data fromByteArray = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow5));
                Data fromByteArray2 = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow6));
                long j = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow7);
                long j2 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow8);
                long j3 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow9);
                int i7 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow10);
                int intToBackoffPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToBackoffPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow11));
                long j4 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow12);
                long j5 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow13);
                int i8 = i6;
                long j6 = query$ar$ds$e1ca310e_0.getLong(i8);
                int i9 = columnIndexOrThrow;
                int i10 = columnIndexOrThrow15;
                long j7 = query$ar$ds$e1ca310e_0.getLong(i10);
                columnIndexOrThrow15 = i10;
                int i11 = columnIndexOrThrow16;
                if (query$ar$ds$e1ca310e_0.getInt(i11) != 0) {
                    columnIndexOrThrow16 = i11;
                    i = columnIndexOrThrow17;
                    z = true;
                } else {
                    columnIndexOrThrow16 = i11;
                    i = columnIndexOrThrow17;
                    z = false;
                }
                int intToOutOfQuotaPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToOutOfQuotaPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(i));
                columnIndexOrThrow17 = i;
                int i12 = columnIndexOrThrow18;
                int i13 = query$ar$ds$e1ca310e_0.getInt(i12);
                columnIndexOrThrow18 = i12;
                int i14 = columnIndexOrThrow19;
                int i15 = query$ar$ds$e1ca310e_0.getInt(i14);
                columnIndexOrThrow19 = i14;
                int i16 = columnIndexOrThrow20;
                long j8 = query$ar$ds$e1ca310e_0.getLong(i16);
                columnIndexOrThrow20 = i16;
                int i17 = columnIndexOrThrow21;
                int i18 = query$ar$ds$e1ca310e_0.getInt(i17);
                columnIndexOrThrow21 = i17;
                int i19 = columnIndexOrThrow22;
                int i20 = query$ar$ds$e1ca310e_0.getInt(i19);
                columnIndexOrThrow22 = i19;
                int i21 = columnIndexOrThrow23;
                if (query$ar$ds$e1ca310e_0.isNull(i21)) {
                    string = null;
                } else {
                    string = query$ar$ds$e1ca310e_0.getString(i21);
                }
                String str = string;
                columnIndexOrThrow23 = i21;
                int i22 = columnIndexOrThrow24;
                int intToNetworkType$ar$edu = AppCompatTextHelper.Api26Impl.intToNetworkType$ar$edu(query$ar$ds$e1ca310e_0.getInt(i22));
                columnIndexOrThrow24 = i22;
                int i23 = columnIndexOrThrow25;
                NetworkRequestCompat networkRequest$work_runtime_release = AppCompatTextHelper.Api26Impl.toNetworkRequest$work_runtime_release(query$ar$ds$e1ca310e_0.getBlob(i23));
                columnIndexOrThrow25 = i23;
                int i24 = columnIndexOrThrow26;
                if (query$ar$ds$e1ca310e_0.getInt(i24) != 0) {
                    columnIndexOrThrow26 = i24;
                    i2 = columnIndexOrThrow27;
                    z2 = true;
                } else {
                    columnIndexOrThrow26 = i24;
                    i2 = columnIndexOrThrow27;
                    z2 = false;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i2) != 0) {
                    columnIndexOrThrow27 = i2;
                    i3 = columnIndexOrThrow28;
                    z3 = true;
                } else {
                    columnIndexOrThrow27 = i2;
                    i3 = columnIndexOrThrow28;
                    z3 = false;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i3) != 0) {
                    columnIndexOrThrow28 = i3;
                    i4 = columnIndexOrThrow29;
                    z4 = true;
                } else {
                    columnIndexOrThrow28 = i3;
                    i4 = columnIndexOrThrow29;
                    z4 = false;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i4) != 0) {
                    columnIndexOrThrow29 = i4;
                    i5 = columnIndexOrThrow30;
                    z5 = true;
                } else {
                    columnIndexOrThrow29 = i4;
                    i5 = columnIndexOrThrow30;
                    z5 = false;
                }
                long j9 = query$ar$ds$e1ca310e_0.getLong(i5);
                columnIndexOrThrow30 = i5;
                int i25 = columnIndexOrThrow31;
                long j10 = query$ar$ds$e1ca310e_0.getLong(i25);
                columnIndexOrThrow31 = i25;
                int i26 = columnIndexOrThrow32;
                columnIndexOrThrow32 = i26;
                arrayList.add(new WorkSpec(string2, intToState$ar$edu, string3, string4, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(networkRequest$work_runtime_release, intToNetworkType$ar$edu, z2, z3, z4, z5, j9, j10, AppCompatTextHelper.Api26Impl.byteArrayToSetOfTriggers(query$ar$ds$e1ca310e_0.getBlob(i26))), i7, intToBackoffPolicy$ar$edu, j4, j5, j6, j7, z, intToOutOfQuotaPolicy$ar$edu, i13, i15, j8, i18, i20, str));
                columnIndexOrThrow = i9;
                i6 = i8;
            }
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            List runningWork = workSpecDao.getRunningWork();
            List allEligibleWorkSpecsForScheduling$ar$ds = workSpecDao.getAllEligibleWorkSpecsForScheduling$ar$ds();
            if (!arrayList.isEmpty()) {
                Logger.get$ar$ds$16341a92_0();
                int i27 = DiagnosticsWorkerKt.DiagnosticsWorkerKt$ar$NoOp;
                Logger.get$ar$ds$16341a92_0();
                systemIdInfoDao = systemIdInfoDao2;
                workNameDao = workNameDao2;
                workTagDao = workTagDao2;
                DiagnosticsWorkerKt.workSpecRows$ar$ds(workNameDao, workTagDao, systemIdInfoDao, arrayList);
            } else {
                systemIdInfoDao = systemIdInfoDao2;
                workNameDao = workNameDao2;
                workTagDao = workTagDao2;
            }
            if (!runningWork.isEmpty()) {
                Logger.get$ar$ds$16341a92_0();
                int i28 = DiagnosticsWorkerKt.DiagnosticsWorkerKt$ar$NoOp;
                Logger.get$ar$ds$16341a92_0();
                DiagnosticsWorkerKt.workSpecRows$ar$ds(workNameDao, workTagDao, systemIdInfoDao, runningWork);
            }
            if (!allEligibleWorkSpecsForScheduling$ar$ds.isEmpty()) {
                Logger.get$ar$ds$16341a92_0();
                int i29 = DiagnosticsWorkerKt.DiagnosticsWorkerKt$ar$NoOp;
                Logger.get$ar$ds$16341a92_0();
                DiagnosticsWorkerKt.workSpecRows$ar$ds(workNameDao, workTagDao, systemIdInfoDao, allEligibleWorkSpecsForScheduling$ar$ds);
            }
            return new ListenableWorker$Result$Success();
        } catch (Throwable th2) {
            th = th2;
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }
}
