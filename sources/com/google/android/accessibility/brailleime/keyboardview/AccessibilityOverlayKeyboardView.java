package com.google.android.accessibility.brailleime.keyboardview;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Size;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.google.android.accessibility.brailleime.BrailleIme;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import com.google.android.accessibility.talkback.braille.TalkBackForBrailleImeImpl;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccessibilityOverlayKeyboardView extends KeyboardView {
    public AccessibilityOverlayKeyboardView(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        super(context, resolutionResultListener);
    }

    private final WindowManager.LayoutParams getWindowsLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.format = -3;
        layoutParams.flags |= 8;
        layoutParams.flags |= 256;
        layoutParams.flags |= 512;
        if (Build.VERSION.SDK_INT == 33) {
            layoutParams.flags |= 8388608;
        }
        layoutParams.type = 2032;
        layoutParams.gravity = 48;
        int i = layoutParams.gravity;
        Context context = this.context;
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("config_navBarInteractionMode", "integer", "android");
        int i2 = 3;
        if ((identifier <= 0 || resources.getInteger(identifier) != 2) && _BOUNDARY.getDisplayRotationDegrees(context) == 3) {
            i2 = 5;
        }
        layoutParams.gravity = i | i2;
        Size displaySizeInPixels = _BOUNDARY.getDisplaySizeInPixels(this.context);
        layoutParams.height = displaySizeInPixels.getHeight();
        layoutParams.width = displaySizeInPixels.getWidth();
        return layoutParams;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    protected final View createImeInputViewInternal() {
        if (this.imeInputView == null) {
            if (Build.VERSION.SDK_INT == 28) {
                View view = new View(this.context);
                view.setLayoutParams(new WindowManager.LayoutParams(-2, -2));
                this.imeInputView = view;
            } else {
                this.imeInputView = new FrameLayout(this.context);
            }
            this.imeInputView.setMinimumHeight(1);
        }
        return this.imeInputView;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final KeyboardView.ViewContainer createViewContainerInternal() {
        if (this.viewContainer == null) {
            this.viewContainer = new KeyboardView.ViewContainer(this.context);
        }
        if (((TalkBackForBrailleImeImpl) BrailleIme.talkBackForBrailleIme).dimScreenController.isDimmingEnabled()) {
            this.viewContainer.setAlpha(0.001f);
        }
        this.windowManager.addView(this.viewContainer, getWindowsLayoutParams());
        return this.viewContainer;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final Size getScreenSize() {
        return _BOUNDARY.getDisplaySizeInPixels(this.context);
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final void setKeyboardViewTransparent(boolean z) {
        float f;
        KeyboardView.ViewContainer viewContainer = this.viewContainer;
        if (viewContainer != null) {
            if (true != z) {
                f = 1.0f;
            } else {
                f = 0.001f;
            }
            viewContainer.setAlpha(f);
        }
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    protected final void tearDownInternal() {
        WindowManager windowManager = this.windowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(this.viewContainer);
        }
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final void updateViewContainerInternal() {
        this.windowManager.updateViewLayout(this.viewContainer, getWindowsLayoutParams());
    }
}
