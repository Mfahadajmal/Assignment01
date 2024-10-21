package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.google.android.libraries.surveys.internal.view.ScrollableAnswerFragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScrollViewWithSizeCallback extends ScrollView {
    public ScrollableAnswerFragment.ScrollShadowHandler onHeightChangedListener$ar$class_merging;

    public ScrollViewWithSizeCallback(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ScrollView, android.view.View
    protected final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        ScrollableAnswerFragment.ScrollShadowHandler scrollShadowHandler = this.onHeightChangedListener$ar$class_merging;
        if (scrollShadowHandler != null && i4 != i2 && i2 != 0) {
            scrollShadowHandler.updateShadowVisibility(i2);
        }
    }
}
