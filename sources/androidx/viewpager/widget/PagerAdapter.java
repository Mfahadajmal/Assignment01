package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class PagerAdapter {
    public final DataSetObservable mObservable = new DataSetObservable();
    public DataSetObserver mViewPagerObserver;

    public void destroyItem$ar$ds(ViewGroup viewGroup, Object obj) {
        throw null;
    }

    public abstract int getCount();

    public int getItemPosition$ar$ds() {
        return -1;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        throw null;
    }

    public abstract boolean isViewFromObject(View view, Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setViewPagerObserver(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.mViewPagerObserver = dataSetObserver;
        }
    }

    public void finishUpdate$ar$ds() {
    }

    public void restoreState$ar$ds() {
    }

    public void setPrimaryItem$ar$ds(Object obj) {
    }

    public void startUpdate(ViewGroup viewGroup) {
    }
}
