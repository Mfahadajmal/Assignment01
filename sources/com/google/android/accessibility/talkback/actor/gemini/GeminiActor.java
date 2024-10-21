package com.google.android.accessibility.talkback.actor.gemini;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.SystemClock;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda1;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.actor.gemini.progress.ProgressTonePlayer;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GeminiActor {
    private static final long KEEP_WAITING_TIME_MS = TimeUnit.SECONDS.toMillis(15);
    public final AiCoreEndpoint aiCoreEndpoint;
    private final AiCoreEndpoint.AiFeatureDownloadCallback aiFeatureDownloadCallback;
    private final TalkBackAnalytics analytics;
    public final Context context;
    private final GeminiEndpoint geminiEndpoint;
    public GeminiResultDialog geminiResultDialog;
    private final Handler mainHandler;
    public Pipeline.FeedbackReturner pipeline;
    private final PrimesController primesController;
    public final ProgressTonePlayer progressTonePlayer;
    private long startTime;
    public final FloatingActionButton.ShadowDelegateImpl state$ar$class_merging$afb67bed_0$ar$class_merging$ar$class_merging;
    public long downloadedSizeInBytes = -1;
    public long featureSizeInBytes = -1;
    private final Map requestIdMap = new HashMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface GeminiEndpoint {
        void cancelCommand();

        boolean createRequestGeminiCommand(String str, Bitmap bitmap, boolean z, GeminiResponseListener geminiResponseListener);

        boolean hasPendingTransaction();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface GeminiResponseListener {
        void onError$ar$edu(int i);

        void onResponse$ar$edu(int i, String str);
    }

    public GeminiActor(Context context, TalkBackAnalytics talkBackAnalytics, PrimesController primesController, GeminiEndpoint geminiEndpoint, AiCoreEndpoint aiCoreEndpoint) {
        AiCoreEndpoint.AiFeatureDownloadCallback aiFeatureDownloadCallback = new AiCoreEndpoint.AiFeatureDownloadCallback() { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiActor.1
            @Override // com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint.AiFeatureDownloadCallback
            public final void onDownloadCompleted() {
                LogUtils.d("GeminiActor", "GeminiActor - Feature download completed.", new Object[0]);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint.AiFeatureDownloadCallback
            public final void onDownloadProgress(long j, long j2) {
                GeminiActor geminiActor = GeminiActor.this;
                geminiActor.downloadedSizeInBytes = j;
                geminiActor.featureSizeInBytes = j2;
            }
        };
        this.aiFeatureDownloadCallback = aiFeatureDownloadCallback;
        this.context = context;
        this.analytics = talkBackAnalytics;
        this.primesController = primesController;
        this.geminiEndpoint = geminiEndpoint;
        this.mainHandler = new Handler(context.getMainLooper());
        this.progressTonePlayer = new ProgressTonePlayer(new SpannableUtils$NonCopyableTextSpan(), new Interpreters$$ExternalSyntheticLambda1(this, 2), KEEP_WAITING_TIME_MS, new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 8));
        this.aiCoreEndpoint = aiCoreEndpoint;
        aiCoreEndpoint.downloadCallback = aiFeatureDownloadCallback;
        this.state$ar$class_merging$afb67bed_0$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
    }

    private final boolean isServerSideRequest(int i) {
        Map map = this.requestIdMap;
        Integer valueOf = Integer.valueOf(i);
        if (map.containsKey(valueOf) && Objects.equals(this.requestIdMap.get(valueOf), Boolean.TRUE)) {
            return true;
        }
        return false;
    }

    private final void responseImageCaptionResult$ar$ds(int i, int i2, boolean z) {
        responseImageCaptionResult$ar$edu$3ac4f1f3_0(i, this.context.getString(i2), false, 5, Boolean.valueOf(z));
    }

    private final void responseImageCaptionResult$ar$ds$adcd7c03_0(int i, String str, boolean z) {
        responseImageCaptionResult$ar$edu$3ac4f1f3_0(i, str, false, 5, Boolean.valueOf(z));
    }

    private final void responseImageCaptionResult$ar$edu$3ac4f1f3_0(int i, String str, boolean z, int i2, Boolean bool) {
        String str2;
        if (bool.booleanValue() && !z) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(str));
        }
        Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
        Logger logger2 = Performance.DEFAULT_LOGGER;
        boolean booleanValue = bool.booleanValue();
        Feedback.Part.Builder builder = Feedback.Part.builder();
        Feedback.ImageCaptionResult.Builder builder2 = new Feedback.ImageCaptionResult.Builder(null);
        builder2.requestId = i;
        byte b = builder2.set$0;
        builder2.set$0 = (byte) (b | 1);
        if (str != null) {
            builder2.text = str;
            builder2.isSuccess = z;
            builder2.userRequested = booleanValue;
            int i3 = b | 7;
            builder2.set$0 = (byte) i3;
            builder2.finishReason$ar$edu = i2;
            if (i3 == 7 && (str2 = builder2.text) != null) {
                builder.imageCaptionResult = new Feedback.ImageCaptionResult(i, str2, z, booleanValue, i2);
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, builder);
                return;
            }
            StringBuilder sb = new StringBuilder();
            if ((builder2.set$0 & 1) == 0) {
                sb.append(" requestId");
            }
            if (builder2.text == null) {
                sb.append(" text");
            }
            if ((builder2.set$0 & 2) == 0) {
                sb.append(" isSuccess");
            }
            if ((builder2.set$0 & 4) == 0) {
                sb.append(" userRequested");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        throw new NullPointerException("Null text");
    }

    private final void responseImageCaptionResult$ar$edu$ar$ds(int i, int i2, int i3, boolean z) {
        responseImageCaptionResult$ar$edu$3ac4f1f3_0(i, this.context.getString(i2), false, i3, Boolean.valueOf(z));
    }

    public final void handleErrorResponse$ar$edu(int i, int i2, boolean z) {
        switch (i2 - 1) {
            case 0:
                responseImageCaptionResult$ar$ds(i, R.string.gemini_error_message, z);
                this.analytics.onGeminiFailEvent(11, isServerSideRequest(i));
                break;
            case 1:
                responseImageCaptionResult$ar$ds(i, R.string.summary_pref_gemini_support_disabled, z);
                this.analytics.onGeminiFailEvent(2, isServerSideRequest(i));
                break;
            case 2:
                responseImageCaptionResult$ar$ds(i, R.string.gemini_network_error, z);
                this.analytics.onGeminiFailEvent(3, isServerSideRequest(i));
                break;
            case 3:
                responseImageCaptionResult$ar$ds(i, R.string.gemini_screenshot_unavailable, z);
                this.analytics.onGeminiFailEvent(4, isServerSideRequest(i));
                break;
            case 4:
                Context context = this.context;
                responseImageCaptionResult$ar$ds$adcd7c03_0(i, context.getString(R.string.voice_commands_partial_result, context.getString(R.string.title_pref_help)), z);
                this.analytics.onGeminiFailEvent(5, isServerSideRequest(i));
                break;
            case 5:
                responseImageCaptionResult$ar$ds(i, R.string.gemini_screenshot_unavailable, z);
                this.analytics.onGeminiFailEvent(6, isServerSideRequest(i));
                break;
            case 6:
                long j = this.featureSizeInBytes;
                if (j > 0) {
                    long j2 = this.downloadedSizeInBytes;
                    if (j2 >= 0 && j >= j2) {
                        responseImageCaptionResult$ar$ds$adcd7c03_0(i, this.context.getString(R.string.message_aifeature_downloading_with_progress, Long.valueOf(j2 >> 20), Long.valueOf(j >> 20)), z);
                        this.analytics.onGeminiFailEvent(11, isServerSideRequest(i));
                        break;
                    }
                }
                LogUtils.w("GeminiActor", "Can't get the download progress.", new Object[0]);
                responseImageCaptionResult$ar$ds(i, R.string.message_aifeature_downloading, z);
                this.analytics.onGeminiFailEvent(11, isServerSideRequest(i));
                break;
            default:
                this.analytics.onGeminiFailEvent(10, isServerSideRequest(i));
                break;
        }
        this.progressTonePlayer.stop();
    }

    public final void handleResponse$ar$edu(int i, int i2, String str, boolean z) {
        int i3 = i2 - 1;
        if (i3 != 0) {
            if (i3 != 1) {
                if (i3 != 2) {
                    responseImageCaptionResult$ar$edu$ar$ds(i, R.string.gemini_block_message, 4, z);
                    this.analytics.onGeminiFailEvent(8, isServerSideRequest(i));
                } else {
                    responseImageCaptionResult$ar$edu$ar$ds(i, R.string.gemini_error_message, 3, z);
                    this.analytics.onGeminiFailEvent(9, isServerSideRequest(i));
                }
            } else {
                responseImageCaptionResult$ar$edu$ar$ds(i, R.string.gemini_error_parsing_result, 2, z);
                this.analytics.onGeminiFailEvent(7, isServerSideRequest(i));
            }
        } else {
            if (z) {
                this.mainHandler.post(new DelayedWorkTracker.AnonymousClass1(this, str, 18, (byte[]) null));
            }
            responseImageCaptionResult$ar$edu$3ac4f1f3_0(i, str, true, 1, Boolean.valueOf(z));
            this.analytics.onGeminiEvent(2, isServerSideRequest(i), z);
            Map map = this.requestIdMap;
            PrimesController.TimerAction timerAction = PrimesController.TimerAction.GEMINI_RESPONSE_LATENCY;
            Integer valueOf = Integer.valueOf(i);
            if (map.containsKey(valueOf) && Objects.equals(this.requestIdMap.get(valueOf), Boolean.FALSE)) {
                timerAction = PrimesController.TimerAction.GEMINI_ON_DEVICE_RESPONSE_LATENCY;
            }
            this.primesController.recordDuration(timerAction, this.startTime, SystemClock.uptimeMillis());
        }
        Map map2 = this.requestIdMap;
        Integer valueOf2 = Integer.valueOf(i);
        if (map2.containsKey(valueOf2)) {
            this.requestIdMap.remove(valueOf2);
        }
        this.progressTonePlayer.stop();
    }

    public final void onTimeout() {
        this.geminiEndpoint.cancelCommand();
        this.aiCoreEndpoint.cancelExistingRequestIfNeeded();
    }

    public final void requestAiCoreImageCaptioning(final int i, Bitmap bitmap, final boolean z) {
        if (z || (!this.aiCoreEndpoint.hasPendingTransaction() && !this.geminiEndpoint.hasPendingTransaction())) {
            if (this.requestIdMap.size() > 100) {
                LogUtils.w("GeminiActor", "The requestIdMap reaches its max capacity.", new Object[0]);
                this.requestIdMap.clear();
            }
            Map map = this.requestIdMap;
            Integer valueOf = Integer.valueOf(i);
            map.put(valueOf, false);
            this.analytics.onGeminiEvent(1, false, z);
            if (!this.aiCoreEndpoint.hasAiCore()) {
                handleErrorResponse$ar$edu(i, 1, z);
                return;
            }
            this.startTime = SystemClock.uptimeMillis();
            if (this.aiCoreEndpoint.createRequestGeminiCommand(this.context.getString(R.string.image_caption_with_gemini_prefix), bitmap, z, new GeminiResponseListener(this) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiActor.3
                final /* synthetic */ GeminiActor this$0;

                {
                    this.this$0 = this;
                }

                @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiResponseListener
                public final void onError$ar$edu(int i2) {
                    this.this$0.handleErrorResponse$ar$edu(i, i2, z);
                }

                @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiResponseListener
                public final void onResponse$ar$edu(int i2, String str) {
                    this.this$0.handleResponse$ar$edu(i, i2, str, z);
                }
            })) {
                if (this.requestIdMap.size() > 100) {
                    LogUtils.w("GeminiActor", "The requestIdMap reaches its max capacity.", new Object[0]);
                    this.requestIdMap.clear();
                }
                this.requestIdMap.put(valueOf, false);
                this.progressTonePlayer.stop();
                this.geminiEndpoint.cancelCommand();
                this.progressTonePlayer.play$ar$ds$e8dc0924_0(!z);
            }
        }
    }

    public final void requestOnlineGeminiCommand(final int i, String str, Bitmap bitmap) {
        if (this.requestIdMap.size() > 100) {
            LogUtils.w("GeminiActor", "The requestIdMap reaches its max capacity.", new Object[0]);
            this.requestIdMap.clear();
        }
        Map map = this.requestIdMap;
        Integer valueOf = Integer.valueOf(i);
        map.put(valueOf, true);
        this.analytics.onGeminiEvent(1, true, true);
        this.startTime = SystemClock.uptimeMillis();
        if (this.geminiEndpoint.createRequestGeminiCommand(str, bitmap, true, new GeminiResponseListener(this) { // from class: com.google.android.accessibility.talkback.actor.gemini.GeminiActor.2
            final /* synthetic */ GeminiActor this$0;

            {
                this.this$0 = this;
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiResponseListener
            public final void onError$ar$edu(int i2) {
                this.this$0.handleErrorResponse$ar$edu(i, i2, true);
            }

            @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiResponseListener
            public final void onResponse$ar$edu(int i2, String str2) {
                this.this$0.handleResponse$ar$edu(i, i2, str2, true);
            }
        })) {
            if (this.requestIdMap.size() > 100) {
                LogUtils.w("GeminiActor", "The requestIdMap reaches its max capacity.", new Object[0]);
                this.requestIdMap.clear();
            }
            this.requestIdMap.put(valueOf, true);
            this.progressTonePlayer.stop();
            this.aiCoreEndpoint.cancelExistingRequestIfNeeded();
            this.progressTonePlayer.play$ar$ds$e8dc0924_0(false);
        }
    }
}
