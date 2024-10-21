package com.google.android.accessibility.talkback.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.os.SystemClock;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.PrimesController;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.monitor.InputMethodMonitor;
import com.google.android.accessibility.talkback.training.TutorialConfigUtils$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.ServiceKeyEventListener;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.WeakReferenceHandler;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableSet;
import googledata.experiments.mobile.accessibility_suite.features.TvNavigationConfig;
import j$.time.Duration;
import j$.util.DesugarCollections;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TelevisionNavigationController implements ServiceKeyEventListener {
    private static final ImmutableSet HANDLED_KEYS = ImmutableSet.of((Object) 21, (Object) 22, (Object) 19, (Object) 20, (Object) 23, (Object) 66, (Object[]) new Integer[]{84});
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    private final InputMethodMonitor inputMethodMonitor;
    private final boolean letSystemHandleDpadCenterWhenFocusNotInSync;
    public final ListMenuManager listMenuManager;
    public AccessibilityNodeInfoCompat listMenuTriggerNode;
    public final Pipeline.FeedbackReturner pipeline;
    private final PrimesController primesController;
    public final TalkBackService service;
    private final Duration timeout;
    private final SharedPreferences.OnSharedPreferenceChangeListener treeDebugChangeListener;
    public final String treeDebugPrefKey;
    private final boolean useHandlerThread;
    public final BroadcastReceiver treeDebugBroadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.accessibility.talkback.controller.TelevisionNavigationController.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (TelevisionNavigationController.this.treeDebugEnabled) {
                TelevisionNavigationController.this.logNodeTreesOnAllDisplays();
            }
        }
    };
    public int mode = 0;
    private final TelevisionKeyHandler handler = new TelevisionKeyHandler(this);
    public volatile boolean treeDebugEnabled = false;
    public volatile boolean shouldProcessDPadKeyEvent = true;
    private final Map focusCache = DesugarCollections.synchronizedMap(new EnumMap(FocusType.class));
    private volatile boolean isHoldingConfirmKey = false;
    public boolean hasTriggeredConfirmKeyLongPress = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum FocusType {
        A11Y_FOCUS(false),
        ANY_FOCUS(true);

        final boolean useInputFocusIfEmpty;

        FocusType(boolean z) {
            this.useInputFocusIfEmpty = z;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TelevisionKeyHandler extends WeakReferenceHandler {
        public TelevisionKeyHandler(TelevisionNavigationController televisionNavigationController) {
            super(televisionNavigationController);
        }

        @Override // com.google.android.accessibility.utils.WeakReferenceHandler
        protected final /* bridge */ /* synthetic */ void handleMessage(Message message, Object obj) {
            TelevisionNavigationController televisionNavigationController = (TelevisionNavigationController) obj;
            int i = message.arg1;
            Performance.EventId eventId = (Performance.EventId) message.obj;
            int i2 = message.what;
            if (i2 != 1) {
                if (i2 != 2) {
                    return;
                }
                SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(televisionNavigationController.pipeline, eventId, Feedback.focus(Feedback.Focus.Action.LONG_CLICK_CURRENT));
                televisionNavigationController.hasTriggeredConfirmKeyLongPress = true;
                return;
            }
            televisionNavigationController.onDirectionalKey(i, eventId);
        }
    }

    public TelevisionNavigationController(TalkBackService talkBackService, AccessibilityFocusMonitor accessibilityFocusMonitor, InputMethodMonitor inputMethodMonitor, PrimesController primesController, ListMenuManager listMenuManager, Pipeline.FeedbackReturner feedbackReturner, boolean z) {
        OverlayDisplay.AnonymousClass1 anonymousClass1 = new OverlayDisplay.AnonymousClass1(this, 13);
        this.treeDebugChangeListener = anonymousClass1;
        this.service = talkBackService;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.inputMethodMonitor = inputMethodMonitor;
        this.pipeline = feedbackReturner;
        this.primesController = primesController;
        this.useHandlerThread = z;
        this.listMenuManager = listMenuManager;
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(talkBackService);
        sharedPreferences.registerOnSharedPreferenceChangeListener(anonymousClass1);
        String string = talkBackService.getString(R.string.pref_tree_debug_key);
        this.treeDebugPrefKey = string;
        anonymousClass1.onSharedPreferenceChanged(sharedPreferences, string);
        this.letSystemHandleDpadCenterWhenFocusNotInSync = TvNavigationConfig.letSystemHandleDpadCenterWhenFocusNotInSyncNew(talkBackService);
        this.timeout = Duration.ofMillis(TvNavigationConfig.keyEventTimeoutMillis(talkBackService));
    }

    private final AccessibilityNodeInfoCompat getFocus(FocusType focusType, Performance.EventId eventId) {
        if (eventId == null) {
            return this.accessibilityFocusMonitor.getAccessibilityFocus(focusType.useInputFocusIfEmpty);
        }
        GoogleApi.Settings.Builder builder = (GoogleApi.Settings.Builder) this.focusCache.get(focusType);
        if (builder == null || !((Performance.EventId) builder.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging).equals(eventId)) {
            AccessibilityNodeInfoCompat accessibilityFocus = this.accessibilityFocusMonitor.getAccessibilityFocus(focusType.useInputFocusIfEmpty);
            if (accessibilityFocus == null) {
                return null;
            }
            GoogleApi.Settings.Builder builder2 = new GoogleApi.Settings.Builder(accessibilityFocus, eventId);
            this.focusCache.put(focusType, builder2);
            builder = builder2;
        }
        return (AccessibilityNodeInfoCompat) builder.GoogleApi$Settings$Builder$ar$looper;
    }

    private static boolean isConfirmKey(int i) {
        if (i != 23 && i != 66) {
            return false;
        }
        return true;
    }

    private final void setMode(int i, Performance.EventId eventId) {
        int i2;
        boolean z;
        int i3 = this.mode;
        if (i == i3) {
            return;
        }
        if (i == 0) {
            i2 = R.string.template_tv_remote_mode_ended;
            z = false;
        } else {
            i2 = R.string.template_tv_remote_mode_started;
            i3 = i;
            z = true;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (i3 == 1) {
            TalkBackService talkBackService = this.service;
            StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, talkBackService.getString(i2, new Object[]{talkBackService.getString(R.string.value_tv_remote_mode_seek_control)}));
            if (z) {
                StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, this.service.getString(R.string.value_hint_tv_remote_mode_seek_control));
            }
        }
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 1;
        speakOptions.mFlags = 28;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.speech(spannableStringBuilder, speakOptions));
        this.mode = i;
    }

    private final boolean shouldHandleKeyCenter(KeyEvent keyEvent, Performance.EventId eventId) {
        if ((this.isHoldingConfirmKey && keyEvent.getAction() == 1) || this.mode != 0 || SpannableUtils$IdentifierSpan.getRole(getFocus(FocusType.ANY_FOCUS, eventId)) == 10) {
            return true;
        }
        AccessibilityNodeInfoCompat focus = getFocus(FocusType.A11Y_FOCUS, eventId);
        if (focus == null) {
            return false;
        }
        if (shouldTriggerClick$ar$ds(focus) || shouldOpenLinkMenu(focus)) {
            return true;
        }
        if (!this.letSystemHandleDpadCenterWhenFocusNotInSync && !WebInterfaceUtils.supportsWebActions(focus) && !focus.equals(this.accessibilityFocusMonitor.getInputFocus())) {
            return true;
        }
        return false;
    }

    private final boolean shouldOpenLinkMenu(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat != null && !shouldTriggerClick$ar$ds(accessibilityNodeInfoCompat) && !WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat) && SpannableUtils$IdentifierSpan.hasTargetClickableSpanInNodeTree(accessibilityNodeInfoCompat, AccessibilityNodeInfoUtils.BASE_CLICKABLE_SPAN)) {
            return true;
        }
        return false;
    }

    private static final boolean shouldTriggerClick$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, AccessibilityNodeInfoUtils.FILTER_CLICKABLE) != null) {
            return true;
        }
        return false;
    }

    public final void logNodeTreesOnAllDisplays() {
        SpannableUtils$IdentifierSpan.forEachWindowInfoListOnAllDisplays(this.service, new TutorialConfigUtils$$ExternalSyntheticLambda1(1));
    }

    public final void onDirectionalKey(int i, Performance.EventId eventId) {
        int i2 = 0;
        if (this.mode != 0) {
            AccessibilityNodeInfoCompat focus = getFocus(FocusType.ANY_FOCUS, eventId);
            if (SpannableUtils$IdentifierSpan.getRole(focus) != 10) {
                setMode(0, eventId);
                return;
            }
            boolean isScreenLayoutRTL = SpannableUtils$NonCopyableTextSpan.isScreenLayoutRTL(this.service);
            switch (i) {
                case 19:
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 4096));
                    return;
                case 20:
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 8192));
                    return;
                case 21:
                    if (isScreenLayoutRTL) {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 4096));
                        return;
                    } else {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 8192));
                        return;
                    }
                case 22:
                    if (isScreenLayoutRTL) {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 8192));
                        return;
                    } else {
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(this.pipeline, eventId, Feedback.nodeAction(focus, 4096));
                        return;
                    }
                default:
                    return;
            }
        }
        switch (i) {
            case 19:
                i2 = 5;
                break;
            case 20:
                i2 = 6;
                break;
            case 21:
                i2 = 3;
                break;
            case 22:
                i2 = 4;
                break;
        }
        if (i2 != 0) {
            Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
            Feedback.FocusDirection.Builder focusDirection = Feedback.focusDirection(i2);
            focusDirection.granularity = CursorGranularity.DEFAULT;
            focusDirection.setInputMode$ar$ds(2);
            focusDirection.setScroll$ar$ds(true);
            focusDirection.setDefaultToInputFocus$ar$ds(true);
            SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, focusDirection);
            if (eventId != null) {
                this.primesController.recordDuration(PrimesController.TimerAction.DPAD_NAVIGATION, eventId.eventTimeMs, SystemClock.uptimeMillis());
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
    
        if (shouldHandleKeyCenter(r9, r10) != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0053, code lost:
    
        if (r0 != false) goto L27;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:15:0x003a. Please report as an issue. */
    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onKeyEvent(android.view.KeyEvent r9, com.google.android.accessibility.utils.Performance.EventId r10) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.controller.TelevisionNavigationController.onKeyEvent(android.view.KeyEvent, com.google.android.accessibility.utils.Performance$EventId):boolean");
    }

    @Override // com.google.android.accessibility.utils.ServiceKeyEventListener
    public final boolean processWhenServiceSuspended() {
        return false;
    }
}
