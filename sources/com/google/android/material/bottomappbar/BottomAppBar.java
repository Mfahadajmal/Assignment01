package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.navigation.NavigationBarItemView$$ExternalSyntheticLambda0;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BottomAppBar extends Toolbar implements CoordinatorLayout.AttachedBehavior {
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public final /* bridge */ /* synthetic */ CoordinatorLayout.Behavior getBehavior() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        throw null;
    }

    @Override // android.support.v7.widget.Toolbar, android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        throw null;
    }

    @Override // android.support.v7.widget.Toolbar, android.view.View
    protected final Parcelable onSaveInstanceState() {
        throw null;
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        throw null;
    }

    @Override // android.support.v7.widget.Toolbar
    public final void setNavigationIcon(Drawable drawable) {
        throw null;
    }

    @Override // android.support.v7.widget.Toolbar
    public final void setSubtitle(CharSequence charSequence) {
        throw null;
    }

    @Override // android.support.v7.widget.Toolbar
    public final void setTitle(CharSequence charSequence) {
        throw null;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Behavior extends HideBottomViewOnScrollBehavior<BottomAppBar> {
        public final Rect fabContentRect;
        private final View.OnLayoutChangeListener fabLayoutListener;
        public WeakReference viewRef;

        public Behavior() {
            this.fabLayoutListener = new NavigationBarItemView$$ExternalSyntheticLambda0(this, 1);
            this.fabContentRect = new Rect();
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            this.viewRef = new WeakReference((BottomAppBar) view);
            throw null;
        }

        @Override // com.google.android.material.behavior.HideBottomViewOnScrollBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            throw null;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.fabLayoutListener = new NavigationBarItemView$$ExternalSyntheticLambda0(this, 1);
            this.fabContentRect = new Rect();
        }
    }
}
