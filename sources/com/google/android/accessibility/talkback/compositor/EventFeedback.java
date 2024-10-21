package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.libraries.performance.primes.DaggerProdInternalComponent;
import j$.util.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventFeedback {
    private final Boolean advanceContinuousReading;
    private final Integer earcon;
    private final Double earconRate;
    private final Double earconVolume;
    private final Boolean forceFeedbackEvenIfAudioPlaybackActive;
    private final Boolean forceFeedbackEvenIfMicrophoneActive;
    private final Boolean forceFeedbackEvenIfPhoneCallActive;
    private final Boolean forceFeedbackEvenIfSsbActive;
    private final Integer haptic;
    private final Boolean preventDeviceSleep;
    private final Integer queueMode;
    private final Boolean refreshSourceNode;
    private final Boolean ttsAddToHistory;
    private final Integer ttsClearQueueGroup;
    private final Boolean ttsForceFeedback;
    private final Boolean ttsInterruptSameGroup;
    private final Optional ttsOutput;
    private final Double ttsPitch;
    private final Boolean ttsSkipDuplicate;

    public EventFeedback() {
    }

    public static DaggerProdInternalComponent.Builder builder$ar$class_merging$f18d5ad3_0() {
        DaggerProdInternalComponent.Builder builder = new DaggerProdInternalComponent.Builder(null, null);
        builder.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.of("");
        builder.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider = 1;
        builder.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider = true;
        builder.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier = 0;
        builder.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider = false;
        builder.setTtsPitch$ar$ds(1.0d);
        builder.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken = false;
        builder.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider = false;
        builder.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider = -1;
        builder.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider = -1;
        Double valueOf = Double.valueOf(1.0d);
        builder.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier = valueOf;
        builder.DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider = valueOf;
        return builder;
    }

    public final Boolean advanceContinuousReading() {
        return this.advanceContinuousReading;
    }

    public final Integer earcon() {
        return this.earcon;
    }

    public final Double earconRate() {
        return this.earconRate;
    }

    public final Double earconVolume() {
        return this.earconVolume;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof EventFeedback) {
            EventFeedback eventFeedback = (EventFeedback) obj;
            if (this.ttsOutput.equals(eventFeedback.ttsOutput()) && this.queueMode.equals(eventFeedback.queueMode()) && this.forceFeedbackEvenIfAudioPlaybackActive.equals(eventFeedback.forceFeedbackEvenIfAudioPlaybackActive()) && this.forceFeedbackEvenIfMicrophoneActive.equals(eventFeedback.forceFeedbackEvenIfMicrophoneActive()) && this.forceFeedbackEvenIfSsbActive.equals(eventFeedback.forceFeedbackEvenIfSsbActive()) && this.forceFeedbackEvenIfPhoneCallActive.equals(eventFeedback.forceFeedbackEvenIfPhoneCallActive()) && this.ttsClearQueueGroup.equals(eventFeedback.ttsClearQueueGroup()) && this.ttsInterruptSameGroup.equals(eventFeedback.ttsInterruptSameGroup()) && this.ttsSkipDuplicate.equals(eventFeedback.ttsSkipDuplicate()) && this.ttsAddToHistory.equals(eventFeedback.ttsAddToHistory()) && this.ttsForceFeedback.equals(eventFeedback.ttsForceFeedback()) && this.ttsPitch.equals(eventFeedback.ttsPitch()) && this.preventDeviceSleep.equals(eventFeedback.preventDeviceSleep()) && this.refreshSourceNode.equals(eventFeedback.refreshSourceNode()) && this.advanceContinuousReading.equals(eventFeedback.advanceContinuousReading()) && this.haptic.equals(eventFeedback.haptic()) && this.earcon.equals(eventFeedback.earcon()) && this.earconRate.equals(eventFeedback.earconRate()) && this.earconVolume.equals(eventFeedback.earconVolume())) {
                return true;
            }
        }
        return false;
    }

    public final Boolean forceFeedbackEvenIfAudioPlaybackActive() {
        return this.forceFeedbackEvenIfAudioPlaybackActive;
    }

    public final Boolean forceFeedbackEvenIfMicrophoneActive() {
        return this.forceFeedbackEvenIfMicrophoneActive;
    }

    public final Boolean forceFeedbackEvenIfPhoneCallActive() {
        return this.forceFeedbackEvenIfPhoneCallActive;
    }

    public final Boolean forceFeedbackEvenIfSsbActive() {
        return this.forceFeedbackEvenIfSsbActive;
    }

    public final Integer haptic() {
        return this.haptic;
    }

    public final int hashCode() {
        return ((((((((((((((((((((((((((((((((((((this.ttsOutput.hashCode() ^ 1000003) * 1000003) ^ this.queueMode.hashCode()) * 1000003) ^ this.forceFeedbackEvenIfAudioPlaybackActive.hashCode()) * 1000003) ^ this.forceFeedbackEvenIfMicrophoneActive.hashCode()) * 1000003) ^ this.forceFeedbackEvenIfSsbActive.hashCode()) * 1000003) ^ this.forceFeedbackEvenIfPhoneCallActive.hashCode()) * 1000003) ^ this.ttsClearQueueGroup.hashCode()) * 1000003) ^ this.ttsInterruptSameGroup.hashCode()) * 1000003) ^ this.ttsSkipDuplicate.hashCode()) * 1000003) ^ this.ttsAddToHistory.hashCode()) * 1000003) ^ this.ttsForceFeedback.hashCode()) * 1000003) ^ this.ttsPitch.hashCode()) * 1000003) ^ this.preventDeviceSleep.hashCode()) * 1000003) ^ this.refreshSourceNode.hashCode()) * 1000003) ^ this.advanceContinuousReading.hashCode()) * 1000003) ^ this.haptic.hashCode()) * 1000003) ^ this.earcon.hashCode()) * 1000003) ^ this.earconRate.hashCode()) * 1000003) ^ this.earconVolume.hashCode();
    }

    public final Boolean preventDeviceSleep() {
        return this.preventDeviceSleep;
    }

    public final Integer queueMode() {
        return this.queueMode;
    }

    public final Boolean refreshSourceNode() {
        return this.refreshSourceNode;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(String.format("ttsOutput= %s  ", ttsOutput().orElseGet(new EventFeedback$$ExternalSyntheticLambda0(0))), StringBuilderUtils.optionalInt("queueMode", queueMode().intValue(), 1), StringBuilderUtils.optionalTag("ttsAddToHistory", ttsAddToHistory().booleanValue()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfAudioPlaybackActive", forceFeedbackEvenIfAudioPlaybackActive().booleanValue()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfMicrophoneActive", forceFeedbackEvenIfMicrophoneActive().booleanValue()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfSsbActive", forceFeedbackEvenIfSsbActive().booleanValue()), StringBuilderUtils.optionalTag("forceFeedbackEvenIfPhoneCallActive", forceFeedbackEvenIfPhoneCallActive().booleanValue()), StringBuilderUtils.optionalTag("ttsForceFeedback", ttsForceFeedback().booleanValue()), StringBuilderUtils.optionalTag("ttsInterruptSameGroup", ttsInterruptSameGroup().booleanValue()), StringBuilderUtils.optionalInt("ttsClearQueueGroup", ttsClearQueueGroup().intValue(), 0), StringBuilderUtils.optionalTag("ttsSkipDuplicate", ttsSkipDuplicate().booleanValue()), StringBuilderUtils.optionalDouble("ttsPitch", ttsPitch().doubleValue(), 1.0d), StringBuilderUtils.optionalTag("advanceContinuousReading", advanceContinuousReading().booleanValue()), StringBuilderUtils.optionalTag("preventDeviceSleep", preventDeviceSleep().booleanValue()), StringBuilderUtils.optionalTag("refreshSourceNode", refreshSourceNode().booleanValue()), StringBuilderUtils.optionalInt("haptic", haptic().intValue(), -1), StringBuilderUtils.optionalInt("earcon", earcon().intValue(), -1), StringBuilderUtils.optionalDouble("earconRate", earconRate().doubleValue(), 1.0d), StringBuilderUtils.optionalDouble("earconVolume", earconVolume().doubleValue(), 1.0d));
    }

    public final Boolean ttsAddToHistory() {
        return this.ttsAddToHistory;
    }

    public final Integer ttsClearQueueGroup() {
        return this.ttsClearQueueGroup;
    }

    public final Boolean ttsForceFeedback() {
        return this.ttsForceFeedback;
    }

    public final Boolean ttsInterruptSameGroup() {
        return this.ttsInterruptSameGroup;
    }

    public final Optional ttsOutput() {
        return this.ttsOutput;
    }

    public final Double ttsPitch() {
        return this.ttsPitch;
    }

    public final Boolean ttsSkipDuplicate() {
        return this.ttsSkipDuplicate;
    }

    public EventFeedback(Optional optional, Integer num, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Integer num2, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Double d, Boolean bool9, Boolean bool10, Boolean bool11, Integer num3, Integer num4, Double d2, Double d3) {
        this();
        this.ttsOutput = optional;
        this.queueMode = num;
        this.forceFeedbackEvenIfAudioPlaybackActive = bool;
        this.forceFeedbackEvenIfMicrophoneActive = bool2;
        this.forceFeedbackEvenIfSsbActive = bool3;
        this.forceFeedbackEvenIfPhoneCallActive = bool4;
        this.ttsClearQueueGroup = num2;
        this.ttsInterruptSameGroup = bool5;
        this.ttsSkipDuplicate = bool6;
        this.ttsAddToHistory = bool7;
        this.ttsForceFeedback = bool8;
        this.ttsPitch = d;
        this.preventDeviceSleep = bool9;
        this.refreshSourceNode = bool10;
        this.advanceContinuousReading = bool11;
        this.haptic = num3;
        this.earcon = num4;
        this.earconRate = d2;
        this.earconVolume = d3;
    }
}
