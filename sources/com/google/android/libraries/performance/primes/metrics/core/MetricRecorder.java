package com.google.android.libraries.performance.primes.metrics.core;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.room.util.SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Feedback;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.actor.TalkBackUIActor$Type;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.accessibility.talkback.selector.SelectorController;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Logger;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.output.SpeechController;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.Shutdown;
import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesActiveTraceProvider;
import com.google.android.libraries.performance.primes.sampling.Sampler;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.android.marvin.talkback.R;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlinx.coroutines.scheduling.WorkQueue;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.MemoryMetric$MemoryUsageMetric;
import logs.proto.wireless.performance.mobile.ProcessProto$AndroidProcessStats;
import logs.proto.wireless.performance.mobile.ProcessProto$ProcessStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$AccountableComponent;
import logs.proto.wireless.performance.mobile.SystemHealthProto$ApplicationInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DebugLogs;
import logs.proto.wireless.performance.mobile.SystemHealthProto$DeviceInfo;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MetricRecorder {
    public final Object MetricRecorder$ar$activeTraceProvider;
    public final Object MetricRecorder$ar$enableActiveTraceCollectionForCrash;
    public final Object MetricRecorder$ar$enableSafeFormatArgsAsStrings;
    public final Object MetricRecorder$ar$executor;
    public final Object MetricRecorder$ar$globalExtensionProvider;
    public final Object MetricRecorder$ar$interactionContextProvider;
    public final Object MetricRecorder$ar$metricDispatcher;
    public final Object MetricRecorder$ar$metricStamperProvider;
    public final Object MetricRecorder$ar$recentLogs;
    public final Object MetricRecorder$ar$sampler;
    private final Object MetricRecorder$ar$shutdown;

    public MetricRecorder(TalkBackService talkBackService, Pipeline.FeedbackReturner feedbackReturner, ActorState actorState, ListMenuManager listMenuManager, SelectorController selectorController, AccessibilityFocusMonitor accessibilityFocusMonitor, AccessibilityFocusInterpreter accessibilityFocusInterpreter, GestureShortcutMapping gestureShortcutMapping, TalkBackAnalytics talkBackAnalytics) {
        this.MetricRecorder$ar$metricStamperProvider = new HashMap();
        this.MetricRecorder$ar$globalExtensionProvider = new HashMap();
        if (feedbackReturner == null) {
            throw new IllegalStateException();
        }
        if (listMenuManager == null) {
            throw new IllegalStateException();
        }
        if (selectorController != null) {
            this.MetricRecorder$ar$metricDispatcher = feedbackReturner;
            this.MetricRecorder$ar$shutdown = actorState;
            this.MetricRecorder$ar$interactionContextProvider = listMenuManager;
            this.MetricRecorder$ar$sampler = talkBackService;
            this.MetricRecorder$ar$enableSafeFormatArgsAsStrings = selectorController;
            this.MetricRecorder$ar$recentLogs = accessibilityFocusMonitor;
            this.MetricRecorder$ar$enableActiveTraceCollectionForCrash = accessibilityFocusInterpreter;
            this.MetricRecorder$ar$executor = gestureShortcutMapping;
            this.MetricRecorder$ar$activeTraceProvider = talkBackAnalytics;
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v6, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void changeAccessibilityVolume(Performance.EventId eventId, boolean z) {
        int i;
        int i2;
        String string;
        int i3;
        if (z) {
            i = 2;
        } else {
            i = 1;
        }
        if (SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.MetricRecorder$ar$metricDispatcher, eventId, Feedback.adjustVolume$ar$edu(i, 1))) {
            Object obj = this.MetricRecorder$ar$sampler;
            if (true != z) {
                i3 = R.string.template_volume_change_increase;
            } else {
                i3 = R.string.template_volume_change_decrease;
            }
            string = ((TalkBackService) obj).getString(i3);
        } else {
            Object obj2 = this.MetricRecorder$ar$sampler;
            if (true != z) {
                i2 = R.string.template_volume_change_maximum;
            } else {
                i2 = R.string.template_volume_change_minimum;
            }
            string = ((TalkBackService) obj2).getString(i2);
        }
        ?? r2 = this.MetricRecorder$ar$metricDispatcher;
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mQueueMode = 1;
        speakOptions.mFlags = 318;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r2, eventId, Feedback.speech(string, speakOptions));
        ?? r1 = this.MetricRecorder$ar$metricDispatcher;
        TalkBackUIActor$Type talkBackUIActor$Type = TalkBackUIActor$Type.GESTURE_ACTION_OVERLAY;
        Feedback.Part.Builder builder = Feedback.Part.builder();
        builder.talkBackUI = new Feedback.TalkBackUI(2, talkBackUIActor$Type, string, false);
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r1, eventId, builder);
    }

    private final AccessibilityNodeInfoCompat getEditTextFocus() {
        AccessibilityNodeInfoCompat accessibilityFocus = ((AccessibilityFocusMonitor) this.MetricRecorder$ar$recentLogs).getAccessibilityFocus(true);
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityFocus) == 4) {
            return accessibilityFocus;
        }
        speak(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.not_editable));
        return null;
    }

    private final AccessibilityNodeInfoCompat getSelectTextFocus() {
        AccessibilityNodeInfoCompat accessibilityFocus = ((AccessibilityFocusMonitor) this.MetricRecorder$ar$recentLogs).getAccessibilityFocus(true);
        if (AccessibilityNodeInfoUtils.isTextSelectable(accessibilityFocus)) {
            return accessibilityFocus;
        }
        AccessibilityNodeInfoCompat editingNodeFromFocusedKeyboard = ((AccessibilityFocusMonitor) this.MetricRecorder$ar$recentLogs).getEditingNodeFromFocusedKeyboard(accessibilityFocus);
        if (editingNodeFromFocusedKeyboard != null) {
            return editingNodeFromFocusedKeyboard;
        }
        speak(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.not_selectable));
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    private final void speak(String str) {
        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
        speakOptions.mFlags = 30;
        Logger logger = Performance.DEFAULT_LOGGER;
        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) this.MetricRecorder$ar$metricDispatcher, (Performance.EventId) null, Feedback.speech(str, speakOptions));
    }

    public final String actionFromFingerprintGesture(int i) {
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences((Context) this.MetricRecorder$ar$sampler);
        if (i != 1) {
            if (i != 2) {
                if (i != 4) {
                    if (i != 8) {
                        return ((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.shortcut_value_unassigned);
                    }
                    return sharedPreferences.getString(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_down_key), ((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_down_default));
                }
                return sharedPreferences.getString(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_up_key), ((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_up_default));
            }
            return sharedPreferences.getString(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_left_key), ((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_left_default));
        }
        return sharedPreferences.getString(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_right_key), ((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.pref_shortcut_fingerprint_right_default));
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v2, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    public final boolean gestureHandledByTraining(int i, boolean z) {
        AccessibilityWindowInfo window;
        AccessibilityNodeInfo root;
        Integer num;
        String string;
        Object obj = this.MetricRecorder$ar$sampler;
        AccessibilityNodeInfo rootInActiveWindow = ((AccessibilityService) obj).getRootInActiveWindow();
        if (rootInActiveWindow != null && (window = AccessibilityNodeInfoUtils.getWindow(rootInActiveWindow)) != null && (root = window.getRoot()) != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= root.getChildCount()) {
                    break;
                }
                AccessibilityNodeInfo child = root.getChild(i2);
                if (child != null && TextUtils.equals(child.getViewIdResourceName(), ((Context) obj).getResources().getResourceName(R.id.training_root))) {
                    if (z) {
                        num = (Integer) this.MetricRecorder$ar$globalExtensionProvider.get(Integer.valueOf(i));
                    } else {
                        num = (Integer) this.MetricRecorder$ar$metricStamperProvider.get(Integer.valueOf(i));
                    }
                    if (num != null && num.intValue() != -1) {
                        if (num.intValue() == 0) {
                            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                            if (!z) {
                                StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, GestureShortcutMapping.getGestureString((Context) this.MetricRecorder$ar$sampler, i), GestureShortcutMapping.getActionString((Context) this.MetricRecorder$ar$sampler, ((GestureShortcutMapping) this.MetricRecorder$ar$executor).getActionKeyFromGestureId(i)));
                            } else {
                                CharSequence[] charSequenceArr = new CharSequence[2];
                                charSequenceArr[0] = GestureShortcutMapping.getFingerprintGestureString((Context) this.MetricRecorder$ar$sampler, i);
                                Object obj2 = this.MetricRecorder$ar$sampler;
                                GestureShortcutMapping gestureShortcutMapping = (GestureShortcutMapping) this.MetricRecorder$ar$executor;
                                String str = (String) gestureShortcutMapping.fingerprintGestureIdToActionKey.get(Integer.valueOf(i));
                                if (str == null) {
                                    str = gestureShortcutMapping.actionUnassigned;
                                }
                                charSequenceArr[1] = GestureShortcutMapping.getActionString((Context) obj2, str);
                                StringBuilderUtils.appendWithSeparator$ar$ds(spannableStringBuilder, false, charSequenceArr);
                            }
                            string = spannableStringBuilder.toString();
                        } else {
                            string = ((TalkBackService) this.MetricRecorder$ar$sampler).getString(num.intValue());
                        }
                        ?? r9 = this.MetricRecorder$ar$metricDispatcher;
                        SpeechController.SpeakOptions speakOptions = new SpeechController.SpeakOptions();
                        speakOptions.mQueueMode = 2;
                        SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((Pipeline.FeedbackReturner) r9, (Performance.EventId) null, Feedback.speech(string, speakOptions));
                        return true;
                    }
                } else {
                    i2++;
                }
            }
        }
        return false;
    }

    public final boolean isFingerprintGestureAssigned(int i) {
        if (!TextUtils.equals(((TalkBackService) this.MetricRecorder$ar$sampler).getString(R.string.shortcut_value_unassigned), actionFromFingerprintGesture(i))) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:279:0x09a4, code lost:
    
        if (com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan.$default$returnFeedback((com.google.android.accessibility.talkback.Pipeline.FeedbackReturner) r0, (com.google.android.accessibility.utils.Performance.EventId) null, r10) != false) goto L211;
     */
    /* JADX WARN: Type inference failed for: r0v273, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v293, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v294, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v295, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v296, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v297, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v298, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v303, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v304, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v10, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v104, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v105, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v106, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v107, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v108, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v109, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v11, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v110, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v111, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v112, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v113, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v114, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v115, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v116, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v117, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v12, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v13, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v14, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v18, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v19, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v20, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v38, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v39, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v40, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v41, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v46, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v47, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v48, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v53, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v64, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v65, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v71, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v72, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v73, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v74, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v75, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v76, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v77, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v78, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v79, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v90, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v91, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v92, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v93, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v97, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v98, types: [com.google.android.accessibility.talkback.Pipeline$FeedbackReturner, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void performAction(java.lang.String r10, com.google.android.accessibility.utils.Performance.EventId r11) {
        /*
            Method dump skipped, instructions count: 2487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.core.MetricRecorder.performAction(java.lang.String, com.google.android.accessibility.utils.Performance$EventId):void");
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.concurrent.Executor, java.lang.Object] */
    public final ListenableFuture recordMetric(final Metric metric) {
        if (((Shutdown) this.MetricRecorder$ar$shutdown).shutdown) {
            return ContextDataProvider.immediateCancelledFuture();
        }
        return ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.core.MetricRecorder$$ExternalSyntheticLambda0
            /* JADX WARN: Type inference failed for: r1v4, types: [com.google.common.base.Supplier, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v16, types: [javax.inject.Provider, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v38, types: [javax.inject.Provider, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r4v5, types: [javax.inject.Provider, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r6v30, types: [javax.inject.Provider, java.lang.Object] */
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                SystemHealthProto$SamplingParameters samplingOffParameters;
                String str;
                String str2;
                MetricRecorder metricRecorder = MetricRecorder.this;
                Metric metric2 = metric;
                RuntimeException runtimeException = null;
                if (metric2.isUnsampled) {
                    SystemHealthProto$PackedHistogram.Builder createBuilder = SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE.createBuilder();
                    int i = SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_ALWAYS_ON$ar$edu;
                    createBuilder.copyOnWrite();
                    SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = (SystemHealthProto$SamplingParameters) createBuilder.instance;
                    int i2 = i - 1;
                    if (i != 0) {
                        systemHealthProto$SamplingParameters.samplingStrategy_ = i2;
                        systemHealthProto$SamplingParameters.bitField0_ |= 4;
                        samplingOffParameters = (SystemHealthProto$SamplingParameters) createBuilder.build();
                    } else {
                        throw null;
                    }
                } else {
                    Long l = metric2.sampleRatePermille;
                    Sampler sampler = (Sampler) metricRecorder.MetricRecorder$ar$sampler;
                    boolean z = sampler.enabled;
                    SamplingStrategy samplingStrategy = sampler.samplingStrategy;
                    if (z) {
                        samplingOffParameters = samplingStrategy.getSamplingParametersIfShouldRecord(l);
                    } else {
                        samplingOffParameters = samplingStrategy.getSamplingOffParameters();
                    }
                }
                if (samplingOffParameters.sampleRatePermille_ == -1) {
                    return ImmediateFuture.NULL;
                }
                MetricStamper metricStamper = (MetricStamper) metricRecorder.MetricRecorder$ar$metricStamperProvider.get();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = metric2.metric;
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$SystemHealthMetric.toBuilder();
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$ApplicationInfo.DEFAULT_INSTANCE.createBuilder();
                int i3 = metricStamper.hardwareVariant$ar$edu;
                builder2.copyOnWrite();
                SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo = (SystemHealthProto$ApplicationInfo) builder2.instance;
                int i4 = i3 - 1;
                if (i3 != 0) {
                    systemHealthProto$ApplicationInfo.hardwareVariant_ = i4;
                    systemHealthProto$ApplicationInfo.bitField0_ |= 4;
                    String str3 = metricStamper.applicationPackage;
                    if (str3 != null) {
                        builder2.copyOnWrite();
                        SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo2 = (SystemHealthProto$ApplicationInfo) builder2.instance;
                        systemHealthProto$ApplicationInfo2.bitField0_ |= 1;
                        systemHealthProto$ApplicationInfo2.applicationPackage_ = str3;
                    }
                    builder2.copyOnWrite();
                    SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo3 = (SystemHealthProto$ApplicationInfo) builder2.instance;
                    systemHealthProto$ApplicationInfo3.bitField0_ |= 8;
                    systemHealthProto$ApplicationInfo3.primesVersion_ = 643847009L;
                    String str4 = metricStamper.versionName;
                    if (str4 != null) {
                        builder2.copyOnWrite();
                        SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo4 = (SystemHealthProto$ApplicationInfo) builder2.instance;
                        systemHealthProto$ApplicationInfo4.bitField0_ |= 2;
                        systemHealthProto$ApplicationInfo4.applicationVersionName_ = str4;
                    }
                    MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric = systemHealthProto$SystemHealthMetric.memoryUsageMetric_;
                    if (memoryMetric$MemoryUsageMetric == null) {
                        memoryMetric$MemoryUsageMetric = MemoryMetric$MemoryUsageMetric.DEFAULT_INSTANCE;
                    }
                    ProcessProto$ProcessStats processProto$ProcessStats = memoryMetric$MemoryUsageMetric.processStats_;
                    if (processProto$ProcessStats == null) {
                        processProto$ProcessStats = ProcessProto$ProcessStats.DEFAULT_INSTANCE;
                    }
                    ProcessProto$AndroidProcessStats processProto$AndroidProcessStats = processProto$ProcessStats.androidProcessStats_;
                    if (processProto$AndroidProcessStats == null) {
                        processProto$AndroidProcessStats = ProcessProto$AndroidProcessStats.DEFAULT_INSTANCE;
                    }
                    if ((processProto$AndroidProcessStats.bitField0_ & 8) != 0 && ((Boolean) metricStamper.readCorrectProcStatusProvider.get()).booleanValue()) {
                        String str5 = metricStamper.applicationPackage;
                        MemoryMetric$MemoryUsageMetric memoryMetric$MemoryUsageMetric2 = systemHealthProto$SystemHealthMetric.memoryUsageMetric_;
                        if (memoryMetric$MemoryUsageMetric2 == null) {
                            memoryMetric$MemoryUsageMetric2 = MemoryMetric$MemoryUsageMetric.DEFAULT_INSTANCE;
                        }
                        ProcessProto$ProcessStats processProto$ProcessStats2 = memoryMetric$MemoryUsageMetric2.processStats_;
                        if (processProto$ProcessStats2 == null) {
                            processProto$ProcessStats2 = ProcessProto$ProcessStats.DEFAULT_INSTANCE;
                        }
                        ProcessProto$AndroidProcessStats processProto$AndroidProcessStats2 = processProto$ProcessStats2.androidProcessStats_;
                        if (processProto$AndroidProcessStats2 == null) {
                            processProto$AndroidProcessStats2 = ProcessProto$AndroidProcessStats.DEFAULT_INSTANCE;
                        }
                        str = ProcessStats.getShortProcessName(str5, processProto$AndroidProcessStats2.processName_);
                    } else {
                        str = metricStamper.shortProcessName;
                    }
                    if (str != null) {
                        builder2.copyOnWrite();
                        SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo5 = (SystemHealthProto$ApplicationInfo) builder2.instance;
                        systemHealthProto$ApplicationInfo5.bitField0_ |= 16;
                        systemHealthProto$ApplicationInfo5.shortProcessName_ = str;
                    }
                    builder.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder.instance;
                    SystemHealthProto$ApplicationInfo systemHealthProto$ApplicationInfo6 = (SystemHealthProto$ApplicationInfo) builder2.build();
                    systemHealthProto$ApplicationInfo6.getClass();
                    systemHealthProto$SystemHealthMetric2.applicationInfo_ = systemHealthProto$ApplicationInfo6;
                    systemHealthProto$SystemHealthMetric2.bitField0_ |= 2097152;
                    if (DirectBootUtils.isUserUnlocked(metricStamper.application)) {
                        SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$DeviceInfo.DEFAULT_INSTANCE.createBuilder();
                        long freeSpace = metricStamper.dataPartitionSize$ar$class_merging.getDataPartition().getFreeSpace() / 1024;
                        builder3.copyOnWrite();
                        SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo = (SystemHealthProto$DeviceInfo) builder3.instance;
                        systemHealthProto$DeviceInfo.bitField0_ |= 1;
                        systemHealthProto$DeviceInfo.availableDiskSizeKb_ = freeSpace;
                        long longValue = ((Long) metricStamper.totalDiskSizeKbSupplier.get()).longValue();
                        builder3.copyOnWrite();
                        SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo2 = (SystemHealthProto$DeviceInfo) builder3.instance;
                        systemHealthProto$DeviceInfo2.bitField0_ |= 2;
                        systemHealthProto$DeviceInfo2.totalDiskSizeKb_ = longValue;
                        builder.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric3 = (SystemHealthProto$SystemHealthMetric) builder.instance;
                        SystemHealthProto$DeviceInfo systemHealthProto$DeviceInfo3 = (SystemHealthProto$DeviceInfo) builder3.build();
                        systemHealthProto$DeviceInfo3.getClass();
                        systemHealthProto$SystemHealthMetric3.deviceInfo_ = systemHealthProto$DeviceInfo3;
                        systemHealthProto$SystemHealthMetric3.bitField0_ |= 8388608;
                    }
                    Supplier supplier = metricStamper.componentNameSupplier;
                    if (supplier == null) {
                        str2 = null;
                    } else {
                        str2 = ((NoPiiString) supplier.get()).value;
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent = systemHealthProto$SystemHealthMetric.accountableComponent_;
                        if (systemHealthProto$AccountableComponent == null) {
                            systemHealthProto$AccountableComponent = SystemHealthProto$AccountableComponent.DEFAULT_INSTANCE;
                        }
                        SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$AccountableComponent.toBuilder();
                        if (((SystemHealthProto$AccountableComponent) builder4.instance).customName_.isEmpty()) {
                            builder4.copyOnWrite();
                            SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent2 = (SystemHealthProto$AccountableComponent) builder4.instance;
                            str2.getClass();
                            systemHealthProto$AccountableComponent2.bitField0_ |= 1;
                            systemHealthProto$AccountableComponent2.customName_ = str2;
                        } else {
                            String str6 = str2 + "::" + ((SystemHealthProto$AccountableComponent) builder4.instance).customName_;
                            builder4.copyOnWrite();
                            SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent3 = (SystemHealthProto$AccountableComponent) builder4.instance;
                            systemHealthProto$AccountableComponent3.bitField0_ |= 1;
                            systemHealthProto$AccountableComponent3.customName_ = str6;
                        }
                        builder.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric4 = (SystemHealthProto$SystemHealthMetric) builder.instance;
                        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent4 = (SystemHealthProto$AccountableComponent) builder4.build();
                        systemHealthProto$AccountableComponent4.getClass();
                        systemHealthProto$SystemHealthMetric4.accountableComponent_ = systemHealthProto$AccountableComponent4;
                        systemHealthProto$SystemHealthMetric4.bitField0_ |= 33554432;
                    }
                    SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) ((SystemHealthProto$SystemHealthMetric) builder.build()).toBuilder();
                    builder5.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric5 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                    samplingOffParameters.getClass();
                    systemHealthProto$SystemHealthMetric5.samplingParameters_ = samplingOffParameters;
                    systemHealthProto$SystemHealthMetric5.bitField0_ |= 524288;
                    if (metric2.debugLogsTime != null && ((Optional) metricRecorder.MetricRecorder$ar$recentLogs).isPresent()) {
                        int i5 = metric2.debugLogsSize;
                        ((Boolean) metricRecorder.MetricRecorder$ar$enableSafeFormatArgsAsStrings.get()).booleanValue();
                        ArrayList arrayList = new ArrayList();
                        Collections.sort(arrayList, new SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$1(10));
                        SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$DebugLogs.DEFAULT_INSTANCE.createBuilder();
                        int max = Math.max(arrayList.size() - i5, 0);
                        if (max >= arrayList.size()) {
                            SystemHealthProto$DebugLogs systemHealthProto$DebugLogs = new RecentLogs.Snapshot((SystemHealthProto$DebugLogs) builder6.build()).debugLogs;
                            builder5.copyOnWrite();
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric6 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                            systemHealthProto$SystemHealthMetric6.debugLogs_ = systemHealthProto$DebugLogs;
                            systemHealthProto$SystemHealthMetric6.bitField0_ |= 1048576;
                        } else {
                            Object obj = ((AppLifecycleMonitor) arrayList.get(max)).AppLifecycleMonitor$ar$tracker;
                            throw null;
                        }
                    }
                    if (((Optional) metricRecorder.MetricRecorder$ar$activeTraceProvider).isPresent() && (metric2.metric.bitField0_ & 64) != 0 && ((Boolean) metricRecorder.MetricRecorder$ar$enableActiveTraceCollectionForCrash.get()).booleanValue()) {
                        List transform = ContextDataProvider.transform(((PrimesActiveTraceProvider) ((Optional) metricRecorder.MetricRecorder$ar$activeTraceProvider).get()).getActiveTraces(), new AccessibilityEventUtils$$ExternalSyntheticLambda0(15));
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric7 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        Internal.ProtobufList protobufList = systemHealthProto$SystemHealthMetric7.traceMetric_;
                        if (!protobufList.isModifiable()) {
                            systemHealthProto$SystemHealthMetric7.traceMetric_ = GeneratedMessageLite.mutableCopy(protobufList);
                        }
                        AbstractMessageLite.addAll(transform, systemHealthProto$SystemHealthMetric7.traceMetric_);
                    }
                    Optional optional = (Optional) metricRecorder.MetricRecorder$ar$interactionContextProvider;
                    if (optional.isPresent()) {
                        ImmutableList interactions = ((InteractionContextProvider) optional.get()).getInteractions();
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric8 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        Internal.ProtobufList protobufList2 = systemHealthProto$SystemHealthMetric8.interactionContext_;
                        if (!protobufList2.isModifiable()) {
                            systemHealthProto$SystemHealthMetric8.interactionContext_ = GeneratedMessageLite.mutableCopy(protobufList2);
                        }
                        AbstractMessageLite.addAll(interactions, systemHealthProto$SystemHealthMetric8.interactionContext_);
                    }
                    String str7 = metric2.customEventName;
                    if (metric2.isEventNameConstant) {
                        if (str7 != null) {
                            builder5.copyOnWrite();
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric9 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                            systemHealthProto$SystemHealthMetric9.bitField0_ |= 4;
                            systemHealthProto$SystemHealthMetric9.constantEventName_ = str7;
                        } else {
                            builder5.copyOnWrite();
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric10 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                            systemHealthProto$SystemHealthMetric10.bitField0_ &= -5;
                            systemHealthProto$SystemHealthMetric10.constantEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.constantEventName_;
                        }
                    } else if (str7 != null) {
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric11 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        systemHealthProto$SystemHealthMetric11.bitField0_ |= 2;
                        systemHealthProto$SystemHealthMetric11.customEventName_ = str7;
                    } else {
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric12 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        systemHealthProto$SystemHealthMetric12.bitField0_ &= -3;
                        systemHealthProto$SystemHealthMetric12.customEventName_ = SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.customEventName_;
                    }
                    metricRecorder.MetricRecorder$ar$globalExtensionProvider.get();
                    ExtensionMetric$MetricExtension extensionMetric$MetricExtension = metric2.metricExtension;
                    if (extensionMetric$MetricExtension != null) {
                        extensionMetric$MetricExtension.getClass();
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric13 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        extensionMetric$MetricExtension.getClass();
                        systemHealthProto$SystemHealthMetric13.metricExtension_ = extensionMetric$MetricExtension;
                        systemHealthProto$SystemHealthMetric13.bitField0_ |= 16777216;
                    }
                    String str8 = metric2.accountableComponentName;
                    if (str8 != null) {
                        SystemHealthProto$PackedHistogram.Builder builder7 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$AccountableComponent.DEFAULT_INSTANCE.createBuilder();
                        builder7.copyOnWrite();
                        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent5 = (SystemHealthProto$AccountableComponent) builder7.instance;
                        systemHealthProto$AccountableComponent5.bitField0_ |= 1;
                        systemHealthProto$AccountableComponent5.customName_ = str8;
                        builder5.copyOnWrite();
                        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric14 = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                        SystemHealthProto$AccountableComponent systemHealthProto$AccountableComponent6 = (SystemHealthProto$AccountableComponent) builder7.build();
                        systemHealthProto$AccountableComponent6.getClass();
                        systemHealthProto$SystemHealthMetric14.accountableComponent_ = systemHealthProto$AccountableComponent6;
                        systemHealthProto$SystemHealthMetric14.bitField0_ |= 33554432;
                    }
                    Object obj2 = metricRecorder.MetricRecorder$ar$metricDispatcher;
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric15 = (SystemHealthProto$SystemHealthMetric) builder5.build();
                    ImmutableList immutableList = (ImmutableList) ((MetricDispatcher) obj2).MetricDispatcher$ar$metricTransmittersSupplier.get();
                    ImmutableList.Builder builderWithExpectedSize = ImmutableList.builderWithExpectedSize(immutableList.size());
                    int size = immutableList.size();
                    for (int i6 = 0; i6 < size; i6++) {
                        try {
                            builderWithExpectedSize.add$ar$ds$4f674a09_0(((MetricTransmitter) immutableList.get(i6)).send(systemHealthProto$SystemHealthMetric15));
                        } catch (RuntimeException e) {
                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/core/MetricDispatcher", "dispatch", 73, "MetricDispatcher.java")).log("One transmitter failed to send message");
                            if (runtimeException == null) {
                                runtimeException = e;
                            } else {
                                runtimeException.addSuppressed(e);
                            }
                        }
                    }
                    if (runtimeException == null) {
                        ListenableFuture call = ContextDataProvider.whenAllSucceed$ar$class_merging$ar$class_merging(builderWithExpectedSize.build()).call(new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(1), DirectExecutor.INSTANCE);
                        ((Sampler) metricRecorder.MetricRecorder$ar$sampler).rateLimiter.incrementEventCount();
                        return call;
                    }
                    throw runtimeException;
                }
                throw null;
            }
        }, this.MetricRecorder$ar$executor);
    }

    public final long samplingRatePermilleIfShouldCollect(String str) {
        if (((Shutdown) this.MetricRecorder$ar$shutdown).shutdown) {
            return -1L;
        }
        Sampler sampler = (Sampler) this.MetricRecorder$ar$sampler;
        if (sampler.rateLimiter.isRateLimitExceeded()) {
            return -1L;
        }
        boolean z = sampler.enabled;
        SamplingStrategy samplingStrategy = sampler.samplingStrategy;
        if (!z) {
            return -1L;
        }
        return samplingStrategy.getSamplingRatePermilleIfShouldSample(str);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.Map, java.lang.Object] */
    public final void setCaptureGestureIdToAnnouncements(ImmutableMap immutableMap, ImmutableMap immutableMap2) {
        this.MetricRecorder$ar$metricStamperProvider.clear();
        if (!immutableMap.isEmpty()) {
            this.MetricRecorder$ar$metricStamperProvider.putAll(immutableMap);
        }
        this.MetricRecorder$ar$globalExtensionProvider.clear();
        if (!immutableMap2.isEmpty()) {
            this.MetricRecorder$ar$globalExtensionProvider.putAll(immutableMap2);
        }
    }

    public final boolean shouldCollectMetric(String str) {
        if (samplingRatePermilleIfShouldCollect(str) != -1) {
            return true;
        }
        return false;
    }

    public final boolean shouldRecordMetric() {
        Sampler sampler = (Sampler) this.MetricRecorder$ar$sampler;
        boolean z = sampler.enabled;
        SamplingStrategy samplingStrategy = sampler.samplingStrategy;
        if (z && samplingStrategy.getMetricServiceEnabled()) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v5, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v7, types: [javax.inject.Provider, java.lang.Object] */
    public MetricRecorder(MetricDispatcher metricDispatcher, Provider provider, Shutdown shutdown, WorkQueue workQueue, Provider provider2, Optional optional, Optional optional2, Provider provider3, Optional optional3, Provider provider4, Executor executor, Lazy lazy, Provider provider5) {
        this.MetricRecorder$ar$metricDispatcher = metricDispatcher;
        this.MetricRecorder$ar$shutdown = shutdown;
        this.MetricRecorder$ar$metricStamperProvider = provider;
        this.MetricRecorder$ar$executor = executor;
        this.MetricRecorder$ar$globalExtensionProvider = new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(provider2, 2);
        Context context = (Context) workQueue.WorkQueue$ar$consumerIndex.get();
        context.getClass();
        Executor executor2 = (Executor) workQueue.WorkQueue$ar$lastScheduledTask.get();
        executor2.getClass();
        SamplingStrategy.Factory factory = (SamplingStrategy.Factory) workQueue.WorkQueue$ar$blockingTasksInBuffer.get();
        factory.getClass();
        Boolean bool = (Boolean) workQueue.WorkQueue$ar$buffer.get();
        bool.getClass();
        boolean booleanValue = bool.booleanValue();
        Optional optional4 = (Optional) workQueue.WorkQueue$ar$producerIndex.get();
        optional4.getClass();
        this.MetricRecorder$ar$sampler = new Sampler(context, executor2, factory, lazy, booleanValue, optional4, provider5);
        this.MetricRecorder$ar$recentLogs = optional;
        this.MetricRecorder$ar$interactionContextProvider = optional2;
        this.MetricRecorder$ar$enableSafeFormatArgsAsStrings = provider3;
        this.MetricRecorder$ar$activeTraceProvider = optional3;
        this.MetricRecorder$ar$enableActiveTraceCollectionForCrash = provider4;
    }
}
