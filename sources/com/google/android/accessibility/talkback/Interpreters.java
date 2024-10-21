package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.actor.voicecommands.VoiceCommandProcessor;
import com.google.android.accessibility.talkback.interpreters.AccessibilityEventIdleInterpreter;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.accessibility.talkback.interpreters.AutoScrollInterpreter;
import com.google.android.accessibility.talkback.interpreters.DirectionNavigationInterpreter;
import com.google.android.accessibility.talkback.interpreters.FullScreenReadInterpreter;
import com.google.android.accessibility.talkback.interpreters.HintEventInterpreter;
import com.google.android.accessibility.talkback.interpreters.InputFocusInterpreter;
import com.google.android.accessibility.talkback.interpreters.ManualScrollInterpreter;
import com.google.android.accessibility.talkback.interpreters.PassThroughModeInterpreter;
import com.google.android.accessibility.talkback.interpreters.ScrollPositionInterpreter;
import com.google.android.accessibility.talkback.interpreters.StateChangeEventInterpreter;
import com.google.android.accessibility.talkback.interpreters.SubtreeChangeEventInterpreter;
import com.google.android.accessibility.talkback.interpreters.UiChangeEventInterpreter;
import com.google.android.accessibility.utils.input.HeadsUpNotificationEventInterpreter;
import com.google.android.accessibility.utils.input.ScrollEventInterpreter;
import com.google.android.accessibility.utils.input.SelectionEventInterpreter;
import j$.util.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Interpreters {
    public final AccessibilityEventIdleInterpreter accessibilityEventIdleInterpreter;
    public final AccessibilityFocusInterpreter accessibilityFocusInterpreter;
    public final AutoScrollInterpreter autoScrollInterpreter;
    public final FullScreenReadInterpreter continuousReadInterpreter;
    public final DirectionNavigationInterpreter directionNavigationInterpreter;
    public final int eventTypeMask;
    public final HintEventInterpreter hintEventInterpreter;
    public final InputFocusInterpreter inputFocusInterpreter;
    public final ManualScrollInterpreter manualScrollInterpreter;
    public final HeadsUpNotificationEventInterpreter notificationEventInterpreter;
    public final PassThroughModeInterpreter passThroughModeInterpreter;
    public Optional pipelineInterpretations = Optional.empty();
    public final ScrollEventInterpreter scrollEventInterpreter;
    public final ScrollPositionInterpreter scrollPositionInterpreter;
    public final SelectionEventInterpreter selectionInterpreter;
    public final StateChangeEventInterpreter stateChangeEventInterpreter;
    public final SubtreeChangeEventInterpreter subtreeChangeEventInterpreter;
    public final UiChangeEventInterpreter uiChangeEventInterpreter;
    public final VoiceCommandProcessor voiceCommandProcessor;

    public Interpreters(InputFocusInterpreter inputFocusInterpreter, ScrollEventInterpreter scrollEventInterpreter, ManualScrollInterpreter manualScrollInterpreter, AutoScrollInterpreter autoScrollInterpreter, ScrollPositionInterpreter scrollPositionInterpreter, SelectionEventInterpreter selectionEventInterpreter, AccessibilityFocusInterpreter accessibilityFocusInterpreter, FullScreenReadInterpreter fullScreenReadInterpreter, StateChangeEventInterpreter stateChangeEventInterpreter, DirectionNavigationInterpreter directionNavigationInterpreter, HintEventInterpreter hintEventInterpreter, VoiceCommandProcessor voiceCommandProcessor, PassThroughModeInterpreter passThroughModeInterpreter, SubtreeChangeEventInterpreter subtreeChangeEventInterpreter, AccessibilityEventIdleInterpreter accessibilityEventIdleInterpreter, UiChangeEventInterpreter uiChangeEventInterpreter, HeadsUpNotificationEventInterpreter headsUpNotificationEventInterpreter) {
        this.inputFocusInterpreter = inputFocusInterpreter;
        this.scrollEventInterpreter = scrollEventInterpreter;
        this.manualScrollInterpreter = manualScrollInterpreter;
        this.autoScrollInterpreter = autoScrollInterpreter;
        this.scrollPositionInterpreter = scrollPositionInterpreter;
        this.selectionInterpreter = selectionEventInterpreter;
        this.accessibilityFocusInterpreter = accessibilityFocusInterpreter;
        this.continuousReadInterpreter = fullScreenReadInterpreter;
        this.stateChangeEventInterpreter = stateChangeEventInterpreter;
        this.directionNavigationInterpreter = directionNavigationInterpreter;
        this.hintEventInterpreter = hintEventInterpreter;
        this.voiceCommandProcessor = voiceCommandProcessor;
        this.passThroughModeInterpreter = passThroughModeInterpreter;
        this.subtreeChangeEventInterpreter = subtreeChangeEventInterpreter;
        this.accessibilityEventIdleInterpreter = accessibilityEventIdleInterpreter;
        this.uiChangeEventInterpreter = uiChangeEventInterpreter;
        this.notificationEventInterpreter = headsUpNotificationEventInterpreter;
        manualScrollInterpreter.listener$ar$class_merging$c6a3d175_0 = accessibilityFocusInterpreter;
        scrollEventInterpreter.addListener(manualScrollInterpreter);
        scrollEventInterpreter.addListener(scrollPositionInterpreter);
        scrollEventInterpreter.addListener(autoScrollInterpreter);
        selectionEventInterpreter.listeners.add(new Interpreters$$ExternalSyntheticLambda1(this, 1));
        if (headsUpNotificationEventInterpreter != null) {
            headsUpNotificationEventInterpreter.listeners.add(new Interpreters$$ExternalSyntheticLambda1(this, r1));
        }
        this.eventTypeMask = (headsUpNotificationEventInterpreter != null ? 4194368 : 0) | 4250159 | subtreeChangeEventInterpreter.maskEventType | 3178505;
    }
}
