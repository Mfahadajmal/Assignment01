package com.google.android.accessibility.talkback.focusmanagement;

import com.google.android.accessibility.talkback.ActorState;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusProcessorForScreenStateChange {
    public final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public ActorState actorState;
    public long handledOverrideFocusRestoreUptimeMs = 0;
    public HapticPatternParser$$ExternalSyntheticLambda1 pipeline$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum FocusResult {
        FAIL_NO_ACTIVE_WINDOW,
        FAIL_HAS_INTERACTION,
        FAIL_HAS_VALID_FOCUS,
        FAIL_DEFAULT,
        SUCCESS
    }

    public FocusProcessorForScreenStateChange(AccessibilityFocusMonitor accessibilityFocusMonitor) {
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
    }
}
