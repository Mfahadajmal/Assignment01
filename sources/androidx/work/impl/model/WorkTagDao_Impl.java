package androidx.work.impl.model;

import android.database.Cursor;
import androidx.core.widget.NestedScrollView;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkTagDao_Impl implements WorkTagDao {
    public final RoomDatabase __db;
    public final EntityInsertionAdapter __insertionAdapterOfWorkTag;

    public WorkTagDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWorkTag = new EntityInsertionAdapter(roomDatabase) { // from class: androidx.work.impl.model.WorkTagDao_Impl.1
            @Override // androidx.room.EntityInsertionAdapter
            protected final /* bridge */ /* synthetic */ void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj) {
                NodeBrailler nodeBrailler = (NodeBrailler) obj;
                frameworkSQLiteStatement.bindString(1, (String) nodeBrailler.NodeBrailler$ar$context);
                frameworkSQLiteStatement.bindString(2, (String) nodeBrailler.NodeBrailler$ar$rules);
            }

            @Override // androidx.room.SharedSQLiteStatement
            protected final String createQuery() {
                return "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
            }
        };
        new SharedSQLiteStatement(roomDatabase) { // from class: androidx.work.impl.model.WorkTagDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "DELETE FROM worktag WHERE work_spec_id=?";
            }
        };
    }

    @Override // androidx.work.impl.model.WorkTagDao
    public final List getTagsForWorkSpecId(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?", 1);
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
