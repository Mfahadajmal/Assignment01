package com.google.android.accessibility.utils.traversal;

import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OrderedTraversalController {
    public AccessibilityNodeInfoCompat initialFocusNode;
    public final Map nodeTreeMap = new LinkedHashMap();
    public Map speakingNodesCache;
    public ExecutionList.RunnableExecutorPair tree$ar$class_merging;

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.lang.Object] */
    public static final void detachSubtreeFromItsParent$ar$ds$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        Object obj = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
        if (obj != null) {
            ((ExecutionList.RunnableExecutorPair) obj).ExecutionList$RunnableExecutorPair$ar$executor.remove(runnableExecutorPair);
        }
        runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next = null;
    }

    public final ExecutionList.RunnableExecutorPair createWorkingTree$ar$ds$ar$class_merging(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ExecutionList.RunnableExecutorPair runnableExecutorPair, NodeCachedBoundsCalculator nodeCachedBoundsCalculator) {
        if (this.nodeTreeMap.containsKey(accessibilityNodeInfoCompat)) {
            LogUtils.w("OrderedTraversalCont", "creating node tree with looped nodes - break the loop edge", new Object[0]);
            return null;
        }
        ExecutionList.RunnableExecutorPair runnableExecutorPair2 = new ExecutionList.RunnableExecutorPair(accessibilityNodeInfoCompat, runnableExecutorPair);
        this.nodeTreeMap.put(accessibilityNodeInfoCompat, runnableExecutorPair2);
        if (!WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat)) {
            ReorderedChildrenIterator createAscendingIterator = ReorderedChildrenIterator.createAscendingIterator(accessibilityNodeInfoCompat, nodeCachedBoundsCalculator);
            while (createAscendingIterator.hasNext()) {
                ExecutionList.RunnableExecutorPair createWorkingTree$ar$ds$ar$class_merging = createWorkingTree$ar$ds$ar$class_merging(createAscendingIterator.next(), runnableExecutorPair2, nodeCachedBoundsCalculator);
                if (createWorkingTree$ar$ds$ar$class_merging != null) {
                    runnableExecutorPair2.addChild$ar$class_merging(createWorkingTree$ar$ds$ar$class_merging);
                }
            }
        }
        return runnableExecutorPair2;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x005c A[LOOP:0: B:13:0x0027->B:18:0x005c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[EDGE_INSN: B:19:0x0060->B:9:0x0060 BREAK  A[LOOP:0: B:13:0x0027->B:18:0x005c], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.util.List, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.core.view.accessibility.AccessibilityNodeInfoCompat findNext(androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6) {
        /*
            r5 = this;
            java.util.Map r0 = r5.nodeTreeMap
            java.lang.Object r6 = r0.get(r6)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r6 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r6
            r0 = 0
            r1 = 0
            if (r6 != 0) goto L16
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r0 = "OrderedTraversalCont"
            java.lang.String r2 = "findNext(), can't find WorkingTree for AccessibilityNodeInfo"
            com.google.android.libraries.accessibility.utils.log.LogUtils.w(r0, r2, r6)
            return r1
        L16:
            java.lang.Object r2 = r6.ExecutionList$RunnableExecutorPair$ar$executor
            boolean r2 = r2.isEmpty()
            if (r2 != 0) goto L27
            java.lang.Object r6 = r6.ExecutionList$RunnableExecutorPair$ar$executor
            java.lang.Object r6 = r6.get(r0)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r6 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r6
            goto L60
        L27:
            if (r6 == 0) goto L5f
            r2 = r6
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r2 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r2
            java.lang.Object r3 = r2.ExecutionList$RunnableExecutorPair$ar$next
            if (r3 != 0) goto L32
        L30:
            r6 = r1
            goto L59
        L32:
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r3 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r3
            java.lang.Object r4 = r3.ExecutionList$RunnableExecutorPair$ar$executor
            int r6 = r4.indexOf(r6)
            if (r6 >= 0) goto L46
            java.lang.Object[] r6 = new java.lang.Object[r0]
            java.lang.String r3 = "WorkingTree"
            java.lang.String r4 = "WorkingTree IllegalStateException: swap child not found"
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r3, r4, r6)
            goto L30
        L46:
            int r6 = r6 + 1
            java.lang.Object r4 = r3.ExecutionList$RunnableExecutorPair$ar$executor
            int r4 = r4.size()
            if (r6 < r4) goto L51
            goto L30
        L51:
            java.lang.Object r3 = r3.ExecutionList$RunnableExecutorPair$ar$executor
            java.lang.Object r6 = r3.get(r6)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r6 = (com.google.common.util.concurrent.ExecutionList.RunnableExecutorPair) r6
        L59:
            if (r6 == 0) goto L5c
            goto L60
        L5c:
            java.lang.Object r6 = r2.ExecutionList$RunnableExecutorPair$ar$next
            goto L27
        L5f:
            r6 = r1
        L60:
            if (r6 == 0) goto L67
            java.lang.Object r6 = r6.ExecutionList$RunnableExecutorPair$ar$runnable
            androidx.core.view.accessibility.AccessibilityNodeInfoCompat r6 = (androidx.core.view.accessibility.AccessibilityNodeInfoCompat) r6
            return r6
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.traversal.OrderedTraversalController.findNext(androidx.core.view.accessibility.AccessibilityNodeInfoCompat):androidx.core.view.accessibility.AccessibilityNodeInfoCompat");
    }

    public final ExecutionList.RunnableExecutorPair getParentsThatAreMovedBeforeOrSameNode$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        Object obj = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
        if (obj != null) {
            ExecutionList.RunnableExecutorPair runnableExecutorPair2 = (ExecutionList.RunnableExecutorPair) obj;
            AccessibilityNodeInfoCompat traversalBefore = ((AccessibilityNodeInfoCompat) runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$runnable).getTraversalBefore();
            if (traversalBefore != null && traversalBefore.equals(runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable)) {
                return getParentsThatAreMovedBeforeOrSameNode$ar$class_merging(runnableExecutorPair2);
            }
            return runnableExecutorPair;
        }
        return runnableExecutorPair;
    }
}
