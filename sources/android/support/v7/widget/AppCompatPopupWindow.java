package android.support.v7.widget;

import android.content.Context;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.widget.PopupWindow;
import androidx.core.widget.PopupWindowCompat$Api23Impl;
import com.google.common.util.concurrent.ExecutionList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatPopupWindow extends PopupWindow {
    public AppCompatPopupWindow(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(context, attributeSet, R$styleable.PopupWindow, i, 0);
        if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(2)) {
            PopupWindowCompat$Api23Impl.setOverlapAnchor(this, obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getBoolean(2, false));
        }
        setBackgroundDrawable(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(0));
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
    }
}
