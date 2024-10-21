package androidx.sqlite.db;

import android.support.v4.os.BundleCompat$Api33Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleSQLiteQuery implements SupportSQLiteQuery {
    public static final BundleCompat$Api33Impl Companion$ar$class_merging$f66c8d42_0 = new BundleCompat$Api33Impl();
    private final String query;

    public SimpleSQLiteQuery(String str) {
        this.query = str;
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public final void bindTo(SupportSQLiteProgram supportSQLiteProgram) {
        BundleCompat$Api33Impl.bind$ar$ds(supportSQLiteProgram, null);
    }

    @Override // androidx.sqlite.db.SupportSQLiteQuery
    public final String getSql() {
        return this.query;
    }
}
