package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcelable;
import android.support.v7.view.menu.CascadingMenuPopup;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DropDownListView;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.MenuPopupWindow;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.google.android.accessibility.selecttospeak.ui.HighlightScrollingTextView;
import com.google.android.libraries.phenotype.client.stable.PhenotypeProcessReaper;
import com.google.android.marvin.talkback.R;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class StandardMenuPopup extends MenuPopup implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, MenuPresenter, View.OnKeyListener {
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private int mContentWidth;
    private final Context mContext;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private MenuPresenter.Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new AnonymousClass1(this, 0);
    private final View.OnAttachStateChangeListener mAttachStateChangeListener = new CascadingMenuPopup.AnonymousClass2(this, 2);
    private int mDropDownGravity = 0;

    /* compiled from: PG */
    /* renamed from: android.support.v7.view.menu.StandardMenuPopup$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ Object StandardMenuPopup$1$ar$this$0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, int i) {
            this.switching_field = i;
            this.StandardMenuPopup$1$ar$this$0 = obj;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                            HighlightScrollingTextView highlightScrollingTextView = (HighlightScrollingTextView) this.StandardMenuPopup$1$ar$this$0;
                            highlightScrollingTextView.maxHeightPx = highlightScrollingTextView.getMeasuredHeight();
                            ((HighlightScrollingTextView) this.StandardMenuPopup$1$ar$this$0).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            return;
                        }
                        AppCompatSpinner appCompatSpinner = AppCompatSpinner.this;
                        if (appCompatSpinner.isAttachedToWindow() && appCompatSpinner.getGlobalVisibleRect(((AppCompatSpinner.DropdownPopup) this.StandardMenuPopup$1$ar$this$0).mVisibleRect)) {
                            ((AppCompatSpinner.DropdownPopup) this.StandardMenuPopup$1$ar$this$0).computeContentWidth();
                            super/*android.support.v7.widget.ListPopupWindow*/.show();
                            return;
                        } else {
                            ((ListPopupWindow) this.StandardMenuPopup$1$ar$this$0).dismiss();
                            return;
                        }
                    }
                    if (!((AppCompatSpinner) this.StandardMenuPopup$1$ar$this$0).mPopup.isShowing()) {
                        ((AppCompatSpinner) this.StandardMenuPopup$1$ar$this$0).showPopup();
                    }
                    ViewTreeObserver viewTreeObserver = ((AppCompatSpinner) this.StandardMenuPopup$1$ar$this$0).getViewTreeObserver();
                    if (viewTreeObserver != null) {
                        viewTreeObserver.removeOnGlobalLayoutListener(this);
                        return;
                    }
                    return;
                }
                if (((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).isShowing() && ((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).mShowingMenus.size() > 0 && !((ListPopupWindow) ((PhenotypeProcessReaper) ((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).mShowingMenus.get(0)).PhenotypeProcessReaper$ar$executorProvider).mModal) {
                    View view = ((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).mShownAnchorView;
                    if (view != null && view.isShown()) {
                        Iterator it = ((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).mShowingMenus.iterator();
                        while (it.hasNext()) {
                            ((ListPopupWindow) ((PhenotypeProcessReaper) it.next()).PhenotypeProcessReaper$ar$executorProvider).show();
                        }
                        return;
                    }
                    ((CascadingMenuPopup) this.StandardMenuPopup$1$ar$this$0).dismiss();
                    return;
                }
                return;
            }
            if (((StandardMenuPopup) this.StandardMenuPopup$1$ar$this$0).isShowing()) {
                StandardMenuPopup standardMenuPopup = (StandardMenuPopup) this.StandardMenuPopup$1$ar$this$0;
                if (!standardMenuPopup.mPopup.mModal) {
                    View view2 = standardMenuPopup.mShownAnchorView;
                    if (view2 != null && view2.isShown()) {
                        ((StandardMenuPopup) this.StandardMenuPopup$1$ar$this$0).mPopup.show();
                    } else {
                        ((StandardMenuPopup) this.StandardMenuPopup$1$ar$this$0).dismiss();
                    }
                }
            }
        }
    }

    public StandardMenuPopup(Context context, MenuBuilder menuBuilder, View view, int i, boolean z) {
        this.mContext = context;
        this.mMenu = menuBuilder;
        this.mOverflowOnly = z;
        this.mAdapter = new MenuAdapter(menuBuilder, LayoutInflater.from(context), z, R.layout.abc_popup_menu_item_layout);
        this.mPopupStyleAttr = i;
        Resources resources = context.getResources();
        this.mPopupMaxWidth = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.mAnchorView = view;
        this.mPopup = new MenuPopupWindow(context, i);
        menuBuilder.addMenuPresenter(this, context);
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void dismiss() {
        if (isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final boolean flagActionItems() {
        return false;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final ListView getListView() {
        return this.mPopup.mDropDownList;
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final boolean isShowing() {
        if (!this.mWasDismissed && this.mPopup.isShowing()) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        if (menuBuilder == this.mMenu) {
            dismiss();
            MenuPresenter.Callback callback = this.mPresenterCallback;
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        ViewTreeObserver viewTreeObserver = this.mTreeObserver;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        PopupWindow.OnDismissListener onDismissListener = this.mOnDismissListener;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
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
        if (subMenuBuilder.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper = new MenuPopupHelper(this.mContext, subMenuBuilder, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr);
            menuPopupHelper.setPresenterCallback(this.mPresenterCallback);
            menuPopupHelper.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenuBuilder));
            menuPopupHelper.mOnDismissListener = this.mOnDismissListener;
            this.mOnDismissListener = null;
            this.mMenu.close(false);
            MenuPopupWindow menuPopupWindow = this.mPopup;
            int i = menuPopupWindow.mDropDownHorizontalOffset;
            int verticalOffset = menuPopupWindow.getVerticalOffset();
            if ((Gravity.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) == 5) {
                i += this.mAnchorView.getWidth();
            }
            if (!menuPopupHelper.isShowing()) {
                if (menuPopupHelper.mAnchorView != null) {
                    menuPopupHelper.showPopup(i, verticalOffset, true, true);
                }
            }
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
        this.mAnchorView = view;
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void setCallback(MenuPresenter.Callback callback) {
        this.mPresenterCallback = callback;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setForceShowIcon(boolean z) {
        this.mAdapter.mForceShowIcon = z;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setGravity(int i) {
        this.mDropDownGravity = i;
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void setHorizontalOffset(int i) {
        this.mPopup.mDropDownHorizontalOffset = i;
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
        this.mPopup.setVerticalOffset(i);
    }

    @Override // android.support.v7.view.menu.ShowableListMenu
    public final void show() {
        View view;
        if (isShowing()) {
            return;
        }
        if (!this.mWasDismissed && (view = this.mAnchorView) != null) {
            this.mShownAnchorView = view;
            this.mPopup.setOnDismissListener(this);
            MenuPopupWindow menuPopupWindow = this.mPopup;
            menuPopupWindow.mItemClickListener = this;
            menuPopupWindow.setModal$ar$ds();
            View view2 = this.mShownAnchorView;
            ViewTreeObserver viewTreeObserver = this.mTreeObserver;
            ViewTreeObserver viewTreeObserver2 = view2.getViewTreeObserver();
            this.mTreeObserver = viewTreeObserver2;
            if (viewTreeObserver == null) {
                viewTreeObserver2.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
            }
            view2.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            MenuPopupWindow menuPopupWindow2 = this.mPopup;
            menuPopupWindow2.mDropDownAnchorView = view2;
            menuPopupWindow2.mDropDownGravity = this.mDropDownGravity;
            if (!this.mHasContentWidth) {
                this.mContentWidth = measureIndividualMenuWidth$ar$ds(this.mAdapter, this.mContext, this.mPopupMaxWidth);
                this.mHasContentWidth = true;
            }
            this.mPopup.setContentWidth(this.mContentWidth);
            this.mPopup.setInputMethodMode$ar$ds();
            this.mPopup.setEpicenterBounds(this.mEpicenterBounds);
            this.mPopup.show();
            DropDownListView dropDownListView = this.mPopup.mDropDownList;
            dropDownListView.setOnKeyListener(this);
            if (this.mShowTitle && this.mMenu.mHeaderTitle != null) {
                FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.mContext).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup) dropDownListView, false);
                TextView textView = (TextView) frameLayout.findViewById(android.R.id.title);
                if (textView != null) {
                    textView.setText(this.mMenu.mHeaderTitle);
                }
                frameLayout.setEnabled(false);
                dropDownListView.addHeaderView(frameLayout, null, false);
            }
            this.mPopup.setAdapter(this.mAdapter);
            this.mPopup.show();
            return;
        }
        throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void updateMenuView(boolean z) {
        this.mHasContentWidth = false;
        MenuAdapter menuAdapter = this.mAdapter;
        if (menuAdapter != null) {
            menuAdapter.notifyDataSetChanged();
        }
    }

    @Override // android.support.v7.view.menu.MenuPopup
    public final void addMenu(MenuBuilder menuBuilder) {
    }

    @Override // android.support.v7.view.menu.MenuPresenter
    public final void onRestoreInstanceState(Parcelable parcelable) {
    }
}
