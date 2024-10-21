package androidx.work.impl.model;

import android.database.Cursor;
import androidx.core.widget.NestedScrollView;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DependencyDao_Impl implements DependencyDao {
    public final RoomDatabase __db;
    public final EntityInsertionAdapter __insertionAdapterOfDependency;

    public DependencyDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfDependency = new EntityInsertionAdapter(roomDatabase) { // from class: androidx.work.impl.model.DependencyDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            protected final /* bridge */ /* synthetic */ void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj) {
                WorkName workName = (WorkName) obj;
                frameworkSQLiteStatement.bindString(1, (String) workName.WorkName$ar$workSpecId);
                frameworkSQLiteStatement.bindString(2, (String) workName.WorkName$ar$name);
            }

            @Override // androidx.room.SharedSQLiteStatement
            protected final String createQuery() {
                return "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
            }
        };
    }

    @Override // androidx.work.impl.model.DependencyDao
    public final List getDependentWorkIds(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?", 1);
        acquire.bindString(1, str);
        this.__db.assertNotSuspendingTransaction();
        Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(this.__db, acquire);
        try {
            ArrayList arrayList = new ArrayList(query$ar$ds$e1ca310e_0.getCount());
            while (query$ar$ds$e1ca310e_0.moveToNext()) {
                arrayList.add(query$ar$ds$e1ca310e_0.getString(0));
            }
            return arrayList;
        } finally {
            query$ar$ds$e1ca310e_0.close();
            acquire.release();
        }
    }
}
