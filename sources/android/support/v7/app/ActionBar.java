package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ActionBar {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;

        public LayoutParams() {
            super(-2, -2);
            this.gravity = 8388627;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBarLayout);
            this.gravity = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnMenuVisibilityListener {
        void onMenuVisibilityChanged$ar$ds();
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        throw null;
    }

    public Context getThemedContext() {
        throw null;
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        throw null;
    }

    public abstract void setDisplayHomeAsUpEnabled(boolean z);

    public void setShowHideAnimationEnabled(boolean z) {
        throw null;
    }

    public abstract void setTitle(CharSequence charSequence);

    public void setWindowTitle(CharSequence charSequence) {
        throw null;
    }
}
