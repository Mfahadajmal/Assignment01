package com.google.android.accessibility.braille.brailledisplay;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.AdapterHelper$UpdateOp;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import androidx.core.util.Pools$SimplePool;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService;
import com.google.android.accessibility.accessibilitymenu.proto.A11ymenuSettingsEnums$A11ymenuSettings;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.controller.AutoScrollManager;
import com.google.android.accessibility.braille.brailledisplay.controller.BdController;
import com.google.android.accessibility.braille.brailledisplay.controller.CellsContentManager;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer;
import com.google.android.accessibility.braille.brailledisplay.settings.AdvancedSettingsActivity;
import com.google.android.accessibility.braille.brailledisplay.settings.AutoScrollActivity;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleGradeActivity;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.braille.common.translate.EditBuffer;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.BrailleImeGestureController;
import com.google.android.accessibility.brailleime.BrailleInputOptions;
import com.google.android.accessibility.brailleime.analytics.BrailleImeAnalytics;
import com.google.android.accessibility.brailleime.analytics.BrailleImeLogProto$SettingsEvent;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.brailleime.settings.BrailleImePreferencesActivity;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.proto.A11yS2SProtoEnums$A11yS2SActions;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.controller.TelevisionNavigationController;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.keyboard.KeyComboManager;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.googlecode.eyesfree.brailleback.analytics.BraillebackLogProto$BraillebackExtension;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueue;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OverlayDisplay {
    public final Object OverlayDisplay$ar$context;
    public final Object OverlayDisplay$ar$displayThreadHandler;
    public final Object OverlayDisplay$ar$mainThreadHandler;
    public final Object OverlayDisplay$ar$overlayCallback$ar$class_merging;
    private final Object OverlayDisplay$ar$sharedPreferencesListener;
    public int numOfTextCell;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.braille.brailledisplay.OverlayDisplay$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements SharedPreferences.OnSharedPreferenceChangeListener {
        final /* synthetic */ Object OverlayDisplay$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.OverlayDisplay$1$ar$this$0 = obj;
        }

        private final void refreshEditBufferAndBrailleDisplay() {
            BrailleIme brailleIme = (BrailleIme) this.OverlayDisplay$1$ar$this$0;
            EditBuffer editBuffer = brailleIme.editBuffer;
            if (editBuffer != null) {
                editBuffer.commit$ar$class_merging$ar$class_merging(brailleIme.getImeConnection$ar$class_merging$ar$class_merging());
            }
            ((BrailleIme) this.OverlayDisplay$1$ar$this$0).createEditBuffer();
            BrailleIme brailleIme2 = (BrailleIme) this.OverlayDisplay$1$ar$this$0;
            BrailleImeGestureController brailleImeGestureController = brailleIme2.brailleImeGestureController;
            if (brailleImeGestureController != null) {
                brailleImeGestureController.editBuffer = brailleIme2.editBuffer;
            }
            brailleIme2.getWindow().setTitle(_BOUNDARY.getBrailleKeyboardDisplayName((Context) this.OverlayDisplay$1$ar$this$0));
            ((BrailleIme) this.OverlayDisplay$1$ar$this$0).showOnBrailleDisplay();
        }

        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            int i;
            BrailleImeLogProto$SettingsEvent brailleImeLogProto$SettingsEvent;
            int i2;
            int i3;
            switch (this.switching_field) {
                case 0:
                    if (((Context) ((OverlayDisplay) this.OverlayDisplay$1$ar$this$0).OverlayDisplay$ar$context).getString(R.string.pref_braille_overlay_key).equals(str)) {
                        ((DisplayThreadHandler) ((OverlayDisplay) this.OverlayDisplay$1$ar$this$0).OverlayDisplay$ar$displayThreadHandler).reportPreferenceChange();
                        return;
                    }
                    return;
                case 1:
                    AccessibilityMenuService accessibilityMenuService = (AccessibilityMenuService) this.OverlayDisplay$1$ar$this$0;
                    if (str.equals(accessibilityMenuService.getString(R.string.pref_large_buttons))) {
                        accessibilityMenuService.a11yMenuLayout.configureLayout$ar$ds();
                        ProcessStatsCapture processStatsCapture = accessibilityMenuService.analytics$ar$class_merging$ar$class_merging$ar$class_merging;
                        if (processStatsCapture != null) {
                            int i4 = A11ymenuSettingsEnums$A11ymenuSettings.LARGE_BUTTON_SETTINGS$ar$edu;
                            String stringGeneratedc97e382a2b02f4b7 = A11ymenuSettingsEnums$A11ymenuSettings.toStringGeneratedc97e382a2b02f4b7(i4);
                            if (i4 != 0) {
                                processStatsCapture.increaseEventTimes(stringGeneratedc97e382a2b02f4b7);
                                return;
                            }
                            throw null;
                        }
                        return;
                    }
                    return;
                case 2:
                    AutoScrollManager autoScrollManager = (AutoScrollManager) this.OverlayDisplay$1$ar$this$0;
                    if (str.equals(autoScrollManager.context.getString(R.string.pref_bd_auto_scroll_duration_key))) {
                        autoScrollManager.handler.removeCallbacksAndMessages(null);
                        autoScrollManager.duration = BrailleUserPreferences.readAutoScrollDuration(autoScrollManager.context);
                        autoScrollManager.handler.postDelayed(autoScrollManager.runnable, autoScrollManager.getDuration());
                        return;
                    } else {
                        if (str.equals(autoScrollManager.context.getString(R.string.pref_bd_auto_adjust_duration_enable_key))) {
                            autoScrollManager.autoAdjustDurationEnabled = BrailleUserPreferences.readAutoAdjustDurationEnable(autoScrollManager.context);
                            return;
                        }
                        return;
                    }
                case 3:
                    if (((CellsContentManager) this.OverlayDisplay$1$ar$this$0).context.getString(R.string.pref_bd_blinking_interval_key).equals(str)) {
                        CellsContentManager cellsContentManager = (CellsContentManager) this.OverlayDisplay$1$ar$this$0;
                        cellsContentManager.pulseHandler$ar$class_merging.currentStage = BrailleUserPreferences.readBlinkingIntervalMs(cellsContentManager.context);
                        return;
                    }
                    return;
                case 4:
                    if (Objects.equals(str, "connection_enabled_by_user")) {
                        ((Connectioneer) this.OverlayDisplay$1$ar$this$0).figureEnablement();
                        return;
                    }
                    if (Objects.equals(str, "auto_connect")) {
                        Connectioneer connectioneer = (Connectioneer) this.OverlayDisplay$1$ar$this$0;
                        if (connectioneer.isBrailleDisplayEnabled() && AppCompatDelegate.Api24Impl.isAutoConnect(connectioneer.context)) {
                            connectioneer.autoConnectIfPossibleToBondedDevice$ar$edu(5);
                            return;
                        }
                        return;
                    }
                    return;
                case 5:
                    ((AdvancedSettingsActivity.AdvancedSettingsFragment) this.OverlayDisplay$1$ar$this$0).m41x2823ac43(sharedPreferences, str);
                    return;
                case 6:
                    ((AutoScrollActivity.AutoScrollFragment) this.OverlayDisplay$1$ar$this$0).m42x25ab6cc2(sharedPreferences, str);
                    return;
                case 7:
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_bd_output_code))) {
                        BrailleDisplayAnalytics.getInstance(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()).logBrailleOutputCodeSetting(BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()), BrailleUserPreferences.readContractedMode(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()));
                        return;
                    }
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_brailleime_translator_code))) {
                        BrailleDisplayAnalytics.getInstance(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()).logBrailleInputCodeSetting(BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()), BrailleUserPreferences.readContractedMode(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext()));
                        return;
                    }
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_key_bd_auto_connect))) {
                        BrailleDisplayAnalytics brailleDisplayAnalytics = BrailleDisplayAnalytics.getInstance(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext());
                        boolean isAutoConnect = AppCompatDelegate.Api24Impl.isAutoConnect(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext());
                        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                        int state$ar$ds$ar$edu = BrailleDisplayAnalytics.getState$ar$ds$ar$edu(isAutoConnect);
                        builder.copyOnWrite();
                        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension = (BraillebackLogProto$BraillebackExtension) builder.instance;
                        int i5 = state$ar$ds$ar$edu - 1;
                        if (state$ar$ds$ar$edu != 0) {
                            braillebackLogProto$BraillebackExtension.autoConnectV2_ = i5;
                            braillebackLogProto$BraillebackExtension.bitField0_ |= 4096;
                            brailleDisplayAnalytics.sendLogs((BraillebackLogProto$BraillebackExtension) builder.build());
                            return;
                        }
                        throw null;
                    }
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_key_bd_enabler))) {
                        BrailleDisplayAnalytics brailleDisplayAnalytics2 = BrailleDisplayAnalytics.getInstance(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext());
                        boolean isConnectionEnabled = AppCompatDelegate.Api24Impl.isConnectionEnabled(((Fragment) this.OverlayDisplay$1$ar$this$0).getContext());
                        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) BraillebackLogProto$BraillebackExtension.DEFAULT_INSTANCE.createBuilder();
                        int state$ar$ds$ar$edu2 = BrailleDisplayAnalytics.getState$ar$ds$ar$edu(isConnectionEnabled);
                        builder2.copyOnWrite();
                        BraillebackLogProto$BraillebackExtension braillebackLogProto$BraillebackExtension2 = (BraillebackLogProto$BraillebackExtension) builder2.instance;
                        int i6 = state$ar$ds$ar$edu2 - 1;
                        if (state$ar$ds$ar$edu2 != 0) {
                            braillebackLogProto$BraillebackExtension2.enablerV2_ = i6;
                            braillebackLogProto$BraillebackExtension2.bitField0_ |= 8192;
                            brailleDisplayAnalytics2.sendLogs((BraillebackLogProto$BraillebackExtension) builder2.build());
                            return;
                        }
                        throw null;
                    }
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_braille_contracted_mode))) {
                        ((BrailleDisplaySettingsFragment) this.OverlayDisplay$1$ar$this$0).updateBrailleGradeSummary();
                        return;
                    }
                    return;
                case 8:
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_braille_contracted_mode))) {
                        BrailleGradeActivity.BrailleGradeFragment.m62$$Nest$monModelChanged((BrailleGradeActivity.BrailleGradeFragment) this.OverlayDisplay$1$ar$this$0);
                        return;
                    }
                    return;
                case 9:
                    if (str.equals(((BrailleIme) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_brailleime_translator_code))) {
                        Object obj = this.OverlayDisplay$1$ar$this$0;
                        BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect((Context) obj);
                        if (!((BrailleIme) obj).brailleDisplayConnectedAndNotSuspended) {
                            Object obj2 = this.OverlayDisplay$1$ar$this$0;
                            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), ((BrailleIme) obj2).getString(R.string.switch_to_language_announcement, new Object[]{readCurrentActiveInputCodeAndCorrect.getUserFacingName((Context) obj2)}), 1);
                        }
                        Object obj3 = this.OverlayDisplay$1$ar$this$0;
                        BrailleInputView brailleInputView = ((BrailleIme) obj3).keyboardView.brailleInputView;
                        if (brailleInputView != null) {
                            i3 = brailleInputView.options.brailleType$ar$edu;
                        } else {
                            i3 = -1;
                        }
                        if (i3 != BrailleUserPreferences.getCurrentTypingLanguageType$ar$edu((Context) obj3)) {
                            KeyboardView keyboardView = ((BrailleIme) this.OverlayDisplay$1$ar$this$0).keyboardView;
                            BrailleInputOptions obtainBrailleInputOptions = keyboardView.obtainBrailleInputOptions();
                            BrailleInputView brailleInputView2 = keyboardView.brailleInputView;
                            if (brailleInputView2 != null) {
                                brailleInputView2.options = obtainBrailleInputOptions;
                                BrailleInputPlane brailleInputPlane = brailleInputView2.inputPlane;
                                brailleInputPlane.options = obtainBrailleInputOptions;
                                brailleInputPlane.refresh();
                                brailleInputView2.invalidate();
                                brailleInputView2.requestLayout();
                            }
                        }
                        refreshEditBufferAndBrailleDisplay();
                        return;
                    }
                    if (str.equals(((BrailleIme) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_braille_contracted_mode))) {
                        Object obj4 = this.OverlayDisplay$1$ar$this$0;
                        boolean readContractedMode = BrailleUserPreferences.readContractedMode((Context) obj4);
                        if (BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect((Context) obj4).isSupportsContracted((Context) obj4)) {
                            WorkQueue workQueue = BrailleImeAnalytics.getInstance((Context) this.OverlayDisplay$1$ar$this$0).helper$ar$class_merging$ar$class_merging$ar$class_merging;
                            if (readContractedMode) {
                                brailleImeLogProto$SettingsEvent = BrailleImeLogProto$SettingsEvent.SWITCH_TO_CONTRACTED;
                            } else {
                                brailleImeLogProto$SettingsEvent = BrailleImeLogProto$SettingsEvent.SWITCH_TO_CONTRACTED_OFF;
                            }
                            if (((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$lastScheduledTask).ApplicationModule$ar$application).containsKey(brailleImeLogProto$SettingsEvent)) {
                                i2 = ((Integer) ((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$lastScheduledTask).ApplicationModule$ar$application).get(brailleImeLogProto$SettingsEvent)).intValue() + 1;
                            } else {
                                i2 = 1;
                            }
                            ((HashMap) ((ApplicationModule) workQueue.WorkQueue$ar$lastScheduledTask).ApplicationModule$ar$application).put(brailleImeLogProto$SettingsEvent, Integer.valueOf(i2));
                        }
                        if (!((BrailleIme) this.OverlayDisplay$1$ar$this$0).brailleDisplayConnectedAndNotSuspended) {
                            Object obj5 = this.OverlayDisplay$1$ar$this$0;
                            if (true != readContractedMode) {
                                i = R.string.switched_to_uncontracted_announcement;
                            } else {
                                i = R.string.switched_to_contracted_announcement;
                            }
                            AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), ((BrailleIme) obj5).getString(i), 1);
                        }
                        refreshEditBufferAndBrailleDisplay();
                        return;
                    }
                    return;
                case 10:
                    if (str.equals(((Fragment) this.OverlayDisplay$1$ar$this$0).getString(R.string.pref_braille_contracted_mode))) {
                        ((BrailleImePreferencesActivity.BrailleImePrefFragment) this.OverlayDisplay$1$ar$this$0).updateBrailleGradeSummary();
                        return;
                    }
                    return;
                case 11:
                    LogUtils.d("SelectToSpeakService", "A shared preference changed: %s", str);
                    ((SelectToSpeakService) this.OverlayDisplay$1$ar$this$0).reloadPreferences();
                    Object obj6 = this.OverlayDisplay$1$ar$this$0;
                    Resources resources = ((SelectToSpeakService) obj6).getResources();
                    if (((SelectToSpeakService) obj6).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                        if (str.equals(resources.getString(R.string.s2s_pref_multitasking_key))) {
                            ((SelectToSpeakService) this.OverlayDisplay$1$ar$this$0).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.SET_MULTITASK_ACTION$ar$edu);
                            return;
                        } else {
                            if (str.equals(resources.getString(R.string.s2s_pref_ocr_key))) {
                                ((SelectToSpeakService) this.OverlayDisplay$1$ar$this$0).selectToSpeakClearcutAnalytics$ar$class_merging$ar$class_merging$ar$class_merging.increaseEventCount$ar$edu(A11yS2SProtoEnums$A11yS2SActions.SET_OCR_ACTION$ar$edu);
                                return;
                            }
                            return;
                        }
                    }
                    return;
                case 12:
                    LogUtils.d("TalkBackService", "A shared preference changed: %s", str);
                    TalkBackService talkBackService = (TalkBackService) this.OverlayDisplay$1$ar$this$0;
                    if (talkBackService.getString(R.string.pref_previous_global_window_animation_scale_key).equals(str)) {
                        return;
                    }
                    talkBackService.reloadPreferences();
                    return;
                case 13:
                    if (str != null && str.equals(((TelevisionNavigationController) this.OverlayDisplay$1$ar$this$0).treeDebugPrefKey)) {
                        TelevisionNavigationController televisionNavigationController = (TelevisionNavigationController) this.OverlayDisplay$1$ar$this$0;
                        televisionNavigationController.treeDebugEnabled = PreferencesActivityUtils.getDiagnosticPref(sharedPreferences, televisionNavigationController.service.getResources(), R.string.pref_tree_debug_key, R.bool.pref_tree_debug_default);
                        if (((TelevisionNavigationController) this.OverlayDisplay$1$ar$this$0).treeDebugEnabled) {
                            TelevisionNavigationController televisionNavigationController2 = (TelevisionNavigationController) this.OverlayDisplay$1$ar$this$0;
                            EditorInfoCompat.registerReceiver$ar$ds(televisionNavigationController2.service, televisionNavigationController2.treeDebugBroadcastReceiver, new IntentFilter("com.google.android.accessibility.talkback.PRINT_TREE_DEBUG"), 2);
                            return;
                        }
                        TelevisionNavigationController televisionNavigationController3 = (TelevisionNavigationController) this.OverlayDisplay$1$ar$this$0;
                        televisionNavigationController3.service.unregisterReceiver(televisionNavigationController3.treeDebugBroadcastReceiver);
                        return;
                    }
                    return;
                case 14:
                    GestureShortcutMapping gestureShortcutMapping = (GestureShortcutMapping) this.OverlayDisplay$1$ar$this$0;
                    gestureShortcutMapping.loadGestureIdToActionKeyMap();
                    if (gestureShortcutMapping.context.getResources().getString(R.string.pref_gesture_set_key).equals(str)) {
                        gestureShortcutMapping.currentGestureSet = SpannableUtils$IdentifierSpan.getIntFromStringPref(sharedPreferences, gestureShortcutMapping.context.getResources(), R.string.pref_gesture_set_key, R.string.pref_gesture_set_value_default);
                        return;
                    } else {
                        if (gestureShortcutMapping.context.getResources().getString(R.string.pref_multiple_gesture_set_key).equals(str)) {
                            gestureShortcutMapping.gestureSetEnabled = GestureShortcutMapping.isGestureSetEnabled$ar$ds(gestureShortcutMapping.context, sharedPreferences);
                            return;
                        }
                        return;
                    }
                case 15:
                    if (((KeyComboManager) this.OverlayDisplay$1$ar$this$0).context.getString(R.string.pref_select_keymap_key).equals(str)) {
                        ((KeyComboManager) this.OverlayDisplay$1$ar$this$0).updateKeymapChangesNotificationVisibility();
                        return;
                    }
                    return;
                default:
                    ((SelectorController) this.OverlayDisplay$1$ar$this$0).cachedSelectSettingGestureNames = null;
                    return;
            }
        }

        public /* synthetic */ AnonymousClass1(Object obj, int i, byte[] bArr) {
            this.switching_field = i;
            this.OverlayDisplay$1$ar$this$0 = obj;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BrailleOverlay extends DraggableOverlay {
        public static final /* synthetic */ int OverlayDisplay$BrailleOverlay$ar$NoOp = 0;
        public final BrailleView brailleView;
        private final View.OnHoverListener hoverForwarder;

        public BrailleOverlay(Context context, OverlayDisplay overlayDisplay) {
            super(context);
            View.OnHoverListener onHoverListener = new View.OnHoverListener() { // from class: com.google.android.accessibility.braille.brailledisplay.OverlayDisplay.BrailleOverlay.1
                @Override // android.view.View.OnHoverListener
                public final boolean onHover(View view, MotionEvent motionEvent) {
                    return view.dispatchTouchEvent(SpannableUtils$IdentifierSpan.convertHoverToTouch(motionEvent));
                }
            };
            this.hoverForwarder = onHoverListener;
            this.contentView.removeAllViews();
            LayoutInflater.from(this.context).inflate(R.layout.overlay, this.contentView);
            BrailleView brailleView = (BrailleView) findViewById(R.id.braille_view);
            this.brailleView = brailleView;
            brailleView.setOnHoverListener(onHoverListener);
            brailleView.brailleCellClickListener$ar$class_merging = overlayDisplay;
            ImageButton imageButton = (ImageButton) findViewById(R.id.pan_left_button);
            imageButton.setOnHoverListener(onHoverListener);
            imageButton.setOnClickListener(new ActionBarContextView.AnonymousClass1(overlayDisplay, 6));
            ImageButton imageButton2 = (ImageButton) findViewById(R.id.pan_right_button);
            imageButton2.setOnHoverListener(onHoverListener);
            imageButton2.setOnClickListener(new ActionBarContextView.AnonymousClass1(overlayDisplay, 7));
        }

        @Override // com.google.android.accessibility.braille.brailledisplay.DraggableOverlay
        protected final void onStartDragging() {
            this.brailleView.cancelPendingTouches();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DisplayThreadHandler extends WeakReferenceHandler {
        public DisplayThreadHandler(OverlayDisplay overlayDisplay) {
            super(overlayDisplay);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            OverlayDisplay overlayDisplay = (OverlayDisplay) obj;
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                overlayDisplay.sendInputEvent((BrailleInputEvent) message.obj);
                return;
            }
            overlayDisplay.updateFromSharedPreferences();
        }

        public final void reportPreferenceChange() {
            obtainMessage(1).sendToTarget();
        }

        public final void sendInputEvent(BrailleInputEvent brailleInputEvent) {
            obtainMessage(2, brailleInputEvent).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MainThreadHandler extends WeakReferenceHandler {
        public byte[] braille;
        public int[] brailleToTextPositions;
        private final Context context;
        public int numOfTextCell;
        private BrailleOverlay overlay;
        public CharSequence text;

        public MainThreadHandler(Context context, OverlayDisplay overlayDisplay) {
            super(overlayDisplay, Looper.getMainLooper());
            this.context = context;
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        public final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            int i;
            byte[] bArr;
            CharSequence charSequence;
            int[] iArr;
            OverlayDisplay overlayDisplay = (OverlayDisplay) obj;
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 == 4) {
                            BrailleInputEvent brailleInputEvent = (BrailleInputEvent) message.obj;
                            if (this.overlay != null && BrailleInputEvent.argumentType(brailleInputEvent.getCommand()) == 2) {
                                BrailleOverlay brailleOverlay = this.overlay;
                                int i3 = BrailleOverlay.OverlayDisplay$BrailleOverlay$ar$NoOp;
                                BrailleView brailleView = brailleOverlay.brailleView;
                                brailleView.highlightedCell = brailleInputEvent.getArgument();
                                brailleView.removeCallbacks(brailleView.clearHighlightedCell);
                                brailleView.postDelayed(brailleView.clearHighlightedCell, 300L);
                                brailleView.invalidate();
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    if (this.overlay != null) {
                        synchronized (this) {
                            i = this.numOfTextCell;
                            bArr = this.braille;
                            charSequence = this.text;
                            iArr = this.brailleToTextPositions;
                        }
                        BrailleOverlay brailleOverlay2 = this.overlay;
                        int i4 = BrailleOverlay.OverlayDisplay$BrailleOverlay$ar$NoOp;
                        BrailleView brailleView2 = brailleOverlay2.brailleView;
                        if (brailleView2.numTextCells != i) {
                            brailleView2.numTextCells = i;
                            brailleView2.requestLayout();
                        }
                        brailleView2.braille = bArr;
                        brailleView2.text = charSequence;
                        brailleView2.brailleToTextPositions = iArr;
                        brailleView2.invalidate();
                        return;
                    }
                    return;
                }
                BrailleOverlay brailleOverlay3 = this.overlay;
                if (brailleOverlay3 != null) {
                    brailleOverlay3.hide();
                    this.overlay = null;
                    return;
                }
                return;
            }
            if (this.overlay == null) {
                this.overlay = new BrailleOverlay(this.context, overlayDisplay);
            }
            this.overlay.show();
        }

        public final void hideOverlay() {
            sendEmptyMessage(2);
        }
    }

    public OverlayDisplay(FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.OverlayDisplay$ar$displayThreadHandler = new Pools$SimplePool(30);
        this.OverlayDisplay$ar$mainThreadHandler = new ArrayList();
        this.OverlayDisplay$ar$overlayCallback$ar$class_merging = new ArrayList();
        this.numOfTextCell = 0;
        this.OverlayDisplay$ar$sharedPreferencesListener = shadowDelegateImpl;
        this.OverlayDisplay$ar$context = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(this);
    }

    private final boolean canFindInPreLayout(int i) {
        int size = ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).size();
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 1;
            AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).get(i2);
            int i4 = adapterHelper$UpdateOp.cmd;
            if (i4 == 8) {
                if (findPositionOffset(adapterHelper$UpdateOp.itemCount, i3) == i) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = adapterHelper$UpdateOp.positionStart;
                int i6 = adapterHelper$UpdateOp.itemCount + i5;
                while (i5 < i6) {
                    if (findPositionOffset(i5, i3) == i) {
                        return true;
                    }
                    i5++;
                }
            } else {
                continue;
            }
            i2 = i3;
        }
        return false;
    }

    private final void dispatchAndUpdateViewHolders(AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        int i;
        int i2 = adapterHelper$UpdateOp.cmd;
        if (i2 != 1 && i2 != 8) {
            int updatePositionWithPostponed = updatePositionWithPostponed(adapterHelper$UpdateOp.positionStart, i2);
            int i3 = adapterHelper$UpdateOp.positionStart;
            int i4 = adapterHelper$UpdateOp.cmd;
            if (i4 != 2) {
                if (i4 == 4) {
                    i = 1;
                } else {
                    java.util.Objects.toString(adapterHelper$UpdateOp);
                    throw new IllegalArgumentException("op should be remove or update.".concat(String.valueOf(adapterHelper$UpdateOp)));
                }
            } else {
                i = 0;
            }
            int i5 = 1;
            for (int i6 = 1; i6 < adapterHelper$UpdateOp.itemCount; i6++) {
                int updatePositionWithPostponed2 = updatePositionWithPostponed(adapterHelper$UpdateOp.positionStart + (i * i6), adapterHelper$UpdateOp.cmd);
                int i7 = adapterHelper$UpdateOp.cmd;
                if (i7 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : !(i7 != 4 || updatePositionWithPostponed2 != updatePositionWithPostponed + 1)) {
                    i5++;
                } else {
                    AdapterHelper$UpdateOp obtainUpdateOp = obtainUpdateOp(i7, updatePositionWithPostponed, i5, adapterHelper$UpdateOp.payload);
                    dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, i3);
                    recycleUpdateOp(obtainUpdateOp);
                    if (adapterHelper$UpdateOp.cmd == 4) {
                        i3 += i5;
                    }
                    i5 = 1;
                    updatePositionWithPostponed = updatePositionWithPostponed2;
                }
            }
            Object obj = adapterHelper$UpdateOp.payload;
            recycleUpdateOp(adapterHelper$UpdateOp);
            if (i5 > 0) {
                AdapterHelper$UpdateOp obtainUpdateOp2 = obtainUpdateOp(adapterHelper$UpdateOp.cmd, updatePositionWithPostponed, i5, obj);
                dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, i3);
                recycleUpdateOp(obtainUpdateOp2);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("should not dispatch add or move for pre layout");
    }

    private final void postponeAndUpdateViewHolders(AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).add(adapterHelper$UpdateOp);
        int i = adapterHelper$UpdateOp.cmd;
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i == 8) {
                        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForMove(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                        return;
                    }
                    java.util.Objects.toString(adapterHelper$UpdateOp);
                    throw new IllegalArgumentException("Unknown update op type for ".concat(String.valueOf(adapterHelper$UpdateOp)));
                }
                ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).markViewHoldersUpdated(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
                return;
            }
            Object obj = this.OverlayDisplay$ar$sharedPreferencesListener;
            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = (FloatingActionButton.ShadowDelegateImpl) obj;
            ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).offsetPositionRecordsForRemove(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, false);
            ((RecyclerView) shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0).mItemsAddedOrRemoved = true;
            return;
        }
        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForAdd(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
    }

    private final int updatePositionWithPostponed(int i, int i2) {
        int i3;
        int i4;
        int size = ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).get(size);
            int i5 = adapterHelper$UpdateOp.cmd;
            if (i5 == 8) {
                int i6 = adapterHelper$UpdateOp.positionStart;
                int i7 = adapterHelper$UpdateOp.itemCount;
                if (i6 < i7) {
                    i3 = i7;
                } else {
                    i3 = i6;
                }
                if (i6 < i7) {
                    i4 = i6;
                } else {
                    i4 = i7;
                }
                if (i >= i4 && i <= i3) {
                    if (i4 == i6) {
                        if (i2 == 1) {
                            adapterHelper$UpdateOp.itemCount = i7 + 1;
                        } else if (i2 == 2) {
                            adapterHelper$UpdateOp.itemCount = i7 - 1;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            adapterHelper$UpdateOp.positionStart = i6 + 1;
                        } else if (i2 == 2) {
                            adapterHelper$UpdateOp.positionStart = i6 - 1;
                        }
                        i--;
                    }
                } else if (i < i6) {
                    if (i2 == 1) {
                        adapterHelper$UpdateOp.positionStart = i6 + 1;
                        adapterHelper$UpdateOp.itemCount = i7 + 1;
                    } else if (i2 == 2) {
                        adapterHelper$UpdateOp.positionStart = i6 - 1;
                        adapterHelper$UpdateOp.itemCount = i7 - 1;
                    }
                }
            } else {
                int i8 = adapterHelper$UpdateOp.positionStart;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= adapterHelper$UpdateOp.itemCount;
                    } else if (i5 == 2) {
                        i += adapterHelper$UpdateOp.itemCount;
                    }
                } else if (i2 == 1) {
                    adapterHelper$UpdateOp.positionStart = i8 + 1;
                } else if (i2 == 2) {
                    adapterHelper$UpdateOp.positionStart = i8 - 1;
                }
            }
        }
        int size2 = ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).size();
        while (true) {
            size2--;
            if (size2 >= 0) {
                AdapterHelper$UpdateOp adapterHelper$UpdateOp2 = (AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).get(size2);
                if (adapterHelper$UpdateOp2.cmd == 8) {
                    int i9 = adapterHelper$UpdateOp2.itemCount;
                    if (i9 == adapterHelper$UpdateOp2.positionStart || i9 < 0) {
                        ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).remove(size2);
                        recycleUpdateOp(adapterHelper$UpdateOp2);
                    }
                } else if (adapterHelper$UpdateOp2.itemCount <= 0) {
                    ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).remove(size2);
                    recycleUpdateOp(adapterHelper$UpdateOp2);
                }
            } else {
                return i;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.lang.Object] */
    public final void consumePostponedUpdates() {
        int size = ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).size();
        for (int i = 0; i < size; i++) {
            ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate((AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).get(i));
        }
        recycleUpdateOpsAndClearList(this.OverlayDisplay$ar$overlayCallback$ar$class_merging);
        this.numOfTextCell = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.lang.Object] */
    public final void consumeUpdatesInOnePass() {
        consumePostponedUpdates();
        int size = ((ArrayList) this.OverlayDisplay$ar$mainThreadHandler).size();
        for (int i = 0; i < size; i++) {
            AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$mainThreadHandler).get(i);
            int i2 = adapterHelper$UpdateOp.cmd;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 4) {
                        if (i2 == 8) {
                            ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate(adapterHelper$UpdateOp);
                            ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForMove(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                        }
                    } else {
                        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate(adapterHelper$UpdateOp);
                        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).markViewHoldersUpdated(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
                    }
                } else {
                    ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate(adapterHelper$UpdateOp);
                    ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForRemovingInvisible(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                }
            } else {
                ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate(adapterHelper$UpdateOp);
                ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForAdd(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
        }
        recycleUpdateOpsAndClearList(this.OverlayDisplay$ar$mainThreadHandler);
        this.numOfTextCell = 0;
    }

    final void dispatchFirstPassAndUpdateViewHolders(AdapterHelper$UpdateOp adapterHelper$UpdateOp, int i) {
        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).dispatchUpdate(adapterHelper$UpdateOp);
        int i2 = adapterHelper$UpdateOp.cmd;
        if (i2 != 2) {
            if (i2 == 4) {
                ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).markViewHoldersUpdated(i, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
                return;
            }
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
        ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$sharedPreferencesListener).offsetPositionsForRemovingInvisible(i, adapterHelper$UpdateOp.itemCount);
    }

    public final void displayDots(byte[] bArr, CharSequence charSequence, int[] iArr) {
        Object obj = this.OverlayDisplay$ar$mainThreadHandler;
        int i = this.numOfTextCell;
        synchronized (obj) {
            ((MainThreadHandler) obj).numOfTextCell = i;
            ((MainThreadHandler) obj).braille = bArr;
            ((MainThreadHandler) obj).text = charSequence;
            ((MainThreadHandler) obj).brailleToTextPositions = iArr;
        }
        ((MainThreadHandler) obj).sendEmptyMessage(3);
    }

    public final int findPositionOffset(int i) {
        return findPositionOffset(i, 0);
    }

    public final boolean hasAnyUpdateTypes(int i) {
        if ((i & this.numOfTextCell) != 0) {
            return true;
        }
        return false;
    }

    public final boolean hasPendingUpdates() {
        if (((ArrayList) this.OverlayDisplay$ar$mainThreadHandler).size() > 0) {
            return true;
        }
        return false;
    }

    public final AdapterHelper$UpdateOp obtainUpdateOp(int i, int i2, int i3, Object obj) {
        AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((Pools$SimplePool) this.OverlayDisplay$ar$displayThreadHandler).acquire();
        if (adapterHelper$UpdateOp == null) {
            return new AdapterHelper$UpdateOp(i, i2, i3, obj);
        }
        adapterHelper$UpdateOp.cmd = i;
        adapterHelper$UpdateOp.positionStart = i2;
        adapterHelper$UpdateOp.itemCount = i3;
        adapterHelper$UpdateOp.payload = obj;
        return adapterHelper$UpdateOp;
    }

    public final void onBrailleCellClick$ar$ds(int i) {
        ((DisplayThreadHandler) this.OverlayDisplay$ar$displayThreadHandler).sendInputEvent(new BrailleInputEvent(50, i, SystemClock.uptimeMillis()));
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0002 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0122 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void preProcess() {
        /*
            Method dump skipped, instructions count: 694
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.OverlayDisplay.preProcess():void");
    }

    public final void recycleUpdateOp(AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        adapterHelper$UpdateOp.payload = null;
        ((Pools$SimplePool) this.OverlayDisplay$ar$displayThreadHandler).release(adapterHelper$UpdateOp);
    }

    final void recycleUpdateOpsAndClearList(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            recycleUpdateOp((AdapterHelper$UpdateOp) list.get(i));
        }
        list.clear();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    public final void reset() {
        recycleUpdateOpsAndClearList(this.OverlayDisplay$ar$mainThreadHandler);
        recycleUpdateOpsAndClearList(this.OverlayDisplay$ar$overlayCallback$ar$class_merging);
        this.numOfTextCell = 0;
    }

    public final void sendInputEvent(BrailleInputEvent brailleInputEvent) {
        ((BdController) ((FloatingActionButton.ShadowDelegateImpl) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).FloatingActionButton$ShadowDelegateImpl$ar$this$0).onBrailleInputEvent(brailleInputEvent);
        ((MainThreadHandler) this.OverlayDisplay$ar$mainThreadHandler).obtainMessage(4, brailleInputEvent).sendToTarget();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.SharedPreferences$OnSharedPreferenceChangeListener, java.lang.Object] */
    public final void shutdown$ar$ds() {
        BrailleUserPreferences.getSharedPreferences$ar$ds((Context) this.OverlayDisplay$ar$context).unregisterOnSharedPreferenceChangeListener(this.OverlayDisplay$ar$sharedPreferencesListener);
        ((MainThreadHandler) this.OverlayDisplay$ar$mainThreadHandler).hideOverlay();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.content.SharedPreferences$OnSharedPreferenceChangeListener, java.lang.Object] */
    public final void start(int i) {
        this.numOfTextCell = i;
        BrailleUserPreferences.getSharedPreferences$ar$ds((Context) this.OverlayDisplay$ar$context).registerOnSharedPreferenceChangeListener(this.OverlayDisplay$ar$sharedPreferencesListener);
        ((DisplayThreadHandler) this.OverlayDisplay$ar$displayThreadHandler).reportPreferenceChange();
    }

    public final void updateFromSharedPreferences() {
        Context context = (Context) this.OverlayDisplay$ar$context;
        if (BrailleUserPreferences.getSharedPreferences$ar$ds(context).getBoolean(context.getString(R.string.pref_braille_overlay_key), false)) {
            ((MainThreadHandler) this.OverlayDisplay$ar$mainThreadHandler).sendEmptyMessage(1);
        } else {
            ((MainThreadHandler) this.OverlayDisplay$ar$mainThreadHandler).hideOverlay();
        }
    }

    final int findPositionOffset(int i, int i2) {
        int size = ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).size();
        while (i2 < size) {
            AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp) ((ArrayList) this.OverlayDisplay$ar$overlayCallback$ar$class_merging).get(i2);
            int i3 = adapterHelper$UpdateOp.cmd;
            if (i3 == 8) {
                int i4 = adapterHelper$UpdateOp.positionStart;
                if (i4 == i) {
                    i = adapterHelper$UpdateOp.itemCount;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (adapterHelper$UpdateOp.itemCount <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = adapterHelper$UpdateOp.positionStart;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = adapterHelper$UpdateOp.itemCount;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += adapterHelper$UpdateOp.itemCount;
                }
            }
            i2++;
        }
        return i;
    }

    public OverlayDisplay(Context context, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl) {
        this.OverlayDisplay$ar$sharedPreferencesListener = new AnonymousClass1(this, 0);
        this.OverlayDisplay$ar$context = context;
        this.OverlayDisplay$ar$overlayCallback$ar$class_merging = shadowDelegateImpl;
        this.OverlayDisplay$ar$mainThreadHandler = new MainThreadHandler(context, this);
        this.OverlayDisplay$ar$displayThreadHandler = new DisplayThreadHandler(this);
    }
}
