package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BufferedChannel$receiveCatchingOnNoWaiterSuspend$1 extends ContinuationImpl {
    Object L$0;
    ConcurrentLinkedListNode L$1$ar$dn$2365ec9a_0$ar$class_merging;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BufferedChannel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BufferedChannel$receiveCatchingOnNoWaiterSuspend$1(BufferedChannel bufferedChannel, Continuation continuation) {
        super(continuation);
        this.this$0 = bufferedChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging = this.this$0.m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging(null, 0, 0L, this);
        if (m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging;
        }
        return new ChannelResult(m255receiveCatchingOnNoWaiterSuspendGKJJFZk$ar$class_merging);
    }
}
