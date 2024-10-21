package com.google.android.accessibility.talkback.actor;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.accessibility.talkback.ActorStateWritable;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemActionPerformer {
    public static final ImmutableList EXCLUDED_ACTIONS = ImmutableList.copyOf((Collection) Arrays.asList(1, 2, 3, 4, 10, 11, 12, 100, 6, 8, 9, 13, 16, 17, 18, 19, 20));
    public ActorStateWritable actorState;
    private final AccessibilityService service;

    public SystemActionPerformer(AccessibilityService accessibilityService) {
        this.service = accessibilityService;
    }

    public final boolean performAction(int i) {
        List systemActions;
        ActorStateWritable actorStateWritable = this.actorState;
        if (actorStateWritable != null) {
            actorStateWritable.lastSystemAction = i;
        }
        if (FeatureSupport.supportGetSystemActions(this.service) && i != 10) {
            systemActions = this.service.getSystemActions();
            if (systemActions.contains(new AccessibilityNodeInfo.AccessibilityAction(i, null))) {
                return this.service.performGlobalAction(i);
            }
            if (i == 14 && systemActions.contains(new AccessibilityNodeInfo.AccessibilityAction(100, null))) {
                return this.service.performGlobalAction(100);
            }
            return false;
        }
        return this.service.performGlobalAction(i);
    }
}
