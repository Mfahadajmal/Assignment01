package com.google.mlkit.logging.schema;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InferenceCommonLogEvent {
    public Boolean autoManageModelOnBackground;
    public Boolean autoManageModelOnLowMemory;
    public Long durationMs;
    public MLKitEnum$ErrorCode errorCode;
    public Boolean isColdCall;

    public InferenceCommonLogEvent() {
    }

    public InferenceCommonLogEvent(InferenceCommonLogEvent inferenceCommonLogEvent) {
        this.durationMs = inferenceCommonLogEvent.durationMs;
        this.errorCode = inferenceCommonLogEvent.errorCode;
        this.isColdCall = inferenceCommonLogEvent.isColdCall;
        this.autoManageModelOnBackground = inferenceCommonLogEvent.autoManageModelOnBackground;
        this.autoManageModelOnLowMemory = inferenceCommonLogEvent.autoManageModelOnLowMemory;
    }
}
