package androidx.sqlite.db.framework;

import android.database.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteProgram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameworkSQLiteStatement extends FrameworkSQLiteProgram implements SupportSQLiteProgram {
    private final SQLiteStatement delegate;

    public FrameworkSQLiteStatement(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.delegate = sQLiteStatement;
    }

    public final void execute() {
        this.delegate.execute();
    }

    public final void executeInsert$ar$ds() {
        this.delegate.executeInsert();
    }

    public final int executeUpdateDelete() {
        return this.delegate.executeUpdateDelete();
    }
}
