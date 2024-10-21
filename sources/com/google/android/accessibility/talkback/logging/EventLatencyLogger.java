package com.google.android.accessibility.talkback.logging;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.util.StringBuilderPrinter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.analytics.AccessibilityActionEnums$ActionType;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.FailoverTextToSpeech;
import com.google.android.accessibility.utils.performance.AccessibilityActionDetails;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import googledata.experiments.mobile.accessibility_suite.features.PrimesConfig;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import org.chromium.net.impl.JavaUrlRequestUtils$DirectPreventingExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventLatencyLogger implements FailoverTextToSpeech.FailoverTtsListener {
    public final Handler backgroundHandler;
    private final long delayThresholdForMessageQueueDetails;
    private final Executor executor;
    private final boolean isDebugBuild;
    private final PrimesController primesController;
    public final FeatureStateProvider stateProvider;
    private final TtsLatencyLogger ttsLatencyLogger;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TtsLatencyLogger {
        public final Context context;
        public String enginePackageName = null;
        public long engineVersionCode = -1;
        public final PrimesController primesController;
        public Pair utteranceInfo;

        public TtsLatencyLogger(PrimesController primesController, Context context) {
            this.primesController = primesController;
            this.context = context;
        }
    }

    public EventLatencyLogger(PrimesController primesController, Context context, SharedPreferences sharedPreferences) {
        boolean z;
        if (!Build.TYPE.equals("eng") && !Build.TYPE.equals("userdebug")) {
            z = false;
        } else {
            z = true;
        }
        this.isDebugBuild = z;
        this.primesController = primesController;
        this.ttsLatencyLogger = new TtsLatencyLogger(primesController, context);
        this.stateProvider = new FeatureStateProvider(context, sharedPreferences);
        HandlerThread handlerThread = new HandlerThread("EventLatencyLogger");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.backgroundHandler = handler;
        this.executor = new JavaUrlRequestUtils$DirectPreventingExecutor(handler, 1);
        this.delayThresholdForMessageQueueDetails = PrimesConfig.INSTANCE.get().getThresholdMessageQueueDetails(context);
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final void onAccessibilityActionPerformed(Performance.EventData eventData) {
        int i;
        Pattern pattern = TalkbackExtensionUtils.PARSE_DELAYED_TIME;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.EventLatencyInfo.DEFAULT_INSTANCE.createBuilder();
        int eventTypeToEventLatencyInfoType$ar$edu = TalkbackExtensionUtils.eventTypeToEventLatencyInfoType$ar$edu(eventData.eventId);
        if (eventTypeToEventLatencyInfoType$ar$edu != 0) {
            builder.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
            eventLatencyInfo.eventType_ = eventTypeToEventLatencyInfoType$ar$edu - 1;
            eventLatencyInfo.bitField0_ |= 1;
        }
        long j = eventData.uptimeReceivedAtTalkback;
        Performance.EventId eventId = eventData.eventId;
        builder.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
        eventLatencyInfo2.bitField0_ |= 2;
        eventLatencyInfo2.latencyEventTransmissionMs_ = j - eventId.eventTimeMs;
        AccessibilityActionDetails actionDetails = eventData.getActionDetails();
        if (actionDetails != null) {
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.ActionLatencyInfo.DEFAULT_INSTANCE.createBuilder();
            int i2 = actionDetails.actionId;
            if (i2 == 64) {
                i = AccessibilityActionEnums$ActionType.TYPE_ACCESSIBILITY_FOCUS$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_CLEAR_ACCESSIBILITY_FOCUS$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_FOCUS$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SHOW_ON_SCREEN$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_CLICK$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_LONG_CLICK.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_LONG_CLICK$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_UP$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_DOWN$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_LEFT$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_RIGHT$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_BACKWARD$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_SCROLL_FORWARD$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_UP.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_PAGE_UP$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_DOWN.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_PAGE_DOWN$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_LEFT.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_PAGE_LEFT$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PAGE_RIGHT.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_PAGE_RIGHT$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COPY.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_COPY$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_PASTE.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_PASTE$ar$edu;
            } else if (i2 == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CUT.getId()) {
                i = AccessibilityActionEnums$ActionType.TYPE_CUT$ar$edu;
            } else {
                i = AccessibilityActionEnums$ActionType.TYPE_UNDEFINED$ar$edu;
            }
            builder2.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.ActionLatencyInfo actionLatencyInfo = (ExtensionTalkback$TalkbackExtension.ActionLatencyInfo) builder2.instance;
            int i3 = i - 1;
            if (i != 0) {
                actionLatencyInfo.actionType_ = i3;
                actionLatencyInfo.bitField0_ |= 1;
                long j2 = actionDetails.processingTime;
                builder2.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.ActionLatencyInfo actionLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.ActionLatencyInfo) builder2.instance;
                actionLatencyInfo2.bitField0_ |= 4;
                actionLatencyInfo2.processingTimeMs_ = j2;
                boolean z = actionDetails.success;
                builder2.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.ActionLatencyInfo actionLatencyInfo3 = (ExtensionTalkback$TalkbackExtension.ActionLatencyInfo) builder2.instance;
                actionLatencyInfo3.bitField0_ |= 2;
                actionLatencyInfo3.success_ = z;
                ExtensionTalkback$TalkbackExtension.ActionLatencyInfo actionLatencyInfo4 = (ExtensionTalkback$TalkbackExtension.ActionLatencyInfo) builder2.build();
                builder.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo3 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
                actionLatencyInfo4.getClass();
                eventLatencyInfo3.actionLatencyInfo_ = actionLatencyInfo4;
                eventLatencyInfo3.bitField0_ |= 64;
            } else {
                throw null;
            }
        }
        ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo4 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.build();
        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.DEFAULT_INSTANCE.createBuilder();
        builder3.copyOnWrite();
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension = (ExtensionTalkback$TalkbackExtension) builder3.instance;
        eventLatencyInfo4.getClass();
        extensionTalkback$TalkbackExtension.eventLatencyInfo_ = eventLatencyInfo4;
        extensionTalkback$TalkbackExtension.bitField0_ |= 1;
        int i4 = this.stateProvider.featureStates;
        builder3.copyOnWrite();
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension2 = (ExtensionTalkback$TalkbackExtension) builder3.instance;
        extensionTalkback$TalkbackExtension2.bitField0_ |= 4;
        extensionTalkback$TalkbackExtension2.featureStateBitmask_ = i4;
        this.primesController.recordDuration(PrimesController.TimerAction.EVENT_BASED_PERFORMING_ACTION, eventData.uptimeReceivedAtTalkback, eventData.getActionDetails().finishedUpTime, (ExtensionTalkback$TalkbackExtension) builder3.build());
        long j3 = eventData.getActionDetails().finishedUpTime;
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final void onBeforeUtteranceRequested(String str, FailoverTextToSpeech.UtteranceInfoCombo utteranceInfoCombo) {
        Pair pair = new Pair(str, utteranceInfoCombo);
        TtsLatencyLogger ttsLatencyLogger = this.ttsLatencyLogger;
        ttsLatencyLogger.utteranceInfo = pair;
        ttsLatencyLogger.primesController.startTimer(PrimesController.TimerAction.TTS_DELAY, str);
    }

    public final void onFeedbackOutput(Performance.EventData eventData) {
        SystemHealthProto$PackedHistogram.Builder builder;
        int i;
        int i2;
        int i3;
        if (eventData.timeReceivedAtTalkback >= eventData.getTimeFeedbackOutput()) {
            return;
        }
        long timeFeedbackComposed = eventData.getTimeFeedbackComposed() - eventData.timeReceivedAtTalkback;
        long j = this.delayThresholdForMessageQueueDetails;
        try {
            Pattern pattern = TalkbackExtensionUtils.PARSE_DELAYED_TIME;
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.EventLatencyInfo.DEFAULT_INSTANCE.createBuilder();
            int eventTypeToEventLatencyInfoType$ar$edu = TalkbackExtensionUtils.eventTypeToEventLatencyInfoType$ar$edu(eventData.eventId);
            if (eventTypeToEventLatencyInfoType$ar$edu != 0) {
                builder2.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder2.instance;
                eventLatencyInfo.eventType_ = eventTypeToEventLatencyInfoType$ar$edu - 1;
                eventLatencyInfo.bitField0_ |= 1;
            }
            long j2 = eventData.uptimeReceivedAtTalkback - eventData.eventId.eventTimeMs;
            long timeFeedbackComposed2 = eventData.getTimeFeedbackComposed() - eventData.timeReceivedAtTalkback;
            long timeFeedbackQueued = eventData.getTimeFeedbackQueued() - eventData.timeReceivedAtTalkback;
            if (timeFeedbackComposed > j) {
                Looper mainLooper = Looper.getMainLooper();
                StringBuilder sb = new StringBuilder();
                mainLooper.dump(new StringBuilderPrinter(sb), mainLooper.getThread().getName());
                String sb2 = sb.toString();
                Matcher matcher = TalkbackExtensionUtils.PARSE_DELAYED_TIME.matcher(sb2);
                long j3 = -1;
                long j4 = -1;
                int i4 = 0;
                while (matcher.find()) {
                    i4++;
                    if (j4 == j3) {
                        String group = matcher.group(1);
                        if (group != null) {
                            i2 = Integer.parseInt(group.substring(0, group.length() - 1));
                        } else {
                            i2 = 0;
                        }
                        String group2 = matcher.group(2);
                        if (group2 != null) {
                            i3 = Integer.parseInt(group2.substring(0, group2.length() - 2));
                        } else {
                            i3 = 0;
                        }
                        j4 = (i2 * 1000) + i3;
                        j3 = -1;
                    }
                }
                Matcher matcher2 = TalkbackExtensionUtils.PARSE_TOTAL_MASSAGE.matcher(sb2);
                if (matcher2.find()) {
                    i = Integer.parseInt(matcher2.group(1));
                } else {
                    i = 0;
                }
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.MessageQueueDetails.DEFAULT_INSTANCE.createBuilder();
                builder3.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.MessageQueueDetails messageQueueDetails = (ExtensionTalkback$TalkbackExtension.MessageQueueDetails) builder3.instance;
                messageQueueDetails.bitField0_ |= 1;
                messageQueueDetails.maxWaitingTimeMs_ = j4;
                long j5 = i4;
                builder3.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.MessageQueueDetails messageQueueDetails2 = (ExtensionTalkback$TalkbackExtension.MessageQueueDetails) builder3.instance;
                messageQueueDetails2.bitField0_ |= 2;
                messageQueueDetails2.pendingMessageCounts_ = j5;
                long j6 = i;
                builder3.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.MessageQueueDetails messageQueueDetails3 = (ExtensionTalkback$TalkbackExtension.MessageQueueDetails) builder3.instance;
                messageQueueDetails3.bitField0_ |= 4;
                messageQueueDetails3.queuedMessageCounts_ = j6;
                ExtensionTalkback$TalkbackExtension.MessageQueueDetails messageQueueDetails4 = (ExtensionTalkback$TalkbackExtension.MessageQueueDetails) builder3.build();
                builder2.copyOnWrite();
                builder = builder2;
                ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
                messageQueueDetails4.getClass();
                eventLatencyInfo2.messageQueueDetails_ = messageQueueDetails4;
                eventLatencyInfo2.bitField0_ |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
            } else {
                builder = builder2;
            }
            builder.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo3 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
            eventLatencyInfo3.bitField0_ = 2 | eventLatencyInfo3.bitField0_;
            eventLatencyInfo3.latencyEventTransmissionMs_ = j2;
            builder.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo4 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
            eventLatencyInfo4.bitField0_ |= 4;
            eventLatencyInfo4.latencyFeedbackComposedMs_ = timeFeedbackComposed2;
            builder.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo5 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
            eventLatencyInfo5.bitField0_ |= 8;
            eventLatencyInfo5.latencyFeedbackQueuedMs_ = timeFeedbackQueued;
            ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo6 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.build();
            int i5 = this.stateProvider.featureStates;
            if (eventData.getChangeToSameLocale()) {
                i5 |= 256;
            }
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.DEFAULT_INSTANCE.createBuilder();
            builder4.copyOnWrite();
            ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension = (ExtensionTalkback$TalkbackExtension) builder4.instance;
            eventLatencyInfo6.getClass();
            extensionTalkback$TalkbackExtension.eventLatencyInfo_ = eventLatencyInfo6;
            extensionTalkback$TalkbackExtension.bitField0_ |= 1;
            builder4.copyOnWrite();
            ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension2 = (ExtensionTalkback$TalkbackExtension) builder4.instance;
            extensionTalkback$TalkbackExtension2.bitField0_ |= 4;
            extensionTalkback$TalkbackExtension2.featureStateBitmask_ = i5;
            this.primesController.recordDuration(PrimesController.TimerAction.EVENT_BASED_HEARING_FEEDBACK, eventData.timeReceivedAtTalkback, eventData.getTimeFeedbackOutput(), (ExtensionTalkback$TalkbackExtension) builder4.build());
        } catch (NumberFormatException e) {
            if (!this.isDebugBuild) {
                LogUtils.e("EventLatencyLogger", e, "NumberFormatException when parsing looper dump, skip logging", new Object[0]);
                return;
            }
            throw e;
        }
    }

    public final void onGestureRecognized(Performance.GestureEventData gestureEventData) {
        boolean z;
        Pattern pattern = TalkbackExtensionUtils.PARSE_DELAYED_TIME;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.EventLatencyInfo.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo.DEFAULT_INSTANCE.createBuilder();
        int i = gestureEventData.displayId;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo.bitField0_ |= 1;
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        gestureEventLatencyInfo.isDefaultDisplay_ = z;
        int i2 = gestureEventData.gestureId;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo2.bitField0_ |= 2;
        gestureEventLatencyInfo2.gestureId_ = i2;
        long j = gestureEventData.gestureDetectionStartedTime;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo3 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo3.bitField0_ |= 4;
        gestureEventLatencyInfo3.firstMotionEventReceivedMs_ = j;
        long j2 = gestureEventData.gestureDecisionTime - gestureEventData.lastMotionEventTime;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo4 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo4.bitField0_ |= 8;
        gestureEventLatencyInfo4.lastMotionEventTransmissionLatencyMs_ = j2;
        long j3 = gestureEventData.gestureDecisionTime;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo5 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo5.bitField0_ |= 16;
        gestureEventLatencyInfo5.gestureDecisionTimeMs_ = j3;
        long j4 = gestureEventData.gestureDetectedTime;
        builder2.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo6 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.instance;
        gestureEventLatencyInfo6.bitField0_ |= 32;
        gestureEventLatencyInfo6.onGestureDetectedTimeMs_ = j4;
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo7 = (ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo) builder2.build();
        builder.copyOnWrite();
        ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.instance;
        gestureEventLatencyInfo7.getClass();
        eventLatencyInfo.gestureEventLatencyInfo_ = gestureEventLatencyInfo7;
        eventLatencyInfo.bitField0_ |= 256;
        ExtensionTalkback$TalkbackExtension.EventLatencyInfo eventLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.EventLatencyInfo) builder.build();
        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.DEFAULT_INSTANCE.createBuilder();
        builder3.copyOnWrite();
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension = (ExtensionTalkback$TalkbackExtension) builder3.instance;
        eventLatencyInfo2.getClass();
        extensionTalkback$TalkbackExtension.eventLatencyInfo_ = eventLatencyInfo2;
        extensionTalkback$TalkbackExtension.bitField0_ |= 1;
        int i3 = this.stateProvider.featureStates;
        builder3.copyOnWrite();
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension2 = (ExtensionTalkback$TalkbackExtension) builder3.instance;
        extensionTalkback$TalkbackExtension2.bitField0_ |= 4;
        extensionTalkback$TalkbackExtension2.featureStateBitmask_ = i3;
        ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension3 = (ExtensionTalkback$TalkbackExtension) builder3.build();
        PrimesController primesController = this.primesController;
        PrimesController.TimerAction timerAction = PrimesController.TimerAction.GESTURE_EVENT_LATENCY;
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo8 = eventLatencyInfo2.gestureEventLatencyInfo_;
        if (gestureEventLatencyInfo8 == null) {
            gestureEventLatencyInfo8 = ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo.DEFAULT_INSTANCE;
        }
        long j5 = gestureEventLatencyInfo8.firstMotionEventReceivedMs_;
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo9 = eventLatencyInfo2.gestureEventLatencyInfo_;
        if (gestureEventLatencyInfo9 == null) {
            gestureEventLatencyInfo9 = ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo.DEFAULT_INSTANCE;
        }
        primesController.recordDuration(timerAction, j5, gestureEventLatencyInfo9.onGestureDetectedTimeMs_, extensionTalkback$TalkbackExtension3);
        ExtensionTalkback$TalkbackExtension.GestureEventLatencyInfo gestureEventLatencyInfo10 = eventLatencyInfo2.gestureEventLatencyInfo_;
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final void onTtsInitialized(boolean z, String str) {
        TtsLatencyLogger ttsLatencyLogger = this.ttsLatencyLogger;
        if (TextUtils.equals(ttsLatencyLogger.enginePackageName, str)) {
            return;
        }
        ttsLatencyLogger.enginePackageName = str;
        ttsLatencyLogger.engineVersionCode = SpannableUtils$IdentifierSpan.getVersionCodeCompat(ttsLatencyLogger.context, str);
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final /* synthetic */ void onUtteranceStarted(String str) {
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final void onUtteranceStarted(String str, long j) {
        TtsLatencyLogger ttsLatencyLogger = this.ttsLatencyLogger;
        int i = this.stateProvider.featureStates;
        Pair pair = ttsLatencyLogger.utteranceInfo;
        if (pair != null && TextUtils.equals(str, (CharSequence) pair.first)) {
            if (((FailoverTextToSpeech.UtteranceInfoCombo) ttsLatencyLogger.utteranceInfo.second).isLocaleAttached()) {
                i |= 16;
            }
            if (((FailoverTextToSpeech.UtteranceInfoCombo) ttsLatencyLogger.utteranceInfo.second).isSeparatorInUtterance()) {
                i |= 32;
            }
            if (((FailoverTextToSpeech.UtteranceInfoCombo) ttsLatencyLogger.utteranceInfo.second).isAggressiveChunking()) {
                i |= 64;
            }
            String str2 = ttsLatencyLogger.enginePackageName;
            long j2 = ttsLatencyLogger.engineVersionCode;
            int length = ((FailoverTextToSpeech.UtteranceInfoCombo) ttsLatencyLogger.utteranceInfo.second).text().length();
            Locale locale = ((FailoverTextToSpeech.UtteranceInfoCombo) ttsLatencyLogger.utteranceInfo.second).locale();
            int ttsEnginePackageNameToEnum$ar$edu = TalkbackExtensionUtils.ttsEnginePackageNameToEnum$ar$edu(str2);
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.TtsLatencyInfo.DEFAULT_INSTANCE.createBuilder();
            builder.copyOnWrite();
            ExtensionTalkback$TalkbackExtension.TtsLatencyInfo ttsLatencyInfo = (ExtensionTalkback$TalkbackExtension.TtsLatencyInfo) builder.instance;
            int i2 = ttsEnginePackageNameToEnum$ar$edu - 1;
            if (ttsEnginePackageNameToEnum$ar$edu != 0) {
                ttsLatencyInfo.enginePackageName_ = i2;
                ttsLatencyInfo.bitField0_ |= 1;
                builder.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.TtsLatencyInfo ttsLatencyInfo2 = (ExtensionTalkback$TalkbackExtension.TtsLatencyInfo) builder.instance;
                ttsLatencyInfo2.bitField0_ |= 2;
                ttsLatencyInfo2.engineVersionCode_ = j2;
                builder.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.TtsLatencyInfo ttsLatencyInfo3 = (ExtensionTalkback$TalkbackExtension.TtsLatencyInfo) builder.instance;
                ttsLatencyInfo3.bitField0_ |= 4;
                ttsLatencyInfo3.textLength_ = length;
                String languageTag = locale == null ? "" : locale.toLanguageTag();
                builder.copyOnWrite();
                ExtensionTalkback$TalkbackExtension.TtsLatencyInfo ttsLatencyInfo4 = (ExtensionTalkback$TalkbackExtension.TtsLatencyInfo) builder.instance;
                languageTag.getClass();
                ttsLatencyInfo4.bitField0_ |= 8;
                ttsLatencyInfo4.locale_ = languageTag;
                ExtensionTalkback$TalkbackExtension.TtsLatencyInfo ttsLatencyInfo5 = (ExtensionTalkback$TalkbackExtension.TtsLatencyInfo) builder.build();
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) ExtensionTalkback$TalkbackExtension.DEFAULT_INSTANCE.createBuilder();
                builder2.copyOnWrite();
                ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension = (ExtensionTalkback$TalkbackExtension) builder2.instance;
                ttsLatencyInfo5.getClass();
                extensionTalkback$TalkbackExtension.ttsLatencyInfo_ = ttsLatencyInfo5;
                extensionTalkback$TalkbackExtension.bitField0_ |= 2;
                builder2.copyOnWrite();
                ExtensionTalkback$TalkbackExtension extensionTalkback$TalkbackExtension2 = (ExtensionTalkback$TalkbackExtension) builder2.instance;
                extensionTalkback$TalkbackExtension2.bitField0_ |= 4;
                extensionTalkback$TalkbackExtension2.featureStateBitmask_ = i;
                ttsLatencyLogger.primesController.stopTimer(PrimesController.TimerAction.TTS_DELAY, str, (ExtensionTalkback$TalkbackExtension) builder2.build());
                ttsLatencyLogger.utteranceInfo = null;
                return;
            }
            throw null;
        }
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final void onUtteranceCompleted(String str, boolean z) {
    }

    @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
    public final void onUtteranceRangeStarted(String str, int i, int i2) {
    }
}
