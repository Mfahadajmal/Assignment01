package com.google.android.accessibility.selecttospeak.iterator;

import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.selecttospeak.AccessibilityNodeInfoCompatWithVisibility;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextFinderFromNodeInfo implements TextLocationFinder {
    private final AccessibilityNodeInfoCompatWithVisibility nodeInfo$ar$class_merging;
    private final boolean supportsTextLocation;

    public TextFinderFromNodeInfo(AccessibilityNodeInfoCompatWithVisibility accessibilityNodeInfoCompatWithVisibility) {
        accessibilityNodeInfoCompatWithVisibility.getClass();
        this.nodeInfo$ar$class_merging = accessibilityNodeInfoCompatWithVisibility;
        this.supportsTextLocation = AccessibilityNodeInfoUtils.supportsTextLocation(accessibilityNodeInfoCompatWithVisibility.compat());
    }

    private final List nodeBound() {
        Rect rect = new Rect();
        this.nodeInfo$ar$class_merging.compat().getBoundsInScreen(rect);
        return OnDeviceLanguageIdentificationLogEvent.listOf(rect);
    }

    @Override // com.google.android.accessibility.selecttospeak.iterator.TextLocationFinder
    public final boolean getSupportsTextLocation() {
        return this.supportsTextLocation;
    }

    @Override // com.google.android.accessibility.selecttospeak.iterator.TextLocationFinder
    public final List getTextLocation(boolean z, int i, int i2, int i3) {
        if (z) {
            if (this.nodeInfo$ar$class_merging.compat().refresh() && this.nodeInfo$ar$class_merging.compat().isVisibleToUser()) {
                return getTextLocation(false, i, i2, i3);
            }
            return EmptyList.INSTANCE;
        }
        if (this.nodeInfo$ar$class_merging.getHasOcrBlocks()) {
            Rect specificWordBounds = this.nodeInfo$ar$class_merging.getSpecificWordBounds(i + i2);
            if (specificWordBounds == null) {
                return EmptyList.INSTANCE;
            }
            return OnDeviceLanguageIdentificationLogEvent.listOf(specificWordBounds);
        }
        if (this.supportsTextLocation) {
            AccessibilityNodeInfoCompat compat = this.nodeInfo$ar$class_merging.compat();
            List textLocations = AccessibilityNodeInfoUtils.getTextLocations(compat, AccessibilityNodeInfoUtils.getText(compat), i2 + i, i + i3);
            if (textLocations == null) {
                return nodeBound();
            }
            return textLocations;
        }
        return nodeBound();
    }
}
