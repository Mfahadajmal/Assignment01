package androidx.room;

import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SharedSQLiteStatement {
    private final RoomDatabase database;
    private final AtomicBoolean lock = new AtomicBoolean(false);
    private final Lazy stmt$delegate = new SynchronizedLazyImpl(new SharedSQLiteStatement$stmt$2(this, 0));

    public SharedSQLiteStatement(RoomDatabase roomDatabase) {
        this.database = roomDatabase;
    }

    private final FrameworkSQLiteStatement getStmt$ar$class_merging() {
        return (FrameworkSQLiteStatement) this.stmt$delegate.getValue();
    }

    public final FrameworkSQLiteStatement acquire$ar$class_merging() {
        this.database.assertNotMainThread();
        if (this.lock.compareAndSet(false, true)) {
            return getStmt$ar$class_merging();
        }
        return createNewStatement$ar$class_merging();
    }

    public final FrameworkSQLiteStatement createNewStatement$ar$class_merging() {
        RoomDatabase roomDatabase = this.database;
        roomDatabase.assertNotMainThread();
        roomDatabase.assertNotSuspendingTransaction();
        return roomDatabase.getOpenHelper().getWritableDatabase().compileStatement$ar$class_merging(createQuery());
    }

    protected abstract String createQuery();

    public final void release$ar$class_merging(FrameworkSQLiteStatement frameworkSQLiteStatement) {
        frameworkSQLiteStatement.getClass();
        if (frameworkSQLiteStatement == getStmt$ar$class_merging()) {
            this.lock.set(false);
        }
    }
}
