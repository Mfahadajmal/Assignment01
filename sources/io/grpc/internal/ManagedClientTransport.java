package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ManagedClientTransport extends ClientTransport {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Listener {
        Attributes filterTransport(Attributes attributes);

        void transportInUse(boolean z);

        void transportReady();

        void transportShutdown(Status status);

        void transportTerminated();
    }

    void shutdown(Status status);

    void shutdownNow(Status status);

    Runnable start(Listener listener);
}
