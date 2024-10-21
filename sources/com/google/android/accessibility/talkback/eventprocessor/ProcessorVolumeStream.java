package com.google.android.accessibility.talkback.eventprocessor;

import android.media.AudioManager;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ServiceKeyEventListener;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.compat.media.AudioManagerCompatUtils;
import com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternDetector;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessorVolumeStream implements AccessibilityEventListener, ServiceKeyEventListener, VolumeButtonPatternDetector.OnPatternMatchListener {
    private static final int STREAM_TALKBACK_AUDIO = 10;
    private static final String WL_TAG = "ProcessorVolumeStream";
    private final ActorState actorState;
    private final AudioManager audioManager;
    private final VolumeStreamHandler handler = new VolumeStreamHandler(this);
    private boolean isTouchInteracting = false;
    private final MostRecentVolumeKeyAdjustment mostRecentVolumeKeyAdjustment = new MostRecentVolumeKeyAdjustment();
    private final VolumeButtonPatternDetector patternDetector;
    private final RetryingNameResolver.ResolutionResultListener touchInteractingIndicator$ar$class_merging$ar$class_merging$ar$class_merging;
    private final PowerManager.WakeLock wakeLock;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MostRecentVolumeKeyAdjustment {
        public long moment;
        public int stream;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class VolumeStreamHandler extends WeakReferenceHandler {
        public VolumeStreamHandler(ProcessorVolumeStream processorVolumeStream) {
            super(processorVolumeStream);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            ProcessorVolumeStream processorVolumeStream = (ProcessorVolumeStream) obj;
            int i = message.arg1;
            int i2 = message.arg2;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                processorVolumeStream.passThroughMediaButtonClick(i2);
                return;
            }
            processorVolumeStream.passThroughMediaButtonClick(i2);
        }
    }

    public ProcessorVolumeStream(ActorState actorState, TalkBackService talkBackService, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.audioManager = (AudioManager) talkBackService.getSystemService("audio");
        this.actorState = actorState;
        this.touchInteractingIndicator$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.wakeLock = ((PowerManager) talkBackService.getSystemService("power")).newWakeLock(536870922, WL_TAG);
        VolumeButtonPatternDetector volumeButtonPatternDetector = new VolumeButtonPatternDetector();
        this.patternDetector = volumeButtonPatternDetector;
        volumeButtonPatternDetector.mListener = this;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 3145728;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        boolean z;
        int eventType = accessibilityEvent.getEventType();
        if (eventType != 1048576) {
            if (eventType != 2097152) {
                return;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        this.isTouchInteracting = z;
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean onKeyEvent(KeyEvent keyEvent, Performance.EventId eventId) {
        boolean onKeyEvent = this.patternDetector.onKeyEvent(keyEvent);
        if (onKeyEvent) {
            this.wakeLock.acquire();
            this.wakeLock.release();
        }
        return onKeyEvent;
    }

    @Override // com.google.android.accessibility.utils.volumebutton.VolumeButtonPatternDetector.OnPatternMatchListener
    public final void onPatternMatched(int i, int i2, Performance.EventId eventId) {
        VolumeStreamHandler volumeStreamHandler = this.handler;
        volumeStreamHandler.sendMessage(volumeStreamHandler.obtainMessage(0, i, i2, eventId));
    }

    public final void passThroughMediaButtonClick(int i) {
        int i2;
        if (i != 79) {
            boolean z = this.isTouchInteracting;
            if (i == 24) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            if (!z && !this.actorState.getContinuousRead$ar$class_merging$ar$class_merging().isActive() && !this.touchInteractingIndicator$ar$class_merging$ar$class_merging$ar$class_merging.isTouchInteracting()) {
                MostRecentVolumeKeyAdjustment mostRecentVolumeKeyAdjustment = this.mostRecentVolumeKeyAdjustment;
                long j = mostRecentVolumeKeyAdjustment.moment;
                long uptimeMillis = SystemClock.uptimeMillis();
                mostRecentVolumeKeyAdjustment.moment = uptimeMillis;
                if (uptimeMillis - j >= 1000 ? this.actorState.getSpeechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().isSpeakingOrQueuedAndNotSourceIsVolumeAnnouncment() : this.mostRecentVolumeKeyAdjustment.stream == 10) {
                    this.mostRecentVolumeKeyAdjustment.stream = 10;
                } else {
                    this.mostRecentVolumeKeyAdjustment.stream = Integer.MIN_VALUE;
                    this.audioManager.adjustSuggestedStreamVolume(i2, Integer.MIN_VALUE, 21);
                    return;
                }
            }
            AudioManager audioManager = this.audioManager;
            getClass().getName();
            int i3 = AudioManagerCompatUtils.AudioManagerCompatUtils$ar$NoOp;
            if (audioManager != null) {
                try {
                    audioManager.adjustStreamVolume(10, i2, 16);
                    return;
                } catch (SecurityException e) {
                    LogUtils.e("AudioManagerCompatUtils", "Error while adjusting stream volume: %s", e);
                    return;
                }
            }
            return;
        }
        this.audioManager.dispatchMediaKeyEvent(new KeyEvent(0, 79));
        this.audioManager.dispatchMediaKeyEvent(new KeyEvent(1, 79));
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean processWhenServiceSuspended() {
        return true;
    }
}
