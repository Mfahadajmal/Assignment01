package android.support.v7.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.app.ActionBar;
import android.support.v7.appcompat.R$styleable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContainer;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowDecorActionBar extends ActionBar implements ActionBarOverlayLayout.ActionBarVisibilityCallback {
    private static final Interpolator sHideInterpolator = new AccelerateInterpolator();
    private static final Interpolator sShowInterpolator = new DecelerateInterpolator();
    ActionModeImpl mActionMode;
    public ActionBarContainer mContainerView;
    public boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    public int mCurWindowVisibility;
    public ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    public ToolbarWidgetWrapper mDecorToolbar$ar$class_merging;
    ActionMode mDeferredDestroyActionMode;
    ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    public boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private final ArrayList mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    private Context mThemedContext;
    final FloatingActionButton.ShadowDelegateImpl mUpdateListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ActionModeImpl extends ActionMode implements MenuBuilder.Callback {
        private final Context mActionModeContext;
        public ActionMode.Callback mCallback;
        private WeakReference mCustomView;
        public final MenuBuilder mMenu;

        public ActionModeImpl(Context context, ActionMode.Callback callback) {
            this.mActionModeContext = context;
            this.mCallback = callback;
            MenuBuilder menuBuilder = new MenuBuilder(context);
            menuBuilder.setDefaultShowAsAction$ar$ds();
            this.mMenu = menuBuilder;
            menuBuilder.setCallback(this);
        }

        @Override // android.support.v7.view.ActionMode
        public final void finish() {
            WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
            if (windowDecorActionBar.mActionMode != this) {
                return;
            }
            if (!WindowDecorActionBar.checkShowingFlags$ar$ds(windowDecorActionBar.mHiddenBySystem, false)) {
                windowDecorActionBar.mDeferredDestroyActionMode = this;
                windowDecorActionBar.mDeferredModeDestroyCallback = this.mCallback;
            } else {
                this.mCallback.onDestroyActionMode(this);
            }
            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            ActionBarContextView actionBarContextView = WindowDecorActionBar.this.mContextView;
            if (actionBarContextView.mClose == null) {
                actionBarContextView.killMode();
            }
            WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
            windowDecorActionBar2.mOverlayLayout.setHideOnContentScrollEnabled(windowDecorActionBar2.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
        }

        @Override // android.support.v7.view.ActionMode
        public final View getCustomView() {
            WeakReference weakReference = this.mCustomView;
            if (weakReference != null) {
                return (View) weakReference.get();
            }
            return null;
        }

        @Override // android.support.v7.view.ActionMode
        public final Menu getMenu() {
            return this.mMenu;
        }

        @Override // android.support.v7.view.ActionMode
        public final MenuInflater getMenuInflater() {
            return new SupportMenuInflater(this.mActionModeContext);
        }

        @Override // android.support.v7.view.ActionMode
        public final CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.mSubtitle;
        }

        @Override // android.support.v7.view.ActionMode
        public final CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.mTitle;
        }

        @Override // android.support.v7.view.ActionMode
        public final void invalidate() {
            if (WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, this.mMenu);
            } finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override // android.support.v7.view.ActionMode
        public final boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.mTitleOptional;
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            ActionMode.Callback callback = this.mCallback;
            if (callback != null) {
                return callback.onActionItemClicked(this, menuItem);
            }
            return false;
        }

        @Override // android.support.v7.view.menu.MenuBuilder.Callback
        public final void onMenuModeChange(MenuBuilder menuBuilder) {
            if (this.mCallback == null) {
                return;
            }
            invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu$ar$ds();
        }

        @Override // android.support.v7.view.ActionMode
        public final void setCustomView(View view) {
            WindowDecorActionBar.this.mContextView.setCustomView(view);
            this.mCustomView = new WeakReference(view);
        }

        @Override // android.support.v7.view.ActionMode
        public final void setSubtitle(int i) {
            setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(i));
        }

        @Override // android.support.v7.view.ActionMode
        public final void setTitle(int i) {
            setTitle(WindowDecorActionBar.this.mContext.getResources().getString(i));
        }

        @Override // android.support.v7.view.ActionMode
        public final void setTitleOptionalHint(boolean z) {
            this.mTitleOptionalHint = z;
            WindowDecorActionBar.this.mContextView.setTitleOptional(z);
        }

        @Override // android.support.v7.view.ActionMode
        public final void setSubtitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence);
        }

        @Override // android.support.v7.view.ActionMode
        public final void setTitle(CharSequence charSequence) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence);
        }
    }

    public WindowDecorActionBar(Activity activity, boolean z) {
        new ArrayList();
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.1
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                View view;
                WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
                if (windowDecorActionBar.mContentAnimations && (view = windowDecorActionBar.mContentView) != null) {
                    view.setTranslationY(0.0f);
                    WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                windowDecorActionBar2.mCurrentShowAnim = null;
                ActionMode.Callback callback = windowDecorActionBar2.mDeferredModeDestroyCallback;
                if (callback != null) {
                    callback.onDestroyActionMode(windowDecorActionBar2.mDeferredDestroyActionMode);
                    windowDecorActionBar2.mDeferredDestroyActionMode = null;
                    windowDecorActionBar2.mDeferredModeDestroyCallback = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.Api20Impl.requestApplyInsets(actionBarOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.2
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
                windowDecorActionBar.mCurrentShowAnim = null;
                windowDecorActionBar.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        View decorView = activity.getWindow().getDecorView();
        init(decorView);
        if (z) {
            return;
        }
        this.mContentView = decorView.findViewById(R.id.content);
    }

    static boolean checkShowingFlags$ar$ds(boolean z, boolean z2) {
        if (z2 || !z) {
            return true;
        }
        return false;
    }

    private final void init(View view) {
        String str;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(com.google.android.marvin.talkback.R.id.decor_content_parent);
        this.mOverlayLayout = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.mActionBarVisibilityCallback = this;
            if (actionBarOverlayLayout.getWindowToken() != null) {
                ((WindowDecorActionBar) actionBarOverlayLayout.mActionBarVisibilityCallback).mCurWindowVisibility = actionBarOverlayLayout.mWindowVisibility;
                int i = actionBarOverlayLayout.mLastSystemUiVisibility;
                if (i != 0) {
                    actionBarOverlayLayout.onWindowSystemUiVisibilityChanged(i);
                    ViewCompat.Api20Impl.requestApplyInsets(actionBarOverlayLayout);
                }
            }
        }
        View findViewById = view.findViewById(com.google.android.marvin.talkback.R.id.action_bar);
        if (findViewById instanceof Toolbar) {
            this.mDecorToolbar$ar$class_merging = ((Toolbar) findViewById).getWrapper$ar$class_merging();
            this.mContextView = (ActionBarContextView) view.findViewById(com.google.android.marvin.talkback.R.id.action_context_bar);
            ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(com.google.android.marvin.talkback.R.id.action_bar_container);
            this.mContainerView = actionBarContainer;
            ToolbarWidgetWrapper toolbarWidgetWrapper = this.mDecorToolbar$ar$class_merging;
            if (toolbarWidgetWrapper != null && this.mContextView != null && actionBarContainer != null) {
                this.mContext = toolbarWidgetWrapper.getContext();
                if ((this.mDecorToolbar$ar$class_merging.getDisplayOptions() & 4) != 0) {
                    this.mDisplayHomeAsUpSet = true;
                }
                AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat = new AccessibilityNodeInfoCompat.CollectionItemInfoCompat(this.mContext);
                int i2 = ((Context) collectionItemInfoCompat.mInfo).getApplicationInfo().targetSdkVersion;
                setHasEmbeddedTabs(collectionItemInfoCompat.hasEmbeddedTabs());
                TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(null, R$styleable.ActionBar, com.google.android.marvin.talkback.R.attr.actionBarStyle, 0);
                if (obtainStyledAttributes.getBoolean(14, false)) {
                    ActionBarOverlayLayout actionBarOverlayLayout2 = this.mOverlayLayout;
                    if (actionBarOverlayLayout2.mOverlayMode) {
                        this.mHideOnContentScroll = true;
                        actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
                    } else {
                        throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
                    }
                }
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(12, 0);
                if (dimensionPixelSize != 0) {
                    ViewCompat.Api21Impl.setElevation(this.mContainerView, dimensionPixelSize);
                }
                obtainStyledAttributes.recycle();
                return;
            }
            throw new IllegalStateException(String.valueOf(getClass().getSimpleName()).concat(" can only be used with a compatible window decor layout"));
        }
        StringBuilder sb = new StringBuilder("Can't make a decor toolbar out of ");
        if (findViewById != null) {
            str = findViewById.getClass().getSimpleName();
        } else {
            str = "null";
        }
        sb.append(str);
        throw new IllegalStateException(sb.toString());
    }

    public final void animateToMode(boolean z) {
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat;
        AccessibilityNodeInfoCompat.CollectionItemInfoCompat collectionItemInfoCompat2;
        long j;
        if (z) {
            if (!this.mShowingForMode) {
                this.mShowingForMode = true;
                updateVisibility(false);
            }
        } else if (this.mShowingForMode) {
            this.mShowingForMode = false;
            updateVisibility(false);
        }
        if (this.mContainerView.isLaidOut()) {
            if (z) {
                collectionItemInfoCompat2 = this.mDecorToolbar$ar$class_merging.setupAnimatorToVisibility$ar$class_merging$ar$class_merging(4, 100L);
                collectionItemInfoCompat = this.mContextView.setupAnimatorToVisibility$ar$class_merging$ar$class_merging(0, 200L);
            } else {
                collectionItemInfoCompat = this.mDecorToolbar$ar$class_merging.setupAnimatorToVisibility$ar$class_merging$ar$class_merging(0, 200L);
                collectionItemInfoCompat2 = this.mContextView.setupAnimatorToVisibility$ar$class_merging$ar$class_merging(8, 100L);
            }
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet = new ViewPropertyAnimatorCompatSet();
            viewPropertyAnimatorCompatSet.mAnimators.add(collectionItemInfoCompat2);
            View view = (View) ((WeakReference) collectionItemInfoCompat2.mInfo).get();
            if (view != null) {
                j = view.animate().getDuration();
            } else {
                j = 0;
            }
            View view2 = (View) ((WeakReference) collectionItemInfoCompat.mInfo).get();
            if (view2 != null) {
                view2.animate().setStartDelay(j);
            }
            viewPropertyAnimatorCompatSet.mAnimators.add(collectionItemInfoCompat);
            viewPropertyAnimatorCompatSet.start();
            return;
        }
        if (z) {
            this.mDecorToolbar$ar$class_merging.setVisibility(4);
            this.mContextView.setVisibility(0);
        } else {
            this.mDecorToolbar$ar$class_merging.setVisibility(0);
            this.mContextView.setVisibility(8);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public final void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int size = this.mMenuVisibilityListeners.size();
            for (int i = 0; i < size; i++) {
                ((ActionBar.OnMenuVisibilityListener) this.mMenuVisibilityListeners.get(i)).onMenuVisibilityChanged$ar$ds();
            }
        }
    }

    @Override // android.support.v7.app.ActionBar
    public final Context getThemedContext() {
        if (this.mThemedContext == null) {
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(com.google.android.marvin.talkback.R.attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, i);
            } else {
                this.mThemedContext = this.mContext;
            }
        }
        return this.mThemedContext;
    }

    @Override // android.support.v7.app.ActionBar
    public final void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if (!this.mDisplayHomeAsUpSet) {
            setDisplayHomeAsUpEnabled(z);
        }
    }

    @Override // android.support.v7.app.ActionBar
    public final void setDisplayHomeAsUpEnabled(boolean z) {
        int i;
        if (true != z) {
            i = 0;
        } else {
            i = 4;
        }
        setDisplayOptions(i, 4);
    }

    public final void setDisplayOptions(int i, int i2) {
        int displayOptions = this.mDecorToolbar$ar$class_merging.getDisplayOptions();
        if ((i2 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar$ar$class_merging.setDisplayOptions((i & i2) | ((~i2) & displayOptions));
    }

    public final void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        this.mDecorToolbar$ar$class_merging.setCollapsible(false);
        this.mOverlayLayout.mHasNonEmbeddedTabs = false;
    }

    @Override // android.support.v7.app.ActionBar
    public final void setShowHideAnimationEnabled(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet;
        this.mShowHideAnimationEnabled = z;
        if (!z && (viewPropertyAnimatorCompatSet = this.mCurrentShowAnim) != null) {
            viewPropertyAnimatorCompatSet.cancel();
        }
    }

    @Override // android.support.v7.app.ActionBar
    public final void setTitle(CharSequence charSequence) {
        this.mDecorToolbar$ar$class_merging.setTitle(charSequence);
    }

    @Override // android.support.v7.app.ActionBar
    public final void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar$ar$class_merging.setWindowTitle(charSequence);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateVisibility(boolean r6) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.WindowDecorActionBar.updateVisibility(boolean):void");
    }

    public WindowDecorActionBar(Dialog dialog) {
        new ArrayList();
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.1
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                View view;
                WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
                if (windowDecorActionBar.mContentAnimations && (view = windowDecorActionBar.mContentView) != null) {
                    view.setTranslationY(0.0f);
                    WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar windowDecorActionBar2 = WindowDecorActionBar.this;
                windowDecorActionBar2.mCurrentShowAnim = null;
                ActionMode.Callback callback = windowDecorActionBar2.mDeferredModeDestroyCallback;
                if (callback != null) {
                    callback.onDestroyActionMode(windowDecorActionBar2.mDeferredDestroyActionMode);
                    windowDecorActionBar2.mDeferredDestroyActionMode = null;
                    windowDecorActionBar2.mDeferredModeDestroyCallback = null;
                }
                ActionBarOverlayLayout actionBarOverlayLayout = WindowDecorActionBar.this.mOverlayLayout;
                if (actionBarOverlayLayout != null) {
                    ViewCompat.Api20Impl.requestApplyInsets(actionBarOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() { // from class: android.support.v7.app.WindowDecorActionBar.2
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public final void onAnimationEnd$ar$ds() {
                WindowDecorActionBar windowDecorActionBar = WindowDecorActionBar.this;
                windowDecorActionBar.mCurrentShowAnim = null;
                windowDecorActionBar.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new FloatingActionButton.ShadowDelegateImpl(this);
        init(dialog.getWindow().getDecorView());
    }
}
