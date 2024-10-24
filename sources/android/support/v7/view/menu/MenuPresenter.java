package android.support.v7.view.menu;

import android.content.Context;
import android.os.Parcelable;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface MenuPresenter {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Callback {
        void onCloseMenu(MenuBuilder menuBuilder, boolean z);

        boolean onOpenSubMenu(MenuBuilder menuBuilder);
    }

    boolean collapseItemActionView$ar$ds(MenuItemImpl menuItemImpl);

    boolean expandItemActionView$ar$ds(MenuItemImpl menuItemImpl);

    boolean flagActionItems();

    int getId();

    void initForMenu(Context context, MenuBuilder menuBuilder);

    void onCloseMenu(MenuBuilder menuBuilder, boolean z);

    void onRestoreInstanceState(Parcelable parcelable);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder);

    void setCallback(Callback callback);

    void updateMenuView(boolean z);
}
