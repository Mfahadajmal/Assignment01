package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlinx.atomicfu.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CancelledContinuation extends CompletedExceptionally {
    public final AtomicBoolean _resumed;

    public CancelledContinuation(Continuation continuation, Throwable th, boolean z) {
        super(th == null ? new CancellationException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(continuation, "Continuation ", " was cancelled normally")) : th, z);
        this._resumed = OnDeviceSubjectSegmentationCreateLogEvent.atomic(false);
    }
}
