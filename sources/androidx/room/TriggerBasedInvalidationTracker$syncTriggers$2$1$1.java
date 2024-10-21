package androidx.room;

import androidx.room.RoomConnectionManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$syncTriggers$2$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ RoomConnectionManager.SupportPooledConnection $connection$ar$class_merging;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ TriggerBasedInvalidationTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$syncTriggers$2$1$1(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, RoomConnectionManager.SupportPooledConnection supportPooledConnection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = triggerBasedInvalidationTracker;
        this.$connection$ar$class_merging = supportPooledConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TriggerBasedInvalidationTracker$syncTriggers$2$1$1(this.this$0, this.$connection$ar$class_merging, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return ((TriggerBasedInvalidationTracker$syncTriggers$2$1$1) create((RoomConnectionManager.SupportPooledConnection.SupportTransactor) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x00b2, code lost:
    
        if (((androidx.room.TriggerBasedInvalidationTracker) r6).stopTrackingTable(r5, r4, r12) != r0) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x00d1, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x00cc, code lost:
    
        if (((androidx.room.TriggerBasedInvalidationTracker) r6).startTrackingTable(r5, r4, r12) != r0) goto L42;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x008a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x00ce -> B:6:0x00cf). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker$syncTriggers$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
