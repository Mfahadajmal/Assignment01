package androidx.core.view;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat$Api21Impl;
import androidx.core.graphics.BlendModeUtils$Api29Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowInsetsControllerCompat$Impl30 extends BlendModeUtils$Api29Impl {
    final WindowInsetsController mInsetsController;
    protected Window mWindow;

    public WindowInsetsControllerCompat$Impl30(Window window) {
        WindowInsetsController insetsController;
        insetsController = window.getInsetsController();
        new SimpleArrayMap();
        this.mInsetsController = insetsController;
        this.mWindow = window;
    }

    @Override // androidx.core.graphics.BlendModeUtils$Api29Impl
    public final void setAppearanceLightNavigationBars(boolean z) {
        if (z) {
            if (this.mWindow != null) {
                setSystemUiFlag(16);
            }
            this.mInsetsController.setSystemBarsAppearance(16, 16);
        } else {
            if (this.mWindow != null) {
                unsetSystemUiFlag(16);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 16);
        }
    }

    @Override // androidx.core.graphics.BlendModeUtils$Api29Impl
    public final void setAppearanceLightStatusBars(boolean z) {
        if (z) {
            if (this.mWindow != null) {
                setSystemUiFlag(8192);
            }
            this.mInsetsController.setSystemBarsAppearance(8, 8);
        } else {
            if (this.mWindow != null) {
                unsetSystemUiFlag(8192);
            }
            this.mInsetsController.setSystemBarsAppearance(0, 8);
        }
    }

    protected final void setSystemUiFlag(int i) {
        View decorView = this.mWindow.getDecorView();
        decorView.setSystemUiVisibility(i | decorView.getSystemUiVisibility());
    }

    protected final void unsetSystemUiFlag(int i) {
        View decorView = this.mWindow.getDecorView();
        decorView.setSystemUiVisibility((~i) & decorView.getSystemUiVisibility());
    }

    public WindowInsetsControllerCompat$Impl30(Window window, AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat, FontResourcesParserCompat$Api21Impl fontResourcesParserCompat$Api21Impl) {
        this(window);
    }
}
