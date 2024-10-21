package androidx.room.migration;

import androidx.lifecycle.ViewModelStore;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Migration {
    public final int endVersion;
    public final int startVersion;

    public Migration(int i, int i2) {
        this.startVersion = i;
        this.endVersion = i2;
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.sqlite.db.SupportSQLiteDatabase, java.lang.Object] */
    public final void migrate$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore) {
        migrate(viewModelStore.ViewModelStore$ar$map);
    }
}
