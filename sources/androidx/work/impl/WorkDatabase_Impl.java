package androidx.work.impl;

import _COROUTINE._BOUNDARY;
import androidx.lifecycle.ReportFragment;
import androidx.room.DatabaseConfiguration;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.PreferenceDao;
import androidx.work.impl.model.PreferenceDao_Impl;
import androidx.work.impl.model.RawWorkInfoDao;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.SystemIdInfoDao_Impl;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkNameDao_Impl;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkProgressDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkSpecDao_Impl;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.model.WorkTagDao_Impl;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import io.grpc.internal.RetriableStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkDatabase_Impl extends WorkDatabase {
    private volatile DependencyDao _dependencyDao;
    private volatile PreferenceDao _preferenceDao;
    private volatile SystemIdInfoDao _systemIdInfoDao;
    private volatile WorkNameDao _workNameDao;
    private volatile WorkProgressDao _workProgressDao;
    private volatile WorkSpecDao _workSpecDao;
    private volatile WorkTagDao _workTagDao;

    /* compiled from: PG */
    /* renamed from: androidx.work.impl.WorkDatabase_Impl$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends RoomOpenHelper.Delegate {
        public AnonymousClass1() {
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `trace_tag` TEXT, `required_network_type` INTEGER NOT NULL, `required_network_request` BLOB NOT NULL DEFAULT x'', `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '86254750241babac4b8d52996a675549')");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public final RetriableStream.HedgingPlan onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
            hashMap.put("prerequisite_id", new TableInfo.Column("prerequisite_id", "TEXT", true, 2, null, 1));
            HashSet hashSet = new HashSet(2);
            hashSet.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            hashSet.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("prerequisite_id"), Arrays.asList("id")));
            HashSet hashSet2 = new HashSet(2);
            hashSet2.add(new TableInfo.Index("index_Dependency_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
            hashSet2.add(new TableInfo.Index("index_Dependency_prerequisite_id", false, Arrays.asList("prerequisite_id"), Arrays.asList("ASC")));
            TableInfo tableInfo = new TableInfo("Dependency", hashMap, hashSet, hashSet2);
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "Dependency");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo, read)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read, tableInfo, "Dependency(androidx.work.impl.model.Dependency).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap2 = new HashMap(32);
            hashMap2.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
            hashMap2.put("state", new TableInfo.Column("state", "INTEGER", true, 0, null, 1));
            hashMap2.put("worker_class_name", new TableInfo.Column("worker_class_name", "TEXT", true, 0, null, 1));
            hashMap2.put("input_merger_class_name", new TableInfo.Column("input_merger_class_name", "TEXT", true, 0, null, 1));
            hashMap2.put("input", new TableInfo.Column("input", "BLOB", true, 0, null, 1));
            hashMap2.put("output", new TableInfo.Column("output", "BLOB", true, 0, null, 1));
            hashMap2.put("initial_delay", new TableInfo.Column("initial_delay", "INTEGER", true, 0, null, 1));
            hashMap2.put("interval_duration", new TableInfo.Column("interval_duration", "INTEGER", true, 0, null, 1));
            hashMap2.put("flex_duration", new TableInfo.Column("flex_duration", "INTEGER", true, 0, null, 1));
            hashMap2.put("run_attempt_count", new TableInfo.Column("run_attempt_count", "INTEGER", true, 0, null, 1));
            hashMap2.put("backoff_policy", new TableInfo.Column("backoff_policy", "INTEGER", true, 0, null, 1));
            hashMap2.put("backoff_delay_duration", new TableInfo.Column("backoff_delay_duration", "INTEGER", true, 0, null, 1));
            hashMap2.put("last_enqueue_time", new TableInfo.Column("last_enqueue_time", "INTEGER", true, 0, "-1", 1));
            hashMap2.put("minimum_retention_duration", new TableInfo.Column("minimum_retention_duration", "INTEGER", true, 0, null, 1));
            hashMap2.put("schedule_requested_at", new TableInfo.Column("schedule_requested_at", "INTEGER", true, 0, null, 1));
            hashMap2.put("run_in_foreground", new TableInfo.Column("run_in_foreground", "INTEGER", true, 0, null, 1));
            hashMap2.put("out_of_quota_policy", new TableInfo.Column("out_of_quota_policy", "INTEGER", true, 0, null, 1));
            hashMap2.put("period_count", new TableInfo.Column("period_count", "INTEGER", true, 0, "0", 1));
            hashMap2.put("generation", new TableInfo.Column("generation", "INTEGER", true, 0, "0", 1));
            hashMap2.put("next_schedule_time_override", new TableInfo.Column("next_schedule_time_override", "INTEGER", true, 0, "9223372036854775807", 1));
            hashMap2.put("next_schedule_time_override_generation", new TableInfo.Column("next_schedule_time_override_generation", "INTEGER", true, 0, "0", 1));
            hashMap2.put("stop_reason", new TableInfo.Column("stop_reason", "INTEGER", true, 0, "-256", 1));
            hashMap2.put("trace_tag", new TableInfo.Column("trace_tag", "TEXT", false, 0, null, 1));
            hashMap2.put("required_network_type", new TableInfo.Column("required_network_type", "INTEGER", true, 0, null, 1));
            hashMap2.put("required_network_request", new TableInfo.Column("required_network_request", "BLOB", true, 0, "x''", 1));
            hashMap2.put("requires_charging", new TableInfo.Column("requires_charging", "INTEGER", true, 0, null, 1));
            hashMap2.put("requires_device_idle", new TableInfo.Column("requires_device_idle", "INTEGER", true, 0, null, 1));
            hashMap2.put("requires_battery_not_low", new TableInfo.Column("requires_battery_not_low", "INTEGER", true, 0, null, 1));
            hashMap2.put("requires_storage_not_low", new TableInfo.Column("requires_storage_not_low", "INTEGER", true, 0, null, 1));
            hashMap2.put("trigger_content_update_delay", new TableInfo.Column("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
            hashMap2.put("trigger_max_content_delay", new TableInfo.Column("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
            hashMap2.put("content_uri_triggers", new TableInfo.Column("content_uri_triggers", "BLOB", true, 0, null, 1));
            HashSet hashSet3 = new HashSet(0);
            HashSet hashSet4 = new HashSet(2);
            hashSet4.add(new TableInfo.Index("index_WorkSpec_schedule_requested_at", false, Arrays.asList("schedule_requested_at"), Arrays.asList("ASC")));
            hashSet4.add(new TableInfo.Index("index_WorkSpec_last_enqueue_time", false, Arrays.asList("last_enqueue_time"), Arrays.asList("ASC")));
            TableInfo tableInfo2 = new TableInfo("WorkSpec", hashMap2, hashSet3, hashSet4);
            TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "WorkSpec");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo2, read2)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read2, tableInfo2, "WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap3 = new HashMap(2);
            hashMap3.put("tag", new TableInfo.Column("tag", "TEXT", true, 1, null, 1));
            hashMap3.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 2, null, 1));
            HashSet hashSet5 = new HashSet(1);
            hashSet5.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            HashSet hashSet6 = new HashSet(1);
            hashSet6.add(new TableInfo.Index("index_WorkTag_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
            TableInfo tableInfo3 = new TableInfo("WorkTag", hashMap3, hashSet5, hashSet6);
            TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "WorkTag");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo3, read3)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read3, tableInfo3, "WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap4 = new HashMap(3);
            hashMap4.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
            hashMap4.put("generation", new TableInfo.Column("generation", "INTEGER", true, 2, "0", 1));
            hashMap4.put("system_id", new TableInfo.Column("system_id", "INTEGER", true, 0, null, 1));
            HashSet hashSet7 = new HashSet(1);
            hashSet7.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            TableInfo tableInfo4 = new TableInfo("SystemIdInfo", hashMap4, hashSet7, new HashSet(0));
            TableInfo read4 = TableInfo.read(supportSQLiteDatabase, "SystemIdInfo");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo4, read4)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read4, tableInfo4, "SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap5 = new HashMap(2);
            hashMap5.put("name", new TableInfo.Column("name", "TEXT", true, 1, null, 1));
            hashMap5.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 2, null, 1));
            HashSet hashSet8 = new HashSet(1);
            hashSet8.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            HashSet hashSet9 = new HashSet(1);
            hashSet9.add(new TableInfo.Index("index_WorkName_work_spec_id", false, Arrays.asList("work_spec_id"), Arrays.asList("ASC")));
            TableInfo tableInfo5 = new TableInfo("WorkName", hashMap5, hashSet8, hashSet9);
            TableInfo read5 = TableInfo.read(supportSQLiteDatabase, "WorkName");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo5, read5)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read5, tableInfo5, "WorkName(androidx.work.impl.model.WorkName).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap6 = new HashMap(2);
            hashMap6.put("work_spec_id", new TableInfo.Column("work_spec_id", "TEXT", true, 1, null, 1));
            hashMap6.put("progress", new TableInfo.Column("progress", "BLOB", true, 0, null, 1));
            HashSet hashSet10 = new HashSet(1);
            hashSet10.add(new TableInfo.ForeignKey("WorkSpec", "CASCADE", "CASCADE", Arrays.asList("work_spec_id"), Arrays.asList("id")));
            TableInfo tableInfo6 = new TableInfo("WorkProgress", hashMap6, hashSet10, new HashSet(0));
            TableInfo read6 = TableInfo.read(supportSQLiteDatabase, "WorkProgress");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo6, read6)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read6, tableInfo6, "WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n", "\n Found:\n"));
            }
            HashMap hashMap7 = new HashMap(2);
            hashMap7.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, 1));
            hashMap7.put("long_value", new TableInfo.Column("long_value", "INTEGER", false, 0, null, 1));
            TableInfo tableInfo7 = new TableInfo("Preference", hashMap7, new HashSet(0), new HashSet(0));
            TableInfo read7 = TableInfo.read(supportSQLiteDatabase, "Preference");
            if (!ReportFragment.LifecycleCallbacks.Companion.equalsCommon(tableInfo7, read7)) {
                return new RetriableStream.HedgingPlan(false, (Object) _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(read7, tableInfo7, "Preference(androidx.work.impl.model.Preference).\n Expected:\n", "\n Found:\n"));
            }
            return new RetriableStream.HedgingPlan(true, (Object) null);
        }
    }

    @Override // androidx.room.RoomDatabase
    protected final PlusMinusButtons createInvalidationTracker$ar$class_merging() {
        return new PlusMinusButtons(this, new HashMap(0), new HashMap(0), "Dependency", "WorkSpec", "WorkTag", "SystemIdInfo", "WorkName", "WorkProgress", "Preference");
    }

    @Override // androidx.room.RoomDatabase
    public final SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        RoomOpenHelper roomOpenHelper = new RoomOpenHelper(databaseConfiguration, new AnonymousClass1());
        SupportSQLiteOpenHelper.Configuration.Builder builder = new SupportSQLiteOpenHelper.Configuration.Builder(databaseConfiguration.context);
        builder.name = databaseConfiguration.name;
        builder.callback = roomOpenHelper;
        return databaseConfiguration.sqliteOpenHelperFactory.create(builder.build());
    }

    @Override // androidx.work.impl.WorkDatabase
    public final DependencyDao dependencyDao() {
        DependencyDao dependencyDao;
        if (this._dependencyDao != null) {
            return this._dependencyDao;
        }
        synchronized (this) {
            if (this._dependencyDao == null) {
                this._dependencyDao = new DependencyDao_Impl(this);
            }
            dependencyDao = this._dependencyDao;
        }
        return dependencyDao;
    }

    @Override // androidx.room.RoomDatabase
    public final List getAutoMigrations$ar$ds() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WorkDatabase_AutoMigration_13_14_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_14_15_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_16_17_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_17_18_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_18_19_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_19_20_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_20_21_Impl());
        arrayList.add(new WorkDatabase_AutoMigration_22_23_Impl());
        return arrayList;
    }

    @Override // androidx.room.RoomDatabase
    public final Set getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    protected final Map getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(WorkSpecDao.class, Collections.emptyList());
        hashMap.put(DependencyDao.class, Collections.emptyList());
        hashMap.put(WorkTagDao.class, Collections.emptyList());
        hashMap.put(SystemIdInfoDao.class, Collections.emptyList());
        hashMap.put(WorkNameDao.class, Collections.emptyList());
        hashMap.put(WorkProgressDao.class, Collections.emptyList());
        hashMap.put(PreferenceDao.class, Collections.emptyList());
        hashMap.put(RawWorkInfoDao.class, Collections.emptyList());
        return hashMap;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final PreferenceDao preferenceDao() {
        PreferenceDao preferenceDao;
        if (this._preferenceDao != null) {
            return this._preferenceDao;
        }
        synchronized (this) {
            if (this._preferenceDao == null) {
                this._preferenceDao = new PreferenceDao_Impl(this);
            }
            preferenceDao = this._preferenceDao;
        }
        return preferenceDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final SystemIdInfoDao systemIdInfoDao() {
        SystemIdInfoDao systemIdInfoDao;
        if (this._systemIdInfoDao != null) {
            return this._systemIdInfoDao;
        }
        synchronized (this) {
            if (this._systemIdInfoDao == null) {
                this._systemIdInfoDao = new SystemIdInfoDao_Impl(this);
            }
            systemIdInfoDao = this._systemIdInfoDao;
        }
        return systemIdInfoDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final WorkNameDao workNameDao() {
        WorkNameDao workNameDao;
        if (this._workNameDao != null) {
            return this._workNameDao;
        }
        synchronized (this) {
            if (this._workNameDao == null) {
                this._workNameDao = new WorkNameDao_Impl(this);
            }
            workNameDao = this._workNameDao;
        }
        return workNameDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final WorkProgressDao workProgressDao() {
        WorkProgressDao workProgressDao;
        if (this._workProgressDao != null) {
            return this._workProgressDao;
        }
        synchronized (this) {
            if (this._workProgressDao == null) {
                this._workProgressDao = new WorkProgressDao_Impl(this);
            }
            workProgressDao = this._workProgressDao;
        }
        return workProgressDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final WorkSpecDao workSpecDao() {
        WorkSpecDao workSpecDao;
        if (this._workSpecDao != null) {
            return this._workSpecDao;
        }
        synchronized (this) {
            if (this._workSpecDao == null) {
                this._workSpecDao = new WorkSpecDao_Impl(this);
            }
            workSpecDao = this._workSpecDao;
        }
        return workSpecDao;
    }

    @Override // androidx.work.impl.WorkDatabase
    public final WorkTagDao workTagDao() {
        WorkTagDao workTagDao;
        if (this._workTagDao != null) {
            return this._workTagDao;
        }
        synchronized (this) {
            if (this._workTagDao == null) {
                this._workTagDao = new WorkTagDao_Impl(this);
            }
            workTagDao = this._workTagDao;
        }
        return workTagDao;
    }
}
