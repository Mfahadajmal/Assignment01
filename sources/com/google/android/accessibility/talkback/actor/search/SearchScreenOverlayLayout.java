package com.google.android.accessibility.talkback.actor.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SearchScreenOverlayLayout extends LinearLayout {
    public View.OnKeyListener keyListener;
    int overlayId;

    public SearchScreenOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.keyListener = null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        View.OnKeyListener onKeyListener = this.keyListener;
        if (onKeyListener != null && onKeyListener.onKey(this, keyEvent.getKeyCode(), keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.overlayId = accessibilityNodeInfo.getWindowId();
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.overlayId = accessibilityEvent.getWindowId();
        return super.requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View
    public final void setOnKeyListener(View.OnKeyListener onKeyListener) {
        this.keyListener = onKeyListener;
    }
}
