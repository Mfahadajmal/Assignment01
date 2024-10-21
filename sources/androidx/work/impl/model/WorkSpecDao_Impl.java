package androidx.work.impl.model;

import android.database.Cursor;
import android.net.NetworkRequest;
import android.os.Build;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextHelper;
import androidx.core.widget.ImageViewCompat$Api21Impl;
import androidx.core.widget.NestedScrollView;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.NetworkRequest28;
import androidx.work.impl.utils.NetworkRequest31;
import androidx.work.impl.utils.NetworkRequestCompat;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkSpecDao_Impl implements WorkSpecDao {
    public final RoomDatabase __db;
    public final EntityInsertionAdapter __insertionAdapterOfWorkSpec;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    public final SharedSQLiteStatement __preparedStmtOfIncrementPeriodCount;
    public final SharedSQLiteStatement __preparedStmtOfIncrementWorkSpecRunAttemptCount;
    private final SharedSQLiteStatement __preparedStmtOfMarkWorkSpecScheduled;
    public final SharedSQLiteStatement __preparedStmtOfResetScheduledState;
    private final SharedSQLiteStatement __preparedStmtOfResetWorkSpecNextScheduleTimeOverride;
    public final SharedSQLiteStatement __preparedStmtOfResetWorkSpecRunAttemptCount;
    public final SharedSQLiteStatement __preparedStmtOfSetCancelledState;
    private final SharedSQLiteStatement __preparedStmtOfSetLastEnqueueTime;
    private final SharedSQLiteStatement __preparedStmtOfSetOutput;
    private final SharedSQLiteStatement __preparedStmtOfSetState;
    private final SharedSQLiteStatement __preparedStmtOfSetStopReason;

    /* compiled from: PG */
    /* renamed from: androidx.work.impl.model.WorkSpecDao_Impl$25, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass25 implements Callable {
        final /* synthetic */ RoomSQLiteQuery val$_statement;

        public AnonymousClass25(RoomSQLiteQuery roomSQLiteQuery) {
            this.val$_statement = roomSQLiteQuery;
        }

        @Override // java.util.concurrent.Callable
        public final Boolean call() {
            boolean z;
            Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(WorkSpecDao_Impl.this.__db, this.val$_statement);
            try {
                if (query$ar$ds$e1ca310e_0.moveToFirst()) {
                    z = Boolean.valueOf(query$ar$ds$e1ca310e_0.getInt(0) != 0);
                } else {
                    z = false;
                }
                return z;
            } finally {
                query$ar$ds$e1ca310e_0.close();
            }
        }

        protected final void finalize() {
            this.val$_statement.release();
        }
    }

    public WorkSpecDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkSpec = new EntityInsertionAdapter(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            protected final /* bridge */ /* synthetic */ void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj) {
                int i;
                int i2;
                int[] intArray;
                int[] intArray2;
                byte[] byteArray;
                byte[] byteArray2;
                WorkSpec workSpec = (WorkSpec) obj;
                int i3 = 1;
                frameworkSQLiteStatement.bindString(1, workSpec.id);
                frameworkSQLiteStatement.bindLong(2, AppCompatTextHelper.Api26Impl.stateToInt$ar$edu(workSpec.state$ar$edu));
                frameworkSQLiteStatement.bindString(3, workSpec.workerClassName);
                frameworkSQLiteStatement.bindString(4, workSpec.inputMergerClassName);
                frameworkSQLiteStatement.bindBlob(5, Data.toByteArrayInternalV1(workSpec.input));
                frameworkSQLiteStatement.bindBlob(6, Data.toByteArrayInternalV1(workSpec.output));
                frameworkSQLiteStatement.bindLong(7, workSpec.initialDelay);
                frameworkSQLiteStatement.bindLong(8, workSpec.intervalDuration);
                frameworkSQLiteStatement.bindLong(9, workSpec.flexDuration);
                frameworkSQLiteStatement.bindLong(10, workSpec.runAttemptCount);
                int i4 = workSpec.backoffPolicy$ar$edu;
                if (i4 != 0) {
                    if (i4 - 1 != 0) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                    frameworkSQLiteStatement.bindLong(11, i);
                    frameworkSQLiteStatement.bindLong(12, workSpec.backoffDelayDuration);
                    frameworkSQLiteStatement.bindLong(13, workSpec.lastEnqueueTime);
                    frameworkSQLiteStatement.bindLong(14, workSpec.minimumRetentionDuration);
                    frameworkSQLiteStatement.bindLong(15, workSpec.scheduleRequestedAt);
                    frameworkSQLiteStatement.bindLong(16, workSpec.expedited ? 1L : 0L);
                    int i5 = workSpec.outOfQuotaPolicy$ar$edu;
                    if (i5 != 0) {
                        if (i5 - 1 != 0) {
                            i2 = 1;
                        } else {
                            i2 = 0;
                        }
                        frameworkSQLiteStatement.bindLong(17, i2);
                        frameworkSQLiteStatement.bindLong(18, workSpec.periodCount);
                        frameworkSQLiteStatement.bindLong(19, workSpec.generation);
                        frameworkSQLiteStatement.bindLong(20, workSpec.nextScheduleTimeOverride);
                        frameworkSQLiteStatement.bindLong(21, workSpec.nextScheduleTimeOverrideGeneration);
                        frameworkSQLiteStatement.bindLong(22, workSpec.stopReason);
                        String str = workSpec.traceTag;
                        if (str == null) {
                            frameworkSQLiteStatement.bindNull(23);
                        } else {
                            frameworkSQLiteStatement.bindString(23, str);
                        }
                        Constraints constraints = workSpec.constraints;
                        int i6 = constraints.requiredNetworkType$ar$edu;
                        int i7 = i6 - 1;
                        if (i7 != 0) {
                            if (i7 != 1) {
                                if (i7 != 2) {
                                    if (i7 != 3) {
                                        if (i7 != 4) {
                                            if (Build.VERSION.SDK_INT >= 30 && i6 == 6) {
                                                i3 = 5;
                                            } else {
                                                throw new IllegalArgumentException("Could not convert " + ((Object) AppCompatDelegateImpl.Api33Impl.toStringGenerated69893d619e9da448(i6)) + " to int");
                                            }
                                        } else {
                                            i3 = 4;
                                        }
                                    } else {
                                        i3 = 3;
                                    }
                                } else {
                                    i3 = 2;
                                }
                            }
                        } else {
                            i3 = 0;
                        }
                        frameworkSQLiteStatement.bindLong(24, i3);
                        NetworkRequestCompat networkRequestCompat = constraints.requiredNetworkRequestCompat;
                        if (Build.VERSION.SDK_INT < 28) {
                            byteArray = new byte[0];
                        } else {
                            Object obj2 = networkRequestCompat.wrapped;
                            if (obj2 == null) {
                                byteArray = new byte[0];
                            } else {
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                try {
                                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                                    try {
                                        if (Build.VERSION.SDK_INT >= 31) {
                                            intArray = NetworkRequest31.INSTANCE.transportTypes((NetworkRequest) obj2);
                                        } else {
                                            int[] iArr = {2, 0, 3, 6, 9, 8, 4, 1, 5};
                                            ArrayList arrayList = new ArrayList();
                                            for (int i8 = 0; i8 < 9; i8++) {
                                                int i9 = iArr[i8];
                                                if (NetworkRequest28.INSTANCE.hasTransport$work_runtime_release((NetworkRequest) obj2, i9)) {
                                                    arrayList.add(Integer.valueOf(i9));
                                                }
                                            }
                                            intArray = OnDeviceLanguageIdentificationLogEvent.toIntArray(arrayList);
                                        }
                                        if (Build.VERSION.SDK_INT >= 31) {
                                            intArray2 = NetworkRequest31.INSTANCE.capabilities((NetworkRequest) obj2);
                                        } else {
                                            int[] iArr2 = {17, 5, 2, 10, 29, 19, 3, 32, 7, 4, 12, 23, 0, 33, 20, 11, 13, 18, 21, 15, 35, 34, 8, 1, 25, 14, 16, 6, 9};
                                            ArrayList arrayList2 = new ArrayList();
                                            for (int i10 = 0; i10 < 29; i10++) {
                                                int i11 = iArr2[i10];
                                                if (NetworkRequest28.INSTANCE.hasCapability$work_runtime_release((NetworkRequest) obj2, i11)) {
                                                    arrayList2.add(Integer.valueOf(i11));
                                                }
                                            }
                                            intArray2 = OnDeviceLanguageIdentificationLogEvent.toIntArray(arrayList2);
                                        }
                                        objectOutputStream.writeInt(intArray.length);
                                        for (int i12 : intArray) {
                                            objectOutputStream.writeInt(i12);
                                        }
                                        objectOutputStream.writeInt(intArray2.length);
                                        for (int i13 : intArray2) {
                                            objectOutputStream.writeInt(i13);
                                        }
                                        OnDeviceShadowRemovalLogEvent.closeFinally(objectOutputStream, null);
                                        OnDeviceShadowRemovalLogEvent.closeFinally(byteArrayOutputStream, null);
                                        byteArray = byteArrayOutputStream.toByteArray();
                                        byteArray.getClass();
                                    } finally {
                                        try {
                                            throw th;
                                        } finally {
                                        }
                                    }
                                } finally {
                                    try {
                                        throw th;
                                    } finally {
                                    }
                                }
                            }
                        }
                        frameworkSQLiteStatement.bindBlob(25, byteArray);
                        frameworkSQLiteStatement.bindLong(26, constraints.requiresCharging ? 1L : 0L);
                        frameworkSQLiteStatement.bindLong(27, constraints.requiresDeviceIdle ? 1L : 0L);
                        frameworkSQLiteStatement.bindLong(28, constraints.requiresBatteryNotLow ? 1L : 0L);
                        frameworkSQLiteStatement.bindLong(29, constraints.requiresStorageNotLow ? 1L : 0L);
                        frameworkSQLiteStatement.bindLong(30, constraints.contentTriggerUpdateDelayMillis);
                        frameworkSQLiteStatement.bindLong(31, constraints.contentTriggerMaxDelayMillis);
                        Set<Constraints.ContentUriTrigger> set = constraints.contentUriTriggers;
                        if (set.isEmpty()) {
                            byteArray2 = new byte[0];
                        } else {
                            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                            try {
                                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream2);
                                try {
                                    objectOutputStream2.writeInt(set.size());
                                    for (Constraints.ContentUriTrigger contentUriTrigger : set) {
                                        objectOutputStream2.writeUTF(contentUriTrigger.uri.toString());
                                        objectOutputStream2.writeBoolean(contentUriTrigger.isTriggeredForDescendants);
                                    }
                                    OnDeviceShadowRemovalLogEvent.closeFinally(objectOutputStream2, null);
                                    OnDeviceShadowRemovalLogEvent.closeFinally(byteArrayOutputStream2, null);
                                    byteArray2 = byteArrayOutputStream2.toByteArray();
                                    byteArray2.getClass();
                                } finally {
                                }
                            } finally {
                            }
                        }
                        frameworkSQLiteStatement.bindBlob(32, byteArray2);
                        return;
                    }
                    throw null;
                }
                throw null;
            }

            @Override // androidx.room.SharedSQLiteStatement
            protected final String createQuery() {
                return "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`trace_tag`,`required_network_type`,`required_network_request`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        new EntityDeletionOrUpdateAdapter(roomDatabase, null);
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "DELETE FROM workspec WHERE id=?";
            }
        };
        this.__preparedStmtOfSetState = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET state=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetCancelledState = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET stop_reason = CASE WHEN state=1 THEN 1 ELSE -256 END, state=5 WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementPeriodCount = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET period_count=period_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetOutput = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.7
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET output=? WHERE id=?";
            }
        };
        this.__preparedStmtOfSetLastEnqueueTime = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.8
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET last_enqueue_time=? WHERE id=?";
            }
        };
        this.__preparedStmtOfIncrementWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.9
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecRunAttemptCount = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.10
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET run_attempt_count=0 WHERE id=?";
            }
        };
        new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.11
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.12
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET next_schedule_time_override=9223372036854775807 WHERE (id=? AND next_schedule_time_override_generation=?)";
            }
        };
        this.__preparedStmtOfMarkWorkSpecScheduled = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.13
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=? WHERE id=?";
            }
        };
        this.__preparedStmtOfResetScheduledState = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.14
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)";
            }
        };
        new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.15
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "DELETE FROM workspec WHERE state IN (2, 3, 5) AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))";
            }
        };
        new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.16
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET generation=generation+1 WHERE id=?";
            }
        };
        this.__preparedStmtOfSetStopReason = new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkSpecDao_Impl.17
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "UPDATE workspec SET stop_reason=? WHERE id=?";
            }
        };
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final List getAllEligibleWorkSpecsForScheduling$ar$ds() {
        RoomSQLiteQuery roomSQLiteQuery;
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
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?", 1);
        acquire.bindLong(1, 200L);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
        try {
            int columnIndexOrThrow = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "id");
            int columnIndexOrThrow2 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "state");
            int columnIndexOrThrow3 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "worker_class_name");
            int columnIndexOrThrow4 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input_merger_class_name");
            int columnIndexOrThrow5 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "input");
            int columnIndexOrThrow6 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "output");
            int columnIndexOrThrow7 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "initial_delay");
            int columnIndexOrThrow8 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "interval_duration");
            int columnIndexOrThrow9 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "flex_duration");
            int columnIndexOrThrow10 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "run_attempt_count");
            int columnIndexOrThrow11 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_policy");
            int columnIndexOrThrow12 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "backoff_delay_duration");
            int columnIndexOrThrow13 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "last_enqueue_time");
            int columnIndexOrThrow14 = ImageViewCompat$Api21Impl.getColumnIndexOrThrow(query$ar$ds$e1ca310e_0, "minimum_retention_duration");
            roomSQLiteQuery = acquire;
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
                return arrayList;
            } catch (Throwable th) {
                th = th;
                query$ar$ds$e1ca310e_0.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final List getRunningWork() {
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
        RoomDatabase roomDatabase = this.__db;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=1", 0);
        roomDatabase.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
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
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final List getScheduledWork() {
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
        RoomDatabase roomDatabase = this.__db;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1", 0);
        roomDatabase.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
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
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final int getState$ar$edu$fd856834_0(String str) {
        Integer valueOf;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT state FROM workspec WHERE id=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
        try {
            int i = 0;
            if (query$ar$ds$e1ca310e_0.moveToFirst()) {
                if (query$ar$ds$e1ca310e_0.isNull(0)) {
                    valueOf = null;
                } else {
                    valueOf = Integer.valueOf(query$ar$ds$e1ca310e_0.getInt(0));
                }
                if (valueOf != null) {
                    i = AppCompatTextHelper.Api26Impl.intToState$ar$edu(valueOf.intValue());
                }
            }
            return i;
        } finally {
            query$ar$ds$e1ca310e_0.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final WorkSpec getWorkSpec(String str) {
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
        boolean z;
        int i;
        boolean z2;
        int i2;
        boolean z3;
        int i3;
        boolean z4;
        int i4;
        boolean z5;
        int i5;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM workspec WHERE id=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
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
            WorkSpec workSpec = null;
            String string = null;
            if (query$ar$ds$e1ca310e_0.moveToFirst()) {
                String string2 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow);
                int intToState$ar$edu = AppCompatTextHelper.Api26Impl.intToState$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow2));
                String string3 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow3);
                String string4 = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow4);
                Data fromByteArray = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow5));
                Data fromByteArray2 = Data.fromByteArray(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow6));
                long j = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow7);
                long j2 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow8);
                long j3 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow9);
                int i6 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow10);
                int intToBackoffPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToBackoffPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow11));
                long j4 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow12);
                long j5 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow13);
                long j6 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow14);
                long j7 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow15);
                if (query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow16) != 0) {
                    i = columnIndexOrThrow17;
                    z = true;
                } else {
                    z = false;
                    i = columnIndexOrThrow17;
                }
                int intToOutOfQuotaPolicy$ar$edu = AppCompatTextHelper.Api26Impl.intToOutOfQuotaPolicy$ar$edu(query$ar$ds$e1ca310e_0.getInt(i));
                int i7 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow18);
                int i8 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow19);
                long j8 = query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow20);
                int i9 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow21);
                int i10 = query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow22);
                if (!query$ar$ds$e1ca310e_0.isNull(columnIndexOrThrow23)) {
                    string = query$ar$ds$e1ca310e_0.getString(columnIndexOrThrow23);
                }
                String str2 = string;
                int intToNetworkType$ar$edu = AppCompatTextHelper.Api26Impl.intToNetworkType$ar$edu(query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow24));
                NetworkRequestCompat networkRequest$work_runtime_release = AppCompatTextHelper.Api26Impl.toNetworkRequest$work_runtime_release(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow25));
                if (query$ar$ds$e1ca310e_0.getInt(columnIndexOrThrow26) != 0) {
                    i2 = columnIndexOrThrow27;
                    z2 = true;
                } else {
                    z2 = false;
                    i2 = columnIndexOrThrow27;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i2) != 0) {
                    i3 = columnIndexOrThrow28;
                    z3 = true;
                } else {
                    z3 = false;
                    i3 = columnIndexOrThrow28;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i3) != 0) {
                    i4 = columnIndexOrThrow29;
                    z4 = true;
                } else {
                    z4 = false;
                    i4 = columnIndexOrThrow29;
                }
                if (query$ar$ds$e1ca310e_0.getInt(i4) != 0) {
                    i5 = columnIndexOrThrow30;
                    z5 = true;
                } else {
                    z5 = false;
                    i5 = columnIndexOrThrow30;
                }
                workSpec = new WorkSpec(string2, intToState$ar$edu, string3, string4, fromByteArray, fromByteArray2, j, j2, j3, new Constraints(networkRequest$work_runtime_release, intToNetworkType$ar$edu, z2, z3, z4, z5, query$ar$ds$e1ca310e_0.getLong(i5), query$ar$ds$e1ca310e_0.getLong(columnIndexOrThrow31), AppCompatTextHelper.Api26Impl.byteArrayToSetOfTriggers(query$ar$ds$e1ca310e_0.getBlob(columnIndexOrThrow32))), i6, intToBackoffPolicy$ar$edu, j4, j5, j6, j7, z, intToOutOfQuotaPolicy$ar$edu, i7, i8, j8, i9, i10, str2);
            }
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            return workSpec;
        } catch (Throwable th2) {
            th = th2;
            query$ar$ds$e1ca310e_0.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final List getWorkSpecIdAndStatesForName$ar$ds() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)", 1);
        acquire.bindString(1, null);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
        try {
            ArrayList arrayList = new ArrayList(query$ar$ds$e1ca310e_0.getCount());
            while (query$ar$ds$e1ca310e_0.moveToNext()) {
                arrayList.add(new WorkSpec.IdAndState(query$ar$ds$e1ca310e_0.getString(0), AppCompatTextHelper.Api26Impl.intToState$ar$edu(query$ar$ds$e1ca310e_0.getInt(1))));
            }
            return arrayList;
        } finally {
            query$ar$ds$e1ca310e_0.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void markWorkSpecScheduled$ar$ds(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfMarkWorkSpecScheduled.acquire$ar$class_merging();
        acquire$ar$class_merging.bindLong(1, j);
        acquire$ar$class_merging.bindString(2, str);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfMarkWorkSpecScheduled.release$ar$class_merging(acquire$ar$class_merging);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void resetWorkSpecNextScheduleTimeOverride(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.acquire$ar$class_merging();
        acquire$ar$class_merging.bindString(1, str);
        acquire$ar$class_merging.bindLong(2, i);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfResetWorkSpecNextScheduleTimeOverride.release$ar$class_merging(acquire$ar$class_merging);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void setLastEnqueueTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfSetLastEnqueueTime.acquire$ar$class_merging();
        acquire$ar$class_merging.bindLong(1, j);
        acquire$ar$class_merging.bindString(2, str);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetLastEnqueueTime.release$ar$class_merging(acquire$ar$class_merging);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void setOutput(String str, Data data) {
        this.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfSetOutput.acquire$ar$class_merging();
        acquire$ar$class_merging.bindBlob(1, Data.toByteArrayInternalV1(data));
        acquire$ar$class_merging.bindString(2, str);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetOutput.release$ar$class_merging(acquire$ar$class_merging);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void setState$ar$edu$ar$ds(int i, String str) {
        this.__db.assertNotSuspendingTransaction();
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfSetState.acquire$ar$class_merging();
        acquire$ar$class_merging.bindLong(1, AppCompatTextHelper.Api26Impl.stateToInt$ar$edu(i));
        acquire$ar$class_merging.bindString(2, str);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetState.release$ar$class_merging(acquire$ar$class_merging);
        }
    }

    @Override // androidx.work.impl.model.WorkSpecDao
    public final void setStopReason(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        long j = i;
        FrameworkSQLiteStatement acquire$ar$class_merging = this.__preparedStmtOfSetStopReason.acquire$ar$class_merging();
        acquire$ar$class_merging.bindLong(1, j);
        acquire$ar$class_merging.bindString(2, str);
        try {
            this.__db.beginTransaction();
            try {
                acquire$ar$class_merging.executeUpdateDelete();
                this.__db.setTransactionSuccessful();
            } finally {
                this.__db.endTransaction();
            }
        } finally {
            this.__preparedStmtOfSetStopReason.release$ar$class_merging(acquire$ar$class_merging);
        }
    }
}
