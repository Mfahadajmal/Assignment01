package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    private final Thread thread;

    public BlockingEventLoop(Thread thread) {
        this.thread = thread;
    }

    @Override // kotlinx.coroutines.EventLoop
    protected final Thread getThread() {
        return this.thread;
    }
}
