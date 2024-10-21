package android.support.v7.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat$Api24Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.internal.view.SupportMenuItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MenuItemImpl implements SupportMenuItem {
    public ContextCompat$Api24Impl mActionProvider$ar$class_merging;
    private View mActionView;
    public final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    public CharSequence mContentDescription;
    public final int mGroup;
    private Drawable mIconDrawable;
    public final int mId;
    public Intent mIntent;
    final MenuBuilder mMenu;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    public final int mOrdering;
    public char mShortcutAlphabeticChar;
    public char mShortcutNumericChar;
    public int mShowAsAction;
    public SubMenuBuilder mSubMenu;
    public CharSequence mTitle;
    private CharSequence mTitleCondensed;
    public CharSequence mTooltipText;
    public int mShortcutNumericModifiers = 4096;
    public int mShortcutAlphabeticModifiers = 4096;
    private int mIconResId = 0;
    private ColorStateList mIconTintList = null;
    private PorterDuff.Mode mIconTintMode = null;
    private boolean mHasIconTint = false;
    private boolean mHasIconTintMode = false;
    private boolean mNeedToApplyIconTint = false;
    private int mFlags = 16;
    public boolean mIsActionViewExpanded = false;

    public MenuItemImpl(MenuBuilder menuBuilder, int i, int i2, int i3, int i4, CharSequence charSequence, int i5) {
        this.mMenu = menuBuilder;
        this.mId = i2;
        this.mGroup = i;
        this.mCategoryOrder = i3;
        this.mOrdering = i4;
        this.mTitle = charSequence;
        this.mShowAsAction = i5;
    }

    public static void appendModifier(StringBuilder sb, int i, int i2, String str) {
        if ((i & i2) == i2) {
            sb.append(str);
        }
    }

    private final Drawable applyIconTintIfNecessary(Drawable drawable) {
        if (drawable != null && this.mNeedToApplyIconTint && (this.mHasIconTint || this.mHasIconTintMode)) {
            drawable = drawable.mutate();
            if (this.mHasIconTint) {
                DrawableCompat$Api21Impl.setTintList(drawable, this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                DrawableCompat$Api21Impl.setTintMode(drawable, this.mIconTintMode);
            }
            this.mNeedToApplyIconTint = false;
        }
        return drawable;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
        if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionCollapse(this)) {
            return false;
        }
        return this.mMenu.collapseItemActionView(this);
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final boolean expandActionView() {
        if (hasCollapsibleActionView()) {
            MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
            if (onActionExpandListener != null && !onActionExpandListener.onMenuItemActionExpand(this)) {
                return false;
            }
            return this.mMenu.expandItemActionView(this);
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final View getActionView() {
        View view = this.mActionView;
        if (view != null) {
            return view;
        }
        ContextCompat$Api24Impl contextCompat$Api24Impl = this.mActionProvider$ar$class_merging;
        if (contextCompat$Api24Impl != null) {
            View onCreateActionView = contextCompat$Api24Impl.onCreateActionView(this);
            this.mActionView = onCreateActionView;
            return onCreateActionView;
        }
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    @Override // android.view.MenuItem
    public final char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    @Override // android.view.MenuItem
    public final int getGroupId() {
        return this.mGroup;
    }

    @Override // android.view.MenuItem
    public final Drawable getIcon() {
        Drawable drawable = this.mIconDrawable;
        if (drawable != null) {
            return applyIconTintIfNecessary(drawable);
        }
        int i = this.mIconResId;
        if (i != 0) {
            Drawable drawable2 = AppCompatDelegate.Api33Impl.getDrawable(this.mMenu.mContext, i);
            this.mIconResId = 0;
            this.mIconDrawable = drawable2;
            return applyIconTintIfNecessary(drawable2);
        }
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    @Override // android.view.MenuItem
    public final Intent getIntent() {
        return this.mIntent;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public final int getItemId() {
        return this.mId;
    }

    @Override // android.view.MenuItem
    public final ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    @Override // android.view.MenuItem
    public final char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public final int getOrder() {
        return this.mCategoryOrder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final char getShortcut() {
        if (this.mMenu.isQwertyMode()) {
            return this.mShortcutAlphabeticChar;
        }
        return this.mShortcutNumericChar;
    }

    @Override // android.view.MenuItem
    public final SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public final ContextCompat$Api24Impl getSupportActionProvider$ar$class_merging() {
        return this.mActionProvider$ar$class_merging;
    }

    @Override // android.view.MenuItem
    @ViewDebug.CapturedViewProperty
    public final CharSequence getTitle() {
        return this.mTitle;
    }

    @Override // android.view.MenuItem
    public final CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed;
        if (charSequence != null) {
            return charSequence;
        }
        return this.mTitle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        if (itemView.prefersCondensedTitle()) {
            return getTitleCondensed();
        }
        return this.mTitle;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public final boolean hasCollapsibleActionView() {
        ContextCompat$Api24Impl contextCompat$Api24Impl;
        if ((this.mShowAsAction & 8) != 0) {
            if (this.mActionView == null && (contextCompat$Api24Impl = this.mActionProvider$ar$class_merging) != null) {
                this.mActionView = contextCompat$Api24Impl.onCreateActionView(this);
            }
            if (this.mActionView != null) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean hasSubMenu() {
        if (this.mSubMenu != null) {
            return true;
        }
        return false;
    }

    public final boolean invoke() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mClickListener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder.dispatchMenuItemSelected(menuBuilder, this)) {
            return true;
        }
        Intent intent = this.mIntent;
        if (intent != null) {
            try {
                this.mMenu.mContext.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        ContextCompat$Api24Impl contextCompat$Api24Impl = this.mActionProvider$ar$class_merging;
        if (contextCompat$Api24Impl != null && contextCompat$Api24Impl.onPerformDefaultAction()) {
            return true;
        }
        return false;
    }

    public final boolean isActionButton() {
        if ((this.mFlags & 32) == 32) {
            return true;
        }
        return false;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    @Override // android.view.MenuItem
    public final boolean isCheckable() {
        if ((this.mFlags & 1) == 1) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isChecked() {
        if ((this.mFlags & 2) == 2) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isEnabled() {
        if ((this.mFlags & 16) != 0) {
            return true;
        }
        return false;
    }

    public final boolean isExclusiveCheckable() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final boolean isVisible() {
        ContextCompat$Api24Impl contextCompat$Api24Impl = this.mActionProvider$ar$class_merging;
        if (contextCompat$Api24Impl != null && contextCompat$Api24Impl.overridesItemVisibility()) {
            if ((this.mFlags & 8) == 0 && this.mActionProvider$ar$class_merging.isVisible()) {
                return true;
            }
            return false;
        }
        if ((this.mFlags & 8) == 0) {
            return true;
        }
        return false;
    }

    public final boolean requestsActionButton() {
        if ((this.mShowAsAction & 1) == 1) {
            return true;
        }
        return false;
    }

    public final boolean requiresActionButton() {
        if ((this.mShowAsAction & 2) == 2) {
            return true;
        }
        return false;
    }

    @Override // android.view.MenuItem
    public final MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setActionView(int i) {
        Context context = this.mMenu.mContext;
        setActionView$ar$ds(LayoutInflater.from(context).inflate(i, (ViewGroup) new LinearLayout(context), false));
        return this;
    }

    public final void setActionView$ar$ds(View view) {
        int i;
        this.mActionView = view;
        this.mActionProvider$ar$class_merging = null;
        if (view != null && view.getId() == -1 && (i = this.mId) > 0) {
            view.setId(i);
        }
        this.mMenu.onItemActionRequestChanged$ar$ds();
    }

    public final void setActionViewExpanded(boolean z) {
        this.mIsActionViewExpanded = z;
        this.mMenu.onItemsChanged(false);
    }

    @Override // android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c) {
        if (this.mShortcutAlphabeticChar == c) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setCheckable(boolean z) {
        int i = this.mFlags;
        int i2 = (z ? 1 : 0) | (i & (-2));
        this.mFlags = i2;
        if (i != i2) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setChecked(boolean z) {
        boolean z2;
        if ((this.mFlags & 4) != 0) {
            MenuBuilder menuBuilder = this.mMenu;
            int i = this.mGroup;
            int size = menuBuilder.mItems.size();
            menuBuilder.stopDispatchingItemsChanged();
            for (int i2 = 0; i2 < size; i2++) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuBuilder.mItems.get(i2);
                if (menuItemImpl.mGroup == i && menuItemImpl.isExclusiveCheckable() && menuItemImpl.isCheckable()) {
                    if (menuItemImpl == this) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    menuItemImpl.setCheckedInt(z2);
                }
            }
            menuBuilder.startDispatchingItemsChanged();
        } else {
            setCheckedInt(z);
        }
        return this;
    }

    final void setCheckedInt(boolean z) {
        int i;
        int i2 = this.mFlags;
        int i3 = i2 & (-3);
        if (true != z) {
            i = 0;
        } else {
            i = 2;
        }
        int i4 = i | i3;
        this.mFlags = i4;
        if (i2 != i4) {
            this.mMenu.onItemsChanged(false);
        }
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription$ar$ds(charSequence);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public final void setContentDescription$ar$ds(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
    }

    @Override // android.view.MenuItem
    public final MenuItem setEnabled(boolean z) {
        int i;
        if (z) {
            i = this.mFlags | 16;
        } else {
            i = this.mFlags & (-17);
        }
        this.mFlags = i;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public final void setExclusiveCheckable(boolean z) {
        int i;
        int i2 = this.mFlags & (-5);
        if (true != z) {
            i = 0;
        } else {
            i = 4;
        }
        this.mFlags = i | i2;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(int i) {
        this.mIconDrawable = null;
        this.mIconResId = i;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mIconTintMode = mode;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public final void setIsActionButton(boolean z) {
        int i;
        if (z) {
            i = this.mFlags | 32;
        } else {
            i = this.mFlags & (-33);
        }
        this.mFlags = i;
    }

    @Override // android.view.MenuItem
    public final MenuItem setNumericShortcut(char c) {
        if (this.mShortcutNumericChar == c) {
            return this;
        }
        this.mShortcutNumericChar = c;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setShortcut(char c, char c2) {
        this.mShortcutNumericChar = c;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final void setShowAsAction(int i) {
        int i2 = i & 3;
        if (i2 != 0 && i2 != 1 && i2 != 2) {
            throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
        }
        this.mShowAsAction = i;
        this.mMenu.onItemActionRequestChanged$ar$ds();
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    public final void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(this.mTitle);
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public final void setSupportActionProvider$ar$ds$ar$class_merging(ContextCompat$Api24Impl contextCompat$Api24Impl) {
        this.mActionView = null;
        this.mActionProvider$ar$class_merging = contextCompat$Api24Impl;
        this.mMenu.onItemsChanged(true);
        ContextCompat$Api24Impl contextCompat$Api24Impl2 = this.mActionProvider$ar$class_merging;
        if (contextCompat$Api24Impl2 != null) {
            contextCompat$Api24Impl2.setVisibilityListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(new FloatingActionButton.ShadowDelegateImpl(this));
        }
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(int i) {
        setTitle(this.mMenu.mContext.getString(i));
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText$ar$ds(charSequence);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem
    public final void setTooltipText$ar$ds(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
    }

    @Override // android.view.MenuItem
    public final MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            this.mMenu.onItemVisibleChanged$ar$ds();
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean setVisibleInt(boolean z) {
        int i;
        int i2 = this.mFlags;
        int i3 = i2 & (-9);
        if (true != z) {
            i = 8;
        } else {
            i = 0;
        }
        int i4 = i | i3;
        this.mFlags = i4;
        if (i2 == i4) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean shouldShowShortcut() {
        if (this.mMenu.isShortcutsVisible() && getShortcut() != 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    @Override // android.view.MenuItem
    public final MenuItem setIcon(Drawable drawable) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final MenuItem setNumericShortcut(char c, int i) {
        if (this.mShortcutNumericChar == c && this.mShortcutNumericModifiers == i) {
            return this;
        }
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // android.view.MenuItem
    public final MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder subMenuBuilder = this.mSubMenu;
        if (subMenuBuilder != null) {
            subMenuBuilder.setHeaderTitle(charSequence);
        }
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final /* bridge */ /* synthetic */ MenuItem setActionView(View view) {
        setActionView$ar$ds(view);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final MenuItem setAlphabeticShortcut(char c, int i) {
        if (this.mShortcutAlphabeticChar == c && this.mShortcutAlphabeticModifiers == i) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    @Override // androidx.core.internal.view.SupportMenuItem, android.view.MenuItem
    public final MenuItem setShortcut(char c, char c2, int i, int i2) {
        this.mShortcutNumericChar = c;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c2);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }
}
