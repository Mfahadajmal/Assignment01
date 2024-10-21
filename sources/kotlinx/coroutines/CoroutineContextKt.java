package kotlinx.coroutines;

import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CoroutineContextKt {
    private static final CoroutineContext foldCopies(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, final boolean z) {
        boolean hasCopyableElements = hasCopyableElements(coroutineContext);
        boolean hasCopyableElements2 = hasCopyableElements(coroutineContext2);
        if (!hasCopyableElements && !hasCopyableElements2) {
            return coroutineContext.plus(coroutineContext2);
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = coroutineContext2;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(EmptyCoroutineContext.INSTANCE, new Function2() { // from class: kotlinx.coroutines.CoroutineContextKt$foldCopies$folded$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                CopyableThreadContextElement copyableThreadContextElement;
                CoroutineContext coroutineContext4 = (CoroutineContext) obj;
                CoroutineContext.Element element = (CoroutineContext.Element) obj2;
                if (!(element instanceof CopyableThreadContextElement)) {
                    return coroutineContext4.plus(element);
                }
                if (((CoroutineContext) Ref$ObjectRef.this.element).get(element.getKey()) == null) {
                    if (z) {
                        copyableThreadContextElement = ((CopyableThreadContextElement) element).copyForChild();
                    } else {
                        copyableThreadContextElement = (CopyableThreadContextElement) element;
                    }
                    return coroutineContext4.plus(copyableThreadContextElement);
                }
                Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                ref$ObjectRef2.element = ((CoroutineContext) ref$ObjectRef2.element).minusKey(element.getKey());
                return coroutineContext4.plus(((CopyableThreadContextElement) element).mergeForChild$ar$ds());
            }
        });
        if (hasCopyableElements2) {
            ref$ObjectRef.element = ((CoroutineContext) ref$ObjectRef.element).fold(EmptyCoroutineContext.INSTANCE, CombinedContext$toString$1.INSTANCE$ar$class_merging$eed8d28d_0);
        }
        return coroutineContext3.plus((CoroutineContext) ref$ObjectRef.element);
    }

    private static final boolean hasCopyableElements(CoroutineContext coroutineContext) {
        return ((Boolean) coroutineContext.fold(false, CombinedContext$toString$1.INSTANCE$ar$class_merging$e3050a31_0)).booleanValue();
    }

    public static final CoroutineContext newCoroutineContext(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        return !hasCopyableElements(coroutineContext2) ? coroutineContext.plus(coroutineContext2) : foldCopies(coroutineContext, coroutineContext2, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0017, code lost:
    
        if (r2 != 0) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001c, code lost:
    
        if ((r2 instanceof kotlinx.coroutines.UndispatchedCoroutine) == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001e, code lost:
    
        r1 = (kotlinx.coroutines.UndispatchedCoroutine) r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0021, code lost:
    
        if (r1 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0023, code lost:
    
        r1.saveThreadContext(r3, r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0026, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        if (r3.get(kotlinx.coroutines.UndispatchedMarker.INSTANCE) != null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
    
        if ((r2 instanceof kotlinx.coroutines.DispatchedCoroutine) == false) goto L10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0013, code lost:
    
        r2 = r2.getCallerFrame();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlinx.coroutines.UndispatchedCoroutine updateUndispatchedCompletion(kotlin.coroutines.Continuation r2, kotlin.coroutines.CoroutineContext r3, java.lang.Object r4) {
        /*
            boolean r0 = r2 instanceof kotlin.coroutines.jvm.internal.CoroutineStackFrame
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.INSTANCE
            kotlin.coroutines.CoroutineContext$Element r0 = r3.get(r0)
            if (r0 == 0) goto L26
        Le:
            boolean r0 = r2 instanceof kotlinx.coroutines.DispatchedCoroutine
            if (r0 == 0) goto L13
            goto L21
        L13:
            kotlin.coroutines.jvm.internal.CoroutineStackFrame r2 = r2.getCallerFrame()
            if (r2 != 0) goto L1a
            goto L21
        L1a:
            boolean r0 = r2 instanceof kotlinx.coroutines.UndispatchedCoroutine
            if (r0 == 0) goto Le
            r1 = r2
            kotlinx.coroutines.UndispatchedCoroutine r1 = (kotlinx.coroutines.UndispatchedCoroutine) r1
        L21:
            if (r1 == 0) goto L26
            r1.saveThreadContext(r3, r4)
        L26:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineContextKt.updateUndispatchedCompletion(kotlin.coroutines.Continuation, kotlin.coroutines.CoroutineContext, java.lang.Object):kotlinx.coroutines.UndispatchedCoroutine");
    }

    public static final CoroutineContext newCoroutineContext(CoroutineScope coroutineScope, CoroutineContext coroutineContext) {
        CoroutineContext foldCopies = foldCopies(coroutineScope.getCoroutineContext(), coroutineContext, true);
        CoroutineContext plus = DebugKt.DEBUG ? foldCopies.plus(new CoroutineId(DebugKt.COROUTINE_ID.incrementAndGet())) : foldCopies;
        return (foldCopies == Dispatchers.Default || foldCopies.get(ContinuationInterceptor.Key) != null) ? plus : plus.plus(Dispatchers.Default);
    }
}
