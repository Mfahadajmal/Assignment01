package kotlinx.coroutines.channels;

import kotlinx.coroutines.Waiter;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WaiterEB {
    public final Waiter waiter;

    public WaiterEB(Waiter waiter) {
        this.waiter = waiter;
    }

    public final String toString() {
        return "WaiterEB(" + this.waiter + ")";
    }
}
