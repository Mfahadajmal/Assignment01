package com.google.android.accessibility.talkback.interpreters;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.talkback.Interpretation$HintableEvent;
import com.google.android.accessibility.talkback.Interpretation$ID;
import com.google.android.accessibility.talkback.focusmanagement.action.NavigationAction;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionInfo;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.input.TextEventInterpretation;
import com.google.android.accessibility.utils.input.TextEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import io.grpc.okhttp.internal.OptionalMethod;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HintEventInterpreter implements AccessibilityEventListener, TextEventInterpreter.InterpretationConsumer {
    public ActorState actorState;
    private final Context context;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipelineInterpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public HintEventInterpreter(Context context) {
        this.context = context;
    }

    @Override // com.google.android.accessibility.utils.input.TextEventInterpreter.InterpretationConsumer
    public final void accept$ar$class_merging$ar$class_merging$d1a82255_0$ar$class_merging(OptionalMethod optionalMethod) {
        if (SpannableUtils$IdentifierSpan.supportTextSuggestion()) {
            Object obj = optionalMethod.OptionalMethod$ar$returnType;
            AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(((AccessibilityEvent) optionalMethod.OptionalMethod$ar$methodName).getSource());
            if (compat != null && obj != null) {
                int i = ((TextEventInterpretation) obj).event;
                boolean z = false;
                if (i != 1073741837) {
                    if (i != 1073741828 || compat.getTextSelectionStart() != compat.getTextSelectionEnd()) {
                        return;
                    }
                } else if (i != 1073741828) {
                    z = true;
                }
                if (!AccessibilityNodeInfoUtils.getSpellingSuggestions(this.context, compat, z).isEmpty()) {
                    this.pipelineInterpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging((Performance.EventId) optionalMethod.OptionalMethod$ar$methodParams, null, new Interpretation$ID(Interpretation$ID.Value.SPELLING_SUGGESTION_HINT), null);
                }
            }
        }
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 1081353;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        boolean z;
        boolean z2;
        CursorGranularity cursorGranularity;
        int eventType = accessibilityEvent.getEventType();
        boolean z3 = false;
        if (eventType == 8) {
            if (FormFactorUtils.getInstance().isAndroidTv || AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource()) == null) {
                return;
            }
        } else if (eventType == 32768) {
            FocusActionInfo focusActionInfoFromEvent = this.actorState.getFocusHistory$ar$class_merging$ar$class_merging().getFocusActionInfoFromEvent(accessibilityEvent);
            if (focusActionInfoFromEvent != null) {
                if (!focusActionInfoFromEvent.forceMuteFeedback) {
                    NavigationAction navigationAction = focusActionInfoFromEvent.navigationAction;
                    if (navigationAction != null && (cursorGranularity = navigationAction.originalNavigationGranularity) != null && cursorGranularity.isMicroGranularity()) {
                        return;
                    }
                } else {
                    return;
                }
            }
            AccessibilityNodeInfoCompat compat = AccessibilityNodeInfoUtils.toCompat(accessibilityEvent.getSource());
            if (focusActionInfoFromEvent != null && focusActionInfoFromEvent.forceFeedbackEvenIfAudioPlaybackActive()) {
                z = true;
            } else {
                z = false;
            }
            if (focusActionInfoFromEvent != null && focusActionInfoFromEvent.forceFeedbackEvenIfMicrophoneActive()) {
                z3 = true;
            }
            if (compat == null) {
                return;
            }
            z2 = z3;
            z3 = z;
            this.pipelineInterpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$HintableEvent(z3, z2), null);
        }
        z2 = false;
        this.pipelineInterpretationReceiver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.input$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(eventId, accessibilityEvent, new Interpretation$HintableEvent(z3, z2), null);
    }
}
