package kotlinx.coroutines.flow;

import kotlin.coroutines.CombinedContext$toString$1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FlowKt__DistinctKt {
    private static final Function1 defaultKeySelector = new Function1() { // from class: kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return obj;
        }
    };
    private static final Function2 defaultAreEquivalent = CombinedContext$toString$1.INSTANCE$ar$class_merging$53896bf0_0;

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0016, code lost:
    
        if (r0.areEquivalent == r2) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlinx.coroutines.flow.Flow distinctUntilChanged(kotlinx.coroutines.flow.Flow r4) {
        /*
            boolean r0 = r4 instanceof kotlinx.coroutines.flow.StateFlow
            if (r0 == 0) goto L5
            goto L19
        L5:
            boolean r0 = r4 instanceof kotlinx.coroutines.flow.DistinctFlowImpl
            kotlin.jvm.functions.Function1 r1 = kotlinx.coroutines.flow.FlowKt__DistinctKt.defaultKeySelector
            kotlin.jvm.functions.Function2 r2 = kotlinx.coroutines.flow.FlowKt__DistinctKt.defaultAreEquivalent
            if (r0 == 0) goto L1a
            r0 = r4
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = (kotlinx.coroutines.flow.DistinctFlowImpl) r0
            kotlin.jvm.functions.Function1 r3 = r0.keySelector
            if (r3 != r1) goto L1a
            kotlin.jvm.functions.Function2 r0 = r0.areEquivalent
            if (r0 == r2) goto L19
            goto L1a
        L19:
            return r4
        L1a:
            kotlinx.coroutines.flow.DistinctFlowImpl r0 = new kotlinx.coroutines.flow.DistinctFlowImpl
            r0.<init>(r4, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.FlowKt__DistinctKt.distinctUntilChanged(kotlinx.coroutines.flow.Flow):kotlinx.coroutines.flow.Flow");
    }
}
