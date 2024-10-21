package androidx.room;

import androidx.sqlite.db.framework.FrameworkSQLiteStatement;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class EntityInsertionAdapter extends SharedSQLiteStatement {
    public EntityInsertionAdapter(RoomDatabase roomDatabase) {
        super(roomDatabase);
    }

    protected abstract void bind$ar$class_merging$340ef526_0(FrameworkSQLiteStatement frameworkSQLiteStatement, Object obj);

    public final void insert(Object obj) {
        FrameworkSQLiteStatement acquire$ar$class_merging = acquire$ar$class_merging();
        try {
            bind$ar$class_merging$340ef526_0(acquire$ar$class_merging, obj);
            acquire$ar$class_merging.executeInsert$ar$ds();
        } finally {
            release$ar$class_merging(acquire$ar$class_merging);
        }
    }
}
