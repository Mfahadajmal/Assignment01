package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$SafeFuture;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ListenableFutureKt$launchFuture$1$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ CallbackToFutureAdapter$Completer $completer;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListenableFutureKt$launchFuture$1$2(Function2 function2, CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer, Continuation continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$completer = callbackToFutureAdapter$Completer;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ListenableFutureKt$launchFuture$1$2 listenableFutureKt$launchFuture$1$2 = new ListenableFutureKt$launchFuture$1$2(this.$block, this.$completer, continuation);
        listenableFutureKt$launchFuture$1$2.L$0 = obj;
        return listenableFutureKt$launchFuture$1$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((ListenableFutureKt$launchFuture$1$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        try {
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Function2 function2 = this.$block;
                this.label = 1;
                obj = function2.invoke(coroutineScope, this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            this.$completer.set$ar$ds(obj);
        } catch (CancellationException unused) {
            CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer = this.$completer;
            callbackToFutureAdapter$Completer.attemptedSetting = true;
            CallbackToFutureAdapter$SafeFuture callbackToFutureAdapter$SafeFuture = callbackToFutureAdapter$Completer.future;
            if (callbackToFutureAdapter$SafeFuture != null && callbackToFutureAdapter$SafeFuture.delegate.cancel(true)) {
                callbackToFutureAdapter$Completer.setCompletedNormally();
            }
        } catch (Throwable th) {
            this.$completer.setException$ar$ds(th);
        }
        return Unit.INSTANCE;
    }
}
