package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.monitor.CallStateMonitor;
import com.google.android.accessibility.utils.compat.media.AudioSystemCompatUtils;
import com.google.android.accessibility.utils.monitor.AudioPlaybackMonitor;
import com.google.android.accessibility.utils.monitor.MediaRecorderMonitor;
import com.google.android.accessibility.utils.monitor.SpeechStateMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.ssb.SsbProto$SsbState;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VoiceActionMonitor {
    public final AudioPlaybackMonitor audioPlaybackMonitor;
    private final HapticPatternParser$$ExternalSyntheticLambda1 audioPlaybackStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final HapticPatternParser$$ExternalSyntheticLambda1 callStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final CallStateMonitor callStateMonitor;
    public final MediaRecorderMonitor mediaRecorderMonitor;
    private final HapticPatternParser$$ExternalSyntheticLambda1 microphoneStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final TalkBackService service;
    public boolean skipInterruption = true;
    private final SpeechStateMonitor speechStateMonitor;
    public final SsbServiceClientMonitor ssbServiceClientMonitor;
    private final RetryingNameResolver.ResolutionResultListener ssbServiceStateChangedListener$ar$class_merging$ar$class_merging;

    public VoiceActionMonitor(TalkBackService talkBackService, CallStateMonitor callStateMonitor, SpeechStateMonitor speechStateMonitor) {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = new RetryingNameResolver.ResolutionResultListener(this, null);
        this.ssbServiceStateChangedListener$ar$class_merging$ar$class_merging = resolutionResultListener;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(this);
        this.microphoneStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12 = new HapticPatternParser$$ExternalSyntheticLambda1(this);
        this.audioPlaybackStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda12;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda13 = new HapticPatternParser$$ExternalSyntheticLambda1(this);
        this.callStateChangedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda13;
        this.service = talkBackService;
        this.speechStateMonitor = speechStateMonitor;
        SsbServiceClientMonitor ssbServiceClientMonitor = new SsbServiceClientMonitor(talkBackService);
        this.ssbServiceClientMonitor = ssbServiceClientMonitor;
        ssbServiceClientMonitor.listener$ar$class_merging$ar$class_merging = resolutionResultListener;
        MediaRecorderMonitor mediaRecorderMonitor = new MediaRecorderMonitor(talkBackService);
        this.mediaRecorderMonitor = mediaRecorderMonitor;
        mediaRecorderMonitor.listener$ar$class_merging$aeea20e2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        AudioPlaybackMonitor audioPlaybackMonitor = new AudioPlaybackMonitor(talkBackService);
        this.audioPlaybackMonitor = audioPlaybackMonitor;
        audioPlaybackMonitor.listener$ar$class_merging$d672cb8a_0$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda12;
        this.callStateMonitor = callStateMonitor;
        if (callStateMonitor.supportTelephony) {
            callStateMonitor.callStateChangedListeners.add(hapticPatternParser$$ExternalSyntheticLambda13);
        }
    }

    public final void interruptTalkBackAudio(int i) {
        String str;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    str = "CALL_STATE";
                } else {
                    str = "AUDIO_PLAYBACK";
                }
            } else {
                str = "MEDIA_RECORDER";
            }
        } else {
            str = "SSB";
        }
        LogUtils.v("VoiceActionMonitor", "Interrupt TalkBack audio. voice action source=%s", str);
        this.service.interruptAllFeedback$ar$ds$404beace_1();
    }

    public final boolean isAudioPlaybackActive() {
        AudioPlaybackMonitor audioPlaybackMonitor = this.audioPlaybackMonitor;
        if ((audioPlaybackMonitor.audioPlaybackCallback != null && audioPlaybackMonitor.isPlaying) || this.speechStateMonitor.isStateValid(1)) {
            return true;
        }
        return false;
    }

    public final boolean isHeadphoneOn() {
        return SnackbarManager.isHeadphoneOn(this.service);
    }

    public final boolean isMicrophoneActive() {
        MediaRecorderMonitor mediaRecorderMonitor = this.mediaRecorderMonitor;
        if (mediaRecorderMonitor.audioRecordingCallback == null ? !(AudioSystemCompatUtils.isSourceActive(6) || AudioSystemCompatUtils.isSourceActive(1)) : !mediaRecorderMonitor.isRecording) {
            if (!this.speechStateMonitor.isStateValid(4)) {
                return false;
            }
        }
        return true;
    }

    public final boolean isVoiceRecognitionActive() {
        boolean isSourceActive;
        SsbServiceClientMonitor ssbServiceClientMonitor = this.ssbServiceClientMonitor;
        if (!ssbServiceClientMonitor.ssbServiceClient.isConnected() || (ssbServiceClientMonitor.ssbState$ar$edu != SsbProto$SsbState.AudioState.LISTENING$ar$edu && ssbServiceClientMonitor.ssbState$ar$edu != SsbProto$SsbState.AudioState.RECORDING$ar$edu)) {
            MediaRecorderMonitor mediaRecorderMonitor = this.mediaRecorderMonitor;
            if (mediaRecorderMonitor.audioRecordingCallback != null) {
                isSourceActive = mediaRecorderMonitor.isVoiceRecognitionActive;
            } else {
                isSourceActive = AudioSystemCompatUtils.isSourceActive(6);
            }
            if (isSourceActive) {
                return true;
            }
            return false;
        }
        return true;
    }
}
