package android.support.v7.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ActivityChooserView$InnerLayout extends LinearLayout {
    private static final int[] TINT_ATTRS = {R.attr.background};

    public ActivityChooserView$InnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging(context, attributeSet, TINT_ATTRS);
        setBackgroundDrawable(obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.getDrawable(0));
        obtainStyledAttributes$ar$class_merging$4a1e2eef_0$ar$class_merging$ar$class_merging.recycle();
    }
}
