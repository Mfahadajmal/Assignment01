package androidx.work.impl.model;

import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkNameDao_Impl implements WorkNameDao {
    public final RoomDatabase __db;
    public final EntityInsertionAdapter __insertionAdapterOfWorkName;

    public WorkNameDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkName = new EntityInsertionAdapter(roomDatabase) { // from class: androidx.work.impl.model.WorkNameDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            protected final /* bridge */ /* synthetic */ void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj) {
                throw null;
            }

            @Override // androidx.room.SharedSQLiteStatement
            protected final String createQuery() {
                return "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
            }
        };
    }
}
