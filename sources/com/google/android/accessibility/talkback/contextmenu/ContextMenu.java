package com.google.android.accessibility.talkback.contextmenu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ContextMenu implements Menu {
    private final Context context;
    public final List items = new ArrayList();
    public MenuItem.OnMenuItemClickListener listener;
    public String title;

    public ContextMenu(Context context) {
        this.context = context;
    }

    private final void addItem(ContextMenuItem contextMenuItem) {
        contextMenuItem.listener = this.listener;
        add(contextMenuItem);
    }

    public static ContextMenuItem createMenuItem(Context context, int i, int i2, int i3, CharSequence charSequence) {
        ContextMenuItem contextMenuItem = new ContextMenuItem(context, i, i2, i3, charSequence);
        contextMenuItem.enabled = true;
        return contextMenuItem;
    }

    @Override // android.view.Menu
    public final /* bridge */ /* synthetic */ MenuItem add(int i) {
        return add(0, 0, 0, i);
    }

    @Override // android.view.Menu
    public final int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        PackageManager packageManager = this.context.getPackageManager();
        int i5 = 0;
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        if ((i4 & 1) == 0) {
            removeGroup(i);
        }
        for (ResolveInfo resolveInfo : queryIntentActivityOptions) {
            Drawable loadIcon = resolveInfo.loadIcon(packageManager);
            ContextMenuItem add = add(i, i2, i3, resolveInfo.loadLabel(packageManager));
            add.icon = loadIcon;
            if (i5 < menuItemArr.length) {
                menuItemArr[i5] = add;
                i5++;
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        }
        return queryIntentActivityOptions.size();
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, i);
    }

    @Override // android.view.Menu
    public final void clear() {
        for (ContextMenuItem contextMenuItem : this.items) {
            MenuItem.OnMenuItemClickListener onMenuItemClickListener = contextMenuItem.listener;
            if (onMenuItemClickListener instanceof OnContextMenuItemClickListener) {
                ((OnContextMenuItemClickListener) onMenuItemClickListener).clear();
            }
            contextMenuItem.listener = null;
            ListSubMenu listSubMenu = contextMenuItem.subMenu;
            if (listSubMenu != null) {
                listSubMenu.clear();
            }
        }
        this.items.clear();
    }

    public final ContextMenuItem findItemInMenuOrSubmenus(int i) {
        ContextMenuItem findItem;
        for (ContextMenuItem contextMenuItem : this.items) {
            if (contextMenuItem.itemId == i) {
                return contextMenuItem;
            }
            if (contextMenuItem.hasSubMenu()) {
                ListSubMenu listSubMenu = contextMenuItem.subMenu;
                if ((listSubMenu instanceof ListSubMenu) && (findItem = listSubMenu.findItem(i)) != null) {
                    return findItem;
                }
            }
        }
        return null;
    }

    protected final ContextMenuItem getItemForShortcut(int i) {
        for (ContextMenuItem contextMenuItem : this.items) {
            if (contextMenuItem.alphaShortcut == i || contextMenuItem.numericShortcut == i) {
                return contextMenuItem;
            }
        }
        return null;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // android.view.Menu
    public final boolean hasVisibleItems() {
        Iterator it = this.items.iterator();
        while (it.hasNext()) {
            if (((MenuItem) it.next()).isVisible()) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.Menu
    public final boolean isShortcutKey(int i, KeyEvent keyEvent) {
        if (getItemForShortcut(i) != null) {
            return true;
        }
        return false;
    }

    @Override // android.view.Menu
    public final boolean performIdentifierAction(int i, int i2) {
        return performMenuItem$ar$ds(findItem(i));
    }

    public final boolean performMenuItem$ar$ds(ContextMenuItem contextMenuItem) {
        if (contextMenuItem == null || contextMenuItem.onClickPerformed()) {
            return true;
        }
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.listener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(contextMenuItem)) {
            return true;
        }
        return false;
    }

    @Override // android.view.Menu
    public final boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return performMenuItem$ar$ds(getItemForShortcut(i));
    }

    @Override // android.view.Menu
    public final void removeGroup(int i) {
        ArrayList arrayList = new ArrayList();
        for (MenuItem menuItem : this.items) {
            if (menuItem.getGroupId() == i) {
                arrayList.add(menuItem);
            }
        }
        this.items.removeAll(arrayList);
    }

    @Override // android.view.Menu
    public final void removeItem(int i) {
        ContextMenuItem findItem = findItem(i);
        if (findItem == null) {
            return;
        }
        this.items.remove(findItem);
    }

    @Override // android.view.Menu
    public final void setGroupCheckable(int i, boolean z, boolean z2) {
        for (MenuItem menuItem : this.items) {
            if (menuItem.getGroupId() == i) {
                menuItem.setCheckable(z);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupEnabled(int i, boolean z) {
        for (MenuItem menuItem : this.items) {
            if (menuItem.getGroupId() == i) {
                menuItem.setEnabled(z);
            }
        }
    }

    @Override // android.view.Menu
    public final void setGroupVisible(int i, boolean z) {
        for (MenuItem menuItem : this.items) {
            if (menuItem.getGroupId() == i) {
                menuItem.setVisible(z);
            }
        }
    }

    @Override // android.view.Menu
    public final int size() {
        return this.items.size();
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.context.getText(i4));
    }

    @Override // android.view.Menu
    public final ContextMenuItem findItem(int i) {
        if (i == -1) {
            return null;
        }
        for (ContextMenuItem contextMenuItem : this.items) {
            if (contextMenuItem.itemId == i) {
                return contextMenuItem;
            }
        }
        return null;
    }

    @Override // android.view.Menu
    public final ContextMenuItem getItem(int i) {
        return (ContextMenuItem) this.items.get(i);
    }

    @Override // android.view.Menu
    public final ContextMenuItem add(int i, int i2, int i3, int i4) {
        return add(i, i2, i3, this.context.getText(i4));
    }

    @Override // android.view.Menu
    public final ListSubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        ContextMenuItem contextMenuItem = new ContextMenuItem(this.context, i, i2, i3, charSequence);
        addItem(contextMenuItem);
        if (contextMenuItem.subMenu == null) {
            contextMenuItem.subMenu = new ListSubMenu(contextMenuItem.context, contextMenuItem);
        }
        return contextMenuItem.subMenu;
    }

    @Override // android.view.Menu
    public final ContextMenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        ContextMenuItem contextMenuItem = new ContextMenuItem(this.context, i, i2, i3, charSequence);
        addItem(contextMenuItem);
        return contextMenuItem;
    }

    public final void add(ContextMenuItem contextMenuItem) {
        this.items.add(contextMenuItem);
    }

    @Override // android.view.Menu
    public final SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final /* bridge */ /* synthetic */ MenuItem add(CharSequence charSequence) {
        return add(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public final void close() {
    }

    @Override // android.view.Menu
    public final void setQwertyMode(boolean z) {
    }
}
