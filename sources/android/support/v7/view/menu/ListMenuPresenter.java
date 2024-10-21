package android.support.v7.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import com.google.android.accessibility.talkback.analytics.TrainingProto$TrainingPageId;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListMenuPresenter implements MenuPresenter, AdapterView.OnItemClickListener {
    public MenuAdapter mAdapter;
    public MenuPresenter.Callback mCallback;
    Context mContext;
    public LayoutInflater mInflater;
    final int mItemLayoutRes = R.layout.abc_list_menu_item_layout;
    MenuBuilder mMenu;
    public ExpandedMenuView mMenuView;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex = -1;

        public MenuAdapter() {
            findExpandedIndex();
        }

        final void findExpandedIndex() {
            MenuBuilder menuBuilder = ListMenuPresenter.this.mMenu;
            MenuItemImpl menuItemImpl = menuBuilder.mExpandedItem;
            if (menuItemImpl != null) {
                ArrayList nonActionItems = menuBuilder.getNonActionItems();
                int size = nonActionItems.size();
                for (int i = 0; i < size; i++) {
                    if (((MenuItemImpl) nonActionItems.get(i)) == menuItemImpl) {
                        this.mExpandedIndex = i;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            int size = ListMenuPresenter.this.mMenu.getNonActionItems().size();
            if (this.mExpandedIndex < 0) {
                return size;
            }
            return size - 1;
        }

        @Override // android.widget.Adapter
        public final MenuItemImpl getItem(int i) {
            ArrayList nonActionItems = ListMenuPresenter.this.mMenu.getNonActionItems();
            int i2 = this.mExpandedIndex;
            if (i2 >= 0 && i >= i2) {
                i++;
            }
            return (MenuItemImpl) nonActionItems.get(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = ListMenuPresenter.this.mInflater.inflate(R.layout.abc_list_menu_item_layout, viewGroup, false);
            }
            ((MenuView.ItemView) view).initialize$ar$ds(getItem(i));
            return view;
        }

        @Override // android.widget.BaseAdapter
        public final void notifyDataSetChanged() {
            findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

    public ListMenuPresenter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
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

    public final ListAdapter getAdapter() {
        if (this.mAdapter == null) {
            this.mAdapter = new MenuAdapter();
        }
        return this.mAdapter;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final int getId() {
        return 0;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void initForMenu(Context context, MenuBuilder menuBuilder) {
        if (this.mContext != null) {
            this.mContext = context;
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(context);
            }
        }
        this.mMenu = menuBuilder;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCloseMenu(menuBuilder, z);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.mMenu.performItemAction(this.mAdapter.getItem(i), this, 0);
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onRestoreInstanceState(Parcelable parcelable) {
        SparseArray<Parcelable> sparseParcelableArray = ((Bundle) parcelable).getSparseParcelableArray("android:menu:list");
        if (sparseParcelableArray != null) {
            this.mMenuView.restoreHierarchyState(sparseParcelableArray);
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final Parcelable onSaveInstanceState() {
        if (this.mMenuView == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        ExpandedMenuView expandedMenuView = this.mMenuView;
        if (expandedMenuView != null) {
            expandedMenuView.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("android:menu:list", sparseArray);
        return bundle;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        if (!subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        MenuDialogHelper menuDialogHelper = new MenuDialogHelper(subMenuBuilder);
        MenuBuilder menuBuilder = menuDialogHelper.mMenu;
        AlertDialog.Builder builder = new AlertDialog.Builder(menuBuilder.mContext);
        menuDialogHelper.mPresenter = new ListMenuPresenter(builder.getContext());
        ListMenuPresenter listMenuPresenter = menuDialogHelper.mPresenter;
        listMenuPresenter.mCallback = menuDialogHelper;
        menuDialogHelper.mMenu.addMenuPresenter(listMenuPresenter);
        builder.setAdapter(menuDialogHelper.mPresenter.getAdapter(), menuDialogHelper);
        View view = menuBuilder.mHeaderView;
        if (view != null) {
            builder.P.mCustomTitleView = view;
        } else {
            builder.setIcon$ar$ds(menuBuilder.mHeaderIcon);
            builder.setTitle(menuBuilder.mHeaderTitle);
        }
        builder.P.mOnKeyListener = menuDialogHelper;
        menuDialogHelper.mDialog = builder.create();
        menuDialogHelper.mDialog.setOnDismissListener(menuDialogHelper);
        WindowManager.LayoutParams attributes = menuDialogHelper.mDialog.getWindow().getAttributes();
        attributes.type = TrainingProto$TrainingPageId.PAGE_ID_ANSWER_CALLS$ar$edu;
        attributes.flags |= 131072;
        menuDialogHelper.mDialog.show();
        MenuPresenter.Callback callback = this.mCallback;
        if (callback != null) {
            callback.onOpenSubMenu(subMenuBuilder);
            return true;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void setCallback(MenuPresenter.Callback callback) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void updateMenuView(boolean z) {
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }
}
