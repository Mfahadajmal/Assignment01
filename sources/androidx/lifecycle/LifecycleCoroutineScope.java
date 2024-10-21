package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LifecycleCoroutineScope implements CoroutineScope, LifecycleEventObserver {
    private final CoroutineContext coroutineContext;
    public final Lifecycle lifecycle;

    public LifecycleCoroutineScope() {
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (this.lifecycle.getCurrentState().compareTo(Lifecycle.State.DESTROYED) <= 0) {
            this.lifecycle.removeObserver(this);
            ScannerAutoZoomEvent.PredictedArea.cancel$default$ar$ds$e5a45333_0(this.coroutineContext);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LifecycleCoroutineScope(Lifecycle lifecycle, CoroutineContext coroutineContext) {
        this();
        coroutineContext.getClass();
        this.lifecycle = lifecycle;
        this.coroutineContext = coroutineContext;
        if (lifecycle.getCurrentState() == Lifecycle.State.DESTROYED) {
            ScannerAutoZoomEvent.PredictedArea.cancel$default$ar$ds$e5a45333_0(coroutineContext);
        }
    }
}
