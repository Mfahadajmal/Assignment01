package com.google.android.material.navigation;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuItemImpl;
import android.view.MenuItem;
import android.view.SubMenu;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NavigationBarMenu extends MenuBuilder {
    private final Class viewClass;

    public NavigationBarMenu(Context context, Class cls) {
        super(context);
        this.viewClass = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.view.menu.MenuBuilder
    public final MenuItem addInternal(int i, int i2, int i3, CharSequence charSequence) {
        if (size() + 1 <= 5) {
            stopDispatchingItemsChanged();
            MenuItem addInternal = super.addInternal(i, i2, i3, charSequence);
            ((MenuItemImpl) addInternal).setExclusiveCheckable(true);
            startDispatchingItemsChanged();
            return addInternal;
        }
        String simpleName = this.viewClass.getSimpleName();
        throw new IllegalArgumentException("Maximum number of items supported by " + simpleName + " is 5. Limit can be checked with " + simpleName + "#getMaxItemCount()");
    }

    @Override // android.support.v7.view.menu.MenuBuilder, android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        throw new UnsupportedOperationException(String.valueOf(this.viewClass.getSimpleName()).concat(" does not support submenus"));
    }
}
