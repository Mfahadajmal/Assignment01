package com.google.android.accessibility.utils;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScrollableNodeInfo {
    private final boolean isRtl;
    public final AccessibilityNodeInfoCompat node;
    private boolean supportsLeftRightScrolling;
    private boolean supportsUpDownScrolling;

    public ScrollableNodeInfo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        this.node = accessibilityNodeInfoCompat;
        this.isRtl = z;
        this.supportsUpDownScrolling = false;
        this.supportsLeftRightScrolling = false;
        for (AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat : accessibilityNodeInfoCompat.getActionList()) {
            if (accessibilityActionCompat.equals(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP) || accessibilityActionCompat.equals(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN)) {
                this.supportsUpDownScrolling = true;
            }
            if (accessibilityActionCompat.equals(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT) || accessibilityActionCompat.equals(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT)) {
                this.supportsLeftRightScrolling = true;
            }
        }
    }

    private static ScrollableNodeInfo findMatchingScrollable(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        String str;
        if (!AccessibilityNodeInfoUtils.FILTER_AUTO_SCROLL.accept(accessibilityNodeInfoCompat)) {
            return null;
        }
        ScrollableNodeInfo scrollableNodeInfo = new ScrollableNodeInfo(accessibilityNodeInfoCompat, z);
        Integer supportedScrollDirection = scrollableNodeInfo.getSupportedScrollDirection(i);
        if (supportedScrollDirection != null) {
            if (new NodeActionFilter(TraversalStrategyUtils.convertSearchDirectionToScrollAction(supportedScrollDirection.intValue())).accept(accessibilityNodeInfoCompat)) {
                return scrollableNodeInfo;
            }
        } else {
            switch (i) {
                case 1:
                    str = "SEARCH_FOCUS_FORWARD";
                    break;
                case 2:
                    str = "SEARCH_FOCUS_BACKWARD";
                    break;
                case 3:
                    str = "SEARCH_FOCUS_LEFT";
                    break;
                case 4:
                    str = "SEARCH_FOCUS_RIGHT";
                    break;
                case 5:
                    str = "SEARCH_FOCUS_UP";
                    break;
                case 6:
                    str = "SEARCH_FOCUS_DOWN";
                    break;
                default:
                    str = String.format(Locale.ENGLISH, "unavailable direction: %d", Integer.valueOf(i));
                    break;
            }
            LogUtils.d("ScrollableNodeInfo", "findMatchingScrollable - supportedDirection is null, direction = %s", str);
        }
        return null;
    }

    public static ScrollableNodeInfo findScrollableNodeForDirection(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, boolean z2) {
        ScrollableNodeInfo findMatchingScrollable;
        if (i != 0) {
            if (z && (findMatchingScrollable = findMatchingScrollable(i, accessibilityNodeInfoCompat, z2)) != null) {
                return findMatchingScrollable;
            }
            if (accessibilityNodeInfoCompat.getParent() != null) {
                return findScrollableNodeForDirectionRecursive(i, accessibilityNodeInfoCompat.getParent(), z2);
            }
            return null;
        }
        return null;
    }

    private static ScrollableNodeInfo findScrollableNodeForDirectionRecursive(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        ScrollableNodeInfo findMatchingScrollable = findMatchingScrollable(i, accessibilityNodeInfoCompat, z);
        if (findMatchingScrollable != null) {
            return findMatchingScrollable;
        }
        if (accessibilityNodeInfoCompat.getParent() != null) {
            return findScrollableNodeForDirectionRecursive(i, accessibilityNodeInfoCompat.getParent(), z);
        }
        return null;
    }

    private final Integer getDirectionIfNativelySupported(int i) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.node;
        int convertSearchDirectionToScrollAction = TraversalStrategyUtils.convertSearchDirectionToScrollAction(i);
        Iterator it = accessibilityNodeInfoCompat.getActionList().iterator();
        while (it.hasNext()) {
            if (((AccessibilityNodeInfoCompat.AccessibilityActionCompat) it.next()).getId() == convertSearchDirectionToScrollAction) {
                return Integer.valueOf(i);
            }
        }
        return null;
    }

    public final Integer getSupportedScrollDirection(int i) {
        int i2;
        int i3;
        Integer directionIfNativelySupported = getDirectionIfNativelySupported(i);
        if (directionIfNativelySupported != null) {
            return directionIfNativelySupported;
        }
        if (TraversalStrategyUtils.isLogicalDirection(i)) {
            if (this.supportsUpDownScrolling) {
                if (!this.supportsLeftRightScrolling) {
                    if (i == 1) {
                        i3 = 6;
                    } else {
                        i3 = 5;
                    }
                    return getDirectionIfNativelySupported(i3);
                }
                return null;
            }
            if (this.supportsLeftRightScrolling) {
                boolean z = this.isRtl;
                int i4 = 4;
                if (true != z) {
                    i2 = 4;
                } else {
                    i2 = 3;
                }
                if (true != z) {
                    i4 = 3;
                }
                if (i != 1) {
                    i2 = i4;
                }
                return getDirectionIfNativelySupported(i2);
            }
        }
        if (TraversalStrategyUtils.isSpatialDirection(i)) {
            return getDirectionIfNativelySupported(TraversalStrategyUtils.getLogicalDirection(i, this.isRtl));
        }
        return null;
    }
}
