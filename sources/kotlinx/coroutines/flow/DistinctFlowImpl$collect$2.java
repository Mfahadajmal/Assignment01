package kotlinx.coroutines.flow;

import kotlin.jvm.internal.Ref$ObjectRef;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DistinctFlowImpl$collect$2 implements FlowCollector {
    final /* synthetic */ FlowCollector $collector;
    final /* synthetic */ Ref$ObjectRef $previousKey;
    final /* synthetic */ DistinctFlowImpl this$0;

    public DistinctFlowImpl$collect$2(DistinctFlowImpl distinctFlowImpl, Ref$ObjectRef ref$ObjectRef, FlowCollector flowCollector) {
        this.this$0 = distinctFlowImpl;
        this.$previousKey = ref$ObjectRef;
        this.$collector = flowCollector;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    @Override // kotlinx.coroutines.flow.FlowCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r0 = (kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1 r0 = new kotlinx.coroutines.flow.DistinctFlowImpl$collect$2$emit$1
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 != r3) goto L27
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            goto L61
        L27:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L2f:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r7)
            kotlinx.coroutines.flow.DistinctFlowImpl r7 = r5.this$0
            kotlin.jvm.functions.Function1 r7 = r7.keySelector
            java.lang.Object r7 = r7.invoke(r6)
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.$previousKey
            java.lang.Object r2 = r2.element
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            if (r2 == r4) goto L52
            kotlinx.coroutines.flow.DistinctFlowImpl r4 = r5.this$0
            kotlin.jvm.functions.Function2 r4 = r4.areEquivalent
            java.lang.Object r2 = r4.invoke(r2, r7)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L61
        L52:
            kotlin.jvm.internal.Ref$ObjectRef r2 = r5.$previousKey
            r2.element = r7
            kotlinx.coroutines.flow.FlowCollector r7 = r5.$collector
            r0.label = r3
            java.lang.Object r6 = r7.emit(r6, r0)
            if (r6 != r1) goto L61
            return r1
        L61:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.DistinctFlowImpl$collect$2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
