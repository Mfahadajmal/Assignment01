package androidx.work.impl.workers;

import androidx.room.SharedSQLiteStatement$stmt$2;
import androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3;
import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2;
import kotlinx.coroutines.flow.internal.FlowCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1 implements Flow {
    final /* synthetic */ Object ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$ar$$this_unsafeTransform$inlined;
    private final /* synthetic */ int switching_field;

    /* compiled from: PG */
    /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements FlowCollector {
        final /* synthetic */ FlowCollector $this_unsafeFlow;

        /* compiled from: PG */
        /* renamed from: androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;

            public AnonymousClass1(Continuation continuation) {
                super(continuation);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return AnonymousClass2.this.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector flowCollector) {
            this.$this_unsafeFlow = flowCollector;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
        @Override // kotlinx.coroutines.flow.FlowCollector
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
            /*
                r4 = this;
                boolean r0 = r6 instanceof androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1.AnonymousClass2.AnonymousClass1
                if (r0 == 0) goto L13
                r0 = r6
                androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$1 r0 = (androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1.AnonymousClass2.AnonymousClass1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$1 r0 = new androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$2$1
                r0.<init>(r6)
            L18:
                java.lang.Object r6 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L2f
                if (r2 != r3) goto L27
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
                goto L41
            L27:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r6)
                throw r5
            L2f:
                com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r6)
                kotlinx.coroutines.flow.FlowCollector r6 = r4.$this_unsafeFlow
                boolean r2 = r5 instanceof androidx.work.impl.constraints.ConstraintsState$ConstraintsNotMet
                if (r2 == 0) goto L41
                r0.label = r3
                java.lang.Object r5 = r6.emit(r5, r0)
                if (r5 != r1) goto L41
                return r1
            L41:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.workers.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1(Object obj, int i) {
        this.switching_field = i;
        this.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$ar$$this_unsafeTransform$inlined = obj;
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [kotlinx.coroutines.flow.Flow, java.lang.Object] */
    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        if (this.switching_field != 0) {
            Object obj = this.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$ar$$this_unsafeTransform$inlined;
            CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2((Flow[]) obj, new SharedSQLiteStatement$stmt$2(obj, 5), new WorkConstraintsTracker$track$$inlined$combine$1$3(null), flowCollector, null);
            FlowCoroutine flowCoroutine = new FlowCoroutine(continuation.getContext(), continuation);
            Object startUndispatchedOrReturn = ToxicityDetectionCreateEvent.startUndispatchedOrReturn(flowCoroutine, flowCoroutine, combineKt$combineInternal$2);
            if (startUndispatchedOrReturn != CoroutineSingletons.COROUTINE_SUSPENDED) {
                startUndispatchedOrReturn = Unit.INSTANCE;
            }
            if (startUndispatchedOrReturn == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return startUndispatchedOrReturn;
            }
            return Unit.INSTANCE;
        }
        Object collect = this.ConstraintTrackingWorkerKt$awaitConstraintsNotMet$$inlined$filterIsInstance$1$ar$$this_unsafeTransform$inlined.collect(new AnonymousClass2(flowCollector), continuation);
        if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return collect;
        }
        return Unit.INSTANCE;
    }
}
