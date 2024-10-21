package com.google.android.accessibility.talkback.utils;

import android.content.Context;
import com.google.android.accessibility.utils.DiagnosticOverlayController;
import com.google.android.accessibility.utils.DiagnosticOverlayUtils;
import java.util.HashMap;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DiagnosticOverlayControllerImpl implements DiagnosticOverlayController {
    public final Context context;
    public DiagnosticOverlay diagnosticOverlay;
    public boolean enabled;
    public HighlightOverlay highlightOverlay;
    public static final HashMap traversedIdToNode = new HashMap();
    public static HashMap unfocusedIdToNode = new HashMap();
    public static HashSet refocusNodePath = new HashSet();

    public DiagnosticOverlayControllerImpl(Context context) {
        this.context = context;
        DiagnosticOverlayUtils.diagnosticOverlayController = this;
    }

    public final void clearHighlight() {
        if (!isHighlightOverlayEnabled()) {
            return;
        }
        this.highlightOverlay.clearHighlight();
    }

    public final boolean isHighlightOverlayEnabled() {
        if (this.enabled && this.highlightOverlay != null && this.diagnosticOverlay != null) {
            return true;
        }
        return false;
    }
}
