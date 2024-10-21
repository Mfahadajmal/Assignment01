package androidx.work.impl;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Migration_6_7 extends Migration {
    public static final Migration_6_7 INSTANCE = new Migration_6_7();

    private Migration_6_7() {
        super(6, 7);
    }

    @Override // androidx.room.migration.Migration
    public final void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("\n    CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress`\n    BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`)\n    REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )\n    ");
    }
}