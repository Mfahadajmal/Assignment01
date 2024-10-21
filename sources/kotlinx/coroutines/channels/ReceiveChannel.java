package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.BufferedChannel;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ReceiveChannel {
    void cancel(CancellationException cancellationException);

    BufferedChannel.BufferedChannelIterator iterator$ar$class_merging();

    /* renamed from: receiveCatching-JP2dKIU */
    Object mo254receiveCatchingJP2dKIU(Continuation continuation);

    /* renamed from: tryReceive-PtdJZtk */
    Object mo256tryReceivePtdJZtk();
}
