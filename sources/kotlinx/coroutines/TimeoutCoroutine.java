package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeoutCoroutine extends ScopeCoroutine implements Runnable {
    public final long time;

    public TimeoutCoroutine(long j, Continuation continuation) {
        super(continuation.getContext(), continuation);
        this.time = j;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    public final String nameString$kotlinx_coroutines_core() {
        return super.nameString$kotlinx_coroutines_core() + "(timeMillis=" + this.time + ")";
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0064, code lost:
    
        if (r0 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005a, code lost:
    
        if (r3 > 4611686018427387903L) goto L13;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            r9 = this;
            kotlin.coroutines.CoroutineContext r0 = r9.context
            kotlinx.coroutines.Delay r0 = kotlinx.coroutines.DebugStringsKt.getDelay(r0)
            boolean r1 = r0 instanceof kotlinx.coroutines.DelayWithTimeoutDiagnostics
            if (r1 == 0) goto Ld
            kotlinx.coroutines.DelayWithTimeoutDiagnostics r0 = (kotlinx.coroutines.DelayWithTimeoutDiagnostics) r0
            goto Le
        Ld:
            r0 = 0
        Le:
            long r1 = r9.time
            if (r0 == 0) goto L66
            int r3 = kotlin.time.Duration.Duration$ar$NoOp
            kotlin.time.DurationUnit r3 = kotlin.time.DurationUnit.MILLISECONDS
            r3.getClass()
            r4 = 4611686018426999999(0x3ffffffffffa14bf, double:1.9999999999138678)
            kotlin.time.DurationUnit r6 = kotlin.time.DurationUnit.NANOSECONDS
            long r4 = com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent.convertDurationUnitOverflow(r4, r6, r3)
            long r6 = -r4
            kotlin.ranges.LongRange r8 = new kotlin.ranges.LongRange
            r8.<init>(r6, r4)
            boolean r4 = r8.contains(r1)
            if (r4 == 0) goto L3b
            kotlin.time.DurationUnit r4 = kotlin.time.DurationUnit.NANOSECONDS
            long r3 = com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent.convertDurationUnitOverflow(r1, r3, r4)
            long r3 = r3 + r3
            kotlin.time.Duration.m251constructorimpl$ar$ds(r3)
            goto L60
        L3b:
            kotlin.time.DurationUnit r4 = kotlin.time.DurationUnit.MILLISECONDS
            r4.getClass()
            java.util.concurrent.TimeUnit r3 = r3.timeUnit
            java.util.concurrent.TimeUnit r4 = r4.timeUnit
            long r3 = r4.convert(r1, r3)
            r5 = -4611686018427387903(0xc000000000000001, double:-2.0000000000000004)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L53
        L51:
            r3 = r5
            goto L5d
        L53:
            r5 = 4611686018427387903(0x3fffffffffffffff, double:1.9999999999999998)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L5d
            goto L51
        L5d:
            com.google.mlkit.logging.schema.OnDeviceStainRemovalLogEvent.durationOfMillis(r3)
        L60:
            java.lang.String r0 = r0.m252timeoutMessageLRDsOJo$ar$ds()
            if (r0 != 0) goto L79
        L66:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Timed out waiting for "
            r0.<init>(r3)
            r0.append(r1)
            java.lang.String r1 = " ms"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
        L79:
            kotlinx.coroutines.TimeoutCancellationException r1 = new kotlinx.coroutines.TimeoutCancellationException
            r1.<init>(r0, r9)
            r9.cancelImpl$kotlinx_coroutines_core(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.TimeoutCoroutine.run():void");
    }
}
