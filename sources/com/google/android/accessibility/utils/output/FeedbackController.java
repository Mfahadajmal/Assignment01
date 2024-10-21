package com.google.android.accessibility.utils.output;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.SparseIntArray;
import com.google.android.accessibility.utils.Performance;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FeedbackController {
    public static final int DEFAULT_STREAM = 10;
    public boolean mAuditoryEnabled;
    private final Context mContext;
    public boolean mHapticEnabled;
    private final Set mHapticFeedbackListeners;
    private final Resources mResources;
    private final SparseIntArray mSoundIds;
    public final SoundPool mSoundPool;
    private final Vibrator mVibrator;
    public float mVolumeAdjustment;
    private final WindowTrackerFactory parser$ar$class_merging$ar$class_merging;
    public final HashMap resIdToLastPlayUptimeMillisec;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface HapticFeedbackListener {
        void onHapticFeedbackStarting$ar$ds();
    }

    public FeedbackController(Context context) {
        SoundPool build = new SoundPool.Builder().setMaxStreams(10).setAudioAttributes(new AudioAttributes.Builder().setUsage(11).setContentType(2).build()).build();
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        this.mSoundIds = new SparseIntArray();
        this.mVolumeAdjustment = 1.0f;
        this.mHapticFeedbackListeners = new HashSet();
        this.resIdToLastPlayUptimeMillisec = new HashMap();
        this.mContext = context;
        this.mResources = context.getResources();
        this.mSoundPool = build;
        this.mVibrator = vibrator;
        this.parser$ar$class_merging$ar$class_merging = new WindowTrackerFactory(vibrator);
    }

    public final void interrupt() {
        this.mVibrator.cancel();
    }

    public final void playAuditory(int i, float f, float f2, Performance.EventId eventId) {
        playAuditory$ar$ds(i, f, f2, eventId);
    }

    public final void playAuditory$ar$ds(int i, final float f, float f2, Performance.EventId eventId) {
        if (this.mAuditoryEnabled && i != 0) {
            LogUtils.v("FeedbackController", "playAuditory() resId=%d eventId=%s", Integer.valueOf(i), eventId);
            final float f3 = f2 * this.mVolumeAdjustment;
            int i2 = this.mSoundIds.get(i);
            if (i2 != 0) {
                new EarconsPlayTask(this.mSoundPool, i2, f3, f).execute(new Void[0]);
            } else {
                this.mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() { // from class: com.google.android.accessibility.utils.output.FeedbackController$$ExternalSyntheticLambda0
                    @Override // android.media.SoundPool.OnLoadCompleteListener
                    public final void onLoadComplete(SoundPool soundPool, int i3, int i4) {
                        FeedbackController feedbackController = FeedbackController.this;
                        if (feedbackController.mAuditoryEnabled && i3 != 0) {
                            float f4 = f;
                            new EarconsPlayTask(feedbackController.mSoundPool, i3, f3, f4).execute(new Void[0]);
                        }
                    }
                });
                this.mSoundIds.put(i, this.mSoundPool.load(this.mContext, i, 1));
            }
        }
    }

    public final void playHaptic$ar$ds(int i, Performance.EventId eventId) {
        if (this.mHapticEnabled && i != 0) {
            LogUtils.v("FeedbackController", "playHaptic() resId=%d eventId=%s", Integer.valueOf(i), eventId);
            try {
                VibrationEffect parse = this.parser$ar$class_merging$ar$class_merging.parse(this.mResources.getIntArray(i));
                Set set = this.mHapticFeedbackListeners;
                System.nanoTime();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((HapticFeedbackListener) it.next()).onHapticFeedbackStarting$ar$ds();
                }
                this.mVibrator.vibrate(parse);
            } catch (Resources.NotFoundException unused) {
                LogUtils.e("FeedbackController", "Failed to load pattern %d", Integer.valueOf(i));
            }
        }
    }

    public final void shutdown() {
        this.mHapticFeedbackListeners.clear();
        this.mSoundPool.release();
        this.mVibrator.cancel();
        this.mAuditoryEnabled = false;
        this.mHapticEnabled = false;
    }

    public final void playAuditory(int i, Performance.EventId eventId) {
        playAuditory(i, 1.0f, 1.0f, eventId);
    }
}
