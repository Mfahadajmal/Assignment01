package com.google.android.accessibility.utils.monitor;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager$AudioRecordingCallback;
import android.media.AudioRecordingConfiguration;
import androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MediaRecorderMonitor {
    public final AudioManager audioManager;
    public HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$aeea20e2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean isRecording = false;
    public boolean isVoiceRecognitionActive = false;
    public final AudioManager$AudioRecordingCallback audioRecordingCallback = new AudioManager$AudioRecordingCallback() { // from class: com.google.android.accessibility.utils.monitor.MediaRecorderMonitor.1
        public final void onRecordingConfigChanged(List list) {
            HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1;
            super.onRecordingConfigChanged(list);
            MediaRecorderMonitor.this.isVoiceRecognitionActive = MediaRecorderMonitor.containsAudioSourceVoiceRecog(list);
            boolean containsAudioSources = MediaRecorderMonitor.containsAudioSources(list);
            MediaRecorderMonitor mediaRecorderMonitor = MediaRecorderMonitor.this;
            if (!mediaRecorderMonitor.isRecording && containsAudioSources && (hapticPatternParser$$ExternalSyntheticLambda1 = mediaRecorderMonitor.listener$ar$class_merging$aeea20e2_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging) != null) {
                hapticPatternParser$$ExternalSyntheticLambda1.onMicrophoneActivated();
            }
            MediaRecorderMonitor.this.isRecording = containsAudioSources;
        }
    };

    public MediaRecorderMonitor(Context context) {
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    public static boolean containsAudioSourceVoiceRecog(List list) {
        int clientAudioSource;
        if (list == null) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            clientAudioSource = ViewCompat$$ExternalSyntheticApiModelOutline0.m(it.next()).getClientAudioSource();
            if (clientAudioSource == 6) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAudioSources(List list) {
        int clientAudioSource;
        int clientAudioSource2;
        if (list == null) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AudioRecordingConfiguration m = ViewCompat$$ExternalSyntheticApiModelOutline0.m(it.next());
            clientAudioSource = m.getClientAudioSource();
            if (clientAudioSource != 6) {
                clientAudioSource2 = m.getClientAudioSource();
                if (clientAudioSource2 == 1) {
                }
            }
            return true;
        }
        return false;
    }
}
