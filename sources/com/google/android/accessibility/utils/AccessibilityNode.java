package com.google.android.accessibility.utils;

import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityNode {
    public static final SpannableUtils$IdentifierSpan FACTORY$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$IdentifierSpan();
    private AccessibilityNodeInfo nodeBare;
    private AccessibilityNodeInfoCompat nodeCompat;

    protected AccessibilityNode() {
    }

    public static AccessibilityNode construct$ar$class_merging$443fffb0_0$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        AccessibilityNode accessibilityNode = new AccessibilityNode();
        if (z) {
            accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
        }
        accessibilityNode.nodeCompat = accessibilityNodeInfoCompat;
        return accessibilityNode;
    }

    public static AccessibilityNode takeOwnership(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo == null) {
            return null;
        }
        AccessibilityNode accessibilityNode = new AccessibilityNode();
        accessibilityNode.nodeBare = accessibilityNodeInfo;
        return accessibilityNode;
    }

    public final boolean equalTo(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return getCompat().equals(accessibilityNodeInfoCompat);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityNode)) {
            return false;
        }
        return getCompat().equals(((AccessibilityNode) obj).getCompat());
    }

    public final AccessibilityNodeInfo getBare() {
        if (this.nodeBare == null) {
            this.nodeBare = this.nodeCompat.mInfo;
        }
        return this.nodeBare;
    }

    public final void getBoundsInScreen(Rect rect) {
        getCompat().getBoundsInScreen(rect);
    }

    public final AccessibilityNode getChild(int i) {
        return takeOwnership(getCompat().getChild(i));
    }

    public final int getChildCount() {
        return getCompat().getChildCount();
    }

    public final AccessibilityNodeInfoCompat.CollectionItemInfoCompat getCollectionItemInfo() {
        return getCompat().getCollectionItemInfo();
    }

    public final AccessibilityNodeInfoCompat getCompat() {
        if (this.nodeCompat == null) {
            this.nodeCompat = new AccessibilityNodeInfoCompat(this.nodeBare);
        }
        return this.nodeCompat;
    }

    public final CharSequence getNodeText() {
        return AccessibilityNodeInfoUtils.getNodeText(getCompat());
    }

    public final AccessibilityNode getParent() {
        return takeOwnership(getCompat().getParent());
    }

    public final int getRole() {
        return SpannableUtils$IdentifierSpan.getRole(getCompat());
    }

    public final AccessibilityNode getSelfOrMatchingAncestor(Filter filter) {
        return takeOwnership(AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(getCompat(), filter));
    }

    public final String getUniqueId() {
        return getCompat().getUniqueId();
    }

    public final int hashCode() {
        return getBare().hashCode();
    }

    public final AccessibilityNodeInfoCompat obtainCopyCompat() {
        return AccessibilityNodeInfoCompat.obtain(getCompat());
    }

    public final synchronized boolean refresh() {
        try {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.nodeCompat;
            if (accessibilityNodeInfoCompat == null) {
                return this.nodeBare.refresh();
            }
            this.nodeBare = null;
            return accessibilityNodeInfoCompat.refresh();
        } catch (IllegalStateException e) {
            LogUtils.e("AccessibilityNode", "Caught IllegalStateException from accessibility framework trying to refresh node", new Object[0]);
            LogUtils.e("AccessibilityNode", "%s", e);
            return false;
        }
    }

    public final String toString() {
        return AccessibilityNodeInfoUtils.toStringShort(getCompat());
    }

    public static AccessibilityNode takeOwnership(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        return construct$ar$class_merging$443fffb0_0$ar$ds(accessibilityNodeInfoCompat, false);
    }
}
