package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppCompatSeekBar extends SeekBar {
    private final AppCompatSeekBarHelper mAppCompatSeekBarHelper;

    public AppCompatSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        ThemeUtils.checkAppCompatTheme(this, getContext());
        AppCompatSeekBarHelper appCompatSeekBarHelper = new AppCompatSeekBarHelper(this);
        this.mAppCompatSeekBarHelper = appCompatSeekBarHelper;
        appCompatSeekBarHelper.loadFromAttributes(attributeSet, R.attr.seekBarStyle);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatSeekBarHelper appCompatSeekBarHelper = this.mAppCompatSeekBarHelper;
        Drawable drawable = appCompatSeekBarHelper.mTickMark;
        if (drawable != null && drawable.isStateful() && drawable.setState(appCompatSeekBarHelper.mView.getDrawableState())) {
            appCompatSeekBarHelper.mView.invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.mAppCompatSeekBarHelper.mTickMark;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected final synchronized void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        AppCompatSeekBarHelper appCompatSeekBarHelper = this.mAppCompatSeekBarHelper;
        if (appCompatSeekBarHelper.mTickMark != null) {
            int max = appCompatSeekBarHelper.mView.getMax();
            int i2 = 1;
            if (max > 1) {
                int intrinsicWidth = appCompatSeekBarHelper.mTickMark.getIntrinsicWidth();
                int intrinsicHeight = appCompatSeekBarHelper.mTickMark.getIntrinsicHeight();
                if (intrinsicWidth >= 0) {
                    i = intrinsicWidth >> 1;
                } else {
                    i = 1;
                }
                if (intrinsicHeight >= 0) {
                    i2 = intrinsicHeight >> 1;
                }
                appCompatSeekBarHelper.mTickMark.setBounds(-i, -i2, i, i2);
                int width = (appCompatSeekBarHelper.mView.getWidth() - appCompatSeekBarHelper.mView.getPaddingLeft()) - appCompatSeekBarHelper.mView.getPaddingRight();
                int save = canvas.save();
                canvas.translate(appCompatSeekBarHelper.mView.getPaddingLeft(), appCompatSeekBarHelper.mView.getHeight() / 2);
                for (int i3 = 0; i3 <= max; i3++) {
                    appCompatSeekBarHelper.mTickMark.draw(canvas);
                    canvas.translate(width / max, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
