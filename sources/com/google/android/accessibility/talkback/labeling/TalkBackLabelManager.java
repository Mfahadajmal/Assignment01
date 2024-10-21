package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.labeling.LabelsFetchRequest;
import com.google.android.accessibility.utils.AccessibilityEventListener;
import com.google.android.accessibility.utils.AccessibilityNodeInfoUtils;
import com.google.android.accessibility.utils.Performance;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.labeling.LabelManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TalkBackLabelManager implements LabelManager, AccessibilityEventListener {
    private boolean hasFocusedEventText = false;

    public static CharSequence getNodeText(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, TalkBackLabelManager talkBackLabelManager) {
        Label labelForViewIdFromCache;
        CharSequence nodeText = AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat);
        if (!TextUtils.isEmpty(nodeText)) {
            return nodeText;
        }
        if (talkBackLabelManager != null && talkBackLabelManager.isInitialized() && (labelForViewIdFromCache = talkBackLabelManager.getLabelForViewIdFromCache(accessibilityNodeInfoCompat.getViewIdResourceName())) != null) {
            return labelForViewIdFromCache.mText;
        }
        return null;
    }

    public abstract boolean canAddLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final int getEventTypes() {
        return 32768;
    }

    @Override // com.google.android.accessibility.utils.labeling.LabelManager
    public abstract Label getLabelForViewIdFromCache(String str);

    public abstract void getLabelsFromDatabase(LabelsFetchRequest.OnLabelsFetchedListener onLabelsFetchedListener);

    public boolean isInitialized() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean needsLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (accessibilityNodeInfoCompat == null) {
            return false;
        }
        if ((this.hasFocusedEventText && (SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 14 || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 30 || SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) == 31)) || !accessibilityNodeInfoCompat.isEnabled() || ((!AccessibilityNodeInfoUtils.isClickable(accessibilityNodeInfoCompat) && !AccessibilityNodeInfoUtils.isLongClickable(accessibilityNodeInfoCompat) && (!AccessibilityNodeInfoUtils.isFocusable(accessibilityNodeInfoCompat) || !canAddLabel(accessibilityNodeInfoCompat))) || accessibilityNodeInfoCompat.getChildCount() != 0 || !TextUtils.isEmpty(AccessibilityNodeInfoUtils.getNodeText(accessibilityNodeInfoCompat)))) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.accessibility.utils.AccessibilityEventListener
    public final void onAccessibilityEvent(AccessibilityEvent accessibilityEvent, Performance.EventId eventId) {
        this.hasFocusedEventText = !TextUtils.isEmpty(SpannableUtils$IdentifierSpan.getEventTextOrDescription(accessibilityEvent));
    }

    public abstract boolean setLabel(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, String str);

    public void onUnlockedBoot() {
    }

    public void shutdown() {
    }

    public void onResume(Context context) {
    }

    public void onSuspend(Context context) {
    }
}
