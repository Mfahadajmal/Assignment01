package com.google.mlkit.logging.schema;

import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MLKitSdkLogEvent {
    public AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent;
    public MLKitEnum$ClientType clientType;
    public MLKitEnum$EventName eventName;
    public InputImageConstructionLogEvent inputImageConstructionLogEvent;
    public OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent;
    public OnDeviceTextDetectionLogEvent onDeviceTextDetectionLogEvent;
    public SystemInfo systemInfo;

    public MLKitSdkLogEvent() {
    }

    public final String encodeAsJson() {
        JsonDataEncoderBuilder jsonDataEncoderBuilder = new JsonDataEncoderBuilder();
        AutoMLKitSdkLogEventEncoder.configure$ar$ds(jsonDataEncoderBuilder);
        jsonDataEncoderBuilder.ignoreNullValues = true;
        return new RetryingNameResolver.ResolutionResultListener(jsonDataEncoderBuilder).encode(this);
    }

    public MLKitSdkLogEvent(MLKitSdkLogEvent mLKitSdkLogEvent) {
        this.systemInfo = mLKitSdkLogEvent.systemInfo;
        this.eventName = mLKitSdkLogEvent.eventName;
        this.clientType = mLKitSdkLogEvent.clientType;
        this.onDeviceTextDetectionLogEvent = mLKitSdkLogEvent.onDeviceTextDetectionLogEvent;
        this.onDeviceTextDetectionLoadLogEvent = mLKitSdkLogEvent.onDeviceTextDetectionLoadLogEvent;
        this.aggregatedOnDeviceTextDetectionLogEvent = mLKitSdkLogEvent.aggregatedOnDeviceTextDetectionLogEvent;
        this.inputImageConstructionLogEvent = mLKitSdkLogEvent.inputImageConstructionLogEvent;
    }
}
