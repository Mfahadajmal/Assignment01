package android.support.v7.view.menu;

import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MenuAdapter extends BaseAdapter {
    public final MenuBuilder mAdapterMenu;
    private int mExpandedIndex = -1;
    public boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menuBuilder, LayoutInflater layoutInflater, boolean z, int i) {
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater;
        this.mAdapterMenu = menuBuilder;
        this.mItemLayoutRes = i;
        findExpandedIndex();
    }

    final void findExpandedIndex() {
        MenuBuilder menuBuilder = this.mAdapterMenu;
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
        ArrayList visibleItems;
        if (this.mOverflowOnly) {
            visibleItems = this.mAdapterMenu.getNonActionItems();
        } else {
            visibleItems = this.mAdapterMenu.getVisibleItems();
        }
        if (this.mExpandedIndex < 0) {
            return visibleItems.size();
        }
        return visibleItems.size() - 1;
    }

    @Override // android.widget.Adapter
    public final MenuItemImpl getItem(int i) {
        ArrayList nonActionItems = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
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
        int i2;
        boolean z;
        int i3 = 0;
        if (view == null) {
            view = this.mInflater.inflate(this.mItemLayoutRes, viewGroup, false);
        }
        int i4 = getItem(i).mGroup;
        int i5 = i - 1;
        if (i5 >= 0) {
            i2 = getItem(i5).mGroup;
        } else {
            i2 = i4;
        }
        ListMenuItemView listMenuItemView = (ListMenuItemView) view;
        if (this.mAdapterMenu.isGroupDividerEnabled() && i4 != i2) {
            z = true;
        } else {
            z = false;
        }
        ImageView imageView = listMenuItemView.mGroupDivider;
        if (imageView != null) {
            if (listMenuItemView.mHasListDivider || !z) {
                i3 = 8;
            }
            imageView.setVisibility(i3);
        }
        MenuView.ItemView itemView = (MenuView.ItemView) view;
        if (this.mForceShowIcon) {
            listMenuItemView.mForceShowIcon = true;
            listMenuItemView.mPreserveIconSpacing = true;
        }
        itemView.initialize$ar$ds(getItem(i));
        return view;
    }

    @Override // android.widget.BaseAdapter
    public final void notifyDataSetChanged() {
        findExpandedIndex();
        super.notifyDataSetChanged();
    }
}
