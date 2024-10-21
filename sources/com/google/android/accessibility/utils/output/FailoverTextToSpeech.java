package com.google.android.accessibility.utils.output;

import android.content.ComponentCallbacks;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.talkback.Interpreters$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver;
import com.google.android.accessibility.utils.compat.CompatUtils;
import com.google.android.accessibility.utils.compat.speech.tts.TextToSpeechCompatUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailoverTextToSpeech {
    private static final Locale PREFERRED_FALLBACK_LOCALE = Locale.US;
    private final boolean cacheTtsLocale;
    public Locale cachedTtsLocale;
    public final List listeners;
    public Locale localeInUse;
    private final AudioManager mAudioManager;
    public final ComponentCallbacks mComponentCallbacks;
    public final Context mContext;
    public Locale mDefaultLocale;
    public float mDefaultPitch;
    public float mDefaultRate;
    public String mDefaultTtsEngine;
    public final SpeechHandler mHandler;
    private final LinkedList mInstalledTtsEngines;
    public Locale mLastUtteranceLocale;
    public final ContentObserver mLocaleObserver;
    public final MediaMountStateMonitor mMediaStateMonitor;
    public final ContentObserver mPitchObserver;
    public final ContentObserver mRateObserver;
    public LinkedList mRecentUtteranceIds;
    public final ContentResolver mResolver;
    public boolean mShouldHandleTtsCallbackInMainThread;
    public final ContentObserver mSynthObserver;
    public Locale mSystemLocale;
    public String mSystemTtsEngine;
    private final TelephonyManager mTelephonyManager;
    public TextToSpeech mTempTts;
    public String mTempTtsEngine;
    public TextToSpeech mTts;
    private final TextToSpeech.OnInitListener mTtsChangeListener;
    public String mTtsEngine;
    private int mTtsFailures;
    public final UtteranceProgressListener mUtteranceProgressListener;
    public PowerManager.WakeLock mWakeLock;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FailoverTtsListener {
        void onBeforeUtteranceRequested(String str, UtteranceInfoCombo utteranceInfoCombo);

        void onTtsInitialized(boolean z, String str);

        void onUtteranceCompleted(String str, boolean z);

        void onUtteranceRangeStarted(String str, int i, int i2);

        void onUtteranceStarted(String str);

        void onUtteranceStarted(String str, long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MediaMountStateMonitor extends SameThreadBroadcastReceiver {
        public final IntentFilter mMediaIntentFilter;

        public MediaMountStateMonitor() {
            IntentFilter intentFilter = new IntentFilter();
            this.mMediaIntentFilter = intentFilter;
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addDataScheme("file");
        }

        @Override // com.google.android.accessibility.utils.broadcast.SameThreadBroadcastReceiver
        public final void onReceiveIntent(Intent intent) {
            FailoverTextToSpeech.this.mHandler.obtainMessage(4, intent.getAction()).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SpeechHandler extends WeakReferenceHandler {
        public SpeechHandler(FailoverTextToSpeech failoverTextToSpeech) {
            super(failoverTextToSpeech);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            boolean z;
            FailoverTextToSpeech failoverTextToSpeech = (FailoverTextToSpeech) obj;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i == 5) {
                                failoverTextToSpeech.handleUtteranceRangeStarted((String) message.obj, message.arg1, message.arg2);
                                return;
                            }
                            return;
                        }
                        String str = (String) message.obj;
                        if ("android.intent.action.MEDIA_UNMOUNTED".equals(str) && !TextUtils.equals(failoverTextToSpeech.mSystemTtsEngine, failoverTextToSpeech.mTtsEngine)) {
                            LogUtils.v("FailoverTextToSpeech", "Saw media unmount", new Object[0]);
                            failoverTextToSpeech.setTtsEngine(failoverTextToSpeech.mSystemTtsEngine, true);
                        }
                        if ("android.intent.action.MEDIA_MOUNTED".equals(str) && !TextUtils.equals(failoverTextToSpeech.mDefaultTtsEngine, failoverTextToSpeech.mTtsEngine)) {
                            LogUtils.v("FailoverTextToSpeech", "Saw media mount", new Object[0]);
                            failoverTextToSpeech.setTtsEngine(failoverTextToSpeech.mDefaultTtsEngine, true);
                            return;
                        }
                        return;
                    }
                    Pair pair = (Pair) message.obj;
                    failoverTextToSpeech.handleUtteranceCompleted((String) pair.first, ((Boolean) pair.second).booleanValue());
                    return;
                }
                failoverTextToSpeech.handleUtteranceStarted((String) message.obj, SystemClock.uptimeMillis() - message.getWhen());
                return;
            }
            int i2 = message.arg1;
            TextToSpeech textToSpeech = failoverTextToSpeech.mTempTts;
            if (textToSpeech == null) {
                LogUtils.e("FailoverTextToSpeech", "Attempted to initialize TTS more than once!", new Object[0]);
                return;
            }
            String str2 = failoverTextToSpeech.mTempTtsEngine;
            failoverTextToSpeech.mTempTts = null;
            failoverTextToSpeech.mTempTtsEngine = null;
            failoverTextToSpeech.cachedTtsLocale = null;
            if (i2 != 0) {
                failoverTextToSpeech.attemptTtsFailover(str2);
                return;
            }
            TextToSpeech textToSpeech2 = failoverTextToSpeech.mTts;
            if (textToSpeech2 != null) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                SpannableUtils$NonCopyableTextSpan.attemptTtsShutdown(textToSpeech2);
            }
            failoverTextToSpeech.mTts = textToSpeech;
            failoverTextToSpeech.mTts.setOnUtteranceProgressListener(failoverTextToSpeech.mUtteranceProgressListener);
            if (str2 == null) {
                failoverTextToSpeech.mTtsEngine = (String) CompatUtils.invoke(failoverTextToSpeech.mTts, null, TextToSpeechCompatUtils.METHOD_getCurrentEngine, new Object[0]);
            } else {
                failoverTextToSpeech.mTtsEngine = str2;
            }
            failoverTextToSpeech.updateDefaultLocale();
            failoverTextToSpeech.mTts.setAudioAttributes(new AudioAttributes.Builder().setUsage(11).setFlags(256).build());
            LogUtils.i("FailoverTextToSpeech", "Switched to TTS engine: %s", str2);
            Iterator it = failoverTextToSpeech.listeners.iterator();
            while (it.hasNext()) {
                ((FailoverTtsListener) it.next()).onTtsInitialized(z, str2);
            }
        }

        public final void onUtteranceCompleted(String str, boolean z) {
            obtainMessage(3, Pair.create(str, Boolean.valueOf(z))).sendToTarget();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UtteranceInfoCombo {
        private final boolean flushGlobalTtsQueue;
        private final boolean isAggressiveChunking;
        private final boolean isLocaleAttached;
        private final boolean isSeparatorInUtterance;
        private final Locale locale;
        private final CharSequence text;

        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class Builder {
            public boolean flushGlobalTtsQueue;
            public boolean isAggressiveChunking;
            public boolean isLocaleAttached;
            public boolean isSeparatorInUtterance;
            public Locale locale;
            public byte set$0;
            public CharSequence text;

            public Builder() {
            }

            public final void setFlushGlobalTtsQueue$ar$ds(boolean z) {
                this.flushGlobalTtsQueue = z;
                this.set$0 = (byte) (this.set$0 | 8);
            }

            public final void setIsAggressiveChunking$ar$ds(boolean z) {
                this.isAggressiveChunking = z;
                this.set$0 = (byte) (this.set$0 | 4);
            }

            public final void setIsSeparatorInUtterance$ar$ds(boolean z) {
                this.isSeparatorInUtterance = z;
                this.set$0 = (byte) (this.set$0 | 2);
            }

            public Builder(byte[] bArr) {
                this();
            }
        }

        public UtteranceInfoCombo() {
        }

        public final boolean equals(Object obj) {
            Locale locale;
            if (obj == this) {
                return true;
            }
            if (obj instanceof UtteranceInfoCombo) {
                UtteranceInfoCombo utteranceInfoCombo = (UtteranceInfoCombo) obj;
                if (this.text.equals(utteranceInfoCombo.text()) && ((locale = this.locale) != null ? locale.equals(utteranceInfoCombo.locale()) : utteranceInfoCombo.locale() == null) && this.isLocaleAttached == utteranceInfoCombo.isLocaleAttached() && this.isSeparatorInUtterance == utteranceInfoCombo.isSeparatorInUtterance() && this.isAggressiveChunking == utteranceInfoCombo.isAggressiveChunking() && this.flushGlobalTtsQueue == utteranceInfoCombo.flushGlobalTtsQueue()) {
                    return true;
                }
            }
            return false;
        }

        public final boolean flushGlobalTtsQueue() {
            return this.flushGlobalTtsQueue;
        }

        public final int hashCode() {
            int hashCode;
            int i;
            int i2;
            int i3;
            int hashCode2 = this.text.hashCode() ^ 1000003;
            Locale locale = this.locale;
            if (locale == null) {
                hashCode = 0;
            } else {
                hashCode = locale.hashCode();
            }
            int i4 = ((hashCode2 * 1000003) ^ hashCode) * 1000003;
            int i5 = 1237;
            if (true != this.isLocaleAttached) {
                i = 1237;
            } else {
                i = 1231;
            }
            int i6 = (i4 ^ i) * 1000003;
            if (true != this.isSeparatorInUtterance) {
                i2 = 1237;
            } else {
                i2 = 1231;
            }
            int i7 = (i6 ^ i2) * 1000003;
            if (true != this.isAggressiveChunking) {
                i3 = 1237;
            } else {
                i3 = 1231;
            }
            int i8 = (i7 ^ i3) * 1000003;
            if (true == this.flushGlobalTtsQueue) {
                i5 = 1231;
            }
            return i8 ^ i5;
        }

        public final boolean isAggressiveChunking() {
            return this.isAggressiveChunking;
        }

        public final boolean isLocaleAttached() {
            return this.isLocaleAttached;
        }

        public final boolean isSeparatorInUtterance() {
            return this.isSeparatorInUtterance;
        }

        public final Locale locale() {
            return this.locale;
        }

        public final CharSequence text() {
            return this.text;
        }

        public final String toString() {
            Locale locale = this.locale;
            return "UtteranceInfoCombo{text=" + String.valueOf(this.text) + ", locale=" + String.valueOf(locale) + ", isLocaleAttached=" + this.isLocaleAttached + ", isSeparatorInUtterance=" + this.isSeparatorInUtterance + ", isAggressiveChunking=" + this.isAggressiveChunking + ", flushGlobalTtsQueue=" + this.flushGlobalTtsQueue + "}";
        }

        public UtteranceInfoCombo(CharSequence charSequence, Locale locale, boolean z, boolean z2, boolean z3, boolean z4) {
            this();
            this.text = charSequence;
            this.locale = locale;
            this.isLocaleAttached = z;
            this.isSeparatorInUtterance = z2;
            this.isAggressiveChunking = z3;
            this.flushGlobalTtsQueue = z4;
        }
    }

    public FailoverTextToSpeech(Context context, boolean z) {
        MediaMountStateMonitor mediaMountStateMonitor = new MediaMountStateMonitor();
        this.mMediaStateMonitor = mediaMountStateMonitor;
        this.mInstalledTtsEngines = new LinkedList();
        this.listeners = new ArrayList();
        this.mShouldHandleTtsCallbackInMainThread = true;
        this.mRecentUtteranceIds = new LinkedList();
        this.mSystemLocale = Locale.getDefault();
        this.mDefaultLocale = null;
        this.mLastUtteranceLocale = null;
        this.localeInUse = null;
        SpeechHandler speechHandler = new SpeechHandler(this);
        this.mHandler = speechHandler;
        ContentObserver contentObserver = new ContentObserver(speechHandler) { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                FailoverTextToSpeech.this.updateDefaultEngine();
            }
        };
        this.mSynthObserver = contentObserver;
        ContentObserver contentObserver2 = new ContentObserver(speechHandler) { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.2
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                FailoverTextToSpeech.this.updateDefaultPitch();
            }
        };
        this.mPitchObserver = contentObserver2;
        ContentObserver contentObserver3 = new ContentObserver(speechHandler) { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.3
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                FailoverTextToSpeech.this.updateDefaultRate();
            }
        };
        this.mRateObserver = contentObserver3;
        ContentObserver contentObserver4 = new ContentObserver(speechHandler) { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.4
            @Override // android.database.ContentObserver
            public final void onChange(boolean z2) {
                FailoverTextToSpeech.this.updateDefaultLocale();
            }
        };
        this.mLocaleObserver = contentObserver4;
        this.mUtteranceProgressListener = new UtteranceProgressListener() { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.5
            private String mLastUpdatedUtteranceId = null;

            private final void handleUtteranceCompleted(String str, boolean z2) {
                LogUtils.d("FailoverTextToSpeech", "Received callback for \"%s\", returned %b", str, Boolean.valueOf(z2));
                FailoverTextToSpeech failoverTextToSpeech = FailoverTextToSpeech.this;
                if (failoverTextToSpeech.mShouldHandleTtsCallbackInMainThread) {
                    failoverTextToSpeech.mHandler.onUtteranceCompleted(str, z2);
                } else {
                    failoverTextToSpeech.handleUtteranceCompleted(str, z2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onAudioAvailable(String str, byte[] bArr) {
                Performance.EventId eventId;
                Performance.EventData recentEvent;
                long j;
                if (str != null && !str.equals(this.mLastUpdatedUtteranceId)) {
                    Performance performance = Performance.instance;
                    if (performance.trackEvents()) {
                        synchronized (performance.lockRecentEvents) {
                            eventId = (Performance.EventId) performance.utteranceToEvent.get(str);
                        }
                        if (eventId != null && (recentEvent = performance.getRecentEvent(eventId)) != null) {
                            if (recentEvent.getTimeFeedbackOutput() <= 0 && recentEvent.getTimeFeedbackQueued() > 0) {
                                long currentTimeMillis = System.currentTimeMillis();
                                recentEvent.setFeedbackOutput(currentTimeMillis);
                                performance.notifyLatencyTracker(new Interpreters$$ExternalSyntheticLambda1(recentEvent, 4));
                                if (performance.computeStatsEnabled) {
                                    long j2 = currentTimeMillis - recentEvent.timeReceivedAtTalkback;
                                    String[] strArr = recentEvent.labels;
                                    int i = 0;
                                    while (i <= 0) {
                                        String str2 = strArr[i];
                                        performance.getOrCreateStatistics(str2, 3).increment(j2);
                                        if (j2 > 1000) {
                                            LogUtils.d("Performance", "Feedback heard latency exceeds %s ms : %s", 1000L, Long.valueOf(j2));
                                        }
                                        long timeFeedbackQueued = currentTimeMillis - recentEvent.getTimeFeedbackQueued();
                                        if (timeFeedbackQueued > 500) {
                                            j = currentTimeMillis;
                                            LogUtils.d("Performance", "TTS latency of %s exceeds %s ms : %s", str, 500L, Long.valueOf(timeFeedbackQueued));
                                        } else {
                                            j = currentTimeMillis;
                                        }
                                        performance.getOrCreateStatistics(str2, 4).increment(timeFeedbackQueued);
                                        i++;
                                        currentTimeMillis = j;
                                    }
                                }
                            }
                            performance.collectMissingLatencies(recentEvent);
                            performance.removeRecentEvent(eventId);
                            synchronized (performance.lockRecentEvents) {
                                performance.utteranceToEvent.remove(str);
                            }
                        }
                    }
                }
                this.mLastUpdatedUtteranceId = str;
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onDone(String str) {
                handleUtteranceCompleted(str, true);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onError(String str) {
                handleUtteranceCompleted(str, false);
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onRangeStart(String str, int i, int i2, int i3) {
                FailoverTextToSpeech failoverTextToSpeech = FailoverTextToSpeech.this;
                if (failoverTextToSpeech.mShouldHandleTtsCallbackInMainThread) {
                    failoverTextToSpeech.mHandler.obtainMessage(5, i, i2, str).sendToTarget();
                } else {
                    failoverTextToSpeech.handleUtteranceRangeStarted(str, i, i2);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onStart(String str) {
                FailoverTextToSpeech failoverTextToSpeech = FailoverTextToSpeech.this;
                if (failoverTextToSpeech.mShouldHandleTtsCallbackInMainThread) {
                    failoverTextToSpeech.mHandler.obtainMessage(2, str).sendToTarget();
                } else {
                    failoverTextToSpeech.handleUtteranceStarted(str, 0L);
                }
            }

            @Override // android.speech.tts.UtteranceProgressListener
            public final void onStop(String str, boolean z2) {
                handleUtteranceCompleted(str, !z2);
            }
        };
        this.mTtsChangeListener = new TextToSpeech.OnInitListener() { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.6
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int i) {
                FailoverTextToSpeech.this.mHandler.obtainMessage(1, i, 0).sendToTarget();
            }
        };
        ComponentCallbacks componentCallbacks = new ComponentCallbacks() { // from class: com.google.android.accessibility.utils.output.FailoverTextToSpeech.7
            @Override // android.content.ComponentCallbacks
            public final void onConfigurationChanged(Configuration configuration) {
                FailoverTextToSpeech failoverTextToSpeech = FailoverTextToSpeech.this;
                Locale locale = configuration.locale;
                if (locale.equals(failoverTextToSpeech.mSystemLocale)) {
                    return;
                }
                failoverTextToSpeech.mSystemLocale = locale;
                failoverTextToSpeech.localeInUse = failoverTextToSpeech.ensureSupportedLocale();
            }

            @Override // android.content.ComponentCallbacks
            public final void onLowMemory() {
            }
        };
        this.mComponentCallbacks = componentCallbacks;
        this.mContext = context;
        EditorInfoCompat.registerReceiver$ar$ds(context, mediaMountStateMonitor, mediaMountStateMonitor.mMediaIntentFilter, 2);
        Uri uriFor = Settings.Secure.getUriFor("tts_default_synth");
        Uri uriFor2 = Settings.Secure.getUriFor("tts_default_pitch");
        Uri uriFor3 = Settings.Secure.getUriFor("tts_default_rate");
        ContentResolver contentResolver = context.getContentResolver();
        this.mResolver = contentResolver;
        contentResolver.registerContentObserver(uriFor, false, contentObserver);
        contentResolver.registerContentObserver(uriFor2, false, contentObserver2);
        contentResolver.registerContentObserver(uriFor3, false, contentObserver3);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor("tts_default_locale"), false, contentObserver4);
        context.registerComponentCallbacks(componentCallbacks);
        updateDefaultPitch();
        updateDefaultRate();
        updateDefaultEngine();
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(536870918, "FailoverTextToSpeech");
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mTelephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.cacheTtsLocale = z;
    }

    private static boolean isNotAvailableStatus(int i) {
        if (i != 0 && i != 1 && i != 2) {
            return true;
        }
        return false;
    }

    private final int setTtsLocale(Locale locale) {
        LogUtils.d("FailoverTextToSpeech", "set ttsLocale: cachedlocale=%s with locale %s", this.cachedTtsLocale, locale);
        Locale locale2 = this.cachedTtsLocale;
        if (locale2 != null && locale2.equals(locale)) {
            return 0;
        }
        int language = this.mTts.setLanguage(locale);
        LogUtils.d("FailoverTextToSpeech", "set ttsLocale: %s with status %s", locale, Integer.valueOf(language));
        if (this.cacheTtsLocale && !isNotAvailableStatus(language)) {
            this.cachedTtsLocale = locale;
        }
        return language;
    }

    public final void addListener(FailoverTtsListener failoverTtsListener) {
        this.listeners.add(failoverTtsListener);
    }

    public final void allowDeviceSleep() {
        allowDeviceSleep(null);
    }

    public final Locale attemptSetLanguage(Locale locale) {
        if (locale == null) {
            LogUtils.w("FailoverTextToSpeech", "Cannot set null locale.", new Object[0]);
            return null;
        }
        if (this.mTts == null) {
            LogUtils.e("FailoverTextToSpeech", "mTts null when setting locale.", new Object[0]);
            return null;
        }
        if (isNotAvailableStatus(setTtsLocale(locale))) {
            LogUtils.e("FailoverTextToSpeech", "Failed to set locale to %s", locale);
            return null;
        }
        LogUtils.v("FailoverTextToSpeech", "attemptSetLanguage- Set locale to %s", locale);
        return locale;
    }

    public final void attemptTtsFailover(String str) {
        LogUtils.logWithLimit$ar$ds(6, this.mTtsFailures, "Attempting TTS failover from %s", str);
        this.mTtsFailures++;
        if (this.mInstalledTtsEngines.size() > 1 && this.mTtsFailures >= 3) {
            if (str != null) {
                this.mInstalledTtsEngines.remove(str);
                this.mInstalledTtsEngines.addLast(str);
            }
            setTtsEngine((String) this.mInstalledTtsEngines.getFirst(), true);
            return;
        }
        setTtsEngine(str, false);
    }

    public final Locale ensureSupportedLocale() {
        TextToSpeech textToSpeech;
        Set<String> features;
        Locale locale;
        char c;
        if ("com.google.android.tts".equals(this.mTtsEngine) && this.mDefaultLocale == null && (textToSpeech = this.mTts) != null && (((features = textToSpeech.getFeatures(this.mSystemLocale)) == null || !features.contains("embeddedTts")) && isNotAvailableStatus(this.mTts.isLanguageAvailable(this.mSystemLocale)))) {
            TextToSpeech textToSpeech2 = this.mTts;
            if (textToSpeech2 == null) {
                locale = null;
            } else {
                locale = PREFERRED_FALLBACK_LOCALE;
                if (textToSpeech2.isLanguageAvailable(locale) < 0) {
                    char c2 = 65535;
                    Locale locale2 = null;
                    for (Locale locale3 : Locale.getAvailableLocales()) {
                        if (!isNotAvailableStatus(this.mTts.isLanguageAvailable(locale3))) {
                            Locale locale4 = this.mSystemLocale;
                            String language = locale4.getLanguage();
                            if (language != null && language.equals(locale3.getLanguage())) {
                                String country = locale4.getCountry();
                                if (country != null && country.equals(locale3.getCountry())) {
                                    String variant = locale4.getVariant();
                                    c = 2;
                                    if (variant != null && variant.equals(locale3.getVariant())) {
                                        c = 3;
                                    }
                                } else {
                                    c = 1;
                                }
                            } else {
                                c = 0;
                            }
                            if (c > c2) {
                                locale2 = locale3;
                                c2 = c;
                            }
                        }
                    }
                    locale = locale2;
                }
            }
            if (locale == null) {
                LogUtils.e("FailoverTextToSpeech", "Failed to find fallback locale", new Object[0]);
                return null;
            }
            LogUtils.v("FailoverTextToSpeech", "Attempt setting fallback TTS locale.", new Object[0]);
            LogUtils.v("FailoverTextToSpeech", "attemptSetFallbackLanguage fallback tts locale.", new Object[0]);
            return attemptSetLanguage(locale);
        }
        if (this.mTts == null) {
            return null;
        }
        this.mLastUtteranceLocale = null;
        Locale locale5 = this.mDefaultLocale;
        if (locale5 == null) {
            locale5 = this.mSystemLocale;
        }
        try {
            if (!isNotAvailableStatus(setTtsLocale(locale5))) {
                LogUtils.i("FailoverTextToSpeech", "Restored TTS locale to %s", locale5);
                return locale5;
            }
        } catch (Exception e) {
            LogUtils.e("FailoverTextToSpeech", "Failed to setLanguage(): %s", e.toString());
        }
        LogUtils.e("FailoverTextToSpeech", "Failed to restore TTS locale to %s", locale5);
        return null;
    }

    public final void handleUtteranceCompleted(String str, boolean z) {
        if (z) {
            this.mTtsFailures = 0;
        }
        allowDeviceSleep(str);
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FailoverTtsListener) it.next()).onUtteranceCompleted(str, z);
        }
    }

    public final void handleUtteranceRangeStarted(String str, int i, int i2) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FailoverTtsListener) it.next()).onUtteranceRangeStarted(str, i, i2);
        }
    }

    public final void handleUtteranceStarted(String str, long j) {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FailoverTtsListener) it.next()).onUtteranceStarted(str, j);
        }
    }

    public final boolean isReady() {
        if (this.mTts != null) {
            return true;
        }
        return false;
    }

    public final void setTtsEngine(String str, boolean z) {
        if (z) {
            this.mTtsFailures = 0;
        }
        SpannableUtils$NonCopyableTextSpan.attemptTtsShutdown(this.mTts);
        SpannableUtils$NonCopyableTextSpan.attemptTtsShutdown(this.mTempTts);
        TextToSpeech textToSpeech = this.mTempTts;
        if (textToSpeech != null && textToSpeech.getLanguage() != null) {
            LogUtils.e("FailoverTextToSpeech", "Can't start TTS engine %s while still loading previous engine", str);
            return;
        }
        LogUtils.i("FailoverTextToSpeech", "Bad TextToSpeech instance detected. Re-creating.", new Object[0]);
        LogUtils.logWithLimit$ar$ds(4, this.mTtsFailures, "Switching to TTS engine: %s", str);
        this.mTempTtsEngine = str;
        this.mTempTts = new TextToSpeech(this.mContext, this.mTtsChangeListener, str);
    }

    public final void stopFromTalkBack() {
        try {
            allowDeviceSleep();
            this.mTts.speak("", 0, null);
        } catch (Exception unused) {
        }
    }

    public final String toLanguageTag(Locale locale) {
        if (locale != null) {
            return locale.toLanguageTag();
        }
        return "null";
    }

    public final void updateDefaultEngine() {
        LinkedList linkedList = this.mInstalledTtsEngines;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        linkedList.clear();
        Iterator<ResolveInfo> it = this.mContext.getPackageManager().queryIntentServices(new Intent("android.intent.action.TTS_SERVICE"), 4).iterator();
        String str = null;
        while (it.hasNext()) {
            LinkedList linkedList2 = this.mInstalledTtsEngines;
            ServiceInfo serviceInfo = it.next().serviceInfo;
            ApplicationInfo applicationInfo = serviceInfo.applicationInfo;
            String str2 = serviceInfo.packageName;
            int i = applicationInfo.flags & 1;
            linkedList2.add(serviceInfo.packageName);
            if (1 == i) {
                str = str2;
            }
        }
        this.mSystemTtsEngine = str;
        String string = Settings.Secure.getString(contentResolver, "tts_default_synth");
        this.mDefaultTtsEngine = string;
        String str3 = this.mTtsEngine;
        if (str3 == null || !str3.equals(string)) {
            if (this.mInstalledTtsEngines.contains(this.mDefaultTtsEngine)) {
                setTtsEngine(this.mDefaultTtsEngine, true);
            } else if (!this.mInstalledTtsEngines.isEmpty()) {
                setTtsEngine((String) this.mInstalledTtsEngines.get(0), true);
            }
        }
    }

    public final void updateDefaultLocale() {
        String str;
        ContentResolver contentResolver = this.mResolver;
        String str2 = this.mTtsEngine;
        String string = Settings.Secure.getString(contentResolver, "tts_default_locale");
        Locale locale = null;
        if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(str2)) {
            String[] split = string.split(",");
            int length = split.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String str3 = split[i];
                int indexOf = str3.indexOf(58);
                if (indexOf > 0 && str2.equals(str3.substring(0, indexOf))) {
                    String substring = str3.substring(indexOf + 1);
                    if (!TextUtils.isEmpty(substring)) {
                        str = substring.replace('_', '-');
                    }
                } else {
                    i++;
                }
            }
        }
        str = null;
        if (!TextUtils.isEmpty(str)) {
            locale = Locale.forLanguageTag(str);
        }
        this.mDefaultLocale = locale;
        this.localeInUse = ensureSupportedLocale();
    }

    public final void updateDefaultPitch() {
        this.mDefaultPitch = Settings.Secure.getInt(this.mResolver, "tts_default_pitch", 100) / 100.0f;
    }

    public final void updateDefaultRate() {
        this.mDefaultRate = Settings.Secure.getInt(this.mResolver, "tts_default_rate", 100) / 100.0f;
    }

    private final void allowDeviceSleep(String str) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        boolean contains = this.mRecentUtteranceIds.contains(str);
        boolean z = false;
        if (!this.mRecentUtteranceIds.isEmpty() && ((String) this.mRecentUtteranceIds.getLast()).equals(str)) {
            z = true;
        }
        if (str == null || z || !contains) {
            try {
                this.mWakeLock.release();
            } catch (RuntimeException unused) {
            }
        }
    }

    public final void ensureQueueFlush() {
    }
}
