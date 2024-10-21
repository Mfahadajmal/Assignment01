package androidx.core.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Pools$SynchronizedPool extends Pools$SimplePool {
    private final Object lock;

    public Pools$SynchronizedPool(int i) {
        super(i);
        this.lock = new Object();
    }

    @Override // androidx.core.util.Pools$SimplePool
    public final Object acquire() {
        Object acquire;
        synchronized (this.lock) {
            acquire = super.acquire();
        }
        return acquire;
    }

    @Override // androidx.core.util.Pools$SimplePool
    public final boolean release(Object obj) {
        boolean release;
        synchronized (this.lock) {
            release = super.release(obj);
        }
        return release;
    }
}
