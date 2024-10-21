package com.google.android.accessibility.talkback;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.controller.TelevisionNavigationController;
import com.google.android.accessibility.talkback.monitor.CallStateMonitor;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.accessibility.utils.monitor.DisplayMonitor;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RingerModeAndScreenMonitor extends SameThreadBroadcastReceiver implements DisplayMonitor.DisplayStateChangedListener {
    public static final IntentFilter STATE_CHANGE_FILTER;
    public final AudioManager audioManager;
    public final CallStateMonitor callStateMonitor;
    public boolean defaultDisplayOn;
    public final DisplayMonitor displayMonitor;
    private final ExecutorService executor;
    public boolean isInteractive;
    public final boolean isWatch;
    private final ListMenuManager menuManager;
    public final Pipeline.FeedbackReturner pipeline;
    private final TalkBackService.ProximitySensorListener proximitySensorListener;
    public final TalkBackService service;
    public final TelevisionNavigationController televisionNavigationController;
    public final Set openDialogs = new HashSet();
    public int ringerMode = 2;
    public int timeFormat = 0;
    public final List screenChangedListeners = new CopyOnWriteArrayList();
    public final List deviceUnlockedListeners = new CopyOnWriteArrayList();
    public boolean monitoring = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface DeviceUnlockedListener {
        void onDeviceUnlocked();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ScreenChangedListener {
        void onScreenChanged$ar$ds(boolean z);
    }

    static {
        IntentFilter intentFilter = new IntentFilter();
        STATE_CHANGE_FILTER = intentFilter;
        intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
    }

    public RingerModeAndScreenMonitor(ListMenuManager listMenuManager, Pipeline.FeedbackReturner feedbackReturner, TalkBackService.ProximitySensorListener proximitySensorListener, CallStateMonitor callStateMonitor, DisplayMonitor displayMonitor, TalkBackService talkBackService) {
        if (listMenuManager != null) {
            if (feedbackReturner != null) {
                if (proximitySensorListener != null) {
                    this.service = talkBackService;
                    this.menuManager = listMenuManager;
                    this.pipeline = feedbackReturner;
                    this.proximitySensorListener = proximitySensorListener;
                    this.callStateMonitor = callStateMonitor;
                    this.displayMonitor = displayMonitor;
                    this.televisionNavigationController = talkBackService.televisionNavigationController;
                    this.audioManager = (AudioManager) talkBackService.getSystemService("audio");
                    this.isInteractive = ((PowerManager) talkBackService.getSystemService("power")).isInteractive();
                    this.isWatch = FormFactorUtils.getInstance().isAndroidWear;
                    this.executor = Executors.newSingleThreadExecutor();
                    return;
                }
                throw new IllegalStateException();
            }
            throw new IllegalStateException();
        }
        throw new IllegalStateException();
    }

    public static int prefValueToTimeFeedbackFormat(Resources resources, String str) {
        if (TextUtils.equals(str, resources.getString(R.string.pref_time_feedback_format_values_12_hour))) {
            return 1;
        }
        if (TextUtils.equals(str, resources.getString(R.string.pref_time_feedback_format_values_24_hour))) {
            return 2;
        }
        return 0;
    }

    public final void addScreenChangedListener(ScreenChangedListener screenChangedListener) {
        this.screenChangedListeners.add(screenChangedListener);
    }

    public final float getStreamVolume(int i) {
        return this.audioManager.getStreamVolume(i) / this.audioManager.getStreamMaxVolume(i);
    }

    public final boolean isIdle() {
        CallStateMonitor callStateMonitor = this.callStateMonitor;
        if (callStateMonitor != null && callStateMonitor.getCurrentCallState() == 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.monitor.DisplayMonitor.DisplayStateChangedListener
    public final void onDisplayStateChanged(boolean z) {
        boolean z2 = this.defaultDisplayOn;
        this.defaultDisplayOn = z;
        if (z && !z2) {
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (isIdle()) {
                if (Settings.Secure.getInt(this.service.getContentResolver(), "device_provisioned", 0) != 0) {
                    int i = this.timeFormat;
                    int i2 = 5185;
                    if (i != 1 && (i == 2 || DateFormat.is24HourFormat(this.service))) {
                        i2 = 5249;
                    }
                    StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, DateUtils.formatDateTime(this.service, System.currentTimeMillis(), i2));
                } else {
                    spannableStringBuilder.append((CharSequence) this.service.getString(R.string.value_screen_on));
                }
            }
            SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
            speakOptions.mQueueMode = 2;
            speakOptions.mFlags = 4;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.speech(spannableStringBuilder, speakOptions));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
    public final void onReceiveIntent(Intent intent) {
        String action;
        char c;
        if (TalkBackService.isServiceActive() && (action = intent.getAction()) != null) {
            Logger logger = Performance.DEFAULT_LOGGER;
            int i = 1;
            switch (action.hashCode()) {
                case -2128145023:
                    if (action.equals("android.intent.action.SCREEN_OFF")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1454123155:
                    if (action.equals("android.intent.action.SCREEN_ON")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 823795052:
                    if (action.equals("android.intent.action.USER_PRESENT")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 2070024785:
                    if (action.equals("android.media.RINGER_MODE_CHANGED")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c != 0) {
                if (c != 1) {
                    if (c != 2) {
                        if (c == 3) {
                            if (isIdle()) {
                                if (this.isWatch) {
                                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.volume_beep));
                                } else {
                                    String string = this.service.getString(R.string.value_device_unlocked);
                                    SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                                    speakOptions.mQueueMode = 3;
                                    speakOptions.mFlags = 256;
                                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.speech(string, speakOptions));
                                }
                            }
                            Iterator it = this.deviceUnlockedListeners.iterator();
                            while (it.hasNext()) {
                                ((DeviceUnlockedListener) it.next()).onDeviceUnlocked();
                            }
                            return;
                        }
                        return;
                    }
                    this.isInteractive = false;
                    this.proximitySensorListener.setScreenIsOn(false);
                    this.menuManager.dismissAll();
                    ArrayList arrayList = new ArrayList(this.openDialogs);
                    int size = arrayList.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((DialogInterface) arrayList.get(i2)).cancel();
                    }
                    this.openDialogs.clear();
                    for (ScreenChangedListener screenChangedListener : this.screenChangedListeners) {
                        if (screenChangedListener != null) {
                            screenChangedListener.onScreenChanged$ar$ds(this.isInteractive);
                        }
                    }
                    this.executor.execute(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, i));
                    return;
                }
                this.isInteractive = true;
                this.proximitySensorListener.setScreenIsOn(true);
                for (ScreenChangedListener screenChangedListener2 : this.screenChangedListeners) {
                    if (screenChangedListener2 != null) {
                        screenChangedListener2.onScreenChanged$ar$ds(this.isInteractive);
                    }
                }
                return;
            }
            this.ringerMode = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2);
        }
    }
}
