package androidx.sqlite.db;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import java.io.Closeable;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    void beginTransactionReadOnly();

    FrameworkSQLiteStatement compileStatement$ar$class_merging(String str);

    void endTransaction();

    void execSQL(String str);

    void execSQL$ar$ds(Object[] objArr);

    List getAttachedDbs();

    String getPath();

    boolean inTransaction();

    boolean isOpen();

    boolean isWriteAheadLoggingEnabled();

    Cursor query(SupportSQLiteQuery supportSQLiteQuery);

    Cursor query(String str);

    void setTransactionSuccessful();

    void update$ar$ds(ContentValues contentValues, Object[] objArr);
}
