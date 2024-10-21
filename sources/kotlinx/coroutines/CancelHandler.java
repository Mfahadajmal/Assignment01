package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CancelHandler extends NotCompleted {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UserSupplied implements CancelHandler {
        private final Object CancelHandler$UserSupplied$ar$handler;
        private final /* synthetic */ int switching_field;

        public UserSupplied(Object obj, int i) {
            this.switching_field = i;
            this.CancelHandler$UserSupplied$ar$handler = obj;
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, kotlin.jvm.functions.Function1] */
        /* JADX WARN: Type inference failed for: r3v1, types: [java.util.concurrent.Future, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v2, types: [kotlinx.coroutines.DisposableHandle, java.lang.Object] */
        @Override // kotlinx.coroutines.CancelHandler
        public final void invoke(Throwable th) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    this.CancelHandler$UserSupplied$ar$handler.dispose();
                    return;
                } else {
                    if (th != null) {
                        this.CancelHandler$UserSupplied$ar$handler.cancel(false);
                        return;
                    }
                    return;
                }
            }
            this.CancelHandler$UserSupplied$ar$handler.invoke(th);
        }

        public final String toString() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    return "DisposeOnCancel[" + this.CancelHandler$UserSupplied$ar$handler + "]";
                }
                return "CancelFutureOnCancel[" + this.CancelHandler$UserSupplied$ar$handler + "]";
            }
            return "CancelHandler.UserSupplied[" + DebugStringsKt.getClassSimpleName(this.CancelHandler$UserSupplied$ar$handler) + "@" + DebugStringsKt.getHexAddress(this) + "]";
        }
    }

    void invoke(Throwable th);
}
