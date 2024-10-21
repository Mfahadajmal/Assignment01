package androidx.room;

import androidx.core.widget.CompoundButtonCompat$Api23Impl;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.atomicfu.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker {
    public static final CompoundButtonCompat$Api23Impl Companion$ar$class_merging$b38b6612_0 = new CompoundButtonCompat$Api23Impl();
    private static final String[] TRIGGERS = {"INSERT", "UPDATE", "DELETE"};
    private final RoomDatabase database;
    public final TaskQueue observedTableStates$ar$class_merging;
    public final Map observerMap;
    private final Map shadowTablesMap;
    private final String[] tablesNames;
    private final Map viewTables;
    public final ReentrantLock observerMapLock = new ReentrantLock();
    private final AtomicBoolean pendingRefresh = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
    public Function0 onAllowRefresh = TriggerBasedInvalidationTracker$onAllowRefresh$1.INSTANCE;
    private final Map tableIdLookup = new LinkedHashMap();

    public TriggerBasedInvalidationTracker(RoomDatabase roomDatabase, Map map, Map map2, String[] strArr) {
        String str;
        this.database = roomDatabase;
        this.shadowTablesMap = map;
        this.viewTables = map2;
        String[] strArr2 = new String[7];
        for (int i = 0; i < 7; i++) {
            String lowerCase = strArr[i].toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            this.tableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str2 = (String) this.shadowTablesMap.get(strArr[i]);
            if (str2 != null) {
                str = str2.toLowerCase(Locale.ROOT);
                str.getClass();
            } else {
                str = null;
            }
            if (str != null) {
                lowerCase = str;
            }
            strArr2[i] = lowerCase;
        }
        this.tablesNames = strArr2;
        for (Map.Entry entry : this.shadowTablesMap.entrySet()) {
            String lowerCase2 = ((String) entry.getValue()).toLowerCase(Locale.ROOT);
            lowerCase2.getClass();
            if (this.tableIdLookup.containsKey(lowerCase2)) {
                String lowerCase3 = ((String) entry.getKey()).toLowerCase(Locale.ROOT);
                lowerCase3.getClass();
                Map map3 = this.tableIdLookup;
                map3.put(lowerCase3, OnDeviceLanguageIdentificationLogEvent.getOrImplicitDefaultNullable(map3, lowerCase2));
            }
        }
        this.observerMap = new LinkedHashMap();
        this.observedTableStates$ar$class_merging = new TaskQueue(7);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object addObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer r19, kotlin.coroutines.Continuation r20) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.addObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object checkInvalidatedTables(androidx.room.PooledConnection r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L3e
            if (r2 == r4) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r6 = r0.L$0
            java.util.Set r6 = (java.util.Set) r6
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            goto L66
        L2e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L36:
            java.lang.Object r6 = r0.L$0
            androidx.room.PooledConnection r6 = (androidx.room.PooledConnection) r6
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            goto L4f
        L3e:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            androidx.navigation.ActivityNavigator$hostActivity$1 r7 = androidx.navigation.ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$2c410286_0
            r0.L$0 = r6
            r0.label = r4
            java.lang.String r2 = "SELECT * FROM room_table_modification_log WHERE invalidated = 1"
            java.lang.Object r7 = r6.usePrepared(r2, r7, r0)
            if (r7 == r1) goto L67
        L4f:
            java.util.Set r7 = (java.util.Set) r7
            boolean r2 = r7.isEmpty()
            if (r2 != 0) goto L65
            r0.L$0 = r7
            r0.label = r3
            java.lang.String r2 = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1"
            java.lang.Object r6 = androidx.core.widget.CompoundButtonCompat$Api21Impl.execSQL(r6, r2, r0)
            if (r6 == r1) goto L64
            goto L65
        L64:
            return r1
        L65:
            r6 = r7
        L66:
            return r6
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.checkInvalidatedTables(androidx.room.PooledConnection, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0082 A[Catch: all -> 0x002c, TRY_LEAVE, TryCatch #2 {all -> 0x002c, blocks: (B:11:0x0028, B:12:0x007a, B:14:0x0082, B:44:0x00fd, B:47:0x0102, B:48:0x0105, B:16:0x0087, B:17:0x0091, B:19:0x0097, B:22:0x00aa, B:24:0x00b9, B:26:0x00ca, B:28:0x00d3, B:31:0x00d7, B:32:0x00ef, B:35:0x00f5, B:39:0x00dc, B:41:0x00ea, B:42:0x00ed), top: B:10:0x0028, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00f5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0091 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.util.Set] */
    /* JADX WARN: Type inference failed for: r6v8, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object notifyInvalidatedObservers(kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instructions count: 277
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.notifyInvalidatedObservers(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void refreshInvalidationAsync$room_runtime_release(Function0 function0, Function0 function02) {
        function0.getClass();
        function02.getClass();
        if (this.pendingRefresh.compareAndSet(false, true)) {
            OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(this.database.getCoroutineScope$room_runtime_release(), null, 0, new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(this, function02, (Continuation) null, 0), 3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object removeObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer r19, kotlin.coroutines.Continuation r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r20
            boolean r2 = r0 instanceof androidx.room.TriggerBasedInvalidationTracker$removeObserver$1
            if (r2 == 0) goto L17
            r2 = r0
            androidx.room.TriggerBasedInvalidationTracker$removeObserver$1 r2 = (androidx.room.TriggerBasedInvalidationTracker$removeObserver$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            androidx.room.TriggerBasedInvalidationTracker$removeObserver$1 r2 = new androidx.room.TriggerBasedInvalidationTracker$removeObserver$1
            r2.<init>(r1, r0)
        L1c:
            java.lang.Object r0 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 0
            r6 = 1
            if (r4 == 0) goto L37
            if (r4 != r6) goto L2f
            int r2 = r2.I$0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r0)
            goto L9d
        L2f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L37:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r0)
            java.util.concurrent.locks.ReentrantLock r4 = r1.observerMapLock
            r4.lock()
            java.util.Map r0 = r1.observerMap     // Catch: java.lang.Throwable -> La6
            r7 = r19
            java.lang.Object r0 = r0.remove(r7)     // Catch: java.lang.Throwable -> La6
            androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor r0 = (androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor) r0     // Catch: java.lang.Throwable -> La6
            r4.unlock()
            if (r0 == 0) goto L8d
            com.google.mlkit.common.sdkinternal.TaskQueue r4 = r1.observedTableStates$ar$class_merging
            java.lang.Object r7 = r4.TaskQueue$ar$pendingTasks
            java.util.concurrent.locks.ReentrantLock r7 = (java.util.concurrent.locks.ReentrantLock) r7
            r7.lock()
            java.lang.Object r0 = r0.WorkManagerTaskExecutor$ar$mMainThreadHandler
            r8 = r0
            int[] r8 = (int[]) r8     // Catch: java.lang.Throwable -> L88
            int r8 = r8.length     // Catch: java.lang.Throwable -> L88
            r9 = r5
            r10 = r9
        L5f:
            if (r9 >= r8) goto L81
            r11 = r0
            int[] r11 = (int[]) r11     // Catch: java.lang.Throwable -> L88
            r11 = r11[r9]     // Catch: java.lang.Throwable -> L88
            java.lang.Object r12 = r4.lock     // Catch: java.lang.Throwable -> L88
            r13 = r12
            long[] r13 = (long[]) r13     // Catch: java.lang.Throwable -> L88
            r14 = r13[r11]     // Catch: java.lang.Throwable -> L88
            r16 = -1
            long r16 = r14 + r16
            long[] r12 = (long[]) r12     // Catch: java.lang.Throwable -> L88
            r12[r11] = r16     // Catch: java.lang.Throwable -> L88
            r11 = 1
            int r11 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r11 != 0) goto L7e
            r4.isActive = r6     // Catch: java.lang.Throwable -> L88
            r10 = r6
        L7e:
            int r9 = r9 + 1
            goto L5f
        L81:
            r7.unlock()
            if (r10 == 0) goto L8d
            r0 = r6
            goto L8e
        L88:
            r0 = move-exception
            r7.unlock()
            throw r0
        L8d:
            r0 = r5
        L8e:
            if (r0 == 0) goto L9c
            r2.I$0 = r6
            r2.label = r6
            java.lang.Object r2 = r1.syncTriggers$room_runtime_release(r2)
            if (r2 == r3) goto L9b
            goto L9c
        L9b:
            return r3
        L9c:
            r2 = r0
        L9d:
            if (r6 == r2) goto La0
            goto La1
        La0:
            r5 = r6
        La1:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r5)
            return r0
        La6:
            r0 = move-exception
            r4.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.removeObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00be -> B:11:0x00c1). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object startTrackingTable(androidx.room.PooledConnection r12, int r13, kotlin.coroutines.Continuation r14) {
        /*
            r11 = this;
            boolean r0 = r14 instanceof androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1
            if (r0 == 0) goto L13
            r0 = r14
            androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$startTrackingTable$1
            r0.<init>(r11, r14)
        L18:
            java.lang.Object r14 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L51
            if (r2 == r4) goto L43
            if (r2 != r3) goto L3b
            int r12 = r0.I$2
            int r13 = r0.I$1
            int r2 = r0.I$0
            java.lang.String[] r5 = r0.L$2$ar$dn
            java.lang.Object r6 = r0.L$1
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r0.L$0
            androidx.room.PooledConnection r7 = (androidx.room.PooledConnection) r7
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r14)
            goto Lc1
        L3b:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L43:
            int r13 = r0.I$0
            java.lang.Object r12 = r0.L$1
            androidx.room.PooledConnection r12 = (androidx.room.PooledConnection) r12
            java.lang.Object r2 = r0.L$0
            androidx.room.TriggerBasedInvalidationTracker r2 = (androidx.room.TriggerBasedInvalidationTracker) r2
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r14)
            goto L6b
        L51:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r14)
            java.lang.String r14 = "INSERT OR IGNORE INTO room_table_modification_log VALUES("
            java.lang.String r2 = ", 0)"
            java.lang.String r14 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(r13, r14, r2)
            r0.L$0 = r11
            r0.L$1 = r12
            r0.I$0 = r13
            r0.label = r4
            java.lang.Object r14 = androidx.core.widget.CompoundButtonCompat$Api21Impl.execSQL(r12, r14, r0)
            if (r14 == r1) goto Lc6
            r2 = r11
        L6b:
            java.lang.String[] r14 = r2.tablesNames
            r14 = r14[r13]
            java.lang.String[] r2 = androidx.room.TriggerBasedInvalidationTracker.TRIGGERS
            r5 = 3
            r6 = 0
            r7 = r12
            r12 = r5
            r5 = r2
            r2 = r13
            r13 = r6
            r6 = r14
        L79:
            if (r13 >= r12) goto Lc3
            r14 = r5[r13]
            java.lang.String r8 = androidx.core.widget.CompoundButtonCompat$Api23Impl.getTriggerName$ar$ds(r6, r14)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "CREATE TEMP TRIGGER IF NOT EXISTS `"
            r9.<init>(r10)
            r9.append(r8)
            java.lang.String r8 = "` AFTER "
            r9.append(r8)
            r9.append(r14)
            java.lang.String r14 = " ON `"
            r9.append(r14)
            r9.append(r6)
            java.lang.String r14 = "` BEGIN UPDATE room_table_modification_log SET invalidated = 1 WHERE table_id = "
            r9.append(r14)
            r9.append(r2)
            java.lang.String r14 = " AND invalidated = 0; END"
            r9.append(r14)
            java.lang.String r14 = r9.toString()
            r0.L$0 = r7
            r0.L$1 = r6
            r0.L$2$ar$dn = r5
            r0.I$0 = r2
            r0.I$1 = r13
            r0.I$2 = r12
            r0.label = r3
            java.lang.Object r14 = androidx.core.widget.CompoundButtonCompat$Api21Impl.execSQL(r7, r14, r0)
            if (r14 != r1) goto Lc1
            return r1
        Lc1:
            int r13 = r13 + r4
            goto L79
        Lc3:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        Lc6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.startTrackingTable(androidx.room.PooledConnection, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0078 -> B:10:0x007a). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object stopTrackingTable(androidx.room.PooledConnection r10, int r11, kotlin.coroutines.Continuation r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1
            if (r0 == 0) goto L13
            r0 = r12
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3a
            if (r2 != r3) goto L32
            int r10 = r0.I$1
            int r11 = r0.I$0
            java.lang.String[] r2 = r0.L$2$ar$dn$ee13600b_0
            java.lang.String r4 = r0.L$1$ar$dn$ee13600b_0
            java.lang.Object r5 = r0.L$0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
            r12 = r4
            goto L7a
        L32:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3a:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r12)
            java.lang.String[] r12 = r9.tablesNames
            r11 = r12[r11]
            java.lang.String[] r12 = androidx.room.TriggerBasedInvalidationTracker.TRIGGERS
            r2 = 3
            r4 = 0
            r8 = r11
            r11 = r10
            r10 = r2
            r2 = r12
            r12 = r8
        L4a:
            if (r4 >= r10) goto L7e
            r5 = r2[r4]
            java.lang.String r5 = androidx.core.widget.CompoundButtonCompat$Api23Impl.getTriggerName$ar$ds(r12, r5)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "DROP TRIGGER IF EXISTS `"
            r6.<init>(r7)
            r6.append(r5)
            r5 = 96
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r0.L$0 = r11
            r0.L$1$ar$dn$ee13600b_0 = r12
            r0.L$2$ar$dn$ee13600b_0 = r2
            r0.I$0 = r4
            r0.I$1 = r10
            r0.label = r3
            java.lang.Object r5 = androidx.core.widget.CompoundButtonCompat$Api21Impl.execSQL(r11, r5, r0)
            if (r5 != r1) goto L78
            return r1
        L78:
            r5 = r11
            r11 = r4
        L7a:
            int r4 = r11 + 1
            r11 = r5
            goto L4a
        L7e:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.stopTrackingTable(androidx.room.PooledConnection, int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object syncTriggers$room_runtime_release(kotlin.coroutines.Continuation r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1
            r0.<init>(r8, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            androidx.work.impl.model.WorkName r0 = r0.L$0$ar$dn$88293588_0$ar$class_merging
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L29
            goto L55
        L29:
            r9 = move-exception
            goto L5e
        L2b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L33:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r9)
            androidx.room.RoomDatabase r9 = r8.database
            androidx.work.impl.model.WorkName r9 = r9.closeBarrier$ar$class_merging
            boolean r2 = r9.block$room_runtime_release()
            if (r2 == 0) goto L62
            androidx.room.RoomDatabase r2 = r8.database     // Catch: java.lang.Throwable -> L5a
            androidx.room.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 r4 = new androidx.room.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1     // Catch: java.lang.Throwable -> L5a
            r5 = 2
            r6 = 0
            r4.<init>(r8, r6, r5, r6)     // Catch: java.lang.Throwable -> L5a
            r0.L$0$ar$dn$88293588_0$ar$class_merging = r9     // Catch: java.lang.Throwable -> L5a
            r0.label = r3     // Catch: java.lang.Throwable -> L5a
            r3 = 0
            java.lang.Object r0 = r2.useConnection$room_runtime_release(r3, r4, r0)     // Catch: java.lang.Throwable -> L5a
            if (r0 == r1) goto L59
            r0 = r9
        L55:
            r0.unblock$room_runtime_release()
            goto L62
        L59:
            return r1
        L5a:
            r0 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
        L5e:
            r0.unblock$room_runtime_release()
            throw r9
        L62:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.syncTriggers$room_runtime_release(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
