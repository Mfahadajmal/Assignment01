package com.google.android.accessibility.talkback.actor.gemini.progress;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.Consumer;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.Supplier;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.Http2Ping;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProgressTonePlayer {
    private final Supplier currentTimeMsSupplier;
    private long endTimeMs;
    private final Handler handler;
    private final Runnable onTimeoutListener;
    private final long timeoutDelayMs;
    private final Consumer tonePlayer;
    private final SpannableUtils$NonCopyableTextSpan toneProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private SpannableUtils$NonCopyableTextSpan tones$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public ProgressTonePlayer(SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan, Consumer consumer, long j, Runnable runnable) {
        Handler handler = new Handler(Looper.getMainLooper());
        Supplier supplier = new Supplier() { // from class: com.google.android.accessibility.talkback.actor.gemini.progress.ProgressTonePlayer$$ExternalSyntheticLambda0
            @Override // com.google.android.accessibility.utils.Supplier
            public final Object get() {
                return Long.valueOf(SystemClock.elapsedRealtime());
            }
        };
        this.handler = handler;
        this.toneProvider$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
        this.tonePlayer = consumer;
        this.onTimeoutListener = runnable;
        this.currentTimeMsSupplier = supplier;
        this.timeoutDelayMs = j;
    }

    public final void play$ar$ds$e8dc0924_0(boolean z) {
        stop();
        long longValue = ((Long) this.currentTimeMsSupplier.get()).longValue() + this.timeoutDelayMs;
        this.tones$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
        if (z) {
            this.handler.postDelayed(new Http2Ping.AnonymousClass1(this, longValue, 1), 2500L);
        } else {
            playTonesInternal(longValue);
        }
    }

    public final void playNextTone() {
        long longValue = ((Long) this.currentTimeMsSupplier.get()).longValue();
        long j = this.endTimeMs;
        if (longValue >= j) {
            this.onTimeoutListener.run();
            return;
        }
        if (this.tones$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            long longValue2 = j - ((Long) this.currentTimeMsSupplier.get()).longValue();
            if (longValue2 > 0) {
                this.handler.postDelayed(this.onTimeoutListener, longValue2);
                return;
            } else {
                this.onTimeoutListener.run();
                return;
            }
        }
        this.tonePlayer.accept(new ApplicationModule(Feedback.Part.builder().setSound(new Feedback.Sound(R.raw.loading, 1.0f, 1.0f, 0L))));
        this.handler.postDelayed(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 9), 3000L);
    }

    public final void playTonesInternal(long j) {
        this.endTimeMs = j;
        playNextTone();
    }

    public final void stop() {
        stopTones();
        this.handler.removeCallbacksAndMessages(null);
    }

    public final void stopTones() {
        this.tones$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
    }
}
