package androidx.core.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Pools$SimplePool {
    private final Object[] pool;
    private int poolSize;

    public Pools$SimplePool(int i) {
        this.pool = new Object[i];
    }

    public Object acquire() {
        int i = this.poolSize;
        if (i <= 0) {
            return null;
        }
        int i2 = i - 1;
        Object[] objArr = this.pool;
        Object obj = objArr[i2];
        obj.getClass();
        objArr[i2] = null;
        this.poolSize = i2;
        return obj;
    }

    public boolean release(Object obj) {
        obj.getClass();
        int i = this.poolSize;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.pool[i2] == obj) {
                throw new IllegalStateException("Already in the pool!");
            }
        }
        int i3 = this.poolSize;
        Object[] objArr = this.pool;
        if (i3 >= objArr.length) {
            return false;
        }
        objArr[i3] = obj;
        this.poolSize = i3 + 1;
        return true;
    }
}
