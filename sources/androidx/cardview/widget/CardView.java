package androidx.cardview.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.cardview.R$styleable;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.AnimationHandler;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = {R.attr.colorBackground};
    public static final AppCompatTextViewAutoSizeHelper.Impl IMPL$ar$class_merging$ar$class_merging = new AppCompatTextViewAutoSizeHelper.Impl(null);
    public final AnimationHandler.DurationScaleChangeListener33 mCardViewDelegate$ar$class_merging$ar$class_merging;
    public boolean mCompatPadding;
    public final Rect mContentPadding;
    public boolean mPreventCornerOverlap;
    public final Rect mShadowBounds;

    public CardView(Context context) {
        this(context, null);
    }

    public static /* synthetic */ void access$001(CardView cardView, int i, int i2, int i3, int i4) {
        super.setPadding(i, i2, i3, i4);
    }

    public final float getMaxCardElevation() {
        return IMPL$ar$class_merging$ar$class_merging.getMaxElevation$ar$class_merging$ar$class_merging(this.mCardViewDelegate$ar$class_merging$ar$class_merging);
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    public CardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.marvin.talkback.R.attr.cardViewStyle);
    }

    public CardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int color;
        ColorStateList valueOf;
        Rect rect = new Rect();
        this.mContentPadding = rect;
        this.mShadowBounds = new Rect();
        AnimationHandler.DurationScaleChangeListener33 durationScaleChangeListener33 = new AnimationHandler.DurationScaleChangeListener33(this);
        this.mCardViewDelegate$ar$class_merging$ar$class_merging = durationScaleChangeListener33;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CardView, i, com.google.android.marvin.talkback.R.style.CardView);
        ViewCompat.saveAttributeDataForStyleable(this, context, R$styleable.CardView, attributeSet, obtainStyledAttributes, i, com.google.android.marvin.talkback.R.style.CardView);
        if (obtainStyledAttributes.hasValue(2)) {
            valueOf = obtainStyledAttributes.getColorStateList(2);
        } else {
            TypedArray obtainStyledAttributes2 = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int color2 = obtainStyledAttributes2.getColor(0, 0);
            obtainStyledAttributes2.recycle();
            float[] fArr = new float[3];
            Color.colorToHSV(color2, fArr);
            if (fArr[2] > 0.5f) {
                color = getResources().getColor(com.google.android.marvin.talkback.R.color.cardview_light_background);
            } else {
                color = getResources().getColor(com.google.android.marvin.talkback.R.color.cardview_dark_background);
            }
            valueOf = ColorStateList.valueOf(color);
        }
        ColorStateList colorStateList = valueOf;
        float dimension = obtainStyledAttributes.getDimension(3, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(4, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(5, 0.0f);
        this.mCompatPadding = obtainStyledAttributes.getBoolean(7, false);
        this.mPreventCornerOverlap = obtainStyledAttributes.getBoolean(6, true);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
        rect.left = obtainStyledAttributes.getDimensionPixelSize(10, dimensionPixelSize);
        rect.top = obtainStyledAttributes.getDimensionPixelSize(12, dimensionPixelSize);
        rect.right = obtainStyledAttributes.getDimensionPixelSize(11, dimensionPixelSize);
        rect.bottom = obtainStyledAttributes.getDimensionPixelSize(9, dimensionPixelSize);
        float f = dimension2 > dimension3 ? dimension2 : dimension3;
        obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        IMPL$ar$class_merging$ar$class_merging.initialize$ar$class_merging$4b34ab49_0$ar$ds$ar$class_merging(durationScaleChangeListener33, colorStateList, dimension, dimension2, f);
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
    }
}
