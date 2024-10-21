package androidx.work.impl.constraints;

import androidx.lifecycle.ViewModelStore;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.model.WorkSpec;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkConstraintsTrackerKt$listen$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object WorkConstraintsTrackerKt$listen$1$ar$$listener;
    final /* synthetic */ Object WorkConstraintsTrackerKt$listen$1$ar$$spec;
    final /* synthetic */ Object WorkConstraintsTrackerKt$listen$1$ar$$this_listen;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerKt$listen$1(ViewModelStore viewModelStore, WorkSpec workSpec, OnConstraintsStateChangedListener onConstraintsStateChangedListener, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.WorkConstraintsTrackerKt$listen$1$ar$$this_listen = viewModelStore;
        this.WorkConstraintsTrackerKt$listen$1$ar$$spec = workSpec;
        this.WorkConstraintsTrackerKt$listen$1$ar$$listener = onConstraintsStateChangedListener;
    }

    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.Object, androidx.work.impl.constraints.OnConstraintsStateChangedListener] */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.work.ForegroundUpdater, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        if (this.switching_field != 0) {
            Object obj2 = this.WorkConstraintsTrackerKt$listen$1$ar$$spec;
            return new WorkConstraintsTrackerKt$listen$1((WorkerWrapper) obj2, (ListenableWorker) this.WorkConstraintsTrackerKt$listen$1$ar$$this_listen, (ForegroundUpdater) this.WorkConstraintsTrackerKt$listen$1$ar$$listener, continuation, 1);
        }
        Object obj3 = this.WorkConstraintsTrackerKt$listen$1$ar$$this_listen;
        return new WorkConstraintsTrackerKt$listen$1((ViewModelStore) obj3, (WorkSpec) this.WorkConstraintsTrackerKt$listen$1$ar$$spec, (OnConstraintsStateChangedListener) this.WorkConstraintsTrackerKt$listen$1$ar$$listener, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        if (this.switching_field != 0) {
            return ((WorkConstraintsTrackerKt$listen$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((WorkConstraintsTrackerKt$listen$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0058, code lost:
    
        if (r11 == r0) goto L22;
     */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.Object, androidx.work.impl.constraints.OnConstraintsStateChangedListener] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.work.ForegroundUpdater, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            int r0 = r10.switching_field
            r1 = 1
            if (r0 == 0) goto L76
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r10.label
            if (r2 == 0) goto L15
            if (r2 == r1) goto L11
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r11)
            goto L75
        L11:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r11)
            goto L5b
        L15:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r11)
            java.lang.Object r11 = r10.WorkConstraintsTrackerKt$listen$1$ar$$spec
            java.lang.Object r2 = r10.WorkConstraintsTrackerKt$listen$1$ar$$this_listen
            java.lang.Object r6 = r10.WorkConstraintsTrackerKt$listen$1$ar$$listener
            r10.label = r1
            int r1 = androidx.work.impl.utils.WorkForegroundKt.WorkForegroundKt$ar$NoOp
            androidx.work.impl.WorkerWrapper r11 = (androidx.work.impl.WorkerWrapper) r11
            androidx.work.impl.model.WorkSpec r5 = r11.workSpec
            boolean r1 = r5.expedited
            if (r1 == 0) goto L56
            int r1 = android.os.Build.VERSION.SDK_INT
            r3 = 31
            if (r1 < r3) goto L31
            goto L56
        L31:
            androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor r1 = r11.workTaskExecutor$ar$class_merging
            android.content.Context r7 = r11.appContext
            java.util.concurrent.Executor r11 = r1.getMainThreadExecutor()
            r11.getClass()
            kotlinx.coroutines.CoroutineDispatcher r11 = com.google.mlkit.logging.schema.RemoteConfigLogEvent.from(r11)
            androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1 r1 = new androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1
            r4 = r2
            androidx.work.ListenableWorker r4 = (androidx.work.ListenableWorker) r4
            r8 = 0
            r9 = 1
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9)
            java.lang.Object r11 = com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationLoadLogEvent.withContext(r11, r1, r10)
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r11 == r1) goto L58
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            goto L58
        L56:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
        L58:
            if (r11 != r0) goto L5b
            goto L74
        L5b:
            java.lang.String r11 = androidx.work.impl.WorkerWrapperKt.TAG
            androidx.work.Logger.get$ar$ds$16341a92_0()
            java.lang.Object r11 = r10.WorkConstraintsTrackerKt$listen$1$ar$$this_listen
            r1 = r11
            androidx.work.ListenableWorker r1 = (androidx.work.ListenableWorker) r1
            com.google.common.util.concurrent.ListenableFuture r1 = r1.startWork()
            r2 = 2
            r10.label = r2
            androidx.work.ListenableWorker r11 = (androidx.work.ListenableWorker) r11
            java.lang.Object r11 = androidx.work.impl.WorkerWrapperKt.awaitWithin(r1, r11, r10)
            if (r11 != r0) goto L75
        L74:
            return r0
        L75:
            return r11
        L76:
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r10.label
            if (r2 == 0) goto L80
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r11)
            goto La2
        L80:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r11)
            java.lang.Object r11 = r10.WorkConstraintsTrackerKt$listen$1$ar$$this_listen
            java.lang.Object r2 = r10.WorkConstraintsTrackerKt$listen$1$ar$$spec
            java.lang.Object r3 = r10.WorkConstraintsTrackerKt$listen$1$ar$$listener
            r4 = r2
            androidx.work.impl.model.WorkSpec r4 = (androidx.work.impl.model.WorkSpec) r4
            androidx.lifecycle.ViewModelStore r11 = (androidx.lifecycle.ViewModelStore) r11
            kotlinx.coroutines.flow.Flow r11 = r11.track(r4)
            androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1$1 r4 = new androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1$1
            androidx.work.impl.model.WorkSpec r2 = (androidx.work.impl.model.WorkSpec) r2
            r4.<init>()
            r10.label = r1
            java.lang.Object r11 = r11.collect(r4, r10)
            if (r11 != r0) goto La2
            return r0
        La2:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerKt$listen$1(WorkerWrapper workerWrapper, ListenableWorker listenableWorker, ForegroundUpdater foregroundUpdater, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.WorkConstraintsTrackerKt$listen$1$ar$$spec = workerWrapper;
        this.WorkConstraintsTrackerKt$listen$1$ar$$this_listen = listenableWorker;
        this.WorkConstraintsTrackerKt$listen$1$ar$$listener = foregroundUpdater;
    }
}
