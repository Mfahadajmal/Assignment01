package com.google.android.material.navigation;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tooltip.TooltipDrawable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationBarItemView$$ExternalSyntheticLambda0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ Object NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ NavigationBarItemView$$ExternalSyntheticLambda0(NavigationBarItemView navigationBarItemView, int i) {
        this.switching_field = i;
        this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0 = navigationBarItemView;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        boolean z;
        int i9 = this.switching_field;
        if (i9 != 0) {
            if (i9 != 1) {
                ((TooltipDrawable) this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0).updateLocationOnScreen(view);
                return;
            }
            if (((BottomAppBar) ((BottomAppBar.Behavior) this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0).viewRef.get()) != null && (((z = view instanceof FloatingActionButton)) || (view instanceof ExtendedFloatingActionButton))) {
                view.getHeight();
                if (z) {
                    ((FloatingActionButton) view).getMeasuredContentRect(((BottomAppBar.Behavior) this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0).fabContentRect);
                    ((BottomAppBar.Behavior) this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0).fabContentRect.height();
                    throw null;
                }
                throw null;
            }
            view.removeOnLayoutChangeListener(this);
            return;
        }
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0;
        if (navigationBarItemView.icon.getVisibility() == 0) {
            ImageView imageView = navigationBarItemView.icon;
            if (navigationBarItemView.hasBadge()) {
                FileUtils.setBadgeDrawableBounds$ar$ds(navigationBarItemView.badgeDrawable, imageView);
            }
        }
        if (navigationBarItemView.itemIconGravity == 1) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) navigationBarItemView.innerContentContainer.getLayoutParams();
            int i10 = (i3 - i) + layoutParams.rightMargin + layoutParams.leftMargin;
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) navigationBarItemView.activeIndicatorView.getLayoutParams();
            int i11 = navigationBarItemView.activeIndicatorDesiredWidth;
            int measuredWidth = navigationBarItemView.getMeasuredWidth();
            int i12 = navigationBarItemView.activeIndicatorMarginHorizontal;
            layoutParams2.width = Math.max(i10, Math.min(i11, measuredWidth - (i12 + i12)));
            navigationBarItemView.activeIndicatorView.setLayoutParams(layoutParams2);
        }
    }

    public NavigationBarItemView$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.NavigationBarItemView$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}
