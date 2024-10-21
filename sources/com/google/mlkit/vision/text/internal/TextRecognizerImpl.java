package com.google.mlkit.vision.text.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.MLKitEnum$ClientType;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.OnDeviceDocumentEnhancementLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextRecognizerOptions;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.MobileVisionBase;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerImpl extends MobileVisionBase implements TextRecognizer {
    private final TextRecognizerOptionsInterface options;

    public TextRecognizerImpl(TextRecognizerTaskWithResource textRecognizerTaskWithResource, Executor executor, MLKitStatsLogger mLKitStatsLogger, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(textRecognizerTaskWithResource, executor);
        MLKitEnum$ClientType mLKitEnum$ClientType;
        this.options = textRecognizerOptionsInterface;
        MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent();
        if (textRecognizerOptionsInterface.getIsThickClient()) {
            mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THICK;
        } else {
            mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THIN;
        }
        mLKitSdkLogEvent.clientType = mLKitEnum$ClientType;
        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
        textRecognizerOptionsInterface.getLoggingLanguageOption$ar$ds();
        onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = OnDeviceTextRecognizerOptions.LanguageOption.LATIN;
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = new OnDeviceTextRecognizerOptions(onDeviceTextDetectionLoadLogEvent);
        mLKitSdkLogEvent.onDeviceTextDetectionLogEvent = new OnDeviceTextDetectionLogEvent(aggregatedOnDeviceTextDetectionLogEvent);
        mLKitStatsLogger.logEventWithEventName$ar$class_merging(new SchemaLogEvent(mLKitSdkLogEvent, 1), MLKitEnum$EventName.ON_DEVICE_TEXT_CREATE);
    }

    @Override // com.google.android.gms.common.api.OptionalModuleApi
    public final Feature[] getOptionalFeatures() {
        return OnDeviceDocumentEnhancementLogEvent.getOptionalFeatures(this.options);
    }

    @Override // com.google.mlkit.vision.text.TextRecognizer
    public final Task process(InputImage inputImage) {
        return super.processBase(inputImage);
    }
}
