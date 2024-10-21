package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlinx.atomicfu.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CompletedExceptionally {
    public final AtomicBoolean _handled;
    public final Throwable cause;

    public CompletedExceptionally(Throwable th, boolean z) {
        this.cause = th;
        this._handled = OnDeviceSubjectSegmentationCreateLogEvent.atomic(z);
    }

    public final boolean makeHandled() {
        return this._handled.compareAndSet(false, true);
    }

    public final String toString() {
        return DebugStringsKt.getClassSimpleName(this) + "[" + this.cause + "]";
    }

    public /* synthetic */ CompletedExceptionally(Throwable th) {
        this(th, false);
    }
}
