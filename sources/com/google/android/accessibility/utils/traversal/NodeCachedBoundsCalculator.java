package com.google.android.accessibility.utils.traversal;

import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeCachedBoundsCalculator {
    private static final Rect EMPTY_RECT = new Rect();
    public Map speakingNodesCache;
    private final Map boundsMap = new HashMap();
    private final Set calculatingNodes = new HashSet();
    private final Rect tempRect = new Rect();

    private final Rect getBoundsInternal(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return EMPTY_RECT;
        }
        if (this.calculatingNodes.contains(accessibilityNodeInfoCompat)) {
            LogUtils.w("NodeCachedBoundsCalculator", "node tree loop detected while calculating node bounds", new Object[0]);
            return EMPTY_RECT;
        }
        Rect rect = (Rect) this.boundsMap.get(accessibilityNodeInfoCompat);
        if (rect == null) {
            this.calculatingNodes.add(accessibilityNodeInfoCompat);
            if (!AccessibilityNodeInfoUtils.isVisible(accessibilityNodeInfoCompat)) {
                rect = EMPTY_RECT;
            } else if (AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat, this.speakingNodesCache)) {
                rect = new Rect();
                accessibilityNodeInfoCompat.getBoundsInScreen(rect);
            } else {
                int childCount = accessibilityNodeInfoCompat.getChildCount();
                int i = Integer.MIN_VALUE;
                int i2 = Integer.MAX_VALUE;
                int i3 = Integer.MAX_VALUE;
                boolean z = false;
                int i4 = Integer.MIN_VALUE;
                for (int i5 = 0; i5 < childCount; i5++) {
                    Rect boundsInternal = getBoundsInternal(accessibilityNodeInfoCompat.getChild(i5));
                    if (!boundsInternal.equals(EMPTY_RECT)) {
                        if (boundsInternal.top < i2) {
                            i2 = boundsInternal.top;
                        }
                        if (boundsInternal.left < i3) {
                            i3 = boundsInternal.left;
                        }
                        if (boundsInternal.right > i4) {
                            i4 = boundsInternal.right;
                        }
                        if (boundsInternal.bottom > i) {
                            i = boundsInternal.bottom;
                        }
                        z = true;
                    }
                }
                rect = new Rect();
                accessibilityNodeInfoCompat.getBoundsInScreen(rect);
                if (z) {
                    rect.top = Math.max(i2, rect.top);
                    rect.left = Math.max(i3, rect.left);
                    rect.right = Math.min(i4, rect.right);
                    rect.bottom = Math.min(i, rect.bottom);
                }
            }
            this.boundsMap.put(accessibilityNodeInfoCompat, rect);
            this.calculatingNodes.remove(accessibilityNodeInfoCompat);
        }
        return rect;
    }

    public final Rect getBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect boundsInternal = getBoundsInternal(accessibilityNodeInfoCompat);
        if (boundsInternal.equals(EMPTY_RECT)) {
            return null;
        }
        return boundsInternal;
    }

    public final boolean usesChildrenBounds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        Rect bounds;
        if (accessibilityNodeInfoCompat == null || (bounds = getBounds(accessibilityNodeInfoCompat)) == null) {
            return false;
        }
        accessibilityNodeInfoCompat.getBoundsInScreen(this.tempRect);
        if (this.tempRect.equals(bounds)) {
            return false;
        }
        return true;
    }
}
