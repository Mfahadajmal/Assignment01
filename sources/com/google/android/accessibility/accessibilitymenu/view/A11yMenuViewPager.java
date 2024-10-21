package com.google.android.accessibility.accessibilitymenu.view;

import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.viewpager.widget.ViewPager;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yMenuViewPager {
    protected A11yMenuFooter a11yMenuFooter;
    public ViewGroup a11yMenuLayout;
    public List a11yMenuShortcutList;
    public final AccessibilityMenuService service;
    public ViewPager viewPager;
    public ViewPagerAdapter viewPagerAdapter;
    public final List gridPageList = new ArrayList();
    protected final FloatingActionButton.ShadowDelegateImpl footerCallbacks$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);

    public A11yMenuViewPager(AccessibilityMenuService accessibilityMenuService) {
        this.service = accessibilityMenuService;
    }

    public final void goToPage(int i) {
        ViewPager viewPager = this.viewPager;
        if (viewPager != null && i >= 0 && i < viewPager.mAdapter.getCount()) {
            this.viewPager.setCurrentItem(i);
        }
    }

    public final void updateFooterState() {
        boolean z;
        ViewPager viewPager = this.viewPager;
        int i = viewPager.mCurItem;
        int count = viewPager.mAdapter.getCount() - 1;
        ImageButton imageButton = this.a11yMenuFooter.previousPageBtn;
        boolean z2 = true;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        imageButton.setEnabled(z);
        ImageButton imageButton2 = this.a11yMenuFooter.nextPageBtn;
        if (i >= count) {
            z2 = false;
        }
        imageButton2.setEnabled(z2);
    }
}
