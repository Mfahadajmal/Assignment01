package com.google.android.accessibility.selecttospeak.overlayui;

import android.view.View;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.accessibility.talkback.menurules.NodeMenuRuleCreator;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import io.grpc.internal.RetryingNameResolver;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ControlOverlays {
    public SelectToSpeakOverlay collapsedOverlay;
    public CollapsibleControlPanel collapsedPanel;
    public RetryingNameResolver.ResolutionResultListener controlListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public SelectToSpeakOverlay expandableOverlay;
    public CollapsibleControlPanel expandablePanel;
    public boolean isInflated;
    public NodeMenuRuleCreator overlayCoordinatesSynchronizer$ar$class_merging;
    public View.OnClickListener triggerButtonClickListener;
    public SelectToSpeakOverlay triggerButtonOverlay;
    public final List _triggerButtons = new ArrayList();
    public OverlayTypes foregroundOverlayType = OverlayTypes.NONE;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OverlayTypes {
        private static final /* synthetic */ OverlayTypes[] $VALUES;
        public static final OverlayTypes COLLAPSED;
        public static final OverlayTypes EXPANDABLE;
        public static final OverlayTypes NONE;
        public static final OverlayTypes TRIGGER_BUTTON;

        static {
            OverlayTypes overlayTypes = new OverlayTypes("TRIGGER_BUTTON", 0);
            TRIGGER_BUTTON = overlayTypes;
            OverlayTypes overlayTypes2 = new OverlayTypes("COLLAPSED", 1);
            COLLAPSED = overlayTypes2;
            OverlayTypes overlayTypes3 = new OverlayTypes("EXPANDABLE", 2);
            EXPANDABLE = overlayTypes3;
            OverlayTypes overlayTypes4 = new OverlayTypes("NONE", 3);
            NONE = overlayTypes4;
            OverlayTypes[] overlayTypesArr = {overlayTypes, overlayTypes2, overlayTypes3, overlayTypes4};
            $VALUES = overlayTypesArr;
            OnDevicePoseDetectionLogEvent.enumEntries$ar$class_merging(overlayTypesArr);
        }

        private OverlayTypes(String str, int i) {
        }

        public static OverlayTypes[] values() {
            return (OverlayTypes[]) $VALUES.clone();
        }
    }

    public final void collapse$ar$ds() {
        CollapsibleControlPanel collapsibleControlPanel = this.expandablePanel;
        if (collapsibleControlPanel != null) {
            collapsibleControlPanel.collapse$ar$ds$57f5e804_0(false);
        }
    }

    public final void fractionalToPixelCoordinates(float[] fArr, int[] iArr) {
        SelectToSpeakOverlay selectToSpeakOverlay = this.triggerButtonOverlay;
        if (selectToSpeakOverlay != null) {
            selectToSpeakOverlay.fractionalToPixelCoordinates(fArr, iArr);
        }
    }

    public final void hideAll() {
        LogUtils.v("ControlOverlays", "hideAll overlays: %s %s", this.triggerButtonOverlay, this.expandableOverlay);
        SelectToSpeakOverlay selectToSpeakOverlay = this.triggerButtonOverlay;
        if (selectToSpeakOverlay != null) {
            selectToSpeakOverlay.hide();
        }
        SelectToSpeakOverlay selectToSpeakOverlay2 = this.collapsedOverlay;
        if (selectToSpeakOverlay2 != null) {
            selectToSpeakOverlay2.hide();
        }
        SelectToSpeakOverlay selectToSpeakOverlay3 = this.expandableOverlay;
        if (selectToSpeakOverlay3 != null) {
            selectToSpeakOverlay3.hide();
        }
    }

    public final boolean isCollapsed() {
        CollapsibleControlPanel collapsibleControlPanel = this.expandablePanel;
        if (collapsibleControlPanel != null) {
            return collapsibleControlPanel.isCollapsed;
        }
        return false;
    }

    public final void setForegroundOverlayType(OverlayTypes overlayTypes) {
        overlayTypes.getClass();
        this.foregroundOverlayType = overlayTypes;
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.accessibility.selecttospeak.overlayui.SelectToSpeakOverlay, java.lang.Object] */
    public final void setFractionalCoordinates(float f, float f2) {
        NodeMenuRuleCreator nodeMenuRuleCreator = this.overlayCoordinatesSynchronizer$ar$class_merging;
        if (nodeMenuRuleCreator != null) {
            float[] fArr = (float[]) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleSpannables;
            fArr[0] = f;
            fArr[1] = f2;
            nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleViewPager.fractionalToPixelCoordinates(fArr, (int[]) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleUnlabeledNode);
            int[] iArr = (int[]) nodeMenuRuleCreator.NodeMenuRuleCreator$ar$ruleUnlabeledNode;
            nodeMenuRuleCreator.setPixelCoordinates(iArr[0], iArr[1]);
        }
    }
}
