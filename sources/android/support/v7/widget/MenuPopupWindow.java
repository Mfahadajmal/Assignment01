package android.support.v7.widget;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.transition.Transition;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.core.view.WindowInsetsAnimationCompat$Impl21;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    public static Method sSetTouchModalMethod;
    public MenuItemHoverListener mHoverListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api23Impl {
        public static void setEnterTransition(PopupWindow popupWindow, Transition transition) {
            popupWindow.setEnterTransition(transition);
        }

        public static void setExitTransition(PopupWindow popupWindow, Transition transition) {
            popupWindow.setExitTransition(transition);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        public static void setTouchModal(PopupWindow popupWindow, boolean z) {
            popupWindow.setTouchModal(z);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        public MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context context, boolean z) {
            super(context, z);
            int i;
            int layoutDirection = context.getResources().getConfiguration().getLayoutDirection();
            if (layoutDirection == 1) {
                i = 21;
            } else {
                i = 22;
            }
            this.mAdvanceKey = i;
            this.mRetreatKey = layoutDirection == 1 ? 22 : 21;
        }

        @Override // android.support.v7.widget.DropDownListView
        public final /* bridge */ /* synthetic */ int measureHeightOfChildrenCompat$ar$ds(int i, int i2) {
            return super.measureHeightOfChildrenCompat$ar$ds(i, i2);
        }

        @Override // android.support.v7.widget.DropDownListView, android.view.View
        public final boolean onHoverEvent(MotionEvent motionEvent) {
            MenuAdapter menuAdapter;
            int i;
            MenuItemImpl menuItemImpl;
            MenuItemHoverListener menuItemHoverListener;
            MenuItemHoverListener menuItemHoverListener2;
            int pointToPosition;
            int i2;
            if (this.mHoverListener != null) {
                ListAdapter adapter = getAdapter();
                int i3 = 0;
                if (adapter instanceof HeaderViewListAdapter) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
                    i = headerViewListAdapter.getHeadersCount();
                    menuAdapter = (MenuAdapter) headerViewListAdapter.getWrappedAdapter();
                } else {
                    menuAdapter = (MenuAdapter) adapter;
                    i = 0;
                }
                PhenotypeProcessReaper phenotypeProcessReaper = null;
                if (motionEvent.getAction() != 10 && (pointToPosition = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY())) != -1 && (i2 = pointToPosition - i) >= 0 && i2 < menuAdapter.getCount()) {
                    menuItemImpl = menuAdapter.getItem(i2);
                } else {
                    menuItemImpl = null;
                }
                MenuItem menuItem = this.mHoveredMenuItem;
                if (menuItem != menuItemImpl) {
                    MenuBuilder menuBuilder = menuAdapter.mAdapterMenu;
                    if (menuItem != null && (menuItemHoverListener2 = ((MenuPopupWindow) this.mHoverListener).mHoverListener) != null) {
                        CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menuBuilder);
                    }
                    this.mHoveredMenuItem = menuItemImpl;
                    if (menuItemImpl != null && (menuItemHoverListener = ((MenuPopupWindow) this.mHoverListener).mHoverListener) != null) {
                        CascadingMenuPopup.AnonymousClass3 anonymousClass3 = (CascadingMenuPopup.AnonymousClass3) menuItemHoverListener;
                        CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(null);
                        int size = CascadingMenuPopup.this.mShowingMenus.size();
                        while (true) {
                            if (i3 < size) {
                                if (menuBuilder == ((PhenotypeProcessReaper) CascadingMenuPopup.this.mShowingMenus.get(i3)).PhenotypeProcessReaper$ar$isKillable) {
                                    break;
                                }
                                i3++;
                            } else {
                                i3 = -1;
                                break;
                            }
                        }
                        if (i3 != -1) {
                            int i4 = i3 + 1;
                            if (i4 < CascadingMenuPopup.this.mShowingMenus.size()) {
                                phenotypeProcessReaper = (PhenotypeProcessReaper) CascadingMenuPopup.this.mShowingMenus.get(i4);
                            }
                            CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(new WindowInsetsAnimationCompat$Impl21.Impl21OnApplyWindowInsetsListener.AnonymousClass3(anonymousClass3, phenotypeProcessReaper, menuItemImpl, menuBuilder, 1), menuBuilder, SystemClock.uptimeMillis() + 200);
                        }
                    }
                }
            }
            return super.onHoverEvent(motionEvent);
        }

        @Override // android.widget.ListView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
        public final boolean onKeyDown(int i, KeyEvent keyEvent) {
            MenuAdapter menuAdapter;
            ListMenuItemView listMenuItemView = (ListMenuItemView) getSelectedView();
            if (listMenuItemView != null && i == this.mAdvanceKey) {
                if (listMenuItemView.isEnabled() && listMenuItemView.mItemData.hasSubMenu()) {
                    performItemClick(listMenuItemView, getSelectedItemPosition(), getSelectedItemId());
                }
                return true;
            }
            if (listMenuItemView != null && i == this.mRetreatKey) {
                setSelection(-1);
                ListAdapter adapter = getAdapter();
                if (adapter instanceof HeaderViewListAdapter) {
                    menuAdapter = (MenuAdapter) ((HeaderViewListAdapter) adapter).getWrappedAdapter();
                } else {
                    menuAdapter = (MenuAdapter) adapter;
                }
                menuAdapter.mAdapterMenu.close(false);
                return true;
            }
            return super.onKeyDown(i, keyEvent);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    public MenuPopupWindow(Context context, int i) {
        super(context, null, i, null);
    }

    @Override // android.support.v7.widget.ListPopupWindow
    public final DropDownListView createDropDownListView(Context context, boolean z) {
        MenuDropDownListView menuDropDownListView = new MenuDropDownListView(context, z);
        menuDropDownListView.mHoverListener = this;
        return menuDropDownListView;
    }
}
