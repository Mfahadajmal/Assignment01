package com.google.android.accessibility.talkback;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Message;
import android.util.SparseIntArray;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.monitor.CallStateMonitor;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.accessibility.utils.compat.media.AudioManagerCompatUtils;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VolumeMonitor extends SameThreadBroadcastReceiver {
    private static final SparseIntArray STREAM_NAMES;
    public AudioManager audioManager;
    public final CallStateMonitor callStateMonitor;
    public Context context;
    public boolean hintA11yVolumeControl;
    public Pipeline.FeedbackReturner pipeline;
    public final SparseIntArray selfAdjustments = new SparseIntArray(10);
    public int cachedAccessibilityStreamVolume = -1;
    public int cachedAccessibilityStreamMaxVolume = -1;
    public int currentStream = -1;
    public final VolumeHandler handler = new VolumeHandler(this);
    public final SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = new SelectToSpeakJob.AnonymousClass1(this, 2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class VolumeHandler extends WeakReferenceHandler {
        public VolumeHandler(VolumeMonitor volumeMonitor) {
            super(volumeMonitor);
        }

        public final void clearReleaseControl() {
            removeMessages(2);
            removeMessages(3);
            removeMessages(5);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            String announcementForStreamType$ar$ds;
            VolumeMonitor volumeMonitor = (VolumeMonitor) obj;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5 && volumeMonitor.hintA11yVolumeControl) {
                                int a11yVolumeControlHintTimes = volumeMonitor.getA11yVolumeControlHintTimes();
                                if (a11yVolumeControlHintTimes >= 3) {
                                    volumeMonitor.hintA11yVolumeControl = false;
                                    return;
                                }
                                CallStateMonitor callStateMonitor = volumeMonitor.callStateMonitor;
                                if (callStateMonitor == null || callStateMonitor.getCurrentCallState() == 0) {
                                    volumeMonitor.increaseAndStoreA11yVolumeControlHintTimes(a11yVolumeControlHintTimes);
                                    Feedback.Speech.Builder builder = Feedback.Speech.builder();
                                    builder.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
                                    builder.text = " ";
                                    SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                                    speakOptions.mFlags = 6;
                                    builder.hintSpeakOptions = speakOptions;
                                    builder.hint = volumeMonitor.context.getString(R.string.hint_a11y_volume_control);
                                    Feedback.Part.Builder builder2 = Feedback.Part.builder();
                                    builder2.speech = builder.build();
                                    Pipeline.FeedbackReturner feedbackReturner = volumeMonitor.pipeline;
                                    Logger logger = Performance.DEFAULT_LOGGER;
                                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder2);
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        int i2 = message.arg1;
                        boolean booleanValue = ((Boolean) message.obj).booleanValue();
                        removeMessages(5);
                        if (i2 == 3) {
                            if (booleanValue) {
                                announcementForStreamType$ar$ds = volumeMonitor.context.getString(R.string.template_stream_muted_set, volumeMonitor.getStreamName(3));
                            } else {
                                announcementForStreamType$ar$ds = volumeMonitor.getAnnouncementForStreamType$ar$ds(3);
                            }
                            if (!volumeMonitor.shouldAnnounceStream(3)) {
                                volumeMonitor.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(volumeMonitor, 3));
                                return;
                            }
                            SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = volumeMonitor.utteranceCompleteRunnable;
                            Logger logger2 = Performance.DEFAULT_LOGGER;
                            volumeMonitor.speakWithCompletion$ar$ds(announcementForStreamType$ar$ds, 3, utteranceCompleteRunnable);
                            return;
                        }
                        return;
                    }
                    volumeMonitor.handler.clearReleaseControl();
                    int i3 = volumeMonitor.currentStream;
                    if (i3 >= 0) {
                        LogUtils.v("VolumeMonitor", "Released control of stream %d", Integer.valueOf(i3));
                        if (!volumeMonitor.shouldAnnounceStream(i3)) {
                            volumeMonitor.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(volumeMonitor, 4));
                            return;
                        }
                        String announcementForStreamType$ar$ds2 = volumeMonitor.getAnnouncementForStreamType$ar$ds(i3);
                        Logger logger3 = Performance.DEFAULT_LOGGER;
                        volumeMonitor.speakWithCompletion$ar$ds(announcementForStreamType$ar$ds2, i3, volumeMonitor.utteranceCompleteRunnable);
                        return;
                    }
                    return;
                }
                LogUtils.v("VolumeMonitor", "Acquired control of stream %d", Integer.valueOf(message.arg1));
                volumeMonitor.handler.releaseControlDelayed();
                return;
            }
            Integer num = (Integer) message.obj;
            int i4 = message.arg1;
            int i5 = message.arg2;
            int intValue = num.intValue();
            if (volumeMonitor.selfAdjustments.indexOfKey(intValue) >= 0 && volumeMonitor.selfAdjustments.get(intValue) == i4) {
                volumeMonitor.selfAdjustments.put(intValue, -1);
                return;
            }
            if (FeatureSupport.hasAccessibilityAudioStream(volumeMonitor.context) && intValue == 10) {
                if (volumeMonitor.cachedAccessibilityStreamVolume != i4) {
                    volumeMonitor.cacheAccessibilityStreamVolume();
                    SpannableUtils$IdentifierSpan.putIntPref(SpannableUtils$IdentifierSpan.getSharedPreferences(volumeMonitor.context), volumeMonitor.context.getResources(), R.string.pref_a11y_volume_key, i4);
                }
                intValue = 10;
            }
            if (volumeMonitor.currentStream < 0) {
                volumeMonitor.currentStream = intValue;
                AudioManagerCompatUtils.forceVolumeControlStream(volumeMonitor.audioManager, intValue);
                VolumeHandler volumeHandler = volumeMonitor.handler;
                volumeHandler.removeMessages(2);
                volumeHandler.removeMessages(3);
                volumeHandler.sendMessageDelayed(volumeHandler.obtainMessage(2, intValue, 0), 1000L);
                return;
            }
            if (i4 != i5) {
                volumeMonitor.handler.releaseControlDelayed();
            }
        }

        public final void releaseControlDelayed() {
            clearReleaseControl();
            sendMessageDelayed(obtainMessage(3), 2000L);
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        STREAM_NAMES = sparseIntArray;
        sparseIntArray.put(0, R.string.value_stream_voice_call);
        sparseIntArray.put(1, R.string.value_stream_system);
        sparseIntArray.put(2, R.string.value_stream_ring);
        sparseIntArray.put(3, R.string.value_stream_music);
        sparseIntArray.put(4, R.string.value_stream_alarm);
        sparseIntArray.put(5, R.string.value_stream_notification);
        sparseIntArray.put(8, R.string.value_stream_dtmf);
        sparseIntArray.put(10, R.string.value_stream_accessibility);
    }

    public VolumeMonitor(Pipeline.FeedbackReturner feedbackReturner, Context context, CallStateMonitor callStateMonitor) {
        this.hintA11yVolumeControl = true;
        if (feedbackReturner != null) {
            this.context = context;
            this.pipeline = feedbackReturner;
            this.audioManager = (AudioManager) context.getSystemService("audio");
            this.callStateMonitor = callStateMonitor;
            this.hintA11yVolumeControl = FeatureSupport.hasAccessibilityAudioStream(context);
            return;
        }
        throw new IllegalStateException();
    }

    public final void cacheAccessibilityStreamVolume() {
        this.cachedAccessibilityStreamVolume = this.audioManager.getStreamVolume(10);
        this.cachedAccessibilityStreamMaxVolume = this.audioManager.getStreamMaxVolume(10);
    }

    public final int getA11yVolumeControlHintTimes() {
        int i = SpannableUtils$IdentifierSpan.getSharedPreferences(this.context).getInt(this.context.getResources().getString(R.string.pref_a11y_volume_control_hint_times), 0);
        if (i >= 3) {
            this.hintA11yVolumeControl = false;
        }
        return i;
    }

    public final String getAnnouncementForStreamType$ar$ds(int i) {
        int i2;
        int i3;
        if (i == 2) {
            int ringerMode = this.audioManager.getRingerMode();
            if (ringerMode != 0) {
                if (ringerMode != 1) {
                    i = 2;
                } else {
                    return this.context.getString(R.string.value_ringer_vibrate);
                }
            } else {
                return this.context.getString(R.string.value_ringer_silent);
            }
        }
        String streamName = getStreamName(i);
        if (STREAM_NAMES.get(i) > 0) {
            i2 = this.audioManager.getStreamMinVolume(i);
        } else {
            i2 = 0;
        }
        int streamMaxVolume = this.audioManager.getStreamMaxVolume(i) - i2;
        int streamVolume = this.audioManager.getStreamVolume(i) - i2;
        if (streamMaxVolume != 0) {
            i3 = Math.round((streamVolume * 100.0f) / streamMaxVolume);
            if (i3 >= 0) {
                if (i3 > 100) {
                    i3 = 100;
                }
                return this.context.getString(R.string.template_stream_volume_set, streamName, Integer.valueOf(i3));
            }
        } else {
            LogUtils.e("VolumeMonitor", "Volume of stream-type:%d incorrect", Integer.valueOf(i));
        }
        i3 = 0;
        return this.context.getString(R.string.template_stream_volume_set, streamName, Integer.valueOf(i3));
    }

    public final String getStreamName(int i) {
        int i2;
        if (!FormFactorUtils.getInstance().isAndroidTv && (i2 = STREAM_NAMES.get(i)) > 0) {
            return this.context.getString(i2);
        }
        return "";
    }

    public final void increaseAndStoreA11yVolumeControlHintTimes(int i) {
        int i2 = i + 1;
        SpannableUtils$IdentifierSpan.putIntPref(SpannableUtils$IdentifierSpan.getSharedPreferences(this.context), this.context.getResources(), R.string.pref_a11y_volume_control_hint_times, i2);
        if (i2 >= 3) {
            this.hintA11yVolumeControl = false;
        }
    }

    @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
    public final void onReceiveIntent(Intent intent) {
        String action = intent.getAction();
        if ("android.media.VOLUME_CHANGED_ACTION".equals(action)) {
            int intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE_ALIAS", intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1));
            int intExtra2 = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1);
            int intExtra3 = intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", -1);
            if (intExtra >= 0 && intExtra2 >= 0 && intExtra3 >= 0) {
                this.handler.obtainMessage(1, intExtra2, intExtra3, Integer.valueOf(intExtra)).sendToTarget();
                return;
            }
            return;
        }
        if ("android.media.STREAM_MUTE_CHANGED_ACTION".equals(action)) {
            this.handler.obtainMessage(4, intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1), 0, Boolean.valueOf(intent.getBooleanExtra("android.media.EXTRA_STREAM_VOLUME_MUTED", false))).sendToTarget();
        }
    }

    public final void releaseControl() {
        this.currentStream = -1;
        AudioManagerCompatUtils.forceVolumeControlStream(this.audioManager, -1);
    }

    public final boolean shouldAnnounceStream(int i) {
        if (i == 0 || (i == 3 && this.audioManager.isMusicActive())) {
            return false;
        }
        return true;
    }

    public final void speakWithCompletion$ar$ds(String str, int i, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable) {
        int a11yVolumeControlHintTimes;
        CallStateMonitor callStateMonitor = this.callStateMonitor;
        if (callStateMonitor != null && callStateMonitor.getCurrentCallState() != 0) {
            this.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 2));
            return;
        }
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 0;
        speakOptions.mFlags = 8196;
        speakOptions.mUtteranceGroup = 0;
        speakOptions.mCompletedAction = utteranceCompleteRunnable;
        Feedback.Speech.Builder builder = Feedback.Speech.builder();
        builder.setAction$ar$ds$c7b78277_0(Feedback.Speech.Action.SPEAK);
        builder.text = str;
        builder.options = speakOptions;
        if (this.hintA11yVolumeControl && (a11yVolumeControlHintTimes = getA11yVolumeControlHintTimes()) < 3 && i != 10) {
            increaseAndStoreA11yVolumeControlHintTimes(a11yVolumeControlHintTimes);
            SpeechController.SpeakOptions speakOptions2 = new SpeechController.SpeakOptions();
            speakOptions2.mFlags = 6;
            builder.hintSpeakOptions = speakOptions2;
            builder.hint = this.context.getString(R.string.hint_a11y_volume_control);
        }
        Feedback.Part.Builder builder2 = Feedback.Part.builder();
        builder2.speech = builder.build();
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, builder2);
    }
}
