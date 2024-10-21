package com.google.android.accessibility.talkback.imagecaption;

import android.os.Handler;
import android.os.Looper;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.search.mdi.aratea.proto.FeatureName;
import j$.time.Duration;
import j$.time.Instant;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Request {
    public Instant endTimestamp;
    private final Handler handler;
    private final HapticPatternParser$$ExternalSyntheticLambda1 onPendingListener$ar$class_merging$ar$class_merging;
    private Instant startTimestamp;
    private final Duration timeout;
    private final Runnable timeoutRunnable;

    Request() {
        this(null, null);
    }

    public static String errorName(int i) {
        if (i != 200) {
            if (i != 300) {
                switch (i) {
                    case FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu /* 101 */:
                        return "ERROR_TIMEOUT";
                    case 102:
                        return "ERROR_NETWORK_ERROR";
                    case ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu /* 103 */:
                        return "ERROR_INSUFFICIENT_STORAGE";
                    default:
                        switch (i) {
                            case 400:
                                return "ERROR_IMAGE_DESCRIPTION_NO_RESULT";
                            case FeatureName.STYLUS_IMAGE_CAPTION$ar$edu /* 401 */:
                                return "ERROR_IMAGE_DESCRIPTION_FAILURE";
                            case FeatureName.STYLUS_SKETCH_TO_IMAGE$ar$edu /* 402 */:
                                return "ERROR_IMAGE_DESCRIPTION_INITIALIZATION_FAILURE";
                            default:
                                return "";
                        }
                }
            }
            return "ERROR_ICON_DETECTION_NO_RESULT";
        }
        return "ERROR_TEXT_RECOGNITION_NO_RESULT";
    }

    public final long getDurationMillis() {
        Instant instant;
        Instant instant2 = this.startTimestamp;
        if (instant2 != null && (instant = this.endTimestamp) != null) {
            return Duration.between(instant2, instant).toMillis();
        }
        return -1L;
    }

    public abstract void onError(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onPending(boolean z, Duration duration) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.onPendingListener$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 == null) {
            return;
        }
        hapticPatternParser$$ExternalSyntheticLambda1.onPending(z, duration);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void perform();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void runTimeoutRunnable() {
        Duration duration = this.timeout;
        if (duration == null) {
            LogUtils.w("ImageCaptionRequest", "runTimeoutRunnable() with invalid timeout.", new Object[0]);
        } else {
            this.handler.postDelayed(this.timeoutRunnable, duration.toMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setEndTimestamp() {
        this.endTimestamp = Instant.now();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setStartTimestamp() {
        this.startTimestamp = Instant.now();
    }

    public final void stopTimeoutRunnable() {
        this.handler.removeCallbacks(this.timeoutRunnable);
    }

    public Request(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, Duration duration) {
        this.onPendingListener$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.timeout = duration;
        this.handler = new Handler(Looper.myLooper());
        this.timeoutRunnable = new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 18);
    }
}
