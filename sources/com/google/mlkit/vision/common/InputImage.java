package com.google.mlkit.vision.common;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.preference.Preference;
import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.mlkit.common.internal.model.CustomRemoteModelManager;
import com.google.mlkit.logging.schema.ImageInfo;
import com.google.mlkit.logging.schema.InputImageConstructionLogEvent;
import com.google.mlkit.logging.schema.MLKitEnum$EventName;
import com.google.mlkit.logging.schema.MLKitSdkLogEvent;
import com.google.mlkit.shared.logger.MLKitStatsLogger;
import com.google.mlkit.shared.logger.SchemaLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InputImage {
    public volatile Bitmap bitmap;
    public final int height;
    public final int width;

    private InputImage(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.width = bitmap.getWidth();
        this.height = bitmap.getHeight();
        StrictModeUtils$VmPolicyBuilderCompatS.checkArgument(true, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
    }

    public static InputImage fromBitmap$ar$ds(Bitmap bitmap) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(bitmap);
        final int height = bitmap.getHeight();
        final int width = bitmap.getWidth();
        final int allocationByteCount = bitmap.getAllocationByteCount();
        MLKitStatsLogger loggerInstance = CustomRemoteModelManager.getLoggerInstance("vision-common");
        final long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        loggerInstance.logThrottledEventWithEventName(new MLKitStatsLogger.LogEventProvider() { // from class: com.google.mlkit.shared.logger.utils.LoggingUtils$$ExternalSyntheticLambda0
            public final /* synthetic */ int f$0 = -1;
            public final /* synthetic */ int f$1 = 1;

            @Override // com.google.mlkit.shared.logger.MLKitStatsLogger.LogEventProvider
            public final SchemaLogEvent provideLogEvent$ar$class_merging() {
                InputImageConstructionLogEvent inputImageConstructionLogEvent = new InputImageConstructionLogEvent();
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageFormat = ImageInfo.ImageFormat.BITMAP;
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageSource = InputImageConstructionLogEvent.ImageSource.BITMAP;
                int i = allocationByteCount;
                Integer.valueOf(i).getClass();
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageByteSize = Integer.valueOf(i & Preference.DEFAULT_ORDER);
                int i2 = height;
                Integer.valueOf(i2).getClass();
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$imageHeight = Integer.valueOf(i2 & Preference.DEFAULT_ORDER);
                int i3 = width;
                Integer.valueOf(i3).getClass();
                inputImageConstructionLogEvent.imageWidth = Integer.valueOf(i3 & Preference.DEFAULT_ORDER);
                long j = elapsedRealtime2;
                Long.valueOf(j).getClass();
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$durationMs = Long.valueOf(j & Long.MAX_VALUE);
                Integer num = 0;
                num.getClass();
                inputImageConstructionLogEvent.InputImageConstructionLogEvent$ar$rotationDegrees = num;
                InputImageConstructionLogEvent inputImageConstructionLogEvent2 = new InputImageConstructionLogEvent(inputImageConstructionLogEvent);
                MLKitSdkLogEvent mLKitSdkLogEvent = new MLKitSdkLogEvent();
                mLKitSdkLogEvent.inputImageConstructionLogEvent = inputImageConstructionLogEvent2;
                return new SchemaLogEvent(mLKitSdkLogEvent, 0);
            }
        }, MLKitEnum$EventName.INPUT_IMAGE_CONSTRUCTION);
        return inputImage;
    }
}
