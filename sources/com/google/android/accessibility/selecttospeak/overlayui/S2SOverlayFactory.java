package com.google.android.accessibility.selecttospeak.overlayui;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.MagnificationConfig;
import com.google.android.accessibility.selecttospeak.UIManager;
import com.google.android.accessibility.selecttospeak.overlayui.ControlOverlays;
import com.google.android.accessibility.selecttospeak.ui.S2SWindowOverlay;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.S2sMagnificationConfig;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SOverlayFactory {
    public static final S2SOverlayFactory INSTANCE = new S2SOverlayFactory();

    private S2SOverlayFactory() {
    }

    public static /* synthetic */ SelectToSpeakOverlay createOverlay$default$ar$ds(S2SOverlayFactory s2SOverlayFactory, AccessibilityService accessibilityService, int i) {
        if (shouldDrawEmbedded$ar$ds(accessibilityService)) {
            S2SEmbeddedOverlay s2SEmbeddedOverlay = new S2SEmbeddedOverlay(accessibilityService, i);
            String str = UIManager.S2S_OVERLAY_IDENTIFIER;
            str.getClass();
            s2SEmbeddedOverlay.rootViewClassName = str;
            return s2SEmbeddedOverlay;
        }
        S2SWindowOverlay s2SWindowOverlay = new S2SWindowOverlay(accessibilityService, i, -2, -2);
        s2SWindowOverlay.rootViewClassName = UIManager.S2S_OVERLAY_IDENTIFIER;
        return s2SWindowOverlay;
    }

    private static final boolean shouldDrawEmbedded$ar$ds(AccessibilityService accessibilityService) {
        AccessibilityService.MagnificationController magnificationController;
        MagnificationConfig magnificationConfig;
        boolean isActivated;
        if (S2sMagnificationConfig.INSTANCE.get().embeddedDisplay(accessibilityService) && SpannableUtils$IdentifierSpan.isAtLeastU()) {
            magnificationController = accessibilityService.getMagnificationController();
            magnificationConfig = magnificationController.getMagnificationConfig();
            if (magnificationConfig != null) {
                isActivated = magnificationConfig.isActivated();
                if (isActivated) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public final SelectToSpeakOverlay createOverlay(AccessibilityService accessibilityService, ControlOverlays.OverlayTypes overlayTypes) {
        overlayTypes.getClass();
        LogUtils.v("S2SOverlayRendererFactory", "createOverlay %s embedded: %s", overlayTypes, Boolean.valueOf(shouldDrawEmbedded$ar$ds(accessibilityService)));
        int ordinal = overlayTypes.ordinal();
        if (ordinal != 0) {
            if (ordinal != 1) {
                if (ordinal != 2) {
                    if (ordinal != 3) {
                        throw new NoWhenBranchMatchedException();
                    }
                    throw new UnsupportedOperationException("Unsupported option");
                }
                if (shouldDrawEmbedded$ar$ds(accessibilityService)) {
                    LogUtils.d("S2SOverlayRendererFactory", "inflating embedded display overlay", new Object[0]);
                    return new S2SEmbeddedOverlay(accessibilityService, R.layout.layout_overlay_expandable_control_panel_and_trigger_button);
                }
                LogUtils.d("S2SOverlayRendererFactory", "inflating window overlay", new Object[0]);
                return new S2SWindowOverlay(accessibilityService, R.layout.layout_overlay_expandable_control_panel_and_trigger_button, SpannableUtils$IdentifierSpan.getDefaultScreenDensityContext(accessibilityService).getResources().getDimensionPixelSize(R.dimen.expanded_control_panel_and_trigger_button_overlay_width), -2);
            }
            return createOverlay$default$ar$ds(this, accessibilityService, R.layout.layout_overlay_collapsed_control_panel_and_trigger_button);
        }
        return createOverlay$default$ar$ds(this, accessibilityService, R.layout.layout_overlay_trigger_button);
    }
}
