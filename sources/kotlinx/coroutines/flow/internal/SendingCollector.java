package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SendingCollector implements FlowCollector {
    private final SendChannel channel;

    public SendingCollector(SendChannel sendChannel) {
        this.channel = sendChannel;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Object send = this.channel.send(obj, continuation);
        if (send == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return send;
        }
        return Unit.INSTANCE;
    }
}
