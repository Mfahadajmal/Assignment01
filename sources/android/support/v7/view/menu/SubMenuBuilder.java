package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.view.menu.MenuBuilder;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SubMenuBuilder extends MenuBuilder implements SubMenu {
    public final MenuItemImpl mItem;
    public final MenuBuilder mParentMenu;

    public SubMenuBuilder(Context context, MenuBuilder menuBuilder, MenuItemImpl menuItemImpl) {
        super(context);
        this.mParentMenu = menuBuilder;
        this.mItem = menuItemImpl;
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean collapseItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.collapseItemActionView(menuItemImpl);
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean dispatchMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (!super.dispatchMenuItemSelected(menuBuilder, menuItem) && !this.mParentMenu.dispatchMenuItemSelected(menuBuilder, menuItem)) {
            return false;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean expandItemActionView(MenuItemImpl menuItemImpl) {
        return this.mParentMenu.expandItemActionView(menuItemImpl);
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final String getActionViewStatesKey() {
        int i = this.mItem.mId;
        if (i == 0) {
            return null;
        }
        return "android:menu:actionviewstates:" + i;
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.mItem;
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final MenuBuilder getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean isGroupDividerEnabled() {
        return this.mParentMenu.isGroupDividerEnabled();
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override // android.support.v7.view.menu.MenuBuilder
    public final void setCallback(MenuBuilder.Callback callback) {
        this.mParentMenu.setCallback(callback);
    }

    @Override // android.support.v7.view.menu.MenuBuilder, android.view.Menu
    public final void setGroupDividerEnabled(boolean z) {
        this.mParentMenu.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        super.setHeaderInternal(0, null, i, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        super.setHeaderInternal(i, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        super.setHeaderInternal(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.mItem.setIcon(i);
        return this;
    }

    @Override // android.support.v7.view.menu.MenuBuilder, android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.mParentMenu.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        super.setHeaderInternal(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        super.setHeaderInternal(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.mItem.setIcon(drawable);
        return this;
    }
}
