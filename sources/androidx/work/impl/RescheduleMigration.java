package androidx.work.impl;

import android.content.Context;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RescheduleMigration extends Migration {
    private final Context mContext;

    public RescheduleMigration(Context context, int i, int i2) {
        super(i, i2);
        this.mContext = context;
    }

    @Override // androidx.room.migration.Migration
    public final void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        if (this.endVersion >= 10) {
            supportSQLiteDatabase.execSQL$ar$ds(new Object[]{"reschedule_needed", 1});
        } else {
            this.mContext.getSharedPreferences("androidx.work.util.preferences", 0).edit().putBoolean("reschedule_needed", true).apply();
        }
    }
}
