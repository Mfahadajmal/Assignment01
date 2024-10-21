package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.menu.ActionMenuItem;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToolbarWidgetWrapper {
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private final Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private CharSequence mSubtitle;
    CharSequence mTitle;
    private boolean mTitleSet;
    final Toolbar mToolbar;
    Window.Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar) {
        boolean z;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar;
        this.mTitle = toolbar.mTitleText;
        this.mSubtitle = toolbar.mSubtitleText;
        if (this.mTitle != null) {
            z = true;
        } else {
            z = false;
        }
        this.mTitleSet = z;
        this.mNavIcon = toolbar.getNavigationIcon();
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(toolbar.getContext(), null, R$styleable.ActionBar, R.attr.actionBarStyle, 0);
        Drawable drawable = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(15);
        this.mDefaultNavigationIcon = drawable;
        CharSequence text = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getText(27);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getText(25);
        if (!TextUtils.isEmpty(text2)) {
            this.mSubtitle = text2;
            if ((this.mDisplayOpts & 8) != 0) {
                toolbar.setSubtitle(text2);
            }
        }
        Drawable drawable2 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(20);
        if (drawable2 != null) {
            setLogo(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(17);
        if (drawable3 != null) {
            this.mIcon = drawable3;
            updateToolbarLogo();
        }
        if (this.mNavIcon == null && drawable != null) {
            setNavigationIcon(drawable);
        }
        setDisplayOptions(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(10, 0));
        int resourceId = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(9, 0);
        if (resourceId != 0) {
            View inflate = LayoutInflater.from(toolbar.getContext()).inflate(resourceId, (ViewGroup) toolbar, false);
            View view = this.mCustomView;
            if (view != null && (this.mDisplayOpts & 16) != 0) {
                toolbar.removeView(view);
            }
            this.mCustomView = inflate;
            if (inflate != null && (this.mDisplayOpts & 16) != 0) {
                toolbar.addView(inflate);
            }
            setDisplayOptions(this.mDisplayOpts | 16);
        }
        int layoutDimension = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getLayoutDimension(13, 0);
        if (layoutDimension > 0) {
            ViewGroup.LayoutParams layoutParams = toolbar.getLayoutParams();
            layoutParams.height = layoutDimension;
            toolbar.setLayoutParams(layoutParams);
        }
        int dimensionPixelOffset = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDimensionPixelOffset(7, -1);
        int dimensionPixelOffset2 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDimensionPixelOffset(3, -1);
        if (dimensionPixelOffset >= 0 || dimensionPixelOffset2 >= 0) {
            int max = Math.max(dimensionPixelOffset, 0);
            int max2 = Math.max(dimensionPixelOffset2, 0);
            toolbar.ensureContentInsets();
            toolbar.mContentInsets.setRelative(max, max2);
        }
        int resourceId2 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(28, 0);
        if (resourceId2 != 0) {
            Context context = toolbar.getContext();
            toolbar.mTitleTextAppearance = resourceId2;
            TextView textView = toolbar.mTitleTextView;
            if (textView != null) {
                textView.setTextAppearance(context, resourceId2);
            }
        }
        int resourceId3 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(26, 0);
        if (resourceId3 != 0) {
            Context context2 = toolbar.getContext();
            toolbar.mSubtitleTextAppearance = resourceId3;
            TextView textView2 = toolbar.mSubtitleTextView;
            if (textView2 != null) {
                textView2.setTextAppearance(context2, resourceId3);
            }
        }
        int resourceId4 = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getResourceId(22, 0);
        if (resourceId4 != 0) {
            toolbar.setPopupTheme(resourceId4);
        }
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
        if (this.mDefaultNavigationContentDescription != R.string.abc_action_bar_up_description) {
            this.mDefaultNavigationContentDescription = R.string.abc_action_bar_up_description;
            if (TextUtils.isEmpty(toolbar.getNavigationContentDescription())) {
                setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
        this.mHomeDescription = toolbar.getNavigationContentDescription();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: android.support.v7.widget.ToolbarWidgetWrapper.1
            final ActionMenuItem mNavItem;

            {
                this.mNavItem = new ActionMenuItem(ToolbarWidgetWrapper.this.mToolbar.getContext(), ToolbarWidgetWrapper.this.mTitle);
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ToolbarWidgetWrapper toolbarWidgetWrapper = ToolbarWidgetWrapper.this;
                Window.Callback callback = toolbarWidgetWrapper.mWindowCallback;
                if (callback != null && toolbarWidgetWrapper.mMenuPrepared) {
                    callback.onMenuItemSelected(0, this.mNavItem);
                }
            }
        });
    }

    private final void setTitleInt(CharSequence charSequence) {
        this.mTitle = charSequence;
        if ((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(charSequence);
            if (this.mTitleSet) {
                ViewCompat.setAccessibilityPaneTitle(this.mToolbar.getRootView(), charSequence);
            }
        }
    }

    private final void updateHomeAccessibility() {
        CharSequence charSequence;
        if ((this.mDisplayOpts & 4) != 0) {
            if (TextUtils.isEmpty(this.mHomeDescription)) {
                Toolbar toolbar = this.mToolbar;
                int i = this.mDefaultNavigationContentDescription;
                if (i != 0) {
                    charSequence = toolbar.getContext().getText(i);
                } else {
                    charSequence = null;
                }
                toolbar.setNavigationContentDescription(charSequence);
                return;
            }
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    private final void updateNavigationIcon() {
        if ((this.mDisplayOpts & 4) != 0) {
            Toolbar toolbar = this.mToolbar;
            Drawable drawable = this.mNavIcon;
            if (drawable == null) {
                drawable = this.mDefaultNavigationIcon;
            }
            toolbar.setNavigationIcon(drawable);
            return;
        }
        this.mToolbar.setNavigationIcon(null);
    }

    private final void updateToolbarLogo() {
        Drawable drawable;
        int i = this.mDisplayOpts;
        if ((i & 2) != 0) {
            if ((i & 1) == 0 || (drawable = this.mLogo) == null) {
                drawable = this.mIcon;
            }
        } else {
            drawable = null;
        }
        this.mToolbar.setLogo(drawable);
    }

    public final boolean canShowOverflowMenu() {
        ActionMenuView actionMenuView;
        Toolbar toolbar = this.mToolbar;
        if (toolbar.getVisibility() == 0 && (actionMenuView = toolbar.mMenuView) != null && actionMenuView.mReserveOverflow) {
            return true;
        }
        return false;
    }

    public final void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    public final void dismissPopupMenus() {
        ActionMenuView actionMenuView = this.mToolbar.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.dismissPopupMenus();
        }
    }

    public final Context getContext() {
        return this.mToolbar.getContext();
    }

    public final int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    public final boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    public final boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter;
        ActionMenuView actionMenuView = this.mToolbar.mMenuView;
        if (actionMenuView != null && (actionMenuPresenter = actionMenuView.mPresenter) != null && actionMenuPresenter.hideOverflowMenu()) {
            return true;
        }
        return false;
    }

    public final boolean isOverflowMenuShowPending() {
        ActionMenuPresenter actionMenuPresenter;
        ActionMenuView actionMenuView = this.mToolbar.mMenuView;
        if (actionMenuView == null || (actionMenuPresenter = actionMenuView.mPresenter) == null) {
            return false;
        }
        if (actionMenuPresenter.mPostedOpenRunnable$ar$class_merging == null && !actionMenuPresenter.isOverflowMenuShowing()) {
            return false;
        }
        return true;
    }

    public final boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    public final void setCollapsible(boolean z) {
        Toolbar toolbar = this.mToolbar;
        toolbar.mCollapsible = false;
        toolbar.requestLayout();
    }

    public final void setDisplayOptions(int i) {
        View view;
        int i2 = this.mDisplayOpts ^ i;
        this.mDisplayOpts = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    updateHomeAccessibility();
                }
                updateNavigationIcon();
            }
            if ((i2 & 3) != 0) {
                updateToolbarLogo();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                } else {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
            }
            if ((i2 & 16) != 0 && (view = this.mCustomView) != null) {
                if ((i & 16) != 0) {
                    this.mToolbar.addView(view);
                } else {
                    this.mToolbar.removeView(view);
                }
            }
        }
    }

    public final void setLogo(Drawable drawable) {
        this.mLogo = drawable;
        updateToolbarLogo();
    }

    public final void setMenu(Menu menu, MenuPresenter.Callback callback) {
        ActionMenuPresenter actionMenuPresenter = this.mActionMenuPresenter;
        if (actionMenuPresenter == null) {
            ActionMenuPresenter actionMenuPresenter2 = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter = actionMenuPresenter2;
            actionMenuPresenter2.mId = R.id.action_menu_presenter;
            actionMenuPresenter = actionMenuPresenter2;
        }
        actionMenuPresenter.mCallback = callback;
        Toolbar toolbar = this.mToolbar;
        if (menu != null || toolbar.mMenuView != null) {
            toolbar.ensureMenuView();
            MenuBuilder menuBuilder = toolbar.mMenuView.mMenu;
            if (menuBuilder == menu) {
                return;
            }
            if (menuBuilder != null) {
                menuBuilder.removeMenuPresenter(toolbar.mOuterActionMenuPresenter);
                menuBuilder.removeMenuPresenter(toolbar.mExpandedMenuPresenter);
            }
            if (toolbar.mExpandedMenuPresenter == null) {
                toolbar.mExpandedMenuPresenter = new Toolbar.ExpandedActionViewMenuPresenter();
            }
            actionMenuPresenter.setExpandedActionViewsExclusive$ar$ds();
            if (menu != null) {
                MenuBuilder menuBuilder2 = (MenuBuilder) menu;
                menuBuilder2.addMenuPresenter(actionMenuPresenter, toolbar.mPopupContext);
                menuBuilder2.addMenuPresenter(toolbar.mExpandedMenuPresenter, toolbar.mPopupContext);
            } else {
                actionMenuPresenter.initForMenu(toolbar.mPopupContext, null);
                toolbar.mExpandedMenuPresenter.initForMenu(toolbar.mPopupContext, null);
                actionMenuPresenter.updateMenuView(true);
                toolbar.mExpandedMenuPresenter.updateMenuView(true);
            }
            toolbar.mMenuView.setPopupTheme(toolbar.mPopupTheme);
            toolbar.mMenuView.setPresenter(actionMenuPresenter);
            toolbar.mOuterActionMenuPresenter = actionMenuPresenter;
            toolbar.updateBackInvokedCallbackState();
        }
    }

    public final void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    public final void setNavigationContentDescription(int i) {
        String string;
        if (i == 0) {
            string = null;
        } else {
            string = getContext().getString(i);
        }
        this.mHomeDescription = string;
        updateHomeAccessibility();
    }

    public final void setNavigationIcon(Drawable drawable) {
        this.mNavIcon = drawable;
        updateNavigationIcon();
    }

    public final void setTitle(CharSequence charSequence) {
        this.mTitleSet = true;
        setTitleInt(charSequence);
    }

    public final void setVisibility(int i) {
        this.mToolbar.setVisibility(i);
    }

    public final void setWindowCallback(Window.Callback callback) {
        this.mWindowCallback = callback;
    }

    public final void setWindowTitle(CharSequence charSequence) {
        if (!this.mTitleSet) {
            setTitleInt(charSequence);
        }
    }

    public final AccessibilityNodeInfoCompat.CollectionItemInfoCompat setupAnimatorToVisibility$ar$class_merging$ar$class_merging(final int i, long j) {
        float f;
        if (i == 0) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat animate$ar$class_merging$ar$class_merging = ViewCompat.animate$ar$class_merging$ar$class_merging(this.mToolbar);
        animate$ar$class_merging$ar$class_merging.alpha$ar$ds(f);
        animate$ar$class_merging$ar$class_merging.setDuration$ar$ds$d0f32809_0(j);
        animate$ar$class_merging$ar$class_merging.setListener$ar$ds$34caea9b_0(new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.widget.ToolbarWidgetWrapper.2
            private boolean mCanceled = false;

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationCancel$ar$ds() {
                this.mCanceled = true;
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                if (!this.mCanceled) {
                    ToolbarWidgetWrapper toolbarWidgetWrapper = ToolbarWidgetWrapper.this;
                    toolbarWidgetWrapper.mToolbar.setVisibility(i);
                }
            }

            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationStart$ar$ds() {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
            }
        });
        return animate$ar$class_merging$ar$class_merging;
    }

    public final boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }
}
