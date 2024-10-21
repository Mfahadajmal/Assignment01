package androidx.room;

import android.database.Cursor;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import androidx.core.view.animation.PathInterpolatorCompat$Api21Impl;
import androidx.core.view.autofill.AutofillIdCompat;
import androidx.core.widget.TextViewCompat$Api23Impl;
import androidx.lifecycle.ViewModelStore;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.room.migration.Migration;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import io.grpc.internal.RetriableStream;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.builders.ListBuilder;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {
    public static final AutofillIdCompat Companion$ar$class_merging$742e62b4_0 = new AutofillIdCompat();
    private final List callbacks;
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Delegate {
        public abstract void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase);

        public RetriableStream.HedgingPlan onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(SupportSQLiteDatabase supportSQLiteDatabase) {
            throw null;
        }
    }

    public RoomOpenHelper(DatabaseConfiguration databaseConfiguration, Delegate delegate) {
        super(23);
        this.callbacks = databaseConfiguration.callbacks;
        this.configuration = databaseConfiguration;
        this.delegate = delegate;
        this.identityHash = "86254750241babac4b8d52996a675549";
        this.legacyHash = "1cbd3130fa23b59692c061c594c16cc0";
    }

    private final void updateIdentity(SupportSQLiteDatabase supportSQLiteDatabase) {
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        supportSQLiteDatabase.execSQL(PathInterpolatorCompat$Api21Impl.createInsertQuery(this.identityHash));
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public final void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Cursor query = supportSQLiteDatabase.query("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (query.moveToFirst()) {
                if (query.getInt(0) == 0) {
                    z = true;
                }
            }
            OnDeviceShadowRemovalLogEvent.closeFinally(query, null);
            this.delegate.createAllTables(supportSQLiteDatabase);
            if (!z) {
                RetriableStream.HedgingPlan onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.delegate.onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(supportSQLiteDatabase);
                if (!onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isHedgeable) {
                    throw new IllegalStateException("Pre-packaged database has an invalid schema: ".concat(String.valueOf(onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis)));
                }
            }
            updateIdentity(supportSQLiteDatabase);
            for (AccessibilityWindowInfoCompat.Api21Impl api21Impl : this.callbacks) {
            }
        } finally {
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public final void onDowngrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        onUpgrade(supportSQLiteDatabase, i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006c  */
    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onOpen(androidx.sqlite.db.SupportSQLiteDatabase r5) {
        /*
            r4 = this;
            java.lang.String r0 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
            android.database.Cursor r0 = r5.query(r0)
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> Ld8
            r2 = 0
            if (r1 == 0) goto L15
            int r1 = r0.getInt(r2)     // Catch: java.lang.Throwable -> Ld8
            if (r1 == 0) goto L15
            r1 = 1
            goto L16
        L15:
            r1 = r2
        L16:
            r3 = 0
            com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent.closeFinally(r0, r3)
            if (r1 == 0) goto L6c
            androidx.sqlite.db.SimpleSQLiteQuery r0 = new androidx.sqlite.db.SimpleSQLiteQuery
            java.lang.String r1 = "SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1"
            r0.<init>(r1)
            android.database.Cursor r0 = r5.query(r0)
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L65
            if (r1 == 0) goto L32
            java.lang.String r1 = r0.getString(r2)     // Catch: java.lang.Throwable -> L65
            goto L33
        L32:
            r1 = r3
        L33:
            com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent.closeFinally(r0, r3)
            java.lang.String r0 = r4.identityHash
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L79
            java.lang.String r0 = r4.legacyHash
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L47
            goto L79
        L47:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: "
            r0.<init>(r2)
            java.lang.String r2 = r4.identityHash
            r0.append(r2)
            java.lang.String r2 = ", found: "
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        L65:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L67
        L67:
            r1 = move-exception
            com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent.closeFinally(r0, r5)
            throw r1
        L6c:
            androidx.room.RoomOpenHelper$Delegate r0 = r4.delegate
            io.grpc.internal.RetriableStream$HedgingPlan r0 = r0.onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(r5)
            boolean r1 = r0.isHedgeable
            if (r1 == 0) goto Lc6
            r4.updateIdentity(r5)
        L79:
            androidx.room.RoomOpenHelper$Delegate r0 = r4.delegate
            androidx.work.impl.WorkDatabase_Impl$1 r0 = (androidx.work.impl.WorkDatabase_Impl.AnonymousClass1) r0
            androidx.work.impl.WorkDatabase_Impl r1 = androidx.work.impl.WorkDatabase_Impl.this
            r1.mDatabase = r5
            java.lang.String r1 = "PRAGMA foreign_keys = ON"
            r5.execSQL(r1)
            androidx.lifecycle.ViewModelStore r1 = new androidx.lifecycle.ViewModelStore
            r1.<init>(r5, r3)
            androidx.work.impl.WorkDatabase_Impl r0 = androidx.work.impl.WorkDatabase_Impl.this
            com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons r0 = r0.getInvalidationTracker$ar$class_merging()
            java.lang.Object r2 = r0.PlusMinusButtons$ar$canPlus
            java.lang.String r2 = "PRAGMA temp_store = MEMORY"
            androidx.preference.PreferenceDialogFragmentCompat.Api30Impl.execSQL$ar$class_merging$ar$class_merging(r1, r2)
            java.lang.String r2 = "PRAGMA recursive_triggers = 1"
            androidx.preference.PreferenceDialogFragmentCompat.Api30Impl.execSQL$ar$class_merging$ar$class_merging(r1, r2)
            java.lang.String r2 = "CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)"
            androidx.preference.PreferenceDialogFragmentCompat.Api30Impl.execSQL$ar$class_merging$ar$class_merging(r1, r2)
            java.lang.Object r1 = r0.PlusMinusButtons$ar$getIndex
            monitor-enter(r1)
            java.lang.Object r2 = r0.PlusMinusButtons$ar$indexTextView     // Catch: java.lang.Throwable -> Lc3
            java.lang.Object r0 = r0.PlusMinusButtons$ar$plusButton     // Catch: java.lang.Throwable -> Lc3
            monitor-exit(r1)
            java.util.List r0 = r4.callbacks
            java.util.Iterator r0 = r0.iterator()
        Lb0:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lc0
            java.lang.Object r1 = r0.next()
            androidx.core.view.accessibility.AccessibilityWindowInfoCompat$Api21Impl r1 = (androidx.core.view.accessibility.AccessibilityWindowInfoCompat.Api21Impl) r1
            r1.onOpen(r5)
            goto Lb0
        Lc0:
            r4.configuration = r3
            return
        Lc3:
            r5 = move-exception
            monitor-exit(r1)
            throw r5
        Lc6:
            java.lang.Object r5 = r0.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis
            java.lang.String r0 = "Pre-packaged database has an invalid schema: "
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r5 = r0.concat(r5)
            r1.<init>(r5)
            throw r1
        Ld8:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> Lda
        Lda:
            r1 = move-exception
            com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent.closeFinally(r0, r5)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.onOpen(androidx.sqlite.db.SupportSQLiteDatabase):void");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public final void onUpgrade(SupportSQLiteDatabase supportSQLiteDatabase, int i, int i2) {
        List findMigrationPath$ar$class_merging$ar$class_merging;
        DatabaseConfiguration databaseConfiguration = this.configuration;
        if (databaseConfiguration != null && (findMigrationPath$ar$class_merging$ar$class_merging = TextViewCompat$Api23Impl.findMigrationPath$ar$class_merging$ar$class_merging(databaseConfiguration.migrationContainer$ar$class_merging$ar$class_merging, i, i2)) != null) {
            ViewModelStore viewModelStore = new ViewModelStore(supportSQLiteDatabase, (byte[]) null);
            ListBuilder listBuilder = new ListBuilder();
            SQLiteStatement m32prepare = viewModelStore.m32prepare("SELECT name FROM sqlite_master WHERE type = 'trigger'");
            while (m32prepare.step()) {
                try {
                    listBuilder.add(m32prepare.getText(0));
                } catch (Throwable th) {
                    m32prepare.close();
                    throw th;
                }
            }
            m32prepare.close();
            for (String str : OnDeviceLanguageIdentificationLogEvent.build(listBuilder)) {
                if (OnDeviceStainRemovalLogEvent.startsWith$default$ar$ds(str, "room_fts_content_sync_")) {
                    PreferenceDialogFragmentCompat.Api30Impl.execSQL$ar$class_merging$ar$class_merging(viewModelStore, "DROP TRIGGER IF EXISTS ".concat(String.valueOf(str)));
                }
            }
            Iterator it = findMigrationPath$ar$class_merging$ar$class_merging.iterator();
            while (it.hasNext()) {
                ((Migration) it.next()).migrate$ar$class_merging$ar$class_merging(new ViewModelStore(supportSQLiteDatabase, (byte[]) null));
            }
            RetriableStream.HedgingPlan onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.delegate.onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(supportSQLiteDatabase);
            if (onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isHedgeable) {
                updateIdentity(supportSQLiteDatabase);
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle: ".concat(String.valueOf(onValidateSchema$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.RetriableStream$HedgingPlan$ar$hedgingPushbackMillis)));
        }
        DatabaseConfiguration databaseConfiguration2 = this.configuration;
        if (databaseConfiguration2 != null && !TextViewCompat$Api23Impl.isMigrationRequired(databaseConfiguration2, i, i2)) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Dependency`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkSpec`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkTag`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `SystemIdInfo`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkName`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `WorkProgress`");
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `Preference`");
            for (AccessibilityWindowInfoCompat.Api21Impl api21Impl : this.callbacks) {
            }
            this.delegate.createAllTables(supportSQLiteDatabase);
            return;
        }
        throw new IllegalStateException("A migration from " + i + " to " + i2 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public final void onConfigure(SupportSQLiteDatabase supportSQLiteDatabase) {
    }
}
