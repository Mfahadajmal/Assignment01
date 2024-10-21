package com.google.android.accessibility.talkback.actor;

import _COROUTINE._BOUNDARY;
import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda6;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.usb.UsbConnectManager$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.UserInterface$UserInputEventListener;
import com.google.android.accessibility.talkback.actor.gemini.GeminiOptInDialog;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.dynamicfeature.IconDetectionModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.ImageDescriptionModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter;
import com.google.android.accessibility.talkback.dynamicfeature.SplitApkDownloader;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.imagecaption.CharacterCaptionRequest;
import com.google.android.accessibility.talkback.imagecaption.IconDetectionRequest;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$AutomaticImageCaptioningState;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$DownloadStateListenerResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$FeatureSwitchDialogResources;
import com.google.android.accessibility.talkback.imagecaption.ImageCaptionConstants$ImageCaptionPreferenceKeys;
import com.google.android.accessibility.talkback.imagecaption.ImageDescriptionRequest;
import com.google.android.accessibility.talkback.imagecaption.RequestList;
import com.google.android.accessibility.talkback.imagecaption.ScreenshotCaptureRequest;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.ClassLoadingCache;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.libraries.accessibility.utils.screenunderstanding.VisualAnnotationPipeline;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.surveys.internal.utils.MetricsLogger;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineException;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ExecutionList;
import googledata.experiments.mobile.accessibility_suite.features.ImageCaption;
import j$.time.Duration;
import j$.util.Collection;
import j$.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageCaptioner extends Handler implements WindowEventInterpreter.WindowEventHandler, SharedPreferences.OnSharedPreferenceChangeListener, UserInterface$UserInputEventListener {
    public static final /* synthetic */ int ImageCaptioner$ar$NoOp = 0;
    private static final Duration TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES;
    private static final ExecutorService executorService;
    private static final boolean supportIconDetection = true;
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorState actorState;
    public final TalkBackAnalytics analytics;
    private final Map captionResults;
    public final RequestList characterCaptionRequests;
    public GeminiOptInDialog configImageDescriptionDialog;
    private GeminiOptInDialog geminiNanoOptInDialog;
    public GeminiOptInDialog geminiOptInDialog;
    WorkQueue iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private boolean iconAnnotationsDetectorStarted;
    public final IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter;
    public final RequestList iconDetectionRequests;
    public final AppLifecycleMonitor imageCaptionStorage$ar$class_merging$ar$class_merging$ar$class_merging;
    public final ImageDescriptionModuleDownloadPrompter imageDescriptionModuleDownloadPrompter;
    public ImageDescriptionProcessor imageDescriptionProcessor;
    public final RequestList imageDescriptionRequests;
    public boolean isImageDescriptionProcessorInitializing;
    public AccessibilityNodeInfoCompat nodeToBeDetailDescribed;
    public Pipeline.FeedbackReturner pipeline;
    public final SharedPreferences prefs;
    private final PrimesController primesController;
    public AccessibilityNodeInfoCompat queuedNode;
    private int requestId;
    public final RequestList screenshotRequests;
    public final AccessibilityService service;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.ImageCaptioner$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends GeminiOptInDialog {
        public AnonymousClass1(Context context) {
            super(context, R.string.title_gemini_opt_in_dialog, false, R.string.dialog_message_detailed_ai_promotion, R.string.positive_button_gemini_opt_in_dialog, R.string.negative_button_gemini_opt_in_dialog);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogClick(int i) {
            if (i != -2) {
                if (i == -1) {
                    ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_detailed_image_description_key), true).apply();
                    ImageCaptioner.this.analytics.onGeminiOptInEvent(2, true);
                    return;
                }
                return;
            }
            ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_detailed_image_description_key), false).apply();
            ImageCaptioner.this.analytics.onGeminiOptInEvent(3, true);
            int i2 = ImageCaptioner.this.prefs.getInt(this.context.getString(R.string.pref_gemini_repeat_opt_in_count_key), 0);
            if (i2 <= 0) {
                ImageCaptioner imageCaptioner = ImageCaptioner.this;
                Context context = this.context;
                SpannableUtils$IdentifierSpan.putIntPref(imageCaptioner.prefs, context.getResources(), R.string.pref_gemini_repeat_opt_in_count_key, i2 + 1);
            }
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogDismiss() {
            ImageCaptioner imageCaptioner = ImageCaptioner.this;
            if (imageCaptioner.nodeToBeDetailDescribed != null && SpannableUtils$IdentifierSpan.getBooleanPref(imageCaptioner.prefs, this.context.getResources(), R.string.pref_detailed_image_description_key, R.bool.pref_detailed_image_description_default)) {
                Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.performDetailedImageCaption(ImageCaptioner.this.nodeToBeDetailDescribed));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.ImageCaptioner$2 */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends GeminiOptInDialog {
        public AnonymousClass2(Context context) {
            super(context, R.string.title_on_device_gemini_opt_in_dialog, true, R.string.dialog_message_on_device_detailed_ai_promotion, R.string.positive_button_gemini_nano_opt_in_dialog, -1);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogClick(int i) {
            if (i != -2) {
                if (i != -1) {
                    return;
                }
                ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_auto_on_devices_image_description_key), true).apply();
                ImageCaptioner.this.analytics.onGeminiOptInEvent(2, false);
                return;
            }
            ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_auto_on_devices_image_description_key), false).apply();
            ImageCaptioner.this.analytics.onGeminiOptInEvent(3, false);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogDismiss() {
            ImageCaptioner imageCaptioner = ImageCaptioner.this;
            if (imageCaptioner.nodeToBeDetailDescribed != null && SpannableUtils$IdentifierSpan.getBooleanPref(imageCaptioner.prefs, this.context.getResources(), R.string.pref_auto_on_devices_image_description_key, R.bool.pref_auto_on_device_image_description_default)) {
                Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.performDetailedOnDeviceImageCaption(ImageCaptioner.this.nodeToBeDetailDescribed));
            }
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.ImageCaptioner$4 */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 extends Filter {
        final /* synthetic */ Rect val$nodeBounds;
        final /* synthetic */ Set val$selfAndAncestors;

        public AnonymousClass4(Rect rect, Set set) {
            r1 = rect;
            r2 = set;
        }

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            Rect rect = new Rect();
            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
            if (r1.contains(rect) && !r2.contains(accessibilityNodeInfoCompat) && r1.width() - rect.width() > r1.width() / 10 && r1.height() - rect.height() > r1.height() / 10) {
                return AccessibilityNodeInfoUtils.isFocusable(accessibilityNodeInfoCompat);
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CaptionResult {
        public Result iconLabelResult;
        public Result imageDescriptionResult;
        public boolean isGeminiFinishedWithError;
        private boolean isIconDetectionFinished;
        public boolean isImageDescriptionByGemini;
        private boolean isImageDescriptionFinished;
        private boolean isOcrFinished;
        public final boolean isUserRequest;
        public final AccessibilityNode node;
        public Result ocrTextResult;
        private final List screenshots = new ArrayList();

        public CaptionResult(AccessibilityNode accessibilityNode, boolean z, boolean z2) {
            this.node = accessibilityNode;
            this.isUserRequest = z;
            this.isImageDescriptionByGemini = z2;
        }

        public final void addUsedScreenshot(Bitmap bitmap) {
            this.screenshots.add(bitmap);
        }

        public final boolean isFinished() {
            if (this.isOcrFinished && this.isIconDetectionFinished && this.isImageDescriptionFinished) {
                return true;
            }
            return false;
        }

        public final void recycleAndClearScreenshots() {
            LogUtils.d("ImageCaptioner", "Recycle screenshots ", new Object[0]);
            Collection.EL.forEach(this.screenshots, new Connectioneer$AspectConnection$$ExternalSyntheticLambda6(8));
            this.screenshots.clear();
        }

        public final void setIconLabel(Result result) {
            this.isIconDetectionFinished = true;
            this.iconLabelResult = result;
        }

        public final void setImageDescription(Result result) {
            this.isImageDescriptionFinished = true;
            this.imageDescriptionResult = result;
        }

        public final void setOcrText(Result result) {
            this.isOcrFinished = true;
            this.ocrTextResult = result;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ManualDownloadStateListener implements ModuleDownloadPrompter.DownloadStateListener {
        private final TalkBackAnalytics analytics;
        private final Context context;
        private final ImageCaptioner imageCapitoner;
        private final ImageCaptionConstants$DownloadStateListenerResources listenerResources;
        private final TalkBackAnalytics.ImageCaptionLogKeys logKeys;
        private final int moduleSizeInMb;
        private final Pipeline.FeedbackReturner pipeline;
        private final ImageCaptionConstants$ImageCaptionPreferenceKeys preferenceKeys;
        private final SharedPreferences prefs;

        public ManualDownloadStateListener(Context context, Pipeline.FeedbackReturner feedbackReturner, TalkBackAnalytics talkBackAnalytics, ImageCaptioner imageCaptioner, ImageCaptionConstants$DownloadStateListenerResources imageCaptionConstants$DownloadStateListenerResources, ImageCaptionConstants$ImageCaptionPreferenceKeys imageCaptionConstants$ImageCaptionPreferenceKeys, int i, TalkBackAnalytics.ImageCaptionLogKeys imageCaptionLogKeys) {
            this.context = context;
            this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
            this.pipeline = feedbackReturner;
            this.analytics = talkBackAnalytics;
            this.imageCapitoner = imageCaptioner;
            this.listenerResources = imageCaptionConstants$DownloadStateListenerResources;
            this.preferenceKeys = imageCaptionConstants$ImageCaptionPreferenceKeys;
            this.moduleSizeInMb = i;
            this.logKeys = imageCaptionLogKeys;
        }

        private final void returnFeedback(CharSequence charSequence, boolean z) {
            if (z) {
                Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                speakOptions.mQueueMode = 6;
                speakOptions.mFlags = 2048;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(charSequence, speakOptions));
                return;
            }
            Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
            Logger logger2 = Performance.DEFAULT_LOGGER;
            SpeechController.SpeakOptions speakOptions2 = new SpeechController.SpeakOptions();
            speakOptions2.mFlags = 2048;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.speech(charSequence, speakOptions2));
        }

        private final void returnFeedbackUninterruptible(int i) {
            returnFeedbackUninterruptible(this.context.getString(i));
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public final void onAccepted() {
            this.analytics.onImageCaptionEvent(this.logKeys.installRequest);
            ImageCaptionConstants$ImageCaptionPreferenceKeys imageCaptionConstants$ImageCaptionPreferenceKeys = this.preferenceKeys;
            SpannableUtils$IdentifierSpan.putBooleanPref(this.prefs, this.context.getResources(), imageCaptionConstants$ImageCaptionPreferenceKeys.uninstalledKey, false);
            returnFeedbackUninterruptible(this.listenerResources.downloadingHint);
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public final void onDialogDismissed(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, (Performance.EventId) null, Feedback.focus(Feedback.Focus.Action.MUTE_NEXT_FOCUS));
            this.imageCapitoner.queuedNode = accessibilityNodeInfoCompat;
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public final void onFailed(int i) {
            this.analytics.onImageCaptionEvent(this.logKeys.installFail);
            if (i != 102) {
                if (i != 103) {
                    returnFeedbackUninterruptible(this.listenerResources.downloadFailedHint);
                    return;
                } else {
                    returnFeedbackUninterruptible(this.context.getString(R.string.download_storage_error_hint, Integer.valueOf(this.moduleSizeInMb)));
                    return;
                }
            }
            returnFeedbackUninterruptible(R.string.download_network_error_hint);
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public final void onInstalled() {
            this.analytics.onImageCaptionEvent(this.logKeys.installSuccess);
            returnFeedbackUninterruptible(this.listenerResources.downloadSuccessfulHint);
        }

        @Override // com.google.android.accessibility.talkback.dynamicfeature.ModuleDownloadPrompter.DownloadStateListener
        public final void onRejected() {
            this.analytics.onImageCaptionEvent(this.logKeys.installDeny);
            String string = this.context.getString(this.preferenceKeys.downloadShownTimesKey);
            this.prefs.edit().putInt(string, this.prefs.getInt(string, 0) + 1).apply();
            returnFeedback(this.context.getString(R.string.confirm_download_negative_button_hint), false);
        }

        private final void returnFeedbackUninterruptible(CharSequence charSequence) {
            returnFeedback(charSequence, true);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProcessedScreens {
        public final Bitmap blockedScreenCapture;
        public final Bitmap croppedScreenCapture;

        public ProcessedScreens() {
        }

        public final Bitmap blockedScreenCapture() {
            return this.blockedScreenCapture;
        }

        public final Bitmap croppedScreenCapture() {
            return this.croppedScreenCapture;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ProcessedScreens) {
                ProcessedScreens processedScreens = (ProcessedScreens) obj;
                if (this.blockedScreenCapture.equals(processedScreens.blockedScreenCapture()) && this.croppedScreenCapture.equals(processedScreens.croppedScreenCapture())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.blockedScreenCapture.hashCode() ^ 1000003) * 1000003) ^ this.croppedScreenCapture.hashCode();
        }

        public final String toString() {
            Bitmap bitmap = this.croppedScreenCapture;
            return "ProcessedScreens{blockedScreenCapture=" + this.blockedScreenCapture.toString() + ", croppedScreenCapture=" + bitmap.toString() + "}";
        }

        public ProcessedScreens(Bitmap bitmap, Bitmap bitmap2) {
            this();
            this.blockedScreenCapture = bitmap;
            if (bitmap2 == null) {
                throw new NullPointerException("Null croppedScreenCapture");
            }
            this.croppedScreenCapture = bitmap2;
        }
    }

    static {
        Duration ofSeconds;
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            ofSeconds = Duration.ofMillis(333L);
        } else {
            ofSeconds = Duration.ofSeconds(1L);
        }
        TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES = ofSeconds;
        executorService = Executors.newSingleThreadExecutor();
    }

    public ImageCaptioner(AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, AccessibilityFocusMonitor accessibilityFocusMonitor, TalkBackAnalytics talkBackAnalytics, PrimesController primesController) {
        super(Looper.myLooper());
        this.iconAnnotationsDetectorStarted = false;
        this.requestId = 0;
        this.characterCaptionRequests = new RequestList();
        this.iconDetectionRequests = new RequestList();
        this.imageDescriptionRequests = new RequestList();
        this.service = accessibilityService;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(accessibilityService);
        this.prefs = sharedPreferences;
        this.screenshotRequests = new RequestList(1, TAKE_SCREENSHOT_REQUEST_INTERVAL_TIMES);
        this.imageCaptionStorage$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.captionResults = new HashMap();
        this.analytics = talkBackAnalytics;
        this.primesController = primesController;
        SplitApkDownloader splitApkDownloader = SplitApkDownloader.getInstance(accessibilityService);
        splitApkDownloader.updateAllDownloadStatus();
        IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter = new IconDetectionModuleDownloadPrompter(accessibilityService, splitApkDownloader);
        this.iconDetectionModuleDownloadPrompter = iconDetectionModuleDownloadPrompter;
        this.imageDescriptionModuleDownloadPrompter = new ImageDescriptionModuleDownloadPrompter(accessibilityService, splitApkDownloader);
        SpannableUtils$IdentifierSpan.getSharedPreferences(accessibilityService).registerOnSharedPreferenceChangeListener(this);
        if (initIconDetection()) {
            if (!sharedPreferences.contains(accessibilityService.getString(ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION.switchKey)) && iconDetectionModuleDownloadPrompter.isModuleAvailable() && !iconDetectionModuleDownloadPrompter.isUninstalled()) {
                SpannableUtils$IdentifierSpan.putBooleanPref(sharedPreferences, accessibilityService.getResources(), ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION.switchKey, true);
            }
        } else {
            LogUtils.v("ImageCaptioner", "Icon detection is not initialized in ImageCaptioner()", new Object[0]);
        }
        if (!initImageDescription()) {
            LogUtils.v("ImageCaptioner", "Image description is not initialized in ImageCaptioner()", new Object[0]);
        }
        this.geminiOptInDialog = new GeminiOptInDialog(accessibilityService) { // from class: com.google.android.accessibility.talkback.actor.ImageCaptioner.1
            public AnonymousClass1(Context accessibilityService2) {
                super(accessibilityService2, R.string.title_gemini_opt_in_dialog, false, R.string.dialog_message_detailed_ai_promotion, R.string.positive_button_gemini_opt_in_dialog, R.string.negative_button_gemini_opt_in_dialog);
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogClick(int i) {
                if (i != -2) {
                    if (i == -1) {
                        ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_detailed_image_description_key), true).apply();
                        ImageCaptioner.this.analytics.onGeminiOptInEvent(2, true);
                        return;
                    }
                    return;
                }
                ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_detailed_image_description_key), false).apply();
                ImageCaptioner.this.analytics.onGeminiOptInEvent(3, true);
                int i2 = ImageCaptioner.this.prefs.getInt(this.context.getString(R.string.pref_gemini_repeat_opt_in_count_key), 0);
                if (i2 <= 0) {
                    ImageCaptioner imageCaptioner = ImageCaptioner.this;
                    Context context = this.context;
                    SpannableUtils$IdentifierSpan.putIntPref(imageCaptioner.prefs, context.getResources(), R.string.pref_gemini_repeat_opt_in_count_key, i2 + 1);
                }
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogDismiss() {
                ImageCaptioner imageCaptioner = ImageCaptioner.this;
                if (imageCaptioner.nodeToBeDetailDescribed != null && SpannableUtils$IdentifierSpan.getBooleanPref(imageCaptioner.prefs, this.context.getResources(), R.string.pref_detailed_image_description_key, R.bool.pref_detailed_image_description_default)) {
                    Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
                    Logger logger = Performance.DEFAULT_LOGGER;
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.performDetailedImageCaption(ImageCaptioner.this.nodeToBeDetailDescribed));
                }
            }
        };
        this.geminiNanoOptInDialog = new GeminiOptInDialog(accessibilityService2) { // from class: com.google.android.accessibility.talkback.actor.ImageCaptioner.2
            public AnonymousClass2(Context accessibilityService2) {
                super(accessibilityService2, R.string.title_on_device_gemini_opt_in_dialog, true, R.string.dialog_message_on_device_detailed_ai_promotion, R.string.positive_button_gemini_nano_opt_in_dialog, -1);
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogClick(int i) {
                if (i != -2) {
                    if (i != -1) {
                        return;
                    }
                    ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_auto_on_devices_image_description_key), true).apply();
                    ImageCaptioner.this.analytics.onGeminiOptInEvent(2, false);
                    return;
                }
                ImageCaptioner.this.prefs.edit().putBoolean(this.context.getString(R.string.pref_auto_on_devices_image_description_key), false).apply();
                ImageCaptioner.this.analytics.onGeminiOptInEvent(3, false);
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogDismiss() {
                ImageCaptioner imageCaptioner = ImageCaptioner.this;
                if (imageCaptioner.nodeToBeDetailDescribed != null && SpannableUtils$IdentifierSpan.getBooleanPref(imageCaptioner.prefs, this.context.getResources(), R.string.pref_auto_on_devices_image_description_key, R.bool.pref_auto_on_device_image_description_default)) {
                    Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
                    Logger logger = Performance.DEFAULT_LOGGER;
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.performDetailedOnDeviceImageCaption(ImageCaptioner.this.nodeToBeDetailDescribed));
                }
            }
        };
        this.configImageDescriptionDialog = new GeminiOptInDialog(accessibilityService2) { // from class: com.google.android.accessibility.talkback.actor.ImageCaptioner.3
            public AnonymousClass3(Context accessibilityService2) {
                super(accessibilityService2, R.string.dialog_title_turn_on_image_description, false, -1, R.string.positive_button_turn_on_image_descriptions_dialog, R.string.delete_dialog_negative_button_text);
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogClick(int i) {
                if (i != -1) {
                    return;
                }
                Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
                Logger logger = Performance.DEFAULT_LOGGER;
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.triggerIntent$ar$edu(5));
            }

            @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
            public final void handleDialogDismiss() {
            }
        };
    }

    private static void blackenBlock(Rect rect, int[] iArr, int i) {
        for (int i2 = rect.top; i2 < rect.bottom; i2++) {
            for (int i3 = rect.left; i3 < rect.right; i3++) {
                int i4 = (i2 * i) + i3;
                if (i4 < iArr.length && i4 >= 0) {
                    iArr[i4] = -16777216;
                } else {
                    LogUtils.e("ImageCaptioner", "blacken - invalid index", new Object[0]);
                    return;
                }
            }
        }
    }

    private final boolean canTakeScreenshot() {
        if (!SpannableUtils$IdentifierSpan.isAtLeastU() && this.actorState.getDimScreen$ar$class_merging$ar$class_merging().isDimmingEnabled()) {
            return false;
        }
        return true;
    }

    private final boolean detectionLibraryReady() {
        if (supportsIconDetection$ar$ds() && this.iconDetectionModuleDownloadPrompter.isModuleAvailable() && !this.iconDetectionModuleDownloadPrompter.isUninstalled()) {
            return true;
        }
        return false;
    }

    public static int getCaptionNodeType$ar$edu(Context context, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        CharSequence className;
        if (SpannableUtils$IdentifierSpan.needImageCaptionForUnlabelledView(context, accessibilityNodeInfoCompat)) {
            return 3;
        }
        if (ImageCaption.enableAutomaticCaptioningForAllImages(context)) {
            if (!SpannableUtils$IdentifierSpan.needImageCaptionForUnlabelledView(context, accessibilityNodeInfoCompat)) {
                if (SpannableUtils$IdentifierSpan.isCaptionable(context, accessibilityNodeInfoCompat)) {
                    if (z) {
                        if (accessibilityNodeInfoCompat != null && (className = accessibilityNodeInfoCompat.getClassName()) != null && ((ClassLoadingCache.checkInstanceOf(className, ImageView.class) && !ClassLoadingCache.checkInstanceOf(className, ImageButton.class)) || className.toString().equals("android.widget.Image") || WebInterfaceUtils.containsImage(accessibilityNodeInfoCompat))) {
                            Rect rect = new Rect();
                            accessibilityNodeInfoCompat.getBoundsInScreen(rect);
                            if (!rect.isEmpty()) {
                                if (Math.max(rect.height(), rect.width()) <= context.getResources().getDisplayMetrics().density * 60.0f) {
                                    return 1;
                                }
                            }
                        }
                    }
                }
            }
            return 2;
        }
        return 1;
    }

    private final void handleScreenshotCaptureFailure(boolean z) {
        this.analytics.onImageCaptionEvent(3);
        LogUtils.e("ImageCaptioner", "onScreenCaptureFinish() taking screenshot has failed.", new Object[0]);
        if (z) {
            returnFeedback(R.string.gemini_screenshot_unavailable);
        }
        this.screenshotRequests.performNextRequest();
    }

    public static boolean needAutomaticIconDetection$ar$edu(Context context, SharedPreferences sharedPreferences, int i) {
        return needAutomaticImageCaptioning$ar$edu(context, sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources.ICON_DETECTION, i);
    }

    private static boolean needAutomaticImageCaptioning$ar$edu(Context context, SharedPreferences sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources imageCaptionConstants$FeatureSwitchDialogResources, int i) {
        ImageCaptionConstants$AutomaticImageCaptioningState automaticImageCaptioningState = SpannableUtils$IdentifierSpan.getAutomaticImageCaptioningState(context, sharedPreferences, imageCaptionConstants$FeatureSwitchDialogResources);
        int ordinal = automaticImageCaptioningState.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    LogUtils.e("ImageCaptioner", "Invalid automatic image captioning state [%s]", automaticImageCaptioningState);
                    return true;
                }
                if (i != 3) {
                    return false;
                }
                return true;
            }
            if (i != 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean needAutomaticImageDescription$ar$edu(Context context, SharedPreferences sharedPreferences, int i, boolean z) {
        if (z) {
            return needAutomaticImageCaptioning$ar$edu(context, sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION_AICORE_OPT_IN, i);
        }
        return needAutomaticImageCaptioning$ar$edu(context, sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources.IMAGE_DESCRIPTION, i);
    }

    public static boolean needAutomaticTextRecognition$ar$edu(Context context, SharedPreferences sharedPreferences, int i) {
        return needAutomaticImageCaptioning$ar$edu(context, sharedPreferences, ImageCaptionConstants$FeatureSwitchDialogResources.TEXT_RECOGNITION, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0120  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final com.google.android.accessibility.talkback.actor.ImageCaptioner.ProcessedScreens processImageForNodeCaption(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r20, android.graphics.Bitmap r21, int r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 448
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.ImageCaptioner.processImageForNodeCaption(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, android.graphics.Bitmap, int, boolean):com.google.android.accessibility.talkback.actor.ImageCaptioner$ProcessedScreens");
    }

    private final void returnCaptionResult(CaptionResult captionResult) {
        returnCaptionResult(captionResult.ocrTextResult, captionResult.iconLabelResult, captionResult.imageDescriptionResult, captionResult.isUserRequest, captionResult.isImageDescriptionByGemini, captionResult.isGeminiFinishedWithError);
    }

    public static boolean supportsIconDetection$ar$ds() {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && SpannableUtils$IdentifierSpan.supportDynamicFeatures()) {
            return true;
        }
        return false;
    }

    public static boolean supportsImageCaption$ar$ds() {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && SpannableUtils$IdentifierSpan.supportDynamicFeatures()) {
            return true;
        }
        return false;
    }

    public static boolean supportsImageDescription(Context context) {
        if (SpannableUtils$IdentifierSpan.isAtLeastR() && SpannableUtils$IdentifierSpan.supportDynamicFeatures() && ImageCaption.INSTANCE.get().enableImageDescriptionV2(context)) {
            return true;
        }
        return false;
    }

    final void addCaptionRequest(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z) {
        this.characterCaptionRequests.addRequest(new CharacterCaptionRequest(i, accessibilityNodeInfoCompat, bitmap, new ImageCaptioner$$ExternalSyntheticLambda5(this, 1), new ImageCaptioner$$ExternalSyntheticLambda6(this, accessibilityNodeInfoCompat, 1), z));
    }

    final void addIconDetectionRequest(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z) {
        WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        Locale currentLanguage = ((LanguageActor) this.actorState.getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging().FloatingActionButton$ShadowDelegateImpl$ar$this$0).speechLanguage$ar$class_merging$ar$class_merging.getCurrentLanguage();
        if (currentLanguage == null) {
            currentLanguage = Locale.getDefault();
        }
        this.iconDetectionRequests.addRequest(new IconDetectionRequest(i, accessibilityNodeInfoCompat, bitmap, workQueue, currentLanguage, new ImageCaptioner$$ExternalSyntheticLambda5(this, 0), new ImageCaptioner$$ExternalSyntheticLambda6(this, accessibilityNodeInfoCompat, 0), z));
    }

    public final boolean caption(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        boolean z2;
        AppLifecycleMonitor.getCaptionResults$ar$ds(accessibilityNodeInfoCompat);
        this.analytics.onImageCaptionEvent(1);
        if (!canTakeScreenshot()) {
            this.analytics.onImageCaptionEvent(35);
            if (z) {
                returnFeedback(R.string.image_caption_with_hide_screen);
            }
            return false;
        }
        if (z) {
            this.analytics.onImageCaptionEvent(2);
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && !this.iconAnnotationsDetectorStarted) {
            startIconDetector();
        }
        this.screenshotRequests.addRequest(new ScreenshotCaptureRequest(this.service, accessibilityNodeInfoCompat, new HapticPatternParser$$ExternalSyntheticLambda1(this), new ImageCaptioner$$ExternalSyntheticLambda11(this, 2), z2));
        return true;
    }

    public final boolean captionWithGemini(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!canTakeScreenshot()) {
            return false;
        }
        this.screenshotRequests.addRequest(new ScreenshotCaptureRequest(this.service, accessibilityNodeInfoCompat, new HapticPatternParser$$ExternalSyntheticLambda1(this), new ImageCaptioner$$ExternalSyntheticLambda11(this, 0), true));
        return true;
    }

    public final boolean captionWithOnDeviceGemini(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!canTakeScreenshot()) {
            return false;
        }
        this.screenshotRequests.addRequest(new ScreenshotCaptureRequest(this.service, accessibilityNodeInfoCompat, new HapticPatternParser$$ExternalSyntheticLambda1(this), new ImageCaptioner$$ExternalSyntheticLambda11(this, 1), true));
        return true;
    }

    public final boolean clearCacheForView(Rect rect) {
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            return false;
        }
        clearPartialScreenCache(rect);
        AccessibilityNode.takeOwnership(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
        return true;
    }

    public final boolean clearPartialScreenCache(Rect rect) {
        WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (workQueue == null) {
            return false;
        }
        MetricsLogger metricsLogger = (MetricsLogger) workQueue.WorkQueue$ar$buffer;
        ((UiChangesTracker) metricsLogger.MetricsLogger$ar$logSessionId).onPartialUiChange(rect);
        UiChangesTracker uiChangesTracker = (UiChangesTracker) metricsLogger.MetricsLogger$ar$logSessionId;
        metricsLogger.updateLatestResultsState(uiChangesTracker.isWholeScreenChangeObserved(), uiChangesTracker.getDirtyRegion());
        if (workQueue.isProcessingScreenshot()) {
            ((UiChangesTracker) workQueue.WorkQueue$ar$lastScheduledTask).onPartialUiChange(rect);
            return true;
        }
        return true;
    }

    public final boolean clearWholeScreenCache() {
        WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (workQueue == null) {
            return false;
        }
        MetricsLogger metricsLogger = (MetricsLogger) workQueue.WorkQueue$ar$buffer;
        ((UiChangesTracker) metricsLogger.MetricsLogger$ar$logSessionId).onWholeScreenChange();
        metricsLogger.updateLatestResultsState(true, null);
        if (workQueue.isProcessingScreenshot()) {
            ((UiChangesTracker) workQueue.WorkQueue$ar$lastScheduledTask).onWholeScreenChange();
        }
        return true;
    }

    public final boolean confirmDownloadAndPerformCaption(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        boolean z;
        boolean z2;
        ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType;
        if (!canTakeScreenshot()) {
            this.analytics.onImageCaptionEvent(35);
            returnFeedback(R.string.image_caption_with_hide_screen);
            return true;
        }
        if (detectionLibraryReady() && this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            initIconDetection();
        }
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            z = true;
        } else {
            z = false;
        }
        if (descriptionLibraryReady() && this.imageDescriptionProcessor == null) {
            initImageDescription();
        }
        if (this.imageDescriptionProcessor == null && !this.isImageDescriptionProcessorInitializing) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (supportsImageDescription(this.service)) {
            if (SpannableUtils$IdentifierSpan.isSmallSizeNode(this.service, accessibilityNodeInfoCompat)) {
                imageCaptionUtils$CaptionType = ImageCaptionUtils$CaptionType.ICON_LABEL;
            } else {
                imageCaptionUtils$CaptionType = ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION;
            }
        } else {
            imageCaptionUtils$CaptionType = ImageCaptionUtils$CaptionType.ICON_LABEL;
        }
        LogUtils.v("ImageCaptioner", "iconDetectionAvailable=%s, imageDescriptionAvailable=%s, captionType = %s", Boolean.valueOf(z), Boolean.valueOf(z2), imageCaptionUtils$CaptionType);
        if (imageCaptionUtils$CaptionType != ImageCaptionUtils$CaptionType.ICON_LABEL || z ? !(imageCaptionUtils$CaptionType != ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION || z2 || !showImageDescriptionDownloadDialog$ar$edu(accessibilityNodeInfoCompat, 1)) : showIconDetectionDownloadDialog$ar$edu(accessibilityNodeInfoCompat, 1)) {
            return false;
        }
        caption(accessibilityNodeInfoCompat, true);
        return true;
    }

    public final boolean descriptionLibraryReady() {
        if (supportsImageDescription(this.service) && this.imageDescriptionModuleDownloadPrompter.isModuleAvailable() && !this.imageDescriptionModuleDownloadPrompter.isUninstalled()) {
            return true;
        }
        return false;
    }

    public final void geminiOnDeviceOptInForManualTrigger$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.nodeToBeDetailDescribed = accessibilityNodeInfoCompat;
        this.geminiNanoOptInDialog.showDialog();
        this.analytics.onGeminiOptInEvent(1, false);
    }

    @Override // com.google.android.accessibility.utils.input.WindowEventInterpreter.WindowEventHandler
    public final void handle(WindowEventInterpreter.EventInterpretation eventInterpretation, Performance.EventId eventId) {
        if (eventInterpretation.windowsStable && this.queuedNode != null) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.performImageCaptions(this.queuedNode, true));
            this.queuedNode = null;
        }
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 0) {
            CaptionResult captionResult = (CaptionResult) this.captionResults.get(Integer.valueOf(message.arg1));
            if (captionResult != null) {
                if (captionResult.node.equals(AccessibilityNode.takeOwnership(this.accessibilityFocusMonitor.getAccessibilityFocus(false)))) {
                    returnCaptionResult(captionResult);
                }
            }
            LogUtils.w("ImageCaptioner", "Caption request is timeout.", new Object[0]);
            this.screenshotRequests.clear();
            this.characterCaptionRequests.clear();
            this.iconDetectionRequests.clear();
            this.imageDescriptionRequests.clear();
            Map.EL.forEach(this.captionResults, new UsbConnectManager$$ExternalSyntheticLambda0(8));
            this.captionResults.clear();
        }
    }

    public final void handleResult(int i, AccessibilityNode accessibilityNode, Result result, boolean z) {
        boolean z2;
        boolean z3;
        AccessibilityNode takeOwnership = AccessibilityNode.takeOwnership(this.accessibilityFocusMonitor.getAccessibilityFocus(false));
        java.util.Map map = this.captionResults;
        Integer valueOf = Integer.valueOf(i);
        CaptionResult captionResult = (CaptionResult) map.get(valueOf);
        if (accessibilityNode != null) {
            z2 = accessibilityNode.equals(takeOwnership);
        } else {
            z2 = false;
        }
        if (!z && (!z2 || Math.max(0, this.screenshotRequests.requests$ar$class_merging$ar$class_merging.size() - 1) > 0)) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (captionResult == null) {
            if (z3) {
                int ordinal = result.type.ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal == 2) {
                            returnCaptionResult(null, null, result, z, false, false);
                        }
                    } else {
                        returnCaptionResult(null, result, null, z, false, false);
                    }
                } else {
                    returnCaptionResult(result, null, null, z, false, false);
                }
            }
            this.screenshotRequests.performNextRequest();
        } else {
            int ordinal2 = result.type.ordinal();
            if (ordinal2 != 0) {
                if (ordinal2 != 1) {
                    if (ordinal2 == 2) {
                        captionResult.setImageDescription(result);
                        if (!z3) {
                            if (this.imageDescriptionProcessor != null && !captionResult.isImageDescriptionByGemini) {
                                this.analytics.onImageCaptionEvent(25);
                            }
                            z3 = false;
                        }
                        z3 = true;
                    }
                } else {
                    captionResult.setIconLabel(result);
                    if (!z3) {
                        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                            this.analytics.onImageCaptionEvent(13);
                        }
                        z3 = false;
                    }
                    z3 = true;
                }
            } else {
                captionResult.setOcrText(result);
                if (!z3) {
                    this.analytics.onImageCaptionEvent(14);
                    z3 = false;
                }
                z3 = true;
            }
            if (captionResult.isFinished()) {
                if (z3 && (!z || !captionResult.isImageDescriptionByGemini)) {
                    returnCaptionResult(captionResult);
                }
                this.screenshotRequests.performNextRequest();
                captionResult.recycleAndClearScreenshots();
                this.captionResults.remove(valueOf);
                removeMessages(0);
            }
        }
        if (this.captionResults.isEmpty()) {
            removeMessages(0);
        }
        if (captionResult == null) {
            LogUtils.v("ImageCaptioner", "captionRequest is null", new Object[0]);
        } else {
            LogUtils.v("ImageCaptioner", "captionRequest finish=%b isUserRequested=%b, focused=%b", Boolean.valueOf(captionResult.isFinished()), Boolean.valueOf(z), Boolean.valueOf(accessibilityNode.equals(takeOwnership)));
        }
    }

    public final void handleResultFromGemini$ar$edu$ar$ds(int i, String str, boolean z, int i2, boolean z2) {
        AccessibilityNode accessibilityNode;
        Integer valueOf = Integer.valueOf(i);
        LogUtils.d("ImageCaptioner", "handleResultFromGemini, id = %d, isSuccess= %s, manualTrigger= %s", valueOf, Boolean.valueOf(z), Boolean.valueOf(z2));
        CaptionResult captionResult = (CaptionResult) this.captionResults.get(valueOf);
        if (captionResult != null) {
            accessibilityNode = captionResult.node;
        } else {
            accessibilityNode = null;
        }
        if (z && !z2) {
            handleResult(i, accessibilityNode, new Result(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, str, 1.0f), false);
            return;
        }
        if (i2 == 4 && !z2 && captionResult != null) {
            captionResult.isGeminiFinishedWithError = true;
            handleResult(i, accessibilityNode, new Result(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, str, 1.0f), false);
        } else {
            handleResult(i, accessibilityNode, new Result(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, "", 1.0f), z2);
        }
    }

    public final void handleScreenshotCaptureResponse(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z, boolean z2) {
        boolean z3;
        if (bitmap == null) {
            handleScreenshotCaptureFailure(z);
            return;
        }
        LogUtils.v("ImageCaptioner", "onScreenCaptureFinish() taking screenshot is successful.", new Object[0]);
        if (this.actorState.getGeminiState$ar$class_merging$ar$class_merging$ar$class_merging().hasAiCore()) {
            if (!z) {
                LogUtils.v("ImageCaptioner", "onScreenCaptureFinish(): auto-caption with Gemini", new Object[0]);
                processImageCaptioningWithGemini(accessibilityNodeInfoCompat, bitmap, false, z2, true);
                return;
            }
            LogUtils.w("ImageCaptioner", "Shouldn't trigger manual image description with AiCore here.", new Object[0]);
        }
        this.requestId++;
        ProcessedScreens processImageForNodeCaption = processImageForNodeCaption(accessibilityNodeInfoCompat, bitmap, SpannableUtils$IdentifierSpan.getTalkBackFocusStrokeWidth(this.prefs, this.service.getResources()), z2);
        CaptionResult captionResult = new CaptionResult(AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat), z, false);
        this.captionResults.put(Integer.valueOf(this.requestId), captionResult);
        captionResult.addUsedScreenshot(processImageForNodeCaption.blockedScreenCapture);
        if (!processImageForNodeCaption.croppedScreenCapture.equals(processImageForNodeCaption.blockedScreenCapture)) {
            captionResult.addUsedScreenshot(processImageForNodeCaption.croppedScreenCapture);
        }
        removeMessages(0);
        Message message = new Message();
        message.what = 0;
        message.arg1 = this.requestId;
        sendMessageAtTime(message, System.currentTimeMillis() + 5000);
        int captionNodeType$ar$edu = getCaptionNodeType$ar$edu(this.service, accessibilityNodeInfoCompat, false);
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && (z || needAutomaticIconDetection$ar$edu(this.service, this.prefs, captionNodeType$ar$edu))) {
            this.analytics.onImageCaptionEvent(5);
            addIconDetectionRequest(this.requestId, accessibilityNodeInfoCompat, processImageForNodeCaption.blockedScreenCapture, z);
            z3 = true;
        } else {
            captionResult.setIconLabel(null);
            z3 = false;
        }
        if (!z && !needAutomaticTextRecognition$ar$edu(this.service, this.prefs, captionNodeType$ar$edu)) {
            captionResult.setOcrText(null);
        } else {
            this.analytics.onImageCaptionEvent(9);
            addCaptionRequest(this.requestId, accessibilityNodeInfoCompat, processImageForNodeCaption.croppedScreenCapture, z);
            z3 = true;
        }
        if (this.imageDescriptionProcessor == null) {
            captionResult.setImageDescription(null);
            if (this.isImageDescriptionProcessorInitializing) {
                LogUtils.v("ImageCaptioner", "Image description process is initializing...", new Object[0]);
            }
        } else {
            if (!z) {
                AccessibilityService accessibilityService = this.service;
                if (!needAutomaticImageDescription$ar$edu(accessibilityService, this.prefs, getCaptionNodeType$ar$edu(accessibilityService, accessibilityNodeInfoCompat, true), false)) {
                    captionResult.setImageDescription(null);
                }
            }
            this.analytics.onImageCaptionEvent(21);
            this.imageDescriptionRequests.addRequest(new ImageDescriptionRequest(this.requestId, this.service, accessibilityNodeInfoCompat, processImageForNodeCaption.croppedScreenCapture, this.imageDescriptionProcessor, new ImageCaptioner$$ExternalSyntheticLambda5(this, 2), new ImageCaptioner$$ExternalSyntheticLambda6(this, accessibilityNodeInfoCompat, 2), z));
            return;
        }
        if (z3) {
            return;
        }
        LogUtils.v("ImageCaptioner", "No caption request for the screenshot. Perform the next screenshot request.", new Object[0]);
        this.screenshotRequests.performNextRequest();
    }

    public final boolean initIconDetection() {
        if (!detectionLibraryReady()) {
            return false;
        }
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            return true;
        }
        SplitCompat.installActivity(this.service);
        try {
            this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new WorkQueue(this.service.getApplicationContext(), (byte[]) null);
            startIconDetector();
            return true;
        } catch (UnsatisfiedLinkError e) {
            LogUtils.e("ImageCaptioner", e.getMessage(), new Object[0]);
            return false;
        }
    }

    public final boolean initImageDescription() {
        if (!descriptionLibraryReady()) {
            return false;
        }
        if (this.imageDescriptionProcessor == null && !this.isImageDescriptionProcessorInitializing) {
            SplitCompat.installActivity(this.service);
            this.isImageDescriptionProcessorInitializing = true;
            executorService.submit(new WorkerWrapper$$ExternalSyntheticLambda0(this, 4));
        }
        return true;
    }

    /* renamed from: lambda$initImageDescription$0$com-google-android-accessibility-talkback-actor-ImageCaptioner */
    public final /* synthetic */ Boolean m95x7b45e1cf() {
        LogUtils.v("ImageCaptioner", "Creating ImageDescriptionProcessor...", new Object[0]);
        boolean z = true;
        try {
            if (this.imageDescriptionProcessor == null) {
                try {
                    this.imageDescriptionProcessor = new ImageDescriptionProcessor(this.service, this.analytics);
                    LogUtils.v("ImageCaptioner", "ImageDescriptionProcessor initialized.", new Object[0]);
                } catch (UnsatisfiedLinkError e) {
                    LogUtils.e("ImageCaptioner", e.getMessage(), new Object[0]);
                    z = false;
                }
                return z;
            }
            LogUtils.v("ImageCaptioner", "ImageDescriptionProcessor created", new Object[0]);
            return true;
        } finally {
            this.isImageDescriptionProcessorInitializing = false;
        }
    }

    public final boolean needDownloadDialog$ar$edu$8a2eb55_0(ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType, int i) {
        int ordinal = imageCaptionUtils$CaptionType.ordinal();
        if (ordinal != 1) {
            if (ordinal == 2 && supportsImageDescription(this.service)) {
                return this.imageDescriptionModuleDownloadPrompter.needDownloadDialog$ar$edu(i);
            }
            return false;
        }
        if (supportsIconDetection$ar$ds()) {
            return this.iconDetectionModuleDownloadPrompter.needDownloadDialog$ar$edu(i);
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void newItemFocused$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(AccessibilityNodeInfo accessibilityNodeInfo, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        CaptionResult captionResult = (CaptionResult) this.captionResults.get(Integer.valueOf(this.requestId));
        if (captionResult != null && !captionResult.isFinished() && !captionResult.isUserRequest) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            Feedback.GeminiRequest.Builder builder2 = Feedback.GeminiRequest.builder();
            builder2.setAction$ar$edu$e15164a1_0$ar$ds(3);
            builder.geminiRequest = builder2.build();
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (this.service.getString(ImageCaptionConstants$ImageCaptionPreferenceKeys.ICON_DETECTION.uninstalledKey).equals(str) && this.iconDetectionModuleDownloadPrompter.isUninstalled()) {
            this.iconDetectionRequests.clear();
            shutdownIconDetector();
        } else if (this.service.getString(ImageCaptionConstants$ImageCaptionPreferenceKeys.IMAGE_DESCRIPTION.uninstalledKey).equals(str) && this.imageDescriptionModuleDownloadPrompter.isUninstalled()) {
            this.imageDescriptionRequests.clear();
            shutdownImageDescription();
        }
    }

    public final void primeLogImageCaptionEvent(PrimesController.TimerAction timerAction, long j) {
        if (j > 0) {
            SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = this.primesController.logger$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            SpannableUtils$NonCopyableTextSpan.recordDuration$ar$ds(timerAction.noPiiString, 0L, j, null);
        }
    }

    public final void processImageCaptioningWithGemini(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, Bitmap bitmap, boolean z, boolean z2, boolean z3) {
        if (bitmap == null) {
            handleScreenshotCaptureFailure(z);
            return;
        }
        this.requestId++;
        ProcessedScreens processImageForNodeCaption = processImageForNodeCaption(accessibilityNodeInfoCompat, bitmap, SpannableUtils$IdentifierSpan.getTalkBackFocusStrokeWidth(this.prefs, this.service.getResources()), z2);
        CaptionResult captionResult = new CaptionResult(AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat), z, true);
        this.captionResults.put(Integer.valueOf(this.requestId), captionResult);
        captionResult.addUsedScreenshot(processImageForNodeCaption.croppedScreenCapture);
        if (!processImageForNodeCaption.croppedScreenCapture.equals(processImageForNodeCaption.blockedScreenCapture)) {
            captionResult.addUsedScreenshot(processImageForNodeCaption.blockedScreenCapture);
        }
        int captionNodeType$ar$edu = getCaptionNodeType$ar$edu(this.service, accessibilityNodeInfoCompat, false);
        if (!z && needAutomaticTextRecognition$ar$edu(this.service, this.prefs, captionNodeType$ar$edu)) {
            this.analytics.onImageCaptionEvent(9);
            addCaptionRequest(this.requestId, accessibilityNodeInfoCompat, processImageForNodeCaption.croppedScreenCapture, false);
        } else {
            captionResult.setOcrText(null);
        }
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && !z && needAutomaticIconDetection$ar$edu(this.service, this.prefs, captionNodeType$ar$edu)) {
            this.analytics.onImageCaptionEvent(5);
            addIconDetectionRequest(this.requestId, accessibilityNodeInfoCompat, processImageForNodeCaption.blockedScreenCapture, false);
        } else {
            captionResult.setIconLabel(null);
        }
        if (!z) {
            AccessibilityService accessibilityService = this.service;
            if (!needAutomaticImageDescription$ar$edu(accessibilityService, this.prefs, getCaptionNodeType$ar$edu(accessibilityService, accessibilityNodeInfoCompat, true), true)) {
                captionResult.setImageDescription(null);
                this.screenshotRequests.performNextRequest();
            }
        }
        if (z3) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            int i = this.requestId;
            Bitmap bitmap2 = processImageForNodeCaption.croppedScreenCapture;
            Feedback.Part.Builder builder = Feedback.Part.builder();
            Feedback.GeminiRequest.Builder builder2 = Feedback.GeminiRequest.builder();
            builder2.setManualTrigger$ar$ds(z);
            builder2.setAction$ar$edu$e15164a1_0$ar$ds(2);
            builder2.setRequestId$ar$ds(i);
            builder2.image = bitmap2;
            builder.geminiRequest = builder2.build();
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, builder);
        } else {
            Pipeline.FeedbackReturner feedbackReturner2 = this.pipeline;
            Logger logger2 = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner2, (Performance.EventId) null, Feedback.geminiRequest(this.requestId, this.service.getResources().getString(R.string.image_caption_with_gemini_prefix), processImageForNodeCaption.croppedScreenCapture));
        }
        this.screenshotRequests.performNextRequest();
    }

    public final void returnFeedback(int i) {
        returnFeedback(this.service.getString(i));
    }

    public final boolean showIconDetectionDownloadDialog$ar$edu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        if (needDownloadDialog$ar$edu$8a2eb55_0(ImageCaptionUtils$CaptionType.ICON_LABEL, i)) {
            IconDetectionModuleDownloadPrompter iconDetectionModuleDownloadPrompter = this.iconDetectionModuleDownloadPrompter;
            iconDetectionModuleDownloadPrompter.queuedNode = accessibilityNodeInfoCompat;
            iconDetectionModuleDownloadPrompter.showDownloadDialog$ar$edu(i);
            return true;
        }
        return false;
    }

    public final boolean showImageDescriptionDownloadDialog$ar$edu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        if (needDownloadDialog$ar$edu$8a2eb55_0(ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION, i)) {
            ImageDescriptionModuleDownloadPrompter imageDescriptionModuleDownloadPrompter = this.imageDescriptionModuleDownloadPrompter;
            imageDescriptionModuleDownloadPrompter.queuedNode = accessibilityNodeInfoCompat;
            imageDescriptionModuleDownloadPrompter.showDownloadDialog$ar$edu(i);
            return true;
        }
        return false;
    }

    public final void shutdownIconDetector() {
        removeMessages(0);
        this.captionResults.clear();
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            synchronized (this) {
                if (this.iconAnnotationsDetectorStarted) {
                    this.iconAnnotationsDetectorStarted = false;
                    ((com.google.android.libraries.vision.visionkit.pipeline.Pipeline) ((ExecutionList.RunnableExecutorPair) this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$producerIndex).ExecutionList$RunnableExecutorPair$ar$runnable).close();
                }
            }
            this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = null;
        }
    }

    public final void shutdownImageDescription() {
        LogUtils.v("ImageCaptioner", "shutdownImageDescription isImageDescriptionProcessorInitializing=%b", Boolean.valueOf(this.isImageDescriptionProcessorInitializing));
        executorService.submit(new WorkerWrapper$$ExternalSyntheticLambda0(this, 5));
    }

    final void startIconDetector() {
        if (this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            synchronized (this) {
                if (!this.iconAnnotationsDetectorStarted) {
                    boolean z = true;
                    this.iconAnnotationsDetectorStarted = true;
                    WorkQueue workQueue = this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                    Object obj = workQueue.WorkQueue$ar$producerIndex;
                    ((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$next = workQueue;
                    ((VisualAnnotationPipeline) ((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$runnable).listener$ar$class_merging$643a5d1f_0$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(obj, null);
                    if (((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$next == null) {
                        z = false;
                    }
                    ContextDataProvider.checkState(z, "Call setListener before starting the UnderstandingManager.");
                    Object obj2 = ((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$runnable;
                    long j = ((com.google.android.libraries.vision.visionkit.pipeline.Pipeline) obj2).nativeContext;
                    if (j != 0) {
                        try {
                            ((com.google.android.libraries.vision.visionkit.pipeline.Pipeline) obj2).nativePipeline.start(j);
                        } catch (PipelineException e) {
                            throw new IllegalStateException("Pipeline did not start successfully.", e);
                        }
                    } else {
                        throw new IllegalStateException("Pipeline has been closed or was not initialized");
                    }
                }
            }
        }
    }

    private final void returnCaptionResult(Result result, Result result2, Result result3, boolean z, boolean z2, boolean z3) {
        String constructCaptionTextForAuto;
        if (Result.isEmpty(result2) && this.iconAnnotationsDetector$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
            this.analytics.onImageCaptionEvent(7);
        }
        if (Result.isEmpty(result)) {
            this.analytics.onImageCaptionEvent(11);
        }
        if (Result.isEmpty(result3) && this.imageDescriptionProcessor != null && !z2) {
            this.analytics.onImageCaptionEvent(23);
        }
        if (z3 && !Result.isEmpty(result3) && Result.isEmpty(result2) && Result.isEmpty(result)) {
            returnFeedback(result3.text);
            return;
        }
        if (z) {
            AccessibilityService accessibilityService = this.service;
            StringBuilder sb = new StringBuilder();
            if (!Result.isEmpty(result3)) {
                sb.append(SpannableUtils$IdentifierSpan.constructImageDescriptionText(accessibilityService, result3));
            }
            if (!Result.isEmpty(result2)) {
                sb.append(accessibilityService.getString(R.string.detected_icon_label, result2.text));
            }
            if (!Result.isEmpty(result)) {
                sb.append(accessibilityService.getString(R.string.detected_recognized_text, result.text));
            }
            if (TextUtils.isEmpty(sb)) {
                constructCaptionTextForAuto = accessibilityService.getString(R.string.image_caption_no_result);
            } else {
                constructCaptionTextForAuto = accessibilityService.getString(R.string.detected_result, sb);
            }
        } else {
            constructCaptionTextForAuto = SpannableUtils$IdentifierSpan.constructCaptionTextForAuto(this.service, result3, result2, result);
        }
        returnFeedback(constructCaptionTextForAuto);
    }

    private final void returnFeedback(CharSequence charSequence) {
        Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
        Logger logger = Performance.DEFAULT_LOGGER;
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 2048;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.speech(charSequence, speakOptions));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.ImageCaptioner$3 */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 extends GeminiOptInDialog {
        public AnonymousClass3(Context accessibilityService2) {
            super(accessibilityService2, R.string.dialog_title_turn_on_image_description, false, -1, R.string.positive_button_turn_on_image_descriptions_dialog, R.string.delete_dialog_negative_button_text);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogClick(int i) {
            if (i != -1) {
                return;
            }
            Pipeline.FeedbackReturner feedbackReturner = ImageCaptioner.this.pipeline;
            Logger logger = Performance.DEFAULT_LOGGER;
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, (Performance.EventId) null, Feedback.triggerIntent$ar$edu(5));
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogDismiss() {
        }
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void editTextOrSelectableTextSelected(boolean z) {
    }

    @Override // com.google.android.accessibility.talkback.UserInterface$UserInputEventListener
    public final void touchInteractionState(boolean z) {
    }
}
