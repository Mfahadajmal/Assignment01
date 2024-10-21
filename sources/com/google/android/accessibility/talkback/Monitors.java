package com.google.android.accessibility.talkback;

import com.google.android.accessibility.talkback.monitor.BatteryMonitor;
import com.google.android.accessibility.talkback.monitor.CallStateMonitor;
import com.google.android.accessibility.utils.monitor.CollectionState;
import com.google.android.accessibility.utils.monitor.SpeechStateMonitor;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.screenunderstanding.UiChangesTracker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Monitors {
    public final BatteryMonitor batteryMonitor;
    public final CallStateMonitor callMonitor;
    public final CollectionState collectionState;
    public final SpeechStateMonitor speechStateMonitor;
    public final UiChangesTracker touchMonitor$ar$class_merging;
    public final HapticPatternParser$$ExternalSyntheticLambda1 state$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    public final int eventTypeMask = 36732928;

    public Monitors(BatteryMonitor batteryMonitor, CallStateMonitor callStateMonitor, UiChangesTracker uiChangesTracker, SpeechStateMonitor speechStateMonitor, CollectionState collectionState) {
        this.batteryMonitor = batteryMonitor;
        this.callMonitor = callStateMonitor;
        this.touchMonitor$ar$class_merging = uiChangesTracker;
        this.speechStateMonitor = speechStateMonitor;
        this.collectionState = collectionState;
    }
}
