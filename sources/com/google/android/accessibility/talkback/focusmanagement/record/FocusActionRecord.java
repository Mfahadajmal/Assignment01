package com.google.android.accessibility.talkback.focusmanagement.record;

import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription;
import com.google.android.accessibility.utils.AccessibilityNode;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.traversal.TraversalStrategy;
import com.google.android.accessibility.utils.traversal.TraversalStrategyUtils;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import j$.util.Objects;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FocusActionRecord {
    public final long actionTime;
    public final FocusActionInfo extraInfo;
    public final AccessibilityNodeInfoCompat focusedNode;
    public final NodePathDescription nodePathDescription;
    public final String uniqueId;

    public FocusActionRecord(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo, long j) {
        this.focusedNode = accessibilityNodeInfoCompat;
        final NodePathDescription nodePathDescription = new NodePathDescription();
        AccessibilityNodeInfoUtils.getSelfOrMatchingAncestor(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription.1
            public AnonymousClass1() {
            }

            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                NodePathDescription.this.nodeDescriptions.add(new NodeDescription((AccessibilityNodeInfoCompat) obj, NodePathDescription.this.nodeDescriptions.isEmpty()));
                return false;
            }
        });
        this.nodePathDescription = nodePathDescription;
        this.extraInfo = focusActionInfo;
        this.actionTime = j;
        this.uniqueId = compoundPackageNameAndUniqueId(accessibilityNodeInfoCompat);
    }

    public static String compoundPackageNameAndUniqueId(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        String uniqueId;
        if (accessibilityNodeInfoCompat != null && (uniqueId = accessibilityNodeInfoCompat.getUniqueId()) != null) {
            return String.valueOf(accessibilityNodeInfoCompat.getPackageName()) + ":" + uniqueId;
        }
        return null;
    }

    public static FocusActionRecord copy(FocusActionRecord focusActionRecord) {
        if (focusActionRecord == null) {
            return null;
        }
        return new FocusActionRecord(focusActionRecord.focusedNode, focusActionRecord.nodePathDescription, focusActionRecord.extraInfo, focusActionRecord.actionTime, focusActionRecord.uniqueId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1, types: [int] */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v10 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v7, types: [int] */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r2v20, types: [java.lang.CharSequence, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.CharSequence, java.lang.Object] */
    public static AccessibilityNodeInfoCompat getFocusableNodeFromFocusRecord$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AppLifecycleMonitor appLifecycleMonitor, FocusActionRecord focusActionRecord) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2;
        int i;
        AccessibilityNode accessibilityNode;
        AccessibilityNode accessibilityNode2;
        AccessibilityNode accessibilityNode3;
        int i2;
        AccessibilityNode accessibilityNode4;
        boolean z;
        ?? r13;
        ?? r15;
        int i3;
        AccessibilityNode accessibilityNode5;
        AccessibilityNode accessibilityNode6;
        boolean z2;
        CharSequence text;
        int i4;
        AccessibilityNode construct$ar$class_merging$443fffb0_0$ar$ds;
        String uniqueId;
        AccessibilityNodeInfoCompat searchFromBfs;
        final String str = focusActionRecord.uniqueId;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = focusActionRecord.focusedNode;
        if (accessibilityNodeInfoCompat3.refresh() && AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat3) && ((str == null && accessibilityNodeInfoCompat3.getUniqueId() == null) || (str != null && str.equals(compoundPackageNameAndUniqueId(accessibilityNodeInfoCompat3))))) {
            return accessibilityNodeInfoCompat3;
        }
        if (str != null && (searchFromBfs = AccessibilityNodeInfoUtils.searchFromBfs(accessibilityNodeInfoCompat, new Filter() { // from class: com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord.1
            @Override // com.google.android.accessibility.utils.Filter
            public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
                return str.equals(FocusActionRecord.compoundPackageNameAndUniqueId((AccessibilityNodeInfoCompat) obj));
            }
        })) != null && AccessibilityNodeInfoUtils.shouldFocusNode(searchFromBfs)) {
            return searchFromBfs;
        }
        NodePathDescription nodePathDescription = focusActionRecord.nodePathDescription;
        boolean z3 = false;
        if (!nodePathDescription.nodeDescriptions.isEmpty() && ((NodeDescription) nodePathDescription.nodeDescriptions.get(0)).savedNode != null) {
            accessibilityNodeInfoCompat2 = ((NodeDescription) nodePathDescription.nodeDescriptions.get(0)).savedNode.obtainCopyCompat();
        } else {
            accessibilityNodeInfoCompat2 = null;
        }
        AccessibilityNode takeOwnership = AccessibilityNode.takeOwnership(accessibilityNodeInfoCompat);
        if (takeOwnership == null) {
            accessibilityNodeInfoCompat2 = null;
        } else {
            boolean z4 = true;
            int i5 = 1;
            while (true) {
                i = -1;
                if (i5 < nodePathDescription.nodeDescriptions.size() - 1) {
                    NodeDescription nodeDescription = (NodeDescription) nodePathDescription.nodeDescriptions.get(i5);
                    AccessibilityNode accessibilityNode7 = nodeDescription.savedNode;
                    if (accessibilityNode7 == null) {
                        construct$ar$class_merging$443fffb0_0$ar$ds = null;
                    } else {
                        construct$ar$class_merging$443fffb0_0$ar$ds = AccessibilityNode.construct$ar$class_merging$443fffb0_0$ar$ds(accessibilityNode7.getCompat(), true);
                    }
                    accessibilityNode = NodePathDescription.refreshOrNull(nodeDescription.savedNode);
                    if (accessibilityNode != null && construct$ar$class_merging$443fffb0_0$ar$ds != null && (uniqueId = construct$ar$class_merging$443fffb0_0$ar$ds.getUniqueId()) != null && uniqueId.equals(accessibilityNode.getUniqueId())) {
                        break;
                    }
                    i5++;
                } else {
                    accessibilityNode = null;
                    break;
                }
            }
            if (accessibilityNode == null || accessibilityNodeInfoCompat2 == null || !accessibilityNodeInfoCompat2.refresh() || !AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat2)) {
                if (accessibilityNode == null) {
                    int i6 = 0;
                    while (true) {
                        if (i6 < nodePathDescription.nodeDescriptions.size() + i) {
                            NodeDescription nodeDescription2 = (NodeDescription) nodePathDescription.nodeDescriptions.get(i6);
                            int i7 = i6 + 1;
                            NodeDescription nodeDescription3 = (NodeDescription) nodePathDescription.nodeDescriptions.get(i7);
                            if (i6 == 0) {
                                z = z4;
                            } else {
                                z = z3;
                            }
                            AccessibilityNode refreshOrNull = NodePathDescription.refreshOrNull(nodeDescription2.savedNode);
                            AccessibilityNode refreshOrNull2 = NodePathDescription.refreshOrNull(nodeDescription3.savedNode);
                            if (refreshOrNull != null && nodeDescription2.nodeInfoHashCode == refreshOrNull.hashCode()) {
                                r13 = z4;
                            } else {
                                r13 = z3;
                            }
                            boolean contentMatches = NodePathDescription.contentMatches(nodeDescription2, refreshOrNull, z);
                            if (nodeDescription2.hasCollectionIndex()) {
                                r15 = nodeDescription2.matchesCollectionIndices(refreshOrNull);
                            } else {
                                if (refreshOrNull2 != null) {
                                    int childCount = refreshOrNull2.getChildCount();
                                    int i8 = nodeDescription2.rawIndexInParent;
                                    if (childCount > i8) {
                                        if (i8 != i && nodeDescription2.savedNode != null) {
                                            r15 = nodeDescription2.savedNode.equals(refreshOrNull2.getChild(i8));
                                        }
                                        r15 = z3;
                                    }
                                }
                                if (nodeDescription2.rawIndexInParent == i) {
                                    r15 = z4;
                                }
                                r15 = z3;
                            }
                            int i9 = r13 + r15 + (contentMatches ? 1 : 0);
                            if (i9 < 3) {
                                if (refreshOrNull2 != null && TextUtils.equals(nodeDescription3.text, NodeDescription.getText(refreshOrNull2, z))) {
                                    CharSequence charSequence = nodeDescription2.previousSiblingText;
                                    CharSequence charSequence2 = nodeDescription2.nextSiblingText;
                                    if (!TextUtils.isEmpty(charSequence) || !TextUtils.isEmpty(charSequence2)) {
                                        GooglePlayServicesUpdatedReceiver.Callback callback = new GooglePlayServicesUpdatedReceiver.Callback(charSequence, charSequence2);
                                        CharSequence charSequence3 = "OUT_OF_RANGE";
                                        int i10 = 0;
                                        CharSequence charSequence4 = null;
                                        accessibilityNode5 = null;
                                        while (i10 <= refreshOrNull2.getChildCount()) {
                                            if (i10 < refreshOrNull2.getChildCount()) {
                                                accessibilityNode6 = refreshOrNull2.getChild(i10);
                                            } else {
                                                accessibilityNode6 = null;
                                            }
                                            if (accessibilityNode6 == null) {
                                                z2 = z;
                                                text = "OUT_OF_RANGE";
                                            } else {
                                                z2 = z;
                                                text = NodeDescription.getText(accessibilityNode6, z);
                                            }
                                            AccessibilityNode accessibilityNode8 = accessibilityNode6;
                                            if (accessibilityNode5 != null) {
                                                ?? r2 = callback.GooglePlayServicesUpdatedReceiver$Callback$ar$this$1;
                                                accessibilityNode2 = takeOwnership;
                                                ?? r5 = callback.GooglePlayServicesUpdatedReceiver$Callback$ar$val$updatingDialog;
                                                if (TextUtils.equals(charSequence4, r2) && TextUtils.equals(text, r5)) {
                                                    break;
                                                }
                                            } else {
                                                accessibilityNode2 = takeOwnership;
                                            }
                                            i10++;
                                            charSequence4 = charSequence3;
                                            accessibilityNode5 = accessibilityNode8;
                                            takeOwnership = accessibilityNode2;
                                            charSequence3 = text;
                                            z = z2;
                                        }
                                    }
                                }
                                accessibilityNode2 = takeOwnership;
                                accessibilityNode5 = null;
                                if (accessibilityNode5 == null) {
                                    i4 = 0;
                                } else {
                                    i4 = 1;
                                }
                                i9 += i4;
                                accessibilityNode = accessibilityNode5;
                                i3 = 3;
                            } else {
                                accessibilityNode2 = takeOwnership;
                                i3 = 3;
                                accessibilityNode = null;
                            }
                            if (i9 >= i3) {
                                if (r13 != 0 && contentMatches && r15 != 0) {
                                    accessibilityNode = refreshOrNull;
                                    break;
                                }
                                if (accessibilityNode != null) {
                                    break;
                                }
                            }
                            i6 = i7;
                            takeOwnership = accessibilityNode2;
                            z3 = false;
                            i = -1;
                            z4 = true;
                        } else {
                            accessibilityNode2 = takeOwnership;
                            accessibilityNode = null;
                            break;
                        }
                    }
                } else {
                    accessibilityNode2 = takeOwnership;
                }
                if (accessibilityNode == null) {
                    accessibilityNode3 = accessibilityNode2;
                } else {
                    accessibilityNode3 = accessibilityNode;
                }
                AccessibilityNodeInfoCompat compat = accessibilityNode3.getCompat();
                Class cls = AccessibilityNodeInfoUtils.CLASS_TOUCHWIZ_TWADAPTERVIEW;
                if (compat == null) {
                    i2 = -1;
                } else {
                    AccessibilityNodeInfoUtils.NodeCounter nodeCounter = new AccessibilityNodeInfoUtils.NodeCounter();
                    AccessibilityNodeInfoUtils.isOrHasMatchingAncestor(compat, nodeCounter);
                    i2 = nodeCounter.count - 1;
                }
                int rawIndexInParent = NodeDescription.getRawIndexInParent(accessibilityNode3, accessibilityNode3.getParent());
                HashSet hashSet = new HashSet();
                NodePathDescription.Match findDownwardMatch = nodePathDescription.findDownwardMatch(accessibilityNode3, rawIndexInParent, i2, null, null, hashSet);
                hashSet.size();
                if (findDownwardMatch != null && findDownwardMatch.hasNode() && !findDownwardMatch.node.equalTo(accessibilityNodeInfoCompat)) {
                    if (findDownwardMatch.isPathEnd) {
                        accessibilityNode4 = findDownwardMatch.node;
                    } else {
                        AccessibilityNode accessibilityNode9 = findDownwardMatch.node;
                        TraversalStrategy traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = TraversalStrategyUtils.getTraversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(accessibilityNodeInfoCompat, appLifecycleMonitor, 1);
                        accessibilityNode4 = AccessibilityNode.takeOwnership(TraversalStrategyUtils.searchFocus(traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging, accessibilityNode9.obtainCopyCompat(), 1, new Filter.NodeCompat(new NodePathDescription$$ExternalSyntheticLambda0(traversalStrategy$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.getSpeakingNodesCache(), 0))));
                    }
                } else {
                    accessibilityNode4 = null;
                }
                if (accessibilityNode4 == null) {
                    accessibilityNodeInfoCompat2 = accessibilityNode3.obtainCopyCompat();
                } else {
                    accessibilityNodeInfoCompat2 = accessibilityNode4.obtainCopyCompat();
                }
            }
        }
        if (accessibilityNodeInfoCompat2 != null && AccessibilityNodeInfoUtils.shouldFocusNode(accessibilityNodeInfoCompat2)) {
            return accessibilityNodeInfoCompat2;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FocusActionRecord)) {
            return false;
        }
        FocusActionRecord focusActionRecord = (FocusActionRecord) obj;
        if (this.focusedNode.equals(focusActionRecord.focusedNode) && this.nodePathDescription.equals(focusActionRecord.nodePathDescription) && this.extraInfo.equals(focusActionRecord.extraInfo) && this.actionTime == focusActionRecord.actionTime && Objects.equals(this.uniqueId, focusActionRecord.uniqueId)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(Long.valueOf(this.actionTime), this.focusedNode, this.nodePathDescription, this.extraInfo);
    }

    public final String toString() {
        FocusActionInfo focusActionInfo = this.extraInfo;
        return "FocusActionRecord: \n    node=" + AccessibilityNodeInfoUtils.toStringShort(this.focusedNode) + "\n    time=" + this.actionTime + "\n    extraInfo=" + focusActionInfo.toString() + "\n    uniqueId=" + this.uniqueId;
    }

    private FocusActionRecord(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, NodePathDescription nodePathDescription, FocusActionInfo focusActionInfo, long j, String str) {
        this.focusedNode = accessibilityNodeInfoCompat;
        this.nodePathDescription = new NodePathDescription(nodePathDescription);
        this.extraInfo = focusActionInfo;
        this.actionTime = j;
        this.uniqueId = str;
    }
}
