package com.google.mlkit.logging.schema;

import io.grpc.Attributes;
import io.grpc.LoadBalancer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceTextDetectionLogEvent {
    public Object OnDeviceTextDetectionLogEvent$ar$imageInfo;
    public Object OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent;
    public Object OnDeviceTextDetectionLogEvent$ar$recognizerOptions;

    public OnDeviceTextDetectionLogEvent(AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent) {
        this.OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
        this.OnDeviceTextDetectionLogEvent$ar$imageInfo = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;
        this.OnDeviceTextDetectionLogEvent$ar$recognizerOptions = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    public final LoadBalancer.ResolvedAddresses build() {
        return new LoadBalancer.ResolvedAddresses(this.OnDeviceTextDetectionLogEvent$ar$recognizerOptions, (Attributes) this.OnDeviceTextDetectionLogEvent$ar$imageInfo, this.OnDeviceTextDetectionLogEvent$ar$inferenceCommonLogEvent);
    }

    public OnDeviceTextDetectionLogEvent() {
        this.OnDeviceTextDetectionLogEvent$ar$imageInfo = Attributes.EMPTY;
    }
}
