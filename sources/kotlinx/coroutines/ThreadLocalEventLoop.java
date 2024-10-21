package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadLocalEventLoop {
    public static final ThreadLocalEventLoop INSTANCE = new ThreadLocalEventLoop();
    public static final ThreadLocal ref = new ThreadLocal();

    private ThreadLocalEventLoop() {
    }

    public static final EventLoop getEventLoop$kotlinx_coroutines_core$ar$ds() {
        ThreadLocal threadLocal = ref;
        EventLoop eventLoop = (EventLoop) threadLocal.get();
        if (eventLoop == null) {
            BlockingEventLoop blockingEventLoop = new BlockingEventLoop(Thread.currentThread());
            threadLocal.set(blockingEventLoop);
            return blockingEventLoop;
        }
        return eventLoop;
    }
}
