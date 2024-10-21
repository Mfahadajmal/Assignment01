package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface InternalCompletionHandler {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UserSupplied implements InternalCompletionHandler {
        private final Function1 handler;

        public UserSupplied(Function1 function1) {
            this.handler = function1;
        }

        @Override // kotlinx.coroutines.InternalCompletionHandler
        public final void invoke(Throwable th) {
            this.handler.invoke(th);
        }

        public final String toString() {
            return "InternalCompletionHandler.UserSupplied[" + DebugStringsKt.getClassSimpleName(this.handler) + "@" + DebugStringsKt.getHexAddress(this) + "]";
        }
    }

    void invoke(Throwable th);
}
