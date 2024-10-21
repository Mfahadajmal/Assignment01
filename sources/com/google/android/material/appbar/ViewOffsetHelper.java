package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewOffsetHelper {
    private int layoutLeft;
    public int layoutTop;
    public int offsetTop;
    private final boolean verticalOffsetEnabled = true;
    private final View view;

    public ViewOffsetHelper(View view) {
        this.view = view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void applyOffsets() {
        View view = this.view;
        int top = this.offsetTop - (view.getTop() - this.layoutTop);
        int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
        view.offsetTopAndBottom(top);
        View view2 = this.view;
        view2.offsetLeftAndRight(-(view2.getLeft() - this.layoutLeft));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
    }

    public final boolean setTopAndBottomOffset(int i) {
        if (this.offsetTop != i) {
            this.offsetTop = i;
            applyOffsets();
            return true;
        }
        return false;
    }
}
