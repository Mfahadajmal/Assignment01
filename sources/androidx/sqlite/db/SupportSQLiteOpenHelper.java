package androidx.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import java.io.Closeable;
import java.io.File;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SupportSQLiteOpenHelper extends Closeable {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Configuration {
        public static final AppCompatDelegate.Api24Impl Companion$ar$class_merging$a628e26d_0$ar$class_merging = new AppCompatDelegate.Api24Impl();
        public final boolean allowDataLossOnRecovery;
        public final Callback callback;
        public final Context context;
        public final String name;
        public final boolean useNoBackupDirectory;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public boolean allowDataLossOnRecovery;
            public Callback callback;
            private final Context context;
            public String name;
            public boolean useNoBackupDirectory;

            public Builder(Context context) {
                this.context = context;
            }

            public final Configuration build() {
                String str;
                Callback callback = this.callback;
                if (callback != null) {
                    if (this.useNoBackupDirectory && ((str = this.name) == null || str.length() == 0)) {
                        throw new IllegalArgumentException("Must set a non-null database name to a configuration that uses the no backup directory.");
                    }
                    return new Configuration(this.context, this.name, callback, this.useNoBackupDirectory, this.allowDataLossOnRecovery);
                }
                throw new IllegalArgumentException("Must set a callback to create the configuration.");
            }
        }

        public Configuration(Context context, String str, Callback callback, boolean z, boolean z2) {
            this.context = context;
            this.name = str;
            this.callback = callback;
            this.useNoBackupDirectory = z;
            this.allowDataLossOnRecovery = z2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Factory {
        SupportSQLiteOpenHelper create(Configuration configuration);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    SupportSQLiteDatabase getWritableDatabase();

    void setWriteAheadLoggingEnabled(boolean z);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Callback {
        public final int version = 23;

        public Callback(int i) {
        }

        public static final void deleteDatabaseFile$ar$ds(String str) {
            int i;
            if (!OnDeviceStainRemovalLogEvent.equals$ar$ds$566ac877_0(str, ":memory:")) {
                int length = str.length() - 1;
                int i2 = 0;
                boolean z = false;
                while (i2 <= length) {
                    if (true != z) {
                        i = i2;
                    } else {
                        i = length;
                    }
                    int compare = Intrinsics.compare(str.charAt(i), 32);
                    if (!z) {
                        if (compare > 0) {
                            z = true;
                        } else {
                            i2++;
                        }
                    } else if (compare > 0) {
                        break;
                    } else {
                        length--;
                    }
                }
                if (str.subSequence(i2, length + 1).toString().length() != 0) {
                    Log.w("SupportSQLite", "deleting the database file: ".concat(str));
                    try {
                        SQLiteDatabase.deleteDatabase(new File(str));
                    } catch (Exception e) {
                        Log.w("SupportSQLite", "delete failed: ", e);
                    }
                }
            }
        }

        public void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
            throw null;
        }

        public abstract void onCreate(SupportSQLiteDatabase supportSQLiteDatabase);

        public void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
            throw null;
        }

        public abstract void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2);

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }
}
