package androidx.sqlite.db.framework;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.util.Pair;
import androidx.room.SharedSQLiteStatement$stmt$2;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.SynchronizedLazyImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public final boolean allowDataLossOnRecovery;
    public final SupportSQLiteOpenHelper.Callback callback;
    public final Context context;
    private final Lazy lazyDelegate = new SynchronizedLazyImpl(new SharedSQLiteStatement$stmt$2(this, 2));
    public final String name;
    public final boolean useNoBackupDirectory;
    public boolean writeAheadLoggingEnabled;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OpenHelper extends SQLiteOpenHelper {
        public static final AppCompatDelegate.Api33Impl Companion$ar$class_merging$d1561cd5_0 = new AppCompatDelegate.Api33Impl();
        private final boolean allowDataLossOnRecovery;
        private final SupportSQLiteOpenHelper.Callback callback;
        private final Context context;
        private final OnDeviceTextDetectionLoadLogEvent dbRef$ar$class_merging$ar$class_merging$ar$class_merging;
        private final ProcessLock lock;
        private boolean migrated;
        private boolean opened;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class CallbackException extends RuntimeException {
            public final int callbackName$ar$edu;
            public final Throwable cause;

            public CallbackException(int i, Throwable th) {
                super(th);
                this.callbackName$ar$edu = i;
                this.cause = th;
            }

            @Override // java.lang.Throwable
            public final Throwable getCause() {
                return this.cause;
            }
        }

        public OpenHelper(Context context, String str, final OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent, final SupportSQLiteOpenHelper.Callback callback, boolean z) {
            super(context, str, null, 23, new DatabaseErrorHandler() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0
                @Override // android.database.DatabaseErrorHandler
                public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    sQLiteDatabase.getClass();
                    FrameworkSQLiteDatabase wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging = AppCompatDelegate.Api33Impl.getWrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent, sQLiteDatabase);
                    Log.e("SupportSQLite", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging, "Corruption reported by sqlite on database: ", ".path"));
                    if (!wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.isOpen()) {
                        String path = wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.getPath();
                        if (path != null) {
                            SupportSQLiteOpenHelper.Callback.deleteDatabaseFile$ar$ds(path);
                            return;
                        }
                        return;
                    }
                    List list = null;
                    try {
                        try {
                            list = wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.getAttachedDbs();
                        } catch (SQLiteException unused) {
                        }
                        try {
                            wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.close();
                        } catch (IOException unused2) {
                            if (list != null) {
                                Iterator it = list.iterator();
                                while (it.hasNext()) {
                                    Object obj = ((Pair) it.next()).second;
                                    obj.getClass();
                                    SupportSQLiteOpenHelper.Callback.deleteDatabaseFile$ar$ds((String) obj);
                                }
                                return;
                            }
                            String path2 = wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.getPath();
                            if (path2 != null) {
                                SupportSQLiteOpenHelper.Callback.deleteDatabaseFile$ar$ds(path2);
                            }
                        }
                    } catch (Throwable th) {
                        if (list != null) {
                            Iterator it2 = list.iterator();
                            while (it2.hasNext()) {
                                Object obj2 = ((Pair) it2.next()).second;
                                obj2.getClass();
                                SupportSQLiteOpenHelper.Callback.deleteDatabaseFile$ar$ds((String) obj2);
                            }
                        } else {
                            String path3 = wrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging.getPath();
                            if (path3 != null) {
                                SupportSQLiteOpenHelper.Callback.deleteDatabaseFile$ar$ds(path3);
                            }
                        }
                        throw th;
                    }
                }
            });
            this.context = context;
            this.dbRef$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceTextDetectionLoadLogEvent;
            this.callback = callback;
            this.allowDataLossOnRecovery = z;
            if (str == null) {
                str = UUID.randomUUID().toString();
                str.getClass();
            }
            this.lock = new ProcessLock(str, context.getCacheDir(), false);
        }

        private final SQLiteDatabase getWritableOrReadableDatabase(boolean z) {
            SQLiteDatabase writableDatabase = super.getWritableDatabase();
            writableDatabase.getClass();
            return writableDatabase;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public final void close() {
            try {
                ProcessLock processLock = this.lock;
                Map map = ProcessLock.threadLocksMap;
                boolean z = processLock.processLock;
                processLock.lock(false);
                super.close();
                this.dbRef$ar$class_merging$ar$class_merging$ar$class_merging.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = null;
                this.opened = false;
            } finally {
                this.lock.unlock();
            }
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean z) {
            boolean z2;
            SQLiteDatabase writableOrReadableDatabase;
            SupportSQLiteDatabase wrappedDb;
            File parentFile;
            try {
                ProcessLock processLock = this.lock;
                if (!this.opened && getDatabaseName() != null) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                processLock.lock(z2);
                this.migrated = false;
                String databaseName = getDatabaseName();
                boolean z3 = this.opened;
                if (databaseName != null && !z3 && (parentFile = this.context.getDatabasePath(databaseName).getParentFile()) != null) {
                    parentFile.mkdirs();
                    if (!parentFile.isDirectory()) {
                        Objects.toString(parentFile);
                        Log.w("SupportSQLite", "Invalid database parent file, not a directory: ".concat(parentFile.toString()));
                    }
                }
                try {
                    writableOrReadableDatabase = getWritableOrReadableDatabase(true);
                } catch (Throwable unused) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException unused2) {
                    }
                    try {
                        writableOrReadableDatabase = getWritableOrReadableDatabase(true);
                    } catch (Throwable th) {
                        if (th instanceof CallbackException) {
                            CallbackException callbackException = th;
                            Throwable th2 = callbackException.cause;
                            int i = callbackException.callbackName$ar$edu;
                            int i2 = i - 1;
                            if (i != 0) {
                                if (i2 != 0) {
                                    if (i2 != 1) {
                                        if (i2 != 2) {
                                            if (i2 != 3) {
                                                if (!(th2 instanceof SQLiteException)) {
                                                    throw th2;
                                                }
                                            } else {
                                                throw th2;
                                            }
                                        } else {
                                            throw th2;
                                        }
                                    } else {
                                        throw th2;
                                    }
                                } else {
                                    throw th2;
                                }
                            } else {
                                throw null;
                            }
                        } else if (th instanceof SQLiteException) {
                            if (databaseName == null || !this.allowDataLossOnRecovery) {
                                throw th;
                            }
                        } else {
                            throw th;
                        }
                        this.context.deleteDatabase(databaseName);
                        try {
                            writableOrReadableDatabase = getWritableOrReadableDatabase(true);
                        } catch (CallbackException e) {
                            throw e.cause;
                        }
                    }
                }
                if (this.migrated) {
                    close();
                    wrappedDb = getSupportDatabase(true);
                } else {
                    wrappedDb = getWrappedDb(writableOrReadableDatabase);
                }
                return wrappedDb;
            } finally {
                this.lock.unlock();
            }
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.getClass();
            return AppCompatDelegate.Api33Impl.getWrappedDb$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(this.dbRef$ar$class_merging$ar$class_merging$ar$class_merging, sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.getClass();
            if (!this.migrated) {
                int i = this.callback.version;
                if (sQLiteDatabase.getVersion() != 23) {
                    sQLiteDatabase.setMaxSqlCacheSize(1);
                }
            }
            try {
                this.callback.onConfigure(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(1, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.getClass();
            try {
                this.callback.onCreate(getWrappedDb(sQLiteDatabase));
            } catch (Throwable th) {
                throw new CallbackException(2, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.getClass();
            this.migrated = true;
            try {
                this.callback.onDowngrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(4, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onOpen(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.getClass();
            if (!this.migrated) {
                try {
                    this.callback.onOpen(getWrappedDb(sQLiteDatabase));
                } catch (Throwable th) {
                    throw new CallbackException(5, th);
                }
            }
            this.opened = true;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            sQLiteDatabase.getClass();
            this.migrated = true;
            try {
                this.callback.onUpgrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(3, th);
            }
        }
    }

    public FrameworkSQLiteOpenHelper(Context context, String str, SupportSQLiteOpenHelper.Callback callback, boolean z, boolean z2) {
        this.context = context;
        this.name = str;
        this.callback = callback;
        this.useNoBackupDirectory = z;
        this.allowDataLossOnRecovery = z2;
    }

    private final OpenHelper getDelegate() {
        return (OpenHelper) this.lazyDelegate.getValue();
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.lazyDelegate.isInitialized()) {
            getDelegate().close();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public final SupportSQLiteDatabase getWritableDatabase() {
        return getDelegate().getSupportDatabase(true);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public final void setWriteAheadLoggingEnabled(boolean z) {
        if (this.lazyDelegate.isInitialized()) {
            getDelegate().setWriteAheadLoggingEnabled(z);
        }
        this.writeAheadLoggingEnabled = z;
    }
}
