package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LifecycleCoroutineScopeImpl$register$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    final /* synthetic */ LifecycleCoroutineScope this$0$ar$class_merging$32664695_0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LifecycleCoroutineScopeImpl$register$1(LifecycleCoroutineScope lifecycleCoroutineScope, Continuation continuation) {
        super(2, continuation);
        this.this$0$ar$class_merging$32664695_0 = lifecycleCoroutineScope;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        LifecycleCoroutineScopeImpl$register$1 lifecycleCoroutineScopeImpl$register$1 = new LifecycleCoroutineScopeImpl$register$1(this.this$0$ar$class_merging$32664695_0, continuation);
        lifecycleCoroutineScopeImpl$register$1.L$0 = obj;
        return lifecycleCoroutineScopeImpl$register$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((LifecycleCoroutineScopeImpl$register$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        if (this.this$0$ar$class_merging$32664695_0.lifecycle.getCurrentState().compareTo(Lifecycle.State.INITIALIZED) < 0) {
            ScannerAutoZoomEvent.PredictedArea.cancel(coroutineScope.getCoroutineContext(), null);
        } else {
            LifecycleCoroutineScope lifecycleCoroutineScope = this.this$0$ar$class_merging$32664695_0;
            lifecycleCoroutineScope.lifecycle.addObserver(lifecycleCoroutineScope);
        }
        return Unit.INSTANCE;
    }
}
