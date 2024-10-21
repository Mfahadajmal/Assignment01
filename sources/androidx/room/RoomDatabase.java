package androidx.room;

import _COROUTINE._BOUNDARY;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;
import androidx.activity.OnBackPressedDispatcher;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.core.widget.PopupWindowCompat$Api23Impl;
import androidx.lifecycle.ViewModelStore;
import androidx.room.migration.Migration;
import androidx.room.support.AutoClosingRoomOpenHelper;
import androidx.room.support.PrePackagedCopyOpenHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.work.CoroutineWorker$getForegroundInfoAsync$1;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.utils.SerialExecutorImpl;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceShadowRemovalLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import com.google.mlkit.logging.schema.RemoteConfigLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptyMap;
import kotlin.collections.EmptySet;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class RoomDatabase {
    public boolean allowMainThreadQueries;
    public RoomConnectionManager connectionManager;
    public CoroutineScope coroutineScope;
    public Executor internalQueryExecutor;
    public PlusMinusButtons internalTracker$ar$class_merging;
    public Executor internalTransactionExecutor;
    public volatile SupportSQLiteDatabase mDatabase;
    public final WorkName closeBarrier$ar$class_merging = new WorkName((char[]) null);
    private final ThreadLocal suspendingTransactionId = new ThreadLocal();
    public final Map typeConverters = new LinkedHashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public boolean allowDestructiveMigrationOnDowngrade;
        public boolean allowMainThreadQueries;
        private final Context context;
        private final KClass klass;
        private Set migrationStartAndEndVersions;
        private final String name;
        public Executor queryExecutor;
        public SupportSQLiteOpenHelper.Factory supportOpenHelperFactory;
        private Executor transactionExecutor;
        public final List callbacks = new ArrayList();
        private final List typeConverters = new ArrayList();
        private final List autoMigrationSpecs = new ArrayList();
        private final int journalMode$ar$edu = 1;
        public boolean requireMigration = true;
        private final ViewModelStore migrationContainer$ar$class_merging$ar$class_merging = new ViewModelStore((byte[]) null);
        private final Set migrationsNotRequiredFrom = new LinkedHashSet();

        public Builder(Context context, Class cls, String str) {
            this.klass = OnDeviceShadowRemovalLogEvent.getKotlinClass(cls);
            this.context = context;
            this.name = str;
        }

        public final Builder addMigrations(Migration... migrationArr) {
            if (this.migrationStartAndEndVersions == null) {
                this.migrationStartAndEndVersions = new HashSet();
            }
            Migration migration = migrationArr[0];
            Set set = this.migrationStartAndEndVersions;
            set.getClass();
            set.add(Integer.valueOf(migration.startVersion));
            Set set2 = this.migrationStartAndEndVersions;
            set2.getClass();
            set2.add(Integer.valueOf(migration.endVersion));
            ViewModelStore viewModelStore = this.migrationContainer$ar$class_merging$ar$class_merging;
            Migration[] migrationArr2 = (Migration[]) Arrays.copyOf(migrationArr, 1);
            migrationArr2.getClass();
            for (Migration migration2 : migrationArr2) {
                viewModelStore.addMigration(migration2);
            }
            return this;
        }

        /* JADX WARN: Type inference failed for: r6v18, types: [java.util.Map, java.lang.Object] */
        public final RoomDatabase build() {
            ActivityManager activityManager;
            Executor executor = this.queryExecutor;
            if (executor == null && this.transactionExecutor == null) {
                Executor executor2 = ArchTaskExecutor.sIOThreadExecutor;
                this.transactionExecutor = executor2;
                this.queryExecutor = executor2;
            } else if (executor != null && this.transactionExecutor == null) {
                this.transactionExecutor = executor;
            } else if (executor == null) {
                this.queryExecutor = this.transactionExecutor;
            }
            Set set = this.migrationStartAndEndVersions;
            if (set != null) {
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    int intValue = ((Number) it.next()).intValue();
                    if (!(!this.migrationsNotRequiredFrom.contains(Integer.valueOf(intValue)))) {
                        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(intValue, "Inconsistency detected. A Migration was supplied to addMigration(Migration... migrations) that has a start or end version equal to a start version supplied to fallbackToDestructiveMigrationFrom(int... startVersions). Start version: "));
                    }
                }
            }
            SupportSQLiteOpenHelper.Factory factory = this.supportOpenHelperFactory;
            if (factory == null) {
                factory = new FrameworkSQLiteOpenHelperFactory();
            }
            SupportSQLiteOpenHelper.Factory factory2 = factory;
            Context context = this.context;
            String str = this.name;
            ViewModelStore viewModelStore = this.migrationContainer$ar$class_merging$ar$class_merging;
            List list = this.callbacks;
            boolean z = this.allowMainThreadQueries;
            Object systemService = context.getSystemService("activity");
            if (systemService instanceof ActivityManager) {
                activityManager = (ActivityManager) systemService;
            } else {
                activityManager = null;
            }
            int i = 2;
            if (activityManager != null && !activityManager.isLowRamDevice()) {
                i = 3;
            }
            Executor executor3 = this.queryExecutor;
            if (executor3 != null) {
                Executor executor4 = this.transactionExecutor;
                if (executor4 == null) {
                    throw new IllegalArgumentException("Required value was null.");
                }
                DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(context, str, factory2, viewModelStore, list, z, i, executor3, executor4, this.requireMigration, this.allowDestructiveMigrationOnDowngrade, this.migrationsNotRequiredFrom, this.typeConverters, this.autoMigrationSpecs);
                RoomDatabase roomDatabase = (RoomDatabase) PopupWindowCompat$Api23Impl.findAndInstantiateDatabaseImpl$ar$ds(OnDeviceShadowRemovalLogEvent.getJavaClass(this.klass));
                try {
                    throw new NotImplementedError(null);
                } catch (NotImplementedError unused) {
                    roomDatabase.connectionManager = new RoomConnectionManager(databaseConfiguration, new OnBackPressedDispatcher.AnonymousClass1(roomDatabase, 14));
                    roomDatabase.internalTracker$ar$class_merging = roomDatabase.createInvalidationTracker$ar$class_merging();
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    Set requiredAutoMigrationSpecs = roomDatabase.getRequiredAutoMigrationSpecs();
                    ArrayList arrayList = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(requiredAutoMigrationSpecs));
                    Iterator it2 = requiredAutoMigrationSpecs.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(OnDeviceShadowRemovalLogEvent.getKotlinClass((Class) it2.next()));
                    }
                    Set set2 = OnDeviceLanguageIdentificationLogEvent.toSet(arrayList);
                    boolean[] zArr = new boolean[set2.size()];
                    Iterator it3 = set2.iterator();
                    while (true) {
                        int i2 = -1;
                        if (it3.hasNext()) {
                            KClass kClass = (KClass) it3.next();
                            int size = databaseConfiguration.autoMigrationSpecs.size() - 1;
                            if (size >= 0) {
                                while (true) {
                                    int i3 = size - 1;
                                    if (PopupWindowCompat$Api23Impl.isAssignableFrom(kClass, Reflection.getOrCreateKotlinClass(databaseConfiguration.autoMigrationSpecs.get(size).getClass()))) {
                                        zArr[size] = true;
                                        i2 = size;
                                        break;
                                    }
                                    if (i3 < 0) {
                                        break;
                                    }
                                    size = i3;
                                }
                            }
                            if (i2 >= 0) {
                                linkedHashMap.put(kClass, databaseConfiguration.autoMigrationSpecs.get(i2));
                            } else {
                                throw new IllegalArgumentException("A required auto migration spec (" + kClass.getQualifiedName() + ") is missing in the database configuration.");
                            }
                        } else {
                            int size2 = databaseConfiguration.autoMigrationSpecs.size() - 1;
                            if (size2 >= 0) {
                                while (true) {
                                    int i4 = size2 - 1;
                                    if (zArr[size2]) {
                                        if (i4 < 0) {
                                            break;
                                        }
                                        size2 = i4;
                                    } else {
                                        throw new IllegalArgumentException("Unexpected auto migration specs found. Annotate AutoMigrationSpec implementation with @ProvidedAutoMigrationSpec annotation or remove this spec from the builder.");
                                    }
                                }
                            }
                            LinkedHashMap linkedHashMap2 = new LinkedHashMap(OnDeviceLanguageIdentificationLogEvent.mapCapacity(linkedHashMap.size()));
                            for (Map.Entry entry : linkedHashMap.entrySet()) {
                                linkedHashMap2.put(OnDeviceShadowRemovalLogEvent.getJavaClass((KClass) entry.getKey()), entry.getValue());
                            }
                            for (Migration migration : roomDatabase.getAutoMigrations$ar$ds()) {
                                ViewModelStore viewModelStore2 = databaseConfiguration.migrationContainer$ar$class_merging$ar$class_merging;
                                int i5 = migration.startVersion;
                                int i6 = migration.endVersion;
                                Integer valueOf = Integer.valueOf(i5);
                                ?? r6 = viewModelStore2.ViewModelStore$ar$map;
                                if (r6.containsKey(valueOf)) {
                                    Map map = (Map) r6.get(valueOf);
                                    if (map == null) {
                                        map = EmptyMap.INSTANCE;
                                    }
                                    if (!map.containsKey(Integer.valueOf(i6))) {
                                    }
                                }
                                databaseConfiguration.migrationContainer$ar$class_merging$ar$class_merging.addMigration(migration);
                            }
                            Set<Map.Entry> entrySet = roomDatabase.getRequiredTypeConverters().entrySet();
                            LinkedHashMap linkedHashMap3 = new LinkedHashMap(OnDeviceSmartReplyLogEvent.SmartReply.coerceAtLeast(OnDeviceLanguageIdentificationLogEvent.mapCapacity(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(entrySet)), 16));
                            for (Map.Entry entry2 : entrySet) {
                                Class cls = (Class) entry2.getKey();
                                List list2 = (List) entry2.getValue();
                                KClass kotlinClass = OnDeviceShadowRemovalLogEvent.getKotlinClass(cls);
                                ArrayList arrayList2 = new ArrayList(OnDeviceLanguageIdentificationLogEvent.collectionSizeOrDefault$ar$ds(list2));
                                Iterator it4 = list2.iterator();
                                while (it4.hasNext()) {
                                    arrayList2.add(OnDeviceShadowRemovalLogEvent.getKotlinClass((Class) it4.next()));
                                }
                                Pair pair = new Pair(kotlinClass, arrayList2);
                                linkedHashMap3.put(pair.first, pair.second);
                            }
                            boolean[] zArr2 = new boolean[linkedHashMap3.size()];
                            for (Map.Entry entry3 : linkedHashMap3.entrySet()) {
                                KClass kClass2 = (KClass) entry3.getKey();
                                for (KClass kClass3 : (List) entry3.getValue()) {
                                    int size3 = databaseConfiguration.typeConverters.size() - 1;
                                    if (size3 >= 0) {
                                        while (true) {
                                            int i7 = size3 - 1;
                                            if (PopupWindowCompat$Api23Impl.isAssignableFrom(kClass3, Reflection.getOrCreateKotlinClass(databaseConfiguration.typeConverters.get(size3).getClass()))) {
                                                zArr2[size3] = true;
                                                break;
                                            }
                                            if (i7 < 0) {
                                                break;
                                            }
                                            size3 = i7;
                                        }
                                    }
                                    size3 = -1;
                                    if (size3 >= 0) {
                                        Object obj = databaseConfiguration.typeConverters.get(size3);
                                        kClass3.getClass();
                                        obj.getClass();
                                        roomDatabase.typeConverters.put(kClass3, obj);
                                    } else {
                                        throw new IllegalArgumentException("A required type converter (" + kClass3 + ") for " + kClass2.getQualifiedName() + " is missing in the database configuration.");
                                    }
                                }
                            }
                            int size4 = databaseConfiguration.typeConverters.size() - 1;
                            if (size4 >= 0) {
                                while (true) {
                                    int i8 = size4 - 1;
                                    if (zArr2[size4]) {
                                        if (i8 < 0) {
                                            break;
                                        }
                                        size4 = i8;
                                    } else {
                                        throw new IllegalArgumentException("Unexpected type converter " + databaseConfiguration.typeConverters.get(size4) + ". Annotate TypeConverter class with @ProvidedTypeConverter annotation or remove this converter from the builder.");
                                    }
                                }
                            }
                            RoomConnectionManager roomConnectionManager = roomDatabase.connectionManager;
                            if (roomConnectionManager == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
                                roomConnectionManager = null;
                            }
                            if (((PrePackagedCopyOpenHelper) roomDatabase.unwrapOpenHelper(PrePackagedCopyOpenHelper.class, roomConnectionManager.getSupportOpenHelper$room_runtime_release())) == null) {
                                RoomConnectionManager roomConnectionManager2 = roomDatabase.connectionManager;
                                if (roomConnectionManager2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
                                    roomConnectionManager2 = null;
                                }
                                if (((AutoClosingRoomOpenHelper) roomDatabase.unwrapOpenHelper(AutoClosingRoomOpenHelper.class, roomConnectionManager2.getSupportOpenHelper$room_runtime_release())) == null) {
                                    roomDatabase.internalQueryExecutor = databaseConfiguration.queryExecutor;
                                    roomDatabase.internalTransactionExecutor = new SerialExecutorImpl(databaseConfiguration.transactionExecutor, 1, null);
                                    Executor executor5 = roomDatabase.internalQueryExecutor;
                                    if (executor5 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("internalQueryExecutor");
                                        executor5 = null;
                                    }
                                    roomDatabase.coroutineScope = OtherError.CoroutineScope(OnDeviceObjectLoadLogEvent.plus((CoroutineContext.Element) RemoteConfigLogEvent.from(executor5), (CoroutineContext) ScannerAutoZoomEvent.SupervisorJob$default$ar$class_merging$ar$ds()));
                                    CoroutineScope coroutineScope = roomDatabase.coroutineScope;
                                    if (coroutineScope == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
                                        coroutineScope = null;
                                    }
                                    CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
                                    Executor executor6 = roomDatabase.internalTransactionExecutor;
                                    if (executor6 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("internalTransactionExecutor");
                                        executor6 = null;
                                    }
                                    coroutineContext.plus(RemoteConfigLogEvent.from(executor6));
                                    roomDatabase.allowMainThreadQueries = databaseConfiguration.allowMainThreadQueries;
                                    return roomDatabase;
                                }
                                throw null;
                            }
                            throw null;
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Required value was null.");
            }
        }
    }

    public final void assertNotMainThread() {
        if (this.allowMainThreadQueries || Looper.getMainLooper().getThread() != Thread.currentThread()) {
        } else {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    public final void assertNotSuspendingTransaction() {
        if (!inTransaction() && this.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    public final void beginTransaction() {
        assertNotMainThread();
        internalBeginTransaction();
    }

    protected abstract PlusMinusButtons createInvalidationTracker$ar$class_merging();

    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        throw new NotImplementedError(null);
    }

    public final void endTransaction() {
        internalEndTransaction();
    }

    public List getAutoMigrations$ar$ds() {
        return EmptyList.INSTANCE;
    }

    public final CoroutineScope getCoroutineScope$room_runtime_release() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            return null;
        }
        return coroutineScope;
    }

    public final PlusMinusButtons getInvalidationTracker$ar$class_merging() {
        PlusMinusButtons plusMinusButtons = this.internalTracker$ar$class_merging;
        if (plusMinusButtons == null) {
            Intrinsics.throwUninitializedPropertyAccessException("internalTracker");
            return null;
        }
        return plusMinusButtons;
    }

    public final SupportSQLiteOpenHelper getOpenHelper() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        SupportSQLiteOpenHelper supportOpenHelper$room_runtime_release = roomConnectionManager.getSupportOpenHelper$room_runtime_release();
        if (supportOpenHelper$room_runtime_release != null) {
            return supportOpenHelper$room_runtime_release;
        }
        throw new IllegalStateException("Cannot return a SupportSQLiteOpenHelper since no SupportSQLiteOpenHelper.Factory was configured with Room.");
    }

    public final CoroutineContext getQueryContext() {
        CoroutineScope coroutineScope = this.coroutineScope;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        }
        return coroutineScope.getCoroutineContext();
    }

    public Set getRequiredAutoMigrationSpecs() {
        return EmptySet.INSTANCE;
    }

    protected Map getRequiredTypeConverters() {
        return EmptyMap.INSTANCE;
    }

    public final boolean inCompatibilityMode$room_runtime_release() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        if (roomConnectionManager.getSupportOpenHelper$room_runtime_release() != null) {
            return true;
        }
        return false;
    }

    public final boolean inTransaction() {
        return getOpenHelper().getWritableDatabase().inTransaction();
    }

    public final void internalBeginTransaction() {
        assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = getOpenHelper().getWritableDatabase();
        if (!writableDatabase.inTransaction()) {
            OnDeviceSubjectSegmentationInferenceLogEvent.runBlocking$default$ar$ds(new CoroutineWorker$getForegroundInfoAsync$1(getInvalidationTracker$ar$class_merging(), (Continuation) null, 1));
        }
        if (writableDatabase.isWriteAheadLoggingEnabled()) {
            writableDatabase.beginTransactionNonExclusive();
        } else {
            writableDatabase.beginTransaction();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    public final void internalEndTransaction() {
        getOpenHelper().getWritableDatabase().endTransaction();
        if (!inTransaction()) {
            PlusMinusButtons invalidationTracker$ar$class_merging = getInvalidationTracker$ar$class_merging();
            ((TriggerBasedInvalidationTracker) invalidationTracker$ar$class_merging.PlusMinusButtons$ar$canPlus).refreshInvalidationAsync$room_runtime_release(invalidationTracker$ar$class_merging.PlusMinusButtons$ar$minusButtonFactory, invalidationTracker$ar$class_merging.PlusMinusButtons$ar$indexTextViewFactory);
        }
    }

    public final boolean isOpenInternal() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        SupportSQLiteDatabase supportSQLiteDatabase = roomConnectionManager.supportDatabase;
        if (supportSQLiteDatabase != null) {
            return supportSQLiteDatabase.isOpen();
        }
        return false;
    }

    public final void onClosed() {
        CoroutineScope coroutineScope = this.coroutineScope;
        RoomConnectionManager roomConnectionManager = null;
        if (coroutineScope == null) {
            Intrinsics.throwUninitializedPropertyAccessException("coroutineScope");
            coroutineScope = null;
        }
        Job job = (Job) coroutineScope.getCoroutineContext().get(Job.Key$ar$class_merging$e5be0816_0);
        if (job != null) {
            job.cancel(null);
            getInvalidationTracker$ar$class_merging().stopMultiInstanceInvalidation();
            RoomConnectionManager roomConnectionManager2 = this.connectionManager;
            if (roomConnectionManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            } else {
                roomConnectionManager = roomConnectionManager2;
            }
            roomConnectionManager.connectionPool$ar$class_merging.close();
            return;
        }
        Objects.toString(coroutineScope);
        throw new IllegalStateException("Scope cannot be cancelled because it does not have a job: ".concat(String.valueOf(coroutineScope)));
    }

    public final Object runInTransaction(Callable callable) {
        beginTransaction();
        try {
            Object call = callable.call();
            setTransactionSuccessful();
            return call;
        } finally {
            endTransaction();
        }
    }

    public final void setTransactionSuccessful() {
        getOpenHelper().getWritableDatabase().setTransactionSuccessful();
    }

    public final Object unwrapOpenHelper(Class cls, SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        if (cls.isInstance(supportSQLiteOpenHelper)) {
            return supportSQLiteOpenHelper;
        }
        if (supportSQLiteOpenHelper instanceof DelegatingOpenHelper) {
            return unwrapOpenHelper(cls, ((DelegatingOpenHelper) supportSQLiteOpenHelper).getDelegate());
        }
        return null;
    }

    public final Object useConnection$room_runtime_release(boolean z, Function2 function2, Continuation continuation) {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionManager");
            roomConnectionManager = null;
        }
        return roomConnectionManager.connectionPool$ar$class_merging.useConnection$ar$ds(function2, continuation);
    }
}
