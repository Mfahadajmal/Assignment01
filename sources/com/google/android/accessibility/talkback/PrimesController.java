package com.google.android.accessibility.talkback;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import j$.util.Objects;
import java.util.EnumMap;
import java.util.Map;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesController {
    public static final NoPiiString COMPONENT_NAME = new NoPiiString("TALKBACK");
    public SpannableUtils$NonCopyableTextSpan logger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Map timerMap = new EnumMap(TimerAction.class);
    private final Object lockTimerMap = new Object();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum TimerAction {
        START_UP(new NoPiiString("TalkBack_StartUp"), false),
        GESTURE_EVENT(new NoPiiString("TalkBack_GestureEvent"), false),
        KEY_EVENT(new NoPiiString("TalkBack_KeyEvent"), false),
        DPAD_NAVIGATION(new NoPiiString("TalkBack_DpadNavigation"), false),
        TTS_DELAY(new NoPiiString("TalkBack_TtsDelay"), true),
        INITIAL_FOCUS_RESTORE,
        INITIAL_FOCUS_FOLLOW_INPUT,
        INITIAL_FOCUS_FIRST_CONTENT,
        IMAGE_CAPTION_OCR_SUCCEED,
        IMAGE_CAPTION_OCR_FAILED,
        IMAGE_CAPTION_ICON_LABEL_SUCCEED,
        IMAGE_CAPTION_ICON_LABEL_FAILED,
        IMAGE_CAPTION_IMAGE_DESCRIPTION_SUCCEED,
        IMAGE_CAPTION_IMAGE_DESCRIPTION_FAILED,
        IMAGE_CAPTION_IMAGE_PROCESS_BLOCK_OVERLAY,
        GEMINI_RESPONSE_LATENCY,
        LATENCY_BETWEEN_SCREENSHOT_CAPTURE_REQUEST,
        EVENT_BASED_HEARING_FEEDBACK,
        EVENT_BASED_PERFORMING_ACTION,
        GESTURE_EVENT_LATENCY,
        TOUCH_CONTROLLER_STATE_CHANGE_LATENCY,
        GEMINI_ON_DEVICE_RESPONSE_LATENCY;

        public final NoPiiString noPiiString;
        final boolean overrideStartedTimer;

        TimerAction(NoPiiString noPiiString, boolean z) {
            this.noPiiString = noPiiString;
            this.overrideStartedTimer = z;
        }

        TimerAction() {
            this.noPiiString = NoPiiString.fromEnum(this);
            this.overrideStartedTimer = false;
        }
    }

    public final void recordDuration(TimerAction timerAction, long j, long j2) {
        SpannableUtils$NonCopyableTextSpan.recordDuration$ar$ds(timerAction.noPiiString, j, j2, null);
    }

    public final void startTimer(TimerAction timerAction) {
        startTimer(timerAction, null);
    }

    public final void stopTimer(TimerAction timerAction) {
        stopTimer(timerAction, null, null);
    }

    public final void recordDuration(TimerAction timerAction, long j, long j2, ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension) {
        SpannableUtils$NonCopyableTextSpan.recordDuration$ar$ds(timerAction.noPiiString, j, j2, extensionTalkback$TalkbackExtension);
    }

    public final void startTimer(TimerAction timerAction, String str) {
        synchronized (this.lockTimerMap) {
            if (timerAction.overrideStartedTimer || !this.timerMap.containsKey(timerAction)) {
                this.timerMap.put(timerAction, new WindowTrackerFactory(Primes.get().startTimer(), str));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void stopTimer(TimerAction timerAction, String str, ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension) {
        ExtensionMetric$MetricExtension extensionMetric$MetricExtension;
        synchronized (this.lockTimerMap) {
            WindowTrackerFactory windowTrackerFactory = (WindowTrackerFactory) this.timerMap.remove(timerAction);
            if (windowTrackerFactory != null) {
                if (Objects.equals(windowTrackerFactory.WindowTrackerFactory$ar$executorProvider, str)) {
                    Object obj = windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider;
                    NoPiiString noPiiString = timerAction.noPiiString;
                    if (extensionTalkback$TalkbackExtension != null) {
                        SchedulerOptions.Builder builder = (SchedulerOptions.Builder) ExtensionMetric$MetricExtension.DEFAULT_INSTANCE.createBuilder();
                        builder.setExtension$ar$ds(ExtensionTalkback$TalkbackExtension.metricExtension, extensionTalkback$TalkbackExtension);
                        extensionMetric$MetricExtension = (ExtensionMetric$MetricExtension) builder.build();
                    } else {
                        extensionMetric$MetricExtension = null;
                    }
                    Primes.get().primesApi.stopTimer$ar$edu$2eed496a_0((TimerEvent) obj, noPiiString, extensionMetric$MetricExtension, 1);
                } else {
                    this.timerMap.put(timerAction, windowTrackerFactory);
                }
            }
        }
    }
}
