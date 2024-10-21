package com.google.android.libraries.accessibility.widgets.simple;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SimpleOverlay {
    public final ViewGroup contentView;
    public final Context context;
    private boolean isVisible;
    public final WindowManager.LayoutParams params;
    private final WindowManager windowManager;

    public SimpleOverlay(Context context) {
        this.context = context;
        this.windowManager = (WindowManager) context.getSystemService("window");
        this.contentView = new FrameLayout(context) { // from class: com.google.android.libraries.accessibility.widgets.simple.SimpleOverlay.1
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
        layoutParams.type = 2003;
        layoutParams.format = -3;
        layoutParams.flags |= 8;
        this.isVisible = false;
    }

    public final View findViewById(int i) {
        return this.contentView.findViewById(i);
    }

    public final void hide() {
        if (!this.isVisible) {
            return;
        }
        this.windowManager.removeViewImmediate(this.contentView);
        this.isVisible = false;
    }

    public final void setParams(WindowManager.LayoutParams layoutParams) {
        this.params.copyFrom(layoutParams);
        if (this.isVisible) {
            this.windowManager.updateViewLayout(this.contentView, this.params);
        }
    }

    public final void show() {
        if (this.isVisible) {
            return;
        }
        try {
            this.windowManager.addView(this.contentView, this.params);
            this.isVisible = true;
        } catch (WindowManager.BadTokenException e) {
            Log.e("SimpleOverlay", "Overlay not shown. Ignoring thrown BadTokenException.", e);
        }
    }
}
