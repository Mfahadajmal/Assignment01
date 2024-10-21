package com.google.android.material.navigation;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.util.Pools$SimplePool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.inputmethod.EditorInfoCompat;
import androidx.transition.TransitionSet;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.TextScale;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    public final SparseArray badgeDrawables;
    public NavigationBarItemView[] buttons;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    public ColorStateList itemActiveIndicatorColor;
    public boolean itemActiveIndicatorEnabled;
    public int itemActiveIndicatorHeight;
    public int itemActiveIndicatorLabelPadding;
    public int itemActiveIndicatorMarginHorizontal;
    public ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    public int itemActiveIndicatorWidth;
    public int itemBackgroundRes;
    public boolean itemHorizontalTranslationEnabled;
    public int itemIconGravity;
    public int itemIconSize;
    private ColorStateList itemIconTint;
    public int itemPaddingBottom;
    public int itemPaddingTop;
    private final Pools$SimplePool itemPool$ar$class_merging;
    public ColorStateList itemRippleColor;
    public int itemTextAppearanceActive;
    public boolean itemTextAppearanceActiveBoldEnabled;
    public int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    public ColorStateList itemTextColorFromUser;
    public int labelVisibilityMode;
    public boolean measurePaddingFromLabelBaseline;
    public MenuBuilder menu;
    private final View.OnClickListener onClickListener;
    private final SparseArray onTouchListeners;
    public NavigationBarPresenter presenter;
    public int selectedItemId;
    public int selectedItemPosition;
    public final TransitionSet set;
    private final List tempChildWidths;

    public NavigationBarMenuView(Context context, byte[] bArr) {
        this(context);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources.getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_bottom_navigation_active_item_min_width);
    }

    protected static NavigationBarItemView createNavigationBarItemView$ar$ds(Context context) {
        return new BottomNavigationItemView(context);
    }

    public final void buildMenuView() {
        BadgeDrawable badgeDrawable;
        removeAllViews();
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    this.itemPool$ar$class_merging.release(navigationBarItemView);
                    navigationBarItemView.tryRemoveBadgeFromAnchor(navigationBarItemView.icon);
                    navigationBarItemView.itemData = null;
                    navigationBarItemView.activeIndicatorProgress = 0.0f;
                    navigationBarItemView.initialized = false;
                }
            }
        }
        if (this.menu.size() != 0) {
            HashSet hashSet = new HashSet();
            for (int i = 0; i < this.menu.size(); i++) {
                hashSet.add(Integer.valueOf(this.menu.getItem(i).getItemId()));
            }
            for (int i2 = 0; i2 < this.badgeDrawables.size(); i2++) {
                int keyAt = this.badgeDrawables.keyAt(i2);
                if (!hashSet.contains(Integer.valueOf(keyAt))) {
                    this.badgeDrawables.delete(keyAt);
                }
            }
            this.buttons = new NavigationBarItemView[this.menu.size()];
            boolean isShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
            for (int i3 = 0; i3 < this.menu.size(); i3++) {
                this.presenter.updateSuspended = true;
                this.menu.getItem(i3).setCheckable(true);
                this.presenter.updateSuspended = false;
                NavigationBarItemView navigationBarItemView2 = (NavigationBarItemView) this.itemPool$ar$class_merging.acquire();
                if (navigationBarItemView2 == null) {
                    navigationBarItemView2 = createNavigationBarItemView$ar$ds(getContext());
                }
                this.buttons[i3] = navigationBarItemView2;
                navigationBarItemView2.setIconTintList(this.itemIconTint);
                navigationBarItemView2.setIconSize(this.itemIconSize);
                navigationBarItemView2.setTextColor(this.itemTextColorDefault);
                navigationBarItemView2.setTextAppearanceInactive(this.itemTextAppearanceInactive);
                navigationBarItemView2.setTextAppearanceActive(this.itemTextAppearanceActive);
                navigationBarItemView2.setTextAppearanceActiveBoldEnabled(this.itemTextAppearanceActiveBoldEnabled);
                navigationBarItemView2.setTextColor(this.itemTextColorFromUser);
                int i4 = this.itemPaddingTop;
                if (i4 != -1) {
                    navigationBarItemView2.setItemPaddingTop(i4);
                }
                int i5 = this.itemPaddingBottom;
                if (i5 != -1) {
                    navigationBarItemView2.setItemPaddingBottom(i5);
                }
                navigationBarItemView2.setMeasureBottomPaddingFromLabelBaseline(this.measurePaddingFromLabelBaseline);
                int i6 = this.itemActiveIndicatorLabelPadding;
                if (i6 != -1) {
                    navigationBarItemView2.setActiveIndicatorLabelPadding(i6);
                }
                navigationBarItemView2.setActiveIndicatorWidth(this.itemActiveIndicatorWidth);
                navigationBarItemView2.setActiveIndicatorHeight(this.itemActiveIndicatorHeight);
                navigationBarItemView2.setActiveIndicatorMarginHorizontal(this.itemActiveIndicatorMarginHorizontal);
                navigationBarItemView2.setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
                navigationBarItemView2.setActiveIndicatorEnabled(this.itemActiveIndicatorEnabled);
                navigationBarItemView2.setItemBackground(this.itemBackgroundRes);
                navigationBarItemView2.setItemRippleColor(this.itemRippleColor);
                navigationBarItemView2.setShifting(isShifting);
                navigationBarItemView2.setLabelVisibilityMode(this.labelVisibilityMode);
                navigationBarItemView2.setItemIconGravity(this.itemIconGravity);
                MenuItemImpl menuItemImpl = (MenuItemImpl) this.menu.getItem(i3);
                navigationBarItemView2.initialize$ar$ds(menuItemImpl);
                int i7 = menuItemImpl.mId;
                navigationBarItemView2.setOnTouchListener((View.OnTouchListener) this.onTouchListeners.get(i7));
                navigationBarItemView2.setOnClickListener(this.onClickListener);
                int i8 = this.selectedItemId;
                if (i8 != 0 && i7 == i8) {
                    this.selectedItemPosition = i3;
                }
                int id = navigationBarItemView2.getId();
                if (id != -1 && (badgeDrawable = (BadgeDrawable) this.badgeDrawables.get(id)) != null) {
                    navigationBarItemView2.setBadge(badgeDrawable);
                }
                addView(navigationBarItemView2);
            }
            int min = Math.min(this.menu.size() - 1, this.selectedItemPosition);
            this.selectedItemPosition = min;
            this.menu.getItem(min).setChecked(true);
            return;
        }
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.buttons = null;
    }

    public final ColorStateList createDefaultColorStateList$ar$ds() {
        TypedValue typedValue = new TypedValue();
        if (getContext().getTheme().resolveAttribute(R.attr.textColorSecondary, typedValue, true)) {
            ColorStateList colorStateList = EditorInfoCompat.getColorStateList(getContext(), typedValue.resourceId);
            if (getContext().getTheme().resolveAttribute(com.google.android.marvin.talkback.R.attr.colorPrimary, typedValue, true)) {
                int i = typedValue.data;
                int defaultColor = colorStateList.getDefaultColor();
                int[] iArr = DISABLED_STATE_SET;
                return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i, defaultColor});
            }
            return null;
        }
        return null;
    }

    public final Drawable createItemActiveIndicatorDrawable() {
        if (this.itemActiveIndicatorShapeAppearance != null && this.itemActiveIndicatorColor != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
            materialShapeDrawable.setFillColor(this.itemActiveIndicatorColor);
            return materialShapeDrawable;
        }
        return null;
    }

    @Override // android.support.v7.view.menu.MenuView
    public final void initialize(MenuBuilder menuBuilder) {
        this.menu = menuBuilder;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isShifting(int i, int i2) {
        if (i == -1) {
            if (i2 > 3) {
                return true;
            }
            return false;
        }
        if (i == 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        new AccessibilityNodeInfoCompat(accessibilityNodeInfo).setCollectionInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$ar$class_merging(1, this.menu.getVisibleItems().size(), 1));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int i7 = i4 - i2;
                if (getLayoutDirection() == 1) {
                    int i8 = (i3 - i) - i5;
                    childAt.layout(i8 - childAt.getMeasuredWidth(), 0, i8, i7);
                } else {
                    childAt.layout(i5, 0, childAt.getMeasuredWidth() + i5, i7);
                }
                i5 += childAt.getMeasuredWidth();
            }
        }
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        MenuBuilder menuBuilder = this.menu;
        int size = View.MeasureSpec.getSize(i);
        int size2 = menuBuilder.getVisibleItems().size();
        int childCount = getChildCount();
        this.tempChildWidths.clear();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), Integer.MIN_VALUE);
        int i8 = 1;
        int i9 = 0;
        if (this.itemIconGravity == 0) {
            if (isShifting(this.labelVisibilityMode, size2) && this.itemHorizontalTranslationEnabled) {
                View childAt = getChildAt(this.selectedItemPosition);
                int i10 = this.activeItemMinWidth;
                if (childAt.getVisibility() != 8) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), makeMeasureSpec);
                    i10 = Math.max(i10, childAt.getMeasuredWidth());
                }
                if (childAt.getVisibility() != 8) {
                    i6 = 1;
                } else {
                    i6 = 0;
                }
                int i11 = size2 - i6;
                int min = Math.min(size - (this.inactiveItemMinWidth * i11), Math.min(i10, this.activeItemMaxWidth));
                int i12 = size - min;
                if (i11 != 0) {
                    i8 = i11;
                }
                int min2 = Math.min(i12 / i8, this.inactiveItemMaxWidth);
                int i13 = i12 - (i11 * min2);
                for (int i14 = 0; i14 < childCount; i14++) {
                    if (getChildAt(i14).getVisibility() != 8) {
                        if (i14 == this.selectedItemPosition) {
                            i7 = min;
                        } else {
                            i7 = min2;
                        }
                        if (i13 > 0) {
                            i7++;
                            i13--;
                        }
                    } else {
                        i7 = 0;
                    }
                    this.tempChildWidths.add(Integer.valueOf(i7));
                }
            } else {
                if (size2 != 0) {
                    i8 = size2;
                }
                int min3 = Math.min(size / i8, this.activeItemMaxWidth);
                int i15 = size - (size2 * min3);
                for (int i16 = 0; i16 < childCount; i16++) {
                    if (getChildAt(i16).getVisibility() != 8) {
                        if (i15 > 0) {
                            i5 = min3 + 1;
                            i15--;
                        } else {
                            i5 = min3;
                        }
                    } else {
                        i5 = 0;
                    }
                    this.tempChildWidths.add(Integer.valueOf(i5));
                }
            }
            i3 = 0;
            i4 = 0;
            while (i9 < childCount) {
                View childAt2 = getChildAt(i9);
                if (childAt2.getVisibility() != 8) {
                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(((Integer) this.tempChildWidths.get(i9)).intValue(), 1073741824), makeMeasureSpec);
                    childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                    i3 += childAt2.getMeasuredWidth();
                    i4 = Math.max(i4, childAt2.getMeasuredHeight());
                }
                i9++;
            }
        } else {
            if (size2 == 0) {
                size2 = 1;
            }
            float f = size;
            float min4 = Math.min((size2 + 3) / 10.0f, 0.9f) * f;
            float f2 = size2;
            float f3 = f / f2;
            int round = Math.round(min4 / f2);
            int round2 = Math.round(f3);
            int i17 = 0;
            int i18 = 0;
            while (i9 < childCount) {
                View childAt3 = getChildAt(i9);
                if (childAt3.getVisibility() != 8) {
                    childAt3.measure(View.MeasureSpec.makeMeasureSpec(round2, Integer.MIN_VALUE), makeMeasureSpec);
                    if (childAt3.getMeasuredWidth() < round) {
                        childAt3.measure(View.MeasureSpec.makeMeasureSpec(round, 1073741824), makeMeasureSpec);
                    }
                    i17 += childAt3.getMeasuredWidth();
                    i18 = Math.max(i18, childAt3.getMeasuredHeight());
                }
                i9++;
            }
            i3 = i17;
            i4 = i18;
        }
        setMeasuredDimension(i3, Math.max(i4, getSuggestedMinimumHeight()));
    }

    public final void setIconTintList(ColorStateList colorStateList) {
        this.itemIconTint = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
    }

    public NavigationBarMenuView(Context context) {
        super(context);
        this.itemPool$ar$class_merging = new Pools$SynchronizedPool(5);
        this.onTouchListeners = new SparseArray(5);
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.badgeDrawables = new SparseArray(5);
        this.itemPaddingTop = -1;
        this.itemPaddingBottom = -1;
        this.itemActiveIndicatorLabelPadding = -1;
        this.itemTextColorDefault = createDefaultColorStateList$ar$ds();
        if (isInEditMode()) {
            this.set = null;
        } else {
            TransitionSet transitionSet = new TransitionSet(null);
            this.set = transitionSet;
            transitionSet.setOrdering$ar$ds(0);
            transitionSet.setDuration$ar$ds$b4560d67_0(DrawableUtils$OutlineCompatR.resolveInteger(getContext(), com.google.android.marvin.talkback.R.attr.motionDurationMedium4, getResources().getInteger(com.google.android.marvin.talkback.R.integer.material_motion_duration_long_1)));
            transitionSet.setInterpolator$ar$ds$b0a8efd3_0(DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), com.google.android.marvin.talkback.R.attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            transitionSet.addTransition$ar$ds(new TextScale());
        }
        this.onClickListener = new TrainingActivity$$ExternalSyntheticLambda0(this, 14, null);
        setImportantForAccessibility(1);
    }
}
