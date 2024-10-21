package com.google.android.accessibility.utils.traversal;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OrderedTraversalStrategy implements TraversalStrategy {
    public final OrderedTraversalController controller;
    private final Map speakingNodesCache;

    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.util.List, java.lang.Object] */
    public OrderedTraversalStrategy(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        HashMap hashMap = new HashMap();
        this.speakingNodesCache = hashMap;
        OrderedTraversalController orderedTraversalController = new OrderedTraversalController();
        this.controller = orderedTraversalController;
        orderedTraversalController.speakingNodesCache = hashMap;
        if (accessibilityNodeInfoCompat != null) {
            NodeCachedBoundsCalculator nodeCachedBoundsCalculator = new NodeCachedBoundsCalculator();
            nodeCachedBoundsCalculator.speakingNodesCache = orderedTraversalController.speakingNodesCache;
            orderedTraversalController.tree$ar$class_merging = orderedTraversalController.createWorkingTree$ar$ds$ar$class_merging(accessibilityNodeInfoCompat, null, nodeCachedBoundsCalculator);
            for (ExecutionList.RunnableExecutorPair runnableExecutorPair : orderedTraversalController.nodeTreeMap.values()) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = (AccessibilityNodeInfoCompat) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable;
                if (AccessibilityNodeInfoUtils.hasRequestInitialAccessibilityFocus(accessibilityNodeInfoCompat2)) {
                    orderedTraversalController.initialFocusNode = accessibilityNodeInfoCompat2;
                }
                AccessibilityNodeInfoCompat traversalBefore = accessibilityNodeInfoCompat2.getTraversalBefore();
                if (traversalBefore != null) {
                    ExecutionList.RunnableExecutorPair runnableExecutorPair2 = (ExecutionList.RunnableExecutorPair) orderedTraversalController.nodeTreeMap.get(traversalBefore);
                    if (runnableExecutorPair != null && runnableExecutorPair2 != null && !runnableExecutorPair.hasDescendant$ar$class_merging(runnableExecutorPair2)) {
                        ExecutionList.RunnableExecutorPair parentsThatAreMovedBeforeOrSameNode$ar$class_merging = orderedTraversalController.getParentsThatAreMovedBeforeOrSameNode$ar$class_merging(runnableExecutorPair);
                        Object obj = runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$next;
                        ExecutionList.RunnableExecutorPair runnableExecutorPair3 = (ExecutionList.RunnableExecutorPair) obj;
                        if (!parentsThatAreMovedBeforeOrSameNode$ar$class_merging.hasDescendant$ar$class_merging(runnableExecutorPair3)) {
                            OrderedTraversalController.detachSubtreeFromItsParent$ar$ds$ar$class_merging(parentsThatAreMovedBeforeOrSameNode$ar$class_merging);
                            if (obj != null) {
                                int indexOf = runnableExecutorPair3.ExecutionList$RunnableExecutorPair$ar$executor.indexOf(runnableExecutorPair2);
                                if (indexOf < 0) {
                                    LogUtils.e("WorkingTree", "WorkingTree IllegalStateException: swap child not found", new Object[0]);
                                } else {
                                    runnableExecutorPair3.ExecutionList$RunnableExecutorPair$ar$executor.set(indexOf, parentsThatAreMovedBeforeOrSameNode$ar$class_merging);
                                }
                            }
                            parentsThatAreMovedBeforeOrSameNode$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$next = obj;
                            runnableExecutorPair.addChild$ar$class_merging(runnableExecutorPair2);
                            runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$next = runnableExecutorPair;
                        }
                    }
                } else {
                    AccessibilityNodeInfoCompat traversalAfter = accessibilityNodeInfoCompat2.getTraversalAfter();
                    if (traversalAfter != null) {
                        ExecutionList.RunnableExecutorPair runnableExecutorPair4 = (ExecutionList.RunnableExecutorPair) orderedTraversalController.nodeTreeMap.get(traversalAfter);
                        if (runnableExecutorPair != null && runnableExecutorPair4 != null && !runnableExecutorPair.hasDescendant$ar$class_merging(runnableExecutorPair4)) {
                            ExecutionList.RunnableExecutorPair parentsThatAreMovedBeforeOrSameNode$ar$class_merging2 = orderedTraversalController.getParentsThatAreMovedBeforeOrSameNode$ar$class_merging(runnableExecutorPair);
                            if (!parentsThatAreMovedBeforeOrSameNode$ar$class_merging2.hasDescendant$ar$class_merging(runnableExecutorPair4)) {
                                OrderedTraversalController.detachSubtreeFromItsParent$ar$ds$ar$class_merging(parentsThatAreMovedBeforeOrSameNode$ar$class_merging2);
                                runnableExecutorPair4.addChild$ar$class_merging(parentsThatAreMovedBeforeOrSameNode$ar$class_merging2);
                                parentsThatAreMovedBeforeOrSameNode$ar$class_merging2.ExecutionList$RunnableExecutorPair$ar$next = runnableExecutorPair4;
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004d  */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.core.view.accessibility.AccessibilityNodeInfoCompat findFocus(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4, int r5) {
        /*
            r3 = this;
            r0 = 1
            if (r5 == r0) goto L59
            r0 = 2
            r1 = 0
            if (r5 == r0) goto L8
            goto L58
        L8:
            com.google.android.accessibility.utils.traversal.OrderedTraversalController r5 = r3.controller
            java.util.Map r5 = r5.nodeTreeMap
            java.lang.Object r4 = r5.get(r4)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r4 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r4
            r5 = 0
            if (r4 != 0) goto L1f
            java.lang.Object[] r4 = new java.lang.Object[r5]
            java.lang.String r5 = "OrderedTraversalCont"
            java.lang.String r0 = "findPrevious(), can't find WorkingTree for AccessibilityNodeInfo"
            com.google.android.libraries.accessibility.utils.log.LogUtils.w(r5, r0, r4)
            goto L58
        L1f:
            java.lang.Object r0 = r4.ExecutionList$RunnableExecutorPair$ar$next
            if (r0 != 0) goto L25
        L23:
            r5 = r1
            goto L46
        L25:
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r0
            java.lang.Object r2 = r0.ExecutionList$RunnableExecutorPair$ar$executor
            int r2 = r2.indexOf(r4)
            if (r2 >= 0) goto L39
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r0 = "WorkingTree"
            java.lang.String r2 = "WorkingTree IllegalStateException: swap child not found"
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r0, r2, r5)
            goto L23
        L39:
            int r2 = r2 + (-1)
            if (r2 >= 0) goto L3e
            goto L23
        L3e:
            java.lang.Object r5 = r0.ExecutionList$RunnableExecutorPair$ar$executor
            java.lang.Object r5 = r5.get(r2)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r5 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r5
        L46:
            if (r5 == 0) goto L4d
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r4 = r5.getLastNode$ar$class_merging()
            goto L4f
        L4d:
            java.lang.Object r4 = r4.ExecutionList$RunnableExecutorPair$ar$next
        L4f:
            if (r4 == 0) goto L58
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r4 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r4
            java.lang.Object r4 = r4.ExecutionList$RunnableExecutorPair$ar$runnable
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat) r4
            return r4
        L58:
            return r1
        L59:
            com.google.android.accessibility.utils.traversal.OrderedTraversalController r5 = r3.controller
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r4 = r5.findNext(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.traversal.OrderedTraversalStrategy.findFocus(androidx.core.view.accessibility.AccessibilityNodeInfoCompat, int):androidx.core.view.accessibility.AccessibilityNodeInfoCompat");
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final AccessibilityNodeInfoCompat focusFirst(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i) {
        ExecutionList.RunnableExecutorPair runnableExecutorPair;
        if (i == 1) {
            ExecutionList.RunnableExecutorPair runnableExecutorPair2 = (ExecutionList.RunnableExecutorPair) this.controller.nodeTreeMap.get(accessibilityNodeInfoCompat);
            if (runnableExecutorPair2 != null) {
                return (AccessibilityNodeInfoCompat) runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$runnable;
            }
            return null;
        }
        if (i == 2 && (runnableExecutorPair = (ExecutionList.RunnableExecutorPair) this.controller.nodeTreeMap.get(accessibilityNodeInfoCompat)) != null) {
            return (AccessibilityNodeInfoCompat) runnableExecutorPair.getLastNode$ar$class_merging().ExecutionList$RunnableExecutorPair$ar$runnable;
        }
        return null;
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final AccessibilityNodeInfoCompat focusInitial(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        OrderedTraversalController orderedTraversalController = this.controller;
        ExecutionList.RunnableExecutorPair runnableExecutorPair = (ExecutionList.RunnableExecutorPair) orderedTraversalController.nodeTreeMap.get(accessibilityNodeInfoCompat);
        if (runnableExecutorPair != null && runnableExecutorPair.hasDescendant$ar$class_merging((ExecutionList.RunnableExecutorPair) orderedTraversalController.nodeTreeMap.get(orderedTraversalController.initialFocusNode))) {
            return orderedTraversalController.initialFocusNode;
        }
        return null;
    }

    @Override // com.google.android.accessibility.utils.traversal.TraversalStrategy
    public final Map getSpeakingNodesCache() {
        return this.speakingNodesCache;
    }
}
