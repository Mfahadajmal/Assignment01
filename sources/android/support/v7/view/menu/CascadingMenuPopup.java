package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.view.menu.StandardMenuPopup;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.core.view.ViewCompat;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import com.google.android.material.textfield.EndCompoundLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CascadingMenuPopup extends MenuPopup implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener {
    private View mAnchorView;
    private final Context mContext;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final int mPopupStyleAttr;
    private MenuPresenter.Callback mPresenterCallback;
    public boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    View mShownAnchorView;
    public final Handler mSubMenuHoverHandler;
    ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;
    private final List mPendingMenus = new ArrayList();
    public final List mShowingMenus = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new StandardMenuPopup.AnonymousClass1(this, 1);
    private final View.OnAttachStateChangeListener mAttachStateChangeListener = new AnonymousClass2(this, 0);
    private final MenuItemHoverListener mMenuItemHoverListener = new AnonymousClass3();
    private int mRawDropDownGravity = 0;
    private int mDropDownGravity = 0;
    private boolean mForceShowIcon = false;
    private int mLastPosition = getInitialMenuPosition();

    /* compiled from: PG */
    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 implements View.OnAttachStateChangeListener {
        final /* synthetic */ Object CascadingMenuPopup$2$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass2(Object obj, int i) {
            this.switching_field = i;
            this.CascadingMenuPopup$2$ar$this$0 = obj;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        ((EndCompoundLayout) this.CascadingMenuPopup$2$ar$this$0).addTouchExplorationStateChangeListenerIfNeeded();
                    }
                } else {
                    ((View) this.CascadingMenuPopup$2$ar$this$0).removeOnAttachStateChangeListener(this);
                    ViewCompat.Api20Impl.requestApplyInsets((View) this.CascadingMenuPopup$2$ar$this$0);
                }
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        ((EndCompoundLayout) this.CascadingMenuPopup$2$ar$this$0).removeTouchExplorationStateChangeListenerIfNeeded();
                        return;
                    }
                    ViewTreeObserver viewTreeObserver = ((StandardMenuPopup) this.CascadingMenuPopup$2$ar$this$0).mTreeObserver;
                    if (viewTreeObserver != null) {
                        if (!viewTreeObserver.isAlive()) {
                            ((StandardMenuPopup) this.CascadingMenuPopup$2$ar$this$0).mTreeObserver = view.getViewTreeObserver();
                        }
                        StandardMenuPopup standardMenuPopup = (StandardMenuPopup) this.CascadingMenuPopup$2$ar$this$0;
                        standardMenuPopup.mTreeObserver.removeGlobalOnLayoutListener(standardMenuPopup.mGlobalLayoutListener);
                    }
                    view.removeOnAttachStateChangeListener(this);
                    return;
                }
                return;
            }
            ViewTreeObserver viewTreeObserver2 = ((CascadingMenuPopup) this.CascadingMenuPopup$2$ar$this$0).mTreeObserver;
            if (viewTreeObserver2 != null) {
                if (!viewTreeObserver2.isAlive()) {
                    ((CascadingMenuPopup) this.CascadingMenuPopup$2$ar$this$0).mTreeObserver = view.getViewTreeObserver();
                }
                CascadingMenuPopup cascadingMenuPopup = (CascadingMenuPopup) this.CascadingMenuPopup$2$ar$this$0;
                cascadingMenuPopup.mTreeObserver.removeGlobalOnLayoutListener(cascadingMenuPopup.mGlobalLayoutListener);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* compiled from: PG */
    /* renamed from: android.support.v7.view.menu.CascadingMenuPopup$3, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements MenuItemHoverListener {
        public AnonymousClass3() {
        }
    }

    public CascadingMenuPopup(Context context, View view, int i, boolean z) {
        this.mContext = context;
        this.mAnchorView = view;
        this.mPopupStyleAttr = i;
        this.mOverflowOnly = z;
        Resources resources = context.getResources();
        this.mMenuMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    private final int getInitialMenuPosition() {
        if (this.mAnchorView.getLayoutDirection() != 1) {
            return 1;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0164  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void showMenu(android.support.v7.view.menu.MenuBuilder r17) {
        /*
            Method dump skipped, instructions count: 442
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.view.menu.CascadingMenuPopup.showMenu(android.support.v7.view.menu.MenuBuilder):void");
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void addMenu(MenuBuilder menuBuilder) {
        menuBuilder.addMenuPresenter(this, this.mContext);
        if (isShowing()) {
            showMenu(menuBuilder);
        } else {
            this.mPendingMenus.add(menuBuilder);
        }
    }

    @Override // android.support.v7.view.menu.MenuPopup
    protected final boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void dismiss() {
        int size = this.mShowingMenus.size();
        if (size > 0) {
            PhenotypeProcessReaper[] phenotypeProcessReaperArr = (PhenotypeProcessReaper[]) this.mShowingMenus.toArray(new PhenotypeProcessReaper[size]);
            while (true) {
                size--;
                if (size >= 0) {
                    PhenotypeProcessReaper phenotypeProcessReaper = phenotypeProcessReaperArr[size];
                    if (((ListPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).isShowing()) {
                        ((ListPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).dismiss();
                    }
                } else {
                    return;
                }
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean flagActionItems() {
        return false;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final ListView getListView() {
        if (this.mShowingMenus.isEmpty()) {
            return null;
        }
        return ((PhenotypeProcessReaper) this.mShowingMenus.get(r0.size() - 1)).getListView();
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final boolean isShowing() {
        if (this.mShowingMenus.size() <= 0 || !((ListPopupWindow) ((PhenotypeProcessReaper) this.mShowingMenus.get(0)).PhenotypeProcessReaper$ar$executorProvider).isShowing()) {
            return false;
        }
        return true;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        int size = this.mShowingMenus.size();
        int i = 0;
        while (true) {
            if (i < size) {
                if (menuBuilder == ((PhenotypeProcessReaper) this.mShowingMenus.get(i)).PhenotypeProcessReaper$ar$isKillable) {
                    break;
                } else {
                    i++;
                }
            } else {
                i = -1;
                break;
            }
        }
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 < this.mShowingMenus.size()) {
                ((MenuBuilder) ((PhenotypeProcessReaper) this.mShowingMenus.get(i2)).PhenotypeProcessReaper$ar$isKillable).close(false);
            }
            PhenotypeProcessReaper phenotypeProcessReaper = (PhenotypeProcessReaper) this.mShowingMenus.remove(i);
            ((MenuBuilder) phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).removeMenuPresenter(this);
            if (this.mShouldCloseImmediately) {
                MenuPopupWindow.Api23Impl.setExitTransition(((MenuPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).mPopup, null);
                ((ListPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).mPopup.setAnimationStyle(0);
            }
            ((ListPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).dismiss();
            int size2 = this.mShowingMenus.size();
            if (size2 > 0) {
                this.mLastPosition = ((PhenotypeProcessReaper) this.mShowingMenus.get(size2 - 1)).pollingMinutes;
            } else {
                this.mLastPosition = getInitialMenuPosition();
            }
            if (size2 == 0) {
                dismiss();
                MenuPresenter.Callback callback = this.mPresenterCallback;
                if (callback != null) {
                    callback.onCloseMenu(menuBuilder, true);
                }
                ViewTreeObserver viewTreeObserver = this.mTreeObserver;
                if (viewTreeObserver != null) {
                    if (viewTreeObserver.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mOnDismissListener.onDismiss();
                return;
            }
            if (z) {
                ((MenuBuilder) ((PhenotypeProcessReaper) this.mShowingMenus.get(0)).PhenotypeProcessReaper$ar$isKillable).close(false);
            }
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        PhenotypeProcessReaper phenotypeProcessReaper;
        int size = this.mShowingMenus.size();
        int i = 0;
        while (true) {
            if (i < size) {
                phenotypeProcessReaper = (PhenotypeProcessReaper) this.mShowingMenus.get(i);
                if (!((ListPopupWindow) phenotypeProcessReaper.PhenotypeProcessReaper$ar$executorProvider).isShowing()) {
                    break;
                } else {
                    i++;
                }
            } else {
                phenotypeProcessReaper = null;
                break;
            }
        }
        if (phenotypeProcessReaper != null) {
            ((MenuBuilder) phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable).close(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 && i == 82) {
            dismiss();
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        for (PhenotypeProcessReaper phenotypeProcessReaper : this.mShowingMenus) {
            if (subMenuBuilder == phenotypeProcessReaper.PhenotypeProcessReaper$ar$isKillable) {
                phenotypeProcessReaper.getListView().requestFocus();
                return true;
            }
        }
        if (subMenuBuilder.hasVisibleItems()) {
            addMenu(subMenuBuilder);
            MenuPresenter.Callback callback = this.mPresenterCallback;
            if (callback != null) {
                callback.onOpenSubMenu(subMenuBuilder);
            }
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setAnchorView(View view) {
        if (this.mAnchorView != view) {
            this.mAnchorView = view;
            this.mDropDownGravity = Gravity.getAbsoluteGravity(this.mRawDropDownGravity, view.getLayoutDirection());
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setGravity(int i) {
        if (this.mRawDropDownGravity != i) {
            this.mRawDropDownGravity = i;
            this.mDropDownGravity = Gravity.getAbsoluteGravity(i, this.mAnchorView.getLayoutDirection());
        }
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setHorizontalOffset(int i) {
        this.mHasXOffset = true;
        this.mXOffset = i;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setVerticalOffset(int i) {
        this.mHasYOffset = true;
        this.mYOffset = i;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void show() {
        if (!isShowing()) {
            Iterator it = this.mPendingMenus.iterator();
            while (it.hasNext()) {
                showMenu((MenuBuilder) it.next());
            }
            this.mPendingMenus.clear();
            View view = this.mAnchorView;
            this.mShownAnchorView = view;
            if (view != null) {
                ViewTreeObserver viewTreeObserver = this.mTreeObserver;
                ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
                this.mTreeObserver = viewTreeObserver2;
                if (viewTreeObserver == null) {
                    viewTreeObserver2.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            }
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void updateMenuView(boolean z) {
        Iterator it = this.mShowingMenus.iterator();
        while (it.hasNext()) {
            toMenuAdapter(((PhenotypeProcessReaper) it.next()).getListView().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onRestoreInstanceState(Parcelable parcelable) {
    }
}
