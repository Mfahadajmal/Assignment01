package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import androidx.core.view.ViewCompat;
import com.google.android.marvin.talkback.R;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
final class AppCompatSeekBarHelper extends AppCompatProgressBarHelper {
    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    public Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private PorterDuff.Mode mTickMarkTintMode;
    public final SeekBar mView;

    public AppCompatSeekBarHelper(SeekBar seekBar) {
        super(seekBar);
        this.mTickMarkTintList = null;
        this.mTickMarkTintMode = null;
        this.mHasTickMarkTint = false;
        this.mHasTickMarkTintMode = false;
        this.mView = seekBar;
    }

    private final void applyTickMarkTint() {
        Drawable drawable = this.mTickMark;
        if (drawable != null) {
            if (this.mHasTickMarkTint || this.mHasTickMarkTintMode) {
                Drawable mutate = drawable.mutate();
                this.mTickMark = mutate;
                if (this.mHasTickMarkTint) {
                    DrawableCompat$Api21Impl.setTintList(mutate, this.mTickMarkTintList);
                }
                if (this.mHasTickMarkTintMode) {
                    DrawableCompat$Api21Impl.setTintMode(this.mTickMark, this.mTickMarkTintMode);
                }
                if (this.mTickMark.isStateful()) {
                    this.mTickMark.setState(this.mView.getDrawableState());
                }
            }
        }
    }

    @Override // android.support.v7.widget.AppCompatProgressBarHelper
    public final void loadFromAttributes(AttributeSet attributeSet, int i) {
        super.loadFromAttributes(attributeSet, R.attr.seekBarStyle);
        ExecutionList.RunnableExecutorPair obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging = ExecutionList.RunnableExecutorPair.obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging(this.mView.getContext(), attributeSet, R$styleable.AppCompatSeekBar, R.attr.seekBarStyle, 0);
        Object obj = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.ExecutionList$RunnableExecutorPair$ar$runnable;
        SeekBar seekBar = this.mView;
        ViewCompat.saveAttributeDataForStyleable(seekBar, seekBar.getContext(), R$styleable.AppCompatSeekBar, attributeSet, (TypedArray) obj, R.attr.seekBarStyle, 0);
        Drawable drawableIfKnown = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawableIfKnown(0);
        if (drawableIfKnown != null) {
            this.mView.setThumb(drawableIfKnown);
        }
        Drawable drawable = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getDrawable(1);
        Drawable drawable2 = this.mTickMark;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mTickMark = drawable;
        if (drawable != null) {
            drawable.setCallback(this.mView);
            DrawableCompat$Api23Impl.setLayoutDirection(drawable, this.mView.getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(this.mView.getDrawableState());
            }
            applyTickMarkTint();
        }
        this.mView.invalidate();
        if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(3)) {
            this.mTickMarkTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getInt(3, -1), this.mTickMarkTintMode);
            this.mHasTickMarkTintMode = true;
        }
        if (obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.hasValue(2)) {
            this.mTickMarkTintList = obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.getColorStateList(2);
            this.mHasTickMarkTint = true;
        }
        obtainStyledAttributes$ar$class_merging$ef38c46f_0$ar$class_merging$ar$class_merging.recycle();
        applyTickMarkTint();
    }
}
