package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.actor.AutoScrollActor;
import com.google.android.accessibility.talkback.actor.NodeActionPerformer;
import com.google.android.accessibility.utils.StringBuilderUtils;
import com.google.android.accessibility.utils.labeling.LabelManager;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActorStateWritable {
    public final HapticPatternParser$$ExternalSyntheticLambda1 continuousRead$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 dimScreen$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 directionNavigation$ar$class_merging$ar$class_merging$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 focusHistory$ar$class_merging$f0c7926e_0$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl geminiState$ar$class_merging$ar$class_merging$ar$class_merging;
    public NodeActionPerformer.NodeActionRecord inputFocusActionRecord$ar$class_merging;
    public final LabelManager.State labelerState;
    public final FloatingActionButton.ShadowDelegateImpl languageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl nodeActionState$ar$class_merging$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl passThroughModeState$ar$class_merging$ar$class_merging$ar$class_merging;
    public final AutoScrollActor.StateReader scrollState;
    public final FloatingActionButton.ShadowDelegateImpl speechRateState$ar$class_merging$ar$class_merging;
    public final FloatingActionButton.ShadowDelegateImpl speechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public int lastWindowId = -1;
    public long lastWindowIdUptimeMs = 0;
    public long overrideFocusRestoreUptimeMs = 0;
    public int lastSystemAction = 0;

    public ActorStateWritable(HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda12, AutoScrollActor.StateReader stateReader, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda13, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda14, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl2, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl3, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl4, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl5, LabelManager.State state, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl6) {
        this.dimScreen$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.speechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl;
        this.continuousRead$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda12;
        this.scrollState = stateReader;
        this.focusHistory$ar$class_merging$f0c7926e_0$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda13;
        this.directionNavigation$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda14;
        this.nodeActionState$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl2;
        this.languageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl3;
        this.speechRateState$ar$class_merging$ar$class_merging = shadowDelegateImpl4;
        this.passThroughModeState$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl5;
        this.labelerState = state;
        this.geminiState$ar$class_merging$ar$class_merging$ar$class_merging = shadowDelegateImpl6;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(StringBuilderUtils.optionalTag("isSpeaking", this.speechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isSpeaking()), StringBuilderUtils.optionalTag("isSpeakingOrQueuedAndNotSourceIsVolumeAnnouncment", this.speechState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.isSpeakingOrQueuedAndNotSourceIsVolumeAnnouncment()), StringBuilderUtils.optionalInt("lastWindowId", this.lastWindowId, -1), StringBuilderUtils.optionalInt$ar$ds("lastWindowIdUptimeMs", this.lastWindowIdUptimeMs), StringBuilderUtils.optionalSubObj("inputFocusActionRecord", this.inputFocusActionRecord$ar$class_merging), StringBuilderUtils.optionalInt$ar$ds("overrideFocusRestoreUptimeMs", this.overrideFocusRestoreUptimeMs), StringBuilderUtils.optionalSubObj("scrollState", this.scrollState.get()), StringBuilderUtils.optionalTag("isSelectionModeActive", this.directionNavigation$ar$class_merging$ar$class_merging$ar$class_merging.isSelectionModeActive()), StringBuilderUtils.optionalField("currentGranularity", this.directionNavigation$ar$class_merging$ar$class_merging$ar$class_merging.getCurrentGranularity()), StringBuilderUtils.optionalTag("allowSelectLanguage", this.languageState$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.allowSelectLanguage()), StringBuilderUtils.optionalInt("speechRatePercent", this.speechRateState$ar$class_merging$ar$class_merging.getSpeechRatePercentage(), 100), StringBuilderUtils.optionalTag("passThroughModeState", this.passThroughModeState$ar$class_merging$ar$class_merging$ar$class_merging.isPassThroughModeActive()), StringBuilderUtils.optionalTag("hasAiCore", this.geminiState$ar$class_merging$ar$class_merging$ar$class_merging.hasAiCore()), StringBuilderUtils.optionalTag("isAiFeatureAvailable", this.geminiState$ar$class_merging$ar$class_merging$ar$class_merging.isAiFeatureAvailable()));
    }
}
