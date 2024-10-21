package io.grpc.internal;

import com.google.mlkit.common.model.RemoteModelManager;
import com.google.mlkit.logging.schema.OnDeviceImageLabelLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TransportTracer {
    public static final RemoteModelManager DEFAULT_FACTORY$ar$class_merging$ar$class_merging = new RemoteModelManager(TimeProvider.SYSTEM_TIME_PROVIDER);
    public long keepAlivesSent;
    public volatile long lastMessageReceivedTimeNanos;
    public final LongCounter messagesReceived;
    public long messagesSent;
    public long streamsFailed;
    public long streamsStarted;
    public long streamsSucceeded;
    public final TimeProvider timeProvider;

    public TransportTracer() {
        this.messagesReceived = OnDeviceImageLabelLoadLogEvent.create();
        this.timeProvider = TimeProvider.SYSTEM_TIME_PROVIDER;
    }

    public TransportTracer(TimeProvider timeProvider) {
        this.messagesReceived = OnDeviceImageLabelLoadLogEvent.create();
        this.timeProvider = timeProvider;
    }
}
