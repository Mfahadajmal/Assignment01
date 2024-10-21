package com.google.android.accessibility.talkback.contextmenu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextMenuItem implements MenuItem {
    public char alphaShortcut;
    public final Context context;
    private final int groupId;
    public Drawable icon;
    private Intent intent;
    public final int itemId;
    public MenuItem.OnMenuItemClickListener listener;
    public char numericShortcut;
    public final int order;
    public ListSubMenu subMenu;
    public CharSequence title;
    private CharSequence titleCondensed;
    public boolean enabled = true;
    private boolean visible = true;
    public boolean checkable = false;
    public boolean checked = false;
    public boolean skipRefocusEvents = false;
    public boolean skipWindowEvents = false;
    public boolean showsAlertDialog = false;
    public int deferredType$ar$edu = 1;
    public boolean needRestoreFocus = false;
    private final ContextMenuInfo menuInfo = new ContextMenuInfo();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ContextMenuInfo implements ContextMenu.ContextMenuInfo {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ContextMenuItemId {
        public final int itemId;
        private final String title;

        public ContextMenuItemId() {
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ContextMenuItemId) {
                ContextMenuItemId contextMenuItemId = (ContextMenuItemId) obj;
                if (this.itemId == contextMenuItemId.itemId() && this.title.equals(contextMenuItemId.title())) {
                    return true;
                }
            }
            return false;
        }

        public final int hashCode() {
            return ((this.itemId ^ 1000003) * 1000003) ^ this.title.hashCode();
        }

        public final int itemId() {
            return this.itemId;
        }

        public final String title() {
            return this.title;
        }

        public final String toString() {
            return "ContextMenuItemId{itemId=" + this.itemId + ", title=" + this.title + "}";
        }

        public ContextMenuItemId(int i, String str) {
            this();
            this.itemId = i;
            if (str == null) {
                throw new NullPointerException("Null title");
            }
            this.title = str;
        }
    }

    public ContextMenuItem(Context context, int i, int i2, int i3, CharSequence charSequence) {
        this.itemId = i2;
        this.groupId = i;
        this.context = context;
        this.order = i3;
        this.title = charSequence;
    }

    @Override // android.view.MenuItem
    public final boolean collapseActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        return null;
    }

    @Override // android.view.MenuItem
    public final View getActionView() {
        return null;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.alphaShortcut;
    }

    public final ContextMenuItemId getContextMenuItemId() {
        return new ContextMenuItemId(this.itemId, this.title.toString());
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.groupId;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        return this.icon;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.intent;
    }

    @Override // android.view.MenuItem
    public final int getItemId() {
        return this.itemId;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.menuInfo;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.numericShortcut;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.order;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.subMenu;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitle() {
        return this.title;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        return this.titleCondensed;
    }

    public final boolean hasNextPopupWidget() {
        if (!hasSubMenu() && !this.showsAlertDialog) {
            return false;
        }
        return true;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        if (this.subMenu != null) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        return this.checkable;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        if (this.checkable && this.checked) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        return this.enabled;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        return this.visible;
    }

    public final boolean onClickPerformed() {
        if (this.checkable) {
            this.checked = !this.checked;
        }
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.listener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final /* synthetic */ MenuItem setAlphabeticShortcut(char c) {
        this.alphaShortcut = c;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z) {
        this.checkable = z;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z) {
        this.checked = z;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z) {
        this.enabled = z;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.icon = drawable;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.intent = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public final /* synthetic */ MenuItem setNumericShortcut(char c) {
        this.numericShortcut = c;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.listener = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setShortcut(char c, char c2) {
        this.numericShortcut = c;
        this.alphaShortcut = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public final void setShowAsAction(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setShowAsActionFlags(int i) {
        throw new UnsupportedOperationException();
    }

    public final void setSkipRefocusEvents$ar$ds() {
        this.skipRefocusEvents = true;
    }

    public final void setSkipWindowEvents$ar$ds() {
        this.skipWindowEvents = true;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.title = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.titleCondensed = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z) {
        this.visible = z;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i) {
        this.icon = i == 0 ? null : this.context.getResources().getDrawable(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i) {
        this.title = this.context.getText(i);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        return this;
    }
}
