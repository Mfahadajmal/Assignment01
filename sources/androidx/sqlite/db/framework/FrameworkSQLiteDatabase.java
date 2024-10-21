package androidx.sqlite.db.framework;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;
import android.database.sqlite.SQLiteStatement;
import android.support.v4.os.BundleCompat$Api33Impl;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteQuery;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.lang.reflect.Method;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function4;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameworkSQLiteDatabase implements SupportSQLiteDatabase {
    public final SQLiteDatabase delegate;
    public static final AppCompatDelegate.Api24Impl Companion$ar$class_merging$cca23af4_0 = new AppCompatDelegate.Api24Impl();
    private static final String[] CONFLICT_VALUES = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final Lazy getThreadSessionMethod$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$a11f4c67_0);
    public static final Lazy beginTransactionMethod$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.NONE$ar$edu, TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE$ar$class_merging$723ce0fa_0);

    public FrameworkSQLiteDatabase(SQLiteDatabase sQLiteDatabase) {
        this.delegate = sQLiteDatabase;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransaction() {
        this.delegate.beginTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransactionNonExclusive() {
        this.delegate.beginTransactionNonExclusive();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void beginTransactionReadOnly() {
        if (AppCompatDelegate.Api24Impl.getBeginTransactionMethod$ar$ds() != null && AppCompatDelegate.Api24Impl.getGetThreadSessionMethod$ar$ds() != null) {
            Method beginTransactionMethod$ar$ds = AppCompatDelegate.Api24Impl.getBeginTransactionMethod$ar$ds();
            beginTransactionMethod$ar$ds.getClass();
            Method getThreadSessionMethod$ar$ds = AppCompatDelegate.Api24Impl.getGetThreadSessionMethod$ar$ds();
            getThreadSessionMethod$ar$ds.getClass();
            Object invoke = getThreadSessionMethod$ar$ds.invoke(this.delegate, null);
            if (invoke != null) {
                beginTransactionMethod$ar$ds.invoke(invoke, 0, null, 0, null);
                return;
            }
            throw new IllegalStateException("Required value was null.");
        }
        beginTransaction();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.delegate.close();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final FrameworkSQLiteStatement compileStatement$ar$class_merging(String str) {
        SQLiteStatement compileStatement = this.delegate.compileStatement(str);
        compileStatement.getClass();
        return new FrameworkSQLiteStatement(compileStatement);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void endTransaction() {
        this.delegate.endTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void execSQL(String str) {
        this.delegate.execSQL(str);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void execSQL$ar$ds(Object[] objArr) {
        this.delegate.execSQL("INSERT OR REPLACE INTO `Preference` (`key`, `long_value`) VALUES (@key, @long_value)", objArr);
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final List getAttachedDbs() {
        return this.delegate.getAttachedDbs();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final String getPath() {
        return this.delegate.getPath();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final boolean inTransaction() {
        return this.delegate.inTransaction();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final boolean isOpen() {
        return this.delegate.isOpen();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final boolean isWriteAheadLoggingEnabled() {
        return this.delegate.isWriteAheadLoggingEnabled();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final Cursor query(final SupportSQLiteQuery supportSQLiteQuery) {
        final Function4 function4 = new Function4() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$query$cursorFactory$1
            {
                super(4);
            }

            @Override // kotlin.jvm.functions.Function4
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                SQLiteQuery sQLiteQuery = (SQLiteQuery) obj4;
                sQLiteQuery.getClass();
                SupportSQLiteQuery.this.bindTo(new FrameworkSQLiteProgram(sQLiteQuery));
                return new SQLiteCursor((SQLiteCursorDriver) obj2, (String) obj3, sQLiteQuery);
            }
        };
        Cursor rawQueryWithFactory = this.delegate.rawQueryWithFactory(new SQLiteDatabase.CursorFactory() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteDatabase$$ExternalSyntheticLambda1
            @Override // android.database.sqlite.SQLiteDatabase.CursorFactory
            public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
                Lazy lazy = FrameworkSQLiteDatabase.getThreadSessionMethod$delegate;
                return (Cursor) Function4.this.invoke(sQLiteDatabase, sQLiteCursorDriver, str, sQLiteQuery);
            }
        }, supportSQLiteQuery.getSql(), EMPTY_STRING_ARRAY, null);
        rawQueryWithFactory.getClass();
        return rawQueryWithFactory;
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void setTransactionSuccessful() {
        this.delegate.setTransactionSuccessful();
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final void update$ar$ds(ContentValues contentValues, Object[] objArr) {
        String str;
        if (contentValues.size() != 0) {
            int size = contentValues.size();
            Object[] objArr2 = new Object[size];
            StringBuilder sb = new StringBuilder("UPDATE ");
            sb.append(CONFLICT_VALUES[3]);
            sb.append("WorkSpec SET ");
            int i = 0;
            for (String str2 : contentValues.keySet()) {
                if (i > 0) {
                    str = ",";
                } else {
                    str = "";
                }
                sb.append(str);
                sb.append(str2);
                objArr2[i] = contentValues.get(str2);
                sb.append("=?");
                i++;
            }
            for (int i2 = size; i2 < size; i2++) {
                objArr2[i2] = objArr[i2 - size];
            }
            if (!TextUtils.isEmpty("last_enqueue_time = 0 AND interval_duration <> 0 ")) {
                sb.append(" WHERE last_enqueue_time = 0 AND interval_duration <> 0 ");
            }
            FrameworkSQLiteStatement compileStatement$ar$class_merging = compileStatement$ar$class_merging(sb.toString());
            BundleCompat$Api33Impl.bind$ar$ds(compileStatement$ar$class_merging, objArr2);
            compileStatement$ar$class_merging.executeUpdateDelete();
            return;
        }
        throw new IllegalArgumentException("Empty values");
    }

    @Override // androidx.sqlite.db.SupportSQLiteDatabase
    public final Cursor query(String str) {
        return query(new SimpleSQLiteQuery(str));
    }
}
