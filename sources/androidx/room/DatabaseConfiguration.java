package androidx.room;

import android.content.Context;
import androidx.lifecycle.ViewModelStore;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DatabaseConfiguration {
    public final boolean allowDestructiveMigrationOnDowngrade;
    public final boolean allowMainThreadQueries;
    public final List autoMigrationSpecs;
    public final List callbacks;
    public final Context context;
    public final int journalMode$ar$edu;
    public final ViewModelStore migrationContainer$ar$class_merging$ar$class_merging;
    public final Set migrationNotRequiredFrom;
    public final String name;
    public final Executor queryExecutor;
    public final boolean requireMigration;
    public final SupportSQLiteOpenHelper.Factory sqliteOpenHelperFactory;
    public final Executor transactionExecutor;
    public final List typeConverters;

    public DatabaseConfiguration(Context context, String str, SupportSQLiteOpenHelper.Factory factory, ViewModelStore viewModelStore, List list, boolean z, int i, Executor executor, Executor executor2, boolean z2, boolean z3, Set set, List list2, List list3) {
        this.context = context;
        this.name = str;
        this.sqliteOpenHelperFactory = factory;
        this.migrationContainer$ar$class_merging$ar$class_merging = viewModelStore;
        this.callbacks = list;
        this.allowMainThreadQueries = z;
        this.journalMode$ar$edu = i;
        this.queryExecutor = executor;
        this.transactionExecutor = executor2;
        this.requireMigration = z2;
        this.allowDestructiveMigrationOnDowngrade = z3;
        this.migrationNotRequiredFrom = set;
        this.typeConverters = list2;
        this.autoMigrationSpecs = list3;
    }
}
