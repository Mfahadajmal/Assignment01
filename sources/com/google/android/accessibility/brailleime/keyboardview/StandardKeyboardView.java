package com.google.android.accessibility.brailleime.keyboardview;

import android.content.Context;
import android.graphics.Rect;
import android.util.Size;
import android.view.View;
import com.google.android.accessibility.brailleime.keyboardview.KeyboardView;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StandardKeyboardView extends KeyboardView {
    private final boolean fullScreen;

    public StandardKeyboardView(Context context, RetryingNameResolver.ResolutionResultListener resolutionResultListener, boolean z) {
        super(context, resolutionResultListener);
        this.fullScreen = false;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    protected final View createImeInputViewInternal() {
        if (this.imeInputView == null) {
            this.imeInputView = new KeyboardView.ViewContainer(this.context);
        }
        return this.imeInputView;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final KeyboardView.ViewContainer createViewContainerInternal() {
        if (this.viewContainer == null) {
            if (this.imeInputView == null) {
                this.imeInputView = createImeInputViewInternal();
            }
            this.viewContainer = (KeyboardView.ViewContainer) this.imeInputView;
        }
        return this.viewContainer;
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final Size getScreenSize() {
        KeyboardView.ViewContainer viewContainer = this.viewContainer;
        Rect rect = new Rect();
        viewContainer.getWindowVisibleDisplayFrame(rect);
        return new Size(rect.width(), rect.height());
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    protected final void tearDownInternal() {
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final void updateViewContainerInternal() {
    }

    @Override // com.google.android.accessibility.brailleime.keyboardview.KeyboardView
    public final void setKeyboardViewTransparent(boolean z) {
    }
}
