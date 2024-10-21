package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.actor.AutoScrollActor;
import com.google.android.accessibility.talkback.actor.NodeActionPerformer;
import com.google.android.accessibility.utils.labeling.LabelManager;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActorState {
    public final ActorStateWritable writable;

    public ActorState(ActorStateWritable actorStateWritable) {
        this.writable = actorStateWritable;
    }

    public final HapticPatternParser$$ExternalSyntheticLambda1 getContinuousRead$ar$class_merging$ar$class_merging() {
        return this.writable.continuousRead$ar$class_merging$ar$class_merging;
    }

    public final HapticPatternParser$$ExternalSyntheticLambda1 getDimScreen$ar$class_merging$ar$class_merging() {
        return this.writable.dimScreen$ar$class_merging$ar$class_merging;
    }

    public final HapticPatternParser$$ExternalSyntheticLambda1 getDirectionNavigation$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.writable.directionNavigation$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final HapticPatternParser$$ExternalSyntheticLambda1 getFocusHistory$ar$class_merging$ar$class_merging() {
        return this.writable.focusHistory$ar$class_merging$f0c7926e_0$ar$class_merging;
    }

    public final FloatingActionButton.ShadowDelegateImpl getGeminiState$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.writable.geminiState$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final NodeActionPerformer.NodeActionRecord getInputFocusActionRecord$ar$class_merging() {
        return this.writable.inputFocusActionRecord$ar$class_merging;
    }

    public final LabelManager.State getLabelManagerState() {
        return this.writable.labelerState;
    }

    public final FloatingActionButton.ShadowDelegateImpl getLanguageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.writable.languageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final long getOverrideFocusRestoreUptimeMs() {
        return this.writable.overrideFocusRestoreUptimeMs;
    }

    public final AutoScrollActor.StateReader getScrollerState() {
        return this.writable.scrollState;
    }

    public final FloatingActionButton.ShadowDelegateImpl getSpeechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.writable.speechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final String toString() {
        return this.writable.toString();
    }
}
