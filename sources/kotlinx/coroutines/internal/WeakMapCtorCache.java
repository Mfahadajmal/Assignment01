package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
final class WeakMapCtorCache extends CtorCache {
    public static final WeakMapCtorCache INSTANCE = new WeakMapCtorCache();
    private static final ReentrantReadWriteLock cacheLock = new ReentrantReadWriteLock();
    private static final WeakHashMap exceptionCtors = new WeakHashMap();

    private WeakMapCtorCache() {
    }

    @Override // kotlinx.coroutines.internal.CtorCache
    public final Function1 get(Class cls) {
        int i;
        ReentrantReadWriteLock reentrantReadWriteLock = cacheLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        try {
            Function1 function1 = (Function1) exceptionCtors.get(cls);
            if (function1 == null) {
                ReentrantReadWriteLock.ReadLock readLock2 = reentrantReadWriteLock.readLock();
                int i2 = 0;
                if (reentrantReadWriteLock.getWriteHoldCount() == 0) {
                    i = reentrantReadWriteLock.getReadHoldCount();
                } else {
                    i = 0;
                }
                for (int i3 = 0; i3 < i; i3++) {
                    readLock2.unlock();
                }
                ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
                writeLock.lock();
                try {
                    WeakHashMap weakHashMap = exceptionCtors;
                    Function1 function12 = (Function1) weakHashMap.get(cls);
                    if (function12 != null) {
                        return function12;
                    }
                    Function1 createConstructor = ExceptionsConstructorKt.createConstructor(cls);
                    weakHashMap.put(cls, createConstructor);
                    while (i2 < i) {
                        readLock2.lock();
                        i2++;
                    }
                    writeLock.unlock();
                    return createConstructor;
                } finally {
                    while (i2 < i) {
                        readLock2.lock();
                        i2++;
                    }
                    writeLock.unlock();
                }
            }
            return function1;
        } finally {
            readLock.unlock();
        }
    }
}
