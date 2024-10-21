package androidx.core.view;

import android.view.View;
import android.view.Window;
import androidx.core.graphics.BlendModeUtils$Api29Impl;

/* compiled from: PG */
/* loaded from: classes.dex */
class WindowInsetsControllerCompat$Impl20 extends BlendModeUtils$Api29Impl {
    protected final Window mWindow;

    public WindowInsetsControllerCompat$Impl20(Window window) {
        this.mWindow = window;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setSystemUiFlag(int i) {
        View decorView = this.mWindow.getDecorView();
        decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setWindowFlag$ar$ds() {
        this.mWindow.addFlags(Integer.MIN_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unsetSystemUiFlag(int i) {
        View decorView = this.mWindow.getDecorView();
        decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unsetWindowFlag(int i) {
        this.mWindow.clearFlags(i);
    }
}
