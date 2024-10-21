package com.google.android.play.core.splitcompat.ingestion;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.TouchDots;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.compositor.Compositor$HandleEventOptions;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.accessibility.talkback.compositor.EventInterpretation;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.compositor.TalkBackFeedbackProvider;
import com.google.android.accessibility.talkback.compositor.roledescription.RoleDescriptionExtractor;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ProximitySensor;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechCleanupUtils;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.play.core.splitcompat.FileStorage;
import com.google.android.play.core.splitinstall.NativeLibraryPathListMutex;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.common.util.concurrent.ExecutionList;
import com.google.firebase.encoders.FieldDescriptor;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Optional;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashSet;
import java.util.Locale;
import java.util.function.Function;
import org.chromium.base.BuildInfo$$ExternalSyntheticApiModelOutline1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Verifier {
    private final Object Verifier$ar$apkSigV2Verifier;
    public Object Verifier$ar$basePackageInfo;
    public final Object Verifier$ar$fileStorage;
    public final Object Verifier$ar$manifestParser;
    public final Context context;

    public Verifier(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.Verifier$ar$apkSigV2Verifier = new ProximitySensor.AnonymousClass1(this, 1);
        this.context = context;
        this.Verifier$ar$manifestParser = resolutionResultListener;
        this.Verifier$ar$basePackageInfo = Optional.empty();
        this.Verifier$ar$fileStorage = (SensorManager) context.getSystemService("sensor");
    }

    public static X509Certificate decodeX509Certificate(Signature signature) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
        } catch (CertificateException e) {
            Log.e("SplitCompat", "Cannot decode certificate.", e);
            return null;
        }
    }

    public static String eventTypeToString(int i) {
        switch (i) {
            case 1073741826:
                return "EVENT_TYPE_INPUT_TEXT_CLEAR";
            case 1073741827:
                return "EVENT_TYPE_INPUT_TEXT_REMOVE";
            case 1073741828:
                return "EVENT_TYPE_INPUT_TEXT_ADD";
            case 1073741829:
                return "EVENT_TYPE_INPUT_TEXT_REPLACE";
            case 1073741830:
                return "EVENT_TYPE_INPUT_TEXT_PASSWORD_ADD";
            case 1073741831:
                return "EVENT_TYPE_INPUT_TEXT_PASSWORD_REMOVE";
            case 1073741832:
                return "EVENT_TYPE_INPUT_TEXT_PASSWORD_REPLACE";
            case 1073741833:
                return "EVENT_TYPE_INPUT_CHANGE_INVALID";
            case 1073741834:
                return "EVENT_TYPE_INPUT_SELECTION_FOCUS_EDIT_TEXT";
            case 1073741835:
                return "EVENT_TYPE_INPUT_SELECTION_MOVE_CURSOR_TO_BEGINNING";
            case 1073741836:
                return "EVENT_TYPE_INPUT_SELECTION_MOVE_CURSOR_TO_END";
            case 1073741837:
                return "EVENT_TYPE_INPUT_SELECTION_MOVE_CURSOR_NO_SELECTION";
            case 1073741838:
                return "EVENT_TYPE_INPUT_SELECTION_MOVE_CURSOR_WITH_SELECTION";
            case 1073741839:
                return "EVENT_TYPE_INPUT_SELECTION_MOVE_CURSOR_SELECTION_CLEARED";
            case 1073741840:
                return "EVENT_TYPE_INPUT_SELECTION_CUT";
            case 1073741841:
                return "EVENT_TYPE_INPUT_SELECTION_PASTE";
            case 1073741842:
                return "EVENT_TYPE_INPUT_SELECTION_TEXT_TRAVERSAL";
            case 1073741843:
                return "EVENT_TYPE_INPUT_SELECTION_SELECT_ALL";
            case 1073741844:
                return "EVENT_TYPE_INPUT_SELECTION_SELECT_ALL_WITH_KEYBOARD";
            case 1073741845:
                return "EVENT_TYPE_INPUT_SELECTION_RESET_SELECTION";
            case 1073741846:
                return "EVENT_TYPE_SET_TEXT_BY_ACTION";
            default:
                switch (i) {
                    case 1073741924:
                        return "EVENT_UNKNOWN";
                    case 1073741925:
                        return "EVENT_SPOKEN_FEEDBACK_ON";
                    default:
                        switch (i) {
                            case 1073741928:
                                return "EVENT_SPOKEN_FEEDBACK_DISABLED";
                            case 1073741929:
                                return "EVENT_CAPS_LOCK_ON";
                            case 1073741930:
                                return "EVENT_CAPS_LOCK_OFF";
                            case 1073741931:
                                return "EVENT_NUM_LOCK_ON";
                            case 1073741932:
                                return "EVENT_NUM_LOCK_OFF";
                            case 1073741933:
                                return "EVENT_SCROLL_LOCK_ON";
                            case 1073741934:
                                return "EVENT_SCROLL_LOCK_OFF";
                            case 1073741935:
                                return "EVENT_ORIENTATION_PORTRAIT";
                            case 1073741936:
                                return "EVENT_ORIENTATION_LANDSCAPE";
                            case 1073741937:
                                return "EVENT_SPEAK_HINT";
                            case 1073741938:
                                return "EVENT_SCROLL_POSITION";
                            case 1073741939:
                                return "EVENT_INPUT_DESCRIBE_NODE";
                            case 1073741940:
                                return "EVENT_MAGNIFICATION_CHANGED";
                            default:
                                return SpannableUtils$IdentifierSpan.typeToString(i);
                        }
                }
        }
    }

    private final void speak(CharSequence charSequence, Performance.EventId eventId, SpeechController.SpeakOptions speakOptions) {
        Object obj = this.Verifier$ar$basePackageInfo;
        if (obj != null) {
            ((Pipeline) ((HapticPatternParser$$ExternalSyntheticLambda1) obj).HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).execute(Feedback.create(eventId, Feedback.speech(charSequence, speakOptions).build()));
        }
    }

    public static int toCompositorEvent(AccessibilityEvent accessibilityEvent) {
        int eventType = accessibilityEvent.getEventType();
        if (eventType == 32768) {
            return 32768;
        }
        if (eventType == 8) {
            return 8;
        }
        if (eventType == 128) {
            return BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
        }
        if (eventType == 1) {
            return 1;
        }
        if (eventType == 2) {
            return 2;
        }
        if (eventType == 64) {
            return 64;
        }
        if (eventType == 2048) {
            return 2048;
        }
        if (eventType == 4) {
            return 4;
        }
        if (eventType == 4096) {
            return 4096;
        }
        if (eventType == 16384) {
            return 16384;
        }
        if (eventType == 32) {
            return 32;
        }
        if (eventType == 1048576) {
            return 1048576;
        }
        if (eventType == 2097152) {
            return 2097152;
        }
        if (eventType == 16) {
            return 16;
        }
        if (eventType == 8192) {
            return 8192;
        }
        if (eventType == 131072) {
            return 131072;
        }
        return 1073741924;
    }

    public final PackageInfo getBasePackageInfo() {
        if (this.Verifier$ar$basePackageInfo == null) {
            try {
                this.Verifier$ar$basePackageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 64);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }
        return (PackageInfo) this.Verifier$ar$basePackageInfo;
    }

    public final EventFeedback getEventFeedback(int i, Compositor$HandleEventOptions compositor$HandleEventOptions) {
        Object apply;
        EventFeedback eventFeedback;
        Function m = BuildInfo$$ExternalSyntheticApiModelOutline1.m(((TalkBackFeedbackProvider) this.Verifier$ar$apkSigV2Verifier).feedbackRules.get(Integer.valueOf(i)));
        if (m == null) {
            eventFeedback = TalkBackFeedbackProvider.EMPTY_FEEDBACK;
        } else {
            Object obj = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode;
            if (obj != null && (i == 1 || (i == 2048 && ((AccessibilityNodeInfoCompat) obj).getLiveRegion() != 0))) {
                Object obj2 = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode;
                Class cls = AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW;
                if (obj2 == null || !((AccessibilityNodeInfoCompat) obj2).refresh()) {
                    obj2 = null;
                }
                compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode = obj2;
            }
            apply = m.apply(compositor$HandleEventOptions);
            EventFeedback eventFeedback2 = (EventFeedback) apply;
            LogUtils.v("TalkBackFeedbackProvider", " %s:  %s", eventTypeToString(i), eventFeedback2.toString());
            eventFeedback = eventFeedback2;
        }
        eventFeedback.equals(TalkBackFeedbackProvider.EMPTY_FEEDBACK);
        return eventFeedback;
    }

    public final void handleEvent(int i, Performance.EventId eventId) {
        handleEvent(i, eventId, new Compositor$HandleEventOptions());
    }

    public final void setUserPreferredLanguage(Locale locale) {
        ((GlobalVariables) this.Verifier$ar$manifestParser).userPreferredLocale = locale;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object, android.hardware.SensorEventListener] */
    public final void startIfNeeded() {
        RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) this.Verifier$ar$manifestParser;
        if (BrailleUserPreferences.readLayoutMode((Context) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0) == TouchDots.AUTO_DETECT && !((BrailleIme) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).keyboardView.isTutorialShown() && !((BrailleIme) resolutionResultListener.RetryingNameResolver$ResolutionResultListener$ar$this$0).brailleDisplayConnectedAndNotSuspended) {
            Sensor defaultSensor = ((SensorManager) this.Verifier$ar$fileStorage).getDefaultSensor(1);
            ((SensorManager) this.Verifier$ar$fileStorage).registerListener((SensorEventListener) this.Verifier$ar$apkSigV2Verifier, defaultSensor, 3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, android.hardware.SensorEventListener] */
    public final void stop() {
        ((SensorManager) this.Verifier$ar$fileStorage).unregisterListener((SensorEventListener) this.Verifier$ar$apkSigV2Verifier);
        this.Verifier$ar$basePackageInfo = Optional.empty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.accessibility.utils.output.SpeechController$UtteranceCompleteRunnable, java.lang.Object] */
    public final void handleEvent(int i, Performance.EventId eventId, Compositor$HandleEventOptions compositor$HandleEventOptions) {
        SpeechController.SpeakOptions speakOptions;
        Object obj = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventInterpretation;
        if (obj != null) {
            LogUtils.v("Compositor", "eventInterpretation= %s", obj);
        }
        ?? r0 = compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$onCompleteRunnable;
        EventFeedback eventFeedback = getEventFeedback(i, compositor$HandleEventOptions);
        Integer earcon = eventFeedback.earcon();
        if (earcon.intValue() != -1) {
            speakOptions = new SpeechController.SpeakOptions();
            HashSet hashSet = new HashSet();
            hashSet.add(earcon);
            speakOptions.mEarcons = hashSet;
            Bundle bundle = new Bundle();
            double doubleValue = eventFeedback.earconRate().doubleValue();
            if (doubleValue != 1.0d) {
                bundle.putFloat("earcon_rate", (float) doubleValue);
            }
            double doubleValue2 = eventFeedback.earconVolume().doubleValue();
            if (doubleValue2 != 1.0d) {
                bundle.putFloat("earcon_volume", (float) doubleValue2);
            }
            speakOptions.mNonSpeechParams = bundle;
        } else {
            speakOptions = null;
        }
        Integer haptic = eventFeedback.haptic();
        if (haptic.intValue() != -1) {
            if (speakOptions == null) {
                speakOptions = new SpeechController.SpeakOptions();
            }
            HashSet hashSet2 = new HashSet();
            hashSet2.add(haptic);
            speakOptions.mHaptics = hashSet2;
        }
        if (eventFeedback.advanceContinuousReading().booleanValue()) {
            if (speakOptions == null) {
                speakOptions = new SpeechController.SpeakOptions();
            }
            speakOptions.mFlags |= 64;
        }
        String str = eventFeedback.ttsOutput().isPresent() ? eventFeedback.ttsOutput().get() : "";
        if (TextUtils.isEmpty(str)) {
            if (speakOptions != null) {
                speakOptions.mFlags |= BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE;
                speak("", eventId, speakOptions);
            }
            if (r0 != 0) {
                r0.run(5);
                return;
            }
            return;
        }
        CharSequence cleanUp = SpeechCleanupUtils.cleanUp(this.context, str);
        int intValue = eventFeedback.queueMode().intValue();
        if (intValue == 1073741825) {
            intValue = cleanUp.length() <= 30 ? 6 : 0;
        }
        int intValue2 = eventFeedback.ttsClearQueueGroup().intValue();
        int i2 = true != eventFeedback.ttsAddToHistory().booleanValue() ? 2 : 0;
        if (eventFeedback.ttsForceFeedback().booleanValue()) {
            i2 |= 4096;
        }
        if (eventFeedback.forceFeedbackEvenIfAudioPlaybackActive().booleanValue()) {
            i2 |= 4;
        }
        if (eventFeedback.forceFeedbackEvenIfMicrophoneActive().booleanValue()) {
            i2 |= 8;
        }
        if (eventFeedback.forceFeedbackEvenIfSsbActive().booleanValue()) {
            i2 |= 16;
        }
        if (eventFeedback.forceFeedbackEvenIfPhoneCallActive().booleanValue()) {
            i2 |= 32;
        }
        if (eventFeedback.ttsSkipDuplicate().booleanValue()) {
            i2 |= 256;
        }
        if (eventFeedback.ttsClearQueueGroup().intValue() != 0) {
            i2 |= 512;
        }
        if (eventFeedback.ttsInterruptSameGroup().booleanValue()) {
            i2 |= 1024;
        }
        if (eventFeedback.preventDeviceSleep().booleanValue()) {
            i2 |= 2048;
        }
        double doubleValue3 = eventFeedback.ttsPitch().doubleValue();
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("pitch", (float) doubleValue3);
        if (speakOptions == null) {
            speakOptions = new SpeechController.SpeakOptions();
        }
        speakOptions.mQueueMode = intValue;
        speakOptions.mSpeechParams = bundle2;
        speakOptions.mUtteranceGroup = intValue2;
        speakOptions.mCompletedAction = r0;
        speakOptions.mFlags |= i2;
        speak(cleanUp, eventId, speakOptions);
    }

    public Verifier(Context context, ExecutionList.RunnableExecutorPair runnableExecutorPair, GlobalVariables globalVariables, ProcessorPhoneticLetters processorPhoneticLetters, int i) {
        String str;
        this.Verifier$ar$fileStorage = new HapticPatternParser$$ExternalSyntheticLambda1(this);
        this.Verifier$ar$manifestParser = globalVariables;
        this.context = context;
        long uptimeMillis = SystemClock.uptimeMillis();
        this.Verifier$ar$apkSigV2Verifier = new TalkBackFeedbackProvider(i, context, runnableExecutorPair, globalVariables, new RoleDescriptionExtractor(context, runnableExecutorPair, globalVariables), processorPhoneticLetters);
        long uptimeMillis2 = SystemClock.uptimeMillis();
        if (i != 0) {
            str = i != 2 ? "UNKNOWN" : "FLAVOR_TV";
        } else {
            str = "FLAVOR_NONE";
        }
        LogUtils.i("Compositor", "EventFeedbackProvider built for compositor %s in %d ms", str, Long.valueOf(uptimeMillis2 - uptimeMillis));
    }

    public Verifier(Context context, FileStorage fileStorage, NativeLibraryPathListMutex nativeLibraryPathListMutex) {
        FieldDescriptor.Builder builder = new FieldDescriptor.Builder(new SplitInstallModule(fileStorage));
        this.Verifier$ar$fileStorage = fileStorage;
        this.Verifier$ar$apkSigV2Verifier = nativeLibraryPathListMutex;
        this.context = context;
        this.Verifier$ar$manifestParser = builder;
    }

    public final void handleEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId, EventInterpretation eventInterpretation) {
        int i = eventInterpretation.mEvent;
        AccessibilityNodeInfoCompat sourceCompat = SpannableUtils$IdentifierSpan.sourceCompat(accessibilityEvent);
        Compositor$HandleEventOptions compositor$HandleEventOptions = new Compositor$HandleEventOptions();
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventObject = accessibilityEvent;
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$eventInterpretation = eventInterpretation;
        compositor$HandleEventOptions.Compositor$HandleEventOptions$ar$sourceNode = sourceCompat;
        handleEvent(i, eventId, compositor$HandleEventOptions);
    }
}
