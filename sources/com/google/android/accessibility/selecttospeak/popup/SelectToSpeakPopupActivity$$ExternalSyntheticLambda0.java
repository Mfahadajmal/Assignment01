package com.google.android.accessibility.selecttospeak.popup;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.appbar.AppBarLayout;
import j$.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SelectToSpeakPopupActivity$$ExternalSyntheticLambda0 implements OnApplyWindowInsetsListener {
    public final /* synthetic */ ViewGroup f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SelectToSpeakPopupActivity$$ExternalSyntheticLambda0(ViewGroup viewGroup, int i) {
        this.switching_field = i;
        this.f$0 = viewGroup;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
        boolean z;
        WindowInsetsCompat windowInsetsCompat2;
        int i = this.switching_field;
        if (i != 0) {
            boolean z2 = true;
            if (i != 1) {
                AppBarLayout appBarLayout = (AppBarLayout) this.f$0;
                if (true != appBarLayout.getFitsSystemWindows()) {
                    windowInsetsCompat2 = null;
                } else {
                    windowInsetsCompat2 = windowInsetsCompat;
                }
                if (!Objects.equals(appBarLayout.lastInsets, windowInsetsCompat2)) {
                    appBarLayout.lastInsets = windowInsetsCompat2;
                    appBarLayout.updateWillNotDraw();
                    appBarLayout.requestLayout();
                }
                return windowInsetsCompat;
            }
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.f$0;
            if (!Objects.equals(coordinatorLayout.mLastInsets, windowInsetsCompat)) {
                coordinatorLayout.mLastInsets = windowInsetsCompat;
                if (windowInsetsCompat.getSystemWindowInsetTop() > 0) {
                    z = true;
                } else {
                    z = false;
                }
                coordinatorLayout.mDrawStatusBarBackground = z;
                if (z || coordinatorLayout.getBackground() != null) {
                    z2 = false;
                }
                coordinatorLayout.setWillNotDraw(z2);
                if (!windowInsetsCompat.isConsumed()) {
                    int childCount = coordinatorLayout.getChildCount();
                    for (int i2 = 0; i2 < childCount; i2++) {
                        View childAt = coordinatorLayout.getChildAt(i2);
                        int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                        if (childAt.getFitsSystemWindows() && ((CoordinatorLayout.LayoutParams) childAt.getLayoutParams()).mBehavior != null && windowInsetsCompat.isConsumed()) {
                            break;
                        }
                    }
                }
                coordinatorLayout.requestLayout();
            }
            return windowInsetsCompat;
        }
        view.getClass();
        Insets insets = windowInsetsCompat.getInsets(7);
        ViewGroup viewGroup = this.f$0;
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        layoutParams.getClass();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginLayoutParams.setMargins(0, 0, 0, insets.bottom);
        viewGroup.setLayoutParams(marginLayoutParams);
        return WindowInsetsCompat.CONSUMED;
    }

    public SelectToSpeakPopupActivity$$ExternalSyntheticLambda0(ViewGroup viewGroup, int i, byte[] bArr) {
        this.switching_field = i;
        this.f$0 = viewGroup;
    }
}
