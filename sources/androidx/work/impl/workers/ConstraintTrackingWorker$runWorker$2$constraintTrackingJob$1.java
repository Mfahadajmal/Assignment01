package androidx.work.impl.workers;

import android.content.Context;
import androidx.lifecycle.ViewModelStore;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.impl.model.WorkSpec;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason;
    final /* synthetic */ Object ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future;
    final /* synthetic */ Object ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker;
    final /* synthetic */ Object ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(ViewModelStore viewModelStore, WorkSpec workSpec, AtomicInteger atomicInteger, ListenableFuture listenableFuture, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker = viewModelStore;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec = workSpec;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason = atomicInteger;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future = listenableFuture;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [androidx.work.ForegroundUpdater, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                Object obj2 = this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason;
                Object obj3 = this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker;
                return new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1((ConstraintTrackingWorker) obj2, (ListenableWorker) obj3, (ViewModelStore) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec, (WorkSpec) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future, continuation, 2);
            }
            Object obj4 = this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future;
            return new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1((ListenableWorker) obj4, (WorkSpec) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason, (ForegroundUpdater) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec, (Context) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker, continuation, 1);
        }
        Object obj5 = this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker;
        Object obj6 = this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec;
        return new ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1((ViewModelStore) obj5, (WorkSpec) obj6, (AtomicInteger) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason, (ListenableFuture) this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0052, code lost:
    
        if (r6 != r0) goto L19;
     */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.work.ForegroundUpdater, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.switching_field
            r1 = 1
            if (r0 == 0) goto L9a
            if (r0 == r1) goto L2e
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r5.label
            if (r2 == 0) goto L11
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            goto L2d
        L11:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            java.lang.Object r6 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason
            java.lang.Object r2 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker
            java.lang.Object r3 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec
            java.lang.Object r4 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future
            r5.label = r1
            androidx.work.impl.model.WorkSpec r4 = (androidx.work.impl.model.WorkSpec) r4
            androidx.lifecycle.ViewModelStore r3 = (androidx.lifecycle.ViewModelStore) r3
            androidx.work.ListenableWorker r2 = (androidx.work.ListenableWorker) r2
            androidx.work.impl.workers.ConstraintTrackingWorker r6 = (androidx.work.impl.workers.ConstraintTrackingWorker) r6
            java.lang.Object r6 = r6.runWorker$ar$class_merging$ar$class_merging$ar$class_merging(r2, r3, r4, r5)
            if (r6 != r0) goto L2d
            return r0
        L2d:
            return r6
        L2e:
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r5.label
            if (r2 == 0) goto L3e
            if (r2 == r1) goto L3a
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            goto L79
        L3a:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            goto L54
        L3e:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            java.lang.Object r6 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future
            r2 = r6
            androidx.work.ListenableWorker r2 = (androidx.work.ListenableWorker) r2
            com.google.common.util.concurrent.ListenableFuture r2 = r2.getForegroundInfoAsync()
            r5.label = r1
            androidx.work.ListenableWorker r6 = (androidx.work.ListenableWorker) r6
            java.lang.Object r6 = androidx.work.impl.WorkerWrapperKt.awaitWithin(r2, r6, r5)
            if (r6 == r0) goto L99
        L54:
            androidx.work.ForegroundInfo r6 = (androidx.work.ForegroundInfo) r6
            if (r6 == 0) goto L7a
            int r1 = androidx.work.impl.utils.WorkForegroundKt.WorkForegroundKt$ar$NoOp
            androidx.work.Logger.get$ar$ds$16341a92_0()
            java.lang.Object r1 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec
            java.lang.Object r2 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker
            java.lang.Object r3 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future
            androidx.work.ListenableWorker r3 = (androidx.work.ListenableWorker) r3
            java.util.UUID r3 = r3.getId()
            android.content.Context r2 = (android.content.Context) r2
            com.google.common.util.concurrent.ListenableFuture r6 = r1.setForegroundAsync(r2, r3, r6)
            r1 = 2
            r5.label = r1
            java.lang.Object r6 = android.support.v7.widget.GridLayoutManager.Api21Impl.await(r6, r5)
            if (r6 != r0) goto L79
            goto L99
        L79:
            return r6
        L7a:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "Worker was marked important ("
            r6.<init>(r0)
            java.lang.Object r0 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason
            androidx.work.impl.model.WorkSpec r0 = (androidx.work.impl.model.WorkSpec) r0
            java.lang.String r0 = r0.workerClassName
            r6.append(r0)
            java.lang.String r0 = ") but did not provide ForegroundInfo"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r6)
            throw r0
        L99:
            return r0
        L9a:
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r5.label
            if (r2 == 0) goto La4
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            goto Lb8
        La4:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
            java.lang.Object r6 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker
            java.lang.Object r2 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec
            r5.label = r1
            androidx.work.impl.model.WorkSpec r2 = (androidx.work.impl.model.WorkSpec) r2
            androidx.lifecycle.ViewModelStore r6 = (androidx.lifecycle.ViewModelStore) r6
            java.lang.Object r6 = androidx.work.impl.workers.ConstraintTrackingWorkerKt.awaitConstraintsNotMet$ar$class_merging$ar$class_merging$ar$class_merging(r6, r2, r5)
            if (r6 != r0) goto Lb8
            return r0
        Lb8:
            java.lang.Number r6 = (java.lang.Number) r6
            int r6 = r6.intValue()
            java.lang.Object r0 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason
            java.util.concurrent.atomic.AtomicInteger r0 = (java.util.concurrent.atomic.AtomicInteger) r0
            r0.set(r6)
            java.lang.Object r6 = r5.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future
            r6.cancel(r1)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.workers.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(ListenableWorker listenableWorker, WorkSpec workSpec, ForegroundUpdater foregroundUpdater, Context context, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future = listenableWorker;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason = workSpec;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec = foregroundUpdater;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker = context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1(ConstraintTrackingWorker constraintTrackingWorker, ListenableWorker listenableWorker, ViewModelStore viewModelStore, WorkSpec workSpec, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$atomicReason = constraintTrackingWorker;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workConstraintsTracker = listenableWorker;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$workSpec = viewModelStore;
        this.ConstraintTrackingWorker$runWorker$2$constraintTrackingJob$1$ar$$future = workSpec;
    }
}
