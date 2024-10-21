package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.view.menu.MenuItemImpl;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.TooltipCompat$Api26Impl;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements MenuView.ItemView {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private FrameLayout actionArea;
    boolean checkable;
    private int iconSize;
    boolean isBold;
    private MenuItemImpl itemData;
    private final CheckedTextView textView;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final void initialize$ar$ds(MenuItemImpl menuItemImpl) {
        int i;
        StateListDrawable stateListDrawable;
        this.itemData = menuItemImpl;
        int i2 = menuItemImpl.mId;
        if (i2 > 0) {
            setId(i2);
        }
        int i3 = 1;
        if (true != menuItemImpl.isVisible()) {
            i = 8;
        } else {
            i = 0;
        }
        setVisibility(i);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(com.google.android.marvin.talkback.R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            setBackground(stateListDrawable);
        }
        boolean isCheckable = menuItemImpl.isCheckable();
        refreshDrawableState();
        if (this.checkable != isCheckable) {
            this.checkable = isCheckable;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        }
        boolean isChecked = menuItemImpl.isChecked();
        refreshDrawableState();
        this.textView.setChecked(isChecked);
        CheckedTextView checkedTextView = this.textView;
        Typeface typeface = checkedTextView.getTypeface();
        if (!isChecked || !this.isBold) {
            i3 = 0;
        }
        checkedTextView.setTypeface(typeface, i3);
        setEnabled(menuItemImpl.isEnabled());
        this.textView.setText(menuItemImpl.mTitle);
        Drawable icon = menuItemImpl.getIcon();
        if (icon != null) {
            int i4 = this.iconSize;
            icon.setBounds(0, 0, i4, i4);
        }
        this.textView.setCompoundDrawablesRelative(icon, null, null, null);
        View actionView = menuItemImpl.getActionView();
        if (actionView != null) {
            if (this.actionArea == null) {
                this.actionArea = (FrameLayout) ((ViewStub) findViewById(com.google.android.marvin.talkback.R.id.design_menu_item_action_area_stub)).inflate();
            }
            if (actionView.getParent() != null) {
                ((ViewGroup) actionView.getParent()).removeView(actionView);
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(actionView);
        }
        setContentDescription(menuItemImpl.mContentDescription);
        TooltipCompat$Api26Impl.setTooltipText(this, menuItemImpl.mTooltipText);
        MenuItemImpl menuItemImpl2 = this.itemData;
        if (menuItemImpl2.mTitle == null && menuItemImpl2.getIcon() == null && this.itemData.getActionView() != null) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout = this.actionArea;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                layoutParams.width = -1;
                this.actionArea.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        this.textView.setVisibility(0);
        FrameLayout frameLayout2 = this.actionArea;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            layoutParams2.width = -2;
            this.actionArea.setLayoutParams(layoutParams2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        MenuItemImpl menuItemImpl = this.itemData;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.itemData.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.support.v7.view.menu.MenuView.ItemView
    public final boolean prefersCondensedTitle() {
        return false;
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isBold = true;
        AccessibilityDelegateCompat accessibilityDelegateCompat = new AccessibilityDelegateCompat() { // from class: com.google.android.material.internal.NavigationMenuItemView.1
            @Override // androidx.core.view.AccessibilityDelegateCompat
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
                accessibilityNodeInfoCompat.setCheckable(NavigationMenuItemView.this.checkable);
            }
        };
        this.accessibilityDelegate = accessibilityDelegateCompat;
        setOrientation(0);
        LayoutInflater.from(context).inflate(com.google.android.marvin.talkback.R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        this.iconSize = context.getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.design_navigation_icon_size);
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(com.google.android.marvin.talkback.R.id.design_menu_item_text);
        this.textView = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView, accessibilityDelegateCompat);
    }
}
