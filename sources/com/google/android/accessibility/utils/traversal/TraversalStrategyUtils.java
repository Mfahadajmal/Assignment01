package com.google.android.accessibility.utils.traversal;

import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.Filter;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraversalStrategyUtils {
    private static final Filter DEFAULT_FILTER = new Filter() { // from class: com.google.android.accessibility.utils.traversal.TraversalStrategyUtils.3
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            if (((AccessibilityNodeInfoCompat) obj) != null) {
                return true;
            }
            return false;
        }
    };

    public static int convertSearchDirectionToScrollAction(int i) {
        if (i == 1) {
            return 4096;
        }
        if (i == 2) {
            return 8192;
        }
        if (i == 3) {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT.getId();
        }
        if (i == 4) {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT.getId();
        }
        if (i == 5) {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP.getId();
        }
        if (i == 6) {
            return AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN.getId();
        }
        return 0;
    }

    public static String directionToString(int i) {
        switch (i) {
            case 0:
                return "SEARCH_FOCUS_UNKNOWN";
            case 1:
                return "SEARCH_FOCUS_FORWARD";
            case 2:
                return "SEARCH_FOCUS_BACKWARD";
            case 3:
                return "SEARCH_FOCUS_LEFT";
            case 4:
                return "SEARCH_FOCUS_RIGHT";
            case 5:
                return "SEARCH_FOCUS_UP";
            case 6:
                return "SEARCH_FOCUS_DOWN";
            default:
                return "(unhandled)";
        }
    }

    public static AccessibilityNodeInfoCompat findFirstFocusInNodeTree(TraversalStrategy traversalStrategy, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Filter filter) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        AccessibilityNodeInfoCompat focusFirst = traversalStrategy.focusFirst(accessibilityNodeInfoCompat, i);
        if (filter.accept(focusFirst)) {
            return focusFirst;
        }
        return searchFocus(traversalStrategy, focusFirst, i, filter);
    }

    public static int getLogicalDirection(int i, boolean z) {
        switch (i) {
            case 1:
            case 6:
                return 1;
            case 2:
            case 5:
                return 2;
            case 3:
                if (true != z) {
                    return 2;
                }
                return 1;
            case 4:
                if (true == z) {
                    return 2;
                }
                return 1;
            default:
                throw new IllegalArgumentException("direction must be a SearchDirection");
        }
    }

    public static TraversalStrategy getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AppLifecycleMonitor appLifecycleMonitor, int i) {
        switch (i) {
            case 1:
            case 2:
                return new OrderedTraversalStrategy(accessibilityNodeInfoCompat);
            case 3:
            case 4:
            case 5:
            case 6:
                return new DirectionalTraversalStrategy(accessibilityNodeInfoCompat, appLifecycleMonitor);
            default:
                throw new IllegalArgumentException("direction must be a SearchDirection");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00ba, code lost:
    
        if (r1.right >= r2.left) goto L53;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean isAutoScrollEdgeListItem$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(final androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4, com.google.android.accessibility.utils.ScrollableNodeInfo r5, boolean r6, int r7, com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor r8) {
        /*
            java.lang.Integer r7 = r5.getSupportedScrollDirection(r7)
            r0 = 0
            if (r7 != 0) goto L8
            return r0
        L8:
            int r1 = r7.intValue()
            java.lang.Integer r1 = r5.getSupportedScrollDirection(r1)
            r2 = 0
            if (r1 != 0) goto L14
            goto L3f
        L14:
            int r3 = r1.intValue()
            boolean r3 = isLogicalDirection(r3)
            if (r3 == 0) goto L2a
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r8 = r5.node
            com.google.android.accessibility.utils.traversal.OrderedTraversalStrategy r2 = new com.google.android.accessibility.utils.traversal.OrderedTraversalStrategy
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r8 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getRoot(r8)
            r2.<init>(r8)
            goto L3f
        L2a:
            int r1 = r1.intValue()
            boolean r1 = isSpatialDirection(r1)
            if (r1 == 0) goto L3f
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r1 = r5.node
            com.google.android.accessibility.utils.traversal.DirectionalTraversalStrategy r2 = new com.google.android.accessibility.utils.traversal.DirectionalTraversalStrategy
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getRoot(r1)
            r2.<init>(r1, r8)
        L3f:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r5 = r5.node
            int r7 = r7.intValue()
            com.google.android.accessibility.utils.Filter r8 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.FILTER_AUTO_SCROLL
            boolean r1 = r5.isScrollable()
            if (r1 == 0) goto Ld0
            boolean r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.hasAncestor(r4, r5)
            if (r1 != 0) goto L5b
            boolean r1 = r5.equals(r4)
            if (r1 != 0) goto L5b
            goto Ld0
        L5b:
            com.google.android.accessibility.utils.Filter r1 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.FILTER_SHOULD_FOCUS
            if (r6 == 0) goto L68
            com.google.android.accessibility.utils.traversal.TraversalStrategyUtils$1 r6 = new com.google.android.accessibility.utils.traversal.TraversalStrategyUtils$1
            r6.<init>()
            com.google.android.accessibility.utils.Filter r1 = r1.and(r6)
        L68:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = searchFocus(r2, r4, r7, r1)
            r6 = 1
            if (r4 == 0) goto Lcf
            boolean r7 = r4.equals(r5)
            if (r7 == 0) goto L76
            goto Lcf
        L76:
            boolean r7 = r4.isVisibleToUser()
            if (r7 != 0) goto Lbd
            boolean r7 = com.google.android.accessibility.utils.WebInterfaceUtils.supportsWebActions(r4)
            if (r7 == 0) goto Lbd
            com.google.android.accessibility.utils.traversal.TraversalStrategyUtils$2 r7 = new com.google.android.accessibility.utils.traversal.TraversalStrategyUtils$2
            r7.<init>()
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r7 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getMatchingAncestor(r4, r7)
            if (r7 == 0) goto Lbd
            boolean r1 = r7.isVisibleToUser()
            if (r1 == 0) goto Lcf
            android.graphics.Rect r1 = new android.graphics.Rect
            r1.<init>()
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r7.getBoundsInScreen(r1)
            r4.getBoundsInScreen(r2)
            int r7 = r1.top
            int r3 = r2.bottom
            if (r7 > r3) goto Lbd
            int r7 = r1.bottom
            int r3 = r2.top
            if (r7 >= r3) goto Lb0
            goto Lbd
        Lb0:
            int r7 = r1.left
            int r3 = r2.right
            if (r7 > r3) goto Lbd
            int r7 = r1.right
            int r1 = r2.left
            if (r7 < r1) goto Lbd
            goto Lcf
        Lbd:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getMatchingAncestor(r4, r8)
        Lc1:
            if (r4 == 0) goto Lcf
            boolean r7 = r5.equals(r4)
            if (r7 == 0) goto Lca
            goto Ld0
        Lca:
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getMatchingAncestor(r4, r8)
            goto Lc1
        Lcf:
            r0 = r6
        Ld0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.traversal.TraversalStrategyUtils.isAutoScrollEdgeListItem$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, com.google.android.accessibility.utils.ScrollableNodeInfo, boolean, int, com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor):boolean");
    }

    public static boolean isLogicalDirection(int i) {
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    public static boolean isSpatialDirection(int i) {
        switch (i) {
            case 1:
            case 2:
                return false;
            case 3:
            case 4:
            case 5:
            case 6:
                return true;
            default:
                throw new IllegalArgumentException("direction must be a SearchDirection");
        }
    }

    public static AccessibilityNodeInfoCompat searchFocus(TraversalStrategy traversalStrategy, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Filter filter) {
        if (traversalStrategy == null || accessibilityNodeInfoCompat == null) {
            return null;
        }
        if (filter == null) {
            filter = DEFAULT_FILTER;
        }
        HashSet hashSet = new HashSet();
        do {
            hashSet.add(accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat = traversalStrategy.findFocus(accessibilityNodeInfoCompat, i);
            if (hashSet.contains(accessibilityNodeInfoCompat)) {
                LogUtils.e("TraversalStrategyUtils", "Found duplicate during traversal: %s", accessibilityNodeInfoCompat);
                return null;
            }
            if (accessibilityNodeInfoCompat == null) {
                break;
            }
        } while (!filter.accept(accessibilityNodeInfoCompat));
        return accessibilityNodeInfoCompat;
    }
}
