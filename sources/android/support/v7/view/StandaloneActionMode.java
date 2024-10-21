package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.ActionMode;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.lang.ref.WeakReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StandaloneActionMode extends ActionMode implements MenuBuilder.Callback {
    private final ActionMode.Callback mCallback;
    private final Context mContext;
    private final ActionBarContextView mContextView;
    private WeakReference mCustomView;
    private boolean mFinished;
    public final MenuBuilder mMenu;

    public StandaloneActionMode(Context context, ActionBarContextView actionBarContextView, ActionMode.Callback callback) {
        this.mContext = context;
        this.mContextView = actionBarContextView;
        this.mCallback = callback;
        MenuBuilder menuBuilder = new MenuBuilder(actionBarContextView.getContext());
        menuBuilder.setDefaultShowAsAction$ar$ds();
        this.mMenu = menuBuilder;
        menuBuilder.setCallback(this);
    }

    @Override // android.support.v7.view.ActionMode
    public final void finish() {
        if (this.mFinished) {
            return;
        }
        this.mFinished = true;
        this.mCallback.onDestroyActionMode(this);
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
        return new SupportMenuInflater(this.mContextView.getContext());
    }

    @Override // android.support.v7.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.mContextView.mSubtitle;
    }

    @Override // android.support.v7.view.ActionMode
    public final CharSequence getTitle() {
        return this.mContextView.mTitle;
    }

    @Override // android.support.v7.view.ActionMode
    public final void invalidate() {
        this.mCallback.onPrepareActionMode(this, this.mMenu);
    }

    @Override // android.support.v7.view.ActionMode
    public final boolean isTitleOptional() {
        return this.mContextView.mTitleOptional;
    }

    @Override // android.support.v7.view.menu.MenuBuilder.Callback
    public final boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        return this.mCallback.onActionItemClicked(this, menuItem);
    }

    @Override // android.support.v7.view.menu.MenuBuilder.Callback
    public final void onMenuModeChange(MenuBuilder menuBuilder) {
        invalidate();
        this.mContextView.showOverflowMenu$ar$ds();
    }

    @Override // android.support.v7.view.ActionMode
    public final void setCustomView(View view) {
        WeakReference weakReference;
        this.mContextView.setCustomView(view);
        if (view != null) {
            weakReference = new WeakReference(view);
        } else {
            weakReference = null;
        }
        this.mCustomView = weakReference;
    }

    @Override // android.support.v7.view.ActionMode
    public final void setSubtitle(int i) {
        setSubtitle(this.mContext.getString(i));
    }

    @Override // android.support.v7.view.ActionMode
    public final void setTitle(int i) {
        setTitle(this.mContext.getString(i));
    }

    @Override // android.support.v7.view.ActionMode
    public final void setTitleOptionalHint(boolean z) {
        this.mTitleOptionalHint = z;
        this.mContextView.setTitleOptional(z);
    }

    @Override // android.support.v7.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.mContextView.setSubtitle(charSequence);
    }

    @Override // android.support.v7.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.mContextView.setTitle(charSequence);
    }
}
