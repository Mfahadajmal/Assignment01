package io.grpc.stub;

import com.google.mlkit.logging.schema.OnDeviceFaceDetectionLogEvent;
import io.grpc.CallOptions;
import io.grpc.Channel;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractStub {
    public final CallOptions callOptions;
    public final Channel channel;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface StubFactory {
        AbstractStub newStub(Channel channel, CallOptions callOptions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractStub(Channel channel, CallOptions callOptions) {
        this.channel = channel;
        this.callOptions = callOptions;
    }

    public abstract AbstractStub build(Channel channel, CallOptions callOptions);

    public final AbstractStub withCallCredentials$ar$class_merging(OnDeviceFaceDetectionLogEvent onDeviceFaceDetectionLogEvent) {
        CallOptions.Builder builder = CallOptions.toBuilder(this.callOptions);
        builder.credentials$ar$class_merging = onDeviceFaceDetectionLogEvent;
        return build(this.channel, new CallOptions(builder));
    }
}
