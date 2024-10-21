package com.google.android.accessibility.talkback.contextmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListSubMenu extends ContextMenu implements SubMenu {
    private final ContextMenuItem menuItem;

    public ListSubMenu(Context context, ContextMenuItem contextMenuItem) {
        super(context);
        this.menuItem = contextMenuItem;
    }

    @Override // android.view.SubMenu
    public final void clearHeader() {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.SubMenu
    public final /* synthetic */ MenuItem getItem() {
        return this.menuItem;
    }

    @Override // com.google.android.accessibility.talkback.contextmenu.ContextMenu
    public final String getTitle() {
        CharSequence charSequence = this.menuItem.title;
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString();
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        this.menuItem.icon = drawable;
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        this.menuItem.title = charSequence;
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.menuItem.icon = drawable;
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i) {
        this.menuItem.setIcon(i);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i) {
        this.menuItem.setTitle(i);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i) {
        this.menuItem.setIcon(i);
        return this;
    }
}
