package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.preference.Preference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    public MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    FloatingActionButton.ShadowDelegateImpl mOnMenuItemClickListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private Context mPopupContext;
    private int mPopupTheme;
    public ActionMenuPresenter mPresenter;
    public boolean mReserveOverflow;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends LinearLayoutCompat.LayoutParams {

        @ViewDebug.ExportedProperty
        public int cellsUsed;

        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;

        @ViewDebug.ExportedProperty
        public int extraPixels;

        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;

        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams() {
            super(-2);
            this.isOverflowButton = false;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public static final LayoutParams generateDefaultLayoutParams$ar$ds() {
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.gravity = 16;
        return layoutParams;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    protected final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public final void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus$ar$ds();
        }
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams$ar$ds();
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public final LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public final Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.setCallback(new Toolbar.AnonymousClass3(this, 1));
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.setReserveOverflow$ar$ds();
            this.mPresenter.mCallback = new ActionMenuPresenterCallback();
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    protected final boolean hasSupportDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i - 1);
        KeyEvent.Callback childAt2 = getChildAt(i);
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        if (i > 0 && (childAt2 instanceof ActionMenuChildView)) {
            return ((ActionMenuChildView) childAt2).needsDividerBefore() | z;
        }
        return z;
    }

    @Override // android.support.v7.view.menu.MenuView
    public final void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // android.support.v7.view.menu.MenuBuilder.ItemInvoker
    public final boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    public final boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing()) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int width;
        int i7;
        if (!this.mFormatItems) {
            super.onLayout(z, i, i2, i3, i4);
            return;
        }
        int childCount = getChildCount();
        int i8 = i4 - i2;
        int i9 = this.mDividerWidth;
        int i10 = i3 - i;
        int paddingRight = (i10 - getPaddingRight()) - getPaddingLeft();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (true) {
            i5 = i8 / 2;
            if (i11 >= childCount) {
                break;
            }
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(i11)) {
                        measuredWidth += i9;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (isLayoutRtl) {
                        i7 = getPaddingLeft() + layoutParams.leftMargin;
                        width = i7 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        i7 = width - measuredWidth;
                    }
                    int i14 = i5 - (measuredHeight / 2);
                    childAt.layout(i7, i14, width, measuredHeight + i14);
                    paddingRight -= measuredWidth;
                    i12 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                    hasSupportDividerBeforeChildAt(i11);
                    i13++;
                }
            }
            i11++;
        }
        if (childCount == 1) {
            if (i12 != 0) {
                childCount = 1;
            } else {
                View childAt2 = getChildAt(0);
                int measuredWidth2 = childAt2.getMeasuredWidth();
                int measuredHeight2 = childAt2.getMeasuredHeight();
                int i15 = i5 - (measuredHeight2 / 2);
                int i16 = (i10 / 2) - (measuredWidth2 / 2);
                childAt2.layout(i16, i15, measuredWidth2 + i16, measuredHeight2 + i15);
                return;
            }
        }
        int i17 = i13 - (i12 ^ 1);
        if (i17 > 0) {
            i6 = paddingRight / i17;
        } else {
            i6 = 0;
        }
        int max = Math.max(0, i6);
        if (isLayoutRtl) {
            int width2 = getWidth() - getPaddingRight();
            for (int i18 = 0; i18 < childCount; i18++) {
                View childAt3 = getChildAt(i18);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int i19 = width2 - layoutParams2.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i20 = i5 - (measuredHeight3 / 2);
                    childAt3.layout(i19 - measuredWidth3, i20, i19, measuredHeight3 + i20);
                    width2 = i19 - ((measuredWidth3 + layoutParams2.leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int i21 = 0; i21 < childCount; i21++) {
            View childAt4 = getChildAt(i21);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int i22 = paddingLeft + layoutParams3.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i23 = i5 - (measuredHeight4 / 2);
                childAt4.layout(i22, i23, i22 + measuredWidth4, measuredHeight4 + i23);
                paddingLeft = i22 + measuredWidth4 + layoutParams3.rightMargin + max;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r6v23 */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    public final void onMeasure(int i, int i2) {
        boolean z;
        boolean z2;
        int i3;
        boolean z3;
        boolean z4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        ?? r6;
        boolean z5;
        boolean z6;
        int i9;
        int i10;
        int i11;
        ActionMenuItemView actionMenuItemView;
        boolean z7;
        int i12;
        boolean z8;
        MenuBuilder menuBuilder;
        boolean z9 = this.mFormatItems;
        if (View.MeasureSpec.getMode(i) == 1073741824) {
            z = true;
        } else {
            z = false;
        }
        this.mFormatItems = z;
        if (z9 != z) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(i);
        if (this.mFormatItems && (menuBuilder = this.mMenu) != null && size != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = size;
            menuBuilder.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.mFormatItems && childCount > 0) {
            int mode = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i);
            int size3 = View.MeasureSpec.getSize(i2);
            int paddingLeft = getPaddingLeft() + getPaddingRight();
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int childMeasureSpec = getChildMeasureSpec(i2, paddingTop, -2);
            int i13 = size2 - paddingLeft;
            int i14 = this.mMinCellSize;
            int i15 = i13 / i14;
            int i16 = i13 % i14;
            if (i15 == 0) {
                setMeasuredDimension(i13, 0);
                return;
            }
            int i17 = i14 + (i16 / i15);
            int childCount2 = getChildCount();
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            boolean z10 = false;
            int i21 = 0;
            int i22 = 0;
            long j = 0;
            while (i20 < childCount2) {
                View childAt = getChildAt(i20);
                if (childAt.getVisibility() == 8) {
                    i10 = i13;
                    i8 = size3;
                    i11 = paddingTop;
                } else {
                    boolean z11 = childAt instanceof ActionMenuItemView;
                    int i23 = i18 + 1;
                    if (z11) {
                        int i24 = this.mGeneratedItemPadding;
                        i8 = size3;
                        r6 = 0;
                        childAt.setPadding(i24, 0, i24, 0);
                        z5 = true;
                    } else {
                        i8 = size3;
                        r6 = 0;
                        z5 = false;
                    }
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    layoutParams.expanded = r6;
                    layoutParams.extraPixels = r6;
                    layoutParams.cellsUsed = r6;
                    layoutParams.expandable = r6;
                    layoutParams.leftMargin = r6;
                    layoutParams.rightMargin = r6;
                    if (z5 && ((ActionMenuItemView) childAt).hasText()) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    layoutParams.preventEdgeOffset = z6;
                    if (true != layoutParams.isOverflowButton) {
                        i9 = i15;
                    } else {
                        i9 = 1;
                    }
                    LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                    i10 = i13;
                    i11 = paddingTop;
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingTop, View.MeasureSpec.getMode(childMeasureSpec));
                    if (z11) {
                        actionMenuItemView = (ActionMenuItemView) childAt;
                    } else {
                        actionMenuItemView = null;
                    }
                    if (actionMenuItemView != null && actionMenuItemView.hasText()) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    if (i9 > 0 && (!z7 || i9 >= 2)) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(i9 * i17, Integer.MIN_VALUE), makeMeasureSpec);
                        int measuredWidth = childAt.getMeasuredWidth();
                        i12 = measuredWidth / i17;
                        if (measuredWidth % i17 != 0) {
                            i12++;
                        }
                        if (z7 && i12 < 2) {
                            i12 = 2;
                        }
                    } else {
                        i12 = 0;
                    }
                    if (!layoutParams2.isOverflowButton && z7) {
                        z8 = true;
                    } else {
                        z8 = false;
                    }
                    layoutParams2.expandable = z8;
                    layoutParams2.cellsUsed = i12;
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i12 * i17, 1073741824), makeMeasureSpec);
                    i21 = Math.max(i21, i12);
                    if (layoutParams.expandable) {
                        i19++;
                    }
                    z10 |= layoutParams.isOverflowButton;
                    i15 -= i12;
                    i22 = Math.max(i22, childAt.getMeasuredHeight());
                    if (i12 == 1) {
                        j |= 1 << i20;
                    }
                    i18 = i23;
                }
                i20++;
                size3 = i8;
                paddingTop = i11;
                i13 = i10;
            }
            int i25 = i13;
            int i26 = size3;
            int i27 = i21;
            int i28 = i22;
            if (z10 && i18 == 2) {
                z2 = true;
                i18 = 2;
            } else {
                z2 = false;
            }
            boolean z12 = false;
            while (i19 > 0 && i15 > 0) {
                int i29 = Preference.DEFAULT_ORDER;
                int i30 = 0;
                int i31 = 0;
                long j2 = 0;
                while (i31 < childCount2) {
                    int i32 = i28;
                    LayoutParams layoutParams3 = (LayoutParams) getChildAt(i31).getLayoutParams();
                    boolean z13 = z12;
                    if (layoutParams3.expandable) {
                        int i33 = layoutParams3.cellsUsed;
                        if (i33 < i29) {
                            j2 = 1 << i31;
                            i30 = 1;
                            i29 = i33;
                        } else if (i33 == i29) {
                            j2 |= 1 << i31;
                            i30++;
                        }
                    }
                    i31++;
                    z12 = z13;
                    i28 = i32;
                }
                i3 = i28;
                z3 = z12;
                j |= j2;
                if (i30 > i15) {
                    break;
                }
                int i34 = i29 + 1;
                int i35 = 0;
                while (i35 < childCount2) {
                    View childAt2 = getChildAt(i35);
                    LayoutParams layoutParams4 = (LayoutParams) childAt2.getLayoutParams();
                    int i36 = i19;
                    long j3 = 1 << i35;
                    if ((j2 & j3) == 0) {
                        if (layoutParams4.cellsUsed == i34) {
                            j |= j3;
                        }
                    } else {
                        if (z2 && layoutParams4.preventEdgeOffset && i15 == 1) {
                            int i37 = this.mGeneratedItemPadding;
                            childAt2.setPadding(i37 + i17, 0, i37, 0);
                            i15 = 1;
                        }
                        layoutParams4.cellsUsed++;
                        layoutParams4.expanded = true;
                        i15--;
                    }
                    i35++;
                    i19 = i36;
                }
                z12 = true;
                i28 = i3;
                i19 = i19;
            }
            i3 = i28;
            z3 = z12;
            if (!z10 && i18 == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (i15 > 0 && j != 0 && (i15 < i18 - 1 || z4 || i27 > 1)) {
                float bitCount = Long.bitCount(j);
                if (!z4) {
                    int i38 = childCount2 - 1;
                    if ((j & 1) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                        bitCount -= 0.5f;
                    }
                    if ((j & (1 << i38)) != 0 && !((LayoutParams) getChildAt(i38).getLayoutParams()).preventEdgeOffset) {
                        bitCount -= 0.5f;
                    }
                }
                if (bitCount > 0.0f) {
                    i6 = (int) ((i15 * i17) / bitCount);
                } else {
                    i6 = 0;
                }
                int i39 = 0;
                while (i39 < childCount2) {
                    if ((j & (1 << i39)) != 0) {
                        View childAt3 = getChildAt(i39);
                        LayoutParams layoutParams5 = (LayoutParams) childAt3.getLayoutParams();
                        if (childAt3 instanceof ActionMenuItemView) {
                            layoutParams5.extraPixels = i6;
                            layoutParams5.expanded = true;
                            if (i39 == 0) {
                                if (!layoutParams5.preventEdgeOffset) {
                                    layoutParams5.leftMargin = (-i6) / 2;
                                }
                                i39 = 0;
                            }
                        } else if (layoutParams5.isOverflowButton) {
                            layoutParams5.extraPixels = i6;
                            layoutParams5.expanded = true;
                            layoutParams5.rightMargin = (-i6) / 2;
                        } else {
                            int i40 = childCount2 - 1;
                            if (i39 != 0) {
                                layoutParams5.leftMargin = i6 / 2;
                            }
                            if (i39 != i40) {
                                layoutParams5.rightMargin = i6 / 2;
                            }
                        }
                        i7 = 1;
                        z3 = true;
                        i39 += i7;
                    }
                    i7 = 1;
                    i39 += i7;
                }
            }
            if (z3) {
                for (int i41 = 0; i41 < childCount2; i41++) {
                    View childAt4 = getChildAt(i41);
                    LayoutParams layoutParams6 = (LayoutParams) childAt4.getLayoutParams();
                    if (layoutParams6.expanded) {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams6.cellsUsed * i17) + layoutParams6.extraPixels, 1073741824), childMeasureSpec);
                    }
                }
            }
            if (mode == 1073741824) {
                i5 = i26;
                i4 = i25;
            } else {
                i4 = i25;
                i5 = i3;
            }
            setMeasuredDimension(i4, i5);
            return;
        }
        for (int i42 = 0; i42 < childCount; i42++) {
            LayoutParams layoutParams7 = (LayoutParams) getChildAt(i42).getLayoutParams();
            layoutParams7.rightMargin = 0;
            layoutParams7.leftMargin = 0;
        }
        super.onMeasure(i, i2);
    }

    public final void setPopupTheme(int i) {
        if (this.mPopupTheme != i) {
            this.mPopupTheme = i;
            if (i == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public final void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        actionMenuPresenter.setMenuView(this);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned$ar$ds();
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    protected final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return generateDefaultLayoutParams$ar$ds();
    }

    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup
    public final LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null) {
            LayoutParams layoutParams2 = layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : new LayoutParams(layoutParams);
            if (layoutParams2.gravity <= 0) {
                layoutParams2.gravity = 16;
            }
            return layoutParams2;
        }
        return generateDefaultLayoutParams$ar$ds();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public final boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }

        @Override // android.support.v7.view.menu.MenuPresenter.Callback
        public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }
    }
}
