package androidx.room;

import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState$ConstraintsNotMet;
import androidx.work.impl.constraints.NetworkRequestConstraintController;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.channels.ChannelCoroutine;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$refreshInvalidationAsync$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted;
    final /* synthetic */ Object TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, Function0 function0, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0 = triggerBasedInvalidationTracker;
        this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted = function0;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3((NetworkRequestConstraintController) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0, (ProducerCoroutine) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted, continuation, 2);
            }
            throw null;
        }
        return new TriggerBasedInvalidationTracker$refreshInvalidationAsync$3((TriggerBasedInvalidationTracker) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0, (Function0) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted, continuation, 0);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((TriggerBasedInvalidationTracker$refreshInvalidationAsync$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((TriggerBasedInvalidationTracker$refreshInvalidationAsync$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((TriggerBasedInvalidationTracker$refreshInvalidationAsync$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Object obj2 = this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0;
                    this.label = 1;
                    if (DebugStringsKt.delay(((NetworkRequestConstraintController) obj2).timeoutMs, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                Logger.get$ar$ds$16341a92_0();
                long j = WorkConstraintsTrackerKt.DefaultNetworkRequestTimeoutMs;
                long j2 = ((NetworkRequestConstraintController) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0).timeoutMs;
                ((ChannelCoroutine) this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted).mo257trySendJP2dKIU(new ConstraintsState$ConstraintsNotMet(7));
                return Unit.INSTANCE;
            }
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            throw null;
        }
        CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
        try {
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                Object obj3 = this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0;
                this.label = 1;
                if (((TriggerBasedInvalidationTracker) obj3).notifyInvalidatedObservers(this) == coroutineSingletons3) {
                    return coroutineSingletons3;
                }
            }
            this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted.invoke();
            return Unit.INSTANCE;
        } catch (Throwable th) {
            this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted.invoke();
            throw th;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$refreshInvalidationAsync$3(NetworkRequestConstraintController networkRequestConstraintController, ProducerCoroutine producerCoroutine, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$this$0 = networkRequestConstraintController;
        this.TriggerBasedInvalidationTracker$refreshInvalidationAsync$3$ar$$onRefreshCompleted = producerCoroutine;
    }
}
