package com.google.android.accessibility.accessibilitymenu.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ViewPagerAdapter extends PagerAdapter {
    public List widgetList;

    @Override // androidx.viewpager.widget.PagerAdapter
    public final void destroyItem$ar$ds(ViewGroup viewGroup, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        List list = this.widgetList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getItemPosition$ar$ds() {
        return -2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final Object instantiateItem(ViewGroup viewGroup, int i) {
        List list = this.widgetList;
        if (list == null) {
            return null;
        }
        viewGroup.addView((View) list.get(i));
        return this.widgetList.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final boolean isViewFromObject(View view, Object obj) {
        if (view == obj) {
            return true;
        }
        return false;
    }
}
