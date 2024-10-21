package com.google.android.accessibility.selecttospeak.feedback;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.room.SharedSQLiteStatement$stmt$2;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.EscapeReminder;
import com.google.android.accessibility.selecttospeak.iterator.InSentenceOffset;
import com.google.android.accessibility.selecttospeak.iterator.Paragraph;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.ui.HighlightBoard;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.VolumeMonitor;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.input.TextEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.marvin.talkback.R;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SelectToSpeakJob {
    private final SpeechController.UtteranceCompleteRunnable autoProceedRunnable;
    private final OptionalMethod clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
    private final Context context;
    public final HighlightBoard highlightBoard;
    public boolean isMultitaskingActivated;
    public final SentenceIterator iterator;
    private final JobUpdateListener jobUpdateListener;
    private long lastActionTime;
    public boolean shouldHighlight;
    private final SpeechController.SpeakOptions speakOptions;
    private final SpeechController speechController;
    public final SpeechRateController speechRateController;
    private boolean speechRateUpdatedWhilePaused;
    public int state;
    private final HapticPatternParser$$ExternalSyntheticLambda1 wordHighlighter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements SpeechController.UtteranceCompleteRunnable {
        final /* synthetic */ Object SelectToSpeakJob$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.SelectToSpeakJob$1$ar$this$0 = obj;
        }

        @Override // com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable
        public final void run(int i) {
            int i2 = this.switching_field;
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        FullScreenReadActor fullScreenReadActor = (FullScreenReadActor) this.SelectToSpeakJob$1$ar$this$0;
                        if (fullScreenReadActor.isActive() && i != 3) {
                            Logger logger = Performance.DEFAULT_LOGGER;
                            Pipeline.FeedbackReturner feedbackReturner = fullScreenReadActor.pipeline;
                            Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(1);
                            focusDirection.granularity = CursorGranularity.DEFAULT;
                            focusDirection.setScroll$ar$ds(true);
                            if (!SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, focusDirection)) {
                                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(fullScreenReadActor.pipeline, (Performance.EventId) null, Feedback.sound(R.raw.complete));
                                fullScreenReadActor.interrupt(true);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    ((VolumeMonitor) this.SelectToSpeakJob$1$ar$this$0).releaseControl();
                    return;
                }
                ((EscapeReminder) this.SelectToSpeakJob$1$ar$this$0).finishSpeaking.set(true);
                RetryingNameResolver.ResolutionResultListener resolutionResultListener = ((EscapeReminder) this.SelectToSpeakJob$1$ar$this$0).callback$ar$class_merging$1705af2a_0$ar$class_merging$ar$class_merging;
                if (((BrailleIme) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).keyboardView.isViewContainerCreated() && ((BrailleIme) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).tutorialState$ar$edu == 1) {
                    ((EscapeReminder) this.SelectToSpeakJob$1$ar$this$0).startTimer();
                    return;
                }
                return;
            }
            if (i == 4) {
                SelectToSpeakJob selectToSpeakJob = (SelectToSpeakJob) this.SelectToSpeakJob$1$ar$this$0;
                if (selectToSpeakJob.state == 1) {
                    if (selectToSpeakJob.iterator.hasNext()) {
                        SelectToSpeakJob selectToSpeakJob2 = (SelectToSpeakJob) this.SelectToSpeakJob$1$ar$this$0;
                        selectToSpeakJob2.speakAndConditionalHighlightCurrentNode(selectToSpeakJob2.iterator.next());
                    } else {
                        ((SelectToSpeakJob) this.SelectToSpeakJob$1$ar$this$0).stop();
                    }
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface JobUpdateListener {
        void onFinish();

        void onPause();

        void onResume();

        void onSpeechRateChanged();

        void onStart();
    }

    public SelectToSpeakJob(Context context, HighlightBoard highlightBoard, SpeechController speechController, SentenceIterator sentenceIterator, JobUpdateListener jobUpdateListener, OptionalMethod optionalMethod) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(this, 0);
        this.autoProceedRunnable = anonymousClass1;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
        this.wordHighlighter$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 1;
        speakOptions.mSpeechParams = new Bundle();
        speakOptions.mRangeStartCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        speakOptions.mCompletedAction = anonymousClass1;
        this.speakOptions = speakOptions;
        this.lastActionTime = 0L;
        this.shouldHighlight = true;
        this.speechRateUpdatedWhilePaused = false;
        this.context = context;
        this.highlightBoard = highlightBoard;
        this.speechController = speechController;
        this.iterator = sentenceIterator;
        this.jobUpdateListener = jobUpdateListener;
        this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.speechRateController = SpeechRateController.createSpeechRateController(context);
        updateSpeechParam();
        this.state = 0;
    }

    private final void highlightCurrentNode() {
        Sentence peek = this.iterator.getPeek();
        if (peek != null) {
            highlight(this.isMultitaskingActivated, peek);
        }
    }

    private final void updateSpeechParam() {
        Bundle bundle = this.speakOptions.mSpeechParams;
        if (bundle != null) {
            bundle.putFloat("rate", this.speechRateController.speechRate);
        }
    }

    private final void updateToUseNewSpeechRate() {
        Context context = this.context;
        context.getClass();
        float f = this.speechRateController.speechRate;
        SharedPreferences.Editor edit = SpannableUtils$IdentifierSpan.getSharedPreferences(context).edit();
        edit.putInt(context.getString(R.string.pref_s2s_speech_rate_int_bit), Float.floatToIntBits(f));
        edit.apply();
        updateSpeechParam();
        if (this.state == 1) {
            this.speechController.interrupt(false);
            InSentenceOffset inSentenceOffset = this.iterator.offsetTracker;
            if (inSentenceOffset.isStarted()) {
                inSentenceOffset.updateOffset();
            }
            speakAndConditionalHighlightCurrentNode(this.iterator.getPeek());
            JobUpdateListener jobUpdateListener = this.jobUpdateListener;
            if (jobUpdateListener != null) {
                jobUpdateListener.onSpeechRateChanged();
            }
        }
    }

    public final void activateMultitasking() {
        this.isMultitaskingActivated = true;
    }

    public final boolean canIncreaseSpeechRate() {
        return this.speechRateController.canIncreaseSpeechRate();
    }

    public final boolean canReduceSpeechRate() {
        return this.speechRateController.canReduceSpeechRate();
    }

    public final void highlight(boolean z, Sentence sentence) {
        if (!this.shouldHighlight) {
            return;
        }
        this.highlightBoard.highlight(z, sentence, this.iterator.offsetTracker);
    }

    public final void increaseSpeechRate() {
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_INCREASE_SPEED_ACTION$ar$edu);
        }
        SpeechRateController speechRateController = this.speechRateController;
        if (speechRateController.canIncreaseSpeechRate()) {
            int i = speechRateController.speechRateIndex + 1;
            speechRateController.speechRateIndex = i;
            speechRateController.speechRate = SpeechRateController.SPEECH_RATE_LIST[i];
        }
        if (this.state == 1) {
            updateToUseNewSpeechRate();
        } else {
            this.speechRateUpdatedWhilePaused = true;
        }
    }

    public final void nextItem() {
        Paragraph paragraph;
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (optionalMethod != null) {
            this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_NEXT_PARAGRAPH_ACTION$ar$edu);
        }
        int i = this.state;
        Sentence sentence = null;
        if (i == 1) {
            if (this.iterator.hasNext()) {
                sentence = this.iterator.next();
            }
        } else if (i == 2) {
            Sentence peek = this.iterator.getPeek();
            if (peek != null) {
                paragraph = peek.paragraph.next;
            } else {
                paragraph = null;
            }
            if (paragraph != null) {
                SentenceIterator sentenceIterator = this.iterator;
                if (sentenceIterator.currentIndex == -1) {
                    sentenceIterator.currentIndex = 0;
                }
                sentence = sentenceIterator.moveAndResetIndex(new SharedSQLiteStatement$stmt$2(sentenceIterator, 8));
            }
        } else {
            return;
        }
        this.lastActionTime = uptimeMillis;
        if (sentence == null) {
            stop();
        } else if (this.state == 1) {
            speakAndConditionalHighlightCurrentNode(sentence);
        } else {
            highlightCurrentNode();
        }
    }

    public final void pause() {
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_PAUSE_ACTION$ar$edu);
        }
        if (this.state == 1) {
            this.state = 2;
            this.speechController.interrupt(false);
            JobUpdateListener jobUpdateListener = this.jobUpdateListener;
            if (jobUpdateListener != null) {
                jobUpdateListener.onPause();
            }
        }
    }

    public final void previousItem() {
        Paragraph paragraph;
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (optionalMethod != null) {
            this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_PREVIOUS_PARAGRAPH_ACTION$ar$edu);
        }
        int i = this.state;
        Sentence sentence = null;
        if (i == 1) {
            if (uptimeMillis - this.lastActionTime > 2000) {
                sentence = this.iterator.getPeek();
            } else if (this.iterator.hasPrevious()) {
                sentence = this.iterator.previous();
            }
        } else if (i == 2) {
            Sentence peek = this.iterator.getPeek();
            if (peek != null) {
                paragraph = peek.paragraph.prev;
            } else {
                paragraph = null;
            }
            if (paragraph != null) {
                SentenceIterator sentenceIterator = this.iterator;
                sentence = sentenceIterator.moveAndResetIndex(new SharedSQLiteStatement$stmt$2(sentenceIterator, 9));
            }
        } else {
            return;
        }
        this.lastActionTime = uptimeMillis;
        if (sentence != null) {
            if (this.state == 1) {
                speakAndConditionalHighlightCurrentNode(sentence);
            } else {
                highlightCurrentNode();
            }
        }
    }

    public final void reduceSpeechRate() {
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_DECREASE_SPEED_ACTION$ar$edu);
        }
        SpeechRateController speechRateController = this.speechRateController;
        if (speechRateController.canReduceSpeechRate()) {
            int i = speechRateController.speechRateIndex - 1;
            speechRateController.speechRateIndex = i;
            speechRateController.speechRate = SpeechRateController.SPEECH_RATE_LIST[i];
        }
        if (this.state == 1) {
            updateToUseNewSpeechRate();
        } else {
            this.speechRateUpdatedWhilePaused = true;
        }
    }

    public final void resume() {
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_RESUME_ACTION$ar$edu);
        }
        if (this.state == 2) {
            this.state = 1;
            if (this.speechRateUpdatedWhilePaused) {
                updateToUseNewSpeechRate();
                this.speechRateUpdatedWhilePaused = false;
            }
            Sentence peek = this.iterator.getPeek();
            if (peek == null) {
                stop();
                return;
            }
            InSentenceOffset inSentenceOffset = this.iterator.offsetTracker;
            if (inSentenceOffset.isStarted()) {
                inSentenceOffset.updateOffset();
            }
            speakAndConditionalHighlightCurrentNode(peek);
            JobUpdateListener jobUpdateListener = this.jobUpdateListener;
            if (jobUpdateListener != null) {
                jobUpdateListener.onResume();
            }
        }
    }

    public final void run() {
        if (this.state != 0) {
            return;
        }
        this.state = 1;
        if (!this.iterator.hasNext()) {
            stop();
            return;
        }
        SentenceIterator sentenceIterator = this.iterator;
        JobUpdateListener jobUpdateListener = this.jobUpdateListener;
        Sentence next = sentenceIterator.next();
        if (jobUpdateListener != null) {
            this.jobUpdateListener.onStart();
        }
        speakAndConditionalHighlightCurrentNode(next);
    }

    public final void speakAndConditionalHighlightCurrentNode(Sentence sentence) {
        CharSequence charSequence;
        if (sentence != null) {
            int i = this.iterator.offsetTracker.offset;
            if (i > 0) {
                CharSequence charSequence2 = sentence.text;
                charSequence = TextEventInterpreter.getSubsequenceWithSpans(charSequence2, i, charSequence2.length());
            } else {
                charSequence = sentence.text;
            }
            if (charSequence != null) {
                this.speechController.speak(charSequence, null, this.speakOptions);
            }
            if (!sentence.getSupportsTextLocation()) {
                highlight(this.isMultitaskingActivated, sentence);
            }
        }
    }

    public final void stop() {
        OptionalMethod optionalMethod = this.clearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PANEL_STOP_ACTION$ar$edu);
        }
        if (this.state != 3) {
            this.state = 3;
            this.speechController.interrupt(false);
            JobUpdateListener jobUpdateListener = this.jobUpdateListener;
            if (jobUpdateListener != null) {
                jobUpdateListener.onFinish();
            }
        }
    }
}
