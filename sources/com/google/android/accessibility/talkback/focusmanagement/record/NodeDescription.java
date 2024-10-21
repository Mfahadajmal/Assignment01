package com.google.android.accessibility.talkback.focusmanagement.record;

import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.StringBuilderUtils;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeDescription {
    public final CharSequence className;
    final int columnIndex;
    public final CharSequence nextSiblingText;
    public final int nodeInfoHashCode;
    public final CharSequence previousSiblingText;
    final int rawIndexInParent;
    final int rowIndex;
    public final AccessibilityNode savedNode;
    public final CharSequence text;
    public final String viewIdResourceName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LeafTextCollector extends Filter {
        public final StringBuilder text = new StringBuilder();
        private int numNodes = 0;

        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText((AccessibilityNodeInfoCompat) obj);
            if (!TextUtils.isEmpty(nodeText)) {
                if (!TextUtils.isEmpty(this.text)) {
                    this.text.append(", ");
                }
                this.text.append(nodeText);
            }
            int i = this.numNodes + 1;
            this.numNodes = i;
            if (i > 5) {
                return true;
            }
            return false;
        }
    }

    public NodeDescription(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z) {
        AccessibilityNode takeOwnership = AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat);
        this.savedNode = takeOwnership;
        this.nodeInfoHashCode = accessibilityNodeInfoCompat.hashCode();
        this.text = getText(takeOwnership, z);
        this.className = accessibilityNodeInfoCompat.getClassName();
        this.viewIdResourceName = accessibilityNodeInfoCompat.getViewIdResourceName();
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = accessibilityNodeInfoCompat.getCollectionItemInfo();
        this.rowIndex = collectionItemInfo == null ? -1 : collectionItemInfo.getRowIndex();
        this.columnIndex = collectionItemInfo == null ? -1 : collectionItemInfo.getColumnIndex();
        AccessibilityNode parent = takeOwnership.getParent();
        int rawIndexInParent = getRawIndexInParent(takeOwnership, parent);
        this.rawIndexInParent = rawIndexInParent;
        if (rawIndexInParent == -1) {
            this.previousSiblingText = null;
            this.nextSiblingText = null;
        } else {
            this.previousSiblingText = getChildText(parent, rawIndexInParent - 1, z);
            this.nextSiblingText = getChildText(parent, rawIndexInParent + 1, z);
        }
    }

    private static CharSequence getChildText(AccessibilityNode accessibilityNode, int i, boolean z) {
        if (accessibilityNode != null && i >= 0 && accessibilityNode.getChildCount() > i) {
            AccessibilityNode child = accessibilityNode.getChild(i);
            if (child == null) {
                return null;
            }
            return getText(child, z);
        }
        return "OUT_OF_RANGE";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getRawIndexInParent(AccessibilityNode accessibilityNode, AccessibilityNode accessibilityNode2) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo;
        if (accessibilityNode2 == null) {
            return -1;
        }
        if (accessibilityNode2.getRole() == 16 && (collectionItemInfo = accessibilityNode.getCollectionItemInfo()) != null) {
            return collectionItemInfo.getColumnIndex();
        }
        for (int i = 0; i < accessibilityNode2.getChildCount(); i++) {
            if (accessibilityNode.equals(accessibilityNode2.getChild(i))) {
                return i;
            }
        }
        return -1;
    }

    public static CharSequence getText(AccessibilityNode accessibilityNode, boolean z) {
        if (accessibilityNode == null) {
            return null;
        }
        CharSequence contentDescription = accessibilityNode.getCompat().getContentDescription();
        if (contentDescription != null && TextUtils.getTrimmedLength(contentDescription) > 0) {
            return contentDescription;
        }
        if (z) {
            LeafTextCollector leafTextCollector = new LeafTextCollector();
            if (!leafTextCollector.accept(accessibilityNode.getCompat())) {
                AccessibilityNodeInfoUtils.hasMatchingDescendant(accessibilityNode.getCompat(), leafTextCollector);
            }
            return leafTextCollector.text.toString();
        }
        return AccessibilityNodeInfoUtils.getText(accessibilityNode.getCompat());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NodeDescription)) {
            return false;
        }
        NodeDescription nodeDescription = (NodeDescription) obj;
        if (this.rowIndex == nodeDescription.rowIndex && this.columnIndex == nodeDescription.columnIndex && this.rawIndexInParent == nodeDescription.rawIndexInParent && TextUtils.equals(this.className, nodeDescription.className) && TextUtils.equals(this.viewIdResourceName, nodeDescription.viewIdResourceName) && TextUtils.equals(this.text, nodeDescription.text) && TextUtils.equals(this.previousSiblingText, nodeDescription.previousSiblingText) && TextUtils.equals(this.nextSiblingText, nodeDescription.nextSiblingText)) {
            return true;
        }
        return false;
    }

    public final boolean hasCollectionIndex() {
        if (this.rowIndex == -1 && this.columnIndex == -1) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hash(this.className, this.viewIdResourceName, Integer.valueOf(this.rowIndex), Integer.valueOf(this.columnIndex), Integer.valueOf(this.rawIndexInParent), this.text, this.previousSiblingText, this.nextSiblingText);
    }

    public final boolean matchesCollectionIndices(AccessibilityNode accessibilityNode) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo;
        if (hasCollectionIndex() && accessibilityNode != null && (collectionItemInfo = accessibilityNode.getCollectionItemInfo()) != null) {
            if (collectionItemInfo.getRowIndex() == this.rowIndex && collectionItemInfo.getColumnIndex() == this.columnIndex) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return StringBuilderUtils.joinFields(StringBuilderUtils.optionalInt("index", this.rawIndexInParent, -1), StringBuilderUtils.optionalInt("rowIndex", this.rowIndex, -1), StringBuilderUtils.optionalInt("columnIndex", this.columnIndex, -1), StringBuilderUtils.optionalText("className", this.className), StringBuilderUtils.optionalText("viewIdResourceName", this.viewIdResourceName), StringBuilderUtils.optionalText("text", this.text), StringBuilderUtils.optionalText("previousSiblingText", this.previousSiblingText), StringBuilderUtils.optionalText("nextSiblingText", this.nextSiblingText), StringBuilderUtils.optionalInt("hashcode", this.nodeInfoHashCode, 0));
    }

    public NodeDescription(NodeDescription nodeDescription) {
        this.className = nodeDescription.className;
        this.viewIdResourceName = nodeDescription.viewIdResourceName;
        this.rowIndex = nodeDescription.rowIndex;
        this.columnIndex = nodeDescription.columnIndex;
        this.rawIndexInParent = nodeDescription.rawIndexInParent;
        this.nodeInfoHashCode = nodeDescription.nodeInfoHashCode;
        this.savedNode = nodeDescription.savedNode;
        this.text = nodeDescription.text;
        this.previousSiblingText = nodeDescription.previousSiblingText;
        this.nextSiblingText = nodeDescription.nextSiblingText;
    }
}
