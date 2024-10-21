package kotlinx.coroutines.android;

import _COROUTINE._BOUNDARY;
import android.os.Handler;
import android.os.Looper;
import androidx.navigation.Navigator$navigate$1;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import io.grpc.internal.RetriableStream;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.NonDisposableHandle;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HandlerContext extends HandlerDispatcher implements Delay {
    public final Handler handler;
    private final HandlerContext immediate;
    private final boolean invokeImmediately;
    private final String name;

    public HandlerContext(Handler handler, String str) {
        this(handler, str, false);
    }

    private final void cancelOnRejection(CoroutineContext coroutineContext, Runnable runnable) {
        ScannerAutoZoomEvent.PredictedArea.cancel(coroutineContext, new CancellationException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(this, "The task was rejected, the handler underlying the dispatcher '", "' was closed")));
        Dispatchers.IO.dispatch(coroutineContext, runnable);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        if (!this.handler.post(runnable)) {
            cancelOnRejection(coroutineContext, runnable);
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof HandlerContext) {
            HandlerContext handlerContext = (HandlerContext) obj;
            if (handlerContext.handler == this.handler && handlerContext.invokeImmediately == this.invokeImmediately) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher
    public final /* synthetic */ MainCoroutineDispatcher getImmediate() {
        return this.immediate;
    }

    public final int hashCode() {
        int i;
        boolean z = this.invokeImmediately;
        int identityHashCode = System.identityHashCode(this.handler);
        if (true != z) {
            i = 1237;
        } else {
            i = 1231;
        }
        return i ^ identityHashCode;
    }

    @Override // kotlinx.coroutines.android.HandlerDispatcher, kotlinx.coroutines.Delay
    public final DisposableHandle invokeOnTimeout(long j, final Runnable runnable, CoroutineContext coroutineContext) {
        if (this.handler.postDelayed(runnable, OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(j, 4611686018427387903L))) {
            return new DisposableHandle() { // from class: kotlinx.coroutines.android.HandlerContext$$ExternalSyntheticLambda0
                @Override // kotlinx.coroutines.DisposableHandle
                public final void dispose() {
                    HandlerContext.this.handler.removeCallbacks(runnable);
                }
            };
        }
        cancelOnRejection(coroutineContext, runnable);
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        if (this.invokeImmediately) {
            if (Intrinsics.areEqual(Looper.myLooper(), this.handler.getLooper())) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        RetriableStream.Sublistener.AnonymousClass4 anonymousClass4 = new RetriableStream.Sublistener.AnonymousClass4(cancellableContinuationImpl, this, 2);
        if (this.handler.postDelayed(anonymousClass4, OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(j, 4611686018427387903L))) {
            cancellableContinuationImpl.invokeOnCancellation(new Navigator$navigate$1(this, anonymousClass4, 6));
        } else {
            cancelOnRejection(cancellableContinuationImpl.getContext(), anonymousClass4);
        }
    }

    @Override // kotlinx.coroutines.MainCoroutineDispatcher, kotlinx.coroutines.CoroutineDispatcher
    public final String toString() {
        String stringInternalImpl = toStringInternalImpl();
        if (stringInternalImpl == null) {
            String str = this.name;
            if (str == null) {
                str = this.handler.toString();
            }
            if (this.invokeImmediately) {
                return String.valueOf(str).concat(".immediate");
            }
            return str;
        }
        return stringInternalImpl;
    }

    public /* synthetic */ HandlerContext(Handler handler) {
        this(handler, null);
    }

    private HandlerContext(Handler handler, String str, boolean z) {
        this.handler = handler;
        this.name = str;
        this.invokeImmediately = z;
        this.immediate = z ? this : new HandlerContext(handler, str, true);
    }
}
