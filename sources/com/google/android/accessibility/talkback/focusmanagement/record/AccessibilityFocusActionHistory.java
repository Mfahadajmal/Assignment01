package com.google.android.accessibility.talkback.focusmanagement.record;

import android.text.TextUtils;
import androidx.collection.LruCache;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.talkback.focusmanagement.interpreter.ScreenState;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.input.WindowEventInterpreter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import java.util.ArrayDeque;
import java.util.Deque;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityFocusActionHistory {
    public AccessibilityNodeInfoCompat cachedNodeToRestoreFocus;
    public FocusActionRecord lastEditableFocusActionRecord;
    public int timeoutToleranceMs;
    public final HapticPatternParser$$ExternalSyntheticLambda1 reader$ar$class_merging$ar$class_merging = new HapticPatternParser$$ExternalSyntheticLambda1(this, null);
    public FocusActionInfo pendingWebFocusActionInfo = null;
    public ScreenState pendingScreenState = null;
    public long pendingWebFocusActionTime = -1;
    public final Deque focusActionRecordList = new ArrayDeque();
    public final LruCache windowIdentifierToFocusActionRecordMap = new LruCache(10);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WindowIdentifier {
        private final CharSequence accessibilityPaneTitle;
        private final int windowId;
        private final CharSequence windowTitle;

        public WindowIdentifier() {
        }

        public static WindowIdentifier create(int i, ScreenState screenState) {
            CharSequence charSequence;
            CharSequence charSequence2;
            CharSequence charSequence3 = "";
            if (screenState == null) {
                charSequence = "";
            } else {
                CharSequence windowTitle = screenState.getWindowTitle(i);
                WindowEventInterpreter.Window window = (WindowEventInterpreter.Window) ((WindowEventInterpreter) screenState.windowsDelegate).windowIdToData.get(Integer.valueOf(i));
                if (window == null || !window.hasAccessibilityPane) {
                    charSequence2 = "";
                } else {
                    charSequence2 = window.title;
                }
                if (true != TextUtils.isEmpty(charSequence2)) {
                    charSequence3 = charSequence2;
                }
                charSequence = charSequence3;
                charSequence3 = windowTitle;
            }
            return new WindowIdentifier(i, charSequence3, charSequence);
        }

        public final CharSequence accessibilityPaneTitle() {
            return this.accessibilityPaneTitle;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof WindowIdentifier) {
                WindowIdentifier windowIdentifier = (WindowIdentifier) obj;
                if (this.windowId == windowIdentifier.windowId() && this.windowTitle.equals(windowIdentifier.windowTitle()) && this.accessibilityPaneTitle.equals(windowIdentifier.accessibilityPaneTitle())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((((this.windowId ^ 1000003) * 1000003) ^ this.windowTitle.hashCode()) * 1000003) ^ this.accessibilityPaneTitle.hashCode();
        }

        public final String toString() {
            return "WindowIdentifier{windowId=" + this.windowId + ", windowTitle=" + this.windowTitle.toString() + ", accessibilityPaneTitle=" + this.accessibilityPaneTitle.toString() + "}";
        }

        public final int windowId() {
            return this.windowId;
        }

        public final CharSequence windowTitle() {
            return this.windowTitle;
        }

        public WindowIdentifier(int i, CharSequence charSequence, CharSequence charSequence2) {
            this();
            this.windowId = i;
            if (charSequence == null) {
                throw new NullPointerException("Null windowTitle");
            }
            this.windowTitle = charSequence;
            if (charSequence2 != null) {
                this.accessibilityPaneTitle = charSequence2;
                return;
            }
            throw new NullPointerException("Null accessibilityPaneTitle");
        }
    }

    public AccessibilityFocusActionHistory() {
        this.timeoutToleranceMs = 300;
        if (FormFactorUtils.getInstance().isAndroidTv) {
            this.timeoutToleranceMs = 400;
        }
    }

    public final FocusActionRecord getLastFocusActionRecord() {
        return (FocusActionRecord) this.focusActionRecordList.peekLast();
    }

    public final void onAccessibilityFocusAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, FocusActionInfo focusActionInfo, long j, ScreenState screenState) {
        FocusActionRecord focusActionRecord = new FocusActionRecord(accessibilityNodeInfoCompat, focusActionInfo, j);
        this.focusActionRecordList.offer(focusActionRecord);
        if (this.focusActionRecordList.size() > 5) {
            this.focusActionRecordList.pollFirst();
        }
        this.windowIdentifierToFocusActionRecordMap.put(WindowIdentifier.create(accessibilityNodeInfoCompat.getWindowId(), screenState), FocusActionRecord.copy(focusActionRecord));
        if (!accessibilityNodeInfoCompat.isEditable() && SpannableUtils$IdentifierSpan.getRole(accessibilityNodeInfoCompat) != 4) {
            return;
        }
        this.lastEditableFocusActionRecord = FocusActionRecord.copy(focusActionRecord);
    }

    public final AccessibilityNodeInfoCompat popCachedNodeToRestoreFocus() {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.cachedNodeToRestoreFocus;
        this.cachedNodeToRestoreFocus = null;
        return accessibilityNodeInfoCompat;
    }
}
