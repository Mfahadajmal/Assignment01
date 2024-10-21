package kotlinx.coroutines;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1 onCancellation;
    public final Object result;

    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Throwable th) {
        this.result = obj;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function1;
        this.idempotentResume = null;
        this.cancelCause = th;
    }

    public static /* synthetic */ CompletedContinuation copy$default$ar$ds(CompletedContinuation completedContinuation, CancelHandler cancelHandler, Throwable th, int i) {
        Object obj;
        Function1 function1 = null;
        if ((i & 1) != 0) {
            obj = completedContinuation.result;
        } else {
            obj = null;
        }
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.cancelHandler;
        }
        if ((i & 4) != 0) {
            function1 = completedContinuation.onCancellation;
        }
        if ((i & 8) != 0) {
            Object obj2 = completedContinuation.idempotentResume;
        }
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        return new CompletedContinuation(obj, cancelHandler, function1, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        if (!Intrinsics.areEqual(this.result, completedContinuation.result) || !Intrinsics.areEqual(this.cancelHandler, completedContinuation.cancelHandler) || !Intrinsics.areEqual(this.onCancellation, completedContinuation.onCancellation)) {
            return false;
        }
        Object obj2 = completedContinuation.idempotentResume;
        if (Intrinsics.areEqual(null, null) && Intrinsics.areEqual(this.cancelCause, completedContinuation.cancelCause)) {
            return true;
        }
        return false;
    }

    public final boolean getCancelled() {
        if (this.cancelCause != null) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        int hashCode2;
        int hashCode3;
        Object obj = this.result;
        int i = 0;
        if (obj == null) {
            hashCode = 0;
        } else {
            hashCode = obj.hashCode();
        }
        CancelHandler cancelHandler = this.cancelHandler;
        if (cancelHandler == null) {
            hashCode2 = 0;
        } else {
            hashCode2 = cancelHandler.hashCode();
        }
        int i2 = hashCode * 31;
        Function1 function1 = this.onCancellation;
        if (function1 == null) {
            hashCode3 = 0;
        } else {
            hashCode3 = function1.hashCode();
        }
        int i3 = (i2 + hashCode2) * 31;
        Throwable th = this.cancelCause;
        if (th != null) {
            i = th.hashCode();
        }
        return ((i3 + hashCode3) * 961) + i;
    }

    public final String toString() {
        return "CompletedContinuation(result=" + this.result + ", cancelHandler=" + this.cancelHandler + ", onCancellation=" + this.onCancellation + ", idempotentResume=null, cancelCause=" + this.cancelCause + ")";
    }

    public /* synthetic */ CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Throwable th, int i) {
        this(obj, (i & 2) != 0 ? null : cancelHandler, (i & 4) != 0 ? null : function1, (i & 16) != 0 ? null : th);
    }
}
