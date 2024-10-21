package androidx.work.impl;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkMigration9To10 extends Migration {
    private final Context context;

    public WorkMigration9To10(Context context) {
        super(9, 10);
        this.context = context;
    }

    @Override // androidx.room.migration.Migration
    public final void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("androidx.work.util.preferences", 0);
        if (sharedPreferences.contains("reschedule_needed") || sharedPreferences.contains("last_cancel_all_time_ms")) {
            long j = 0;
            long j2 = sharedPreferences.getLong("last_cancel_all_time_ms", 0L);
            if (true == sharedPreferences.getBoolean("reschedule_needed", false)) {
                j = 1;
            }
            supportSQLiteDatabase.beginTransaction();
            try {
                supportSQLiteDatabase.execSQL$ar$ds(new Object[]{"last_cancel_all_time_ms", Long.valueOf(j2)});
                supportSQLiteDatabase.execSQL$ar$ds(new Object[]{"reschedule_needed", Long.valueOf(j)});
                sharedPreferences.edit().clear().apply();
                supportSQLiteDatabase.setTransactionSuccessful();
            } finally {
            }
        }
        SharedPreferences sharedPreferences2 = this.context.getSharedPreferences("androidx.work.util.id", 0);
        if (!sharedPreferences2.contains("next_job_scheduler_id") && !sharedPreferences2.contains("next_job_scheduler_id")) {
            return;
        }
        int i = sharedPreferences2.getInt("next_job_scheduler_id", 0);
        int i2 = sharedPreferences2.getInt("next_alarm_manager_id", 0);
        supportSQLiteDatabase.beginTransaction();
        try {
            supportSQLiteDatabase.execSQL$ar$ds(new Object[]{"next_job_scheduler_id", Integer.valueOf(i)});
            supportSQLiteDatabase.execSQL$ar$ds(new Object[]{"next_alarm_manager_id", Integer.valueOf(i2)});
            sharedPreferences2.edit().clear().apply();
            supportSQLiteDatabase.setTransactionSuccessful();
        } finally {
        }
    }
}