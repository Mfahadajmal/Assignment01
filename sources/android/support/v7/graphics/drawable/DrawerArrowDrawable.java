package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R$styleable;
import androidx.core.graphics.drawable.DrawableCompat$Api23Impl;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DrawerArrowDrawable extends Drawable {
    private static final float ARROW_HEAD_ANGLE = (float) Math.toRadians(45.0d);
    private final float mArrowHeadLength;
    private final float mArrowShaftLength;
    private float mBarGap;
    private final float mBarLength;
    private final int mDirection;
    private float mMaxCutForBarSize;
    private final Paint mPaint;
    private final Path mPath;
    public float mProgress;
    private final int mSize;
    private boolean mSpin;

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPath = new Path();
        this.mDirection = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R$styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        int color = obtainStyledAttributes.getColor(3, 0);
        if (color != paint.getColor()) {
            paint.setColor(color);
            invalidateSelf();
        }
        float dimension = obtainStyledAttributes.getDimension(7, 0.0f);
        if (paint.getStrokeWidth() != dimension) {
            paint.setStrokeWidth(dimension);
            this.mMaxCutForBarSize = (float) ((dimension / 2.0f) * Math.cos(ARROW_HEAD_ANGLE));
            invalidateSelf();
        }
        boolean z = obtainStyledAttributes.getBoolean(6, true);
        if (this.mSpin != z) {
            this.mSpin = z;
            invalidateSelf();
        }
        float round = Math.round(obtainStyledAttributes.getDimension(5, 0.0f));
        if (round != this.mBarGap) {
            this.mBarGap = round;
            invalidateSelf();
        }
        this.mSize = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        this.mBarLength = Math.round(obtainStyledAttributes.getDimension(2, 0.0f));
        this.mArrowHeadLength = Math.round(obtainStyledAttributes.getDimension(0, 0.0f));
        this.mArrowShaftLength = obtainStyledAttributes.getDimension(1, 0.0f);
        obtainStyledAttributes.recycle();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float f;
        int i;
        Rect bounds = getBounds();
        int i2 = this.mDirection;
        boolean z = false;
        if (i2 != 0 && (i2 == 1 || DrawableCompat$Api23Impl.getLayoutDirection(this) == 1)) {
            z = true;
        }
        float f2 = this.mArrowHeadLength;
        float f3 = f2 * f2;
        float sqrt = (float) Math.sqrt(f3 + f3);
        float f4 = this.mBarLength;
        float f5 = this.mProgress;
        float f6 = (sqrt - f4) * f5;
        float f7 = (this.mArrowShaftLength - f4) * f5;
        float round = Math.round(((this.mMaxCutForBarSize + 0.0f) * f5) + 0.0f);
        float f8 = ARROW_HEAD_ANGLE;
        float f9 = this.mProgress;
        float f10 = (f8 + 0.0f) * f9;
        if (true != z) {
            f = -180.0f;
        } else {
            f = 0.0f;
        }
        float f11 = f9 * 180.0f;
        double d = f10 + 0.0f;
        double d2 = f6 + f4;
        float round2 = (float) Math.round(Math.cos(d) * d2);
        float round3 = (float) Math.round(d2 * Math.sin(d));
        this.mPath.rewind();
        float strokeWidth = this.mBarGap + this.mPaint.getStrokeWidth();
        float f12 = ((-this.mMaxCutForBarSize) - strokeWidth) * this.mProgress;
        float f13 = f4 + f7;
        float f14 = (-f13) / 2.0f;
        this.mPath.moveTo(f14 + round, 0.0f);
        this.mPath.rLineTo(f13 - (round + round), 0.0f);
        float f15 = strokeWidth + f12;
        this.mPath.moveTo(f14, f15);
        this.mPath.rLineTo(round2, round3);
        this.mPath.moveTo(f14, -f15);
        this.mPath.rLineTo(round2, -round3);
        this.mPath.close();
        canvas.save();
        float strokeWidth2 = this.mPaint.getStrokeWidth();
        float f16 = this.mBarGap;
        int height = ((int) ((bounds.height() - (3.0f * strokeWidth2)) - (f16 + f16))) / 4;
        canvas.translate(bounds.centerX(), height + height + (strokeWidth2 * 1.5f) + f16);
        if (this.mSpin) {
            float f17 = f + f11;
            if (true != z) {
                i = 1;
            } else {
                i = -1;
            }
            canvas.rotate(f17 * i);
        } else if (z) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (i != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setProgress(float f) {
        if (this.mProgress != f) {
            this.mProgress = f;
            invalidateSelf();
        }
    }
}
