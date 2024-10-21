package com.google.android.material.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.ViewUtilsLollipop;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SearchBar$ScrollingViewBehavior extends AppBarLayout.ScrollingViewBehavior {
    private boolean initialized;

    public SearchBar$ScrollingViewBehavior() {
        this.initialized = false;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onDependentViewChanged$ar$ds(CoordinatorLayout coordinatorLayout, View view, View view2) {
        super.onDependentViewChanged$ar$ds(coordinatorLayout, view, view2);
        if (!this.initialized && (view2 instanceof AppBarLayout)) {
            this.initialized = true;
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            appBarLayout.setBackgroundColor(0);
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(appBarLayout, 0.0f);
        }
    }

    @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        super.onLayoutChild(coordinatorLayout, view, i);
        return true;
    }

    @Override // com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final /* bridge */ /* synthetic */ boolean onMeasureChild$ar$ds(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        return super.onMeasureChild$ar$ds(coordinatorLayout, view, i, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
    public final boolean shouldHeaderOverlapScrollingChild() {
        return true;
    }

    public SearchBar$ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initialized = false;
    }
}
