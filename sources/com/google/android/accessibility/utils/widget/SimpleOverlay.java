package com.google.android.accessibility.utils.widget;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SimpleOverlay {
    private final ViewGroup contentView;
    public boolean isVisible;
    public final WindowManager.LayoutParams params;
    private final WindowManager windowManager;

    public SimpleOverlay(Context context) {
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.contentView = new FrameLayout(context) { // from class: com.google.android.accessibility.utils.widget.SimpleOverlay.1
            @Override // android.view.ViewGroup, android.view.View
            public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
                motionEvent.offsetLocation(-getTranslationX(), -getTranslationY());
                return super.dispatchTouchEvent(motionEvent);
            }

            @Override // android.view.ViewGroup, android.view.ViewParent
            public final boolean requestSendAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                return false;
            }

            @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
            public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
            }
        };
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.params = layoutParams;
        layoutParams.type = 2032;
        layoutParams.format = -3;
        layoutParams.flags |= 8;
        this.isVisible = false;
    }

    public void hide() {
        if (!this.isVisible) {
            return;
        }
        this.windowManager.removeViewImmediate(this.contentView);
        this.isVisible = false;
    }

    public final void setContentView(View view) {
        this.contentView.removeAllViews();
        this.contentView.addView(view);
    }

    public final void setParams(WindowManager.LayoutParams layoutParams) {
        this.params.copyFrom(layoutParams);
        updateViewLayout();
    }

    public final void show() {
        if (this.isVisible) {
            return;
        }
        if (this.contentView.getParent() != null) {
            this.windowManager.removeViewImmediate(this.contentView);
        }
        try {
            this.windowManager.addView(this.contentView, this.params);
            this.isVisible = true;
        } catch (WindowManager.BadTokenException e) {
            LogUtils.e("SimpleOverlay", e, "BadTokenException is detected in %s.", getClass().getName());
        }
    }

    public final void updateViewLayout() {
        if (this.isVisible) {
            this.windowManager.updateViewLayout(this.contentView, this.params);
        }
    }
}
