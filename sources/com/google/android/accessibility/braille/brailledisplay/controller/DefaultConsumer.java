package com.google.android.accessibility.braille.brailledisplay.controller;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity;
import com.google.android.accessibility.braille.brailledisplay.BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.braille.brailledisplay.analytics.BrailleDisplayAnalytics;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsActivity;
import com.google.android.accessibility.braille.brailledisplay.settings.KeyBindingsActivity;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.FeedbackManager$Type;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.AccessibilityServiceCompatUtils$Constants;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.output.SpeechControllerImpl;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import j$.util.Optional;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultConsumer implements EventConsumer {
    private final FloatingActionButton.ShadowDelegateImpl behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final FloatingActionButton.ShadowDelegateImpl behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final CellsContentManager cellsContentConsumer$ar$class_merging;
    public final Context context;
    private final ApplicationModule feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging;
    private final OnDeviceTextDetectionLoadLogEvent lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
    private final Handler loggingHandler;
    private final NodeBrailler nodeBrailler;
    private AlertDialog turnOffBdDialog;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LoggingHandler extends Handler {
        private final Context context;

        public LoggingHandler(Context context) {
            this.context = context;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what == 1) {
                Context context = this.context;
                BrailleDisplayAnalytics.getInstance(context).logBrailleInputCodeSetting(BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context), BrailleUserPreferences.readContractedMode(context));
            } else if (message.what == 2) {
                Context context2 = this.context;
                BrailleDisplayAnalytics.getInstance(context2).logBrailleOutputCodeSetting(BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(context2), BrailleUserPreferences.readContractedMode(context2));
            }
        }
    }

    public DefaultConsumer(Context context, CellsContentManager cellsContentManager, ApplicationModule applicationModule, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl4, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl5) {
        this.context = context;
        this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl3;
        this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
        this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl4;
        this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl5;
        this.cellsContentConsumer$ar$class_merging = cellsContentManager;
        this.nodeBrailler = new NodeBrailler(context, shadowDelegateImpl);
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging = applicationModule;
        this.loggingHandler = new LoggingHandler(context);
    }

    private static final boolean activateClickableSpan$ar$ds(Context context, ClickableSpan clickableSpan) {
        if (clickableSpan instanceof URLSpan) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(((URLSpan) clickableSpan).getURL()));
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                AppCompatTextViewAutoSizeHelper.Api23Impl.e("DefaultNavigationMode", "Failed to start activity", e);
                return false;
            }
        }
        try {
            clickableSpan.onClick(null);
            return true;
        } catch (RuntimeException e2) {
            AppCompatTextViewAutoSizeHelper.Api23Impl.e("DefaultNavigationMode", "Failed to invoke ClickableSpan", e2);
            return false;
        }
    }

    private final void brailleFirstFocusableNode$ar$edu(int i) {
        AccessibilityNodeInfoCompat focusedNode = getFocusedNode(true);
        if (focusedNode != null) {
            if (!AccessibilityNodeInfoUtils.shouldFocusNode(focusedNode)) {
                Object obj = this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                AccessibilityNodeInfoCompat findFocus = TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(focusedNode, WorkQueue.createFocusFinder$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(), 1).findFocus(focusedNode, 1);
                if (findFocus != null) {
                    focusedNode = findFocus;
                }
            }
            NodeBrailler nodeBrailler = this.nodeBrailler;
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging;
            CellsContent brailleNode = nodeBrailler.brailleNode(focusedNode);
            if (onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode == null && brailleNode.panStrategy == 0) {
                brailleNode.panStrategy = 2;
            }
            onDeviceTextDetectionLoadLogEvent.clear();
            this.cellsContentConsumer$ar$class_merging.setContent$ar$edu(brailleNode, i);
        }
    }

    private final boolean brailleFocusedNode$ar$edu(int i) {
        AccessibilityNodeInfoCompat focusedNode = getFocusedNode(false);
        if (focusedNode == null) {
            return false;
        }
        NodeBrailler nodeBrailler = this.nodeBrailler;
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging;
        CellsContent brailleNode = nodeBrailler.brailleNode(focusedNode);
        if (focusedNode.equals(onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode) && brailleNode.panStrategy == 0) {
            brailleNode.panStrategy = 2;
        }
        this.cellsContentConsumer$ar$class_merging.setContent$ar$edu(brailleNode, i);
        this.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = focusedNode;
        return true;
    }

    private final void displayTimedMessage(String str) {
        if (!this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging.isBrailleDisplayConnected()) {
            return;
        }
        this.cellsContentConsumer$ar$class_merging.setTimedContent(new CellsContent(str), BrailleUserPreferences.getTimedMessageDurationInMillisecond(this.context, str.length()));
    }

    private final AccessibilityNodeInfoCompat getFocusedNode(boolean z) {
        return this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getAccessibilityFocusNode(z);
    }

    private final String getSwitchLanguageAnnounceText(String str) {
        StringBuilder sb = new StringBuilder(str);
        if (BrailleUserPreferences.readSwitchContractedCount(this.context) <= 5) {
            sb.append("\n");
            sb.append(this.context.getString(R.string.bd_switch_contracted_mode_announcement));
        }
        return sb.toString();
    }

    private static final boolean isTriggerImeSwitchCommands$ar$ds(BrailleInputEvent brailleInputEvent) {
        if (brailleInputEvent.getCommand() != 60 && brailleInputEvent.getCommand() != 71) {
            return false;
        }
        return true;
    }

    private final boolean isWebContainer(BrailleInputEvent brailleInputEvent) {
        return WebInterfaceUtils.isWebContainer(this.cellsContentConsumer$ar$class_merging.getAccessibilityNode(brailleInputEvent.getArgument()));
    }

    private final void showSwitchInputMethodDialog() {
        BrailleDisplayImeUnavailableActivity.dialogBuilderProvider$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this, null);
        Context context = this.context;
        if (AppCompatDelegate.Api33Impl.isInputMethodEnabled(context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD) && AppCompatDelegate.Api33Impl.isInputMethodDefault(context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD)) {
            return;
        }
        Intent intent = new Intent(this.context, (Class<?>) BrailleDisplayImeUnavailableActivity.class);
        intent.addFlags(335544320);
        this.context.startActivity(intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003b, code lost:
    
        if (android.text.TextUtils.isEmpty(r4.text) == false) goto L17;
     */
    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onAccessibilityEvent(android.view.accessibility.AccessibilityEvent r6) {
        /*
            r5 = this;
            int r0 = r6.getEventType()
            r1 = 32
            r2 = 3
            if (r0 == r1) goto L5c
            r1 = 2048(0x800, float:2.87E-42)
            if (r0 == r1) goto L58
            r1 = 32768(0x8000, float:4.5918E-41)
            if (r0 == r1) goto L17
            r6 = 4194304(0x400000, float:5.877472E-39)
            if (r0 == r6) goto L5c
            goto L65
        L17:
            com.google.android.accessibility.braille.brailledisplay.controller.CellsContentManager r0 = r5.cellsContentConsumer$ar$class_merging
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r1 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.sourceCompat(r6)
            if (r1 == 0) goto L3e
            com.google.android.accessibility.braille.brailledisplay.controller.NodeBrailler r2 = r5.nodeBrailler
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r3 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.sourceCompat(r6)
            com.google.android.accessibility.braille.brailledisplay.controller.CellsContent r4 = new com.google.android.accessibility.braille.brailledisplay.controller.CellsContent
            java.lang.CharSequence r2 = r2.formatSubtree(r3, r6)
            r4.<init>(r2)
            r2 = 1
            r4.panStrategy = r2
            com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent r2 = r5.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging
            r2.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = r1
            java.lang.CharSequence r1 = r4.text
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L3e
            goto L53
        L3e:
            java.lang.String r1 = "DefaultNavigationMode"
            java.lang.String r2 = "No node on event, falling back on event text"
            android.support.v7.widget.AppCompatTextViewAutoSizeHelper.Api23Impl.v(r1, r2)
            com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent r1 = r5.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging
            r1.clear()
            com.google.android.accessibility.braille.brailledisplay.controller.CellsContent r4 = new com.google.android.accessibility.braille.brailledisplay.controller.CellsContent
            java.lang.CharSequence r6 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.getEventTextOrDescription(r6)
            r4.<init>(r6)
        L53:
            r6 = 2
            r0.setContent$ar$edu(r4, r6)
            return
        L58:
            r5.brailleFocusedNode$ar$edu(r2)
            return
        L5c:
            boolean r6 = r5.brailleFocusedNode$ar$edu(r2)
            if (r6 != 0) goto L65
            r5.brailleFirstFocusableNode$ar$edu(r2)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.braille.brailledisplay.controller.DefaultConsumer.onAccessibilityEvent(android.view.accessibility.AccessibilityEvent):void");
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onActivate() {
        this.lastFocusedNode$ar$class_merging$ar$class_merging$ar$class_merging.clear();
        if (!brailleFocusedNode$ar$edu(1)) {
            brailleFirstFocusableNode$ar$edu(1);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final boolean onMappedInputEvent(BrailleInputEvent brailleInputEvent) {
        Optional of;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (isTriggerImeSwitchCommands$ar$ds(brailleInputEvent) && isTriggerImeSwitchCommands$ar$ds(brailleInputEvent)) {
            if (AppCompatDelegate.Api33Impl.isInputMethodEnabled(this.context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD) && !AppCompatDelegate.Api33Impl.isInputMethodDefault(this.context, AccessibilityServiceCompatUtils$Constants.BRAILLE_KEYBOARD)) {
                if (!((BdController) this.behaviorIme$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.switchInputMethodToBrailleKeyboard()) {
                    showSwitchInputMethodDialog();
                }
            } else {
                showSwitchInputMethodDialog();
            }
        }
        int command = brailleInputEvent.getCommand();
        if (command != 1) {
            if (command != 2) {
                if (command != 3) {
                    if (command != 4) {
                        if (command != 7) {
                            if (command != 8) {
                                if (command != 20) {
                                    if (command != 50) {
                                        if (command != 60) {
                                            if (command != 70) {
                                                if (command != 100) {
                                                    int i7 = 30;
                                                    if (command != 30) {
                                                        int i8 = 31;
                                                        if (command != 31) {
                                                            switch (command) {
                                                                case 13:
                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(6, new Object[0]);
                                                                case 14:
                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(5, new Object[0]);
                                                                case 15:
                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(11, new Object[0]);
                                                                case 16:
                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(12, new Object[0]);
                                                                default:
                                                                    switch (command) {
                                                                        case BrailleInputEvent.CMD_HEADING_NEXT /* 110 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (isWebContainer(brailleInputEvent)) {
                                                                                i = 28;
                                                                            } else {
                                                                                i = 34;
                                                                            }
                                                                            return shadowDelegateImpl.performAction$ar$edu$3bc9316c_0(i, new Object[0]);
                                                                        case BrailleInputEvent.CMD_HEADING_PREVIOUS /* 111 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (isWebContainer(brailleInputEvent)) {
                                                                                i2 = 29;
                                                                            } else {
                                                                                i2 = 35;
                                                                            }
                                                                            return shadowDelegateImpl2.performAction$ar$edu$3bc9316c_0(i2, new Object[0]);
                                                                        case BrailleInputEvent.CMD_CONTROL_NEXT /* 112 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (!isWebContainer(brailleInputEvent)) {
                                                                                i7 = 36;
                                                                            }
                                                                            return shadowDelegateImpl3.performAction$ar$edu$3bc9316c_0(i7, new Object[0]);
                                                                        case BrailleInputEvent.CMD_CONTROL_PREVIOUS /* 113 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl4 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (!isWebContainer(brailleInputEvent)) {
                                                                                i8 = 37;
                                                                            }
                                                                            return shadowDelegateImpl4.performAction$ar$edu$3bc9316c_0(i8, new Object[0]);
                                                                        case BrailleInputEvent.CMD_LINK_NEXT /* 114 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl5 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (isWebContainer(brailleInputEvent)) {
                                                                                i3 = 32;
                                                                            } else {
                                                                                i3 = 38;
                                                                            }
                                                                            return shadowDelegateImpl5.performAction$ar$edu$3bc9316c_0(i3, new Object[0]);
                                                                        case BrailleInputEvent.CMD_LINK_PREVIOUS /* 115 */:
                                                                            FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl6 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                                                                            if (isWebContainer(brailleInputEvent)) {
                                                                                i4 = 33;
                                                                            } else {
                                                                                i4 = 39;
                                                                            }
                                                                            return shadowDelegateImpl6.performAction$ar$edu$3bc9316c_0(i4, new Object[0]);
                                                                        default:
                                                                            switch (command) {
                                                                                case BrailleInputEvent.CMD_SWITCH_TO_NEXT_INPUT_LANGUAGE /* 119 */:
                                                                                    if (BrailleUserPreferences.readAvailablePreferredCodes(this.context).size() > 1) {
                                                                                        Context context = this.context;
                                                                                        BrailleLanguages.Code nextInputCode = BrailleUserPreferences.getNextInputCode(context);
                                                                                        String userFacingName = nextInputCode.getUserFacingName(context);
                                                                                        BrailleUserPreferences.writeCurrentActiveInputCode(this.context, nextInputCode);
                                                                                        displayTimedMessage(userFacingName);
                                                                                        this.loggingHandler.removeMessages(1);
                                                                                        Handler handler = this.loggingHandler;
                                                                                        handler.sendMessageDelayed(handler.obtainMessage(1), 10000L);
                                                                                        BrailleUserPreferences.writeSwitchContactedCount(this.context);
                                                                                        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), getSwitchLanguageAnnounceText(this.context.getString(R.string.bd_switch_typing_language_announcement, userFacingName)), 1);
                                                                                        return true;
                                                                                    }
                                                                                    return false;
                                                                                case BrailleInputEvent.CMD_SWITCH_TO_NEXT_OUTPUT_LANGUAGE /* 120 */:
                                                                                    if (BrailleUserPreferences.readAvailablePreferredCodes(this.context).size() > 1) {
                                                                                        Context context2 = this.context;
                                                                                        List readAvailablePreferredCodes = BrailleUserPreferences.readAvailablePreferredCodes(context2);
                                                                                        int indexOf = readAvailablePreferredCodes.indexOf(BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(context2)) + 1;
                                                                                        if (indexOf >= readAvailablePreferredCodes.size()) {
                                                                                            indexOf = 0;
                                                                                        }
                                                                                        BrailleLanguages.Code code = (BrailleLanguages.Code) readAvailablePreferredCodes.get(indexOf);
                                                                                        String userFacingName2 = code.getUserFacingName(this.context);
                                                                                        BrailleUserPreferences.writeCurrentActiveOutputCode(this.context, code);
                                                                                        displayTimedMessage(userFacingName2);
                                                                                        this.loggingHandler.removeMessages(2);
                                                                                        Handler handler2 = this.loggingHandler;
                                                                                        handler2.sendMessageDelayed(handler2.obtainMessage(2), 10000L);
                                                                                        BrailleUserPreferences.writeSwitchContactedCount(this.context);
                                                                                        AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), getSwitchLanguageAnnounceText(this.context.getString(R.string.bd_switch_reading_language_announcement, userFacingName2)), 1);
                                                                                        return true;
                                                                                    }
                                                                                    return false;
                                                                                case BrailleInputEvent.CMD_BRAILLE_DISPLAY_SETTINGS /* 121 */:
                                                                                    Intent intent = new Intent(this.context, (Class<?>) BrailleDisplaySettingsActivity.class);
                                                                                    intent.addFlags(335544320);
                                                                                    this.context.startActivity(intent);
                                                                                    return true;
                                                                                case BrailleInputEvent.CMD_TALKBACK_SETTINGS /* 122 */:
                                                                                    Intent intent2 = new Intent();
                                                                                    intent2.setComponent(AccessibilityServiceCompatUtils$Constants.SETTINGS_ACTIVITY);
                                                                                    intent2.addFlags(335544320);
                                                                                    this.context.startActivity(intent2);
                                                                                    return true;
                                                                                case BrailleInputEvent.CMD_TURN_OFF_BRAILLE_DISPLAY /* 123 */:
                                                                                    if (this.turnOffBdDialog == null) {
                                                                                        AlertDialog create = SpannableUtils$NonCopyableTextSpan.alertDialogBuilder(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getAccessibilityService()).setTitle(R.string.bd_turn_off_bd_confirm_dialog_title).setMessage(R.string.bd_turn_off_bd_confirm_dialog_message).setPositiveButton(R.string.bd_turn_off_bd_confirm_dialog_positive_button, new BrailleDisplayImeUnavailableActivity$$ExternalSyntheticLambda0(this, 5)).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).create();
                                                                                        this.turnOffBdDialog = create;
                                                                                        create.getWindow().setType(2032);
                                                                                    }
                                                                                    if (!this.turnOffBdDialog.isShowing()) {
                                                                                        this.turnOffBdDialog.show();
                                                                                        return true;
                                                                                    }
                                                                                    return false;
                                                                                case BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK /* 124 */:
                                                                                    boolean performAction$ar$edu$3bc9316c_0 = this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(21, new Object[0]);
                                                                                    Context context3 = this.context;
                                                                                    if (true != ((SpeechControllerImpl) ((BdController) this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).talkBackForBrailleDisplay$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkQueue$ar$consumerIndex).isMuteSpeech) {
                                                                                        i5 = R.string.bd_voice_feedback_unmute;
                                                                                    } else {
                                                                                        i5 = R.string.bd_voice_feedback_mute;
                                                                                    }
                                                                                    displayTimedMessage(context3.getString(i5));
                                                                                    return performAction$ar$edu$3bc9316c_0;
                                                                                case BrailleInputEvent.CMD_PREVIOUS_READING_CONTROL /* 125 */:
                                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(17, new Object[0]);
                                                                                case BrailleInputEvent.CMD_NEXT_READING_CONTROL /* 126 */:
                                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(18, new Object[0]);
                                                                                case BrailleInputEvent.CMD_TOGGLE_BRAILLE_GRADE /* 127 */:
                                                                                    Context context4 = this.context;
                                                                                    BrailleLanguages.Code readCurrentActiveInputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveInputCodeAndCorrect(context4);
                                                                                    BrailleLanguages.Code readCurrentActiveOutputCodeAndCorrect = BrailleUserPreferences.readCurrentActiveOutputCodeAndCorrect(context4);
                                                                                    boolean readContractedMode = BrailleUserPreferences.readContractedMode(context4);
                                                                                    boolean z = !readContractedMode;
                                                                                    BrailleUserPreferences.writeContractedMode(this.context, z);
                                                                                    if (readCurrentActiveInputCodeAndCorrect.isSupportsContracted(this.context)) {
                                                                                        BrailleDisplayAnalytics.getInstance(this.context).logBrailleInputCodeSetting(readCurrentActiveInputCodeAndCorrect, z);
                                                                                    }
                                                                                    if (readCurrentActiveOutputCodeAndCorrect.isSupportsContracted(this.context)) {
                                                                                        BrailleDisplayAnalytics.getInstance(this.context).logBrailleOutputCodeSetting(readCurrentActiveOutputCodeAndCorrect, z);
                                                                                    }
                                                                                    Context context5 = this.context;
                                                                                    if (true != readContractedMode) {
                                                                                        i6 = R.string.bd_switch_to_contracted;
                                                                                    } else {
                                                                                        i6 = R.string.bd_switch_to_uncontracted;
                                                                                    }
                                                                                    String string = context5.getString(i6);
                                                                                    AppCompatDelegateImpl.Api24Impl.$default$speak$ar$edu$f33e3383_0$ar$class_merging$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent.getInstance$ar$class_merging$8b242409_0$ar$class_merging(), string, 1);
                                                                                    displayTimedMessage(string);
                                                                                    return true;
                                                                                case BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE /* 128 */:
                                                                                case BrailleInputEvent.CMD_NAV_BOTTOM_OR_KEY_ACTIVATE /* 129 */:
                                                                                    break;
                                                                                case BrailleInputEvent.CMD_STOP_READING /* 130 */:
                                                                                    return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(47, new Object[0]);
                                                                                default:
                                                                                    return false;
                                                                            }
                                                                    }
                                                            }
                                                        } else {
                                                            return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(7, new Object[0]);
                                                        }
                                                    } else {
                                                        return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(8, new Object[0]);
                                                    }
                                                } else {
                                                    Intent intent3 = new Intent(this.context, (Class<?>) KeyBindingsActivity.class);
                                                    intent3.addFlags(872415232);
                                                    intent3.putExtra("property_key", ((BdController) this.behaviorDisplayer$ar$class_merging$ar$class_merging$ar$class_merging.FloatingActionButton$ShadowDelegateImpl$ar$this$0).displayer.displayProperties);
                                                    this.context.startActivity(intent3);
                                                    return true;
                                                }
                                            }
                                        } else {
                                            return this.behaviorFocus$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.handleBrailleKeyWithoutKeyboardOpen(brailleInputEvent.getArgument());
                                        }
                                    } else {
                                        CellsContentManager cellsContentManager = this.cellsContentConsumer$ar$class_merging;
                                        int argument = brailleInputEvent.getArgument();
                                        ContentHelper contentHelper = cellsContentManager.getCurrentDisplayInfoWrapper().contentHelper;
                                        int textCursorPosition = contentHelper.getTextCursorPosition(argument);
                                        if (textCursorPosition == -1) {
                                            of = Optional.empty();
                                        } else {
                                            of = Optional.of((ClickableSpan[]) new SpannableString(contentHelper.currentTranslationResult.text()).getSpans(textCursorPosition, textCursorPosition, ClickableSpan.class));
                                        }
                                        if (of.isPresent() && ((ClickableSpan[]) of.get()).length > 0 && activateClickableSpan$ar$ds(this.context, ((ClickableSpan[]) of.get())[0])) {
                                            return true;
                                        }
                                        AccessibilityNodeInfoCompat accessibilityNode = this.cellsContentConsumer$ar$class_merging.getAccessibilityNode(brailleInputEvent.getArgument());
                                        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(14, accessibilityNode), FeedbackManager$Type.COMMAND_FAILED);
                                        int textCursorPosition2 = this.cellsContentConsumer$ar$class_merging.getCurrentDisplayInfoWrapper().contentHelper.getTextCursorPosition(brailleInputEvent.getArgument());
                                        if (accessibilityNode != null && AccessibilityNodeInfoUtils.isTextSelectable(accessibilityNode) && textCursorPosition2 != -1) {
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("ACTION_ARGUMENT_SELECTION_START_INT", textCursorPosition2);
                                            bundle.putInt("ACTION_ARGUMENT_SELECTION_END_INT", textCursorPosition2);
                                            SpannableUtils$IdentifierSpan.performAction(accessibilityNode, 131072, bundle, null);
                                        }
                                        return true;
                                    }
                                }
                                this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(13, new Object[0]), FeedbackManager$Type.COMMAND_FAILED);
                                return true;
                            }
                            return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(10, new Object[0]);
                        }
                        return this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(9, new Object[0]);
                    }
                    this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(1, new Object[0]), FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS);
                    return true;
                }
                this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(2, new Object[0]), FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS);
                return true;
            }
            this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(3, new Object[0]), FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS);
            return true;
        }
        this.feedbackManager$ar$class_merging$ar$class_merging$ar$class_merging.emitOnFailure$ar$ds(this.behaviorScreenReaderAction$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.performAction$ar$edu$3bc9316c_0(4, new Object[0]), FeedbackManager$Type.NAVIGATE_OUT_OF_BOUNDS);
        return true;
    }

    @Override // com.google.android.accessibility.braille.brailledisplay.controller.EventConsumer
    public final void onDeactivate() {
    }
}
