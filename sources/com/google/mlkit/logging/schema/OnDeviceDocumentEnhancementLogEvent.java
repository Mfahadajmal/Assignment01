package com.google.mlkit.logging.schema;

import com.google.android.gms.common.Feature;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.logging.schema.MLKitEnum$ClientType;
import com.google.mlkit.logging.schema.MLKitEnum$ErrorCode;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceDocumentEnhancementLogEvent {
    public static Feature[] getOptionalFeatures(TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        if (textRecognizerOptionsInterface.getIsThickClient()) {
            return OptionalModuleUtils.EMPTY_FEATURES;
        }
        textRecognizerOptionsInterface.getLoggingLanguageOption$ar$ds();
        return new Feature[]{OptionalModuleUtils.FEATURE_OCR};
    }

    public static void logLoadEvent(MLKitStatsLogger mLKitStatsLogger, final boolean z, final MLKitEnum$ErrorCode mLKitEnum$ErrorCode) {
        mLKitStatsLogger.logThrottledEventWithEventName(new MLKitStatsLogger.LogEventProvider() { // from class: com.google.mlkit.vision.text.internal.LoggingUtils$$ExternalSyntheticLambda0
            @Override // com.google.mlkit.shared.logger.MLKitStatsLogger.LogEventProvider
            public final SchemaLogEvent provideLogEvent$ar$class_merging() {
                MLKitEnum$ClientType mLKitEnum$ClientType;
                MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent();
                if (z) {
                    mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THICK;
                } else {
                    mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THIN;
                }
                MLKitEnum$ErrorCode mLKitEnum$ErrorCode2 = mLKitEnum$ErrorCode;
                mLKitSdkLogEvent.clientType = mLKitEnum$ClientType;
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
                onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = mLKitEnum$ErrorCode2;
                mLKitSdkLogEvent.onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent(onDeviceTextDetectionLoadLogEvent);
                return new SchemaLogEvent(mLKitSdkLogEvent, 0);
            }
        }, MLKitEnum$EventName.ON_DEVICE_TEXT_LOAD);
    }
}
