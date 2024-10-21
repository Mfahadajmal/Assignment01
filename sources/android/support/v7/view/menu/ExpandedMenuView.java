package android.support.v7.view.menu;

import android.R;
import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements MenuBuilder.ItemInvoker, MenuView, AdapterView.OnItemClickListener {
    private static final int[] TINT_ATTRS = {R.attr.background, R.attr.divider};
    private MenuBuilder mMenu;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // android.support.v7.view.menu.MenuView
    public final void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    @Override // android.support.v7.view.menu.MenuBuilder.ItemInvoker
    public final boolean invokeItem(MenuItemImpl menuItemImpl) {
        throw null;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.mMenu.performItemAction((MenuItemImpl) getAdapter().getItem(i), 0);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(context, attributeSet, TINT_ATTRS, i, 0);
        if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(0)) {
            setBackgroundDrawable(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(0));
        }
        if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(1)) {
            setDivider(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(1));
        }
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
    }
}
