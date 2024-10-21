package io.grpc;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Context {
    static final Logger log = Logger.getLogger(Context.class.getName());
    public static final Context ROOT = new Context();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LazyStorage {
        static final Storage storage;

        static {
            Storage threadLocalContextStorage;
            AtomicReference atomicReference = new AtomicReference();
            try {
                threadLocalContextStorage = (Storage) Class.forName("io.grpc.override.ContextStorageOverride").asSubclass(Storage.class).getConstructor(null).newInstance(null);
            } catch (ClassNotFoundException e) {
                atomicReference.set(e);
                threadLocalContextStorage = new ThreadLocalContextStorage();
            } catch (Exception e2) {
                throw new RuntimeException("Storage override failed to initialize", e2);
            }
            storage = threadLocalContextStorage;
            Throwable th = (Throwable) atomicReference.get();
            if (th != null) {
                Context.log.logp(Level.FINE, "io.grpc.Context$LazyStorage", "<clinit>", "Storage override doesn't exist. Using default", th);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class Storage {
        public abstract Context current();

        public abstract void detach(Context context, Context context2);

        public abstract Context doAttach(Context context);
    }

    private Context() {
    }

    static void checkNotNull$ar$ds$4e7b8cd1_2(Object obj, Object obj2) {
        if (obj != null) {
        } else {
            throw new NullPointerException((String) obj2);
        }
    }

    public static Context current() {
        Context current = LazyStorage.storage.current();
        if (current == null) {
            return ROOT;
        }
        return current;
    }

    public final void addListener$ar$class_merging$bf86fbbc_0$ar$ds(Executor executor) {
        checkNotNull$ar$ds$4e7b8cd1_2(executor, "executor");
    }

    public final Context attach() {
        Context doAttach = LazyStorage.storage.doAttach(this);
        if (doAttach == null) {
            return ROOT;
        }
        return doAttach;
    }

    public final void detach(Context context) {
        checkNotNull$ar$ds$4e7b8cd1_2(context, "toAttach");
        LazyStorage.storage.detach(this, context);
    }

    public final void getDeadline$ar$ds() {
    }

    public final void isCancelled$ar$ds() {
    }

    public final void removeListener$ar$class_merging$89eaf5ff_0$ar$ds() {
    }
}
