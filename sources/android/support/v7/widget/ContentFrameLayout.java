package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContentFrameLayout extends FrameLayout {
    public FloatingActionButton.ShadowDelegateImpl mAttachListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    public final Rect mDecorPadding;
    public TypedValue mFixedHeightMajor;
    public TypedValue mFixedHeightMinor;
    public TypedValue mFixedWidthMajor;
    public TypedValue mFixedWidthMinor;
    public TypedValue mMinWidthMajor;
    public TypedValue mMinWidthMinor;

    public ContentFrameLayout(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = this.mAttachListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        if (shadowDelegateImpl != null) {
            Object obj = shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
            AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) obj;
            DecorContentParent decorContentParent = appCompatDelegateImpl.mDecorContentParent;
            if (decorContentParent != null) {
                decorContentParent.dismissPopups();
            }
            if (appCompatDelegateImpl.mActionModePopup != null) {
                appCompatDelegateImpl.mWindow.getDecorView().removeCallbacks(appCompatDelegateImpl.mShowActionModePopup);
                if (appCompatDelegateImpl.mActionModePopup.isShowing()) {
                    try {
                        ((AppCompatDelegateImpl) obj).mActionModePopup.dismiss();
                    } catch (IllegalArgumentException unused) {
                    }
                }
                appCompatDelegateImpl.mActionModePopup = null;
            }
            appCompatDelegateImpl.endOnGoingFadeAnimation();
            MenuBuilder menuBuilder = appCompatDelegateImpl.getPanelState$ar$ds(0).menu;
            if (menuBuilder != null) {
                menuBuilder.close();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ba  */
    @Override // android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ContentFrameLayout.onMeasure(int, int):void");
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDecorPadding = new Rect();
    }
}
