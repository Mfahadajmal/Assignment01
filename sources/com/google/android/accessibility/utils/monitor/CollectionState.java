package com.google.android.accessibility.utils.monitor;

import android.text.TextUtils;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionState {
    public AccessibilityNodeInfoCompat mCollectionRoot;
    public ItemState mItemState;
    public AccessibilityNodeInfoCompat mLastAnnouncedNode;
    public static final Filter FILTER_HIERARCHICAL_COLLECTION = new Filter() { // from class: com.google.android.accessibility.utils.monitor.CollectionState.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (AccessibilityNodeInfoUtils.FILTER_COLLECTION.accept(accessibilityNodeInfoCompat) && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().isHierarchical()) {
                return true;
            }
            return false;
        }
    };
    private static final Filter FILTER_FLAT_COLLECTION = new Filter() { // from class: com.google.android.accessibility.utils.monitor.CollectionState.2
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (!AccessibilityNodeInfoUtils.FILTER_COLLECTION.accept(accessibilityNodeInfoCompat)) {
                return false;
            }
            if (accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().isHierarchical()) {
                return false;
            }
            return true;
        }
    };
    private static final Filter FILTER_COLLECTION_ITEM = new Filter() { // from class: com.google.android.accessibility.utils.monitor.CollectionState.3
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionItemInfo() != null) {
                return true;
            }
            return false;
        }
    };
    public int mCollectionTransition = 0;
    public int mRowColumnTransition = 0;
    public final SparseArray mRowHeaders = new SparseArray();
    public final SparseArray mColumnHeaders = new SparseArray();
    public int mCollectionLevel = -1;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.monitor.CollectionState$1 */
    /* loaded from: classes.dex */
    final class AnonymousClass1 extends Filter {
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (AccessibilityNodeInfoUtils.FILTER_COLLECTION.accept(accessibilityNodeInfoCompat) && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().isHierarchical()) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.monitor.CollectionState$2 */
    /* loaded from: classes.dex */
    final class AnonymousClass2 extends Filter {
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (!AccessibilityNodeInfoUtils.FILTER_COLLECTION.accept(accessibilityNodeInfoCompat)) {
                return false;
            }
            if (accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging().isHierarchical()) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.utils.monitor.CollectionState$3 */
    /* loaded from: classes.dex */
    final class AnonymousClass3 extends Filter {
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionItemInfo() != null) {
                return true;
            }
            return false;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ItemState {
        int getTransition(ItemState itemState);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ListItemState implements ItemState {
        public final int index;
        public final boolean isHeading;
        public final CharSequence roleDescription;

        public ListItemState(boolean z, CharSequence charSequence, int i) {
            this.isHeading = z;
            this.index = i;
            this.roleDescription = charSequence;
        }

        @Override // com.google.android.accessibility.utils.monitor.CollectionState.ItemState
        public final int getTransition(ItemState itemState) {
            if (!(itemState instanceof ListItemState) || this.index != ((ListItemState) itemState).index) {
                return 3;
            }
            return 0;
        }

        public final String toString() {
            return String.format(" ListItemState: index=%s, ", Integer.valueOf(this.index)) + String.format("heading=%s, ", Boolean.valueOf(this.isHeading)) + String.format("roleDescription=%s, ", this.roleDescription);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PagerItemState implements ItemState {
        private final int columnIndex;
        private final boolean heading;
        private final int rowIndex;

        public PagerItemState(boolean z, int i, int i2) {
            this.heading = z;
            this.rowIndex = i;
            this.columnIndex = i2;
        }

        @Override // com.google.android.accessibility.utils.monitor.CollectionState.ItemState
        public final int getTransition(ItemState itemState) {
            int i;
            if (!(itemState instanceof PagerItemState)) {
                return 3;
            }
            PagerItemState pagerItemState = (PagerItemState) itemState;
            if (this.rowIndex != pagerItemState.rowIndex) {
                i = 1;
            } else {
                i = 0;
            }
            if (this.columnIndex != pagerItemState.columnIndex) {
                return i | 2;
            }
            return i;
        }

        public final String toString() {
            return String.format(" PagerItemState: colIndex=%s, ", Integer.valueOf(this.columnIndex)) + String.format("rowIndex=%s, ", Integer.valueOf(this.rowIndex)) + String.format("heading=%s, ", Boolean.valueOf(this.heading));
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TableItemState implements ItemState {
        public final int columnIndex;
        public final CharSequence columnName;
        public final int headingType;
        public final CharSequence roleDescription;
        public final int rowIndex;
        public final CharSequence rowName;

        public TableItemState(int i, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i2, int i3) {
            this.headingType = i;
            this.rowName = charSequence;
            this.columnName = charSequence2;
            this.roleDescription = charSequence3;
            this.rowIndex = i2;
            this.columnIndex = i3;
        }

        @Override // com.google.android.accessibility.utils.monitor.CollectionState.ItemState
        public final int getTransition(ItemState itemState) {
            int i;
            if (!(itemState instanceof TableItemState)) {
                return 3;
            }
            TableItemState tableItemState = (TableItemState) itemState;
            if (this.rowIndex != tableItemState.rowIndex) {
                i = 1;
            } else {
                i = 0;
            }
            if (this.columnIndex != tableItemState.columnIndex) {
                return i | 2;
            }
            return i;
        }

        public final String toString() {
            return String.format(" TableItemState: colIndex=%s, ", Integer.valueOf(this.columnIndex)) + String.format("rowIndex=%s, ", Integer.valueOf(this.rowIndex)) + String.format("headingType=%s, ", Integer.valueOf(this.headingType)) + String.format("roleDescription=%s, ", this.roleDescription) + String.format("colName=%s, ", this.columnName) + String.format("rowName=%s, ", this.rowName);
        }
    }

    public static PagerItemState extractPagerItemState(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        AccessibilityNode takeOwnership;
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null && (takeOwnership = AccessibilityNode.takeOwnership(AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat2, accessibilityNodeInfoCompat, FILTER_COLLECTION_ITEM))) != null) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging = accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging();
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = takeOwnership.getCollectionItemInfo();
            return new PagerItemState(AccessibilityNodeInfoUtils.isHeading(takeOwnership.getCompat()), getRowIndex$ar$class_merging(collectionItemInfo, collectionInfo$ar$class_merging), getColumnIndex$ar$class_merging(collectionItemInfo, collectionInfo$ar$class_merging));
        }
        return null;
    }

    public static int getCollectionAlignmentInternal$ar$class_merging(AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat) {
        if (collectionItemInfoCompat != null && collectionItemInfoCompat.getRowCount() < collectionItemInfoCompat.getColumnCount()) {
            return 1;
        }
        return 0;
    }

    private static int getColumnIndex$ar$class_merging(AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat2) {
        if (collectionItemInfoCompat.getColumnSpan() != collectionItemInfoCompat2.getColumnCount() && collectionItemInfoCompat.getColumnIndex() >= 0) {
            return collectionItemInfoCompat.getColumnIndex();
        }
        return -1;
    }

    private static int getRowIndex$ar$class_merging(AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat2) {
        if (collectionItemInfoCompat.getRowSpan() != collectionItemInfoCompat2.getRowCount() && collectionItemInfoCompat.getRowIndex() >= 0) {
            return collectionItemInfoCompat.getRowIndex();
        }
        return -1;
    }

    private static int getTableHeadingType$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat2) {
        if (AccessibilityNodeInfoUtils.isHeading(accessibilityNodeInfoCompat)) {
            if (collectionItemInfoCompat.getRowSpan() == 1 && collectionItemInfoCompat.getColumnSpan() == 1) {
                if (getRowIndex$ar$class_merging(collectionItemInfoCompat, collectionItemInfoCompat2) == 0 && collectionItemInfoCompat2.getColumnCount() > 1) {
                    return 2;
                }
                if (getColumnIndex$ar$class_merging(collectionItemInfoCompat, collectionItemInfoCompat2) == 0 && collectionItemInfoCompat2.getRowCount() > 1) {
                    return 1;
                }
                return 4;
            }
            return 4;
        }
        return 0;
    }

    public static boolean shouldEnter(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null) {
            AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging = accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging();
            int rowCount = collectionInfo$ar$class_merging.getRowCount();
            int columnCount = collectionInfo$ar$class_merging.getColumnCount();
            if (rowCount == -1) {
                if (columnCount != -1) {
                    rowCount = -1;
                }
            }
            int i = rowCount * columnCount;
            if (i == 0 || i == 1) {
                return false;
            }
        } else if (accessibilityNodeInfoCompat.getChildCount() <= 1) {
            return false;
        }
        Filter filter = FILTER_FLAT_COLLECTION;
        if (filter.accept(accessibilityNodeInfoCompat) && AccessibilityNodeInfoUtils.hasMatchingDescendant(accessibilityNodeInfoCompat, filter)) {
            return false;
        }
        return true;
    }

    public static boolean updateSingleTableHeader$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, SparseArray sparseArray, SparseArray sparseArray2) {
        CharSequence charSequence;
        HashSet hashSet = new HashSet();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompat;
        while (true) {
            charSequence = null;
            if (accessibilityNodeInfoCompat2 == null || !hashSet.add(accessibilityNodeInfoCompat2)) {
                break;
            }
            CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat2);
            if (nodeText != null) {
                charSequence = nodeText;
                break;
            }
            if (accessibilityNodeInfoCompat2.getChildCount() != 1) {
                break;
            }
            accessibilityNodeInfoCompat2 = accessibilityNodeInfoCompat2.getChild(0);
        }
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = accessibilityNodeInfoCompat.getCollectionItemInfo();
        if (collectionItemInfo != null && charSequence != null) {
            int tableHeadingType$ar$class_merging = getTableHeadingType$ar$class_merging(accessibilityNodeInfoCompat, collectionItemInfo, collectionItemInfoCompat);
            if ((tableHeadingType$ar$class_merging & 1) != 0) {
                sparseArray.put(collectionItemInfo.getRowIndex(), charSequence);
            }
            if ((tableHeadingType$ar$class_merging & 2) != 0) {
                sparseArray2.put(collectionItemInfo.getColumnIndex(), charSequence);
            }
            if (tableHeadingType$ar$class_merging != 0) {
                return true;
            }
        }
        return false;
    }

    public final int getCollectionAlignment() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCollectionRoot;
        if (accessibilityNodeInfoCompat == null) {
            return 0;
        }
        return getCollectionAlignmentInternal$ar$class_merging(accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging());
    }

    public final int getCollectionColumnCount() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCollectionRoot;
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null) {
            return this.mCollectionRoot.getCollectionInfo$ar$class_merging().getColumnCount();
        }
        return -1;
    }

    public final CharSequence getCollectionName() {
        CharSequence containerTitle;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCollectionRoot;
        if (accessibilityNodeInfoCompat == null) {
            containerTitle = "";
        } else {
            containerTitle = accessibilityNodeInfoCompat.getContainerTitle();
        }
        if (TextUtils.isEmpty(containerTitle)) {
            containerTitle = AccessibilityNodeInfoUtils.getNodeText(this.mCollectionRoot);
        }
        if (!TextUtils.isEmpty(containerTitle) && !TextUtils.equals(containerTitle, "com.android.car.ui.utils.ROTARY_CONTAINER") && !TextUtils.equals(containerTitle, "com.android.car.ui.utils.HORIZONTALLY_SCROLLABLE") && !TextUtils.equals(containerTitle, "com.android.car.ui.utils.VERTICALLY_SCROLLABLE")) {
            return containerTitle;
        }
        return null;
    }

    public final int getCollectionRole() {
        return SpannableUtils$IdentifierSpan.getRole(this.mCollectionRoot);
    }

    public final CharSequence getCollectionRoleDescription() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCollectionRoot;
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return accessibilityNodeInfoCompat.getRoleDescription();
    }

    public final int getCollectionRowCount() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.mCollectionRoot;
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() != null) {
            return this.mCollectionRoot.getCollectionInfo$ar$class_merging().getRowCount();
        }
        return -1;
    }

    public final ListItemState getListItemState() {
        ItemState itemState = this.mItemState;
        if (itemState == null || !(itemState instanceof ListItemState)) {
            return null;
        }
        return (ListItemState) itemState;
    }

    public final int getSelectionMode() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat;
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging;
        if (this.mItemState != null && (accessibilityNodeInfoCompat = this.mCollectionRoot) != null && (collectionInfo$ar$class_merging = accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging()) != null) {
            return ((AccessibilityNodeInfo.CollectionInfo) collectionInfo$ar$class_merging.mInfo).getSelectionMode();
        }
        return 0;
    }

    public final TableItemState getTableItemState() {
        ItemState itemState = this.mItemState;
        if (itemState == null || !(itemState instanceof TableItemState)) {
            return null;
        }
        return (TableItemState) itemState;
    }

    public final String toString() {
        return String.format(" CollectionState:  role=%s, ", Integer.valueOf(getCollectionRole())) + String.format("colCount=%s, ", Integer.valueOf(getCollectionColumnCount())) + String.format("rowCount=%s, ", Integer.valueOf(getCollectionRowCount())) + String.format("collectionTransition=%s, ", Integer.valueOf(this.mCollectionTransition)) + String.format("rowColTransition=%s, ", Integer.valueOf(this.mRowColumnTransition)) + String.format("selectionMode=%s, ", Integer.valueOf(getSelectionMode()));
    }

    public static ListItemState getListItemState(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
        AccessibilityNodeInfoCompat selfOrMatchingAncestor;
        int columnIndex$ar$class_merging;
        if (accessibilityNodeInfoCompat == null || accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging() == null || (selfOrMatchingAncestor = AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat2, accessibilityNodeInfoCompat, FILTER_COLLECTION_ITEM)) == null) {
            return null;
        }
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionInfo$ar$class_merging = accessibilityNodeInfoCompat.getCollectionInfo$ar$class_merging();
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfo = selfOrMatchingAncestor.getCollectionItemInfo();
        boolean isHeading = AccessibilityNodeInfoUtils.isHeading(selfOrMatchingAncestor);
        CharSequence roleDescription = selfOrMatchingAncestor.getRoleDescription();
        if (getCollectionAlignmentInternal$ar$class_merging(collectionInfo$ar$class_merging) == 0) {
            columnIndex$ar$class_merging = getRowIndex$ar$class_merging(collectionItemInfo, collectionInfo$ar$class_merging);
        } else {
            columnIndex$ar$class_merging = getColumnIndex$ar$class_merging(collectionItemInfo, collectionInfo$ar$class_merging);
        }
        return new ListItemState(isHeading, roleDescription, columnIndex$ar$class_merging);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0072  */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.lang.CharSequence] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.accessibility.utils.monitor.CollectionState.TableItemState getTableItemState(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r9, androidx.core.view.accessibility.AccessibilityNodeInfoCompat r10, android.util.SparseArray r11, android.util.SparseArray r12) {
        /*
            r0 = 0
            if (r9 == 0) goto L7a
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r9.getCollectionInfo$ar$class_merging()
            if (r1 != 0) goto Lb
            goto L7a
        Lb:
            com.google.android.accessibility.utils.Filter r1 = com.google.android.accessibility.utils.monitor.CollectionState.FILTER_COLLECTION_ITEM
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r10 = com.google.android.accessibility.utils.AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(r10, r9, r1)
            if (r10 == 0) goto L7a
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r9 = r9.getCollectionInfo$ar$class_merging()
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat r1 = r10.getCollectionItemInfo()
            int r3 = getTableHeadingType$ar$class_merging(r10, r1, r9)
            int r7 = getRowIndex$ar$class_merging(r1, r9)
            int r8 = getColumnIndex$ar$class_merging(r1, r9)
            r9 = -1
            if (r7 == r9) goto L31
            java.lang.Object r11 = r11.get(r7)
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            goto L32
        L31:
            r11 = r0
        L32:
            if (r8 == r9) goto L3b
            java.lang.Object r9 = r12.get(r8)
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            goto L3c
        L3b:
            r9 = r0
        L3c:
            java.lang.CharSequence r6 = r10.getRoleDescription()
            if (r11 != 0) goto L59
            boolean r11 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.isAtLeastT()
            if (r11 == 0) goto L57
            android.view.accessibility.AccessibilityNodeInfo r11 = r10.mInfo
            if (r11 == 0) goto L57
            android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo r11 = r11.getCollectionItemInfo()
            if (r11 == 0) goto L57
            java.lang.String r11 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m(r11)
            goto L59
        L57:
            r4 = r0
            goto L5a
        L59:
            r4 = r11
        L5a:
            if (r9 != 0) goto L72
            boolean r9 = com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan.isAtLeastT()
            if (r9 == 0) goto L70
            android.view.accessibility.AccessibilityNodeInfo r9 = r10.mInfo
            if (r9 == 0) goto L70
            android.view.accessibility.AccessibilityNodeInfo$CollectionItemInfo r9 = r9.getCollectionItemInfo()
            if (r9 == 0) goto L70
            java.lang.String r0 = org.chromium.base.ContextUtils$$ExternalSyntheticApiModelOutline0.m$1(r9)
        L70:
            r5 = r0
            goto L73
        L72:
            r5 = r9
        L73:
            com.google.android.accessibility.utils.monitor.CollectionState$TableItemState r9 = new com.google.android.accessibility.utils.monitor.CollectionState$TableItemState
            r2 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r9
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.monitor.CollectionState.getTableItemState(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, androidx.core.view.accessibility.AccessibilityNodeInfoCompat, android.util.SparseArray, android.util.SparseArray):com.google.android.accessibility.utils.monitor.CollectionState$TableItemState");
    }
}
