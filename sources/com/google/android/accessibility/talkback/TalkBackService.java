package com.google.android.accessibility.talkback;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityGestureEvent;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.FingerprintGestureController;
import android.accessibilityservice.FingerprintGestureController$FingerprintGestureCallback;
import android.accessibilityservice.TouchInteractionController;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.media.AudioManager;
import android.media.AudioManager$AudioRecordingCallback;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplay;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.common.BrailleUserPreferences$$ExternalSyntheticLambda2;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.input.MultitouchHandler$$ExternalSyntheticLambda8;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.actor.DimScreenActor;
import com.google.android.accessibility.talkback.actor.DirectionNavigationActor;
import com.google.android.accessibility.talkback.actor.FocusActor;
import com.google.android.accessibility.talkback.actor.FullScreenReadActor;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.actor.LanguageActor;
import com.google.android.accessibility.talkback.actor.PassThroughModeActor;
import com.google.android.accessibility.talkback.actor.search.UniversalSearchManager;
import com.google.android.accessibility.talkback.actor.voicecommands.SpeechRecognizerActor;
import com.google.android.accessibility.talkback.actor.voicecommands.VoiceCommandProcessor;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl;
import com.google.android.accessibility.talkback.compositor.EventFilter;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.accessibility.talkback.controller.TelevisionNavigationController;
import com.google.android.accessibility.talkback.eventprocessor.AccessibilityEventProcessor;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorEventQueue;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorMagnification;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.talkback.feedbackpolicy.ScreenFeedbackManager;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.FocusProcessorForTapAndTouchExploration;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenStateMonitor;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.accessibility.talkback.interpreters.InputFocusInterpreter;
import com.google.android.accessibility.talkback.interpreters.ScrollPositionInterpreter;
import com.google.android.accessibility.talkback.interpreters.SubtreeChangeEventInterpreter;
import com.google.android.accessibility.talkback.ipc.IpcService;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.labeling.TalkBackLabelManager;
import com.google.android.accessibility.talkback.logging.EventLatencyLogger;
import com.google.android.accessibility.talkback.logging.FeatureStateProvider;
import com.google.android.accessibility.talkback.monitor.BatteryMonitor;
import com.google.android.accessibility.talkback.monitor.CallStateMonitor;
import com.google.android.accessibility.talkback.monitor.InputMethodMonitor;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.talkback.speech.SpeakPasswordsManager;
import com.google.android.accessibility.talkback.trainingcommon.PageConfig;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.utils.DiagnosticOverlay;
import com.google.android.accessibility.talkback.utils.DiagnosticOverlayControllerImpl;
import com.google.android.accessibility.talkback.utils.HighlightOverlay;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ProximitySensor;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.Statistics;
import com.google.android.accessibility.utils.TreeDebug;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.gestures.GestureManifold;
import com.google.android.accessibility.utils.input.TextEventInterpreter;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.monitor.AudioPlaybackMonitor;
import com.google.android.accessibility.utils.monitor.CollectionState;
import com.google.android.accessibility.utils.monitor.DisplayMonitor;
import com.google.android.accessibility.utils.monitor.InputModeTracker;
import com.google.android.accessibility.utils.monitor.MediaRecorderMonitor;
import com.google.android.accessibility.utils.monitor.SpeechStateMonitor;
import com.google.android.accessibility.utils.output.EditTextActionHistory;
import com.google.android.accessibility.utils.output.FailoverTextToSpeech;
import com.google.android.accessibility.utils.output.FeedbackController;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.gsa.ssb.client.SsbServiceClient;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.play.core.splitcompat.ingestion.Verifier;
import com.google.android.ssb.SsbProto$SsbState;
import com.google.common.flogger.GoogleLogger;
import io.grpc.internal.InternalSubchannel;
import io.grpc.internal.RetryingNameResolver;
import j$.util.Collection;
import j$.util.DesugarArrays;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackService extends AccessibilityService implements Thread.UncaughtExceptionHandler, SpeechController.Delegate {
    private static final boolean IS_DEBUG_BUILD;
    public static volatile TalkBackService instance;
    private AccessibilityEventProcessor accessibilityEventProcessor;
    private AccessibilityFocusInterpreter accessibilityFocusInterpreter;
    private AccessibilityFocusMonitor accessibilityFocusMonitor;
    public TalkBackAnalyticsImpl analytics;
    private AudioPlaybackMonitor audioPlaybackMonitor;
    private BatteryMonitor batteryMonitor;
    private BootReceiver bootReceiver;
    public BrailleDisplay brailleDisplay;
    private FloatingActionButton.ShadowDelegateImpl brailleImeForTalkBackProvider$ar$class_merging$ar$class_merging;
    public CallStateMonitor callStateMonitor;
    private CollectionState collectionState;
    public Verifier compositor$ar$class_merging$e4d5cfcc_0;
    public DeviceConfigurationMonitor deviceConfigurationMonitor;
    private DiagnosticOverlayControllerImpl diagnosticOverlayController;
    private DimScreenActor dimScreenController;
    private HapticPatternParser$$ExternalSyntheticLambda1 directionNavigationActorStateReader$ar$class_merging$ar$class_merging$ar$class_merging;
    private DisplayMonitor displayMonitor;
    public EditTextActionHistory editTextActionHistory;
    private EventFilter eventFilter;
    private EventLatencyLogger eventLatencyLogger;
    public FeedbackController feedbackController;
    private FingerprintGestureController$FingerprintGestureCallback fingerprintGestureCallback;
    private AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private FocusActor focuser;
    private FullScreenReadActor fullScreenReadActor;
    public MetricRecorder gestureController$ar$class_merging;
    private GestureShortcutMapping gestureShortcutMapping;
    public GlobalVariables globalVariables;
    private SnackbarManager headphoneStateMonitor$ar$class_merging$ar$class_merging;
    public TalkBackUpdateHelper helper;
    public ImageCaptioner imageCaptioner;
    private InputFocusInterpreter inputFocusInterpreter;
    private InputMethodMonitor inputMethodMonitor;
    private IpcClientCallbackImpl ipcClientCallback;
    public KeyComboManager keyComboManager;
    public TalkBackLabelManager labelManager;
    private LanguageActor languageActor;
    public boolean lockedBootCompletedPending;
    public ListMenuManager menuManager;
    private WindowTrackerFactory nodeMenuRuleProcessor$ar$class_merging$ar$class_merging;
    private PassThroughModeActor passThroughModeActor;
    public Pipeline pipeline;
    public SharedPreferences prefs;
    private PrimesController primesController;
    private ProcessorEventQueue processorEventQueue;
    private ProcessorMagnification processorMagnification;
    public ProcessorPhoneticLetters processorPhoneticLetters;
    private ScreenFeedbackManager processorScreen;
    private ProximitySensorListener proximitySensorListener;
    public RingerModeAndScreenMonitor ringerModeAndScreenMonitor;
    private AccessibilityNodeInfo rootNode;
    private ScreenStateMonitor screenStateMonitor;
    private ScrollPositionInterpreter scrollPositionInterpreter;
    private SelectorController selectorController;
    public volatile int serviceState;
    private SpeakPasswordsManager speakPasswordsManager;
    private SpeechControllerImpl speechController;
    private RetryingNameResolver.ResolutionResultListener speechLanguage$ar$class_merging$ar$class_merging;
    private SpeechRecognizerActor speechRecognizer;
    private SpeechStateMonitor speechStateMonitor;
    private SubtreeChangeEventInterpreter subtreeChangeEventInterpreter;
    private Thread.UncaughtExceptionHandler systemUeh;
    public TalkBackExitController talkBackExitController;
    private TelevisionDPadManager televisionDPadManager;
    public TelevisionNavigationController televisionNavigationController;
    private InternalSubchannel.Index textCursorTracker$ar$class_merging;
    private TextEventInterpreter textEventInterpreter;
    private UniversalSearchManager universalSearchManager;
    private Boolean useServiceGestureDetection;
    private VoiceActionMonitor voiceActionMonitor;
    private VoiceCommandProcessor voiceCommandProcessor;
    private VolumeMonitor volumeMonitor;
    private WindowEventInterpreter windowEventInterpreter;
    private final List keyEventListeners = new ArrayList();
    private final List serviceStateListeners = new ArrayList();
    public final boolean supportsTouchScreen = true;
    private boolean gestureDetectionFeatureFlag = true;
    private boolean isRootNodeDirty = true;
    public final InputModeTracker inputModeTracker = new InputModeTracker();
    private final DisableTalkBackCompleteAction disableTalkBackCompleteAction = new DisableTalkBackCompleteAction();
    private final FormFactorUtils formFactorUtils = FormFactorUtils.getInstance();
    private boolean isTouchInteracting = false;
    private boolean volumeUpKeyPressedInPassThroughWindow = false;
    private boolean volumeDownKeyPressedInPassThroughWindow = false;
    private final SparseArray displayIdToTouchInteractionMonitors = new SparseArray();
    private final RetryingNameResolver.ResolutionResultListener touchInteractingIndicator$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final RetryingNameResolver.ResolutionResultListener selectorEventNotifier$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final RetryingNameResolver.ResolutionResultListener dimScreenNotifier$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 12, null);

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.TalkBackService$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends SpannableUtils$NonCopyableTextSpan {
        public final /* synthetic */ TalkBackService this$0;
        public final /* synthetic */ DirectionNavigationActor val$directionNavigationActor;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(TalkBackService talkBackService, DirectionNavigationActor directionNavigationActor) {
            super((byte[]) null);
            this.val$directionNavigationActor = directionNavigationActor;
            this.this$0 = talkBackService;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class DisableTalkBackCompleteAction implements SpeechController.UtteranceCompleteRunnable {
        boolean isDone = false;

        @Override // com.google.android.accessibility.utils.output.SpeechController.UtteranceCompleteRunnable
        public final void run(int i) {
            synchronized (this) {
                this.isDone = true;
                notifyAll();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IpcClientCallbackImpl {
        public boolean hasRequestDisableTalkBack;
        public boolean hasTrainingPageSwitched;
        public AppLifecycleMonitor serverOnDestroyListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        public final TalkBackService talkBackService;
        public PageConfig.PageId currentPageId = PageConfig.PageId.PAGE_ID_UNKNOWN;
        public long clientDisconnectedTimeStamp = -1;

        public IpcClientCallbackImpl(TalkBackService talkBackService) {
            this.talkBackService = talkBackService;
        }

        public final void clearServerOnDestroyListener() {
            this.serverOnDestroyListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        }

        public final boolean isTrainingRecentActive() {
            if (this.serverOnDestroyListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                return true;
            }
            if (this.clientDisconnectedTimeStamp > 0 && System.currentTimeMillis() - this.clientDisconnectedTimeStamp < 1000) {
                return true;
            }
            return false;
        }

        public final void onRequestDownloadLibrary(ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType) {
            ImageCaptioner imageCaptioner = this.talkBackService.imageCaptioner;
            int ordinal = imageCaptionUtils$CaptionType.ordinal();
            if (ordinal != 1) {
                if (ordinal == 2 && !imageCaptioner.showImageDescriptionDownloadDialog$ar$edu(null, 3)) {
                    if (imageCaptioner.imageDescriptionModuleDownloadPrompter.isModuleAvailable()) {
                        imageCaptioner.returnFeedback(R.string.download_image_description_successful_hint);
                        return;
                    } else {
                        imageCaptioner.returnFeedback(R.string.downloading_image_description_hint);
                        return;
                    }
                }
                return;
            }
            if (!imageCaptioner.showIconDetectionDownloadDialog$ar$edu(null, 3)) {
                if (imageCaptioner.iconDetectionModuleDownloadPrompter.isModuleAvailable()) {
                    imageCaptioner.returnFeedback(R.string.download_icon_detection_successful_hint);
                } else {
                    imageCaptioner.returnFeedback(R.string.downloading_icon_detection_hint);
                }
            }
        }

        public final void onTrainingFinish() {
            this.talkBackService.setTrainingFinished(true);
            if (SpannableUtils$IdentifierSpan.hasPostNotificationPermission(this.talkBackService)) {
                this.talkBackService.helper.flushPendingNotification();
            } else {
                SpannableUtils$IdentifierSpan.requestPostNotificationPermissionIfNeeded(this.talkBackService, new BroadcastReceiver() { // from class: com.google.android.accessibility.talkback.TalkBackService.IpcClientCallbackImpl.1
                    @Override // android.content.BroadcastReceiver
                    public final void onReceive(Context context, Intent intent) {
                        if (DesugarArrays.stream(intent.getStringArrayExtra("permissions")).anyMatch(new BrailleUserPreferences$$ExternalSyntheticLambda2(3))) {
                            context.unregisterReceiver(this);
                            IpcClientCallbackImpl.this.talkBackService.helper.flushPendingNotification();
                        }
                    }
                });
            }
            TalkBackService talkBackService = this.talkBackService;
            CallStateMonitor callStateMonitor = talkBackService.callStateMonitor;
            SharedPreferences sharedPreferences = talkBackService.prefs;
            if (callStateMonitor != null && sharedPreferences != null) {
                callStateMonitor.requestPhonePermissionIfNeeded(sharedPreferences);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProximitySensorListener {
        private ProximitySensor proximitySensor;
        private boolean screenIsOn = true;
        private final TalkBackService service;
        private boolean silenceOnProximity;

        public ProximitySensorListener(TalkBackService talkBackService) {
            this.service = talkBackService;
        }

        public final void reloadSilenceOnProximity() {
            setSilenceOnProximity(TalkBackService.this.getBooleanPref(R.string.pref_proximity_key, R.bool.pref_proximity_default));
        }

        public final void setProximitySensorState(boolean z) {
            ProximitySensor proximitySensor = this.proximitySensor;
            if (proximitySensor != null) {
                if (!this.silenceOnProximity) {
                    proximitySensor.stop();
                    this.proximitySensor = null;
                    return;
                } else if (this.service.serviceState == 1) {
                    if (!z) {
                        this.proximitySensor.stop();
                        return;
                    }
                } else {
                    this.proximitySensor.stop();
                    return;
                }
            } else if (z && this.silenceOnProximity) {
                ProximitySensor proximitySensor2 = new ProximitySensor(this.service);
                this.proximitySensor = proximitySensor2;
                proximitySensor2.mCallback$ar$class_merging$16a1d12_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TalkBackService.this.pipeline.proximityChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            } else {
                return;
            }
            ProximitySensor proximitySensor3 = this.proximitySensor;
            Sensor sensor = proximitySensor3.mProxSensor;
            if (sensor != null && !proximitySensor3.mIsActive) {
                proximitySensor3.mIsActive = true;
                proximitySensor3.mShouldDropEvents = true;
                proximitySensor3.mSensorManager.registerListener(proximitySensor3.mListener, sensor, 2);
                LogUtils.v("ProximitySensor", "Proximity sensor registered at %d.", Long.valueOf(System.currentTimeMillis()));
                proximitySensor3.mHandler.postDelayed(proximitySensor3.mFilterRunnable, 120L);
            }
        }

        public final void setProximitySensorStateByScreen() {
            setProximitySensorState(this.screenIsOn);
        }

        public final void setScreenIsOn(boolean z) {
            this.screenIsOn = z;
            if (z) {
                setProximitySensorState(true);
            }
        }

        public final void setSilenceOnProximity(boolean z) {
            this.silenceOnProximity = z;
            setProximitySensorState(z);
        }
    }

    static {
        boolean z = true;
        if (!"eng".equals(Build.TYPE) && !"userdebug".equals(Build.TYPE)) {
            z = false;
        }
        IS_DEBUG_BUILD = z;
    }

    private static final boolean dumpComponent$ar$ds(Set set, String str) {
        if (!set.isEmpty() && !set.contains(str)) {
            return false;
        }
        return true;
    }

    private final void enforceDiagnosisModeLogging() {
        if (LogUtils.minLogLevel != 2 && PreferencesActivityUtils.isDiagnosisModeOn(this.prefs, getResources())) {
            LogUtils.minLogLevel = 2;
        }
    }

    public static int getServiceState() {
        TalkBackService talkBackService = instance;
        if (talkBackService == null) {
            return 0;
        }
        return talkBackService.serviceState;
    }

    private final boolean handleOnGestureById(int i, int i2) {
        if (!isServiceActive()) {
            return false;
        }
        Performance performance = Performance.instance;
        Performance.EventId eventId = new Performance.EventId(SystemClock.uptimeMillis(), 4, i2);
        if (performance.trackEvents()) {
            if (performance.computeStatsEnabled) {
                synchronized (performance.lockGestureDetectionToStats) {
                    Statistics statistics = (Statistics) performance.gestureDetectionToStats.get(i2);
                    if (statistics == null) {
                        statistics = new Statistics();
                        performance.gestureDetectionToStats.put(i2, statistics);
                    }
                    statistics.increment(SystemClock.uptimeMillis() - performance.timeInteractionStart);
                }
            }
            performance.onEventReceived(eventId, new String[]{SpannableUtils$IdentifierSpan.gestureIdToString(i2)});
        }
        this.primesController.startTimer(PrimesController.TimerAction.GESTURE_EVENT);
        if (i2 != -3) {
            if (i2 != 17 && i2 != 18) {
                this.analytics.onGesture(i2);
            }
        } else {
            this.analytics.onGesture(61);
        }
        GestureShortcutMapping gestureShortcutMapping = this.gestureShortcutMapping;
        HashMap hashMap = (HashMap) gestureShortcutMapping.gestureIdToActionKey.get(0);
        Integer valueOf = Integer.valueOf(i2);
        String str = (String) hashMap.get(valueOf);
        if (str != null && !TextUtils.equals(str, gestureShortcutMapping.actionGestureUnsupported)) {
            getFeedbackController().playAuditory(R.raw.gesture_end, eventId);
        }
        MetricRecorder metricRecorder = this.gestureController$ar$class_merging;
        if (!metricRecorder.gestureHandledByTraining(i2, false)) {
            String actionKeyFromGestureId = ((GestureShortcutMapping) metricRecorder.MetricRecorder$ar$executor).getActionKeyFromGestureId(i2);
            GestureShortcutMapping.TalkbackAction actionEvent = ((GestureShortcutMapping) metricRecorder.MetricRecorder$ar$executor).getActionEvent(actionKeyFromGestureId);
            if (actionEvent != null) {
                ((TalkBackAnalytics) metricRecorder.MetricRecorder$ar$activeTraceProvider).onShortcutActionEvent(actionEvent);
            }
            LogUtils.v("GestureController", "Recognized gesture id: %d [%s] , action: [%s]", valueOf, GestureShortcutMapping.getGestureString((Context) metricRecorder.MetricRecorder$ar$sampler, i2), actionKeyFromGestureId);
            metricRecorder.performAction(actionKeyFromGestureId, eventId);
        }
        if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
            TouchInteractionMonitor touchInteractionMonitor = (TouchInteractionMonitor) this.displayIdToTouchInteractionMonitors.get(i);
            if (i2 == -3 && touchInteractionMonitor != null) {
                touchInteractionMonitor.requestTouchExploration("handleOnGestureById");
            }
        }
        performance.onHandlerDone(eventId);
        this.primesController.stopTimer(PrimesController.TimerAction.GESTURE_EVENT);
        return true;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r1v25 com.google.android.accessibility.talkback.Pipeline, still in use, count: 2, list:
          (r1v25 com.google.android.accessibility.talkback.Pipeline) from 0x02d3: MOVE (r42v0 com.google.android.accessibility.talkback.Pipeline) = (r1v25 com.google.android.accessibility.talkback.Pipeline) (LINE:724)
          (r1v25 com.google.android.accessibility.talkback.Pipeline) from 0x02bf: MOVE (r42v4 com.google.android.accessibility.talkback.Pipeline) = (r1v25 com.google.android.accessibility.talkback.Pipeline) (LINE:704)
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:151)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:116)
        	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:80)
        	at jadx.core.utils.InsnRemover.addAndUnbind(InsnRemover.java:56)
        	at jadx.core.dex.visitors.ModVisitor.removeStep(ModVisitor.java:447)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    private final void initializeInfrastructure() {
        /*
            Method dump skipped, instructions count: 1908
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TalkBackService.initializeInfrastructure():void");
    }

    private final boolean isBrailleKeyboardActivated() {
        if (getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() == null) {
            return false;
        }
        return ((BrailleIme) getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).isInputViewShown();
    }

    private final boolean isFirstTimeUser() {
        return this.prefs.getBoolean("first_time_user", true);
    }

    public static boolean isServiceActive() {
        if (getServiceState() == 1) {
            return true;
        }
        return false;
    }

    private final void registerGestureDetection() {
        TouchInteractionController touchInteractionController;
        if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
            AccessibilityServiceInfo serviceInfo = getServiceInfo();
            if (serviceInfo != null) {
                serviceInfo.flags |= 2048;
                setServiceInfo(serviceInfo);
            }
            List<Display> allDisplays = SpannableUtils$NonCopyableTextSpan.getAllDisplays(getApplicationContext());
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            for (Display display : allDisplays) {
                Context createDisplayContext = createDisplayContext(display);
                touchInteractionController = getTouchInteractionController(display.getDisplayId());
                if (touchInteractionController != null) {
                    TouchInteractionMonitor touchInteractionMonitor = new TouchInteractionMonitor(createDisplayContext, touchInteractionController, this, this.primesController);
                    GestureManifold gestureManifold = touchInteractionMonitor.gestureDetector;
                    if (!gestureManifold.multiFingerGesturesEnabled) {
                        gestureManifold.multiFingerGesturesEnabled = true;
                        gestureManifold.gestures.addAll(gestureManifold.multiFingerGestures);
                    }
                    GestureManifold gestureManifold2 = touchInteractionMonitor.gestureDetector;
                    if (!gestureManifold2.twoFingerPassthroughEnabled) {
                        gestureManifold2.twoFingerPassthroughEnabled = true;
                        gestureManifold2.multiFingerGestures.removeAll(gestureManifold2.twoFingerSwipes);
                        gestureManifold2.gestures.removeAll(gestureManifold2.twoFingerSwipes);
                    }
                    touchInteractionMonitor.serviceHandlesDoubleTap = true;
                    touchInteractionController.registerCallback(newSingleThreadExecutor, touchInteractionMonitor);
                    this.displayIdToTouchInteractionMonitors.put(display.getDisplayId(), touchInteractionMonitor);
                    LogUtils.i("TalkBackService", "Enabling service gesture detection on display %d", Integer.valueOf(display.getDisplayId()));
                }
            }
        }
    }

    private final void reloadPreferenceLogLevel() {
        LogUtils.minLogLevel = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, getResources(), R.string.pref_log_level_key, R.string.pref_log_level_default);
        enforceDiagnosisModeLogging();
    }

    private final void resetTouchExplorePassThrough() {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && !isBrailleKeyboardActivated()) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline.feedbackReturner;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.passThroughMode$ar$edu(1));
        }
    }

    private final void setServiceState(int i) {
        if (this.serviceState != i) {
            this.serviceState = i;
            Iterator it = this.serviceStateListeners.iterator();
            while (it.hasNext()) {
                ((KeyComboManager) it.next()).onServiceStateChanged(i);
            }
        }
    }

    private final void unregisterGestureDetection() {
        TouchInteractionController touchInteractionController;
        if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
            for (Display display : SpannableUtils$NonCopyableTextSpan.getAllDisplays(getApplicationContext())) {
                touchInteractionController = getTouchInteractionController(display.getDisplayId());
                TouchInteractionMonitor touchInteractionMonitor = (TouchInteractionMonitor) this.displayIdToTouchInteractionMonitors.get(display.getDisplayId());
                if (touchInteractionController != null && touchInteractionMonitor != null) {
                    touchInteractionController.unregisterCallback(touchInteractionMonitor);
                }
            }
            this.displayIdToTouchInteractionMonitors.clear();
        }
    }

    public final void addEventListener(AccessibilityEventListener accessibilityEventListener) {
        this.accessibilityEventProcessor.accessibilityEventListeners.add(accessibilityEventListener);
    }

    public final void clearQueues() {
        interruptAllFeedback$ar$ds$404beace_1();
        this.processorEventQueue.handler.removeMessages(1);
        WindowEventInterpreter windowEventInterpreter = this.windowEventInterpreter;
        if (windowEventInterpreter != null) {
            windowEventInterpreter.windowEventDelayer.removeMessages(1);
            windowEventInterpreter.clearWindowTransition();
        }
    }

    @Override // android.app.Service
    protected final void dump(FileDescriptor fileDescriptor, final PrintWriter printWriter, String[] strArr) {
        SpeechControllerImpl speechControllerImpl;
        GlobalVariables globalVariables;
        CollectionState.PagerItemState pagerItemState;
        GestureShortcutMapping gestureShortcutMapping;
        super.dump(fileDescriptor, printWriter, strArr);
        printWriter.println("============ Talkback Service Dump: args=" + TextUtils.join(",", strArr) + " ============");
        HashSet hashSet = new HashSet(Arrays.asList(strArr));
        Logger logger = new Logger() { // from class: com.google.android.accessibility.talkback.TalkBackService$$ExternalSyntheticLambda0
            @Override // com.google.android.accessibility.utils.Logger
            public final void log(String str, Object[] objArr) {
                printWriter.println(String.format(str, objArr));
            }
        };
        boolean dumpComponent$ar$ds = dumpComponent$ar$ds(hashSet, "node_hierarchy");
        boolean z = IS_DEBUG_BUILD;
        if (!z && dumpComponent$ar$ds) {
            logger.log(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4("node_hierarchy", "Can not dump information for <", "> in a non-debug type."), new Object[0]);
        }
        if (z && dumpComponent$ar$ds && LogUtils.shouldLog$ar$ds()) {
            logger.log("Current Node Hierarchy:", new Object[0]);
            TreeDebug.logNodeTreesOnAllDisplays(this, logger);
        }
        if (dumpComponent$ar$ds(hashSet, "basic_info")) {
            logger.log("TalkBackService basic information: ", new Object[0]);
            logger.log("  versionName=".concat(String.valueOf(SpannableUtils$IdentifierSpan.getVersionName(this))), new Object[0]);
            logger.log("  versionCode=" + SpannableUtils$IdentifierSpan.getVersionCodeCompat(this, getPackageName()), new Object[0]);
            logger.log("  LogUtils.getLogLevel=" + LogUtils.minLogLevel, new Object[0]);
            logger.log("  Build.VERSION.SDK_INT=" + Build.VERSION.SDK_INT, new Object[0]);
            logger.log("  BuildConfig.DEBUG=false", new Object[0]);
            logger.log("", new Object[0]);
        }
        if (dumpComponent$ar$ds(hashSet, "gesture_mapping") && (gestureShortcutMapping = this.gestureShortcutMapping) != null) {
            logger.log("Gesture mapping", new Object[0]);
            for (Map.Entry entry : ((HashMap) gestureShortcutMapping.gestureIdToActionKey.get(0)).entrySet()) {
                logger.log("Gesture = %s, action = %s", GestureShortcutMapping.getGestureString(gestureShortcutMapping.context, ((Integer) entry.getKey()).intValue()), entry.getValue());
            }
            logger.log("", new Object[0]);
        }
        Voice voice = null;
        if (dumpComponent$ar$ds(hashSet, "compositor_state") && (globalVariables = this.globalVariables) != null) {
            logger.log("Compositor state:\n " + String.format("inputMode=%s, ", Integer.valueOf(globalVariables.getGlobalInputMode())) + String.format("sayCapital=%s, ", Boolean.valueOf(globalVariables.sayCapital)) + String.format("speakCollectionInfo=%s, ", Boolean.valueOf(globalVariables.speakCollectionInfo)) + String.format("speakElementIds=%s, ", Boolean.valueOf(globalVariables.speakElementIds)) + String.format("speakRoles=%s, ", Boolean.valueOf(globalVariables.speakRoles)) + String.format("speakSysWindowTitles=%s, ", Boolean.valueOf(globalVariables.speakSystemWindowTitles)) + String.format("textChangeRateUnlimited=%s, ", Boolean.valueOf(globalVariables.textChangeRateUnlimited)) + String.format("usageHintEnabled=%s, ", Boolean.valueOf(globalVariables.usageHintEnabled)) + String.format("enableMediaControlHintForCall=%s, ", Boolean.valueOf(globalVariables.enableMediaControlHintForCall)) + String.format("enableShortAndLongDurationsForSpecificApps=%s, ", Boolean.valueOf(globalVariables.enableShortAndLongDurationsForSpecificApps)), new Object[0]);
            logger.log(globalVariables.collectionState.toString(), new Object[0]);
            CollectionState.ListItemState listItemState = globalVariables.collectionState.getListItemState();
            if (listItemState != null) {
                logger.log(listItemState.toString(), new Object[0]);
            }
            CollectionState.ItemState itemState = globalVariables.collectionState.mItemState;
            if (itemState instanceof CollectionState.PagerItemState) {
                pagerItemState = (CollectionState.PagerItemState) itemState;
            } else {
                pagerItemState = null;
            }
            if (pagerItemState != null) {
                logger.log(pagerItemState.toString(), new Object[0]);
            }
            CollectionState.TableItemState tableItemState = globalVariables.collectionState.getTableItemState();
            if (tableItemState != null) {
                logger.log(tableItemState.toString(), new Object[0]);
            }
            logger.log("", new Object[0]);
        }
        if (dumpComponent$ar$ds(hashSet, "speech_controller") && (speechControllerImpl = this.speechController) != null) {
            logger.log("Speech Controller", new Object[0]);
            logger.log(" supported languages=%s", speechControllerImpl.getLanguages());
            FailoverTextToSpeech failoverTextToSpeech = speechControllerImpl.mFailoverTts;
            logger.log(" cachedTtsLocale=%s", failoverTextToSpeech.toLanguageTag(failoverTextToSpeech.cachedTtsLocale));
            logger.log(" mDefaultLocale=%s", failoverTextToSpeech.toLanguageTag(failoverTextToSpeech.mDefaultLocale));
            logger.log(" mSystemLocale=%s", failoverTextToSpeech.toLanguageTag(failoverTextToSpeech.mSystemLocale));
            logger.log(" mLastUtteranceLocale=%s", failoverTextToSpeech.toLanguageTag(failoverTextToSpeech.mLastUtteranceLocale));
            TextToSpeech textToSpeech = failoverTextToSpeech.mTts;
            if (textToSpeech != null) {
                voice = textToSpeech.getVoice();
            }
            logger.log(" getVoice=%s", voice);
            logger.log("", new Object[0]);
        }
        if (hashSet.contains("perf_metrics")) {
            Performance performance = Performance.instance;
            if (!performance.computeStatsEnabled) {
                logger.log("performance statistic is not enabled", new Object[0]);
            } else {
                performance.displayLabelToStats(logger);
                performance.displayStatToLabelCompare(logger);
                performance.displayAllEventStats(logger);
                Performance.display(logger, "displayGestureDetectionStats()", new Object[0]);
                synchronized (performance.lockGestureDetectionToStats) {
                    for (int i = 0; i < performance.gestureDetectionToStats.size(); i++) {
                        int keyAt = performance.gestureDetectionToStats.keyAt(i);
                        Performance.display(logger, SpannableUtils$IdentifierSpan.gestureIdToString(keyAt), new Object[0]);
                        Performance.displayStatistics(logger, (Statistics) performance.gestureDetectionToStats.get(keyAt));
                    }
                }
            }
        }
        if (hashSet.contains("clear_perf_metrics")) {
            Performance performance2 = Performance.instance;
            performance2.clearAllStats();
            performance2.clearRecentEvents();
            logger.log("performance statistic is cleared", new Object[0]);
        }
    }

    public final boolean getBooleanPref(int i, int i2) {
        return SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, getResources(), i, i2);
    }

    public final FloatingActionButton.ShadowDelegateImpl getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return ((TalkBackForBrailleImeImpl) this.brailleImeForTalkBackProvider$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).brailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final int getCompositorFlavor() {
        if (this.formFactorUtils.isAndroidTv) {
            return 2;
        }
        return 0;
    }

    public final FeedbackController getFeedbackController() {
        FeedbackController feedbackController = this.feedbackController;
        if (feedbackController != null) {
            return feedbackController;
        }
        throw new IllegalStateException("mFeedbackController has not been initialized");
    }

    public final ImageCaptioner getImageCaptioner() {
        ImageCaptioner imageCaptioner = this.imageCaptioner;
        if (imageCaptioner != null) {
            return imageCaptioner;
        }
        throw new IllegalArgumentException("imageCaptioner has not been initialized");
    }

    public final TalkBackLabelManager getLabelManager() {
        TalkBackLabelManager talkBackLabelManager = this.labelManager;
        if (talkBackLabelManager != null) {
            return talkBackLabelManager;
        }
        throw new IllegalStateException("mLabelManager has not been initialized");
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final AccessibilityNodeInfo getRootInActiveWindow() {
        if (this.isRootNodeDirty || this.rootNode == null) {
            this.rootNode = super.getRootInActiveWindow();
            this.isRootNodeDirty = false;
        }
        AccessibilityNodeInfo accessibilityNodeInfo = this.rootNode;
        if (accessibilityNodeInfo == null) {
            return null;
        }
        return AccessibilityNodeInfo.obtain(accessibilityNodeInfo);
    }

    public final SpeechControllerImpl getSpeechController() {
        SpeechControllerImpl speechControllerImpl = this.speechController;
        if (speechControllerImpl != null) {
            return speechControllerImpl;
        }
        throw new IllegalStateException("mSpeechController has not been initialized");
    }

    public final Locale getUserPreferredLocale() {
        return ((GlobalVariables) this.compositor$ar$class_merging$e4d5cfcc_0.Verifier$ar$manifestParser).userPreferredLocale;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasTrainingFinishedByUser() {
        return this.prefs.getBoolean("has_training_exit", false);
    }

    public final void interruptAllFeedback$ar$ds$404beace_1() {
        FullScreenReadActor fullScreenReadActor = this.fullScreenReadActor;
        if (fullScreenReadActor != null) {
            fullScreenReadActor.interrupt();
        }
        Pipeline pipeline = this.pipeline;
        if (pipeline != null) {
            pipeline.interruptAllFeedback$ar$ds$404beace_0();
        }
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isAudioPlaybackActive() {
        return this.voiceActionMonitor.isAudioPlaybackActive();
    }

    public final boolean isBrailleImeTouchInteracting() {
        if (getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null) {
            BrailleIme brailleIme = (BrailleIme) getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            if (!brailleIme.brailleDisplayConnectedAndNotSuspended) {
                KeyboardView keyboardView = brailleIme.keyboardView;
                if (keyboardView.isInputViewCreated() && keyboardView.brailleInputView.touchInteracting) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isMicrophoneActiveAndHeadphoneOff() {
        VoiceActionMonitor voiceActionMonitor = this.voiceActionMonitor;
        if (voiceActionMonitor.isMicrophoneActive() && !voiceActionMonitor.isHeadphoneOn()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isPhoneCallActive() {
        CallStateMonitor callStateMonitor = this.voiceActionMonitor.callStateMonitor;
        if (callStateMonitor != null && callStateMonitor.isPhoneCallActive()) {
            return true;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isSsbActiveAndHeadphoneOff() {
        VoiceActionMonitor voiceActionMonitor = this.voiceActionMonitor;
        if ((voiceActionMonitor.ssbServiceClientMonitor.isSsbActive() || voiceActionMonitor.isVoiceRecognitionActive()) && !voiceActionMonitor.isHeadphoneOn()) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:164:0x029f, code lost:
    
        if (r6 != false) goto L161;
     */
    @Override // android.accessibilityservice.AccessibilityService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r21) {
        /*
            Method dump skipped, instructions count: 876
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TalkBackService.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent):void");
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        DeviceConfigurationMonitor deviceConfigurationMonitor;
        boolean z;
        Performance.EventId eventId;
        int i;
        int i2;
        getTheme().applyStyle(R.style.TalkbackBaseTheme, true);
        if (this.universalSearchManager != null) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline.feedbackReturner;
            Logger logger = Performance.DEFAULT_LOGGER;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            ApplicationExitConfigurations.Builder builder2 = new ApplicationExitConfigurations.Builder(null, null, null);
            builder2.setAction$ar$edu$aa732c31_0$ar$ds(4);
            builder2.ApplicationExitConfigurations$Builder$ar$reportingProcessShortName = configuration;
            builder.universalSearch = builder2.build();
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        }
        if (isServiceActive() && (deviceConfigurationMonitor = this.deviceConfigurationMonitor) != null) {
            int i3 = configuration.orientation;
            int i4 = deviceConfigurationMonitor.lastOrientation;
            boolean z2 = false;
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
                int i5 = deviceConfigurationMonitor.lastFontWeightAdjustment;
                i = configuration.fontWeightAdjustment;
                if (i5 != i) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    i2 = configuration.fontWeightAdjustment;
                    deviceConfigurationMonitor.lastFontWeightAdjustment = i2;
                }
            } else {
                z = false;
            }
            if (i3 != i4) {
                z2 = true;
            }
            if (z2 || z) {
                deviceConfigurationMonitor.lastOrientation = i3;
                Logger logger2 = Performance.DEFAULT_LOGGER;
                if (z2) {
                    Performance performance = Performance.instance;
                    eventId = new Performance.EventId(SystemClock.uptimeMillis(), 5, i3);
                    if (performance.trackEvents()) {
                        performance.onEventReceived(eventId, new String[]{Performance.orientationToSymbolicName(i3)});
                    }
                } else {
                    eventId = null;
                }
                Iterator it = deviceConfigurationMonitor.listeners.iterator();
                while (it.hasNext()) {
                    ((DimScreenActor) it.next()).onConfigurationChanged$ar$ds$e8a368f3_0();
                }
                if (z2) {
                    if (!deviceConfigurationMonitor.powerManager.isScreenOn()) {
                        Performance.instance.onHandlerDone(eventId);
                    } else {
                        if (i3 == 1) {
                            deviceConfigurationMonitor.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741935, eventId);
                        } else if (i3 == 2) {
                            deviceConfigurationMonitor.compositor$ar$class_merging$e4d5cfcc_0.handleEvent(1073741936, eventId);
                            A11yAlertDialogWrapper a11yAlertDialogWrapper = deviceConfigurationMonitor.dialog;
                            if (a11yAlertDialogWrapper != null && a11yAlertDialogWrapper.isShowing()) {
                                deviceConfigurationMonitor.dialog.cancel();
                                deviceConfigurationMonitor.dialog.show();
                            }
                        }
                        Performance.instance.onHandlerDone(eventId);
                    }
                }
            }
        }
        GestureShortcutMapping gestureShortcutMapping = this.gestureShortcutMapping;
        if (gestureShortcutMapping != null && configuration != null && configuration.screenLayout != gestureShortcutMapping.previousScreenLayout) {
            gestureShortcutMapping.loadGestureIdToActionKeyMap();
            gestureShortcutMapping.previousScreenLayout = configuration.screenLayout;
        }
        KeyComboManager keyComboManager = this.keyComboManager;
        if (keyComboManager != null && configuration.hardKeyboardHidden != keyComboManager.hardwareKeyboardStatus) {
            keyComboManager.hardwareKeyboardStatus = configuration.hardKeyboardHidden;
            keyComboManager.updateKeymapChangesNotificationVisibility();
            keyComboManager.showOrHideUpdateModifierKeysDialog();
        }
        if (this.pipeline != null) {
            resetTouchExplorePassThrough();
            Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline.feedbackReturner;
            Logger logger3 = Performance.DEFAULT_LOGGER;
            Feedback.Part.Builder builder3 = Feedback.Part.builder();
            builder3.deviceInfo = new Feedback.DeviceInfo(1, configuration);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, builder3);
        }
    }

    @Override // android.app.Service
    public final void onCreate() {
        BootReceiver bootReceiver = new BootReceiver();
        this.bootReceiver = bootReceiver;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.LOCKED_BOOT_COMPLETED");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        EditorInfoCompat.registerReceiver$ar$ds(this, bootReceiver, intentFilter, 2);
        super.onCreate();
        setTheme(R.style.TalkbackBaseTheme);
        instance = this;
        setServiceState(0);
        this.systemUeh = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        FingerprintGestureController fingerprintGestureController;
        FingerprintGestureController fingerprintGestureController2;
        AccessibilityService.MagnificationController magnificationController;
        AudioManager audioManager;
        AudioManager audioManager2;
        EventLatencyLogger eventLatencyLogger = this.eventLatencyLogger;
        if (eventLatencyLogger != null) {
            FeatureStateProvider featureStateProvider = eventLatencyLogger.stateProvider;
            featureStateProvider.sharedPreferences.unregisterOnSharedPreferenceChangeListener(featureStateProvider.sharedPreferenceChangeListener);
            eventLatencyLogger.backgroundHandler.getLooper().quitSafely();
        }
        if (shouldUseTalkbackGestureDetection()) {
            unregisterGestureDetection();
        }
        BootReceiver bootReceiver = this.bootReceiver;
        if (bootReceiver != null) {
            unregisterReceiver(bootReceiver);
            this.bootReceiver = null;
        }
        PassThroughModeActor passThroughModeActor = this.passThroughModeActor;
        if (passThroughModeActor != null) {
            passThroughModeActor.cancelPassThroughGuardTimer();
        }
        DisplayMonitor displayMonitor = this.displayMonitor;
        if (displayMonitor != null) {
            displayMonitor.displayStateChangedListeners.clear();
        }
        IpcClientCallbackImpl ipcClientCallbackImpl = this.ipcClientCallback;
        if (ipcClientCallbackImpl != null) {
            AppLifecycleMonitor appLifecycleMonitor = ipcClientCallbackImpl.serverOnDestroyListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            if (appLifecycleMonitor != null) {
                IpcService.sendServerDestroyMsg((Messenger) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker);
            }
            this.ipcClientCallback.clearServerOnDestroyListener();
        }
        super.onDestroy();
        if (isServiceActive()) {
            if (!isServiceActive()) {
                LogUtils.e("TalkBackService", "Attempted to suspend while already suspended", new Object[0]);
            } else {
                setServiceState(2);
                DisplayMonitor displayMonitor2 = this.displayMonitor;
                if (displayMonitor2 != null && displayMonitor2.monitoring) {
                    displayMonitor2.displayManager.unregisterDisplayListener(displayMonitor2);
                    displayMonitor2.monitoring = false;
                }
                AccessibilityEventProcessor accessibilityEventProcessor = this.accessibilityEventProcessor;
                if (accessibilityEventProcessor != null) {
                    accessibilityEventProcessor.displayMonitor.removeDisplayStateChangedListener(accessibilityEventProcessor);
                }
                SubtreeChangeEventInterpreter subtreeChangeEventInterpreter = this.subtreeChangeEventInterpreter;
                if (subtreeChangeEventInterpreter != null) {
                    subtreeChangeEventInterpreter.displayMonitor.removeDisplayStateChangedListener(subtreeChangeEventInterpreter);
                }
                WindowEventInterpreter windowEventInterpreter = this.windowEventInterpreter;
                if (windowEventInterpreter != null) {
                    windowEventInterpreter.displayMonitor.removeDisplayStateChangedListener(windowEventInterpreter);
                }
                CallStateMonitor callStateMonitor = this.callStateMonitor;
                if (callStateMonitor != null && callStateMonitor.isStarted && callStateMonitor.supportTelephony) {
                    LogUtils.d("CallStateMonitor", "Stop monitoring call state.", new Object[0]);
                    callStateMonitor.service.unregisterReceiver(callStateMonitor);
                    callStateMonitor.isStarted = false;
                }
                VoiceActionMonitor voiceActionMonitor = this.voiceActionMonitor;
                if (voiceActionMonitor != null) {
                    SsbServiceClientMonitor ssbServiceClientMonitor = voiceActionMonitor.ssbServiceClientMonitor;
                    ssbServiceClientMonitor.isSuspended = true;
                    ssbServiceClientMonitor.serviceConnectionHandler.removeMessages(0);
                    try {
                        if (ssbServiceClientMonitor.ssbServiceClient.isConnected()) {
                            SsbServiceClient ssbServiceClient = ssbServiceClientMonitor.ssbServiceClient;
                            ssbServiceClient.context.unbindService(ssbServiceClient.connection$ar$class_merging);
                            ssbServiceClient.service = null;
                            ssbServiceClient.callback = null;
                            ssbServiceClient.isPendingConnection = false;
                        }
                    } catch (Exception e) {
                        LogUtils.e("SsbServiceClientMonitor", "Error occurs when disconnecting SsbServiceClient.", new Object[0]);
                        e.printStackTrace();
                    }
                    ssbServiceClientMonitor.ssbState$ar$edu = SsbProto$SsbState.AudioState.IDLE$ar$edu$63119a3c_0;
                    voiceActionMonitor.service.unregisterReceiver(voiceActionMonitor.ssbServiceClientMonitor);
                    MediaRecorderMonitor mediaRecorderMonitor = voiceActionMonitor.mediaRecorderMonitor;
                    AudioManager$AudioRecordingCallback audioManager$AudioRecordingCallback = mediaRecorderMonitor.audioRecordingCallback;
                    if (audioManager$AudioRecordingCallback != null && (audioManager2 = mediaRecorderMonitor.audioManager) != null) {
                        audioManager2.unregisterAudioRecordingCallback(audioManager$AudioRecordingCallback);
                    }
                    voiceActionMonitor.audioPlaybackMonitor.onSuspendInfrastructure();
                }
                AudioPlaybackMonitor audioPlaybackMonitor = this.audioPlaybackMonitor;
                if (audioPlaybackMonitor != null) {
                    audioPlaybackMonitor.onSuspendInfrastructure();
                }
                InputMethodMonitor inputMethodMonitor = this.inputMethodMonitor;
                if (inputMethodMonitor != null) {
                    inputMethodMonitor.service.getContentResolver().unregisterContentObserver(inputMethodMonitor.contentObserver);
                }
                this.dimScreenController.suspend();
                interruptAllFeedback$ar$ds$404beace_1();
                if (this.supportsTouchScreen) {
                    requestTouchExploration$ar$ds$dd7cb577_0(false);
                }
                this.prefs.unregisterOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
                this.prefs.unregisterOnSharedPreferenceChangeListener(this.analytics);
                BroadcastReceiver[] broadcastReceiverArr = {this.batteryMonitor, this.volumeMonitor};
                for (int i = 0; i < 2; i++) {
                    unregisterReceiver(broadcastReceiverArr[i]);
                }
                TalkBackLabelManager talkBackLabelManager = this.labelManager;
                if (talkBackLabelManager != null) {
                    talkBackLabelManager.onSuspend(this);
                }
                VolumeMonitor volumeMonitor = this.volumeMonitor;
                if (volumeMonitor != null) {
                    volumeMonitor.releaseControl();
                }
                SnackbarManager snackbarManager = this.headphoneStateMonitor$ar$class_merging$ar$class_merging;
                if (snackbarManager != null && (audioManager = (AudioManager) ((Context) snackbarManager.SnackbarManager$ar$handler).getSystemService("audio")) != null) {
                    audioManager.unregisterAudioDeviceCallback(snackbarManager.getAudioDeviceCallback());
                }
                ((NotificationManager) getSystemService("notification")).cancelAll();
                ProcessorMagnification processorMagnification = this.processorMagnification;
                if (processorMagnification != null && (magnificationController = processorMagnification.magnificationController) != null) {
                    magnificationController.removeListener(processorMagnification.onMagnificationChangedListener);
                    processorMagnification.lastScale = 1.0f;
                    processorMagnification.lastMode = 0;
                }
                if (this.fingerprintGestureCallback != null) {
                    fingerprintGestureController = getFingerprintGestureController();
                    if (fingerprintGestureController != null) {
                        fingerprintGestureController2 = getFingerprintGestureController();
                        fingerprintGestureController2.unregisterFingerprintGestureCallback(this.fingerprintGestureCallback);
                    }
                }
                if (FeatureSupport.isFingerprintGestureSupported(this)) {
                    requestServiceFlag(512, false);
                }
                ApplicationModule.getInstance$ar$class_merging$416cc131_0().notifyTalkBackServiceStateChanged(false);
                if (getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() != null) {
                    getBrailleImeForTalkBack$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().onTalkBackSuspended();
                }
                this.brailleDisplay.stop();
                if (this.eventLatencyLogger != null) {
                    Performance performance = Performance.instance;
                    EventLatencyLogger eventLatencyLogger2 = this.eventLatencyLogger;
                    synchronized (performance.latencyTrackers) {
                        performance.latencyTrackers.remove(eventLatencyLogger2);
                    }
                    this.speechController.mFailoverTts.listeners.remove(this.eventLatencyLogger);
                }
                IpcService.sClientCallback$ar$class_merging = null;
            }
        }
        instance = null;
        setServiceState(2);
        DimScreenActor dimScreenActor = this.dimScreenController;
        if (dimScreenActor != null) {
            dimScreenActor.shutdown();
        }
        FullScreenReadActor fullScreenReadActor = this.fullScreenReadActor;
        if (fullScreenReadActor != null) {
            fullScreenReadActor.interrupt();
        }
        TalkBackLabelManager talkBackLabelManager2 = this.labelManager;
        if (talkBackLabelManager2 != null) {
            talkBackLabelManager2.shutdown();
        }
        ImageCaptioner imageCaptioner = this.imageCaptioner;
        if (imageCaptioner != null) {
            imageCaptioner.shutdownIconDetector();
            imageCaptioner.iconDetectionModuleDownloadPrompter.shutdown();
            imageCaptioner.shutdownImageDescription();
            imageCaptioner.imageDescriptionModuleDownloadPrompter.shutdown();
            SpannableUtils$IdentifierSpan.getSharedPreferences(imageCaptioner.service).unregisterOnSharedPreferenceChangeListener(imageCaptioner);
        }
        ProximitySensorListener proximitySensorListener = this.proximitySensorListener;
        if (proximitySensorListener != null) {
            proximitySensorListener.setProximitySensorState(false);
        }
        FeedbackController feedbackController = this.feedbackController;
        if (feedbackController != null) {
            feedbackController.shutdown();
        }
        KeyComboManager keyComboManager = this.keyComboManager;
        if (keyComboManager != null) {
            keyComboManager.dismissUpdateModifierKeysDialog();
            keyComboManager.notificationManager.cancel(6);
            keyComboManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(keyComboManager.onSharedPreferenceChangeListener);
        }
        Pipeline pipeline = this.pipeline;
        if (pipeline != null) {
            pipeline.cancelAllDelays();
            pipeline.actors.speaker.shutdown();
            LogUtils.v("SpeechControllerObserverInterpreter", "Shutdown requested.", new Object[0]);
            WindowTrackerFactory windowTrackerFactory = pipeline.speechObserver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ((SpeechControllerImpl) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider).mObservers.remove(windowTrackerFactory);
        }
        TalkBackAnalyticsImpl talkBackAnalyticsImpl = this.analytics;
        if (talkBackAnalyticsImpl != null && talkBackAnalyticsImpl.tryToInitialize()) {
            talkBackAnalyticsImpl.talkBackAnalyticsLogger.shutdownInfrastructure();
        }
        setServiceState(0);
        this.serviceStateListeners.clear();
        TelevisionNavigationController televisionNavigationController = this.televisionNavigationController;
        if (televisionNavigationController != null) {
            televisionNavigationController.service.unregisterReceiver(televisionNavigationController.treeDebugBroadcastReceiver);
        }
        if (!this.gestureDetectionFeatureFlag) {
            this.prefs.edit().putBoolean(getString(R.string.pref_talkback_gesture_detection_key), false).apply();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected final boolean onGesture(int i) {
        return handleOnGestureById(0, i);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
        interruptAllFeedback$ar$ds$404beace_1();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0104  */
    @Override // android.accessibilityservice.AccessibilityService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final boolean onKeyEvent(android.view.KeyEvent r13) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TalkBackService.onKeyEvent(android.view.KeyEvent):boolean");
    }

    public final void onLockedBootCompletedInternal() {
        this.pipeline.onBoot(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:210:0x0b08  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x06de  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x06e0  */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.util.Set, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.util.Set, java.lang.Object] */
    @Override // android.accessibilityservice.AccessibilityService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onServiceConnected() {
        /*
            Method dump skipped, instructions count: 2872
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TalkBackService.onServiceConnected():void");
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final void onSpeakingForcedFeedback() {
        VoiceActionMonitor voiceActionMonitor = this.voiceActionMonitor;
        LogUtils.v("VoiceActionMonitor", "Speaking forced feedback.", new Object[0]);
        SsbServiceClientMonitor ssbServiceClientMonitor = voiceActionMonitor.ssbServiceClientMonitor;
        if (ssbServiceClientMonitor.isSsbActive()) {
            LogUtils.v("SsbServiceClientMonitor", "Interrupt SSB audio", new Object[0]);
            try {
                SsbServiceClient ssbServiceClient = ssbServiceClientMonitor.ssbServiceClient;
                SsbServiceClient.checkMainThread();
                if (ssbServiceClient.isConnected()) {
                    try {
                        ssbServiceClient.sendMessage(Message.obtain((Handler) null, 4));
                    } catch (RemoteException e) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) SsbServiceClient.logger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/gsa/ssb/client/SsbServiceClient", "interruptAudioForTalkback", 963, "SsbServiceClient.java")).log("REQUEST_INTERRUPT_AUDIO message failed");
                    }
                }
            } catch (Exception e2) {
                LogUtils.e("SsbServiceClientMonitor", "Error occurs when interrupting Ssb Audio.", new Object[0]);
                e2.printStackTrace();
            }
        }
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final void onTtsReady() {
        int i = 0;
        String stringPref = SpannableUtils$IdentifierSpan.getStringPref(this.prefs, getResources(), R.string.pref_talkback_prefer_locale_key, 0);
        Verifier verifier = this.compositor$ar$class_merging$e4d5cfcc_0;
        Set voices = this.speechController.getVoices();
        Locale locale = null;
        if (stringPref != null && voices != null) {
            locale = (Locale) Collection.EL.stream(voices).filter(new MultitouchHandler$$ExternalSyntheticLambda8(stringPref, 4)).findFirst().map(new TalkBackAnalyticsImpl$$ExternalSyntheticLambda1(2)).orElse(null);
        }
        verifier.setUserPreferredLanguage(locale);
        VoiceActionMonitor voiceActionMonitor = this.voiceActionMonitor;
        if (voiceActionMonitor.skipInterruption) {
            new Handler(Looper.getMainLooper()).postDelayed(new VoiceActionMonitor$$ExternalSyntheticLambda0(voiceActionMonitor, i), 10000L);
        }
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        int i;
        long currentTimeMillis = System.currentTimeMillis();
        interruptAllFeedback$ar$ds$404beace_1();
        IpcClientCallbackImpl ipcClientCallbackImpl = this.ipcClientCallback;
        if (ipcClientCallbackImpl != null) {
            if (ipcClientCallbackImpl.isTrainingRecentActive() && !this.ipcClientCallback.hasRequestDisableTalkBack) {
                this.analytics.sendLogImmediately(4);
            }
            if (this.ipcClientCallback.hasTrainingPageSwitched) {
                this.prefs.edit().putBoolean(getString(R.string.has_training_page_switched), true);
            }
            this.prefs.edit().putLong(getString(R.string.talkback_off_timestamp), System.currentTimeMillis()).apply();
        }
        Pipeline pipeline = this.pipeline;
        if (pipeline != null) {
            float f = 1.0f;
            if (FeatureSupport.hasAccessibilityAudioStream(this)) {
                AudioManager audioManager = (AudioManager) getSystemService("audio");
                int streamVolume = audioManager.getStreamVolume(3);
                int streamMaxVolume = audioManager.getStreamMaxVolume(3);
                VolumeMonitor volumeMonitor = this.volumeMonitor;
                int i2 = -1;
                if (volumeMonitor == null) {
                    i = -1;
                } else {
                    i = volumeMonitor.cachedAccessibilityStreamVolume;
                }
                if (volumeMonitor != null) {
                    i2 = volumeMonitor.cachedAccessibilityStreamMaxVolume;
                }
                if (streamVolume > 0 && streamMaxVolume > 0 && i >= 0 && i2 > 0) {
                    if (i == 0) {
                        f = 0.0f;
                    } else {
                        if (streamVolume / streamMaxVolume > i / i2) {
                            f = (float) Math.pow(10.0d, (r5 - r4) / 0.4f);
                        }
                    }
                }
            }
            pipeline.onUnbind(f, this.disableTalkBackCompleteAction);
        }
        GestureShortcutMapping gestureShortcutMapping = this.gestureShortcutMapping;
        if (gestureShortcutMapping != null) {
            gestureShortcutMapping.prefs.unregisterOnSharedPreferenceChangeListener(gestureShortcutMapping.sharedPreferenceChangeListener);
        }
        RingerModeAndScreenMonitor ringerModeAndScreenMonitor = this.ringerModeAndScreenMonitor;
        if (ringerModeAndScreenMonitor != null) {
            if (ringerModeAndScreenMonitor.monitoring) {
                unregisterReceiver(ringerModeAndScreenMonitor);
                ringerModeAndScreenMonitor.displayMonitor.removeDisplayStateChangedListener(ringerModeAndScreenMonitor);
                ringerModeAndScreenMonitor.monitoring = false;
            }
            RingerModeAndScreenMonitor ringerModeAndScreenMonitor2 = this.ringerModeAndScreenMonitor;
            ringerModeAndScreenMonitor2.screenChangedListeners.clear();
            ringerModeAndScreenMonitor2.deviceUnlockedListeners.clear();
        }
        while (true) {
            synchronized (this.disableTalkBackCompleteAction) {
                try {
                    this.disableTalkBackCompleteAction.wait(1000L);
                } catch (InterruptedException unused) {
                }
                if (System.currentTimeMillis() - currentTimeMillis > 5000 || this.disableTalkBackCompleteAction.isDone) {
                    break;
                }
            }
        }
        return false;
    }

    public final void postRemoveEventListener(AccessibilityEventListener accessibilityEventListener) {
        AccessibilityEventProcessor accessibilityEventProcessor = this.accessibilityEventProcessor;
        if (!accessibilityEventProcessor.accessibilityEventListeners.contains(accessibilityEventListener)) {
            return;
        }
        new Handler().post(new ListMenuManager$$ExternalSyntheticLambda3(accessibilityEventProcessor, accessibilityEventListener, 3, (byte[]) null));
    }

    public final void reloadPreferences() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i;
        boolean z6;
        Resources resources = getResources();
        int i2 = 1;
        LogUtils.v("TalkBackService", "TalkBackService.reloadPreferences() diagnostic mode=%s", Boolean.valueOf(PreferencesActivityUtils.isDiagnosisModeOn(this.prefs, resources)));
        boolean booleanPref = getBooleanPref(R.string.pref_reduce_window_delay_key, R.bool.pref_reduce_window_delay_default);
        WindowEventInterpreter windowEventInterpreter = this.windowEventInterpreter;
        if (windowEventInterpreter != null) {
            windowEventInterpreter.reduceDelayPref = booleanPref;
        }
        boolean booleanPref2 = getBooleanPref(R.string.pref_performance_stats_key, R.bool.pref_performance_stats_default);
        Performance performance = Performance.instance;
        if (performance.computeStatsEnabled != booleanPref2) {
            performance.clearRecentEvents();
            performance.clearAllStats();
            performance.computeStatsEnabled = booleanPref2;
        }
        boolean diagnosticPref = PreferencesActivityUtils.getDiagnosticPref(this.prefs, resources, R.string.pref_log_overlay_key, R.bool.pref_log_overlay_default);
        DiagnosticOverlayControllerImpl diagnosticOverlayControllerImpl = this.diagnosticOverlayController;
        if (diagnosticPref != diagnosticOverlayControllerImpl.enabled) {
            if (diagnosticPref) {
                if (diagnosticOverlayControllerImpl.diagnosticOverlay == null) {
                    diagnosticOverlayControllerImpl.diagnosticOverlay = new DiagnosticOverlay(diagnosticOverlayControllerImpl.context);
                }
                if (diagnosticOverlayControllerImpl.highlightOverlay == null) {
                    diagnosticOverlayControllerImpl.highlightOverlay = new HighlightOverlay(diagnosticOverlayControllerImpl.context);
                }
            } else {
                DiagnosticOverlay diagnosticOverlay = diagnosticOverlayControllerImpl.diagnosticOverlay;
                if (diagnosticOverlay != null) {
                    diagnosticOverlay.hide();
                    diagnosticOverlayControllerImpl.diagnosticOverlay = null;
                }
                HighlightOverlay highlightOverlay = diagnosticOverlayControllerImpl.highlightOverlay;
                if (highlightOverlay != null) {
                    highlightOverlay.clearHighlight();
                    diagnosticOverlayControllerImpl.highlightOverlay = null;
                }
            }
            diagnosticOverlayControllerImpl.enabled = diagnosticPref;
        }
        this.accessibilityEventProcessor.speakWhenScreenOff = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_screenoff_key), resources.getBoolean(R.bool.pref_screenoff_default));
        this.accessibilityEventProcessor.dumpEventMask = this.prefs.getInt(resources.getString(R.string.pref_dump_event_mask_key), 0);
        this.proximitySensorListener.reloadSilenceOnProximity();
        reloadPreferenceLogLevel();
        boolean booleanPref3 = getBooleanPref(R.string.pref_single_tap_key, R.bool.pref_single_tap_default);
        this.globalVariables.mUseSingleTap = booleanPref3;
        AccessibilityFocusInterpreter accessibilityFocusInterpreter = this.accessibilityFocusInterpreter;
        accessibilityFocusInterpreter.focusProcessorForTapAndTouchExploration.isSingleTapEnabled = booleanPref3;
        int intFromStringPref = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, resources, R.string.pref_typing_confirmation_key, R.string.pref_typing_confirmation_default);
        FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration = accessibilityFocusInterpreter.focusProcessorForTapAndTouchExploration;
        focusProcessorForTapAndTouchExploration.reset();
        focusProcessorForTapAndTouchExploration.typingMethod = intFromStringPref;
        AccessibilityFocusInterpreter accessibilityFocusInterpreter2 = this.accessibilityFocusInterpreter;
        int intFromStringPref2 = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, resources, R.string.pref_typing_long_press_duration_key, R.string.pref_typing_long_press_duration_default);
        FocusProcessorForTapAndTouchExploration focusProcessorForTapAndTouchExploration2 = accessibilityFocusInterpreter2.focusProcessorForTapAndTouchExploration;
        long j = intFromStringPref2;
        focusProcessorForTapAndTouchExploration2.longPressDuration = j;
        FocusProcessorForTapAndTouchExploration.PostDelayHandler postDelayHandler = focusProcessorForTapAndTouchExploration2.postDelayHandler;
        if (postDelayHandler != null) {
            postDelayHandler.longPressDelayMs = j;
        }
        GlobalVariables globalVariables = this.globalVariables;
        if (this.accessibilityFocusInterpreter.focusProcessorForTapAndTouchExploration.typingMethod == 2) {
            z = true;
        } else {
            z = false;
        }
        globalVariables.isInterpretAsEntryKey = z;
        if (this.supportsTouchScreen && !isBrailleKeyboardActivated()) {
            if (!this.formFactorUtils.isAndroidTv && !getBooleanPref(R.string.pref_explore_by_touch_key, R.bool.pref_explore_by_touch_default)) {
                z6 = false;
            } else {
                z6 = true;
            }
            requestTouchExploration$ar$ds$dd7cb577_0(z6);
        }
        if (FeatureSupport.isMultiFingerGestureSupported()) {
            requestServiceFlag(12288, true);
            resetTouchExplorePassThrough();
        }
        this.voiceCommandProcessor.echoNotRecognizedTextEnabled = PreferencesActivityUtils.getDiagnosticPref(this, R.string.pref_echo_recognized_text_speech_key, R.bool.pref_echo_recognized_text_default);
        this.pipeline.actors.speaker.setOverlayEnabled(PreferencesActivityUtils.getDiagnosticPref(this, R.string.pref_tts_overlay_key, R.bool.pref_tts_overlay_default));
        this.pipeline.actors.speaker.mUseIntonation = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_intonation_key), resources.getBoolean(R.bool.pref_intonation_default));
        this.pipeline.actors.speaker.mUsePunctuation = getBooleanPref(R.string.pref_punctuation_key, R.bool.pref_punctuation_default);
        Pipeline pipeline = this.pipeline;
        int parseInt = Integer.parseInt(SpannableUtils$IdentifierSpan.getStringPref(this.prefs, resources, R.string.pref_punctuation_verbosity, R.string.pref_punctuation_verbosity_default));
        SpeechControllerImpl speechControllerImpl = pipeline.actors.speaker;
        speechControllerImpl.punctuationVerbosity = parseInt;
        if (parseInt != 0 && parseInt != 1) {
            z2 = false;
        } else {
            z2 = true;
        }
        speechControllerImpl.mUsePunctuation = z2;
        int parseInt2 = Integer.parseInt(SpannableUtils$IdentifierSpan.getPreferenceValueString(this.prefs, resources, resources.getString(R.string.pref_capital_letters_key), resources.getString(R.string.pref_capital_letters_default)));
        this.speechController.capLetterFeedback = parseInt2;
        GlobalVariables globalVariables2 = this.globalVariables;
        if (parseInt2 == 1) {
            z3 = true;
        } else {
            z3 = false;
        }
        globalVariables2.sayCapital = z3;
        this.pipeline.actors.speaker.mSpeechPitch = SpannableUtils$IdentifierSpan.getFloatFromStringPref(this.prefs, resources, R.string.pref_speech_pitch_key, R.string.pref_speech_pitch_default);
        this.pipeline.actors.speaker.mSpeechRate = SpannableUtils$IdentifierSpan.getFloatFromStringPref(this.prefs, resources, R.string.pref_speech_rate_key, R.string.pref_speech_rate_default);
        this.textEventInterpreter.filter.onScreenKeyboardEcho = SpannableUtils$IdentifierSpan.readOnScreenKeyboardEcho(this.prefs, getResources());
        this.textEventInterpreter.filter.physicalKeyboardEcho = SpannableUtils$IdentifierSpan.readPhysicalKeyboardEcho(this.prefs, getResources());
        boolean booleanPref4 = getBooleanPref(R.string.pref_use_audio_focus_key, R.bool.pref_use_audio_focus_default);
        SpeechControllerImpl speechControllerImpl2 = this.pipeline.actors.speaker;
        speechControllerImpl2.mUseAudioFocus = booleanPref4;
        if (!booleanPref4) {
            LogUtils.v("SpeechControllerImpl", "Abandon Audio Focus.", new Object[0]);
            speechControllerImpl2.mAudioManager.abandonAudioFocusRequest(speechControllerImpl2.mAudioFocusRequest);
        }
        if (!FeatureSupport.hasAccessibilityAudioStream(this)) {
            this.pipeline.actors.speaker.mSpeechVolume = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, resources, R.string.pref_speech_volume_key, R.string.pref_speech_volume_default) / 100.0f;
        }
        SpeakPasswordsManager speakPasswordsManager = this.speakPasswordsManager;
        if (speakPasswordsManager != null) {
            speakPasswordsManager.headphonesConnected = speakPasswordsManager.headphoneStateMonitor$ar$class_merging$ar$class_merging.hasHeadphones();
            speakPasswordsManager.globalVariables.mShouldSpeakPasswords = speakPasswordsManager.shouldSpeakPasswords();
        }
        this.feedbackController.mVolumeAdjustment = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, resources, R.string.pref_soundback_volume_key, R.string.pref_soundback_volume_default) / 100.0f;
        if (FeatureSupport.isVibratorSupported(getApplicationContext()) && getBooleanPref(R.string.pref_vibration_key, R.bool.pref_vibration_default)) {
            z4 = true;
        } else {
            z4 = false;
        }
        this.feedbackController.mHapticEnabled = z4;
        this.feedbackController.mAuditoryEnabled = getBooleanPref(R.string.pref_soundback_key, R.bool.pref_soundback_default);
        this.ringerModeAndScreenMonitor.timeFormat = RingerModeAndScreenMonitor.prefValueToTimeFeedbackFormat(resources, SpannableUtils$IdentifierSpan.getStringPref(this.prefs, resources, R.string.pref_time_feedback_format_key, R.string.pref_time_feedback_format_default));
        ScrollPositionInterpreter scrollPositionInterpreter = this.scrollPositionInterpreter;
        if (scrollPositionInterpreter != null) {
            scrollPositionInterpreter.isVerbose = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_verbose_scroll_announcement_key), resources.getBoolean(R.bool.pref_verbose_scroll_announcement_default));
        }
        if (FeatureSupport.isFingerprintGestureSupported(this) && (this.gestureController$ar$class_merging.isFingerprintGestureAssigned(4) || this.gestureController$ar$class_merging.isFingerprintGestureAssigned(8) || this.gestureController$ar$class_merging.isFingerprintGestureAssigned(2) || this.gestureController$ar$class_merging.isFingerprintGestureAssigned(1))) {
            z5 = true;
        } else {
            z5 = false;
        }
        requestServiceFlag(512, z5);
        if (this.compositor$ar$class_merging$e4d5cfcc_0 != null) {
            this.globalVariables.speakCollectionInfo = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_speak_container_element_positions_key), resources.getBoolean(R.bool.pref_speak_container_element_positions_default));
            this.globalVariables.speakRoles = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_speak_roles_key), resources.getBoolean(R.bool.pref_speak_roles_default));
            this.globalVariables.speakSystemWindowTitles = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_speak_system_window_titles_key), resources.getBoolean(R.bool.pref_speak_system_window_titles_default));
            this.globalVariables.textChangeRateUnlimited = SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, resources, R.string.pref_allow_frequent_content_change_announcement_key, R.bool.pref_allow_frequent_content_change_announcement_default);
            String stringPref = SpannableUtils$IdentifierSpan.getStringPref(this.prefs, resources, R.string.pref_node_desc_order_key, R.string.pref_node_desc_order_default);
            GlobalVariables globalVariables3 = this.globalVariables;
            if (TextUtils.equals(stringPref, resources.getString(R.string.pref_node_desc_order_value_role_name_state_pos))) {
                i2 = 0;
            } else if (!TextUtils.equals(stringPref, resources.getString(R.string.pref_node_desc_order_value_state_name_role_pos))) {
                if (TextUtils.equals(stringPref, resources.getString(R.string.pref_node_desc_order_value_name_role_state_pos))) {
                    i2 = 2;
                } else {
                    LogUtils.e("TalkBackService", "Unhandled description order preference value \"%s\"", stringPref);
                }
            }
            globalVariables3.descriptionOrder = i2;
            this.globalVariables.speakElementIds = getBooleanPref(R.string.pref_speak_element_ids_key, R.bool.pref_speak_element_ids_default);
            this.globalVariables.usageHintEnabled = SpannableUtils$IdentifierSpan.getPreferenceValueBool(this.prefs, resources, resources.getString(R.string.pref_a11y_hints_key), resources.getBoolean(R.bool.pref_a11y_hints_default));
        }
        SharedPreferences sharedPreferences = this.prefs;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            int talkBackFocusStrokeWidth = SpannableUtils$IdentifierSpan.getTalkBackFocusStrokeWidth(sharedPreferences, resources);
            i = sharedPreferences.getInt(resources.getString(R.string.pref_border_color_key), resources.getColor(R.color.accessibility_focus_highlight_color, null));
            setAccessibilityFocusAppearance(talkBackFocusStrokeWidth, i);
        }
    }

    public final void requestDisableTalkBack(int i) {
        LogUtils.d("TalkBackService", "mis-triggering: requestDisableTalkBack  type=%d", Integer.valueOf(i));
        this.ipcClientCallback.hasRequestDisableTalkBack = true;
        this.analytics.sendLogImmediately(i);
        setTrainingFinished(false);
        disableSelf();
    }

    public final void requestServiceFlag(int i, boolean z) {
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        if (serviceInfo != null) {
            if (z) {
                if ((serviceInfo.flags & i) == i) {
                    return;
                }
            } else if ((serviceInfo.flags & i) == 0) {
                return;
            }
            if ((i & 2048) != 0 && this.displayIdToTouchInteractionMonitors.size() != 0) {
                i &= -2049;
            }
            if (z) {
                serviceInfo.flags |= i;
            } else {
                serviceInfo.flags &= ~i;
            }
            LogUtils.v("TalkBackService", "Accessibility Service flag changed: 0x%X", Integer.valueOf(serviceInfo.flags));
            setServiceInfo(serviceInfo);
            if ((i & 4) == 4 && z && shouldUseTalkbackGestureDetection()) {
                unregisterGestureDetection();
                registerGestureDetection();
            }
        }
    }

    public final void requestTouchExploration$ar$ds$dd7cb577_0(boolean z) {
        requestServiceFlag(4, z);
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        if (serviceInfo == null) {
            LogUtils.e("TalkBackService", "Failed to read touch exploration request state, service info was null", new Object[0]);
        } else {
            int i = serviceInfo.flags;
        }
    }

    final void setTrainingFinished(boolean z) {
        this.prefs.edit().putBoolean("has_training_exit", z).apply();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0029, code lost:
    
        if (r2 != 0) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean shouldShowTutorial() {
        /*
            r5 = this;
            com.google.android.accessibility.utils.FormFactorUtils r0 = r5.formFactorUtils
            boolean r0 = r0.isAndroidTv
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            android.content.SharedPreferences r0 = r5.prefs
            android.content.res.Resources r2 = r5.getResources()
            r3 = 2132084838(0x7f150866, float:1.9809858E38)
            r4 = 2131034161(0x7f050031, float:1.7678832E38)
            boolean r0 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getBooleanPref(r0, r2, r3, r4)
            android.content.ContentResolver r2 = r5.getContentResolver()
            java.lang.String r3 = "device_provisioned"
            r4 = 1
            int r2 = android.provider.Settings.Secure.getInt(r2, r3, r4)
            if (r0 == 0) goto L29
            if (r2 == 0) goto L28
            goto L2b
        L28:
            return r1
        L29:
            if (r2 == 0) goto L39
        L2b:
            boolean r0 = r5.isFirstTimeUser()
            if (r0 != 0) goto L39
            boolean r0 = r5.hasTrainingFinishedByUser()
            if (r0 != 0) goto L38
            goto L39
        L38:
            return r1
        L39:
            android.content.res.Resources r0 = r5.getResources()
            android.content.res.Configuration r0 = r0.getConfiguration()
            int r0 = r0.touchscreen
            if (r0 == r4) goto L4a
            boolean r0 = r5.supportsTouchScreen
            if (r0 == 0) goto L4a
            return r4
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.TalkBackService.shouldShowTutorial():boolean");
    }

    public final boolean shouldUseTalkbackGestureDetection() {
        if (this.useServiceGestureDetection == null) {
            this.useServiceGestureDetection = Boolean.valueOf(SpannableUtils$IdentifierSpan.getSharedPreferences(this).getBoolean(getString(R.string.pref_talkback_gesture_detection_key), getResources().getBoolean(R.bool.pref_talkback_gesture_detection_default)));
        }
        return this.useServiceGestureDetection.booleanValue();
    }

    public final void showTutorial() {
        Intent createTrainingIntent;
        Context applicationContext = getApplicationContext();
        if (FormFactorUtils.getInstance().isAndroidWear) {
            createTrainingIntent = TrainingActivity.createTrainingIntent(applicationContext, TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_FOR_WATCH);
        } else if (FormFactorUtils.getInstance().isAndroidTv) {
            createTrainingIntent = TrainingActivity.createTrainingIntent(applicationContext, TrainingConfig.TrainingId.TRAINING_ID_TUTORIAL_FOR_TV);
        } else {
            createTrainingIntent = TrainingActivity.createTrainingIntent(applicationContext, TrainingConfig.TrainingId.TRAINING_ID_FIRST_RUN_TUTORIAL, true);
        }
        startActivity(createTrainingIntent);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            DimScreenActor dimScreenActor = this.dimScreenController;
            if (dimScreenActor != null) {
                dimScreenActor.shutdown();
            }
            ListMenuManager listMenuManager = this.menuManager;
            if (listMenuManager != null && listMenuManager.isMenuShowing()) {
                this.menuManager.dismissAll();
            }
        } catch (Exception unused) {
        } catch (Throwable th2) {
            if (this.systemUeh != null && getServiceState() != 2) {
                this.systemUeh.uncaughtException(thread, th);
            } else {
                LogUtils.e("TalkBackService", "Received exception while shutting down.", th);
            }
            throw th2;
        }
        if (this.systemUeh != null && getServiceState() != 2) {
            this.systemUeh.uncaughtException(thread, th);
        } else {
            LogUtils.e("TalkBackService", "Received exception while shutting down.", th);
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                super.unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException unused) {
                LogUtils.e("TalkBackService", "Do not unregister receiver as it was never registered: ".concat(String.valueOf(broadcastReceiver.getClass().getSimpleName())), new Object[0]);
            }
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final boolean onGesture(AccessibilityGestureEvent accessibilityGestureEvent) {
        int displayId;
        int gestureId;
        displayId = accessibilityGestureEvent.getDisplayId();
        gestureId = accessibilityGestureEvent.getGestureId();
        if (!handleOnGestureById(displayId, gestureId)) {
            return false;
        }
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline.feedbackReturner;
        Logger logger = Performance.DEFAULT_LOGGER;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.gesture = new Feedback.Gesture(1, accessibilityGestureEvent);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        return true;
    }
}
