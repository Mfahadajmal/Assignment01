package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.ViewParentCompat$Api21Impl;
import androidx.core.view.WindowCompat$Api30Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavAction;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import com.google.android.marvin.talkback.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.common.util.concurrent.ExecutionList;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public final NavigationBarMenu menu;
    private MenuInflater menuInflater;
    public final NavigationBarMenuView menuView;
    public final NavigationBarPresenter presenter;
    public FloatingActionButton.ShadowDelegateImpl selectedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public NavigationBarView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, i2), attributeSet, i);
        NavigationBarPresenter navigationBarPresenter = new NavigationBarPresenter();
        this.presenter = navigationBarPresenter;
        Context context2 = getContext();
        ExecutionList.RunnableExecutorPair obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging = ThemeEnforcement.obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging(context2, attributeSet, R$styleable.NavigationBarView, i, i2, 13, 11);
        NavigationBarMenu navigationBarMenu = new NavigationBarMenu(context2, getClass());
        this.menu = navigationBarMenu;
        NavigationBarMenuView createNavigationBarMenuView = createNavigationBarMenuView(context2);
        this.menuView = createNavigationBarMenuView;
        createNavigationBarMenuView.setMinimumHeight(getSuggestedMinimumHeight());
        navigationBarPresenter.menuView = createNavigationBarMenuView;
        navigationBarPresenter.id = 1;
        createNavigationBarMenuView.presenter = navigationBarPresenter;
        navigationBarMenu.addMenuPresenter(navigationBarPresenter);
        navigationBarPresenter.initForMenu(getContext(), navigationBarMenu);
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(7)) {
            createNavigationBarMenuView.setIconTintList(obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getColorStateList(7));
        } else {
            createNavigationBarMenuView.setIconTintList(createNavigationBarMenuView.createDefaultColorStateList$ar$ds());
        }
        int dimensionPixelSize = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(6, getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_bar_item_default_icon_size));
        createNavigationBarMenuView.itemIconSize = dimensionPixelSize;
        NavigationBarItemView[] navigationBarItemViewArr = createNavigationBarMenuView.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setIconSize(dimensionPixelSize);
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(13)) {
            int resourceId = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getResourceId(13, 0);
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            navigationBarMenuView.itemTextAppearanceInactive = resourceId;
            NavigationBarItemView[] navigationBarItemViewArr2 = navigationBarMenuView.buttons;
            if (navigationBarItemViewArr2 != null) {
                for (NavigationBarItemView navigationBarItemView2 : navigationBarItemViewArr2) {
                    navigationBarItemView2.setTextAppearanceInactive(resourceId);
                    ColorStateList colorStateList = navigationBarMenuView.itemTextColorFromUser;
                    if (colorStateList != null) {
                        navigationBarItemView2.setTextColor(colorStateList);
                    }
                }
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(11)) {
            int resourceId2 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getResourceId(11, 0);
            NavigationBarMenuView navigationBarMenuView2 = this.menuView;
            navigationBarMenuView2.itemTextAppearanceActive = resourceId2;
            NavigationBarItemView[] navigationBarItemViewArr3 = navigationBarMenuView2.buttons;
            if (navigationBarItemViewArr3 != null) {
                for (NavigationBarItemView navigationBarItemView3 : navigationBarItemViewArr3) {
                    navigationBarItemView3.setTextAppearanceActive(resourceId2);
                    ColorStateList colorStateList2 = navigationBarMenuView2.itemTextColorFromUser;
                    if (colorStateList2 != null) {
                        navigationBarItemView3.setTextColor(colorStateList2);
                    }
                }
            }
        }
        boolean z = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getBoolean(12, true);
        NavigationBarMenuView navigationBarMenuView3 = this.menuView;
        navigationBarMenuView3.itemTextAppearanceActiveBoldEnabled = z;
        NavigationBarItemView[] navigationBarItemViewArr4 = navigationBarMenuView3.buttons;
        if (navigationBarItemViewArr4 != null) {
            for (NavigationBarItemView navigationBarItemView4 : navigationBarItemViewArr4) {
                navigationBarItemView4.setTextAppearanceActiveBoldEnabled(z);
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(14)) {
            ColorStateList colorStateList3 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getColorStateList(14);
            NavigationBarMenuView navigationBarMenuView4 = this.menuView;
            navigationBarMenuView4.itemTextColorFromUser = colorStateList3;
            NavigationBarItemView[] navigationBarItemViewArr5 = navigationBarMenuView4.buttons;
            if (navigationBarItemViewArr5 != null) {
                for (NavigationBarItemView navigationBarItemView5 : navigationBarItemViewArr5) {
                    navigationBarItemView5.setTextColor(colorStateList3);
                }
            }
        }
        Drawable background = getBackground();
        ColorStateList colorStateListOrNull = DrawableUtils$OutlineCompatR.getColorStateListOrNull(background);
        if (background == null || colorStateListOrNull != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(new ShapeAppearanceModel(ShapeAppearanceModel.builder(context2, attributeSet, i, i2)));
            if (colorStateListOrNull != null) {
                materialShapeDrawable.setFillColor(colorStateListOrNull);
            }
            materialShapeDrawable.initializeElevationOverlay(context2);
            setBackground(materialShapeDrawable);
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(9)) {
            int dimensionPixelSize2 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(9, 0);
            NavigationBarMenuView navigationBarMenuView5 = this.menuView;
            navigationBarMenuView5.itemPaddingTop = dimensionPixelSize2;
            NavigationBarItemView[] navigationBarItemViewArr6 = navigationBarMenuView5.buttons;
            if (navigationBarItemViewArr6 != null) {
                for (NavigationBarItemView navigationBarItemView6 : navigationBarItemViewArr6) {
                    navigationBarItemView6.setItemPaddingTop(dimensionPixelSize2);
                }
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(8)) {
            int dimensionPixelSize3 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(8, 0);
            NavigationBarMenuView navigationBarMenuView6 = this.menuView;
            navigationBarMenuView6.itemPaddingBottom = dimensionPixelSize3;
            NavigationBarItemView[] navigationBarItemViewArr7 = navigationBarMenuView6.buttons;
            if (navigationBarItemViewArr7 != null) {
                for (NavigationBarItemView navigationBarItemView7 : navigationBarItemViewArr7) {
                    navigationBarItemView7.setItemPaddingBottom(dimensionPixelSize3);
                }
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(0)) {
            int dimensionPixelSize4 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(0, 0);
            NavigationBarMenuView navigationBarMenuView7 = this.menuView;
            navigationBarMenuView7.itemActiveIndicatorLabelPadding = dimensionPixelSize4;
            NavigationBarItemView[] navigationBarItemViewArr8 = navigationBarMenuView7.buttons;
            if (navigationBarItemViewArr8 != null) {
                for (NavigationBarItemView navigationBarItemView8 : navigationBarItemViewArr8) {
                    navigationBarItemView8.setActiveIndicatorLabelPadding(dimensionPixelSize4);
                }
            }
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(2)) {
            setElevation(obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getDimensionPixelSize(2, 0));
        }
        DrawableCompat$Api21Impl.setTintList(getBackground().mutate(), DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(context2, obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging, 1));
        int integer = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getInteger(15, -1);
        NavigationBarMenuView navigationBarMenuView8 = this.menuView;
        if (navigationBarMenuView8.labelVisibilityMode != integer) {
            navigationBarMenuView8.labelVisibilityMode = integer;
            this.presenter.updateMenuView(false);
        }
        int integer2 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getInteger(5, 0);
        NavigationBarMenuView navigationBarMenuView9 = this.menuView;
        if (navigationBarMenuView9.itemIconGravity != integer2) {
            navigationBarMenuView9.itemIconGravity = integer2;
            this.presenter.updateMenuView(false);
        }
        int resourceId3 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getResourceId(4, 0);
        if (resourceId3 != 0) {
            NavigationBarMenuView navigationBarMenuView10 = this.menuView;
            navigationBarMenuView10.itemBackgroundRes = resourceId3;
            NavigationBarItemView[] navigationBarItemViewArr9 = navigationBarMenuView10.buttons;
            if (navigationBarItemViewArr9 != null) {
                for (NavigationBarItemView navigationBarItemView9 : navigationBarItemViewArr9) {
                    navigationBarItemView9.setItemBackground(resourceId3);
                }
            }
        } else {
            ColorStateList colorStateList$ar$class_merging$ar$class_merging$ar$class_merging = DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(context2, obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging, 10);
            NavigationBarMenuView navigationBarMenuView11 = this.menuView;
            navigationBarMenuView11.itemRippleColor = colorStateList$ar$class_merging$ar$class_merging$ar$class_merging;
            NavigationBarItemView[] navigationBarItemViewArr10 = navigationBarMenuView11.buttons;
            if (navigationBarItemViewArr10 != null) {
                for (NavigationBarItemView navigationBarItemView10 : navigationBarItemViewArr10) {
                    navigationBarItemView10.setItemRippleColor(colorStateList$ar$class_merging$ar$class_merging$ar$class_merging);
                }
            }
        }
        boolean z2 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getBoolean(16, true);
        NavigationBarMenuView navigationBarMenuView12 = this.menuView;
        navigationBarMenuView12.measurePaddingFromLabelBaseline = z2;
        NavigationBarItemView[] navigationBarItemViewArr11 = navigationBarMenuView12.buttons;
        if (navigationBarItemViewArr11 != null) {
            for (NavigationBarItemView navigationBarItemView11 : navigationBarItemViewArr11) {
                navigationBarItemView11.setMeasureBottomPaddingFromLabelBaseline(z2);
            }
        }
        int resourceId4 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getResourceId(3, 0);
        if (resourceId4 != 0) {
            NavigationBarMenuView navigationBarMenuView13 = this.menuView;
            navigationBarMenuView13.itemActiveIndicatorEnabled = true;
            NavigationBarItemView[] navigationBarItemViewArr12 = navigationBarMenuView13.buttons;
            if (navigationBarItemViewArr12 != null) {
                for (NavigationBarItemView navigationBarItemView12 : navigationBarItemViewArr12) {
                    navigationBarItemView12.setActiveIndicatorEnabled(true);
                }
            }
            TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(resourceId4, R$styleable.NavigationBarActiveIndicator);
            int dimensionPixelSize5 = obtainStyledAttributes.getDimensionPixelSize(1, 0);
            NavigationBarMenuView navigationBarMenuView14 = this.menuView;
            navigationBarMenuView14.itemActiveIndicatorWidth = dimensionPixelSize5;
            NavigationBarItemView[] navigationBarItemViewArr13 = navigationBarMenuView14.buttons;
            if (navigationBarItemViewArr13 != null) {
                for (NavigationBarItemView navigationBarItemView13 : navigationBarItemViewArr13) {
                    navigationBarItemView13.setActiveIndicatorWidth(dimensionPixelSize5);
                }
            }
            int dimensionPixelSize6 = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            NavigationBarMenuView navigationBarMenuView15 = this.menuView;
            navigationBarMenuView15.itemActiveIndicatorHeight = dimensionPixelSize6;
            NavigationBarItemView[] navigationBarItemViewArr14 = navigationBarMenuView15.buttons;
            if (navigationBarItemViewArr14 != null) {
                for (NavigationBarItemView navigationBarItemView14 : navigationBarItemViewArr14) {
                    navigationBarItemView14.setActiveIndicatorHeight(dimensionPixelSize6);
                }
            }
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
            NavigationBarMenuView navigationBarMenuView16 = this.menuView;
            navigationBarMenuView16.itemActiveIndicatorMarginHorizontal = dimensionPixelOffset;
            NavigationBarItemView[] navigationBarItemViewArr15 = navigationBarMenuView16.buttons;
            if (navigationBarItemViewArr15 != null) {
                for (NavigationBarItemView navigationBarItemView15 : navigationBarItemViewArr15) {
                    navigationBarItemView15.setActiveIndicatorMarginHorizontal(dimensionPixelOffset);
                }
            }
            ColorStateList colorStateList4 = DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 2);
            NavigationBarMenuView navigationBarMenuView17 = this.menuView;
            navigationBarMenuView17.itemActiveIndicatorColor = colorStateList4;
            NavigationBarItemView[] navigationBarItemViewArr16 = navigationBarMenuView17.buttons;
            if (navigationBarItemViewArr16 != null) {
                for (NavigationBarItemView navigationBarItemView16 : navigationBarItemViewArr16) {
                    navigationBarItemView16.setActiveIndicatorDrawable(navigationBarMenuView17.createItemActiveIndicatorDrawable());
                }
            }
            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context2, obtainStyledAttributes.getResourceId(4, 0), 0));
            NavigationBarMenuView navigationBarMenuView18 = this.menuView;
            navigationBarMenuView18.itemActiveIndicatorShapeAppearance = shapeAppearanceModel;
            NavigationBarItemView[] navigationBarItemViewArr17 = navigationBarMenuView18.buttons;
            if (navigationBarItemViewArr17 != null) {
                for (NavigationBarItemView navigationBarItemView17 : navigationBarItemViewArr17) {
                    navigationBarItemView17.setActiveIndicatorDrawable(navigationBarMenuView18.createItemActiveIndicatorDrawable());
                }
            }
            obtainStyledAttributes.recycle();
        }
        if (obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.hasValue(17)) {
            int resourceId5 = obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.getResourceId(17, 0);
            this.presenter.updateSuspended = true;
            if (this.menuInflater == null) {
                this.menuInflater = new SupportMenuInflater(getContext());
            }
            this.menuInflater.inflate(resourceId5, this.menu);
            NavigationBarPresenter navigationBarPresenter2 = this.presenter;
            navigationBarPresenter2.updateSuspended = false;
            navigationBarPresenter2.updateMenuView(true);
        }
        obtainTintedStyledAttributes$ar$class_merging$ar$class_merging$ar$class_merging.recycle();
        addView(this.menuView);
        this.menu.mCallback = new MenuBuilder.Callback() { // from class: com.google.android.material.navigation.NavigationBarView.1
            @Override // android.support.v7.view.menu.MenuBuilder.Callback
            public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                int i3;
                NavDestination navDestination;
                int i4;
                NavDestination currentDestination;
                FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl = NavigationBarView.this.selectedListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
                if (shadowDelegateImpl != null) {
                    NavOptions.Builder builder = new NavOptions.Builder();
                    builder.singleTop = true;
                    Object obj = shadowDelegateImpl.FloatingActionButton$ShadowDelegateImpl$ar$this$0;
                    NavController navController = (NavController) obj;
                    NavDestination currentDestination2 = navController.getCurrentDestination();
                    currentDestination2.getClass();
                    NavGraph navGraph = currentDestination2.parent;
                    navGraph.getClass();
                    MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
                    if (navGraph.findNode(menuItemImpl.mId) instanceof ActivityNavigator.Destination) {
                        builder.enterAnim = R.anim.nav_default_enter_anim;
                        builder.exitAnim = R.anim.nav_default_exit_anim;
                        builder.popEnterAnim = R.anim.nav_default_pop_enter_anim;
                        builder.popExitAnim = R.anim.nav_default_pop_exit_anim;
                    } else {
                        builder.enterAnim = R.animator.nav_default_enter_anim;
                        builder.exitAnim = R.animator.nav_default_exit_anim;
                        builder.popEnterAnim = R.animator.nav_default_pop_enter_anim;
                        builder.popExitAnim = R.animator.nav_default_pop_exit_anim;
                    }
                    if ((menuItemImpl.mCategoryOrder & 196608) == 0) {
                        NavOptions.Builder.setPopUpTo$default$ar$ds(builder, WindowCompat$Api30Impl.findStartDestination$ar$ds(navController.getGraph()).id, false);
                    }
                    NavOptions build = builder.build();
                    try {
                        i3 = ((MenuItemImpl) menuItem).mId;
                        if (((NavController) obj).backQueue.isEmpty()) {
                            navDestination = ((NavController) obj)._graph;
                        } else {
                            navDestination = ((NavBackStackEntry) ((NavController) obj).backQueue.last()).destination;
                        }
                    } catch (IllegalArgumentException unused) {
                        ViewParentCompat$Api21Impl.getDisplayName$ar$ds(navController.context, menuItemImpl.mId);
                        Objects.toString(navController.getCurrentDestination());
                    }
                    if (navDestination != null) {
                        NavAction action = navDestination.getAction(i3);
                        Bundle bundle = null;
                        if (action != null) {
                            i4 = action.destinationId;
                            Bundle bundle2 = action.defaultArguments;
                            if (bundle2 != null) {
                                bundle = new Bundle();
                                bundle.putAll(bundle2);
                            }
                        } else {
                            i4 = i3;
                        }
                        if (i4 == 0) {
                            int i5 = build.popUpToId;
                            if (i5 == -1) {
                                i4 = 0;
                            } else {
                                if (i5 != -1) {
                                    ((NavController) obj).popBackStack$ar$ds$8ce35bcf_0(i5, build.popUpToInclusive);
                                }
                                currentDestination = ((NavController) obj).getCurrentDestination();
                                if (currentDestination != null && AccessibilityNodeInfoCompat.Api34Impl.matchDestination$navigation_ui_release(currentDestination, ((MenuItemImpl) menuItem).mId)) {
                                }
                                return true;
                            }
                        }
                        if (i4 != 0) {
                            NavDestination findDestination = ((NavController) obj).findDestination(i4);
                            if (findDestination == null) {
                                String displayName$ar$ds = ViewParentCompat$Api21Impl.getDisplayName$ar$ds(((NavController) obj).context, i4);
                                if (action == null) {
                                    throw new IllegalArgumentException("Navigation action/destination " + displayName$ar$ds + " cannot be found from the current destination " + navDestination);
                                }
                                throw new IllegalArgumentException("Navigation destination " + displayName$ar$ds + " referenced from action " + ViewParentCompat$Api21Impl.getDisplayName$ar$ds(((NavController) obj).context, i3) + " cannot be found from the current destination " + navDestination);
                            }
                            ((NavController) obj).navigate$ar$ds$e57d0073_0(findDestination, bundle, build);
                            currentDestination = ((NavController) obj).getCurrentDestination();
                            if (currentDestination != null) {
                            }
                            return true;
                        }
                        throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
                    }
                    throw new IllegalStateException("No current destination found. Ensure a navigation graph has been set for NavController " + obj + '.');
                }
                return false;
            }

            @Override // android.support.v7.view.menu.MenuBuilder.Callback
            public final void onMenuModeChange(MenuBuilder menuBuilder) {
            }
        };
    }

    protected abstract NavigationBarMenuView createNavigationBarMenuView(Context context);

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        EdgeTreatment.setParentAbsoluteElevation(this);
    }

    @Override // android.view.View
    protected final void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        NavigationBarMenu navigationBarMenu = this.menu;
        SparseArray sparseParcelableArray = savedState.menuPresenterState.getSparseParcelableArray("android:menu:presenters");
        if (sparseParcelableArray != null && !navigationBarMenu.mPresenters.isEmpty()) {
            Iterator it = navigationBarMenu.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    navigationBarMenu.mPresenters.remove(weakReference);
                } else {
                    int id = menuPresenter.getId();
                    if (id > 0 && (parcelable2 = (Parcelable) sparseParcelableArray.get(id)) != null) {
                        menuPresenter.onRestoreInstanceState(parcelable2);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    protected final Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.menuPresenterState = new Bundle();
        Bundle bundle = savedState.menuPresenterState;
        NavigationBarMenu navigationBarMenu = this.menu;
        if (!navigationBarMenu.mPresenters.isEmpty()) {
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            Iterator it = navigationBarMenu.mPresenters.iterator();
            while (it.hasNext()) {
                WeakReference weakReference = (WeakReference) it.next();
                MenuPresenter menuPresenter = (MenuPresenter) weakReference.get();
                if (menuPresenter == null) {
                    navigationBarMenu.mPresenters.remove(weakReference);
                } else {
                    int id = menuPresenter.getId();
                    if (id > 0 && (onSaveInstanceState = menuPresenter.onSaveInstanceState()) != null) {
                        sparseArray.put(id, onSaveInstanceState);
                    }
                }
            }
            bundle.putSparseParcelableArray("android:menu:presenters", sparseArray);
        }
        return savedState;
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        EdgeTreatment.setElevation(this, f);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BottomSheetBehavior.SavedState.AnonymousClass1(5);
        Bundle menuPresenterState;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuPresenterState = parcel.readBundle(classLoader == null ? getClass().getClassLoader() : classLoader);
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.menuPresenterState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
