package io.grpc;

import io.grpc.Context;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ThreadLocalContextStorage extends Context.Storage {
    private static final Logger log = Logger.getLogger(ThreadLocalContextStorage.class.getName());
    static final ThreadLocal localContext = new ThreadLocal();

    @Override // io.grpc.Context.Storage
    public final Context current() {
        Context context = (Context) localContext.get();
        if (context == null) {
            return Context.ROOT;
        }
        return context;
    }

    @Override // io.grpc.Context.Storage
    public final void detach(Context context, Context context2) {
        if (current() != context) {
            log.logp(Level.SEVERE, "io.grpc.ThreadLocalContextStorage", "detach", "Context was not attached when detaching", new Throwable().fillInStackTrace());
        }
        if (context2 != Context.ROOT) {
            localContext.set(context2);
        } else {
            localContext.set(null);
        }
    }

    @Override // io.grpc.Context.Storage
    public final Context doAttach(Context context) {
        Context current = current();
        localContext.set(context);
        return current;
    }
}
