package kotlinx.coroutines.channels;

import com.google.mlkit.logging.schema.OtherError;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProducerCoroutine extends ChannelCoroutine implements CoroutineScope, SendChannel {
    public ProducerCoroutine(CoroutineContext coroutineContext, Channel channel) {
        super(coroutineContext, channel);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected final void onCancelled(Throwable th, boolean z) {
        if (!this._channel.close(th) && !z) {
            OtherError.handleCoroutineException(this.context, th);
        }
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public final /* bridge */ /* synthetic */ void onCompleted(Object obj) {
        this._channel.close(null);
    }

    public final /* synthetic */ SendChannel getChannel() {
        return this;
    }
}
