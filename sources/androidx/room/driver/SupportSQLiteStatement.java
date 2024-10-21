package androidx.room.driver;

import android.database.Cursor;
import androidx.core.widget.EdgeEffectCompat$Api21Impl;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.room.driver.SupportSQLiteStatement;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteProgram;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import kotlin.KotlinNothingValueException;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SupportSQLiteStatement implements SQLiteStatement {
    public static final EdgeEffectCompat$Api21Impl Companion$ar$class_merging$e011757a_0 = new EdgeEffectCompat$Api21Impl();
    public final SupportSQLiteDatabase db;
    public boolean isClosed;
    public final String sql;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SupportAndroidSQLiteStatement extends SupportSQLiteStatement {
        public int[] bindingTypes;
        public byte[][] blobBindings;
        private Cursor cursor;
        public double[] doubleBindings;
        public long[] longBindings;
        public String[] stringBindings;

        public SupportAndroidSQLiteStatement(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            super(supportSQLiteDatabase, str);
            this.bindingTypes = new int[0];
            this.longBindings = new long[0];
            this.doubleBindings = new double[0];
            this.stringBindings = new String[0];
            this.blobBindings = new byte[0];
        }

        private final void ensureCursor() {
            if (this.cursor == null) {
                this.cursor = this.db.query(new SupportSQLiteQuery() { // from class: androidx.room.driver.SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1
                    @Override // androidx.sqlite.db.SupportSQLiteQuery
                    public final void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
                        int length = SupportSQLiteStatement.SupportAndroidSQLiteStatement.this.bindingTypes.length;
                    }

                    @Override // androidx.sqlite.db.SupportSQLiteQuery
                    public final String getSql() {
                        return SupportSQLiteStatement.SupportAndroidSQLiteStatement.this.sql;
                    }
                });
            }
        }

        private static final void throwIfInvalidColumn$ar$ds(Cursor cursor, int i) {
            if (i >= 0 && i < cursor.getColumnCount()) {
                return;
            }
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(25, "column index out of range");
            throw new KotlinNothingValueException();
        }

        private final Cursor throwIfNoRow() {
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor;
            }
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void close() {
            if (!this.isClosed) {
                throwIfClosed();
                this.bindingTypes = new int[0];
                this.longBindings = new long[0];
                this.doubleBindings = new double[0];
                this.stringBindings = new String[0];
                this.blobBindings = new byte[0];
                reset();
            }
            setClosed$ar$ds();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final int getColumnCount() {
            throwIfClosed();
            ensureCursor();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor.getColumnCount();
            }
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getColumnName(int i) {
            throwIfClosed();
            ensureCursor();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                throwIfInvalidColumn$ar$ds(cursor, i);
                String columnName = cursor.getColumnName(i);
                columnName.getClass();
                return columnName;
            }
            throw new IllegalStateException("Required value was null.");
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final long getLong(int i) {
            throwIfClosed();
            Cursor throwIfNoRow = throwIfNoRow();
            throwIfInvalidColumn$ar$ds(throwIfNoRow, i);
            return throwIfNoRow.getLong(i);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getText(int i) {
            throwIfClosed();
            Cursor throwIfNoRow = throwIfNoRow();
            throwIfInvalidColumn$ar$ds(throwIfNoRow, i);
            String string = throwIfNoRow.getString(i);
            string.getClass();
            return string;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean isNull(int i) {
            throwIfClosed();
            Cursor throwIfNoRow = throwIfNoRow();
            throwIfInvalidColumn$ar$ds(throwIfNoRow, i);
            return throwIfNoRow.isNull(i);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void reset() {
            throwIfClosed();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                cursor.close();
            }
            this.cursor = null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean step() {
            throwIfClosed();
            ensureCursor();
            Cursor cursor = this.cursor;
            if (cursor != null) {
                return cursor.moveToNext();
            }
            throw new IllegalStateException("Required value was null.");
        }
    }

    public SupportSQLiteStatement(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        this.db = supportSQLiteDatabase;
        this.sql = str;
    }

    protected final void setClosed$ar$ds() {
        this.isClosed = true;
    }

    protected final void throwIfClosed() {
        if (!this.isClosed) {
            return;
        }
        PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "statement is closed");
        throw new KotlinNothingValueException();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SupportOtherAndroidSQLiteStatement extends SupportSQLiteStatement {
        private final FrameworkSQLiteStatement delegate$ar$class_merging;

        public SupportOtherAndroidSQLiteStatement(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
            super(supportSQLiteDatabase, str);
            this.delegate$ar$class_merging = supportSQLiteDatabase.compileStatement$ar$class_merging(str);
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void close() {
            this.delegate$ar$class_merging.close();
            setClosed$ar$ds();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final int getColumnCount() {
            throwIfClosed();
            return 0;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getColumnName(int i) {
            throwIfClosed();
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final long getLong(int i) {
            throwIfClosed();
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getText(int i) {
            throwIfClosed();
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean isNull(int i) {
            throwIfClosed();
            PreferenceDialogFragmentCompat.Api30Impl.throwSQLiteException$ar$ds(21, "no row");
            throw new KotlinNothingValueException();
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean step() {
            throwIfClosed();
            this.delegate$ar$class_merging.execute();
            return false;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void reset() {
        }
    }
}
