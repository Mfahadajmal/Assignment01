package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.SubMenuBuilder;
import android.util.SparseArray;
import android.view.MenuItem;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeState;
import com.google.android.material.internal.ParcelableSparseArray;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigationBarPresenter implements MenuPresenter {
    public int id;
    private MenuBuilder menu;
    public NavigationBarMenuView menuView;
    public boolean updateSuspended = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new BadgeState.State.AnonymousClass1(5);
        ParcelableSparseArray badgeSavedStates;
        int selectedItemId;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.selectedItemId);
            parcel.writeParcelable(this.badgeSavedStates, 0);
        }

        public SavedState(Parcel parcel) {
            this.selectedItemId = parcel.readInt();
            this.badgeSavedStates = (ParcelableSparseArray) parcel.readParcelable(getClass().getClassLoader());
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean collapseItemActionView$ar$ds(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean expandItemActionView$ar$ds(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean flagActionItems() {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final int getId() {
        return this.id;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.menu = menuBuilder;
        this.menuView.menu = menuBuilder;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onRestoreInstanceState(Parcelable parcelable) {
        BadgeDrawable badgeDrawable;
        if (parcelable instanceof SavedState) {
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            SavedState savedState = (SavedState) parcelable;
            int i = savedState.selectedItemId;
            int size = navigationBarMenuView.menu.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                MenuItem item = navigationBarMenuView.menu.getItem(i2);
                if (i == item.getItemId()) {
                    navigationBarMenuView.selectedItemId = i;
                    navigationBarMenuView.selectedItemPosition = i2;
                    item.setChecked(true);
                    break;
                }
                i2++;
            }
            Context context = this.menuView.getContext();
            ParcelableSparseArray parcelableSparseArray = savedState.badgeSavedStates;
            SparseArray sparseArray = new SparseArray(parcelableSparseArray.size());
            for (int i3 = 0; i3 < parcelableSparseArray.size(); i3++) {
                int keyAt = parcelableSparseArray.keyAt(i3);
                BadgeState.State state = (BadgeState.State) parcelableSparseArray.valueAt(i3);
                if (state != null) {
                    badgeDrawable = new BadgeDrawable(context, state);
                } else {
                    badgeDrawable = null;
                }
                sparseArray.put(keyAt, badgeDrawable);
            }
            NavigationBarMenuView navigationBarMenuView2 = this.menuView;
            for (int i4 = 0; i4 < sparseArray.size(); i4++) {
                int keyAt2 = sparseArray.keyAt(i4);
                if (navigationBarMenuView2.badgeDrawables.indexOfKey(keyAt2) < 0) {
                    navigationBarMenuView2.badgeDrawables.append(keyAt2, (BadgeDrawable) sparseArray.get(keyAt2));
                }
            }
            NavigationBarItemView[] navigationBarItemViewArr = navigationBarMenuView2.buttons;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    BadgeDrawable badgeDrawable2 = (BadgeDrawable) navigationBarMenuView2.badgeDrawables.get(navigationBarItemView.getId());
                    if (badgeDrawable2 != null) {
                        navigationBarItemView.setBadge(badgeDrawable2);
                    }
                }
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final Parcelable onSaveInstanceState() {
        BadgeState.State state;
        SavedState savedState = new SavedState();
        NavigationBarMenuView navigationBarMenuView = this.menuView;
        savedState.selectedItemId = navigationBarMenuView.selectedItemId;
        SparseArray sparseArray = navigationBarMenuView.badgeDrawables;
        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            BadgeDrawable badgeDrawable = (BadgeDrawable) sparseArray.valueAt(i);
            if (badgeDrawable != null) {
                state = badgeDrawable.state.overridingState;
            } else {
                state = null;
            }
            parcelableSparseArray.put(keyAt, state);
        }
        savedState.badgeSavedStates = parcelableSparseArray;
        return savedState;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void setCallback(MenuPresenter.Callback callback) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void updateMenuView(boolean z) {
        TransitionSet transitionSet;
        if (!this.updateSuspended) {
            if (z) {
                this.menuView.buildMenuView();
                return;
            }
            NavigationBarMenuView navigationBarMenuView = this.menuView;
            MenuBuilder menuBuilder = navigationBarMenuView.menu;
            if (menuBuilder != null && navigationBarMenuView.buttons != null) {
                int size = menuBuilder.size();
                if (size != navigationBarMenuView.buttons.length) {
                    navigationBarMenuView.buildMenuView();
                    return;
                }
                int i = navigationBarMenuView.selectedItemId;
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItem item = navigationBarMenuView.menu.getItem(i2);
                    if (item.isChecked()) {
                        navigationBarMenuView.selectedItemId = item.getItemId();
                        navigationBarMenuView.selectedItemPosition = i2;
                    }
                }
                if (i != navigationBarMenuView.selectedItemId && (transitionSet = navigationBarMenuView.set) != null) {
                    TransitionManager.beginDelayedTransition(navigationBarMenuView, transitionSet);
                }
                boolean isShifting = navigationBarMenuView.isShifting(navigationBarMenuView.labelVisibilityMode, navigationBarMenuView.menu.getVisibleItems().size());
                for (int i3 = 0; i3 < size; i3++) {
                    navigationBarMenuView.presenter.updateSuspended = true;
                    navigationBarMenuView.buttons[i3].setLabelVisibilityMode(navigationBarMenuView.labelVisibilityMode);
                    navigationBarMenuView.buttons[i3].setItemIconGravity(navigationBarMenuView.itemIconGravity);
                    navigationBarMenuView.buttons[i3].setShifting(isShifting);
                    navigationBarMenuView.buttons[i3].initialize$ar$ds((MenuItemImpl) navigationBarMenuView.menu.getItem(i3));
                    navigationBarMenuView.presenter.updateSuspended = false;
                }
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
    }
}
