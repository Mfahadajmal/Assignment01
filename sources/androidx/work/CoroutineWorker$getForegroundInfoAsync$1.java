package androidx.work;

import android.database.Cursor;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatReceiveContentHelper$OnDropApi24Impl;
import android.util.Log;
import androidx.core.widget.NestedScrollView;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImplExtKt;
import androidx.work.impl.WorkerStoppedException;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.WorkerWrapper$Resolution$Failed;
import androidx.work.impl.WorkerWrapper$Resolution$Finished;
import androidx.work.impl.WorkerWrapper$Resolution$ResetWorkerStatus;
import androidx.work.impl.WorkerWrapperKt;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.DependencyDao_Impl;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkProgressDao_Impl;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import com.google.android.accessibility.selecttospeak.ui.PlusMinusButtons;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationLoadLogEvent;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineWorker$getForegroundInfoAsync$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(CoroutineWorker coroutineWorker, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = coroutineWorker;
    }

    public static final Boolean invokeSuspend$lambda$1$ar$class_merging(WorkManagerImplExtKt workManagerImplExtKt, WorkerWrapper workerWrapper) {
        long currentTimeMillis;
        boolean z;
        boolean z2 = true;
        if (workManagerImplExtKt instanceof WorkerWrapper$Resolution$Finished) {
            AppCompatDelegateImpl.Api33Impl api33Impl = ((WorkerWrapper$Resolution$Finished) workManagerImplExtKt).result$ar$class_merging$ar$class_merging;
            WorkSpecDao workSpecDao = workerWrapper.workSpecDao;
            String str = workerWrapper.workSpecId;
            WorkDatabase workDatabase = workerWrapper.workDatabase;
            String str2 = workerWrapper.workSpecId;
            int state$ar$edu$fd856834_0 = workSpecDao.getState$ar$edu$fd856834_0(str);
            WorkProgressDao workProgressDao = workDatabase.workProgressDao();
            WorkProgressDao_Impl workProgressDao_Impl = (WorkProgressDao_Impl) workProgressDao;
            workProgressDao_Impl.__db.assertNotSuspendingTransaction();
            FrameworkSQLiteStatement acquire$ar$class_merging = workProgressDao_Impl.__preparedStmtOfDelete.acquire$ar$class_merging();
            acquire$ar$class_merging.bindString(1, str2);
            try {
                ((WorkProgressDao_Impl) workProgressDao).__db.beginTransaction();
                try {
                    acquire$ar$class_merging.executeUpdateDelete();
                    ((WorkProgressDao_Impl) workProgressDao).__db.setTransactionSuccessful();
                    if (state$ar$edu$fd856834_0 != 0) {
                        if (state$ar$edu$fd856834_0 == 2) {
                            if (api33Impl instanceof ListenableWorker$Result$Success) {
                                String str3 = WorkerWrapperKt.TAG;
                                Logger.get$ar$ds$16341a92_0();
                                if (workerWrapper.workSpec.isPeriodic()) {
                                    workerWrapper.resetPeriodic$ar$ds();
                                } else {
                                    workerWrapper.workSpecDao.setState$ar$edu$ar$ds(3, workerWrapper.workSpecId);
                                    Data data = ((ListenableWorker$Result$Success) api33Impl).mOutputData;
                                    data.getClass();
                                    workerWrapper.workSpecDao.setOutput(workerWrapper.workSpecId, data);
                                    DependencyDao dependencyDao = workerWrapper.dependencyDao;
                                    String str4 = workerWrapper.workSpecId;
                                    currentTimeMillis = System.currentTimeMillis();
                                    for (String str5 : dependencyDao.getDependentWorkIds(str4)) {
                                        if (workerWrapper.workSpecDao.getState$ar$edu$fd856834_0(str5) == 5) {
                                            DependencyDao dependencyDao2 = workerWrapper.dependencyDao;
                                            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)", 1);
                                            acquire.bindString(1, str5);
                                            DependencyDao_Impl dependencyDao_Impl = (DependencyDao_Impl) dependencyDao2;
                                            dependencyDao_Impl.__db.assertNotSuspendingTransaction();
                                            Cursor query$ar$ds$e1ca310e_0 = NestedScrollView.Api21Impl.query$ar$ds$e1ca310e_0(dependencyDao_Impl.__db, acquire);
                                            try {
                                                if (query$ar$ds$e1ca310e_0.moveToFirst() && query$ar$ds$e1ca310e_0.getInt(0) != 0) {
                                                    z = true;
                                                } else {
                                                    z = false;
                                                }
                                                if (z) {
                                                    Logger.get$ar$ds$16341a92_0();
                                                    workerWrapper.workSpecDao.setState$ar$edu$ar$ds(1, str5);
                                                    workerWrapper.workSpecDao.setLastEnqueueTime(str5, currentTimeMillis);
                                                }
                                            } finally {
                                                query$ar$ds$e1ca310e_0.close();
                                                acquire.release();
                                            }
                                        }
                                    }
                                }
                            } else if (api33Impl instanceof ListenableWorker$Result$Retry) {
                                String str6 = WorkerWrapperKt.TAG;
                                Logger.get$ar$ds$16341a92_0();
                                workerWrapper.reschedule$ar$ds(-256);
                            } else {
                                String str7 = WorkerWrapperKt.TAG;
                                Logger.get$ar$ds$16341a92_0();
                                if (workerWrapper.workSpec.isPeriodic()) {
                                    workerWrapper.resetPeriodic$ar$ds();
                                } else {
                                    workerWrapper.setFailed$ar$ds$ar$class_merging$ar$class_merging(api33Impl);
                                }
                            }
                        } else if (!AppCompatReceiveContentHelper$OnDropApi24Impl.isFinished$ar$edu(state$ar$edu$fd856834_0)) {
                            workerWrapper.reschedule$ar$ds(-512);
                        }
                    }
                    z2 = false;
                } finally {
                    ((WorkProgressDao_Impl) workProgressDao).__db.endTransaction();
                }
            } finally {
                workProgressDao_Impl.__preparedStmtOfDelete.release$ar$class_merging(acquire$ar$class_merging);
            }
        } else {
            if (workManagerImplExtKt instanceof WorkerWrapper$Resolution$Failed) {
                workerWrapper.setFailed$ar$ds$ar$class_merging$ar$class_merging(((WorkerWrapper$Resolution$Failed) workManagerImplExtKt).result$ar$class_merging$ar$class_merging);
            } else if (workManagerImplExtKt instanceof WorkerWrapper$Resolution$ResetWorkerStatus) {
                int i = ((WorkerWrapper$Resolution$ResetWorkerStatus) workManagerImplExtKt).reason;
                int state$ar$edu$fd856834_02 = workerWrapper.workSpecDao.getState$ar$edu$fd856834_0(workerWrapper.workSpecId);
                if (state$ar$edu$fd856834_02 != 0) {
                    if (!AppCompatReceiveContentHelper$OnDropApi24Impl.isFinished$ar$edu(state$ar$edu$fd856834_02)) {
                        String str8 = WorkerWrapperKt.TAG;
                        Logger.get$ar$ds$16341a92_0();
                        String str9 = workerWrapper.workSpecId;
                        Objects.toString(AppCompatReceiveContentHelper$OnDropApi24Impl.toStringGeneratedffb196af7127d286(state$ar$edu$fd856834_02));
                        workerWrapper.workSpecDao.setState$ar$edu$ar$ds(1, workerWrapper.workSpecId);
                        workerWrapper.workSpecDao.setStopReason(workerWrapper.workSpecId, i);
                        workerWrapper.workSpecDao.markWorkSpecScheduled$ar$ds(workerWrapper.workSpecId, -1L);
                    }
                } else {
                    state$ar$edu$fd856834_02 = 0;
                }
                String str10 = WorkerWrapperKt.TAG;
                Logger.get$ar$ds$16341a92_0();
                String str11 = workerWrapper.workSpecId;
                Objects.toString(AppCompatReceiveContentHelper$OnDropApi24Impl.toStringGeneratedffb196af7127d286(state$ar$edu$fd856834_02));
            } else {
                throw new NoWhenBranchMatchedException();
            }
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return new CoroutineWorker$getForegroundInfoAsync$1((ConstraintTrackingWorker) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 5);
                        }
                        return new CoroutineWorker$getForegroundInfoAsync$1((WorkerWrapper) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 4, (byte[]) null);
                    }
                    return new CoroutineWorker$getForegroundInfoAsync$1((WorkerWrapper) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 3);
                }
                return new CoroutineWorker$getForegroundInfoAsync$1((CoroutineWorker) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 2, (byte[]) null);
            }
            return new CoroutineWorker$getForegroundInfoAsync$1((PlusMinusButtons) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 1);
        }
        return new CoroutineWorker$getForegroundInfoAsync$1((CoroutineWorker) this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                        }
                        return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                    }
                    return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }
                return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((CoroutineWorker$getForegroundInfoAsync$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object workerWrapper$Resolution$Failed;
        int i = this.switching_field;
        int i2 = 1;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                            if (this.label != 0) {
                                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                            } else {
                                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                                Object obj2 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                                this.label = 1;
                                obj = ((ConstraintTrackingWorker) obj2).setupAndRunConstraintTrackingWork(this);
                                if (obj == coroutineSingletons) {
                                    return coroutineSingletons;
                                }
                            }
                            return obj;
                        }
                        CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                        byte[] bArr = null;
                        try {
                            if (this.label != 0) {
                                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                            } else {
                                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                                Object obj3 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                                JobImpl jobImpl = ((WorkerWrapper) obj3).workerJob$ar$class_merging;
                                CoroutineWorker$getForegroundInfoAsync$1 coroutineWorker$getForegroundInfoAsync$1 = new CoroutineWorker$getForegroundInfoAsync$1((WorkerWrapper) obj3, (Continuation) null, 3);
                                this.label = 1;
                                obj = OnDeviceSubjectSegmentationLoadLogEvent.withContext(jobImpl, coroutineWorker$getForegroundInfoAsync$1, this);
                                if (obj == coroutineSingletons2) {
                                    return coroutineSingletons2;
                                }
                            }
                            workerWrapper$Resolution$Failed = (WorkManagerImplExtKt) obj;
                        } catch (WorkerStoppedException e) {
                            workerWrapper$Resolution$Failed = new WorkerWrapper$Resolution$ResetWorkerStatus(e.reason);
                        } catch (CancellationException unused) {
                            workerWrapper$Resolution$Failed = new WorkerWrapper$Resolution$Failed(bArr);
                        } catch (Throwable th) {
                            String str = WorkerWrapperKt.TAG;
                            Logger.get$ar$ds$16341a92_0();
                            Log.e(str, "Unexpected error in WorkerWrapper", th);
                            workerWrapper$Resolution$Failed = new WorkerWrapper$Resolution$Failed(bArr);
                        }
                        Object obj4 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                        Object runInTransaction = ((WorkerWrapper) obj4).workDatabase.runInTransaction(new ImageDescriptionProcessor$$ExternalSyntheticLambda0(workerWrapper$Resolution$Failed, obj4, i2, bArr));
                        runInTransaction.getClass();
                        return runInTransaction;
                    }
                    CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
                    if (this.label != 0) {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    } else {
                        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                        Object obj5 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                        this.label = 1;
                        obj = ((WorkerWrapper) obj5).runWorker(this);
                        if (obj == coroutineSingletons3) {
                            return coroutineSingletons3;
                        }
                    }
                    return obj;
                }
                CoroutineSingletons coroutineSingletons4 = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Object obj6 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                    this.label = 1;
                    obj = ((CoroutineWorker) obj6).doWork(this);
                    if (obj == coroutineSingletons4) {
                        return coroutineSingletons4;
                    }
                }
                return obj;
            }
            CoroutineSingletons coroutineSingletons5 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                Object obj7 = this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0;
                this.label = 1;
                if (((PlusMinusButtons) obj7).sync$room_runtime_release(this) == coroutineSingletons5) {
                    return coroutineSingletons5;
                }
            }
            return Unit.INSTANCE;
        }
        CoroutineSingletons coroutineSingletons6 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            return obj;
        }
        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        this.label = 1;
        throw new IllegalStateException("Not implemented");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(CoroutineWorker coroutineWorker, Continuation continuation, int i, byte[] bArr) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = coroutineWorker;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(WorkerWrapper workerWrapper, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = workerWrapper;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(WorkerWrapper workerWrapper, Continuation continuation, int i, byte[] bArr) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = workerWrapper;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(ConstraintTrackingWorker constraintTrackingWorker, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = constraintTrackingWorker;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineWorker$getForegroundInfoAsync$1(PlusMinusButtons plusMinusButtons, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.CoroutineWorker$getForegroundInfoAsync$1$ar$this$0 = plusMinusButtons;
    }
}
