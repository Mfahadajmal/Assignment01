package com.google.android.accessibility.braille.brailledisplay;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.controller.TranslatorManager;
import com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager;
import com.google.android.accessibility.braille.brltty.BrailleInputEventIA;
import com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.internal.RetryingNameResolver;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleDisplay implements BrailleDisplayForBrailleIme {
    public static final BrailleInputEventIA ENCODER_FACTORY$ar$class_merging$ar$class_merging$ar$class_merging = new BrailleInputEventIA();
    public final AccessibilityService accessibilityService;
    public BrailleDisplayManager brailleDisplayManager;
    public final BdController controller;
    public boolean isRunning;

    public BrailleDisplay(AccessibilityService accessibilityService, WorkQueue workQueue, WindowTrackerFactory windowTrackerFactory, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        BdController bdController = new BdController(accessibilityService, workQueue, windowTrackerFactory, resolutionResultListener);
        this.controller = bdController;
        this.accessibilityService = accessibilityService;
        this.brailleDisplayManager = new BrailleDisplayManager(accessibilityService, bdController, ENCODER_FACTORY$ar$class_merging$ar$class_merging$ar$class_merging);
        OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging().OnDeviceTextDetectionLoadLogEvent$ar$errorCode = windowTrackerFactory;
    }

    @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
    public final boolean isBrailleDisplayConnectedAndNotSuspended() {
        if (this.isRunning) {
            return this.controller.brailleDisplayForBrailleIme.isBrailleDisplayConnectedAndNotSuspended();
        }
        return false;
    }

    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (this.isRunning) {
            BrailleDisplayManager brailleDisplayManager = this.brailleDisplayManager;
            if (brailleDisplayManager.canSendRenderPackets()) {
                BdController bdController = brailleDisplayManager.controller$ar$class_merging;
                if (!bdController.isDisplayerReady()) {
                    AppCompatTextViewAutoSizeHelper.Api23Impl.w("BdController", "Displayer is not ready yet.");
                } else {
                    bdController.eventManager.onAccessibilityEvent(accessibilityEvent);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
    public final void onImeVisibilityChanged(boolean z) {
        if (this.isRunning) {
            this.controller.brailleDisplayForBrailleIme.onImeVisibilityChanged(z);
        }
    }

    public final boolean onKeyEvent(KeyEvent keyEvent) {
        if (!this.isRunning || !this.brailleDisplayManager.canSendRenderPackets() || keyEvent.getKeyCode() != 188) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.braille.interfaces.BrailleDisplayForBrailleIme
    public final void showOnDisplay(BrailleDisplayForBrailleIme.ResultForDisplay resultForDisplay) {
        throw null;
    }

    public final void stop() {
        AppCompatTextViewAutoSizeHelper.Api23Impl.d("BrailleDisplay", "stop");
        BrailleDisplayManager brailleDisplayManager = this.brailleDisplayManager;
        BdController bdController = brailleDisplayManager.controller$ar$class_merging;
        if (bdController.isDisplayerReady()) {
            bdController.onDisconnected();
        }
        TranslatorManager translatorManager = bdController.translatorManager;
        if (translatorManager != null) {
            translatorManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(translatorManager);
            translatorManager.outputTranslator = null;
            translatorManager.inputTranslator = null;
            bdController.translatorManager = null;
        }
        brailleDisplayManager.connectedService = false;
        brailleDisplayManager.connectioneer.aspectConnection.detach$ar$ds(brailleDisplayManager.connectionCallback);
        brailleDisplayManager.connectioneer.aspectTraffic.detach$ar$ds(brailleDisplayManager.trafficCallback$ar$class_merging$ar$class_merging);
        brailleDisplayManager.connectioneer.onServiceEnabledChanged(false);
        brailleDisplayManager.displayer.stop();
        this.brailleDisplayManager.setAccessibilityServiceContextProvider(new BrailleDisplayManager.AccessibilityServiceContextProvider() { // from class: com.google.android.accessibility.braille.brailledisplay.BrailleDisplay$$ExternalSyntheticLambda0
            @Override // com.google.android.accessibility.braille.brailledisplay.platform.BrailleDisplayManager.AccessibilityServiceContextProvider
            public final Context getAccessibilityServiceContext() {
                return null;
            }
        });
        this.brailleDisplayManager = null;
        this.isRunning = false;
    }
}
