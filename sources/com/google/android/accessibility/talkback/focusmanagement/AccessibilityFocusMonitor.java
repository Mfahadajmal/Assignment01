package com.google.android.accessibility.talkback.focusmanagement;

import android.accessibilityservice.AccessibilityService;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityWindowInfoCompat;
import com.google.android.accessibility.talkback.actor.FocusActorForScreenStateChange$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.focusmanagement.record.AccessibilityFocusActionHistory;
import com.google.android.accessibility.talkback.focusmanagement.record.FocusActionRecord;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Filter;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFocusMonitor {
    private final AppLifecycleMonitor focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final HapticPatternParser$$ExternalSyntheticLambda1 history$ar$class_merging$ar$class_merging;
    private final AccessibilityService service;
    public static final Filter NUMBER_PICKER_FILTER_FOR_ADJUST = new Filter.NodeCompat(new FocusActorForScreenStateChange$$ExternalSyntheticLambda0(4));
    private static final Filter FILTER_VISIBLE_EDIT_TEXT_ROLE = new Filter() { // from class: com.google.android.accessibility.talkback.focusmanagement.AccessibilityFocusMonitor.1
        @Override // com.google.android.accessibility.utils.Filter
        public final /* bridge */ /* synthetic */ boolean accept(Object obj) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = (AccessibilityNodeInfoCompat) obj;
            if (accessibilityNodeInfoCompat.isVisibleToUser() && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
                return true;
            }
            return false;
        }
    };

    public AccessibilityFocusMonitor(AccessibilityService accessibilityService, AppLifecycleMonitor appLifecycleMonitor, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.service = accessibilityService;
        this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.history$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    public final AccessibilityNodeInfoCompat getAccessibilityFocus(boolean z) {
        return getAccessibilityFocus(z, true);
    }

    public final AccessibilityNodeInfoCompat getEditingNodeFromFocusedKeyboard(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AccessibilityNodeInfoCompat inputFocus;
        AccessibilityWindowInfoCompat window;
        AccessibilityNodeInfoCompat selfOrMatchingDescendant;
        if (FormFactorUtils.getInstance().isAndroidWear && (window = AccessibilityNodeInfoUtils.getWindow(accessibilityNodeInfoCompat)) != null && window.getType() == 2 && (selfOrMatchingDescendant = AccessibilityNodeInfoUtils.getSelfOrMatchingDescendant(window.getRoot(), FILTER_VISIBLE_EDIT_TEXT_ROLE)) != null) {
            LogUtils.d("AccessibilityFocusMonitor", "Get the editing node from IME on Wear. node=%s", selfOrMatchingDescendant);
            return selfOrMatchingDescendant;
        }
        if (AccessibilityNodeInfoUtils.isSelfOrAncestorFocused(accessibilityNodeInfoCompat) && AccessibilityNodeInfoUtils.isKeyboard(accessibilityNodeInfoCompat) && (inputFocus = getInputFocus()) != null && inputFocus.isVisibleToUser() && SpannableUtils$IdentifierSpan.getRole(inputFocus) == 4) {
            LogUtils.d("AccessibilityFocusMonitor", "Get the editing node from input focus. node=%s", inputFocus);
            return inputFocus;
        }
        LogUtils.d("AccessibilityFocusMonitor", "Failed to get the editing node.", new Object[0]);
        return null;
    }

    public final AccessibilityNodeInfoCompat getInputFocus() {
        return this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(1);
    }

    public final AccessibilityNodeInfoCompat getNodeForEditingActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 4) {
            return accessibilityNodeInfoCompat;
        }
        return getEditingNodeFromFocusedKeyboard(accessibilityNodeInfoCompat);
    }

    public final AccessibilityNodeInfoCompat getSupportedAdjustableNode() {
        AccessibilityNodeInfoCompat accessibilityFocus = getAccessibilityFocus(false);
        if (SpannableUtils$IdentifierSpan.getRole(accessibilityFocus) == 10) {
            return accessibilityFocus;
        }
        return AccessibilityNodeInfoUtils.getMatchingAncestor(accessibilityFocus, NUMBER_PICKER_FILTER_FOR_ADJUST);
    }

    public final boolean hasAccessibilityFocus$ar$ds() {
        if (getAccessibilityFocus(false) == null) {
            return false;
        }
        return true;
    }

    public final AccessibilityNodeInfoCompat getAccessibilityFocus(boolean z, boolean z2) {
        AccessibilityNodeInfoCompat findFocusCompat = this.focusFinder$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.findFocusCompat(2);
        if (findFocusCompat != null && AccessibilityNodeInfoUtils.isVisible(findFocusCompat)) {
            return findFocusCompat;
        }
        if (!z) {
            return null;
        }
        AccessibilityNodeInfoCompat inputFocus = getInputFocus();
        if (inputFocus != null) {
            boolean z3 = true;
            if (!inputFocus.isEditable() && SpannableUtils$IdentifierSpan.getRole(inputFocus) != 4) {
                z3 = false;
            }
            if (inputFocus.isFocused() && (!z2 || z3)) {
                return inputFocus;
            }
        } else {
            LogUtils.w("AccessibilityFocusMonitor", "getAccessibilityFocus, inputFocusedNode is null", new Object[0]);
        }
        FocusActionRecord focusActionRecord = ((AccessibilityFocusActionHistory) this.history$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).lastEditableFocusActionRecord;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = focusActionRecord == null ? null : focusActionRecord.focusedNode;
        if (accessibilityNodeInfoCompat != null && accessibilityNodeInfoCompat.refresh()) {
            if (SpannableUtils$IdentifierSpan.isInputWindowOnScreen(this.service)) {
                return accessibilityNodeInfoCompat;
            }
            LogUtils.d("AccessibilityFocusMonitor", "getAccessibilityFocus, no ime window on the screen", new Object[0]);
        }
        LogUtils.e("AccessibilityFocusMonitor", "getAccessibilityFocus, couldn't fallback from lastFocusedEditFieldInHistory", new Object[0]);
        return null;
    }
}
