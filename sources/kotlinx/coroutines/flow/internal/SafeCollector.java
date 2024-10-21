package kotlinx.coroutines.flow.internal;

import com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SafeCollector extends ContinuationImpl implements FlowCollector, CoroutineStackFrame {
    public final CoroutineContext collectContext;
    public final int collectContextSize;
    public final FlowCollector collector;
    private Continuation completion_;
    private CoroutineContext lastEmissionContext;

    public SafeCollector(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        super(NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE);
        this.collector = flowCollector;
        this.collectContext = coroutineContext;
        this.collectContextSize = ((Number) coroutineContext.fold(0, CombinedContext$toString$1.INSTANCE$ar$class_merging$e958891e_0)).intValue();
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        try {
            CoroutineContext context = continuation.getContext();
            ScannerAutoZoomEvent.PredictedArea.ensureActive(context);
            CoroutineContext coroutineContext = this.lastEmissionContext;
            if (coroutineContext != context) {
                if (!(coroutineContext instanceof DownstreamExceptionContext)) {
                    if (((Number) context.fold(0, new SafeCollector_commonKt$checkContext$result$1(this, 0))).intValue() == this.collectContextSize) {
                        this.lastEmissionContext = context;
                    } else {
                        throw new IllegalStateException("Flow invariant is violated:\n\t\tFlow was collected in " + this.collectContext + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead");
                    }
                } else {
                    throw new IllegalStateException(OnDeviceStainRemovalLogEvent.trimIndent("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((DownstreamExceptionContext) coroutineContext).e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            "));
                }
            }
            this.completion_ = continuation;
            Function3 function3 = SafeCollectorKt.emitFun;
            FlowCollector flowCollector = this.collector;
            flowCollector.getClass();
            Object invoke = function3.invoke(flowCollector, obj, this);
            if (!Intrinsics.areEqual(invoke, CoroutineSingletons.COROUTINE_SUSPENDED)) {
                this.completion_ = null;
            }
            if (invoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return invoke;
            }
            return Unit.INSTANCE;
        } catch (Throwable th) {
            this.lastEmissionContext = new DownstreamExceptionContext(th, continuation.getContext());
            throw th;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        ?? r0 = this.completion_;
        if (r0 instanceof CoroutineStackFrame) {
            return r0;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        CoroutineContext coroutineContext = this.lastEmissionContext;
        if (coroutineContext == null) {
            return EmptyCoroutineContext.INSTANCE;
        }
        return coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Throwable m248exceptionOrNullimpl = Result.m248exceptionOrNullimpl(obj);
        if (m248exceptionOrNullimpl != null) {
            this.lastEmissionContext = new DownstreamExceptionContext(m248exceptionOrNullimpl, getContext());
        }
        Continuation continuation = this.completion_;
        if (continuation != null) {
            continuation.resumeWith(obj);
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final void releaseIntercepted() {
        super.releaseIntercepted();
    }
}
