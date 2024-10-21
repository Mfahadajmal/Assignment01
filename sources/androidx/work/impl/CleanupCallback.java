package androidx.work.impl;

import android.support.v7.view.WindowCallbackWrapper;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CleanupCallback extends AccessibilityWindowInfoCompat.Api21Impl {
    private final WindowCallbackWrapper.Api26Impl clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging;

    public CleanupCallback(WindowCallbackWrapper.Api26Impl api26Impl) {
        this.clock$ar$class_merging$3e4a2eb7_0$ar$class_merging$ar$class_merging = api26Impl;
    }

    @Override // androidx.core.view.accessibility.AccessibilityWindowInfoCompat.Api21Impl
    public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        long currentTimeMillis;
        supportSQLiteDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < ");
            currentTimeMillis = System.currentTimeMillis();
            sb.append(currentTimeMillis - WorkDatabaseKt.PRUNE_THRESHOLD_MILLIS);
            sb.append(" AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))");
            supportSQLiteDatabase.execSQL(sb.toString());
            supportSQLiteDatabase.setTransactionSuccessful();
        } finally {
            supportSQLiteDatabase.endTransaction();
        }
    }
}
