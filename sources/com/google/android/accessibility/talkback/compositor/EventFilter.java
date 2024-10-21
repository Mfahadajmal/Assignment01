package com.google.android.accessibility.talkback.compositor;

import com.google.android.accessibility.talkback.VoiceActionMonitor;
import com.google.android.accessibility.talkback.interpreters.AccessibilityFocusInterpreter;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;
import com.google.android.play.core.splitcompat.ingestion.Verifier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventFilter {
    public AccessibilityFocusInterpreter accessibilityFocusEventInterpreter$ar$class_merging;
    public final Verifier compositor$ar$class_merging$e4d5cfcc_0;
    public final GlobalVariables globalVariables;
    public final UiChangesTracker touchMonitor$ar$class_merging;
    public VoiceActionMonitor voiceActionDelegate$ar$class_merging;

    public EventFilter(Verifier verifier, UiChangesTracker uiChangesTracker, GlobalVariables globalVariables) {
        this.compositor$ar$class_merging$e4d5cfcc_0 = verifier;
        this.touchMonitor$ar$class_merging = uiChangesTracker;
        this.globalVariables = globalVariables;
    }
}
