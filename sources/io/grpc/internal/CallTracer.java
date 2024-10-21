package io.grpc.internal;

import com.google.mlkit.logging.schema.OnDeviceImageLabelLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CallTracer {
    private volatile long lastCallStartedNanos;
    private final TimeProvider timeProvider;
    private final LongCounter callsStarted = OnDeviceImageLabelLoadLogEvent.create();
    private final LongCounter callsSucceeded = OnDeviceImageLabelLoadLogEvent.create();
    private final LongCounter callsFailed = OnDeviceImageLabelLoadLogEvent.create();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Factory {
        CallTracer create();
    }

    public CallTracer(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public final void reportCallEnded(boolean z) {
        if (z) {
            this.callsSucceeded.add$ar$ds$3d014f3e_0();
        } else {
            this.callsFailed.add$ar$ds$3d014f3e_0();
        }
    }

    public final void reportCallStarted() {
        this.callsStarted.add$ar$ds$3d014f3e_0();
        this.lastCallStartedNanos = this.timeProvider.currentTimeNanos();
    }
}
