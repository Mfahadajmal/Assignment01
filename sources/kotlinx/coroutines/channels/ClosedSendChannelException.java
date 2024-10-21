package kotlinx.coroutines.channels;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClosedSendChannelException extends IllegalStateException {
    public ClosedSendChannelException() {
        super("Channel was closed");
    }
}
