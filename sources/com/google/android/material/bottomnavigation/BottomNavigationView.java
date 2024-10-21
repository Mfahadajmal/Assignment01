package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils$RelativePadding;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BottomNavigationView extends NavigationBarView {
    public BottomNavigationView(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    protected final NavigationBarMenuView createNavigationBarMenuView(Context context) {
        return new NavigationBarMenuView(context, null);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int suggestedMinimumHeight = getSuggestedMinimumHeight();
        if (View.MeasureSpec.getMode(i2) != 1073741824 && suggestedMinimumHeight > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.max(View.MeasureSpec.getSize(i2), suggestedMinimumHeight + getPaddingTop() + getPaddingBottom()), Integer.MIN_VALUE);
        } else {
            i3 = i2;
        }
        super.onMeasure(i, i3);
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            setMeasuredDimension(getMeasuredWidth(), Math.max(getMeasuredHeight(), getSuggestedMinimumHeight() + getPaddingTop() + getPaddingBottom()));
        }
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, R.style.Widget_Design_BottomNavigationView);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        ExecutionList.RunnableExecutorPair obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging = ThemeEnforcement.obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging(getContext(), attributeSet, R$styleable.BottomNavigationView, i, i2, new int[0]);
        boolean z = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getBoolean(2, true);
        NavigationBarMenuView navigationBarMenuView = this.menuView;
        if (navigationBarMenuView.itemHorizontalTranslationEnabled != z) {
            navigationBarMenuView.itemHorizontalTranslationEnabled = z;
            this.presenter.updateMenuView(false);
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(0)) {
            setMinimumHeight(obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(0, 0));
        }
        obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getBoolean(1, true);
        obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.recycle();
        DrawableUtils$OutlineCompatL.doOnApplyWindowInsets(this, new ViewUtils$OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.1
            @Override // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
            public final void onApplyWindowInsets$ar$ds(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils$RelativePadding viewUtils$RelativePadding) {
                int i3;
                viewUtils$RelativePadding.bottom += windowInsetsCompat.getSystemWindowInsetBottom();
                int layoutDirection = view.getLayoutDirection();
                int systemWindowInsetLeft = windowInsetsCompat.getSystemWindowInsetLeft();
                int systemWindowInsetRight = windowInsetsCompat.getSystemWindowInsetRight();
                if (layoutDirection == 1) {
                    i3 = systemWindowInsetRight;
                } else {
                    i3 = systemWindowInsetLeft;
                }
                int i4 = viewUtils$RelativePadding.start + i3;
                viewUtils$RelativePadding.start = i4;
                if (layoutDirection != 1) {
                    systemWindowInsetLeft = systemWindowInsetRight;
                }
                int i5 = viewUtils$RelativePadding.end + systemWindowInsetLeft;
                viewUtils$RelativePadding.end = i5;
                view.setPaddingRelative(i4, viewUtils$RelativePadding.top, i5, viewUtils$RelativePadding.bottom);
            }
        });
    }
}
