package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.view.menu.BaseMenuPresenter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.support.v7.view.menu.ShowableListMenu;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionMenuView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.gms.common.api.internal.BaseLifecycleHelper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActionMenuPresenter extends BaseMenuPresenter {
    private final SparseBooleanArray mActionButtonGroups;
    public ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    public int mMaxItems;
    public int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    public OverflowPopup mOverflowPopup;
    private FloatingActionButton.ShadowDelegateImpl mPopupCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    final AppCompatDelegateImpl.PanelMenuPresenterCallback mPopupPresenterCallback$ar$class_merging;
    public BaseLifecycleHelper.ConnectionFailedResolver mPostedOpenRunnable$ar$class_merging;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private int mWidthLimit;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context, SubMenuBuilder subMenuBuilder, View view) {
            super(context, subMenuBuilder, view, false);
            if (!subMenuBuilder.mItem.isActionButton()) {
                View view2 = ActionMenuPresenter.this.mOverflowButton;
                this.mAnchorView = view2 == null ? (View) ActionMenuPresenter.this.mMenuView : view2;
            }
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback$ar$class_merging);
        }

        @Override // android.support.v7.view.menu.MenuPopupHelper
        protected final void onDismiss() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.mActionButtonPopup = null;
            actionMenuPresenter.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.ActionMenuChildView {
        public OverflowMenuButton(Context context) {
            super(context, null, R.attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            TooltipCompat$Api26Impl.setTooltipText(this, getContentDescription());
            setOnTouchListener(new ForwardingListener(this) { // from class: android.support.v7.widget.ActionMenuPresenter.OverflowMenuButton.1
                @Override // android.support.v7.widget.ForwardingListener
                public final ShowableListMenu getPopup() {
                    OverflowPopup overflowPopup = ActionMenuPresenter.this.mOverflowPopup;
                    if (overflowPopup == null) {
                        return null;
                    }
                    return overflowPopup.getPopup();
                }

                @Override // android.support.v7.widget.ForwardingListener
                public final boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                @Override // android.support.v7.widget.ForwardingListener
                public final boolean onForwardingStopped() {
                    ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                    if (actionMenuPresenter.mPostedOpenRunnable$ar$class_merging != null) {
                        return false;
                    }
                    actionMenuPresenter.hideOverflowMenu();
                    return true;
                }
            });
        }

        @Override // android.support.v7.widget.ActionMenuView.ActionMenuChildView
        public final boolean needsDividerAfter() {
            return false;
        }

        @Override // android.support.v7.widget.ActionMenuView.ActionMenuChildView
        public final boolean needsDividerBefore() {
            return false;
        }

        @Override // android.view.View
        public final boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        @Override // android.widget.ImageView
        protected final boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int max = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                DrawableCompat$Api21Impl.setHotspotBounds(background, paddingLeft - max, paddingTop - max, paddingLeft + max, paddingTop + max);
            }
            return frame;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view) {
            super(context, menuBuilder, view, true);
            this.mDropDownGravity = 8388613;
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback$ar$class_merging);
        }

        @Override // android.support.v7.view.menu.MenuPopupHelper
        protected final void onDismiss() {
            MenuBuilder menuBuilder = ActionMenuPresenter.this.mMenu;
            if (menuBuilder != null) {
                menuBuilder.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new BackStackState.AnonymousClass1(5);
        public int openSubMenuId;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.openSubMenuId);
        }

        public SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback$ar$class_merging = new AppCompatDelegateImpl.PanelMenuPresenterCallback(this, 2);
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public final void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize$ar$ds(menuItemImpl);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.mItemInvoker = (ActionMenuView) this.mMenuView;
        if (this.mPopupCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging == null) {
            this.mPopupCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        }
        actionMenuItemView.mPopupCallback$ar$class_merging$faf2e25b_0$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = this.mPopupCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    public final void dismissPopupMenus$ar$ds() {
        hideOverflowMenu();
        hideSubMenus();
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public final boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.mOverflowButton) {
            return false;
        }
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public final boolean flagActionItems() {
        ArrayList arrayList;
        int i;
        boolean z;
        boolean z2;
        boolean z3;
        MenuBuilder menuBuilder = this.mMenu;
        View view = null;
        boolean z4 = false;
        if (menuBuilder != null) {
            arrayList = menuBuilder.getVisibleItems();
            i = arrayList.size();
        } else {
            arrayList = null;
            i = 0;
        }
        int i2 = this.mMaxItems;
        int i3 = this.mActionItemWidthLimit;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        int i4 = 0;
        boolean z5 = false;
        int i5 = 0;
        int i6 = 0;
        while (true) {
            z = true;
            if (i4 >= i) {
                break;
            }
            MenuItemImpl menuItemImpl = (MenuItemImpl) arrayList.get(i4);
            if (menuItemImpl.requiresActionButton()) {
                i5++;
            } else if (menuItemImpl.requestsActionButton()) {
                i6++;
            } else {
                z5 = true;
            }
            if (this.mExpandedActionViewsExclusive && menuItemImpl.mIsActionViewExpanded) {
                i2 = 0;
            }
            i4++;
        }
        if (this.mReserveOverflow && (z5 || i6 + i5 > i2)) {
            i2--;
        }
        int i7 = i2 - i5;
        SparseBooleanArray sparseBooleanArray = this.mActionButtonGroups;
        sparseBooleanArray.clear();
        int i8 = 0;
        int i9 = 0;
        while (i8 < i) {
            MenuItemImpl menuItemImpl2 = (MenuItemImpl) arrayList.get(i8);
            if (menuItemImpl2.requiresActionButton()) {
                View itemView = getItemView(menuItemImpl2, view, viewGroup);
                itemView.measure(makeMeasureSpec, makeMeasureSpec);
                int measuredWidth = itemView.getMeasuredWidth();
                i3 -= measuredWidth;
                if (i9 == 0) {
                    i9 = measuredWidth;
                }
                int i10 = menuItemImpl2.mGroup;
                if (i10 != 0) {
                    sparseBooleanArray.put(i10, z);
                }
                menuItemImpl2.setIsActionButton(z);
                z2 = z4;
            } else if (menuItemImpl2.requestsActionButton()) {
                int i11 = menuItemImpl2.mGroup;
                boolean z6 = sparseBooleanArray.get(i11);
                if ((i7 > 0 || z6) && i3 > 0) {
                    z3 = z;
                } else {
                    z3 = z4;
                }
                if (z3) {
                    View itemView2 = getItemView(menuItemImpl2, view, viewGroup);
                    itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                    int measuredWidth2 = itemView2.getMeasuredWidth();
                    i3 -= measuredWidth2;
                    if (i9 == 0) {
                        i9 = measuredWidth2;
                    }
                    if (i3 + i9 > 0) {
                        z3 = z;
                    } else {
                        z3 = false;
                    }
                }
                boolean z7 = z3;
                if (z7 && i11 != 0) {
                    sparseBooleanArray.put(i11, z);
                } else if (z6) {
                    sparseBooleanArray.put(i11, false);
                    for (int i12 = 0; i12 < i8; i12++) {
                        MenuItemImpl menuItemImpl3 = (MenuItemImpl) arrayList.get(i12);
                        if (menuItemImpl3.mGroup == i11) {
                            if (menuItemImpl3.isActionButton()) {
                                i7++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                    }
                }
                if (z7) {
                    i7--;
                }
                menuItemImpl2.setIsActionButton(z7);
                z2 = false;
            } else {
                z2 = z4;
                menuItemImpl2.setIsActionButton(z2);
            }
            i8++;
            z4 = z2;
            view = null;
            z = true;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public final View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        MenuView.ItemView itemView;
        View actionView = menuItemImpl.getActionView();
        int i = 0;
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            if (view instanceof MenuView.ItemView) {
                itemView = (MenuView.ItemView) view;
            } else {
                itemView = (MenuView.ItemView) this.mSystemInflater.inflate(R.layout.abc_action_menu_item_layout, viewGroup, false);
            }
            bindItemView(menuItemImpl, itemView);
            actionView = (View) itemView;
        }
        if (true == menuItemImpl.mIsActionViewExpanded) {
            i = 8;
        }
        actionView.setVisibility(i);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!(layoutParams instanceof ActionMenuView.LayoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public final MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = this.mMenuView;
        if (this.mMenuView == null) {
            this.mMenuView = (MenuView) this.mSystemInflater.inflate(R.layout.abc_action_menu_layout, viewGroup, false);
            this.mMenuView.initialize(this.mMenu);
            updateMenuView(true);
        }
        MenuView menuView2 = this.mMenuView;
        if (menuView != menuView2) {
            ((ActionMenuView) menuView2).setPresenter(this);
        }
        return menuView2;
    }

    public final boolean hideOverflowMenu() {
        Object obj;
        BaseLifecycleHelper.ConnectionFailedResolver connectionFailedResolver = this.mPostedOpenRunnable$ar$class_merging;
        if (connectionFailedResolver != null && (obj = this.mMenuView) != null) {
            ((View) obj).removeCallbacks(connectionFailedResolver);
            this.mPostedOpenRunnable$ar$class_merging = null;
            return true;
        }
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup != null) {
            overflowPopup.dismiss();
            return true;
        }
        return false;
    }

    public final boolean hideSubMenus() {
        ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
        if (actionButtonSubmenu != null) {
            actionButtonSubmenu.dismiss();
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        this.mContext = context;
        LayoutInflater.from(this.mContext);
        this.mMenu = menuBuilder;
        Resources resources = context.getResources();
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = true;
        }
        this.mWidthLimit = ((Context) collectionItemInfoCompat.mInfo).getResources().getDisplayMetrics().widthPixels / 2;
        this.mMaxItems = collectionItemInfoCompat.getMaxActionButtons();
        int i = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = i;
        float f = resources.getDisplayMetrics().density;
    }

    public final boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup != null && overflowPopup.isShowing()) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus$ar$ds();
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onRestoreInstanceState(Parcelable parcelable) {
        int i;
        MenuItem findItem;
        if ((parcelable instanceof SavedState) && (i = ((SavedState) parcelable).openSubMenuId) > 0 && (findItem = this.mMenu.findItem(i)) != null) {
            onSubMenuSelected((SubMenuBuilder) findItem.getSubMenu());
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [android.view.View] */
    /* JADX WARN: Type inference failed for: r9v3, types: [android.support.v7.view.menu.MenuBuilder] */
    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z = false;
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (true) {
            MenuBuilder menuBuilder = subMenuBuilder2.mParentMenu;
            if (menuBuilder == this.mMenu) {
                break;
            }
            subMenuBuilder2 = (SubMenuBuilder) menuBuilder;
        }
        MenuItemImpl menuItemImpl = subMenuBuilder2.mItem;
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        View view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    break;
                }
                ?? childAt = viewGroup.getChildAt(i);
                if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItemImpl) {
                    view = childAt;
                    break;
                }
                i++;
            }
        }
        if (view == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder.mItem.mId;
        int size = subMenuBuilder.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.mContext, subMenuBuilder, view);
        this.mActionButtonPopup = actionButtonSubmenu;
        actionButtonSubmenu.setForceShowIcon(z);
        if (this.mActionButtonPopup.tryShow()) {
            MenuPresenter.Callback callback = this.mCallback;
            SubMenuBuilder subMenuBuilder3 = subMenuBuilder;
            if (callback != null) {
                if (subMenuBuilder == null) {
                    subMenuBuilder3 = this.mMenu;
                }
                callback.onOpenSubMenu(subMenuBuilder3);
            }
            return true;
        }
        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
    }

    public final void setExpandedActionViewsExclusive$ar$ds() {
        this.mExpandedActionViewsExclusive = true;
    }

    public final void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.mMenu = this.mMenu;
    }

    public final void setReserveOverflow$ar$ds() {
        this.mReserveOverflow = true;
        this.mReserveOverflowSet = true;
    }

    @Override // android.support.v7.view.menu.BaseMenuPresenter
    public final boolean shouldIncludeItem$ar$ds(MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public final boolean showOverflowMenu() {
        MenuBuilder menuBuilder;
        if (this.mReserveOverflow && !isOverflowMenuShowing() && (menuBuilder = this.mMenu) != null && this.mMenuView != null && this.mPostedOpenRunnable$ar$class_merging == null && !menuBuilder.getNonActionItems().isEmpty()) {
            this.mPostedOpenRunnable$ar$class_merging = new BaseLifecycleHelper.ConnectionFailedResolver(this, new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton), 1);
            ((View) this.mMenuView).post(this.mPostedOpenRunnable$ar$class_merging);
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.view.menu.BaseMenuPresenter, android.support.v7.view.menu.MenuPresenter
    public final void updateMenuView(boolean z) {
        int i;
        MenuItemImpl menuItemImpl;
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        ArrayList arrayList = null;
        boolean z2 = false;
        if (viewGroup != null) {
            MenuBuilder menuBuilder = this.mMenu;
            if (menuBuilder != null) {
                menuBuilder.flagActionItems();
                ArrayList visibleItems = this.mMenu.getVisibleItems();
                int size = visibleItems.size();
                i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    MenuItemImpl menuItemImpl2 = (MenuItemImpl) visibleItems.get(i2);
                    if (shouldIncludeItem$ar$ds(menuItemImpl2)) {
                        View childAt = viewGroup.getChildAt(i);
                        if (childAt instanceof MenuView.ItemView) {
                            menuItemImpl = ((MenuView.ItemView) childAt).getItemData();
                        } else {
                            menuItemImpl = null;
                        }
                        View itemView = getItemView(menuItemImpl2, childAt, viewGroup);
                        if (menuItemImpl2 != menuItemImpl) {
                            itemView.setPressed(false);
                            itemView.jumpDrawablesToCurrentState();
                        }
                        if (itemView != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) itemView.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(itemView);
                            }
                            ((ViewGroup) this.mMenuView).addView(itemView, i);
                        }
                        i++;
                    }
                }
            } else {
                i = 0;
            }
            while (i < viewGroup.getChildCount()) {
                if (!filterLeftoverView(viewGroup, i)) {
                    i++;
                }
            }
        }
        ((View) this.mMenuView).requestLayout();
        MenuBuilder menuBuilder2 = this.mMenu;
        if (menuBuilder2 != null) {
            menuBuilder2.flagActionItems();
            ArrayList arrayList2 = menuBuilder2.mActionItems;
            int size2 = arrayList2.size();
            for (int i3 = 0; i3 < size2; i3++) {
                ContextCompat$Api24Impl contextCompat$Api24Impl = ((MenuItemImpl) arrayList2.get(i3)).mActionProvider$ar$class_merging;
            }
        }
        MenuBuilder menuBuilder3 = this.mMenu;
        if (menuBuilder3 != null) {
            arrayList = menuBuilder3.getNonActionItems();
        }
        if (this.mReserveOverflow && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z2 = !((MenuItemImpl) arrayList.get(0)).mIsActionViewExpanded;
            } else if (size3 > 0) {
                z2 = true;
            }
            if (z2) {
                if (this.mOverflowButton == null) {
                    this.mOverflowButton = new OverflowMenuButton(this.mSystemContext);
                }
                ViewGroup viewGroup3 = (ViewGroup) this.mOverflowButton.getParent();
                if (viewGroup3 != this.mMenuView) {
                    if (viewGroup3 != null) {
                        viewGroup3.removeView(this.mOverflowButton);
                    }
                    ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                    OverflowMenuButton overflowMenuButton = this.mOverflowButton;
                    ActionMenuView.LayoutParams generateDefaultLayoutParams$ar$ds = ActionMenuView.generateDefaultLayoutParams$ar$ds();
                    generateDefaultLayoutParams$ar$ds.isOverflowButton = true;
                    actionMenuView.addView(overflowMenuButton, generateDefaultLayoutParams$ar$ds);
                }
                ((ActionMenuView) this.mMenuView).mReserveOverflow = this.mReserveOverflow;
            }
        }
        OverflowMenuButton overflowMenuButton2 = this.mOverflowButton;
        if (overflowMenuButton2 != null) {
            Object parent = overflowMenuButton2.getParent();
            Object obj = this.mMenuView;
            if (parent == obj) {
                ((ViewGroup) obj).removeView(this.mOverflowButton);
            }
        }
        ((ActionMenuView) this.mMenuView).mReserveOverflow = this.mReserveOverflow;
    }
}
