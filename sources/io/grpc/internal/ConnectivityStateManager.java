package io.grpc.internal;

import io.grpc.ConnectivityState;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConnectivityStateManager {
    private ArrayList listeners = new ArrayList();
    private volatile ConnectivityState state = ConnectivityState.IDLE;

    public final void gotoState(ConnectivityState connectivityState) {
        connectivityState.getClass();
        if (this.state != connectivityState && this.state != ConnectivityState.SHUTDOWN) {
            this.state = connectivityState;
            if (!this.listeners.isEmpty()) {
                ArrayList arrayList = this.listeners;
                this.listeners = new ArrayList();
                if (arrayList.size() > 0) {
                    throw null;
                }
            }
        }
    }
}
