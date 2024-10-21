package com.google.android.accessibility.selecttospeak;

import android.accessibilityservice.AccessibilityService;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Toast;
import androidx.activity.OnBackPressedDispatcher;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.os.UserManagerCompat$Api24Impl;
import androidx.core.text.ICUCompat$Api24Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.lifecycle.Lifecycle;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.brailleime.dialog.ContextMenuDialog$$ExternalSyntheticLambda5;
import com.google.android.accessibility.selecttospeak.PrimesController;
import com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity;
import com.google.android.accessibility.selecttospeak.debug.SerializableSnapshotHelper;
import com.google.android.accessibility.selecttospeak.feedback.SelectToSpeakJob;
import com.google.android.accessibility.selecttospeak.feedback.SpeechRateController;
import com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder;
import com.google.android.accessibility.selecttospeak.iterator.ImprovedSelectionFinder$takeScreenShot$2;
import com.google.android.accessibility.selecttospeak.iterator.Sentence;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIterator;
import com.google.android.accessibility.selecttospeak.iterator.SentenceIteratorFactory;
import com.google.android.accessibility.selecttospeak.lifecycle.ServiceState;
import com.google.android.accessibility.selecttospeak.logging.S2SPipelineAnalytics;
import com.google.android.accessibility.selecttospeak.logging.SelectToSpeakLogSender;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakJobModel$jobUpdateListener$1;
import com.google.android.accessibility.selecttospeak.popup.SelectToSpeakPopupActivity$$ExternalSyntheticLambda4;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SEntryPoint;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SSettings;
import com.google.android.accessibility.selecttospeak.services.StreamVolumeController;
import com.google.android.accessibility.selecttospeak.tts.VoiceUtil;
import com.google.android.accessibility.selecttospeak.ui.OutsideTouchListener;
import com.google.android.accessibility.selecttospeak.ui.TriggerButtonDragActionDetector;
import com.google.android.accessibility.talkback.TalkBackService$$ExternalSyntheticLambda8;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor;
import com.google.android.accessibility.utils.monitor.ScreenMonitor;
import com.google.android.accessibility.utils.ocr.OcrController;
import com.google.android.accessibility.utils.ocr.OcrInfo;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.accessibility.utils.screencapture.ScreenCaptureController;
import com.google.android.libraries.performance.primes.Primes;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.libraries.security.app.SaferPendingIntent;
import com.google.android.marvin.talkback.R;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfig;
import io.grpc.internal.RetryingNameResolver;
import io.grpc.okhttp.internal.OptionalMethod;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;
import kotlinx.coroutines.Job;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SelectToSpeakService extends AccessibilityService implements Thread.UncaughtExceptionHandler, SpeechController.Delegate, AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback, ScreenMonitor.ScreenStateChangeListener {
    private static final Filter FILTER_WEBVIEW = new Filter() { // from class: com.google.android.accessibility.selecttospeak.SelectToSpeakService.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) == 15) {
                return true;
            }
            return false;
        }
    };
    public static final long WAKE_LOCK_TIMEOUT_MS = TimeUnit.MINUTES.toMillis(10);
    private static WeakReference service;
    private AccessibilityButtonMonitor accessibilityButtonMonitor;
    private Configuration cachedConfig;
    private OnDeviceTextDetectionLoadLogEvent coordinateConverter$ar$class_merging$ar$class_merging;
    private ImprovedSelectionFinder improvedSelectionFinder;
    public Rect mlastSelection;
    public S2SPipelineAnalytics pipelineAnalytics;
    public SharedPreferences prefs;
    private PrimesController primesController;
    public ScreenCaptureController screenCaptureController;
    public ScreenCapturePermissionHelper screenCapturePermissionHelper;
    private ScreenMonitor screenMonitor;
    public Bitmap screenshot;
    public OptionalMethod selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
    private OptionalMethod selectionFinder$ar$class_merging$ar$class_merging$ar$class_merging;
    private SpeechControllerImpl speechController;
    private StreamVolumeController streamVolumeController;
    private Thread.UncaughtExceptionHandler systemUeh;
    public UIManager uIManager;
    public PowerManager.WakeLock wakeLock;
    public long lastTimeAccessibilityButtonClicked = 0;
    public final ServiceState serviceState = new ServiceState();
    private boolean isUIInitialized = false;
    public boolean isMultitaskingSettingEnabled = false;
    public final BroadcastReceiver userUnlockedBroadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.selecttospeak.SelectToSpeakService.2
        public AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            SelectToSpeakService selectToSpeakService = SelectToSpeakService.this;
            selectToSpeakService.unregisterReceiver(selectToSpeakService.userUnlockedBroadcastReceiver);
            SelectToSpeakService selectToSpeakService2 = SelectToSpeakService.this;
            if (SpannableUtils$IdentifierSpan.getBooleanPref(selectToSpeakService2.prefs, selectToSpeakService2.getResources(), R.string.s2s_pref_ocr_key, R.bool.s2s_pref_ocr_default) && !SpannableUtils$IdentifierSpan.isAtLeastR()) {
                SelectToSpeakService.this.screenCapturePermissionHelper.requestForPermission(null);
            }
            if (SelectToSpeakService.this.uIManager.isNarrowScreen()) {
                SelectToSpeakService.this.onDensityDpiOrLayoutDirectionChanged();
            }
        }
    };
    private final RetryingNameResolver.ResolutionResultListener controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    public final RetryingNameResolver.ResolutionResultListener selectionCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this);
    private final View.OnClickListener triggerButtonClickListener = new SelectToSpeakPopupActivity$$ExternalSyntheticLambda4(1);
    public final OutsideTouchListener controlPanelOverlayOutsideTouchListener = new OutsideTouchListener(this);
    private final SelectToSpeakJob.JobUpdateListener jobUpdateListener = new SelectToSpeakJobModel$jobUpdateListener$1(this, 1);
    public final Handler selectionRequestHandler = new Handler();
    public SelectToSpeakJob job = null;
    private final Handler interruptHandler = new Handler();
    private final Runnable interruptRunnable = new ContextMenuDialog$$ExternalSyntheticLambda5(this, 16, null);
    public final ScreenCaptureController.CaptureListener captureListener = new SelectToSpeakService$$ExternalSyntheticLambda3(this, 1);
    private final ScreenCaptureController.CaptureListener captureListenerContinuous = new SelectToSpeakService$$ExternalSyntheticLambda3(this, 0);
    private final OcrController.OcrListener ocrListener = new OcrController.OcrListener() { // from class: com.google.android.accessibility.selecttospeak.SelectToSpeakService.11
        public AnonymousClass11() {
        }

        @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
        public final void onOcrFinished(List list) {
            List list2;
            if (SelectToSpeakService.this.serviceState.isInactive()) {
                return;
            }
            ArrayList<AccessibilityNodeInfoCompatWithVisibility> arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                OcrInfo ocrInfo = (OcrInfo) it.next();
                AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility = (AccessibilityNodeInfoCompatWithVisibility) ocrInfo.node;
                accessibilityNodeInfoCompatWithVisibility.ocrTextBlocks = ocrInfo.textBlocks;
                arrayList.add(accessibilityNodeInfoCompatWithVisibility);
            }
            for (AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility2 : arrayList) {
                if (!accessibilityNodeInfoCompatWithVisibility2.isImage || (((list2 = accessibilityNodeInfoCompatWithVisibility2.ocrTextBlocks) != null && !list2.isEmpty()) || !TextUtils.isEmpty(accessibilityNodeInfoCompatWithVisibility2.getText()) || !TextUtils.isEmpty(accessibilityNodeInfoCompatWithVisibility2.getContentDescription()))) {
                    SelectToSpeakService.this.startJob(SentenceIteratorFactory.generateIterator(arrayList));
                    return;
                }
            }
            SelectToSpeakService.this.abortHandlingSelection();
        }

        @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
        public final void onOcrStarted() {
            SelectToSpeakService.this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PERFORM_WITH_OCR_ACTION$ar$edu);
        }
    };
    private final SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 11);

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.selecttospeak.SelectToSpeakService$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends Filter {
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            if (SpannableUtils$IdentifierSpan.getRole((AccessibilityNodeInfoCompat) obj) == 15) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.selecttospeak.SelectToSpeakService$11 */
    /* loaded from: classes.dex */
    final class AnonymousClass11 implements OcrController.OcrListener {
        public AnonymousClass11() {
        }

        @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
        public final void onOcrFinished(List list) {
            List list2;
            if (SelectToSpeakService.this.serviceState.isInactive()) {
                return;
            }
            ArrayList<AccessibilityNodeInfoCompatWithVisibility> arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                OcrInfo ocrInfo = (OcrInfo) it.next();
                AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility = (AccessibilityNodeInfoCompatWithVisibility) ocrInfo.node;
                accessibilityNodeInfoCompatWithVisibility.ocrTextBlocks = ocrInfo.textBlocks;
                arrayList.add(accessibilityNodeInfoCompatWithVisibility);
            }
            for (AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility2 : arrayList) {
                if (!accessibilityNodeInfoCompatWithVisibility2.isImage || (((list2 = accessibilityNodeInfoCompatWithVisibility2.ocrTextBlocks) != null && !list2.isEmpty()) || !TextUtils.isEmpty(accessibilityNodeInfoCompatWithVisibility2.getText()) || !TextUtils.isEmpty(accessibilityNodeInfoCompatWithVisibility2.getContentDescription()))) {
                    SelectToSpeakService.this.startJob(SentenceIteratorFactory.generateIterator(arrayList));
                    return;
                }
            }
            SelectToSpeakService.this.abortHandlingSelection();
        }

        @Override // com.google.android.accessibility.utils.ocr.OcrController.OcrListener
        public final void onOcrStarted() {
            SelectToSpeakService.this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.PERFORM_WITH_OCR_ACTION$ar$edu);
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.selecttospeak.SelectToSpeakService$2 */
    /* loaded from: classes.dex */
    final class AnonymousClass2 extends BroadcastReceiver {
        public AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            SelectToSpeakService selectToSpeakService = SelectToSpeakService.this;
            selectToSpeakService.unregisterReceiver(selectToSpeakService.userUnlockedBroadcastReceiver);
            SelectToSpeakService selectToSpeakService2 = SelectToSpeakService.this;
            if (SpannableUtils$IdentifierSpan.getBooleanPref(selectToSpeakService2.prefs, selectToSpeakService2.getResources(), R.string.s2s_pref_ocr_key, R.bool.s2s_pref_ocr_default) && !SpannableUtils$IdentifierSpan.isAtLeastR()) {
                SelectToSpeakService.this.screenCapturePermissionHelper.requestForPermission(null);
            }
            if (SelectToSpeakService.this.uIManager.isNarrowScreen()) {
                SelectToSpeakService.this.onDensityDpiOrLayoutDirectionChanged();
            }
        }
    }

    public static SelectToSpeakService getInstance() {
        WeakReference weakReference = service;
        if (weakReference == null) {
            return null;
        }
        return (SelectToSpeakService) weakReference.get();
    }

    public static void interrupt$ar$ds$762ae497_0() {
        WeakReference weakReference = service;
        if (weakReference != null) {
            SelectToSpeakService selectToSpeakService = (SelectToSpeakService) weakReference.get();
            LogUtils.v("SelectToSpeakService", "interrupt() service: %s", selectToSpeakService);
            if (selectToSpeakService != null) {
                selectToSpeakService.onInterrupt();
            } else {
                LogUtils.v("SelectToSpeakService", "interrupt() service set to null! WTF: ".concat("null"), new Object[0]);
                service = null;
            }
        }
    }

    private final boolean isImprovedAccuracyAvailable() {
        if (isOcrEnabled() && !this.isMultitaskingSettingEnabled && S2sCommonConfig.enableImproveAccuracy(this)) {
            return true;
        }
        return false;
    }

    private final void requestSelection() {
        this.selectionRequestHandler.removeCallbacks(null);
        this.selectionRequestHandler.post(new ContextMenuDialog$$ExternalSyntheticLambda5(this, 14));
    }

    private final void shutdownInfrastructure() {
        LogUtils.v("SelectToSpeakService", "shutdownInfrastructure: service: %s isUIInitialized: %s", service, Boolean.valueOf(this.isUIInitialized));
        if (this.isUIInitialized) {
            this.uIManager.shutdown();
        }
        SpeechControllerImpl speechControllerImpl = this.speechController;
        if (speechControllerImpl.mIsSpeaking) {
            speechControllerImpl.interrupt(true);
        }
        this.speechController.shutdown();
        if (this.wakeLock.isHeld()) {
            try {
                this.wakeLock.release();
            } catch (RuntimeException e) {
                LogUtils.e("SelectToSpeakService", "Error while releasing wakelock", e);
            }
        }
        OptionalMethod optionalMethod = this.selectionFinder$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            optionalMethod.shutdown();
        }
        ImprovedSelectionFinder improvedSelectionFinder = this.improvedSelectionFinder;
        if (improvedSelectionFinder != null) {
            improvedSelectionFinder.legacySelectionFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.shutdown();
            Job job = improvedSelectionFinder.screenshotJob;
            if (job != null) {
                job.cancel(null);
            }
            Job job2 = improvedSelectionFinder.getSelectionJob;
            if (job2 != null) {
                job2.cancel(null);
            }
        }
        ScreenCapturePermissionHelper screenCapturePermissionHelper = this.screenCapturePermissionHelper;
        if (screenCapturePermissionHelper != null) {
            screenCapturePermissionHelper.deauthorizeCapture();
        }
        ScreenCaptureController screenCaptureController = this.screenCaptureController;
        if (screenCaptureController != null) {
            screenCaptureController.deauthorizeCapture();
        }
        AccessibilityButtonMonitor accessibilityButtonMonitor = this.accessibilityButtonMonitor;
        if (accessibilityButtonMonitor != null) {
            accessibilityButtonMonitor.shutdown();
        }
        ScreenMonitor screenMonitor = this.screenMonitor;
        if (screenMonitor != null && SpannableUtils$IdentifierSpan.isAtLeastT()) {
            unregisterReceiver(screenMonitor);
        }
        if (FeatureSupport.screenshotRequiresForeground()) {
            stopForeground(true);
        }
        this.prefs.unregisterOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
    }

    /* JADX WARN: Type inference failed for: r1v16, types: [android.content.SharedPreferences, java.lang.Object] */
    public static void trigger$ar$ds() {
        WeakReference weakReference = service;
        if (weakReference != null) {
            SelectToSpeakService selectToSpeakService = (SelectToSpeakService) weakReference.get();
            int i = 0;
            LogUtils.v("SelectToSpeakService", "trigger service: service: %s", selectToSpeakService);
            if (selectToSpeakService != null) {
                LogUtils.v("SelectToSpeakService", "onTrigger(): service: %s", service);
                if (selectToSpeakService.serviceState.isInactive()) {
                    if (!selectToSpeakService.speechController.mFailoverTts.isReady()) {
                        Toast.makeText(selectToSpeakService, R.string.toast_tts_not_ready, 0).show();
                        return;
                    }
                    if (!selectToSpeakService.isUIInitialized) {
                        return;
                    }
                    selectToSpeakService.primesController.startTimer(PrimesController.Timer.S2S_WARM_START);
                    Bitmap bitmap = selectToSpeakService.screenshot;
                    if (bitmap != null) {
                        bitmap.recycle();
                    }
                    selectToSpeakService.screenshot = null;
                    selectToSpeakService.mlastSelection = null;
                    boolean isImprovedAccuracyAvailable = selectToSpeakService.isImprovedAccuracyAvailable();
                    OptionalMethod optionalMethod = selectToSpeakService.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
                    if (isImprovedAccuracyAvailable) {
                        SharedPreferences.Editor edit = optionalMethod.OptionalMethod$ar$returnType.edit();
                        int i2 = A11yS2SProtoEnums$A11yS2SSettings.ENABLE_IMPROVED_ACCURACY_SETTING$ar$edu;
                        String stringGeneratedcb0e5ccfc321d674 = A11yS2SProtoEnums$A11yS2SSettings.toStringGeneratedcb0e5ccfc321d674(i2);
                        if (i2 != 0) {
                            edit.putBoolean(stringGeneratedcb0e5ccfc321d674, true).apply();
                            if (selectToSpeakService.improvedSelectionFinder == null) {
                                selectToSpeakService.improvedSelectionFinder = new ImprovedSelectionFinder(ICUCompat$Api24Impl.getLifecycleScope(selectToSpeakService.serviceState));
                            }
                            ImprovedSelectionFinder improvedSelectionFinder = selectToSpeakService.improvedSelectionFinder;
                            SelectToSpeakService$$ExternalSyntheticLambda10 selectToSpeakService$$ExternalSyntheticLambda10 = new SelectToSpeakService$$ExternalSyntheticLambda10(selectToSpeakService, 3);
                            SelectToSpeakService$$ExternalSyntheticLambda10 selectToSpeakService$$ExternalSyntheticLambda102 = new SelectToSpeakService$$ExternalSyntheticLambda10(selectToSpeakService, i);
                            OnBackPressedDispatcher.AnonymousClass1 anonymousClass1 = new OnBackPressedDispatcher.AnonymousClass1(selectToSpeakService, 16);
                            Job job = improvedSelectionFinder.screenshotJob;
                            if (job != null) {
                                job.cancel(null);
                            }
                            improvedSelectionFinder.screenshotJob = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(improvedSelectionFinder.scope, null, 0, new ImprovedSelectionFinder$takeScreenShot$2(selectToSpeakService$$ExternalSyntheticLambda10, selectToSpeakService$$ExternalSyntheticLambda102, improvedSelectionFinder, selectToSpeakService, anonymousClass1, null), 3);
                            return;
                        }
                        throw null;
                    }
                    selectToSpeakService.showAssist();
                    return;
                }
                SelectToSpeakJob selectToSpeakJob = selectToSpeakService.job;
                if (selectToSpeakJob == null) {
                    S2SPipelineAnalytics s2SPipelineAnalytics = selectToSpeakService.pipelineAnalytics;
                    if (s2SPipelineAnalytics.stopWatch.isRunning && s2SPipelineAnalytics.elapsedMetrics.isEmpty()) {
                        s2SPipelineAnalytics.recordElapsed("KEY_S2S_UI_READY_TO_STOP_MS");
                        s2SPipelineAnalytics.amend();
                    } else {
                        s2SPipelineAnalytics.cleanupInMemoryMetrics();
                    }
                    selectToSpeakService.hideAssistImmediate();
                    return;
                }
                selectToSpeakJob.stop();
                return;
            }
            service = null;
        }
    }

    public final void abortHandlingSelection() {
        LogUtils.d("SelectToSpeakService", "Cannot handle selection, fall back to idle state and request selection again.", new Object[0]);
        S2SPipelineAnalytics s2SPipelineAnalytics = this.pipelineAnalytics;
        if (s2SPipelineAnalytics.stopWatch.isRunning && !s2SPipelineAnalytics.elapsedMetrics.isEmpty()) {
            s2SPipelineAnalytics.recordElapsed("KEY_INTERACTION_TO_NO_TEXT_FOUND_MS");
            s2SPipelineAnalytics.amend();
        } else {
            s2SPipelineAnalytics.cleanupInMemoryMetrics();
        }
        this.serviceState.setCurrent$ar$edu(ServiceState.State.IDLE$ar$edu);
        this.mlastSelection = null;
        Bundle bundle = new Bundle();
        float f = SpeechRateController.createSpeechRateController(this).speechRate;
        bundle.putFloat("pitch", 1.2f);
        bundle.putFloat("rate", f);
        this.speechController.speak$ar$class_merging$8236f667_0$ar$ds(getString(R.string.msg_no_text_selected), bundle, null);
        this.uIManager.clearBoardBackground();
        requestSelection();
    }

    public final void activateMultitaskingIfNecessary() {
        LogUtils.v("SelectToSpeakService", "activateMultitaskingIfNecessary: isEnabled: %s", Boolean.valueOf(this.isMultitaskingSettingEnabled));
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob != null && this.isMultitaskingSettingEnabled && !selectToSpeakJob.isMultitaskingActivated) {
            selectToSpeakJob.activateMultitasking();
            this.uIManager.clearBoardBackground();
            this.uIManager.setControlPanelOverlayOutsideTouchListener(null);
            if (this.uIManager.isControlPanelExpanded()) {
                this.uIManager.collapseControlPanel();
            }
        }
    }

    @Override // android.app.Service
    protected final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(fileDescriptor, printWriter, strArr);
        SerializableSnapshotHelper serializableSnapshotHelper = SerializableSnapshotHelper.INSTANCE;
        printWriter.getClass();
        try {
            printWriter.println("===== Select to Speak Service Dump =====");
            for (File file : SerializableSnapshotHelper.INSTANCE.getSnapshotFiles(this)) {
                printWriter.println("===== Dump file : " + file + " start =====");
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    try {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            } else {
                                printWriter.println(readLine);
                            }
                        } catch (IOException e) {
                            LogUtils.e("SerializableSnapshotHelper", "Unable to read snapshot file " + file, e);
                            bufferedReader.close();
                        }
                    } finally {
                    }
                }
                bufferedReader.close();
                fileInputStream.close();
                printWriter.println("===== Dump file : " + file + " end =====");
            }
        } catch (IOException e2) {
            LogUtils.e("SerializableSnapshotHelper", "Unable to read snapshot files", e2);
        }
    }

    public final void handleContinuousReadingRequest(Consumer consumer) {
        S2SPipelineAnalytics s2SPipelineAnalytics = this.pipelineAnalytics;
        if (s2SPipelineAnalytics.stopWatch.isRunning && s2SPipelineAnalytics.elapsedMetrics.isEmpty()) {
            s2SPipelineAnalytics.recordElapsed("KEY_S2S_UI_READY_TO_PLAY_MS");
        } else {
            s2SPipelineAnalytics.cleanupInMemoryMetrics();
        }
        if (!isImprovedAccuracyAvailable() && isOcrEnabled()) {
            if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                SelectToSpeakService selectToSpeakService = getInstance();
                if (selectToSpeakService != null) {
                    SpannableUtils$NonCopyableTextSpan.takeScreenshot(selectToSpeakService, this.captureListenerContinuous);
                }
            } else {
                this.screenCaptureController.requestScreenCaptureAsync(this.captureListenerContinuous);
            }
            consumer.accept(true);
            return;
        }
        handleSelection(SpannableUtils$NonCopyableTextSpan.getDisplaySizeRect(this), true, consumer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0548  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0550  */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v40, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleSelection(android.graphics.Rect r57, boolean r58, java.util.function.Consumer r59) {
        /*
            Method dump skipped, instructions count: 1387
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.SelectToSpeakService.handleSelection(android.graphics.Rect, boolean, java.util.function.Consumer):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void hideAssistImmediate() {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.SelectToSpeakService.hideAssistImmediate():void");
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isAudioPlaybackActive() {
        return false;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isMicrophoneActiveAndHeadphoneOff() {
        return false;
    }

    public final boolean isOcrEnabled() {
        boolean z;
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            Context applicationContext = getApplicationContext();
            z = SpannableUtils$IdentifierSpan.getSharedPreferences(applicationContext).getBoolean(applicationContext.getResources().getString(R.string.s2s_pref_ocr_key), false);
            return z;
        }
        return this.screenCapturePermissionHelper.isAuthorizedForScreenCapture();
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isPhoneCallActive() {
        return false;
    }

    public final boolean isScreenshotRequiredOnSelection() {
        if (!isOcrEnabled()) {
            return false;
        }
        if (!this.isMultitaskingSettingEnabled && S2sCommonConfig.enableImproveAccuracy(this)) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final boolean isSsbActiveAndHeadphoneOff() {
        return false;
    }

    @Override // com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback
    public final void onAccessibilityButtonClicked() {
        LogUtils.v("SelectToSpeakService", "onA11yButtonclicked ", new Object[0]);
        this.lastTimeAccessibilityButtonClicked = SystemClock.uptimeMillis();
        trigger$ar$ds();
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        SelectToSpeakJob selectToSpeakJob;
        Sentence peek;
        if (!this.serviceState.isInactive()) {
            int eventType = accessibilityEvent.getEventType();
            if (eventType != 1 && eventType != 2) {
                if (eventType == 2048 && (selectToSpeakJob = this.job) != null && selectToSpeakJob.isMultitaskingActivated && (peek = selectToSpeakJob.iterator.getPeek()) != null) {
                    if (!peek.getSupportsTextLocation()) {
                        selectToSpeakJob.highlight(true, peek);
                        return;
                    } else {
                        if (selectToSpeakJob.iterator.offsetTracker.isStarted() || selectToSpeakJob.state == 2) {
                            selectToSpeakJob.highlight(true, peek);
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            if (this.job != null) {
                if ((!this.isMultitaskingSettingEnabled || this.serviceState.isIdle()) && !TextUtils.equals(getPackageName(), accessibilityEvent.getPackageName())) {
                    LogUtils.d("SelectToSpeakService", "Multitasking disabled and non-S2S view clicked.", new Object[0]);
                    this.interruptHandler.postDelayed(this.interruptRunnable, 100L);
                }
            }
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        Configuration configuration2 = this.cachedConfig;
        if (configuration2 == null) {
            return;
        }
        if (configuration2.densityDpi == configuration.densityDpi && this.cachedConfig.getLayoutDirection() == configuration.getLayoutDirection() && (this.cachedConfig.uiMode & 48) == (configuration.uiMode & 48)) {
            if (this.cachedConfig.screenWidthDp != configuration.screenWidthDp || this.cachedConfig.screenHeightDp != configuration.screenHeightDp || this.cachedConfig.orientation != configuration.orientation) {
                if (this.isUIInitialized) {
                    UIManager uIManager = this.uIManager;
                    uIManager.clearBoardBackground();
                    TriggerButtonDragActionDetector triggerButtonDragActionDetector = uIManager.triggerButtonDragActionDetector;
                    if (triggerButtonDragActionDetector != null) {
                        triggerButtonDragActionDetector.cancelDragDetection();
                    }
                    uIManager.workingBoardOverlay.updateScreenBounds();
                    ControlOverlays controlOverlays = uIManager.controlOverlays;
                    SelectToSpeakOverlay selectToSpeakOverlay = controlOverlays.triggerButtonOverlay;
                    if (selectToSpeakOverlay != null) {
                        selectToSpeakOverlay.updateScreenBounds();
                    }
                    SelectToSpeakOverlay selectToSpeakOverlay2 = controlOverlays.collapsedOverlay;
                    if (selectToSpeakOverlay2 != null) {
                        selectToSpeakOverlay2.updateScreenBounds();
                    }
                    SelectToSpeakOverlay selectToSpeakOverlay3 = controlOverlays.expandableOverlay;
                    if (selectToSpeakOverlay3 != null) {
                        selectToSpeakOverlay3.updateScreenBounds();
                    }
                    uIManager.workingBoardOverlay.setPixelCoordinates(0, 0);
                    float[] fArr = new float[2];
                    uIManager.loadTriggerButtonFractionalCoordinates(uIManager.isTriggerButtonAppearanceActive, fArr);
                    uIManager.controlOverlays.setFractionalCoordinates(fArr[0], fArr[1]);
                }
                SelectToSpeakJob selectToSpeakJob = this.job;
                if (selectToSpeakJob != null) {
                    selectToSpeakJob.shouldHighlight = false;
                    selectToSpeakJob.highlightBoard.clear$ar$ds();
                }
            }
        } else {
            onDensityDpiOrLayoutDirectionChanged();
        }
        this.cachedConfig = new Configuration(configuration);
    }

    @Override // com.google.android.accessibility.utils.accessibilitybutton.AccessibilityButtonMonitor.AccessibilityButtonMonitorCallback
    public final void onConfirmSupportability(boolean z) {
        if (this.serviceState.isZombieState) {
            return;
        }
        this.primesController.startTimer(PrimesController.Timer.S2S_UI_INFLATION);
        this.uIManager.initializeInfrastructure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(z, this.controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, this.triggerButtonClickListener);
        this.isUIInitialized = true;
        this.primesController.stopTimer(PrimesController.Timer.S2S_UI_INFLATION);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        LogUtils.v("SelectToSpeakService", "onCreate: service: %s", service);
        this.primesController = new PrimesController();
        Application application = getApplication();
        ClearcutMetricTransmitter.Builder builder = new ClearcutMetricTransmitter.Builder();
        builder.context = application;
        builder.logSource = "SELECT_TO_SPEAK_ANDROID_PRIMES";
        builder.anonymous = true;
        Primes.initialize$ar$class_merging$fd7e8a43_0$ar$ds(BatteryMetricService.newInstance$ar$class_merging(application, new PrimesController$$ExternalSyntheticLambda8(builder.build(), application, 0)));
        Primes.get().startMemoryMonitor();
        Primes.get().startCrashMonitor();
        this.primesController.startTimer(PrimesController.Timer.S2S_START_UP);
        this.systemUeh = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this);
        this.prefs = sharedPreferences;
        sharedPreferences.registerOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        SpeechControllerImpl speechControllerImpl = new SpeechControllerImpl(this, this);
        this.speechController = speechControllerImpl;
        speechControllerImpl.ttsChangeAnnouncementEnabled = false;
        this.wakeLock = ((PowerManager) getSystemService("power")).newWakeLock(536870922, "SelectToSpeakService::WakelockTag");
        reloadPreferences();
        this.primesController.stopTimer(PrimesController.Timer.S2S_START_UP);
    }

    public final void onDensityDpiOrLayoutDirectionChanged() {
        SpeechControllerImpl speechControllerImpl = this.speechController;
        if (speechControllerImpl.mIsSpeaking) {
            speechControllerImpl.interrupt(true);
        }
        if (this.wakeLock.isHeld()) {
            try {
                this.wakeLock.release();
            } catch (RuntimeException e) {
                LogUtils.e("SelectToSpeakService", "Error while releasing wakelock", e);
            }
        }
        this.job = null;
        this.serviceState.setCurrent$ar$edu(ServiceState.State.INACTIVE$ar$edu);
        if (!this.isUIInitialized) {
            return;
        }
        this.uIManager.shutdown();
        this.primesController.startTimer(PrimesController.Timer.S2S_UI_INFLATION);
        this.uIManager.initializeInfrastructure$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(this.accessibilityButtonMonitor.isAccessibilityButtonSupported(), this.controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, this.triggerButtonClickListener);
        this.primesController.stopTimer(PrimesController.Timer.S2S_UI_INFLATION);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        ReentrantReadWriteLock reentrantReadWriteLock;
        ReentrantReadWriteLock.ReadLock readLock;
        LogUtils.v("SelectToSpeakService", "onDestroy: service: %s", service);
        ServiceState serviceState = this.serviceState;
        serviceState.isZombieState = true;
        serviceState.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY);
        OptionalMethod optionalMethod = this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging;
        if (optionalMethod != null) {
            LogUtils.v("SelectToSpeakClearcutAnalytics", "In stop before service being destroyed ", new Object[0]);
            ((JobScheduler) ((Context) optionalMethod.OptionalMethod$ar$methodName).getSystemService("jobscheduler")).cancel(-559038737);
            SelectToSpeakLogSender.lock.writeLock().lock();
            try {
                SharedPreferences sharedPreference = SelectToSpeakLogSender.getSharedPreference(this);
                Resources resources = getResources();
                resources.getClass();
                SelectToSpeakLogSender.lock.readLock().lock();
                try {
                    long currentTimeMillis = System.currentTimeMillis() - sharedPreference.getLong(resources.getString(R.string.s2s_pref_last_log_time_ms), 0L);
                    if (currentTimeMillis < 0) {
                        LogUtils.e("SelectToSpeakLogSender", "Wrong timestamp on log request, refresh.", new Object[0]);
                        readLock = SelectToSpeakLogSender.lock.readLock();
                    } else if (currentTimeMillis > SelectToSpeakLogSender.INTERVAL_BETWEEN_LOGS_MS) {
                        readLock = SelectToSpeakLogSender.lock.readLock();
                    } else {
                        SelectToSpeakLogSender.lock.readLock().unlock();
                        LogUtils.v("SelectToSpeakLogSender", "A day has not yet passed since last send.", new Object[0]);
                        reentrantReadWriteLock = SelectToSpeakLogSender.lock;
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                    readLock.unlock();
                    SelectToSpeakLogSender.sendSelectToSpeakLogs(this);
                    reentrantReadWriteLock = SelectToSpeakLogSender.lock;
                    reentrantReadWriteLock.writeLock().unlock();
                } catch (Throwable th) {
                    SelectToSpeakLogSender.lock.readLock().unlock();
                    throw th;
                }
            } catch (Throwable th2) {
                SelectToSpeakLogSender.lock.writeLock().unlock();
                throw th2;
            }
        }
        super.onDestroy();
        shutdownInfrastructure();
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final void onInterrupt() {
        LogUtils.v("SelectToSpeakService", "onInterrupt: isInactive: %s", Boolean.valueOf(this.serviceState.isInactive()));
        if (this.serviceState.isInactive()) {
            return;
        }
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob == null) {
            hideAssistImmediate();
        } else {
            selectToSpeakJob.stop();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public final boolean onKeyEvent(KeyEvent keyEvent) {
        if (this.serviceState.isInactive()) {
            return false;
        }
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        if (action == 0 && (keyCode == 3 || keyCode == 187 || keyCode == 4)) {
            prepareToChangeActivity();
            return false;
        }
        return this.streamVolumeController.patternDetector.onKeyEvent(keyEvent);
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected final void onServiceConnected() {
        boolean z;
        LogUtils.v("SelectToSpeakService", "onServiceConnected: service: %s", service);
        service = new WeakReference(this);
        this.serviceState.setCurrent$ar$edu(ServiceState.State.INACTIVE$ar$edu);
        LogUtils.parameterCustomizer = new TalkBackService$$ExternalSyntheticLambda8(1);
        this.screenMonitor = new ScreenMonitor((PowerManager) getSystemService("power"), this);
        int i = 2;
        if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
            EditorInfoCompat.registerReceiver$ar$ds(this, this.screenMonitor, ScreenMonitor.SCREEN_CHANGE_FILTER, 2);
        }
        this.cachedConfig = new Configuration(getResources().getConfiguration());
        this.streamVolumeController = new StreamVolumeController(this);
        this.coordinateConverter$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        this.pipelineAnalytics = new S2SPipelineAnalytics(this);
        this.uIManager = new UIManager(this, ICUCompat$Api24Impl.getLifecycleScope(this.serviceState), this.coordinateConverter$ar$class_merging$ar$class_merging, this.pipelineAnalytics);
        OptionalMethod optionalMethod = new OptionalMethod(this);
        this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.uIManager.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging = optionalMethod;
        this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.SERVICE_ENABLE_ACTION$ar$edu);
        if (!SpannableUtils$IdentifierSpan.isAtLeastR()) {
            ScreenCaptureController screenCaptureController = new ScreenCaptureController(this);
            this.screenCaptureController = screenCaptureController;
            this.screenCapturePermissionHelper = new ScreenCapturePermissionHelper(this, screenCaptureController);
            if (SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, getResources(), R.string.s2s_pref_ocr_key, R.bool.s2s_pref_ocr_default) && UserManagerCompat$Api24Impl.isUserUnlocked(this)) {
                this.screenCapturePermissionHelper.requestForPermission(null);
            }
        }
        if (!UserManagerCompat$Api24Impl.isUserUnlocked(this) && SpannableUtils$IdentifierSpan.isAtLeastT()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_UNLOCKED");
            EditorInfoCompat.registerReceiver$ar$ds(this, this.userUnlockedBroadcastReceiver, intentFilter, 2);
        }
        this.selectionFinder$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(new SelectToSpeakService$$ExternalSyntheticLambda10(this, i), this.ocrListener);
        AccessibilityButtonMonitor accessibilityButtonMonitor = new AccessibilityButtonMonitor(this);
        this.accessibilityButtonMonitor = accessibilityButtonMonitor;
        accessibilityButtonMonitor.initAccessibilityButton(this);
        if (FeatureSupport.screenshotRequiresForeground()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            NotificationChannel notificationChannel = new NotificationChannel("SelectToSpeakServiceChannel", getResources().getString(R.string.s2s_service_name), 2);
            notificationChannel.setDescription(getResources().getString(R.string.s2s_service_summary));
            notificationManager.createNotificationChannel(notificationChannel);
            Intent intent = new Intent(this, (Class<?>) SelectToSpeakPreferencesActivity.class);
            ContextDataProvider.checkArgument(true, (Object) "Cannot set any dangerous parts of intent to be mutable.");
            ContextDataProvider.checkArgument(true, (Object) "Cannot use Intent.FILL_IN_ACTION unless the action is marked as mutable.");
            ContextDataProvider.checkArgument(true, (Object) "Cannot use Intent.FILL_IN_DATA unless the data is marked as mutable.");
            ContextDataProvider.checkArgument(true, (Object) "Cannot use Intent.FILL_IN_CATEGORIES unless the category is marked as mutable.");
            ContextDataProvider.checkArgument(true, (Object) "Cannot use Intent.FILL_IN_CLIP_DATA unless the clip data is marked as mutable.");
            if (intent.getComponent() != null) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, (Object) "Must set component on Intent.");
            if (SaferPendingIntent.isSet(0, 1)) {
                ContextDataProvider.checkArgument(!SaferPendingIntent.isSet(201326592, 67108864), (Object) "Cannot set mutability flags if PendingIntent.FLAG_IMMUTABLE is set.");
            } else {
                ContextDataProvider.checkArgument(SaferPendingIntent.isSet(201326592, 67108864), (Object) "Must set PendingIntent.FLAG_IMMUTABLE for SDK >= 23 if no parts of intent are mutable.");
            }
            Intent intent2 = new Intent(intent);
            if (!SaferPendingIntent.isSet(201326592, 67108864)) {
                if (intent2.getPackage() == null) {
                    intent2.setPackage(intent2.getComponent().getPackageName());
                }
                if (!SaferPendingIntent.isSet(0, 3) && intent2.getAction() == null) {
                    intent2.setAction("");
                }
                if (!SaferPendingIntent.isSet(0, 9) && intent2.getCategories() == null) {
                    intent2.addCategory("");
                }
                if (!SaferPendingIntent.isSet(0, 5) && intent2.getData() == null) {
                    intent2.setDataAndType(Uri.EMPTY, "*/*");
                }
                if (!SaferPendingIntent.isSet(0, 17) && intent2.getClipData() == null) {
                    intent2.setClipData(SaferPendingIntent.SENTINEL_CLIP_DATA);
                }
            }
            PendingIntent activity = PendingIntent.getActivity(this, 0, intent2, 201326592);
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this, "SelectToSpeakServiceChannel");
            notificationCompat$Builder.setContentTitle$ar$ds(getResources().getString(R.string.s2s_service_on));
            notificationCompat$Builder.setContentText$ar$ds(getResources().getString(R.string.s2s_notification_description));
            notificationCompat$Builder.setSmallIcon$ar$ds(R.drawable.ic_accessibility_black_24dp);
            notificationCompat$Builder.mContentIntent = activity;
            notificationCompat$Builder.setOngoing$ar$ds(true);
            startForeground(1, notificationCompat$Builder.build());
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService("accessibility");
        if (accessibilityManager.isEnabled()) {
            AccessibilityEvent obtain = AccessibilityEvent.obtain();
            obtain.setEventType(16384);
            obtain.setClassName(getClass().getName());
            obtain.setPackageName(getPackageName());
            obtain.getText().add(getString(R.string.s2s_enabled));
            accessibilityManager.sendAccessibilityEvent(obtain);
        }
        SpannableUtils$IdentifierSpan.getSharedPreferences(getApplicationContext()).getBoolean(getString(R.string.pref_s2s_first_time_user), true);
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final void onTtsReady() {
        VoiceUtil.loadUserSavedVoice(this.speechController, this);
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        LogUtils.v("SelectToSpeakService", "onUnbind: service: %s", service);
        service = null;
        return super.onUnbind(intent);
    }

    public final void prepareToChangeActivity() {
        if (this.isMultitaskingSettingEnabled && !this.serviceState.isIdle()) {
            activateMultitaskingIfNecessary();
        } else {
            interrupt$ar$ds$762ae497_0();
        }
    }

    public final void reloadPreferences() {
        Resources resources = getResources();
        LogUtils.minLogLevel = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, resources, R.string.pref_log_level_key, R.string.pref_log_level_default);
        this.speechController.setOverlayEnabled(SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, resources, R.string.s2s_pref_tts_overlay_key, R.bool.s2s_pref_tts_overlay_default));
        this.isMultitaskingSettingEnabled = SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, resources, R.string.s2s_pref_multitasking_key, R.bool.s2s_pref_multitasking_default);
        VoiceUtil.loadUserSavedVoice(this.speechController, getApplicationContext());
    }

    @Override // com.google.android.accessibility.utils.monitor.ScreenMonitor.ScreenStateChangeListener
    public final void screenTurnedOff() {
        interrupt$ar$ds$762ae497_0();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0122  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void showAssist() {
        /*
            Method dump skipped, instructions count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.selecttospeak.SelectToSpeakService.showAssist():void");
    }

    public final void startJob(SentenceIterator sentenceIterator) {
        LogUtils.v("SelectToSpeakService", "startJob() service: %s", service);
        this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.recordWordCount$ar$edu(A11yS2SProtoEnums$A11yS2SEntryPoint.ENTRY_A11Y_SERVICE$ar$edu, sentenceIterator.characterCount);
        this.job = new SelectToSpeakJob(this, this.uIManager.drawingBoard, this.speechController, sentenceIterator, this.jobUpdateListener, this.selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging);
        updateViewOnSpeechRateChanged();
        this.job.run();
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final void uncaughtException(Thread thread, Throwable th) {
        LogUtils.v("SelectToSpeakService", "uncaughtexception: ", th);
        try {
            shutdownInfrastructure();
        } catch (Exception unused) {
        } catch (Throwable th2) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.systemUeh;
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
            throw th2;
        }
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.systemUeh;
        if (uncaughtExceptionHandler2 != null) {
            uncaughtExceptionHandler2.uncaughtException(thread, th);
        }
    }

    public final void updateViewOnSpeechRateChanged() {
        SelectToSpeakJob selectToSpeakJob = this.job;
        if (selectToSpeakJob == null) {
            return;
        }
        this.uIManager.setControlActionEnabled(1, selectToSpeakJob.canReduceSpeechRate());
        this.uIManager.setControlActionEnabled(2, selectToSpeakJob.canIncreaseSpeechRate());
    }

    @Override // com.google.android.accessibility.utils.output.SpeechController.Delegate
    public final void onSpeakingForcedFeedback() {
    }
}
