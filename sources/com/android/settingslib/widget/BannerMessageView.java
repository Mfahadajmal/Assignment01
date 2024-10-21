package com.android.settingslib.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BannerMessageView extends LinearLayout {
    private Rect mTouchTargetForDismissButton;

    public BannerMessageView(Context context) {
        super(context);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        super.onLayout(z, i, i2, i3, i4);
        if (this.mTouchTargetForDismissButton == null) {
            View findViewById = findViewById(R.id.top_row);
            View findViewById2 = findViewById(R.id.banner_dismiss_btn);
            if (findViewById != null && findViewById2 != null && findViewById2.getVisibility() == 0) {
                int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.settingslib_preferred_minimum_touch_target);
                int width = findViewById2.getWidth();
                int height = findViewById2.getHeight();
                int i8 = 0;
                if (width < dimensionPixelSize) {
                    i5 = dimensionPixelSize - width;
                } else {
                    i5 = 0;
                }
                if (height < dimensionPixelSize) {
                    i8 = dimensionPixelSize - height;
                }
                Rect rect = new Rect();
                findViewById2.getHitRect(rect);
                Rect rect2 = new Rect();
                findViewById.getHitRect(rect2);
                Rect rect3 = new Rect();
                this.mTouchTargetForDismissButton = rect3;
                rect3.left = rect2.left + rect.left;
                this.mTouchTargetForDismissButton.right = rect2.left + rect.right;
                this.mTouchTargetForDismissButton.top = rect2.top + rect.top;
                this.mTouchTargetForDismissButton.bottom = rect2.top + rect.bottom;
                Rect rect4 = this.mTouchTargetForDismissButton;
                int i9 = rect4.left;
                if (i5 % 2 == 1) {
                    i6 = (i5 / 2) + 1;
                } else {
                    i6 = i5 / 2;
                }
                rect4.left = i9 - i6;
                Rect rect5 = this.mTouchTargetForDismissButton;
                int i10 = rect5.top;
                if (i8 % 2 == 1) {
                    i7 = (i8 / 2) + 1;
                } else {
                    i7 = i8 / 2;
                }
                rect5.top = i10 - i7;
                this.mTouchTargetForDismissButton.right += i5 / 2;
                this.mTouchTargetForDismissButton.bottom += i8 / 2;
                setTouchDelegate(new TouchDelegate(this.mTouchTargetForDismissButton, findViewById2));
            }
        }
    }

    public BannerMessageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BannerMessageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public BannerMessageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }
}
