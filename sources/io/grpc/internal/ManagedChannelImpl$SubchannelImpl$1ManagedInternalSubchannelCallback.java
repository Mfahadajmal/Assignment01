package io.grpc.internal;

import com.google.mlkit.logging.schema.OnDeviceImageLabelDetectionLogEvent;
import io.grpc.InternalChannelz;
import io.grpc.LoadBalancer;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback extends OnDeviceImageLabelDetectionLogEvent {
    final /* synthetic */ AbstractSubchannel this$1$ar$class_merging;
    final /* synthetic */ LoadBalancer.SubchannelStateListener val$listener;

    public ManagedChannelImpl$SubchannelImpl$1ManagedInternalSubchannelCallback(AbstractSubchannel abstractSubchannel, LoadBalancer.SubchannelStateListener subchannelStateListener) {
        this.val$listener = subchannelStateListener;
        this.this$1$ar$class_merging = abstractSubchannel;
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceImageLabelDetectionLogEvent
    public final void onTerminated(InternalSubchannel internalSubchannel) {
        this.this$1$ar$class_merging.this$0.subchannels.remove(internalSubchannel);
        InternalChannelz.remove(this.this$1$ar$class_merging.this$0.channelz.subchannels, internalSubchannel);
        this.this$1$ar$class_merging.this$0.maybeTerminateChannel();
    }
}
