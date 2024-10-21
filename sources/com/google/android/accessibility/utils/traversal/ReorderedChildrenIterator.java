package com.google.android.accessibility.utils.traversal;

import android.graphics.Rect;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReorderedChildrenIterator implements Iterator {
    private final NodeCachedBoundsCalculator boundsCalculator;
    private int currentIndex;
    private final Rect mTempLeftBounds = new Rect();
    private final Rect mTempRightBounds = new Rect();
    private final List nodes;
    private final AccessibilityNodeInfoCompat parent;

    private ReorderedChildrenIterator(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NodeCachedBoundsCalculator nodeCachedBoundsCalculator) {
        this.parent = accessibilityNodeInfoCompat;
        this.boundsCalculator = nodeCachedBoundsCalculator == null ? new NodeCachedBoundsCalculator() : nodeCachedBoundsCalculator;
        this.nodes = new ArrayList(accessibilityNodeInfoCompat.getChildCount());
        int childCount = accessibilityNodeInfoCompat.getChildCount();
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfoCompat child = this.parent.getChild(i);
            if (child != null) {
                this.nodes.add(child);
            }
        }
        if (!WebInterfaceUtils.isWebContainer(accessibilityNodeInfoCompat)) {
            List list = this.nodes;
            if (list.size() != 1) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (this.boundsCalculator.usesChildrenBounds((AccessibilityNodeInfoCompat) it.next())) {
                        List list2 = this.nodes;
                        if (list2.size() != 1) {
                            int size = list2.size();
                            AccessibilityNodeInfoCompat[] accessibilityNodeInfoCompatArr = new AccessibilityNodeInfoCompat[size];
                            list2.toArray(accessibilityNodeInfoCompatArr);
                            for (int i2 = size - 2; i2 >= 0; i2--) {
                                if (this.boundsCalculator.usesChildrenBounds(accessibilityNodeInfoCompatArr[i2])) {
                                    AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompatArr[i2];
                                    for (int i3 = i2 + 1; i3 < size; i3++) {
                                        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = accessibilityNodeInfoCompatArr[i3];
                                        if (accessibilityNodeInfoCompat2 != null && accessibilityNodeInfoCompat3 != null && compare$ar$ds$4d18eee7_0(this.boundsCalculator.getBounds(accessibilityNodeInfoCompat2), this.boundsCalculator.getBounds(accessibilityNodeInfoCompat3)) > 0) {
                                            accessibilityNodeInfoCompat2.getBoundsInScreen(this.mTempLeftBounds);
                                            accessibilityNodeInfoCompat3.getBoundsInScreen(this.mTempRightBounds);
                                            if (compare$ar$ds$4d18eee7_0(this.mTempLeftBounds, this.mTempRightBounds) < 0) {
                                                accessibilityNodeInfoCompatArr[i3 - 1] = accessibilityNodeInfoCompatArr[i3];
                                                accessibilityNodeInfoCompatArr[i3] = accessibilityNodeInfoCompat2;
                                            }
                                        }
                                    }
                                }
                            }
                            list2.clear();
                            list2.addAll(Arrays.asList(accessibilityNodeInfoCompatArr));
                        }
                    }
                }
            }
        }
        this.currentIndex = 0;
    }

    private static final int compare$ar$ds$4d18eee7_0(Rect rect, Rect rect2) {
        if (rect == null || rect2 == null || rect.bottom - rect2.top <= 0) {
            return -1;
        }
        if (rect.top - rect2.bottom >= 0) {
            return 1;
        }
        int i = rect.left - rect2.left;
        if (i != 0) {
            return i;
        }
        int i2 = rect.top - rect2.top;
        if (i2 != 0) {
            return i2;
        }
        int height = rect.height() - rect2.height();
        if (height != 0) {
            return -height;
        }
        int width = rect.width() - rect2.width();
        if (width != 0) {
            return -width;
        }
        return -1;
    }

    public static ReorderedChildrenIterator createAscendingIterator(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return createAscendingIterator(accessibilityNodeInfoCompat, null);
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.currentIndex < this.nodes.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final AccessibilityNodeInfoCompat next() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) this.nodes.get(this.currentIndex);
        this.currentIndex++;
        return accessibilityNodeInfoCompat;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("ReorderedChildrenIterator does not support remove operation");
    }

    public static ReorderedChildrenIterator createAscendingIterator(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NodeCachedBoundsCalculator nodeCachedBoundsCalculator) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return new ReorderedChildrenIterator(accessibilityNodeInfoCompat, nodeCachedBoundsCalculator);
    }
}
