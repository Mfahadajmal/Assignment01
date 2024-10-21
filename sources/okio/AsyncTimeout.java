package okio;

import com.google.mlkit.logging.schema.ToxicityDetectionInferenceEvent;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AsyncTimeout extends Timeout {
    private static final ToxicityDetectionInferenceEvent Companion$ar$class_merging$32e2604f_0$ar$class_merging = new ToxicityDetectionInferenceEvent();
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final ReentrantLock lock;
    private int state;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        lock = reentrantLock;
        reentrantLock.newCondition().getClass();
        long millis = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_MILLIS = millis;
        TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final boolean exit() {
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            this.state = 0;
            return false;
        } finally {
            reentrantLock.unlock();
        }
    }
}
