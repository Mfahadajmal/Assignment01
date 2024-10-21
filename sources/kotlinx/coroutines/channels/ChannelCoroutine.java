package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.channels.BufferedChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ChannelCoroutine extends AbstractCoroutine implements Channel {
    public final Channel _channel;

    public ChannelCoroutine(CoroutineContext coroutineContext, Channel channel) {
        super(coroutineContext, true);
        this._channel = channel;
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void cancel(CancellationException cancellationException) {
        if (isCancelled()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelInternal(cancellationException);
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void cancelInternal(Throwable th) {
        Channel channel = this._channel;
        CancellationException cancellationException = toCancellationException(th, null);
        channel.cancel(cancellationException);
        cancelImpl$kotlinx_coroutines_core(cancellationException);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean close(Throwable th) {
        return this._channel.close(th);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final void invokeOnClose(Function1 function1) {
        this._channel.invokeOnClose(function1);
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final BufferedChannel.BufferedChannelIterator iterator$ar$class_merging() {
        return this._channel.iterator$ar$class_merging();
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: receiveCatching-JP2dKIU */
    public final Object mo254receiveCatchingJP2dKIU(Continuation continuation) {
        throw null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        return this._channel.send(obj, continuation);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    /* renamed from: tryReceive-PtdJZtk */
    public final Object mo256tryReceivePtdJZtk() {
        throw null;
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public final Object mo257trySendJP2dKIU(Object obj) {
        return this._channel.mo257trySendJP2dKIU(obj);
    }
}
