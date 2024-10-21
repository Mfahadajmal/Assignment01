package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.TextDetectionOptionalModuleOptions;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LazyStandaloneCoroutine extends StandaloneCoroutine {
    private final Continuation continuation;

    public LazyStandaloneCoroutine(CoroutineContext coroutineContext, Function2 function2) {
        super(coroutineContext, false);
        this.continuation = OnDevicePoseDetectionLogEvent.createCoroutineUnintercepted(function2, this, this);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void onStart() {
        try {
            DispatchedContinuationKt.resumeCancellableWith$ar$ds(OnDevicePoseDetectionLogEvent.intercepted(this.continuation), Unit.INSTANCE);
        } catch (Throwable th) {
            TextDetectionOptionalModuleOptions.dispatcherFailure(this, th);
        }
    }
}
