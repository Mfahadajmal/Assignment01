package com.google.android.accessibility.talkback;

import android.text.TextUtils;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.Pipeline;
import com.google.android.accessibility.talkback.compositor.GlobalVariables;
import com.google.android.accessibility.talkback.eventprocessor.ProcessorPhoneticLetters;
import com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor;
import com.google.android.accessibility.talkback.focusmanagement.record.NodePathDescription$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.WebInterfaceUtils;
import com.google.android.accessibility.utils.input.CursorGranularity;
import com.google.android.accessibility.utils.traversal.ReorderedChildrenIterator;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.common.base.Predicate;
import j$.util.Collection;
import j$.util.function.Consumer$CC;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CursorGranularityManager {
    private final AccessibilityFocusMonitor accessibilityFocusMonitor;
    public int currentNodeIndex;
    private final GlobalVariables globalVariables;
    public final SnackbarManager granularityTraversal$ar$class_merging;
    public AccessibilityNodeInfoCompat lockedNode;
    public Pipeline.FeedbackReturner pipeline;
    public boolean selectionModeActive;
    public ApplicationModule userInterface$ar$class_merging$ar$class_merging$ar$class_merging;
    public final List navigableNodes = new ArrayList();
    private final ArrayList supportedGranularities = new ArrayList();
    public CursorGranularity savedGranularity = CursorGranularity.DEFAULT;
    public CursorGranularity currentGranularity = CursorGranularity.DEFAULT;

    public CursorGranularityManager(GlobalVariables globalVariables, AccessibilityFocusMonitor accessibilityFocusMonitor, ProcessorPhoneticLetters processorPhoneticLetters) {
        this.globalVariables = globalVariables;
        this.accessibilityFocusMonitor = accessibilityFocusMonitor;
        this.granularityTraversal$ar$class_merging = new SnackbarManager(processorPhoneticLetters);
    }

    public static int extractNavigableNodes(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, List list, Set set) {
        int i = 0;
        if (accessibilityNodeInfoCompat == null || !set.add(accessibilityNodeInfoCompat)) {
            return 0;
        }
        if (SnackbarManager.shouldHandleGranularityTraversalInTalkback(accessibilityNodeInfoCompat)) {
            LogUtils.d("CursorGranularityManager", "Adding granularities supported by Talkback managed granularity navigation", new Object[0]);
            i = 11;
            if (list != null) {
                list.add(accessibilityNodeInfoCompat);
            } else {
                list = null;
            }
        } else if (AccessibilityNodeInfoUtils.getMovementGranularity(accessibilityNodeInfoCompat) != 0) {
            if (list != null) {
                list.add(accessibilityNodeInfoCompat);
            } else {
                list = null;
            }
            i = accessibilityNodeInfoCompat.getMovementGranularities();
        }
        if (!TextUtils.isEmpty(accessibilityNodeInfoCompat.getContentDescription())) {
            return i;
        }
        ReorderedChildrenIterator createAscendingIterator = ReorderedChildrenIterator.createAscendingIterator(accessibilityNodeInfoCompat);
        while (createAscendingIterator.hasNext()) {
            AccessibilityNodeInfoCompat next = createAscendingIterator.next();
            if (AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE.accept(next)) {
                i |= extractNavigableNodes(next, list, set);
            }
        }
        return i;
    }

    private final void setLockedNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, boolean z, final Performance.EventId eventId) {
        AccessibilityNodeInfoCompat editingNodeFromFocusedKeyboard = this.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat);
        if (editingNodeFromFocusedKeyboard != null) {
            accessibilityNodeInfoCompat = editingNodeFromFocusedKeyboard;
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = this.lockedNode;
        if (accessibilityNodeInfoCompat2 != null && (!accessibilityNodeInfoCompat2.equals(accessibilityNodeInfoCompat) || !TextUtils.equals(AccessibilityNodeInfoUtils.getText(this.lockedNode), AccessibilityNodeInfoUtils.getText(accessibilityNodeInfoCompat)))) {
            clear();
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat3 = this.lockedNode;
        if (accessibilityNodeInfoCompat3 == null && accessibilityNodeInfoCompat != null) {
            this.lockedNode = accessibilityNodeInfoCompat;
            int i = 2;
            if (z && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4 && !accessibilityNodeInfoCompat.isFocused()) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat4 = this.lockedNode;
                final Pipeline.FeedbackReturner feedbackReturner = this.pipeline;
                if (accessibilityNodeInfoCompat4 != null) {
                    SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(feedbackReturner, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat4, 131072));
                    AccessibilityNodeInfoUtils.getSelfOrMatchingDescendant(accessibilityNodeInfoCompat4, new Filter.NodeCompat(new NodePathDescription$$ExternalSyntheticLambda0(new Filter.NodeCompat(new Predicate() { // from class: com.google.android.accessibility.talkback.CursorGranularityManager$$ExternalSyntheticLambda0
                        @Override // com.google.common.base.Predicate
                        public final boolean apply(Object obj) {
                            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat5 = (AccessibilityNodeInfoCompat) obj;
                            if (AccessibilityNodeInfoUtils.FILTER_NON_FOCUSABLE_VISIBLE_NODE.accept(accessibilityNodeInfoCompat5)) {
                                if (SpannableUtils$NonCopyableTextSpan.$default$returnFeedback(Pipeline.FeedbackReturner.this, eventId, Feedback.nodeAction(accessibilityNodeInfoCompat5, 131072))) {
                                    return true;
                                }
                                return false;
                            }
                            return false;
                        }
                    }), i)));
                }
            }
            CursorGranularity.extractFromMask(extractNavigableNodes(this.lockedNode, this.navigableNodes, new HashSet()), WebInterfaceUtils.supportsWebActions(this.lockedNode), WebInterfaceUtils.getSupportedHtmlElements(this.lockedNode), this.supportedGranularities);
            LogUtils.d("CursorGranularityManager", "setLockedNode: lockedNode=%s, supportedGranularities=%s", this.lockedNode, this.supportedGranularities);
            return;
        }
        LogUtils.d("CursorGranularityManager", "setLockedNode - reuse lock node or the lock node is null, lockNode=%s", accessibilityNodeInfoCompat3);
    }

    public final boolean adjustGranularityAt(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, int i, Performance.EventId eventId) {
        setLockedNode(accessibilityNodeInfoCompat, true, eventId);
        if (accessibilityNodeInfoCompat == null) {
            this.supportedGranularities.clear();
            CursorGranularity.extractFromMask(0, false, null, this.supportedGranularities);
        }
        ArrayList arrayList = this.supportedGranularities;
        int size = arrayList.size();
        int indexOf = arrayList.indexOf(this.currentGranularity);
        int i2 = (i + indexOf) % size;
        if (i2 < 0) {
            i2 = size - 1;
        }
        CursorGranularity cursorGranularity = (CursorGranularity) this.supportedGranularities.get(i2);
        this.currentGranularity = cursorGranularity;
        this.savedGranularity = cursorGranularity;
        if (i2 != indexOf) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v4, types: [java.util.Map, java.lang.Object] */
    public final void clear() {
        LogUtils.v("CursorGranularityManager", "Clearing the currently locked node and associated state variables", new Object[0]);
        this.currentNodeIndex = 0;
        this.supportedGranularities.clear();
        this.navigableNodes.clear();
        this.granularityTraversal$ar$class_merging.lock.clear();
        this.lockedNode = null;
        setSelectionModeActive(false);
    }

    public final boolean isLockedToNodeOrEditingNode(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (this.lockedNode == null) {
            LogUtils.e("CursorGranularityManager", "isLockedToNodeOrEditingNode- lockedNode is null ", new Object[0]);
            return false;
        }
        AccessibilityNodeInfoCompat editingNodeFromFocusedKeyboard = this.accessibilityFocusMonitor.getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat);
        if (editingNodeFromFocusedKeyboard != null) {
            accessibilityNodeInfoCompat = editingNodeFromFocusedKeyboard;
        }
        if (accessibilityNodeInfoCompat == null) {
            LogUtils.d("CursorGranularityManager", "isLockedToNodeOrEditingNode- node to check  is null ", new Object[0]);
            return false;
        }
        if (this.currentGranularity == CursorGranularity.DEFAULT || !this.lockedNode.equals(accessibilityNodeInfoCompat) || this.currentGranularity.isNativeMacroGranularity()) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:156:0x033e, code lost:
    
        r17 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0340, code lost:
    
        if (r23 == false) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0342, code lost:
    
        r20.currentNodeIndex = r13 - r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0346, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0347, code lost:
    
        r20.currentNodeIndex = r13 + r17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x034c, code lost:
    
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x034d, code lost:
    
        if (r23 == false) goto L168;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x034f, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0350, code lost:
    
        return -1;
     */
    /* JADX WARN: Type inference failed for: r5v29, types: [java.util.Map, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v39, types: [java.util.Map, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int navigateInternal(int r21, final com.google.android.accessibility.utils.Performance.EventId r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 852
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.CursorGranularityManager.navigateInternal(int, com.google.android.accessibility.utils.Performance$EventId, boolean):int");
    }

    public final boolean setGranularityAt(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, CursorGranularity cursorGranularity, Performance.EventId eventId) {
        boolean z;
        setLockedNode(accessibilityNodeInfoCompat, cursorGranularity.isMicroGranularity(), eventId);
        if (WebInterfaceUtils.supportsWebActions(accessibilityNodeInfoCompat) && cursorGranularity == CursorGranularity.WEB_LANDMARK) {
            z = true;
        } else {
            z = false;
        }
        if (!this.supportedGranularities.contains(cursorGranularity) && !z) {
            this.currentGranularity = CursorGranularity.DEFAULT;
            return false;
        }
        this.savedGranularity = cursorGranularity;
        this.currentGranularity = cursorGranularity;
        return true;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.List, java.lang.Object] */
    public final void setSelectionModeActive(final boolean z) {
        if (this.selectionModeActive != z) {
            this.selectionModeActive = z;
            this.globalVariables.mSelectionModeActive = z;
            ApplicationModule applicationModule = this.userInterface$ar$class_merging$ar$class_merging$ar$class_merging;
            if (!applicationModule.ApplicationModule$ar$application.isEmpty()) {
                Collection.EL.stream(applicationModule.ApplicationModule$ar$application).forEach(new Consumer() { // from class: com.google.android.accessibility.talkback.UserInterface$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((UserInterface$UserInputEventListener) obj).editTextOrSelectableTextSelected(z);
                    }

                    public final /* synthetic */ Consumer andThen(Consumer consumer) {
                        return Consumer$CC.$default$andThen(this, consumer);
                    }
                });
            }
        }
    }
}
