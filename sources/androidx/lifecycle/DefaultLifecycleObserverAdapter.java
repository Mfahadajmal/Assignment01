package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements LifecycleEventObserver {
    private final DefaultLifecycleObserver defaultLifecycleObserver;
    private final LifecycleEventObserver lifecycleEventObserver;

    public DefaultLifecycleObserverAdapter(DefaultLifecycleObserver defaultLifecycleObserver, LifecycleEventObserver lifecycleEventObserver) {
        this.defaultLifecycleObserver = defaultLifecycleObserver;
        this.lifecycleEventObserver = lifecycleEventObserver;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        int ordinal = event.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                if (ordinal != 4) {
                    if (ordinal != 5) {
                        if (ordinal == 6) {
                            throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                        }
                    } else {
                        this.defaultLifecycleObserver.onDestroy$ar$ds();
                    }
                } else {
                    this.defaultLifecycleObserver.onStop$ar$ds();
                }
            } else {
                this.defaultLifecycleObserver.onResume(lifecycleOwner);
            }
        } else {
            this.defaultLifecycleObserver.onStart$ar$ds$f453d6c4_0();
        }
        LifecycleEventObserver lifecycleEventObserver = this.lifecycleEventObserver;
        if (lifecycleEventObserver != null) {
            lifecycleEventObserver.onStateChanged(lifecycleOwner, event);
        }
    }
}
