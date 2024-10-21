package com.google.mlkit.vision.text.internal;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.preference.Preference;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager$$ExternalSyntheticLambda4;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.mlkit.logging.schema.InferenceCommonLogEvent;
import com.google.mlkit.logging.schema.MLKitEnum$ClientType;
import com.google.mlkit.logging.schema.MLKitEnum$ErrorCode;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextRecognizerOptions;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextRecognizerTaskWithResource extends MLTask {
    static boolean isColdCall = true;
    private final MLKitStatsLogger mlKitStatsLogger;
    private final TextRecognizerImplFactory mlKitTelemetryLogger$ar$class_merging;
    private final TextRecognitionDelegate textRecognitionDelegate;
    public final TextRecognizerOptionsInterface textRecognizerOptions;
    private static final ImageUtils imageUtils = ImageUtils.INSTANCE;
    private static final TaskQueue taskQueue = new TaskQueue();

    public TextRecognizerTaskWithResource(MLKitStatsLogger mLKitStatsLogger, TextRecognitionDelegate textRecognitionDelegate, TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(taskQueue);
        this.mlKitStatsLogger = mLKitStatsLogger;
        this.textRecognitionDelegate = textRecognitionDelegate;
        this.mlKitTelemetryLogger$ar$class_merging = new TextRecognizerImplFactory(MlKitContext.getInstance().getApplicationContext());
        this.textRecognizerOptions = textRecognizerOptionsInterface;
    }

    private final void logSchemaDetectionEventsWithThrottle(final MLKitEnum$ErrorCode mLKitEnum$ErrorCode, long j, final InputImage inputImage) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.mlKitStatsLogger.logThrottledEventWithEventName(new MLKitStatsLogger.LogEventProvider() { // from class: com.google.mlkit.vision.text.internal.TextRecognizerTaskWithResource$$ExternalSyntheticLambda0
            @Override // com.google.mlkit.shared.logger.MLKitStatsLogger.LogEventProvider
            public final SchemaLogEvent provideLogEvent$ar$class_merging() {
                MLKitEnum$ClientType mLKitEnum$ClientType;
                AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
                InferenceCommonLogEvent inferenceCommonLogEvent = new InferenceCommonLogEvent();
                long j2 = elapsedRealtime;
                Long.valueOf(j2).getClass();
                inferenceCommonLogEvent.durationMs = Long.valueOf(j2 & Long.MAX_VALUE);
                inferenceCommonLogEvent.errorCode = mLKitEnum$ErrorCode;
                inferenceCommonLogEvent.isColdCall = Boolean.valueOf(TextRecognizerTaskWithResource.isColdCall);
                inferenceCommonLogEvent.autoManageModelOnBackground = true;
                inferenceCommonLogEvent.autoManageModelOnLowMemory = true;
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = new InferenceCommonLogEvent(inferenceCommonLogEvent);
                Bitmap bitmap = inputImage.bitmap;
                StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(bitmap);
                int allocationByteCount = bitmap.getAllocationByteCount();
                ImageInfo.Builder builder = new ImageInfo.Builder();
                builder.ImageInfo$Builder$ar$imageFormat = ImageInfo.ImageFormat.BITMAP;
                Integer.valueOf(allocationByteCount).getClass();
                builder.ImageInfo$Builder$ar$originalImageSize = Integer.valueOf(allocationByteCount & Preference.DEFAULT_ORDER);
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = new ImageInfo(builder);
                OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
                TextRecognizerTaskWithResource textRecognizerTaskWithResource = TextRecognizerTaskWithResource.this;
                textRecognizerTaskWithResource.textRecognizerOptions.getLoggingLanguageOption$ar$ds();
                onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = OnDeviceTextRecognizerOptions.LanguageOption.LATIN;
                aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = new OnDeviceTextRecognizerOptions(onDeviceTextDetectionLoadLogEvent);
                OnDeviceTextDetectionLogEvent onDeviceTextDetectionLogEvent = new OnDeviceTextDetectionLogEvent(aggregatedOnDeviceTextDetectionLogEvent);
                MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent();
                if (textRecognizerTaskWithResource.textRecognizerOptions.getIsThickClient()) {
                    mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THICK;
                } else {
                    mLKitEnum$ClientType = MLKitEnum$ClientType.TYPE_THIN;
                }
                mLKitSdkLogEvent.clientType = mLKitEnum$ClientType;
                mLKitSdkLogEvent.onDeviceTextDetectionLogEvent = onDeviceTextDetectionLogEvent;
                return new SchemaLogEvent(mLKitSdkLogEvent, 0);
            }
        }, MLKitEnum$EventName.ON_DEVICE_TEXT_DETECT);
        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent();
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = mLKitEnum$ErrorCode;
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = Boolean.valueOf(isColdCall);
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
        this.textRecognizerOptions.getLoggingLanguageOption$ar$ds();
        onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = OnDeviceTextRecognizerOptions.LanguageOption.LATIN;
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = new OnDeviceTextRecognizerOptions(onDeviceTextDetectionLoadLogEvent);
        AggregatedOnDeviceTextDetectionLogEvent.LogEventKey logEventKey = new AggregatedOnDeviceTextDetectionLogEvent.LogEventKey(aggregatedOnDeviceTextDetectionLogEvent);
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this, null);
        MLTaskExecutor.WorkerThreadExecutor.INSTANCE.execute(new FakeSplitInstallManager$$ExternalSyntheticLambda4(this.mlKitStatsLogger, MLKitEnum$EventName.AGGREGATED_ON_DEVICE_TEXT_DETECTION, logEventKey, elapsedRealtime, resolutionResultListener, 2));
        long currentTimeMillis = System.currentTimeMillis();
        this.mlKitTelemetryLogger$ar$class_merging.logThrottledEvent(this.textRecognizerOptions.getLoggingEventId(), mLKitEnum$ErrorCode.value, currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void load() {
        this.textRecognitionDelegate.load();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    public final synchronized void release() {
        isColdCall = true;
        this.textRecognitionDelegate.release();
    }

    public final synchronized MultiFlavorDetectorCreator run$ar$class_merging(InputImage inputImage) {
        MLKitEnum$ErrorCode mLKitEnum$ErrorCode;
        MultiFlavorDetectorCreator run$ar$class_merging;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            run$ar$class_merging = this.textRecognitionDelegate.run$ar$class_merging(inputImage);
            logSchemaDetectionEventsWithThrottle(MLKitEnum$ErrorCode.NO_ERROR, elapsedRealtime, inputImage);
            isColdCall = false;
        } catch (MlKitException e) {
            if (e.code == 14) {
                mLKitEnum$ErrorCode = MLKitEnum$ErrorCode.MODEL_NOT_DOWNLOADED;
            } else {
                mLKitEnum$ErrorCode = MLKitEnum$ErrorCode.UNKNOWN_ERROR;
            }
            logSchemaDetectionEventsWithThrottle(mLKitEnum$ErrorCode, elapsedRealtime, inputImage);
            throw e;
        }
        return run$ar$class_merging;
    }
}
