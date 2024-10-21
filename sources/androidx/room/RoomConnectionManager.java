package androidx.room;

import androidx.activity.OnBackPressedDispatcher;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import androidx.lifecycle.ViewModelStore;
import androidx.room.coroutines.RawConnectionAccessor;
import androidx.room.driver.SupportSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.work.impl.model.WorkName;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyThreadSafetyMode;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoomConnectionManager extends AccessibilityViewCommand.CommandArguments {
    private final List callbacks;
    private final DatabaseConfiguration configuration;
    public final SupportConnectionPool connectionPool$ar$class_merging;
    private final WorkName openDelegate$ar$class_merging$ar$class_merging = new WorkName((byte[]) null, (byte[]) null);
    public SupportSQLiteDatabase supportDatabase;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SupportConnectionPool {
        private final Lazy supportConnection$delegate = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.lazy$ar$edu(LazyThreadSafetyMode.PUBLICATION$ar$edu, new SharedSQLiteStatement$stmt$2(this, 1));
        public final ViewModelStore supportDriver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

        public SupportConnectionPool(ViewModelStore viewModelStore) {
            this.supportDriver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = viewModelStore;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.sqlite.db.SupportSQLiteOpenHelper, java.lang.Object] */
        public final void close() {
            this.supportDriver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ViewModelStore$ar$map.close();
        }

        public final Object useConnection$ar$ds(Function2 function2, Continuation continuation) {
            return function2.invoke((SupportPooledConnection) this.supportConnection$delegate.getValue(), continuation);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SupportPooledConnection implements RawConnectionAccessor, PooledConnection {
        private int currentTransactionType$ar$edu;
        public final ViewModelStore delegate$ar$class_merging$90b5e8df_0;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        final class RollbackException extends Throwable {
        }

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SupportTransactor implements RawConnectionAccessor, PooledConnection {
            public SupportTransactor() {
            }

            @Override // androidx.room.coroutines.RawConnectionAccessor
            public final ViewModelStore getRawConnection$ar$class_merging$ar$class_merging() {
                return SupportPooledConnection.this.delegate$ar$class_merging$90b5e8df_0;
            }

            @Override // androidx.room.PooledConnection
            public final Object usePrepared(String str, Function1 function1, Continuation continuation) {
                return SupportPooledConnection.this.usePrepared(str, function1, continuation);
            }
        }

        public SupportPooledConnection(ViewModelStore viewModelStore) {
            this.delegate$ar$class_merging$90b5e8df_0 = viewModelStore;
        }

        @Override // androidx.room.coroutines.RawConnectionAccessor
        public final ViewModelStore getRawConnection$ar$class_merging$ar$class_merging() {
            return this.delegate$ar$class_merging$90b5e8df_0;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.sqlite.db.SupportSQLiteDatabase, java.lang.Object] */
        public final Object inTransaction$ar$ds() {
            return Boolean.valueOf(this.delegate$ar$class_merging$90b5e8df_0.ViewModelStore$ar$map.inTransaction());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:15:0x007f  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0038  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        /* JADX WARN: Type inference failed for: r10v3, types: [androidx.sqlite.db.SupportSQLiteDatabase, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r8v10 */
        /* JADX WARN: Type inference failed for: r8v14, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r8v2 */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v8, types: [androidx.sqlite.db.SupportSQLiteDatabase] */
        /* JADX WARN: Type inference failed for: r9v0, types: [kotlin.jvm.functions.Function2] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object transaction$ar$edu(int r8, kotlin.jvm.functions.Function2 r9, kotlin.coroutines.Continuation r10) {
            /*
                r7 = this;
                boolean r0 = r10 instanceof androidx.room.RoomConnectionManager$SupportPooledConnection$transaction$1
                if (r0 == 0) goto L13
                r0 = r10
                androidx.room.RoomConnectionManager$SupportPooledConnection$transaction$1 r0 = (androidx.room.RoomConnectionManager$SupportPooledConnection$transaction$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.room.RoomConnectionManager$SupportPooledConnection$transaction$1 r0 = new androidx.room.RoomConnectionManager$SupportPooledConnection$transaction$1
                r0.<init>(r7, r10)
            L18:
                java.lang.Object r10 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 0
                r4 = 0
                r5 = 1
                if (r2 == 0) goto L38
                if (r2 != r5) goto L30
                java.lang.Object r8 = r0.L$1
                androidx.room.RoomConnectionManager$SupportPooledConnection r9 = r0.L$0$ar$dn
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L2d androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L8b
                goto L73
            L2d:
                r10 = move-exception
                goto L8c
            L30:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r9)
                throw r8
            L38:
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)
                androidx.lifecycle.ViewModelStore r10 = r7.delegate$ar$class_merging$90b5e8df_0
                java.lang.Object r10 = r10.ViewModelStore$ar$map
                boolean r2 = r10.inTransaction()
                if (r2 != 0) goto L47
                r7.currentTransactionType$ar$edu = r8
            L47:
                int r2 = r8 + (-1)
                if (r8 == 0) goto L98
                if (r2 == 0) goto L5b
                if (r2 == r5) goto L57
                r8 = 2
                if (r2 == r8) goto L53
                goto L5e
            L53:
                r10.beginTransaction()
                goto L5e
            L57:
                r10.beginTransactionNonExclusive()
                goto L5e
            L5b:
                r10.beginTransactionReadOnly()
            L5e:
                androidx.room.RoomConnectionManager$SupportPooledConnection$SupportTransactor r8 = new androidx.room.RoomConnectionManager$SupportPooledConnection$SupportTransactor     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                r8.<init>()     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                r0.L$0$ar$dn = r7     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                r0.L$1 = r10     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                r0.label = r5     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                java.lang.Object r8 = r9.invoke(r8, r0)     // Catch: java.lang.Throwable -> L83 androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L89
                if (r8 == r1) goto L82
                r9 = r7
                r6 = r10
                r10 = r8
                r8 = r6
            L73:
                r8.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L2d androidx.room.RoomConnectionManager.SupportPooledConnection.RollbackException -> L8b
                r8.endTransaction()
                boolean r8 = r8.inTransaction()
                if (r8 != 0) goto L81
                r9.currentTransactionType$ar$edu = r3
            L81:
                return r10
            L82:
                return r1
            L83:
                r8 = move-exception
                r9 = r7
                r6 = r10
                r10 = r8
                r8 = r6
                goto L8c
            L89:
                r9 = r7
                r8 = r10
            L8b:
                throw r4     // Catch: java.lang.Throwable -> L2d
            L8c:
                r8.endTransaction()
                boolean r8 = r8.inTransaction()
                if (r8 != 0) goto L97
                r9.currentTransactionType$ar$edu = r3
            L97:
                throw r10
            L98:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomConnectionManager.SupportPooledConnection.transaction$ar$edu(int, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
        }

        @Override // androidx.room.PooledConnection
        public final Object usePrepared(String str, Function1 function1, Continuation continuation) {
            SupportSQLiteStatement m32prepare = this.delegate$ar$class_merging$90b5e8df_0.m32prepare(str);
            try {
                return function1.invoke(m32prepare);
            } finally {
                m32prepare.close();
            }
        }

        public final Object withTransaction$ar$edu(int i, Function2 function2, Continuation continuation) {
            return transaction$ar$edu(i, function2, continuation);
        }
    }

    public RoomConnectionManager(DatabaseConfiguration databaseConfiguration, Function1 function1) {
        this.configuration = databaseConfiguration;
        this.callbacks = databaseConfiguration.callbacks;
        final OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(this, 13);
        this.connectionPool$ar$class_merging = new SupportConnectionPool(new ViewModelStore((SupportSQLiteOpenHelper) function1.invoke(new DatabaseConfiguration(databaseConfiguration.context, databaseConfiguration.name, databaseConfiguration.sqliteOpenHelperFactory, databaseConfiguration.migrationContainer$ar$class_merging$ar$class_merging, OnDeviceLanguageIdentificationLogEvent.plus(databaseConfiguration.callbacks, new AccessibilityWindowInfoCompat.Api21Impl() { // from class: androidx.room.RoomConnectionManager$installOnOpenCallback$newCallbacks$1
            @Override // androidx.core.view.accessibility.AccessibilityWindowInfoCompat.Api21Impl
            public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                Function1.this.invoke(supportSQLiteDatabase);
            }
        }), databaseConfiguration.allowMainThreadQueries, databaseConfiguration.journalMode$ar$edu, databaseConfiguration.queryExecutor, databaseConfiguration.transactionExecutor, databaseConfiguration.requireMigration, databaseConfiguration.allowDestructiveMigrationOnDowngrade, databaseConfiguration.migrationNotRequiredFrom, databaseConfiguration.typeConverters, databaseConfiguration.autoMigrationSpecs))));
        init();
    }

    private final void init() {
        boolean z;
        SupportSQLiteOpenHelper supportOpenHelper$room_runtime_release = getSupportOpenHelper$room_runtime_release();
        if (supportOpenHelper$room_runtime_release != null) {
            if (this.configuration.journalMode$ar$edu == 3) {
                z = true;
            } else {
                z = false;
            }
            supportOpenHelper$room_runtime_release.setWriteAheadLoggingEnabled(z);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.sqlite.db.SupportSQLiteOpenHelper, java.lang.Object] */
    public final SupportSQLiteOpenHelper getSupportOpenHelper$room_runtime_release() {
        SupportConnectionPool supportConnectionPool = this.connectionPool$ar$class_merging;
        if (!(supportConnectionPool instanceof SupportConnectionPool)) {
            supportConnectionPool = null;
        }
        if (supportConnectionPool == null) {
            return null;
        }
        return supportConnectionPool.supportDriver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.ViewModelStore$ar$map;
    }
}
