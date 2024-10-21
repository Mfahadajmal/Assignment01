package com.google.android.accessibility.utils.monitor;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.AudioManager$AudioPlaybackCallback;
import android.media.AudioPlaybackConfiguration;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.BundleUtils$$ExternalSyntheticApiModelOutline0;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AudioPlaybackMonitor {
    private AudioManager audioManager;
    private final Context context;
    public HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$d672cb8a_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean isPlaying = false;
    public final AudioManager$AudioPlaybackCallback audioPlaybackCallback = new AudioManager$AudioPlaybackCallback() { // from class: com.google.android.accessibility.utils.monitor.AudioPlaybackMonitor.1
        public final void onPlaybackConfigChanged(List list) {
            AudioAttributes audioAttributes;
            super.onPlaybackConfigChanged(list);
            ArrayList arrayList = new ArrayList();
            for (int i : PlaybackSource.values$ar$edu$ae4f3112_0()) {
                if (i != 0) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        AudioPlaybackConfiguration m266m = BundleUtils$$ExternalSyntheticApiModelOutline0.m266m(it.next());
                        audioAttributes = m266m.getAudioAttributes();
                        if (i == audioAttributes.getUsage()) {
                            arrayList.add(m266m);
                        }
                    }
                } else {
                    throw null;
                }
            }
            boolean z = !arrayList.isEmpty();
            AudioPlaybackMonitor audioPlaybackMonitor = AudioPlaybackMonitor.this;
            HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = audioPlaybackMonitor.listener$ar$class_merging$d672cb8a_0$ar$class_merging$ar$class_merging$ar$class_merging;
            if (hapticPatternParser$$ExternalSyntheticLambda1 != null && !audioPlaybackMonitor.isPlaying && z) {
                hapticPatternParser$$ExternalSyntheticLambda1.onAudioPlaybackActivated(arrayList);
            }
            AudioPlaybackMonitor.this.isPlaying = z;
        }
    };

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PlaybackSource {
        public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE$ar$edu = 12;
        public static final int USAGE_MEDIA$ar$edu = 1;
        public static final int USAGE_GAME$ar$edu = 14;
        public static final int USAGE_ASSISTANT$ar$edu = 16;
        public static final int USAGE_ALARM$ar$edu = 4;
        private static final /* synthetic */ int[] $VALUES$ar$edu$1a412365_0 = {USAGE_ASSISTANCE_NAVIGATION_GUIDANCE$ar$edu, USAGE_MEDIA$ar$edu, USAGE_GAME$ar$edu, USAGE_ASSISTANT$ar$edu, USAGE_ALARM$ar$edu};

        public static int[] values$ar$edu$ae4f3112_0() {
            return new int[]{USAGE_ASSISTANCE_NAVIGATION_GUIDANCE$ar$edu, USAGE_MEDIA$ar$edu, USAGE_GAME$ar$edu, USAGE_ASSISTANT$ar$edu, USAGE_ALARM$ar$edu};
        }
    }

    public AudioPlaybackMonitor(Context context) {
        this.context = context;
    }

    public final AudioManager getAudioManager() {
        if (this.audioManager == null) {
            this.audioManager = (AudioManager) this.context.getSystemService("audio");
        }
        return this.audioManager;
    }

    public final void onResumeInfrastructure() {
        if (this.audioPlaybackCallback != null) {
            this.isPlaying = false;
            AudioManager audioManager = getAudioManager();
            if (audioManager == null) {
                return;
            }
            audioManager.registerAudioPlaybackCallback(this.audioPlaybackCallback, null);
        }
    }

    public final void onSuspendInfrastructure() {
        AudioManager audioManager;
        if (this.audioPlaybackCallback == null || (audioManager = getAudioManager()) == null) {
            return;
        }
        audioManager.unregisterAudioPlaybackCallback(this.audioPlaybackCallback);
    }
}
