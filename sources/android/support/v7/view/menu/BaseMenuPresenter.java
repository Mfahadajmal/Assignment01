package android.support.v7.view.menu;

import android.content.Context;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseMenuPresenter implements MenuPresenter {
    public MenuPresenter.Callback mCallback;
    public Context mContext;
    public int mId;
    public MenuBuilder mMenu;
    public MenuView mMenuView;
    protected final Context mSystemContext;
    protected final LayoutInflater mSystemInflater;
    public final int mMenuLayoutRes = R.layout.abc_action_menu_layout;
    public final int mItemLayoutRes = R.layout.abc_action_menu_item_layout;

    public BaseMenuPresenter(Context context) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
    }

    public abstract void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView);

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean collapseItemActionView$ar$ds(MenuItemImpl menuItemImpl) {
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean expandItemActionView$ar$ds(MenuItemImpl menuItemImpl) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean filterLeftoverView(ViewGroup viewGroup, int i) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean flagActionItems() {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final int getId() {
        return this.mId;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void setCallback(MenuPresenter.Callback callback) {
        throw null;
    }

    public boolean shouldIncludeItem$ar$ds(MenuItemImpl menuItemImpl) {
        throw null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        throw null;
    }
}
