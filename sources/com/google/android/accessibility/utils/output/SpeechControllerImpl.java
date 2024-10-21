package com.google.android.accessibility.utils.output;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.text.BreakIterator;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.speech.tts.Voice;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.LocaleSpan;
import android.text.style.ReplacementSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.util.Range;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.braille.BrailleUnicode;
import com.google.android.accessibility.utils.output.FailoverTextToSpeech;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechControllerImpl implements SpeechController {
    public int capLetterFeedback;
    public FeedbackFragmentsIterator currentFragmentIterator;
    public ArrayList feedbackQueue;
    public long feedbackSavedTime;
    public boolean isMuteSpeech;
    private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener;
    public final AudioFocusRequest mAudioFocusRequest;
    public final AudioManager mAudioManager;
    public final Context mContext;
    public FeedbackItem mCurrentFeedbackItem;
    public final SpeechController.Delegate mDelegate;
    public final FailoverTextToSpeech mFailoverTts;
    private final FeedbackController mFeedbackController;
    public final LinkedList mFeedbackHistory;
    public SpeechController.UtteranceCompleteRunnable mFullScreenReadNextCallback;
    public final Handler mHandler;
    public boolean mInjectFullScreenReadCallbacks;
    public boolean mIsSpeaking;
    public int mNextUtteranceIndex;
    public final Set mObservers;
    public boolean mShouldHandleTtsCallBackInMainThread;
    public boolean mSkipNextTTSChangeAnnouncement;
    private final HashMap mSpeechParametersMap;
    public float mSpeechPitch;
    public float mSpeechRate;
    public float mSpeechVolume;
    private TextToSpeechOverlay mTtsOverlay;
    public boolean mUseAudioFocus;
    public boolean mUseIntonation;
    public boolean mUsePunctuation;
    public final PriorityQueue mUtteranceCompleteActions;
    public final HashMap mUtteranceRangeStartCallbacks;
    public final PriorityQueue mUtteranceStartActions;
    public int punctuationVerbosity;
    private final boolean removeUnnecessarySpans;
    public boolean requestPause;
    public FeedbackItem savedFeedbackItem;
    public ArrayList savedFeedbackQueue;
    public FeedbackFragmentsIterator savedFragmentIterator;
    public FeedbackItem savedUtterance;
    public boolean shouldSilentSpeech;
    public boolean sourceIsVolumeControl;
    public final FloatingActionButton.ShadowDelegateImpl state$ar$class_merging$b33be634_0$ar$class_merging$ar$class_merging$ar$class_merging;
    public boolean ttsChangeAnnouncementEnabled;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.output.SpeechControllerImpl$4, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements Runnable {
        final /* synthetic */ Object SpeechControllerImpl$4$ar$val$callback$ar$class_merging$fe7d60de_0;
        private final /* synthetic */ int switching_field;
        final /* synthetic */ int val$end;
        final /* synthetic */ int val$start;

        public AnonymousClass4(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, int i, int i2, int i3) {
            this.switching_field = i3;
            this.SpeechControllerImpl$4$ar$val$callback$ar$class_merging$fe7d60de_0 = hapticPatternParser$$ExternalSyntheticLambda1;
            this.val$start = i;
            this.val$end = i2;
        }

        /* JADX WARN: Type inference failed for: r1v7, types: [android.content.SharedPreferences, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r3v2, types: [android.content.SharedPreferences, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r5v2, types: [android.content.SharedPreferences, java.lang.Object] */
        @Override // java.lang.Runnable
        public final void run() {
            if (this.switching_field != 0) {
                ReentrantReadWriteLock reentrantReadWriteLock = SelectToSpeakLogSender.lock;
                reentrantReadWriteLock.writeLock().lock();
                int i = this.val$start;
                int i2 = this.val$end;
                Object obj = this.SpeechControllerImpl$4$ar$val$callback$ar$class_merging$fe7d60de_0;
                try {
                    String stringGeneratedd7c9859b585c301d = A11yS2SProtoEnums$A11yS2SEntryPoint.toStringGeneratedd7c9859b585c301d(i2);
                    if (i2 != 0) {
                        String usageKey$ar$edu = SelectToSpeakLogSender.getUsageKey$ar$edu(i2);
                        int i3 = ((OptionalMethod) obj).OptionalMethod$ar$returnType.getInt(stringGeneratedd7c9859b585c301d, 0) + i;
                        int i4 = ((OptionalMethod) obj).OptionalMethod$ar$returnType.getInt(usageKey$ar$edu, 0) + 1;
                        SharedPreferences.Editor edit = ((OptionalMethod) obj).OptionalMethod$ar$returnType.edit();
                        edit.putInt(usageKey$ar$edu, i4);
                        edit.putInt(stringGeneratedd7c9859b585c301d, i3);
                        edit.commit();
                        return;
                    }
                    throw null;
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            ((HapticPatternParser$$ExternalSyntheticLambda1) this.SpeechControllerImpl$4$ar$val$callback$ar$class_merging$fe7d60de_0).onUtteranceRangeStarted(this.val$start, this.val$end);
        }

        public /* synthetic */ AnonymousClass4(OptionalMethod optionalMethod, int i, int i2, int i3) {
            this.switching_field = i3;
            this.SpeechControllerImpl$4$ar$val$callback$ar$class_merging$fe7d60de_0 = optionalMethod;
            this.val$end = i;
            this.val$start = i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackItemDisjunctionPredicateSet implements FeedbackItemPredicate {
        private final FeedbackItemPredicate mPredicate1;
        private final FeedbackItemPredicate mPredicate2;
        private final /* synthetic */ int switching_field;

        public FeedbackItemDisjunctionPredicateSet(FeedbackItemPredicate feedbackItemPredicate, FeedbackItemPredicate feedbackItemPredicate2, int i) {
            this.switching_field = i;
            this.mPredicate1 = feedbackItemPredicate;
            this.mPredicate2 = feedbackItemPredicate2;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechControllerImpl.FeedbackItemPredicate
        public final boolean accept(FeedbackItem feedbackItem) {
            if (this.switching_field != 0) {
                if (!this.mPredicate1.accept(feedbackItem) || !this.mPredicate2.accept(feedbackItem)) {
                    return false;
                }
                return true;
            }
            if (!this.mPredicate1.accept(feedbackItem) && !this.mPredicate2.accept(feedbackItem)) {
                return false;
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackItemEqualSamplePredicate implements FeedbackItemPredicate {
        private final boolean mEqual;
        private final FeedbackItem mSample;

        public FeedbackItemEqualSamplePredicate(FeedbackItem feedbackItem, boolean z) {
            this.mSample = feedbackItem;
            this.mEqual = z;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechControllerImpl.FeedbackItemPredicate
        public final boolean accept(FeedbackItem feedbackItem) {
            if (this.mEqual) {
                if (this.mSample == feedbackItem) {
                    return true;
                }
                return false;
            }
            if (this.mSample != feedbackItem) {
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackItemInterruptiblePredicate implements FeedbackItemPredicate {
        @Override // com.google.android.accessibility.utils.output.SpeechControllerImpl.FeedbackItemPredicate
        public final boolean accept(FeedbackItem feedbackItem) {
            if (feedbackItem == null || feedbackItem.mIsUninterruptible) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface FeedbackItemPredicate {
        boolean accept(FeedbackItem feedbackItem);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FeedbackItemUtteranceGroupPredicate implements FeedbackItemPredicate {
        private final int mUtteranceGroup;

        public FeedbackItemUtteranceGroupPredicate(int i) {
            this.mUtteranceGroup = i;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechControllerImpl.FeedbackItemPredicate
        public final boolean accept(FeedbackItem feedbackItem) {
            if (feedbackItem == null) {
                return false;
            }
            if (feedbackItem.mUtteranceGroup != this.mUtteranceGroup) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class UtteranceCompleteAction implements Comparable {
        public final SpeechController.UtteranceCompleteRunnable runnable;
        public final int utteranceGroup;
        public int utteranceIndex;

        public UtteranceCompleteAction(int i, int i2, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable) {
            this.utteranceIndex = i;
            this.runnable = utteranceCompleteRunnable;
            this.utteranceGroup = i2;
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return this.utteranceIndex - ((UtteranceCompleteAction) obj).utteranceIndex;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class UtteranceStartAction implements Comparable {
        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            throw null;
        }
    }

    public SpeechControllerImpl(Context context, SpeechController.Delegate delegate) {
        this(context, delegate, null, new FailoverTextToSpeech(context, true), false);
    }

    private static final boolean feedbackTextEquals$ar$ds(FeedbackItem feedbackItem, FeedbackItem feedbackItem2) {
        if (feedbackItem2 == null) {
            return false;
        }
        List fragments = feedbackItem.getFragments();
        List fragments2 = feedbackItem2.getFragments();
        if (fragments.size() != fragments2.size()) {
            return false;
        }
        int size = fragments.size();
        for (int i = 0; i < size; i++) {
            FeedbackFragment feedbackFragment = (FeedbackFragment) fragments.get(i);
            FeedbackFragment feedbackFragment2 = (FeedbackFragment) fragments2.get(i);
            if (feedbackFragment != null && feedbackFragment2 != null && !TextUtils.equals(feedbackFragment.mText, feedbackFragment2.mText)) {
                return false;
            }
            if ((feedbackFragment == null && feedbackFragment2 != null) || (feedbackFragment != null && feedbackFragment2 == null)) {
                return false;
            }
        }
        return true;
    }

    private static final void notifyItemInterrupted$ar$ds(FeedbackItem feedbackItem) {
        SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = feedbackItem.mCompletedAction;
        if (utteranceCompleteRunnable != null) {
            utteranceCompleteRunnable.run(3);
        }
    }

    private static float parseFloatParam$ar$ds(HashMap hashMap, String str) {
        String str2 = (String) hashMap.get(str);
        if (str2 == null) {
            return 1.0f;
        }
        try {
            return Float.parseFloat(str2);
        } catch (NumberFormatException unused) {
            LogUtils.e("SpeechControllerImpl", "value '%s' is not a string", str2);
            return 1.0f;
        }
    }

    public static int parseUtteranceId(String str) {
        if (str.startsWith("talkback_")) {
            try {
                return Integer.parseInt(str.substring(9));
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return -1;
            }
        }
        LogUtils.e("SpeechControllerImpl", "Bad utterance ID: %s", str);
        return -1;
    }

    private final void playEarconsFromFragment(FeedbackFragment feedbackFragment, Performance.EventId eventId) {
        Bundle bundle = feedbackFragment.mNonSpeechParams;
        float f = bundle.getFloat("earcon_rate", 1.0f);
        float f2 = bundle.getFloat("earcon_volume", 1.0f);
        if (this.mFeedbackController != null) {
            Iterator it = feedbackFragment.getEarcons().iterator();
            while (it.hasNext()) {
                this.mFeedbackController.playAuditory(((Integer) it.next()).intValue(), f, f2, eventId);
            }
        }
    }

    private final void playHapticsFromFragment(FeedbackFragment feedbackFragment, Performance.EventId eventId) {
        if (this.mFeedbackController != null) {
            Iterator it = feedbackFragment.getHaptics().iterator();
            while (it.hasNext()) {
                this.mFeedbackController.playHaptic$ar$ds(((Integer) it.next()).intValue(), eventId);
            }
        }
    }

    private final void runUtteranceCompleteRunnable(SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable, int i) {
        SpeechController.CompletionRunner completionRunner = new SpeechController.CompletionRunner(utteranceCompleteRunnable, i);
        if (this.mShouldHandleTtsCallBackInMainThread) {
            this.mHandler.post(completionRunner);
        } else {
            completionRunner.run();
        }
    }

    private final void tryNotifyFullScreenReaderCallback() {
        SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable;
        if (this.mInjectFullScreenReadCallbacks && (utteranceCompleteRunnable = this.mFullScreenReadNextCallback) != null) {
            if (this.mShouldHandleTtsCallBackInMainThread) {
                this.mHandler.post(new TrainingActivity$$ExternalSyntheticLambda1(this, 5, null));
            } else {
                utteranceCompleteRunnable.run(5);
            }
        }
    }

    public final void addUtteranceCompleteAction(int i, int i2, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable) {
        this.mUtteranceCompleteActions.add(new UtteranceCompleteAction(i, i2, utteranceCompleteRunnable));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r10v11, types: [android.text.SpannableStringBuilder] */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.lang.CharSequence] */
    public final void copyUtteranceToClipboard$ar$ds(FeedbackItem feedbackItem, Performance.EventId eventId) {
        if (feedbackItem != null) {
            ?? aggregateText = feedbackItem.getAggregateText();
            if (!TextUtils.isEmpty(aggregateText)) {
                ArrayDeque arrayDeque = new ArrayDeque();
                SpannableString spannableString = new SpannableString(aggregateText);
                int i = 0;
                int i2 = 0;
                while (i >= 0 && i < aggregateText.length()) {
                    i = spannableString.nextSpanTransition(i2, aggregateText.length(), SpannableUtils$IdentifierSpan.class);
                    CharSequence subSequence = aggregateText.subSequence(i2, i);
                    if (!TextUtils.isEmpty(subSequence) && !SpannableUtils$IdentifierSpan.isWrappedWithTargetSpan$ar$ds(subSequence, SpannableUtils$NonCopyableTextSpan.class)) {
                        arrayDeque.offer(subSequence);
                    }
                    i2 = i + 1;
                    while (i2 < aggregateText.length()) {
                        int i3 = i2 + 1;
                        if (SpannableUtils$IdentifierSpan.isWrappedWithTargetSpan$ar$ds(aggregateText.subSequence(i2, i3), SpannableUtils$IdentifierSpan.class)) {
                            i2 = i3;
                        }
                    }
                }
                aggregateText = new SpannableStringBuilder("");
                CharSequence charSequence = (CharSequence) arrayDeque.poll();
                boolean z = true;
                while (charSequence != null) {
                    if (!z) {
                        aggregateText.append(", ");
                    }
                    aggregateText.append(charSequence);
                    charSequence = (CharSequence) arrayDeque.poll();
                    z = false;
                }
            }
            if (!TextUtils.isEmpty(aggregateText)) {
                ClipboardManager clipboardManager = (ClipboardManager) this.mContext.getSystemService("clipboard");
                ClipData newPlainText = ClipData.newPlainText(null, aggregateText);
                clipboardManager.setPrimaryClip(newPlainText);
                if (!SpannableUtils$IdentifierSpan.isAtLeastQ()) {
                    newPlainText = clipboardManager.getPrimaryClip();
                }
                if (newPlainText != null && newPlainText.getItemCount() > 0 && newPlainText.getItemAt(0).getText() != null) {
                    speak$ar$ds(this.mContext.getString(R.string.template_text_copied, newPlainText.getItemAt(0).getText().toString()), 1, eventId);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController
    public final FailoverTextToSpeech getFailoverTts() {
        return this.mFailoverTts;
    }

    public final Set getLanguages() {
        Set<Voice> voices = getVoices();
        if (voices == null) {
            return null;
        }
        HashSet hashSet = new HashSet();
        for (Voice voice : voices) {
            Set<String> features = voice.getFeatures();
            if (features != null && !features.contains("notInstalled") && !voice.isNetworkConnectionRequired()) {
                hashSet.add(voice.getLocale());
            }
        }
        return hashSet;
    }

    public final FeedbackItem getLastUtterance() {
        if (this.mFeedbackHistory.isEmpty()) {
            return null;
        }
        return (FeedbackItem) this.mFeedbackHistory.getLast();
    }

    public final Set getVoices() {
        try {
            if (this.mFailoverTts.isReady()) {
                return this.mFailoverTts.mTts.getVoices();
            }
            LogUtils.w("SpeechControllerImpl", "Attempted to get voices before TTS was initialized.", new Object[0]);
            return null;
        } catch (Exception e) {
            LogUtils.e("SpeechControllerImpl", "TTS client crashed while generating language menu items", new Object[0]);
            e.printStackTrace();
            return null;
        }
    }

    public final void handleSpeechStarting() {
        List activeRecordingConfigurations;
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            ((WindowTrackerFactory) it.next()).onSpeechStarting();
        }
        boolean z = this.mUseAudioFocus;
        activeRecordingConfigurations = this.mAudioManager.getActiveRecordingConfigurations();
        if (activeRecordingConfigurations.isEmpty() && z) {
            LogUtils.v("SpeechControllerImpl", "Request Audio Focus.", new Object[0]);
            this.mAudioManager.requestAudioFocus(this.mAudioFocusRequest);
        }
        if (this.mIsSpeaking) {
            LogUtils.e("SpeechControllerImpl", "Started speech while already speaking!", new Object[0]);
        }
        this.mIsSpeaking = true;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController
    public final void interrupt(boolean z) {
        FeedbackFragmentsIterator feedbackFragmentsIterator;
        if (!this.requestPause) {
            this.feedbackSavedTime = SystemClock.uptimeMillis();
            this.savedFeedbackQueue = (ArrayList) this.feedbackQueue.clone();
            this.savedFeedbackItem = this.mCurrentFeedbackItem;
            FeedbackFragmentsIterator feedbackFragmentsIterator2 = this.currentFragmentIterator;
            if (feedbackFragmentsIterator2 != null) {
                ArrayList arrayList = new ArrayList();
                ContextDataProvider.addAll(arrayList, feedbackFragmentsIterator2.currentFragmentIterator);
                feedbackFragmentsIterator = new FeedbackFragmentsIterator(arrayList.iterator());
                feedbackFragmentsIterator.currentFeedbackFragment.set((FeedbackFragment) feedbackFragmentsIterator2.currentFeedbackFragment.get());
                feedbackFragmentsIterator.feedBackItemUtteranceId = feedbackFragmentsIterator2.feedBackItemUtteranceId;
                feedbackFragmentsIterator2.currentFragmentIterator = ((ArrayList) arrayList.clone()).iterator();
            } else {
                feedbackFragmentsIterator = null;
            }
            this.savedFragmentIterator = feedbackFragmentsIterator;
        }
        this.feedbackQueue.clear();
        this.currentFragmentIterator = null;
        FeedbackItem feedbackItem = this.mCurrentFeedbackItem;
        if (feedbackItem != null) {
            onFragmentCompleted$ar$ds(feedbackItem.mUtteranceId, false, true);
            this.mCurrentFeedbackItem = null;
        }
        LogUtils.v("SpeechControllerImpl", "interrupt, stopTtsSpeechCompletely=" + z, new Object[0]);
        this.mUtteranceRangeStartCallbacks.clear();
        while (true) {
            UtteranceCompleteAction utteranceCompleteAction = (UtteranceCompleteAction) this.mUtteranceCompleteActions.poll();
            if (utteranceCompleteAction == null) {
                break;
            }
            SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = utteranceCompleteAction.runnable;
            if (utteranceCompleteRunnable != null) {
                runUtteranceCompleteRunnable(utteranceCompleteRunnable, 3);
            }
        }
        if (z) {
            FailoverTextToSpeech failoverTextToSpeech = this.mFailoverTts;
            try {
                failoverTextToSpeech.allowDeviceSleep();
                failoverTextToSpeech.ensureQueueFlush();
                failoverTextToSpeech.mTts.speak("", 2, null);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        this.mFailoverTts.stopFromTalkBack();
    }

    public final void onFragmentCompleted$ar$ds(String str, boolean z, boolean z2) {
        boolean z3;
        int i;
        FeedbackItem feedbackItem;
        FeedbackFragmentsIterator feedbackFragmentsIterator = this.currentFragmentIterator;
        String str2 = null;
        if (feedbackFragmentsIterator != null) {
            if (!TextUtils.equals(str, feedbackFragmentsIterator.feedBackItemUtteranceId)) {
                LogUtils.w("FeedbackFragmentsIterator", "onFragmentCompleted -- utteranceId = %s,feedBackItemUtteranceId =  %s", str, feedbackFragmentsIterator.feedBackItemUtteranceId);
            } else if (z) {
                feedbackFragmentsIterator.currentFeedbackFragment.set(null);
            }
        }
        int parseUtteranceId = parseUtteranceId(str);
        FeedbackItem feedbackItem2 = this.mCurrentFeedbackItem;
        if (feedbackItem2 != null && !feedbackItem2.mUtteranceId.equals(str)) {
            z3 = true;
        } else {
            z3 = false;
        }
        int i2 = 4;
        if (z3) {
            i = 3;
        } else if (this.requestPause && (feedbackItem = this.savedFeedbackItem) != null && str.equals(feedbackItem.mUtteranceId)) {
            i = 7;
        } else if (z) {
            i = 4;
        } else {
            i = 1;
        }
        if (i == 4) {
            if (processNextFragmentInternal()) {
                return;
            }
        } else {
            i2 = i;
        }
        this.sourceIsVolumeControl = false;
        while (true) {
            UtteranceCompleteAction utteranceCompleteAction = (UtteranceCompleteAction) this.mUtteranceCompleteActions.peek();
            if (utteranceCompleteAction == null || utteranceCompleteAction.utteranceIndex > parseUtteranceId) {
                break;
            }
            this.mUtteranceCompleteActions.remove(utteranceCompleteAction);
            SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = utteranceCompleteAction.runnable;
            if (utteranceCompleteRunnable != null) {
                runUtteranceCompleteRunnable(utteranceCompleteRunnable, i2);
            }
        }
        HashMap hashMap = this.mUtteranceRangeStartCallbacks;
        Integer valueOf = Integer.valueOf(parseUtteranceId);
        hashMap.remove(valueOf);
        if (z3) {
            FeedbackItem feedbackItem3 = this.mCurrentFeedbackItem;
            if (feedbackItem3 != null) {
                str2 = feedbackItem3.mUtteranceId;
            }
            LogUtils.v("SpeechControllerImpl", "Interrupted %d with %s", valueOf, str2);
            return;
        }
        if (z2 && !speakNextItem()) {
            for (WindowTrackerFactory windowTrackerFactory : this.mObservers) {
                if (i2 == 7) {
                    windowTrackerFactory.onSpeechPaused();
                } else {
                    windowTrackerFactory.onSpeechCompleted();
                }
            }
            if (this.mUseAudioFocus) {
                this.mAudioManager.abandonAudioFocusRequest(this.mAudioFocusRequest);
            }
            if (!this.mIsSpeaking) {
                LogUtils.e("SpeechControllerImpl", "Completed speech while already completed!", new Object[0]);
            }
            this.mIsSpeaking = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:94:0x0493, code lost:
    
        if (r15 != 0) goto L207;
     */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02b3 A[Catch: Exception -> 0x0482, TryCatch #1 {Exception -> 0x0482, blocks: (B:81:0x0258, B:95:0x025f, B:98:0x0272, B:100:0x0276, B:102:0x027e, B:104:0x0288, B:106:0x029c, B:109:0x02a9, B:111:0x02b3, B:113:0x02d3, B:115:0x02e6, B:117:0x02ed, B:119:0x02f9, B:121:0x0301, B:123:0x0310, B:124:0x0313, B:126:0x0319, B:129:0x031f, B:130:0x0342, B:132:0x0348, B:134:0x0352, B:135:0x035f, B:137:0x0365, B:139:0x0375, B:144:0x03a5, B:147:0x03ae, B:149:0x03b4, B:151:0x03ba, B:152:0x03cb, B:156:0x03d2, B:158:0x03d6, B:160:0x03ec, B:164:0x0407, B:165:0x0408, B:167:0x0410, B:168:0x0416, B:170:0x042f, B:172:0x0438, B:173:0x043d, B:175:0x0443, B:176:0x0448, B:178:0x044e, B:179:0x0453, B:181:0x0459, B:182:0x045e, B:184:0x0465, B:185:0x046a, B:186:0x0479, B:187:0x047a, B:188:0x0481, B:191:0x028d, B:193:0x0291, B:154:0x03cc, B:155:0x03d1), top: B:80:0x0258, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x047a A[Catch: Exception -> 0x0482, TryCatch #1 {Exception -> 0x0482, blocks: (B:81:0x0258, B:95:0x025f, B:98:0x0272, B:100:0x0276, B:102:0x027e, B:104:0x0288, B:106:0x029c, B:109:0x02a9, B:111:0x02b3, B:113:0x02d3, B:115:0x02e6, B:117:0x02ed, B:119:0x02f9, B:121:0x0301, B:123:0x0310, B:124:0x0313, B:126:0x0319, B:129:0x031f, B:130:0x0342, B:132:0x0348, B:134:0x0352, B:135:0x035f, B:137:0x0365, B:139:0x0375, B:144:0x03a5, B:147:0x03ae, B:149:0x03b4, B:151:0x03ba, B:152:0x03cb, B:156:0x03d2, B:158:0x03d6, B:160:0x03ec, B:164:0x0407, B:165:0x0408, B:167:0x0410, B:168:0x0416, B:170:0x042f, B:172:0x0438, B:173:0x043d, B:175:0x0443, B:176:0x0448, B:178:0x044e, B:179:0x0453, B:181:0x0459, B:182:0x045e, B:184:0x0465, B:185:0x046a, B:186:0x0479, B:187:0x047a, B:188:0x0481, B:191:0x028d, B:193:0x0291, B:154:0x03cc, B:155:0x03d1), top: B:80:0x0258, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0224 A[LOOP:1: B:59:0x021a->B:61:0x0224, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x023f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean processNextFragmentInternal() {
        /*
            Method dump skipped, instructions count: 1246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.output.SpeechControllerImpl.processNextFragmentInternal():boolean");
    }

    public final void resetSavedFeedbackInfo() {
        this.requestPause = false;
        this.savedFragmentIterator = null;
        this.savedFeedbackItem = null;
        ArrayList arrayList = this.savedFeedbackQueue;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public final void setOverlayEnabled(boolean z) {
        if (z) {
            if (this.mTtsOverlay == null) {
                this.mTtsOverlay = new TextToSpeechOverlay(this.mContext);
            }
        } else {
            TextToSpeechOverlay textToSpeechOverlay = this.mTtsOverlay;
            if (textToSpeechOverlay != null) {
                textToSpeechOverlay.hide();
                this.mTtsOverlay = null;
            }
        }
    }

    public final void shutdown() {
        interrupt(false);
        FailoverTextToSpeech failoverTextToSpeech = this.mFailoverTts;
        failoverTextToSpeech.allowDeviceSleep();
        failoverTextToSpeech.mContext.unregisterReceiver(failoverTextToSpeech.mMediaStateMonitor);
        failoverTextToSpeech.mResolver.unregisterContentObserver(failoverTextToSpeech.mLocaleObserver);
        failoverTextToSpeech.mContext.unregisterComponentCallbacks(failoverTextToSpeech.mComponentCallbacks);
        failoverTextToSpeech.mResolver.unregisterContentObserver(failoverTextToSpeech.mSynthObserver);
        failoverTextToSpeech.mResolver.unregisterContentObserver(failoverTextToSpeech.mPitchObserver);
        failoverTextToSpeech.mResolver.unregisterContentObserver(failoverTextToSpeech.mRateObserver);
        SpannableUtils$NonCopyableTextSpan.attemptTtsShutdown(failoverTextToSpeech.mTts);
        failoverTextToSpeech.mTts = null;
        SpannableUtils$NonCopyableTextSpan.attemptTtsShutdown(failoverTextToSpeech.mTempTts);
        failoverTextToSpeech.mTempTts = null;
        setOverlayEnabled(false);
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController
    public final void speak(CharSequence charSequence, Performance.EventId eventId, SpeechController.SpeakOptions speakOptions) {
        if (speakOptions == null) {
            speakOptions = new SpeechController.SpeakOptions();
        }
        speak$ar$class_merging$c686da1_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(charSequence, speakOptions.mEarcons, speakOptions.mHaptics, speakOptions.mQueueMode, speakOptions.mFlags, speakOptions.mUtteranceGroup, speakOptions.mSpeechParams, speakOptions.mNonSpeechParams, speakOptions.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, speakOptions.mCompletedAction, eventId);
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController
    public final void speak$ar$class_merging$8236f667_0$ar$ds(CharSequence charSequence, Bundle bundle, Performance.EventId eventId) {
        speak$ar$class_merging$c686da1_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(charSequence, null, null, 1, 0, 0, bundle, null, null, null, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void speak$ar$class_merging$b9800f89_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.utils.output.FeedbackItem r17, int r18, com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r19, com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable r20) {
        /*
            Method dump skipped, instructions count: 531
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.output.SpeechControllerImpl.speak$ar$class_merging$b9800f89_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(com.google.android.accessibility.utils.output.FeedbackItem, int, com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1, com.google.android.accessibility.utils.output.SpeechController$UtteranceCompleteRunnable):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v16, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r1v24, types: [android.text.Spannable] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r7v43, types: [android.text.SpannableStringBuilder] */
    public final void speak$ar$class_merging$c686da1_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(CharSequence charSequence, Set set, Set set2, int i, int i2, int i3, Bundle bundle, Bundle bundle2, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable, Performance.EventId eventId) {
        ?? r7;
        CharSequence charSequence2;
        boolean z;
        char c;
        BreakIterator sentenceInstance;
        int next;
        int next2;
        Context context;
        int i4;
        Context context2;
        CharacterStyle characterStyle;
        CharSequence charSequence3;
        FeedbackFragment feedbackFragment;
        ReplacementSpan[] replacementSpanArr;
        CharSequence contentDescription;
        int i5 = 0;
        if (this.isMuteSpeech) {
            LogUtils.v("SpeechControllerImpl", "Voice feedback is off.", new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(charSequence) && ((set == null || set.isEmpty()) && (set2 == null || set2.isEmpty()))) {
            if ((i2 & 64) != 0) {
                tryNotifyFullScreenReaderCallback();
                return;
            }
            return;
        }
        int i6 = 1;
        if (!SpannableUtils$IdentifierSpan.isAtLeastR() || TextUtils.isEmpty(charSequence) || !(charSequence instanceof Spanned) || (replacementSpanArr = (ReplacementSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ReplacementSpan.class)) == null || (replacementSpanArr.length) == 0) {
            r7 = charSequence;
        } else {
            r7 = new SpannableStringBuilder(charSequence);
            for (ReplacementSpan replacementSpan : replacementSpanArr) {
                contentDescription = replacementSpan.getContentDescription();
                if (contentDescription != null) {
                    LogUtils.v("SpeechControllerImpl", "Replace ReplacementSpan by content description: %s", contentDescription);
                    int spanStart = r7.getSpanStart(replacementSpan);
                    int spanEnd = r7.getSpanEnd(replacementSpan);
                    int spanFlags = r7.getSpanFlags(replacementSpan);
                    r7.removeSpan(replacementSpan);
                    r7.setSpan(new TtsSpan.TextBuilder(contentDescription.toString()).build(), spanStart, spanEnd, spanFlags);
                }
            }
        }
        Context context3 = this.mContext;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(r7);
        int i7 = 0;
        while (i7 < spannableStringBuilder.length()) {
            int i8 = i7 + 1;
            char charAt = spannableStringBuilder.charAt(i7);
            if (BrailleUnicode.isBraille(charAt)) {
                List dotNumbers = BrailleUnicode.toDotNumbers(charAt);
                StringBuilder sb = new StringBuilder();
                for (int i9 = 0; i9 < dotNumbers.size(); i9++) {
                    sb.append(dotNumbers.get(i9));
                }
                String sb2 = sb.toString();
                String quantityString = context3.getResources().getQuantityString(R.plurals.dots, sb2.length(), sb2);
                SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                String string = context3.getString(R.string.symbol_braille, quantityString);
                spannableStringBuilder2.append((CharSequence) string);
                spannableStringBuilder2.append((CharSequence) " ");
                spannableStringBuilder2.setSpan(new TtsSpan.VerbatimBuilder().setVerbatim(sb2).build(), string.indexOf(sb2), string.indexOf(sb2) + sb2.length(), 17);
                spannableStringBuilder.replace(i7, i8, (CharSequence) spannableStringBuilder2);
            }
            i7 = i8;
        }
        Context context4 = this.mContext;
        boolean z2 = this.mUsePunctuation;
        boolean z3 = this.removeUnnecessarySpans;
        FeedbackItem feedbackItem = new FeedbackItem(eventId);
        feedbackItem.mFragments.add(new FeedbackFragment(spannableStringBuilder, set, set2, bundle, bundle2));
        feedbackItem.addFlag(i2);
        feedbackItem.mUtteranceGroup = i3;
        if (!z2 || !FeedbackProcessingUtils.aggressiveChunking) {
            if (feedbackItem.getFragments().size() != 1) {
                LogUtils.e("FeedbackProcessingUtils", "It only supports to handle the feedback item with single fragment.", new Object[0]);
            } else {
                FeedbackFragment feedbackFragment2 = (FeedbackFragment) feedbackItem.getFragments().get(0);
                CharSequence charSequence4 = feedbackFragment2.mText;
                if (!TextUtils.isEmpty(charSequence4) && charSequence4.length() >= 35) {
                    List formInRangeSpans = FeedbackProcessingUtils.formInRangeSpans(charSequence4, charSequence4.length());
                    Matcher matcher = FeedbackProcessingUtils.CHUNK_DELIMITER.matcher(charSequence4);
                    int i10 = 0;
                    int i11 = 1;
                    while (matcher.find()) {
                        int end = matcher.end();
                        if (FeedbackProcessingUtils.splitFeasible(formInRangeSpans, end)) {
                            int i12 = i11;
                            charSequence2 = charSequence4;
                            FeedbackProcessingUtils.splitChunk(feedbackItem, feedbackFragment2, formInRangeSpans, i10, end, i12);
                            i11 = i12 + 1;
                            i10 = end;
                            formInRangeSpans = formInRangeSpans;
                        } else {
                            charSequence2 = charSequence4;
                        }
                        charSequence4 = charSequence2;
                    }
                    List list = formInRangeSpans;
                    CharSequence charSequence5 = charSequence4;
                    int i13 = i11;
                    if (i13 > 1) {
                        if (i10 < charSequence5.length()) {
                            FeedbackProcessingUtils.splitChunk(feedbackItem, feedbackFragment2, list, i10, charSequence5.length(), i13);
                        }
                        feedbackItem.removeFragment$ar$ds(feedbackFragment2);
                        FeedbackProcessingUtils.copyFragmentMetadata(feedbackFragment2, (FeedbackFragment) feedbackItem.getFragments().get(0));
                    }
                }
            }
        }
        int i14 = 0;
        while (i14 < feedbackItem.getFragments().size()) {
            FeedbackFragment feedbackFragment3 = (FeedbackFragment) feedbackItem.getFragments().get(i14);
            CharSequence charSequence6 = feedbackFragment3.mText;
            if (TextUtils.isEmpty(charSequence6) || !(charSequence6 instanceof Spannable)) {
                context = context4;
                i4 = 1;
            } else {
                Spannable spannable = (Spannable) charSequence6;
                int length = spannable.length();
                int i15 = i5;
                int i16 = i6;
                while (i15 < length) {
                    Class[] clsArr = new Class[2];
                    clsArr[i5] = LocaleSpan.class;
                    clsArr[i6] = AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN;
                    int i17 = i5;
                    int i18 = length;
                    for (int i19 = 2; i17 < i19; i19 = 2) {
                        int nextSpanTransition = spannable.nextSpanTransition(i15, length, clsArr[i17]);
                        if (nextSpanTransition < i18) {
                            i18 = nextSpanTransition;
                        }
                        i17++;
                    }
                    if (!z2 && i18 < length && SpeechCleanupUtils.characterToName(context4, charSequence6.charAt(i18)) != null) {
                        int i20 = i18 + 1;
                        if (!SpannableUtils$IdentifierSpan.isWrappedWithTargetSpan$ar$ds(charSequence6.subSequence(i18, i20), SpannableUtils$IdentifierSpan.class)) {
                            i18 = i20;
                        }
                    }
                    CharacterStyle[] characterStyleArr = (CharacterStyle[]) spannable.getSpans(i15, i18, CharacterStyle.class);
                    int length2 = characterStyleArr.length;
                    int i21 = i5;
                    CharacterStyle characterStyle2 = null;
                    while (true) {
                        if (i21 >= length2) {
                            context2 = context4;
                            characterStyle = characterStyle2;
                            break;
                        }
                        characterStyle = characterStyleArr[i21];
                        context2 = context4;
                        if (characterStyle instanceof LocaleSpan) {
                            break;
                        }
                        if ((characterStyle instanceof ClickableSpan) || (characterStyle instanceof URLSpan)) {
                            characterStyle2 = characterStyle;
                        }
                        i21++;
                        context4 = context2;
                    }
                    ?? subSequence = spannable.subSequence(i15, i18);
                    if (z3) {
                        subSequence = (Spannable) subSequence;
                        ParcelableSpan[] parcelableSpanArr = (ParcelableSpan[]) subSequence.getSpans(0, subSequence.length(), ParcelableSpan.class);
                        int length3 = parcelableSpanArr.length;
                        int i22 = 0;
                        while (i22 < length3) {
                            CharSequence charSequence7 = charSequence6;
                            ParcelableSpan parcelableSpan = parcelableSpanArr[i22];
                            ParcelableSpan[] parcelableSpanArr2 = parcelableSpanArr;
                            if (!(parcelableSpan instanceof TtsSpan)) {
                                subSequence.removeSpan(parcelableSpan);
                            }
                            i22++;
                            parcelableSpanArr = parcelableSpanArr2;
                            charSequence6 = charSequence7;
                        }
                    }
                    CharSequence charSequence8 = charSequence6;
                    if (!SpannableUtils$IdentifierSpan.isWrappedWithTargetSpan$ar$ds(subSequence.subSequence(0, 1), SpannableUtils$IdentifierSpan.class)) {
                        charSequence3 = subSequence;
                    } else if (subSequence.length() == 1) {
                        i15 = i18;
                        charSequence6 = charSequence8;
                        context4 = context2;
                        i5 = 0;
                        i6 = 1;
                    } else {
                        charSequence3 = subSequence.subSequence(subSequence.charAt(1) == ' ' ? 2 : 1, subSequence.length());
                    }
                    if (i16 != 0) {
                        feedbackFragment3.mText = charSequence3;
                        feedbackFragment = feedbackFragment3;
                    } else {
                        feedbackFragment = new FeedbackFragment(charSequence3, null);
                        i14++;
                        if (i15 >= 0) {
                            feedbackFragment.startIndexInFeedbackItem = i15;
                        }
                        feedbackItem.addFragmentAtPosition(feedbackFragment, i14);
                    }
                    if (characterStyle instanceof LocaleSpan) {
                        feedbackFragment.mLocale = ((LocaleSpan) characterStyle).getLocale();
                    } else if (characterStyle != null) {
                        Bundle bundle3 = new Bundle(Bundle.EMPTY);
                        bundle3.putFloat("pitch", 0.95f);
                        feedbackFragment.mSpeechParams = bundle3;
                        feedbackFragment.addEarcon(R.raw.hyperlink);
                    }
                    i16 = 0;
                    i15 = i18;
                    charSequence6 = charSequence8;
                    context4 = context2;
                    i5 = 0;
                    i6 = 1;
                }
                context = context4;
                i4 = i6;
            }
            i14 += i4;
            i6 = i4;
            context4 = context;
            i5 = 0;
        }
        char c2 = ' ';
        if (z2) {
            boolean z4 = FeedbackProcessingUtils.aggressiveChunking;
            if (SpannableUtils$IdentifierSpan.isAtLeastQ()) {
                int i23 = 0;
                loop8: while (i23 < feedbackItem.getFragments().size()) {
                    FeedbackFragment feedbackFragment4 = (FeedbackFragment) feedbackItem.getFragments().get(i23);
                    CharSequence charSequence9 = feedbackFragment4.mText;
                    int length4 = charSequence9.length();
                    if (TextUtils.isEmpty(charSequence9) || length4 < 35) {
                        c = c2;
                    } else {
                        List formInRangeSpans2 = FeedbackProcessingUtils.formInRangeSpans(charSequence9, length4);
                        Locale locale = feedbackFragment4.mLocale;
                        if (locale == null) {
                            locale = Locale.getDefault();
                        }
                        sentenceInstance = BreakIterator.getSentenceInstance(locale);
                        sentenceInstance.setText(charSequence9);
                        next = sentenceInstance.next();
                        boolean z5 = false;
                        int i24 = 0;
                        while (next != -1) {
                            if (!FeedbackProcessingUtils.splitFeasible(formInRangeSpans2, next)) {
                                next = sentenceInstance.next();
                            } else {
                                feedbackItem.addFlag(16384);
                                if (!z4) {
                                    break loop8;
                                }
                                i23++;
                                BreakIterator breakIterator = sentenceInstance;
                                FeedbackProcessingUtils.splitChunk(feedbackItem, feedbackFragment4, formInRangeSpans2, i24, next, i23);
                                next2 = breakIterator.next();
                                formInRangeSpans2 = formInRangeSpans2;
                                i24 = next;
                                next = next2;
                                c2 = c2;
                                sentenceInstance = breakIterator;
                                z5 = true;
                            }
                        }
                        c = c2;
                        List list2 = formInRangeSpans2;
                        if (z5) {
                            if (i24 < length4) {
                                i23++;
                                FeedbackProcessingUtils.splitChunk(feedbackItem, feedbackFragment4, list2, i24, length4, i23);
                            }
                            feedbackItem.removeFragment$ar$ds(feedbackFragment4);
                            FeedbackProcessingUtils.copyFragmentMetadata(feedbackFragment4, (FeedbackFragment) feedbackItem.getFragments().get(0));
                            i23--;
                        }
                    }
                    i23++;
                    c2 = c;
                }
            }
        }
        char c3 = c2;
        for (int i25 = 0; i25 < feedbackItem.getFragments().size(); i25++) {
            FeedbackFragment feedbackFragment5 = (FeedbackFragment) feedbackItem.getFragments().get(i25);
            CharSequence charSequence10 = feedbackFragment5.mText;
            if (!TextUtils.isEmpty(charSequence10) && charSequence10.length() >= FeedbackProcessingUtils.MAX_UTTERANCE_LENGTH) {
                feedbackItem.removeFragment$ar$ds(feedbackFragment5);
                int length5 = charSequence10.length();
                int i26 = 0;
                int i27 = 0;
                while (i26 < length5) {
                    int i28 = (FeedbackProcessingUtils.MAX_UTTERANCE_LENGTH + i26) - 1;
                    int lastIndexOf = TextUtils.lastIndexOf(charSequence10, c3, i26 + 1, i28);
                    int min = lastIndexOf < 0 ? Math.min(i28, length5) : lastIndexOf;
                    feedbackItem.addFragmentAtPosition(new FeedbackFragment(TextUtils.substring(charSequence10, i26, min), feedbackFragment5.mSpeechParams), i25 + i27);
                    i27++;
                    i26 = min;
                }
                FeedbackProcessingUtils.copyFragmentMetadata(feedbackFragment5, (FeedbackFragment) feedbackItem.getFragments().get(i25));
            }
        }
        if (this.mUsePunctuation) {
            for (FeedbackFragment feedbackFragment6 : feedbackItem.getFragments()) {
                CharSequence charSequence11 = feedbackFragment6.mText;
                if (!TextUtils.isEmpty(charSequence11)) {
                    SpannableStringBuilder spannableStringBuilder3 = new SpannableStringBuilder(charSequence11);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    Spanned spanned = (Spanned) charSequence11;
                    Collections.addAll(arrayList2, (SpannableUtils$IdentifierSpan[]) spanned.getSpans(0, charSequence11.length(), SpannableUtils$IdentifierSpan.class));
                    Collections.addAll(arrayList2, (TtsSpan[]) spanned.getSpans(0, charSequence11.length(), TtsSpan.class));
                    int size = arrayList2.size();
                    for (int i29 = 0; i29 < size; i29++) {
                        Object obj = arrayList2.get(i29);
                        arrayList.add(new Range(Integer.valueOf(spannableStringBuilder3.getSpanStart(obj)), Integer.valueOf(spannableStringBuilder3.getSpanEnd(obj))));
                    }
                    Locale locale2 = feedbackFragment6.mLocale;
                    Context context5 = this.mContext;
                    if (locale2 != null) {
                        Configuration configuration = new Configuration(context5.getResources().getConfiguration());
                        configuration.setLocale(locale2);
                        context5 = this.mContext.createConfigurationContext(configuration);
                    }
                    boolean z6 = false;
                    for (int i30 = 0; i30 < charSequence11.length(); i30++) {
                        String characterToName = SpeechCleanupUtils.characterToName(context5, charSequence11.charAt(i30), this.punctuationVerbosity);
                        if (characterToName != null) {
                            int i31 = i30 + 1;
                            Iterator it = arrayList.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (((Range) it.next()).contains((Range) Integer.valueOf(i30))) {
                                        z = false;
                                        break;
                                    }
                                } else {
                                    spannableStringBuilder3.setSpan(new TtsSpan.TextBuilder(characterToName).build(), i30, i31, 17);
                                    z = true;
                                    break;
                                }
                            }
                            z6 = z | z6;
                        }
                    }
                    if (z6) {
                        feedbackFragment6.mText = spannableStringBuilder3;
                    }
                }
            }
        }
        speak$ar$class_merging$b9800f89_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(feedbackItem, i, hapticPatternParser$$ExternalSyntheticLambda1, utteranceCompleteRunnable);
    }

    public final void speak$ar$ds(CharSequence charSequence, int i, Performance.EventId eventId) {
        speak$ar$ds$2ee55f38_0(charSequence, i, 0, eventId);
    }

    public final void speak$ar$ds$2ee55f38_0(CharSequence charSequence, int i, int i2, Performance.EventId eventId) {
        speak$ar$class_merging$c686da1_0$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(charSequence, null, null, i, i2, 0, null, null, null, null, eventId);
    }

    public final boolean speakNextItem() {
        FeedbackItem feedbackItem;
        FeedbackItem feedbackItem2 = this.mCurrentFeedbackItem;
        if (this.feedbackQueue.isEmpty()) {
            feedbackItem = null;
        } else {
            feedbackItem = (FeedbackItem) this.feedbackQueue.remove(0);
        }
        this.mCurrentFeedbackItem = feedbackItem;
        if (feedbackItem == null) {
            LogUtils.v("SpeechControllerImpl", "No next item, stopping speech queue", new Object[0]);
            return false;
        }
        if (feedbackItem2 == null) {
            handleSpeechStarting();
        }
        this.currentFragmentIterator = new FeedbackFragmentsIterator(feedbackItem.getFragments().iterator());
        int i = this.mNextUtteranceIndex;
        this.mNextUtteranceIndex = i + 1;
        String str = "talkback_" + i;
        feedbackItem.mUtteranceId = str;
        this.currentFragmentIterator.feedBackItemUtteranceId = str;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = feedbackItem.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null) {
            this.mUtteranceRangeStartCallbacks.put(Integer.valueOf(i), hapticPatternParser$$ExternalSyntheticLambda1);
        }
        SpeechController.UtteranceCompleteRunnable utteranceCompleteRunnable = feedbackItem.mCompletedAction;
        int i2 = feedbackItem.mUtteranceGroup;
        if (utteranceCompleteRunnable != null) {
            addUtteranceCompleteAction(i, i2, utteranceCompleteRunnable);
        }
        if (this.mInjectFullScreenReadCallbacks && feedbackItem.hasFlag(64)) {
            addUtteranceCompleteAction(i, i2, this.mFullScreenReadNextCallback);
        }
        if (!feedbackItem.hasFlag(2)) {
            while (this.mFeedbackHistory.size() >= 10) {
                LinkedList linkedList = this.mFeedbackHistory;
                linkedList.remove(linkedList.peek());
            }
            this.mFeedbackHistory.addLast(feedbackItem);
        }
        processNextFragmentInternal();
        return true;
    }

    public SpeechControllerImpl(Context context, SpeechController.Delegate delegate, FeedbackController feedbackController, FailoverTextToSpeech failoverTextToSpeech, boolean z) {
        AudioFocusRequest.Builder onAudioFocusChangeListener;
        AudioFocusRequest.Builder audioAttributes;
        AudioFocusRequest build;
        this.mSpeechParametersMap = new HashMap();
        this.mUtteranceStartActions = new PriorityQueue();
        this.mUtteranceCompleteActions = new PriorityQueue();
        this.mUtteranceRangeStartCallbacks = new HashMap();
        this.feedbackQueue = new ArrayList();
        this.mFeedbackHistory = new LinkedList();
        this.mShouldHandleTtsCallBackInMainThread = true;
        this.mObservers = new HashSet();
        this.currentFragmentIterator = null;
        this.savedFragmentIterator = null;
        this.mUseAudioFocus = false;
        this.mNextUtteranceIndex = 0;
        this.mUseIntonation = true;
        this.mUsePunctuation = false;
        this.capLetterFeedback = 1;
        this.mSpeechRate = 1.0f;
        this.mSpeechPitch = 1.0f;
        this.mSpeechVolume = 1.0f;
        this.mSkipNextTTSChangeAnnouncement = false;
        this.ttsChangeAnnouncementEnabled = true;
        this.requestPause = false;
        this.isMuteSpeech = false;
        this.shouldSilentSpeech = false;
        this.sourceIsVolumeControl = false;
        this.state$ar$class_merging$b33be634_0$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        Handler handler = new Handler();
        this.mHandler = handler;
        AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener2 = new AudioManager.OnAudioFocusChangeListener() { // from class: com.google.android.accessibility.utils.output.SpeechControllerImpl.5
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i) {
                LogUtils.d("SpeechControllerImpl", "Saw audio focus change: %d", Integer.valueOf(i));
            }
        };
        this.mAudioFocusListener = onAudioFocusChangeListener2;
        onAudioFocusChangeListener = new AudioFocusRequest.Builder(3).setOnAudioFocusChangeListener(onAudioFocusChangeListener2, handler);
        audioAttributes = onAudioFocusChangeListener.setAudioAttributes(new AudioAttributes.Builder().setContentType(1).setUsage(11).build());
        build = audioAttributes.build();
        this.mAudioFocusRequest = build;
        this.mContext = context;
        this.mDelegate = delegate;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mFailoverTts = failoverTextToSpeech;
        failoverTextToSpeech.addListener(new FailoverTextToSpeech.FailoverTtsListener() { // from class: com.google.android.accessibility.utils.output.SpeechControllerImpl.1
            /* JADX WARN: Removed duplicated region for block: B:14:0x005a  */
            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onTtsInitialized(boolean r6, java.lang.String r7) {
                /*
                    r5 = this;
                    com.google.android.accessibility.utils.output.SpeechControllerImpl r7 = com.google.android.accessibility.utils.output.SpeechControllerImpl.this
                    com.google.android.accessibility.utils.output.SpeechController$Delegate r0 = r7.mDelegate
                    r0.onTtsReady()
                    com.google.android.accessibility.utils.output.FeedbackItem r0 = r7.mCurrentFeedbackItem
                    r1 = 0
                    r2 = 0
                    if (r0 == 0) goto L14
                    java.lang.String r0 = r0.mUtteranceId
                    r7.onFragmentCompleted$ar$ds(r0, r2, r2)
                    r7.mCurrentFeedbackItem = r1
                L14:
                    if (r6 == 0) goto L70
                    boolean r6 = r7.ttsChangeAnnouncementEnabled
                    if (r6 == 0) goto L70
                    boolean r6 = r7.mSkipNextTTSChangeAnnouncement
                    if (r6 != 0) goto L70
                    com.google.android.accessibility.utils.output.FailoverTextToSpeech r6 = r7.mFailoverTts
                    java.lang.String r0 = r6.mTtsEngine
                    if (r0 != 0) goto L26
                L24:
                    r6 = r1
                    goto L54
                L26:
                    android.content.Context r6 = r6.mContext
                    android.content.pm.PackageManager r6 = r6.getPackageManager()
                    android.content.Intent r3 = new android.content.Intent
                    java.lang.String r4 = "android.intent.action.TTS_SERVICE"
                    r3.<init>(r4)
                    r3.setPackage(r0)
                    r0 = 65536(0x10000, float:9.18355E-41)
                    java.util.List r0 = r6.queryIntentServices(r3, r0)
                    if (r0 == 0) goto L24
                    boolean r3 = r0.isEmpty()
                    if (r3 == 0) goto L45
                    goto L24
                L45:
                    java.lang.Object r0 = r0.get(r2)
                    android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
                    android.content.pm.ServiceInfo r0 = r0.serviceInfo
                    if (r0 != 0) goto L50
                    goto L24
                L50:
                    java.lang.CharSequence r6 = r0.loadLabel(r6)
                L54:
                    boolean r0 = android.text.TextUtils.isEmpty(r6)
                    if (r0 != 0) goto L7b
                    android.content.Context r0 = r7.mContext
                    r3 = 1
                    java.lang.Object[] r3 = new java.lang.Object[r3]
                    r3[r2] = r6
                    r6 = 2132086186(0x7f150daa, float:1.9812592E38)
                    java.lang.String r6 = r0.getString(r6, r3)
                    com.google.android.accessibility.utils.Logger r0 = com.google.android.accessibility.utils.Performance.DEFAULT_LOGGER
                    r0 = 14
                    r7.speak$ar$ds$2ee55f38_0(r6, r2, r0, r1)
                    goto L7b
                L70:
                    java.util.ArrayList r6 = r7.feedbackQueue
                    boolean r6 = r6.isEmpty()
                    if (r6 != 0) goto L7b
                    r7.speakNextItem()
                L7b:
                    r7.mSkipNextTTSChangeAnnouncement = r2
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.output.SpeechControllerImpl.AnonymousClass1.onTtsInitialized(boolean, java.lang.String):void");
            }

            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            public final void onUtteranceCompleted(String str, boolean z2) {
                SpeechControllerImpl.this.onFragmentCompleted$ar$ds(str, z2, true);
            }

            /* JADX WARN: Removed duplicated region for block: B:17:0x006c  */
            /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onUtteranceRangeStarted(java.lang.String r7, int r8, int r9) {
                /*
                    r6 = this;
                    com.google.android.accessibility.utils.output.SpeechControllerImpl r0 = com.google.android.accessibility.utils.output.SpeechControllerImpl.this
                    com.google.android.accessibility.utils.output.FeedbackFragmentsIterator r1 = r0.currentFragmentIterator
                    r2 = 0
                    if (r1 == 0) goto L57
                    java.lang.String r3 = r1.feedBackItemUtteranceId
                    boolean r3 = android.text.TextUtils.equals(r7, r3)
                    java.lang.String r4 = "FeedbackFragmentsIterator"
                    r5 = 1
                    if (r3 == 0) goto L3a
                    java.util.concurrent.atomic.AtomicReference r3 = r1.currentFeedbackFragment
                    java.lang.Object r3 = r3.get()
                    com.google.android.accessibility.utils.output.FeedbackFragment r3 = (com.google.android.accessibility.utils.output.FeedbackFragment) r3
                    if (r3 == 0) goto L1e
                    r3.fragmentStartIndex = r8
                L1e:
                    java.util.concurrent.atomic.AtomicReference r1 = r1.currentFeedbackFragment
                    java.lang.Object r1 = r1.get()
                    com.google.android.accessibility.utils.output.FeedbackFragment r1 = (com.google.android.accessibility.utils.output.FeedbackFragment) r1
                    if (r1 != 0) goto L2a
                    r1 = 0
                    goto L2c
                L2a:
                    java.lang.CharSequence r1 = r1.mText
                L2c:
                    java.lang.CharSequence r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.subsequenceSafe(r1, r8, r9)
                    java.lang.Object[] r3 = new java.lang.Object[r5]
                    r3[r2] = r1
                    java.lang.String r1 = "onFragmentRangeStarted ,  speak word = %s"
                    com.google.android.libraries.accessibility.utils.log.LogUtils.v(r4, r1, r3)
                    goto L48
                L3a:
                    java.lang.String r1 = r1.feedBackItemUtteranceId
                    r3 = 2
                    java.lang.Object[] r3 = new java.lang.Object[r3]
                    r3[r2] = r1
                    r3[r5] = r7
                    java.lang.String r1 = "onFragmentRangeStarted ,difference utteranceId, expected:%s ,actual:%s"
                    com.google.android.libraries.accessibility.utils.log.LogUtils.d(r4, r1, r3)
                L48:
                    com.google.android.accessibility.utils.output.FeedbackFragmentsIterator r1 = r0.currentFragmentIterator
                    java.util.concurrent.atomic.AtomicReference r1 = r1.currentFeedbackFragment
                    java.lang.Object r1 = r1.get()
                    com.google.android.accessibility.utils.output.FeedbackFragment r1 = (com.google.android.accessibility.utils.output.FeedbackFragment) r1
                    if (r1 == 0) goto L57
                    int r1 = r1.startIndexInFeedbackItem
                    goto L58
                L57:
                    r1 = r2
                L58:
                    int r7 = com.google.android.accessibility.utils.output.SpeechControllerImpl.parseUtteranceId(r7)
                    int r8 = r8 + r1
                    int r9 = r9 + r1
                    java.util.HashMap r1 = r0.mUtteranceRangeStartCallbacks
                    java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
                    java.lang.Object r7 = r1.get(r7)
                    com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1 r7 = (com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1) r7
                    if (r7 == 0) goto L7e
                    boolean r1 = r0.mShouldHandleTtsCallBackInMainThread
                    if (r1 == 0) goto L7b
                    android.os.Handler r0 = r0.mHandler
                    com.google.android.accessibility.utils.output.SpeechControllerImpl$4 r1 = new com.google.android.accessibility.utils.output.SpeechControllerImpl$4
                    r1.<init>(r7, r8, r9, r2)
                    r0.post(r1)
                    return
                L7b:
                    r7.onUtteranceRangeStarted(r8, r9)
                L7e:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.output.SpeechControllerImpl.AnonymousClass1.onUtteranceRangeStarted(java.lang.String, int, int):void");
            }

            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            public final /* synthetic */ void onUtteranceStarted(String str, long j) {
                onUtteranceStarted(str);
            }

            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            public final void onUtteranceStarted(String str) {
                int parseUtteranceId = SpeechControllerImpl.parseUtteranceId(str);
                while (true) {
                    SpeechControllerImpl speechControllerImpl = SpeechControllerImpl.this;
                    UtteranceStartAction utteranceStartAction = (UtteranceStartAction) speechControllerImpl.mUtteranceStartActions.peek();
                    if (utteranceStartAction == null || parseUtteranceId < 0) {
                        return;
                    } else {
                        speechControllerImpl.mUtteranceStartActions.remove(utteranceStartAction);
                    }
                }
            }

            @Override // com.google.android.accessibility.utils.output.FailoverTextToSpeech.FailoverTtsListener
            public final void onBeforeUtteranceRequested(String str, FailoverTextToSpeech.UtteranceInfoCombo utteranceInfoCombo) {
            }
        });
        this.mFeedbackController = feedbackController;
        this.mInjectFullScreenReadCallbacks = false;
        this.removeUnnecessarySpans = z;
    }
}
