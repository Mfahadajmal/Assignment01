package androidx.work.impl.model;

import android.database.Cursor;
import androidx.core.widget.NestedScrollView;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PreferenceDao_Impl implements PreferenceDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter __insertionAdapterOfPreference;

    public PreferenceDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPreference = new EntityInsertionAdapter(roomDatabase) { // from class: androidx.work.impl.model.PreferenceDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            protected final /* bridge */ /* synthetic */ void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj) {
                Preference preference = (Preference) obj;
                frameworkSQLiteStatement.bindString(1, preference.key);
                frameworkSQLiteStatement.bindLong(2, preference.value.longValue());
            }

            @Override // androidx.room.SharedSQLiteStatement
            protected final String createQuery() {
                return "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
            }
        };
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public final Long getLongValue(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT long_value FROM Preference where `key`=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
        try {
            Long l = null;
            if (query$ar$ds$e1ca310e_0.moveToFirst() && !query$ar$ds$e1ca310e_0.isNull(0)) {
                l = Long.valueOf(query$ar$ds$e1ca310e_0.getLong(0));
            }
            return l;
        } finally {
            query$ar$ds$e1ca310e_0.close();
            acquire.release();
        }
    }

    @Override // androidx.work.impl.model.PreferenceDao
    public final void insertPreference(Preference preference) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPreference.insert(preference);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }
}
