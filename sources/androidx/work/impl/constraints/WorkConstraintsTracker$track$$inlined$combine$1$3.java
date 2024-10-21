package androidx.work.impl.constraints;

import androidx.work.impl.background.systemjob.SystemJobService;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkConstraintsTracker$track$$inlined$combine$1$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public WorkConstraintsTracker$track$$inlined$combine$1$3(Continuation continuation) {
        super(3, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        WorkConstraintsTracker$track$$inlined$combine$1$3 workConstraintsTracker$track$$inlined$combine$1$3 = new WorkConstraintsTracker$track$$inlined$combine$1$3(obj3);
        workConstraintsTracker$track$$inlined$combine$1$3.L$0 = obj;
        workConstraintsTracker$track$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return workConstraintsTracker$track$$inlined$combine$1$3.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SystemJobService.Api24Impl api24Impl;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        if (i == 0) {
            ?? r7 = this.L$0;
            SystemJobService.Api24Impl[] api24ImplArr = (SystemJobService.Api24Impl[]) this.L$1;
            int length = api24ImplArr.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    api24Impl = api24ImplArr[i2];
                    if (!Intrinsics.areEqual(api24Impl, ConstraintsState$ConstraintsMet.INSTANCE)) {
                        break;
                    }
                    i2++;
                } else {
                    api24Impl = null;
                    break;
                }
            }
            if (api24Impl == null) {
                api24Impl = ConstraintsState$ConstraintsMet.INSTANCE;
            }
            this.label = 1;
            if (r7.emit(api24Impl, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        }
        return Unit.INSTANCE;
    }
}
