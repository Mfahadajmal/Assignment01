package com.google.android.accessibility.accessibilitymenu.view;

import _COROUTINE._BOUNDARY;
import android.database.DataSetObserver;
import android.graphics.Insets;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService;
import com.google.android.accessibility.accessibilitymenu.PrimesController;
import com.google.android.accessibility.accessibilitymenu.activity.A11yMenuSettingsActivity;
import com.google.android.accessibility.accessibilitymenu.model.A11yMenuShortcut;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yMenuOverlayLayout {
    public A11yMenuViewPager a11yMenuViewPager;
    public ViewGroup layout;
    public WindowManager.LayoutParams layoutParameter;
    private final PrimesController primesController;
    private final AccessibilityMenuService service;
    public final WindowManager windowManager;
    private static final int[] SHORTCUT_LIST_DEFAULT = {1, 2, 3, 12, 11, 4, 10, 9, 5, 6, 7, 8};
    private static final int[] LARGE_SHORTCUT_LIST_DEFAULT = {1, 2, 3, 4, 12, 11, 10, 9, 5, 6, 7, 8};

    public A11yMenuOverlayLayout(AccessibilityMenuService accessibilityMenuService, PrimesController primesController) {
        this.service = accessibilityMenuService;
        this.primesController = primesController;
        this.windowManager = (WindowManager) accessibilityMenuService.getSystemService("window");
        configureLayout$ar$ds();
    }

    public final View configureLayout(int i) {
        int i2;
        int i3;
        int i4;
        this.primesController.startTimer(PrimesController.Timer.CONFIG_LAYOUT);
        ViewGroup viewGroup = this.layout;
        if (viewGroup != null) {
            WindowManager windowManager = this.windowManager;
            i2 = viewGroup.getVisibility();
            windowManager.removeView(this.layout);
            this.layout = null;
        } else {
            i2 = 8;
        }
        if (this.layoutParameter == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.layoutParameter = layoutParams;
            layoutParams.type = 2032;
            this.layoutParameter.format = -3;
            this.layoutParameter.flags |= 32;
            this.layoutParameter.flags |= 262144;
            this.layoutParameter.setTitle(this.service.getString(R.string.accessibility_menu_service_name));
        }
        this.layout = new FrameLayout(this.service);
        updateLayoutPosition();
        ViewGroup viewGroup2 = this.layout;
        LayoutInflater.from(this.service).inflate(R.layout.paged_menu, viewGroup2);
        viewGroup2.setOnTouchListener(this.service);
        final A11yMenuViewPager a11yMenuViewPager = new A11yMenuViewPager(this.service);
        this.a11yMenuViewPager = a11yMenuViewPager;
        ViewGroup viewGroup3 = this.layout;
        ArrayList arrayList = new ArrayList();
        int i5 = 0;
        if (A11yMenuSettingsActivity.A11yMenuPreferenceFragment.isLargeButtonsEnabled(this.service)) {
            int[] iArr = LARGE_SHORTCUT_LIST_DEFAULT;
            for (int i6 = 0; i6 < 12; i6++) {
                arrayList.add(new A11yMenuShortcut(iArr[i6]));
            }
        } else {
            int[] iArr2 = SHORTCUT_LIST_DEFAULT;
            for (int i7 = 0; i7 < 12; i7++) {
                arrayList.add(new A11yMenuShortcut(iArr2[i7]));
            }
        }
        a11yMenuViewPager.a11yMenuLayout = viewGroup3;
        a11yMenuViewPager.a11yMenuShortcutList = arrayList;
        a11yMenuViewPager.viewPager = (ViewPager) a11yMenuViewPager.a11yMenuLayout.findViewById(R.id.view_pager);
        a11yMenuViewPager.viewPagerAdapter = new ViewPagerAdapter();
        a11yMenuViewPager.viewPager.setAdapter(a11yMenuViewPager.viewPagerAdapter);
        a11yMenuViewPager.viewPager.setOverScrollMode(2);
        a11yMenuViewPager.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.google.android.accessibility.accessibilitymenu.view.A11yMenuViewPager.1
            public AnonymousClass1() {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public final void onPageSelected$ar$ds() {
                A11yMenuViewPager.this.updateFooterState();
            }
        });
        List list = a11yMenuViewPager.a11yMenuShortcutList;
        if (list != null && !list.isEmpty()) {
            if (!a11yMenuViewPager.gridPageList.isEmpty()) {
                a11yMenuViewPager.gridPageList.clear();
            }
            int size = a11yMenuViewPager.a11yMenuShortcutList.size();
            while (i5 < size) {
                if (true != A11yMenuSettingsActivity.A11yMenuPreferenceFragment.isLargeButtonsEnabled(a11yMenuViewPager.service)) {
                    i3 = 9;
                } else {
                    i3 = 4;
                }
                int min = Math.min(i3 + i5, size);
                List subList = a11yMenuViewPager.a11yMenuShortcutList.subList(i5, min);
                GridView gridView = (GridView) LayoutInflater.from(a11yMenuViewPager.service).inflate(R.layout.grid_view, (ViewGroup) null).findViewById(R.id.gridview);
                A11yMenuAdapter a11yMenuAdapter = new A11yMenuAdapter(a11yMenuViewPager.service, subList);
                if (true != A11yMenuSettingsActivity.A11yMenuPreferenceFragment.isLargeButtonsEnabled(a11yMenuViewPager.service)) {
                    i4 = 3;
                } else {
                    i4 = 2;
                }
                gridView.setNumColumns(i4);
                gridView.setAdapter((ListAdapter) a11yMenuAdapter);
                a11yMenuViewPager.gridPageList.add(gridView);
                i5 = min;
            }
            ViewPagerAdapter viewPagerAdapter = a11yMenuViewPager.viewPagerAdapter;
            viewPagerAdapter.widgetList = a11yMenuViewPager.gridPageList;
            synchronized (viewPagerAdapter) {
                DataSetObserver dataSetObserver = viewPagerAdapter.mViewPagerObserver;
                if (dataSetObserver != null) {
                    dataSetObserver.onChanged();
                }
            }
            viewPagerAdapter.mObservable.notifyChanged();
        }
        a11yMenuViewPager.a11yMenuFooter = new A11yMenuFooter(viewGroup3, a11yMenuViewPager.footerCallbacks$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging);
        a11yMenuViewPager.updateFooterState();
        a11yMenuViewPager.a11yMenuLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.accessibility.accessibilitymenu.view.A11yMenuViewPager.2
            boolean isFirstTime = true;

            public AnonymousClass2() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                GridView gridView2;
                int i8;
                int i9;
                int i10;
                int i11;
                int i12;
                WindowMetrics currentWindowMetrics;
                Rect bounds;
                if (this.isFirstTime && !A11yMenuViewPager.this.gridPageList.isEmpty() && (gridView2 = (GridView) A11yMenuViewPager.this.gridPageList.get(0)) != null && gridView2.getChildAt(0) != null) {
                    this.isFirstTime = false;
                    int measuredHeight = gridView2.getChildAt(0).getMeasuredHeight();
                    A11yMenuViewPager a11yMenuViewPager2 = A11yMenuViewPager.this;
                    AccessibilityMenuService accessibilityMenuService = a11yMenuViewPager2.service;
                    boolean isLargeButtonsEnabled = A11yMenuSettingsActivity.A11yMenuPreferenceFragment.isLargeButtonsEnabled(a11yMenuViewPager2.service);
                    int dimension = (int) accessibilityMenuService.getResources().getDimension(R.dimen.a11ymenu_layout_margin);
                    int dimension2 = (int) a11yMenuViewPager2.service.getResources().getDimension(R.dimen.table_margin_top);
                    int i13 = a11yMenuViewPager2.service.getResources().getConfiguration().orientation;
                    int measuredHeight2 = a11yMenuViewPager2.viewPager.getMeasuredHeight();
                    if (true != isLargeButtonsEnabled) {
                        i8 = 3;
                    } else {
                        i8 = 2;
                    }
                    if (i13 == 1) {
                        measuredHeight2 = (measuredHeight * i8) + dimension + dimension2;
                    } else if (i13 == 2) {
                        int i14 = measuredHeight * i8;
                        DisplayMetrics displayMetrics = a11yMenuViewPager2.service.getResources().getDisplayMetrics();
                        float f = displayMetrics.densityDpi;
                        i9 = DisplayMetrics.DENSITY_DEVICE_STABLE;
                        View findViewById = a11yMenuViewPager2.a11yMenuLayout.findViewById(R.id.footerlayout);
                        findViewById.getLayoutParams().height = (int) (findViewById.getLayoutParams().height / (f / i9));
                        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
                            currentWindowMetrics = ((WindowManager) a11yMenuViewPager2.service.getSystemService("window")).getCurrentWindowMetrics();
                            Insets windowInsets = SpannableUtils$NonCopyableTextSpan.getWindowInsets(currentWindowMetrics);
                            bounds = currentWindowMetrics.getBounds();
                            i10 = bounds.height() - findViewById.getLayoutParams().height;
                            i11 = windowInsets.bottom;
                        } else {
                            i10 = displayMetrics.heightPixels;
                            i11 = findViewById.getLayoutParams().height;
                        }
                        measuredHeight2 = i10 - i11;
                        int i15 = (measuredHeight2 - dimension2) - dimension;
                        int i16 = i8 + 1;
                        Iterator it = a11yMenuViewPager2.gridPageList.iterator();
                        while (true) {
                            i12 = (i15 - i14) / i16;
                            if (!it.hasNext()) {
                                break;
                            } else {
                                ((GridView) it.next()).setVerticalSpacing(i12);
                            }
                        }
                        a11yMenuViewPager2.viewPager.setPadding(dimension, i12 + dimension2, dimension, dimension);
                    }
                    ViewGroup.LayoutParams layoutParams2 = a11yMenuViewPager2.viewPager.getLayoutParams();
                    layoutParams2.height = measuredHeight2;
                    a11yMenuViewPager2.viewPager.setLayoutParams(layoutParams2);
                }
            }
        });
        a11yMenuViewPager.goToPage(i);
        this.windowManager.addView(this.layout, this.layoutParameter);
        this.layout.setVisibility(i2);
        this.primesController.stopTimer(PrimesController.Timer.CONFIG_LAYOUT, null);
        return this.layout;
    }

    public final void configureLayout$ar$ds() {
        configureLayout(0);
    }

    public final boolean hideMenu() {
        if (this.layout.getVisibility() == 0) {
            this.layout.setVisibility(8);
            return true;
        }
        return false;
    }

    public final void showSnackBar(String str) {
        Snackbar.make$ar$ds(this.layout.findViewById(R.id.coordinatorLayout), str).show();
    }

    public final boolean updateLayoutByWindowInsetsIfNeeded() {
        WindowMetrics currentWindowMetrics;
        int i;
        int i2;
        int i3;
        Rect bounds;
        boolean z = false;
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            return false;
        }
        currentWindowMetrics = this.windowManager.getCurrentWindowMetrics();
        Insets windowInsets = SpannableUtils$NonCopyableTextSpan.getWindowInsets(currentWindowMetrics);
        i = windowInsets.left;
        i2 = windowInsets.right;
        int max = Math.max(i, i2);
        i3 = windowInsets.bottom;
        bounds = currentWindowMetrics.getBounds();
        if (this.layoutParameter.x != max || this.layoutParameter.y != i3) {
            this.layoutParameter.x = max;
            this.layoutParameter.y = i3;
            z = true;
        }
        int i4 = this.service.getResources().getConfiguration().orientation;
        if (this.layout.getHeight() != this.layoutParameter.height && i4 == 2) {
            this.layoutParameter.height = bounds.height() - i3;
            return true;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x002d, code lost:
    
        if (r0 != 3) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateLayoutPosition() {
        /*
            r6 = this;
            com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService r0 = r6.service
            java.lang.String r1 = "window"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.view.WindowManager r0 = (android.view.WindowManager) r0
            android.view.Display r0 = r0.getDefaultDisplay()
            com.google.android.accessibility.accessibilitymenu.AccessibilityMenuService r1 = r6.service
            android.content.res.Resources r1 = r1.getResources()
            android.content.res.Configuration r1 = r1.getConfiguration()
            int r1 = r1.orientation
            r2 = -2
            r3 = -1
            r4 = 2
            if (r1 != r4) goto L7e
            int r0 = r0.getRotation()
            r1 = 65536(0x10000, float:9.18355E-41)
            if (r0 == 0) goto L57
            r5 = 1
            if (r0 == r5) goto L30
            if (r0 == r4) goto L30
            r4 = 3
            if (r0 == r4) goto L57
            goto L94
        L30:
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r4 = 8388693(0x800055, float:1.1755063E-38)
            r0.gravity = r4
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.width = r2
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.height = r3
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            int r2 = r0.flags
            r2 = r2 | 256(0x100, float:3.59E-43)
            r0.flags = r2
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            int r2 = r0.flags
            r1 = r1 | r2
            r0.flags = r1
            android.view.ViewGroup r0 = r6.layout
            r1 = 2131231384(0x7f080298, float:1.8078847E38)
            r0.setBackgroundResource(r1)
            goto L94
        L57:
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r4 = 8388691(0x800053, float:1.175506E-38)
            r0.gravity = r4
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.width = r2
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.height = r3
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            int r2 = r0.flags
            r2 = r2 | 256(0x100, float:3.59E-43)
            r0.flags = r2
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            int r2 = r0.flags
            r1 = r1 | r2
            r0.flags = r1
            android.view.ViewGroup r0 = r6.layout
            r1 = 2131231383(0x7f080297, float:1.8078845E38)
            r0.setBackgroundResource(r1)
            goto L94
        L7e:
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r1 = 80
            r0.gravity = r1
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.width = r3
            android.view.WindowManager$LayoutParams r0 = r6.layoutParameter
            r0.height = r2
            android.view.ViewGroup r0 = r6.layout
            r1 = 2131231382(0x7f080296, float:1.8078843E38)
            r0.setBackgroundResource(r1)
        L94:
            r6.updateLayoutByWindowInsetsIfNeeded()
            boolean r0 = _COROUTINE._BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()
            if (r0 == 0) goto La7
            android.view.ViewGroup r0 = r6.layout
            com.google.android.accessibility.accessibilitymenu.view.A11yMenuOverlayLayout$$ExternalSyntheticLambda0 r1 = new com.google.android.accessibility.accessibilitymenu.view.A11yMenuOverlayLayout$$ExternalSyntheticLambda0
            r1.<init>()
            r0.setOnApplyWindowInsetsListener(r1)
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.accessibilitymenu.view.A11yMenuOverlayLayout.updateLayoutPosition():void");
    }
}
