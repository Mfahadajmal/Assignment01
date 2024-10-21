package androidx.room;

import androidx.room.RoomConnectionManager;
import androidx.work.impl.constraints.controllers.BaseConstraintController;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    final /* synthetic */ Object TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0 = triggerBasedInvalidationTracker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 = new TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1((BaseConstraintController) this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0, continuation, 3);
                    triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1.L$0 = obj;
                    return triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1;
                }
                TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$12 = new TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1((TriggerBasedInvalidationTracker) this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0, continuation, 2, (char[]) null);
                triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$12.L$0 = obj;
                return triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$12;
            }
            TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$13 = new TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1((TriggerBasedInvalidationTracker) this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0, continuation, 1, (byte[]) null);
            triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$13.L$0 = obj;
            return triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$13;
        }
        TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1 triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$14 = new TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1((TriggerBasedInvalidationTracker) this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0, continuation, 0);
        triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$14.L$0 = obj;
        return triggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$14;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return ((TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1) create((ProducerCoroutine) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }
                return ((TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1) create((RoomConnectionManager.SupportPooledConnection) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1) create((RoomConnectionManager.SupportPooledConnection.SupportTransactor) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1) create((RoomConnectionManager.SupportPooledConnection) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b7, code lost:
    
        if (r3.withTransaction$ar$edu(r4, r5, r8) == r0) goto L41;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, Continuation continuation, int i, byte[] bArr) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0 = triggerBasedInvalidationTracker;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1(TriggerBasedInvalidationTracker triggerBasedInvalidationTracker, Continuation continuation, int i, char[] cArr) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0 = triggerBasedInvalidationTracker;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1(BaseConstraintController baseConstraintController, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1$ar$this$0 = baseConstraintController;
    }
}
