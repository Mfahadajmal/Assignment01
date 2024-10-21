package com.google.android.accessibility.talkback.actor.voicecommands;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.utils.DelayHandler;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpeechRecognizerActor {
    public final TalkBackAnalytics analytics;
    public Pipeline.FeedbackReturner pipeline;
    public Intent recognizerIntent;
    public SpeechRecognitionDialog speechRecognitionDialog;
    public SpeechRecognizer speechRecognizer;
    public final Context talkbackContext;
    public final VoiceCommandProcessor voiceCommandProcessor;
    private boolean recognizerProducesFinalResults = false;
    private final Handler executeCommandDelayHandler = new Handler();
    public final DelayHandler stopListeningDelayHandler = new DelayHandler() { // from class: com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor.1
        @Override // com.google.android.accessibility.utils.DelayHandler
        public final void handle(Object obj) {
            SpeechRecognizerActor speechRecognizerActor = SpeechRecognizerActor.this;
            speechRecognizerActor.analytics.onVoiceCommandEvent(2);
            speechRecognizerActor.stopListening();
            Context context = speechRecognizerActor.talkbackContext;
            speechRecognizerActor.speakDelayed(context.getString(R.string.voice_commands_partial_result, context.getString(R.string.title_pref_help)));
            speechRecognizerActor.reset();
        }
    };
    public final BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor.2
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            context.unregisterReceiver(SpeechRecognizerActor.this.receiver);
            String[] stringArrayExtra = intent.getStringArrayExtra("permissions");
            int[] intArrayExtra = intent.getIntArrayExtra("grant_results");
            if (stringArrayExtra != null && intArrayExtra != null) {
                for (int i = 0; i < stringArrayExtra.length; i++) {
                    if (TextUtils.equals(stringArrayExtra[i], "android.permission.RECORD_AUDIO") && intArrayExtra[i] == 0) {
                        SpeechRecognizerActor speechRecognizerActor = SpeechRecognizerActor.this;
                        speechRecognizerActor.hasMicPermission = true;
                        speechRecognizerActor.startListening(true);
                        return;
                    }
                }
                Pipeline.FeedbackReturner feedbackReturner = SpeechRecognizerActor.this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.showToast$ar$edu(1, context.getString(R.string.voice_commands_no_mic_permissions), true));
            }
        }
    };
    public boolean listening = false;
    public boolean hasMicPermission = false;
    public final RecognitionListener speechRecognitionListener = new RecognitionListener() { // from class: com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor.3
        @Override // android.speech.RecognitionListener
        public final void onEndOfSpeech() {
            SpeechRecognizerActor speechRecognizerActor = SpeechRecognizerActor.this;
            speechRecognizerActor.listening = false;
            speechRecognizerActor.stopListeningDelayHandler.removeMessages();
            SpeechRecognizerActor.this.speechRecognizer.stopListening();
        }

        @Override // android.speech.RecognitionListener
        public final void onError(int i) {
            int i2;
            LogUtils.v("SpeechRecognizerActor", "Speech recognizer onError() error=%d", Integer.valueOf(i));
            SpeechRecognizerActor.this.stopListeningDelayHandler.removeMessages();
            if (i == 9) {
                SpeechRecognizerActor speechRecognizerActor = SpeechRecognizerActor.this;
                speechRecognizerActor.speakDelayed(speechRecognizerActor.talkbackContext, R.string.voice_commands_no_mic_permissions);
                SpeechRecognizerActor.this.hasMicPermission = false;
            } else if (i == 8) {
                SpeechRecognizerActor.this.speechRecognizer.stopListening();
                SpeechRecognizerActor speechRecognizerActor2 = SpeechRecognizerActor.this;
                speechRecognizerActor2.speakDelayed(speechRecognizerActor2.talkbackContext, R.string.voice_commands_many_requests);
            } else if (i == 7) {
                SpeechRecognizerActor speechRecognizerActor3 = SpeechRecognizerActor.this;
                Context context = speechRecognizerActor3.talkbackContext;
                speechRecognizerActor3.speakDelayed(context.getString(R.string.voice_commands_partial_result, context.getString(R.string.title_pref_help)));
            } else if (i == 6) {
                SpeechRecognizerActor speechRecognizerActor4 = SpeechRecognizerActor.this;
                Context context2 = speechRecognizerActor4.talkbackContext;
                speechRecognizerActor4.speakDelayed(context2.getString(R.string.voice_commands_timeout, context2.getString(R.string.title_pref_help)));
            } else {
                SpeechRecognizerActor speechRecognizerActor5 = SpeechRecognizerActor.this;
                speechRecognizerActor5.speakDelayed(speechRecognizerActor5.talkbackContext, R.string.voice_commands_error);
            }
            SpeechRecognizerActor speechRecognizerActor6 = SpeechRecognizerActor.this;
            if (i == 6) {
                i2 = 2;
            } else {
                i2 = 5;
            }
            speechRecognizerActor6.analytics.onVoiceCommandEvent(i2);
            SpeechRecognizerActor.this.reset();
        }

        @Override // android.speech.RecognitionListener
        public final void onPartialResults(Bundle bundle) {
            LogUtils.v("SpeechRecognizerActor", "Speech recognizer onPartialResults()", new Object[0]);
            SpeechRecognizerActor.this.handleResult(bundle.getStringArrayList("results_recognition"), true);
        }

        @Override // android.speech.RecognitionListener
        public final void onResults(Bundle bundle) {
            LogUtils.v("SpeechRecognizerActor", "Speech recognizer onResults()", new Object[0]);
            SpeechRecognizerActor.this.handleResult(bundle.getStringArrayList("results_recognition"), false);
        }

        @Override // android.speech.RecognitionListener
        public final void onBeginningOfSpeech() {
        }

        @Override // android.speech.RecognitionListener
        public final void onBufferReceived(byte[] bArr) {
        }

        @Override // android.speech.RecognitionListener
        public final void onReadyForSpeech(Bundle bundle) {
        }

        @Override // android.speech.RecognitionListener
        public final void onRmsChanged(float f) {
        }

        @Override // android.speech.RecognitionListener
        public final void onEvent(int i, Bundle bundle) {
        }
    };

    public SpeechRecognizerActor(Context context, VoiceCommandProcessor voiceCommandProcessor, TalkBackAnalytics talkBackAnalytics) {
        this.talkbackContext = context;
        this.voiceCommandProcessor = voiceCommandProcessor;
        this.analytics = talkBackAnalytics;
    }

    private final void setListeningState(boolean z) {
        if (z) {
            this.listening = true;
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(Feedback.Speech.Action.SILENCE));
            return;
        }
        this.listening = false;
        Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
        Logger logger2 = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.speech(Feedback.Speech.Action.UNSILENCE));
    }

    public final void handleResult(List list, boolean z) {
        String str;
        String format;
        long j;
        this.stopListeningDelayHandler.removeMessages();
        String str2 = null;
        this.stopListeningDelayHandler.delay(10000L, null);
        if (!z) {
            this.recognizerProducesFinalResults = true;
        }
        if (!this.recognizerProducesFinalResults || !z) {
            if (true != z) {
                str = "final";
            } else {
                str = "partial";
            }
            if (list == null) {
                format = "null";
            } else {
                format = String.format("\"%s\"", TextUtils.join("\" \"", list));
            }
            LogUtils.v("SpeechRecognizerActor", "Speech recognized %s: %s", str, format);
            if (!z) {
                this.stopListeningDelayHandler.removeMessages();
                reset();
            }
            this.executeCommandDelayHandler.removeCallbacksAndMessages(null);
            if (list != null && !list.isEmpty()) {
                str2 = (String) list.get(0);
            }
            if (true != this.recognizerProducesFinalResults) {
                j = 1000;
            } else {
                j = 250;
            }
            if (!TextUtils.isEmpty(str2)) {
                this.executeCommandDelayHandler.postDelayed(new DelayedWorkTracker.AnonymousClass1(this, str2, 20), j);
            }
        }
    }

    public final void reset() {
        try {
            SpeechRecognizer speechRecognizer = this.speechRecognizer;
            if (speechRecognizer != null) {
                speechRecognizer.setRecognitionListener(null);
                this.speechRecognizer.cancel();
                this.speechRecognizer.destroy();
                this.speechRecognizer = null;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        setListeningState(false);
        this.recognizerIntent = null;
    }

    public final void speak(String str) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 30;
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(str, speakOptions));
    }

    public final void speakDelayed(Context context, int i) {
        speakDelayed(context.getString(i));
    }

    public final void startListening(boolean z) {
        if (this.listening) {
            return;
        }
        if (z && this.speechRecognitionDialog.getShouldShowDialogPref()) {
            this.speechRecognitionDialog.showDialog();
            return;
        }
        this.analytics.onVoiceCommandEvent(1);
        if (this.speechRecognizer == null) {
            this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.talkbackContext);
            if (!SpeechRecognizer.isRecognitionAvailable(this.talkbackContext)) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.showToast$ar$edu(1, this.talkbackContext.getString(R.string.voice_commands_no_voice_recognition_ability), false));
            }
            this.recognizerIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH").putExtra("calling_package", this.talkbackContext.getPackageName()).putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form").putExtra("android.speech.extra.PARTIAL_RESULTS", true);
            this.speechRecognizer.setRecognitionListener(this.speechRecognitionListener);
        }
        setListeningState(true);
        this.speechRecognizer.startListening(this.recognizerIntent);
        this.stopListeningDelayHandler.delay(10000L, null);
    }

    public final void stopListening() {
        if (this.speechRecognizer != null && this.recognizerIntent != null && this.speechRecognitionListener != null) {
            setListeningState(false);
            this.speechRecognizer.stopListening();
            this.stopListeningDelayHandler.removeMessages();
        }
    }

    public final void speakDelayed(String str) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 30;
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder speech = Feedback.speech(str, speakOptions);
        speech.setDelayMs$ar$ds(100);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, speech);
    }
}
