package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AbstractFlow implements Flow {
    private final Function2 block;

    public AbstractFlow() {
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.AbstractFlow$collect$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = (kotlinx.coroutines.flow.AbstractFlow$collect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r0 = new kotlinx.coroutines.flow.AbstractFlow$collect$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L33
            if (r2 != r3) goto L2b
            kotlinx.coroutines.flow.internal.SafeCollector r6 = r0.L$0$ar$dn$8ecad6e3_0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L29
            goto L4a
        L29:
            r7 = move-exception
            goto L55
        L2b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L33:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            kotlinx.coroutines.flow.internal.SafeCollector r7 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            r7.<init>(r6, r2)
            r0.L$0$ar$dn$8ecad6e3_0 = r7     // Catch: java.lang.Throwable -> L51
            r0.label = r3     // Catch: java.lang.Throwable -> L51
            java.lang.Object r6 = r5.collectSafely(r7, r0)     // Catch: java.lang.Throwable -> L51
            if (r6 == r1) goto L50
            r6 = r7
        L4a:
            r6.releaseIntercepted()
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L50:
            return r1
        L51:
            r6 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
        L55:
            r6.releaseIntercepted()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.AbstractFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Object collectSafely(FlowCollector flowCollector, Continuation continuation) {
        Object invoke = this.block.invoke(flowCollector, continuation);
        if (invoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return invoke;
        }
        return Unit.INSTANCE;
    }

    public AbstractFlow(Function2 function2) {
        this();
        this.block = function2;
    }
}
