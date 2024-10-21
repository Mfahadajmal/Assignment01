package androidx.room;

import androidx.room.RoomConnectionManager;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class RoomConnectionManager$SupportPooledConnection$transaction$1 extends ContinuationImpl {
    RoomConnectionManager.SupportPooledConnection L$0$ar$dn;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RoomConnectionManager.SupportPooledConnection this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomConnectionManager$SupportPooledConnection$transaction$1(RoomConnectionManager.SupportPooledConnection supportPooledConnection, Continuation continuation) {
        super(continuation);
        this.this$0 = supportPooledConnection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.transaction$ar$edu(0, null, this);
    }
}
