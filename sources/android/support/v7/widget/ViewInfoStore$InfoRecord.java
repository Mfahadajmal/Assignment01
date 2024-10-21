package android.support.v7.widget;

import androidx.core.util.Pools$SimplePool;
import androidx.core.view.NestedScrollingParentHelper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewInfoStore$InfoRecord {
    static final Pools$SimplePool sPool$ar$class_merging = new Pools$SimplePool(20);
    public int flags;
    public NestedScrollingParentHelper postInfo$ar$class_merging;
    public NestedScrollingParentHelper preInfo$ar$class_merging;

    private ViewInfoStore$InfoRecord() {
    }

    public static ViewInfoStore$InfoRecord obtain() {
        ViewInfoStore$InfoRecord viewInfoStore$InfoRecord = (ViewInfoStore$InfoRecord) sPool$ar$class_merging.acquire();
        if (viewInfoStore$InfoRecord == null) {
            return new ViewInfoStore$InfoRecord();
        }
        return viewInfoStore$InfoRecord;
    }

    public static void recycle(ViewInfoStore$InfoRecord viewInfoStore$InfoRecord) {
        viewInfoStore$InfoRecord.flags = 0;
        viewInfoStore$InfoRecord.preInfo$ar$class_merging = null;
        viewInfoStore$InfoRecord.postInfo$ar$class_merging = null;
        sPool$ar$class_merging.release(viewInfoStore$InfoRecord);
    }
}
