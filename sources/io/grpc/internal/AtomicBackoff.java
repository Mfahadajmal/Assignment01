package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AtomicBackoff {
    public static final Logger log = Logger.getLogger(AtomicBackoff.class.getName());
    public final String name;
    public final AtomicLong value;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class State {
        public final long savedValue;

        public State(long j) {
            this.savedValue = j;
        }
    }

    public AtomicBackoff(long j) {
        boolean z;
        AtomicLong atomicLong = new AtomicLong();
        this.value = atomicLong;
        if (j > 0) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "value must be positive");
        this.name = "keepalive time nanos";
        atomicLong.set(j);
    }
}
